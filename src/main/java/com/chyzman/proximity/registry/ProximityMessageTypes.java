package com.chyzman.proximity.registry;

import net.minecraft.network.message.MessageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static com.chyzman.proximity.Proximity.id;

public class ProximityMessageTypes {

    public static final RegistryKey<MessageType> MUMBLE_COMMAND_INCOMING = register("mumble_command_incoming");

    public static final RegistryKey<MessageType> MUMBLE_COMMAND_OUTGOING = register("mumble_command_outgoing");

    public static final RegistryKey<MessageType> SHOUT_COMMAND_INCOMING = register("shout_command_incoming");

    public static final RegistryKey<MessageType> SHOUT_COMMAND_OUTGOING = register("shout_command_outgoing");


    private static RegistryKey<MessageType> register(String id) {
        return RegistryKey.of(RegistryKeys.MESSAGE_TYPE, id(id));
    }
}
