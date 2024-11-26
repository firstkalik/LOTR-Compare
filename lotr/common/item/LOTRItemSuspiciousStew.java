/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LOTRItemSuspiciousStew
extends ItemFood {
    private static final Random random = new Random();

    public LOTRItemSuspiciousStew() {
        super(6, 0.6f, false);
        this.maxStackSize = 1;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabFood);
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onEaten(stack, world, player);
        if (!world.isRemote && player != null) {
            this.applyRandomEffect(player);
        }
        return !player.capabilities.isCreativeMode ? new ItemStack(Items.bowl) : stack;
    }

    private void applyRandomEffect(EntityPlayer player) {
        int[] effects = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 31, 32, 33, 36, 37, 38, 39, 40, 41, 42, 43, 44, 46, 47};
        int randomEffect = effects[random.nextInt(effects.length)];
        int duration = 200;
        player.addPotionEffect(new PotionEffect(randomEffect, duration));
    }
}

