/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.structure;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.entity.npc.LOTREntityAngbandWarg;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.structure.LOTRWorldGenWargPitBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRWorldGenUrukWargPit
extends LOTRWorldGenWargPitBase {
    public LOTRWorldGenUrukWargPit(boolean flag) {
        super(flag);
        this.wallBlock = LOTRMod.utumnoBrick;
        this.wallMeta = 2;
        this.groundBlock = Blocks.snow;
        this.groundMeta = 0;
    }

    @Override
    protected boolean canGenerateAt(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k) == Blocks.snow && world.getBiomeGenForCoords(i, k) == LOTRBiome.fallforodwaith;
    }

    @Override
    protected LOTREntityNPC getOrc(World world) {
        return new LOTREntityAngbandOrc(world);
    }

    @Override
    protected LOTREntityNPC getWarg(World world) {
        return new LOTREntityAngbandWarg(world);
    }
}

