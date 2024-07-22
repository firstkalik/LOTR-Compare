/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockForgeBase;
import lotr.common.tileentity.LOTRTileEntityUnsmeltery;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockUnsmeltery
extends LOTRBlockForgeBase {
    @SideOnly(value=Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        return this.getIcon(side, world.getBlockMetadata(i, j, k));
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return Blocks.cobblestone.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityUnsmeltery();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getUnsmelteryRenderID();
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if (!world.isRemote) {
            entityplayer.openGui((Object)LOTRMod.instance, 38, world, i, j, k);
        }
        return true;
    }

    @Override
    protected boolean useLargeSmoke() {
        return false;
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        super.randomDisplayTick(world, i, j, k, random);
        if (LOTRBlockForgeBase.isForgeActive((IBlockAccess)world, i, j, k)) {
            for (int l = 0; l < 3; ++l) {
                float f = (float)i + 0.25f + random.nextFloat() * 0.5f;
                float f1 = (float)j + 0.5f + random.nextFloat() * 0.5f;
                float f2 = (float)k + 0.25f + random.nextFloat() * 0.5f;
                world.spawnParticle("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
            }
        }
    }
}

