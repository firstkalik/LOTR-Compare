/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemAnimalJar;
import lotr.common.tileentity.LOTRTileEntityAnimalJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class LOTRBlockAnimalJar
extends BlockContainer {
    public LOTRBlockAnimalJar(Material material) {
        super(material);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public abstract boolean canCapture(Entity var1);

    public abstract float getJarEntityHeight();

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityAnimalJar();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return this.canBlockStay(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            this.dropBlockAsItem(world, i, j, k, meta, 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public void onBlockHarvested(World world, int i, int j, int k, int meta, EntityPlayer entityplayer) {
        if (entityplayer.capabilities.isCreativeMode) {
            world.setBlockMetadataWithNotify(i, j, k, meta |= 8, 4);
        }
        this.dropBlockAsItem(world, i, j, k, meta, 0);
        super.onBlockHarvested(world, i, j, k, meta, entityplayer);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public int damageDropped(int i) {
        return i;
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((meta & 8) == 0) {
            ItemStack itemstack = this.getJarDrop(world, i, j, k, meta);
            LOTRTileEntityAnimalJar jar = (LOTRTileEntityAnimalJar)world.getTileEntity(i, j, k);
            if (jar != null) {
                drops.add(itemstack);
            }
        }
        return drops;
    }

    public ItemStack getJarDrop(World world, int i, int j, int k, int metadata) {
        ItemStack itemstack = new ItemStack(Item.getItemFromBlock((Block)this), 1, this.damageDropped(metadata));
        LOTRTileEntityAnimalJar jar = (LOTRTileEntityAnimalJar)world.getTileEntity(i, j, k);
        if (jar != null) {
            LOTRItemAnimalJar.setEntityData(itemstack, jar.getEntityData());
        }
        return itemstack;
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        LOTRTileEntityAnimalJar jar;
        int light;
        TileEntity te = world.getTileEntity(i, j, k);
        if (te instanceof LOTRTileEntityAnimalJar && (light = (jar = (LOTRTileEntityAnimalJar)te).getLightValue()) > 0) {
            return light;
        }
        return super.getLightValue(world, i, j, k);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k) {
        world.markBlockForUpdate(i, j, k);
        return this.getJarDrop(world, i, j, k, world.getBlockMetadata(i, j, k));
    }
}

