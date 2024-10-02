/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemBaseRing3;
import lotr.common.item.LOTRStoryItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemRadaghastStaff
extends LOTRItemBaseRing3
implements LOTRStoryItem {
    public LOTRItemRadaghastStaff() {
        this.setMaxDamage(1500);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.lotrWeaponDamage = 7.0f;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public boolean onItemUse(ItemStack srcItemStack, EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ, int par7, float par8, float par9, float par10) {
        if (!playerEntity.capabilities.isCreativeMode && this.isOutOfCharge(srcItemStack)) {
            this.playSound(noChargeAttackSound, world, playerEntity);
            return true;
        }
        boolean success = this.growBlock(playerEntity, world, targetX, targetY, targetZ);
        if (success) {
            this.playSound("random.orb", world, playerEntity);
            if (!playerEntity.capabilities.isCreativeMode) {
                srcItemStack.damageItem(this.getUseCost(), (EntityLivingBase)playerEntity);
            }
        }
        return success;
    }

    protected boolean growBlock(EntityPlayer playerEntity, World world, int targetX, int targetY, int targetZ) {
        Block targetBlock = world.getBlock(targetX, targetY, targetZ);
        ItemStack fauxItemStack = new ItemStack(Items.dye, 1, 15);
        if (targetBlock == Blocks.cactus) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.cactus) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.cactus);
            }
            return true;
        }
        if (targetBlock == Blocks.cactus) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.cactus) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.cactus);
            }
            return true;
        }
        if (targetBlock == Blocks.reeds) {
            int y = targetY + 1;
            while (world.getBlock(targetX, y, targetZ) == Blocks.reeds) {
                ++y;
            }
            if (world.isAirBlock(targetX, y, targetZ)) {
                world.setBlock(targetX, y, targetZ, Blocks.reeds);
            }
            return true;
        }
        return ItemDye.applyBonemeal((ItemStack)fauxItemStack, (World)world, (int)targetX, (int)targetY, (int)targetZ, (EntityPlayer)playerEntity);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack srcItemStack, World world, EntityPlayer playerEntity) {
        playerEntity.setItemInUse(srcItemStack, this.getMaxItemUseDuration(srcItemStack));
        return srcItemStack;
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }
}

