package com.chyzman.proximity;

import com.chyzman.proximity.registry.ProximityEntityAttributes;
import com.chyzman.proximity.registry.ProximityGameRules;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import com.chyzman.proximity.ProximityConfig;

public class Proximity implements ModInitializer {
    public static final String MODID = "proximity";

    public static final ProximityConfig PROXIMITY_CONFIG = ProximityConfig.createAndLoad();


    @Override
    public void onInitialize() {
        ProximityGameRules.init();

        ProximityEntityAttributes.init();
    }

    public static Identifier id(String path) {
        return Identifier.of(MODID, path);
    }
}
