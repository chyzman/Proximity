package com.chyzman.proximity.mixin.common;

import com.chyzman.proximity.api.ProximityLocation;
import com.chyzman.proximity.pond.LivingEntityDuck;
import com.chyzman.proximity.registry.ProximityEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.chyzman.proximity.api.ProximityHandler.CURRENT_PROXIMITY_DISTANCE;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityDuck {

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void injectProximityAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(Registries.ATTRIBUTE.getEntry(ProximityEntityAttributes.SPEECH_DISTANCE.value()), 0)
                .add(Registries.ATTRIBUTE.getEntry(ProximityEntityAttributes.HEARING_DISTANCE.value()), 0);
    }

    @Inject(method = "getAttributeBaseValue", at = @At("RETURN"), cancellable = true)
    private void injectProximityAttributeBaseValues(RegistryEntry<EntityAttribute> attribute, CallbackInfoReturnable<Double> cir) {
        if (attribute.equals(ProximityEntityAttributes.SPEECH_DISTANCE) || attribute.equals(ProximityEntityAttributes.HEARING_DISTANCE)) {
            cir.setReturnValue(cir.getReturnValue() + CURRENT_PROXIMITY_DISTANCE);
        }
    }

    @Override
    public ProximityLocation proximity$getProximityLocation() {
        return null;
    }
}
