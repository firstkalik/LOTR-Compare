/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 */
package lotr.common.world;

import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityPortal;
import lotr.common.world.LOTRWorldProviderMiddleEarth;
import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class LOTRTeleporter
extends Teleporter {
    private WorldServer world;
    private boolean makeRingPortal;

    public LOTRTeleporter(WorldServer worldserver, boolean flag) {
        super(worldserver);
        this.world = worldserver;
        this.makeRingPortal = flag;
    }

    public void placeInPortal(Entity entity, double d, double d1, double d2, float f) {
        int j;
        int i;
        int k;
        if (this.world.provider.dimensionId == LOTRDimension.MIDDLE_EARTH.dimensionID) {
            i = 0;
            k = 0;
            j = LOTRMod.getTrueTopBlock((World)this.world, i, k);
        } else {
            i = LOTRLevelData.overworldPortalX;
            k = LOTRLevelData.overworldPortalZ;
            j = LOTRLevelData.overworldPortalY;
        }
        entity.setLocationAndAngles((double)i + 0.5, (double)j + 1.0, (double)k + 0.5, entity.rotationYaw, 0.0f);
        if (this.world.provider.dimensionId == LOTRDimension.MIDDLE_EARTH.dimensionID && LOTRLevelData.madeMiddleEarthPortal == 0) {
            LOTRLevelData.setMadeMiddleEarthPortal(1);
            if (this.makeRingPortal) {
                if (this.world.provider instanceof LOTRWorldProviderMiddleEarth) {
                    ((LOTRWorldProviderMiddleEarth)this.world.provider).setRingPortalLocation(i, j, k);
                }
                LOTREntityPortal portal = new LOTREntityPortal((World)this.world);
                portal.setLocationAndAngles((double)i + 0.5, (double)j + 3.5, (double)k + 0.5, 0.0f, 0.0f);
                this.world.spawnEntityInWorld((Entity)portal);
            }
        }
    }
}

