/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockForgeBase;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.tileentity.LOTRTileEntityMoriaForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRBlockMoriaForge
extends LOTRBlockForgeBase {
    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityMoriaForge();
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        boolean hasRequiredAlignment;
        boolean bl = hasRequiredAlignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.BLUE_MOUNTAINS) >= 3000.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.DURINS_FOLK) >= 3000.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.RED_MOUNTAINS) >= 3000.0f;
        if (hasRequiredAlignment) {
            if (!world.isRemote) {
                entityplayer.openGui((Object)LOTRMod.instance, 5, world, i, j, k);
            } else {
                for (int l = 0; l < 8; ++l) {
                    double d = (float)i + world.rand.nextFloat();
                    double d1 = (double)j + 1.0;
                    double d2 = (float)k + world.rand.nextFloat();
                    world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
                }
                if (!world.isRemote) {
                    LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 3000.0f, LOTRFaction.BLUE_MOUNTAINS, LOTRFaction.DURINS_FOLK, LOTRFaction.RED_MOUNTAINS);
                }
            }
            return true;
        }
        for (int l = 0; l < 8; ++l) {
            double d = (float)i + world.rand.nextFloat();
            double d1 = (double)j + 1.0;
            double d2 = (float)k + world.rand.nextFloat();
            world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
        }
        if (!world.isRemote) {
            LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 3000.0f, LOTRFaction.BLUE_MOUNTAINS, LOTRFaction.DURINS_FOLK, LOTRFaction.RED_MOUNTAINS);
        }
        return true;
    }

    @Override
    protected boolean useLargeSmoke() {
        return true;
    }
}

