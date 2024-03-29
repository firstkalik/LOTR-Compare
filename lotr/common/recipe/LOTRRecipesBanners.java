/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import lotr.common.item.LOTRItemBanner;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LOTRRecipesBanners
implements IRecipe {
    public boolean matches(InventoryCrafting inv, World world) {
        return this.getCraftingResult(inv) != null;
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack baseBanner = null;
        int emptyBanners = 0;
        for (int pass = 0; pass < 2; ++pass) {
            for (int i = 0; i < inv.getSizeInventory(); ++i) {
                ItemStack itemstack = inv.getStackInSlot(i);
                if (itemstack == null) continue;
                if (itemstack.getItem() instanceof LOTRItemBanner) {
                    NBTTagCompound data = LOTRItemBanner.getProtectionData(itemstack);
                    if (pass == 0 && data != null) {
                        if (baseBanner == null) {
                            baseBanner = itemstack;
                        } else {
                            return null;
                        }
                    }
                    if (pass != 1) continue;
                    if (baseBanner != null && itemstack.getItemDamage() == baseBanner.getItemDamage()) {
                        if (data != null) continue;
                        ++emptyBanners;
                        continue;
                    }
                    return null;
                }
                return null;
            }
        }
        if (baseBanner == null) {
            return null;
        }
        if (emptyBanners > 0) {
            ItemStack result = baseBanner.copy();
            result.stackSize = emptyBanners + 1;
            return result;
        }
        ItemStack result = baseBanner.copy();
        result.stackSize = 1;
        result.setTagCompound(null);
        return result;
    }

    public int getRecipeSize() {
        return 1;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

