/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.entity.npc.LOTREntityAngbandOrcArcher;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LOTREntityAngbandSpiderFire
extends LOTREntitySpiderBase {
    public LOTREntityAngbandSpiderFire(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    protected int getRandomSpiderType() {
        return VENOM_NONE;
    }

    @Override
    protected int getRandomSpiderScale() {
        return 1 + this.rand.nextInt(3);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
    }

    public int getTotalArmorValue() {
        return 8;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag && this.rand.nextInt(4) == 0) {
            this.dropItem(LOTRMod.mysteryWeb, 1);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);
        if (!this.worldObj.isRemote && flag) {
            entity.setFire(4);
        }
        return flag;
    }

    @Override
    public IEntityLivingData initCreatureForHire(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        return data;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (!this.worldObj.isRemote && this.rand.nextInt(3) == 0) {
            LOTREntityAngbandOrc rider = this.rand.nextBoolean() ? new LOTREntityAngbandOrcArcher(this.worldObj) : new LOTREntityAngbandOrc(this.worldObj);
            rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
            ((LOTREntityNPC)rider).onSpawnWithEgg(null);
            rider.isNPCPersistent = this.isNPCPersistent;
            this.worldObj.spawnEntityInWorld((Entity)rider);
            rider.mountEntity((Entity)this);
        }
        return data;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return null;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killMirkwoodSpider;
    }
}

