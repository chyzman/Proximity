package com.chyzman.proximity.command;

import com.chyzman.proximity.api.ChatContext;
import com.chyzman.proximity.api.ProximityHandler;
import com.chyzman.proximity.api.ProximityLocation;
import com.chyzman.proximity.registry.ProximityGameRules;
import com.chyzman.proximity.registry.ProximityMessageTypes;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static com.chyzman.proximity.registry.ProximityEntityAttributes.SPEECH_DISTANCE;
import static net.minecraft.server.command.CommandManager.literal;

public class MumbleCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LiteralCommandNode<ServerCommandSource> literalCommandNode = dispatcher.register(
                    literal("mumble")
                            .then(CommandManager.argument("message", MessageArgumentType.message()).executes(context -> {
                                var sender = context.getSource().getEntity();
                                if (sender == null) return 0;
                                MessageArgumentType.getSignedMessage(context, "message", message -> {
                                    ServerCommandSource serverCommandSource = context.getSource();
                                    PlayerManager playerManager = serverCommandSource.getServer().getPlayerManager();
                                    ProximityHandler.broadcastProximityChat(
                                            new ChatContext(
                                                    playerManager,
                                                    sender,
                                                    message,
                                                    MessageType.params(ProximityMessageTypes.MUMBLE_COMMAND_INCOMING, serverCommandSource),
                                                    MessageType.params(ProximityMessageTypes.MUMBLE_COMMAND_OUTGOING, serverCommandSource)
                                            ),
                                            ProximityLocation.fromEntity(sender, 1),
                                            ProximityHandler.getProximityAttributeValue(
                                                    sender,
                                                    SPEECH_DISTANCE,
                                                    sender.getServer().getGameRules().get(ProximityGameRules.MUMBLE_DISTANCE).get()
                                            )
                                    );
                                });
                                return 1;
                            })));
            dispatcher.register(CommandManager.literal("m").redirect(literalCommandNode));
        });
    }
}
