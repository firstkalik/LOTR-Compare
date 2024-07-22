/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketWeaponFX;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityGandalfFireball
extends EntityThrowable {
    public int animationTick;

    public LOTREntityGandalfFireball(World world) {
        super(world);
    }

    public LOTREntityGandalfFireball(World world, EntityLivingBase entityliving) {
        super(world, entityliving);
    }

    public LOTREntityGandalfFireball(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    public int getFireballAge() {
        return this.dataWatcher.getWatchableObjectShort(16);
    }

    public void setFireballAge(int age) {
        this.dataWatcher.updateObject(16, (Object)((short)age));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("FireballAge", this.getFireballAge());
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setFireballAge(nbt.getInteger("FireballAge"));
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted % 5 == 0) {
            ++this.animationTick;
            if (this.animationTick >= 4) {
                this.animationTick = 0;
            }
        }
        if (!this.worldObj.isRemote) {
            this.setFireballAge(this.getFireballAge() + 1);
            if (this.getFireballAge() >= 200) {
                this.explode(null);
            }
        }
    }

    protected void onImpact(MovingObjectPosition m) {
        if (!this.worldObj.isRemote) {
            Entity entity;
            if (m.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                this.explode(null);
            } else if (m.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && this.isEntityVulnerable(entity = m.entityHit)) {
                this.explode(entity);
            }
        }
    }

    private void explode(Entity target) {
        List entities;
        if (this.worldObj.isRemote) {
            return;
        }
        this.worldObj.playSoundAtEntity((Entity)this, "lotr:item.gandalfFireball", 4.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        LOTRPacketWeaponFX packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.FIREBALL_GANDALF_WHITE, (Entity)this);
        LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)this, 64.0));
        if (target != null && this.isEntityVulnerable(target)) {
            target.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.getThrower()), 10.0f);
        }
        if (!(entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(6.0, 6.0, 6.0))).isEmpty()) {
            for (Object entitie : entities) {
                float f;
                EntityLivingBase entity = (EntityLivingBase)entitie;
                if (entity == target || !this.isEntityVulnerable((Entity)entity)) continue;
                float damage = 500.0f - this.getDistanceToEntity((Entity)entity) * 0.5f;
                if (f <= 0.0f) continue;
                entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.getThrower()), damage);
            }
        }
        this.setDead();
    }

    private boolean isEntityVulnerable(Entity entity) {
        if (entity == this.getThrower()) {
            return false;
        }
        if (!(entity instanceof EntityLivingBase)) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.HIGH_ELF) < 0.0f;
        }
        return !LOTRFaction.HIGH_ELF.isGoodRelation(LOTRMod.getNPCFaction(entity));
    }

    protected float func_70182_d() {
        return 1.5f;
    }

    protected float getGravityVelocity() {
        return 0.0f;
    }
}

