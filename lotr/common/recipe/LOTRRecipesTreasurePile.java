/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package lotr.common.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipesTreasurePile
implements IRecipe {
    private Block treasureBlock;
    private Item ingotItem;

    public LOTRRecipesTreasurePile(Block block, Item item) {
        this.treasureBlock = block;
        this.ingotItem = item;
    }

    public boolean matches(InventoryCrafting inv, World world) {
        return this.getCraftingResult(inv) != null;
    }

    public ItemStack getCraftingResult(InventoryCrafting inv) {
        int ingredientCount = 0;
        int ingredientTotalSize = 0;
        int resultCount = 0;
        int resultMeta = 0;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack == null) continue;
            if (itemstack.getItem() == Item.getItemFromBlock((Block)this.treasureBlock)) {
                ++ingredientCount;
                int meta = itemstack.getItemDamage();
                ingredientTotalSize += meta + 1;
                continue;
            }
            return null;
        }
        if (ingredientCount > 0) {
            if (ingredientCount == 1) {
                if (ingredientTotalSize > 1) {
                    resultCount = ingredientTotalSize;
                    resultMeta = 0;
                }
            } else {
                resultCount = 1;
                resultMeta = ingredientTotalSize - 1;
            }
        }
        if (resultCount <= 0 || resultMeta > 7) {
            return null;
        }
        if (ingredientCount == 1 && ingredientTotalSize == 8) {
            return new ItemStack(this.ingotItem, 4);
        }
        return new ItemStack(this.treasureBlock, resultCount, resultMeta);
    }

    public int getRecipeSize() {
        return 10;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}

