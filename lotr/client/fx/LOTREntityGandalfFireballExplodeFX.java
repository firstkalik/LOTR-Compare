/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFireworkOverlayFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFireworkOverlayFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class LOTREntityGandalfFireballExplodeFX
extends EntityFireworkOverlayFX {
    public LOTREntityGandalfFireballExplodeFX(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
        this.particleRed = 0.33f;
        this.particleGreen = 1.0f;
        this.particleBlue = 1.0f;
        this.particleMaxAge = 32;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBrightnessForRender(float f) {
        return 15728880;
    }

    public float getBrightness(float f) {
        return 1.0f;
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float var8 = 0.25f;
        float var9 = var8 + 0.25f;
        float var10 = 0.125f;
        float var11 = var10 + 0.25f;
        float var12 = 16.0f - (float)this.particleAge * 0.2f;
        this.particleAlpha = 0.9f - ((float)this.particleAge + f - 1.0f) * 0.15f;
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }
}

