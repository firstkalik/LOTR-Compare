/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockSeaWeed2
extends BlockDoublePlant {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] seaWeedBottomIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] seaWeedTopIcons;

    public LOTRBlockSeaWeed2() {
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 2.0f, 0.875f);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public int getRenderType() {
        return LOTRMod.proxy.getDoublePlantRenderID();
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 2.0f, 0.875f);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return super.canPlaceBlockAt(world, i, j, k) && world.isAirBlock(i, j + 1, k);
    }

    protected void checkAndDropBlock(World world, int i, int j, int k) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            if (!LOTRBlockSeaWeed2.isTop(meta)) {
                this.dropBlockAsItem(world, i, j, k, meta, 0);
                if (world.getBlock(i, j + 1, k) == this) {
                    world.setBlock(i, j + 1, k, Blocks.air, 0, 2);
                }
            }
            world.setBlock(i, j, k, Blocks.air, 0, 2);
        }
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        if (world.getBlock(i, j, k) != this) {
            return super.canBlockStay(world, i, j, k);
        }
        int meta = world.getBlockMetadata(i, j, k);
        return LOTRBlockSeaWeed2.isTop(meta) ? world.getBlock(i, j - 1, k) == this : world.getBlock(i, j + 1, k) == this && super.canBlockStay(world, i, j, k);
    }

    public Item getItemDropped(int i, Random random, int j) {
        if (LOTRBlockSeaWeed2.isTop(i)) {
            return null;
        }
        return Item.getItemFromBlock((Block)this);
    }

    public int damageDropped(int i) {
        return LOTRBlockSeaWeed2.isTop(i) ? 0 : i & 7;
    }

    public static boolean isTop(int i) {
        return (i & 8) != 0;
    }

    public static int getSeaWeedMeta(int i) {
        return i & 7;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (LOTRBlockSeaWeed2.isTop(meta)) {
            return this.seaWeedTopIcons[LOTRBlockSeaWeed2.getSeaWeedMeta(meta)];
        }
        return this.seaWeedBottomIcons[LOTRBlockSeaWeed2.getSeaWeedMeta(meta)];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.seaWeedBottomIcons = new IIcon[1];
        this.seaWeedTopIcons = new IIcon[1];
        this.seaWeedBottomIcons[0] = iconregister.registerIcon(this.getTextureName() + "_bottom");
        this.seaWeedTopIcons[0] = iconregister.registerIcon(this.getTextureName() + "_top");
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack itemstack) {
        int meta = (MathHelper.floor_double((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3) + 2;
        world.setBlock(i, j + 1, k, (Block)this, 8 | meta, 2);
    }

    public void onBlockHarvested(World world, int i, int j, int k, int meta, EntityPlayer player) {
        if (LOTRBlockSeaWeed2.isTop(meta)) {
            if (world.getBlock(i, j - 1, k) == this) {
                if (!player.capabilities.isCreativeMode) {
                    world.func_147480_a(i, j - 1, k, true);
                } else {
                    world.setBlockToAir(i, j - 1, k);
                }
            }
        } else if (player.capabilities.isCreativeMode && world.getBlock(i, j + 1, k) == this) {
            world.setBlock(i, j + 1, k, Blocks.air, 0, 2);
        }
        super.onBlockHarvested(world, i, j, k, meta, player);
    }

    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
    }
}

