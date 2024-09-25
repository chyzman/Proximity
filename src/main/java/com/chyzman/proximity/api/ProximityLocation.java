package com.chyzman.proximity.api;

import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public record ProximityLocation(Vec3d pos, RegistryKey<World> world, double multiplier) {

    public static ProximityLocation fromEntity(Entity entity, double multiplier) {
        return new ProximityLocation(entity.getEyePos(), entity.getWorld().getRegistryKey(), multiplier);
    }
}
