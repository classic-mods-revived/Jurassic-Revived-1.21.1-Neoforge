package net.cmr.jurassicrevived.entity.custom;

import net.cmr.jurassicrevived.entity.ModEntities;
import net.cmr.jurassicrevived.entity.client.BrachiosaurusVariant;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;

public class BrachiosaurusEntity extends Animal implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(BrachiosaurusEntity.class, EntityDataSerializers.INT);

    // Procedural tail sway state (client-side use for rendering)
    private float tailSwayOffset;   // Smoothed offset in range roughly [-1, 1]
    private float tailSwayVelocity; // Internal velocity for spring-damper
    private float tailSwayPrev;     // Previous frame value for interpolation

    public BrachiosaurusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, DilophosaurusEntity.class, (float) 20, 1, 1));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, VelociraptorEntity.class, (float) 20, 1, 1));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, CeratosaurusEntity.class, (float) 20, 1, 1));
        //this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, SpinosaurusEntity.class, (float) 20, 1, 1));
        this.goalSelector.addGoal(7, new AvoidEntityGoal<>(this, TyrannosaurusRexEntity.class, (float) 20, 1, 1));
        this.goalSelector.addGoal(8, new MeleeAttackGoal(this, 1, false) {
            private double getAttackReachSqr(LivingEntity entity) {
                return 49;
            }
        });
        this.goalSelector.addGoal(9, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(9, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(10, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(12, new FollowMobGoal(this, 1, (float) 20, (float) 10));
        this.goalSelector.addGoal(13, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 500D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR, 0D)
                .add(Attributes.FOLLOW_RANGE, 32D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D)
                .add(Attributes.ATTACK_DAMAGE, 75D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.KELP);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        AgeableMob child = ModEntities.BRACHIOSAURUS.get().create(pLevel);
        if (child instanceof BrachiosaurusEntity baby) {
            BrachiosaurusVariant randomVariant = Util.getRandom(BrachiosaurusVariant.values(), this.random);
            baby.setVariant(randomVariant);
        }
        return child;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hit = super.doHurtTarget(target);
        if (!level().isClientSide && hit && target instanceof LivingEntity) {
            if (this.level() instanceof ServerLevel serverLevel) {
                this.triggerAnim("attackController", "attack");
            }
        }
        return hit;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walk/Run/Idle", state -> {
            if (state.isMoving())
                return state.setAndContinue(BrachiosaurusEntity.this.isSprinting() ? RawAnimation.begin().then("anim.brachiosaurus.run", Animation.LoopType.LOOP) : RawAnimation.begin().then("anim.brachiosaurus.walk", Animation.LoopType.LOOP));

            return state.setAndContinue(RawAnimation.begin().then("anim.brachiosaurus.idle", Animation.LoopType.LOOP));
        }));

        controllers.add(new AnimationController<>(this, "attackController", state -> PlayState.STOP)
                .triggerableAnim("attack", RawAnimation.begin().then("anim.brachiosaurus.attack", Animation.LoopType.PLAY_ONCE)));
    }

    private float getSignedTurnDelta() {
        // Only consider the body (torso) rotation so head look does not affect tail sway
        return Mth.wrapDegrees(this.yBodyRot - this.yBodyRotO);
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            // Capture previous for smooth interpolation between ticks
            this.tailSwayPrev = this.tailSwayOffset;
            updateProceduralTailSway();
        }
    }

    private void updateProceduralTailSway() {
        // Turn input derived from rotation deltas; works even when standing still and turning
        float turnDegrees = getSignedTurnDelta();

        // Deadzone to ignore tiny jitter so the tail can return to center cleanly
        float deadzoneDeg = 0.6f; // smaller deadzone for more responsiveness
        float turnInput = 0.0f;
        if (Math.abs(turnDegrees) >= deadzoneDeg) {
            // Higher sensitivity so small in-place turns still affect the model
            turnInput = Mth.clamp(turnDegrees / 15.0f, -1.0f, 1.0f);
        }

        // Target offset: keep intuitive sign (positive input -> positive sway)
        float target = turnInput;

        // One-pole low-pass (no bounce). Larger alpha => snappier and less "stiff".
        float alpha = 0.24f; // try 0.20–0.30 to taste

        tailSwayOffset += (target - tailSwayOffset) * alpha;

        // Snap tiny residuals to zero so it visibly settles
        if (Math.abs(tailSwayOffset) < 0.003f) {
            tailSwayOffset = 0.0f;
        }

        // No oscillation velocity retained
        tailSwayVelocity = 0.0f;

        tailSwayOffset = Mth.clamp(tailSwayOffset, -1.5f, 1.5f);
    }

    // Expose to the model for bone rotation
    public float getTailSwayOffset() {
        return tailSwayOffset;
    }

    // Interpolated sway for smooth rendering between ticks
    public float getTailSwayOffset(float partialTick) {
        return Mth.lerp(Mth.clamp(partialTick, 0.0f, 1.0f), tailSwayPrev, tailSwayOffset);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(VARIANT, 0);
    }
    public int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public BrachiosaurusVariant getVariant() {
        return BrachiosaurusVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(BrachiosaurusVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public boolean canMate(Animal other) {
        if (!super.canMate(other)) return false;
        if (!(other instanceof BrachiosaurusEntity that)) return false;
        return this.getVariant() != that.getVariant();
    }
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        BrachiosaurusVariant variant = Util.getRandom(BrachiosaurusVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}