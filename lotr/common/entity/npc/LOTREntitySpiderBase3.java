/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.entity.ai.LOTREntityAIWargBombardierAttack5;
import lotr.common.entity.npc.LOTREntitySpiderBase5;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public abstract class LOTREntitySpiderBase3
extends LOTREntitySpiderBase5 {
    public LOTREntitySpiderBase3(World world) {
        super(world);
    }

    @Override
    public EntityAIBase getWargAttackAI() {
        return new LOTREntityAIWargBombardierAttack5(this, 1.7);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(41, (Object)35);
        this.dataWatcher.addObject(42, (Object)1);
    }

    public int getBombFuse() {
        return this.dataWatcher.getWatchableObjectByte(41);
    }

    public void setBombFuse(int i) {
        this.dataWatcher.updateObject(41, (Object)((byte)i));
    }

    public int getBombStrengthLevel() {
        return this.dataWatcher.getWatchableObjectByte(42);
    }

    public void setBombStrengthLevel(int i) {
        this.dataWatcher.updateObject(42, (Object)((byte)i));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setByte("BombFuse", (byte)this.getBombFuse());
        nbt.setByte("BombStrengthLevel", (byte)this.getBombStrengthLevel());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setBombFuse(nbt.getByte("BombFuse"));
        this.setBombStrengthLevel(nbt.getByte("BombStrengthLevel"));
    }

    @Override
    protected boolean canRideSpider() {
        return false;
    }

    @Override
    public boolean isMountSaddled() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getBombFuse() == 0) {
            float explosionPower = 10.0f;
            this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, explosionPower, true);
            this.setBombFuse(35);
            int minDuration = 100;
            int maxDuration = 260;
            int effectDuration = minDuration + this.worldObj.rand.nextInt(maxDuration - minDuration + 1);
            int effectAmplifier = 0;
            double radius = 10.0;
            List entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(radius, radius, radius));
            for (EntityLivingBase entity : entities) {
                entity.addPotionEffect(new PotionEffect(Potion.wither.id, effectDuration, effectAmplifier));
            }
        }
    }

    @Override
    public void setAttackTarget(EntityLivingBase target) {
        super.setAttackTarget(target);
        if (target != null) {
            this.worldObj.playSoundAtEntity((Entity)this, "game.tnt.primed", 1.0f, 1.0f);
        }
    }
}

