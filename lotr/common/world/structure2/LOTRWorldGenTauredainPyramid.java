/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.world.structure2;

import com.google.common.math.IntMath;
import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityTauredainPyramidWraith;
import lotr.common.item.LOTRItemBanner;
import lotr.common.tileentity.LOTRTileEntityDartTrap;
import lotr.common.util.LOTRMazeGenerator;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenTauredainPyramid
extends LOTRWorldGenStructureBase2 {
    public static int RADIUS = 60;
    private boolean isGolden;
    private boolean carson;

    public LOTRWorldGenTauredainPyramid(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int k1;
        int j1;
        int i1;
        int k12;
        int i12;
        int depth = 20;
        this.setOriginAndRotation(world, i, j -= depth - 1, k, rotation, this.usingPlayer != null ? RADIUS - depth : 0);
        this.isGolden = random.nextInt(20) == 0;
        this.carson = random.nextInt(200) == 0;
        int maze1R = 19;
        int maze1W = maze1R * 2 + 1;
        LOTRMazeGenerator maze1 = new LOTRMazeGenerator(maze1W, maze1W);
        maze1.setStart(maze1R + 0, maze1R + 4);
        int maze1CentreW = 3;
        for (int i13 = -maze1CentreW - 1; i13 <= maze1CentreW + 1; ++i13) {
            for (int k13 = -maze1CentreW - 1; k13 <= maze1CentreW + 1; ++k13) {
                int i2 = Math.abs(i13);
                int k2 = Math.abs(k13);
                if (i2 > maze1CentreW || k2 > maze1CentreW) {
                    maze1.exclude(maze1R + i13, maze1R + k13);
                    continue;
                }
                maze1.clear(maze1R + i13, maze1R + k13);
            }
        }
        maze1.generate(random);
        maze1.selectOuterEndpoint(random);
        int[] maze1End = maze1.getEnd();
        int maze2R = 25;
        int maze2W = maze2R * 2 + 1;
        LOTRMazeGenerator maze2 = new LOTRMazeGenerator(maze2W, maze2W);
        maze2.setStart(maze1End[0] + (maze2.xSize - maze1.xSize), maze1End[1] + (maze2.zSize - maze1.zSize));
        maze2.generate(random);
        maze2.selectOuterEndpoint(random);
        int maze3R = 13;
        int maze3W = maze3R * 2 + 1;
        LOTRMazeGenerator maze3 = new LOTRMazeGenerator(maze3W, maze3W);
        maze3.setStart(maze3R + 0, maze3R + 2);
        int maze3CentreW = 1;
        for (int i14 = -maze3CentreW - 1; i14 <= maze3CentreW + 1; ++i14) {
            for (int k14 = -maze3CentreW - 1; k14 <= maze3CentreW + 1; ++k14) {
                int i2 = Math.abs(i14);
                int k2 = Math.abs(k14);
                if (i2 > maze3CentreW || k2 > maze3CentreW) {
                    maze3.exclude(maze3R + i14, maze3R + 4 + k14);
                    continue;
                }
                maze3.clear(maze3R + i14, maze3R + 4 + k14);
            }
        }
        maze3.generate(random);
        maze3.selectOuterEndpoint(random);
        IInventory[] chests = new IInventory[4];
        for (int l = 0; l < chests.length; ++l) {
            chests[l] = new InventoryBasic("drops", false, 27);
            LOTRChestContents itemPool = LOTRChestContents.TAUREDAIN_PYRAMID;
            int amount = LOTRChestContents.getRandomItemAmount(itemPool, random);
            if (this.isGolden) {
                amount *= 3;
            }
            LOTRChestContents.fillInventory(chests[l], random, itemPool, amount);
        }
        if (this.carson) {
            for (IInventory chest : chests) {
                for (int l = 0; l < chest.getSizeInventory(); ++l) {
                    chest.setInventorySlotContents(l, new ItemStack(Items.wheat, 64));
                }
            }
        }
        if (this.restrictions) {
            for (i12 = -RADIUS; i12 <= RADIUS; ++i12) {
                for (k1 = -RADIUS; k1 <= RADIUS; ++k1) {
                    j1 = this.getTopBlock(world, i12, k1);
                    Block block = this.getBlock(world, i12, j1 - 1, k1);
                    if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.stone || block == LOTRMod.mudGrass || block == LOTRMod.mud) continue;
                    return false;
                }
            }
        }
        for (i12 = -RADIUS; i12 <= RADIUS; ++i12) {
            for (k1 = -RADIUS; k1 <= RADIUS; ++k1) {
                j1 = 0;
                while (!(this.getY(j1) < this.originY && this.isOpaque(world, i12, j1, k1) || this.getY(j1) < 0)) {
                    this.placeRandomBrick(world, random, i12, j1, k1);
                    this.setGrassToDirt(world, i12, j1 - 1, k1);
                    --j1;
                }
            }
        }
        int steps = (RADIUS - 10) / 2;
        int topRadius = RADIUS - steps * 2;
        int topHeight = steps * 2;
        for (int step = 0; step < steps; ++step) {
            for (int j12 = step * 2; j12 <= step * 2 + 1; ++j12) {
                int r = RADIUS - step * 2;
                for (int i15 = -r; i15 <= r; ++i15) {
                    for (int k15 = -r; k15 <= r; ++k15) {
                        this.placeRandomBrick(world, random, i15, j12, k15);
                        if (Math.abs(i15) != r - 1 && Math.abs(k15) != r - 1 || random.nextInt(3) != 0) continue;
                        this.placeRandomBrick(world, random, i15, j12 + 1, k15);
                    }
                }
            }
        }
        for (int i16 = -2; i16 <= 2; ++i16) {
            for (int k16 = -2; k16 <= 2; ++k16) {
                this.setBlockAndMetadata(world, i16, topHeight, k16, LOTRMod.brick4, 3);
                for (int j13 = topHeight + 1; j13 <= topHeight + 6; ++j13) {
                    if (Math.abs(i16) == 2 && Math.abs(k16) == 2) {
                        this.setBlockAndMetadata(world, i16, j13, k16, LOTRMod.pillar2, 12);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i16, j13, k16, LOTRMod.brick4, 4);
                }
                this.setBlockAndMetadata(world, i16, topHeight + 7, k16, LOTRMod.brick4, 3);
            }
        }
        for (int i15 : new int[]{-10, 10}) {
            for (int k17 : new int[]{-10, 10}) {
                this.setBlockAndMetadata(world, i15, topHeight, k17, LOTRMod.brick4, 3);
                for (int j14 = topHeight + 1; j14 <= topHeight + 3; ++j14) {
                    this.setBlockAndMetadata(world, i15, j14, k17, LOTRMod.pillar2, 12);
                }
                this.setBlockAndMetadata(world, i15, topHeight + 4, k17, LOTRMod.brick4, 3);
            }
        }
        this.generateMaze(world, random, 0, topHeight - 13, 0, maze1, 5, 1, false);
        int stepX = 0;
        int stepY = topHeight - 1;
        int stepZ = 3;
        do {
            if (stepY < topHeight - 13) break;
            int newX = stepX;
            int newY = stepY;
            int newZ = stepZ;
            if (stepX == -3 && stepZ == -3) {
                this.placeRandomBrick(world, random, stepX, stepY, stepZ);
                ++newZ;
            } else if (stepX == -3 && stepZ == 3) {
                this.placeRandomBrick(world, random, stepX, stepY, stepZ);
                ++newX;
            } else if (stepX == 3 && stepZ == 3) {
                this.placeRandomBrick(world, random, stepX, stepY, stepZ);
                --newZ;
            } else if (stepX == 3 && stepZ == -3) {
                this.placeRandomBrick(world, random, stepX, stepY, stepZ);
                --newX;
            } else if (stepZ == -3) {
                this.placeRandomStairs(world, random, stepX, stepY, stepZ, 1);
                --newX;
                --newY;
            } else if (stepZ == 3) {
                this.placeRandomStairs(world, random, stepX, stepY, stepZ, 0);
                ++newX;
                --newY;
            } else if (stepX == -3) {
                this.placeRandomStairs(world, random, stepX, stepY, stepZ, 3);
                ++newZ;
                --newY;
            } else if (stepX == 3) {
                this.placeRandomStairs(world, random, stepX, stepY, stepZ, 2);
                --newZ;
                --newY;
            }
            for (int j15 = 1; j15 <= 3; ++j15) {
                this.setAir(world, stepX, stepY + j15, stepZ);
            }
            stepX = newX;
            stepY = newY;
            stepZ = newZ;
        } while (true);
        this.setAir(world, stepX, stepY + 3, stepZ);
        for (int j16 = topHeight - 18 + 2; j16 < topHeight - 13; ++j16) {
            this.setAir(world, maze1End[0] - (maze1.xSize - 1) / 2, j16, maze1End[1] - (maze1.zSize - 1) / 2);
        }
        this.generateMaze(world, random, 0, topHeight - 18, 0, maze2, 2, 1, false);
        int[] maze2End = maze2.getEnd();
        for (int j17 = topHeight - 22; j17 < topHeight - 18; ++j17) {
            this.setAir(world, maze2End[0] - (maze2.xSize - 1) / 2, j17, maze2End[1] - (maze2.zSize - 1) / 2);
        }
        int chamberRMin = 22;
        int chamberRMax = 26;
        for (int i17 = -chamberRMax - 1; i17 <= chamberRMax + 1; ++i17) {
            for (int k17 = -chamberRMax - 1; k17 <= chamberRMax + 1; ++k17) {
                int i2 = Math.abs(i17);
                int k2 = Math.abs(k17);
                if (i2 == chamberRMax + 1 || k2 == chamberRMax + 1) {
                    this.setBlockAndMetadata(world, i17, topHeight - 25, k17, LOTRMod.brick4, 4);
                    this.setBlockAndMetadata(world, i17, topHeight - 24, k17, LOTRMod.brick4, 3);
                }
                if (i2 > chamberRMax || k2 > chamberRMax || i2 < chamberRMin && k2 < chamberRMin) continue;
                for (int j18 = topHeight - 26; j18 < topHeight - 22; ++j18) {
                    this.setAir(world, i17, j18, k17);
                }
                if (i2 == chamberRMax && k2 == chamberRMax) {
                    this.setBlockAndMetadata(world, i17, topHeight - 26, k17, LOTRMod.hearth, 0);
                    this.setBlockAndMetadata(world, i17, topHeight - 25, k17, (Block)Blocks.fire, 0);
                } else if (i2 >= chamberRMax - 1 && k2 >= chamberRMax - 1) {
                    this.setBlockAndMetadata(world, i17, topHeight - 26, k17, LOTRMod.brick4, 3);
                } else if (i2 >= chamberRMax - 2 && k2 >= chamberRMax - 2) {
                    this.setBlockAndMetadata(world, i17, topHeight - 26, k17, LOTRMod.slabSingle8, 4);
                }
                if ((i2 != chamberRMax || k2 % 6 != 0 || k2 >= chamberRMax - 4) && (k2 != chamberRMax || i2 % 6 != 0 || i2 >= chamberRMax - 4)) continue;
                Block pillarBlock = LOTRMod.pillar;
                int pillarMeta = 14;
                if (this.isGolden) {
                    pillarBlock = LOTRMod.pillar2;
                    pillarMeta = 11;
                }
                for (int j19 = topHeight - 26; j19 < topHeight - 22; ++j19) {
                    this.setBlockAndMetadata(world, i17, j19, k17, pillarBlock, pillarMeta);
                }
            }
        }
        this.generateMaze(world, random, 0, topHeight - 35, 0, maze3, 4, 3, true);
        int[] maze3End = maze3.getEnd();
        int maze3EndX = maze3End[0] - (maze3.xSize - 1) / 2;
        int maze3EndZ = maze3End[1] - (maze3.zSize - 1) / 2;
        maze3EndX *= 3;
        maze3EndZ *= 3;
        for (int step = 0; step <= 9; ++step) {
            for (int i18 = -1; i18 <= 1; ++i18) {
                int j2;
                int j110 = topHeight - 36 + step;
                int k18 = 13 + step;
                if (step > 0) {
                    this.placeRandomStairs(world, random, i18, j110, k18, 2);
                }
                for (j2 = 1; j2 <= 4; ++j2) {
                    this.setAir(world, i18, j110 + j2, k18);
                }
                if (step < 9) {
                    this.placeRandomStairs(world, random, i18, j110 + 5, k18, 7);
                }
                if (step > 3) continue;
                for (j2 = 1; j2 < step; ++j2) {
                    this.placeRandomBrick(world, random, i18, j110 - step + j2, k18);
                }
            }
        }
        int roomBottom = topHeight - 49;
        int roomFloor = topHeight - 47;
        int roomTop = topHeight - 36;
        int roomPillarEdge = 32;
        for (i1 = -37; i1 <= 37; ++i1) {
            for (k12 = -37; k12 <= 37; ++k12) {
                int j111;
                int j112;
                int i2 = Math.abs(i1);
                int k2 = Math.abs(k12);
                int actingRoomTop = roomTop;
                if (i2 != roomPillarEdge && k2 != roomPillarEdge) {
                    actingRoomTop -= random.nextInt(2);
                }
                for (j111 = roomFloor + 1; j111 < actingRoomTop; ++j111) {
                    this.setAir(world, i1, j111, k12);
                }
                if (i2 > roomPillarEdge || k2 > roomPillarEdge) {
                    for (j111 = roomBottom + 1; j111 <= roomFloor + 1; ++j111) {
                        this.placeRandomBrick(world, random, i1, j111, k12);
                    }
                    continue;
                }
                if (i2 == roomPillarEdge || k2 == roomPillarEdge) {
                    for (j111 = roomBottom + 1; j111 <= roomFloor + 1; ++j111) {
                        this.setBlockAndMetadata(world, i1, j111, k12, LOTRMod.brick4, 4);
                    }
                    this.placeRandomBrick(world, random, i1, actingRoomTop - 1, k12);
                    if (this.isGolden) {
                        this.setBlockAndMetadata(world, i1, actingRoomTop - 2, k12, LOTRMod.pillar2, 11);
                    } else {
                        this.setBlockAndMetadata(world, i1, actingRoomTop - 2, k12, LOTRMod.pillar, 14);
                    }
                    int i3 = IntMath.mod((int)i1, (int)4);
                    int k3 = IntMath.mod((int)k12, (int)4);
                    if (i2 == roomPillarEdge && k3 == 0 || k2 == roomPillarEdge && i3 == 0) {
                        for (j112 = roomFloor + 2; j112 <= actingRoomTop - 2; ++j112) {
                            if (this.isGolden) {
                                this.setBlockAndMetadata(world, i1, j112, k12, LOTRMod.pillar2, 11);
                                continue;
                            }
                            this.setBlockAndMetadata(world, i1, j112, k12, LOTRMod.pillar, 14);
                        }
                    }
                    if (i2 == roomPillarEdge) {
                        if (k3 == 1) {
                            this.placeRandomStairs(world, random, i1, actingRoomTop - 3, k12, 7);
                            continue;
                        }
                        if (k3 != 3) continue;
                        this.placeRandomStairs(world, random, i1, actingRoomTop - 3, k12, 6);
                        continue;
                    }
                    if (k2 != roomPillarEdge) continue;
                    if (i3 == 1) {
                        this.placeRandomStairs(world, random, i1, actingRoomTop - 3, k12, 4);
                        continue;
                    }
                    if (i3 != 3) continue;
                    this.placeRandomStairs(world, random, i1, actingRoomTop - 3, k12, 5);
                    continue;
                }
                if (i2 <= 10 && k2 <= 10) {
                    int max = Math.max(i2, k2);
                    int height = (10 - Math.max(max, 3)) / 2;
                    for (j112 = roomBottom + 1; j112 <= roomFloor; ++j112) {
                        this.placeRandomBrick(world, random, i1, j112, k12);
                    }
                    int lvlMin = roomFloor + 1;
                    int lvlMax = lvlMin + height;
                    for (int j113 = lvlMin; j113 <= lvlMax; ++j113) {
                        this.placeRandomBrick(world, random, i1, j113, k12);
                    }
                    if (max > 3 && max % 2 == 0) {
                        this.setBlockAndMetadata(world, i1, lvlMax, k12, LOTRMod.brick4, 4);
                        if (i2 == k2) {
                            this.setBlockAndMetadata(world, i1, lvlMax, k12, LOTRMod.pillar2, 11);
                            this.setBlockAndMetadata(world, i1, lvlMax + 1, k12, LOTRMod.pillar2, 11);
                            this.setBlockAndMetadata(world, i1, lvlMax + 2, k12, LOTRMod.tauredainDoubleTorch, 0);
                            this.setBlockAndMetadata(world, i1, lvlMax + 3, k12, LOTRMod.tauredainDoubleTorch, 1);
                        }
                    }
                    if (max <= 3 || i2 > 1 && k2 > 1) continue;
                    if (max % 2 == 0) {
                        this.setBlockAndMetadata(world, i1, lvlMax, k12, LOTRMod.slabSingle8, 3);
                        continue;
                    }
                    this.setBlockAndMetadata(world, i1, lvlMax, k12, LOTRMod.brick4, 3);
                    continue;
                }
                for (j111 = roomBottom + 1; j111 <= roomFloor; ++j111) {
                    this.setBlockAndMetadata(world, i1, j111, k12, Blocks.lava, 0);
                }
                if (random.nextInt(300) == 0) {
                    this.setBlockAndMetadata(world, i1, actingRoomTop, k12, (Block)Blocks.flowing_lava, 0);
                }
                if (i2 == roomPillarEdge - 1 || k2 == roomPillarEdge - 1) {
                    if (random.nextInt(4) <= 0) continue;
                    this.setBlockAndMetadata(world, i1, roomFloor, k12, Blocks.obsidian, 0);
                    continue;
                }
                if (i2 == roomPillarEdge - 2 || k2 == roomPillarEdge - 2) {
                    if (random.nextInt(2) != 0) continue;
                    this.setBlockAndMetadata(world, i1, roomFloor, k12, Blocks.obsidian, 0);
                    continue;
                }
                if (i2 == roomPillarEdge - 3 || k2 == roomPillarEdge - 3) {
                    if (random.nextInt(4) != 0) continue;
                    this.setBlockAndMetadata(world, i1, roomFloor, k12, Blocks.obsidian, 0);
                    continue;
                }
                if (random.nextInt(16) == 0) {
                    this.placeRandomBrick(world, random, i1, roomFloor, k12);
                }
                if (random.nextInt(200) != 0) continue;
                Block pillarBlock = LOTRMod.pillar;
                int pillarMeta = 14;
                if (this.isGolden) {
                    pillarBlock = LOTRMod.pillar2;
                    pillarMeta = 11;
                }
                if (random.nextBoolean()) {
                    pillarBlock = LOTRMod.pillar2;
                    pillarMeta = 12;
                }
                for (j112 = roomBottom + 1; j112 < actingRoomTop; ++j112) {
                    this.setBlockAndMetadata(world, i1, j112, k12, pillarBlock, pillarMeta);
                }
            }
        }
        this.placePyramidBanner(world, 0, roomFloor + 6, 0);
        this.placeSpawnerChest(world, random, -1, roomFloor + 5, 0, LOTRMod.spawnerChestStone, 5, LOTREntityTauredainPyramidWraith.class, null);
        this.putInventoryInChest(world, -1, roomFloor + 5, 0, chests[0]);
        this.placeSpawnerChest(world, random, 1, roomFloor + 5, 0, LOTRMod.spawnerChestStone, 4, LOTREntityTauredainPyramidWraith.class, null);
        this.putInventoryInChest(world, 1, roomFloor + 5, 0, chests[1]);
        this.placeSpawnerChest(world, random, 0, roomFloor + 5, -1, LOTRMod.spawnerChestStone, 2, LOTREntityTauredainPyramidWraith.class, null);
        this.putInventoryInChest(world, 0, roomFloor + 5, -1, chests[2]);
        this.placeSpawnerChest(world, random, 0, roomFloor + 5, 1, LOTRMod.spawnerChestStone, 3, LOTREntityTauredainPyramidWraith.class, null);
        this.putInventoryInChest(world, 0, roomFloor + 5, 1, chests[3]);
        stepX = 1;
        stepY = topHeight - 36;
        stepZ = 0;
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k12 = -1; k12 <= 1; ++k12) {
                this.setAir(world, maze3EndX + i1, stepY, maze3EndZ + k12);
                this.setAir(world, maze3EndX + i1, stepY - 1, maze3EndZ + k12);
            }
        }
        this.placeRandomBrick(world, random, maze3EndX + 1, stepY, maze3EndZ + 1);
        while (stepY > roomFloor + 1) {
            int newX = stepX;
            int newY = stepY;
            int newZ = stepZ;
            int stepPlaceX = stepX + maze3EndX;
            int stepPlaceZ = stepZ + maze3EndZ;
            if (stepX == -1 && stepZ == -1) {
                this.placeRandomBrick(world, random, stepPlaceX, stepY, stepPlaceZ);
            } else if (stepX == -1 && stepZ == 1) {
                this.placeRandomBrick(world, random, stepPlaceX, stepY, stepPlaceZ);
            } else if (stepX == 1 && stepZ == 1) {
                this.placeRandomBrick(world, random, stepPlaceX, stepY, stepPlaceZ);
                --newZ;
            } else if (stepX == 1 && stepZ == -1) {
                this.placeRandomBrick(world, random, stepPlaceX, stepY, stepPlaceZ);
                --newX;
            } else if (stepZ == -1) {
                this.placeRandomStairs(world, random, stepPlaceX, stepY, stepPlaceZ, 1);
                this.placeRandomStairs(world, random, stepPlaceX, stepY - 1, stepPlaceZ, 4);
                --newX;
            } else if (stepZ == 1) {
                this.placeRandomStairs(world, random, stepPlaceX, stepY, stepPlaceZ, 0);
                this.placeRandomStairs(world, random, stepPlaceX, stepY - 1, stepPlaceZ, 5);
                ++newX;
                --newY;
            } else if (stepX == -1) {
                this.placeRandomStairs(world, random, stepPlaceX, stepY, stepPlaceZ, 3);
                this.placeRandomStairs(world, random, stepPlaceX, stepY - 1, stepPlaceZ, 6);
                ++newZ;
                --newY;
            } else if (stepX == 1) {
                this.placeRandomStairs(world, random, stepPlaceX, stepY, stepPlaceZ, 2);
                this.placeRandomStairs(world, random, stepPlaceX, stepY - 1, stepPlaceZ, 7);
                --newZ;
                --newY;
            }
            stepX = ++newX;
            stepY = --newY;
            stepZ = ++newZ;
        }
        for (int j114 = roomFloor + 1; j114 <= topHeight - 32; ++j114) {
            this.setBlockAndMetadata(world, maze3EndX, j114, maze3EndZ, LOTRMod.pillar2, 12);
        }
        this.setBlockAndMetadata(world, maze3EndX + 1, topHeight - 33, maze3EndZ, Blocks.torch, 2);
        this.setBlockAndMetadata(world, maze3EndX - 1, topHeight - 33, maze3EndZ, Blocks.torch, 1);
        this.setBlockAndMetadata(world, maze3EndX, topHeight - 33, maze3EndZ + 1, Blocks.torch, 3);
        this.setBlockAndMetadata(world, maze3EndX, topHeight - 33, maze3EndZ - 1, Blocks.torch, 4);
        return true;
    }

    private void placeRandomBrick(World world, Random random, int i, int j, int k) {
        if (this.isGolden) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 3);
            return;
        }
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 1);
            } else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 2);
            }
        } else {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 0);
        }
    }

    private void placeRandomWall(World world, Random random, int i, int j, int k) {
        if (this.isGolden) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.wall4, 3);
            return;
        }
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.wall4, 1);
            } else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.wall4, 2);
            }
        } else {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.wall4, 0);
        }
    }

    private void placeRandomStairs(World world, Random random, int i, int j, int k, int meta) {
        if (this.isGolden) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsTauredainBrickGold, meta);
            return;
        }
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsTauredainBrickMossy, meta);
            } else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsTauredainBrickCracked, meta);
            }
        } else {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.stairsTauredainBrick, meta);
        }
    }

    private void placePyramidBanner(World world, int i, int j, int k) {
        this.setBlockAndMetadata(world, i, j - 1, k, Blocks.gold_block, 0);
        for (int j1 = 0; j1 <= 3; ++j1) {
            this.setAir(world, i, j + j1, k);
        }
        this.placeBanner(world, i, j, k, LOTRItemBanner.BannerType.TAUREDAIN, 0, true, 64);
    }

    private void generateMaze(World world, Random random, int i, int j, int k, LOTRMazeGenerator maze, int height, int scale, boolean traps) {
        int xr = (maze.xSize - 1) / 2;
        int zr = (maze.zSize - 1) / 2;
        i -= xr;
        k -= zr;
        int scaleR = (scale - 1) / 2;
        for (int pass = 0; pass <= 1; ++pass) {
            for (int i1 = 0; i1 < maze.xSize; ++i1) {
                for (int k1 = 0; k1 < maze.zSize; ++k1) {
                    boolean path;
                    if (pass == 0 && (path = maze.isPath(i1, k1))) {
                        for (int i2 = 0; i2 < scale; ++i2) {
                            for (int k2 = 0; k2 < scale; ++k2) {
                                for (int j1 = 0; j1 < height; ++j1) {
                                    this.setAir(world, (i + i1) * scale - scaleR + i2, j + j1, (k + k1) * scale - scaleR + k2);
                                }
                            }
                        }
                    }
                    if (pass != 1) continue;
                    if (maze.isDeadEnd(i1, k1) && random.nextInt(3) == 0) {
                        this.setBlockAndMetadata(world, (i + i1) * scale - scaleR, j + 1, (k + k1) * scale - scaleR, Blocks.torch, 0);
                    }
                    if (!traps || maze.isPath(i1, k1) || random.nextInt(4) != 0) continue;
                    ArrayList<ForgeDirection> validDirs = new ArrayList<ForgeDirection>();
                    if (i1 - 1 >= 0 && maze.isPath(i1 - 1, k1)) {
                        validDirs.add(ForgeDirection.WEST);
                    }
                    if (i1 + 1 < maze.xSize && maze.isPath(i1 + 1, k1)) {
                        validDirs.add(ForgeDirection.EAST);
                    }
                    if (k1 - 1 >= 0 && maze.isPath(i1, k1 - 1)) {
                        validDirs.add(ForgeDirection.NORTH);
                    }
                    if (k1 + 1 < maze.zSize && maze.isPath(i1, k1 + 1)) {
                        validDirs.add(ForgeDirection.SOUTH);
                    }
                    if (validDirs.isEmpty()) continue;
                    ForgeDirection dir = (ForgeDirection)validDirs.get(random.nextInt(validDirs.size()));
                    this.placeDartTrap(world, random, (i + i1) * scale + dir.offsetX * scaleR, j + 0, (k + k1) * scale + dir.offsetZ * scaleR, dir.ordinal());
                }
            }
        }
    }

    private void placeDartTrap(World world, Random random, int i, int j, int k, int meta) {
        Block dartTrapBlock = LOTRMod.tauredainDartTrap;
        if (this.isGolden) {
            dartTrapBlock = LOTRMod.tauredainDartTrapGold;
        }
        this.setBlockAndMetadata(world, i, j, k, dartTrapBlock, meta);
        TileEntity tileentity = this.getTileEntity(world, i, j, k);
        if (tileentity instanceof LOTRTileEntityDartTrap) {
            LOTRTileEntityDartTrap trap = (LOTRTileEntityDartTrap)tileentity;
            for (int l = 0; l < trap.getSizeInventory(); ++l) {
                if (!random.nextBoolean()) continue;
                int darts = MathHelper.getRandomIntegerInRange((Random)random, (int)2, (int)6);
                if (random.nextBoolean()) {
                    trap.setInventorySlotContents(l, new ItemStack(LOTRMod.tauredainDartPoisoned, darts));
                    continue;
                }
                trap.setInventorySlotContents(l, new ItemStack(LOTRMod.tauredainDart, darts));
            }
        }
    }
}

