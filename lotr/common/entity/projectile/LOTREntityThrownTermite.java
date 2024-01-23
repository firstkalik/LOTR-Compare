/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntityThrownTermite
extends EntityThrowable {
    public LOTREntityThrownTermite(World world) {
        super(world);
    }

    public LOTREntityThrownTermite(World world, EntityLivingBase throwingEntity) {
        super(world, throwingEntity);
    }

    public LOTREntityThrownTermite(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    protected void onImpact(MovingObjectPosition movingobjectposition) {
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 2.0f, true);
            this.setDead();
        }
    }
}

