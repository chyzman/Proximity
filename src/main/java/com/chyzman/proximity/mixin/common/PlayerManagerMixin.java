package com.chyzman.proximity.mixin.common;

import com.chyzman.proximity.api.ProximityHandler;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Inject(method = "broadcast(Lnet/minecraft/network/message/SignedMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/network/message/MessageType$Parameters;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void proximitify$chat(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params, CallbackInfo ci) {
        if (ProximityHandler.broadcastProximityChat(sender, message, params)) ci.cancel();
    }

    @Inject(method = "broadcast(Lnet/minecraft/network/message/SignedMessage;Lnet/minecraft/server/command/ServerCommandSource;Lnet/minecraft/network/message/MessageType$Parameters;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void proximitify$meAndSay(SignedMessage message, ServerCommandSource source, MessageType.Parameters params, CallbackInfo ci) {
        if (ProximityHandler.broadcastProximityChat(source.getEntity(), message, params)) ci.cancel();
    }
}
