/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityMordorOrcArcher;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.world.World;

public class LOTREntityMordorSpider
extends LOTREntitySpiderBase {
    public LOTREntityMordorSpider(World world) {
        super(world);
    }

    @Override
    protected int getRandomSpiderScale() {
        return 2 + this.rand.nextInt(3);
    }

    @Override
    protected int getRandomSpiderType() {
        return VENOM_POISON;
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
            LOTREntityMordorOrc rider = this.rand.nextBoolean() ? new LOTREntityMordorOrcArcher(this.worldObj) : new LOTREntityMordorOrc(this.worldObj);
            rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
            ((LOTREntityNPC)rider).onSpawnWithEgg(null);
            rider.isNPCPersistent = this.isNPCPersistent;
            this.worldObj.spawnEntityInWorld((Entity)rider);
            rider.mountEntity((Entity)this);
        }
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.MORDOR;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killMordorSpider;
    }
}

