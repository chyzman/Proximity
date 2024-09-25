package com.chyzman.proximity;

import com.chyzman.proximity.command.MumbleCommand;
import com.chyzman.proximity.command.ShoutCommand;
import com.chyzman.proximity.registry.ProximityEntityAttributes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Proximity implements ModInitializer {
    public static final String MODID = "proximity";

    public static final com.chyzman.proximity.ProximityConfig PROXIMITY_CONFIG = com.chyzman.proximity.ProximityConfig.createAndLoad();


    @Override
    public void onInitialize() {
        ProximityEntityAttributes.init();

        MumbleCommand.register();
        ShoutCommand.register();
    }

    public static Identifier id(String path) {
        return Identifier.of(MODID, path);
    }
}
