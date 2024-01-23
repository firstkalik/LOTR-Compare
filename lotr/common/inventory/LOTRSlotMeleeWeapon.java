/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.item.LOTRWeaponStats;
import lotr.common.util.LOTRCommonIcons;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRSlotMeleeWeapon
extends Slot {
    public LOTRSlotMeleeWeapon(IInventory inv, int i, int j, int k) {
        super(inv, i, j, k);
    }

    public int getSlotStackLimit() {
        return 1;
    }

    public boolean isItemValid(ItemStack itemstack) {
        return LOTRWeaponStats.isMeleeWeapon(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        return LOTRCommonIcons.iconMeleeWeapon;
    }
}

