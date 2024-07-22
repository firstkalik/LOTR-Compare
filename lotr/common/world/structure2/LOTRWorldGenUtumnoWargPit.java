/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityAngbandCapWarg;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.entity.npc.LOTREntityAngbandWarg;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenWargPitBase;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenUtumnoWargPit
extends LOTRWorldGenWargPitBase {
    public LOTRWorldGenUtumnoWargPit(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.brickBlock = LOTRMod.utumnoBrick;
        this.brickMeta = 2;
        this.brickSlabBlock = LOTRMod.slabUtumnoSingle;
        this.brickSlabMeta = 1;
        this.brickStairBlock = LOTRMod.stairsUtumnoBrickIce;
        this.brickWallBlock = LOTRMod.wallUtumno;
        this.brickWallMeta = 1;
        this.pillarBlock = LOTRMod.utumnoPillar;
        this.pillarMeta = 1;
        this.woolBlock = Blocks.wool;
        this.woolMeta = 15;
        this.carpetBlock = Blocks.carpet;
        this.carpetMeta = 15;
        this.tableBlock = LOTRMod.angbandtable;
        this.banner = LOTRItemBanner.BannerType.ANGBAND;
        this.chestContents = LOTRChestContents.LOTRChestContents2.ANGBAND_TENT;
    }

    @Override
    protected LOTREntityNPC getOrc(World world) {
        return new LOTREntityAngbandOrc(world);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        if (super.generateWithSetRotation(world, random, i, j, k, rotation)) {
            LOTREntityAngbandCapWarg wargKeeper = new LOTREntityAngbandCapWarg(world);
            this.spawnNPCAndSetHome(wargKeeper, world, 0, 1, 0, 8);
            return true;
        }
        return false;
    }

    @Override
    protected LOTREntityNPC getWarg(World world) {
        return new LOTREntityAngbandWarg(world);
    }

    @Override
    protected void setOrcSpawner(LOTREntityNPCRespawner spawner) {
        spawner.setSpawnClass(LOTREntityAngbandOrc.class);
    }

    @Override
    protected void setWargSpawner(LOTREntityNPCRespawner spawner) {
        spawner.setSpawnClass(LOTREntityAngbandWarg.class);
    }

    @Override
    protected void associateGroundBlocks() {
        super.associateGroundBlocks();
        this.clearScanAlias("GROUND_COVER");
        this.addBlockMetaAliasOption("GROUND_COVER", 1, Blocks.snow_layer, 0);
        this.setBlockAliasChance("GROUND_COVER", 0.25f);
    }
}

