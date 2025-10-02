package com.chyzman.proximity.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.chyzman.proximity.registry.ProximityEntityAttributes.HEARING_DISTANCE;
import static com.chyzman.proximity.registry.ProximityEntityAttributes.SPEECH_DISTANCE;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void injectProximityAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
            .add(SPEECH_DISTANCE, 0)
            .add(HEARING_DISTANCE, 0);
    }
}
