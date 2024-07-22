/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.event.ForgeEventFactory
 */
package lotr.common.world.spawning;

import cpw.mods.fml.common.eventhandler.Event;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lotr.common.LOTRConfig;
import lotr.common.LOTRSpawnDamping;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnEntry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.ForgeEventFactory;

public class LOTRSpawnerNPCs {
    public static final int expectedChunks = 196;
    private static Set<ChunkCoordIntPair> eligibleSpawnChunks = new HashSet<ChunkCoordIntPair>();
    private static Map<Integer, Integer> ticksSinceCycle = new HashMap<Integer, Integer>();

    public static ChunkPosition getRandomSpawningPointInChunk(World world, ChunkCoordIntPair chunkCoords) {
        int i = chunkCoords.chunkXPos;
        int k = chunkCoords.chunkZPos;
        return LOTRSpawnerNPCs.getRandomSpawningPointInChunk(world, i, k);
    }

    public static ChunkPosition getRandomSpawningPointInChunk(World world, int i, int k) {
        if (!world.getChunkProvider().chunkExists(i, k)) {
            return null;
        }
        Chunk chunk = world.getChunkFromChunkCoords(i, k);
        int i1 = i * 16 + world.rand.nextInt(16);
        int k1 = k * 16 + world.rand.nextInt(16);
        int j = world.rand.nextInt(chunk == null ? world.getActualHeight() : chunk.getTopFilledSegment() + 16 - 1);
        return new ChunkPosition(i1, j, k1);
    }

