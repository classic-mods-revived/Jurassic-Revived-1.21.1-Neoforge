package net.jurassicrevived.jurassicrevived.util;

import net.jurassicrevived.jurassicrevived.block.custom.FenceWireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public final class FenceClimbClientHandler {
    public static void register() {
        NeoForge.EVENT_BUS.addListener(FenceClimbClientHandler::onPlayerTickClient);
    }

    private static void onPlayerTickClient(PlayerTickEvent.Post e) {
        var player = e.getEntity();
        var level = player.level();
        if (!level.isClientSide || !player.isAlive()) return;

        var bb = player.getBoundingBox().inflate(0.05, 0.05, 0.05);
        int minX = Mth.floor(bb.minX), maxX = Mth.floor(bb.maxX);
        int minY = Mth.floor(bb.minY), maxY = Mth.floor(Math.min(bb.minY + 1.0, bb.maxY));
        int minZ = Mth.floor(bb.minZ), maxZ = Mth.floor(bb.maxZ);

        boolean touching = false;
        outer:
        for (int x=minX;x<=maxX;x++) for (int y=minY;y<=maxY;y++) for (int z=minZ;z<=maxZ;z++) {
            var pos = new BlockPos(x,y,z);
            var state = level.getBlockState(pos);

            // Accept FenceWireBlock OR FencePoleBlock
            boolean isWire = state.getBlock() instanceof net.jurassicrevived.jurassicrevived.block.custom.FenceWireBlock;
            boolean isPole = state.getBlock() instanceof net.jurassicrevived.jurassicrevived.block.custom.FencePoleBlock;
            if (!isWire && !isPole) continue;

            var shape = state.getCollisionShape(level, pos, net.minecraft.world.phys.shapes.CollisionContext.of(player));
            if (shape.isEmpty()) continue;

            // For poles: ignore center post; only consider arms/diagonals by subtracting the POST box
            if (isPole) {
                // Inline center-post AABB: (6..10, 0..16, 6..10) in 1/16th units
                var postAabbWorld = new net.minecraft.world.phys.AABB(
                    pos.getX() + 6 / 16.0, pos.getY() + 0.0,       pos.getZ() + 6 / 16.0,
                    pos.getX() + 10 / 16.0, pos.getY() + 16 / 16.0, pos.getZ() + 10 / 16.0
                );
                for (var aabb : shape.toAabbs()) {
                    var moved = aabb.move(pos.getX(), pos.getY(), pos.getZ());
                    // Skip if this sub-box is the center post (approx equals)
                    if (approximatelySame(moved, postAabbWorld)) continue;
                    if (bb.intersects(moved)) { touching = true; break outer; }
                }
            } else {
                for (var aabb : shape.toAabbs()) {
                    if (bb.intersects(aabb.move(pos.getX(), pos.getY(), pos.getZ()))) { touching = true; break outer; }
                }
            }
        }
        if (!touching) return;

        var v = player.getDeltaMovement();
        boolean sneaking = player.isShiftKeyDown();

        double maxHoriz = 0.15;
        double vx = Mth.clamp(v.x, -maxHoriz, maxHoriz);
        double vz = Mth.clamp(v.z, -maxHoriz, maxHoriz);

        boolean forward = Minecraft.getInstance().options.keyUp.isDown();

        double vy = v.y;
        if (sneaking) {
            vy = 0.0;
        } else if (forward) {
            vy = 0.2;
        } else {
            vy = Math.max(vy, -0.15);
        }

        player.fallDistance = 0.0F;
        player.setDeltaMovement(vx, vy, vz);
        player.hasImpulse = true;
    }

    // Treat two AABBs as the same center post if they are very close numerically
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
