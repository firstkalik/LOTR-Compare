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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityPebble
extends EntityThrowable {
    private boolean isSling = false;

    public LOTREntityPebble(World world) {
        super(world);
    }

    public LOTREntityPebble(World world, EntityLivingBase entityliving) {
        super(world, entityliving);
    }

    public LOTREntityPebble(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    public LOTREntityPebble setSling() {
        this.isSling = true;
        return this;
    }

    public boolean isSling() {
        return this.isSling;
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Sling", this.isSling);
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.isSling = nbt.getBoolean("Sling");
    }

    public void onUpdate() {
        super.onUpdate();
        float speed = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
        if (speed > 0.1f && this.motionY < 0.0 && this.isInWater()) {
            float factor = MathHelper.randomFloatClamp((Random)this.rand, (float)0.4f, (float)0.8f);
            this.motionX *= (double)factor;
            this.motionZ *= (double)factor;
            this.motionY += (double)factor;
        }
    }

    protected void onImpact(MovingObjectPosition m) {
        if (m.entityHit != null) {
            float damage = this.isSling ? 2.0f : 1.0f;
            m.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), damage);
        }
        if (!this.worldObj.isRemote) {
            this.entityDropItem(new ItemStack(LOTRMod.pebble), 0.0f);
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

