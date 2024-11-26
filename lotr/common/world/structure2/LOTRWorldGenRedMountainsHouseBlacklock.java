/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBlacklock;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDwarfHouse3;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenRedMountainsHouseBlacklock
extends LOTRWorldGenDwarfHouse3 {
    public LOTRWorldGenRedMountainsHouseBlacklock(boolean flag) {
        super(flag);
    }

    @Override
    protected LOTREntityDwarf createDwarf(World world) {
        return new LOTREntityBlacklock(world);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.stoneBlock = Blocks.stone;
        this.stoneMeta = 0;
        this.fillerBlock = LOTRMod.rock;
        this.fillerMeta = 4;
        this.topBlock = LOTRMod.rock;
        this.topMeta = 4;
        this.brick2Block = LOTRMod.brick2;
        this.brick2Meta = 2;
        this.brickStairBlock = LOTRMod.stairsRedRockBrick;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 4;
        this.chandelierBlock = LOTRMod.chandelier;
        this.chandelierMeta = 2;
        this.tableBlock = LOTRMod.reddwarvenTable;
        this.barsBlock = LOTRMod.silverBars;
        this.larderContents = LOTRChestContents.LOTRChestContents2.REDDWARFTOWER;
        this.personalContents = LOTRChestContents.LOTRChestContents2.REDDWARFTOWER;
        this.plateFoods = LOTRFoods.DWARF;
        this.drinkFoods = LOTRFoods.REDDWARF_DRINK_BLACLLOCK;
    }

    @Override
    protected ItemStack getRandomWeaponItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.swordRed), new ItemStack(LOTRMod.daggerRed), new ItemStack(LOTRMod.hammerRed), new ItemStack(LOTRMod.battleaxeRed), new ItemStack(LOTRMod.pickaxeRed), new ItemStack(LOTRMod.mattockRed), new ItemStack(LOTRMod.throwingAxeRed), new ItemStack(LOTRMod.pikeRed)};
        return items[random.nextInt(items.length)].copy();
    }

    @Override
    protected ItemStack getRandomOtherItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.helmetBlacklock), new ItemStack(LOTRMod.bodyBlacklock), new ItemStack(LOTRMod.legsBlacklock), new ItemStack(LOTRMod.bootsBlacklock), new ItemStack(LOTRMod.dwarfSteel), new ItemStack(LOTRMod.bronze), new ItemStack(Items.iron_ingot), new ItemStack(LOTRMod.silver), new ItemStack(LOTRMod.silverNugget), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_nugget)};
        return items[random.nextInt(items.length)].copy();
    }
}

