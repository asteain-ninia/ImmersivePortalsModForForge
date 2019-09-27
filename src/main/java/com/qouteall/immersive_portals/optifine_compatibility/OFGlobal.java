package com.qouteall.immersive_portals.optifine_compatibility;

import net.optifine.shaders.uniform.ShaderUniforms;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class OFGlobal {
    public static RendererDeferred rendererDeferred = new RendererDeferred();
    public static RendererDebugWithShader rendererDebugWithShader = new RendererDebugWithShader();
    public static RendererMixed rendererMixed = new RendererMixed();
    public static ShaderContextManager shaderContextManager = new ShaderContextManager();
    
    public static Consumer<PerDimensionContext> copyContextToObject;
    public static Consumer<PerDimensionContext> copyContextFromObject;
    public static Supplier<Integer> getDfb;
    public static Runnable bindGbuffersTextures;
    public static Runnable flipShaderFb;
    public static Supplier<ShaderUniforms> getShaderUniforms;
    
    public static boolean doForceInitSequence = false;
    public static boolean alwaysRenderShadowMap = true;
    
    
    /**
     * mc;
     * entityRenderer;
     * isInitializedOnce;
     * isShaderPackInitialized;
     * capabilities;
     * glVersionString;
     * glVendorString;
     * glRendererString;
     * hasGlGenMipmap;
     * countResetDisplayLists;
     * renderDisplayWidth;
     * renderDisplayHeight;
     * renderWidth;
     * renderHeight;
     * isRenderingWorld;
     * isRenderingSky;
     * isCompositeRendered;
     * isRenderingDfb;
     * isShadowPass;
     * isSleeping;
     * isRenderingFirstPersonHand;
     * isHandRenderedMain;
     * isHandRenderedOff;
     * skipRenderHandMain;
     * skipRenderHandOff;
     * renderItemKeepDepthMask;
     * itemToRenderMainTranslucent;
     * itemToRenderOffTranslucent;
     * sunPosition;
     * moonPosition;
     * shadowLightPosition;
     * upPosition;
     * shadowLightPositionVector;
     * upPosModelView;
     * sunPosModelView;
     * moonPosModelView;
     * tempMat;
     * clearColorR;
     * clearColorG;
     * clearColorB;
     * skyColorR;
     * skyColorG;
     * skyColorB;
     * worldTime;
     * lastWorldTime;
     * diffWorldTime;
     * celestialAngle;
     * sunAngle;
     * shadowAngle;
     * moonPhase;
     * systemTime;
     * lastSystemTime;
     * diffSystemTime;
     * frameCounter;
     * frameTime;
     * frameTimeCounter;
     * systemTimeInt32;
     * rainStrength;
     * wetness;
     * wetnessHalfLife;
     * drynessHalfLife;
     * eyeBrightnessHalflife;
     * usewetness;
     * isEyeInWater;
     * eyeBrightness;
     * eyeBrightnessFadeX;
     * eyeBrightnessFadeY;
     * eyePosY;
     * centerDepth;
     * centerDepthSmooth;
     * centerDepthSmoothHalflife;
     * centerDepthSmoothEnabled;
     * superSamplingLevel;
     * nightVision;
     * blindness;
     * lightmapEnabled;
     * fogEnabled;
     * entityAttrib;
     * midTexCoordAttrib;
     * tangentAttrib;
     * useEntityAttrib;
     * useMidTexCoordAttrib;
     * useTangentAttrib;
     * progUseEntityAttrib;
     * progUseMidTexCoordAttrib;
     * progUseTangentAttrib;
     * progArbGeometryShader4;
     * progMaxVerticesOut;
     * hasGeometryShaders;
     * atlasSizeX;
     * atlasSizeY;
     * shaderUniforms;
     * uniform_entityColor;
     * uniform_entityId;
     * uniform_blockEntityId;
     * uniform_texture;
     * uniform_lightmap;
     * uniform_normals;
     * uniform_specular;
     * uniform_shadow;
     * uniform_watershadow;
     * uniform_shadowtex0;
     * uniform_shadowtex1;
     * uniform_depthtex0;
     * uniform_depthtex1;
     * uniform_shadowcolor;
     * uniform_shadowcolor0;
     * uniform_shadowcolor1;
     * uniform_noisetex;
     * uniform_gcolor;
     * uniform_gdepth;
     * uniform_gnormal;
     * uniform_composite;
     * uniform_gaux1;
     * uniform_gaux2;
     * uniform_gaux3;
     * uniform_gaux4;
     * uniform_colortex0;
     * uniform_colortex1;
     * uniform_colortex2;
     * uniform_colortex3;
     * uniform_colortex4;
     * uniform_colortex5;
     * uniform_colortex6;
     * uniform_colortex7;
     * uniform_gdepthtex;
     * uniform_depthtex2;
     * uniform_tex;
     * uniform_heldItemId;
     * uniform_heldBlockLightValue;
     * uniform_heldItemId2;
     * uniform_heldBlockLightValue2;
     * uniform_fogMode;
     * uniform_fogDensity;
     * uniform_fogColor;
     * uniform_skyColor;
     * uniform_worldTime;
     * uniform_worldDay;
     * uniform_moonPhase;
     * uniform_frameCounter;
     * uniform_frameTime;
     * uniform_frameTimeCounter;
     * uniform_sunAngle;
     * uniform_shadowAngle;
     * uniform_rainStrength;
     * uniform_aspectRatio;
     * uniform_viewWidth;
     * uniform_viewHeight;
     * uniform_near;
     * uniform_far;
     * uniform_sunPosition;
     * uniform_moonPosition;
     * uniform_shadowLightPosition;
     * uniform_upPosition;
     * uniform_previousCameraPosition;
     * uniform_cameraPosition;
     * uniform_gbufferModelView;
     * uniform_gbufferModelViewInverse;
     * uniform_gbufferPreviousProjection;
     * uniform_gbufferProjection;
     * uniform_gbufferProjectionInverse;
     * uniform_gbufferPreviousModelView;
     * uniform_shadowProjection;
     * uniform_shadowProjectionInverse;
     * uniform_shadowModelView;
     * uniform_shadowModelViewInverse;
     * uniform_wetness;
     * uniform_eyeAltitude;
     * uniform_eyeBrightness;
     * uniform_eyeBrightnessSmooth;
     * uniform_terrainTextureSize;
     * uniform_terrainIconSize;
     * uniform_isEyeInWater;
     * uniform_nightVision;
     * uniform_blindness;
     * uniform_screenBrightness;
     * uniform_hideGUI;
     * uniform_centerDepthSmooth;
     * uniform_atlasSize;
     * uniform_blendFunc;
     * previousCameraPositionX;
     * previousCameraPositionY;
     * previousCameraPositionZ;
     * cameraPositionX;
     * cameraPositionY;
     * cameraPositionZ;
     * cameraOffsetX;
     * cameraOffsetZ;
     * shadowPassInterval;
     * needResizeShadow;
     * shadowMapWidth;
     * shadowMapHeight;
     * spShadowMapWidth;
     * spShadowMapHeight;
     * shadowMapFOV;
     * shadowMapHalfPlane;
     * shadowMapIsOrtho;
     * shadowDistanceRenderMul;
     * shadowPassCounter;
     * shouldSkipDefaultShadow;
     * waterShadowEnabled;
     * MaxDrawBuffers;
     * MaxColorBuffers;
     * MaxDepthBuffers;
     * MaxShadowColorBuffers;
     * MaxShadowDepthBuffers;
     * usedColorBuffers;
     * usedDepthBuffers;
     * usedShadowColorBuffers;
     * usedShadowDepthBuffers;
     * usedColorAttachs;
     * usedDrawBuffers;
     * dfb;
     * sfb;
     * gbuffersFormat;
     * gbuffersClear;
     * gbuffersClearColor;
     * programs;
     * ProgramNone;
     * ProgramShadow;
     * ProgramShadowSolid;
     * ProgramShadowCutout;
     * ProgramBasic;
     * ProgramTextured;
     * ProgramTexturedLit;
     * ProgramSkyBasic;
     * ProgramSkyTextured;
     * ProgramClouds;
     * ProgramTerrain;
     * ProgramTerrainSolid;
     * ProgramTerrainCutoutMip;
     * ProgramTerrainCutout;
     * ProgramDamagedBlock;
     * ProgramBlock;
     * ProgramBeaconBeam;
     * ProgramItem;
     * ProgramEntities;
     * ProgramArmorGlint;
     * ProgramSpiderEyes;
     * ProgramHand;
     * ProgramWeather;
     * ProgramDeferredPre;
     * ProgramsDeferred;
     * ProgramDeferred;
     * ProgramWater;
     * ProgramHandWater;
     * ProgramCompositePre;
     * ProgramsComposite;
     * ProgramComposite;
     * ProgramFinal;
     * ProgramCount;
     * ProgramsAll;
     * activeProgram;
     * activeProgramID;
     * programStackLeash;
     * hasDeferredPrograms;
     * activeDrawBuffers;
     * activeCompositeMipmapSetting;
     * loadedShaders;
     * shadersConfig;
     * defaultTexture;
     * shadowHardwareFilteringEnabled;
     * shadowMipmapEnabled;
     * shadowFilterNearest;
     * shadowColorMipmapEnabled;
     * shadowColorFilterNearest;
     * configTweakBlockDamage;
     * configCloudShadow;
     * configHandDepthMul;
     * configRenderResMul;
     * configShadowResMul;
     * configTexMinFilB;
     * configTexMinFilN;
     * configTexMinFilS;
     * configTexMagFilB;
     * configTexMagFilN;
     * configTexMagFilS;
     * configShadowClipFrustrum;
     * configNormalMap;
     * configSpecularMap;
     * configOldLighting;
     * configOldHandLight;
     * configAntialiasingLevel;
     * texMinFilRange;
     * texMagFilRange;
     * texMinFilDesc;
     * texMagFilDesc;
     * texMinFilValue;
     * texMagFilValue;
     * shaderPack;
     * shaderPackLoaded;
     * currentShaderName;
     * SHADER_PACK_NAME_NONE;
     * SHADER_PACK_NAME_DEFAULT;
     * SHADER_PACKS_DIR_NAME;
     * OPTIONS_FILE_NAME;
     * shaderPacksDir;
     * configFile;
     * shaderPackOptions;
     * shaderPackOptionSliders;
     * shaderPackProfiles;
     * shaderPackGuiScreens;
     * shaderPackProgramConditions;
     * PATH_SHADERS_PROPERTIES;
     * shaderPackClouds;
     * shaderPackOldLighting;
     * shaderPackOldHandLight;
     * shaderPackDynamicHandLight;
     * shaderPackShadowTranslucent;
     * shaderPackUnderwaterOverlay;
     * shaderPackSun;
     * shaderPackMoon;
     * shaderPackVignette;
     * shaderPackBackFaceSolid;
     * shaderPackBackFaceCutout;
     * shaderPackBackFaceCutoutMipped;
     * shaderPackBackFaceTranslucent;
     * shaderPackRainDepth;
     * shaderPackBeaconBeamDepth;
     * shaderPackSeparateAo;
     * shaderPackFrustumCulling;
     * shaderPackResources;
     * currentWorld;
     * shaderPackDimensions;
     * customTexturesGbuffers;
     * customTexturesComposite;
     * customTexturesDeferred;
     * noiseTexturePath;
     * customUniforms;
     * STAGE_GBUFFERS;
     * STAGE_COMPOSITE;
     * STAGE_DEFERRED;
     * STAGE_NAMES;
     * enableShadersOption;
     * enableShadersDebug;
     * saveFinalShaders;
     * blockLightLevel05;
     * blockLightLevel06;
     * blockLightLevel08;
     * aoLevel;
     * sunPathRotation;
     * shadowAngleInterval;
     * fogMode;
     * fogDensity;
     * fogColorR;
     * fogColorG;
     * fogColorB;
     * shadowIntervalSize;
     * terrainIconSize;
     * terrainTextureSize;
     * noiseTexture;
     * noiseTextureEnabled;
     * noiseTextureResolution;
     * colorTextureImageUnit;
     * bigBufferSize;
     * bigBuffer;
     * faProjection;
     * faProjectionInverse;
     * faModelView;
     * faModelViewInverse;
     * faShadowProjection;
     * faShadowProjectionInverse;
     * faShadowModelView;
     * faShadowModelViewInverse;
     * projection;
     * projectionInverse;
     * modelView;
     * modelViewInverse;
     * shadowProjection;
     * shadowProjectionInverse;
     * shadowModelView;
     * shadowModelViewInverse;
     * previousProjection;
     * previousModelView;
     * tempMatrixDirectBuffer;
     * tempDirectFloatBuffer;
     * dfbColorTextures;
     * dfbDepthTextures;
     * sfbColorTextures;
     * sfbDepthTextures;
     * dfbDrawBuffers;
     * sfbDrawBuffers;
     * drawBuffersNone;
     * drawBuffersColorAtt0;
     * dfbColorTexturesFlip;
     * mapBlockToEntityData;
     * formatNames;
     * formatIds;
     * patternLoadEntityDataMap;
     * entityData;
     * entityDataIndex;
     */
}
