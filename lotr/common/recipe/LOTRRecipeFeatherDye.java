/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockColored
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import java.util.ArrayList;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemDye;
import lotr.common.item.LOTRItemFeatherDyed;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipeFeatherDye
implements IRecipe {
    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack feather = null;
        ArrayList<ItemStack> dyes = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (LOTRMod.isOreNameEqual(itemstack, "feather") || itemstack.getItem() == LOTRMod.featherDyed) {
                if (feather != null) {
                    return false;
                }
                feather = itemstack;
                continue;
            }
            if (LOTRItemDye.isItemDye(itemstack) == -1) {
                return false;
            }
            dyes.add(itemstack);
        }
        return feather != null && !dyes.isEmpty();
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack feather = null;
        int[] rgb = new int[3];
        int totalColor = 0;
        int coloredItems = 0;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (LOTRMod.isOreNameEqual(itemstack, "feather") || itemstack.getItem() == LOTRMod.featherDyed) {
                if (feather != null) {
                    return null;
                }
                feather = itemstack.copy();
                feather.stackSize = 1;
                if (feather.getItem() != LOTRMod.featherDyed) continue;
                int featherColor = LOTRItemFeatherDyed.getFeatherColor(feather);
                float r = (float)(featherColor >> 16 & 0xFF) / 255.0f;
                float g = (float)(featherColor >> 8 & 0xFF) / 255.0f;
                float b = (float)(featherColor & 0xFF) / 255.0f;
                totalColor = (int)((float)totalColor + Math.max(r, Math.max(g, b)) * 255.0f);
                rgb[0] = (int)((float)rgb[0] + r * 255.0f);
                rgb[1] = (int)((float)rgb[1] + g * 255.0f);
                rgb[2] = (int)((float)rgb[2] + b * 255.0f);
                ++coloredItems;
                continue;
            }
            int dye = LOTRItemDye.isItemDye(itemstack);
            if (dye == -1) {
                return null;
            }
            float[] dyeColors = EntitySheep.fleeceColorTable[BlockColored.func_150031_c((int)dye)];
            int r = (int)(dyeColors[0] * 255.0f);
            int g = (int)(dyeColors[1] * 255.0f);
            int b = (int)(dyeColors[2] * 255.0f);
            totalColor += Math.max(r, Math.max(g, b));
            rgb[0] = rgb[0] + r;
            rgb[1] = rgb[1] + g;
            rgb[2] = rgb[2] + b;
            ++coloredItems;
        }
        if (feather == null) {
            return null;
        }
        int r = rgb[0] / coloredItems;
        int g = rgb[1] / coloredItems;
        int b = rgb[2] / coloredItems;
        float averageColor = (float)totalColor / (float)coloredItems;
        float maxColor = Math.max(r, Math.max(g, b));
        r = (int)((float)r * averageColor / maxColor);
        g = (int)((float)g * averageColor / maxColor);
        b = (int)((float)b * averageColor / maxColor);
        int color = (r << 16) + (g << 8) + b;
        feather = new ItemStack(LOTRMod.featherDyed);
        LOTRItemFeatherDyed.setFeatherColor(feather, color);
        return feather;
    }

    public int getRecipeSize() {
        return 10;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

