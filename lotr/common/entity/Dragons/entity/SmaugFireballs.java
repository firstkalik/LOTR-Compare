/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityLargeFireball
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.Dragons.entity;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRLevelData;
import lotr.common.block.LOTRBlockLeavesBase;
import lotr.common.block.LOTRBlockWoodBase;
import lotr.common.entity.Dragons.entity.LOTREntityDragonAlpha;
import lotr.common.entity.Dragons.entity.LOTREntityDragonAnkalagon;
import lotr.common.entity.Dragons.entity.LOTREntityDragonHunter;
import lotr.common.entity.Dragons.entity.LOTREntityDragonScout;
import lotr.common.entity.Dragons.entity.LOTREntityDragonSmaug;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class SmaugFireballs
extends EntityLargeFireball {
    EntityDragon shooter;

    public SmaugFireballs(World par1World) {
        super(par1World);
    }

    public SmaugFireballs(World par1World, EntityDragon par2EntityLivingBase, double par3, double par5, double par7) {
        super(par1World, (EntityLivingBase)par2EntityLivingBase, par3, par5, par7);
        this.shooter = par2EntityLivingBase;
    }

    public SmaugFireballs(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
    }

    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        if (!this.worldObj.isRemote) {
            boolean causesFire = !LOTRBannerProtection.isProtected(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, LOTRBannerProtection.anyBanner(), false);
            boolean damagesBlocks = false;
            Explosion explosion = this.worldObj.newExplosion((Entity)this, this.posX, this.posY, this.posZ, 2.8f, causesFire, damagesBlocks);
            float radius = explosion.explosionSize;
            if (causesFire) {
                int minX = (int)(this.posX - (double)radius);
                int maxX = (int)(this.posX + (double)radius);
                int minY = (int)(this.posY - (double)radius);
                int maxY = (int)(this.posY + (double)radius);
                int minZ = (int)(this.posZ - (double)radius);
                int maxZ = (int)(this.posZ + (double)radius);
                for (int x = minX; x <= maxX; ++x) {
                    for (int y = minY; y <= maxY; ++y) {
                        for (int z = minZ; z <= maxZ; ++z) {
                            Block block = this.worldObj.getBlock(x, y, z);
                            if (!(block instanceof LOTRBlockLeavesBase) && !(block instanceof LOTRBlockWoodBase)) continue;
                            this.worldObj.setBlockToAir(x, y, z);
                        }
                    }
                }
            }
            List entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox((double)(this.posX - (double)radius), (double)(this.posY - (double)radius), (double)(this.posZ - (double)radius), (double)(this.posX + (double)radius), (double)(this.posY + (double)radius), (double)(this.posZ + (double)radius)));
            for (Object obj : entities) {
                EntityLivingBase entity;
                if (!(obj instanceof EntityLivingBase) || !((entity = (EntityLivingBase)obj) instanceof EntityPlayer)) continue;
                EntityPlayer player = (EntityPlayer)entity;
                LOTRLevelData.getData(player).addAchievement(LOTRAchievement.playerDeathDragonFire);
            }
            this.setDead();
        }
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        return false;
    }

    public void applyEntityCollision(Entity par1Entity) {
        if (par1Entity == this.shooter) {
            return;
        }
        if (par1Entity instanceof LOTREntityDragonScout || par1Entity instanceof LOTREntityDragonHunter || par1Entity instanceof LOTREntityDragonAlpha || par1Entity instanceof LOTREntityDragonAnkalagon || par1Entity instanceof LOTREntityDragonSmaug) {
            return;
        }
        super.applyEntityCollision(par1Entity);
    }

    protected float getMotionFactor() {
        return 1.5f;
    }
}

