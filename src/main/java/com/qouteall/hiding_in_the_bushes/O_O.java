package com.qouteall.hiding_in_the_bushes;

import com.qouteall.immersive_portals.Global;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class O_O {
    @OnlyIn(Dist.CLIENT)
    public static void onPlayerChangeDimensionClient(
        DimensionType from, DimensionType to
    ) {
        //nothing
    }
    
    @OnlyIn(Dist.CLIENT)
    public static void segregateClientEntity(
        ClientWorld fromWorld,
        Entity entity
    ) {
        ((IEClientWorld_MA) fromWorld).removeEntityWhilstMaintainingCapability(entity);
        entity.removed = false;
    }
    
    public static void segregateServerEntity(
        ServerWorld fromWorld,
        Entity entity
    ) {
        fromWorld.removeEntity(entity, false);
        entity.revive();
    }
    
    public static void segregateServerPlayer(
        ServerWorld fromWorld,
        ServerPlayerEntity player
    ) {
        fromWorld.removePlayer(player, false);
        player.revive();
    }
    
    public static void onPlayerTravelOnServer(
        ServerPlayerEntity player,
        DimensionType from,
        DimensionType to
    ) {
        Global.serverTeleportationManager.isFiringMyChangeDimensionEvent = true;
        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(
            player, from, to
        );
        Global.serverTeleportationManager.isFiringMyChangeDimensionEvent = false;
    }
    
    public static void loadConfigFabric() {
        //nothing
    }
}