package net.jurassicrevived.jurassicrevived.util;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.custom.FenceWireBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;

public final class FenceClimbHandler {
    private static final Logger LOG = JRMod.LOGGER;

    public static void register() {
        NeoForge.EVENT_BUS.addListener(FenceClimbHandler::onServerTickEnd);
    }

    private static void onServerTickEnd(ServerTickEvent.Post e) {
        var server = e.getServer();
        for (var level : server.getAllLevels()) {
            for (var player : level.players()) {
                if (!player.isAlive()) continue;

                boolean touching = false;
                var bb = player.getBoundingBox().inflate(0.05, 0.05, 0.05);
                int minX = Mth.floor(bb.minX), maxX = Mth.floor(bb.maxX);
                int minY = Mth.floor(bb.minY), maxY = Mth.floor(Math.min(bb.minY + 1.0, bb.maxY));
                int minZ = Mth.floor(bb.minZ), maxZ = Mth.floor(bb.maxZ);
                outer:
                for (int x=minX;x<=maxX;x++) for (int y=minY;y<=maxY;y++) for (int z=minZ;z<=maxZ;z++) {
                    var pos = new BlockPos(x,y,z);
                    var state = level.getBlockState(pos);

                    boolean isWire = state.getBlock() instanceof net.jurassicrevived.jurassicrevived.block.custom.FenceWireBlock;
                    boolean isPole = state.getBlock() instanceof net.jurassicrevived.jurassicrevived.block.custom.FencePoleBlock;
                    if (!isWire && !isPole) continue;

                    var shape = state.getCollisionShape(level, pos, CollisionContext.of(player));
                    if (shape.isEmpty()) continue;

                    if (isPole) {
                        var postAabb = new net.minecraft.world.phys.AABB(6/16.0, 0.0, 6/16.0, 10/16.0, 1.0, 10/16.0).move(pos.getX(), pos.getY(), pos.getZ());
                        for (var aabb : shape.toAabbs()) {
                            var moved = aabb.move(pos.getX(), pos.getY(), pos.getZ());
                            if (approximatelySame(moved, postAabb)) continue;
                            if (bb.intersects(moved)) { touching = true; break outer; }
                        }
                    } else {
                        for (var aabb : shape.toAabbs()) {
                            if (bb.intersects(aabb.move(pos.getX(), pos.getY(), pos.getZ()))) { touching = true; break outer; }
                        }
                    }
                }
                if (!touching) continue;

                var v = player.getDeltaMovement();
                boolean sneaking = player.isShiftKeyDown();

                double maxHoriz = 0.15;
                double vx = Mth.clamp(v.x, -maxHoriz, maxHoriz);
                double vz = Mth.clamp(v.z, -maxHoriz, maxHoriz);

                double vy = v.y;
                if (sneaking) {
                    vy = Math.max(vy, 0.0);
                } else {
                    vy = Math.max(vy, -0.15);
                    vy = Math.max(vy, 0.01);
                }

                player.setOnGround(false);
                player.fallDistance = 0.0F;
                player.setDeltaMovement(vx, vy, vz);
                player.hasImpulse = true;
            }
        }
    }

    private static boolean approximatelySame(net.minecraft.world.phys.AABB a, net.minecraft.world.phys.AABB b) {
        double eps = 1e-6;
        return Math.abs(a.minX - b.minX) < eps &&
               Math.abs(a.minY - b.minY) < eps &&
               Math.abs(a.minZ - b.minZ) < eps &&
               Math.abs(a.maxX - b.maxX) < eps &&
               Math.abs(a.maxY - b.maxY) < eps &&
               Math.abs(a.maxZ - b.maxZ) < eps;
    }
}