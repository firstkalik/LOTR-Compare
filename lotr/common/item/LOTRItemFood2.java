/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 */
package lotr.common.item;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class LOTRItemFood2
extends LOTRItemFood {
    public static Item block = new LOTRItemFood2(200, 100.3f, false);

    public LOTRItemFood2(int healAmount, float saturation, boolean canWolfEat) {
        super(healAmount, saturation, canWolfEat);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(block), (int)1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote && this == LOTRMod.maggotyBread) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.eatMaggotyBread);
        }
        return super.onEaten(itemstack, world, entityplayer);
    }

    public static class BlockCustomFood
    extends LOTRItemFood {
        public BlockCustomFood(int par2, float par3, boolean par4) {
            super(par2, par3, par4);
        }
    }

}

