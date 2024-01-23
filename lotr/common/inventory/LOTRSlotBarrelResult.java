/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemMug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRSlotBarrelResult
extends Slot {
    public LOTRSlotBarrelResult(IInventory inv, int i, int j, int k) {
        super(inv, i, j, k);
    }

    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    public boolean canTakeStack(EntityPlayer entityplayer) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        IIcon iIcon;
        if (this.getSlotIndex() > 5) {
            LOTRItemMug cfr_ignored_0 = (LOTRItemMug)LOTRMod.mugAle;
            iIcon = LOTRItemMug.barrelGui_emptyMugSlotIcon;
        } else {
            iIcon = null;
        }
        return iIcon;
    }
}

