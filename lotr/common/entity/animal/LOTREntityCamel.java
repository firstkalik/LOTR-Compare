/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockColored
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRReflection;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityCamel
extends LOTREntityHorse
implements LOTRBiomeGenNearHarad.ImmuneToHeat {
    public LOTREntityCamel(World world) {
        super(world);
        this.setSize(1.6f, 1.8f);
    }

    public int getHorseType() {
        return this.worldObj.isRemote ? 0 : 1;
    }

    @Override
    protected void onLOTRHorseSpawn() {
        double jumpStrength = this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue();
        this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength *= 0.5);
    }

    @Override
    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)12.0, (double)36.0);
    }

    @Override
    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.1, (double)0.6);
    }

    @Override
    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.1, (double)0.35);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.wheat;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.riddenByEntity instanceof EntityPlayer && this.isMountSaddled()) {
            LOTRLevelData.getData((EntityPlayer)this.riddenByEntity).addAchievement(LOTRAchievement.rideCamel);
        }
    }

    protected void dropFewItems(boolean flag, int i) {
        int hides = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < hides; ++l) {
            this.dropItem(Items.leather, 1);
        }
        int meat = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.camelCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.camelRaw, 1);
        }
    }

    public boolean func_110259_cr() {
        return true;
    }

    @Override
    public boolean isMountArmorValid(ItemStack itemstack) {
        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock((Block)Blocks.carpet)) {
            return true;
        }
        return super.isMountArmorValid(itemstack);
    }

    public boolean isCamelWearingCarpet() {
        ItemStack itemstack = this.getMountArmor();
        return itemstack != null && itemstack.getItem() == Item.getItemFromBlock((Block)Blocks.carpet);
    }

    public int getCamelCarpetColor() {
        ItemStack itemstack = this.getMountArmor();
        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock((Block)Blocks.carpet)) {
            int meta = itemstack.getItemDamage();
            int dyeMeta = BlockColored.func_150031_c((int)meta);
            int[] colors = ItemDye.field_150922_c;
            dyeMeta = MathHelper.clamp_int((int)dyeMeta, (int)0, (int)colors.length);
            return colors[dyeMeta];
        }
        return -1;
    }

    public void setNomadChestAndCarpet() {
        this.setChestedForWorldGen();
        ItemStack carpet = new ItemStack(Blocks.carpet, 1, this.rand.nextInt(16));
        this.setMountArmor(carpet);
    }
}

