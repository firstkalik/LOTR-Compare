/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockChest
extends BlockContainer {
    private Block baseBlock;
    private int baseMeta;
    private String chestTextureName;

    public LOTRBlockChest(Material m, Block b, int i, String s) {
        super(m);
        this.baseBlock = b;
        this.baseMeta = i;
        this.setStepSound(b.stepSound);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
        this.chestTextureName = s;
    }

    public TileEntity createNewTileEntity(World world, int i) {
        LOTRTileEntityChest chest = new LOTRTileEntityChest();
        chest.textureName = this.getChestTextureName();
        return chest;
    }

    public String getChestTextureName() {
        return this.chestTextureName;
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if (world.isSideSolid(i, j + 1, k, ForgeDirection.DOWN)) {
            return false;
        }
        if (!world.isRemote) {
            entityplayer.openGui((Object)LOTRMod.instance, 41, world, i, j, k);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.baseBlock.getIcon(i, this.baseMeta);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getChestRenderID();
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        this.setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k) {
        if (!world.isRemote) {
            Block i1 = world.getBlock(i, j, k - 1);
            Block j1 = world.getBlock(i, j, k + 1);
            Block k1 = world.getBlock(i - 1, j, k);
            Block l1 = world.getBlock(i + 1, j, k);
            int meta = 3;
            if (i1.isOpaqueCube() && !j1.isOpaqueCube()) {
                meta = 3;
            }
            if (j1.isOpaqueCube() && !i1.isOpaqueCube()) {
                meta = 2;
            }
            if (k1.isOpaqueCube() && !l1.isOpaqueCube()) {
                meta = 5;
            }
            if (l1.isOpaqueCube() && !k1.isOpaqueCube()) {
                meta = 4;
            }
            world.setBlockMetadataWithNotify(i, j, k, meta, 2);
        }
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack itemstack) {
        int meta = 0;
        int l = MathHelper.floor_double((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            meta = 2;
        }
        if (l == 1) {
            meta = 5;
        }
        if (l == 2) {
            meta = 3;
        }
        if (l == 3) {
            meta = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, meta, 3);
        if (itemstack.hasDisplayName()) {
            ((LOTRTileEntityChest)world.getTileEntity(i, j, k)).setCustomName(itemstack.getDisplayName());
        }
    }

    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        LOTRTileEntityChest chest = (LOTRTileEntityChest)world.getTileEntity(i, j, k);
        if (chest != null) {
            LOTRMod.dropContainerItems(chest, world, i, j, k);
            world.func_147453_f(i, j, k, block);
        }
        super.breakBlock(world, i, j, k, block, meta);
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int i, int j, int k, int direction) {
        return Container.calcRedstoneFromInventory((IInventory)((IInventory)world.getTileEntity(i, j, k)));
    }
}

