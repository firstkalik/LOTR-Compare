/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.IShearable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockGrass
extends BlockBush
implements IShearable {
    private boolean isSandy;

    public LOTRBlockGrass() {
        super(Material.vine);
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeGrass);
    }

    public LOTRBlockGrass setSandy() {
        this.isSandy = true;
        return this;
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        Block below = world.getBlock(i, j - 1, k);
        if (this.isSandy && below.getMaterial() == Material.sand && below.isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP)) {
            return true;
        }
        return below.canSustainPlant((IBlockAccess)world, i, j, k, ForgeDirection.UP, (IPlantable)this);
    }

    public boolean isReplaceable(IBlockAccess world, int i, int j, int k) {
        return true;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getGrassRenderID();
    }

    public int quantityDroppedWithBonus(int i, Random random) {
        return Blocks.tallgrass.quantityDroppedWithBonus(i, random);
    }

    public ArrayList getDrops(World world, int i, int j, int k, int meta, int fortune) {
        return Blocks.tallgrass.getDrops(world, i, j, k, meta, fortune);
    }

    public int getDamageValue(World world, int i, int j, int k) {
        return world.getBlockMetadata(i, j, k);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int i, int j, int k) {
        return true;
    }

    public ArrayList onSheared(ItemStack item, IBlockAccess world, int i, int j, int k, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack((Block)this, 1, world.getBlockMetadata(i, j, k)));
        return list;
    }
}

