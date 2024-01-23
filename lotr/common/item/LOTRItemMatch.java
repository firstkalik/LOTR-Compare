/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemMatch
extends Item {
    public LOTRItemMatch() {
        this.setFull3D();
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabTools);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2) {
        ItemStack proxyItem = new ItemStack(Items.flint_and_steel);
        if (proxyItem.tryPlaceItemIntoWorld(entityplayer, world, i, j, k, side, f, f1, f2)) {
            --itemstack.stackSize;
            return true;
        }
        return false;
    }
}

