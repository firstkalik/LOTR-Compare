/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.block;

import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockRope;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTRBlockHithlainRope
extends LOTRBlockRope {
    public LOTRBlockHithlainRope() {
        super(true);
        this.setLightLevel(0.375f);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isOnLadder()) {
            LOTRFaction ropeFaction = LOTRFaction.LOTHLORIEN;
            boolean harm = false;
            harm = entity instanceof EntityPlayer ? LOTRLevelData.getData((EntityPlayer)entity).getAlignment(ropeFaction) < 0.0f : LOTRMod.getNPCFaction(entity).isBadRelation(ropeFaction);
            if (harm) {
                entity.attackEntityFrom(DamageSource.magic, 1.0f);
            }
        }
    }
}

