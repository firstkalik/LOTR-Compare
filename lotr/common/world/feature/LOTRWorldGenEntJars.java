/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package lotr.common.world.feature;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityEntJar;
import lotr.common.world.biome.LOTRBiomeGenFangorn;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRWorldGenEntJars
extends WorldGenerator {
    public boolean generate(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 16; ++l) {
            int k1;
            int j1;
            int i1 = i - random.nextInt(6) + random.nextInt(6);
            if (world.getBlock(i1, (j1 = j - random.nextInt(2) + random.nextInt(2)) - 1, k1 = k - random.nextInt(6) + random.nextInt(6)) != Blocks.grass || world.getBlock(i1, j1, k1).isNormalCube() || world.getPrecipitationHeight(i1, k1) != j1 || !(world.getBiomeGenForCoords(i1, k1) instanceof LOTRBiomeGenFangorn)) continue;
            world.setBlock(i1, j1, k1, LOTRMod.entJar, 0, 2);
            TileEntity tileentity = world.getTileEntity(i1, j1, k1);
            if (!(tileentity instanceof LOTRTileEntityEntJar)) continue;
            int amount = random.nextInt(LOTRTileEntityEntJar.MAX_CAPACITY + 1);
            for (int l1 = 0; l1 < amount; ++l1) {
                ((LOTRTileEntityEntJar)tileentity).fillWithWater();
            }
        }
        return true;
    }
}

