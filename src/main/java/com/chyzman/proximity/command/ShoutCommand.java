package com.chyzman.proximity.command;

import com.chyzman.proximity.api.ChatContext;
import com.chyzman.proximity.api.ProximityHandler;
import com.chyzman.proximity.api.ProximityLocation;
import com.chyzman.proximity.registry.ProximityMessageTypes;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class ShoutCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LiteralCommandNode<ServerCommandSource> literalCommandNode = dispatcher.register(
                    literal("shout")
                            .then(CommandManager.argument("message", MessageArgumentType.message()).executes(context -> {
                                var sender = context.getSource().getEntity();
                                if (sender == null) return 0;
                                MessageArgumentType.getSignedMessage(context, "message", message -> {
                                    ServerCommandSource serverCommandSource = context.getSource();
                                    PlayerManager playerManager = serverCommandSource.getServer().getPlayerManager();
                                    ProximityHandler.broadcastGlobalChat(
                                            new ChatContext(
                                                    playerManager,
                                                    sender,
                                                    message,
                                                    MessageType.params(ProximityMessageTypes.SHOUT_COMMAND_INCOMING, serverCommandSource),
                                                    MessageType.params(ProximityMessageTypes.SHOUT_COMMAND_OUTGOING, serverCommandSource)
                                            )
                                    );
                                });
                                return 1;
                            })));
            dispatcher.register(CommandManager.literal("s").redirect(literalCommandNode));
        });
    }
}
