/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRModBlock;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTRBlockrottenfleshBlock2
extends LOTRModBlock {
    public LOTRBlockrottenfleshBlock2(int lightLevel) {
        super(LOTRModBlock.materialMagma1);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
    }

    public LOTRBlockrottenfleshBlock2() {
        this(3);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.125f;
        return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)(x + 1), (double)((float)(y + 1) - f), (double)(z + 1));
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        EntityLivingBase living;
        super.onEntityCollidedWithBlock(world, i, j, k, entity);
        if (!world.isRemote && entity instanceof EntityLivingBase && this.isEntityVulnerable(living = (EntityLivingBase)entity)) {
            int dur = 100;
            living.addPotionEffect(new PotionEffect(Potion.blindness.id, dur * 2));
        }
    }

    private boolean isEntityVulnerable(EntityLivingBase entity) {
        if (LOTRMod.getNPCFaction((Entity)entity) == LOTRFaction.MORDOR) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            if (entityplayer.capabilities.isCreativeMode) {
                return false;
            }
            float alignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.MORDOR);
            float max = 250.0f;
            if (alignment >= 250.0f) {
                return false;
            }
            if (alignment > 0.0f) {
                float f = alignment / max;
                f = 1.0f - f;
                return entity.getRNG().nextFloat() < f;
            }
            return true;
        }
        return true;
    }
}

