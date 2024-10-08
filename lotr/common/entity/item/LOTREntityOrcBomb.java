/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityTNTPrimed
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockOrcBomb;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntityOrcBomb
extends EntityTNTPrimed {
    public int orcBombFuse;
    public boolean droppedByPlayer;
    public boolean droppedByHiredUnit;
    public boolean droppedTargetingPlayer;

    public LOTREntityOrcBomb(World world) {
        super(world);
    }

    public LOTREntityOrcBomb(World world, double d, double d1, double d2, EntityLivingBase entity) {
        super(world, d, d1, d2, entity);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    public int getBombStrengthLevel() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }

    public void setBombStrengthLevel(int i) {
        this.dataWatcher.updateObject(16, (Object)((byte)i));
        this.orcBombFuse = 40 + LOTRBlockOrcBomb.getBombStrengthLevel(i) * 20;
    }

    public void setFuseFromExplosion() {
        this.orcBombFuse = this.worldObj.rand.nextInt(this.orcBombFuse / 4) + this.orcBombFuse / 8;
    }

    public void setFuseFromHiredUnit() {
        LOTRBlockOrcBomb.getBombStrengthLevel(this.getBombStrengthLevel());
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.04;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.98;
        this.motionY *= 0.98;
        this.motionZ *= 0.98;
        if (this.onGround) {
            this.motionX *= 0.7;
            this.motionZ *= 0.7;
            this.motionY *= -0.5;
        }
        --this.orcBombFuse;
        if (this.orcBombFuse <= 0 && !this.worldObj.isRemote) {
            this.setDead();
            this.explodeOrcBomb();
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.7, this.posZ, 0.0, 0.0, 0.0);
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("DroppedByPlayer", this.droppedByPlayer);
        nbt.setBoolean("DroppedByHiredUnit", this.droppedByHiredUnit);
        nbt.setBoolean("DroppedTargetingPlayer", this.droppedTargetingPlayer);
        nbt.setInteger("BombStrengthLevel", this.getBombStrengthLevel());
        nbt.setInteger("OrcBombFuse", this.orcBombFuse);
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.droppedByPlayer = nbt.getBoolean("DroppedByPlayer");
        this.droppedByHiredUnit = nbt.getBoolean("DroppedByHiredUnit");
        this.droppedTargetingPlayer = nbt.getBoolean("DroppedTargetingPlayer");
        this.setBombStrengthLevel(nbt.getInteger("BombStrengthLevel"));
        this.orcBombFuse = nbt.getInteger("OrcBombFuse");
    }

    private void explodeOrcBomb() {
        boolean doTerrainDamage = false;
        if (this.droppedByPlayer) {
            doTerrainDamage = true;
        } else if (this.droppedByHiredUnit || this.droppedTargetingPlayer) {
            doTerrainDamage = LOTRMod.canGrief(this.worldObj);
        }
        int meta = this.getBombStrengthLevel();
        int strength = LOTRBlockOrcBomb.getBombStrengthLevel(meta);
        boolean fire = LOTRBlockOrcBomb.isFireBomb(meta);
        this.worldObj.newExplosion((Entity)this, this.posX, this.posY, this.posZ, (float)(strength + 1) * 4.0f, fire, doTerrainDamage);
    }
}

