/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.client.IRenderHandler
 *  org.lwjgl.opengl.ContextCapabilities
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLContext
 *  org.lwjgl.util.glu.Project
 */
package lotr.client.render;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.client.LOTRReflectionClient;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class LOTRCloudRenderer
extends IRenderHandler {
    public static final ResourceLocation cloudTexture = new ResourceLocation("lotr:sky/clouds.png");
    private static int cloudRange;
    private static Random cloudRand;
    private static CloudProperty cloudOpacity;
    private static CloudProperty cloudSpeed;
    private static CloudProperty cloudAngle;
    private static double cloudPosXPre;
    private static double cloudPosX;
    private static double cloudPosZPre;
    private static double cloudPosZ;

    public static void updateClouds(WorldClient world) {
        cloudOpacity.update(world);
        cloudSpeed.update(world);
        cloudAngle.update(world);
        float angle = cloudAngle.getValue(1.0f);
        float speed = cloudSpeed.getValue(1.0f);
        cloudPosXPre = cloudPosX;
        cloudPosX += (double)(MathHelper.cos((float)angle) * speed);
        cloudPosZPre = cloudPosZ;
        cloudPosZ += (double)(MathHelper.sin((float)angle) * speed);
    }

    public static void resetClouds() {
        cloudOpacity.reset();
        cloudSpeed.reset();
        cloudAngle.reset();
    }

    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        world.theProfiler.startSection("lotrClouds");
        if (world.provider.isSurfaceWorld()) {
            Block block = ActiveRenderInfo.getBlockAtEntityViewpoint((World)world, (EntityLivingBase)mc.renderViewEntity, (float)partialTicks);
            if (block.getMaterial() == Material.water) {
                return;
            }
            cloudRange = LOTRConfig.cloudRange;
            GL11.glMatrixMode((int)5889);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            float fov = LOTRReflectionClient.getFOVModifier(mc.entityRenderer, partialTicks, true);
            Project.gluPerspective((float)fov, (float)((float)mc.displayWidth / (float)mc.displayHeight), (float)0.05f, (float)cloudRange);
            GL11.glMatrixMode((int)5888);
            GL11.glPushMatrix();
            GL11.glDisable((int)2884);
            GL11.glDepthMask((boolean)false);
            GL11.glEnable((int)3008);
            float alphaFunc = GL11.glGetFloat((int)3010);
            GL11.glAlphaFunc((int)516, (float)0.01f);
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            GL11.glFogi((int)2917, (int)9729);
            GL11.glFogf((int)2915, (float)((float)cloudRange * 0.9f));
            GL11.glFogf((int)2916, (float)cloudRange);
            if (GLContext.getCapabilities().GL_NV_fog_distance) {
                GL11.glFogi((int)34138, (int)34139);
            }
            Tessellator tessellator = Tessellator.instance;
            mc.renderEngine.bindTexture(cloudTexture);
            Vec3 cloudColor = world.getCloudColour(partialTicks);
            float r = (float)cloudColor.xCoord;
            float g = (float)cloudColor.yCoord;
            float b = (float)cloudColor.zCoord;
            if (mc.gameSettings.anaglyph) {
                float r1 = (r * 30.0f + g * 59.0f + b * 11.0f) / 100.0f;
                float g1 = (r * 30.0f + g * 70.0f) / 100.0f;
                float b1 = (r * 30.0f + b * 70.0f) / 100.0f;
                r = r1;
                g = g1;
                b = b1;
            }
            Vec3 pos = mc.renderViewEntity.getPosition(partialTicks);
            for (int pass = 0; pass < 2; ++pass) {
                int scale = 4096 * IntMath.pow((int)2, (int)pass);
                double invScaleD = 1.0 / (double)scale;
                double posX = pos.xCoord;
                double posZ = pos.zCoord;
                double posY = pos.yCoord;
                double cloudPosXAdd = cloudPosXPre + (cloudPosX - cloudPosXPre) * (double)partialTicks;
                double cloudPosZAdd = cloudPosZPre + (cloudPosZ - cloudPosZPre) * (double)partialTicks;
                int x = MathHelper.floor_double((double)((posX += (cloudPosXAdd /= (double)(pass + 1))) / (double)scale));
                int z = MathHelper.floor_double((double)((posZ += (cloudPosZAdd /= (double)(pass + 1))) / (double)scale));
                double cloudX = posX - (double)(x * scale);
                double cloudZ = posZ - (double)(z * scale);
                double cloudY = (double)world.provider.getCloudHeight() - posY + 0.33000001311302185 + (double)((float)pass * 50.0f);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_F(r, g, b, (0.8f - (float)pass * 0.5f) * cloudOpacity.getValue(partialTicks));
                int interval = cloudRange;
                for (int i = -cloudRange; i < cloudRange; i += interval) {
                    for (int k = -cloudRange; k < cloudRange; k += interval) {
                        int xMin = i + 0;
                        int xMax = i + interval;
                        int zMin = k + 0;
                        int zMax = k + interval;
                        double uMin = ((double)xMin + cloudX) * invScaleD;
                        double uMax = ((double)xMax + cloudX) * invScaleD;
                        double vMin = ((double)zMin + cloudZ) * invScaleD;
                        double vMax = ((double)zMax + cloudZ) * invScaleD;
                        tessellator.addVertexWithUV((double)xMin, cloudY, (double)zMax, uMin, vMax);
                        tessellator.addVertexWithUV((double)xMax, cloudY, (double)zMax, uMax, vMax);
                        tessellator.addVertexWithUV((double)xMax, cloudY, (double)zMin, uMax, vMin);
                        tessellator.addVertexWithUV((double)xMin, cloudY, (double)zMin, uMin, vMin);
                    }
                }
                tessellator.draw();
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)2884);
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)alphaFunc);
            GL11.glDisable((int)3042);
            GL11.glMatrixMode((int)5889);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glPopMatrix();
        }
        world.theProfiler.endSection();
    }

    static {
        cloudRand = new Random();
        cloudOpacity = new CloudProperty(233591206262L, 0.1f, 1.0f, 0.001f);
        cloudSpeed = new CloudProperty(6283905602629L, 0.0f, 0.5f, 0.001f);
        cloudAngle = new CloudProperty(360360635650636L, 0.0f, 6.2831855f, 0.01f);
    }

    private static class CloudProperty {
        private final long baseSeed;
        private float currentDayValue;
        private float value;
        private float prevValue;
        private final float minValue;
        private final float maxValue;
        private final float interval;

        public CloudProperty(long l, float min, float max, float i) {
            this.baseSeed = l;
            this.value = -1.0f;
            this.minValue = min;
            this.maxValue = max;
            this.interval = i;
        }

        public void reset() {
            this.value = -1.0f;
        }

        public float getValue(float f) {
            return this.prevValue + (this.value - this.prevValue) * f;
        }

        public void update(WorldClient world) {
            this.currentDayValue = this.getCurrentDayValue(world);
            if (this.value == -1.0f) {
                this.prevValue = this.value = this.currentDayValue;
            } else {
                this.prevValue = this.value;
                if (this.value > this.currentDayValue) {
                    this.value -= this.interval;
                    this.value = Math.max(this.value, this.currentDayValue);
                } else if (this.value < this.currentDayValue) {
                    this.value += this.interval;
                    this.value = Math.min(this.value, this.currentDayValue);
                }
            }
        }

        private float getCurrentDayValue(WorldClient world) {
            int day = LOTRDate.ShireReckoning.currentDay;
            long seed = (long)day * this.baseSeed + (long)day + 83025820626792L;
            cloudRand.setSeed(seed);
            float f = MathHelper.randomFloatClamp((Random)cloudRand, (float)this.minValue, (float)this.maxValue);
            return f;
        }
    }

}

