/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Project
 */
package lotr.client.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import lotr.client.LOTRReflectionClient;
import lotr.common.LOTRDate;
import lotr.common.LOTRMod;
import lotr.common.LOTRTime;
import lotr.common.world.map.LOTRFixedStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

public class LOTRRenderNorthernLights {
    public static int nlTick;
    public static int currentNightNum;
    public static float brightnessTonight;
    public static float maxNorthTonight;
    public static float minNorthTonight;
    public static int rainingTick;
    public static int rainingTickPrev;
    public static int rainingChangeTime;
    public static boolean atNightKing;
    public static int nightKingChange;
    public static int nightKingChangeTime;
    public static Random rand;
    public static Random dateRand;
    public static float[] colorTopCurrent;
    public static float[] colorMidCurrent;
    public static float[] colorBottomCurrent;
    public static float[] colorTopNext;
    public static float[] colorMidNext;
    public static float[] colorBottomNext;
    public static int colorChangeTime;
    public static int colorChangeTick;
    public static int timeUntilColorChange;
    public static int nightKingCheckTime;
    public static AuroraCycle wave0;
    public static Collection<AuroraCycle> waveOscillations;
    public static Collection<AuroraCycle> glowOscillations;
    public static AuroraCycle glow0;

    public static Color[] generateColorSet() {
        float h1 = MathHelper.randomFloatClamp((Random)rand, (float)0.22f, (float)0.48f);
        float h2 = MathHelper.randomFloatClamp((Random)rand, (float)0.22f, (float)0.48f);
        float h3 = MathHelper.randomFloatClamp((Random)rand, (float)0.22f, (float)0.48f);
        if (rand.nextInt(3) == 0) {
            h1 = MathHelper.randomFloatClamp((Random)rand, (float)0.78f, (float)1.08f);
        }
        if (rand.nextInt(5) == 0) {
            h1 = MathHelper.randomFloatClamp((Random)rand, (float)0.78f, (float)1.08f);
            h2 = MathHelper.randomFloatClamp((Random)rand, (float)0.85f, (float)1.08f);
        }
        if (rand.nextInt(50) == 0) {
            h1 = MathHelper.randomFloatClamp((Random)rand, (float)0.7f, (float)1.08f);
            h2 = MathHelper.randomFloatClamp((Random)rand, (float)0.54f, (float)0.77f);
            h3 = MathHelper.randomFloatClamp((Random)rand, (float)0.48f, (float)0.7f);
        }
        Color topColor = new Color(Color.HSBtoRGB(h1, 1.0f, 1.0f));
        Color midColor = new Color(Color.HSBtoRGB(h2, 1.0f, 1.0f));
        Color bottomColor = new Color(Color.HSBtoRGB(h3, 1.0f, 1.0f));
        return new Color[]{topColor, midColor, bottomColor};
    }

    public static float getNorthernness(EntityLivingBase entity) {
        float minNorth = minNorthTonight;
        float maxNorth = maxNorthTonight;
        float northernness = ((float)entity.posZ - minNorth) / (maxNorth - minNorth);
        return MathHelper.clamp_float((float)northernness, (float)0.0f, (float)1.0f);
    }

    public static float glowEquation(Minecraft mc, float t, float tick, float renderTick) {
        float f = 0.0f;
        f += glow0.calc(t, tick);
        if (mc.gameSettings.fancyGraphics) {
            for (AuroraCycle c : glowOscillations) {
                f += c.calc(t, tick);
            }
        }
        return f;
    }

    public static boolean isRainLayerAt(EntityLivingBase entity) {
        World world = entity.worldObj;
        int i = MathHelper.floor_double((double)entity.posX);
        int j = MathHelper.floor_double((double)entity.boundingBox.minY);
        int k = MathHelper.floor_double((double)entity.posZ);
        if (!world.isRaining()) {
            return false;
        }
        BiomeGenBase biomegenbase = world.getBiomeGenForCoords(i, k);
        return !biomegenbase.getEnableSnow() && biomegenbase.getFloatTemperature(i, j, k) >= 0.15f && biomegenbase.canSpawnLightningBolt();
    }

