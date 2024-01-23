/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockMobSpawner
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.tileentity.LOTRTileEntityMobSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTRBlockMobSpawner
extends BlockMobSpawner {
    public LOTRBlockMobSpawner() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabSpawn);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if (entityplayer.capabilities.isCreativeMode) {
            entityplayer.openGui((Object)LOTRMod.instance, 6, world, i, j, k);
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.mob_spawner.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityMobSpawner();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null && tileentity instanceof LOTRTileEntityMobSpawner) {
            LOTRTileEntityMobSpawner spawner = (LOTRTileEntityMobSpawner)tileentity;
            return new ItemStack((Block)this, 1, LOTREntities.getIDFromString(spawner.getEntityClassName()));
        }
        return null;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getMobSpawnerRenderID();
    }
}

