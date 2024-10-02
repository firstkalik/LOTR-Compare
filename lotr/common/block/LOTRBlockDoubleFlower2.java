/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockLiquid
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
import net.minecraft.block.BlockLiquid;
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

public class LOTRBlockDoubleFlower2
extends BlockDoublePlant {
    public static final String[] flowerNames = new String[]{"tall_seagrass"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] doublePlantBottomIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] doublePlantTopIcons;

    public LOTRBlockDoubleFlower2() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public int getRenderType() {
        return LOTRMod.proxy.getDoublePlantRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        return 16777215;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public int func_149885_e(IBlockAccess world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        return !LOTRBlockDoubleFlower2.isTop(l) ? l & 7 : world.getBlockMetadata(i, j - 1, k) & 7;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        Block blockBelow = world.getBlock(i, j - 1, k);
        return super.canPlaceBlockAt(world, i, j, k) && (blockBelow == Blocks.water || blockBelow == Blocks.flowing_water) && world.isAirBlock(i, j + 1, k);
    }

    protected void checkAndDropBlock(World world, int i, int j, int k) {
        if (!this.canBlockStay(world, i, j, k)) {
            int l = world.getBlockMetadata(i, j, k);
            if (!LOTRBlockDoubleFlower2.isTop(l)) {
                this.dropBlockAsItem(world, i, j, k, l, 0);
                if (world.getBlock(i, j + 1, k) == this) {
                    world.setBlock(i, j + 1, k, Blocks.air, 0, 2);
                }
            }
            world.setBlock(i, j, k, Blocks.air, 0, 2);
        }
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        boolean onWater;
        if (world.getBlock(i, j, k) != this) {
            return super.canBlockStay(world, i, j, k);
        }
        int l = world.getBlockMetadata(i, j, k);
        boolean bl = onWater = world.getBlock(i, j - 1, k) == this || world.getBlock(i, j - 1, k) == Blocks.water || world.getBlock(i, j - 1, k) == Blocks.flowing_water;
        return LOTRBlockDoubleFlower2.isTop(l) ? onWater : onWater && super.canBlockStay(world, i, j, k);
    }

    public Item getItemDropped(int i, Random random, int j) {
        if (LOTRBlockDoubleFlower2.isTop(i)) {
            return null;
        }
        return Item.getItemFromBlock((Block)this);
    }

    public int damageDropped(int i) {
        return LOTRBlockDoubleFlower2.isTop(i) ? 0 : i & 7;
    }

    public static boolean isTop(int i) {
        return (i & 8) != 0;
    }

    public static int getFlowerMeta(int i) {
        return i & 7;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (LOTRBlockDoubleFlower2.isTop(meta)) {
            return this.doublePlantBottomIcons[1];
        }
        int k = meta & 7;
        if (k >= this.doublePlantBottomIcons.length) {
            k = 0;
        }
        return this.doublePlantBottomIcons[k];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149888_a(boolean isTop, int meta) {
        if (isTop) {
            if (meta >= this.doublePlantTopIcons.length) {
                meta = 0;
            }
            return this.doublePlantTopIcons[meta];
        }
        if (meta >= this.doublePlantBottomIcons.length) {
            meta = 0;
        }
        return this.doublePlantBottomIcons[meta];
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack itemstack) {
        int l = ((MathHelper.floor_double((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3) + 2) % 4;
        world.setBlock(i, j + 1, k, (Block)this, 8 | l, 2);
    }

    public void onBlockHarvested(World world, int i, int j, int k, int meta, EntityPlayer player) {
        if (LOTRBlockDoubleFlower2.isTop(meta)) {
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

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.doublePlantBottomIcons = new IIcon[flowerNames.length];
        this.doublePlantTopIcons = new IIcon[flowerNames.length];
        for (int i = 0; i < this.doublePlantBottomIcons.length; ++i) {
            this.doublePlantBottomIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + flowerNames[i] + "_bottom");
            this.doublePlantTopIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + flowerNames[i] + "_top");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.doublePlantBottomIcons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public int getDamageValue(World world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        return LOTRBlockDoubleFlower2.isTop(l) ? LOTRBlockDoubleFlower2.getFlowerMeta(world.getBlockMetadata(i, j - 1, k)) : LOTRBlockDoubleFlower2.getFlowerMeta(l);
    }

    public boolean func_149851_a(World world, int i, int j, int k, boolean flag) {
        return true;
    }

    public boolean func_149852_a(World world, Random random, int i, int j, int k) {
        return true;
    }

    public void func_149853_b(World world, Random random, int i, int j, int k) {
        int meta = this.func_149885_e((IBlockAccess)world, i, j, k);
        this.dropBlockAsItem(world, i, j, k, new ItemStack((Block)this, 1, meta));
    }
}

