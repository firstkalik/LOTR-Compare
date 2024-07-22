/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.chunk.storage.ExtendedBlockStorage
 */
package lotr.common.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lotr.common.world.LOTRUtumnoLevel;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.biome.variant.LOTRBiomeVariantStorage;
import lotr.common.world.mapgen.LOTRMapGenCaves;
import lotr.common.world.mapgen.LOTRMapGenCavesUtumno;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class LOTRChunkProviderUtumno
implements IChunkProvider {
    private World worldObj;
    private Random rand;
    private BiomeGenBase[] biomesForGeneration;
    private LOTRBiomeVariant[] variantsForGeneration;
    private LOTRMapGenCaves caveGenerator = new LOTRMapGenCavesUtumno();

    public LOTRChunkProviderUtumno(World world, long l) {
        this.worldObj = world;
        this.rand = new Random(l);
        LOTRUtumnoLevel.setupLevels();
    }

    private void generateTerrain(int chunkX, int chunkZ, Block[] blocks, byte[] metadata) {
        Arrays.fill((Object[])blocks, (Object)Blocks.air);
        LOTRUtumnoLevel.generateTerrain(this.worldObj, this.rand, chunkX, chunkZ, blocks, metadata);
    }

    public Chunk loadChunk(int i, int j) {
        return this.provideChunk(i, j);
    }

    public Chunk provideChunk(int i, int k) {
        this.rand.setSeed((long)i * 341873128712L + (long)k * 132897987541L);
        LOTRWorldChunkManager chunkManager = (LOTRWorldChunkManager)this.worldObj.getWorldChunkManager();
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        this.generateTerrain(i, k, blocks, metadata);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, i * 16, k * 16, 16, 16);
        this.variantsForGeneration = chunkManager.getBiomeVariants(this.variantsForGeneration, i * 16, k * 16, 16, 16);
        this.caveGenerator.func_151539_a((IChunkProvider)this, this.worldObj, i, k, blocks);
        Chunk chunk = new Chunk(this.worldObj, i, k);
        ExtendedBlockStorage[] blockStorage = chunk.getBlockStorageArray();
        for (int i1 = 0; i1 < 16; ++i1) {
            for (int k1 = 0; k1 < 16; ++k1) {
                for (int j1 = 0; j1 < 256; ++j1) {
                    int blockIndex = i1 << 12 | k1 << 8 | j1;
                    Block block = blocks[blockIndex];
                    if (block == null || block == Blocks.air) continue;
                    byte meta = metadata[blockIndex];
                    int j2 = j1 >> 4;
                    if (blockStorage[j2] == null) {
                        blockStorage[j2] = new ExtendedBlockStorage(j2 << 4, true);
                    }
                    blockStorage[j2].func_150818_a(i1, j1 & 0xF, k1, block);
                    blockStorage[j2].setExtBlockMetadata(i1, j1 & 0xF, k1, meta & 0xF);
                }
            }
        }
        byte[] biomes = chunk.getBiomeArray();
        for (int l = 0; l < biomes.length; ++l) {
            biomes[l] = (byte)this.biomesForGeneration[l].biomeID;
        }
        byte[] variants = new byte[256];
        for (int l = 0; l < variants.length; ++l) {
            variants[l] = (byte)this.variantsForGeneration[l].variantID;
        }
        LOTRBiomeVariantStorage.setChunkBiomeVariants(this.worldObj, chunk, variants);
        chunk.resetRelightChecks();
        return chunk;
    }

    public boolean chunkExists(int i, int j) {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int chunkX, int chunkZ) {
        BlockFalling.fallInstantly = true;
        int i = chunkX * 16;
        int k = chunkZ * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(i + 16, k + 16);
        if (!(biomegenbase instanceof LOTRBiome)) {
            return;
        }
        LOTRBiome biome = (LOTRBiome)biomegenbase;
        this.rand.setSeed(this.worldObj.getSeed());
        long l1 = this.rand.nextLong() / 2L * 2L + 1L;
        long l2 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)chunkX * l1 + (long)chunkZ * l2 ^ this.worldObj.getSeed());
        biome.decorate(this.worldObj, this.rand, i, k);
        BlockFalling.fallInstantly = false;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate update) {
        return true;
    }

    public void saveExtraData() {
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "UtumnoLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType creatureType, int i, int j, int k) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        return biome == null ? null : biome.getSpawnableList(creatureType);
    }

    public ChunkPosition func_147416_a(World world, String type, int i, int j, int k) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int i, int j) {
    }
}

