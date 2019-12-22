package com.qouteall.immersive_portals.optifine_compatibility;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Vector4f;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.world.World;
import net.optifine.expr.IExpressionBool;
import net.optifine.shaders.*;
import net.optifine.shaders.config.*;
import net.optifine.shaders.uniform.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GLCapabilities;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

//do not forget to update it when optifine changes Shaders class
public class PerDimensionContext {
    public Minecraft mc;
    public GameRenderer entityRenderer;
    public boolean isInitializedOnce = false;
    public boolean isShaderPackInitialized = false;
    public GLCapabilities capabilities;
    public String glVersionString;
    public String glVendorString;
    public String glRendererString;
    public boolean hasGlGenMipmap = false;
    public int countResetDisplayLists = 0;
    public int renderDisplayWidth = 0;
    public int renderDisplayHeight = 0;
    public int renderWidth = 0;
    public int renderHeight = 0;
    public boolean isRenderingWorld = false;
    public boolean isRenderingSky = false;
    public boolean isCompositeRendered = false;
    public boolean isRenderingDfb = false;
    public boolean isShadowPass = false;
    public boolean isSleeping;
    public boolean isRenderingFirstPersonHand;
    public boolean isHandRenderedMain;
    public boolean isHandRenderedOff;
    public boolean skipRenderHandMain;
    public boolean skipRenderHandOff;
    public boolean renderItemKeepDepthMask = false;
    public boolean itemToRenderMainTranslucent = false;
    public boolean itemToRenderOffTranslucent = false;
    public float[] sunPosition;
    public float[] moonPosition;
    public float[] shadowLightPosition;
    public float[] upPosition;
    public float[] shadowLightPositionVector;
    public float[] upPosModelView;
    public float[] sunPosModelView;
    public float[] moonPosModelView;
    public float[] tempMat;
    public float clearColorR;
    public float clearColorG;
    public float clearColorB;
    public float skyColorR;
    public float skyColorG;
    public float skyColorB;
    public long worldTime = 0L;
    public long lastWorldTime = 0L;
    public long diffWorldTime = 0L;
    public float celestialAngle = 0.0F;
    public float sunAngle = 0.0F;
    public float shadowAngle = 0.0F;
    public int moonPhase = 0;
    public long systemTime = 0L;
    public long lastSystemTime = 0L;
    public long diffSystemTime = 0L;
    public int frameCounter = 0;
    public float frameTime = 0.0F;
    public float frameTimeCounter = 0.0F;
    public int systemTimeInt32 = 0;
    public float rainStrength = 0.0F;
    public float wetness = 0.0F;
    public float wetnessHalfLife = 600.0F;
    public float drynessHalfLife = 200.0F;
    public float eyeBrightnessHalflife = 10.0F;
    public boolean usewetness = false;
    public int isEyeInWater = 0;
    public int eyeBrightness = 0;
    public float eyeBrightnessFadeX = 0.0F;
    public float eyeBrightnessFadeY = 0.0F;
    public float eyePosY = 0.0F;
    public float centerDepth = 0.0F;
    public float centerDepthSmooth = 0.0F;
    public float centerDepthSmoothHalflife = 1.0F;
    public boolean centerDepthSmoothEnabled = false;
    public int superSamplingLevel = 1;
    public float nightVision = 0.0F;
    public float blindness = 0.0F;
    public boolean lightmapEnabled = false;
    public boolean fogEnabled = true;
    public int entityAttrib = 10;
    public int midTexCoordAttrib = 11;
    public int tangentAttrib = 12;
    public boolean useEntityAttrib = false;
    public boolean useMidTexCoordAttrib = false;
    public boolean useTangentAttrib = false;
    public boolean progUseEntityAttrib = false;
    public boolean progUseMidTexCoordAttrib = false;
    public boolean progUseTangentAttrib = false;
    public boolean progArbGeometryShader4 = false;
    public int progMaxVerticesOut = 3;
    public boolean hasGeometryShaders = false;
    public int atlasSizeX = 0;
    public int atlasSizeY = 0;
    public ShaderUniforms shaderUniforms;
    public ShaderUniform4f uniform_entityColor;
    public ShaderUniform1i uniform_entityId;
    public ShaderUniform1i uniform_blockEntityId;
    public ShaderUniform1i uniform_texture;
    public ShaderUniform1i uniform_lightmap;
    public ShaderUniform1i uniform_normals;
    public ShaderUniform1i uniform_specular;
    public ShaderUniform1i uniform_shadow;
    public ShaderUniform1i uniform_watershadow;
    public ShaderUniform1i uniform_shadowtex0;
    public ShaderUniform1i uniform_shadowtex1;
    public ShaderUniform1i uniform_depthtex0;
    public ShaderUniform1i uniform_depthtex1;
    public ShaderUniform1i uniform_shadowcolor;
    public ShaderUniform1i uniform_shadowcolor0;
    public ShaderUniform1i uniform_shadowcolor1;
    public ShaderUniform1i uniform_noisetex;
    public ShaderUniform1i uniform_gcolor;
    public ShaderUniform1i uniform_gdepth;
    public ShaderUniform1i uniform_gnormal;
    public ShaderUniform1i uniform_composite;
    public ShaderUniform1i uniform_gaux1;
    public ShaderUniform1i uniform_gaux2;
    public ShaderUniform1i uniform_gaux3;
    public ShaderUniform1i uniform_gaux4;
    public ShaderUniform1i uniform_colortex0;
    public ShaderUniform1i uniform_colortex1;
    public ShaderUniform1i uniform_colortex2;
    public ShaderUniform1i uniform_colortex3;
    public ShaderUniform1i uniform_colortex4;
    public ShaderUniform1i uniform_colortex5;
    public ShaderUniform1i uniform_colortex6;
    public ShaderUniform1i uniform_colortex7;
    public ShaderUniform1i uniform_gdepthtex;
    public ShaderUniform1i uniform_depthtex2;
    public ShaderUniform1i uniform_tex;
    public ShaderUniform1i uniform_heldItemId;
    public ShaderUniform1i uniform_heldBlockLightValue;
    public ShaderUniform1i uniform_heldItemId2;
    public ShaderUniform1i uniform_heldBlockLightValue2;
    public ShaderUniform1i uniform_fogMode;
    public ShaderUniform1f uniform_fogDensity;
    public ShaderUniform3f uniform_fogColor;
    public ShaderUniform3f uniform_skyColor;
    public ShaderUniform1i uniform_worldTime;
    public ShaderUniform1i uniform_worldDay;
    public ShaderUniform1i uniform_moonPhase;
    public ShaderUniform1i uniform_frameCounter;
    public ShaderUniform1f uniform_frameTime;
    public ShaderUniform1f uniform_frameTimeCounter;
    public ShaderUniform1f uniform_sunAngle;
    public ShaderUniform1f uniform_shadowAngle;
    public ShaderUniform1f uniform_rainStrength;
    public ShaderUniform1f uniform_aspectRatio;
    public ShaderUniform1f uniform_viewWidth;
    public ShaderUniform1f uniform_viewHeight;
    public ShaderUniform1f uniform_near;
    public ShaderUniform1f uniform_far;
    public ShaderUniform3f uniform_sunPosition;
    public ShaderUniform3f uniform_moonPosition;
    public ShaderUniform3f uniform_shadowLightPosition;
    public ShaderUniform3f uniform_upPosition;
    public ShaderUniform3f uniform_previousCameraPosition;
    public ShaderUniform3f uniform_cameraPosition;
    public ShaderUniformM4 uniform_gbufferModelView;
    public ShaderUniformM4 uniform_gbufferModelViewInverse;
    public ShaderUniformM4 uniform_gbufferPreviousProjection;
    public ShaderUniformM4 uniform_gbufferProjection;
    public ShaderUniformM4 uniform_gbufferProjectionInverse;
    public ShaderUniformM4 uniform_gbufferPreviousModelView;
    public ShaderUniformM4 uniform_shadowProjection;
    public ShaderUniformM4 uniform_shadowProjectionInverse;
    public ShaderUniformM4 uniform_shadowModelView;
    public ShaderUniformM4 uniform_shadowModelViewInverse;
    public ShaderUniform1f uniform_wetness;
    public ShaderUniform1f uniform_eyeAltitude;
    public ShaderUniform2i uniform_eyeBrightness;
    public ShaderUniform2i uniform_eyeBrightnessSmooth;
    public ShaderUniform2i uniform_terrainTextureSize;
    public ShaderUniform1i uniform_terrainIconSize;
    public ShaderUniform1i uniform_isEyeInWater;
    public ShaderUniform1f uniform_nightVision;
    public ShaderUniform1f uniform_blindness;
    public ShaderUniform1f uniform_screenBrightness;
    public ShaderUniform1i uniform_hideGUI;
    public ShaderUniform1f uniform_centerDepthSmooth;
    public ShaderUniform2i uniform_atlasSize;
    public ShaderUniform4i uniform_blendFunc;
    public double previousCameraPositionX;
    public double previousCameraPositionY;
    public double previousCameraPositionZ;
    public double cameraPositionX;
    public double cameraPositionY;
    public double cameraPositionZ;
    public int cameraOffsetX;
    public int cameraOffsetZ;
    public int shadowPassInterval;
    public boolean needResizeShadow;
    public int shadowMapWidth;
    public int shadowMapHeight;
    public int spShadowMapWidth;
    public int spShadowMapHeight;
    public float shadowMapFOV;
    public float shadowMapHalfPlane;
    public boolean shadowMapIsOrtho;
    public float shadowDistanceRenderMul;
    public int shadowPassCounter;
    public boolean shouldSkipDefaultShadow;
    public boolean waterShadowEnabled;
    public int MaxDrawBuffers = 8;
    public int MaxColorBuffers = 8;
    public int MaxDepthBuffers = 3;
    public int MaxShadowColorBuffers = 8;
    public int MaxShadowDepthBuffers = 2;
    public int usedColorBuffers;
    public int usedDepthBuffers;
    public int usedShadowColorBuffers;
    public int usedShadowDepthBuffers;
    public int usedColorAttachs;
    public int usedDrawBuffers;
    public int dfb;
    public int sfb;
    public int[] gbuffersFormat;
    public boolean[] gbuffersClear;
    public Vector4f[] gbuffersClearColor;
    public Programs programs;
    public Program ProgramNone;
    public Program ProgramShadow;
    public Program ProgramShadowSolid;
    public Program ProgramShadowCutout;
    public Program ProgramBasic;
    public Program ProgramTextured;
    public Program ProgramTexturedLit;
    public Program ProgramSkyBasic;
    public Program ProgramSkyTextured;
    public Program ProgramClouds;
    public Program ProgramTerrain;
    public Program ProgramTerrainSolid;
    public Program ProgramTerrainCutoutMip;
    public Program ProgramTerrainCutout;
    public Program ProgramDamagedBlock;
    public Program ProgramBlock;
    public Program ProgramBeaconBeam;
    public Program ProgramItem;
    public Program ProgramEntities;
    public Program ProgramArmorGlint;
    public Program ProgramSpiderEyes;
    public Program ProgramHand;
    public Program ProgramWeather;
    public Program ProgramDeferredPre;
    public Program[] ProgramsDeferred;
    public Program ProgramDeferred;
    public Program ProgramWater;
    public Program ProgramHandWater;
    public Program ProgramCompositePre;
    public Program[] ProgramsComposite;
    public Program ProgramComposite;
    public Program ProgramFinal;
    public int ProgramCount;
    public Program[] ProgramsAll;
    public Program activeProgram;
    public int activeProgramID;
    public ProgramStack programStackLeash;
    public boolean hasDeferredPrograms;
    public IntBuffer activeDrawBuffers;
    public int activeCompositeMipmapSetting;
    public Properties loadedShaders;
    public Properties shadersConfig;
    public ITextureObject defaultTexture;
    public boolean[] shadowHardwareFilteringEnabled;
    public boolean[] shadowMipmapEnabled;
    public boolean[] shadowFilterNearest;
    public boolean[] shadowColorMipmapEnabled;
    public boolean[] shadowColorFilterNearest;
    public boolean configTweakBlockDamage;
    public boolean configCloudShadow;
    public float configHandDepthMul;
    public float configRenderResMul;
    public float configShadowResMul;
    public int configTexMinFilB;
    public int configTexMinFilN;
    public int configTexMinFilS;
    public int configTexMagFilB;
    public int configTexMagFilN;
    public int configTexMagFilS;
    public boolean configShadowClipFrustrum;
    public boolean configNormalMap;
    public boolean configSpecularMap;
    public PropertyDefaultTrueFalse configOldLighting;
    public PropertyDefaultTrueFalse configOldHandLight;
    public int configAntialiasingLevel;
    public int texMinFilRange = 3;
    public int texMagFilRange = 2;
    public String[] texMinFilDesc;
    public String[] texMagFilDesc;
    public int[] texMinFilValue;
    public int[] texMagFilValue;
    public IShaderPack shaderPack;
    public boolean shaderPackLoaded;
    public String currentShaderName;
    public String SHADER_PACK_NAME_NONE = "OFF";
    public String SHADER_PACK_NAME_DEFAULT = "(internal)";
    public String SHADER_PACKS_DIR_NAME = "shaderpacks";
    public String OPTIONS_FILE_NAME = "optionsshaders.txt";
    public File shaderPacksDir;
    public File configFile;
    public ShaderOption[] shaderPackOptions;
    public Set<String> shaderPackOptionSliders;
    public ShaderProfile[] shaderPackProfiles;
    public Map<String, ScreenShaderOptions> shaderPackGuiScreens;
    public Map<String, IExpressionBool> shaderPackProgramConditions;
    public String PATH_SHADERS_PROPERTIES = "/shaders/shaders.properties";
    public PropertyDefaultFastFancyOff shaderPackClouds;
    public PropertyDefaultTrueFalse shaderPackOldLighting;
    public PropertyDefaultTrueFalse shaderPackOldHandLight;
    public PropertyDefaultTrueFalse shaderPackDynamicHandLight;
    public PropertyDefaultTrueFalse shaderPackShadowTranslucent;
    public PropertyDefaultTrueFalse shaderPackUnderwaterOverlay;
    public PropertyDefaultTrueFalse shaderPackSun;
    public PropertyDefaultTrueFalse shaderPackMoon;
    public PropertyDefaultTrueFalse shaderPackVignette;
    public PropertyDefaultTrueFalse shaderPackBackFaceSolid;
    public PropertyDefaultTrueFalse shaderPackBackFaceCutout;
    public PropertyDefaultTrueFalse shaderPackBackFaceCutoutMipped;
    public PropertyDefaultTrueFalse shaderPackBackFaceTranslucent;
    public PropertyDefaultTrueFalse shaderPackRainDepth;
    public PropertyDefaultTrueFalse shaderPackBeaconBeamDepth;
    public PropertyDefaultTrueFalse shaderPackSeparateAo;
    public PropertyDefaultTrueFalse shaderPackFrustumCulling;
    public Map<String, String> shaderPackResources;
    public World currentWorld;
    public List<Integer> shaderPackDimensions;
    public ICustomTexture[] customTexturesGbuffers;
    public ICustomTexture[] customTexturesComposite;
    public ICustomTexture[] customTexturesDeferred;
    public String noiseTexturePath;
    public CustomUniforms customUniforms;
    public int STAGE_GBUFFERS = 0;
    public int STAGE_COMPOSITE = 1;
    public int STAGE_DEFERRED = 2;
    public String[] STAGE_NAMES;
    public boolean enableShadersOption = true;
    public boolean enableShadersDebug = true;
    public boolean saveFinalShaders;
    public float blockLightLevel05;
    public float blockLightLevel06;
    public float blockLightLevel08;
    public float aoLevel;
    public float sunPathRotation;
    public float shadowAngleInterval;
    public int fogMode;
    public float fogDensity;
    public float fogColorR;
    public float fogColorG;
    public float fogColorB;
    public float shadowIntervalSize;
    public int terrainIconSize;
    public int[] terrainTextureSize;
    public ICustomTexture noiseTexture;
    public boolean noiseTextureEnabled;
    public int noiseTextureResolution;
    public int[] colorTextureImageUnit;
    public int bigBufferSize;
    public ByteBuffer bigBuffer;
    public float[] faProjection;
    public float[] faProjectionInverse;
    public float[] faModelView;
    public float[] faModelViewInverse;
    public float[] faShadowProjection;
    public float[] faShadowProjectionInverse;
    public float[] faShadowModelView;
    public float[] faShadowModelViewInverse;
    public FloatBuffer projection;
    public FloatBuffer projectionInverse;
    public FloatBuffer modelView;
    public FloatBuffer modelViewInverse;
    public FloatBuffer shadowProjection;
    public FloatBuffer shadowProjectionInverse;
    public FloatBuffer shadowModelView;
    public FloatBuffer shadowModelViewInverse;
    public FloatBuffer previousProjection;
    public FloatBuffer previousModelView;
    public FloatBuffer tempMatrixDirectBuffer;
    public FloatBuffer tempDirectFloatBuffer;
    public IntBuffer dfbColorTextures;
    public IntBuffer dfbDepthTextures;
    public IntBuffer sfbColorTextures;
    public IntBuffer sfbDepthTextures;
    public IntBuffer dfbDrawBuffers;
    public IntBuffer sfbDrawBuffers;
    public IntBuffer drawBuffersNone;
    public IntBuffer drawBuffersColorAtt0;
    public FlipTextures dfbColorTexturesFlip;
    public Map<Block, Integer> mapBlockToEntityData;
    public String[] formatNames;
    public int[] formatIds;
    public Pattern patternLoadEntityDataMap;
    public int[] entityData;
    public int entityDataIndex;
    
