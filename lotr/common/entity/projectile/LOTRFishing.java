/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemFishFood
 *  net.minecraft.item.ItemFishFood$FishType
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.WeightedRandom$Item
 *  net.minecraftforge.common.FishingHooks
 *  net.minecraftforge.common.FishingHooks$FishableCategory
 */
package lotr.common.entity.projectile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.FishingHooks;

public class LOTRFishing {
    private static List<FishingItem> fish = new ArrayList<FishingItem>();
    private static List<FishingItem> junk = new ArrayList<FishingItem>();
    private static List<FishingItem> treasure = new ArrayList<FishingItem>();

    public static FishResult getFishResult(Random rand, float chance, int luck, int speed, boolean allowJunkTreasure, int seaFortuneLevel) {
        float junkChance = 0.1f - (float)speed * 0.03f;
        junkChance = MathHelper.clamp_float((float)junkChance, (float)0.01f, (float)1.0f);
        float treasureChance = 0.2f + (float)luck * 0.01f + 0.1f * (float)seaFortuneLevel;
        treasureChance = MathHelper.clamp_float((float)treasureChance, (float)0.0f, (float)0.5f);
        float fishChance = 1.0f - junkChance - treasureChance;
        if (allowJunkTreasure) {
            float f;
            if (chance < junkChance) {
                ItemStack result = ((FishingItem)WeightedRandom.getRandomItem((Random)rand, junk)).getRandomResult(rand);
                return new FishResult(FishingHooks.FishableCategory.JUNK, result);
            }
            chance -= junkChance;
            if (f < treasureChance) {
                ItemStack result = ((FishingItem)WeightedRandom.getRandomItem((Random)rand, treasure)).getRandomResult(rand);
                return new FishResult(FishingHooks.FishableCategory.TREASURE, result);
            }
        }
        ItemStack result = ((FishingItem)WeightedRandom.getRandomItem((Random)rand, fish)).getRandomResult(rand);
        return new FishResult(FishingHooks.FishableCategory.FISH, result);
    }

    static {
        fish.add(new FishingItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a()), 60));
        fish.add(new FishingItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 25));
        fish.add(new FishingItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a()), 2));
        fish.add(new FishingItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()), 13));
        junk.add(new FishingItem(new ItemStack((Item)Items.fishing_rod), 5).setMaxDurability(0.1f));
        junk.add(new FishingItem(new ItemStack(Items.wooden_sword), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(Items.wooden_axe), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(Items.wooden_pickaxe), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(Items.wooden_shovel), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(Items.wooden_hoe), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(LOTRMod.leatherHat), 10));
        junk.add(new FishingItem(new ItemStack((Item)Items.leather_helmet), 5).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack((Item)Items.leather_boots), 5).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(LOTRMod.helmetBone), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(LOTRMod.bootsBone), 2).setMaxDurability(0.5f));
        junk.add(new FishingItem(new ItemStack(Items.skull, 1, 0), 5));
        junk.add(new FishingItem(new ItemStack(Items.bone), 20));
        junk.add(new FishingItem(new ItemStack(LOTRMod.orcBone), 10));
        junk.add(new FishingItem(new ItemStack(LOTRMod.elfBone), 2));
        junk.add(new FishingItem(new ItemStack(LOTRMod.dwarfBone), 2));
        junk.add(new FishingItem(new ItemStack(LOTRMod.hobbitBone), 1));
        junk.add(new FishingItem(new ItemStack(LOTRMod.rottenLog, 1, 0), 10));
        junk.add(new FishingItem(new ItemStack(Items.leather), 10));
        junk.add(new FishingItem(new ItemStack(Items.string), 10));
        junk.add(new FishingItem(new ItemStack(Items.bowl), 10));
        junk.add(new FishingItem(new ItemStack(LOTRMod.mug), 10));
        junk.add(new FishingItem(new ItemStack(Items.book), 5));
        junk.add(new FishingItem(new ItemStack(Items.stick), 10));
        junk.add(new FishingItem(new ItemStack(Items.feather), 10));
        junk.add(new FishingItem(new ItemStack(Items.dye, 1, 0), 5));
        junk.add(new FishingItem(new ItemStack(Items.rotten_flesh), 5));
        junk.add(new FishingItem(new ItemStack(LOTRMod.saltedFlesh), 5));
        junk.add(new FishingItem(new ItemStack(LOTRMod.maggotyBread), 5));
        junk.add(new FishingItem(new ItemStack(LOTRMod.manFlesh), 5));
        junk.add(new FishingItem(new ItemStack(Blocks.waterlily), 15));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.pearl), 200));
        treasure.add(new FishingItem(new ItemStack((Item)Items.bow), 20).setMaxDurability(0.75f));
        treasure.add(new FishingItem(new ItemStack((Item)Items.fishing_rod), 20).setMaxDurability(0.75f));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.daggerIron), 20).setMaxDurability(0.75f));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.daggerBronze), 20).setMaxDurability(0.75f));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.silverCoin, 1, 0), 100));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.silverCoin, 1, 1), 10));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.silverCoin, 1, 2), 1));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.pouch, 1, 0), 20));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.pouch, 1, 1), 10));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.pouch, 1, 2), 5));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.pouch, 3, 2), 1));
        treasure.add(new FishingItem(new ItemStack(Items.iron_ingot), 20));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.bronze), 10));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.copper), 10));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.tin), 10));
        treasure.add(new FishingItem(new ItemStack(Items.gold_nugget), 50));
        treasure.add(new FishingItem(new ItemStack(Items.gold_ingot), 5));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.silverNugget), 50));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.silver), 5));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.mithrilNugget), 5));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.silverRing), 10));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.goldRing), 5));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.mithril), 1));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.ringClover), 3));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.graalGold), 3));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.graalMithril), 2));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.ringValarUlmo), 1).setMaxDurability(0.1f));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.steelbow), 1).setMaxDurability(0.1f));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.mithrilRing), 1));
        treasure.add(new FishingItem(new ItemStack(LOTRMod.treasureMap), 5));
    }

    private static class FishingItem
    extends WeightedRandom.Item {
        private final ItemStack theItem;
        private float maxDurability = 0.0f;

        private FishingItem(ItemStack item, int weight) {
            super(weight);
            this.theItem = item;
        }

        public FishingItem setMaxDurability(float f) {
            this.maxDurability = f;
            return this;
        }

        public ItemStack getRandomResult(Random rand) {
            ItemStack result = this.theItem.copy();
            if (this.maxDurability > 0.0f) {
                float damageF = 1.0f - rand.nextFloat() * this.maxDurability;
                int damage = (int)(damageF * (float)result.getMaxDamage());
                damage = Math.min(damage, result.getMaxDamage());
                damage = Math.max(damage, 1);
                result.setItemDamage(damage);
            }
            return result;
        }
    }

    public static class FishResult {
        public final FishingHooks.FishableCategory category;
        public final ItemStack fishedItem;

        public FishResult(FishingHooks.FishableCategory c, ItemStack item) {
            this.category = c;
            this.fishedItem = item;
        }
    }

}

