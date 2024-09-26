package com.chyzman.proximity.registry;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;

import static com.chyzman.proximity.Proximity.MODID;
import static com.chyzman.proximity.Proximity.id;

public class ProximityGameRules {
    public static final CustomGameRuleCategory PROXIMITY_GAMERULE_CATEGORY = new CustomGameRuleCategory(
            id("proximity"),
            Text.translatable("gamerule.proximity.category.proximity").formatted(Formatting.BOLD, Formatting.YELLOW)
    );

    public static final GameRules.Key<DoubleRule> CHAT_DISTANCE = register(
            "chatDistance", GameRuleFactory.createDoubleRule(128, -1)
    );

    public static final GameRules.Key<DoubleRule> COMMAND_DISTANCE = register(
            "commandDistance", GameRuleFactory.createDoubleRule(128, -1)
    );

    public static final GameRules.Key<DoubleRule> MUMBLE_DISTANCE = register(
            "mumbleDistance", GameRuleFactory.createDoubleRule(1, -1)
    );

    public static void init() {
    }

    public static <T extends GameRules.Rule<T>> GameRules.Key<T> register(String name, GameRules.Type<T> type) {
        return GameRuleRegistry.register(MODID + "." + name, PROXIMITY_GAMERULE_CATEGORY, type);
    }
}
