/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntitySmokeRing
extends EntityThrowable {
    public static int MAX_AGE = 300;
    public int renderSmokeAge = -1;
    public int prevRenderSmokeAge = -1;

    public LOTREntitySmokeRing(World world) {
        super(world);
    }

    public LOTREntitySmokeRing(World world, EntityLivingBase entityliving) {
        super(world, entityliving);
    }

    public LOTREntitySmokeRing(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)0);
    }

    public int getSmokeAge() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setSmokeAge(int age) {
        this.dataWatcher.updateObject(16, (Object)age);
    }

    public int getSmokeColour() {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    public void setSmokeColour(int colour) {
        this.dataWatcher.updateObject(17, (Object)((byte)colour));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("SmokeAge", this.getSmokeAge());
        nbt.setInteger("SmokeColour", this.getSmokeColour());
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setSmokeAge(nbt.getInteger("SmokeAge"));
        this.setSmokeColour(nbt.getInteger("SmokeColour"));
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.isInWater() && !this.worldObj.isRemote) {
            this.setDead();
        }
        this.prevRenderSmokeAge = this.renderSmokeAge == -1 ? (this.renderSmokeAge = this.getSmokeAge()) : this.renderSmokeAge;
        if (!this.worldObj.isRemote) {
            this.setSmokeAge(this.getSmokeAge() + 1);
            if (this.getSmokeAge() >= MAX_AGE) {
                this.setDead();
            }
        }
        this.renderSmokeAge = this.getSmokeAge();
    }

    public float getRenderSmokeAge(float f) {
        float smokeAge = (float)this.prevRenderSmokeAge + (float)(this.renderSmokeAge - this.prevRenderSmokeAge) * f;
        return smokeAge / (float)MAX_AGE;
    }

    protected void onImpact(MovingObjectPosition m) {
        if (m.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && m.entityHit == this.getThrower()) {
            return;
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }

    protected float func_70182_d() {
        return 0.1f;
    }

    protected float getGravityVelocity() {
        return 0.0f;
    }
}

