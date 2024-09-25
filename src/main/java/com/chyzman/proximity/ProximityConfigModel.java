package com.chyzman.proximity;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

import static com.chyzman.proximity.Proximity.MODID;

@Modmenu(modId = MODID)
@Config(name = MODID, wrapperName = "ProximityConfig")
public class ProximityConfigModel {

    @Nest
    public ProximityOptions chatMessages = new ProximityOptions(true, 512);

    @Nest
    public ProximityOptions commandMessages = new ProximityOptions(true, 512);

    @Nest
    public ProximityOptions gameMessages = new ProximityOptions(false, 2048);

    @Nest
    public ProximityOptions mumbling = new ProximityOptions(true, 0);

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean shouting = true;

    public static class ProximityOptions {
        public ProximityOptions(boolean enabled, double distance) {
            this.enabled = enabled;
            this.distance = distance;
        }

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        public boolean enabled;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = Double.MAX_VALUE)
        public double distance;
    }
}
