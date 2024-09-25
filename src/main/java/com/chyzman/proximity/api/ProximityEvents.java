package com.chyzman.proximity.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ProximityEvents {

    private ProximityEvents() {
    }

    /**
     * An event used to determine the location used for proximity checks between players.
     * <p>
     * Example: A player is speaking whilst inside some sort of storage device/pocket dimension.
     *
     * @implNote The position of the speaker should never be used for the origin of the message as it may already be different.
     *
     */
    public static final Event<ModifyProximityLocation> MODIFY_PROXIMITY_LOCATION = EventFactory.createArrayBacked(ModifyProximityLocation.class, listeners -> (message, target, location) -> {
        for (int i = 0; i < 1000; i++) {
            var temp = location;
            for (ModifyProximityLocation listener : listeners) {
                var modified = listener.modifySpeechLocation(message, target, location);
                if (modified != null && modified != location) {
                    location = modified;
                    break;
                }
            }
            if (temp == location) break;
        }
        return location;
    });

    @FunctionalInterface
    public interface ModifyProximityLocation {
       ProximityLocation modifySpeechLocation(Text message, Entity speaker, ProximityLocation location);
    }

    /**
     * An event used to force a player to hear or not hear another player.
     * <p>
     * Example: A both players are out of earshot but have some sort of radio device that should allow them to hear each other.
     */
    public static final Event<ForceHearing> FORCE_ABLE_TO_HEAR = EventFactory.createArrayBacked(ForceHearing.class, listeners -> (speaker, listener) -> {
        for (ForceHearing theCodeVersionOfListenerNotLikeEarUser : listeners) {
            var result = theCodeVersionOfListenerNotLikeEarUser.forceHearing(speaker, listener);
            if (!result.equals(TriState.DEFAULT)) return result;
        }
        return TriState.DEFAULT;
    });

    @FunctionalInterface
    public interface ForceHearing {
        TriState forceHearing(Entity speaker, Entity listener);
    }
}
