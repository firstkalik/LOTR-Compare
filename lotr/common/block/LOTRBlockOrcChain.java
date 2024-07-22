/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockChandelier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockOrcChain
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconMiddle;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSingle;

    public LOTRBlockOrcChain() {
        super(Material.circuits);
        this.setHardness(1.0f);
        this.setStepSound(Block.soundTypeMetal);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        float f = 0.2f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 1.0f, 0.5f + f);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.iconMiddle = iconregister.registerIcon(this.getTextureName() + "_mid");
        this.iconTop = iconregister.registerIcon(this.getTextureName() + "_top");
        this.iconBottom = iconregister.registerIcon(this.getTextureName() + "_bottom");
        this.iconSingle = iconregister.registerIcon(this.getTextureName() + "_single");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        boolean chainBelow;
        Block above = world.getBlock(i, j + 1, k);
        Block below = world.getBlock(i, j - 1, k);
        boolean chainAbove = above instanceof LOTRBlockOrcChain;
        boolean bl = chainBelow = below instanceof LOTRBlockOrcChain || below instanceof LOTRBlockChandelier;
        if (chainAbove && chainBelow) {
            return this.iconMiddle;
        }
        if (chainAbove) {
            return this.iconBottom;
        }
        if (chainBelow) {
            return this.iconTop;
        }
        return this.iconSingle;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.iconMiddle;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getOrcChainRenderID();
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        Block block = world.getBlock(i, j + 1, k);
        int meta = world.getBlockMetadata(i, j + 1, k);
        if (block instanceof LOTRBlockOrcChain) {
            return true;
        }
        if (block instanceof BlockFence || block instanceof BlockWall) {
            return true;
        }
        if (block instanceof BlockSlab && !block.isOpaqueCube() && (meta & 8) == 0) {
            return true;
        }
        if (block instanceof BlockStairs && (meta & 4) == 0) {
            return true;
        }
        return world.getBlock(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return this.canPlaceBlockAt(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            this.dropBlockAsItem(world, i, j, k, meta, 0);
            world.setBlockToAir(i, j, k);
        }
        super.onNeighborBlockChange(world, i, j, k, block);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        ItemStack itemstack = entityplayer.getHeldItem();
        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock((Block)this)) {
            int j1;
            Block block;
            for (j1 = j; j1 >= 0 && j1 < world.getHeight() && (block = world.getBlock(i, j1, k)) == this; --j1) {
            }
            if (j1 >= 0 && j1 < world.getHeight()) {
                block = world.getBlock(i, j1, k);
                if (this.canPlaceBlockOnSide(world, i, j1, k, side) && block.isReplaceable((IBlockAccess)world, i, j1, k) && !block.getMaterial().isLiquid()) {
                    int thisMeta = world.getBlockMetadata(i, j, k);
                    world.setBlock(i, j1, k, (Block)this, thisMeta, 3);
                    world.playSoundEffect((double)((float)i + 0.5f), (double)((float)j1 + 0.5f), (double)((float)k + 0.5f), this.stepSound.func_150496_b(), (this.stepSound.getVolume() + 1.0f) / 2.0f, this.stepSound.getPitch() * 0.8f);
                    if (!entityplayer.capabilities.isCreativeMode) {
                        --itemstack.stackSize;
                    }
                    if (itemstack.stackSize <= 0) {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        float f = 0.01f;
        return AxisAlignedBB.getBoundingBox((double)((float)i + 0.5f - f), (double)j, (double)((float)k + 0.5f - f), (double)((float)i + 0.5f + f), (double)(j + 1), (double)((float)k + 0.5f + f));
    }

    public boolean isLadder(IBlockAccess world, int i, int j, int k, EntityLivingBase entity) {
        return true;
    }
}

