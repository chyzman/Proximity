package com.chyzman.proximity.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.chyzman.proximity.Proximity.PROXIMITY_CONFIG;
import static com.chyzman.proximity.registry.ProximityEntityAttributes.SPEECH_DISTANCE;

public class ProximityHandler {
    public static double CURRENT_PROXIMITY_DISTANCE = 0;

    public static boolean broadcastProximityChat(Entity speaker, SignedMessage message, MessageType.Parameters params, double distance) {
        if (!(speaker instanceof LivingEntity living)) return false;
        return broadcastProximityChat(
                new ChatContext(speaker, message, params),
                ProximityLocation.fromEntity(living, getProximityAttributeValue(living, SPEECH_DISTANCE, 1)),
                PROXIMITY_CONFIG.chatMessages.distance(),
                Set.of()
        );
    }

    public static boolean broadcastProximityChat(
            ChatContext context,
            ProximityLocation origin,
            double distance,
            @NotNull Set<Entity> haveHeard
    ) {
        if (haveHeard.contains(context.speaker())) return false;

        haveHeard.add(context.speaker());

        var message = SentMessage.of(context.message());

        return true;
    }

    public static double getProximityAttributeValue(LivingEntity entity, RegistryEntry<EntityAttribute> attribute, double base) {
        CURRENT_PROXIMITY_DISTANCE = base;
        var returned = entity.getAttributeValue(attribute);
        CURRENT_PROXIMITY_DISTANCE = 0;
        return returned;
    }
}
