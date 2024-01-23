/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntitySauron;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemSauronMace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTREntityAISauronUseMace
extends EntityAIBase {
    private LOTREntitySauron theSauron;
    private int attackTick = 0;
    private World theWorld;

    public LOTREntityAISauronUseMace(LOTREntitySauron sauron) {
        this.theSauron = sauron;
        this.theWorld = this.theSauron.worldObj;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        int targets = 0;
        List nearbyEntities = this.theWorld.getEntitiesWithinAABB(EntityLivingBase.class, this.theSauron.boundingBox.expand(6.0, 6.0, 6.0));
        for (int i = 0; i < nearbyEntities.size(); ++i) {
            EntityLivingBase entity = (EntityLivingBase)nearbyEntities.get(i);
            if (!entity.isEntityAlive()) continue;
            if (entity instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)entity;
                if (entityplayer.capabilities.isCreativeMode || !(LOTRLevelData.getData(entityplayer).getAlignment(this.theSauron.getFaction()) < 0.0f) && this.theSauron.getAttackTarget() != entityplayer) continue;
                ++targets;
                continue;
            }
            if (this.theSauron.getFaction().isBadRelation(LOTRMod.getNPCFaction((Entity)entity))) {
                ++targets;
                continue;
            }
            if (this.theSauron.getAttackTarget() != entity && (!(entity instanceof EntityLiving) || ((EntityLiving)entity).getAttackTarget() != this.theSauron)) continue;
            ++targets;
        }
        if (targets >= 4) {
            return true;
        }
        return targets > 0 && this.theSauron.getRNG().nextInt(100) == 0;
    }

    public boolean continueExecuting() {
        return this.theSauron.getIsUsingMace();
    }

    public void startExecuting() {
        this.attackTick = 40;
        this.theSauron.setIsUsingMace(true);
    }

    public void resetTask() {
        this.attackTick = 40;
        this.theSauron.setIsUsingMace(false);
    }

    public void updateTask() {
        this.attackTick = Math.max(this.attackTick - 1, 0);
        if (this.attackTick <= 0) {
            this.attackTick = 40;
            LOTRItemSauronMace.useSauronMace(this.theSauron.getEquipmentInSlot(0), this.theWorld, (EntityLivingBase)this.theSauron);
            this.theSauron.setIsUsingMace(false);
        }
    }
}

