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

import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.fac.LOTRFaction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRAINearestAttackableTargetOrocarni
extends LOTREntityAINearestAttackableTargetBasic {
    public LOTRAINearestAttackableTargetOrocarni(EntityCreature entity, Class targetClass, int chance, boolean flag) {
        super(entity, targetClass, chance, flag);
    }

    public LOTRAINearestAttackableTargetOrocarni(EntityCreature entity, Class targetClass, int chance, boolean flag, IEntitySelector selector) {
        super(entity, targetClass, chance, flag, selector);
    }

    @Override
    protected boolean isPlayerSuitableAlignmentTarget(EntityPlayer entityplayer) {
        float alignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTRMod.getNPCFaction((Entity)this.taskOwner));
        if (alignment >= 100.0f) {
            return false;
        }
        if (alignment <= 100.0f) {
            return true;
        }
        return super.isPlayerSuitableAlignmentTarget(entityplayer);
    }
}