    public ByteBuffer nextByteBuffer(int size) {
        ByteBuffer buffer = bigBuffer;
        int pos = buffer.limit();
        buffer.position(pos).limit(pos + size);
        return buffer.slice();
    }
    
    public IntBuffer nextIntBuffer(int size) {
        ByteBuffer buffer = bigBuffer;
        int pos = buffer.limit();
        buffer.position(pos).limit(pos + size * 4);
        return buffer.asIntBuffer();
    }
    
    public FloatBuffer nextFloatBuffer(int size) {
        ByteBuffer buffer = bigBuffer;
        int pos = buffer.limit();
        buffer.position(pos).limit(pos + size * 4);
        return buffer.asFloatBuffer();
    }
    
    public PerDimensionContext() {
    
    
    }
    
    //some fields are initialized in loadShaderPack() it does not recognize dimension now
    //some fields are initialized in init() it recognizes dimension at that time
    //reset the fields that will be changed by init()
    //do not reset the fields that was initialized by loadShaderPack()
    public void doSpecialInit() {
//        sunPosition = new float[4];
//        moonPosition = new float[4];
//        shadowLightPosition = new float[4];
        //upPosition = new float[4]; //Fix Sildurs shader black entities in nether
//        shadowLightPositionVector = new float[4];
//        upPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
//        sunPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
//        moonPosModelView = new float[]{0.0F, -100.0F, 0.0F, 0.0F};
//        tempMat = new float[16];
//        shaderUniforms = new ShaderUniforms();

//        uniform_entityColor = shaderUniforms.make4f("entityColor");
//        uniform_entityId = shaderUniforms.make1i("entityId");
//        uniform_blockEntityId = shaderUniforms.make1i("blockEntityId");
//        uniform_texture = shaderUniforms.make1i("texture");
//        uniform_lightmap = shaderUniforms.make1i("lightmap");
//        uniform_normals = shaderUniforms.make1i("normals");
//        uniform_specular = shaderUniforms.make1i("specular");
//        uniform_shadow = shaderUniforms.make1i("shadow");
//        uniform_watershadow = shaderUniforms.make1i("watershadow");
//        uniform_shadowtex0 = shaderUniforms.make1i("shadowtex0");
//        uniform_shadowtex1 = shaderUniforms.make1i("shadowtex1");
//        uniform_depthtex0 = shaderUniforms.make1i("depthtex0");
//        uniform_depthtex1 = shaderUniforms.make1i("depthtex1");
//        uniform_shadowcolor = shaderUniforms.make1i("shadowcolor");
//        uniform_shadowcolor0 = shaderUniforms.make1i("shadowcolor0");
//        uniform_shadowcolor1 = shaderUniforms.make1i("shadowcolor1");
//        uniform_noisetex = shaderUniforms.make1i("noisetex");
//        uniform_gcolor = shaderUniforms.make1i("gcolor");
//        uniform_gdepth = shaderUniforms.make1i("gdepth");
//        uniform_gnormal = shaderUniforms.make1i("gnormal");
//        uniform_composite = shaderUniforms.make1i("composite");
//        uniform_gaux1 = shaderUniforms.make1i("gaux1");
//        uniform_gaux2 = shaderUniforms.make1i("gaux2");
//        uniform_gaux3 = shaderUniforms.make1i("gaux3");
//        uniform_gaux4 = shaderUniforms.make1i("gaux4");
//        uniform_colortex0 = shaderUniforms.make1i("colortex0");
//        uniform_colortex1 = shaderUniforms.make1i("colortex1");
//        uniform_colortex2 = shaderUniforms.make1i("colortex2");
//        uniform_colortex3 = shaderUniforms.make1i("colortex3");
//        uniform_colortex4 = shaderUniforms.make1i("colortex4");
//        uniform_colortex5 = shaderUniforms.make1i("colortex5");
//        uniform_colortex6 = shaderUniforms.make1i("colortex6");
//        uniform_colortex7 = shaderUniforms.make1i("colortex7");
//        uniform_gdepthtex = shaderUniforms.make1i("gdepthtex");
//        uniform_depthtex2 = shaderUniforms.make1i("depthtex2");
//        uniform_tex = shaderUniforms.make1i("tex");
//        uniform_heldItemId = shaderUniforms.make1i("heldItemId");
//        uniform_heldBlockLightValue = shaderUniforms.make1i("heldBlockLightValue");
//        uniform_heldItemId2 = shaderUniforms.make1i("heldItemId2");
//        uniform_heldBlockLightValue2 = shaderUniforms.make1i("heldBlockLightValue2");
//        uniform_fogMode = shaderUniforms.make1i("fogMode");
//        uniform_fogDensity = shaderUniforms.make1f("fogDensity");
//        uniform_fogColor = shaderUniforms.make3f("fogColor");
//        uniform_skyColor = shaderUniforms.make3f("skyColor");
//        uniform_worldTime = shaderUniforms.make1i("worldTime");
//        uniform_worldDay = shaderUniforms.make1i("worldDay");
//        uniform_moonPhase = shaderUniforms.make1i("moonPhase");
//        uniform_frameCounter = shaderUniforms.make1i("frameCounter");
//        uniform_frameTime = shaderUniforms.make1f("frameTime");
//        uniform_frameTimeCounter = shaderUniforms.make1f("frameTimeCounter");
//        uniform_sunAngle = shaderUniforms.make1f("sunAngle");
//        uniform_shadowAngle = shaderUniforms.make1f("shadowAngle");
//        uniform_rainStrength = shaderUniforms.make1f("rainStrength");
//        uniform_aspectRatio = shaderUniforms.make1f("aspectRatio");
//        uniform_viewWidth = shaderUniforms.make1f("viewWidth");
//        uniform_viewHeight = shaderUniforms.make1f("viewHeight");
//        uniform_near = shaderUniforms.make1f("near");
//        uniform_far = shaderUniforms.make1f("far");
//        uniform_sunPosition = shaderUniforms.make3f("sunPosition");
//        uniform_moonPosition = shaderUniforms.make3f("moonPosition");
//        uniform_shadowLightPosition = shaderUniforms.make3f("shadowLightPosition");
//        uniform_upPosition = shaderUniforms.make3f("upPosition");
//        uniform_previousCameraPosition = shaderUniforms.make3f("previousCameraPosition");
//        uniform_cameraPosition = shaderUniforms.make3f("cameraPosition");
//        uniform_gbufferModelView = shaderUniforms.makeM4("gbufferModelView");
//        uniform_gbufferModelViewInverse = shaderUniforms.makeM4("gbufferModelViewInverse");
//        uniform_gbufferPreviousProjection = shaderUniforms.makeM4("gbufferPreviousProjection");
//        uniform_gbufferProjection = shaderUniforms.makeM4("gbufferProjection");
//        uniform_gbufferProjectionInverse = shaderUniforms.makeM4("gbufferProjectionInverse");
//        uniform_gbufferPreviousModelView = shaderUniforms.makeM4("gbufferPreviousModelView");
//        uniform_shadowProjection = shaderUniforms.makeM4("shadowProjection");
//        uniform_shadowProjectionInverse = shaderUniforms.makeM4("shadowProjectionInverse");
//        uniform_shadowModelView = shaderUniforms.makeM4("shadowModelView");
//        uniform_shadowModelViewInverse = shaderUniforms.makeM4("shadowModelViewInverse");
//        uniform_wetness = shaderUniforms.make1f("wetness");
//        uniform_eyeAltitude = shaderUniforms.make1f("eyeAltitude");
//        uniform_eyeBrightness = shaderUniforms.make2i("eyeBrightness");
//        uniform_eyeBrightnessSmooth = shaderUniforms.make2i("eyeBrightnessSmooth");
//        uniform_terrainTextureSize = shaderUniforms.make2i("terrainTextureSize");
//        uniform_terrainIconSize = shaderUniforms.make1i("terrainIconSize");
//        uniform_isEyeInWater = shaderUniforms.make1i("isEyeInWater");
//        uniform_nightVision = shaderUniforms.make1f("nightVision");
//        uniform_blindness = shaderUniforms.make1f("blindness");
//        uniform_screenBrightness = shaderUniforms.make1f("screenBrightness");
//        uniform_hideGUI = shaderUniforms.make1i("hideGUI");
//        uniform_centerDepthSmooth = shaderUniforms.make1f("centerDepthSmooth");
//        uniform_atlasSize = shaderUniforms.make2i("atlasSize");
//        uniform_blendFunc = shaderUniforms.make4i("blendFunc");
//        shadowPassInterval = 0;
//        needResizeShadow = false;
//        shadowMapWidth = 1024;
//        shadowMapHeight = 1024;
//        spShadowMapWidth = 1024;
//        spShadowMapHeight = 1024;
//        shadowMapFOV = 90.0F;
//        shadowMapHalfPlane = 160.0F;
//        shadowMapIsOrtho = true;
//        shadowDistanceRenderMul = -1.0F;
//        shadowPassCounter = 0;
//        shouldSkipDefaultShadow = false;
//        waterShadowEnabled = false;
        usedColorBuffers = 0;
        usedDepthBuffers = 0;
        usedShadowColorBuffers = 0;
        usedShadowDepthBuffers = 0;
        usedColorAttachs = 0;
        usedDrawBuffers = 0;
        dfb = 0;
        sfb = 0;
        gbuffersFormat = new int[8];
        gbuffersClear = new boolean[8];
        gbuffersClearColor = new Vector4f[8];
        programs = new Programs();
        ProgramNone = programs.getProgramNone();
        ProgramShadow = programs.makeShadow("shadow", ProgramNone);
        ProgramShadowSolid = programs.makeShadow("shadow_solid", ProgramShadow);
        ProgramShadowCutout = programs.makeShadow("shadow_cutout", ProgramShadow);
        ProgramBasic = programs.makeGbuffers("gbuffers_basic", ProgramNone);
        ProgramTextured = programs.makeGbuffers("gbuffers_textured", ProgramBasic);
        ProgramTexturedLit = programs.makeGbuffers("gbuffers_textured_lit", ProgramTextured);
        ProgramSkyBasic = programs.makeGbuffers("gbuffers_skybasic", ProgramBasic);
        ProgramSkyTextured = programs.makeGbuffers("gbuffers_skytextured", ProgramTextured);
        ProgramClouds = programs.makeGbuffers("gbuffers_clouds", ProgramTextured);
        ProgramTerrain = programs.makeGbuffers("gbuffers_terrain", ProgramTexturedLit);
        ProgramTerrainSolid = programs.makeGbuffers("gbuffers_terrain_solid", ProgramTerrain);
        ProgramTerrainCutoutMip = programs.makeGbuffers(
            "gbuffers_terrain_cutout_mip",
            ProgramTerrain
        );
        ProgramTerrainCutout = programs.makeGbuffers("gbuffers_terrain_cutout", ProgramTerrain);
        ProgramDamagedBlock = programs.makeGbuffers("gbuffers_damagedblock", ProgramTerrain);
        ProgramBlock = programs.makeGbuffers("gbuffers_block", ProgramTerrain);
        ProgramBeaconBeam = programs.makeGbuffers("gbuffers_beaconbeam", ProgramTextured);
        ProgramItem = programs.makeGbuffers("gbuffers_item", ProgramTexturedLit);
        ProgramEntities = programs.makeGbuffers("gbuffers_entities", ProgramTexturedLit);
        ProgramArmorGlint = programs.makeGbuffers("gbuffers_armor_glint", ProgramTextured);
        ProgramSpiderEyes = programs.makeGbuffers("gbuffers_spidereyes", ProgramTextured);
        ProgramHand = programs.makeGbuffers("gbuffers_hand", ProgramTexturedLit);
        ProgramWeather = programs.makeGbuffers("gbuffers_weather", ProgramTexturedLit);
        ProgramDeferredPre = programs.makeVirtual("deferred_pre");
        ProgramsDeferred = programs.makeDeferreds("deferred", 16);
        ProgramDeferred = ProgramsDeferred[0];
        ProgramWater = programs.makeGbuffers("gbuffers_water", ProgramTerrain);
        ProgramHandWater = programs.makeGbuffers("gbuffers_hand_water", ProgramHand);
        ProgramCompositePre = programs.makeVirtual("composite_pre");
        ProgramsComposite = programs.makeComposites("composite", 16);
        ProgramComposite = ProgramsComposite[0];
        ProgramFinal = programs.makeComposite("final");
        ProgramCount = programs.getCount();
        ProgramsAll = programs.getPrograms();
        activeProgram = ProgramNone;
        activeProgramID = 0;
        programStackLeash = new ProgramStack();
//        hasDeferredPrograms = false;
        activeDrawBuffers = null;
//        activeCompositeMipmapSetting = 0;
//        loadedShaders = null;
//        shadersConfig = null;
//        defaultTexture = null;
        shadowHardwareFilteringEnabled = new boolean[2];
        shadowMipmapEnabled = new boolean[2];
        shadowFilterNearest = new boolean[2];
        shadowColorMipmapEnabled = new boolean[8];
        shadowColorFilterNearest = new boolean[8];
//        configTweakBlockDamage = false;
//        configCloudShadow = false;
//        configHandDepthMul = 0.125F;
//        configRenderResMul = 1.0F;
//        configShadowResMul = 1.0F;
//        configTexMinFilB = 0;
//        configTexMinFilN = 0;
//        configTexMinFilS = 0;
//        configTexMagFilB = 0;
//        configTexMagFilN = 0;
//        configTexMagFilS = 0;
//        configShadowClipFrustrum = true;
//        configNormalMap = true;
//        configSpecularMap = true;
//        configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
//        configOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
//        configAntialiasingLevel = 0;
//        texMinFilDesc = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
//        texMagFilDesc = new String[]{"Nearest", "Linear"};
//        texMinFilValue = new int[]{9728, 9984, 9986};
//        texMagFilValue = new int[]{9728, 9729};
//        shaderPack = null;
//        shaderPackLoaded = false;
//        shaderPacksDir = new File(MinecraftClient.getInstance().runDirectory, "shaderpacks");
//        configFile = new File(MinecraftClient.getInstance().runDirectory, "optionsshaders.txt");
//        shaderPackOptions = null;
//        shaderPackOptionSliders = null;
//        shaderPackProfiles = null;
//        shaderPackGuiScreens = null;
//        shaderPackProgramConditions = new HashMap();
//        shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
//        shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
//        shaderPackOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
//        shaderPackDynamicHandLight = new PropertyDefaultTrueFalse("dynamicHandLight", "Dynamic Hand Light", 0);
//        shaderPackShadowTranslucent = new PropertyDefaultTrueFalse("shadowTranslucent", "Shadow Translucent", 0);
//        shaderPackUnderwaterOverlay = new PropertyDefaultTrueFalse("underwaterOverlay", "Underwater Overlay", 0);
//        shaderPackSun = new PropertyDefaultTrueFalse("sun", "Sun", 0);
//        shaderPackMoon = new PropertyDefaultTrueFalse("moon", "Moon", 0);
//        shaderPackVignette = new PropertyDefaultTrueFalse("vignette", "Vignette", 0);
//        shaderPackBackFaceSolid = new PropertyDefaultTrueFalse("backFace.solid", "Back-face Solid", 0);
//        shaderPackBackFaceCutout = new PropertyDefaultTrueFalse("backFace.cutout", "Back-face Cutout", 0);
//        shaderPackBackFaceCutoutMipped = new PropertyDefaultTrueFalse("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
//        shaderPackBackFaceTranslucent = new PropertyDefaultTrueFalse("backFace.translucent", "Back-face Translucent", 0);
//        shaderPackRainDepth = new PropertyDefaultTrueFalse("rain.depth", "Rain Depth", 0);
//        shaderPackBeaconBeamDepth = new PropertyDefaultTrueFalse("beacon.beam.depth", "Rain Depth", 0);
//        shaderPackSeparateAo = new PropertyDefaultTrueFalse("separateAo", "Separate AO", 0);
//        shaderPackFrustumCulling = new PropertyDefaultTrueFalse("frustum.culling", "Frustum Culling", 0);
//        shaderPackResources = new HashMap();
        currentWorld = null;
//        shaderPackDimensions = new ArrayList();
//        customTexturesGbuffers = null;
//        customTexturesComposite = null;
//        customTexturesDeferred = null;
//        noiseTexturePath = null;
        
        //multiple contexts share the same customUniforms
//        customUniforms = null;

//        STAGE_NAMES = new String[]{"gbuffers", "composite", "deferred"};
//        saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
//        blockLightLevel05 = 0.5F;
//        blockLightLevel06 = 0.6F;
//        blockLightLevel08 = 0.8F;
//        aoLevel = -1.0F;
        sunPathRotation = 0.0F;
        shadowAngleInterval = 0.0F;
        fogMode = 0;
        fogDensity = 0.0F;
        shadowIntervalSize = 2.0F;
//        terrainIconSize = 16;
//        terrainTextureSize = new int[2];
//        noiseTextureEnabled = false;
//        noiseTextureResolution = 256;
//        colorTextureImageUnit = new int[]{0, 1, 2, 3, 7, 8, 9, 10};
        bigBufferSize = (285 + 8 * ProgramCount) * 4; //I increased buffer size
        bigBuffer = (ByteBuffer) BufferUtils.createByteBuffer(bigBufferSize).limit(0);
        faProjection = new float[16];
        faProjectionInverse = new float[16];
        faModelView = new float[16];
        faModelViewInverse = new float[16];
        faShadowProjection = new float[16];
        faShadowProjectionInverse = new float[16];
        faShadowModelView = new float[16];
        faShadowModelViewInverse = new float[16];
        projection = nextFloatBuffer(16);
        projectionInverse = nextFloatBuffer(16);
        modelView = nextFloatBuffer(16);
        modelViewInverse = nextFloatBuffer(16);
        shadowProjection = nextFloatBuffer(16);
        shadowProjectionInverse = nextFloatBuffer(16);
        shadowModelView = nextFloatBuffer(16);
        shadowModelViewInverse = nextFloatBuffer(16);
        previousProjection = nextFloatBuffer(16);
        previousModelView = nextFloatBuffer(16);
        tempMatrixDirectBuffer = nextFloatBuffer(16);
        tempDirectFloatBuffer = nextFloatBuffer(16);
        dfbColorTextures = nextIntBuffer(16);
        dfbDepthTextures = nextIntBuffer(3);
        sfbColorTextures = nextIntBuffer(8);
        sfbDepthTextures = nextIntBuffer(2);
        dfbDrawBuffers = nextIntBuffer(8);
        sfbDrawBuffers = nextIntBuffer(8);
        drawBuffersNone = (IntBuffer) nextIntBuffer(8).limit(0);
        drawBuffersColorAtt0 = (IntBuffer) nextIntBuffer(8).put(36064).position(0).limit(1);
        dfbColorTexturesFlip = new FlipTextures(dfbColorTextures, 8);
//        formatNames = new String[]{"R8", "RG8", "RGB8", "RGBA8", "R8_SNORM", "RG8_SNORM", "RGB8_SNORM", "RGBA8_SNORM", "R16", "RG16", "RGB16", "RGBA16", "R16_SNORM", "RG16_SNORM", "RGB16_SNORM", "RGBA16_SNORM", "R16F", "RG16F", "RGB16F", "RGBA16F", "R32F", "RG32F", "RGB32F", "RGBA32F", "R32I", "RG32I", "RGB32I", "RGBA32I", "R32UI", "RG32UI", "RGB32UI", "RGBA32UI", "R3_G3_B2", "RGB5_A1", "RGB10_A2", "R11F_G11F_B10F", "RGB9_E5"};
//        formatIds = new int[]{33321, 33323, 32849, 32856, 36756, 36757, 36758, 36759, 33322, 33324, 32852, 32859, 36760, 36761, 36762, 36763, 33325, 33327, 34843, 34842, 33326, 33328, 34837, 34836, 33333, 33339, 36227, 36226, 33334, 33340, 36209, 36208, 10768, 32855, 32857, 35898, 35901};
//        patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
        entityData = new int[32];
        entityDataIndex = 0;
    }
    
}
