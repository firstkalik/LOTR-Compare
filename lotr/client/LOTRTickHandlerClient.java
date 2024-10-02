/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IReloadableResourceManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.StringUtils
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraftforge.client.GuiIngameForge
 *  net.minecraftforge.client.event.EntityViewRenderEvent
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent
 *  net.minecraftforge.client.event.FOVUpdateEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Text
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.event.world.WorldEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 */
package lotr.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import lotr.client.LOTRAlignmentTicker;
import lotr.client.LOTRAttackTiming;
import lotr.client.LOTRClientProxy;
import lotr.client.LOTREntityRenderer;
import lotr.client.LOTRKeyHandler;
import lotr.client.LOTRReflectionClient;
import lotr.client.LOTRSpeechClient;
import lotr.client.fx.LOTREffectRenderer;
import lotr.client.fx.LOTREntityDeadMarshFace;
import lotr.client.gui.LOTRGuiMap;
import lotr.client.gui.LOTRGuiMenu;
import lotr.client.gui.LOTRGuiMessage;
import lotr.client.gui.LOTRGuiMiniquestTracker;
import lotr.client.gui.LOTRGuiNotificationDisplay;
import lotr.client.model.LOTRModelCompass;
import lotr.client.render.LOTRCloudRenderer;
import lotr.client.render.LOTRRenderNorthernLights;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.client.render.tileentity.LOTRTileEntityMobSpawnerRenderer;
import lotr.client.sound.LOTRAmbience;
import lotr.client.sound.LOTRMusic;
import lotr.client.sound.LOTRMusicTicker;
import lotr.client.sound.LOTRMusicTrack;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDate;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRPlayerFallEventHandler2;
import lotr.common.LOTRSquadrons;
import lotr.common.LOTRTime;
import lotr.common.block.LOTRBlockLeavesBase;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.LOTRInvasionStatus;
import lotr.common.entity.LOTRMountFunctions;
import lotr.common.entity.item.LOTREntityPortal;
import lotr.common.entity.npc.LOTREntityBalrog;
import lotr.common.entity.npc.LOTREntityBarrowWight;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionBounties;
import lotr.common.fac.LOTRFactionRank;
import lotr.common.fac.LOTRFactionRelations;
import lotr.common.fellowship.LOTRFellowshipData;
import lotr.common.item.LOTRItemBanner;
import lotr.common.item.LOTRItemBlowgun;
import lotr.common.item.LOTRItemBow;
import lotr.common.item.LOTRItemCrossbow;
import lotr.common.item.LOTRItemMap;
import lotr.common.item.LOTRItemOwnership;
import lotr.common.item.LOTRItemSpear;
import lotr.common.item.LOTRPoisonedDrinks;
import lotr.common.item.LOTRWeaponStats;
import lotr.common.quest.IPickpocketable;
import lotr.common.util.LOTRColorUtil;
import lotr.common.util.LOTRFunctions;
import lotr.common.util.LOTRLog;
import lotr.common.util.LOTRVersionChecker;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenBarrowDowns;
import lotr.common.world.biome.LOTRBiomeGenDeadMarshes;
import lotr.common.world.biome.LOTRBiomeGenMirkwoodCorrupted;
import lotr.common.world.biome.LOTRBiomeGenMistyMountains;
import lotr.common.world.biome.LOTRBiomeGenMorgulVale;
import lotr.common.world.biome.LOTRBiomeGenUtumno;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.map.LOTRConquestGrid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class LOTRTickHandlerClient {
    private static ResourceLocation portalOverlay = new ResourceLocation("lotr:misc/portal_overlay.png");
    private static ResourceLocation elvenPortalOverlay = new ResourceLocation("lotr:misc/elvenportal_overlay.png");
    private static ResourceLocation morgulPortalOverlay = new ResourceLocation("lotr:misc/morgulportal_overlay.png");
    private static ResourceLocation mistOverlay = new ResourceLocation("lotr:misc/mist_overlay.png");
    private static ResourceLocation frostOverlay = new ResourceLocation("lotr:misc/frost_overlay.png");
    private static ResourceLocation burnOverlay = new ResourceLocation("lotr:misc/burn_overlay.png");
    private static ResourceLocation wightOverlay = new ResourceLocation("lotr:misc/wight.png");
    public static HashMap playersInPortals = new HashMap();
    public static HashMap playersInElvenPortals = new HashMap();
    public static HashMap playersInMorgulPortals = new HashMap();
    private LOTRAmbience ambienceTicker;
    public static int clientTick;
    public static float renderTick;
    private GuiScreen lastGuiOpen;
    private int mistTick;
    private int prevMistTick;
    private float mistFactor;
    private float sunGlare;
    private float prevSunGlare;
    private float rainFactor;
    private float prevRainFactor;
    private int alignmentXBase;
    private int alignmentYBase;
    private int alignmentXCurrent;
    private int alignmentYCurrent;
    private int alignmentXPrev;
    private int alignmentYPrev;
    private boolean firstAlignmentRender = true;
    public static int alignDrainTick;
    public static final int alignDrainTickMax = 200;
    public static int alignDrainNum;
    public static LOTRInvasionStatus watchedInvasion;
    public static LOTRGuiNotificationDisplay notificationDisplay;
    public static LOTRGuiMiniquestTracker miniquestTracker;
    private boolean wasHoldingBannerWithExistingProtection;
    private int bannerRepossessDisplayTick;
    private int frostTick;
    private int burnTick;
    private int drunkennessDirection = 1;
    private int newDate = 0;
    private float utumnoCamRoll = 0.0f;
    public boolean inUtumnoReturnPortal = false;
    public int utumnoReturnX;
    public int utumnoReturnZ;
    private double lastUtumnoReturnY = -1.0;
    private int prevWightLookTick = 0;
    private int wightLookTick = 0;
    public static boolean anyWightsViewed;
    private int prevWightNearTick = 0;
    private int wightNearTick = 0;
    private int prevBalrogNearTick = 0;
    private int balrogNearTick = 0;
    private float balrogFactor;
    public static int scrapTraderMisbehaveTick;
    private float[] storedLightTable;
    private int storedScrapID;
    private boolean addedClientPoisonEffect = false;
    private LOTRMusicTrack lastTrack = null;
    private int musicTrackTick = 0;
    public boolean cancelItemHighlight = false;
    private ItemStack lastHighlightedItemstack;
    private String highlightedItemstackName;

    public LOTRTickHandlerClient() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.ambienceTicker = new LOTRAmbience();
        notificationDisplay = new LOTRGuiNotificationDisplay();
        miniquestTracker = new LOTRGuiMiniquestTracker();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        block78: {
            GuiScreen guiscreen;
            Minecraft minecraft;
            block79: {
                EntityClientPlayerMP entityplayer;
                WorldClient world;
                int i;
                block80: {
                    block81: {
                        minecraft = Minecraft.getMinecraft();
                        entityplayer = minecraft.thePlayer;
                        world = minecraft.theWorld;
                        if (event.phase == TickEvent.Phase.START) {
                            ++clientTick;
                            if (LOTRConfig.fixRenderDistance && !FMLClientHandler.instance().hasOptifine()) {
                                GameSettings gs = Minecraft.getMinecraft().gameSettings;
                                int renderDistance = gs.renderDistanceChunks;
                                if (renderDistance > 16) {
                                    renderDistance = 16;
                                    gs.renderDistanceChunks = 16;
                                    gs.saveOptions();
                                    LOTRLog.logger.info("LOTR: Render distance was above 16 - set to 16 to prevent a vanilla crash");
                                }
                            }
                            if (minecraft.entityRenderer != null && !(minecraft.entityRenderer instanceof LOTREntityRenderer)) {
                                minecraft.entityRenderer = new LOTREntityRenderer(minecraft, minecraft.getResourceManager());
                                ((IReloadableResourceManager)minecraft.getResourceManager()).registerReloadListener((IResourceManagerReloadListener)minecraft.entityRenderer);
                                FMLLog.info((String)"LOTR: Successfully replaced entityrenderer", (Object[])new Object[0]);
                            }
                        }
                        Minecraft mc = Minecraft.getMinecraft();
                        if (mc.thePlayer != null) {
                            if (mc.thePlayer.movementInput instanceof LOTRPlayerFallEventHandler2) {
                                ((LOTRPlayerFallEventHandler2)mc.thePlayer.movementInput).onUpdate((EntityPlayerSP)mc.thePlayer);
                            } else if (LOTRPlayerFallEventHandler2.shouldPerform((EntityPlayerSP)mc.thePlayer)) {
                                LOTRPlayerFallEventHandler2 input = new LOTRPlayerFallEventHandler2(mc.gameSettings);
                                input.copyData(mc.thePlayer.movementInput);
                                mc.thePlayer.movementInput = input;
                            }
                        }
                        if (event.phase != TickEvent.Phase.END) break block78;
                        LOTRTileEntityMobSpawnerRenderer.onClientTick();
                        if (minecraft.currentScreen == null) {
                            this.lastGuiOpen = null;
                        }
                        if (FMLClientHandler.instance().hasOptifine()) {
                            int optifineSetting = 0;
                            try {
                                Object field = GameSettings.class.getField("ofTrees").get((Object)minecraft.gameSettings);
                                if (field instanceof Integer) {
                                    optifineSetting = (Integer)field;
                                }
                            }
                            catch (Exception field) {
                                // empty catch block
                            }
                            boolean fancyGraphics = optifineSetting == 0 ? minecraft.gameSettings.fancyGraphics : (optifineSetting == 1 ? false : optifineSetting == 2);
                            LOTRBlockLeavesBase.setAllGraphicsLevels(fancyGraphics);
                        } else {
                            LOTRBlockLeavesBase.setAllGraphicsLevels(minecraft.gameSettings.fancyGraphics);
                        }
                        if (entityplayer == null || world == null) break block79;
                        if (LOTRConfig.checkUpdates) {
                            LOTRVersionChecker.checkForUpdates();
                        }
                        if (this.isGamePaused(minecraft)) break block80;
                        miniquestTracker.update(minecraft, (EntityPlayer)entityplayer);
                        LOTRAlignmentTicker.updateAll((EntityPlayer)entityplayer, false);
                        if (alignDrainTick > 0 && --alignDrainTick <= 0) {
                            alignDrainNum = 0;
                        }
                        watchedInvasion.tick();
                        boolean isHoldingBannerWithExistingProtection = LOTRItemBanner.isHoldingBannerWithExistingProtection((EntityPlayer)entityplayer);
                        this.bannerRepossessDisplayTick = isHoldingBannerWithExistingProtection && !this.wasHoldingBannerWithExistingProtection ? 60 : (this.bannerRepossessDisplayTick = this.bannerRepossessDisplayTick - 1);
                        this.wasHoldingBannerWithExistingProtection = isHoldingBannerWithExistingProtection;
                        EntityLivingBase viewer = minecraft.renderViewEntity;
                        i = MathHelper.floor_double((double)viewer.posX);
                        int j = MathHelper.floor_double((double)viewer.boundingBox.minY);
                        int k = MathHelper.floor_double((double)viewer.posZ);
                        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
                        LOTRBiome.updateWaterColor(i, j, k);
                        LOTRBiomeGenUtumno.updateFogColor(i, j, k);
                        LOTRCloudRenderer.updateClouds(world);
                        if (LOTRConfig.aurora) {
                            LOTRRenderNorthernLights.update(viewer);
                        }
                        LOTRSpeechClient.update();
                        LOTRKeyHandler.update();
                        LOTRAttackTiming.update();
                        this.prevMistTick = this.mistTick;
                        if (viewer.posY >= 72.0 && biome instanceof LOTRBiomeGenMistyMountains && biome != LOTRBiome.mistyMountainsFoothills && world.canBlockSeeTheSky(i, j, k) && world.getSavedLightValue(EnumSkyBlock.Block, i, j, k) < 7) {
                            if (this.mistTick < 80) {
                                ++this.mistTick;
                            }
                        } else if (this.mistTick > 0) {
                            --this.mistTick;
                        }
                        if (this.frostTick > 0) {
                            --this.frostTick;
                        }
                        if (this.burnTick > 0) {
                            --this.burnTick;
                        }
                        this.prevWightLookTick = this.wightLookTick;
                        if (anyWightsViewed) {
                            if (this.wightLookTick < 100) {
                                ++this.wightLookTick;
                            }
                        } else if (this.wightLookTick > 0) {
                            --this.wightLookTick;
                        }
                        this.prevWightNearTick = this.wightNearTick;
                        double wightRange = 32.0;
                        List nearbyWights = world.getEntitiesWithinAABB(LOTREntityBarrowWight.class, viewer.boundingBox.expand(wightRange, wightRange, wightRange));
                        if (!nearbyWights.isEmpty()) {
                            if (this.wightNearTick < 100) {
                                ++this.wightNearTick;
                            }
                        } else if (this.wightNearTick > 0) {
                            --this.wightNearTick;
                        }
                        this.prevBalrogNearTick = this.balrogNearTick;
                        double balrogRange = 24.0;
                        List nearbyBalrogs = world.getEntitiesWithinAABB(LOTREntityBalrog.class, viewer.boundingBox.expand(balrogRange, balrogRange, balrogRange));
                        if (!nearbyBalrogs.isEmpty()) {
                            if (this.balrogNearTick < 100) {
                                ++this.balrogNearTick;
                            }
                        } else if (this.balrogNearTick > 0) {
                            --this.balrogNearTick;
                        }
                        if (LOTRConfig.enableSunFlare && world.provider instanceof LOTRWorldProvider && !world.provider.hasNoSky) {
                            this.prevSunGlare = this.sunGlare;
                            MovingObjectPosition look = viewer.rayTrace(10000.0, renderTick);
                            boolean lookingAtSky = look == null || look.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
                            boolean biomeHasSun = true;
                            if (biome instanceof LOTRBiome) {
                                biomeHasSun = ((LOTRBiome)biome).hasSky();
                            }
                            float sunPitch = world.getCelestialAngle(renderTick) * 360.0f - 90.0f;
                            float sunYaw = 90.0f;
                            float yc = MathHelper.cos((float)((float)Math.toRadians(-sunYaw - 180.0f)));
                            float ys = MathHelper.sin((float)((float)Math.toRadians(-sunYaw - 180.0f)));
                            float pc = -MathHelper.cos((float)((float)Math.toRadians(-sunPitch)));
                            float ps = MathHelper.sin((float)((float)Math.toRadians(-sunPitch)));
                            Vec3 sunVec = Vec3.createVectorHelper((double)(ys * pc), (double)ps, (double)(yc * pc));
                            Vec3 lookVec = viewer.getLook(renderTick);
                            double cos = lookVec.dotProduct(sunVec) / (lookVec.lengthVector() * sunVec.lengthVector());
                            float cosThreshold = 0.95f;
                            float cQ = ((float)cos - cosThreshold) / (1.0f - cosThreshold);
                            cQ = Math.max(cQ, 0.0f);
                            float brightness = world.getSunBrightness(renderTick);
                            float brightnessThreshold = 0.7f;
                            float bQ = (brightness - brightnessThreshold) / (1.0f - brightnessThreshold);
                            float maxGlare = cQ * (bQ = Math.max(bQ, 0.0f));
                            if (maxGlare > 0.0f && lookingAtSky && !world.isRaining() && biomeHasSun) {
                                if (this.sunGlare < maxGlare) {
                                    this.sunGlare += 0.1f * maxGlare;
                                    this.sunGlare = Math.min(this.sunGlare, maxGlare);
                                } else if (this.sunGlare > maxGlare) {
                                    this.sunGlare -= 0.02f;
                                    this.sunGlare = Math.max(this.sunGlare, maxGlare);
                                }
                            } else {
                                if (this.sunGlare > 0.0f) {
                                    this.sunGlare -= 0.02f;
                                }
                                this.sunGlare = Math.max(this.sunGlare, 0.0f);
                            }
                        } else {
                            this.sunGlare = 0.0f;
                            this.prevSunGlare = 0.0f;
                        }
                        if (LOTRConfig.newWeather) {
                            this.prevRainFactor = this.rainFactor;
                            if (world.isRaining()) {
                                if (this.rainFactor < 1.0f) {
                                    this.rainFactor += 0.008333334f;
                                    this.rainFactor = Math.min(this.rainFactor, 1.0f);
                                }
                            } else if (this.rainFactor > 0.0f) {
                                this.rainFactor -= 0.0016666667f;
                                this.rainFactor = Math.max(this.rainFactor, 0.0f);
                            }
                        } else {
                            this.rainFactor = 0.0f;
                            this.prevRainFactor = 0.0f;
                        }
                        if (minecraft.gameSettings.particleSetting < 2) {
                            this.spawnEnvironmentFX((EntityPlayer)entityplayer, (World)world);
                        }
                        LOTRClientProxy.customEffectRenderer.updateEffects();
                        if (minecraft.renderViewEntity.isPotionActive(Potion.confusion.id)) {
                            float f;
                            float drunkenness = minecraft.renderViewEntity.getActivePotionEffect(Potion.confusion).getDuration();
                            drunkenness /= 20.0f;
                            if (f > 100.0f) {
                                drunkenness = 100.0f;
                            }
                            minecraft.renderViewEntity.rotationYaw += (float)this.drunkennessDirection * drunkenness / 20.0f;
                            minecraft.renderViewEntity.rotationPitch += MathHelper.cos((float)((float)minecraft.renderViewEntity.ticksExisted / 10.0f)) * drunkenness / 20.0f;
                            if (world.rand.nextInt(100) == 0) {
                                this.drunkennessDirection *= -1;
                            }
                        }
                        if (LOTRDimension.getCurrentDimension((World)world) == LOTRDimension.UTUMNO) {
                            if (this.inUtumnoReturnPortal) {
                                if (this.utumnoCamRoll < 180.0f) {
                                    this.utumnoCamRoll += 5.0f;
                                    this.utumnoCamRoll = Math.min(this.utumnoCamRoll, 180.0f);
                                    LOTRReflectionClient.setCameraRoll(minecraft.entityRenderer, this.utumnoCamRoll);
                                }
                            } else if (this.utumnoCamRoll > 0.0f) {
                                this.utumnoCamRoll -= 5.0f;
                                this.utumnoCamRoll = Math.max(this.utumnoCamRoll, 0.0f);
                                LOTRReflectionClient.setCameraRoll(minecraft.entityRenderer, this.utumnoCamRoll);
                            }
                        } else if (this.utumnoCamRoll != 0.0f) {
                            this.utumnoCamRoll = 0.0f;
                            LOTRReflectionClient.setCameraRoll(minecraft.entityRenderer, this.utumnoCamRoll);
                        }
                        if (this.newDate > 0) {
                            --this.newDate;
                        }
                        this.ambienceTicker.updateAmbience((World)world, (EntityPlayer)entityplayer);
                        if (scrapTraderMisbehaveTick <= 0) break block81;
                        if (--scrapTraderMisbehaveTick > 0) break block80;
                        world.provider.lightBrightnessTable = Arrays.copyOf(this.storedLightTable, this.storedLightTable.length);
                        Entity scrap = world.getEntityByID(this.storedScrapID);
                        if (scrap == null) break block80;
                        scrap.ignoreFrustumCheck = false;
                        break block80;
                    }
                    MovingObjectPosition target = minecraft.objectMouseOver;
                    if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && target.entityHit instanceof LOTREntityScrapTrader) {
                        LOTREntityScrapTrader scrap = (LOTREntityScrapTrader)target.entityHit;
                        if (minecraft.currentScreen == null && world.rand.nextInt(50000) == 0) {
                            scrapTraderMisbehaveTick = 400;
                            scrap.ignoreFrustumCheck = true;
                            this.storedScrapID = scrap.getEntityId();
                            float[] lightTable = world.provider.lightBrightnessTable;
                            this.storedLightTable = Arrays.copyOf(lightTable, lightTable.length);
                            for (int l = 0; l < lightTable.length; ++l) {
                                lightTable[l] = 1.0E-7f;
                            }
                        }
                    }
                }
                if ((entityplayer.dimension == 0 || entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) && playersInPortals.containsKey((Object)entityplayer)) {
                    List portals = world.getEntitiesWithinAABB(LOTREntityPortal.class, entityplayer.boundingBox.expand(8.0, 8.0, 8.0));
                    boolean inPortal = false;
                    for (i = 0; i < portals.size(); ++i) {
                        LOTREntityPortal portal = (LOTREntityPortal)((Object)portals.get(i));
                        if (!portal.boundingBox.intersectsWith(entityplayer.boundingBox)) continue;
                        inPortal = true;
                        break;
                    }
                    if (inPortal) {
                        i = (Integer)playersInPortals.get((Object)entityplayer);
                        playersInPortals.put(entityplayer, ++i);
                        if (i >= 100) {
                            minecraft.getSoundHandler().playSound((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("portal.trigger"), (float)(world.rand.nextFloat() * 0.4f + 0.8f)));
                            playersInPortals.remove((Object)entityplayer);
                        }
                    } else {
                        playersInPortals.remove((Object)entityplayer);
                    }
                }
                this.updatePlayerInPortal((EntityPlayer)entityplayer, playersInElvenPortals, LOTRMod.elvenPortal);
                this.updatePlayerInPortal((EntityPlayer)entityplayer, playersInMorgulPortals, LOTRMod.morgulPortal);
                if (this.inUtumnoReturnPortal) {
                    entityplayer.setPosition((double)this.utumnoReturnX + 0.5, entityplayer.posY, (double)this.utumnoReturnZ + 0.5);
                    if (this.lastUtumnoReturnY >= 0.0 && entityplayer.posY < this.lastUtumnoReturnY) {
                        entityplayer.setPosition(entityplayer.posX, this.lastUtumnoReturnY, entityplayer.posZ);
                    }
                    this.lastUtumnoReturnY = entityplayer.posY;
                } else {
                    this.lastUtumnoReturnY = -1.0;
                }
                this.inUtumnoReturnPortal = false;
            }
            LOTRClientProxy.musicHandler.update();
            if (LOTRConfig.displayMusicTrack) {
                LOTRMusicTrack nowPlaying = LOTRMusicTicker.currentTrack;
                if (nowPlaying != this.lastTrack) {
                    this.lastTrack = nowPlaying;
                    this.musicTrackTick = 200;
                }
                if (this.lastTrack != null && this.musicTrackTick > 0) {
                    --this.musicTrackTick;
                }
            }
            if ((guiscreen = minecraft.currentScreen) != null) {
                if (guiscreen instanceof GuiMainMenu && !(this.lastGuiOpen instanceof GuiMainMenu)) {
                    LOTRLevelData.needsLoad = true;
                    LOTRTime.needsLoad = true;
                    LOTRFellowshipData.needsLoad = true;
                    LOTRFactionBounties.needsLoad = true;
                    LOTRFactionRelations.needsLoad = true;
                    LOTRDate.resetWorldTimeInMenu();
                    LOTRConquestGrid.needsLoad = true;
                    LOTRSpeechClient.clearAll();
                    LOTRAttackTiming.reset();
                    LOTRGuiMenu.resetLastMenuScreen();
                    LOTRGuiMap.clearPlayerLocations();
                    LOTRCloudRenderer.resetClouds();
                    this.firstAlignmentRender = true;
                    watchedInvasion.clear();
                }
                this.lastGuiOpen = guiscreen;
            }
            anyWightsViewed = false;
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityClientPlayerMP clientPlayer;
        EntityPlayer player = event.player;
        if (event.phase == TickEvent.Phase.END && player instanceof EntityClientPlayerMP && (clientPlayer = (EntityClientPlayerMP)player).isRiding()) {
            LOTRMountFunctions.sendControlToServer((EntityPlayer)clientPlayer);
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityClientPlayerMP entityplayer = minecraft.thePlayer;
        WorldClient world = minecraft.theWorld;
        if (event.phase == TickEvent.Phase.START) {
            GuiIngame guiIngame;
            renderTick = event.renderTickTime;
            if (this.cancelItemHighlight && LOTRReflectionClient.getHighlightedItemTicks(guiIngame = minecraft.ingameGUI) > 0) {
                LOTRReflectionClient.setHighlightedItemTicks(guiIngame, 0);
                this.cancelItemHighlight = false;
            }
        }
        if (event.phase == TickEvent.Phase.END) {
            if (entityplayer != null && world != null) {
                ScaledResolution resolution;
                if ((world.provider instanceof LOTRWorldProvider || LOTRConfig.alwaysShowAlignment) && Minecraft.isGuiEnabled()) {
                    this.alignmentXPrev = this.alignmentXCurrent;
                    this.alignmentYPrev = this.alignmentYCurrent;
                    this.alignmentXCurrent = this.alignmentXBase;
                    int yMove = (int)((float)(this.alignmentYBase - -20) / 10.0f);
                    boolean alignmentOnscreen = (minecraft.currentScreen == null || minecraft.currentScreen instanceof LOTRGuiMessage) && !minecraft.gameSettings.keyBindPlayerList.getIsKeyPressed() && !minecraft.gameSettings.showDebugInfo;
                    this.alignmentYCurrent = alignmentOnscreen ? Math.min(this.alignmentYCurrent + yMove, this.alignmentYBase) : Math.max(this.alignmentYCurrent - yMove, -20);
                    this.renderAlignment(minecraft, renderTick);
                    if (LOTRConfig.enableOnscreenCompass && minecraft.currentScreen == null && !minecraft.gameSettings.showDebugInfo) {
                        GL11.glPushMatrix();
                        resolution = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
                        int i = resolution.getScaledWidth();
                        resolution.getScaledHeight();
                        int compassX = i - 60;
                        int compassY = 40;
                        GL11.glTranslatef((float)compassX, (float)compassY, (float)0.0f);
                        float rotation = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * event.renderTickTime;
                        rotation = 180.0f - rotation;
                        LOTRModelCompass.compassModel.render(1.0f, rotation);
                        GL11.glPopMatrix();
                        if (LOTRConfig.compassExtraInfo) {
                            BiomeGenBase biome;
                            GL11.glPushMatrix();
                            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                            float scale = 0.5f;
                            float invScale = 1.0f / scale;
                            compassX = (int)((float)compassX * invScale);
                            compassY = (int)((float)compassY * invScale);
                            GL11.glScalef((float)scale, (float)scale, (float)scale);
                            String coords = MathHelper.floor_double((double)entityplayer.posX) + ", " + MathHelper.floor_double((double)entityplayer.boundingBox.minY) + ", " + MathHelper.floor_double((double)entityplayer.posZ);
                            FontRenderer fontRenderer = minecraft.fontRenderer;
                            fontRenderer.drawString(coords, compassX - fontRenderer.getStringWidth(coords) / 2, compassY + 70, 16777215);
                            int playerX = MathHelper.floor_double((double)entityplayer.posX);
                            int playerZ = MathHelper.floor_double((double)entityplayer.posZ);
                            if (LOTRClientProxy.doesClientChunkExist((World)world, playerX, playerZ) && (biome = world.getBiomeGenForCoords(playerX, playerZ)) instanceof LOTRBiome) {
                                String biomeName = ((LOTRBiome)biome).getBiomeDisplayName();
                                fontRenderer.drawString(biomeName, compassX - fontRenderer.getStringWidth(biomeName) / 2, compassY - 70, 16777215);
                            }
                            GL11.glPopMatrix();
                        }
                    }
                }
                if (entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID && minecraft.currentScreen == null && this.newDate > 0) {
                    int halfMaxDate = 100;
                    float alpha = 0.0f;
                    alpha = this.newDate > halfMaxDate ? (float)(200 - this.newDate) / (float)halfMaxDate : (float)this.newDate / (float)halfMaxDate;
                    String date = LOTRDate.ShireReckoning.getShireDate().getDateName(true);
                    ScaledResolution resolution2 = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
                    int i = resolution2.getScaledWidth();
                    int j = resolution2.getScaledHeight();
                    float scale = 1.5f;
                    float invScale = 1.0f / scale;
                    i = (int)((float)i * invScale);
                    j = (int)((float)j * invScale);
                    int x = (i - minecraft.fontRenderer.getStringWidth(date)) / 2;
                    int y = (j - minecraft.fontRenderer.FONT_HEIGHT) * 2 / 5;
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    GL11.glEnable((int)3042);
                    OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
                    minecraft.fontRenderer.drawString(date, x, y, 16777215 + (LOTRClientProxy.getAlphaInt(alpha) << 24));
                    GL11.glDisable((int)3042);
                    GL11.glScalef((float)invScale, (float)invScale, (float)invScale);
                }
                if (LOTRConfig.displayMusicTrack && minecraft.currentScreen == null && this.lastTrack != null && this.musicTrackTick > 0) {
                    ArrayList<String> lines = new ArrayList<String>();
                    lines.add(StatCollector.translateToLocal((String)"lotr.music.nowPlaying"));
                    String title = this.lastTrack.getTitle();
                    lines.add(title);
                    if (!this.lastTrack.getAuthors().isEmpty()) {
                        String authors = "(";
                        int a = 0;
                        for (String auth : this.lastTrack.getAuthors()) {
                            authors = authors + auth;
                            if (a < this.lastTrack.getAuthors().size() - 1) {
                                authors = authors + ", ";
                            }
                            ++a;
                        }
                        authors = authors + ")";
                        lines.add(authors);
                    }
                    resolution = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
                    int w = resolution.getScaledWidth();
                    int h = resolution.getScaledHeight();
                    int border = 20;
                    int x = w - border;
                    int y = h - border - lines.size() * minecraft.fontRenderer.FONT_HEIGHT;
                    float alpha = 1.0f;
                    if (this.musicTrackTick >= 140) {
                        alpha = (float)(200 - this.musicTrackTick) / 60.0f;
                    } else if (this.musicTrackTick <= 60) {
                        alpha = (float)this.musicTrackTick / 60.0f;
                    }
                    for (String line : lines) {
                        x = w - border - minecraft.fontRenderer.getStringWidth(line);
                        minecraft.fontRenderer.drawString(line, x, y, 16777215 + (LOTRClientProxy.getAlphaInt(alpha) << 24));
                        y += minecraft.fontRenderer.FONT_HEIGHT;
                    }
                }
            }
            notificationDisplay.updateWindow();
            if (LOTRConfig.enableQuestTracker && minecraft.currentScreen == null && !minecraft.gameSettings.showDebugInfo) {
                miniquestTracker.drawTracker(minecraft, (EntityPlayer)entityplayer);
            }
        }
    }

    private void updatePlayerInPortal(EntityPlayer entityplayer, HashMap players, Block portalBlock) {
        if ((entityplayer.dimension == 0 || entityplayer.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) && players.containsKey((Object)entityplayer)) {
            boolean inPortal;
            boolean bl = inPortal = entityplayer.worldObj.getBlock(MathHelper.floor_double((double)entityplayer.posX), MathHelper.floor_double((double)entityplayer.boundingBox.minY), MathHelper.floor_double((double)entityplayer.posZ)) == portalBlock;
            if (inPortal) {
                int i = (Integer)players.get((Object)entityplayer);
                players.put(entityplayer, ++i);
                if (i >= entityplayer.getMaxInPortalTime()) {
                    Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("portal.trigger"), (float)(entityplayer.worldObj.rand.nextFloat() * 0.4f + 0.8f)));
                    players.remove((Object)entityplayer);
                }
            } else {
                players.remove((Object)entityplayer);
            }
        }
    }

    private void spawnEnvironmentFX(EntityPlayer entityplayer, World world) {
        world.theProfiler.startSection("lotrEnvironmentFX");
        int i = MathHelper.floor_double((double)entityplayer.posX);
        int j = MathHelper.floor_double((double)entityplayer.boundingBox.minY);
        int k = MathHelper.floor_double((double)entityplayer.posZ);
        int range = 16;
        for (int l = 0; l < 1000; ++l) {
            int i1 = i + world.rand.nextInt(range) - world.rand.nextInt(range);
            int j1 = j + world.rand.nextInt(range) - world.rand.nextInt(range);
            int k1 = k + world.rand.nextInt(range) - world.rand.nextInt(range);
            Block block = world.getBlock(i1, j1, k1);
            int meta = world.getBlockMetadata(i1, j1, k1);
            if (block.getMaterial() == Material.water) {
                BiomeGenBase biome = world.getBiomeGenForCoords(i1, k1);
                if (biome instanceof LOTRBiomeGenMirkwoodCorrupted && world.rand.nextInt(20) == 0) {
                    LOTRMod.proxy.spawnParticle("mirkwoodWater", (float)i1 + world.rand.nextFloat(), (double)j1 + 0.75, (float)k1 + world.rand.nextFloat(), 0.0, 0.05, 0.0);
                }
                if (biome instanceof LOTRBiomeGenMorgulVale && world.rand.nextInt(40) == 0) {
                    LOTRMod.proxy.spawnParticle("morgulWater", (float)i1 + world.rand.nextFloat(), (double)j1 + 0.75, (float)k1 + world.rand.nextFloat(), 0.0, 0.05, 0.0);
                }
                if (biome instanceof LOTRBiomeGenDeadMarshes && world.rand.nextInt(800) == 0) {
                    world.spawnEntityInWorld((Entity)new LOTREntityDeadMarshFace(world, (float)i1 + world.rand.nextFloat(), (double)j1 + 0.25 - (double)world.rand.nextFloat(), (float)k1 + world.rand.nextFloat()));
                }
            }
            if (block.getMaterial() != Material.water || meta == 0 || world.getBlock(i1, j1 - 1, k1).getMaterial() != Material.water) continue;
            for (int i2 = i1 - 1; i2 <= i1 + 1; ++i2) {
                for (int k2 = k1 - 1; k2 <= k1 + 1; ++k2) {
                    Block adjBlock = world.getBlock(i2, j1 - 1, k2);
                    int adjMeta = world.getBlockMetadata(i2, j1 - 1, k2);
                    if (adjBlock.getMaterial() != Material.water || adjMeta != 0 || !world.isAirBlock(i2, j1, k2)) continue;
                    for (int l1 = 0; l1 < 2; ++l1) {
                        double d = (double)i1 + 0.5 + (double)((float)(i2 - i1) * world.rand.nextFloat());
                        double d1 = (float)j1 + world.rand.nextFloat() * 0.2f;
                        double d2 = (double)k1 + 0.5 + (double)((float)(k2 - k1) * world.rand.nextFloat());
                        world.spawnParticle("explode", d, d1, d2, 0.0, 0.0, 0.0);
                    }
                }
            }
        }
        world.theProfiler.endSection();
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (event.world instanceof WorldClient) {
            LOTRClientProxy.customEffectRenderer.clearEffectsAndSetWorld(event.world);
        }
    }

    @SubscribeEvent
    public void onPreRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.theWorld;
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        float partialTicks = event.partialTicks;
        GuiIngame guiIngame = mc.ingameGUI;
        if (world != null && entityplayer != null) {
            int width;
            int height;
            ScaledResolution resolution;
            boolean enchantingDisabled;
            if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
                mc.theWorld.theProfiler.startSection("lotr_fixHighlightedItemName");
                ItemStack itemstack = LOTRReflectionClient.getHighlightedItemStack(guiIngame);
                if (itemstack != null && !itemstack.hasDisplayName() && !LOTREnchantmentHelper.getEnchantList(itemstack).isEmpty()) {
                    this.lastHighlightedItemstack = itemstack;
                    this.highlightedItemstackName = itemstack.hasDisplayName() ? itemstack.getDisplayName() : null;
                    itemstack.setStackDisplayName(LOTREnchantmentHelper.getFullEnchantedName(itemstack, itemstack.getDisplayName()));
                }
                mc.theWorld.theProfiler.endSection();
            }
            if (event.type == RenderGameOverlayEvent.ElementType.HELMET) {
                int i;
                if (this.sunGlare > 0.0f && mc.gameSettings.thirdPersonView == 0) {
                    float brightness = this.prevSunGlare + (this.sunGlare - this.prevSunGlare) * partialTicks;
                    this.renderOverlay(null, brightness *= 1.0f, mc, null);
                }
                if (playersInPortals.containsKey((Object)entityplayer) && (i = ((Integer)playersInPortals.get((Object)entityplayer)).intValue()) > 0) {
                    this.renderOverlay(null, 0.1f + (float)i / 100.0f * 0.6f, mc, portalOverlay);
                }
                if (playersInElvenPortals.containsKey((Object)entityplayer) && (i = ((Integer)playersInElvenPortals.get((Object)entityplayer)).intValue()) > 0) {
                    this.renderOverlay(null, 0.1f + (float)i / (float)entityplayer.getMaxInPortalTime() * 0.6f, mc, elvenPortalOverlay);
                }
                if (playersInMorgulPortals.containsKey((Object)entityplayer) && (i = ((Integer)playersInMorgulPortals.get((Object)entityplayer)).intValue()) > 0) {
                    this.renderOverlay(null, 0.1f + (float)i / (float)entityplayer.getMaxInPortalTime() * 0.6f, mc, morgulPortalOverlay);
                }
                if (LOTRConfig.enableMistyMountainsMist) {
                    float mistTickF = (float)this.prevMistTick + (float)(this.mistTick - this.prevMistTick) * partialTicks;
                    float mistFactorY = (float)entityplayer.posY / 256.0f;
                    this.mistFactor = (mistTickF /= 80.0f) * mistFactorY;
                    if (this.mistFactor > 0.0f) {
                        this.renderOverlay(null, this.mistFactor * 0.75f, mc, mistOverlay);
                    }
                } else {
                    this.mistFactor = 0.0f;
                }
                if (this.frostTick > 0) {
                    this.renderOverlay(null, (float)this.frostTick / 80.0f * 0.9f, mc, frostOverlay);
                }
                if (this.burnTick > 0) {
                    this.renderOverlay(null, (float)this.burnTick / 40.0f * 0.6f, mc, burnOverlay);
                }
                if (this.wightLookTick > 0) {
                    this.renderOverlay(null, (float)this.wightLookTick / 100.0f * 0.95f, mc, wightOverlay);
                }
            }
            if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
                LOTREntitySpiderBase spider;
                if (LOTRConfig.meleeAttackMeter) {
                    LOTRAttackTiming.renderAttackMeter(event.resolution, partialTicks);
                }
                if (entityplayer.ridingEntity instanceof LOTREntitySpiderBase && (spider = (LOTREntitySpiderBase)entityplayer.ridingEntity).shouldRenderClimbingMeter()) {
                    mc.getTextureManager().bindTexture(Gui.icons);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glDisable((int)3042);
                    mc.mcProfiler.startSection("spiderClimb");
                    resolution = event.resolution;
                    width = resolution.getScaledWidth();
                    height = resolution.getScaledHeight();
                    float charge = spider.getClimbFractionRemaining();
                    int x = width / 2 - 91;
                    int filled = (int)(charge * 183.0f);
                    int top = height - 32 + 3;
                    guiIngame.drawTexturedModalRect(x, top, 0, 84, 182, 5);
                    if (filled > 0) {
                        guiIngame.drawTexturedModalRect(x, top, 0, 89, filled, 5);
                    }
                    GL11.glEnable((int)3042);
                    mc.mcProfiler.endSection();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                }
            }
            if (event.type == RenderGameOverlayEvent.ElementType.HEALTH && entityplayer.isPotionActive(LOTRPoisonedDrinks.killingPoison) && !entityplayer.isPotionActive(Potion.poison)) {
                entityplayer.addPotionEffect(new PotionEffect(Potion.poison.id, 20));
                this.addedClientPoisonEffect = true;
            }
            boolean bl = enchantingDisabled = !LOTRLevelData.clientside_thisServer_enchanting && world.provider instanceof LOTRWorldProvider;
            if (event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE && enchantingDisabled) {
                event.setCanceled(true);
                return;
            }
            if (event.type == RenderGameOverlayEvent.ElementType.ALL && enchantingDisabled && entityplayer.ridingEntity == null) {
                GuiIngameForge.left_height -= 6;
                GuiIngameForge.right_height -= 6;
            }
            if (event.type == RenderGameOverlayEvent.ElementType.ARMOR) {
                event.setCanceled(true);
                resolution = event.resolution;
                width = resolution.getScaledWidth();
                height = resolution.getScaledHeight();
                mc.mcProfiler.startSection("armor");
                GL11.glEnable((int)3042);
                int left = width / 2 - 91;
                int top = height - GuiIngameForge.left_height;
                int level = LOTRWeaponStats.getTotalArmorValue((EntityPlayer)mc.thePlayer);
                if (level > 0) {
                    for (int i = 1; i < 20; i += 2) {
                        if (i < level) {
                            guiIngame.drawTexturedModalRect(left, top, 34, 9, 9, 9);
                        } else if (i == level) {
                            guiIngame.drawTexturedModalRect(left, top, 25, 9, 9, 9);
                        } else if (i > level) {
                            guiIngame.drawTexturedModalRect(left, top, 16, 9, 9, 9);
                        }
                        left += 8;
                    }
                }
                GuiIngameForge.left_height += 10;
                GL11.glDisable((int)3042);
                mc.mcProfiler.endSection();
                return;
            }
        }
    }

    @SubscribeEvent
    public void onPostRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.theWorld;
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        GuiIngame guiIngame = mc.ingameGUI;
        if (world != null && entityplayer != null) {
            if (event.type == RenderGameOverlayEvent.ElementType.ALL && this.lastHighlightedItemstack != null) {
                if (this.highlightedItemstackName != null) {
                    this.lastHighlightedItemstack.setStackDisplayName(this.highlightedItemstackName);
                } else {
                    this.lastHighlightedItemstack.func_135074_t();
                }
                this.lastHighlightedItemstack = null;
                this.highlightedItemstackName = null;
            }
            if (event.type == RenderGameOverlayEvent.ElementType.BOSSHEALTH && watchedInvasion.isActive()) {
                GL11.glEnable((int)3042);
                FontRenderer fr = mc.fontRenderer;
                ScaledResolution scaledresolution = event.resolution;
                int width = scaledresolution.getScaledWidth();
                int barWidth = 182;
                int remainingWidth = (int)(watchedInvasion.getHealth() * (float)(barWidth - 2));
                int barHeight = 5;
                int barX = width / 2 - barWidth / 2;
                int barY = 12;
                if (LOTRTickHandlerClient.isBossActive()) {
                    barY += 20;
                }
                mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
                guiIngame.drawTexturedModalRect(barX, barY, 64, 64, barWidth, barHeight);
                if (remainingWidth > 0) {
                    float[] rgb = watchedInvasion.getRGB();
                    GL11.glColor4f((float)rgb[0], (float)rgb[1], (float)rgb[2], (float)1.0f);
                    guiIngame.drawTexturedModalRect(barX + 1, barY + 1, 65, 70, remainingWidth, barHeight - 2);
                }
                String s = watchedInvasion.getTitle();
                fr.drawStringWithShadow(s, width / 2 - fr.getStringWidth(s) / 2, barY - 10, 16777215);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                mc.getTextureManager().bindTexture(Gui.icons);
                GL11.glDisable((int)3042);
            }
            if (event.type == RenderGameOverlayEvent.ElementType.HEALTH && this.addedClientPoisonEffect) {
                entityplayer.removePotionEffectClient(Potion.poison.id);
                this.addedClientPoisonEffect = false;
            }
            if (event.type == RenderGameOverlayEvent.ElementType.TEXT && this.bannerRepossessDisplayTick > 0) {
                String text = StatCollector.translateToLocalFormatted((String)"item.lotr.banner.toggleRepossess", (Object[])new Object[]{GameSettings.getKeyDisplayString((int)mc.gameSettings.keyBindSneak.getKeyCode())});
                int fadeAtTick = 10;
                int opacity = (int)((float)this.bannerRepossessDisplayTick * 255.0f / (float)fadeAtTick);
                if ((opacity = Math.min(opacity, 255)) > 0) {
                    ScaledResolution scaledresolution = event.resolution;
                    int width = scaledresolution.getScaledWidth();
                    int height = scaledresolution.getScaledHeight();
                    int y = height - 59;
                    y -= 12;
                    if (!mc.playerController.shouldDrawHUD()) {
                        y += 14;
                    }
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
                    FontRenderer fr = mc.fontRenderer;
                    int x = (width - fr.getStringWidth(text)) / 2;
                    fr.drawString(text, x, y, 0xFFFFFF | opacity << 24);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderDebugText(RenderGameOverlayEvent.Text event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings.showDebugInfo && mc.theWorld != null && mc.thePlayer != null && mc.theWorld.getWorldChunkManager() instanceof LOTRWorldChunkManager) {
            mc.theWorld.theProfiler.startSection("lotrBiomeDisplay");
            LOTRWorldChunkManager chunkManager = (LOTRWorldChunkManager)mc.theWorld.getWorldChunkManager();
            int i = MathHelper.floor_double((double)mc.thePlayer.posX);
            int j = MathHelper.floor_double((double)mc.thePlayer.boundingBox.minY);
            int k = MathHelper.floor_double((double)mc.thePlayer.posZ);
            LOTRBiome biome = (LOTRBiome)mc.theWorld.getBiomeGenForCoords(i, k);
            LOTRBiomeVariant variant = chunkManager.getBiomeVariantAt(i, k);
            event.left.add(null);
            biome.addBiomeF3Info(event.left, (World)mc.theWorld, variant, i, j, k);
            mc.theWorld.theProfiler.endSection();
        }
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        float f = event.partialTicks;
        if (LOTRConfig.aurora && LOTRDimension.getCurrentDimension((World)mc.theWorld) == LOTRDimension.MIDDLE_EARTH) {
            LOTRRenderNorthernLights.render(mc, mc.theWorld, f);
        }
        mc.entityRenderer.enableLightmap((double)f);
        RenderHelper.disableStandardItemLighting();
        LOTRClientProxy.customEffectRenderer.renderParticles((Entity)mc.renderViewEntity, f);
        mc.entityRenderer.disableLightmap((double)f);
        if (Minecraft.isGuiEnabled() && mc.entityRenderer.debugViewDirection == 0) {
            mc.mcProfiler.startSection("lotrSpeech");
            LOTRNPCRendering.renderAllNPCSpeeches(mc, (World)mc.theWorld, f);
            mc.mcProfiler.endSection();
        }
    }

    @SubscribeEvent
    public void getItemTooltip(ItemTooltipEvent event) {
        String name;
        List<String> previousOwners;
        String squadron;
        int armorProtect;
        int i;
        String currentOwner;
        ItemStack itemstack = event.itemStack;
        List tooltip = event.toolTip;
        EntityPlayer entityplayer = event.entityPlayer;
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        List<LOTREnchantment> enchantments = LOTREnchantmentHelper.getEnchantList(itemstack);
        if (!itemstack.hasDisplayName() && !enchantments.isEmpty()) {
            name = (String)tooltip.get(0);
            name = LOTREnchantmentHelper.getFullEnchantedName(itemstack, name);
            tooltip.set(0, name);
        }
        if (itemstack.getItem() instanceof LOTRSquadrons.SquadronItem && !StringUtils.isNullOrEmpty((String)(squadron = LOTRSquadrons.getSquadron(itemstack)))) {
            ArrayList newTooltip = new ArrayList();
            newTooltip.add(tooltip.get(0));
            newTooltip.add(StatCollector.translateToLocalFormatted((String)"item.lotr.generic.squadron", (Object[])new Object[]{squadron}));
            for (i = 1; i < tooltip.size(); ++i) {
                newTooltip.add(tooltip.get(i));
            }
            tooltip.clear();
            tooltip.addAll(newTooltip);
        }
        if (LOTRWeaponStats.isMeleeWeapon(itemstack)) {
            int dmgIndex = -1;
            for (int i2 = 0; i2 < tooltip.size(); ++i2) {
                String s = (String)tooltip.get(i2);
                if (!s.startsWith(EnumChatFormatting.BLUE.toString())) continue;
                dmgIndex = i2;
                break;
            }
            if (dmgIndex >= 0) {
                ArrayList newTooltip = new ArrayList();
                for (i = 0; i <= dmgIndex - 1; ++i) {
                    newTooltip.add(tooltip.get(i));
                }
                float meleeDamage = LOTRWeaponStats.getMeleeDamageBonus(itemstack);
                newTooltip.add((Object)EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.meleeDamage", (Object[])new Object[]{Float.valueOf(meleeDamage)}));
                float meleeSpeed = LOTRWeaponStats.getMeleeSpeed(itemstack);
                int pcSpeed = Math.round(meleeSpeed * 100.0f);
                newTooltip.add((Object)EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.meleeSpeed", (Object[])new Object[]{pcSpeed}));
                float reach = LOTRWeaponStats.getMeleeReachFactor(itemstack);
                int pcReach = Math.round(reach * 100.0f);
                newTooltip.add((Object)EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.reach", (Object[])new Object[]{pcReach}));
                int kb = LOTRWeaponStats.getTotalKnockback(itemstack);
                if (kb > 0) {
                    newTooltip.add((Object)EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.kb", (Object[])new Object[]{kb}));
                }
                for (int i3 = dmgIndex + 1; i3 < tooltip.size(); ++i3) {
                    newTooltip.add(tooltip.get(i3));
                }
                tooltip.clear();
                tooltip.addAll(newTooltip);
            }
        }
        if (LOTRWeaponStats.isRangedWeapon(itemstack)) {
            float f;
            int kb;
            tooltip.add("");
            float drawSpeed = LOTRWeaponStats.getRangedSpeed(itemstack);
            if (drawSpeed > 0.0f) {
                int pcSpeed = Math.round(drawSpeed * 100.0f);
                tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.rangedSpeed", (Object[])new Object[]{pcSpeed}));
            }
            float damage = LOTRWeaponStats.getRangedDamageFactor(itemstack, false);
            if (f > 0.0f) {
                int pcDamage = Math.round(damage * 100.0f);
                tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.rangedDamage", (Object[])new Object[]{pcDamage}));
                if (itemstack.getItem() instanceof ItemBow || itemstack.getItem() instanceof LOTRItemCrossbow) {
                    float range = LOTRWeaponStats.getRangedDamageFactor(itemstack, true);
                    int pcRange = Math.round(range * 100.0f);
                    tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.range", (Object[])new Object[]{pcRange}));
                }
            }
            if ((kb = LOTRWeaponStats.getRangedKnockback(itemstack)) > 0) {
                tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.kb", (Object[])new Object[]{kb}));
            }
        }
        if (LOTRWeaponStats.isPoisoned(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.poison", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isBlood(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.RED + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.blood", (Object[])new Object[0]));
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.poison", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isBlood1(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.RED + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.blood", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isPoisoned2(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.poison", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isPoisoned3(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.poison", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isPoisoned4(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.poison", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isPoisoned5(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.poison", (Object[])new Object[0]));
        }
        if (LOTRWeaponStats.isPoisoned1(itemstack)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.wither", (Object[])new Object[0]));
        }
        if ((armorProtect = LOTRWeaponStats.getArmorProtection(itemstack)) > 0) {
            tooltip.add("");
            int pcProtection = Math.round((float)armorProtect / 25.0f * 100.0f);
            tooltip.add((Object)EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted((String)"lotr.weaponstat.protection", (Object[])new Object[]{armorProtect, pcProtection}));
        }
        if (!enchantments.isEmpty()) {
            tooltip.add("");
            ArrayList<String> enchGood = new ArrayList<String>();
            ArrayList<String> enchBad = new ArrayList<String>();
            for (LOTREnchantment ench : enchantments) {
                String enchDesc = ench.getNamedFormattedDescription(itemstack);
                if (ench.isBeneficial()) {
                    enchGood.add(enchDesc);
                    continue;
                }
                enchBad.add(enchDesc);
            }
            tooltip.addAll(enchGood);
            tooltip.addAll(enchBad);
        }
        if (LOTRPoisonedDrinks.isDrinkPoisoned(itemstack) && LOTRPoisonedDrinks.canPlayerSeePoisoned(itemstack, entityplayer)) {
            tooltip.add((Object)EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal((String)"item.lotr.drink.poison"));
        }
        if ((currentOwner = LOTRItemOwnership.getCurrentOwner(itemstack)) != null) {
            tooltip.add("");
            String ownerFormatted = (Object)EnumChatFormatting.RED + StatCollector.translateToLocalFormatted((String)"item.lotr.generic.currentOwner", (Object[])new Object[]{currentOwner});
            List ownerLines = fontRenderer.listFormattedStringToWidth(ownerFormatted, 150);
            for (int i4 = 0; i4 < ownerLines.size(); ++i4) {
                String line = (String)ownerLines.get(i4);
                if (i4 > 0) {
                    line = "  " + line;
                }
                tooltip.add(line);
            }
        }
        if (!(previousOwners = LOTRItemOwnership.getPreviousOwners(itemstack)).isEmpty()) {
            tooltip.add("");
            ArrayList<String> ownerLines = new ArrayList<String>();
            if (previousOwners.size() == 1) {
                String ownerFormatted = (Object)EnumChatFormatting.ITALIC + StatCollector.translateToLocalFormatted((String)"item.lotr.generic.previousOwner", (Object[])new Object[]{previousOwners.get(0)});
                ownerLines.addAll(fontRenderer.listFormattedStringToWidth(ownerFormatted, 150));
            } else {
                String beginList = (Object)EnumChatFormatting.ITALIC + StatCollector.translateToLocal((String)"item.lotr.generic.previousOwnerList");
                ownerLines.add(beginList);
                for (String previousOwner : previousOwners) {
                    previousOwner = (Object)EnumChatFormatting.ITALIC + previousOwner;
                    ownerLines.addAll(fontRenderer.listFormattedStringToWidth(previousOwner, 150));
                }
            }
            for (int i5 = 0; i5 < ownerLines.size(); ++i5) {
                String line = (String)ownerLines.get(i5);
                if (i5 > 0) {
                    line = "  " + line;
                }
                tooltip.add(line);
            }
        }
        if (IPickpocketable.Helper.isPickpocketed(itemstack)) {
            tooltip.add("");
            String owner = IPickpocketable.Helper.getOwner(itemstack);
            owner = StatCollector.translateToLocalFormatted((String)"item.lotr.generic.stolen", (Object[])new Object[]{owner});
            String wanter = IPickpocketable.Helper.getWanter(itemstack);
            wanter = StatCollector.translateToLocalFormatted((String)"item.lotr.generic.stolenWanted", (Object[])new Object[]{wanter});
            ArrayList robbedLines = new ArrayList();
            robbedLines.addAll(fontRenderer.listFormattedStringToWidth(owner, 200));
            robbedLines.addAll(fontRenderer.listFormattedStringToWidth(wanter, 200));
            for (int i6 = 0; i6 < robbedLines.size(); ++i6) {
                String line = (String)robbedLines.get(i6);
                if (i6 > 0) {
                    line = "  " + line;
                }
                tooltip.add(line);
            }
        }
        if (itemstack.getItem() == Item.getItemFromBlock((Block)Blocks.monster_egg)) {
            tooltip.set(0, (Object)EnumChatFormatting.RED + (String)tooltip.get(0));
        }
        if (LOTRMod.isAprilFools()) {
            name = (String)tooltip.get(0);
            name = name.replace("kebab", "gyros");
            name = name.replace("Kebab", "Gyros");
            tooltip.set(0, name);
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlayText(RenderGameOverlayEvent.Text event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        if (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof LOTRItemMap && mc.thePlayer.getHeldItem().hasTagCompound()) {
            FontRenderer fr = mc.fontRenderer;
            int coordX = mc.thePlayer.getHeldItem().getTagCompound().getInteger("targetX");
            int coordZ = mc.thePlayer.getHeldItem().getTagCompound().getInteger("targetZ");
            String coordsText = (Object)EnumChatFormatting.GOLD + "X: " + coordX + ", Z: " + coordZ;
            int yOffset = entityplayer.capabilities.isCreativeMode ? 36 : 48;
            fr.drawStringWithShadow(coordsText, event.resolution.getScaledWidth() / 2 - fr.getStringWidth(coordsText) / 2, event.resolution.getScaledHeight() - yOffset, 16766720);
        }
    }

    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        EntityPlayerSP entityplayer = event.entity;
        float fov = event.newfov;
        ItemStack itemstack = entityplayer.getHeldItem();
        Item item = itemstack == null ? null : itemstack.getItem();
        float usage = -1.0f;
        if (entityplayer.isUsingItem()) {
            float maxDrawTime = 0.0f;
            if (item instanceof LOTRItemBow) {
                maxDrawTime = ((LOTRItemBow)item).getMaxDrawTime();
            } else if (item instanceof LOTRItemCrossbow) {
                maxDrawTime = ((LOTRItemCrossbow)item).getMaxDrawTime();
            } else if (item instanceof LOTRItemSpear) {
                maxDrawTime = ((LOTRItemSpear)item).getMaxDrawTime();
            } else if (item instanceof LOTRItemBlowgun) {
                maxDrawTime = ((LOTRItemBlowgun)item).getMaxDrawTime();
            }
            if (maxDrawTime > 0.0f) {
                int i = entityplayer.getItemInUseDuration();
                usage = (float)i / maxDrawTime;
                float f = usage = usage > 1.0f ? 1.0f : (usage = usage * usage);
            }
        }
        if (LOTRItemCrossbow.isLoaded(itemstack)) {
            usage = 1.0f;
        }
        if (usage >= 0.0f) {
            fov *= 1.0f - usage * 0.15f;
        }
        event.newfov = fov;
    }

    @SubscribeEvent
    public void onRenderFog(EntityViewRenderEvent.RenderFogEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityLivingBase viewer = event.entity;
        WorldClient world = mc.theWorld;
        WorldProvider provider = world.provider;
        int i = MathHelper.floor_double((double)viewer.posX);
        int j = MathHelper.floor_double((double)viewer.boundingBox.minY);
        int k = MathHelper.floor_double((double)viewer.posZ);
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
        float farPlane = event.farPlaneDistance;
        int fogMode = event.fogMode;
        if (provider instanceof LOTRWorldProvider) {
            float f;
            LOTRBiome lotrbiome = (LOTRBiome)biome;
            float[] fogStartEnd = ((LOTRWorldProvider)provider).modifyFogIntensity(farPlane, fogMode);
            float fogStart = fogStartEnd[0];
            float fogEnd = fogStartEnd[1];
            if (LOTRConfig.newWeather && (lotrbiome.getEnableRain() || lotrbiome.getEnableSnow())) {
                float f2;
                float rain = this.prevRainFactor + (this.rainFactor - this.prevRainFactor) * renderTick;
                if (f2 > 0.0f) {
                    float rainOpacityStart = 0.95f;
                    float rainOpacityEnd = 0.2f;
                    fogStart -= fogStart * (rain * rainOpacityStart);
                    fogEnd -= fogEnd * (rain * rainOpacityEnd);
                }
            }
            if (this.mistFactor > 0.0f) {
                float mistOpacityStart = 0.95f;
                float mistOpacityEnd = 0.7f;
                fogStart -= fogStart * (this.mistFactor * mistOpacityStart);
                fogEnd -= fogEnd * (this.mistFactor * mistOpacityEnd);
            }
            float wightFactor = (float)this.prevWightNearTick + (float)(this.wightNearTick - this.prevWightNearTick) * renderTick;
            wightFactor /= 100.0f;
            if (f > 0.0f) {
                float wightOpacityStart = 0.97f;
                float wightOpacityEnd = 0.75f;
                fogStart -= fogStart * (wightFactor * wightOpacityStart);
                fogEnd -= fogEnd * (wightFactor * wightOpacityEnd);
            }
            if (lotrbiome instanceof LOTRBiomeGenBarrowDowns) {
                if (wightFactor > 0.0f) {
                    int sky0 = lotrbiome.getBaseSkyColorByTemp(i, j, k);
                    int sky1 = 9674385;
                    int clouds0 = 16777215;
                    int clouds1 = 11842740;
                    int fog0 = 16777215;
                    int fog1 = 10197915;
                    lotrbiome.biomeColors.setSky(LOTRColorUtil.lerpColors_I(sky0, sky1, wightFactor));
                    lotrbiome.biomeColors.setClouds(LOTRColorUtil.lerpColors_I(clouds0, clouds1, wightFactor));
                    lotrbiome.biomeColors.setFog(LOTRColorUtil.lerpColors_I(fog0, fog1, wightFactor));
                } else {
                    lotrbiome.biomeColors.resetSky();
                    lotrbiome.biomeColors.resetClouds();
                    lotrbiome.biomeColors.resetFog();
                }
            }
            this.balrogFactor = (float)this.prevBalrogNearTick + (float)(this.balrogNearTick - this.prevBalrogNearTick) * renderTick;
            this.balrogFactor /= 100.0f;
            if (this.balrogFactor > 0.0f) {
                float balrogOpacityStart = 0.98f;
                float balrogOpacityEnd = 0.75f;
                fogStart -= fogStart * (this.balrogFactor * balrogOpacityStart);
                fogEnd -= fogEnd * (this.balrogFactor * balrogOpacityEnd);
            }
            GL11.glFogf((int)2915, (float)fogStart);
            GL11.glFogf((int)2916, (float)fogEnd);
        }
    }

    @SubscribeEvent
    public void onFogColors(EntityViewRenderEvent.FogColors event) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.theWorld;
        WorldProvider provider = world.provider;
        if (provider instanceof LOTRWorldProvider) {
            float[] rgb = new float[]{event.red, event.green, event.blue};
            rgb = ((LOTRWorldProvider)provider).handleFinalFogColors(event.entity, event.renderPartialTicks, rgb);
            event.red = rgb[0];
            event.green = rgb[1];
            event.blue = rgb[2];
        }
        if (this.balrogFactor > 0.0f) {
            int shadowColor = 1114112;
            float[] rgb = new float[]{event.red, event.green, event.blue};
            rgb = LOTRColorUtil.lerpColors(rgb, shadowColor, this.balrogFactor);
            event.red = rgb[0];
            event.green = rgb[1];
            event.blue = rgb[2];
        }
    }

    private boolean isGamePaused(Minecraft mc) {
        return mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic();
    }

    private void renderOverlay(float[] rgb, float alpha, Minecraft mc, ResourceLocation texture) {
        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glBlendFunc((int)770, (int)771);
        if (rgb != null) {
            GL11.glColor4f((float)rgb[0], (float)rgb[1], (float)rgb[2], (float)alpha);
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        }
        GL11.glDisable((int)3008);
        if (texture != null) {
            mc.getTextureManager().bindTexture(texture);
        } else {
            GL11.glDisable((int)3553);
        }
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0, (double)height, -90.0, 0.0, 1.0);
        tessellator.addVertexWithUV((double)width, (double)height, -90.0, 1.0, 1.0);
        tessellator.addVertexWithUV((double)width, 0.0, -90.0, 1.0, 0.0);
        tessellator.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
        tessellator.draw();
        if (texture == null) {
            GL11.glEnable((int)3553);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderAlignment(Minecraft mc, float f) {
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)entityplayer);
        LOTRFaction viewingFac = pd.getViewingFaction();
        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = resolution.getScaledWidth();
        resolution.getScaledHeight();
        this.alignmentXBase = width / 2 + LOTRConfig.alignmentXOffset;
        this.alignmentYBase = 4 + LOTRConfig.alignmentYOffset;
        if (LOTRTickHandlerClient.isBossActive()) {
            this.alignmentYBase += 20;
        }
        if (watchedInvasion.isActive()) {
            this.alignmentYBase += 20;
        }
        if (this.firstAlignmentRender) {
            LOTRAlignmentTicker.updateAll((EntityPlayer)entityplayer, true);
            this.alignmentXPrev = this.alignmentXCurrent = this.alignmentXBase;
            this.alignmentYCurrent = -20;
            this.alignmentYPrev = -20;
            this.firstAlignmentRender = false;
        }
        float alignmentXF = (float)this.alignmentXPrev + (float)(this.alignmentXCurrent - this.alignmentXPrev) * f;
        float alignmentYF = (float)this.alignmentYPrev + (float)(this.alignmentYCurrent - this.alignmentYPrev) * f;
        boolean text = this.alignmentYCurrent == this.alignmentYBase;
        float alignment = LOTRAlignmentTicker.forFaction(viewingFac).getInterpolatedAlignment(f);
        LOTRTickHandlerClient.renderAlignmentBar(alignment, false, viewingFac, alignmentXF, alignmentYF, text, text, text, false);
        if (alignDrainTick > 0 && text) {
            float alpha = 1.0f;
            int fadeTick = 20;
            if (alignDrainTick < fadeTick) {
                alpha = (float)alignDrainTick / (float)fadeTick;
            }
            LOTRTickHandlerClient.renderAlignmentDrain(mc, (int)alignmentXF - 155, (int)alignmentYF + 2, alignDrainNum, alpha);
        }
    }

    public static void renderAlignmentBar(float alignment, boolean isOtherPlayer, LOTRFaction faction, float x, float y, boolean renderFacName, boolean renderValue, boolean renderLimits, boolean renderLimitValues) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        LOTRPlayerData clientPD = LOTRLevelData.getData((EntityPlayer)entityplayer);
        LOTRFactionRank rank = faction.getRank(alignment);
        boolean pledged = clientPD.isPledgedTo(faction);
        LOTRAlignmentTicker ticker = LOTRAlignmentTicker.forFaction(faction);
        float alignMin = 0.0f;
        float alignMax = 0.0f;
        LOTRFactionRank rankMin = null;
        LOTRFactionRank rankMax = null;
        if (!rank.isDummyRank()) {
            alignMin = rank.alignment;
            rankMin = rank;
            LOTRFactionRank nextRank = faction.getRankAbove(rank);
            if (nextRank != null && !nextRank.isDummyRank() && nextRank != rank) {
                alignMax = nextRank.alignment;
                rankMax = nextRank;
            } else {
                alignMax = rank.alignment * 10.0f;
                rankMax = rank;
                while (alignment >= alignMax) {
                    alignMin = alignMax;
                    alignMax = alignMin * 10.0f;
                }
            }
        } else {
            float firstRankAlign;
            LOTRFactionRank firstRank = faction.getFirstRank();
            float f = firstRankAlign = firstRank != null && !firstRank.isDummyRank() ? firstRank.alignment : 10.0f;
            if (Math.abs(alignment) < firstRankAlign) {
                alignMin = -firstRankAlign;
                alignMax = firstRankAlign;
                rankMin = LOTRFactionRank.RANK_ENEMY;
                rankMax = firstRank != null && !firstRank.isDummyRank() ? firstRank : LOTRFactionRank.RANK_NEUTRAL;
            } else if (alignment < 0.0f) {
                alignMax = -firstRankAlign;
                alignMin = alignMax * 10.0f;
                rankMin = rankMax = LOTRFactionRank.RANK_ENEMY;
                while (alignment <= alignMin) {
                    alignMin = (alignMax *= 10.0f) * 10.0f;
                }
            } else {
                alignMin = firstRankAlign;
                alignMax = alignMin * 10.0f;
                rankMin = rankMax = LOTRFactionRank.RANK_NEUTRAL;
                while (alignment >= alignMax) {
                    alignMin = alignMax;
                    alignMax = alignMin * 10.0f;
                }
            }
        }
        float ringProgress = (alignment - alignMin) / (alignMax - alignMin);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
        int barWidth = 232;
        int barHeight = 14;
        int activeBarWidth = 220;
        float[] factionColors = faction.getFactionRGB();
        GL11.glColor4f((float)factionColors[0], (float)factionColors[1], (float)factionColors[2], (float)1.0f);
        LOTRTickHandlerClient.drawTexturedModalRect(x - (float)(barWidth / 2), y, 0, 14, barWidth, barHeight);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        LOTRTickHandlerClient.drawTexturedModalRect(x - (float)(barWidth / 2), y, 0, 0, barWidth, barHeight);
        float ringProgressAdj = (ringProgress - 0.5f) * 2.0f;
        int ringSize = 16;
        float ringX = x - (float)(ringSize / 2) + ringProgressAdj * (float)activeBarWidth / 2.0f;
        float ringY = y + (float)(barHeight / 2) - (float)(ringSize / 2);
        int flashTick = ticker.flashTick;
        if (pledged) {
            LOTRTickHandlerClient.drawTexturedModalRect(ringX, ringY, 16 * Math.round(flashTick / 3), 212, ringSize, ringSize);
        } else {
            LOTRTickHandlerClient.drawTexturedModalRect(ringX, ringY, 16 * Math.round(flashTick / 3), 36, ringSize, ringSize);
        }
        if (faction.isPlayableAlignmentFaction()) {
            float alpha = 0.0f;
            boolean definedZone = false;
            if (faction.inControlZone((EntityPlayer)entityplayer)) {
                alpha = 1.0f;
                definedZone = faction.inDefinedControlZone((EntityPlayer)entityplayer);
            } else {
                alpha = faction.getControlZoneAlignmentMultiplier((EntityPlayer)entityplayer);
                definedZone = true;
            }
            if (alpha > 0.0f) {
                int arrowSize = 14;
                int y0 = definedZone ? 60 : 88;
                int y1 = definedZone ? 74 : 102;
                GL11.glEnable((int)3042);
                OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
                GL11.glColor4f((float)factionColors[0], (float)factionColors[1], (float)factionColors[2], (float)alpha);
                LOTRTickHandlerClient.drawTexturedModalRect(x - (float)(barWidth / 2) - (float)arrowSize, y, 0, y1, arrowSize, arrowSize);
                LOTRTickHandlerClient.drawTexturedModalRect(x + (float)(barWidth / 2), y, arrowSize, y1, arrowSize, arrowSize);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                LOTRTickHandlerClient.drawTexturedModalRect(x - (float)(barWidth / 2) - (float)arrowSize, y, 0, y0, arrowSize, arrowSize);
                LOTRTickHandlerClient.drawTexturedModalRect(x + (float)(barWidth / 2), y, arrowSize, y0, arrowSize, arrowSize);
                GL11.glDisable((int)3042);
            }
        }
        FontRenderer fr = mc.fontRenderer;
        int textX = Math.round(x);
        int textY = Math.round(y + (float)barHeight + 4.0f);
        if (renderLimits) {
            String sMin = rankMin.getShortNameWithGender(clientPD);
            String sMax = rankMax.getShortNameWithGender(clientPD);
            if (renderLimitValues) {
                sMin = StatCollector.translateToLocalFormatted((String)"lotr.gui.factions.alignment.limits", (Object[])new Object[]{sMin, LOTRAlignmentValues.formatAlignForDisplay(alignMin)});
                sMax = StatCollector.translateToLocalFormatted((String)"lotr.gui.factions.alignment.limits", (Object[])new Object[]{sMax, LOTRAlignmentValues.formatAlignForDisplay(alignMax)});
            }
            int limitsX = barWidth / 2 - 6;
            int xMin = Math.round(x - (float)limitsX);
            int xMax = Math.round(x + (float)limitsX);
            GL11.glPushMatrix();
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            LOTRTickHandlerClient.drawAlignmentText(fr, xMin * 2 - fr.getStringWidth(sMin) / 2, textY * 2, sMin, 1.0f);
            LOTRTickHandlerClient.drawAlignmentText(fr, xMax * 2 - fr.getStringWidth(sMax) / 2, textY * 2, sMax, 1.0f);
            GL11.glPopMatrix();
        }
        if (renderFacName) {
            String name = faction.factionName();
            LOTRTickHandlerClient.drawAlignmentText(fr, textX - fr.getStringWidth(name) / 2, textY, name, 1.0f);
        }
        if (renderValue) {
            float alignAlpha;
            String alignS;
            int numericalTick = ticker.numericalTick;
            if (numericalTick > 0) {
                alignS = LOTRAlignmentValues.formatAlignForDisplay(alignment);
                alignAlpha = LOTRFunctions.triangleWave(numericalTick, 0.7f, 1.0f, 30.0f);
                int fadeTick = 15;
                if (numericalTick < fadeTick) {
                    alignAlpha *= (float)numericalTick / (float)fadeTick;
                }
            } else {
                alignS = rank.getShortNameWithGender(clientPD);
                alignAlpha = 1.0f;
            }
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            LOTRTickHandlerClient.drawAlignmentText(fr, textX - fr.getStringWidth(alignS) / 2, textY + fr.FONT_HEIGHT + 3, alignS, alignAlpha);
            GL11.glDisable((int)3042);
        }
    }

    public static void renderAlignmentDrain(Minecraft mc, int x, int y, int numFactions) {
        LOTRTickHandlerClient.renderAlignmentDrain(mc, x, y, numFactions, 1.0f);
    }

    public static void renderAlignmentDrain(Minecraft mc, int x, int y, int numFactions, float alpha) {
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
        LOTRTickHandlerClient.drawTexturedModalRect(x, y, 0, 128, 16, 16);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        String s = "-" + numFactions;
        FontRenderer fr = mc.fontRenderer;
        LOTRTickHandlerClient.drawBorderedText(fr, x + 8 - fr.getStringWidth(s) / 2, y + 8 - fr.FONT_HEIGHT / 2, s, 16777215, alpha);
        GL11.glDisable((int)3042);
    }

    public static void drawTexturedModalRect(double x, double y, int u, int v, int width, int height) {
        float f = 0.00390625f;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0.0, y + (double)height, 0.0, (double)((float)(u + 0) * f), (double)((float)(v + height) * f));
        tessellator.addVertexWithUV(x + (double)width, y + (double)height, 0.0, (double)((float)(u + width) * f), (double)((float)(v + height) * f));
        tessellator.addVertexWithUV(x + (double)width, y + 0.0, 0.0, (double)((float)(u + width) * f), (double)((float)(v + 0) * f));
        tessellator.addVertexWithUV(x + 0.0, y + 0.0, 0.0, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f));
        tessellator.draw();
    }

    public static void drawAlignmentText(FontRenderer f, int x, int y, String s, float alphaF) {
        LOTRTickHandlerClient.drawBorderedText(f, x, y, s, 16772620, alphaF);
    }

    public static void drawConquestText(FontRenderer f, int x, int y, String s, boolean cleanse, float alphaF) {
        LOTRTickHandlerClient.drawBorderedText(f, x, y, s, cleanse ? 16773846 : 14833677, alphaF);
    }

    public static void drawBorderedText(FontRenderer f, int x, int y, String s, int color, float alphaF) {
        int alpha = (int)(alphaF * 255.0f);
        alpha = MathHelper.clamp_int((int)alpha, (int)4, (int)255);
        f.drawString(s, x - 1, y - 1, 0 | (alpha <<= 24));
        f.drawString(s, x, y - 1, 0 | alpha);
        f.drawString(s, x + 1, y - 1, 0 | alpha);
        f.drawString(s, x + 1, y, 0 | alpha);
        f.drawString(s, x + 1, y + 1, 0 | alpha);
        f.drawString(s, x, y + 1, 0 | alpha);
        f.drawString(s, x - 1, y + 1, 0 | alpha);
        f.drawString(s, x - 1, y, 0 | alpha);
        f.drawString(s, x, y, color | alpha);
    }

    public void onFrostDamage() {
        this.frostTick = 80;
    }

    public void onBurnDamage() {
        this.burnTick = 40;
    }

    public void updateDate() {
        this.newDate = 200;
    }

    public float getWightLookFactor() {
        float f = (float)this.prevWightLookTick + (float)(this.wightLookTick - this.prevWightLookTick) * renderTick;
        return f /= 100.0f;
    }

    private static boolean isBossActive() {
        return BossStatus.bossName != null && BossStatus.statusBarTime > 0;
    }

    static {
        watchedInvasion = new LOTRInvasionStatus();
        scrapTraderMisbehaveTick = 0;
    }
}

