/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.gen.ChunkProviderServer
 */
package lotr.common.world.biome.variant;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lotr.common.LOTRDimension;
import lotr.common.network.LOTRPacketBiomeVariantsUnwatch;
import lotr.common.network.LOTRPacketBiomeVariantsWatch;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;

public class LOTRBiomeVariantStorage {
    private static Map<LOTRDimension, Map<ChunkCoordIntPair, byte[]>> chunkVariantMap = new HashMap<LOTRDimension, Map<ChunkCoordIntPair, byte[]>>();
    private static Map<LOTRDimension, Map<ChunkCoordIntPair, byte[]>> chunkVariantMapClient = new HashMap<LOTRDimension, Map<ChunkCoordIntPair, byte[]>>();

    private static Map<ChunkCoordIntPair, byte[]> getDimensionChunkMap(World world) {
        LOTRDimension dim;
        Map<LOTRDimension, Map<ChunkCoordIntPair, byte[]>> sourcemap = !world.isRemote ? chunkVariantMap : chunkVariantMapClient;
        Map<ChunkCoordIntPair, byte[]> map = sourcemap.get((Object)(dim = LOTRDimension.getCurrentDimensionWithFallback(world)));
        if (map == null) {
            map = new HashMap<ChunkCoordIntPair, byte[]>();
            sourcemap.put(dim, map);
        }
        return map;
    }

    private static ChunkCoordIntPair getChunkKey(Chunk chunk) {
        return new ChunkCoordIntPair(chunk.xPosition, chunk.zPosition);
    }

    public static byte[] getChunkBiomeVariants(World world, Chunk chunk) {
        return LOTRBiomeVariantStorage.getChunkBiomeVariants(world, LOTRBiomeVariantStorage.getChunkKey(chunk));
    }

    public static byte[] getChunkBiomeVariants(World world, ChunkCoordIntPair chunk) {
        return LOTRBiomeVariantStorage.getDimensionChunkMap(world).get((Object)chunk);
    }

    public static void setChunkBiomeVariants(World world, Chunk chunk, byte[] variants) {
        LOTRBiomeVariantStorage.setChunkBiomeVariants(world, LOTRBiomeVariantStorage.getChunkKey(chunk), variants);
    }

    public static void setChunkBiomeVariants(World world, ChunkCoordIntPair chunk, byte[] variants) {
        LOTRBiomeVariantStorage.getDimensionChunkMap(world).put(chunk, variants);
    }

    public static void clearChunkBiomeVariants(World world, Chunk chunk) {
        LOTRBiomeVariantStorage.clearChunkBiomeVariants(world, LOTRBiomeVariantStorage.getChunkKey(chunk));
    }

    public static void clearChunkBiomeVariants(World world, ChunkCoordIntPair chunk) {
        LOTRBiomeVariantStorage.getDimensionChunkMap(world).remove((Object)chunk);
    }

    public static void loadChunkVariants(World world, Chunk chunk, NBTTagCompound data) {
        if (LOTRBiomeVariantStorage.getChunkBiomeVariants(world, chunk) == null) {
            byte[] variants = data.hasKey("LOTRBiomeVariants") ? data.getByteArray("LOTRBiomeVariants") : new byte[256];
            LOTRBiomeVariantStorage.setChunkBiomeVariants(world, chunk, variants);
        }
    }

    public static void saveChunkVariants(World world, Chunk chunk, NBTTagCompound data) {
        byte[] variants = LOTRBiomeVariantStorage.getChunkBiomeVariants(world, chunk);
        if (variants != null) {
            data.setByteArray("LOTRBiomeVariants", variants);
        }
    }

    public static void clearAllVariants(World world) {
        LOTRBiomeVariantStorage.getDimensionChunkMap(world).clear();
        FMLLog.info((String)"Unloading LOTR biome variants in %s", (Object[])new Object[]{LOTRDimension.getCurrentDimensionWithFallback((World)world).dimensionName});
    }

    public static void performCleanup(WorldServer world) {
        Map<ChunkCoordIntPair, byte[]> dimensionMap = LOTRBiomeVariantStorage.getDimensionChunkMap((World)world);
        dimensionMap.size();
        System.nanoTime();
        ArrayList<ChunkCoordIntPair> removalChunks = new ArrayList<ChunkCoordIntPair>();
        for (ChunkCoordIntPair chunk : dimensionMap.keySet()) {
            if (world.theChunkProviderServer.chunkExists(chunk.chunkXPos, chunk.chunkZPos)) continue;
            removalChunks.add(chunk);
        }
        for (ChunkCoordIntPair chunk : removalChunks) {
            dimensionMap.remove((Object)chunk);
        }
    }

    public static void sendChunkVariantsToPlayer(World world, Chunk chunk, EntityPlayerMP entityplayer) {
        byte[] variants = LOTRBiomeVariantStorage.getChunkBiomeVariants(world, chunk);
        if (variants != null) {
            LOTRPacketBiomeVariantsWatch packet = new LOTRPacketBiomeVariantsWatch(chunk.xPosition, chunk.zPosition, variants);
            LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, entityplayer);
        } else {
            String dimName = world.provider.getDimensionName();
            int posX = chunk.xPosition << 4;
            int posZ = chunk.zPosition << 4;
            String playerName = entityplayer.getCommandSenderName();
            FMLLog.severe((String)"Could not find LOTR biome variants for %s chunk at %d, %d; requested by %s", (Object[])new Object[]{dimName, posX, posZ, playerName});
        }
    }

    public static void sendUnwatchToPlayer(World world, Chunk chunk, EntityPlayerMP entityplayer) {
        LOTRPacketBiomeVariantsUnwatch packet = new LOTRPacketBiomeVariantsUnwatch(chunk.xPosition, chunk.zPosition);
        LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet, entityplayer);
    }

    public static int getSize(World world) {
        Map<ChunkCoordIntPair, byte[]> map = LOTRBiomeVariantStorage.getDimensionChunkMap(world);
        return map.size();
    }
}

