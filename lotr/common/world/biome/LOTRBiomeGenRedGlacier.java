/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.biome;

import java.util.Random;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsIronfist;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRBiomeGenRedGlacier
extends LOTRBiomeGenRedMountainsIronfist {
    public LOTRBiomeGenRedGlacier(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.ice;
        this.fillerBlock = Blocks.ice;
    }

    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metadata, int x, int z, double noiseVal) {
        super.genTerrainBlocks(world, rand, blocks, metadata, x, z, noiseVal);
        this.topBlock = rand.nextInt(100) < 10 ? Blocks.packed_ice : Blocks.ice;
    }
}

