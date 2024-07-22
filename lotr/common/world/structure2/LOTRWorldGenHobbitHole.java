/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockTripWireHook
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenHobbitStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class LOTRWorldGenHobbitHole
extends LOTRWorldGenHobbitStructure {
    public LOTRWorldGenHobbitHole(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int signMeta;
        int i17;
        int i15;
        int k1;
        int i36;
        int i362;
        int i363;
        int i18;
        int i37;
        int i364;
        int i6;
        int i372;
        int i365;
        int i366;
        int i373;
        int i367;
        int i10;
        int i30;
        int i374;
        int i38;
        int i368;
        int i369;
        int i375;
        int n;
        int i12;
        int i3;
        int i3610;
        int[] signPos2;
        int i3611;
        int m;
        int i376;
        int i24;
        int i29;
        int i382;
        this.setOriginAndRotation(world, i, j, k, rotation, 17);
        this.setupRandomBlocks(random);
        int radius = 16;
        int height = 7;
        int extraRadius = 2;
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i369 = -radius; i369 <= radius; ++i369) {
                for (i375 = -radius; i375 <= radius; ++i375) {
                    if (i369 * i369 + i375 * i375 > radius * radius) continue;
                    int i383 = this.getTopBlock(world, i369, i375) - 1;
                    if (!this.isSurface(world, i369, i383, i375)) {
                        return false;
                    }
                    if (i383 < minHeight) {
                        minHeight = i383;
                    }
                    if (i383 <= maxHeight) continue;
                    maxHeight = i383;
                }
            }
            if (maxHeight - minHeight > 8) {
                return false;
            }
        }
        for (i3 = -radius; i3 <= radius; ++i3) {
            for (i363 = -radius; i363 <= radius; ++i363) {
                for (i37 = height; i37 >= 0; --i37) {
                    int i384 = i3;
                    int j2 = i37 + radius - height;
                    int k2 = i363;
                    if (i384 * i384 + j2 * j2 + k2 * k2 >= (radius + extraRadius) * (radius + extraRadius)) continue;
                    boolean bool = !this.isOpaque(world, i3, i37 + 1, i363);
                    this.setBlockAndMetadata(world, i3, i37, i363, (Block)(bool ? Blocks.grass : Blocks.dirt), 0);
                    this.setGrassToDirt(world, i3, i37 - 1, i363);
                }
            }
        }
        for (i3 = -radius; i3 <= radius; ++i3) {
            for (i363 = -radius; i363 <= radius; ++i363) {
                if (i3 * i3 + i363 * i363 >= radius * radius) continue;
                i37 = -1;
                while (!this.isOpaque(world, i3, i37, i363) && this.getY(i37) >= 0) {
                    boolean bool = !this.isOpaque(world, i3, i37 + 1, i363);
                    this.setBlockAndMetadata(world, i3, i37, i363, (Block)(bool ? Blocks.grass : Blocks.dirt), 0);
                    this.setGrassToDirt(world, i3, i37 - 1, i363);
                    --i37;
                }
            }
        }
        this.setGrassToDirt(world, 0, 7, 0);
        this.setBlockAndMetadata(world, 0, 8, 0, Blocks.brick_block, 0);
        this.setBlockAndMetadata(world, 0, 9, 0, Blocks.brick_block, 0);
        this.setBlockAndMetadata(world, 0, 10, 0, Blocks.flower_pot, 0);
        for (int i2 = -16; i2 <= -13; ++i2) {
            for (i369 = 1; i369 <= 4; ++i369) {
                for (i375 = -3; i375 <= 3; ++i375) {
                    this.setAir(world, i375, i369, i2);
                }
            }
        }
        for (int j1 = 1; j1 <= 3; ++j1) {
            for (i369 = -2; i369 <= 2; ++i369) {
                this.setAir(world, i369, j1, -12);
            }
        }
        for (n = -17; n <= -13; ++n) {
            for (i369 = -5; i369 <= 5; ++i369) {
                for (i375 = 0; i375 == 0 || !this.isOpaque(world, i369, i375, n) && this.getY(i375) >= 0; --i375) {
                    boolean bool = i375 == 0;
                    this.setBlockAndMetadata(world, i369, i375, n, (Block)(bool ? Blocks.grass : Blocks.dirt), 0);
                }
                for (i375 = 1; i375 <= 3; ++i375) {
                    this.setAir(world, i369, i375, n);
                }
            }
        }
        for (n = -16; n <= -13; ++n) {
            this.setBlockAndMetadata(world, 4, 1, n, this.outFenceBlock, this.outFenceMeta);
            this.setBlockAndMetadata(world, -4, 1, n, this.outFenceBlock, this.outFenceMeta);
            this.setBlockAndMetadata(world, 0, 0, n, this.pathBlock, this.pathMeta);
        }
        for (m = -1; m <= 1; ++m) {
            this.setBlockAndMetadata(world, m, 0, -12, this.pathBlock, this.pathMeta);
            this.setBlockAndMetadata(world, m, 0, -11, this.pathBlock, this.pathMeta);
        }
        for (m = -3; m <= 3; ++m) {
            this.setBlockAndMetadata(world, m, 1, -16, this.outFenceBlock, this.outFenceMeta);
        }
        this.setBlockAndMetadata(world, 0, 1, -16, this.outFenceGateBlock, 0);
        if (random.nextInt(5) == 0) {
            String[] signLines = LOTRNames.getHobbitSign(random);
            int[] signPos2 = new int[]{-3, -2, -1, 1, 2, 3};
            i366 = signPos2[random.nextInt(signPos2.length)];
            signMeta = MathHelper.getRandomIntegerInRange((Random)random, (int)6, (int)10) & 0xF;
            this.placeSign(world, i366, 2, -16, Blocks.standing_sign, signMeta, signLines);
        }
        for (k1 = -15; k1 <= -13; ++k1) {
            signPos2 = new int[]{-1, 1};
            i366 = signPos2.length;
            for (signMeta = 0; signMeta < i366; ++signMeta) {
                i365 = signPos2[signMeta];
                i372 = 1;
                this.plantFlower(world, random, i365, i372, k1);
            }
        }
        if (random.nextInt(3) == 0) {
            for (k1 = -14; k1 <= -13; ++k1) {
                signPos2 = new int[]{-2, 2};
                i366 = signPos2.length;
                for (signMeta = 0; signMeta < i366; ++signMeta) {
                    i365 = signPos2[signMeta];
                    this.setBlockAndMetadata(world, i365, 1, k1, this.hedgeBlock, this.hedgeMeta);
                }
            }
        }
        for (int i1 = -2; i1 <= 2; ++i1) {
            for (i366 = 1; i366 <= 3; ++i366) {
                this.setBlockAndMetadata(world, i1, i366, -10, Blocks.brick_block, 0);
            }
        }
        boolean gateFlip = random.nextBoolean();
        if (gateFlip) {
            for (i366 = 0; i366 <= 1; ++i366) {
                this.setBlockAndMetadata(world, i366, 0, -10, this.floorBlock, this.floorMeta);
                for (i376 = 1; i376 <= 2; ++i376) {
                    this.setAir(world, i366, i376, -11);
                    this.setBlockAndMetadata(world, i366, i376, -10, this.gateBlock, 2);
                }
            }
            this.setBlockAndMetadata(world, -2, 1, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -2, 2, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -2, 3, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -2, 1, -12, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, -2, 3, -12, this.plank2StairBlock, 6);
            this.setBlockAndMetadata(world, -1, 3, -12, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, -1, 1, -11, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -1, 2, -11, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, -1, 3, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 0, 3, -11, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, 0, 3, -12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 1, 3, -11, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 1, 3, -12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 2, 1, -11, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 2, 2, -11, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 2, 3, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 2, 1, -12, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, 2, 3, -12, this.plank2StairBlock, 6);
            this.placeSign(world, -2, 2, -12, Blocks.wall_sign, 2, new String[]{"", this.homeName1, this.homeName2, ""});
        } else {
            for (i366 = -1; i366 <= 0; ++i366) {
                this.setBlockAndMetadata(world, i366, 0, -10, this.floorBlock, this.floorMeta);
                for (i376 = 1; i376 <= 2; ++i376) {
                    this.setAir(world, i366, i376, -11);
                    this.setBlockAndMetadata(world, i366, i376, -10, this.gateBlock, 2);
                }
            }
            this.setBlockAndMetadata(world, 2, 1, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 2, 2, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 2, 3, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 2, 1, -12, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, 2, 3, -12, this.plank2StairBlock, 6);
            this.setBlockAndMetadata(world, 1, 3, -12, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 1, 1, -11, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 1, 2, -11, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 1, 3, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 0, 3, -11, this.plank2StairBlock, 5);
            this.setBlockAndMetadata(world, 0, 3, -12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -1, 3, -11, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, -1, 3, -12, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -2, 1, -11, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -2, 2, -11, this.plank2StairBlock, 4);
            this.setBlockAndMetadata(world, -2, 3, -11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -2, 1, -12, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, -2, 3, -12, this.plank2StairBlock, 6);
            this.placeSign(world, 2, 2, -12, Blocks.wall_sign, 2, new String[]{"", this.homeName1, this.homeName2, ""});
        }
        for (int i34 = 1; i34 <= 3; ++i34) {
            this.setBlockAndMetadata(world, -3, i34, -12, LOTRMod.woodBeamV1, 0);
            this.setBlockAndMetadata(world, 3, i34, -12, LOTRMod.woodBeamV1, 0);
        }
        for (int i33 = -3; i33 <= 3; ++i33) {
            if (Math.abs(i33) <= 1) {
                this.setBlockAndMetadata(world, i33, 4, -13, LOTRMod.slabClayTileDyedSingle2, 5);
                continue;
            }
            this.setBlockAndMetadata(world, i33, 3, -13, LOTRMod.slabClayTileDyedSingle2, 13);
        }
        this.setBlockAndMetadata(world, -4, 3, -13, LOTRMod.slabClayTileDyedSingle2, 5);
        this.setBlockAndMetadata(world, 4, 3, -13, LOTRMod.slabClayTileDyedSingle2, 5);
        for (int i32 = -9; i32 <= 1; ++i32) {
            for (i376 = -2; i376 <= 2; ++i376) {
                for (i382 = 1; i382 <= 3; ++i382) {
                    this.setAir(world, i376, i382, i32);
                }
            }
            this.setBlockAndMetadata(world, 1, 0, i32, this.floorBlock, this.floorMeta);
            this.setBlockAndMetadata(world, 0, 0, i32, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -1, 0, i32, this.floorBlock, this.floorMeta);
            this.setBlockAndMetadata(world, 2, 1, i32, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -2, 1, i32, this.plank2StairBlock, 0);
            for (i3610 = 1; i3610 <= 3; ++i3610) {
                this.setBlockAndMetadata(world, 3, i3610, i32, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, -3, i3610, i32, this.plank2Block, this.plank2Meta);
            }
            this.setBlockAndMetadata(world, 2, 3, i32, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -2, 3, i32, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 1, 3, i32, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, -1, 3, i32, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 0, 4, i32, this.plank2Block, this.plank2Meta);
        }
        int[] i32 = new int[]{-8, -4, 0};
        i3610 = i32.length;
        for (i382 = 0; i382 < i3610; ++i382) {
            i367 = i32[i382];
            this.setBlockAndMetadata(world, 0, 3, i367, this.chandelierBlock, this.chandelierMeta);
        }
        for (int i31 = 1; i31 <= 3; ++i31) {
            for (i3610 = -2; i3610 <= 2; ++i3610) {
                this.setBlockAndMetadata(world, i3610, i31, 2, this.plank2Block, this.plank2Meta);
            }
        }
        this.setBlockAndMetadata(world, 0, 0, 2, this.plankBlock, this.plankMeta);
        this.setAir(world, 0, 1, 2);
        this.setAir(world, 0, 2, 2);
        this.setBlockAndMetadata(world, -1, 1, 2, this.plank2StairBlock, 0);
        this.setBlockAndMetadata(world, 1, 1, 2, this.plank2StairBlock, 1);
        this.setBlockAndMetadata(world, -1, 2, 2, this.plank2StairBlock, 4);
        this.setBlockAndMetadata(world, 1, 2, 2, this.plank2StairBlock, 5);
        for (i30 = 3; i30 <= 9; ++i30) {
            for (i3610 = -3; i3610 <= 3; ++i3610) {
                for (int i377 = 1; i377 <= 3; ++i377) {
                    this.setAir(world, i3610, i377, i30);
                }
                this.setBlockAndMetadata(world, i3610, 4, i30, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i3610, 0, i30, this.plankBlock, this.plankMeta);
            }
        }
        this.setBlockAndMetadata(world, 0, 3, 6, this.chandelierBlock, this.chandelierMeta);
        for (i30 = 5; i30 <= 7; ++i30) {
            for (i3610 = -1; i3610 <= 1; ++i3610) {
                this.setBlockAndMetadata(world, i3610, 1, i30, this.carpetBlock, this.carpetMeta);
            }
        }
        if (this.isWealthy && random.nextBoolean()) {
            this.placeChest(world, random, 0, 0, 6, 2, LOTRChestContents.HOBBIT_HOLE_TREASURE);
        }
        for (i29 = -3; i29 <= 3; ++i29) {
            for (i365 = 1; i365 <= 3; ++i365) {
                this.setBlockAndMetadata(world, i29, i365, 10, this.plank2Block, this.plank2Meta);
            }
        }
        for (i29 = -1; i29 <= 1; ++i29) {
            this.setBlockAndMetadata(world, i29, 2, 10, LOTRMod.glassPane, 0);
            this.setBlockAndMetadata(world, i29, 3, 10, LOTRMod.glassPane, 0);
            for (i365 = 11; i365 <= 14; ++i365) {
                this.setBlockAndMetadata(world, i29, 1, i365, (Block)Blocks.grass, 0);
                for (i372 = 2; i372 <= 3; ++i372) {
                    this.setAir(world, i29, i372, i365);
                }
            }
            this.setBlockAndMetadata(world, i29, 4, 10, this.plank2Block, this.plank2Meta);
        }
        for (int i28 = 1; i28 <= 3; ++i28) {
            this.setBlockAndMetadata(world, -3, i28, 3, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 3, i28, 3, this.plank2Block, this.plank2Meta);
        }
        for (int i27 = -2; i27 <= 2; ++i27) {
            this.setBlockAndMetadata(world, i27, 3, 3, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (int i26 = 4; i26 <= 9; ++i26) {
            this.setBlockAndMetadata(world, -3, 3, i26, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 3, 3, i26, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (int i25 = -3; i25 <= 3; ++i25) {
            this.setBlockAndMetadata(world, i25, 1, 9, Blocks.oak_stairs, 2);
        }
        this.setBlockAndMetadata(world, -3, 1, 8, Blocks.oak_stairs, 0);
        this.setBlockAndMetadata(world, 3, 1, 8, Blocks.oak_stairs, 1);
        for (i24 = 4; i24 <= 9; ++i24) {
            for (i367 = 1; i367 <= 3; ++i367) {
                this.setBlockAndMetadata(world, -4, i367, i24, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, 4, i367, i24, this.plank2Block, this.plank2Meta);
            }
        }
        this.setAir(world, -4, 1, 6);
        this.setAir(world, -4, 2, 6);
        this.setBlockAndMetadata(world, -4, 1, 5, this.plank2StairBlock, 3);
        this.setBlockAndMetadata(world, -4, 1, 7, this.plank2StairBlock, 2);
        this.setBlockAndMetadata(world, -4, 2, 5, this.plank2StairBlock, 7);
        this.setBlockAndMetadata(world, -4, 2, 7, this.plank2StairBlock, 6);
        this.setAir(world, 4, 1, 6);
        this.setAir(world, 4, 2, 6);
        this.setBlockAndMetadata(world, 4, 1, 5, this.plank2StairBlock, 3);
        this.setBlockAndMetadata(world, 4, 1, 7, this.plank2StairBlock, 2);
        this.setBlockAndMetadata(world, 4, 2, 5, this.plank2StairBlock, 7);
        this.setBlockAndMetadata(world, 4, 2, 7, this.plank2StairBlock, 6);
        this.setBlockAndMetadata(world, -3, 2, 4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 3, 2, 4, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -3, 2, 9, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 3, 2, 9, Blocks.torch, 1);
        this.setAir(world, 2, 1, -6);
        this.setBlockAndMetadata(world, 2, 0, -6, this.floorBlock, this.floorMeta);
        this.setBlockAndMetadata(world, 3, 0, -6, this.floorBlock, this.floorMeta);
        this.setAir(world, 3, 1, -6);
        this.setAir(world, 3, 2, -6);
        this.setBlockAndMetadata(world, 3, 1, -7, this.plank2StairBlock, 3);
        this.setBlockAndMetadata(world, 3, 1, -5, this.plank2StairBlock, 2);
        this.setBlockAndMetadata(world, 3, 2, -7, this.plank2StairBlock, 7);
        this.setBlockAndMetadata(world, 3, 2, -5, this.plank2StairBlock, 6);
        for (i24 = -8; i24 <= -3; ++i24) {
            for (i367 = 4; i367 <= 8; ++i367) {
                if (i367 == 8 && i24 == -8) continue;
                for (i373 = 1; i373 <= 3; ++i373) {
                    this.setAir(world, i367, i373, i24);
                }
                this.setBlockAndMetadata(world, i367, 0, i24, this.floorBlock, this.floorMeta);
                if (i367 >= 7 && i24 <= -7) continue;
                this.setBlockAndMetadata(world, i367, 4, i24, this.plank2Block, this.plank2Meta);
            }
        }
        for (int i23 = 4; i23 <= 7; ++i23) {
            for (i36 = 1; i36 <= 3; ++i36) {
                this.setBlockAndMetadata(world, i23, i36, -2, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i23, i36, -8, this.plank2Block, this.plank2Meta);
            }
            this.setBlockAndMetadata(world, i23, 3, -7, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, i23, 3, -3, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (int i22 = -7; i22 <= -3; ++i22) {
            for (i36 = 1; i36 <= 3; ++i36) {
                this.setBlockAndMetadata(world, 8, i36, i22, this.plank2Block, this.plank2Meta);
            }
        }
        for (int i21 = 1; i21 <= 2; ++i21) {
            for (i373 = 5; i373 <= 6; ++i373) {
                this.setAir(world, i373, i21, -8);
                this.setBlockAndMetadata(world, i373, i21, -9, Blocks.bookshelf, 0);
            }
            for (i36 = -6; i36 <= -4; ++i36) {
                this.setAir(world, 8, i21, i36);
                this.setBlockAndMetadata(world, 9, i21, i36, Blocks.bookshelf, 0);
            }
        }
        this.setBlockAndMetadata(world, 6, 3, -5, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, 5, 1, -5, Blocks.oak_stairs, 3);
        this.setBlockAndMetadata(world, 5, 1, -3, (Block)Blocks.wooden_slab, 8);
        this.placeChest(world, random, 7, 1, -3, 2, LOTRChestContents.HOBBIT_HOLE_STUDY);
        if (random.nextBoolean()) {
            this.placeWallBanner(world, 3, 3, -4, LOTRItemBanner.BannerType.HOBBIT, 1);
        }
        this.setAir(world, -2, 1, -6);
        this.setBlockAndMetadata(world, -2, 0, -6, this.floorBlock, this.floorMeta);
        this.setBlockAndMetadata(world, -3, 0, -6, this.floorBlock, this.floorMeta);
        this.setAir(world, -3, 1, -6);
        this.setAir(world, -3, 2, -6);
        this.setBlockAndMetadata(world, -3, 1, -7, this.plank2StairBlock, 3);
        this.setBlockAndMetadata(world, -3, 1, -5, this.plank2StairBlock, 2);
        this.setBlockAndMetadata(world, -3, 2, -7, this.plank2StairBlock, 7);
        this.setBlockAndMetadata(world, -3, 2, -5, this.plank2StairBlock, 6);
        for (int i20 = -7; i20 <= -4; ++i20) {
            for (i36 = -4; i36 >= -7; --i36) {
                this.setBlockAndMetadata(world, i36, 0, i20, this.floorBlock, this.floorMeta);
                for (int i378 = 1; i378 <= 3; ++i378) {
                    this.setAir(world, i36, i378, i20);
                }
                this.setBlockAndMetadata(world, i36, 4, i20, this.plank2Block, this.plank2Meta);
            }
        }
        for (int i19 = -4; i19 >= -7; --i19) {
            for (i36 = 1; i36 <= 3; ++i36) {
                this.setBlockAndMetadata(world, i19, i36, -8, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i19, i36, -3, this.plank2Block, this.plank2Meta);
            }
            this.setBlockAndMetadata(world, i19, 3, -7, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, i19, 3, -4, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (i18 = -7; i18 <= -3; ++i18) {
            for (i36 = 1; i36 <= 3; ++i36) {
                this.setBlockAndMetadata(world, -8, i36, i18, this.plank2Block, this.plank2Meta);
            }
        }
        for (i18 = -7; i18 <= -6; ++i18) {
            for (i36 = -5; i36 >= -6; --i36) {
                this.setBlockAndMetadata(world, i36, 1, i18, this.carpetBlock, this.carpetMeta);
            }
        }
        for (i17 = -5; i17 >= -6; --i17) {
            this.setBlockAndMetadata(world, i17, 0, -8, this.floorBlock, this.floorMeta);
            this.setBlockAndMetadata(world, i17, 1, -8, (Block)Blocks.wooden_slab, 8);
            this.setBlockAndMetadata(world, i17, 2, -8, Blocks.bookshelf, 0);
            this.setBlockAndMetadata(world, i17, 1, -9, this.plank2Block, this.plank2Meta);
        }
        this.setBlockAndMetadata(world, -4, 1, -4, Blocks.planks, 0);
        this.setBlockAndMetadata(world, -7, 1, -4, Blocks.planks, 0);
        this.setBlockAndMetadata(world, -4, 2, -4, Blocks.torch, 5);
        this.setBlockAndMetadata(world, -7, 2, -4, Blocks.torch, 5);
        this.setBlockAndMetadata(world, -5, 1, -5, this.bedBlock, 0);
        this.setBlockAndMetadata(world, -5, 1, -4, this.bedBlock, 8);
        this.setBlockAndMetadata(world, -6, 1, -5, this.bedBlock, 0);
        this.setBlockAndMetadata(world, -6, 1, -4, this.bedBlock, 8);
        this.spawnItemFrame(world, -8, 2, -6, 1, new ItemStack(Items.clock));
        this.setBlockAndMetadata(world, 4, 0, 6, this.plankBlock, this.plankMeta);
        for (i17 = 5; i17 <= 6; ++i17) {
            this.setBlockAndMetadata(world, i17, 0, 7, this.floorBlock, this.floorMeta);
            for (i3611 = 1; i3611 <= 3; ++i3611) {
                this.setAir(world, i17, i3611, 7);
            }
            this.setBlockAndMetadata(world, i17, 4, 7, this.plank2Block, this.plank2Meta);
        }
        for (i17 = 5; i17 <= 7; ++i17) {
            this.setBlockAndMetadata(world, i17, 0, 6, this.floorBlock, this.floorMeta);
            for (i3611 = 1; i3611 <= 3; ++i3611) {
                this.setAir(world, i17, i3611, 6);
            }
            this.setBlockAndMetadata(world, i17, 4, 6, this.plank2Block, this.plank2Meta);
        }
        for (i17 = 5; i17 <= 8; ++i17) {
            this.setBlockAndMetadata(world, i17, 0, 5, this.floorBlock, this.floorMeta);
            for (i3611 = 1; i3611 <= 3; ++i3611) {
                this.setAir(world, i17, i3611, 5);
            }
            this.setBlockAndMetadata(world, i17, 4, 5, this.plank2Block, this.plank2Meta);
        }
        for (int i16 = 1; i16 <= 3; ++i16) {
            this.setBlockAndMetadata(world, 7, i16, 7, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 8, i16, 6, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, 9, i16, 5, this.plank2Block, this.plank2Meta);
        }
        this.setBlockAndMetadata(world, 7, 2, 6, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 8, 2, 5, Blocks.torch, 1);
        for (i15 = 4; i15 >= -1; --i15) {
            for (int i379 = 4; i379 <= 9; ++i379) {
                this.setBlockAndMetadata(world, i379, 0, i15, this.floorBlock, this.floorMeta);
                for (int i385 = 1; i385 <= 3; ++i385) {
                    this.setAir(world, i379, i385, i15);
                }
                this.setBlockAndMetadata(world, i379, 4, i15, this.plank2Block, this.plank2Meta);
            }
            for (int i3612 = 1; i3612 <= 3; ++i3612) {
                this.setBlockAndMetadata(world, 3, i3612, i15, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, 10, i3612, i15, this.plank2Block, this.plank2Meta);
            }
            this.setBlockAndMetadata(world, 4, 3, i15, this.plank2SlabBlock, this.plank2SlabMeta | 8);
            this.setBlockAndMetadata(world, 9, 3, i15, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        }
        for (i15 = 2; i15 >= 0; --i15) {
            this.setBlockAndMetadata(world, 4, 1, i15, Blocks.oak_stairs, 0);
            this.setBlockAndMetadata(world, 9, 1, i15, Blocks.oak_stairs, 1);
        }
        this.setBlockAndMetadata(world, 4, 1, -1, Blocks.oak_stairs, 3);
        this.setBlockAndMetadata(world, 4, 1, 3, Blocks.oak_stairs, 2);
        this.setBlockAndMetadata(world, 9, 1, -1, Blocks.oak_stairs, 3);
        this.setBlockAndMetadata(world, 9, 1, 3, Blocks.oak_stairs, 2);
        this.setBlockAndMetadata(world, 6, 3, 1, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, 7, 3, 1, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, 6, 1, 2, Blocks.planks, 1);
        this.setBlockAndMetadata(world, 7, 1, 2, Blocks.planks, 1);
        this.setBlockAndMetadata(world, 6, 1, 1, (Block)Blocks.wooden_slab, 9);
        this.setBlockAndMetadata(world, 7, 1, 1, (Block)Blocks.wooden_slab, 9);
        this.setBlockAndMetadata(world, 6, 1, 0, Blocks.planks, 1);
        this.setBlockAndMetadata(world, 7, 1, 0, Blocks.planks, 1);
        for (int i14 = 6; i14 <= 7; ++i14) {
            for (i362 = 2; i362 >= 0; --i362) {
                if (random.nextInt(3) == 0) {
                    this.placeMug(world, random, i14, 2, i362, random.nextInt(4), LOTRFoods.HOBBIT_DRINK);
                    continue;
                }
                if (random.nextBoolean()) {
                    this.placePlateWithCertainty(world, random, i14, 2, i362, this.plateBlock, LOTRFoods.HOBBIT);
                    continue;
                }
                this.placePlate(world, random, i14, 2, i362, this.plateBlock, LOTRFoods.HOBBIT);
            }
        }
        this.setBlockAndMetadata(world, 5, 3, 7, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        this.setBlockAndMetadata(world, 6, 3, 7, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        this.setBlockAndMetadata(world, 7, 3, 6, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        this.setBlockAndMetadata(world, 8, 3, 5, this.plank2SlabBlock, this.plank2SlabMeta | 8);
        for (int i13 = 1; i13 <= 3; ++i13) {
            for (i362 = 5; i362 <= 6; ++i362) {
                this.setBlockAndMetadata(world, i362, i13, 8, this.plank2Block, this.plank2Meta);
            }
            for (i362 = 8; i362 <= 9; ++i362) {
                this.setBlockAndMetadata(world, i362, i13, -2, this.plank2Block, this.plank2Meta);
            }
        }
        this.setBlockAndMetadata(world, -4, 0, 6, this.plankBlock, this.plankMeta);
        for (i12 = 7; i12 >= 3; --i12) {
            for (i362 = -5; i362 >= -7; --i362) {
                this.setBlockAndMetadata(world, i362, 0, i12, (Block)Blocks.double_stone_slab, 0);
                for (int i3710 = 1; i3710 <= 3; ++i3710) {
                    this.setAir(world, i362, i3710, i12);
                }
                this.setBlockAndMetadata(world, i362, 4, i12, this.plank2Block, this.plank2Meta);
            }
        }
        for (i12 = 6; i12 >= 3; --i12) {
            for (i362 = -5; i362 >= -6; --i362) {
                this.setBlockAndMetadata(world, i362, 0, i12, this.floorBlock, this.floorMeta);
            }
        }
        this.setBlockAndMetadata(world, -5, 1, 8, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -6, 1, 8, Blocks.crafting_table, 0);
        this.setBlockAndMetadata(world, -7, 1, 8, this.tableBlock, 0);
        for (int i11 = -7; i11 <= -5; ++i11) {
            this.setAir(world, i11, 2, 8);
            this.setBlockAndMetadata(world, i11, 2, 9, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, i11, 3, 8, (Block)Blocks.double_stone_slab, 0);
        }
        this.setBlockAndMetadata(world, -8, 1, 8, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -8, 2, 8, this.plank2Block, this.plank2Meta);
        for (i10 = 6; i10 <= 7; ++i10) {
            for (int i3613 = 1; i3613 <= 3; ++i3613) {
                this.setBlockAndMetadata(world, -8, i3613, i10, this.plank2Block, this.plank2Meta);
            }
        }
        for (i10 = 3; i10 <= 5; ++i10) {
            this.setBlockAndMetadata(world, -8, 0, i10, (Block)Blocks.double_stone_slab, 0);
            this.setAir(world, -8, 2, i10);
            this.setBlockAndMetadata(world, -9, 2, i10, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -8, 3, i10, (Block)Blocks.double_stone_slab, 0);
        }
        this.setBlockAndMetadata(world, -8, 1, 4, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -8, 1, 5, LOTRMod.hobbitOven, 4);
        this.setBlockAndMetadata(world, -8, 1, 3, LOTRMod.hobbitOven, 4);
        this.setBlockAndMetadata(world, -8, 1, 4, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, -6, 3, 5, this.chandelierBlock, this.chandelierMeta);
        for (int i9 = -4; i9 >= -9; --i9) {
            for (i364 = 1; i364 <= 3; ++i364) {
                this.setBlockAndMetadata(world, i9, i364, 2, this.plank2Block, this.plank2Meta);
            }
        }
        this.setBlockAndMetadata(world, -6, 0, 2, this.plankBlock, this.plankMeta);
        this.setAir(world, -6, 1, 2);
        this.setAir(world, -6, 2, 2);
        this.setBlockAndMetadata(world, -7, 1, 2, this.plank2StairBlock, 0);
        this.setBlockAndMetadata(world, -5, 1, 2, this.plank2StairBlock, 1);
        this.setBlockAndMetadata(world, -7, 2, 2, this.plank2StairBlock, 4);
        this.setBlockAndMetadata(world, -5, 2, 2, this.plank2StairBlock, 5);
        for (int i8 = -2; i8 <= 1; ++i8) {
            for (int i3711 = -9; i3711 <= -4; ++i3711) {
                this.setBlockAndMetadata(world, i3711, 0, i8, this.plankBlock, this.plankMeta);
                for (int i386 = 1; i386 <= 3; ++i386) {
                    this.setAir(world, i3711, i386, i8);
                }
                this.setBlockAndMetadata(world, i3711, 4, i8, (Block)Blocks.double_stone_slab, 0);
            }
            for (i364 = 1; i364 <= 3; ++i364) {
                this.setBlockAndMetadata(world, -10, i364, i8, this.plank2Block, this.plank2Meta);
            }
        }
        for (int i7 = -9; i7 <= -4; ++i7) {
            this.setBlockAndMetadata(world, i7, 1, -2, (Block)Blocks.wooden_slab, 8);
            this.setBlockAndMetadata(world, i7, 3, -2, (Block)Blocks.wooden_slab, 0);
        }
        for (i6 = -2; i6 <= 1; ++i6) {
            this.setBlockAndMetadata(world, -4, 1, i6, (Block)Blocks.wooden_slab, 8);
            this.setBlockAndMetadata(world, -4, 3, i6, (Block)Blocks.wooden_slab, 0);
            this.setBlockAndMetadata(world, -9, 1, i6, (Block)Blocks.wooden_slab, 8);
            this.setBlockAndMetadata(world, -9, 3, i6, (Block)Blocks.wooden_slab, 0);
        }
        this.setBlockAndMetadata(world, -8, 1, 1, (Block)Blocks.wooden_slab, 8);
        this.setBlockAndMetadata(world, -8, 3, 1, (Block)Blocks.wooden_slab, 0);
        this.setBlockAndMetadata(world, -6, 3, 1, Blocks.torch, 4);
        for (i6 = -2; i6 <= 1; ++i6) {
            if (random.nextInt(3) == 0) continue;
            Block cakeBlock = LOTRWorldGenHobbitStructure.getRandomCakeBlock(random);
            this.setBlockAndMetadata(world, -4, 2, i6, cakeBlock, 0);
        }
        for (int i5 = -7; i5 <= -6; ++i5) {
            this.placePlateWithCertainty(world, random, i5, 2, -2, this.plateBlock, LOTRFoods.HOBBIT);
        }
        this.placeBarrel(world, random, -5, 2, -2, 3, LOTRFoods.HOBBIT_DRINK);
        for (int i4 = 1; i4 <= 3; ++i4) {
            this.setBlockAndMetadata(world, -9, i4, -3, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, -4, i4, 3, this.plank2Block, this.plank2Meta);
        }
        this.placeChest(world, random, -8, 2, -2, (Block)Blocks.chest, 3, LOTRChestContents.HOBBIT_HOLE_LARDER);
        this.placeChest(world, random, -9, 2, -1, (Block)Blocks.chest, 4, LOTRChestContents.HOBBIT_HOLE_LARDER);
        this.placeChest(world, random, -9, 2, 0, (Block)Blocks.chest, 4, LOTRChestContents.HOBBIT_HOLE_LARDER);
        this.placeChest(world, random, -8, 2, 1, (Block)Blocks.chest, 2, LOTRChestContents.HOBBIT_HOLE_LARDER);
        if (gateFlip) {
            this.setBlockAndMetadata(world, -1, 2, -9, (Block)Blocks.tripwire_hook, 0);
        } else {
            this.setBlockAndMetadata(world, 1, 2, -9, (Block)Blocks.tripwire_hook, 0);
        }
        int grassRadius = radius - 3;
        int grass = MathHelper.getRandomIntegerInRange((Random)random, (int)80, (int)120);
        for (int l = 0; l < grass; ++l) {
            i368 = MathHelper.getRandomIntegerInRange((Random)random, (int)(-grassRadius), (int)grassRadius);
            i374 = MathHelper.getRandomIntegerInRange((Random)random, (int)(-grassRadius), (int)grassRadius);
            i38 = this.getTopBlock(world, i368, i374);
            this.plantTallGrass(world, random, i368, i38, i374);
        }
        int flowers = MathHelper.getRandomIntegerInRange((Random)random, (int)8, (int)16);
        for (int i35 = 0; i35 < flowers; ++i35) {
            int i3614 = MathHelper.getRandomIntegerInRange((Random)random, (int)(-grassRadius), (int)grassRadius);
            int i3712 = MathHelper.getRandomIntegerInRange((Random)random, (int)(-grassRadius), (int)grassRadius);
            int i387 = this.getTopBlock(world, i3614, i3712);
            this.plantFlower(world, random, i3614, i387, i3712);
        }
        if (random.nextInt(4) == 0) {
            i368 = MathHelper.getRandomIntegerInRange((Random)random, (int)(-grassRadius), (int)grassRadius);
            i374 = MathHelper.getRandomIntegerInRange((Random)random, (int)(-grassRadius), (int)grassRadius);
            i38 = this.getTopBlock(world, i368, i374);
            WorldGenAbstractTree worldGenAbstractTree = LOTRBiome.shire.func_150567_a(random);
            worldGenAbstractTree.generate(world, random, this.getX(i368, i374), this.getY(i38), this.getZ(i368, i374));
        }
        int homeRadius = MathHelper.floor_double((double)((double)radius * 1.5));
        this.spawnHobbitCouple(world, 0, 1, 0, homeRadius);
        return true;
    }
}

