package com.qouteall.immersive_portals.alternate_dimension;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.Lifecycle;
import com.qouteall.immersive_portals.McHelper;
import com.qouteall.immersive_portals.ModMain;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import java.util.Optional;
import java.util.function.Supplier;

public class AlternateDimensions {
    public static ChunkGenerator createSkylandGenerator(long seed, DynamicRegistries rm) {
        
        MutableRegistry<Biome> biomeRegistry = rm.func_243612_b(Registry.field_239720_u_);
        OverworldBiomeProvider biomeSource = new OverworldBiomeProvider(
            seed, false, false, biomeRegistry
        );
        
        MutableRegistry<DimensionSettings> settingsRegistry = rm.func_243612_b(Registry.NOISE_SETTINGS_WORLDGEN);
        
        DimensionSettings skylandSetting = settingsRegistry.func_243576_d(DimensionSettings.field_242739_h);
        
        return new NoiseChunkGenerator(
            biomeSource, seed, () -> skylandSetting
        );
    }
    
    public static ChunkGenerator createErrorTerrainGenerator(long seed, DynamicRegistries rm) {
        MutableRegistry<Biome> biomeRegistry = rm.func_243612_b(Registry.field_239720_u_);
        
        ChaosBiomeSource chaosBiomeSource = new ChaosBiomeSource(seed, biomeRegistry);
        return new ErrorTerrainGenerator(seed, chaosBiomeSource);
    }
    
    public static ChunkGenerator createVoidGenerator(DynamicRegistries rm) {
        MutableRegistry<Biome> biomeRegistry = rm.func_243612_b(Registry.field_239720_u_);
        
        DimensionStructuresSettings structuresConfig = new DimensionStructuresSettings(
            Optional.of(DimensionStructuresSettings.field_236192_c_),
            Maps.newHashMap(ImmutableMap.of(
                Structure.VILLAGE, DimensionStructuresSettings.field_236191_b_.get(Structure.VILLAGE)
            ))
        );
        FlatGenerationSettings flatChunkGeneratorConfig = new FlatGenerationSettings(structuresConfig,
            biomeRegistry);
        flatChunkGeneratorConfig.getFlatLayers().add(new FlatLayerInfo(1, Blocks.BEDROCK));
        flatChunkGeneratorConfig.getFlatLayers().add(new FlatLayerInfo(2, Blocks.DIRT));
        flatChunkGeneratorConfig.getFlatLayers().add(new FlatLayerInfo(1, Blocks.GRASS_BLOCK));
        flatChunkGeneratorConfig.updateLayers();
        
        return new FlatChunkGenerator(flatChunkGeneratorConfig);
    }
    
    public static void addDimension(
        long argSeed,
        SimpleRegistry<Dimension> registry,
        RegistryKey<Dimension> key,
        Supplier<DimensionType> dimensionTypeSupplier,
        ChunkGenerator chunkGenerator
    ) {
        if (!registry.keySet().contains(key.func_240901_a_())) {
            registry.register(
                key,
                new Dimension(
                    dimensionTypeSupplier,
                    chunkGenerator
                ),
                Lifecycle.experimental()
            );
        }
    }
    
    public static void addAlternateDimensions(
        SimpleRegistry<Dimension> registry, DynamicRegistries rm,
        long seed
    ) {
        addDimension(
            seed,
            registry,
            ModMain.alternate1Option,
            () -> ModMain.surfaceTypeObject,
            createSkylandGenerator(seed, rm)
        );
        
        addDimension(
            seed,
            registry,
            ModMain.alternate2Option,
            () -> ModMain.surfaceTypeObject,
            createSkylandGenerator(seed, rm)
        );
        
        addDimension(
            seed,
            registry,
            ModMain.alternate3Option,
            () -> ModMain.surfaceTypeObject,
            createErrorTerrainGenerator(seed, rm)
        );
        
        addDimension(
            seed,
            registry,
            ModMain.alternate4Option,
            () -> ModMain.surfaceTypeObject,
            createErrorTerrainGenerator(seed, rm)
        );
        
        addDimension(
            seed,
            registry,
            ModMain.alternate5Option,
            () -> ModMain.surfaceTypeObject,
            createVoidGenerator(rm)
        );
    }
    
    // don't store dimension info into level.dat
    // avoid weird dfu error
    public static SimpleRegistry<Dimension> getAlternateDimensionsRemoved(
        SimpleRegistry<Dimension> registry
    ) {
        return McHelper.filterAndCopyRegistry(
            registry,
            (key, obj) -> !(key == ModMain.alternate1Option ||
                key == ModMain.alternate2Option ||
                key == ModMain.alternate3Option ||
                key == ModMain.alternate4Option ||
                key == ModMain.alternate5Option)
        );
    }
    
}