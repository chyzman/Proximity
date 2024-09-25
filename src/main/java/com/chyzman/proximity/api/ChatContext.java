package com.chyzman.proximity.api;

import net.minecraft.entity.Entity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.PlayerManager;
import org.jetbrains.annotations.Nullable;

public record ChatContext(
        PlayerManager playerManager,
        Entity speaker,
        SignedMessage message,
        MessageType.Parameters parameters,
        @Nullable MessageType.Parameters senderParameters
) {
    public ChatContext(PlayerManager playerManager, Entity speaker, SignedMessage message, MessageType.Parameters parameters) {
        this(playerManager, speaker, message, parameters, null);
    }
}
