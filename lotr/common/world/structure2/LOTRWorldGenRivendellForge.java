/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityRivendellSmith;
import lotr.common.world.structure2.LOTRWorldGenHighElvenForge;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRWorldGenRivendellForge
extends LOTRWorldGenHighElvenForge {
    public LOTRWorldGenRivendellForge(boolean flag) {
        super(flag);
        this.roofBlock = LOTRMod.clayTileDyed;
        this.roofMeta = 9;
        this.roofStairBlock = LOTRMod.stairsClayTileDyedCyan;
        this.tableBlock = LOTRMod.rivendellTable;
    }

    @Override
    protected LOTREntityElf getElf(World world) {
        return new LOTREntityRivendellSmith(world);
    }
}

