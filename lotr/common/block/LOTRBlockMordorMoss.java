/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class LOTRBlockMordorMoss
extends Block
implements IShearable {
    public LOTRBlockMordorMoss() {
        super(Material.plants);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
        this.setTickRandomly(true);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setHardness(0.2f);
        this.setStepSound(Block.soundTypeGrass);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return super.canPlaceBlockAt(world, i, j, k) && this.canBlockStay(world, i, j, k);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        if (j >= 0 && j < 256) {
            return LOTRBiomeGenMordor.isSurfaceMordorBlock(world, i, j - 1, k);
        }
        return false;
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        super.onNeighborBlockChange(world, i, j, k, block);
        this.checkMossCanStay(world, i, j, k);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        this.checkMossCanStay(world, i, j, k);
    }

    private void checkMossCanStay(World world, int i, int j, int k) {
        if (!this.canBlockStay(world, i, j, k)) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public boolean isOpaqueCube() {
        return false;
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

