/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.util.Random;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityDeadMarshFace
extends EntityFX {
    public float faceAlpha;

    public LOTREntityDeadMarshFace(World world, double d, double d1, double d2) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.particleMaxAge = 120 + this.rand.nextInt(120);
        this.rotationYaw = world.rand.nextFloat() * 360.0f;
        this.rotationPitch = -60.0f + world.rand.nextFloat() * 120.0f;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.particleAge;
        this.faceAlpha = MathHelper.sin((float)((float)this.particleAge / (float)this.particleMaxAge * 3.1415927f));
        if (this.particleAge > this.particleMaxAge) {
            this.setDead();
        }
    }
}

