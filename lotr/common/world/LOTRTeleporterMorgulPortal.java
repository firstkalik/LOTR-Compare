/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.LongHashMap
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.Teleporter$PortalPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package lotr.common.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class LOTRTeleporterMorgulPortal
extends Teleporter {
    private final WorldServer theWorld;
    private final LongHashMap portalPositions = new LongHashMap();
    private final List portalTimes = new ArrayList();
    private Random rand = new Random();

    public LOTRTeleporterMorgulPortal(WorldServer world) {
        super(world);
        this.theWorld = world;
    }

    public void placeInPortal(Entity entity, double d, double d1, double d2, float f) {
        if (!this.placeInExistingPortal(entity, d, d1, d2, f)) {
            this.makePortal(entity);
            this.placeInExistingPortal(entity, d, d1, d2, f);
        }
    }

    public boolean placeInExistingPortal(Entity entity, double d, double d1, double d2, float f) {
        int range = 128;
        double distanceToPortal = -1.0;
        int i = 0;
        int j = 0;
        int k = 0;
        int posX = MathHelper.floor_double((double)entity.posX);
        int posZ = MathHelper.floor_double((double)entity.posZ);
        long chunkLocation = ChunkCoordIntPair.chunkXZ2Int((int)posX, (int)posZ);
        boolean isChunkLocationInPortalPositions = true;
        if (this.portalPositions.containsItem(chunkLocation)) {
            Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.portalPositions.getValueByKey(chunkLocation);
            distanceToPortal = 0.0;
            i = portalposition.posX;
            j = portalposition.posY;
            k = portalposition.posZ;
            portalposition.lastUpdateTime = this.theWorld.getTotalWorldTime();
            isChunkLocationInPortalPositions = false;
        } else {
            for (int i1 = posX - range; i1 <= posX + range; ++i1) {
                double xDistance = (double)i1 + 0.5 - entity.posX;
                for (int k1 = posZ - range; k1 <= posZ + range; ++k1) {
                    double zDistance = (double)k1 + 0.5 - entity.posZ;
                    for (int j1 = this.theWorld.getActualHeight() - 1; j1 >= 0; --j1) {
                        boolean portalHere = true;
                        for (int i2 = i1 - 1; i2 <= i1 + 1; ++i2) {
                            for (int k2 = k1 - 1; k2 <= k1 + 1; ++k2) {
                                if (this.theWorld.getBlock(i2, j1, k2) == LOTRMod.morgulPortal) continue;
                                portalHere = false;
                            }
                        }
                        if (!portalHere) continue;
                        double yDistance = (double)j1 + 0.5 - entity.posY;
                        double distanceSq = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;
                        if (!(distanceToPortal < 0.0) && !(distanceSq < distanceToPortal)) continue;
                        distanceToPortal = distanceSq;
                        i = i1;
                        j = j1;
                        k = k1;
                    }
                }
            }
        }
        if (distanceToPortal >= 0.0) {
            if (isChunkLocationInPortalPositions) {
                this.portalPositions.add(chunkLocation, (Object)new Teleporter.PortalPosition((Teleporter)this, i, j, k, this.theWorld.getTotalWorldTime()));
                this.portalTimes.add(chunkLocation);
            }
            int side = this.rand.nextInt(4);
            switch (side) {
                case 0: {
                    entity.setLocationAndAngles((double)(i - 2) + 0.5, (double)(j + 1), (double)(k - 1) + 0.25 + (double)(this.rand.nextFloat() * 2.0f), entity.rotationYaw, entity.rotationPitch);
                    break;
                }
                case 1: {
                    entity.setLocationAndAngles((double)(i + 2) + 0.5, (double)(j + 1), (double)(k - 1) + 0.25 + (double)(this.rand.nextFloat() * 2.0f), entity.rotationYaw, entity.rotationPitch);
                    break;
                }
                case 2: {
                    entity.setLocationAndAngles((double)(i - 1) + 0.25 + (double)(this.rand.nextFloat() * 2.0f), (double)(j + 1), (double)(k - 2) + 0.25, entity.rotationYaw, entity.rotationPitch);
                    break;
                }
                case 3: {
                    entity.setLocationAndAngles((double)(i - 1) + 0.25 + (double)(this.rand.nextFloat() * 2.0f), (double)(j + 1), (double)(k + 2) + 0.25, entity.rotationYaw, entity.rotationPitch);
                }
            }
            return true;
        }
        return false;
    }

    public boolean makePortal(Entity entity) {
        int i1;
        int j2;
        int i2;
        int j1;
        int k2;
        int k1;
        int range = 16;
        double distanceToPortal = -1.0;
        int i = MathHelper.floor_double((double)entity.posX);
        int j = MathHelper.floor_double((double)entity.posY);
        int k = MathHelper.floor_double((double)entity.posZ);
        int posX = i;
        int posY = j;
        int posZ = k;
        for (int i12 = i - range; i12 <= i + range; ++i12) {
            double xDistance = (double)i12 + 0.5 - entity.posX;
            for (int k12 = k - range; k12 <= k + range; ++k12) {
                double zDistance = (double)k12 + 0.5 - entity.posZ;
                block2: for (j1 = this.theWorld.getActualHeight() - 1; j1 >= 0; --j1) {
                    if (!this.theWorld.isAirBlock(i12, j1, k12)) continue;
                    while (j1 > 0 && this.theWorld.isAirBlock(i12, j1 - 1, k12)) {
                        --j1;
                    }
                    for (i2 = i12 - 2; i2 <= i12 + 2; ++i2) {
                        for (int k22 = k12 - 2; k22 <= k12 + 2; ++k22) {
                            for (int j22 = -1; j22 <= 3; ++j22) {
                                int j3 = j1 + j22;
                                if (j22 < 0 && !LOTRMod.isOpaque((World)this.theWorld, i2, j3, k22) || j22 >= 0 && !this.theWorld.isAirBlock(i2, j3, k22)) continue block2;
                            }
                        }
                    }
                    double yDistance = (double)j1 + 0.5 - entity.posY;
                    double distanceSq = xDistance * xDistance + yDistance * yDistance + zDistance * zDistance;
                    if (!(distanceToPortal < 0.0) && !(distanceSq < distanceToPortal)) continue;
                    distanceToPortal = distanceSq;
                    posX = i12;
                    posY = j1;
                    posZ = k12;
                }
            }
        }
        int actualPosX = posX;
        int actualPosY = posY;
        int actualPosZ = posZ;
        boolean generateRockBelow = false;
        if (distanceToPortal < 0.0) {
            if (posY < 70) {
                posY = 70;
            }
            if (posY > this.theWorld.getActualHeight() - 10) {
                posY = this.theWorld.getActualHeight() - 10;
            }
            actualPosY = posY;
            generateRockBelow = true;
        }
        for (i1 = -2; i1 <= 2; ++i1) {
            for (k1 = -2; k1 <= 2; ++k1) {
                int n = j1 = generateRockBelow ? -1 : 0;
                while (j1 <= 3) {
                    i2 = actualPosX + i1;
                    j2 = actualPosY + j1;
                    k2 = actualPosZ + k1;
                    if (j1 > 0) {
                        if (Math.abs(i1) == 2 && Math.abs(k1) == 2) {
                            this.theWorld.setBlock(i2, j2, k2, LOTRMod.guldurilBrick, 0, 2);
                        } else {
                            this.theWorld.setBlock(i2, j2, k2, Blocks.air, 0, 2);
                        }
                    } else if (j1 < 0 && generateRockBelow) {
                        this.theWorld.setBlock(i2, j2, k2, LOTRMod.rock, 0, 2);
                    } else {
                        boolean isFrame = i1 == -2 || i1 == 2 || k1 == -2 || k1 == 2;
                        this.theWorld.setBlock(i2, j2, k2, isFrame ? LOTRMod.rock : LOTRMod.morgulPortal, 0, 2);
                    }
                    ++j1;
                }
            }
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            for (k1 = -3; k1 <= 3; ++k1) {
                int n = j1 = generateRockBelow ? -2 : -1;
                while (j1 <= 4) {
                    i2 = actualPosX + i1;
                    j2 = actualPosY + j1;
                    k2 = actualPosZ + k1;
                    this.theWorld.notifyBlocksOfNeighborChange(i2, j2, k2, this.theWorld.getBlock(i2, j2, k2));
                    ++j1;
                }
            }
        }
        return true;
    }

    public void removeStalePortalLocations(long time) {
        if (time % 100L == 0L) {
            Iterator iterator = this.portalTimes.iterator();
            long j = time - 600L;
            while (iterator.hasNext()) {
                Long olong = (Long)iterator.next();
                Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.portalPositions.getValueByKey(olong.longValue());
                if (portalposition != null && portalposition.lastUpdateTime >= j) continue;
                iterator.remove();
                this.portalPositions.remove(olong.longValue());
            }
        }
    }
}

