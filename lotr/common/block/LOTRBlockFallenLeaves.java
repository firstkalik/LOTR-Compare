/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockFallenLeaves
extends Block
implements IShearable {
    public static List<LOTRBlockFallenLeaves> allFallenLeaves = new ArrayList<LOTRBlockFallenLeaves>();
    private static Random leafRand = new Random();
    private Block[] leafBlocks;

    public LOTRBlockFallenLeaves() {
        super(Material.vine);
        allFallenLeaves.add(this);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setHardness(0.2f);
        this.setStepSound(Block.soundTypeGrass);
        this.useNeighborBrightness = true;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
    }

    public static void assignLeaves(Block fallenLeaves, Block ... leaves) {
        ((LOTRBlockFallenLeaves)fallenLeaves).leafBlocks = leaves;
    }

    public Block[] getLeafBlocks() {
        return this.leafBlocks;
    }

    public Object[] leafBlockMetaFromFallenMeta(int meta) {
        Block leaf = this.leafBlocks[meta / 4];
        int leafMeta = meta & 3;
        return new Object[]{leaf, leafMeta};
    }

    public static Object[] fallenBlockMetaFromLeafBlockMeta(Block block, int meta) {
        meta &= 3;
        for (LOTRBlockFallenLeaves fallenLeaves : allFallenLeaves) {
            for (int i = 0; i < fallenLeaves.leafBlocks.length; ++i) {
                Block leafBlock = fallenLeaves.leafBlocks[i];
                if (leafBlock != block) continue;
                return new Object[]{fallenLeaves, i * 4 + meta};
            }
        }
        return null;
    }

    public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB bb, List boxes, Entity entity) {
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getFallenLeavesRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        Object[] obj = this.leafBlockMetaFromFallenMeta(j);
        return ((Block)obj[0]).getIcon(i, ((Integer)obj[1]).intValue());
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int i) {
        Object[] obj = this.leafBlockMetaFromFallenMeta(i);
        return ((Block)obj[0]).getRenderColor(((Integer)obj[1]).intValue());
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        Object[] obj = this.leafBlockMetaFromFallenMeta(meta);
        return ((Block)obj[0]).colorMultiplier(world, i, j, k);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        Block below = world.getBlock(i, j - 1, k);
        int belowMeta = world.getBlockMetadata(i, j - 1, k);
        if (below.getMaterial() == Material.water && belowMeta == 0) {
            return true;
        }
        return below.isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        Block block = world.getBlock(i, j, k);
        if (block.getMaterial().isLiquid()) {
            return false;
        }
        return this.canBlockStay(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public int damageDropped(int i) {
        return i;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int i, int j, int k) {
        return true;
    }

    public ArrayList onSheared(ItemStack item, IBlockAccess world, int i, int j, int k, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack((Block)this, 1, world.getBlockMetadata(i, j, k)));
        return drops;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.leafBlocks.length; ++i) {
            Block leaf = this.leafBlocks[i];
            ArrayList leafTypes = new ArrayList();
            leaf.getSubBlocks(Item.getItemFromBlock((Block)leaf), leaf.getCreativeTabToDisplayOn(), leafTypes);
            for (ItemStack leafItem : leafTypes) {
                int meta = leafItem.getItemDamage();
                list.add(new ItemStack(item, 1, i * 4 + meta));
            }
        }
    }
}

