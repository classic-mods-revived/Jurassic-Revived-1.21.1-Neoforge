package net.cmr.jurassicrevived.compat;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.custom.EggBlock;
import net.cmr.jurassicrevived.block.entity.custom.EggBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.api.ui.ProgressStyle;
import snownee.jade.api.ui.ScreenDirection;
import snownee.jade.api.ui.BoxStyle;

// ... existing code ...
@WailaPlugin
public class EggJadePlugin implements IWailaPlugin {
    private static final ResourceLocation EGG_UID = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "egg");
    private static final String NBT_SECS = "jr_secs";
    private static final int TOTAL_SECS = 5;

    @Override
    public void registerClient(IWailaClientRegistration reg) {
        reg.registerBlockComponent(new snownee.jade.api.IBlockComponentProvider() {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
                CompoundTag data = accessor.getServerData();
                if (data == null || !data.contains(NBT_SECS)) return;

                int secs = data.getInt(NBT_SECS);
                float ratio = Mth.clamp(1.0f - (secs / (float) TOTAL_SECS), 0.0f, 1.0f);

                IElementHelper h = IElementHelper.get();

                // Theme the progress bar
                ProgressStyle style = h.progressStyle()
                        .color(0xFFFFFFFF, 0xFFFFFFFF)   // bg (semi), fg (green)
                        .direction(ScreenDirection.RIGHT) // RIGHT/LEFT/UP/DOWN
                        .fitContentX(true)
                        .fitContentY(true);

                // Use available BoxStyle factories
                BoxStyle box = BoxStyle.getNestedBox(); // or BoxStyle.getViewGroup()
                // Full overload: ratio, label (nullable OK but avoid null), style, box, showText
                IElement progress = h.progress(ratio, Component.empty(), style, box, true);
                tooltip.add(progress);

                tooltip.add(Component.translatable("tooltip.jurassicrevived.egg.hatches_in_seconds", secs)
                        .withStyle(ChatFormatting.YELLOW));
            }

            @Override
            public ResourceLocation getUid() {
                return EGG_UID;
            }
        }, EggBlock.class);
    }

    @Override
    public void register(IWailaCommonRegistration reg) {
        reg.registerBlockDataProvider(new IServerDataProvider<BlockAccessor>() {
            @Override
            public void appendServerData(CompoundTag data, BlockAccessor accessor) {
                BlockEntity be = accessor.getBlockEntity();
                if (be instanceof EggBlockEntity egg) {
                    int secs = egg.getSecondsRemaining(accessor.getLevel());
                    data.putInt(NBT_SECS, secs);
                }
            }

            @Override
            public ResourceLocation getUid() {
                return EGG_UID;
            }
        }, EggBlockEntity.class);
    }
}