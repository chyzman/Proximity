package com.chyzman.proximity.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;

import static com.chyzman.proximity.Proximity.MODID;
import static com.chyzman.proximity.Proximity.id;

public class ProximityGameRules {
    public static final GameRuleCategory PROXIMITY_GAMERULE_CATEGORY = GameRuleCategory.register(id("category"));

    public static final GameRule<Boolean> PROXIMITY_ENABLED = register(
        "enable_proximity", GameRuleBuilder.forBoolean(true)
    );

    public static final GameRule<Double> CHAT_DISTANCE = register(
        "chat_distance", GameRuleBuilder.forDouble(128).minValue(-1d)
    );

    public static final GameRule<Double> COMMAND_DISTANCE = register(
        "command_distance", GameRuleBuilder.forDouble(128).minValue(-1d)
    );

    public static final GameRule<Double> MUMBLE_DISTANCE = register(
        "mumble_distance", GameRuleBuilder.forDouble(1).minValue(-1d)
    );

    public static void init() {
    }



    public static <T> GameRule<T> register(String name, GameRuleBuilder<T> builder) {
        return Registry.register(Registries.GAME_RULE, id(name), builder.category(PROXIMITY_GAMERULE_CATEGORY).build());
    }
}
