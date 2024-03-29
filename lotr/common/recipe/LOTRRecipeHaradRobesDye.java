/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockColored
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import java.util.ArrayList;
import lotr.common.item.LOTRItemDye;
import lotr.common.item.LOTRItemHaradRobes;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipeHaradRobesDye
implements IRecipe {
    private ItemArmor.ArmorMaterial robeMaterial;

    public LOTRRecipeHaradRobesDye() {
        this(LOTRMaterial.HARAD_ROBES);
    }

    public LOTRRecipeHaradRobesDye(LOTRMaterial material) {
        this.robeMaterial = material.toArmorMaterial();
    }

    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack robes = null;
        ArrayList<ItemStack> dyes = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() instanceof LOTRItemHaradRobes && ((LOTRItemHaradRobes)itemstack.getItem()).getArmorMaterial() == this.robeMaterial) {
                if (robes != null) {
                    return false;
                }
                robes = itemstack;
                continue;
            }
            if (LOTRItemDye.isItemDye(itemstack) == -1) {
                return false;
            }
            dyes.add(itemstack);
        }
        return robes != null && !dyes.isEmpty();
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack robes = null;
        int[] rgb = new int[3];
        int totalColor = 0;
        int coloredItems = 0;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() instanceof LOTRItemHaradRobes && ((LOTRItemHaradRobes)itemstack.getItem()).getArmorMaterial() == this.robeMaterial) {
                if (robes != null) {
                    return null;
                }
                robes = itemstack.copy();
                robes.stackSize = 1;
                if (!LOTRItemHaradRobes.areRobesDyed(robes)) continue;
                int robeColor = LOTRItemHaradRobes.getRobesColor(robes);
                float r = (float)(robeColor >> 16 & 0xFF) / 255.0f;
                float g = (float)(robeColor >> 8 & 0xFF) / 255.0f;
                float b = (float)(robeColor & 0xFF) / 255.0f;
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
        if (robes == null) {
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
        LOTRItemHaradRobes.setRobesColor(robes, color);
        return robes;
    }

    public int getRecipeSize() {
        return 10;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

