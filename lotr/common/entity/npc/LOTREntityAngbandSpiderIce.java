/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.item.EntityItem
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
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LOTREntityAngbandSpiderIce
extends LOTREntitySpiderBase {
    public LOTREntityAngbandSpiderIce(World world) {
        super(world);
    }

    public int getTotalArmorValue() {
        return 8;
    }

    @Override
    protected int getRandomSpiderScale() {
        return 1 + this.rand.nextInt(3);
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    protected boolean canRideSpider() {
        return true;
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
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag && this.rand.nextInt(4) == 0) {
            this.dropItem(LOTRMod.mysteryWeb, 1);
        }
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killIceSpider;
    }

    @Override
    protected int getRandomSpiderType() {
        return VENOM_SLOWNESS;
    }
}

