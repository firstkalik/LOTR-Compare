/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRConfig;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTREntityAIOrcSkirmish
extends LOTREntityAINearestAttackableTargetBasic {
    private LOTREntityOrc theOrc;

    public LOTREntityAIOrcSkirmish(LOTREntityOrc orc, boolean flag) {
        super(orc, LOTREntityOrc.class, 0, flag, null);
        this.theOrc = orc;
    }

    @Override
    public boolean shouldExecute() {
        if (!LOTRConfig.enableOrcSkirmish) {
            return false;
        }
        if (!this.canOrcSkirmish((EntityLivingBase)this.theOrc)) {
            return false;
        }
        if (!this.theOrc.isOrcSkirmishing()) {
            int chance = 20000;
            List nearbyOrcs = this.theOrc.worldObj.getEntitiesWithinAABB(LOTREntityOrc.class, this.theOrc.boundingBox.expand(16.0, 8.0, 16.0));
            for (Object nearbyOrc : nearbyOrcs) {
                LOTREntityOrc orc = (LOTREntityOrc)nearbyOrc;
                if (!orc.isOrcSkirmishing()) continue;
                chance /= 10;
            }
            if (chance < 40) {
                chance = 40;
            }
            if (this.theOrc.getRNG().nextInt(chance) != 0) {
                return false;
            }
        }
        return super.shouldExecute();
    }

    @Override
    protected boolean isSuitableTarget(EntityLivingBase entity, boolean flag) {
        return this.canOrcSkirmish(entity) && super.isSuitableTarget(entity, flag);
    }

    private boolean canOrcSkirmish(EntityLivingBase entity) {
        if (entity instanceof LOTREntityOrc) {
            LOTREntityOrc orc = (LOTREntityOrc)entity;
            return !orc.isTrader() && !orc.hiredNPCInfo.isActive && orc.ridingEntity == null && orc.canOrcSkirmish();
        }
        return false;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.theOrc.setOrcSkirmishing();
    }
}

