/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityExplodeFX
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.util.Random;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityWaveFX
extends EntityExplodeFX {
    private final float initScale;
    private final float finalScale;
    private final double origMotionX;
    private final double origMotionZ;

    public LOTREntityWaveFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        super(world, d, d1, d2, d3, d4, d5);
        this.origMotionX = this.motionX = d3;
        this.motionY = d4;
        this.origMotionZ = this.motionZ = d5;
        this.initScale = this.particleScale = 1.0f + this.rand.nextFloat() * 4.0f;
        this.finalScale = this.initScale * MathHelper.randomFloatClamp((Random)this.rand, (float)1.2f, (float)2.0f);
        this.particleMaxAge = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)60, (int)80);
        this.particleAlpha = 0.0f;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
        if (this.particleAlpha < 1.0f) {
            this.particleAlpha += 0.02f;
            this.particleAlpha = Math.min(this.particleAlpha, 1.0f);
        }
        this.particleScale = this.initScale + (float)this.particleAge / (float)this.particleMaxAge * (this.finalScale - this.initScale);
        this.setParticleTextureIndex((this.particleMaxAge - this.particleAge) % 8);
        this.handleWaterMovement();
        if (this.inWater) {
            this.motionY += (double)MathHelper.randomFloatClamp((Random)this.rand, (float)0.04f, (float)0.1f);
            this.motionX = this.origMotionX;
            this.motionZ = this.origMotionZ;
        } else {
            this.motionY -= 0.02;
            this.motionX *= 0.98;
            this.motionZ *= 0.98;
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionY *= 0.995;
    }
}

