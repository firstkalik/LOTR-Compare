/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockLeavesBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRBlockLeaves7
extends LOTRBlockLeavesBase {
    public LOTRBlockLeaves7() {
        this.setLeafNames("aspen", "greenOak", "lairelosse", "almond");
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)LOTRMod.sapling7);
    }

    @Override
    protected void addSpecialLeafDrops(ArrayList drops, World world, int i, int j, int k, int meta, int fortune) {
        if ((meta & 3) == 3 && world.rand.nextInt(this.calcFortuneModifiedDropChance(12, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.almond));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        super.randomDisplayTick(world, i, j, k, random);
        String s = null;
        int metadata = world.getBlockMetadata(i, j, k) & 3;
        if (metadata == 1 && random.nextInt(150) == 0) {
            s = "leafGreen";
        }
        if (s != null) {
            double d = (float)i + random.nextFloat();
            double d1 = (double)j - 0.05;
            double d2 = (float)k + random.nextFloat();
            double d3 = -0.1 + (double)(random.nextFloat() * 0.2f);
            double d4 = -0.03 - (double)(random.nextFloat() * 0.02f);
            double d5 = -0.1 + (double)(random.nextFloat() * 0.2f);
            LOTRMod.proxy.spawnParticle(s, d, d1, d2, d3, d4, d5);
        }
    }
}