    public static void render(Minecraft mc, WorldClient world, float tick) {
        float maxDaylight;
        if (mc.gameSettings.renderDistanceChunks < 6) {
            return;
        }
        float minSun = 0.2f;
        float daylight = (world.getSunBrightness(tick) - minSun) / (1.0f - minSun);
        float nlBrightness = (1.0f - daylight - (1.0f - 0.3f)) / (maxDaylight = 0.3f);
        if (nlBrightness <= 0.0f) {
            return;
        }
        float tonight = brightnessTonight;
        float utumno = (float)nightKingChange / 200.0f;
        if ((tonight += (1.0f - tonight) * utumno) <= 0.0f) {
            return;
        }
        nlBrightness *= tonight;
        float northernness = LOTRRenderNorthernLights.getNorthernness(mc.renderViewEntity);
        if (northernness <= 0.0f) {
            return;
        }
        nlBrightness *= northernness;
        float raininess = ((float)rainingTickPrev + (float)(rainingTick - rainingTickPrev) * tick) / 80.0f;
        if (raininess >= 1.0f) {
            return;
        }
        nlBrightness *= 1.0f - raininess;
        world.theProfiler.startSection("aurora");
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        float fov = LOTRReflectionClient.getFOVModifier(mc.entityRenderer, tick, true);
        float nlScale = 2000.0f;
        Project.gluPerspective((float)fov, (float)((float)mc.displayWidth / (float)mc.displayHeight), (float)0.05f, (float)(nlScale * 5.0f));
        GL11.glMatrixMode((int)5888);
        GL11.glPushMatrix();
        float nlHeight = nlScale * 0.5f;
        float nlDistance = (1.0f - northernness) * nlScale * 2.0f;
        GL11.glTranslatef((float)0.0f, (float)nlHeight, (float)(-nlDistance));
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)3008);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        float alphaFunc = GL11.glGetFloat((int)3010);
        GL11.glAlphaFunc((int)516, (float)0.01f);
        GL11.glShadeModel((int)7425);
        GL11.glDisable((int)2884);
        world.theProfiler.startSection("sheet");
        LOTRRenderNorthernLights.renderSheet(mc, (World)world, nlScale * -0.5f, (nlBrightness *= 0.3f + (1.0f - world.getRainStrength(tick)) * 0.7f) * 0.8f, nlScale, nlScale * 0.25f, 0.25502f, tick);
        LOTRRenderNorthernLights.renderSheet(mc, (World)world, 0.0f, nlBrightness, nlScale * 1.5f, nlScale * 0.3f, 0.15696f, tick);
        LOTRRenderNorthernLights.renderSheet(mc, (World)world, nlScale * 0.5f, nlBrightness * 0.8f, nlScale, nlScale * 0.25f, 0.67596f, tick);
        world.theProfiler.endSection();
        GL11.glEnable((int)2884);
        GL11.glShadeModel((int)7424);
        GL11.glAlphaFunc((int)516, (float)alphaFunc);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
        GL11.glMatrixMode((int)5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        world.theProfiler.endSection();
    }

    public static void renderSheet(Minecraft mc, World world, float nlDistance, float nlBrightness, double halfWidth, double halfHeight, float tickExtra, float tick) {
        float r1 = colorTopCurrent[0];
        float g1 = colorTopCurrent[1];
        float b1 = colorTopCurrent[2];
        float r2 = colorMidCurrent[0];
        float g2 = colorMidCurrent[1];
        float b2 = colorMidCurrent[2];
        float r3 = colorBottomCurrent[0];
        float g3 = colorBottomCurrent[1];
        float b3 = colorBottomCurrent[2];
        if (colorChangeTime > 0) {
            float t = (float)colorChangeTick / (float)colorChangeTime;
            t = 1.0f - t;
            r1 = colorTopCurrent[0] + (colorTopNext[0] - colorTopCurrent[0]) * t;
            g1 = colorTopCurrent[1] + (colorTopNext[1] - colorTopCurrent[1]) * t;
            b1 = colorTopCurrent[2] + (colorTopNext[2] - colorTopCurrent[2]) * t;
            r2 = colorMidCurrent[0] + (colorMidNext[0] - colorMidCurrent[0]) * t;
            g2 = colorMidCurrent[1] + (colorMidNext[1] - colorMidCurrent[1]) * t;
            b2 = colorMidCurrent[2] + (colorMidNext[2] - colorMidCurrent[2]) * t;
            r3 = colorBottomCurrent[0] + (colorBottomNext[0] - colorBottomCurrent[0]) * t;
            g3 = colorBottomCurrent[1] + (colorBottomNext[1] - colorBottomCurrent[1]) * t;
            b3 = colorBottomCurrent[2] + (colorBottomNext[2] - colorBottomCurrent[2]) * t;
        }
        float a1 = 0.0f;
        a1 *= nlBrightness;
        float a2 = 0.4f;
        a2 *= nlBrightness;
        float a3 = 0.8f;
        a3 *= nlBrightness;
        float fullTick = (float)nlTick + tick + tickExtra;
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setBrightness(15728880);
        world.theProfiler.startSection("vertexLoop");
        int strips = 500;
        if (!mc.gameSettings.fancyGraphics) {
            strips = 80;
        }
        for (int l = 0; l < strips; ++l) {
            float t = (float)l / (float)strips;
            float t1 = (float)(l + 1) / (float)strips;
            float a1_here = a1;
            float a2_here = a2;
            float a3_here = a3;
            float fadeEdge = 0.3f;
            float fadePos = Math.min(t, 1.0f - t);
            if (fadePos < fadeEdge) {
                float fade = fadePos / fadeEdge;
                a1_here *= fade;
                a2_here *= fade;
                a3_here *= fade;
            }
            float randomFade = 0.5f + LOTRRenderNorthernLights.glowEquation(mc, t, fullTick, tick) * 0.5f;
            double x0 = -halfWidth + halfWidth * 2.0 * (double)t;
            double x1 = x0 + halfWidth * 2.0 / (double)strips;
            double yMin = -halfHeight;
            double yMid = -halfHeight * 0.4;
            tess.setColorRGBA_F(r3, g3, b3, 0.0f);
            double extra = halfHeight * 0.15;
            double z0 = nlDistance;
            tess.addVertex(x0, yMin - extra, z0 += (double)LOTRRenderNorthernLights.waveEquation(mc, t, fullTick, tick) * (halfWidth * 0.15));
            double z1 = nlDistance;
            tess.addVertex(x1, yMin - extra, z1 += (double)LOTRRenderNorthernLights.waveEquation(mc, t1, fullTick, tick) * (halfWidth * 0.15));
            tess.setColorRGBA_F(r3, g3, b3, a3_here *= randomFade);
            tess.addVertex(x1, yMin, z1);
            tess.addVertex(x0, yMin, z0);
            tess.setColorRGBA_F(r3, g3, b3, a3_here);
            tess.addVertex(x0, yMin, z0);
            tess.addVertex(x1, yMin, z1);
            tess.setColorRGBA_F(r2, g2, b2, a2_here *= randomFade);
            tess.addVertex(x1, yMid, z1);
            tess.addVertex(x0, yMid, z0);
            tess.setColorRGBA_F(r2, g2, b2, a2_here);
            tess.addVertex(x0, yMid, z0);
            tess.addVertex(x1, yMid, z1);
            tess.setColorRGBA_F(r1, g1, b1, a1_here * randomFade);
            tess.addVertex(x1, halfHeight, z1);
            tess.addVertex(x0, halfHeight, z0);
        }
        world.theProfiler.endSection();
        world.theProfiler.startSection("draw");
        tess.draw();
        world.theProfiler.endSection();
    }

    public static void update(EntityLivingBase viewer) {
        AuroraCycle cycle;
        float freq;
        float amp;
        float speed;
        ++nlTick;
        World world = viewer.worldObj;
        int effectiveDay = LOTRDate.ShireReckoning.currentDay;
        float daytime = world.getWorldTime() % (long)LOTRTime.DAY_LENGTH;
        if (daytime < 0.25f) {
            --effectiveDay;
        }
        if (effectiveDay != currentNightNum) {
            currentNightNum = effectiveDay;
            dateRand.setSeed((long)currentNightNum * 35920558925051L + (long)currentNightNum + 83025820626792L);
            maxNorthTonight = -35000.0f;
            minNorthTonight = MathHelper.randomFloatClamp((Random)dateRand, (float)-20000.0f, (float)-15000.0f);
            float goSouth = dateRand.nextFloat();
            if (LOTRMod.isChristmas() || goSouth < 0.01f) {
                minNorthTonight += 15000.0f;
            } else if (goSouth < 0.1f) {
                minNorthTonight += 10000.0f;
            } else if (goSouth < 0.5f) {
                minNorthTonight += 5000.0f;
            }
            if (LOTRMod.isChristmas()) {
                minNorthTonight = 1000000.0f;
            }
            float appearChance = 0.5f;
            brightnessTonight = LOTRMod.isChristmas() || dateRand.nextFloat() < appearChance ? MathHelper.randomFloatClamp((Random)dateRand, (float)0.4f, (float)1.0f) : 0.0f;
        }
        rainingTickPrev = rainingTick;
        boolean raining = LOTRRenderNorthernLights.isRainLayerAt(viewer);
        if (raining) {
            if (rainingTick < 80) {
                ++rainingTick;
            }
        } else if (rainingTick > 0) {
            --rainingTick;
        }
        if (colorTopCurrent == null) {
            Color[] cs = LOTRRenderNorthernLights.generateColorSet();
            colorTopCurrent = cs[0].getColorComponents(null);
            colorMidCurrent = cs[1].getColorComponents(null);
            colorBottomCurrent = cs[2].getColorComponents(null);
        }
        if (timeUntilColorChange > 0) {
            --timeUntilColorChange;
        } else if (rand.nextInt(1200) == 0) {
            Color[] cs = LOTRRenderNorthernLights.generateColorSet();
            colorTopNext = cs[0].getColorComponents(null);
            colorMidNext = cs[1].getColorComponents(null);
            colorBottomNext = cs[2].getColorComponents(null);
            colorChangeTick = colorChangeTime = MathHelper.getRandomIntegerInRange((Random)rand, (int)100, (int)200);
            nightKingCheckTime = 0;
        }
        if (colorChangeTick > 0 && --colorChangeTick <= 0) {
            colorChangeTime = 0;
            colorTopCurrent = colorTopNext;
            colorMidCurrent = colorMidNext;
            colorBottomCurrent = colorBottomNext;
            colorTopNext = null;
            colorMidNext = null;
            colorBottomNext = null;
            timeUntilColorChange = MathHelper.getRandomIntegerInRange((Random)rand, (int)1200, (int)2400);
        }
        if (nightKingCheckTime > 0) {
            --nightKingCheckTime;
        } else {
            double range = 256.0;
            if (LOTRFixedStructures.UTUMNO_ENTRANCE.distanceSqTo(viewer) <= range * range) {
                atNightKing = true;
                timeUntilColorChange = 0;
                colorTopNext = new float[]{1.0f, 0.4f, 0.0f};
                colorMidNext = new float[]{1.0f, 0.0f, 0.0f};
                colorBottomNext = new float[]{1.0f, 0.0f, 0.3f};
                colorChangeTick = colorChangeTime = MathHelper.getRandomIntegerInRange((Random)rand, (int)100, (int)200);
            } else {
                atNightKing = false;
            }
            nightKingCheckTime = 200;
        }
        if (atNightKing) {
            if (nightKingChange < 200) {
                ++nightKingChange;
            }
        } else if (nightKingChange > 0) {
            --nightKingChange;
        }
        if (rand.nextInt(50) == 0) {
            freq = MathHelper.randomFloatClamp((Random)rand, (float)8.0f, (float)100.0f);
            speed = freq * 5.0E-4f;
            amp = MathHelper.randomFloatClamp((Random)rand, (float)0.05f, (float)0.3f);
            cycle = new AuroraCycle(freq, speed, amp);
            cycle.age = cycle.maxAge = MathHelper.getRandomIntegerInRange((Random)rand, (int)100, (int)400);
            waveOscillations.add(cycle);
        }
        if (!waveOscillations.isEmpty()) {
            HashSet<AuroraCycle> removes = new HashSet<AuroraCycle>();
            for (AuroraCycle c : waveOscillations) {
                c.update();
                if (c.age > 0) continue;
                removes.add(c);
            }
            waveOscillations.removeAll(removes);
        }
        if (rand.nextInt(120) == 0) {
            freq = MathHelper.randomFloatClamp((Random)rand, (float)30.0f, (float)150.0f);
            speed = freq * 0.002f;
            amp = MathHelper.randomFloatClamp((Random)rand, (float)0.05f, (float)0.5f);
            cycle = new AuroraCycle(freq, speed, amp);
            cycle.age = cycle.maxAge = MathHelper.getRandomIntegerInRange((Random)rand, (int)100, (int)400);
            glowOscillations.add(cycle);
        }
        if (rand.nextInt(300) == 0) {
            freq = MathHelper.randomFloatClamp((Random)rand, (float)400.0f, (float)500.0f);
            speed = freq * 0.004f;
            amp = MathHelper.randomFloatClamp((Random)rand, (float)0.1f, (float)0.2f);
            cycle = new AuroraCycle(freq, speed, amp);
            cycle.age = cycle.maxAge = MathHelper.getRandomIntegerInRange((Random)rand, (int)100, (int)200);
            glowOscillations.add(cycle);
        }
        if (!glowOscillations.isEmpty()) {
            HashSet<AuroraCycle> removes = new HashSet<AuroraCycle>();
            for (AuroraCycle c : glowOscillations) {
                c.update();
                if (c.age > 0) continue;
                removes.add(c);
            }
            glowOscillations.removeAll(removes);
        }
    }

    public static float waveEquation(Minecraft mc, float t, float tick, float renderTick) {
        float f = 0.0f;
        f += wave0.calc(t, tick);
        for (AuroraCycle c : waveOscillations) {
            f += c.calc(t, tick);
        }
        return f;
    }

    static {
        rainingChangeTime = 80;
        nightKingChangeTime = 200;
        rand = new Random();
        dateRand = new Random();
        wave0 = new AuroraCycle(4.0f, 0.01f, 0.9f);
        waveOscillations = new ArrayList<AuroraCycle>();
        glowOscillations = new ArrayList<AuroraCycle>();
        glow0 = new AuroraCycle(20.0f, 0.02f, 0.6f);
    }

    public static class AuroraCycle {
        public float freq;
        public float tickMultiplier;
        public float amp;
        public int age;
        public int maxAge = -1;
        public float ampModifier = 1.0f;

        public AuroraCycle(float f, float t, float a) {
            this.freq = f;
            this.tickMultiplier = t;
            this.amp = a;
        }

        public float calc(float t, float tick) {
            return MathHelper.cos((float)(t * this.freq + tick * this.tickMultiplier)) * this.amp * this.ampModifier;
        }

        public void update() {
            if (this.age >= 0) {
                --this.age;
                float a = (float)(this.maxAge - this.age) / (float)this.maxAge;
                this.ampModifier = Math.min(a, 1.0f - a);
            }
        }
    }

}

