/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRReflection;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.world.biome.LOTRBiomeGenShire;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityGiraffe
extends LOTREntityHorse {
    public LOTREntityGiraffe(World world) {
        super(world);
        this.setSize(1.7f, 4.0f);
    }

    public int getHorseType() {
        return 0;
    }

    @Override
    protected void onLOTRHorseSpawn() {
        double jumpStrength = this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue();
        this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength *= 0.8);
    }

    @Override
    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)12.0, (double)34.0);
    }

    @Override
    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.2, (double)1.0);
    }

    @Override
    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.08, (double)0.35);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && Block.getBlockFromItem((Item)itemstack.getItem()) instanceof BlockLeavesBase;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.riddenByEntity instanceof EntityPlayer && this.isMountSaddled()) {
            EntityPlayer entityplayer = (EntityPlayer)this.riddenByEntity;
            BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.posZ));
            if (biome instanceof LOTRBiomeGenShire) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.rideGiraffeShire);
            }
        }
    }

    protected Item getDropItem() {
        return Items.leather;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag) {
            int rugChance = 30 - i * 5;
            if (this.rand.nextInt(rugChance = Math.max(rugChance, 1)) == 0) {
                this.entityDropItem(new ItemStack(LOTRMod.giraffeRug), 0.0f);
            }
        }
        int k = 1 + this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int j = 0; j < k; ++j) {
            this.dropItem(Items.bone, 1);
        }
    }
}

