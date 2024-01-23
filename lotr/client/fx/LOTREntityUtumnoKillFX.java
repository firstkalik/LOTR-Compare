/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityUtumnoKillFX
extends EntityFlameFX {
    private double paramMotionX;
    private double paramMotionY;
    private double paramMotionZ;

    public LOTREntityUtumnoKillFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        super(world, d, d1, d2, d3, d4, d5);
        this.paramMotionX = this.motionX = d3;
        this.paramMotionY = this.motionY = d4;
        this.paramMotionZ = this.motionZ = d5;
        this.setParticleTextureIndex(144 + this.rand.nextInt(3));
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionX = this.paramMotionX;
        this.motionY = this.paramMotionY;
        this.motionZ = this.paramMotionZ;
        Block block = this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posY), MathHelper.floor_double((double)this.posZ));
        if (block == LOTRMod.utumnoReturnPortalBase) {
            this.setDead();
        }
    }
}

