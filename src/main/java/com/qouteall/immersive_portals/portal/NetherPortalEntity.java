package com.qouteall.immersive_portals.portal;

import com.qouteall.immersive_portals.my_util.Helper;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.UUID;

public class NetherPortalEntity extends Portal {
    public static EntityType<NetherPortalEntity> entityType;
    
    //the reversed portal is in another dimension and face the opposite direction
    public UUID reversePortalId;
    public ObsidianFrame obsidianFrame;
    
    private boolean isNotified = true;
    private boolean shouldBreakNetherPortal = false;
    
    public static void init() {
        entityType = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation("immersive_portals", "monitoring_nether_portal"),
            FabricEntityTypeBuilder.create(
                EntityClassification.MISC,
                (EntityType.IFactory<NetherPortalEntity>) NetherPortalEntity::new
            ).size(
                new EntitySize(1, 1, true)
            ).setImmuneToFire().build()
        );
    
    
        PortalPlaceholderBlock.portalBlockUpdateSignal.connect((world, pos) -> {
            Helper.getEntitiesNearby(
                world,
                new Vec3d(pos),
                NetherPortalEntity.class,
                20
            ).forEach(
                NetherPortalEntity::notifyToCheckIntegrity
            );
        });
    }
    
    public NetherPortalEntity(
        EntityType type,
        World world
    ) {
        super(type, world);
    }
    
    public NetherPortalEntity(
        World world
    ) {
        super(entityType, world);
    }
    
    private void breakPortalOnThisSide() {
        assert shouldBreakNetherPortal;
        assert !removed;
        
        breakNetherPortalBlocks();
        this.remove();
    }
    
    private void breakNetherPortalBlocks() {
        ServerWorld world1 = Helper.getServer().getWorld(dimension);
    
        obsidianFrame.boxWithoutObsidian.stream()
            .filter(
                blockPos -> world1.getBlockState(
                    blockPos
                ).getBlock() == PortalPlaceholderBlock.instance
            )
            .forEach(
                blockPos -> world1.setBlockState(
                    blockPos, Blocks.AIR.getDefaultState()
                )
            );
    }
    
    @Override
    public boolean isPortalValid() {
        return super.isPortalValid() &&
            reversePortalId != null &&
            obsidianFrame != null;
    }
    
    private void notifyToCheckIntegrity() {
        isNotified = true;
    }
    
    private NetherPortalEntity getReversePortal() {
        assert !world.isRemote;
        
        ServerWorld world = getServer().getWorld(dimensionTo);
        return world == null ?
            null : (NetherPortalEntity) world.getEntity(reversePortalId);
    }
    
    @Override
    public void tick() {
        super.tick();
        
        if (!world.isRemote) {
            if (isNotified) {
                isNotified = false;
                checkPortalIntegrity();
            }
            if (shouldBreakNetherPortal) {
                breakPortalOnThisSide();
            }
        }
    }
    
    private void checkPortalIntegrity() {
        assert !world.isRemote;
        
        if (!isPortalValid()) {
            remove();
            return;
        }
    
        if (!isPortalIntactOnThisSide()) {
            shouldBreakNetherPortal = true;
            NetherPortalEntity reversePortal = getReversePortal();
            if (reversePortal != null) {
                reversePortal.shouldBreakNetherPortal = true;
            }
            else {
                Helper.err(
                    "Cannot find the reverse portal. Nether portal may not be removed normally."
                );
            }
        }
    }
    
    private boolean isPortalIntactOnThisSide() {
        assert Helper.getServer() != null;
        
        return NetherPortalMatcher.isObsidianFrameIntact(
            world,
            obsidianFrame.normalAxis,
            obsidianFrame.boxWithoutObsidian
        )
            && isInnerPortalBlocksIntact(world, obsidianFrame);
    }
    
    //if the region is not loaded, it will return true
    private static boolean isObsidianFrameIntact(
        DimensionType dimension,
        ObsidianFrame obsidianFrame
    ) {
        ServerWorld world = Helper.getServer().getWorld(dimension);
        
        if (world == null) {
            return true;
        }
        
        if (!world.isBlockLoaded(obsidianFrame.boxWithoutObsidian.l)) {
            return true;
        }
    
        if (!NetherPortalMatcher.isObsidianFrameIntact(
            world,
            obsidianFrame.normalAxis,
            obsidianFrame.boxWithoutObsidian
        )) {
            return false;
        }
    
        return isInnerPortalBlocksIntact(world, obsidianFrame);
    }
    
    private static boolean isInnerPortalBlocksIntact(
        IWorld world,
        ObsidianFrame obsidianFrame
    ) {
        return obsidianFrame.boxWithoutObsidian.stream().allMatch(
            blockPos -> world.getBlockState(blockPos).getBlock()
                == PortalPlaceholderBlock.instance
        );
    }
    
    
    @Override
    protected void readCustomDataFromTag(CompoundNBT compoundTag) {
        super.readCustomDataFromTag(compoundTag);
    
        reversePortalId = compoundTag.getUuid("reversePortalId");
        obsidianFrame = ObsidianFrame.fromTag(compoundTag.getCompound("obsidianFrame"));
    }
    
    @Override
    protected void writeCustomDataToTag(CompoundNBT compoundTag) {
        super.writeCustomDataToTag(compoundTag);
    
        compoundTag.putUuid("reversePortalId", reversePortalId);
        compoundTag.put("obsidianFrame", obsidianFrame.toTag());
    }
    
}
