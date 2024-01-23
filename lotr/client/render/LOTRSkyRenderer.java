/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.client.IRenderHandler
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render;

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;

public class LOTRSkyRenderer
extends IRenderHandler {
    private static final ResourceLocation moonTexture = new ResourceLocation("lotr:sky/moon.png");
    private static final ResourceLocation sunTexture = new ResourceLocation("lotr:sky/sun.png");
    private static final ResourceLocation earendilTexture = new ResourceLocation("lotr:sky/earendil.png");
    private LOTRWorldProvider worldProvider;
    private LOTRRandomSkins skyTextures;
    private ResourceLocation currentSkyTexture;
    private int glSkyList;
    private int glSkyList2;

    public LOTRSkyRenderer(LOTRWorldProvider provider) {
        int j;
        int k;
        this.worldProvider = provider;
        this.skyTextures = LOTRRandomSkins.loadSkinsList("lotr:sky/night");
        Tessellator tessellator = Tessellator.instance;
        this.glSkyList = GLAllocation.generateDisplayLists((int)3);
        GL11.glNewList((int)this.glSkyList, (int)4864);
        int b2 = 64;
        int i = 256 / b2 + 2;
        float f = 16.0f;
        for (j = -b2 * i; j <= b2 * i; j += b2) {
            for (k = -b2 * i; k <= b2 * i; k += b2) {
                tessellator.startDrawingQuads();
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + b2), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + b2), (double)f, (double)(k + b2));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + b2));
                tessellator.draw();
            }
        }
        GL11.glEndList();
        this.glSkyList2 = this.glSkyList + 1;
        GL11.glNewList((int)this.glSkyList2, (int)4864);
        f = -16.0f;
        tessellator.startDrawingQuads();
        for (j = -b2 * i; j <= b2 * i; j += b2) {
            for (k = -b2 * i; k <= b2 * i; k += b2) {
                tessellator.addVertex((double)(j + b2), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + b2));
                tessellator.addVertex((double)(j + b2), (double)f, (double)(k + b2));
            }
        }
        tessellator.draw();
        GL11.glEndList();
    }

    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        world.theProfiler.startSection("lotrSky");
        boolean renderSkyFeatures = world.provider.isSurfaceWorld();
        int i = MathHelper.floor_double((double)mc.renderViewEntity.posX);
        int k = MathHelper.floor_double((double)mc.renderViewEntity.posZ);
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiome && renderSkyFeatures) {
            renderSkyFeatures = ((LOTRBiome)biome).hasSky();
        }
        GL11.glDisable((int)3553);
        Vec3 skyColor = world.getSkyColor((Entity)mc.renderViewEntity, partialTicks);
        float skyR = (float)skyColor.xCoord;
        float skyG = (float)skyColor.yCoord;
        float skyB = (float)skyColor.zCoord;
        if (mc.gameSettings.anaglyph) {
            float newSkyR = (skyR * 30.0f + skyG * 59.0f + skyB * 11.0f) / 100.0f;
            float newSkyG = (skyR * 30.0f + skyG * 70.0f) / 100.0f;
            float newSkyB = (skyR * 30.0f + skyB * 70.0f) / 100.0f;
            skyR = newSkyR;
            skyG = newSkyG;
            skyB = newSkyB;
        }
        GL11.glColor3f((float)skyR, (float)skyG, (float)skyB);
        Tessellator tessellator = Tessellator.instance;
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2912);
        GL11.glColor3f((float)skyR, (float)skyG, (float)skyB);
        GL11.glCallList((int)this.glSkyList);
        GL11.glDisable((int)2912);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        RenderHelper.disableStandardItemLighting();
        float[] sunrise = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);
        if (sunrise != null) {
            GL11.glDisable((int)3553);
            GL11.glShadeModel((int)7425);
            GL11.glPushMatrix();
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(MathHelper.sin((float)world.getCelestialAngleRadians(partialTicks)) < 0.0f ? 180.0f : 0.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            float r = sunrise[0];
            float g = sunrise[1];
            g *= 1.2f;
            float b = sunrise[2];
            if (mc.gameSettings.anaglyph) {
                float r1 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
                float g1 = (r * 30.0f + g * 70.0f) / 100.0f;
                float b1 = (r * 30.0f + b * 70.0f) / 100.0f;
                r = r1;
                g = g1;
                b = b1;
            }
            tessellator.startDrawing(6);
            tessellator.setColorRGBA_F(r, g, b, sunrise[3]);
            tessellator.addVertex(0.0, 100.0, 0.0);
            tessellator.setColorRGBA_F(sunrise[0], sunrise[1], sunrise[2], 0.0f);
            int passes = 16;
            for (int l = 0; l <= passes; ++l) {
                float angle = (float)l * 3.1415927f * 2.0f / (float)passes;
                float sin = MathHelper.sin((float)angle);
                float cos = MathHelper.cos((float)angle);
                tessellator.addVertex((double)(sin * 120.0f), (double)(cos * 120.0f), (double)(-cos * 40.0f * sunrise[3]));
            }
            tessellator.draw();
            GL11.glPopMatrix();
            GL11.glShadeModel((int)7424);
        }
        GL11.glPushMatrix();
        if (renderSkyFeatures) {
            GL11.glEnable((int)3553);
            GL11.glBlendFunc((int)770, (int)1);
            float rainBrightness = 1.0f - world.getRainStrength(partialTicks);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)rainBrightness);
            float x = 0.0f;
            float y = 0.0f;
            float z = 0.0f;
            GL11.glTranslatef((float)x, (float)y, (float)z);
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float starBrightness = world.getStarBrightness(partialTicks) * rainBrightness;
            if (starBrightness > 0.0f) {
                if (this.currentSkyTexture == null) {
                    this.currentSkyTexture = this.skyTextures.getRandomSkin();
                }
                mc.renderEngine.bindTexture(this.currentSkyTexture);
                GL11.glPushMatrix();
                GL11.glRotatef((float)(world.getCelestialAngle(partialTicks) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)starBrightness);
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                this.renderSkyboxSide(tessellator, 4);
                GL11.glPushMatrix();
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                this.renderSkyboxSide(tessellator, 1);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                this.renderSkyboxSide(tessellator, 0);
                GL11.glPopMatrix();
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                this.renderSkyboxSide(tessellator, 5);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                this.renderSkyboxSide(tessellator, 2);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                this.renderSkyboxSide(tessellator, 3);
                GL11.glPopMatrix();
            } else {
                this.currentSkyTexture = null;
            }
            GL11.glRotatef((float)(world.getCelestialAngle(partialTicks) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glBlendFunc((int)770, (int)771);
            mc.renderEngine.bindTexture(sunTexture);
            float rSun = 10.0f;
            for (int pass = 0; pass <= 1; ++pass) {
                if (pass == 0) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)rainBrightness);
                } else if (pass == 1) {
                    if (sunrise == null) continue;
                    float sunriseBlend = sunrise[3];
                    GL11.glColor4f((float)1.0f, (float)0.9f, (float)0.2f, (float)((sunriseBlend *= 0.5f) * rainBrightness));
                }
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV((double)(-rSun), 100.0, (double)(-rSun), 0.0, 0.0);
                tessellator.addVertexWithUV((double)rSun, 100.0, (double)(-rSun), 1.0, 0.0);
                tessellator.addVertexWithUV((double)rSun, 100.0, (double)rSun, 1.0, 1.0);
                tessellator.addVertexWithUV((double)(-rSun), 100.0, (double)rSun, 0.0, 1.0);
                tessellator.draw();
            }
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)rainBrightness);
            int phases = LOTRWorldProvider.MOON_PHASES;
            int moonPhase = LOTRWorldProvider.getLOTRMoonPhase();
            boolean lunarEclipse = LOTRWorldProvider.isLunarEclipse();
            if (lunarEclipse) {
                GL11.glColor3f((float)1.0f, (float)0.6f, (float)0.4f);
            }
            mc.renderEngine.bindTexture(moonTexture);
            float rMoon = 10.0f;
            float f14 = (float)moonPhase / (float)phases;
            float f15 = 0.0f;
            float f16 = (float)(moonPhase + 1) / (float)phases;
            float f17 = 1.0f;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)(-rMoon), -100.0, (double)rMoon, (double)f16, (double)f17);
            tessellator.addVertexWithUV((double)rMoon, -100.0, (double)rMoon, (double)f14, (double)f17);
            tessellator.addVertexWithUV((double)rMoon, -100.0, (double)(-rMoon), (double)f14, (double)f15);
            tessellator.addVertexWithUV((double)(-rMoon), -100.0, (double)(-rMoon), (double)f16, (double)f15);
            tessellator.draw();
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            float celestialAngle = world.getCelestialAngle(partialTicks);
            float f0 = celestialAngle - 0.5f;
            float f1 = Math.abs(f0);
            float eMin = 0.15f;
            float eMax = 0.3f;
            if (f1 >= eMin && f1 <= eMax) {
                float eMid = (eMin + eMax) / 2.0f;
                float eHalfWidth = eMax - eMid;
                float eBright = MathHelper.cos((float)((f1 - eMid) / eHalfWidth * 3.1415927f / 2.0f));
                eBright *= eBright;
                float eAngle = Math.signum(f0) * 18.0f;
                GL11.glPushMatrix();
                GL11.glRotatef((float)eAngle, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glBlendFunc((int)770, (int)1);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(eBright * rainBrightness));
                mc.renderEngine.bindTexture(earendilTexture);
                float rEarendil = 1.5f;
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV((double)(-rEarendil), 100.0, (double)(-rEarendil), 0.0, 0.0);
                tessellator.addVertexWithUV((double)rEarendil, 100.0, (double)(-rEarendil), 1.0, 0.0);
                tessellator.addVertexWithUV((double)rEarendil, 100.0, (double)rEarendil, 1.0, 1.0);
                tessellator.addVertexWithUV((double)(-rEarendil), 100.0, (double)rEarendil, 0.0, 1.0);
                tessellator.draw();
                GL11.glPopMatrix();
            }
            GL11.glDisable((int)3553);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)2912);
        GL11.glPopMatrix();
        GL11.glDisable((int)3553);
        GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
        double d0 = mc.thePlayer.getPosition((float)partialTicks).yCoord - world.getHorizon();
        if (d0 < 0.0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)12.0f, (float)0.0f);
            GL11.glCallList((int)this.glSkyList2);
            GL11.glPopMatrix();
            float f8 = 1.0f;
            float f9 = -((float)(d0 + 65.0));
            float f10 = -f8;
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_I(0, 255);
            tessellator.addVertex((double)(-f8), (double)f9, (double)f8);
            tessellator.addVertex((double)f8, (double)f9, (double)f8);
            tessellator.addVertex((double)f8, (double)f10, (double)f8);
            tessellator.addVertex((double)(-f8), (double)f10, (double)f8);
            tessellator.addVertex((double)(-f8), (double)f10, (double)(-f8));
            tessellator.addVertex((double)f8, (double)f10, (double)(-f8));
            tessellator.addVertex((double)f8, (double)f9, (double)(-f8));
            tessellator.addVertex((double)(-f8), (double)f9, (double)(-f8));
            tessellator.addVertex((double)f8, (double)f10, (double)(-f8));
            tessellator.addVertex((double)f8, (double)f10, (double)f8);
            tessellator.addVertex((double)f8, (double)f9, (double)f8);
            tessellator.addVertex((double)f8, (double)f9, (double)(-f8));
            tessellator.addVertex((double)(-f8), (double)f9, (double)(-f8));
            tessellator.addVertex((double)(-f8), (double)f9, (double)f8);
            tessellator.addVertex((double)(-f8), (double)f10, (double)f8);
            tessellator.addVertex((double)(-f8), (double)f10, (double)(-f8));
            tessellator.addVertex((double)(-f8), (double)f10, (double)(-f8));
            tessellator.addVertex((double)(-f8), (double)f10, (double)f8);
            tessellator.addVertex((double)f8, (double)f10, (double)f8);
            tessellator.addVertex((double)f8, (double)f10, (double)(-f8));
            tessellator.draw();
        }
        if (world.provider.isSkyColored()) {
            GL11.glColor3f((float)(skyR * 0.2f + 0.04f), (float)(skyG * 0.2f + 0.04f), (float)(skyB * 0.6f + 0.1f));
        } else {
            GL11.glColor3f((float)skyR, (float)skyG, (float)skyB);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-((float)(d0 - 16.0))), (float)0.0f);
        GL11.glCallList((int)this.glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDepthMask((boolean)true);
        world.theProfiler.endSection();
    }

    private void renderSkyboxSide(Tessellator tessellator, int side) {
        double u = (double)(side % 3) / 3.0;
        double v = (double)(side / 3) / 2.0;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-100.0, -100.0, -100.0, u, v);
        tessellator.addVertexWithUV(-100.0, -100.0, 100.0, u, v + 0.5);
        tessellator.addVertexWithUV(100.0, -100.0, 100.0, u + 0.3333333333333333, v + 0.5);
        tessellator.addVertexWithUV(100.0, -100.0, -100.0, u + 0.3333333333333333, v);
        tessellator.draw();
    }
}

