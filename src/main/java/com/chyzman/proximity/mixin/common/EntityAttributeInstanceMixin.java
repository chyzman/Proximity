package com.chyzman.proximity.mixin.common;

import com.chyzman.proximity.registry.ProximityEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.chyzman.proximity.api.ProximityHandler.CURRENT_PROXIMITY_DISTANCE;

@Mixin(EntityAttributeInstance.class)
public class EntityAttributeInstanceMixin {

    @Shadow @Final private RegistryEntry<EntityAttribute> type;

    @Inject(method = "getBaseValue", at = @At("RETURN"), cancellable = true)
    private void injectProximityAttributeBaseValues(CallbackInfoReturnable<Double> cir) {
        if (type.equals(ProximityEntityAttributes.SPEECH_DISTANCE) || type.equals(ProximityEntityAttributes.HEARING_DISTANCE)) {
            cir.setReturnValue(cir.getReturnValue() + CURRENT_PROXIMITY_DISTANCE);
        }
    }
}
