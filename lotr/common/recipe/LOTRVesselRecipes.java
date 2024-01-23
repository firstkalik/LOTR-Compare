/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package lotr.common.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lotr.common.item.LOTRItemMug;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class LOTRVesselRecipes {
    public static void addRecipes(ItemStack result, Object[] ingredients) {
        LOTRVesselRecipes.addRecipes(result, null, ingredients);
    }

    public static void addRecipes(ItemStack result, Item drinkBase, Object[] ingredients) {
        List<IRecipe> recipes = LOTRVesselRecipes.generateRecipes(result, drinkBase, ingredients);
        for (IRecipe r : recipes) {
            GameRegistry.addRecipe((IRecipe)r);
        }
    }

    public static List<IRecipe> generateRecipes(ItemStack result, Object[] ingredients) {
        return LOTRVesselRecipes.generateRecipes(result, null, ingredients);
    }

    public static List<IRecipe> generateRecipes(ItemStack result, Item drinkBase, Object[] ingredients) {
        ArrayList<IRecipe> recipes = new ArrayList<IRecipe>();
        for (LOTRItemMug.Vessel v : LOTRItemMug.Vessel.values()) {
            ArrayList<Object> vIngredients = new ArrayList<Object>();
            ItemStack vBase = v.getEmptyVessel();
            if (drinkBase != null) {
                vBase = new ItemStack(drinkBase);
                LOTRItemMug.setVessel(vBase, v, true);
            }
            vIngredients.add((Object)vBase);
            vIngredients.addAll(Arrays.asList(ingredients));
            ItemStack vResult = result.copy();
            LOTRItemMug.setVessel(vResult, v, true);
            ShapelessOreRecipe recipe = new ShapelessOreRecipe(vResult, vIngredients.toArray());
            recipes.add((IRecipe)recipe);
        }
        return recipes;
    }
}

