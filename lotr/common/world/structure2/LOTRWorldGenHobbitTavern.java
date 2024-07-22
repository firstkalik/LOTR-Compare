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
        int i60;
        int grassHeight;
        int i18;
        int i59;
        int i592;
        int i14;
        int i593;
        int i54;
        int i594;
        int k1;
        int i56;
        int i38;
        int i53;
        int m;
        int i55;
        int i595;
        int i52;
        Object room;
        int i602;
        int i25;
        int i603;
        int i15;
        int i596;
        int i604;
        int i4;
        int i37;
        int i605;
        int i597;
        int i51;
        int wood;
        int i2;
        int i61;
        int i598;
        int i6;
        int i44;
        int i50;
        int i34;
        int i58;
        int i599;
        int i5910;
        int i39;
        int i62;
        int i3;
        int i48;
        int i20;
        int i33;
        this.setOriginAndRotation(world, i, j, k, rotation, 12);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i59 = -18; i59 <= 18; ++i59) {
                for (int i606 = -12; i606 <= 19; ++i606) {
                    i61 = this.getTopBlock(world, i59, i606) - 1;
                    if (!this.isSurface(world, i59, i61, i606)) {
                        return false;
                    }
                    if (i61 < minHeight) {
                        minHeight = i61;
                    }
                    if (i61 > maxHeight) {
                        maxHeight = i61;
                    }
                    if (maxHeight - minHeight <= 8) continue;
                    return false;
                }
            }
        }
        for (i58 = -16; i58 <= 16; ++i58) {
            for (i599 = -12; i599 <= 18; ++i599) {
                int i612;
                i602 = Math.abs(i58);
                grassHeight = -1;
                if (i602 <= 14) {
                    if (i599 <= -6) {
                        grassHeight = i599 == -7 && i602 <= 1 || i599 == -6 && i602 <= 3 ? 1 : 0;
                    } else if (i599 <= -5 && (i602 == 11 || i602 <= 5)) {
                        grassHeight = 1;
                    } else if (i599 <= -4 && i602 <= 11 || i599 <= -3 && i602 <= 3) {
                        grassHeight = 1;
                    }
                }
                if (grassHeight >= 0) {
                    for (i61 = grassHeight; !(i61 < -1 && this.isOpaque(world, i58, i61, i599) || this.getY(i61) < 0); --i61) {
                        if (i61 == grassHeight) {
                            this.setBlockAndMetadata(world, i58, i61, i599, (Block)Blocks.grass, 0);
                        } else {
                            this.setBlockAndMetadata(world, i58, i61, i599, Blocks.dirt, 0);
                        }
                        this.setGrassToDirt(world, i58, i61 - 1, i599);
                    }
                    for (i61 = grassHeight + 1; i61 <= 12; ++i61) {
                        this.setAir(world, i58, i61, i599);
                    }
                    continue;
                }
                wood = 0;
                boolean beam = false;
                if (i599 >= -5 && i599 <= 17) {
                    wood = i602 >= 15 && (i599 <= -4 || i599 >= 16) ? 0 : 1;
                }
                if (i602 == 15 && (i599 == -4 || i599 == 16)) {
                    beam = true;
                }
                if (i599 == 18 && i602 <= 14 && IntMath.mod((int)i58, (int)5) == 0) {
                    beam = true;
                }
                if (!beam && wood == 0) continue;
                for (i612 = 5; !(i612 < 0 && this.isOpaque(world, i58, i612, i599) || this.getY(i612) < 0); --i612) {
                    if (beam) {
                        this.setBlockAndMetadata(world, i58, i612, i599, this.beamBlock, this.beamMeta);
                    } else {
                        this.setBlockAndMetadata(world, i58, i612, i599, this.plankBlock, this.plankMeta);
                    }
                    this.setGrassToDirt(world, i58, i612 - 1, i599);
                }
                this.setBlockAndMetadata(world, i58, 6, i599, this.plankBlock, this.plankMeta);
                for (i612 = 8; i612 <= 12; ++i612) {
                    this.setAir(world, i58, i612, i599);
                }
            }
        }
        for (i58 = -16; i58 <= 16; ++i58) {
            i599 = Math.abs(i58);
            if (i599 <= 4) {
                this.setBlockAndMetadata(world, i58, 1, -10, this.outFenceBlock, this.outFenceMeta);
            }
            if (i599 >= 4 && i599 <= 11) {
                this.setBlockAndMetadata(world, i58, 1, -9, this.outFenceBlock, this.outFenceMeta);
            }
            if (i599 >= 11 && i599 <= 13) {
                this.setBlockAndMetadata(world, i58, 1, -8, this.outFenceBlock, this.outFenceMeta);
            }
            if (i599 == 13) {
                this.setBlockAndMetadata(world, i58, 1, -7, this.outFenceBlock, this.outFenceMeta);
                this.setBlockAndMetadata(world, i58, 1, -6, this.outFenceBlock, this.outFenceMeta);
            }
            if (i599 == 0) {
                this.setBlockAndMetadata(world, i58, 1, -10, this.outFenceGateBlock, 0);
            }
            if (i599 == 4) {
                this.setBlockAndMetadata(world, i58, 2, -10, Blocks.torch, 5);
            }
            if (i599 <= 1) {
                if (i58 == 0) {
                    this.setBlockAndMetadata(world, i58, 0, -12, this.pathBlock, this.pathMeta);
                    this.setBlockAndMetadata(world, i58, 0, -11, this.pathBlock, this.pathMeta);
                    this.setBlockAndMetadata(world, i58, 0, -10, this.pathBlock, this.pathMeta);
                }
                this.setBlockAndMetadata(world, i58, 0, -9, this.pathBlock, this.pathMeta);
                this.setBlockAndMetadata(world, i58, 0, -8, this.pathBlock, this.pathMeta);
                this.setBlockAndMetadata(world, i58, 1, -7, this.pathSlabBlock, this.pathSlabMeta);
                this.setBlockAndMetadata(world, i58, 1, -6, this.pathSlabBlock, this.pathSlabMeta);
                for (i602 = -5; i602 <= -2; ++i602) {
                    this.setBlockAndMetadata(world, i58, 1, i602, this.pathBlock, this.pathMeta);
                }
            }
            if (i599 == 5 || i599 == 11) {
                this.setGrassToDirt(world, i58, 0, -4);
                for (i602 = 1; i602 <= 5; ++i602) {
                    this.setBlockAndMetadata(world, i58, i602, -4, this.beamBlock, this.beamMeta);
                }
            }
            if (i599 == 6 || i599 == 10) {
                this.setBlockAndMetadata(world, i58, 3, -4, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i58, 2, -4, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i58, 1, -5, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i58, 0, -5, (Block)Blocks.grass, 0);
            }
            if (i599 >= 7 && i599 <= 9) {
                this.setBlockAndMetadata(world, i58, 2, -5, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i58, 1, -5, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i58, 0, -5, (Block)Blocks.grass, 0);
                this.setBlockAndMetadata(world, i58, 1, -6, this.hedgeBlock, this.hedgeMeta);
                this.setBlockAndMetadata(world, i58, 2, -4, this.brickBlock, this.brickMeta);
                this.setGrassToDirt(world, i58, 1, -4);
                this.setBlockAndMetadata(world, i58, 3, -3, LOTRMod.glassPane, 0);
                this.setBlockAndMetadata(world, i58, 4, -3, LOTRMod.glassPane, 0);
                if (i599 == 7 || i599 == 9) {
                    this.placeFlowerPot(world, i58, 3, -4, this.getRandomFlower(world, random));
                }
            }
            if (i599 >= 6 && i599 <= 10) {
                this.setBlockAndMetadata(world, i58, 5, -4, this.plankStairBlock, 6);
            }
            if (i599 >= 5 && i599 <= 11) {
                this.setBlockAndMetadata(world, i58, 6, -4, this.plankBlock, this.plankMeta);
            }
            if (i599 == 13) {
                this.setBlockAndMetadata(world, i58, 3, -6, this.fence2Block, this.fence2Meta);
                this.setBlockAndMetadata(world, i58, 4, -6, Blocks.torch, 5);
            }
            if (i599 == 4) {
                this.setBlockAndMetadata(world, i58, 2, -4, this.hedgeBlock, this.hedgeMeta);
            }
            if (i599 != 3) continue;
            this.setBlockAndMetadata(world, i58, 2, -4, this.hedgeBlock, this.hedgeMeta);
            this.setBlockAndMetadata(world, i58, 2, -3, this.hedgeBlock, this.hedgeMeta);
        }
        for (i58 = -12; i58 <= 12; ++i58) {
            for (i599 = -8; i599 <= -2; ++i599) {
                for (i602 = 0; i602 <= 1; ++i602) {
                    if (this.getBlock(world, i58, i602, i599) != Blocks.grass || !this.isAir(world, i58, i602 + 1, i599) || random.nextInt(12) != 0) continue;
                    this.plantFlower(world, random, i58, i602 + 1, i599);
                }
            }
        }
        for (i58 = -2; i58 <= 2; ++i58) {
            for (i599 = 2; i599 <= 4; ++i599) {
                this.setAir(world, i58, i599, -2);
            }
        }
        this.setBlockAndMetadata(world, -2, 2, -2, this.plankStairBlock, 0);
        this.setBlockAndMetadata(world, -2, 4, -2, this.plankStairBlock, 4);
        this.setBlockAndMetadata(world, 2, 2, -2, this.plankStairBlock, 1);
        this.setBlockAndMetadata(world, 2, 4, -2, this.plankStairBlock, 5);
        for (i58 = -1; i58 <= 1; ++i58) {
            for (i599 = 2; i599 <= 4; ++i599) {
                this.setAir(world, i58, i599, -1);
                this.setBlockAndMetadata(world, i58, i599, 0, this.brickBlock, this.brickMeta);
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
        for (i58 = -3; i58 <= 3; ++i58) {
            this.setBlockAndMetadata(world, i58, 6, -3, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -3, 6, -4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -4, 6, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -4, 6, -5, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 3, 6, -4, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 4, 6, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 4, 6, -5, this.roofStairBlock, 1);
        for (i58 = -11; i58 <= -5; ++i58) {
            this.setBlockAndMetadata(world, i58, 6, -5, this.roofStairBlock, 2);
        }
        for (i58 = 5; i58 <= 11; ++i58) {
            this.setBlockAndMetadata(world, i58, 6, -5, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -11, 6, -6, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 11, 6, -6, this.roofStairBlock, 1);
        for (i58 = -14; i58 <= -12; ++i58) {
            this.setBlockAndMetadata(world, i58, 6, -6, this.roofStairBlock, 2);
        }
        for (i58 = 12; i58 <= 14; ++i58) {
            this.setBlockAndMetadata(world, i58, 6, -6, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -15, 6, -6, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -15, 6, -5, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -16, 6, -5, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -16, 6, -4, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 15, 6, -6, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 15, 6, -5, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 16, 6, -5, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 16, 6, -4, this.roofStairBlock, 2);
        for (int i57 = -4; i57 <= 16; ++i57) {
            this.setBlockAndMetadata(world, -17, 6, i57, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 17, 6, i57, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -16, 6, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -16, 6, 17, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -15, 6, 17, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -15, 6, 18, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 16, 6, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 16, 6, 17, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 15, 6, 17, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 15, 6, 18, this.roofStairBlock, 0);
        for (i56 = -14; i56 <= -11; ++i56) {
            this.setBlockAndMetadata(world, i56, 6, 18, this.roofStairBlock, 3);
        }
        for (i56 = 11; i56 <= 14; ++i56) {
            this.setBlockAndMetadata(world, i56, 6, 18, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -11, 6, 19, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 11, 6, 19, this.roofStairBlock, 0);
        for (i56 = -10; i56 <= 10; ++i56) {
            this.setBlockAndMetadata(world, i56, 6, 18, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i56, 6, 19, this.roofStairBlock, 3);
            i59 = IntMath.mod((int)i56, (int)5);
            if (IntMath.mod((int)i56, (int)5) == 0) continue;
            this.setBlockAndMetadata(world, i56, 5, 18, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i56, 1, 18, this.brickBlock, this.brickMeta);
            this.setGrassToDirt(world, i56, 0, 18);
            if (i59 == 1 || i59 == 4) {
                this.setBlockAndMetadata(world, i56, 2, 18, this.hedgeBlock, this.hedgeMeta);
            } else {
                this.placeFlowerPot(world, i56, 2, 18, this.getRandomFlower(world, random));
            }
            if (this.isOpaque(world, i56, 0, 18)) continue;
            this.setBlockAndMetadata(world, i56, 0, 18, this.plankStairBlock, 7);
        }
        int[] i5911 = new int[]{-15, 12};
        grassHeight = i5911.length;
        for (wood = 0; wood < grassHeight; ++wood) {
            int i5912;
            for (i605 = i5912 = i5911[wood]; i605 <= i5912 + 3; ++i605) {
                this.setBlockAndMetadata(world, i605, 11, 6, this.brickStairBlock, 2);
                this.setBlockAndMetadata(world, i605, 11, 8, this.brickStairBlock, 3);
            }
            this.setBlockAndMetadata(world, i5912, 11, 7, this.brickStairBlock, 1);
            this.setBlockAndMetadata(world, i5912 + 3, 11, 7, this.brickStairBlock, 0);
            for (i605 = i5912 + 1; i605 <= i5912 + 2; ++i605) {
                this.setBlockAndMetadata(world, i605, 11, 7, this.brickBlock, this.brickMeta);
                this.setBlockAndMetadata(world, i605, 12, 7, Blocks.flower_pot, 0);
            }
        }
        for (int i5912 : new int[]{-16, 16}) {
            for (i605 = 3; i605 <= 4; ++i605) {
                for (int i613 = 2; i613 <= 3; ++i613) {
                    this.setBlockAndMetadata(world, i5912, i613, i605, LOTRMod.glassPane, 0);
                }
            }
        }
        for (int i5912 : new int[]{-17, 17}) {
            for (i605 = 2; i605 <= 10; ++i605) {
                if (i605 == 6) continue;
                this.setBlockAndMetadata(world, i5912, 1, i605, this.brickBlock, this.brickMeta);
                this.setGrassToDirt(world, i5912, 0, i605);
                if (i605 == 2 || i605 == 5 || i605 == 7 || i605 == 10) {
                    this.setBlockAndMetadata(world, i5912, 2, i605, this.hedgeBlock, this.hedgeMeta);
                    continue;
                }
                this.placeFlowerPot(world, i5912, 2, i605, this.getRandomFlower(world, random));
            }
            for (int i614 : new int[]{1, 6, 11}) {
                for (i62 = 5; !(i62 < 0 && this.isOpaque(world, i5912, i62, i614) || this.getY(i62) < 0); --i62) {
                    this.setBlockAndMetadata(world, i5912, i62, i614, this.beamBlock, this.beamMeta);
                    this.setGrassToDirt(world, i5912, i62, i614);
                }
            }
            for (i605 = 1; i605 <= 11; ++i605) {
                this.setBlockAndMetadata(world, i5912, 6, i605, this.roofBlock, this.roofMeta);
            }
        }
        for (i55 = 2; i55 <= 10; ++i55) {
            if (i55 == 6) continue;
            if (!this.isOpaque(world, -17, 0, i55)) {
                this.setBlockAndMetadata(world, -17, 0, i55, this.plankStairBlock, 5);
            }
            this.setBlockAndMetadata(world, -17, 5, i55, this.plankStairBlock, 5);
            if (!this.isOpaque(world, 17, 0, i55)) {
                this.setBlockAndMetadata(world, 17, 0, i55, this.plankStairBlock, 4);
            }
            this.setBlockAndMetadata(world, 17, 5, i55, this.plankStairBlock, 4);
        }
        for (i55 = 7; i55 <= 10; ++i55) {
            this.setBlockAndMetadata(world, -17, 5, i55, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, 17, 5, i55, this.plankStairBlock, 4);
        }
        for (i55 = 1; i55 <= 11; ++i55) {
            this.setBlockAndMetadata(world, -18, 6, i55, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 18, 6, i55, this.roofStairBlock, 0);
        }
        for (int i5913 : new int[]{-18, 18}) {
            this.setBlockAndMetadata(world, i5913, 6, 0, this.roofStairBlock, 2);
            this.setBlockAndMetadata(world, i5913, 6, 12, this.roofStairBlock, 3);
        }
        for (i54 = -4; i54 <= 4; ++i54) {
            this.setBlockAndMetadata(world, i54, 7, -2, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -4, 7, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -5, 7, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -5, 7, -4, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 4, 7, -3, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 5, 7, -3, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 5, 7, -4, this.roofStairBlock, 1);
        for (i54 = -12; i54 <= -6; ++i54) {
            this.setBlockAndMetadata(world, i54, 7, -4, this.roofStairBlock, 2);
        }
        for (i54 = 6; i54 <= 12; ++i54) {
            this.setBlockAndMetadata(world, i54, 7, -4, this.roofStairBlock, 2);
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
        for (i53 = -3; i53 <= 0; ++i53) {
            this.setBlockAndMetadata(world, -16, 7, i53, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 16, 7, i53, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -16, 7, 1, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 16, 7, 1, this.roofStairBlock, 2);
        for (i53 = 1; i53 <= 11; ++i53) {
            this.setBlockAndMetadata(world, -17, 7, i53, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 17, 7, i53, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -16, 7, 11, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 16, 7, 11, this.roofStairBlock, 3);
        for (i53 = 12; i53 <= 15; ++i53) {
            this.setBlockAndMetadata(world, -16, 7, i53, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 16, 7, i53, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -15, 7, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -15, 7, 16, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -14, 7, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -14, 7, 17, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 15, 7, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 15, 7, 16, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 14, 7, 16, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 14, 7, 17, this.roofStairBlock, 0);
        for (i52 = -13; i52 <= -11; ++i52) {
            this.setBlockAndMetadata(world, i52, 7, 17, this.roofStairBlock, 3);
        }
        for (i52 = 11; i52 <= 13; ++i52) {
            this.setBlockAndMetadata(world, i52, 7, 17, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -10, 7, 17, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 10, 7, 17, this.roofStairBlock, 0);
        for (i52 = -10; i52 <= 10; ++i52) {
            this.setBlockAndMetadata(world, i52, 7, 18, this.roofStairBlock, 3);
        }
        for (i52 = -5; i52 <= 5; ++i52) {
            this.setBlockAndMetadata(world, i52, 8, -1, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -5, 8, -2, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, -6, 8, -2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, -6, 8, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 5, 8, -2, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 6, 8, -2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 6, 8, -3, this.roofStairBlock, 1);
        for (i52 = -13; i52 <= -7; ++i52) {
            this.setBlockAndMetadata(world, i52, 8, -3, this.roofStairBlock, 2);
        }
        for (i52 = 7; i52 <= 13; ++i52) {
            this.setBlockAndMetadata(world, i52, 8, -3, this.roofStairBlock, 2);
        }
        this.setBlockAndMetadata(world, -13, 8, -4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, 13, 8, -4, this.roofSlabBlock, this.roofSlabMeta);
        this.setBlockAndMetadata(world, -14, 8, -3, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -14, 8, -2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 14, 8, -3, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 14, 8, -2, this.roofStairBlock, 2);
        for (i51 = -2; i51 <= 1; ++i51) {
            this.setBlockAndMetadata(world, -15, 8, i51, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 15, 8, i51, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -15, 8, 2, this.roofStairBlock, 2);
        this.setBlockAndMetadata(world, 15, 8, 2, this.roofStairBlock, 2);
        for (i51 = 2; i51 <= 10; ++i51) {
            this.setBlockAndMetadata(world, -16, 8, i51, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 16, 8, i51, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -15, 8, 10, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 15, 8, 10, this.roofStairBlock, 3);
        for (i51 = 11; i51 <= 14; ++i51) {
            this.setBlockAndMetadata(world, -15, 8, i51, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 15, 8, i51, this.roofStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -14, 8, 14, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -14, 8, 15, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, -13, 8, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, -13, 8, 16, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 14, 8, 14, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 14, 8, 15, this.roofStairBlock, 0);
        this.setBlockAndMetadata(world, 13, 8, 15, this.roofStairBlock, 3);
        this.setBlockAndMetadata(world, 13, 8, 16, this.roofStairBlock, 0);
        for (i50 = -12; i50 <= -10; ++i50) {
            this.setBlockAndMetadata(world, i50, 8, 16, this.roofStairBlock, 3);
        }
        for (i50 = 10; i50 <= 12; ++i50) {
            this.setBlockAndMetadata(world, i50, 8, 16, this.roofStairBlock, 3);
        }
        this.setBlockAndMetadata(world, -9, 8, 16, this.roofStairBlock, 1);
        this.setBlockAndMetadata(world, 9, 8, 16, this.roofStairBlock, 0);
        for (i50 = -9; i50 <= 9; ++i50) {
            this.setBlockAndMetadata(world, i50, 8, 17, this.roofStairBlock, 3);
        }
        for (i50 = -16; i50 <= 16; ++i50) {
            boolean roof;
            i594 = Math.abs(i50);
            for (i603 = -4; i603 <= 17; ++i603) {
                roof = false;
                if (i603 == -4) {
                    boolean bl = roof = i594 == 13;
                }
                if (i603 == -3) {
                    boolean bl = roof = i594 >= 6 && i594 <= 14;
                }
                if (i603 == -2) {
                    boolean bl = roof = i594 >= 5 && i594 <= 15;
                }
                if (i603 >= -1 && i603 <= 1) {
                    boolean bl = roof = i594 <= 15;
                }
                if (i603 >= 2 && i603 <= 10) {
                    boolean bl = roof = i594 <= 16;
                }
                if (i603 >= 11 && i603 <= 14) {
                    boolean bl = roof = i594 <= 15;
                }
                if (i603 == 15) {
                    boolean bl = roof = i594 <= 14;
                }
                if (i603 == 16) {
                    boolean bl = roof = i594 <= 13;
                }
                if (i603 == 17) {
                    boolean bl = roof = i594 <= 9;
                }
                if (!roof) continue;
                this.setBlockAndMetadata(world, i50, 7, i603, this.roofBlock, this.roofMeta);
            }
            for (i603 = -2; i603 <= 16; ++i603) {
                roof = false;
                if (i603 == -2) {
                    boolean bl = roof = i594 >= 7 && i594 <= 13;
                }
                if (i603 == -1) {
                    boolean bl = roof = i594 >= 6 && i594 <= 14;
                }
                if (i603 >= 0 && i603 <= 2) {
                    boolean bl = roof = i594 <= 14;
                }
                if (i603 >= 3 && i603 <= 9) {
                    boolean bl = roof = i594 <= 15;
                }
                if (i603 >= 10 && i603 <= 13) {
                    boolean bl = roof = i594 <= 14;
                }
                if (i603 == 14) {
                    boolean bl = roof = i594 <= 13;
                }
                if (i603 == 15) {
                    boolean bl = roof = i594 <= 12;
                }
                if (i603 == 16) {
                    boolean bl = roof = i594 <= 8;
                }
                if (!roof) continue;
                this.setBlockAndMetadata(world, i50, 8, i603, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i50, 9, i603, this.roofSlabBlock, this.roofSlabMeta);
            }
        }
        for (i50 = -6; i50 <= 6; ++i50) {
            i594 = Math.abs(i50);
            for (int i615 = 1; i615 <= 15; ++i615) {
                room = false;
                if (i615 == 1 && i594 <= 1) {
                    room = true;
                }
                if (i615 == 2 && i594 <= 2) {
                    room = true;
                }
                if (i615 == 3 && i594 <= 3) {
                    room = true;
                }
                if (i615 == 4 && i594 <= 4) {
                    room = true;
                }
                if (i615 == 5 && i594 <= 5) {
                    room = true;
                }
                if (i615 >= 6 && i615 <= 10 && i594 <= 6) {
                    room = true;
                }
                if (i615 >= 11 && i615 <= 12 && i594 <= 5) {
                    room = true;
                }
                if (i615 == 13 && i594 <= 4) {
                    room = true;
                }
                if (i615 >= 14 && i615 <= 15 && i594 <= 2) {
                    room = true;
                }
                if (!room) continue;
                this.setBlockAndMetadata(world, i50, 1, i615, this.floorBlock, this.floorMeta);
                for (i62 = 2; i62 <= 5; ++i62) {
                    this.setAir(world, i50, i62, i615);
                }
            }
            for (i603 = 2; i603 <= 4; ++i603) {
                if (i594 == 2) {
                    this.setBlockAndMetadata(world, i50, i603, 1, this.brickBlock, this.brickMeta);
                }
                if (i594 == 4) {
                    this.setBlockAndMetadata(world, i50, i603, 3, this.beamBlock, this.beamMeta);
                }
                if (i594 == 6) {
                    this.setBlockAndMetadata(world, i50, i603, 5, this.beamBlock, this.beamMeta);
                    this.setBlockAndMetadata(world, i50, i603, 11, this.beamBlock, this.beamMeta);
                }
                if (i594 == 5) {
                    this.setBlockAndMetadata(world, i50, i603, 13, this.beamBlock, this.beamMeta);
                }
                if (i594 != 3) continue;
                this.setBlockAndMetadata(world, i50, i603, 14, this.beamBlock, this.beamMeta);
            }
        }
        for (i50 = -5; i50 <= 5; ++i50) {
            for (i594 = 11; i594 <= 15; ++i594) {
                this.setBlockAndMetadata(world, i50, 5, i594, this.plankBlock, this.plankMeta);
            }
            this.setBlockAndMetadata(world, i50, 5, 10, this.plankStairBlock, 6);
        }
        for (i50 = -1; i50 <= 1; ++i50) {
            this.setBlockAndMetadata(world, i50, 5, 1, this.plankBlock, this.plankMeta);
        }
        for (i50 = -2; i50 <= 2; ++i50) {
            this.setBlockAndMetadata(world, i50, 5, 2, this.plankStairBlock, 7);
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
        for (int i49 = 7; i49 <= 10; ++i49) {
            this.setBlockAndMetadata(world, -6, 5, i49, this.plankStairBlock, 4);
            this.setBlockAndMetadata(world, 6, 5, i49, this.plankStairBlock, 5);
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
        for (i48 = -1; i48 <= 1; ++i48) {
            for (i592 = 1; i592 <= 2; ++i592) {
                this.setBlockAndMetadata(world, i48, 2, i592, this.carpetBlock, this.carpetMeta);
            }
        }
        for (i48 = -2; i48 <= 2; ++i48) {
            for (i592 = 5; i592 <= 7; ++i592) {
                this.setBlockAndMetadata(world, i48, 2, i592, this.carpetBlock, this.carpetMeta);
            }
        }
        for (i48 = -3; i48 <= 3; ++i48) {
            i592 = Math.abs(i48);
            this.setBlockAndMetadata(world, i48, 2, 11, this.plank2Block, this.plank2Meta);
            this.setBlockAndMetadata(world, i48, 4, 11, this.fence2Block, this.fence2Meta);
            if (IntMath.mod((int)i48, (int)2) == 1) {
                this.setBlockAndMetadata(world, i48, 2, 9, this.fence2Block, this.fence2Meta);
            }
            if (i592 == 2) {
                this.placeBarrel(world, random, i48, 3, 11, 3, LOTRFoods.HOBBIT_DRINK);
            }
            if (i592 != 1) continue;
            this.placeMug(world, random, i48, 3, 11, 0, LOTRFoods.HOBBIT_DRINK);
        }
        for (int i47 = 12; i47 <= 13; ++i47) {
            room = new int[]{-3, 3};
            i62 = ((boolean)room).length;
            for (int i5 = 0; i5 < i62; ++i5) {
                i5910 = room[i5];
                this.setBlockAndMetadata(world, i5910, 2, i47, this.plank2Block, this.plank2Meta);
                this.setBlockAndMetadata(world, i5910, 4, i47, this.fence2Block, this.fence2Meta);
            }
        }
        this.setBlockAndMetadata(world, 3, 2, 12, this.fenceGate2Block, 1);
        for (int i46 = -2; i46 <= 2; ++i46) {
            this.setBlockAndMetadata(world, i46, 4, 15, this.plankStairBlock, 6);
        }
        this.setBlockAndMetadata(world, -2, 4, 14, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 2, 4, 14, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -2, 2, 15, Blocks.crafting_table, 0);
        this.placeChest(world, random, -1, 2, 15, 2, LOTRChestContents.HOBBIT_HOLE_LARDER);
        this.setBlockAndMetadata(world, 0, 2, 15, this.plankBlock, this.plankMeta);
        this.placeFlowerPot(world, 0, 3, 15, new ItemStack(LOTRMod.shireHeather));
        this.setBlockAndMetadata(world, 1, 2, 15, (Block)Blocks.cauldron, 3);
        this.setBlockAndMetadata(world, 2, 2, 15, LOTRMod.hobbitOven, 2);
        for (int i5914 : new int[]{-7, 7}) {
            this.setBlockAndMetadata(world, i5914, 1, 8, this.floorBlock, this.floorMeta);
            this.setBlockAndMetadata(world, i5914, 2, 8, this.carpetBlock, this.carpetMeta);
            this.setAir(world, i5914, 3, 8);
            this.setBlockAndMetadata(world, i5914, 2, 7, this.plankStairBlock, 3);
            this.setBlockAndMetadata(world, i5914, 3, 7, this.plankStairBlock, 7);
            this.setBlockAndMetadata(world, i5914, 2, 9, this.plankStairBlock, 2);
            this.setBlockAndMetadata(world, i5914, 3, 9, this.plankStairBlock, 6);
        }
        for (int i45 = 7; i45 <= 9; ++i45) {
            this.setBlockAndMetadata(world, -6, 2, i45, this.carpetBlock, this.carpetMeta);
            this.setBlockAndMetadata(world, -5, 2, i45, this.carpetBlock, this.carpetMeta);
            this.setBlockAndMetadata(world, 5, 2, i45, this.carpetBlock, this.carpetMeta);
            this.setBlockAndMetadata(world, 6, 2, i45, this.carpetBlock, this.carpetMeta);
        }
        for (i44 = -15; i44 <= -8; ++i44) {
            for (i597 = 3; i597 <= 14; ++i597) {
                this.setBlockAndMetadata(world, i44, 0, i597, this.floorBlock, this.floorMeta);
                for (i60 = 1; i60 <= 5; ++i60) {
                    this.setAir(world, i44, i60, i597);
                }
            }
        }
        for (i44 = -15; i44 <= -11; ++i44) {
            for (i597 = -3; i597 <= 3; ++i597) {
                this.setBlockAndMetadata(world, i44, 0, i597, this.floorBlock, this.floorMeta);
                for (i60 = 1; i60 <= 5; ++i60) {
                    this.setAir(world, i44, i60, i597);
                }
            }
        }
        for (i44 = -10; i44 <= -5; ++i44) {
            for (i597 = -2; i597 <= 3; ++i597) {
                this.setBlockAndMetadata(world, i44, 1, i597, this.floorBlock, this.floorMeta);
                for (i60 = 2; i60 <= 5; ++i60) {
                    this.setAir(world, i44, i60, i597);
                }
            }
        }
        for (int i43 = 1; i43 <= 5; ++i43) {
            this.setBlockAndMetadata(world, -15, i43, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -9, i43, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -8, i43, 14, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -8, i43, 11, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -8, i43, 5, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -8, i43, 4, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -9, i43, 3, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, -8, 3, 6, Blocks.torch, 3);
        this.setBlockAndMetadata(world, -8, 3, 10, Blocks.torch, 4);
        for (int i42 = 6; i42 <= 10; ++i42) {
            this.setBlockAndMetadata(world, -8, 1, i42, this.floorStairBlock, 1);
            this.setBlockAndMetadata(world, -8, 5, i42, this.plankStairBlock, 5);
        }
        this.setBlockAndMetadata(world, -9, 1, 11, this.plank2Block, this.plank2Meta);
        for (int i41 = 2; i41 <= 4; ++i41) {
            this.setBlockAndMetadata(world, -9, i41, 11, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, -9, 5, 11, this.plank2Block, this.plank2Meta);
        for (int i40 = 12; i40 <= 13; ++i40) {
            this.setBlockAndMetadata(world, -9, 1, i40, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -8, 1, i40, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -8, 2, i40, this.plankStairBlock, 5);
            this.placeFlowerPot(world, -8, 3, i40, this.getRandomFlower(world, random));
            this.setBlockAndMetadata(world, -8, 4, i40, this.plankStairBlock, 5);
            this.setBlockAndMetadata(world, -8, 5, i40, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -9, 5, i40, this.plank2StairBlock, 5);
        }
        for (i39 = -14; i39 <= -10; ++i39) {
            this.setBlockAndMetadata(world, i39, 1, 14, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i39, 5, 14, this.plank2StairBlock, 6);
        }
        for (i39 = -13; i39 <= -11; ++i39) {
            this.setBlockAndMetadata(world, i39, 2, 15, this.plankStairBlock, 6);
            this.setBlockAndMetadata(world, i39, 3, 15, LOTRMod.barrel, 2);
            this.setBlockAndMetadata(world, i39, 4, 15, this.plankStairBlock, 6);
        }
        for (i38 = 9; i38 <= 13; ++i38) {
            this.setBlockAndMetadata(world, -15, 1, i38, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -15, 5, i38, this.plank2StairBlock, 4);
        }
        for (i38 = 10; i38 <= 12; ++i38) {
            this.spawnItemFrame(world, -16, 3, i38, 1, this.getTavernFramedItem(random));
        }
        for (i37 = -13; i37 <= -11; ++i37) {
            for (i5910 = 10; i5910 <= 12; ++i5910) {
                this.setBlockAndMetadata(world, i37, 1, i5910, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i37, 2, i5910);
            }
        }
        this.setBlockAndMetadata(world, -12, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -13, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -11, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -12, 5, 10, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -12, 5, 12, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, -12, 4, 11, this.chandelierBlock, this.chandelierMeta);
        for (i37 = -15; i37 <= -12; ++i37) {
            for (i5910 = 6; i5910 <= 8; ++i5910) {
                int i607;
                this.setBlockAndMetadata(world, i37, 1, i5910, Blocks.stonebrick, 0);
                for (i607 = 2; i607 <= 4; ++i607) {
                    this.setBlockAndMetadata(world, i37, i607, i5910, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i37, 5, i5910, Blocks.stonebrick, 0);
                for (i607 = 6; i607 <= 10; ++i607) {
                    this.setBlockAndMetadata(world, i37, i607, i5910, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (i37 = -14; i37 <= -13; ++i37) {
            this.setBlockAndMetadata(world, i37, 0, 7, LOTRMod.hearth, 0);
            this.setBlockAndMetadata(world, i37, 1, 7, (Block)Blocks.fire, 0);
            for (i5910 = 2; i5910 <= 10; ++i5910) {
                this.setAir(world, i37, i5910, 7);
            }
        }
        for (int i36 = 1; i36 <= 3; ++i36) {
            this.setBlockAndMetadata(world, -12, i36, 7, this.barsBlock, 0);
        }
        this.setBlockAndMetadata(world, -10, 5, 7, this.chandelierBlock, this.chandelierMeta);
        for (int i35 = 2; i35 <= 5; ++i35) {
            this.setBlockAndMetadata(world, -15, 1, i35, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -15, 5, i35, this.plank2StairBlock, 4);
        }
        for (i34 = 1; i34 <= 5; ++i34) {
            this.setBlockAndMetadata(world, -15, i34, 1, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, -14, 1, 1, this.plank2Block, this.plank2Meta);
        for (i34 = 2; i34 <= 4; ++i34) {
            this.setBlockAndMetadata(world, -14, i34, 1, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, -14, 5, 1, this.plank2Block, this.plank2Meta);
        for (i33 = 3; i33 <= 4; ++i33) {
            this.setBlockAndMetadata(world, -13, 1, i33, this.plank2Block, this.plank2Meta);
            this.placePlateOrMug(world, random, -13, 2, i33);
        }
        this.setBlockAndMetadata(world, -13, 5, 4, this.chandelierBlock, this.chandelierMeta);
        for (i33 = -3; i33 <= 0; ++i33) {
            this.setBlockAndMetadata(world, -15, 1, i33, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, -15, 5, i33, this.plank2StairBlock, 4);
        }
        for (i33 = -2; i33 <= -1; ++i33) {
            for (int i5915 = -13; i5915 <= -12; ++i5915) {
                this.setBlockAndMetadata(world, i5915, 1, i33, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i5915, 2, i33);
            }
            this.spawnItemFrame(world, -16, 3, i33, 1, this.getTavernFramedItem(random));
        }
        for (int i32 = -14; i32 <= -12; ++i32) {
            this.setBlockAndMetadata(world, i32, 1, -4, this.plank2StairBlock, 3);
            for (i595 = 2; i595 <= 4; ++i595) {
                this.setAir(world, i32, i595, -4);
            }
            this.setBlockAndMetadata(world, i32, 5, -4, this.plank2StairBlock, 7);
        }
        this.spawnItemFrame(world, -13, 3, -5, 0, this.getTavernFramedItem(random));
        this.setBlockAndMetadata(world, -12, 5, -1, this.chandelierBlock, this.chandelierMeta);
        for (int i31 = -1; i31 <= 2; ++i31) {
            this.setBlockAndMetadata(world, -10, 1, i31, this.floorStairBlock, 1);
        }
        this.setBlockAndMetadata(world, -10, 1, 3, this.floorStairBlock, 3);
        for (int i30 = 2; i30 <= 5; ++i30) {
            this.setBlockAndMetadata(world, -5, i30, 3, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, -5, i30, -2, this.beamBlock, this.beamMeta);
        }
        for (int i29 = -8; i29 <= -6; ++i29) {
            this.setBlockAndMetadata(world, i29, 2, 3, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i29, 5, 3, this.plank2StairBlock, 6);
        }
        this.setBlockAndMetadata(world, -7, 3, 4, LOTRMod.barrel, 2);
        this.setBlockAndMetadata(world, -7, 4, 4, this.plankStairBlock, 6);
        for (int i28 = -1; i28 <= 2; ++i28) {
            this.setBlockAndMetadata(world, -5, 2, i28, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, -5, 5, i28, this.plank2StairBlock, 5);
        }
        this.setBlockAndMetadata(world, -4, 3, 2, this.plankStairBlock, 2);
        this.setBlockAndMetadata(world, -4, 4, 2, this.plankStairBlock, 6);
        this.setBlockAndMetadata(world, -4, 3, -1, this.plankStairBlock, 3);
        this.setBlockAndMetadata(world, -4, 4, -1, this.plankStairBlock, 7);
        this.placeFlowerPot(world, -4, 3, 1, this.getRandomFlower(world, random));
        this.placeFlowerPot(world, -4, 3, 0, this.getRandomFlower(world, random));
        this.setBlockAndMetadata(world, -4, 4, 1, this.plankSlabBlock, this.plankSlabMeta | 8);
        this.setBlockAndMetadata(world, -4, 4, 0, this.plankSlabBlock, this.plankSlabMeta | 8);
        for (int i27 = -9; i27 <= -6; ++i27) {
            this.setBlockAndMetadata(world, i27, 2, -2, this.plank2StairBlock, 3);
            this.setBlockAndMetadata(world, i27, 5, -2, this.plank2StairBlock, 7);
        }
        this.setBlockAndMetadata(world, -10, 1, -2, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, -10, 2, -2, this.plank2Block, this.plank2Meta);
        for (int i26 = 3; i26 <= 4; ++i26) {
            this.setBlockAndMetadata(world, -10, i26, -2, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, -10, 5, -2, this.plank2Block, this.plank2Meta);
        for (i25 = -8; i25 <= -7; ++i25) {
            for (i595 = 0; i595 <= 1; ++i595) {
                this.setBlockAndMetadata(world, i25, 2, i595, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i25, 3, i595);
            }
        }
        this.setBlockAndMetadata(world, -8, 5, 1, this.chandelierBlock, this.chandelierMeta);
        for (i25 = 8; i25 <= 15; ++i25) {
            for (i595 = 3; i595 <= 14; ++i595) {
                this.setBlockAndMetadata(world, i25, 0, i595, this.floorBlock, this.floorMeta);
                for (i604 = 1; i604 <= 5; ++i604) {
                    this.setAir(world, i25, i604, i595);
                }
            }
        }
        for (i25 = 11; i25 <= 15; ++i25) {
            for (i595 = -3; i595 <= 3; ++i595) {
                this.setBlockAndMetadata(world, i25, 0, i595, this.floorBlock, this.floorMeta);
                for (i604 = 1; i604 <= 5; ++i604) {
                    this.setAir(world, i25, i604, i595);
                }
            }
        }
        for (i25 = 5; i25 <= 10; ++i25) {
            for (i595 = -2; i595 <= 3; ++i595) {
                this.setBlockAndMetadata(world, i25, 1, i595, this.floorBlock, this.floorMeta);
                for (i604 = 2; i604 <= 5; ++i604) {
                    this.setAir(world, i25, i604, i595);
                }
            }
        }
        for (int i24 = 1; i24 <= 5; ++i24) {
            this.setBlockAndMetadata(world, 15, i24, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 9, i24, 14, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 8, i24, 14, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 8, i24, 11, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 8, i24, 5, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 8, i24, 4, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 9, i24, 3, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, 8, 3, 6, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 8, 3, 10, Blocks.torch, 4);
        for (int i23 = 6; i23 <= 10; ++i23) {
            this.setBlockAndMetadata(world, 8, 1, i23, this.floorStairBlock, 0);
            this.setBlockAndMetadata(world, 8, 5, i23, this.plankStairBlock, 4);
        }
        this.setBlockAndMetadata(world, 9, 1, 11, this.plank2Block, this.plank2Meta);
        for (int i22 = 2; i22 <= 4; ++i22) {
            this.setBlockAndMetadata(world, 9, i22, 11, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, 9, 5, 11, this.plank2Block, this.plank2Meta);
        for (int i21 = 12; i21 <= 13; ++i21) {
            this.setBlockAndMetadata(world, 9, 1, i21, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 8, 1, i21, this.plankBlock, this.plankMeta);
            for (i593 = 2; i593 <= 4; ++i593) {
                this.setBlockAndMetadata(world, 8, i593, i21, Blocks.bookshelf, 0);
            }
            this.setBlockAndMetadata(world, 8, 5, i21, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, 9, 5, i21, this.plank2StairBlock, 4);
        }
        for (i20 = 10; i20 <= 14; ++i20) {
            this.setBlockAndMetadata(world, i20, 1, 14, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i20, 5, 14, this.plank2StairBlock, 6);
        }
        for (i20 = 10; i20 <= 14; ++i20) {
            for (i593 = 2; i593 <= 4; ++i593) {
                this.setBlockAndMetadata(world, i20, i593, 15, Blocks.bookshelf, 0);
            }
        }
        for (int i19 = 9; i19 <= 13; ++i19) {
            this.setBlockAndMetadata(world, 15, 1, i19, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 15, 5, i19, this.plank2StairBlock, 5);
        }
        this.placeWallBanner(world, 16, 4, 11, LOTRItemBanner.BannerType.HOBBIT, 3);
        for (i18 = 11; i18 <= 13; ++i18) {
            for (i596 = 10; i596 <= 12; ++i596) {
                this.setBlockAndMetadata(world, i18, 1, i596, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i18, 2, i596);
            }
        }
        this.setBlockAndMetadata(world, 12, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 13, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 11, 5, 11, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 12, 5, 10, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 12, 5, 12, this.fence2Block, this.fence2Meta);
        this.setBlockAndMetadata(world, 12, 4, 11, this.chandelierBlock, this.chandelierMeta);
        for (i18 = 12; i18 <= 15; ++i18) {
            for (i596 = 6; i596 <= 8; ++i596) {
                int i608;
                this.setBlockAndMetadata(world, i18, 1, i596, Blocks.stonebrick, 0);
                for (i608 = 2; i608 <= 4; ++i608) {
                    this.setBlockAndMetadata(world, i18, i608, i596, this.brickBlock, this.brickMeta);
                }
                this.setBlockAndMetadata(world, i18, 5, i596, Blocks.stonebrick, 0);
                for (i608 = 6; i608 <= 10; ++i608) {
                    this.setBlockAndMetadata(world, i18, i608, i596, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (i18 = 13; i18 <= 14; ++i18) {
            this.setBlockAndMetadata(world, i18, 0, 7, LOTRMod.hearth, 0);
            this.setBlockAndMetadata(world, i18, 1, 7, (Block)Blocks.fire, 0);
            for (i596 = 2; i596 <= 10; ++i596) {
                this.setAir(world, i18, i596, 7);
            }
        }
        for (int i17 = 1; i17 <= 3; ++i17) {
            this.setBlockAndMetadata(world, 12, i17, 7, this.barsBlock, 0);
        }
        this.setBlockAndMetadata(world, 10, 5, 7, this.chandelierBlock, this.chandelierMeta);
        for (int i16 = 2; i16 <= 5; ++i16) {
            this.setBlockAndMetadata(world, 15, 1, i16, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 15, 5, i16, this.plank2StairBlock, 5);
        }
        for (i15 = 1; i15 <= 5; ++i15) {
            this.setBlockAndMetadata(world, 15, i15, 1, this.beamBlock, this.beamMeta);
        }
        this.setBlockAndMetadata(world, 14, 1, 1, this.plank2Block, this.plank2Meta);
        for (i15 = 2; i15 <= 4; ++i15) {
            this.setBlockAndMetadata(world, 14, i15, 1, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, 14, 5, 1, this.plank2Block, this.plank2Meta);
        for (i14 = 3; i14 <= 4; ++i14) {
            this.setBlockAndMetadata(world, 13, 1, i14, this.plank2Block, this.plank2Meta);
            this.placePlateOrMug(world, random, 13, 2, i14);
        }
        this.setBlockAndMetadata(world, 13, 5, 4, this.chandelierBlock, this.chandelierMeta);
        for (i14 = -3; i14 <= 0; ++i14) {
            this.setBlockAndMetadata(world, 15, 1, i14, this.plank2StairBlock, 1);
            this.setBlockAndMetadata(world, 15, 5, i14, this.plank2StairBlock, 5);
        }
        for (i14 = -2; i14 <= -1; ++i14) {
            for (int i5916 = 12; i5916 <= 13; ++i5916) {
                this.setBlockAndMetadata(world, i5916, 1, i14, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i5916, 2, i14);
            }
        }
        this.placeWallBanner(world, 16, 4, -2, LOTRItemBanner.BannerType.HOBBIT, 3);
        for (int i13 = 12; i13 <= 14; ++i13) {
            this.setBlockAndMetadata(world, i13, 1, -4, this.plank2StairBlock, 3);
            for (i598 = 2; i598 <= 4; ++i598) {
                this.setAir(world, i13, i598, -4);
            }
            this.setBlockAndMetadata(world, i13, 5, -4, this.plank2StairBlock, 7);
        }
        this.placeWallBanner(world, 13, 4, -5, LOTRItemBanner.BannerType.HOBBIT, 0);
        this.setBlockAndMetadata(world, 12, 5, -1, this.chandelierBlock, this.chandelierMeta);
        for (int i12 = -1; i12 <= 2; ++i12) {
            this.setBlockAndMetadata(world, 10, 1, i12, this.floorStairBlock, 0);
        }
        this.setBlockAndMetadata(world, 10, 1, 3, this.floorStairBlock, 3);
        for (int i11 = 2; i11 <= 5; ++i11) {
            this.setBlockAndMetadata(world, 5, i11, 3, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 5, i11, -2, this.beamBlock, this.beamMeta);
        }
        for (int i10 = 6; i10 <= 8; ++i10) {
            this.setBlockAndMetadata(world, i10, 2, 3, this.plank2StairBlock, 2);
            this.setBlockAndMetadata(world, i10, 5, 3, this.plank2StairBlock, 6);
        }
        this.placeWallBanner(world, 7, 4, 4, LOTRItemBanner.BannerType.HOBBIT, 2);
        for (int i9 = -1; i9 <= 2; ++i9) {
            this.setBlockAndMetadata(world, 5, 2, i9, this.plank2StairBlock, 0);
            this.setBlockAndMetadata(world, 4, 3, i9, Blocks.bookshelf, 0);
            this.setBlockAndMetadata(world, 4, 4, i9, Blocks.bookshelf, 0);
            this.setBlockAndMetadata(world, 5, 5, i9, this.plank2StairBlock, 4);
        }
        for (int i8 = 6; i8 <= 9; ++i8) {
            this.setBlockAndMetadata(world, i8, 2, -2, this.plank2StairBlock, 3);
            this.setBlockAndMetadata(world, i8, 5, -2, this.plank2StairBlock, 7);
        }
        this.setBlockAndMetadata(world, 10, 1, -2, this.plank2Block, this.plank2Meta);
        this.setBlockAndMetadata(world, 10, 2, -2, this.plank2Block, this.plank2Meta);
        for (int i7 = 3; i7 <= 4; ++i7) {
            this.setBlockAndMetadata(world, 10, i7, -2, this.fence2Block, this.fence2Meta);
        }
        this.setBlockAndMetadata(world, 10, 5, -2, this.plank2Block, this.plank2Meta);
        for (i6 = 7; i6 <= 8; ++i6) {
            for (i598 = 0; i598 <= 1; ++i598) {
                this.setBlockAndMetadata(world, i6, 2, i598, this.plank2Block, this.plank2Meta);
                this.placePlateOrMug(world, random, i6, 3, i598);
            }
        }
        this.setBlockAndMetadata(world, 8, 5, 1, this.chandelierBlock, this.chandelierMeta);
        for (i6 = -3; i6 <= 4; ++i6) {
            for (i598 = 11; i598 <= 15; ++i598) {
                this.setBlockAndMetadata(world, i6, -4, i598, this.floorBlock, this.floorMeta);
                for (int i609 = -3; i609 <= 0; ++i609) {
                    this.setAir(world, i6, i609, i598);
                }
            }
        }
        for (i6 = -3; i6 <= 4; ++i6) {
            for (int i6010 : new int[]{10, 16}) {
                this.setBlockAndMetadata(world, i6, -3, i6010, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i6, -2, i6010, this.beamBlock, this.beamMeta | 4);
                this.setBlockAndMetadata(world, i6, -1, i6010, this.plankBlock, this.plankMeta);
            }
            for (i598 = 11; i598 <= 13; ++i598) {
                if (i6 < 0) continue;
                this.setBlockAndMetadata(world, i6, 0, i598, this.beamBlock, this.beamMeta | 4);
            }
            for (i598 = 14; i598 <= 15; ++i598) {
                this.setBlockAndMetadata(world, i6, 0, i598, this.beamBlock, this.beamMeta | 4);
            }
        }
        for (int i5 = 11; i5 <= 15; ++i5) {
            for (int i5917 : new int[]{-4, 5}) {
                this.setBlockAndMetadata(world, i5917, -3, i5, this.plankBlock, this.plankMeta);
                this.setBlockAndMetadata(world, i5917, -2, i5, this.beamBlock, this.beamMeta | 8);
                this.setBlockAndMetadata(world, i5917, -1, i5, this.plankBlock, this.plankMeta);
            }
        }
        for (int j1 = -3; j1 <= -1; ++j1) {
            this.setBlockAndMetadata(world, -3, j1, 15, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 4, j1, 15, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 4, j1, 11, this.beamBlock, this.beamMeta);
            this.setBlockAndMetadata(world, 0, j1, 11, this.beamBlock, this.beamMeta);
        }
        this.placeBarrel(world, random, 4, -3, 14, 5, LOTRFoods.HOBBIT_DRINK);
        for (i4 = 12; i4 <= 13; ++i4) {
            this.placeChest(world, random, 4, -3, i4, 5, LOTRChestContents.HOBBIT_HOLE_LARDER);
        }
        for (i4 = 12; i4 <= 14; ++i4) {
            this.setBlockAndMetadata(world, 4, -2, i4, this.plankSlabBlock, this.plankSlabMeta | 8);
            this.placeBarrel(world, random, 4, -1, i4, 5, LOTRFoods.HOBBIT_DRINK);
        }
        this.placeBarrel(world, random, 1, -3, 11, 3, LOTRFoods.HOBBIT_DRINK);
        for (i3 = 2; i3 <= 3; ++i3) {
            this.placeChest(world, random, i3, -3, 11, 3, LOTRChestContents.HOBBIT_HOLE_LARDER);
        }
        for (i3 = 1; i3 <= 3; ++i3) {
            this.setBlockAndMetadata(world, i3, -2, 11, this.plankSlabBlock, this.plankSlabMeta | 8);
            Block cakeBlock = LOTRWorldGenHobbitStructure.getRandomCakeBlock(random);
            this.setBlockAndMetadata(world, i3, -1, 11, cakeBlock, 0);
        }
        for (i2 = 11; i2 <= 13; ++i2) {
            this.setAir(world, -2, 1, i2);
            this.setAir(world, -3, 1, i2);
            this.setAir(world, -3, 0, i2);
        }
        for (i2 = 10; i2 <= 12; ++i2) {
            this.setAir(world, -3, 0, i2);
        }
        this.setBlockAndMetadata(world, -3, 1, 14, this.floorBlock, this.floorMeta);
        for (int n = -3; n <= -1; ++n) {
            for (int i5917 = 11; i5917 <= 12; ++i5917) {
                for (int i6011 = -3; i6011 <= -1; ++i6011) {
                    this.setBlockAndMetadata(world, n, i6011, i5917, this.brickBlock, this.brickMeta);
                }
            }
        }
        for (int step = 0; step <= 2; ++step) {
            this.setBlockAndMetadata(world, -2, 1 - step, 14 - step, this.floorStairBlock, 2);
        }
        for (m = -3; m <= -2; ++m) {
            this.setAir(world, m, -1, 11);
            this.setBlockAndMetadata(world, m, -2, 11, this.floorBlock, this.floorMeta);
        }
        this.setAir(world, -3, -1, 12);
        this.setBlockAndMetadata(world, -3, -2, 12, this.floorStairBlock, 3);
        for (m = -2; m <= -1; ++m) {
            this.setBlockAndMetadata(world, m, -1, 13, this.floorStairBlock, 7);
        }
        for (k1 = 13; k1 <= 14; ++k1) {
            this.setBlockAndMetadata(world, -3, -3, k1, this.floorBlock, this.floorMeta);
        }
        for (k1 = 13; k1 <= 15; ++k1) {
            this.setBlockAndMetadata(world, -2, -3, k1, this.floorStairBlock, 0);
        }
        this.setBlockAndMetadata(world, -2, -1, 15, Blocks.torch, 2);
        for (k1 = 11; k1 <= 13; ++k1) {
            this.setBlockAndMetadata(world, -4, 0, k1, this.beamBlock, this.beamMeta | 8);
            this.setBlockAndMetadata(world, -1, 0, k1, this.beamBlock, this.beamMeta | 8);
        }
        for (int i1 = -3; i1 <= -2; ++i1) {
            this.setBlockAndMetadata(world, i1, 0, 10, this.beamBlock, this.beamMeta | 4);
        }
        LOTREntityHobbitBartender bartender = new LOTREntityHobbitBartender(world);
        bartender.setSpecificLocationName(this.tavernNameNPC);
        this.spawnNPCAndSetHome(bartender, world, 1, 2, 13, 2);
        for (int i5918 : new int[]{-10, 10}) {
            int i6012 = 1;
            int i616 = 7;
            int hobbits = 3 + random.nextInt(6);
            for (int l = 0; l < hobbits; ++l) {
                LOTREntityHobbit hobbit = new LOTREntityHobbit(world);
                this.spawnNPCAndSetHome(hobbit, world, i5918, i6012, i616, 16);
            }
            if (random.nextInt(4) != 0) continue;
            LOTREntityHobbitShirriff lOTREntityHobbitShirriff = new LOTREntityHobbitShirriff(world);
            lOTREntityHobbitShirriff.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(lOTREntityHobbitShirriff, world, i5918, i6012, i616, 16);
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

