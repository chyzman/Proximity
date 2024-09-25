package com.chyzman.proximity.api;

import net.minecraft.entity.Entity;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;

public record ChatContext(Entity speaker, SignedMessage message, MessageType.Parameters parameters) {
}
