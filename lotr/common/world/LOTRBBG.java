/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.IChunkProvider
 */
package lotr.common.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class LOTRBBG
implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
        boolean isNether = b.biomeName.equals("Hell");
        int startX = chunkX << 4;
        int startZ = chunkZ << 4;
        if (isNether) {
            for (int blockX = startX; blockX < startX + 16; ++blockX) {
                for (int blockZ = startZ; blockZ < startZ + 16; ++blockZ) {
                    for (int i = 126; i > 121; --i) {
                        if (world.getBlock(blockX, i, blockZ) != Blocks.bedrock) continue;
                        world.setBlock(blockX, i, blockZ, Blocks.netherrack, 0, 2);
                    }
                }
            }
        } else {
            for (int blockX = startX; blockX < startX + 16; ++blockX) {
                for (int blockZ = startZ; blockZ < startZ + 16; ++blockZ) {
                    for (int blockY = 5; blockY > 0; --blockY) {
                        if (world.getBlock(blockX, blockY, blockZ) != Blocks.bedrock) continue;
                        world.setBlock(blockX, blockY, blockZ, Blocks.stone, 0, 2);
                    }
                }
            }
        }
    }

    public static void init() {
        GameRegistry.registerWorldGenerator((IWorldGenerator)new LOTRBBG(), (int)0);
    }
}

