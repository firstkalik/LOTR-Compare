/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCauldron
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockFire
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityGondorBartender;
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenGondorTavern
extends LOTRWorldGenGondorStructure {
    private String[] tavernName;
    private String[] tavernNameSign;
    private String tavernNameNPC;

    public LOTRWorldGenGondorTavern(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        this.bedBlock = Blocks.bed;
        this.tavernName = LOTRNames.getGondorTavernName(random);
        this.tavernNameSign = new String[]{"", this.tavernName[0], this.tavernName[1], ""};
        this.tavernNameNPC = this.tavernName[0] + " " + this.tavernName[1];
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int step;
        int k1;
        int k122;
        int j1;
        int i1;
        int i12;
        int j12;
        int i132;
        int oppHeight;
        int i14;
        int k13;
        int k142;
        int beam;
        this.setOriginAndRotation(world, i, j, k, rotation, 1);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (i1 = -9; i1 <= 13; ++i1) {
                for (k122 = -2; k122 <= 16; ++k122) {
                    int j13 = this.getTopBlock(world, i1, k122) - 1;
                    if (!this.isSurface(world, i1, j13, k122)) {
                        return false;
                    }
                    if (j13 < minHeight) {
                        minHeight = j13;
                    }
                    if (j13 > maxHeight) {
                        maxHeight = j13;
                    }
                    if (maxHeight - minHeight <= 6) continue;
                    return false;
                }
            }
        }
        if (this.restrictions && (oppHeight = this.getTopBlock(world, 0, 15) - 1) > 0) {
            this.originY = this.getY(oppHeight);
        }
        for (int i15 = -7; i15 <= 11; ++i15) {
            for (k13 = 0; k13 <= 14; ++k13) {
                if ((i15 == -7 || i15 == 11) & (k13 == 0 || k13 == 14)) continue;
                beam = 0;
                if (i15 == -7 || i15 == 11) {
                    beam = IntMath.mod((int)k13, (int)4) == 1 ? 1 : 0;
                } else if (k13 == 0 || k13 == 14) {
                    int n = beam = IntMath.mod((int)i15, (int)4) == 2 ? 1 : 0;
                }
                if (beam != 0) {
                    for (j1 = 4; !(j1 < 0 && this.isOpaque(world, i15, j1, k13) || this.getY(j1) < 0); --j1) {
                        this.setBlockAndMetadata(world, i15, j1, k13, this.woodBeamBlock, this.woodBeamMeta);
                        this.setGrassToDirt(world, i15, j1 - 1, k13);
                    }
                    continue;
                }
                if (i15 == -7 || i15 == 11 || k13 == 0 || k13 == 14) {
                    for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i15, j1, k13) || this.getY(j1) < 0); --j1) {
                        this.setBlockAndMetadata(world, i15, j1, k13, this.rockBlock, this.rockMeta);
                        this.setGrassToDirt(world, i15, j1 - 1, k13);
                    }
                    for (j1 = 1; j1 <= 4; ++j1) {
                        this.setBlockAndMetadata(world, i15, j1, k13, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                for (j1 = 0; !(j1 < 0 && this.isOpaque(world, i15, j1, k13) || this.getY(j1) < 0); --j1) {
                    this.setBlockAndMetadata(world, i15, j1, k13, this.plankBlock, this.plankMeta);
                    this.setGrassToDirt(world, i15, j1 - 1, k13);
                }
                for (j1 = 1; j1 <= 4; ++j1) {
                    this.setAir(world, i15, j1, k13);
                }
            }
        }
        for (int k122 : new int[]{0, 14}) {
            for (i14 = -4; i14 <= 8; ++i14) {
                if (IntMath.mod((int)i14, (int)4) != 0 || i14 == 0) continue;
                this.setBlockAndMetadata(world, i14, 2, k122, LOTRMod.glassPane, 0);
                this.setBlockAndMetadata(world, i14, 3, k122, LOTRMod.glassPane, 0);
            }
        }
        for (int i132 : new int[]{-7, 11}) {
            for (k142 = 3; k142 <= 11; ++k142) {
                if (IntMath.mod((int)k142, (int)4) != 3 || i132 == -7 && k142 == 7) continue;
                this.setBlockAndMetadata(world, i132, 2, k142, LOTRMod.glassPane, 0);
                this.setBlockAndMetadata(world, i132, 3, k142, LOTRMod.glassPane, 0);
            }
        }
        this.setBlockAndMetadata(world, 0, 0, 0, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, 0, 1, 0, this.doorBlock, 1);
        this.setBlockAndMetadata(world, 0, 2, 0, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 0, 0, 14, this.plankBlock, this.plankMeta);
        this.setBlockAndMetadata(world, 0, 1, 14, this.doorBlock, 3);
        this.setBlockAndMetadata(world, 0, 2, 14, this.doorBlock, 8);
        int[] i15 = new int[]{-1, 15};
        k13 = i15.length;
        block13: for (beam = 0; beam < k13; ++beam) {
            int j14;
            i14 = 0;
            k122 = i15[beam];
            int doorHeight = this.getTopBlock(world, i14, k122) - 1;
            if (doorHeight >= 0) continue;
            for (j14 = 0; !(j14 != 0 && this.isOpaque(world, i14, j14, k122) || this.getY(j14) < 0); --j14) {
                this.setBlockAndMetadata(world, i14, j14, k122, this.plankBlock, this.plankMeta);
                this.setGrassToDirt(world, i14, j14 - 1, k122);
            }
            ++i14;
            j14 = 0;
            while (!this.isOpaque(world, i14, j14, k122) && this.getY(j14) >= 0) {
                this.setBlockAndMetadata(world, i14, j14, k122, this.plankStairBlock, 0);
                this.setGrassToDirt(world, i14, j14 - 1, k122);
                int j2 = j14 - 1;
                while (!this.isOpaque(world, i14, j2, k122) && this.getY(j2) >= 0) {
                    this.setBlockAndMetadata(world, i14, j2, k122, this.plankBlock, this.plankMeta);
                    this.setGrassToDirt(world, i14, j2 - 1, k122);
                    --j2;
                }
                if (++i14 >= 15) continue block13;
                --j14;
            }
        }
        this.setBlockAndMetadata(world, -2, 3, -1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -2, 4, -1, Blocks.torch, 5);
        this.setBlockAndMetadata(world, 2, 3, -1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 2, 4, -1, Blocks.torch, 5);
        this.setBlockAndMetadata(world, 0, 4, -1, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 4, -2, this.plankBlock, this.plankMeta);
        this.placeSign(world, -1, 4, -2, Blocks.wall_sign, 5, this.tavernNameSign);
        this.placeSign(world, 1, 4, -2, Blocks.wall_sign, 4, this.tavernNameSign);
        this.placeSign(world, 0, 4, -3, Blocks.wall_sign, 2, this.tavernNameSign);
        this.setBlockAndMetadata(world, -2, 3, 15, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -2, 4, 15, Blocks.torch, 5);
        this.setBlockAndMetadata(world, 2, 3, 15, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 2, 4, 15, Blocks.torch, 5);
        this.setBlockAndMetadata(world, 0, 4, 15, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, 0, 4, 16, this.plankBlock, this.plankMeta);
        this.placeSign(world, -1, 4, 16, Blocks.wall_sign, 5, this.tavernNameSign);
        this.placeSign(world, 1, 4, 16, Blocks.wall_sign, 4, this.tavernNameSign);
        this.placeSign(world, 0, 4, 17, Blocks.wall_sign, 3, this.tavernNameSign);
        for (int i16 = -8; i16 <= 12; ++i16) {
            for (k13 = -1; k13 <= 15; ++k13) {
                if ((i16 <= -7 || i16 >= 11) & (k13 <= 0 || k13 >= 14)) continue;
                beam = 0;
                if (i16 == -8 || i16 == 12) {
                    beam = IntMath.mod((int)k13, (int)4) == 1 ? 1 : 0;
                } else if (k13 == -1 || k13 == 15) {
                    int n = beam = IntMath.mod((int)i16, (int)4) == 2 ? 1 : 0;
                }
                if (beam != 0) {
                    if (i16 == -8 || i16 == 12) {
                        this.setBlockAndMetadata(world, i16, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                    }
                    if (k13 == -1 || k13 == 15) {
                        this.setBlockAndMetadata(world, i16, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 8);
                    }
                    for (j1 = 6; j1 <= 8; ++j1) {
                        this.setBlockAndMetadata(world, i16, j1, k13, this.woodBeamBlock, this.woodBeamMeta);
                    }
                    continue;
                }
                if (i16 == -8 || i16 == 12 || k13 == -1 || k13 == 15) {
                    if (i16 == -8 || i16 == 12) {
                        this.setBlockAndMetadata(world, i16, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 8);
                    }
                    if (k13 == -1 || k13 == 15) {
                        this.setBlockAndMetadata(world, i16, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                    }
                    for (j1 = 6; j1 <= 8; ++j1) {
                        this.setBlockAndMetadata(world, i16, j1, k13, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                if (!((i16 != -7 && i16 != 11 || k13 != 1 && k13 != 13) && (i16 != -6 && i16 != 10 || k13 != 0 && k13 != 14))) {
                    if (i16 == -7 || i16 == 11) {
                        this.setBlockAndMetadata(world, i16, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 4);
                    }
                    if (k13 == 0 || k13 == 14) {
                        this.setBlockAndMetadata(world, i16, 5, k13, this.woodBeamBlock, this.woodBeamMeta | 8);
                    }
                    for (j1 = 6; j1 <= 8; ++j1) {
                        this.setBlockAndMetadata(world, i16, j1, k13, this.wallBlock, this.wallMeta);
                    }
                    continue;
                }
                this.setBlockAndMetadata(world, i16, 5, k13, this.plankBlock, this.plankMeta);
                for (j1 = 6; j1 <= 11; ++j1) {
                    this.setAir(world, i16, j1, k13);
                }
            }
        }
        for (int k122 : new int[]{-1, 15}) {
            for (i14 = -4; i14 <= 8; ++i14) {
                if (IntMath.mod((int)i14, (int)4) != 0) continue;
                this.setBlockAndMetadata(world, i14, 7, k122, LOTRMod.glassPane, 0);
            }
        }
        int[] i16 = new int[]{-8, 12};
        k13 = i16.length;
        for (beam = 0; beam < k13; ++beam) {
            i132 = i16[beam];
            for (k142 = 3; k142 <= 11; ++k142) {
                if (IntMath.mod((int)k142, (int)4) != 3) continue;
                this.setBlockAndMetadata(world, i132, 7, k142, LOTRMod.glassPane, 0);
            }
        }
        for (int step2 = 0; step2 <= 2; ++step2) {
            for (int i17 = -9; i17 <= 13; ++i17) {
                if (i17 >= -7 + step2 && i17 <= 11 - step2) {
                    this.setBlockAndMetadata(world, i17, 8 + step2, -2 + step2, this.roofStairBlock, 2);
                    this.setBlockAndMetadata(world, i17, 8 + step2, 16 - step2, this.roofStairBlock, 3);
                }
                if (i17 > -7 + step2 && i17 < 11 - step2) continue;
                this.setBlockAndMetadata(world, i17, 8 + step2, 0 + step2, this.roofStairBlock, 2);
                this.setBlockAndMetadata(world, i17, 8 + step2, 14 - step2, this.roofStairBlock, 3);
            }
            this.setBlockAndMetadata(world, -7 + step2, 8 + step2, -1 + step2, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 11 - step2, 8 + step2, -1 + step2, this.roofStairBlock, 0);
            this.setBlockAndMetadata(world, -7 + step2, 8 + step2, 15 - step2, this.roofStairBlock, 1);
            this.setBlockAndMetadata(world, 11 - step2, 8 + step2, 15 - step2, this.roofStairBlock, 0);
        }
        for (int i18 = -9; i18 <= 13; ++i18) {
            this.setBlockAndMetadata(world, i18, 11, 4, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i18, 12, 5, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i18, 12, 6, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i18, 12, 7, this.woodBeamBlock, this.woodBeamMeta | 4);
            this.setBlockAndMetadata(world, i18, 13, 7, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i18, 12, 8, this.roofBlock, this.roofMeta);
            this.setBlockAndMetadata(world, i18, 12, 9, this.roofSlabBlock, this.roofSlabMeta);
            this.setBlockAndMetadata(world, i18, 11, 10, this.roofBlock, this.roofMeta);
            if (i18 >= -3 && i18 <= 7) {
                this.setBlockAndMetadata(world, i18, 11, 1, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, i18, 11, 2, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i18, 11, 3, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i18, 11, 11, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i18, 11, 12, this.roofBlock, this.roofMeta);
                this.setBlockAndMetadata(world, i18, 11, 13, this.roofSlabBlock, this.roofSlabMeta);
            } else {
                this.setBlockAndMetadata(world, i18, 11, 3, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, i18, 11, 11, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (i18 == -4 || i18 == 8) {
                this.setBlockAndMetadata(world, i18, 11, 1, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, i18, 11, 2, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, i18, 11, 12, this.roofSlabBlock, this.roofSlabMeta);
                this.setBlockAndMetadata(world, i18, 11, 13, this.roofSlabBlock, this.roofSlabMeta);
            }
            if (i18 != -9 && i18 != 13) continue;
            this.setBlockAndMetadata(world, i18, 8, 1, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i18, 9, 2, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i18, 10, 3, this.roofStairBlock, 7);
            this.setBlockAndMetadata(world, i18, 11, 5, this.roofSlabBlock, this.roofSlabMeta | 8);
            this.setBlockAndMetadata(world, i18, 11, 9, this.roofSlabBlock, this.roofSlabMeta | 8);
            this.setBlockAndMetadata(world, i18, 10, 11, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, i18, 9, 12, this.roofStairBlock, 6);
            this.setBlockAndMetadata(world, i18, 8, 13, this.roofStairBlock, 6);
        }
        for (int i132 : new int[]{-8, 12}) {
            for (k142 = 2; k142 <= 12; ++k142) {
                this.setBlockAndMetadata(world, i132, 9, k142, this.woodBeamBlock, this.woodBeamMeta | 8);
            }
            for (k142 = 3; k142 <= 11; ++k142) {
                this.setBlockAndMetadata(world, i132, 10, k142, this.wallBlock, this.wallMeta);
            }
            for (k142 = 5; k142 <= 9; ++k142) {
                this.setBlockAndMetadata(world, i132, 11, k142, this.wallBlock, this.wallMeta);
            }
        }
        for (int i19 = 3; i19 <= 5; ++i19) {
            for (k13 = 6; k13 <= 8; ++k13) {
                for (j12 = 0; j12 <= 13; ++j12) {
                    if (i19 == 4 && k13 == 7) {
                        this.setAir(world, i19, j12, k13);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i19, j12, k13, this.brickBlock, this.brickMeta);
                }
            }
            this.setBlockAndMetadata(world, i19, 14, 6, this.brickStairBlock, 2);
            this.setBlockAndMetadata(world, i19, 14, 8, this.brickStairBlock, 3);
        }
        this.setBlockAndMetadata(world, 3, 14, 7, this.brickStairBlock, 1);
        this.setBlockAndMetadata(world, 5, 14, 7, this.brickStairBlock, 0);
        this.setBlockAndMetadata(world, 4, 15, 7, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 4, 16, 7, this.brickBlock, this.brickMeta);
        this.setBlockAndMetadata(world, 4, 17, 7, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 4, 18, 7, this.brickWallBlock, this.brickWallMeta);
        this.setBlockAndMetadata(world, 4, 0, 7, LOTRMod.hearth, 0);
        this.setBlockAndMetadata(world, 4, 1, 7, (Block)Blocks.fire, 0);
        this.setBlockAndMetadata(world, 4, 1, 6, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 4, 1, 8, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 3, 1, 7, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 5, 1, 7, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 4, 2, 6, Blocks.furnace, 2);
        this.setBlockAndMetadata(world, 4, 2, 8, Blocks.furnace, 3);
        this.setBlockAndMetadata(world, 3, 2, 7, Blocks.furnace, 5);
        this.setBlockAndMetadata(world, 5, 2, 7, Blocks.furnace, 4);
        this.setBlockAndMetadata(world, 0, 4, 3, LOTRMod.chandelier, 1);
        this.setBlockAndMetadata(world, 0, 4, 11, LOTRMod.chandelier, 1);
        this.setBlockAndMetadata(world, 8, 4, 3, LOTRMod.chandelier, 1);
        this.setBlockAndMetadata(world, 8, 4, 11, LOTRMod.chandelier, 1);
        for (int k122 : new int[]{1, 2}) {
            this.setBlockAndMetadata(world, -4, 1, k122, this.plankBlock, this.plankMeta);
            this.placeMugOrPlate(world, random, -4, 2, k122);
            this.setBlockAndMetadata(world, -6, 1, k122, this.plankStairBlock, 0);
            this.setBlockAndMetadata(world, -2, 1, k122, this.plankStairBlock, 1);
        }
        int[] i19 = new int[]{1, 2, 12, 13};
        k13 = i19.length;
        for (j12 = 0; j12 < k13; ++j12) {
            k122 = i19[j12];
            this.setBlockAndMetadata(world, 2, 1, k122, this.plankBlock, this.plankMeta);
            this.placeMugOrPlate(world, random, 2, 2, k122);
            this.setBlockAndMetadata(world, 3, 1, k122, this.plankBlock, this.plankMeta);
            this.placeMugOrPlate(world, random, 3, 2, k122);
            this.setBlockAndMetadata(world, 5, 1, k122, this.plankStairBlock, 1);
        }
        for (k1 = 6; k1 <= 8; ++k1) {
            this.setBlockAndMetadata(world, 8, 1, k1, this.plankBlock, this.plankMeta);
            this.placeMugOrPlate(world, random, 8, 2, k1);
            this.setBlockAndMetadata(world, 10, 1, k1, this.plankStairBlock, 1);
        }
        for (i12 = 7; i12 <= 10; ++i12) {
            this.setBlockAndMetadata(world, i12, 1, 1, this.plankStairBlock, 3);
            this.setBlockAndMetadata(world, i12, 1, 13, this.plankStairBlock, 2);
        }
        for (k1 = 2; k1 <= 4; ++k1) {
            this.setBlockAndMetadata(world, 10, 1, k1, this.plankStairBlock, 1);
        }
        for (k1 = 10; k1 <= 12; ++k1) {
            this.setBlockAndMetadata(world, 10, 1, k1, this.plankStairBlock, 1);
        }
        for (i12 = 7; i12 <= 8; ++i12) {
            int[] k15 = new int[]{3, 4, 10, 11};
            j12 = k15.length;
            for (k122 = 0; k122 < j12; ++k122) {
                k142 = k15[k122];
                this.setBlockAndMetadata(world, i12, 1, k142, this.plankBlock, this.plankMeta);
                this.placeMugOrPlate(world, random, i12, 2, k142);
            }
        }
        for (int j15 = 1; j15 <= 4; ++j15) {
            this.setBlockAndMetadata(world, -2, j15, 5, this.woodBeamBlock, this.woodBeamMeta);
            this.setBlockAndMetadata(world, -2, j15, 9, this.woodBeamBlock, this.woodBeamMeta);
        }
        for (i12 = -6; i12 <= -3; ++i12) {
            this.setBlockAndMetadata(world, i12, 1, 5, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i12, 3, 5, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i12, 4, 5, this.woodBeamBlock, this.woodBeamMeta | 4);
            this.setBlockAndMetadata(world, i12, 1, 9, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, i12, 3, 9, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i12, 4, 9, this.woodBeamBlock, this.woodBeamMeta | 4);
        }
        for (k1 = 6; k1 <= 8; ++k1) {
            this.setBlockAndMetadata(world, -2, 1, k1, this.plankBlock, this.plankMeta);
            this.setBlockAndMetadata(world, -2, 3, k1, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, -2, 4, k1, this.woodBeamBlock, this.woodBeamMeta | 8);
        }
        this.setBlockAndMetadata(world, -4, 1, 5, this.fenceGateBlock, 0);
        this.placeBarrel(world, random, -6, 2, 5, 3, LOTRFoods.GONDOR_DRINK);
        this.placeMug(world, random, -5, 2, 5, 2, LOTRFoods.GONDOR_DRINK);
        this.placeMug(world, random, -3, 2, 5, 2, LOTRFoods.GONDOR_DRINK);
        this.setBlockAndMetadata(world, -4, 1, 9, this.fenceGateBlock, 2);
        this.placeBarrel(world, random, -6, 2, 9, 2, LOTRFoods.GONDOR_DRINK);
        this.placeMug(world, random, -5, 2, 9, 0, LOTRFoods.GONDOR_DRINK);
        this.placeMug(world, random, -3, 2, 9, 0, LOTRFoods.GONDOR_DRINK);
        this.placeBarrel(world, random, -2, 2, 8, 5, LOTRFoods.GONDOR_DRINK);
        this.placeMug(world, random, -2, 2, 6, 1, LOTRFoods.GONDOR_DRINK);
        this.setBlockAndMetadata(world, -6, 1, 6, this.plankStairBlock, 4);
        this.placePlateWithCertainty(world, random, -6, 2, 6, this.plateBlock, LOTRFoods.GONDOR);
        this.setBlockAndMetadata(world, -6, 1, 7, Blocks.furnace, 4);
        this.setBlockAndMetadata(world, -6, 1, 8, (Block)Blocks.cauldron, 3);
        this.placeChest(world, random, -3, 0, 8, 5, LOTRChestContents.GONDOR_HOUSE);
        for (k1 = 6; k1 <= 8; ++k1) {
            this.setBlockAndMetadata(world, -6, 3, k1, this.plankStairBlock, 4);
            this.placeBarrel(world, random, -6, 4, k1, 4, LOTRFoods.GONDOR_DRINK);
        }
        this.setBlockAndMetadata(world, -4, 4, 7, LOTRMod.chandelier, 1);
        for (step = 0; step <= 2; ++step) {
            this.setBlockAndMetadata(world, -3 - step, 1 + step, 13, this.plankStairBlock, 0);
            this.setBlockAndMetadata(world, -4 - step, 1 + step, 13, this.plankStairBlock, 5);
        }
        this.setBlockAndMetadata(world, -6, 3, 13, this.plankBlock, this.plankMeta);
        for (step = 0; step <= 1; ++step) {
            this.setBlockAndMetadata(world, -6, 4 + step, 12 - step, this.plankStairBlock, 3);
            this.setBlockAndMetadata(world, -6, 3 + step, 12 - step, this.plankStairBlock, 6);
        }
        for (i12 = -6; i12 <= -4; ++i12) {
            this.setAir(world, i12, 5, 13);
        }
        this.setAir(world, -6, 5, 12);
        for (i12 = -5; i12 <= -3; ++i12) {
            this.setBlockAndMetadata(world, i12, 6, 14, this.fenceBlock, this.fenceMeta);
            this.setBlockAndMetadata(world, i12, 6, 12, this.fenceBlock, this.fenceMeta);
        }
        this.setBlockAndMetadata(world, -3, 6, 13, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -7, 6, 12, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -7, 6, 11, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -5, 6, 11, this.fenceBlock, this.fenceMeta);
        this.setBlockAndMetadata(world, -5, 7, 12, Blocks.torch, 5);
        for (i12 = -7; i12 <= -3; ++i12) {
            for (k13 = 10; k13 <= 14; ++k13) {
                if (i12 == -3 && k13 == 10) continue;
                if ((i12 >= -4 || k13 <= 11) && k13 <= 13) {
                    this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
                }
                if (i12 < -5 && k13 > 12) continue;
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, 4, 7, 6, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 4, 7, 8, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 3, 7, 7, Blocks.iron_bars, 0);
        this.setBlockAndMetadata(world, 5, 7, 7, Blocks.iron_bars, 0);
        this.spawnItemFrame(world, 3, 10, 7, 3, this.getGondorFramedItem(random));
        for (i12 = -2; i12 <= 1; ++i12) {
            for (k13 = 5; k13 <= 9; ++k13) {
                this.setBlockAndMetadata(world, i12, 6, k13, Blocks.carpet, 12);
            }
        }
        for (i12 = -2; i12 <= 6; ++i12) {
            int k16;
            int i2 = IntMath.mod((int)i12, (int)4);
            if (i2 == 2) {
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, 3, this.woodBeamBlock, this.woodBeamMeta);
                    for (k122 = 0; k122 <= 2; ++k122) {
                        this.setBlockAndMetadata(world, i12, j12, k122, this.wallBlock, this.wallMeta);
                    }
                    this.setBlockAndMetadata(world, i12, j12, 11, this.woodBeamBlock, this.woodBeamMeta);
                    for (k122 = 12; k122 <= 14; ++k122) {
                        this.setBlockAndMetadata(world, i12, j12, k122, this.wallBlock, this.wallMeta);
                    }
                }
                for (k16 = 0; k16 <= 3; ++k16) {
                    this.setBlockAndMetadata(world, i12, 9, k16, this.woodBeamBlock, this.woodBeamMeta | 8);
                }
                for (k16 = 11; k16 <= 14; ++k16) {
                    this.setBlockAndMetadata(world, i12, 9, k16, this.woodBeamBlock, this.woodBeamMeta | 8);
                }
            } else {
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, 3, this.wallBlock, this.wallMeta);
                    this.setBlockAndMetadata(world, i12, j12, 11, this.wallBlock, this.wallMeta);
                }
                this.setBlockAndMetadata(world, i12, 9, 3, this.woodBeamBlock, this.woodBeamMeta | 4);
                this.setBlockAndMetadata(world, i12, 9, 11, this.woodBeamBlock, this.woodBeamMeta | 4);
                for (k16 = 0; k16 <= 2; ++k16) {
                    this.setBlockAndMetadata(world, i12, 9, k16, this.roofSlabBlock, this.roofSlabMeta | 8);
                }
                for (k16 = 12; k16 <= 14; ++k16) {
                    this.setBlockAndMetadata(world, i12, 9, k16, this.roofSlabBlock, this.roofSlabMeta | 8);
                }
            }
            if (i2 == 0) {
                this.setBlockAndMetadata(world, i12, 6, 3, this.doorBlock, 3);
                this.setBlockAndMetadata(world, i12, 7, 3, this.doorBlock, 8);
                this.setBlockAndMetadata(world, i12, 8, 2, Blocks.torch, 4);
                this.setBlockAndMetadata(world, i12, 6, 11, this.doorBlock, 1);
                this.setBlockAndMetadata(world, i12, 7, 11, this.doorBlock, 8);
                this.setBlockAndMetadata(world, i12, 8, 12, Blocks.torch, 3);
            }
            if (i2 == 3) {
                this.setBlockAndMetadata(world, i12, 6, 1, this.bedBlock, 0);
                this.setBlockAndMetadata(world, i12, 6, 2, this.bedBlock, 8);
                this.setBlockAndMetadata(world, i12, 6, 0, (Block)Blocks.chest, 4);
                this.setBlockAndMetadata(world, i12, 6, 13, this.bedBlock, 2);
                this.setBlockAndMetadata(world, i12, 6, 12, this.bedBlock, 10);
                this.setBlockAndMetadata(world, i12, 6, 14, (Block)Blocks.chest, 4);
            }
            if (i2 == 1) {
                this.setBlockAndMetadata(world, i12, 6, 2, this.plankStairBlock, 2);
                this.setBlockAndMetadata(world, i12, 6, 0, this.plankBlock, this.plankMeta);
                this.placeMug(world, random, i12, 7, 0, 2, LOTRFoods.GONDOR_DRINK);
                this.setBlockAndMetadata(world, i12, 6, 12, this.plankStairBlock, 3);
                this.setBlockAndMetadata(world, i12, 6, 14, this.plankBlock, this.plankMeta);
                this.placeMug(world, random, i12, 7, 14, 0, LOTRFoods.GONDOR_DRINK);
            }
            for (k16 = 1; k16 <= 3; ++k16) {
                this.setBlockAndMetadata(world, i12, 10, k16, this.wallBlock, this.wallMeta);
            }
            for (k16 = 11; k16 <= 13; ++k16) {
                this.setBlockAndMetadata(world, i12, 10, k16, this.wallBlock, this.wallMeta);
            }
        }
        for (k1 = 5; k1 <= 9; ++k1) {
            int k2 = IntMath.mod((int)k1, (int)4);
            if (k2 == 1) {
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, -4, j12, k1, this.woodBeamBlock, this.woodBeamMeta);
                    for (i132 = -7; i132 <= -5; ++i132) {
                        this.setBlockAndMetadata(world, i132, j12, k1, this.wallBlock, this.wallMeta);
                    }
                    this.setBlockAndMetadata(world, 8, j12, k1, this.woodBeamBlock, this.woodBeamMeta);
                    for (i132 = 9; i132 <= 11; ++i132) {
                        this.setBlockAndMetadata(world, i132, j12, k1, this.wallBlock, this.wallMeta);
                    }
                }
                for (i1 = -7; i1 <= -4; ++i1) {
                    this.setBlockAndMetadata(world, i1, 9, k1, this.woodBeamBlock, this.woodBeamMeta | 4);
                }
                for (i1 = 8; i1 <= 11; ++i1) {
                    this.setBlockAndMetadata(world, i1, 9, k1, this.woodBeamBlock, this.woodBeamMeta | 4);
                }
            } else {
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, -4, j12, k1, this.wallBlock, this.wallMeta);
                    this.setBlockAndMetadata(world, 8, j12, k1, this.wallBlock, this.wallMeta);
                }
                this.setBlockAndMetadata(world, -4, 9, k1, this.woodBeamBlock, this.woodBeamMeta | 8);
                this.setBlockAndMetadata(world, 8, 9, k1, this.woodBeamBlock, this.woodBeamMeta | 8);
                for (i1 = -7; i1 <= -5; ++i1) {
                    this.setBlockAndMetadata(world, i1, 9, k1, this.roofSlabBlock, this.roofSlabMeta | 8);
                }
                for (i1 = 9; i1 <= 11; ++i1) {
                    this.setBlockAndMetadata(world, i1, 9, k1, this.roofSlabBlock, this.roofSlabMeta | 8);
                }
            }
            if (k2 == 3) {
                this.setBlockAndMetadata(world, -4, 6, k1, this.doorBlock, 0);
                this.setBlockAndMetadata(world, -4, 7, k1, this.doorBlock, 8);
                this.setBlockAndMetadata(world, -5, 8, k1, Blocks.torch, 1);
                this.setBlockAndMetadata(world, 8, 6, k1, this.doorBlock, 2);
                this.setBlockAndMetadata(world, 8, 7, k1, this.doorBlock, 8);
                this.setBlockAndMetadata(world, 9, 8, k1, Blocks.torch, 2);
            }
            if (k2 == 0) {
                this.setBlockAndMetadata(world, -6, 6, k1, this.bedBlock, 1);
                this.setBlockAndMetadata(world, -5, 6, k1, this.bedBlock, 9);
                this.setBlockAndMetadata(world, -7, 6, k1, (Block)Blocks.chest, 2);
                this.setBlockAndMetadata(world, 10, 6, k1, this.bedBlock, 3);
                this.setBlockAndMetadata(world, 9, 6, k1, this.bedBlock, 11);
                this.setBlockAndMetadata(world, 11, 6, k1, (Block)Blocks.chest, 2);
            }
            if (k2 == 2) {
                this.setBlockAndMetadata(world, -5, 6, k1, this.plankStairBlock, 1);
                this.setBlockAndMetadata(world, -7, 6, k1, this.plankBlock, this.plankMeta);
                this.placeMug(world, random, -7, 7, k1, 3, LOTRFoods.GONDOR_DRINK);
                this.setBlockAndMetadata(world, 9, 6, k1, this.plankStairBlock, 0);
                this.setBlockAndMetadata(world, 11, 6, k1, this.plankBlock, this.plankMeta);
                this.placeMug(world, random, 11, 7, k1, 1, LOTRFoods.GONDOR_DRINK);
            }
            for (i1 = -7; i1 <= -4; ++i1) {
                this.setBlockAndMetadata(world, i1, 10, k1, this.wallBlock, this.wallMeta);
                this.setBlockAndMetadata(world, i1, 11, k1, this.wallBlock, this.wallMeta);
            }
            for (i1 = 8; i1 <= 11; ++i1) {
                this.setBlockAndMetadata(world, i1, 10, k1, this.wallBlock, this.wallMeta);
                this.setBlockAndMetadata(world, i1, 11, k1, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = 7; i12 <= 8; ++i12) {
            for (k13 = 10; k13 <= 11; ++k13) {
                if (i12 == 7 && k13 == 10) continue;
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, k13, this.wallBlock, this.wallMeta);
                }
                this.setBlockAndMetadata(world, i12, 9, k13, this.woodBeamBlock, this.woodBeamMeta | (i12 == 8 ? 8 : 4));
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        this.setBlockAndMetadata(world, 8, 6, 10, this.doorBlock, 2);
        this.setBlockAndMetadata(world, 8, 7, 10, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 9, 8, 10, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 7, 8, 13, Blocks.torch, 2);
        for (i12 = 7; i12 <= 8; ++i12) {
            for (k13 = 12; k13 <= 13; ++k13) {
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = 9; i12 <= 11; ++i12) {
            for (k13 = 10; k13 <= 11; ++k13) {
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = 7; i12 <= 9; ++i12) {
            for (k13 = 12; k13 <= 14; ++k13) {
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        for (i12 = 9; i12 <= 11; ++i12) {
            for (k13 = 10; k13 <= 12; ++k13) {
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, 11, 6, 11, this.bedBlock, 0);
        this.setBlockAndMetadata(world, 11, 6, 12, this.bedBlock, 8);
        this.setBlockAndMetadata(world, 11, 6, 10, (Block)Blocks.chest, 5);
        this.setBlockAndMetadata(world, 7, 6, 13, this.bedBlock, 2);
        this.setBlockAndMetadata(world, 7, 6, 12, this.bedBlock, 10);
        this.setBlockAndMetadata(world, 7, 6, 14, (Block)Blocks.chest, 4);
        this.setBlockAndMetadata(world, 9, 6, 14, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 9, 7, 14, 0, LOTRFoods.GONDOR_DRINK);
        this.setBlockAndMetadata(world, 10, 6, 13, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 10, 7, 13, 1, LOTRFoods.GONDOR_DRINK);
        for (i12 = 7; i12 <= 8; ++i12) {
            for (k13 = 3; k13 <= 4; ++k13) {
                if (i12 == 7 && k13 == 4) continue;
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, k13, this.wallBlock, this.wallMeta);
                }
                this.setBlockAndMetadata(world, i12, 9, k13, this.woodBeamBlock, this.woodBeamMeta | (i12 == 8 ? 8 : 4));
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        this.setBlockAndMetadata(world, 8, 6, 4, this.doorBlock, 2);
        this.setBlockAndMetadata(world, 8, 7, 4, this.doorBlock, 8);
        this.setBlockAndMetadata(world, 9, 8, 4, Blocks.torch, 2);
        this.setBlockAndMetadata(world, 7, 8, 1, Blocks.torch, 2);
        for (i12 = 7; i12 <= 8; ++i12) {
            for (k13 = 1; k13 <= 2; ++k13) {
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = 9; i12 <= 11; ++i12) {
            for (k13 = 3; k13 <= 4; ++k13) {
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = 7; i12 <= 9; ++i12) {
            for (k13 = 0; k13 <= 2; ++k13) {
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        for (i12 = 9; i12 <= 11; ++i12) {
            for (k13 = 2; k13 <= 4; ++k13) {
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, 11, 6, 3, this.bedBlock, 2);
        this.setBlockAndMetadata(world, 11, 6, 2, this.bedBlock, 10);
        this.setBlockAndMetadata(world, 11, 6, 4, (Block)Blocks.chest, 5);
        this.setBlockAndMetadata(world, 7, 6, 1, this.bedBlock, 0);
        this.setBlockAndMetadata(world, 7, 6, 2, this.bedBlock, 8);
        this.setBlockAndMetadata(world, 7, 6, 0, (Block)Blocks.chest, 4);
        this.setBlockAndMetadata(world, 9, 6, 0, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 9, 7, 0, 2, LOTRFoods.GONDOR_DRINK);
        this.setBlockAndMetadata(world, 10, 6, 1, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, 10, 7, 1, 1, LOTRFoods.GONDOR_DRINK);
        for (i12 = -4; i12 <= -3; ++i12) {
            for (k13 = 3; k13 <= 4; ++k13) {
                if (i12 == -3 && k13 == 4) continue;
                for (j12 = 6; j12 <= 8; ++j12) {
                    this.setBlockAndMetadata(world, i12, j12, k13, this.wallBlock, this.wallMeta);
                }
                this.setBlockAndMetadata(world, i12, 9, k13, this.woodBeamBlock, this.woodBeamMeta | (i12 == -4 ? 8 : 4));
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        this.setBlockAndMetadata(world, -4, 6, 4, this.doorBlock, 0);
        this.setBlockAndMetadata(world, -4, 7, 4, this.doorBlock, 8);
        this.setBlockAndMetadata(world, -5, 8, 4, Blocks.torch, 1);
        this.setBlockAndMetadata(world, -3, 8, 1, Blocks.torch, 1);
        for (i12 = -4; i12 <= -3; ++i12) {
            for (k13 = 1; k13 <= 2; ++k13) {
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = -7; i12 <= -5; ++i12) {
            for (k13 = 3; k13 <= 4; ++k13) {
                this.setBlockAndMetadata(world, i12, 10, k13, this.wallBlock, this.wallMeta);
            }
        }
        for (i12 = -5; i12 <= -3; ++i12) {
            for (k13 = 0; k13 <= 2; ++k13) {
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        for (i12 = -7; i12 <= -5; ++i12) {
            for (k13 = 2; k13 <= 4; ++k13) {
                this.setBlockAndMetadata(world, i12, 9, k13, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, -7, 6, 3, this.bedBlock, 2);
        this.setBlockAndMetadata(world, -7, 6, 2, this.bedBlock, 10);
        this.setBlockAndMetadata(world, -7, 6, 4, (Block)Blocks.chest, 4);
        this.setBlockAndMetadata(world, -3, 6, 1, this.bedBlock, 0);
        this.setBlockAndMetadata(world, -3, 6, 2, this.bedBlock, 8);
        this.setBlockAndMetadata(world, -3, 6, 0, (Block)Blocks.chest, 5);
        this.setBlockAndMetadata(world, -5, 6, 0, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, -5, 7, 0, 2, LOTRFoods.GONDOR_DRINK);
        this.setBlockAndMetadata(world, -6, 6, 1, this.plankBlock, this.plankMeta);
        this.placeMug(world, random, -6, 7, 1, 3, LOTRFoods.GONDOR_DRINK);
        for (i12 = -3; i12 <= 7; ++i12) {
            for (int k142 : new int[]{5, 9}) {
                this.setBlockAndMetadata(world, i12, 11, k142, this.roofSlabBlock, this.roofSlabMeta | 8);
            }
        }
        this.setBlockAndMetadata(world, -1, 11, 7, LOTRMod.chandelier, 1);
        this.setBlockAndMetadata(world, 7, 11, 7, LOTRMod.chandelier, 1);
        LOTREntityGondorBartender bartender = new LOTREntityGondorBartender(world);
        bartender.setSpecificLocationName(this.tavernNameNPC);
        this.spawnNPCAndSetHome(bartender, world, -4, 1, 7, 2);
        int men = 6 + random.nextInt(7);
        for (int l = 0; l < men; ++l) {
            LOTREntityGondorMan gondorian = new LOTREntityGondorMan(world);
            this.spawnNPCAndSetHome(gondorian, world, 2, 1, 7, 16);
        }
        return true;
    }

    private void placeMugOrPlate(World world, Random random, int i, int j, int k) {
        if (random.nextBoolean()) {
            this.placeMug(world, random, i, j, k, random.nextInt(4), LOTRFoods.GONDOR_DRINK);
        } else {
            this.placePlate(world, random, i, j, k, this.plateBlock, LOTRFoods.GONDOR);
        }
    }
}

