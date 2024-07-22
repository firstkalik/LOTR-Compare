/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockLeavesBase;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRBlockFruitLeaves
extends LOTRBlockLeavesBase {
    public LOTRBlockFruitLeaves() {
        this.setLeafNames("apple", "pear", "cherry", "mango");
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)LOTRMod.fruitSapling);
    }

    @Override
    protected void addSpecialLeafDrops(ArrayList drops, World world, int i, int j, int k, int meta, int fortune) {
        if ((meta & 3) == 0 && world.rand.nextInt(this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            if (world.rand.nextBoolean()) {
                drops.add(new ItemStack(Items.apple));
            } else {
                drops.add(new ItemStack(LOTRMod.appleGreen));
            }
        }
        if ((meta & 3) == 1 && world.rand.nextInt(this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.pear));
        }
        if ((meta & 3) == 2 && world.rand.nextInt(this.calcFortuneModifiedDropChance(8, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.cherry));
        }
        if ((meta & 3) == 3 && world.rand.nextInt(this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.mango));
        }
    }
}

