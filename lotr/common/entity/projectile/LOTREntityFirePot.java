/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockPlate;
import lotr.common.block.LOTRBlockRhunFireJar;
import lotr.common.entity.animal.LOTREntityBird;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityFirePot
extends EntityThrowable {
    public LOTREntityFirePot(World world) {
        super(world);
    }

    public LOTREntityFirePot(World world, EntityLivingBase entityliving) {
        super(world, entityliving);
    }

    public LOTREntityFirePot(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    protected void onImpact(MovingObjectPosition m) {
        if (!this.worldObj.isRemote) {
            Block block;
            EntityLivingBase thrower = this.getThrower();
            Entity hitEntity = m.entityHit;
            double range = 6.0;
            List entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(range, range, range));
            if (hitEntity instanceof EntityLivingBase && !entities.contains((Object)hitEntity)) {
                entities.add(hitEntity);
            }
            for (Object obj : entities) {
                EntityLivingBase entity = (EntityLivingBase)obj;
                float damage = 1.0f;
                if (entity == hitEntity) {
                    damage = 3.0f;
                }
                if (entity == hitEntity && thrower instanceof EntityPlayer && hitEntity instanceof LOTREntityBird && !((LOTREntityBird)hitEntity).isBirdStill()) {
                    LOTRLevelData.getData((EntityPlayer)thrower).addAchievement(LOTRAchievement.hitBirdFirePot);
                }
                if (!entity.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)thrower), damage)) continue;
                int fire = 2 + this.rand.nextInt(3);
                if (entity == hitEntity) {
                    fire += 2 + this.rand.nextInt(3);
                }
                entity.setFire(fire);
            }
            if (m.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && (block = this.worldObj.getBlock(m.blockX, m.blockY, m.blockZ)) instanceof LOTRBlockRhunFireJar) {
                ((LOTRBlockRhunFireJar)block).explode(this.worldObj, m.blockX, m.blockY, m.blockZ);
            }
            this.worldObj.playSoundAtEntity((Entity)this, LOTRBlockPlate.soundTypePlate.getBreakSound(), 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.setDead();
        }
        for (int i = 0; i < 8; ++i) {
            double d = this.posX + (double)MathHelper.randomFloatClamp((Random)this.rand, (float)-0.25f, (float)0.25f);
            double d1 = this.posY + (double)MathHelper.randomFloatClamp((Random)this.rand, (float)-0.25f, (float)0.25f);
            double d2 = this.posZ + (double)MathHelper.randomFloatClamp((Random)this.rand, (float)-0.25f, (float)0.25f);
            this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock((Block)LOTRMod.rhunFireJar) + "_0", d, d1, d2, 0.0, 0.0, 0.0);
        }
        for (int l = 0; l < 16; ++l) {
            String s = this.rand.nextBoolean() ? "flame" : "smoke";
            double d = this.posX;
            double d1 = this.posY;
            double d2 = this.posZ;
            double d3 = MathHelper.randomFloatClamp((Random)this.rand, (float)-0.1f, (float)0.1f);
            double d4 = MathHelper.randomFloatClamp((Random)this.rand, (float)0.2f, (float)0.3f);
            double d5 = MathHelper.randomFloatClamp((Random)this.rand, (float)-0.1f, (float)0.1f);
            this.worldObj.spawnParticle(s, d, d1, d2, d3, d4, d5);
        }
    }

    protected float func_70182_d() {
        return 1.2f;
    }

    protected float getGravityVelocity() {
        return 0.04f;
    }
}

