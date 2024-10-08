/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockLeaves
extends LOTRBlockLeavesBase {
    public LOTRBlockLeaves() {
        this.setLeafNames("shirePine", "mallorn", "mirkOak", "mirkOakRed");
        this.setSeasonal(false, true, false, false);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        super.randomDisplayTick(world, i, j, k, random);
        String s = null;
        int metadata = world.getBlockMetadata(i, j, k) & 3;
        if (metadata == 1 && random.nextInt(75) == 0) {
            s = "leafGold";
        } else if (metadata == 2 && random.nextInt(250) == 0) {
            s = "leafMirk";
        } else if (metadata == 3 && random.nextInt(40) == 0) {
            s = "leafRed";
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

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)LOTRMod.sapling);
    }

    @Override
    protected void addSpecialLeafDrops(ArrayList drops, World world, int i, int j, int k, int meta, int fortune) {
        if ((meta & 3) == 1 && world.rand.nextInt(this.calcFortuneModifiedDropChance(100, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.mallornNut));
        }
    }

    public int getLightOpacity(IBlockAccess world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k) & 3;
        if (l == 2 || l == 3) {
            return 255;
        }
        return super.getLightOpacity(world, i, j, k);
    }
}

