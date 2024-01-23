/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
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
import lotr.common.recipe.LOTRBrewingRecipes;
import lotr.common.tileentity.LOTRTileEntityBarrel;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRSlotBarrel
extends Slot {
    private LOTRTileEntityBarrel theBarrel;
    private boolean isWater;

    public LOTRSlotBarrel(LOTRTileEntityBarrel inv, int i, int j, int k) {
        super((IInventory)inv, i, j, k);
        this.theBarrel = inv;
    }

    public LOTRSlotBarrel setWaterSource() {
        this.isWater = true;
        return this;
    }

    public boolean isItemValid(ItemStack itemstack) {
        if (this.theBarrel.barrelMode == 0) {
            if (this.isWater) {
                return LOTRBrewingRecipes.isWaterSource(itemstack);
            }
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        IIcon iIcon;
        if (this.getSlotIndex() > 5) {
            LOTRItemMug cfr_ignored_0 = (LOTRItemMug)LOTRMod.mugAle;
            iIcon = LOTRItemMug.barrelGui_emptyBucketSlotIcon;
        } else {
            iIcon = null;
        }
        return iIcon;
    }
}

