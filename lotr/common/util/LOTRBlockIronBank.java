/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.dispenser.PositionImpl
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockIronBank
extends Block {
    @SideOnly(value=Side.CLIENT)
    protected IIcon field_149944_M;
    @SideOnly(value=Side.CLIENT)
    protected IIcon field_149945_N;
    @SideOnly(value=Side.CLIENT)
    protected IIcon field_149946_O;
    public int guibank;
    public String koek;

    public LOTRBlockIronBank(int guiIdBank, String string) {
        super(Material.rock);
        this.setHardness(30.0f);
        this.setResistance(140.0f);
        this.setLightLevel(150.0f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.guibank = guiIdBank;
        this.koek = string;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            LOTRCommonProxy.sendClientsideGUI((EntityPlayerMP)player, this.guibank, 0, 0, 0);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        int k = p_149691_2_ & 7;
        return p_149691_1_ == k ? (k != 1 && k != 0 ? this.field_149945_N : this.field_149946_O) : (k != 1 && k != 0 ? (p_149691_1_ != 1 && p_149691_1_ != 0 ? this.blockIcon : this.field_149944_M) : this.field_149944_M);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(this.koek);
        this.field_149944_M = p_149651_1_.registerIcon(this.koek);
        this.field_149945_N = p_149651_1_.registerIcon(this.getTextureName() + "_front_horizontal");
        this.field_149946_O = p_149651_1_.registerIcon(this.getTextureName() + "_front_vertical");
    }

    public static IPosition func_149939_a(IBlockSource p_149939_0_) {
        EnumFacing enumfacing = LOTRBlockIronBank.func_149937_b(p_149939_0_.getBlockMetadata());
        double d0 = p_149939_0_.getX() + 0.7 * (double)enumfacing.getFrontOffsetX();
        double d1 = p_149939_0_.getY() + 0.7 * (double)enumfacing.getFrontOffsetY();
        double d2 = p_149939_0_.getZ() + 0.7 * (double)enumfacing.getFrontOffsetZ();
        return new PositionImpl(d0, d1, d2);
    }

    public static EnumFacing func_149937_b(int p_149937_0_) {
        return EnumFacing.getFront((int)(p_149937_0_ & 7));
    }

    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = BlockPistonBase.determineOrientation((World)p_149689_1_, (int)p_149689_2_, (int)p_149689_3_, (int)p_149689_4_, (EntityLivingBase)p_149689_5_);
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
    }
}

