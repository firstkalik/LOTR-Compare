/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockTripWireHook
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityHobbitBartender;
import lotr.common.entity.npc.LOTREntityHobbitShirriff;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.item.LOTRItemBanner;
import lotr.common.item.LOTRItemLeatherHat;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenHobbitStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRWorldGenHobbitTavern
extends LOTRWorldGenHobbitStructure {
    private String[] tavernName;
    private String[] tavernNameSign;
    private String tavernNameNPC;

    public LOTRWorldGenHobbitTavern(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.tavernName = LOTRNames.getHobbitTavernName(random);
        this.tavernNameSign = new String[]{"", this.tavernName[0], this.tavernName[1], ""};
        this.tavernNameNPC = this.tavernName[0] + " " + this.tavernName[1];
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int j1;
        int k12;
        int j12;
        int i1;
        int i2;
        int i122;
        int i132;
        int room;
        int i14;
        int k13;
        int i15;
        int k14;
        int j13;
        int i16;
        int j14;
        int j15;
        int k15;
        int k16;
        this.setOriginAndRotation(world, i, j, k, rotation, 12);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i17 = -18; i17 <= 18; ++i17) {
                for (int k17 = -12; k17 <= 19; ++k17) {
                    j13 = this.getTopBlock(world, i17, k17) - 1;
                    if (!this.isSurface(world, i17, j13, k17)) {
                        return false;
                    }
                    if (j13 < minHeight) {
                        minHeight = j13;
                    }
                    if (j13 > maxHeight) {
                        maxHeight = j13;
                    }
                    if (maxHeight - minHeight <= 8) continue;
                    return false;
                }
            }
        }
        for (i14 = -16; i14 <= 16; ++i14) {
            for (k15 = -12; k15 <= 18; ++k15) {
                int j16;
                int i22 = Math.abs(i14);
                int grassHeight = -1;
                if (i22 <= 14) {
                    if (k15 <= -6) {
                        grassHeight = k15 == -7 && i22 <= 1 || k15 == -6 && i22 <= 3 ? 1 : 0;
                    } else if (k15 <= -5 && (i22 == 11 || i22 <= 5)) {
                        grassHeight = 1;
                    } else if (k15 <= -4 && i22 <= 11) {
                        grassHeight = 1;
                    } else if (k15 <= -3 && i22 <= 3) {
                        grassHeight = 1;
                    }
                }
                if (grassHeight >= 0) {
                    for (j13 = grassHeight; !(j13 < -1 && this.isOpaque(world, i14, j13, k15) || this.getY(j13) < 0); --j13) {
                        if (j13 == grassHeight) {
                            this.setBlockAndMetadata(world, i14, j13, k15, (Block)Blocks.grass, 0);
                        } else {
                            this.setBlockAndMetadata(world, i14, j13, k15, Blocks.dirt, 0);
                        }
                        this.setGrassToDirt(world, i14, j13 - 1, k15);
                    }
                    for (j13 = grassHeight + 1; j13 <= 12; ++j13) {
                        this.setAir(world, i14, j13, k15);
                    }
                    continue;
                }
                boolean wood = false;
                boolean beam = false;
                if (k15 >= -5 && k15 <= 17) {
                    wood = i22 < 15 || k15 > -4 && k15 < 16;
                }
                if (i22 == 15 && (k15 == -4 || k15 == 16)) {
                    beam = true;
                }
                if (k15 == 18 && i22 <= 14 && IntMath.mod((int)i14, (int)5) == 0) {
                    beam = true;
                }
                if (!beam && !wood) continue;
                for (j16 = 5; !(j16 < 0 && this.isOpaque(world, i14, j16, k15) || this.getY(j16) < 0); --j16) {
                    if (beam) {
                        this.setBlockAndMetadata(world, i14, j16, k15, this.beamBlock, this.beamMeta);
                    } else {
                        this.setBlockAndMetadata(world, i14, j16, k15, this.plankBlock, this.plankMeta);
                    }
                    this.setGrassToDirt(world, i14, j16 - 1, k15);
                }
                this.setBlockAndMetadata(world, i14, 6, k15, this.plankBlock, this.plankMeta);
                for (j16 = 8; j16 <= 12; ++j16) {
                    this.setAir(world, i14, j16, k15);
                }
            }
        }
        for (i14 = -16; i14 <= 16; ++i14) {
            i2 = Math.abs(i14);
            if (i2 <= 4) {
                this.setBlockAndMetadata(world, i14, 1, -10, this.outFenceBlock, this.outFenceMeta);
            }
            if (i2 >= 4 && i2 <= 11) {
                this.setBlockAndMetadata(world, i14, 1, -9, this.outFenceBlock, this.outFenceMeta);
            }
            if (i2 >= 11 && i2 <= 13) {
                this.setBlockAndMetadata(world, i14, 1, -8, this.outFenceBlock, this.outFenceMeta);
            }
            if (i2 == 13) {
                this.setBlockAndMetadata(world, i14, 1, -7, this.outFenceBlock, this.outFenceMeta);
                this.setBlockAndMetadata(world, i14, 1, -6, this.outFenceBlock, this.outFenceMeta);
            }
            if (i2 == 0) {
                this.setBlockAndMetadata(world, i14, 1, -10, this.outFenceGateBlock, 0);
            }
            if (i2 == 4) {
                this.setBlockAndMetadata(world, i14, 2, -10, Blocks.torch, 5);
            }
            if (i2 <= 1) {
                if (i14 == 0) {
                    this.setBlockAndMetadata(world, i14, 0, -12, this.pathBlock, this.pathMeta);
                    this.setBlockAndMetadata(world, i14, 0, -11, this.pathBlock, this.pathMeta);
                    this.setBlockAndMetadata(world, i14, 0, -10, this.pathBlock, this.pathMeta);
                }
                this.setBlockAndMetadata(world, i14, 0, -9, this.pathBlock, this.pathMeta);
                this.setBlockAndMetadata(world, i14, 0, -8, this.pathBlock, this.pathMeta);
                this.setBlockAndMetadata(world, i14, 1, -7, this.pathSlabBlock, this.pathSlabMeta);
                this.setBlockAndMetadata(world, i14, 1, -6, this.pathSlabBlock, this.pathSlabMeta);
                for (k1 = -5; k1 <= -2; ++k1) {
                    this.setBlockAndMetadata(world, i14, 1, k1, this.pathBlock, this.pathMeta);
                }
            }
            if (i2 == 5 || i2 == 11) {
                this.setGrassToDirt(world, i14, 0, -4);
                for (j14 = 1; j14 <= 5; ++j14) {
                    this.setBlockAndMetadata(world, i14, j14, -4, this.beamBlock, this.beamMeta);
                }
            }
            if (i2 == 6 || i2 == 10) {
                this.setBlockAndMetadata(world, i14, 3, -4, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i14, 2, -4, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i14, 1, -5, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i14, 0, -5, (Block)Blocks.grass, 0);
            }
            if (i2 >= 7 && i2 <= 9) {
                this.setBlockAndMetadata(world, i14, 2, -5, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i14, 1, -5, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i14, 0, -5, (Block)Blocks.grass, 0);
                this.setBlockAndMetadata(world, i14, 1, -6, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i14, 2, -4, this.brickBlock, this.brickMeta);
                this.setGrassToDirt(world, i14, 1, -4);
                this.setBlockAndMetadata(world, i14, 3, -3, LOTRMod.glassPane, 0);
                this.setBlockAndMetadata(world, i14, 4, -3, LOTRMod.glassPane, 0);
                if (i2 == 7 || i2 == 9) {
                    this.placeFlowerPot(world, i14, 3, -4, this.getRandomFlower(world, random));
                }
            }
            if (i2 >= 6 && i2 <= 10) {
                this.setBlockAndMetadata(world, i14, 5, -4, this.plankStairBlock, 6);
            }
            if (i2 >= 5 && i2 <= 11) {
                this.setBlockAndMetadata(world, i14, 6, -4, this.plankBlock, this.plankMeta);
            }
            if (i2 == 13) {
                this.setBlockAndMetadata(world, i14, 3, -6, this.fence2Block, this.fence2Meta);
                this.setBlockAndMetadata(world, i14, 4, -6, Blocks.torch, 5);
            }
            if (i2 == 4) {
                this.setBlockAndMetadata(world, i14, 2, -4, this.hedgeBlock, this.hedgeMeta);
            }
            if (i2 != 3) continue;
            this.setBlockAndMetadata(world, i14, 2, -4, this.hedgeBlock, this.hedgeMeta);
            this.setBlockAndMetadata(world, i14, 2, -3, this.hedgeBlock, this.hedgeMeta);
        }
        for (i14 = -12; i14 <= 12; ++i14) {
            for (k15 = -8; k15 <= -2; ++k15) {
                for (j14 = 0; j14 <= 1; ++j14) {
                    if (this.getBlock(world, i14, j14, k15) != Blocks.grass || !this.isAir(world, i14, j14 + 1, k15) || random.nextInt(12) != 0) continue;
                    this.plantFlower(world, random, i14, j14 + 1, k15);
                }
            }
        }
        for (i14 = -2; i14 <= 2; ++i14) {
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setAir(world, i14, j1, -2);
            }
        }
        this.setBlockAndMetadata(world, -2, 2, -2, this.plankStairBlock, 0);
        this.setBlockAndMetadata(world, -2, 4, -2, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 2, 2, -2, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 2, 4, -2, this.plankStairBlock, 5);
        for (i14 = -1; i14 <= 1; ++i14) {
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setAir(world, i14, j1, -1);
                this.setBlockAndMetadata(world, i14, j1, 0, this.brickBlock, this.brickMeta);
            }
        }
        this.setBlockAndMetadata(world, -1, 2, -1, this.plankStairBlock, 0);
        this.setBlockAndMetadata(world, -1, 4, -1, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 1, 2, -1, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 1, 4, -1, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, 0, 1, -1, this.pathBlock, this.pathMeta);
        this.setBlockAndMetadata(world, 0, 1, 0, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 0, 2, 0, this.doorBlock, 3);
        this.setBlockAndMetadata(world, 0, 3, 0, this.doorBlock, 8);
        this.placeSign(world, 0, 4, -1, Blocks.wall_sign, 2, this.tavernNameSign);
        this.setBlockAndMetadata(world, -2, 3, -2, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 2, 3, -2, Blocks.torch, 4);
        this.setBlockAndMetadata(world, -3, 4, -3, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, -2, 5, -3, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, -1, 5, -3, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, 0, 5, -3, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, 1, 5, -3, this.tileSlabBlock, this.tileSlabMeta | 8);
        this.setBlockAndMetadata(world, 2, 5, -3, this.tileSlabBlock, this.tileSlabMeta);
        this.setBlockAndMetadata(world, 3, 4, -3, this.tileSlabBlock, this.tileSlabMeta | 8);
        if (random.nextBoolean()) {
            this.placeSign(world, -2, 2, -10, Blocks.standing_sign, MathHelper.getRandomIntegerInRange((Random)random, (int)7, (int)9), this.tavernNameSign);
        } else {
            this.placeSign(world, 2, 2, -10, Blocks.standing_sign, MathHelper.getRandomIntegerInRange((Random)random, (int)7, (int)9), this.tavernNameSign);
        }
        for (i14 = -3; i14 <= 3; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, -3, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -3, 6, -4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -4, 6, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -4, 6, -5, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 3, 6, -4, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 4, 6, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 4, 6, -5, this.roofStairBlock, 1);
        for (i14 = -11; i14 <= -5; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, -5, this.roofStairBlock, 2);
        }
        for (i14 = 5; i14 <= 11; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, -5, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -11, 6, -6, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 11, 6, -6, this.roofStairBlock, 1);
        for (i14 = -14; i14 <= -12; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, -6, this.roofStairBlock, 2);
        }
        for (i14 = 12; i14 <= 14; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, -6, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -15, 6, -6, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -15, 6, -5, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -16, 6, -5, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -16, 6, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 15, 6, -6, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 15, 6, -5, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 16, 6, -5, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 16, 6, -4, this.roofStairBlock, 2);
        for (int k18 = -4; k18 <= 16; ++k18) {
            this.setBlockAndMetadata(world, -17, 6, k18, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 17, 6, k18, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -16, 6, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -16, 6, 17, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -15, 6, 17, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -15, 6, 18, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 16, 6, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 16, 6, 17, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 15, 6, 17, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 15, 6, 18, this.roofStairBlock, 0);
        for (i14 = -14; i14 <= -11; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, 18, this.roofStairBlock, 3);
        }
        for (i14 = 11; i14 <= 14; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, 18, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -11, 6, 19, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 11, 6, 19, this.roofStairBlock, 0);
        for (i14 = -10; i14 <= 10; ++i14) {
            this.setBlockAndMetadata(world, i14, 6, 18, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i14, 6, 19, this.roofStairBlock, 3);
            i2 = IntMath.mod((int)i14, (int)5);
            if (IntMath.mod((int)i14, (int)5) == 0) continue;
            this.setBlockAndMetadata(world, i14, 5, 18, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i14, 1, 18, this.brickBlock, this.brickMeta);
            this.setGrassToDirt(world, i14, 0, 18);
            if (i2 == 1 || i2 == 4) {
                this.setBlockAndMetadata(world, i14, 2, 18, this.hedgeBlock, this.hedgeMeta);
            } else {
                this.placeFlowerPot(world, i14, 2, 18, this.getRandomFlower(world, random));
            }
            if (this.isOpaque(world, i14, 0, 18)) continue;
            this.setBlockAndMetadata(world, i14, 0, 18, this.plankStairBlock, 7);
        }
        int[] i18 = new int[]{-15, 12};
        i2 = i18.length;
        for (j14 = 0; j14 < i2; ++j14) {
            int i23;
            for (i23 = i122 = i18[j14]; i23 <= i122 + 3; ++i23) {
                this.setBlockAndMetadata(world, i23, 11, 6, this.brickStairBlock, 2);
                this.setBlockAndMetadata(world, i23, 11, 8, this.brickStairBlock, 3);
            }
            this.setBlockAndMetadata(world, i122, 11, 7, this.brickStairBlock, 1);
            this.setBlockAndMetadata(world, i122 + 3, 11, 7, this.brickStairBlock, 0);
            for (i23 = i122 + 1; i23 <= i122 + 2; ++i23) {
                this.setBlockAndMetadata(world, i23, 11, 7, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i23, 12, 7, Blocks.flower_pot, 0);
            }
        }
        for (int i122 : new int[]{-16, 16}) {
            for (k16 = 3; k16 <= 4; ++k16) {
                for (j12 = 2; j12 <= 3; ++j12) {
                    this.setBlockAndMetadata(world, i122, j12, k16, LOTRMod.glassPane, 0);
                }
            }
        }
        for (int i122 : new int[]{-17, 17}) {
            for (k16 = 2; k16 <= 10; ++k16) {
                if (k16 == 6) continue;
                this.setBlockAndMetadata(world, i122, 1, k16, this.brickBlock, this.brickMeta);
                this.setGrassToDirt(world, i122, 0, k16);
                if (k16 == 2 || k16 == 5 || k16 == 7 || k16 == 10) {
                    this.setBlockAndMetadata(world, i122, 2, k16, this.hedgeBlock, this.hedgeMeta);
                    continue;
                }
                this.placeFlowerPot(world, i122, 2, k16, this.getRandomFlower(world, random));
            }
            for (int k19 : new int[]{1, 6, 11}) {
                for (int j17 = 5; !(j17 < 0 && this.isOpaque(world, i122, j17, k19) || this.getY(j17) < 0); --j17) {
                    this.setBlockAndMetadata(world, i122, j17, k19, this.beamBlock, this.beamMeta);
                    this.setGrassToDirt(world, i122, j17, k19);
                }
            }
            for (k16 = 1; k16 <= 11; ++k16) {
                this.setBlockAndMetadata(world, i122, 6, k16, this.roofBlock, this.roofMeta);
            }
        }
        for (k14 = 2; k14 <= 10; ++k14) {
            if (k14 == 6) continue;
            if (!this.isOpaque(world, -17, 0, k14)) {
                this.setBlockAndMetadata(world, -17, 0, k14, this.plankStairBlock, 5);
            }
            this.setBlockAndMetadata(world, -17, 5, k14, this.plankStairBlock, 5);
            if (!this.isOpaque(world, 17, 0, k14)) {
                this.setBlockAndMetadata(world, 17, 0, k14, this.plankStairBlock, 4);
            }
            this.setBlockAndMetadata(world, 17, 5, k14, this.plankStairBlock, 4);
        }
        for (k14 = 7; k14 <= 10; ++k14) {
            this.setBlockAndMetadata(world, -17, 5, k14, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, 17, 5, k14, this.plankStairBlock, 4);
        }
        for (k14 = 1; k14 <= 11; ++k14) {
            this.setBlockAndMetadata(world, -18, 6, k14, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 18, 6, k14, this.roofStairBlock, 0);
        }
        for (int i122 : new int[]{-18, 18}) {
            this.setBlockAndMetadata(world, i122, 6, 0, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i122, 6, 12, this.roofStairBlock, 3);
        }
        for (i16 = -4; i16 <= 4; ++i16) {
            this.setBlockAndMetadata(world, i16, 7, -2, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -4, 7, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -5, 7, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -5, 7, -4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 4, 7, -3, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 5, 7, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 5, 7, -4, this.roofStairBlock, 1);
        for (i16 = -12; i16 <= -6; ++i16) {
            this.setBlockAndMetadata(world, i16, 7, -4, this.roofStairBlock, 2);
        }
        for (i16 = 6; i16 <= 12; ++i16) {
            this.setBlockAndMetadata(world, i16, 7, -4, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -12, 7, -5, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -13, 7, -5, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -14, 7, -5, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -14, 7, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -15, 7, -4, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -15, 7, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 12, 7, -5, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 13, 7, -5, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 14, 7, -5, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 14, 7, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 15, 7, -4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 15, 7, -3, this.roofStairBlock, 2);
        for (k12 = -3; k12 <= 0; ++k12) {
            this.setBlockAndMetadata(world, -16, 7, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 16, 7, k12, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -16, 7, 1, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 16, 7, 1, this.roofStairBlock, 2);
        for (k12 = 1; k12 <= 11; ++k12) {
            this.setBlockAndMetadata(world, -17, 7, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 17, 7, k12, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -16, 7, 11, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 16, 7, 11, this.roofStairBlock, 3);
        for (k12 = 12; k12 <= 15; ++k12) {
            this.setBlockAndMetadata(world, -16, 7, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 16, 7, k12, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -15, 7, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -15, 7, 16, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -14, 7, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -14, 7, 17, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 15, 7, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 15, 7, 16, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 14, 7, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 14, 7, 17, this.roofStairBlock, 0);
        for (i16 = -13; i16 <= -11; ++i16) {
            this.setBlockAndMetadata(world, i16, 7, 17, this.roofStairBlock, 3);
        }
        for (i16 = 11; i16 <= 13; ++i16) {
            this.setBlockAndMetadata(world, i16, 7, 17, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -10, 7, 17, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 10, 7, 17, this.roofStairBlock, 0);
        for (i16 = -10; i16 <= 10; ++i16) {
            this.setBlockAndMetadata(world, i16, 7, 18, this.roofStairBlock, 3);
        }
        for (i16 = -5; i16 <= 5; ++i16) {
            this.setBlockAndMetadata(world, i16, 8, -1, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -5, 8, -2, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -6, 8, -2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -6, 8, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 5, 8, -2, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 6, 8, -2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 6, 8, -3, this.roofStairBlock, 1);
        for (i16 = -13; i16 <= -7; ++i16) {
            this.setBlockAndMetadata(world, i16, 8, -3, this.roofStairBlock, 2);
        }
        for (i16 = 7; i16 <= 13; ++i16) {
            this.setBlockAndMetadata(world, i16, 8, -3, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -13, 8, -4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 13, 8, -4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -14, 8, -3, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -14, 8, -2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 14, 8, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 14, 8, -2, this.roofStairBlock, 2);
        for (k12 = -2; k12 <= 1; ++k12) {
            this.setBlockAndMetadata(world, -15, 8, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 15, 8, k12, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -15, 8, 2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 15, 8, 2, this.roofStairBlock, 2);
        for (k12 = 2; k12 <= 10; ++k12) {
            this.setBlockAndMetadata(world, -16, 8, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 16, 8, k12, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -15, 8, 10, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 15, 8, 10, this.roofStairBlock, 3);
        for (k12 = 11; k12 <= 14; ++k12) {
            this.setBlockAndMetadata(world, -15, 8, k12, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 15, 8, k12, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -14, 8, 14, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -14, 8, 15, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -13, 8, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -13, 8, 16, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 14, 8, 14, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 14, 8, 15, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 13, 8, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 13, 8, 16, this.roofStairBlock, 0);
        for (i16 = -12; i16 <= -10; ++i16) {
            this.setBlockAndMetadata(world, i16, 8, 16, this.roofStairBlock, 3);
        }
        for (i16 = 10; i16 <= 12; ++i16) {
            this.setBlockAndMetadata(world, i16, 8, 16, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -9, 8, 16, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 9, 8, 16, this.roofStairBlock, 0);
        for (i16 = -9; i16 <= 9; ++i16) {
            this.setBlockAndMetadata(world, i16, 8, 17, this.roofStairBlock, 3);
        }
        for (i16 = -16; i16 <= 16; ++i16) {
            boolean roof;
            i2 = Math.abs(i16);
            for (k1 = -4; k1 <= 17; ++k1) {
                roof = false;
                if (k1 == -4) {
                    boolean bl = roof = i2 == 13;
                }
                if (k1 == -3) {
                    boolean bl = roof = i2 >= 6 && i2 <= 14;
                }
                if (k1 == -2) {
                    boolean bl = roof = i2 >= 5 && i2 <= 15;
                }
                if (k1 >= -1 && k1 <= 1) {
                    boolean bl = roof = i2 <= 15;
                }
                if (k1 >= 2 && k1 <= 10) {
                    boolean bl = roof = i2 <= 16;
                }
                if (k1 >= 11 && k1 <= 14) {
                    boolean bl = roof = i2 <= 15;
                }
                if (k1 == 15) {
                    boolean bl = roof = i2 <= 14;
                }
                if (k1 == 16) {
                    boolean bl = roof = i2 <= 13;
                }
                if (k1 == 17) {
                    boolean bl = roof = i2 <= 9;
                }
                if (!roof) continue;
                this.setBlockAndMetadata(world, i16, 7, k1, this.roofBlock, this.roofMeta);
            }
            for (k1 = -2; k1 <= 16; ++k1) {
                roof = false;
                if (k1 == -2) {
                    boolean bl = roof = i2 >= 7 && i2 <= 13;
                }
                if (k1 == -1) {
                    boolean bl = roof = i2 >= 6 && i2 <= 14;
                }
                if (k1 >= 0 && k1 <= 2) {
                    boolean bl = roof = i2 <= 14;
                }
                if (k1 >= 3 && k1 <= 9) {
                    boolean bl = roof = i2 <= 15;
                }
                if (k1 >= 10 && k1 <= 13) {
                    boolean bl = roof = i2 <= 14;
                }
                if (k1 == 14) {
                    boolean bl = roof = i2 <= 13;
                }
                if (k1 == 15) {
                    boolean bl = roof = i2 <= 12;
                }
                if (k1 == 16) {
                    boolean bl = roof = i2 <= 8;
                }
                if (!roof) continue;
                this.setBlockAndMetadata(world, i16, 8, k1, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i16, 9, k1, this.roofSlabBlock, this.roofSlabMeta);
            }
        }
        for (i16 = -6; i16 <= 6; ++i16) {
            i2 = Math.abs(i16);
            for (k1 = 1; k1 <= 15; ++k1) {
                room = 0;
                if (k1 == 1 && i2 <= 1) {
                    room = 1;
                }
                if (k1 == 2 && i2 <= 2) {
                    room = 1;
                }
                if (k1 == 3 && i2 <= 3) {
                    room = 1;
                }
                if (k1 == 4 && i2 <= 4) {
                    room = 1;
                }
                if (k1 == 5 && i2 <= 5) {
                    room = 1;
                }
                if (k1 >= 6 && k1 <= 10 && i2 <= 6) {
                    room = 1;
                }
                if (k1 >= 11 && k1 <= 12 && i2 <= 5) {
                    room = 1;
                }
                if (k1 == 13 && i2 <= 4) {
                    room = 1;
                }
                if (k1 >= 14 && k1 <= 15 && i2 <= 2) {
                    room = 1;
                }
                if (room == 0) continue;
                this.setBlockAndMetadata(world, i16, 1, k1, this.floorBlock, this.floorMeta);
                for (j13 = 2; j13 <= 5; ++j13) {
                    this.setAir(world, i16, j13, k1);
                }
            }
            for (j14 = 2; j14 <= 4; ++j14) {
                if (i2 == 2) {
                    this.setBlockAndMetadata(world, i16, j14, 1, this.brickBlock, this.brickMeta);
                }
                if (i2 == 4) {
                    this.setBlockAndMetadata(world, i16, j14, 3, this.beamBlock, this.beamMeta);
                }
                if (i2 == 6) {
                    this.setBlockAndMetadata(world, i16, j14, 5, this.beamBlock, this.beamMeta);
                    this.setBlockAndMetadata(world, i16, j14, 11, this.beamBlock, this.beamMeta);
                }
                if (i2 == 5) {
                    this.setBlockAndMetadata(world, i16, j14, 13, this.beamBlock, this.beamMeta);
                }
                if (i2 != 3) continue;
                this.setBlockAndMetadata(world, i16, j14, 14, this.beamBlock, this.beamMeta);
            }
        }
        for (i16 = -5; i16 <= 5; ++i16) {
            for (k15 = 11; k15 <= 15; ++k15) {
                this.setBlockAndMetadata(world, i16, 5, k15, this.plankBlock, this.plankMeta);
            }
            this.setBlockAndMetadata(world, i16, 5, 10, this.plankStairBlock, 6);
        }
        for (i16 = -1; i16 <= 1; ++i16) {
            this.setBlockAndMetadata(world, i16, 5, 1, this.plankBlock, this.plankMeta);
        }
        for (i16 = -2; i16 <= 2; ++i16) {
            this.setBlockAndMetadata(world, i16, 5, 2, this.plankStairBlock, 7);
        }
        this.setBlockAndMetadata(world, -2, 5, 3, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 2, 5, 3, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, -3, 5, 3, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, 3, 5, 3, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, -3, 5, 4, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 3, 5, 4, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, -4, 5, 4, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, 4, 5, 4, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, -4, 5, 5, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 4, 5, 5, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, -5, 5, 5, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, 5, 5, 5, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, -5, 5, 6, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 5, 5, 6, this.plankStairBlock, 5);
        this.setBlockAndMetadata(world, -6, 5, 6, this.plankStairBlock, 7);
        this.setBlockAndMetadata(world, 6, 5, 6, this.plankStairBlock, 7);
        for (k12 = 7; k12 <= 10; ++k12) {
            this.setBlockAndMetadata(world, -6, 5, k12, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, 6, 5, k12, this.plankStairBlock, 5);
        }
        this.setBlockAndMetadata(world, 0, 4, 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -6, 3, 6, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 6, 3, 6, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -6, 3, 10, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 6, 3, 10, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 0, 5, 5, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, -4, 5, 8, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, 4, 5, 8, this.chandelierBlock, this.chandelierMeta);
        this.setBlockAndMetadata(world, -2, 3, 2, (Block)Blocks.tripwire_hook, 0);
        this.setBlockAndMetadata(world, 2, 3, 2, (Block)Blocks.tripwire_hook, 0);
        this.setBlockAndMetadata(world, -3, 3, 3, (Block)Blocks.tripwire_hook, 0);
        this.setBlockAndMetadata(world, 3, 3, 3, (Block)Blocks.tripwire_hook, 0);
        this.setBlockAndMetadata(world, -4, 3, 4, (Block)Blocks.tripwire_hook, 1);
        this.setBlockAndMetadata(world, 4, 3, 4, (Block)Blocks.tripwire_hook, 3);
        for (i16 = -1; i16 <= 1; ++i16) {
            for (k15 = 1; k15 <= 2; ++k15) {
                this.setBlockAndMetadata(world, i16, 2, k15, this.carpetBlock, this.carpetMeta);
            }
        }
        for (i16 = -2; i16 <= 2; ++i16) {
            for (k15 = 5; k15 <= 7; ++k15) {
                this.setBlockAndMetadata(world, i16, 2, k15, this.carpetBlock, this.carpetMeta);
            }
        }
        for (i16 = -3; i16 <= 3; ++i16) {
            i2 = Math.abs(i16);
            this.setBlockAndMetadata(world, i16, 2, 11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, i16, 4, 11, this.fence2Block, this.fence2Meta);
            if (IntMath.mod((int)i16, (int)2) == 1) {
                this.setBlockAndMetadata(world, i16, 2, 9, this.fence2Block, this.fence2Meta);
            }
            if (i2 == 2) {
                this.placeBarrel(world, random, i16, 3, 11, 3, LOTRFoods.HOBBIT_DRINK);
            }
            if (i2 != 1) continue;
            this.placeMug(world, random, i16, 3, 11, 0, LOTRFoods.HOBBIT_DRINK);
        }
        for (k12 = 12; k12 <= 13; ++k12) {
            int[] i24 = new int[]{-3, 3};
            j14 = i24.length;
            for (room = 0; room < j14; ++room) {
                i132 = i24[room];
                this.setBlockAndMetadata(world, i132, 2, k12, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i132, 4, k12, this.fence2Block, this.fence2Meta);
            }
        }
        this.setBlockAndMetadata(world, 3, 2, 12, this.fenceGate2Block, 1);
        for (i16 = -2; i16 <= 2; ++i16) {
            this.setBlockAndMetadata(world, i16, 4, 15, this.plankStairBlock, 6);
        }
        this.setBlockAndMetadata(world, -2, 4, 14, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 2, 4, 14, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -2, 2, 15, Blocks.crafting_table, 0);
        this.placeChest(world, random, -1, 2, 15, 2, LOTRChestContents.HOBBIT_HOLE_LARDER);
        this.setBlockAndMetadata(world, 0, 2, 15, this.plankBlock, this.plankMeta);
        this.placeFlowerPot(world, 0, 3, 15, new ItemStack(LOTRMod.shireHeather));
        this.setBlockAndMetadata(world, 1, 2, 15, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, 2, 2, 15, LOTRMod.hobbitOven, 2);
        int[] i19 = new int[]{-7, 7};
        i2 = i19.length;
        for (j14 = 0; j14 < i2; ++j14) {
            i122 = i19[j14];
            this.setBlockAndMetadata(world, i122, 1, 8, this.floorBlock, this.floorMeta);
            this.setBlockAndMetadata(world, i122, 2, 8, this.carpetBlock, this.carpetMeta);
            this.setAir(world, i122, 3, 8);
            this.setBlockAndMetadata(world, i122, 2, 7, this.plankStairBlock, 3);
            this.setBlockAndMetadata(world, i122, 3, 7, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i122, 2, 9, this.plankStairBlock, 2);
            this.setBlockAndMetadata(world, i122, 3, 9, this.plankStairBlock, 6);
        }
        for (k13 = 7; k13 <= 9; ++k13) {
            this.setBlockAndMetadata(world, -6, 2, k13, this.carpetBlock, this.carpetMeta);
            this.setBlockAndMetadata(world, -5, 2, k13, this.carpetBlock, this.carpetMeta);
            this.setBlockAndMetadata(world, 5, 2, k13, this.carpetBlock, this.carpetMeta);
            this.setBlockAndMetadata(world, 6, 2, k13, this.carpetBlock, this.carpetMeta);
        }
        for (i1 = -15; i1 <= -8; ++i1) {
            for (k15 = 3; k15 <= 14; ++k15) {
                this.setBlockAndMetadata(world, i1, 0, k15, this.floorBlock, this.floorMeta);
                for (j14 = 1; j14 <= 5; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (i1 = -15; i1 <= -11; ++i1) {
            for (k15 = -3; k15 <= 3; ++k15) {
                this.setBlockAndMetadata(world, i1, 0, k15, this.floorBlock, this.floorMeta);
                for (j14 = 1; j14 <= 5; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (i1 = -10; i1 <= -5; ++i1) {
            for (k15 = -2; k15 <= 3; ++k15) {
                this.setBlockAndMetadata(world, i1, 1, k15, this.floorBlock, this.floorMeta);
                for (j14 = 2; j14 <= 5; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (j15 = 1; j15 <= 5; ++j15) {
            this.setBlockAndMetadata(world, -15, j15, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -9, j15, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -8, j15, 14, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -8, j15, 11, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -8, j15, 5, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -8, j15, 4, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -9, j15, 3, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, -8, 3, 6, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -8, 3, 10, Blocks.torch, 4);
        for (k13 = 6; k13 <= 10; ++k13) {
            this.setBlockAndMetadata(world, -8, 1, k13, this.floorStairBlock, 1);
            this.setBlockAndMetadata(world, -8, 5, k13, this.plankStairBlock, 5);
        }
        this.setBlockAndMetadata(world, -9, 1, 11, this.plank2Block, this.plank2Meta);
        for (j15 = 2; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, -9, j15, 11, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, -9, 5, 11, this.plank2Block, this.plank2Meta);
        for (k13 = 12; k13 <= 13; ++k13) {
            this.setBlockAndMetadata(world, -9, 1, k13, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -8, 1, k13, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -8, 2, k13, this.plankStairBlock, 5);
            this.placeFlowerPot(world, -8, 3, k13, this.getRandomFlower(world, random));
            this.setBlockAndMetadata(world, -8, 4, k13, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, -8, 5, k13, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -9, 5, k13, this.plank2StairBlock, 5);
        }
        for (i1 = -14; i1 <= -10; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, 14, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i1, 5, 14, this.plank2StairBlock, 6);
        }
        for (i1 = -13; i1 <= -11; ++i1) {
            this.setBlockAndMetadata(world, i1, 2, 15, this.plankStairBlock, 6);
            this.setBlockAndMetadata(world, i1, 3, 15, LOTRMod.barrel, 2);
            this.setBlockAndMetadata(world, i1, 4, 15, this.plankStairBlock, 6);
        }
        for (k13 = 9; k13 <= 13; ++k13) {
            this.setBlockAndMetadata(world, -15, 1, k13, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -15, 5, k13, this.plank2StairBlock, 4);
        }
        for (k13 = 10; k13 <= 12; ++k13) {
            this.spawnItemFrame(world, -16, 3, k13, 1, this.getTavernFramedItem(random));
        }
        for (i1 = -13; i1 <= -11; ++i1) {
            for (k15 = 10; k15 <= 12; ++k15) {
                this.setBlockAndMetadata(world, i1, 1, k15, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i1, 2, k15);
            }
        }
        this.setBlockAndMetadata(world, -12, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -13, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -11, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -12, 5, 10, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -12, 5, 12, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -12, 4, 11, this.chandelierBlock, this.chandelierMeta);
        for (i1 = -15; i1 <= -12; ++i1) {
            for (k15 = 6; k15 <= 8; ++k15) {
                this.setBlockAndMetadata(world, i1, 1, k15, Blocks.stonebrick, 0);
                for (j14 = 2; j14 <= 4; ++j14) {
                    this.setBlockAndMetadata(world, i1, j14, k15, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i1, 5, k15, Blocks.stonebrick, 0);
                for (j14 = 6; j14 <= 10; ++j14) {
                    this.setBlockAndMetadata(world, i1, j14, k15, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (i1 = -14; i1 <= -13; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, 7, LOTRMod.hearth, 0);
            this.setBlockAndMetadata(world, i1, 1, 7, (Block)Blocks.fire, 0);
            for (j1 = 2; j1 <= 10; ++j1) {
                this.setAir(world, i1, j1, 7);
            }
        }
        for (j15 = 1; j15 <= 3; ++j15) {
            this.setBlockAndMetadata(world, -12, j15, 7, this.barsBlock, 0);
        }
        this.setBlockAndMetadata(world, -10, 5, 7, this.chandelierBlock, this.chandelierMeta);
        for (k13 = 2; k13 <= 5; ++k13) {
            this.setBlockAndMetadata(world, -15, 1, k13, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -15, 5, k13, this.plank2StairBlock, 4);
        }
        for (j15 = 1; j15 <= 5; ++j15) {
            this.setBlockAndMetadata(world, -15, j15, 1, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, -14, 1, 1, this.plank2Block, this.plank2Meta);
        for (j15 = 2; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, -14, j15, 1, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, -14, 5, 1, this.plank2Block, this.plank2Meta);
        for (k13 = 3; k13 <= 4; ++k13) {
            this.setBlockAndMetadata(world, -13, 1, k13, this.plank2Block, this.plank2Meta);
            this.placePlateOrMug(world, random, -13, 2, k13);
        }
        this.setBlockAndMetadata(world, -13, 5, 4, this.chandelierBlock, this.chandelierMeta);
        for (k13 = -3; k13 <= 0; ++k13) {
            this.setBlockAndMetadata(world, -15, 1, k13, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -15, 5, k13, this.plank2StairBlock, 4);
        }
        for (k13 = -2; k13 <= -1; ++k13) {
            for (i15 = -13; i15 <= -12; ++i15) {
                this.setBlockAndMetadata(world, i15, 1, k13, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i15, 2, k13);
            }
            this.spawnItemFrame(world, -16, 3, k13, 1, this.getTavernFramedItem(random));
        }
        for (i1 = -14; i1 <= -12; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, -4, this.plank2StairBlock, 3);
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setAir(world, i1, j1, -4);
            }
            this.setBlockAndMetadata(world, i1, 5, -4, this.plank2StairBlock, 7);
        }
        this.spawnItemFrame(world, -13, 3, -5, 0, this.getTavernFramedItem(random));
        this.setBlockAndMetadata(world, -12, 5, -1, this.chandelierBlock, this.chandelierMeta);
        for (k13 = -1; k13 <= 2; ++k13) {
            this.setBlockAndMetadata(world, -10, 1, k13, this.floorStairBlock, 1);
        }
        this.setBlockAndMetadata(world, -10, 1, 3, this.floorStairBlock, 3);
        for (j15 = 2; j15 <= 5; ++j15) {
            this.setBlockAndMetadata(world, -5, j15, 3, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -5, j15, -2, this.beamBlock, this.beamMeta);
        }
        for (i1 = -8; i1 <= -6; ++i1) {
            this.setBlockAndMetadata(world, i1, 2, 3, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i1, 5, 3, this.plank2StairBlock, 6);
        }
        this.setBlockAndMetadata(world, -7, 3, 4, LOTRMod.barrel, 2);
        this.setBlockAndMetadata(world, -7, 4, 4, this.plankStairBlock, 6);
        for (k13 = -1; k13 <= 2; ++k13) {
            this.setBlockAndMetadata(world, -5, 2, k13, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -5, 5, k13, this.plank2StairBlock, 5);
        }
        this.setBlockAndMetadata(world, -4, 3, 2, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, -4, 4, 2, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, -4, 3, -1, this.plankStairBlock, 3);
        this.setBlockAndMetadata(world, -4, 4, -1, this.plankStairBlock, 7);
        this.placeFlowerPot(world, -4, 3, 1, this.getRandomFlower(world, random));
        this.placeFlowerPot(world, -4, 3, 0, this.getRandomFlower(world, random));
        this.setBlockAndMetadata(world, -4, 4, 1, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, -4, 4, 0, this.plankSlabBlock, this.plankSlabMeta | 8);
        for (i1 = -9; i1 <= -6; ++i1) {
            this.setBlockAndMetadata(world, i1, 2, -2, this.plank2StairBlock, 3);
            this.setBlockAndMetadata(world, i1, 5, -2, this.plank2StairBlock, 7);
        }
        this.setBlockAndMetadata(world, -10, 1, -2, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -10, 2, -2, this.plank2Block, this.plank2Meta);
        for (j15 = 3; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, -10, j15, -2, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, -10, 5, -2, this.plank2Block, this.plank2Meta);
        for (i1 = -8; i1 <= -7; ++i1) {
            for (k15 = 0; k15 <= 1; ++k15) {
                this.setBlockAndMetadata(world, i1, 2, k15, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i1, 3, k15);
            }
        }
        this.setBlockAndMetadata(world, -8, 5, 1, this.chandelierBlock, this.chandelierMeta);
        for (i1 = 8; i1 <= 15; ++i1) {
            for (k15 = 3; k15 <= 14; ++k15) {
                this.setBlockAndMetadata(world, i1, 0, k15, this.floorBlock, this.floorMeta);
                for (j14 = 1; j14 <= 5; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (i1 = 11; i1 <= 15; ++i1) {
            for (k15 = -3; k15 <= 3; ++k15) {
                this.setBlockAndMetadata(world, i1, 0, k15, this.floorBlock, this.floorMeta);
                for (j14 = 1; j14 <= 5; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (i1 = 5; i1 <= 10; ++i1) {
            for (k15 = -2; k15 <= 3; ++k15) {
                this.setBlockAndMetadata(world, i1, 1, k15, this.floorBlock, this.floorMeta);
                for (j14 = 2; j14 <= 5; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (j15 = 1; j15 <= 5; ++j15) {
            this.setBlockAndMetadata(world, 15, j15, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 9, j15, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 8, j15, 14, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 8, j15, 11, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 8, j15, 5, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 8, j15, 4, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 9, j15, 3, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, 8, 3, 6, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 8, 3, 10, Blocks.torch, 4);
        for (k13 = 6; k13 <= 10; ++k13) {
            this.setBlockAndMetadata(world, 8, 1, k13, this.floorStairBlock, 0);
            this.setBlockAndMetadata(world, 8, 5, k13, this.plankStairBlock, 4);
        }
        this.setBlockAndMetadata(world, 9, 1, 11, this.plank2Block, this.plank2Meta);
        for (j15 = 2; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, 9, j15, 11, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, 9, 5, 11, this.plank2Block, this.plank2Meta);
        for (k13 = 12; k13 <= 13; ++k13) {
            this.setBlockAndMetadata(world, 9, 1, k13, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 8, 1, k13, this.plankBlock, this.plankMeta);
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setBlockAndMetadata(world, 8, j1, k13, Blocks.bookshelf, 0);
            }
            this.setBlockAndMetadata(world, 8, 5, k13, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 9, 5, k13, this.plank2StairBlock, 4);
        }
        for (i1 = 10; i1 <= 14; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, 14, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i1, 5, 14, this.plank2StairBlock, 6);
        }
        for (i1 = 10; i1 <= 14; ++i1) {
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setBlockAndMetadata(world, i1, j1, 15, Blocks.bookshelf, 0);
            }
        }
        for (k13 = 9; k13 <= 13; ++k13) {
            this.setBlockAndMetadata(world, 15, 1, k13, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 15, 5, k13, this.plank2StairBlock, 5);
        }
        this.placeWallBanner(world, 16, 4, 11, LOTRItemBanner.BannerType.HOBBIT, 3);
        for (i1 = 11; i1 <= 13; ++i1) {
            for (k15 = 10; k15 <= 12; ++k15) {
                this.setBlockAndMetadata(world, i1, 1, k15, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i1, 2, k15);
            }
        }
        this.setBlockAndMetadata(world, 12, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 13, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 11, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 12, 5, 10, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 12, 5, 12, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 12, 4, 11, this.chandelierBlock, this.chandelierMeta);
        for (i1 = 12; i1 <= 15; ++i1) {
            for (k15 = 6; k15 <= 8; ++k15) {
                this.setBlockAndMetadata(world, i1, 1, k15, Blocks.stonebrick, 0);
                for (j14 = 2; j14 <= 4; ++j14) {
                    this.setBlockAndMetadata(world, i1, j14, k15, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i1, 5, k15, Blocks.stonebrick, 0);
                for (j14 = 6; j14 <= 10; ++j14) {
                    this.setBlockAndMetadata(world, i1, j14, k15, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (i1 = 13; i1 <= 14; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, 7, LOTRMod.hearth, 0);
            this.setBlockAndMetadata(world, i1, 1, 7, (Block)Blocks.fire, 0);
            for (j1 = 2; j1 <= 10; ++j1) {
                this.setAir(world, i1, j1, 7);
            }
        }
        for (j15 = 1; j15 <= 3; ++j15) {
            this.setBlockAndMetadata(world, 12, j15, 7, this.barsBlock, 0);
        }
        this.setBlockAndMetadata(world, 10, 5, 7, this.chandelierBlock, this.chandelierMeta);
        for (k13 = 2; k13 <= 5; ++k13) {
            this.setBlockAndMetadata(world, 15, 1, k13, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 15, 5, k13, this.plank2StairBlock, 5);
        }
        for (j15 = 1; j15 <= 5; ++j15) {
            this.setBlockAndMetadata(world, 15, j15, 1, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, 14, 1, 1, this.plank2Block, this.plank2Meta);
        for (j15 = 2; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, 14, j15, 1, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, 14, 5, 1, this.plank2Block, this.plank2Meta);
        for (k13 = 3; k13 <= 4; ++k13) {
            this.setBlockAndMetadata(world, 13, 1, k13, this.plank2Block, this.plank2Meta);
            this.placePlateOrMug(world, random, 13, 2, k13);
        }
        this.setBlockAndMetadata(world, 13, 5, 4, this.chandelierBlock, this.chandelierMeta);
        for (k13 = -3; k13 <= 0; ++k13) {
            this.setBlockAndMetadata(world, 15, 1, k13, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 15, 5, k13, this.plank2StairBlock, 5);
        }
        for (k13 = -2; k13 <= -1; ++k13) {
            for (i15 = 12; i15 <= 13; ++i15) {
                this.setBlockAndMetadata(world, i15, 1, k13, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i15, 2, k13);
            }
        }
        this.placeWallBanner(world, 16, 4, -2, LOTRItemBanner.BannerType.HOBBIT, 3);
        for (i1 = 12; i1 <= 14; ++i1) {
            this.setBlockAndMetadata(world, i1, 1, -4, this.plank2StairBlock, 3);
            for (j1 = 2; j1 <= 4; ++j1) {
                this.setAir(world, i1, j1, -4);
            }
            this.setBlockAndMetadata(world, i1, 5, -4, this.plank2StairBlock, 7);
        }
        this.placeWallBanner(world, 13, 4, -5, LOTRItemBanner.BannerType.HOBBIT, 0);
        this.setBlockAndMetadata(world, 12, 5, -1, this.chandelierBlock, this.chandelierMeta);
        for (k13 = -1; k13 <= 2; ++k13) {
            this.setBlockAndMetadata(world, 10, 1, k13, this.floorStairBlock, 0);
        }
        this.setBlockAndMetadata(world, 10, 1, 3, this.floorStairBlock, 3);
        for (j15 = 2; j15 <= 5; ++j15) {
            this.setBlockAndMetadata(world, 5, j15, 3, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 5, j15, -2, this.beamBlock, this.beamMeta);
        }
        for (i1 = 6; i1 <= 8; ++i1) {
            this.setBlockAndMetadata(world, i1, 2, 3, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i1, 5, 3, this.plank2StairBlock, 6);
        }
        this.placeWallBanner(world, 7, 4, 4, LOTRItemBanner.BannerType.HOBBIT, 2);
        for (k13 = -1; k13 <= 2; ++k13) {
            this.setBlockAndMetadata(world, 5, 2, k13, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 4, 3, k13, Blocks.bookshelf, 0);
            this.setBlockAndMetadata(world, 4, 4, k13, Blocks.bookshelf, 0);
            this.setBlockAndMetadata(world, 5, 5, k13, this.plank2StairBlock, 4);
        }
        for (i1 = 6; i1 <= 9; ++i1) {
            this.setBlockAndMetadata(world, i1, 2, -2, this.plank2StairBlock, 3);
            this.setBlockAndMetadata(world, i1, 5, -2, this.plank2StairBlock, 7);
        }
        this.setBlockAndMetadata(world, 10, 1, -2, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, 10, 2, -2, this.plank2Block, this.plank2Meta);
        for (j15 = 3; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, 10, j15, -2, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, 10, 5, -2, this.plank2Block, this.plank2Meta);
        for (i1 = 7; i1 <= 8; ++i1) {
            for (k15 = 0; k15 <= 1; ++k15) {
                this.setBlockAndMetadata(world, i1, 2, k15, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i1, 3, k15);
            }
        }
        this.setBlockAndMetadata(world, 8, 5, 1, this.chandelierBlock, this.chandelierMeta);
        for (i1 = -3; i1 <= 4; ++i1) {
            for (k15 = 11; k15 <= 15; ++k15) {
                this.setBlockAndMetadata(world, i1, -4, k15, this.floorBlock, this.floorMeta);
                for (j14 = -3; j14 <= 0; ++j14) {
                    this.setAir(world, i1, j14, k15);
                }
            }
        }
        for (i1 = -3; i1 <= 4; ++i1) {
            int k110;
            int[] k111 = new int[]{10, 16};
            j14 = k111.length;
            for (i122 = 0; i122 < j14; ++i122) {
                k16 = k111[i122];
                this.setBlockAndMetadata(world, i1, -3, k16, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i1, -2, k16, this.beamBlock, this.beamMeta | 4);
                this.setBlockAndMetadata(world, i1, -1, k16, this.plankBlock, this.plankMeta);
            }
            for (k110 = 11; k110 <= 13; ++k110) {
                if (i1 < 0) continue;
                this.setBlockAndMetadata(world, i1, 0, k110, this.beamBlock, this.beamMeta | 4);
            }
            for (k110 = 14; k110 <= 15; ++k110) {
                this.setBlockAndMetadata(world, i1, 0, k110, this.beamBlock, this.beamMeta | 4);
            }
        }
        for (k13 = 11; k13 <= 15; ++k13) {
            int[] k112 = new int[]{-4, 5};
            j14 = k112.length;
            for (i122 = 0; i122 < j14; ++i122) {
                i132 = k112[i122];
                this.setBlockAndMetadata(world, i132, -3, k13, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i132, -2, k13, this.beamBlock, this.beamMeta | 8);
                this.setBlockAndMetadata(world, i132, -1, k13, this.plankBlock, this.plankMeta);
            }
        }
        for (j15 = -3; j15 <= -1; ++j15) {
            this.setBlockAndMetadata(world, -3, j15, 15, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 4, j15, 15, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 4, j15, 11, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 0, j15, 11, this.beamBlock, this.beamMeta);
        }
        this.placeBarrel(world, random, 4, -3, 14, 5, LOTRFoods.HOBBIT_DRINK);
        for (k13 = 12; k13 <= 13; ++k13) {
            this.placeChest(world, random, 4, -3, k13, 5, LOTRChestContents.HOBBIT_HOLE_LARDER);
        }
        for (k13 = 12; k13 <= 14; ++k13) {
            this.setBlockAndMetadata(world, 4, -2, k13, this.plankSlabBlock, this.plankSlabMeta | 8);
            this.placeBarrel(world, random, 4, -1, k13, 5, LOTRFoods.HOBBIT_DRINK);
        }
        this.placeBarrel(world, random, 1, -3, 11, 3, LOTRFoods.HOBBIT_DRINK);
        for (i1 = 2; i1 <= 3; ++i1) {
            this.placeChest(world, random, i1, -3, 11, 3, LOTRChestContents.HOBBIT_HOLE_LARDER);
        }
        for (i1 = 1; i1 <= 3; ++i1) {
            this.setBlockAndMetadata(world, i1, -2, 11, this.plankSlabBlock, this.plankSlabMeta | 8);
            Block cakeBlock = LOTRWorldGenHobbitStructure.getRandomCakeBlock(random);
            this.setBlockAndMetadata(world, i1, -1, 11, cakeBlock, 0);
        }
        for (k13 = 11; k13 <= 13; ++k13) {
            this.setAir(world, -2, 1, k13);
            this.setAir(world, -3, 1, k13);
            this.setAir(world, -3, 0, k13);
        }
        for (k13 = 10; k13 <= 12; ++k13) {
            this.setAir(world, -3, 0, k13);
        }
        this.setBlockAndMetadata(world, -3, 1, 14, this.floorBlock, this.floorMeta);
        for (i1 = -3; i1 <= -1; ++i1) {
            for (k15 = 11; k15 <= 12; ++k15) {
                for (j14 = -3; j14 <= -1; ++j14) {
                    this.setBlockAndMetadata(world, i1, j14, k15, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (int step = 0; step <= 2; ++step) {
            this.setBlockAndMetadata(world, -2, 1 - step, 14 - step, this.floorStairBlock, 2);
        }
        for (i1 = -3; i1 <= -2; ++i1) {
            this.setAir(world, i1, -1, 11);
            this.setBlockAndMetadata(world, i1, -2, 11, this.floorBlock, this.floorMeta);
        }
        this.setAir(world, -3, -1, 12);
        this.setBlockAndMetadata(world, -3, -2, 12, this.floorStairBlock, 3);
        for (i1 = -2; i1 <= -1; ++i1) {
            this.setBlockAndMetadata(world, i1, -1, 13, this.floorStairBlock, 7);
        }
        for (k13 = 13; k13 <= 14; ++k13) {
            this.setBlockAndMetadata(world, -3, -3, k13, this.floorBlock, this.floorMeta);
        }
        for (k13 = 13; k13 <= 15; ++k13) {
            this.setBlockAndMetadata(world, -2, -3, k13, this.floorStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -2, -1, 15, Blocks.torch, 2);
        for (k13 = 11; k13 <= 13; ++k13) {
            this.setBlockAndMetadata(world, -4, 0, k13, this.beamBlock, this.beamMeta | 8);
            this.setBlockAndMetadata(world, -1, 0, k13, this.beamBlock, this.beamMeta | 8);
        }
        for (i1 = -3; i1 <= -2; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, 10, this.beamBlock, this.beamMeta | 4);
        }
        LOTREntityHobbitBartender bartender = new LOTREntityHobbitBartender(world);
        bartender.setSpecificLocationName(this.tavernNameNPC);
        this.spawnNPCAndSetHome(bartender, world, 1, 2, 13, 2);
        for (int i132 : new int[]{-10, 10}) {
            j12 = 1;
            int k113 = 7;
            int hobbits = 3 + random.nextInt(6);
            for (int l = 0; l < hobbits; ++l) {
                LOTREntityHobbit hobbit = new LOTREntityHobbit(world);
                this.spawnNPCAndSetHome(hobbit, world, i132, j12, k113, 16);
            }
            if (random.nextInt(4) != 0) continue;
            LOTREntityHobbitShirriff shirriffChief = new LOTREntityHobbitShirriff(world);
            shirriffChief.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(shirriffChief, world, i132, j12, k113, 16);
        }
        this.placeSign(world, -8, 4, 8, Blocks.wall_sign, 5, LOTRNames.getHobbitTavernQuote(random));
        this.placeSign(world, 8, 4, 8, Blocks.wall_sign, 4, LOTRNames.getHobbitTavernQuote(random));
        return true;
    }

    private void placePlateOrMug(World world, Random random, int i, int j, int k) {
        if (random.nextBoolean()) {
            this.placeMug(world, random, i, j, k, random.nextInt(4), LOTRFoods.HOBBIT_DRINK);
        } else {
            this.placePlate(world, random, i, j, k, this.plateBlock, LOTRFoods.HOBBIT);
        }
    }

    private ItemStack getTavernFramedItem(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.daggerIron), new ItemStack(LOTRMod.leatherHat), LOTRItemLeatherHat.setFeatherColor(new ItemStack(LOTRMod.leatherHat), 16777215), LOTRItemLeatherHat.setHatColor(new ItemStack(LOTRMod.leatherHat), 2301981), LOTRItemLeatherHat.setFeatherColor(LOTRItemLeatherHat.setHatColor(new ItemStack(LOTRMod.leatherHat), 2301981), 3381529), new ItemStack(LOTRMod.hobbitPipe), new ItemStack(Items.book), new ItemStack(Items.feather), new ItemStack(Items.wooden_sword), new ItemStack((Item)Items.bow), new ItemStack(LOTRMod.mug), new ItemStack(LOTRMod.mugAle), new ItemStack(LOTRMod.mugCider), new ItemStack(LOTRMod.ceramicMug), new ItemStack(Items.glass_bottle), new ItemStack(Items.arrow), new ItemStack(LOTRMod.shireHeather), new ItemStack(LOTRMod.bluebell), new ItemStack((Block)Blocks.yellow_flower, 1, 0), new ItemStack((Block)Blocks.red_flower, 1, 0), new ItemStack((Block)Blocks.red_flower, 1, 3)};
        return items[random.nextInt(items.length)].copy();
    }
}

