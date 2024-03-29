/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.awt.Color;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityElvenGlowFX
extends EntityFlameFX {
    public LOTREntityElvenGlowFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        super(world, d, d1, d2, d3, d4, d5);
        this.particleRed = 0.15f;
        this.particleGreen = 0.9f;
        this.particleBlue = 1.0f;
        this.particleMaxAge *= 3;
    }

    public LOTREntityElvenGlowFX setElvenGlowColor(int color) {
        float[] rgb = new Color(color).getColorComponents(null);
        this.particleRed = rgb[0];
        this.particleGreen = rgb[1];
        this.particleBlue = rgb[2];
        return this;
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float minU = 0.25f;
        float maxU = minU + 0.25f;
        float minV = 0.125f;
        float maxV = minV + 0.25f;
        float var12 = 0.25f + 0.002f * MathHelper.sin((float)(((float)this.particleAge + f - 1.0f) * 0.25f * 3.1415927f));
        this.particleAlpha = 0.9f - ((float)this.particleAge + f - 1.0f) * 0.02f;
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)maxU, (double)maxV);
        tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)maxU, (double)minV);
        tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)minU, (double)minV);
        tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)minU, (double)maxV);
    }
}

