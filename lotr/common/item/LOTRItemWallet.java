/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemWallet
extends Item {
    public LOTRItemWallet() {
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote && entityplayer instanceof EntityPlayerMP) {
            LOTRCommonProxy.sendClientsideGUI((EntityPlayerMP)entityplayer, 94, 0, 0, 0);
        }
        return itemstack;
    }
}

