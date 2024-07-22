/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.spawning;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.LOTREntityGollum;
import lotr.common.world.map.LOTRWaypoint;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRGollumSpawner {
    public static void performSpawning(World world) {
        if (LOTRLevelData.gollumSpawned()) {
            return;
        }
        if (world.getTotalWorldTime() % 20L == 0L) {
            int k;
            LOTRWaypoint home = LOTRWaypoint.HIGH_PASS;
            int x = home.getXCoord();
            int z = home.getZCoord();
            int homeRange = 128;
            int i = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(x - homeRange), (int)(x + homeRange));
            int checkRange = 16;
            int j = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)16, (int)32);
            if (world.checkChunksExist(i - 16, j - checkRange, (k = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(z - homeRange), (int)(z + homeRange))) - checkRange, i + checkRange, j + checkRange, k + checkRange)) {
                AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
                if (world.getEntitiesWithinAABB(EntityPlayer.class, aabb = aabb.expand((double)checkRange, (double)checkRange, (double)checkRange)).isEmpty()) {
                    Block block = world.getBlock(i, j, k);
                    Block below = world.getBlock(i, j - 1, k);
                    Block above = world.getBlock(i, j + 1, k);
                    if (below.isNormalCube() && !block.isNormalCube() && !above.isNormalCube()) {
                        LOTREntityGollum gollum = new LOTREntityGollum(world);
                        gollum.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, 0.0f, 0.0f);
                        if (gollum.getCanSpawnHere()) {
                            gollum.onSpawnWithEgg(null);
                            world.spawnEntityInWorld((Entity)gollum);
                            LOTRLevelData.setGollumSpawned(true);
                        }
                    }
                }
            }
        }
    }
}

