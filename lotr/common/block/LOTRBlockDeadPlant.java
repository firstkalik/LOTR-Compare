/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.IShearable
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.block.LOTRBlockFlower;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

public class LOTRBlockDeadPlant
extends LOTRBlockFlower
implements IShearable {
    public LOTRBlockDeadPlant() {
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int i, int j, int k) {
        return true;
    }

    public ArrayList onSheared(ItemStack item, IBlockAccess world, int i, int j, int k, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack((Block)this));
        return drops;
    }
}

