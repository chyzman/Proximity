package com.chyzman.proximity.mixin.common;

import com.chyzman.proximity.api.ChatContext;
import com.chyzman.proximity.api.ProximityHandler;
import com.chyzman.proximity.api.ProximityLocation;
import com.chyzman.proximity.registry.ProximityGameRules;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.chyzman.proximity.registry.ProximityEntityAttributes.SPEECH_DISTANCE;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Shadow public abstract MinecraftServer getServer();

    @Inject(method = "broadcast(Lnet/minecraft/network/message/SignedMessage;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/network/message/MessageType$Parameters;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void proximitify$chat(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params, CallbackInfo ci) {
        var distance = getServer().getGameRules().get(ProximityGameRules.CHAT_DISTANCE).get();
        if (distance < 0) return;
        if (ProximityHandler.broadcastProximityChat(
                new ChatContext(
                        ((PlayerManager)(Object)this),
                        sender,
                        message,
                        params
                ),
                ProximityLocation.fromEntity(sender, 1),
                ProximityHandler.getProximityAttributeValue(
                        sender,
                        SPEECH_DISTANCE,
                        distance
                )
        )) ci.cancel();
    }

    @Inject(method = "broadcast(Lnet/minecraft/network/message/SignedMessage;Lnet/minecraft/server/command/ServerCommandSource;Lnet/minecraft/network/message/MessageType$Parameters;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void proximitify$commands(SignedMessage message, ServerCommandSource source, MessageType.Parameters params, CallbackInfo ci) {
        var distance = getServer().getGameRules().get(ProximityGameRules.COMMAND_DISTANCE).get();
        if (distance < 0) return;
        var sender = source.getEntity();
        if (ProximityHandler.broadcastProximityChat(
                new ChatContext(
                        ((PlayerManager)(Object)this),
                        sender,
                        message,
                        params
                ),
                ProximityLocation.fromEntity(sender, 1),
                ProximityHandler.getProximityAttributeValue(
                        sender,
                        SPEECH_DISTANCE,
                        distance
                )
        )) ci.cancel();
    }


}