    public static void getSpawnableChunks(World world, Set<ChunkCoordIntPair> set) {
        set.clear();
        for (Object element : world.playerEntities) {
            EntityPlayer entityplayer = (EntityPlayer)element;
            int i = MathHelper.floor_double((double)(entityplayer.posX / 16.0));
            int k = MathHelper.floor_double((double)(entityplayer.posZ / 16.0));
            for (int i1 = -7; i1 <= 7; ++i1) {
                for (int k1 = -7; k1 <= 7; ++k1) {
                    ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i + i1, k + k1);
                    set.add(chunkcoordintpair);
                }
            }
        }
    }

    public static void getSpawnableChunksWithPlayerInRange(World world, Set<ChunkCoordIntPair> set, int range) {
        LOTRSpawnerNPCs.getSpawnableChunks(world, set);
        ArrayList<Object> validPlayers = new ArrayList<Object>();
        for (Object obj : world.playerEntities) {
            EntityPlayer entityplayer = (EntityPlayer)obj;
            if (entityplayer.capabilities.isCreativeMode) continue;
            validPlayers.add((Object)entityplayer);
        }
        int height = world.getHeight();
        HashSet<ChunkCoordIntPair> removes = new HashSet<ChunkCoordIntPair>();
        for (ChunkCoordIntPair chunkCoords : set) {
            int i = chunkCoords.getCenterXPos();
            int k = chunkCoords.getCenterZPosition();
            AxisAlignedBB playerCheckBox = AxisAlignedBB.getBoundingBox((double)(i - range), (double)0.0, (double)(k - range), (double)(i + range), (double)height, (double)(k + range));
            boolean anyPlayers = false;
            for (EntityPlayer entityplayer : validPlayers) {
                if (!entityplayer.boundingBox.intersectsWith(playerCheckBox)) continue;
                anyPlayers = true;
                break;
            }
            if (anyPlayers) continue;
            removes.add(chunkCoords);
        }
        set.removeAll(removes);
    }

    public static List<ChunkCoordIntPair> shuffle(Set<ChunkCoordIntPair> set) {
        ArrayList<ChunkCoordIntPair> list = new ArrayList<ChunkCoordIntPair>(set);
        Collections.shuffle(list);
        return list;
    }

    public static void performSpawning(World world) {
        int interval = LOTRConfig.mobSpawnInterval;
        if (interval > 0) {
            int ticks = 0;
            int dimID = world.provider.dimensionId;
            if (ticksSinceCycle.containsKey(dimID)) {
                ticks = ticksSinceCycle.get(dimID);
            }
            ticksSinceCycle.put(dimID, --ticks);
            if (ticks > 0) {
                return;
            }
            ticks = interval;
            ticksSinceCycle.put(dimID, ticks);
        }
        LOTRSpawnerNPCs.getSpawnableChunks(world, eligibleSpawnChunks);
        ChunkCoordinates spawnPoint = world.getSpawnPoint();
        int totalSpawnCount = LOTRSpawnerNPCs.countNPCs(world);
        int maxSpawnCount = LOTRSpawnDamping.getNPCSpawnCap(world) * eligibleSpawnChunks.size() / 196;
        if (totalSpawnCount <= maxSpawnCount) {
            int cycles = Math.max(1, interval);
            block2: for (int c = 0; c < cycles; ++c) {
                List<ChunkCoordIntPair> shuffled = LOTRSpawnerNPCs.shuffle(eligibleSpawnChunks);
                for (ChunkCoordIntPair chunkCoords : shuffled) {
                    int j;
                    int k;
                    int i;
                    ChunkPosition chunkposition = LOTRSpawnerNPCs.getRandomSpawningPointInChunk(world, chunkCoords);
                    if (chunkposition == null || world.getBlock(i = chunkposition.chunkPosX, j = chunkposition.chunkPosY, k = chunkposition.chunkPosZ).isNormalCube() || world.getBlock(i, j, k).getMaterial() != Material.air) continue;
                    int groups = 3;
                    block4: for (int l = 0; l < groups; ++l) {
                        int i1 = i;
                        int j1 = j;
                        int k1 = k;
                        int range = 5;
                        int yRange = 0;
                        int rangeP1 = range + 1;
                        int yRangeP1 = yRange + 1;
                        LOTRSpawnEntry.Instance spawnEntryInstance = LOTRSpawnerNPCs.getRandomSpawnListEntry(world, i1, j1, k1);
                        if (spawnEntryInstance == null) continue;
                        LOTRSpawnEntry spawnEntry = spawnEntryInstance.spawnEntry;
                        boolean isConquestSpawn = spawnEntryInstance.isConquestSpawn;
                        int spawnCount = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)spawnEntry.minGroupCount, (int)spawnEntry.maxGroupCount);
                        int chance = spawnEntryInstance.spawnChance;
                        if (chance != 0 && world.rand.nextInt(chance) != 0) continue;
                        IEntityLivingData entityData = null;
                        int spawned = 0;
                        int attempts = spawnCount * 8;
                        for (int a = 0; a < attempts; ++a) {
                            Event.Result canSpawn;
                            float f5;
                            float f;
                            float f2;
                            float f3;
                            LOTREntityNPC npc;
                            float f4;
                            float f6;
                            float f22;
                            EntityLiving entity;
                            float f32;
                            float f1;
                            if (!world.blockExists(i1 += world.rand.nextInt(rangeP1) - world.rand.nextInt(rangeP1), j1 += world.rand.nextInt(yRangeP1) - world.rand.nextInt(yRangeP1), k1 += world.rand.nextInt(rangeP1) - world.rand.nextInt(rangeP1)) || !LOTRSpawnerNPCs.canNPCSpawnAtLocation(world, i1, j1, k1) || world.getClosestPlayer((double)(f6 = (float)i1 + 0.5f), (double)(f1 = (float)j1), (double)(f22 = (float)k1 + 0.5f), 24.0) != null || f2 * (f32 = f6 - (float)spawnPoint.posX) + f * (f4 = f1 - (float)spawnPoint.posY) + f3 * (f5 = f22 - (float)spawnPoint.posZ) < 576.0f) continue;
                            try {
                                entity = (EntityLiving)spawnEntry.entityClass.getConstructor(World.class).newInstance(new Object[]{world});
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                            entity.setLocationAndAngles((double)f6, (double)f1, (double)f22, world.rand.nextFloat() * 360.0f, 0.0f);
                            if (entity instanceof LOTREntityNPC && isConquestSpawn) {
                                npc = (LOTREntityNPC)entity;
                                npc.setConquestSpawning(true);
                            }
                            if ((canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entity, (World)world, (float)f6, (float)f1, (float)f22)) != Event.Result.ALLOW && (canSpawn != Event.Result.DEFAULT || !entity.getCanSpawnHere())) continue;
                            world.spawnEntityInWorld((Entity)entity);
                            if (entity instanceof LOTREntityNPC) {
                                npc = (LOTREntityNPC)entity;
                                npc.isNPCPersistent = false;
                                npc.setConquestSpawning(false);
                            }
                            if (!ForgeEventFactory.doSpecialSpawn((EntityLiving)entity, (World)world, (float)f6, (float)f1, (float)f22)) {
                                entityData = entity.onSpawnWithEgg(entityData);
                            }
                            if (c > 0 && (totalSpawnCount += entity instanceof LOTREntityNPC ? ((LOTREntityNPC)entity).getSpawnCountValue() : 1) > maxSpawnCount) break block2;
                            if (++spawned >= spawnCount) continue block4;
                        }
                    }
                }
            }
        }
    }

    private static int countNPCs(World world) {
        int count = 0;
        for (Object element : world.loadedEntityList) {
            Entity entity = (Entity)element;
            if (!(entity instanceof LOTREntityNPC)) continue;
            int spawnCountValue = ((LOTREntityNPC)entity).getSpawnCountValue();
            count += spawnCountValue;
        }
        return count;
    }

    private static boolean canNPCSpawnAtLocation(World world, int i, int j, int k) {
        if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)world, (int)i, (int)(j - 1), (int)k)) {
            return false;
        }
        Block block = world.getBlock(i, j - 1, k);
        world.getBlockMetadata(i, j - 1, k);
        boolean spawnBlock = block.canCreatureSpawn(EnumCreatureType.monster, (IBlockAccess)world, i, j - 1, k);
        return spawnBlock && block != Blocks.bedrock && !world.getBlock(i, j, k).isNormalCube() && !world.getBlock(i, j, k).getMaterial().isLiquid() && !world.getBlock(i, j + 1, k).isNormalCube();
    }

    private static LOTRSpawnEntry.Instance getRandomSpawnListEntry(World world, int i, int j, int k) {
        LOTRBiomeSpawnList spawnlist = null;
        BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiome && world.provider instanceof LOTRWorldProvider) {
            LOTRBiome lotrbiome = (LOTRBiome)biome;
            LOTRWorldChunkManager worldChunkMgr = (LOTRWorldChunkManager)world.provider.worldChunkMgr;
            LOTRBiomeVariant variant = worldChunkMgr.getBiomeVariantAt(i, k);
            spawnlist = lotrbiome.getNPCSpawnList(world, world.rand, i, j, k, variant);
        }
        if (spawnlist != null) {
            return spawnlist.getRandomSpawnEntry(world.rand, world, i, j, k);
        }
        return null;
    }
}

