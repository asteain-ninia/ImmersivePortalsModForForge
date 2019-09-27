package com.qouteall.immersive_portals.mixin;

import com.qouteall.immersive_portals.portal.EndPortalEntity;
import com.qouteall.immersive_portals.portal.PortalPlaceholderBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public class MixinEnderEyeItem {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void onUseOnBlock(
        ItemUseContext itemUsageContext_1,
        CallbackInfoReturnable<ActionResultType> cir
    ) {
        cir.setReturnValue(myUseOnBlock(itemUsageContext_1));
        cir.cancel();
    }
    
    private ActionResultType myUseOnBlock(ItemUseContext itemUsageContext) {
        World world = itemUsageContext.getWorld();
        BlockPos blockPos = itemUsageContext.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getBlock() == Blocks.END_PORTAL_FRAME &&
            !blockState.get(EndPortalFrameBlock.EYE)) {
            if (world.isRemote) {
                return ActionResultType.SUCCESS;
            }
            else {
                BlockState blockState_2 = (BlockState) blockState.with(
                    EndPortalFrameBlock.EYE,
                    true
                );
                Block.pushEntitiesUpBeforeBlockChange(blockState, blockState_2, world, blockPos);
                world.setBlockState(blockPos, blockState_2, 2);
                world.updateHorizontalAdjacent(blockPos, Blocks.END_PORTAL_FRAME);
                itemUsageContext.getStack().decrement(1);
                world.playLevelEvent(1503, blockPos, 0);
                BlockPattern.PatternHelper pattern =
                    EndPortalFrameBlock.getCompletedFramePattern().searchAround(world, blockPos);
                if (pattern != null) {
                    BlockPos blockPos_2 = pattern.getFrontTopLeft().add(-3, 0, -3);
                    
                    for (int dx = 0; dx < 3; ++dx) {
                        for (int dz = 0; dz < 3; ++dz) {
                            world.setBlockState(
                                blockPos_2.add(dx, 0, dz),
                                PortalPlaceholderBlock.instance.getDefaultState().with(
                                    PortalPlaceholderBlock.AXIS, Direction.Axis.Y
                                ),
                                2
                            );
                        }
                    }
                    
                    world.playGlobalEvent(1038, blockPos_2.add(1, 0, 1), 0);
                    
                    EndPortalEntity.onEndPortalComplete(((ServerWorld) world), pattern);
                }
                
                return ActionResultType.SUCCESS;
            }
        }
        else {
            return ActionResultType.PASS;
        }
    }
}
