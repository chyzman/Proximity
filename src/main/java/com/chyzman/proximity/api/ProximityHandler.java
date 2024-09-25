package com.chyzman.proximity.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.network.message.SentMessage;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.chyzman.proximity.registry.ProximityEntityAttributes.HEARING_DISTANCE;
import static com.chyzman.proximity.registry.ProximityEntityAttributes.SPEECH_DISTANCE;

public class ProximityHandler {
    public static double CURRENT_PROXIMITY_DISTANCE = 0;

    public static boolean broadcastProximityChat(
            ChatContext context,
            ProximityLocation origin,
            double distance
    ) {
        var message = SentMessage.of(context.message());

        if (context.speaker() instanceof ServerPlayerEntity player) player.sendChatMessage(message, false, context.senderParameters() != null ? context.senderParameters() : context.parameters());

        var speakerDistance = getProximityAttributeValue(context.speaker(), SPEECH_DISTANCE, distance);

        for (ServerPlayerEntity listener : context.playerManager().getPlayerList()) {
            if (listener == context.speaker()) continue;
            var forced = ProximityEvents.FORCE_ABLE_TO_HEAR.invoker().forceHearing(context.speaker(), listener);
            switch (forced) {
                case DEFAULT:
                    var listenerLocation = ProximityEvents.MODIFY_PROXIMITY_LOCATION.invoker().modifySpeechLocation(message.content(), listener, ProximityLocation.fromEntity(listener, 1));
                    if (listenerLocation.world() != origin.world()) continue;
                    var listenerDistance = getProximityAttributeValue(listener, HEARING_DISTANCE, distance);
                    var distanceBetween = origin.pos().distanceTo(listenerLocation.pos());
                    if (distanceBetween > speakerDistance || distanceBetween > listenerDistance) continue;
                case TRUE:
                    listener.sendChatMessage(message, false, context.parameters());
            }
        }

        return true;
    }

    public static boolean broadcastGlobalChat(
            ChatContext context
    ) {
        var message = SentMessage.of(context.message());

        if (context.speaker() instanceof ServerPlayerEntity player) player.sendChatMessage(message, false, context.senderParameters() != null ? context.senderParameters() : context.parameters());

        for (ServerPlayerEntity listener : context.playerManager().getPlayerList()) {
            if (listener == context.speaker()) continue;
            var forced = ProximityEvents.FORCE_ABLE_TO_HEAR.invoker().forceHearing(context.speaker(), listener);
            if (forced.orElse(true)) listener.sendChatMessage(message, false, context.parameters());
        }

        return true;
    }

    public static double getProximityAttributeValue(Entity entity, RegistryEntry<EntityAttribute> attribute, double base) {
        if (!(entity instanceof LivingEntity living)) return base;
        CURRENT_PROXIMITY_DISTANCE = base;
        var returned = living.getAttributeValue(attribute);
        CURRENT_PROXIMITY_DISTANCE = 0;
        return returned;
    }
}
