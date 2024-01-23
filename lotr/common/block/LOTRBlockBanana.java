/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockHangingFruit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockBanana
extends LOTRBlockHangingFruit {
    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        int dir = world.getBlockMetadata(i, j, k);
        switch (dir) {
            case 0: {
                this.setBlockBounds(0.375f, 0.1875f, 0.0f, 0.625f, 0.9375f, 0.25f);
                break;
            }
            case 1: {
                this.setBlockBounds(0.375f, 0.1875f, 0.75f, 0.625f, 0.9375f, 1.0f);
                break;
            }
            case 2: {
                this.setBlockBounds(0.0f, 0.1875f, 0.375f, 0.25f, 0.9375f, 0.625f);
                break;
            }
            case 3: {
                this.setBlockBounds(0.75f, 0.1875f, 0.375f, 1.0f, 0.9375f, 0.625f);
            }
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return LOTRMod.banana;
    }

    public boolean removedByPlayer(World world, EntityPlayer entityplayer, int i, int j, int k, boolean willHarvest) {
        boolean flag = super.removedByPlayer(world, entityplayer, i, j, k, willHarvest);
        if (flag && !world.isRemote) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.pickBanana);
        }
        return flag;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        return LOTRMod.banana;
    }
}

