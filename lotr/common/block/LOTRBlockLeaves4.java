/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRBlockLeaves4
extends LOTRBlockLeavesBase {
    public LOTRBlockLeaves4() {
        this.setLeafNames("chestnut", "baobab", "cedar", "fir");
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        super.updateTick(world, i, j, k, random);
        if (!world.isRemote && world.getBlock(i, j, k) == this) {
            boolean playerPlaced;
            int meta = world.getBlockMetadata(i, j, k);
            int leafType = meta & 3;
            boolean bl = playerPlaced = (meta & 4) != 0;
            if (leafType == 0 && !playerPlaced && world.isAirBlock(i, j - 1, k) && random.nextInt(300) == 0) {
                double d = (double)i + random.nextDouble();
                double d1 = (double)j - 0.2;
                double d2 = (double)k + random.nextDouble();
                EntityItem conker = new EntityItem(world, d, d1, d2, new ItemStack(LOTRMod.chestnut));
                conker.delayBeforeCanPickup = 10;
                conker.motionZ = 0.0;
                conker.motionY = 0.0;
                conker.motionX = 0.0;
                world.spawnEntityInWorld((Entity)conker);
            }
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)LOTRMod.sapling4);
    }

    @Override
    protected void addSpecialLeafDrops(ArrayList drops, World world, int i, int j, int k, int meta, int fortune) {
        if ((meta & 3) == 0 && world.rand.nextInt(this.calcFortuneModifiedDropChance(20, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.chestnut));
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = super.getDrops(world, i, j, k, meta, fortune);
        if ((meta & 3) == 3 && LOTRMod.isChristmas()) {
            for (ItemStack itemstack : drops) {
                if (world.rand.nextInt(3) != 0 || itemstack.getItem() != Item.getItemFromBlock((Block)LOTRMod.sapling4)) continue;
                itemstack.setStackDisplayName("Christmas Tree");
            }
        }
        return drops;
    }
}

