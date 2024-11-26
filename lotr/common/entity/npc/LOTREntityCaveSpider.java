/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTREntityCaveSpider
extends LOTREntitySpiderBase {
    public LOTREntityCaveSpider(World world) {
        super(world);
        this.isImmuneToFire = false;
    }

    @Override
    protected int getRandomSpiderType() {
        return this.rand.nextBoolean() ? 0 : 1;
    }

    @Override
    protected int getRandomSpiderScale() {
        return 0 + this.rand.nextInt(1);
    }

    public int getTotalArmorValue() {
        return 15;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.HOSTILE;
    }

    @Override
    public float getAlignmentBonus() {
        return 0.0f;
    }

    @Override
    protected boolean canRideSpider() {
        return false;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag && this.rand.nextInt(4) == 0) {
            this.dropItem(LOTRMod.mysteryWeb, 1);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return null;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            int difficulty;
            int duration;
            if (entity instanceof EntityLivingBase && (duration = (difficulty = this.worldObj.difficultySetting.getDifficultyId()) * (difficulty + 5) / 2) > 0) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(LOTRPotions.vulnerability.id, duration * 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(LOTRPotions.infection.id, duration * 20, 0));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        return j > 0 && j <= 75 && (this.worldObj.getBlock(i, j - 1, k) == Blocks.stone || this.worldObj.getBlock(i, j - 1, k) == LOTRMod.rock && this.worldObj.getBlockMetadata(i, j - 1, k) == 4 || this.worldObj.getBlock(i, j - 1, k) == LOTRMod.pillar4 && this.worldObj.getBlockMetadata(i, j - 1, k) == 3 || this.worldObj.getBlock(i, j - 1, k) == LOTRMod.pillar && this.worldObj.getBlockMetadata(i, j - 1, k) == 4);
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killCaveSpider;
    }
}

