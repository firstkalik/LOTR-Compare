/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlowerPot
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
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
import lotr.common.block.LOTRBlockFlower;
import lotr.common.tileentity.LOTRTileEntityFlowerPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockFlowerPot
extends BlockFlowerPot
implements ITileEntityProvider {
    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityFlowerPot();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.flower_pot.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public int getRenderType() {
        return LOTRMod.proxy.getFlowerPotRenderID();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k) {
        return LOTRBlockFlowerPot.getPlant((IBlockAccess)world, i, j, k);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        return false;
    }

    public void onBlockHarvested(World world, int i, int j, int k, int meta, EntityPlayer entityplayer) {
        if (entityplayer.capabilities.isCreativeMode) {
            world.setBlockMetadataWithNotify(i, j, k, meta |= 8, 4);
        }
        super.onBlockHarvested(world, i, j, k, meta, entityplayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ItemStack itemstack;
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(Items.flower_pot));
        if ((meta & 8) == 0 && (itemstack = LOTRBlockFlowerPot.getPlant((IBlockAccess)world, i, j, k)) != null && (LOTRTileEntityFlowerPot)world.getTileEntity(i, j, k) != null) {
            drops.add(itemstack);
        }
        return drops;
    }

    public static boolean canAcceptPlant(ItemStack itemstack) {
        Item item = itemstack.getItem();
        if (item instanceof ItemBlock) {
            Block block = ((ItemBlock)item).field_150939_a;
            return block instanceof LOTRBlockFlower;
        }
        return false;
    }

    public static void setPlant(World world, int i, int j, int k, ItemStack itemstack) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityFlowerPot) {
            LOTRTileEntityFlowerPot flowerPot = (LOTRTileEntityFlowerPot)tileentity;
            flowerPot.item = itemstack.getItem();
            flowerPot.meta = itemstack.getItemDamage();
            world.markBlockForUpdate(i, j, k);
        }
    }

    public static ItemStack getPlant(IBlockAccess world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null && tileentity instanceof LOTRTileEntityFlowerPot) {
            LOTRTileEntityFlowerPot flowerPot = (LOTRTileEntityFlowerPot)tileentity;
            if (flowerPot.item == null) {
                return null;
            }
            return new ItemStack(flowerPot.item, 1, flowerPot.meta);
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (LOTRBlockFlowerPot.getPlant((IBlockAccess)world, i, j, k) != null && LOTRBlockFlowerPot.getPlant((IBlockAccess)world, i, j, k).getItem() == Item.getItemFromBlock((Block)LOTRMod.pipeweedPlant)) {
            double d = (double)i + 0.2 + (double)(random.nextFloat() * 0.6f);
            double d1 = (double)j + 0.625 + (double)(random.nextFloat() * 0.1875f);
            double d2 = (double)k + 0.2 + (double)(random.nextFloat() * 0.6f);
            world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
        }
        if (LOTRBlockFlowerPot.getPlant((IBlockAccess)world, i, j, k) != null && LOTRBlockFlowerPot.getPlant((IBlockAccess)world, i, j, k).getItem() == Item.getItemFromBlock((Block)LOTRMod.corruptMallorn)) {
            LOTRMod.corruptMallorn.randomDisplayTick(world, i, j, k, random);
        }
    }
}

