package com.qouteall.immersive_portals.mixin_client;

import com.qouteall.immersive_portals.CGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(EntityRendererManager.class)
public class MixinEntityRenderDispatcher {
    @Inject(
        method = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;shouldRender(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/render/VisibleRegion;DDD)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onShouldRenderEntity(
        Entity entity_1,
        ICamera visibleRegion_1,
        double double_1,
        double double_2,
        double double_3,
        CallbackInfoReturnable<Boolean> cir
    ) {
        if (!CGlobal.renderer.shouldRenderEntityNow(entity_1)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
