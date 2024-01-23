/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.npc.LOTREntityWoodElf;
import lotr.common.fac.LOTRFaction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;

public class LOTREntityAINearestAttackableTargetWoodElf
extends LOTREntityAINearestAttackableTargetBasic {
    public LOTREntityAINearestAttackableTargetWoodElf(EntityCreature entity, Class targetClass, int chance, boolean flag) {
        super(entity, targetClass, chance, flag);
    }

    public LOTREntityAINearestAttackableTargetWoodElf(EntityCreature entity, Class targetClass, int chance, boolean flag, IEntitySelector selector) {
        super(entity, targetClass, chance, flag, selector);
    }

    @Override
    protected boolean isPlayerSuitableAlignmentTarget(EntityPlayer entityplayer) {
        float alignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTRMod.getNPCFaction((Entity)this.taskOwner));
        if (alignment >= LOTREntityWoodElf.getWoodlandTrustLevel()) {
            return false;
        }
        if (alignment >= 0.0f) {
            int chance = Math.round(alignment * 20.0f);
            chance = Math.max(chance, 1);
            return this.taskOwner.getRNG().nextInt(chance) == 0;
        }
        return super.isPlayerSuitableAlignmentTarget(entityplayer);
    }
}

