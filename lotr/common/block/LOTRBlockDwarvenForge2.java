/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.block;

import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockForgeBase2;
import lotr.common.tileentity.LOTRTileEntityDwarvenForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRBlockDwarvenForge2
extends LOTRBlockForgeBase2 {
    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityDwarvenForge();
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if (!world.isRemote) {
            entityplayer.openGui((Object)LOTRMod.instance, 5, world, i, j, k);
        }
        return true;
    }

    @Override
    protected boolean useLargeSmoke() {
        return true;
    }
}

