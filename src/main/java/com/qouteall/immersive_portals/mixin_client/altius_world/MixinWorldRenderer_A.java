package com.qouteall.immersive_portals.mixin_client.altius_world;

import com.qouteall.immersive_portals.CGlobal;
import com.qouteall.immersive_portals.portal.Portal;
import com.qouteall.immersive_portals.portal.global_portals.VerticalConnectingPortal;
import com.qouteall.immersive_portals.render.MyGameRenderer;
import com.qouteall.immersive_portals.render.MyRenderHelper;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer_A {
//    @Redirect(
//        method = "render",
//        at = @At(
//            value = "INVOKE",
//            target = "Lnet/minecraft/client/render/WorldRenderer;renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V"
//        )
//    )
//    private void redirectRenderSky(WorldRenderer worldRenderer, MatrixStack matrixStack, float f) {
//        if (CGlobal.renderer.isRendering()) {
//            Portal renderingPortal = CGlobal.renderer.getRenderingPortal();
//            if (renderingPortal instanceof VerticalConnectingPortal) {
//                MyGameRenderer.renderSkyFor(
//                    MyRenderHelper.originalPlayerDimension,
//                    matrixStack, f
//                );
//                return;
//            }
//        }
//        worldRenderer.renderSky(matrixStack, f);
//    }
}