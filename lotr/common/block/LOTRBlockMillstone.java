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
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityMillstone;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockMillstone
extends BlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSideActive;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTopActive;

    public LOTRBlockMillstone() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        this.setHardness(4.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityMillstone();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        boolean active = LOTRBlockMillstone.isMillstoneActive(world, i, j, k);
        if (side == 1 || side == 0) {
            return active ? this.iconTopActive : this.iconTop;
        }
        return active ? this.iconSideActive : this.iconSide;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return i == 1 || i == 0 ? this.iconTop : this.iconSide;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.iconSide = iconregister.registerIcon(this.getTextureName() + "_side");
        this.iconTop = iconregister.registerIcon(this.getTextureName() + "_top");
        this.iconSideActive = iconregister.registerIcon(this.getTextureName() + "_side_active");
        this.iconTopActive = iconregister.registerIcon(this.getTextureName() + "_top_active");
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if (!world.isRemote) {
            entityplayer.openGui((Object)LOTRMod.instance, 52, world, i, j, k);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (LOTRBlockMillstone.isMillstoneActive((IBlockAccess)world, i, j, k)) {
            for (int l = 0; l < 6; ++l) {
                float f10 = 0.5f + MathHelper.randomFloatClamp((Random)random, (float)-0.2f, (float)0.2f);
                float f11 = 0.5f + MathHelper.randomFloatClamp((Random)random, (float)-0.2f, (float)0.2f);
                float f12 = 0.9f + random.nextFloat() * 0.2f;
                world.spawnParticle("smoke", (double)((float)i + f10), (double)((float)j + f12), (double)((float)k + f11), 0.0, 0.0, 0.0);
            }
        }
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack itemstack) {
        if (itemstack.hasDisplayName()) {
            ((LOTRTileEntityMillstone)world.getTileEntity(i, j, k)).setSpecialMillstoneName(itemstack.getDisplayName());
        }
    }

    public static boolean isMillstoneActive(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        return (meta & 8) != 0;
    }

    public static void toggleMillstoneActive(World world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        world.setBlockMetadataWithNotify(i, j, k, meta ^ 8, 2);
    }

    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        LOTRTileEntityMillstone millstone = (LOTRTileEntityMillstone)world.getTileEntity(i, j, k);
        if (millstone != null) {
            LOTRMod.dropContainerItems(millstone, world, i, j, k);
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

