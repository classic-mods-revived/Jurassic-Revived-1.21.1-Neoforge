package net.jurassicrevived.jurassicrevived.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.List;
import java.util.function.Supplier;

public class CustomGenderedSpawnEggItem extends DeferredSpawnEggItem {
    private static final String KEY_SELECTED_VARIANT = "SelectedVariant";
    private static final String KEY_VARIANT = "Variant";
    private static final int VARIANT_COUNT = 2; // 0=Male, 1=Female
    private final Supplier<? extends EntityType<? extends Mob>> spawnTypeSupplier;

    public CustomGenderedSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type,
                                      int backgroundColor,
                                      int highlightColor,
                                      Properties properties) {
        super(type, backgroundColor, highlightColor, properties);
        this.spawnTypeSupplier = type;
    }

    // --- Selection stored on the item via the CUSTOM_DATA component ---

    private static int getSelectedVariant(ItemStack stack) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) return 0;
        CompoundTag tag = data.copyTag();
        if (!tag.contains(KEY_SELECTED_VARIANT)) return 0;
        return Math.floorMod(tag.getInt(KEY_SELECTED_VARIANT), VARIANT_COUNT);
    }

    private static void setSelectedVariant(ItemStack stack, int variant) {
        int v = Math.floorMod(variant, VARIANT_COUNT);
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, existing -> {
            CompoundTag tag = existing.copyTag();
            tag.putInt(KEY_SELECTED_VARIANT, v);
            return CustomData.of(tag);
        });
    }

    private static void cycleVariant(ItemStack stack) {
        setSelectedVariant(stack, (getSelectedVariant(stack) + 1) % VARIANT_COUNT);
    }

    // --- Inject the chosen Variant into the ENTITY_DATA component so the mob reads it ---

    private static void ensureEntityDataHasVariant(ItemStack stack) {
        final int variant = getSelectedVariant(stack);
        stack.update(DataComponents.ENTITY_DATA, CustomData.EMPTY, existing -> {
            CompoundTag tag = existing.copyTag();
            tag.putInt(KEY_VARIANT, variant);
            return CustomData.of(tag);
        });
    }
    private static void clearEntityData(ItemStack stack) {
        // Ensure we don't persist a partial entity tag (which would lack an "id")
        stack.remove(DataComponents.ENTITY_DATA);
    }

    // --- Interactions ---

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Shift-right-click in air toggles variant selection
        if (player.isSecondaryUseActive()) {
            if (!level.isClientSide) {
                cycleVariant(stack);
                level.playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        SoundEvents.EXPERIENCE_ORB_PICKUP,
                        SoundSource.PLAYERS,
                        0.5f, 1.1f
                );
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return super.use(level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        // If sneaking and using on a block, toggle gender and DO NOT spawn
        if (player != null && player.isSecondaryUseActive()) {
            Level level = context.getLevel();
            if (!level.isClientSide) {
                cycleVariant(context.getItemInHand());
                level.playSound(
                        null,
                        context.getClickedPos(),
                        SoundEvents.EXPERIENCE_ORB_PICKUP,
                        SoundSource.PLAYERS,
                        0.5f, 1.1f
                );
            }
            return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
        }

        // If actually spawning (not holding secondary-use), inject the Variant only for this call,
        // then clear it to avoid persisting invalid entity data in inventories/saves.
        if (player == null || !player.isSecondaryUseActive()) {
            ensureEntityDataHasVariant(context.getItemInHand());
            InteractionResult result = super.useOn(context);
            clearEntityData(context.getItemInHand());
            return result;
        }
        // ... existing code ...
        return super.useOn(context);
    }

    // Ensure baby matches the clicked parent's gender when using the egg on an entity
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        // Only attempt matching if target is a Mob and same type as this egg
        if (target instanceof Mob mob) {
            EntityType<? extends Mob> eggType = this.spawnTypeSupplier.get();
            if (mob.getType() == eggType) {
                int parentVariant = readVariantFromEntity(mob);

                int previous = getSelectedVariant(stack);
                setSelectedVariant(stack, parentVariant);
                ensureEntityDataHasVariant(stack);
                try {
                    return super.interactLivingEntity(stack, player, target, hand);
                } finally {
                    clearEntityData(stack);
                    setSelectedVariant(stack, previous);
                }
            }
        }

        return super.interactLivingEntity(stack, player, target, hand);
    }

    // Best-effort extraction of the parent's gender/variant
    private int readVariantFromEntity(Mob mob) {
        CompoundTag nbt = new CompoundTag();
        mob.saveWithoutId(nbt);
        if (nbt.contains(KEY_VARIANT)) {
            return Math.floorMod(nbt.getInt(KEY_VARIANT), VARIANT_COUNT);
        }

        CompoundTag pd = mob.getPersistentData();
        if (pd != null && pd.contains(KEY_VARIANT)) {
            return Math.floorMod(pd.getInt(KEY_VARIANT), VARIANT_COUNT);
        }

        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, ctx, tooltip, flag);

        int v = getSelectedVariant(stack);
        String genderText = (v == 0) ? "Male" : "Female";

        tooltip.add(Component.translatable("tooltip.jurassicrevived.gender", genderText));
        tooltip.add(Component.translatable("tooltip.jurassicrevived.gender.hint", "Shift-Right-Click"));
    }

    @Override
    public Component getName(ItemStack stack) {
        Component base = super.getName(stack);
        boolean male = getSelectedVariant(stack) == 0;
        Component gender = Component.literal(male ? "Male" : "Female")
                .withStyle(male ? ChatFormatting.AQUA : ChatFormatting.LIGHT_PURPLE);

        // Example: "Velociraptor Spawn Egg (Male)"
        return base.copy().append(Component.literal(" (")).append(gender).append(Component.literal(")"));
    }
}