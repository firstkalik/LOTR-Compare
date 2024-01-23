/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemFeatherDyed;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipeLeatherHatFeather
implements IRecipe {
    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack hat = null;
        ItemStack feather = null;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() == LOTRMod.leatherHat && !LOTRItemLeatherHat.hasFeather(itemstack)) {
                if (hat != null) {
                    return false;
                }
                hat = itemstack;
                continue;
            }
            if (LOTRMod.isOreNameEqual(itemstack, "feather") || itemstack.getItem() == LOTRMod.featherDyed) {
                if (feather != null) {
                    return false;
                }
                feather = itemstack;
                continue;
            }
            return false;
        }
        return hat != null && feather != null;
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack hat = null;
        ItemStack feather = null;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() == LOTRMod.leatherHat && !LOTRItemLeatherHat.hasFeather(itemstack)) {
                if (hat != null) {
                    return null;
                }
                hat = itemstack.copy();
                continue;
            }
            if (LOTRMod.isOreNameEqual(itemstack, "feather") || itemstack.getItem() == LOTRMod.featherDyed) {
                if (feather != null) {
                    return null;
                }
                feather = itemstack.copy();
                continue;
            }
            return null;
        }
        if (hat == null || feather == null) {
            return null;
        }
        int featherColor = 16777215;
        if (feather.getItem() == LOTRMod.featherDyed) {
            featherColor = LOTRItemFeatherDyed.getFeatherColor(feather);
        }
        LOTRItemLeatherHat.setFeatherColor(hat, featherColor);
        return hat;
    }

    public int getRecipeSize() {
        return 2;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

