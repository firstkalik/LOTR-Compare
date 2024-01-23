/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockColored
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemDye;
import lotr.common.item.LOTRItemHobbitPipe;
import net.minecraft.block.BlockColored;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipeHobbitPipe
implements IRecipe {
    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack hobbitPipe = null;
        ItemStack dye = null;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() == LOTRMod.hobbitPipe) {
                if (hobbitPipe != null) {
                    return false;
                }
                hobbitPipe = itemstack;
                continue;
            }
            if (itemstack.getItem() == LOTRMod.mithrilNugget) {
                dye = itemstack;
                continue;
            }
            if (LOTRItemDye.isItemDye(itemstack) == -1) {
                return false;
            }
            dye = itemstack;
        }
        return hobbitPipe != null && dye != null;
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack hobbitPipe = null;
        ItemStack dye = null;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() == LOTRMod.hobbitPipe) {
                if (hobbitPipe != null) {
                    return null;
                }
                hobbitPipe = itemstack;
                continue;
            }
            if (itemstack.getItem() == LOTRMod.mithrilNugget) {
                dye = itemstack;
                continue;
            }
            if (LOTRItemDye.isItemDye(itemstack) == -1) {
                return null;
            }
            dye = itemstack;
        }
        if (hobbitPipe != null && dye != null) {
            int itemDamage = hobbitPipe.getItemDamage();
            int smokeType = dye.getItem() == LOTRMod.mithrilNugget ? 16 : BlockColored.func_150031_c((int)LOTRItemDye.isItemDye(dye));
            ItemStack result = new ItemStack(LOTRMod.hobbitPipe);
            result.setItemDamage(itemDamage);
            LOTRItemHobbitPipe.setSmokeColor(result, smokeType);
            return result;
        }
        return null;
    }

    public int getRecipeSize() {
        return 2;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

