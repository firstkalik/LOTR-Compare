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
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityWindDwarf;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenDwarfHouse6;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenWindMountainsHouse
extends LOTRWorldGenDwarfHouse6 {
    public LOTRWorldGenWindMountainsHouse(boolean flag) {
        super(flag);
    }

    @Override
    protected LOTREntityWindDwarf createDwarf(World world) {
        return new LOTREntityWindDwarf(world);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.stoneBlock = Blocks.stone;
        this.stoneMeta = 0;
        this.fillerBlock = Blocks.stone;
        this.fillerMeta = 0;
        this.topBlock = Blocks.stone;
        this.topMeta = 0;
        this.brick2Block = LOTRMod.brick9;
        this.brick2Meta = 4;
        this.brickStairBlock = LOTRMod.stairsWindDwarf;
        this.pillarBlock = LOTRMod.pillar4;
        this.pillarMeta = 3;
        this.chandelierBlock = LOTRMod.chandelier2;
        this.chandelierMeta = 0;
        this.tableBlock = LOTRMod.windtable;
        this.barsBlock = LOTRMod.winddwarfBars;
        this.larderContents = LOTRChestContents.LOTRChestContents2.WIND_TOWER;
        this.personalContents = LOTRChestContents.LOTRChestContents2.WIND_TOWER;
        this.plateFoods = LOTRFoods.DWARF;
        this.drinkFoods = LOTRFoods.WIND_DRINK;
    }

    @Override
    protected ItemStack getRandomOtherItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.helmetWind), new ItemStack(LOTRMod.bodyWind), new ItemStack(LOTRMod.legsWind), new ItemStack(LOTRMod.bootsWind), new ItemStack(LOTRMod.dwarfSteel), new ItemStack(LOTRMod.bronze), new ItemStack(Items.iron_ingot), new ItemStack(LOTRMod.silver), new ItemStack(LOTRMod.silverNugget), new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_nugget)};
        return items[random.nextInt(items.length)].copy();
    }

    @Override
    protected ItemStack getRandomWeaponItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.swordDwarven), new ItemStack(LOTRMod.daggerDwarven), new ItemStack(LOTRMod.hammerDwarven), new ItemStack(LOTRMod.battleaxeDwarven), new ItemStack(LOTRMod.pickaxeDwarven), new ItemStack(LOTRMod.mattockDwarven), new ItemStack(LOTRMod.throwingAxeDwarven), new ItemStack(LOTRMod.pikeDwarven)};
        return items[random.nextInt(items.length)].copy();
    }
}

