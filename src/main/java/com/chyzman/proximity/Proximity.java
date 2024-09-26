package com.chyzman.proximity;

import com.chyzman.proximity.command.MumbleCommand;
import com.chyzman.proximity.command.ShoutCommand;
import com.chyzman.proximity.registry.ProximityEntityAttributes;
import com.chyzman.proximity.registry.ProximityGameRules;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Proximity implements ModInitializer {
    public static final String MODID = "proximity";

    @Override
    public void onInitialize() {
        ProximityEntityAttributes.init();

        ProximityGameRules.init();

        MumbleCommand.register();
        ShoutCommand.register();
    }

    public static Identifier id(String path) {
        return Identifier.of(MODID, path);
    }
}
