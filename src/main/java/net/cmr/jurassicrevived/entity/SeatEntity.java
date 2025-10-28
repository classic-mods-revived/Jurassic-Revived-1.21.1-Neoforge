package net.cmr.jurassicrevived.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SeatEntity extends Entity {

    public SeatEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.noCulling = true;
        this.setInvulnerable(true);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // no data
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

private java.util.UUID mountNextTick;
private int aliveTicks = 0;

public void scheduleMount(java.util.UUID rider) { 
    this.mountNextTick = rider; 
    this.aliveTicks = 0; // reset so delay counts from spawn/schedule
}

    @Override
    public void tick() {
        setDeltaMovement(Vec3.ZERO);
        aliveTicks++;

    if (!level().isClientSide) {
        // Delay mounting by ~2 ticks so clients have seat pose
        if (mountNextTick != null && aliveTicks >= 2) {
            Entity e = ((net.minecraft.server.level.ServerLevel) level()).getEntity(mountNextTick);
            if (e instanceof net.minecraft.world.entity.player.Player p && !p.isPassenger()) {
                p.startRiding(this, true);
                p.setYRot(this.getYRot());
                p.setYHeadRot(this.getYRot());
                p.setXRot(0.0f);
            }
            mountNextTick = null;
        }

        if (!this.getPassengers().isEmpty()) {
            Entity rider = this.getPassengers().get(0);
            rider.setDeltaMovement(Vec3.ZERO);
            rider.setYRot(this.getYRot());
            rider.setYHeadRot(this.getYRot());
            if (rider instanceof net.minecraft.world.entity.player.Player p) p.setSprinting(false);
        } else if (aliveTicks > 10) { // keep a bit longer due to delayed mount
            discard();
            return;
        }
    }

    // Reassert pose both sides
    this.setPos(this.getX(), this.getY(), this.getZ());
    this.setYRot(this.getYRot());
    this.setYHeadRot(this.getYRot());

    super.tick();
}

@Override
public Vec3 getPassengerRidingPosition(Entity passenger) {
    // Lower the player by ~1 block (negative Y), tweak to taste (e.g., -0.8)
    return new Vec3(0.0, -0.6, 0.0);
}

@Override
public void onPassengerTurned(Entity passenger) {
    // Force rider to the exact attachment spot every tick after vanilla positions them
    Vec3 attach = getPassengerRidingPosition(passenger);
    // Seat origin + attach offset
    double x = this.getX() + attach.x;
    double y = this.getY() + attach.y;
    double z = this.getZ() + attach.z;

    // Server-authoritative snap
    if (!level().isClientSide) {
        passenger.setPos(x, y, z);
        passenger.setDeltaMovement(Vec3.ZERO);
        passenger.setYRot(this.getYRot());
        passenger.setYHeadRot(this.getYRot());
        passenger.setXRot(0.0f);
    } else {
        // Client smoothing
        passenger.setPos(x, y, z);
    }
}
}