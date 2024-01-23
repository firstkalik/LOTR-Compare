/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.FoodStats
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class LOTRItemMorgulShroom
extends ItemBlock {
    public LOTRItemMorgulShroom(Block block) {
        super(block);
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        --itemstack.stackSize;
        if (LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.MORDOR) > 0.0f) {
            entityplayer.getFoodStats().addStats(4, 0.4f);
        } else if (!world.isRemote) {
            entityplayer.addPotionEffect(new PotionEffect(Potion.poison.id, 80));
        }
        if (!world.isRemote) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.eatMorgulShroom);
        }
        world.playSoundAtEntity((Entity)entityplayer, "random.burp", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.eat;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.canEat(false)) {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }
}

