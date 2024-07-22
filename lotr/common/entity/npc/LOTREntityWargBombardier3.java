/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.ai.LOTREntityAIWargBombardierAttack3;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class LOTREntityWargBombardier3
extends LOTREntitySpiderBase {
    public LOTREntityWargBombardier3(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(21, (Object)35);
        this.dataWatcher.addObject(22, (Object)8);
    }

    public int getBombFuse() {
        return this.dataWatcher.getWatchableObjectByte(21);
    }

    public void setBombFuse(int i) {
        this.dataWatcher.updateObject(21, (Object)((byte)i));
    }

    public int getBombStrengthLevel() {
        return this.dataWatcher.getWatchableObjectByte(22);
    }

    public void setBombStrengthLevel(int i) {
        this.dataWatcher.updateObject(22, (Object)((byte)i));
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
    public boolean isMountSaddled() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.getBombFuse() < 35) {
            this.worldObj.spawnParticle("lava", this.posX, this.posY + 2.2, this.posZ, 0.0, 0.0, 0.0);
            this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 2.2, this.posZ, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void setAttackTarget(EntityLivingBase target) {
        super.setAttackTarget(target);
        if (target != null) {
            this.worldObj.playSoundAtEntity((Entity)this, "game.tnt.primed", 1.0f, 1.0f);
        }
    }

    public EntityAIBase getWargAttackAI() {
        return new LOTREntityAIWargBombardierAttack3(this, 1.7);
    }
}

