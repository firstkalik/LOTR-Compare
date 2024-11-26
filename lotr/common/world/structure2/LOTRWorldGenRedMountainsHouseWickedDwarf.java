/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDwarfHouse2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LOTRWorldGenRedMountainsHouseWickedDwarf
extends LOTRWorldGenDwarfHouse2 {
    public LOTRWorldGenRedMountainsHouseWickedDwarf(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.stoneBlock = LOTRMod.rock;
        this.stoneMeta = 4;
        this.fillerBlock = LOTRMod.rock;
        this.fillerMeta = 4;
        this.topBlock = Blocks.snow;
        this.topMeta = 0;
        this.brick2Block = LOTRMod.brick7;
        this.brick2Meta = 0;
        this.brickStairBlock = LOTRMod.stairsSarnkaranBrickCracked;
        this.pillarBlock = LOTRMod.pillar;
        this.pillarMeta = 4;
        this.chandelierBlock = LOTRMod.chandelier;
        this.chandelierMeta = 3;
        this.tableBlock = Blocks.air;
        this.barsBlock = Blocks.air;
        this.larderContents = LOTRChestContents.LOTRChestContents2.BANDITDWARF;
        this.personalContents = LOTRChestContents.LOTRChestContents2.BANDITDWARF;
        this.plateFoods = LOTRFoods.DWARF;
        this.drinkFoods = LOTRFoods.WICKED_DRINK;
    }

    @Override
    protected ItemStack getRandomWeaponItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.swordWickedDwarf), new ItemStack(LOTRMod.daggerWickedDwarf), new ItemStack(LOTRMod.hammerWickedDwarf), new ItemStack(LOTRMod.battleaxeWickedDwarf), new ItemStack(LOTRMod.pikeWickedDwarf)};
        return items[random.nextInt(items.length)].copy();
    }

    @Override
    protected ItemStack getRandomOtherItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.wdhelmet), new ItemStack(LOTRMod.wdbody), new ItemStack(LOTRMod.wdlegs), new ItemStack(LOTRMod.wdboots), new ItemStack(LOTRMod.dwarfSteel), new ItemStack(LOTRMod.bronze), new ItemStack(Items.iron_ingot), new ItemStack(LOTRMod.silver), new ItemStack(LOTRMod.silverNugget), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_nugget)};
        return items[random.nextInt(items.length)].copy();
    }
}

