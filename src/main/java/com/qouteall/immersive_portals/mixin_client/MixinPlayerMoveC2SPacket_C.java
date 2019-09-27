package com.qouteall.immersive_portals.mixin_client;

import com.qouteall.immersive_portals.exposer.IEPlayerMoveC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CPlayerPacket.class)
public class MixinPlayerMoveC2SPacket_C {
    @Inject(
        method = "Lnet/minecraft/server/network/packet/PlayerMoveC2SPacket;<init>(Z)V",
        at = @At("RETURN")
    )
    private void onConstruct(boolean boolean_1, CallbackInfo ci) {
        DimensionType dimension = Minecraft.getInstance().player.dimension;
        ((IEPlayerMoveC2SPacket) this).setPlayerDimension(dimension);
        assert dimension == Minecraft.getInstance().world.dimension.getType();
    }
}
