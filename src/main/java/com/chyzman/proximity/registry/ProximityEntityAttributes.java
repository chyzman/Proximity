package com.chyzman.proximity.registry;

import com.chyzman.proximity.Proximity;
import io.wispforest.owo.registration.annotations.AssignedName;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static com.chyzman.proximity.Proximity.id;

public class ProximityEntityAttributes {

    public static final RegistryEntry<EntityAttribute> SPEECH_DISTANCE = register(
            "generic.speech_distance",
            new ClampedEntityAttribute(
                    "attribute.name.generic." + Proximity.MODID + ".speech_distance",
                    0,
                    0,
                    Double.MAX_VALUE
            )
    );

    public static final RegistryEntry<EntityAttribute> HEARING_DISTANCE = register(
            "generic.hearing_distance",
            new ClampedEntityAttribute(
                    "attribute.name.generic." + Proximity.MODID + ".hearing_distance",
                    0,
                    0,
                    Double.MAX_VALUE
            )
    );


    public static void init() {
    }

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, id(id), attribute);
    }
}
