/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.entity.item.LOTREntityBarrel;
import lotr.common.tileentity.LOTRTileEntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTRItemBarrel
extends ItemBlock {
    public LOTRItemBarrel(Block block) {
        super(block);
    }

    public static void setBarrelData(ItemStack itemstack, NBTTagCompound nbt) {
        itemstack.setTagCompound(new NBTTagCompound());
        itemstack.getTagCompound().setTag("LOTRBarrelData", (NBTBase)nbt);
    }

    public static NBTTagCompound getBarrelData(ItemStack itemstack) {
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("LOTRBarrelData")) {
            NBTTagCompound barrelData = itemstack.getTagCompound().getCompoundTag("LOTRBarrelData");
            return barrelData;
        }
        return null;
    }

    public static void setBarrelDataFromTE(ItemStack itemstack, LOTRTileEntityBarrel barrel) {
        NBTTagCompound nbt = new NBTTagCompound();
        barrel.writeBarrelToNBT(nbt);
        LOTRItemBarrel.setBarrelData(itemstack, nbt);
    }

    public static void loadBarrelDataToTE(ItemStack itemstack, LOTRTileEntityBarrel barrel) {
        NBTTagCompound nbt = LOTRItemBarrel.getBarrelData(itemstack);
        if (nbt != null) {
            barrel.readBarrelFromNBT(nbt);
        }
    }

    public int getItemStackLimit(ItemStack itemstack) {
        NBTTagCompound nbt = LOTRItemBarrel.getBarrelData(itemstack);
        if (nbt != null) {
            return 1;
        }
        return super.getItemStackLimit(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        NBTTagCompound barrelData = LOTRItemBarrel.getBarrelData(itemstack);
        if (barrelData != null) {
            LOTRTileEntityBarrel tileEntity = new LOTRTileEntityBarrel();
            tileEntity.readBarrelFromNBT(barrelData);
            list.add(tileEntity.getInvSubtitle());
        }
    }

    public boolean placeBlockAt(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2, int metadata) {
        if (super.placeBlockAt(itemstack, entityplayer, world, i, j, k, side, f, f1, f2, metadata)) {
            TileEntity tileentity = world.getTileEntity(i, j, k);
            if (tileentity instanceof LOTRTileEntityBarrel) {
                LOTRTileEntityBarrel barrel = (LOTRTileEntityBarrel)tileentity;
                LOTRItemBarrel.loadBarrelDataToTE(itemstack, barrel);
            }
            return true;
        }
        return false;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        MovingObjectPosition m = this.getMovingObjectPositionFromPlayer(world, entityplayer, true);
        if (m == null) {
            return itemstack;
        }
        if (m.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int i = m.blockX;
            int j = m.blockY;
            int k = m.blockZ;
            if (world.getBlock(i, j, k).getMaterial() != Material.water || world.getBlockMetadata(i, j, k) != 0) {
                return itemstack;
            }
            LOTREntityBarrel barrel = new LOTREntityBarrel(world, (float)i + 0.5f, (float)j + 1.0f, (float)k + 0.5f);
            barrel.rotationYaw = (float)((MathHelper.floor_double((double)((double)(entityplayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3) - 1) * 90.0f;
            if (!world.getCollidingBoundingBoxes((Entity)barrel, barrel.boundingBox.expand(-0.1, -0.1, -0.1)).isEmpty()) {
                return itemstack;
            }
            if (!world.isRemote) {
                barrel.barrelItemData = LOTRItemBarrel.getBarrelData(itemstack);
                world.spawnEntityInWorld((Entity)barrel);
            }
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
        }
        return itemstack;
    }
}

