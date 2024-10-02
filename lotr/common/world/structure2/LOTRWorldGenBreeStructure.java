/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class LOTRWorldGenBreeStructure
extends LOTRWorldGenStructureBase2 {
    public Block brickBlock;
    public int brickMeta;
    public Block brick2Block;
    public int brick2Meta;
    public Block brick2SlabBlock;
    public int brick2SlabMeta;
    public Block brick2StairBlock;
    public Block brick2WallBlock;
    public int brick2WallMeta;
    public Block floorBlock;
    public int floorMeta;
    public Block stoneWallBlock;
    public int stoneWallMeta;
    public Block woodBlock;
    public int woodMeta;
    public Block plankBlock;
    public int plankMeta;
    public Block plankSlabBlock;
    public int plankSlabMeta;
    public Block plankStairBlock;
    public Block fenceBlock;
    public int fenceMeta;
    public Block fenceGateBlock;
    public Block doorBlock;
    public Block trapdoorBlock;
    public Block beamBlock;
    public int beamMeta;
    public Block roofBlock;
    public int roofMeta;
    public Block roofSlabBlock;
    public int roofSlabMeta;
    public Block roofStairBlock;
    public Block carpetBlock;
    public int carpetMeta;
    public Block bedBlock;
    public Block tableBlock;

    public LOTRWorldGenBreeStructure(boolean flag) {
        super(flag);
    }

    public ItemStack getRandomBreeWeapon(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(Items.iron_sword), new ItemStack(LOTRMod.daggerIron), new ItemStack(LOTRMod.pikeIron), new ItemStack(LOTRMod.rollingPin)};
        return items[random.nextInt(items.length)].copy();
    }

    public ItemStack getRandomTavernItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.rollingPin), new ItemStack(LOTRMod.mug), new ItemStack(LOTRMod.ceramicMug), new ItemStack((Item)Items.bow), new ItemStack(Items.wooden_axe), new ItemStack((Item)Items.fishing_rod), new ItemStack(Items.feather)};
        return items[random.nextInt(items.length)].copy();
    }

    public void placeRandomFloor(World world, Random random, int i, int j, int k) {
        float randFloor = random.nextFloat();
        if (randFloor < 0.25f) {
            this.setBlockAndMetadata(world, i, j, k, (Block)Blocks.grass, 0);
        } else if (randFloor < 0.5f) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.dirt, 1);
        } else if (randFloor < 0.75f) {
            this.setBlockAndMetadata(world, i, j, k, Blocks.gravel, 0);
        } else {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.dirtPath, 0);
        }
    }

    @Override
    public void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.brickBlock = LOTRMod.cobblebrick;
        this.brickMeta = 0;
        this.brick2Block = Blocks.stonebrick;
        this.brick2Meta = 0;
        this.brick2SlabBlock = Blocks.stone_slab;
        this.brick2SlabMeta = 5;
        this.brick2StairBlock = Blocks.stone_brick_stairs;
        this.brick2WallBlock = LOTRMod.wallStoneV;
        this.brick2WallMeta = 1;
        this.floorBlock = Blocks.cobblestone;
        this.floorMeta = 0;
        this.stoneWallBlock = Blocks.cobblestone_wall;
        this.stoneWallMeta = 0;
        int randomWood = random.nextInt(7);
        switch (randomWood) {
            case 0: {
                this.plankBlock = Blocks.planks;
                this.plankMeta = 0;
                this.plankSlabBlock = Blocks.wooden_slab;
                this.plankSlabMeta = 0;
                this.plankStairBlock = Blocks.oak_stairs;
                this.fenceBlock = Blocks.fence;
                this.fenceMeta = 0;
                this.fenceGateBlock = Blocks.fence_gate;
                this.doorBlock = Blocks.wooden_door;
                this.trapdoorBlock = Blocks.trapdoor;
                this.beamBlock = LOTRMod.woodBeamV1;
                this.beamMeta = 0;
                break;
            }
            case 1: {
                this.plankBlock = LOTRMod.planks;
                this.plankMeta = 9;
                this.plankSlabBlock = LOTRMod.woodSlabSingle2;
                this.plankSlabMeta = 1;
                this.plankStairBlock = LOTRMod.stairsBeech;
                this.fenceBlock = LOTRMod.fence;
                this.fenceMeta = 9;
                this.fenceGateBlock = LOTRMod.fenceGateBeech;
                this.doorBlock = LOTRMod.doorBeech;
                this.trapdoorBlock = LOTRMod.trapdoorBeech;
                this.beamBlock = LOTRMod.woodBeam2;
                this.beamMeta = 1;
                break;
            }
            case 2: {
                this.plankBlock = Blocks.planks;
                this.plankMeta = 2;
                this.plankSlabBlock = Blocks.wooden_slab;
                this.plankSlabMeta = 2;
                this.plankStairBlock = Blocks.birch_stairs;
                this.fenceBlock = Blocks.fence;
                this.fenceMeta = 2;
                this.fenceGateBlock = LOTRMod.fenceGateBirch;
                this.doorBlock = LOTRMod.doorBirch;
                this.trapdoorBlock = LOTRMod.trapdoorBirch;
                this.beamBlock = LOTRMod.woodBeamV1;
                this.beamMeta = 2;
                break;
            }
            case 3: {
                this.plankBlock = Blocks.planks;
                this.plankMeta = 1;
                this.plankSlabBlock = Blocks.wooden_slab;
                this.plankSlabMeta = 1;
                this.plankStairBlock = Blocks.spruce_stairs;
                this.fenceBlock = Blocks.fence;
                this.fenceMeta = 1;
                this.fenceGateBlock = LOTRMod.fenceGateSpruce;
                this.doorBlock = LOTRMod.doorSpruce;
                this.trapdoorBlock = LOTRMod.trapdoorSpruce;
                this.beamBlock = LOTRMod.woodBeamV1;
                this.beamMeta = 1;
                break;
            }
            case 4: {
                this.woodBlock = LOTRMod.wood4;
                this.woodMeta = 0;
                this.plankBlock = LOTRMod.planks2;
                this.plankMeta = 0;
                this.plankSlabBlock = LOTRMod.woodSlabSingle3;
                this.plankSlabMeta = 0;
                this.plankStairBlock = LOTRMod.stairsChestnut;
                this.fenceBlock = LOTRMod.fence2;
                this.fenceMeta = 0;
                this.fenceGateBlock = LOTRMod.fenceGateChestnut;
                this.doorBlock = LOTRMod.doorChestnut;
                this.trapdoorBlock = LOTRMod.trapdoorChestnut;
                this.beamBlock = LOTRMod.woodBeam4;
                this.beamMeta = 0;
                break;
            }
            case 5: {
                this.woodBlock = LOTRMod.wood3;
                this.woodMeta = 0;
                this.plankBlock = LOTRMod.planks;
                this.plankMeta = 12;
                this.plankSlabBlock = LOTRMod.woodSlabSingle2;
                this.plankSlabMeta = 4;
                this.plankStairBlock = LOTRMod.stairsMaple;
                this.fenceBlock = LOTRMod.fence;
                this.fenceMeta = 12;
                this.fenceGateBlock = LOTRMod.fenceGateMaple;
                this.doorBlock = LOTRMod.doorMaple;
                this.trapdoorBlock = LOTRMod.trapdoorMaple;
                this.beamBlock = LOTRMod.woodBeam3;
                this.beamMeta = 0;
                break;
            }
            case 6: {
                this.woodBlock = LOTRMod.wood7;
                this.woodMeta = 0;
                this.plankBlock = LOTRMod.planks2;
                this.plankMeta = 12;
                this.plankSlabBlock = LOTRMod.woodSlabSingle4;
                this.plankSlabMeta = 4;
                this.plankStairBlock = LOTRMod.stairsAspen;
                this.fenceBlock = LOTRMod.fence2;
                this.fenceMeta = 12;
                this.fenceGateBlock = LOTRMod.fenceGateAspen;
                this.doorBlock = LOTRMod.doorAspen;
                this.trapdoorBlock = LOTRMod.trapdoorAspen;
                this.beamBlock = LOTRMod.woodBeam7;
                this.beamMeta = 0;
                break;
            }
        }
        this.doorBlock = LOTRMod.doorBeech;
        this.trapdoorBlock = LOTRMod.trapdoorBeech;
        this.roofBlock = LOTRMod.thatch;
        this.roofMeta = 0;
        this.roofSlabBlock = LOTRMod.slabSingleThatch;
        this.roofSlabMeta = 0;
        this.roofStairBlock = LOTRMod.stairsThatch;
        this.carpetBlock = Blocks.carpet;
        this.carpetMeta = 12;
        this.bedBlock = LOTRMod.strawBed;
        this.tableBlock = LOTRMod.breeTable;
    }

    public static Block getRandomPieBlock(Random random) {
        int i = random.nextInt(3);
        switch (i) {
            case 0: {
                return LOTRMod.appleCrumble;
            }
            case 1: {
                return LOTRMod.cherryPie;
            }
            case 2: {
                return LOTRMod.berryPie;
            }
        }
        return LOTRMod.appleCrumble;
    }
}

