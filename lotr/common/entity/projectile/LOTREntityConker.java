/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import lotr.common.LOTRMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityConker
extends EntityThrowable {
    public LOTREntityConker(World world) {
        super(world);
    }

    public LOTREntityConker(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public LOTREntityConker(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    protected void onImpact(MovingObjectPosition m) {
        if (m.entityHit != null) {
            m.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 1.0f);
        }
        if (!this.worldObj.isRemote) {
            this.entityDropItem(new ItemStack(LOTRMod.chestnut), 0.0f);
            this.setDead();
        }
    }

    protected float func_70182_d() {
        return 1.0f;
    }

    protected float getGravityVelocity() {
        return 0.04f;
    }
}

