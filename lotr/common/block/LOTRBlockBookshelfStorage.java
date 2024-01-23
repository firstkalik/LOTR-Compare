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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityBookshelf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockBookshelfStorage
extends BlockContainer {
    public LOTRBlockBookshelfStorage() {
        super(Material.wood);
        this.setHardness(1.5f);
        this.setStepSound(Block.soundTypeWood);
        this.setCreativeTab(null);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityBookshelf();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.bookshelf.getIcon(i, j);
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        return Blocks.bookshelf.getDrops(world, i, j, k, meta, fortune);
    }

    public static boolean canOpenBookshelf(World world, int i, int j, int k, EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        return itemstack == null || itemstack.getItem() != Item.getItemFromBlock((Block)Blocks.bookshelf);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if (!LOTRBlockBookshelfStorage.canOpenBookshelf(world, i, j, k, entityplayer)) {
            return false;
        }
        if (!world.isRemote) {
            entityplayer.openGui((Object)LOTRMod.instance, 55, world, i, j, k);
        }
        return true;
    }

    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        LOTRTileEntityBookshelf bookshelf = (LOTRTileEntityBookshelf)world.getTileEntity(i, j, k);
        if (bookshelf != null) {
            LOTRMod.dropContainerItems(bookshelf, world, i, j, k);
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

    protected boolean canSilkHarvest() {
        return true;
    }

    protected ItemStack createStackedBlock(int i) {
        return new ItemStack(Blocks.bookshelf);
    }
}

