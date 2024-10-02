/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package lotr.common.recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import lotr.common.LOTRMod;
import lotr.common.recipe.LOTRRecipes;
import lotr.common.tileentity.LOTRTileEntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class LOTRBrewingRecipes {
    private static ArrayList<ShapelessOreRecipe> recipes = new ArrayList();
    public static int BARREL_CAPACITY = 16;

    public static void createBrewingRecipes() {
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugAle, BARREL_CAPACITY), new Object[]{Items.wheat, Items.wheat, Items.wheat, Items.wheat, Items.wheat, Items.wheat});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMiruvor, BARREL_CAPACITY), new Object[]{LOTRMod.mallornNut, LOTRMod.mallornNut, LOTRMod.mallornNut, LOTRMod.elanor, LOTRMod.niphredil, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugOrcDraught, BARREL_CAPACITY), new Object[]{LOTRMod.morgulShroom, LOTRMod.morgulShroom, LOTRMod.morgulShroom, "bone", "bone", "bone"});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugUrukDraught, BARREL_CAPACITY), new Object[]{LOTRMod.morgulShroom, LOTRMod.morgulShroom, LOTRMod.morgulShroom, LOTRMod.manFlesh, LOTRMod.manFlesh, LOTRMod.manFlesh});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMead, BARREL_CAPACITY), new Object[]{Items.sugar, Items.sugar, Items.sugar, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugCider, BARREL_CAPACITY), "apple", "apple", "apple", "apple", "apple", "apple");
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugPerry, BARREL_CAPACITY), new Object[]{LOTRMod.pear, LOTRMod.pear, LOTRMod.pear, LOTRMod.pear, LOTRMod.pear, LOTRMod.pear});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugCherryLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.cherry, LOTRMod.cherry, LOTRMod.cherry, LOTRMod.cherry, LOTRMod.cherry, LOTRMod.cherry});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugRum, BARREL_CAPACITY), new Object[]{Items.reeds, Items.reeds, Items.reeds, Items.reeds, Items.reeds, Items.reeds});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugAthelasBrew, BARREL_CAPACITY), new Object[]{LOTRMod.athelas, LOTRMod.athelas, LOTRMod.athelas, LOTRMod.athelas, LOTRMod.athelas, LOTRMod.athelas});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugDwarvenTonic, BARREL_CAPACITY), new Object[]{Items.wheat, Items.wheat, Items.wheat, LOTRMod.kham, LOTRMod.dwarfHerb, LOTRMod.mithrilNugget});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugRedDwarvenTonic, BARREL_CAPACITY), new Object[]{LOTRMod.khamRaw, LOTRMod.khamRaw, LOTRMod.khamRaw, LOTRMod.athelas, LOTRMod.dwarfHerb, LOTRMod.mithrilNugget});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugBlueDwarvenTonic, BARREL_CAPACITY), new Object[]{LOTRMod.athelas, LOTRMod.athelas, LOTRMod.athelas, Blocks.brown_mushroom, LOTRMod.mithrilNugget, Blocks.red_mushroom});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugKhamBrew, BARREL_CAPACITY), new Object[]{LOTRMod.kham, LOTRMod.kham, LOTRMod.kham, LOTRMod.kham, LOTRMod.kham, LOTRMod.kham});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugOliveExtract, BARREL_CAPACITY), new Object[]{new ItemStack(LOTRMod.leaves6, 1, 3), new ItemStack(LOTRMod.leaves6, 1, 3), new ItemStack(LOTRMod.leaves6, 1, 3), new ItemStack(LOTRMod.leaves6, 1, 3), new ItemStack(LOTRMod.leaves6, 1, 3), new ItemStack(LOTRMod.leaves6, 1, 3)});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugOliveExtract, BARREL_CAPACITY), new Object[]{new ItemStack(LOTRMod.fallenLeavesLOTR2, 1, 11), new ItemStack(LOTRMod.fallenLeavesLOTR2, 1, 11), new ItemStack(LOTRMod.fallenLeavesLOTR2, 1, 11), new ItemStack(LOTRMod.fallenLeavesLOTR2, 1, 11), new ItemStack(LOTRMod.fallenLeavesLOTR2, 1, 11), new ItemStack(LOTRMod.fallenLeavesLOTR2, 1, 11)});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugDwarvenAle, BARREL_CAPACITY), new Object[]{Items.wheat, Items.wheat, Items.wheat, Items.wheat, LOTRMod.dwarfHerb, LOTRMod.dwarfHerb});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugVodka, BARREL_CAPACITY), new Object[]{Items.potato, Items.potato, Items.potato, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugBlueberryLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.blueberry, LOTRMod.blueberry, LOTRMod.blueberry, LOTRMod.lemon, Items.sugar, Items.wheat});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugCranberryLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.cranberry, LOTRMod.cranberry, LOTRMod.cranberry, LOTRMod.lemon, Items.sugar, Items.wheat});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugBlackberryLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.blackberry, LOTRMod.blackberry, LOTRMod.blackberry, LOTRMod.lemon, Items.sugar, Items.wheat});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugRaspberryLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.raspberry, LOTRMod.raspberry, LOTRMod.raspberry, LOTRMod.lemon, Items.sugar, Items.wheat});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugElderberryLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.elderberry, LOTRMod.elderberry, LOTRMod.elderberry, LOTRMod.lemon, Items.sugar, Items.wheat});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugTomatoLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.tomato, LOTRMod.tomato, LOTRMod.tomato, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugPickleJuice, BARREL_CAPACITY), new Object[]{LOTRMod.pickle, LOTRMod.pickle, LOTRMod.pickle, LOTRMod.pickle, LOTRMod.pickle, LOTRMod.pickle});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugKoumiss, BARREL_CAPACITY), new Object[]{Items.milk_bucket, Items.milk_bucket, Items.milk_bucket, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugAlcohol, BARREL_CAPACITY), new Object[]{Items.potato, Items.potato, Items.potato, Blocks.hay_block, LOTRMod.sugarBlock, Blocks.hay_block});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugAlcohol1000, BARREL_CAPACITY), new Object[]{LOTRMod.sugarBlock, LOTRMod.sugarBlock, LOTRMod.sugarBlock, LOTRMod.sugarBlock, LOTRMod.sugarBlock, LOTRMod.sugarBlock});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMrCider, BARREL_CAPACITY), new Object[]{"apple", "apple", "apple", Items.sugar, LOTRMod.wildberry, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMapleBeer, BARREL_CAPACITY), new Object[]{Items.wheat, Items.wheat, Items.wheat, Items.wheat, LOTRMod.mapleSyrup, LOTRMod.mapleSyrup});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugAraq, BARREL_CAPACITY), new Object[]{LOTRMod.date, LOTRMod.date, LOTRMod.date, LOTRMod.date, LOTRMod.date, LOTRMod.date});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugCarrotWine, BARREL_CAPACITY), new Object[]{Items.carrot, Items.carrot, Items.carrot, Items.carrot, Items.carrot, Items.carrot});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugBananaBeer, BARREL_CAPACITY), new Object[]{LOTRMod.banana, LOTRMod.banana, LOTRMod.banana, LOTRMod.banana, LOTRMod.banana, LOTRMod.banana});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMelonLiqueur, BARREL_CAPACITY), new Object[]{Items.melon, Items.melon, Items.melon, Items.melon, Items.melon, Items.melon});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugCactusLiqueur, BARREL_CAPACITY), new Object[]{Blocks.cactus, Blocks.cactus, Blocks.cactus, Blocks.cactus, Blocks.cactus, Blocks.cactus});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugPumpkinLiqueur, BARREL_CAPACITY), new Object[]{Blocks.pumpkin, Blocks.pumpkin, Blocks.pumpkin, Blocks.pumpkin, Blocks.pumpkin, Blocks.pumpkin});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugChacha, BARREL_CAPACITY), new Object[]{LOTRMod.grapeWhite, LOTRMod.grapeWhite, LOTRMod.grapeWhite, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugChacha, BARREL_CAPACITY), new Object[]{LOTRMod.grapeRed, LOTRMod.grapeRed, LOTRMod.grapeRed, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMeatBroth, BARREL_CAPACITY), new Object[]{LOTRMod.saltedFlesh, LOTRMod.saltedFlesh, LOTRMod.saltedFlesh, LOTRMod.saltedFlesh, LOTRMod.saltedFlesh, LOTRMod.saltedFlesh});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMeatBroth, BARREL_CAPACITY), new Object[]{LOTRMod.manFlesh, LOTRMod.manFlesh, LOTRMod.manFlesh, LOTRMod.manFlesh, LOTRMod.manFlesh, LOTRMod.manFlesh});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMashedPotatoes, BARREL_CAPACITY), new Object[]{Items.potato, Items.potato, Items.potato, Items.potato, Items.potato, Items.potato});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugTorogDraught, BARREL_CAPACITY), new Object[]{Items.reeds, Items.reeds, Items.rotten_flesh, Items.rotten_flesh, Blocks.dirt, LOTRMod.rhinoHorn});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugLemonLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.lemon, LOTRMod.lemon, LOTRMod.lemon, LOTRMod.lemon, LOTRMod.lemon, LOTRMod.lemon});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugLimeLiqueur, BARREL_CAPACITY), new Object[]{LOTRMod.lime, LOTRMod.lime, LOTRMod.lime, LOTRMod.lime, LOTRMod.lime, LOTRMod.lime});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugCornLiquor, BARREL_CAPACITY), new Object[]{LOTRMod.corn, LOTRMod.corn, LOTRMod.corn, LOTRMod.corn, LOTRMod.corn, LOTRMod.corn});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugRedWine, BARREL_CAPACITY), new Object[]{LOTRMod.grapeRed, LOTRMod.grapeRed, LOTRMod.grapeRed, LOTRMod.grapeRed, LOTRMod.grapeRed, LOTRMod.grapeRed});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugWhiteWine, BARREL_CAPACITY), new Object[]{LOTRMod.grapeWhite, LOTRMod.grapeWhite, LOTRMod.grapeWhite, LOTRMod.grapeWhite, LOTRMod.grapeWhite, LOTRMod.grapeWhite});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugMorgulDraught, BARREL_CAPACITY), new Object[]{LOTRMod.morgulFlower, LOTRMod.morgulFlower, LOTRMod.morgulFlower, "bone", "bone", "bone"});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugPlumKvass, BARREL_CAPACITY), new Object[]{Items.wheat, Items.wheat, Items.wheat, LOTRMod.plum, LOTRMod.plum, LOTRMod.plum});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugTermiteTequila, BARREL_CAPACITY), new Object[]{Blocks.cactus, Blocks.cactus, Blocks.cactus, Blocks.cactus, Blocks.cactus, LOTRMod.termite});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugSourMilk, BARREL_CAPACITY), new Object[]{Items.milk_bucket, Items.milk_bucket, Items.milk_bucket, Items.milk_bucket, Items.milk_bucket, Items.milk_bucket});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugPomegranateWine, BARREL_CAPACITY), new Object[]{LOTRMod.pomegranate, LOTRMod.pomegranate, LOTRMod.pomegranate, LOTRMod.pomegranate, LOTRMod.pomegranate, LOTRMod.pomegranate});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugChili, BARREL_CAPACITY), new Object[]{LOTRMod.chili, LOTRMod.chili, LOTRMod.chili, Items.sugar, Items.sugar, Items.sugar});
        LOTRBrewingRecipes.addBrewingRecipe(new ItemStack(LOTRMod.mugPoison, BARREL_CAPACITY), new Object[]{LOTRMod.morgulFlower, LOTRMod.morgulFlower, LOTRMod.morgulFlower, LOTRMod.morgulShroom, new ItemStack((Block)Blocks.red_mushroom, 1, 0), LOTRMod.morgulShroom});
    }

    private static void addBrewingRecipe(ItemStack result, Object ... ingredients) {
        if (ingredients.length != 6) {
            throw new IllegalArgumentException("Brewing recipes must contain exactly 6 items");
        }
        recipes.add(new ShapelessOreRecipe(result, ingredients));
    }

    public static boolean isWaterSource(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.water_bucket;
    }

    public static ItemStack findMatchingRecipe(LOTRTileEntityBarrel barrel) {
        for (int i = 6; i < 9; ++i) {
            ItemStack itemstack = barrel.getStackInSlot(i);
            if (LOTRBrewingRecipes.isWaterSource(itemstack)) continue;
            return null;
        }
        block1: for (ShapelessOreRecipe recipe : recipes) {
            ArrayList ingredients = new ArrayList(recipe.getInput());
            for (int i = 0; i < 6; ++i) {
                ItemStack itemstack = barrel.getStackInSlot(i);
                if (itemstack == null) continue;
                boolean inRecipe = false;
                Iterator it = ingredients.iterator();
                while (it.hasNext()) {
                    boolean match = false;
                    Object next = it.next();
                    if (next instanceof ItemStack) {
                        match = LOTRRecipes.checkItemEquals((ItemStack)next, itemstack);
                    } else if (next instanceof ArrayList) {
                        for (ItemStack item : (ArrayList)next) {
                            match = match || LOTRRecipes.checkItemEquals(item, itemstack);
                        }
                    }
                    if (!match) continue;
                    inRecipe = true;
                    ingredients.remove(next);
                    break;
                }
                if (!inRecipe) continue block1;
            }
            if (!ingredients.isEmpty()) continue;
            return recipe.getRecipeOutput().copy();
        }
        return null;
    }
}

