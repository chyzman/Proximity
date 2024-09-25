package com.chyzman.proximity.mixin.common;

import com.chyzman.proximity.registry.ProximityEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void injectProximityAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(Registries.ATTRIBUTE.getEntry(ProximityEntityAttributes.SPEECH_DISTANCE.value()), 0)
                .add(Registries.ATTRIBUTE.getEntry(ProximityEntityAttributes.HEARING_DISTANCE.value()), 0);
    }
}
