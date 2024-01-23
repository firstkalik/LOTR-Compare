/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityRiverWaterFX
extends EntitySpellParticleFX {
    public LOTREntityRiverWaterFX(World world, double d, double d1, double d2, double d3, double d4, double d5, int color) {
        super(world, d, d1, d2, d3, d4, d5);
        Color c = new Color(color);
        float[] rgb = c.getColorComponents(null);
        this.particleRed = MathHelper.randomFloatClamp((Random)this.rand, (float)(rgb[0] - 0.3f), (float)(rgb[0] + 0.3f));
        this.particleGreen = MathHelper.randomFloatClamp((Random)this.rand, (float)(rgb[1] - 0.3f), (float)(rgb[1] + 0.3f));
        this.particleBlue = MathHelper.randomFloatClamp((Random)this.rand, (float)(rgb[2] - 0.3f), (float)(rgb[2] + 0.3f));
        this.particleRed = MathHelper.clamp_float((float)this.particleRed, (float)0.0f, (float)1.0f);
        this.particleGreen = MathHelper.clamp_float((float)this.particleGreen, (float)0.0f, (float)1.0f);
        this.particleBlue = MathHelper.clamp_float((float)this.particleBlue, (float)0.0f, (float)1.0f);
        this.particleScale = 0.5f + this.rand.nextFloat() * 0.5f;
        this.particleMaxAge = 20 + this.rand.nextInt(20);
        this.motionX = d3;
        this.motionY = d4;
        this.motionZ = d5;
    }

    public void onUpdate() {
        super.onUpdate();
        this.particleAlpha = 0.5f + 0.5f * ((float)this.particleAge / (float)this.particleMaxAge);
    }
}

