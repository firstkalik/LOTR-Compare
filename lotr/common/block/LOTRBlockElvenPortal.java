/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockPortal;
import lotr.common.fac.LOTRFaction;
import lotr.common.tileentity.LOTRTileEntityElvenPortal;
import lotr.common.world.LOTRTeleporterElvenPortal;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRBlockElvenPortal
extends LOTRBlockPortal {
    public LOTRBlockElvenPortal() {
        super(new LOTRFaction[]{LOTRFaction.LOTHLORIEN, LOTRFaction.HIGH_ELF}, LOTRTeleporterElvenPortal.class);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityElvenPortal();
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(3) == 0) {
            double d = (float)i + random.nextFloat();
            double d1 = (double)j + 0.8;
            double d2 = (float)k + random.nextFloat();
            LOTRMod.proxy.spawnParticle("elvenGlow", d, d1, d2, 0.0, 0.3, 0.0);
        }
        super.randomDisplayTick(world, i, j, k, random);
    }

    @Override
    public void setPlayerInPortal(EntityPlayer entityplayer) {
        LOTRMod.proxy.setInElvenPortal(entityplayer);
        if (!entityplayer.worldObj.isRemote) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.useElvenPortal);
        }
    }

    @Override
    public boolean isValidPortalLocation(World world, int i, int j, int k, boolean portalAlreadyMade) {
        for (int i1 = i - 2; i1 <= i + 2; ++i1) {
            for (int k1 = k - 2; k1 <= k + 2; ++k1) {
                if (Math.abs(i1 - i) == 2 && Math.abs(k1 - k) == 2) continue;
                if (Math.abs(i1 - i) == 2 || Math.abs(k1 - k) == 2) {
                    if (world.getBlock(i1, j, k1) == LOTRMod.quenditeGrass) continue;
                } else if (world.getBlock(i1, j, k1) == (portalAlreadyMade ? LOTRMod.elvenPortal : Blocks.water) && LOTRMod.isOpaque(world, i1, j - 1, k1)) continue;
                return false;
            }
        }
        return true;
    }
}

