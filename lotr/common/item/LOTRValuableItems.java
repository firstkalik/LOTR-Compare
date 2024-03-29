/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import lotr.common.item.LOTRItemCoin;
import lotr.common.item.LOTRItemGem;
import lotr.common.item.LOTRItemRing;
import lotr.common.recipe.LOTRRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRValuableItems {
    private static List<ItemStack> toolMaterials = new ArrayList<ItemStack>();
    private static boolean initTools = false;

    private static void registerToolMaterials() {
        if (!initTools) {
            Item.ToolMaterial[] allMaterials;
            toolMaterials.clear();
            for (Item.ToolMaterial material : allMaterials = Item.ToolMaterial.values()) {
                ItemStack repair;
                if (material.getHarvestLevel() < 2 || (repair = material.getRepairItemStack()) == null || repair.getItem() == null) continue;
                toolMaterials.add(repair.copy());
            }
            initTools = true;
        }
    }

    public static List<ItemStack> getToolMaterials() {
        LOTRValuableItems.registerToolMaterials();
        return toolMaterials;
    }

    public static boolean canMagpieSteal(ItemStack itemstack) {
        LOTRValuableItems.registerToolMaterials();
        Item item = itemstack.getItem();
        if (item instanceof LOTRItemCoin || item instanceof LOTRItemRing || item instanceof LOTRItemGem) {
            return true;
        }
        for (ItemStack listItem : toolMaterials) {
            if (!LOTRRecipes.checkItemEquals(listItem, itemstack)) continue;
            return true;
        }
        return false;
    }
}

