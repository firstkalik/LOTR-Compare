/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemMug;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.map.LOTRFixedStructures;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRWorldGenGrukHouse
extends LOTRWorldGenStructureBase2 {
    public LOTRWorldGenGrukHouse(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        int j1;
        int i1;
        int l;
        TileEntitySign sign;
        int k13;
        int k12;
        int k1;
        int k2;
        this.setOriginAndRotation(world, i, j, k, rotation, 9);
        if (this.restrictions) {
            for (i1 = -5; i1 <= 5; ++i1) {
                for (k12 = -8; k12 <= 8; ++k12) {
                    j1 = this.getTopBlock(world, i1, k12);
                    Block block = this.getBlock(world, i1, j1 - 1, k12);
                    if (block == Blocks.grass) continue;
                    return false;
                }
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            for (k12 = -8; k12 <= 8; ++k12) {
                int j12;
                int i2 = Math.abs(i1);
                k2 = Math.abs(k12);
                for (j12 = 0; !(j12 != 0 && this.isOpaque(world, i1, j12, k12) || this.getY(j12) < 0); --j12) {
                    this.setBlockAndMetadata(world, i1, j12, k12, Blocks.cobblestone, 0);
                    this.setGrassToDirt(world, i1, j12 - 1, k12);
                }
                if (i2 == 5 && k2 == 8) {
                    for (j12 = 1; j12 <= 5; ++j12) {
                        this.setBlockAndMetadata(world, i1, j12, k12, LOTRMod.woodBeamV1, 1);
                    }
                    continue;
                }
                if (i2 == 5 || k2 == 8) {
                    for (j12 = 1; j12 <= 5; ++j12) {
                        this.setBlockAndMetadata(world, i1, j12, k12, Blocks.planks, 1);
                    }
                    continue;
                }
                for (j12 = 1; j12 <= 10; ++j12) {
                    this.setAir(world, i1, j12, k12);
                }
            }
        }
        for (k1 = -9; k1 <= 9; ++k1) {
            for (l = 0; l <= 5; ++l) {
                this.setBlockAndMetadata(world, -6 + l, 5 + l, k1, LOTRMod.stairsReed, 1);
                this.setBlockAndMetadata(world, 6 - l, 5 + l, k1, LOTRMod.stairsReed, 0);
                this.setBlockAndMetadata(world, -6 + l, 4 + l, k1, LOTRMod.stairsReed, 4);
                this.setBlockAndMetadata(world, 6 - l, 4 + l, k1, LOTRMod.stairsReed, 5);
            }
            this.setBlockAndMetadata(world, 0, 10, k1, LOTRMod.thatch, 1);
            this.setBlockAndMetadata(world, 0, 11, k1, LOTRMod.slabSingleThatch, 1);
        }
        for (l = 0; l <= 5; ++l) {
            for (int i12 = -5 + l; i12 <= 5 - l; ++i12) {
                this.setBlockAndMetadata(world, i12, 5 + l, -8, Blocks.planks, 1);
                this.setBlockAndMetadata(world, i12, 5 + l, 8, Blocks.planks, 1);
            }
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            this.setBlockAndMetadata(world, i1, 5, -8, LOTRMod.woodBeamV1, 5);
            this.setBlockAndMetadata(world, i1, 5, 8, LOTRMod.woodBeamV1, 5);
            this.setBlockAndMetadata(world, i1, 5, -7, Blocks.fence, 0);
            this.setBlockAndMetadata(world, i1, 5, 7, Blocks.fence, 0);
        }
        for (k1 = -7; k1 <= 7; ++k1) {
            this.setBlockAndMetadata(world, -5, 5, k1, LOTRMod.woodBeamV1, 9);
            this.setBlockAndMetadata(world, 5, 5, k1, LOTRMod.woodBeamV1, 9);
            this.setBlockAndMetadata(world, -4, 5, k1, Blocks.fence, 0);
            this.setBlockAndMetadata(world, 4, 5, k1, Blocks.fence, 0);
        }
        for (i1 = -5; i1 <= 5; ++i1) {
            int i2 = Math.abs(i1);
            if (i2 != 2 && i2 != 3) continue;
            this.setBlockAndMetadata(world, i1, 2, -8, LOTRMod.reedBars, 0);
            this.setBlockAndMetadata(world, i1, 3, -8, LOTRMod.reedBars, 0);
        }
        for (k1 = -7; k1 <= 7; ++k1) {
            int k22 = Math.abs(k1);
            if (k22 == 0 || k22 == 1 || k22 == 5 || k22 == 6) {
                this.setBlockAndMetadata(world, -5, 2, k1, LOTRMod.reedBars, 0);
                this.setBlockAndMetadata(world, -5, 3, k1, LOTRMod.reedBars, 0);
                this.setBlockAndMetadata(world, 5, 2, k1, LOTRMod.reedBars, 0);
                this.setBlockAndMetadata(world, 5, 3, k1, LOTRMod.reedBars, 0);
                continue;
            }
            if (k22 != 3) continue;
            for (j1 = 0; j1 <= 4; ++j1) {
                this.setBlockAndMetadata(world, -5, j1, k1, LOTRMod.woodBeamV1, 1);
                this.setBlockAndMetadata(world, 5, j1, k1, LOTRMod.woodBeamV1, 1);
            }
            this.setBlockAndMetadata(world, -3, 1, k1, Blocks.fence, 1);
            this.setBlockAndMetadata(world, -3, 2, k1, Blocks.torch, 5);
            this.setBlockAndMetadata(world, 3, 1, k1, Blocks.fence, 1);
            this.setBlockAndMetadata(world, 3, 2, k1, Blocks.torch, 5);
        }
        this.setBlockAndMetadata(world, 0, 0, -8, Blocks.cobblestone, 0);
        this.setBlockAndMetadata(world, 0, 1, -8, LOTRMod.doorPine, 1);
        this.setBlockAndMetadata(world, 0, 2, -8, LOTRMod.doorPine, 8);
        this.setBlockAndMetadata(world, 0, 4, -9, Blocks.torch, 4);
        this.setBlockAndMetadata(world, 0, 3, -7, Blocks.torch, 3);
        this.setBlockAndMetadata(world, 0, 1, 7, Blocks.fence, 1);
        this.setBlockAndMetadata(world, 0, 2, 7, Blocks.torch, 5);
        for (k1 = -7; k1 <= 7; ++k1) {
            int[] k22 = new int[]{-4, 4};
            j1 = k22.length;
            for (k2 = 0; k2 < j1; ++k2) {
                int i13 = k22[k2];
                this.setBlockAndMetadata(world, i13, 1, k1, LOTRMod.planks2, 4);
                if (!random.nextBoolean()) continue;
                this.placeMug(world, random, i13, 2, k1, random.nextInt(4), this.getRandomDrink(random), new LOTRItemMug.Vessel[]{LOTRItemMug.Vessel.GOBLET_GOLD, LOTRItemMug.Vessel.GOBLET_SILVER, LOTRItemMug.Vessel.HORN, LOTRItemMug.Vessel.HORN_GOLD});
            }
        }
        for (i1 = -3; i1 <= 3; ++i1) {
            if (i1 == 0) continue;
            this.placeBarrel(world, random, i1, 1, 7, 2, this.getRandomDrink(random));
            this.placeBarrel(world, random, i1, 2, 7, 2, this.getRandomDrink(random));
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            this.setBlockAndMetadata(world, i1, 4, 7, Blocks.wool, 14);
            this.setBlockAndMetadata(world, i1, 5, 7, Blocks.wool, 0);
        }
        for (i1 = -1; i1 <= 1; ++i1) {
            for (k13 = -6; k13 <= -3; ++k13) {
                this.setBlockAndMetadata(world, i1, 1, k13, Blocks.carpet, 14);
            }
            for (k13 = -2; k13 <= 1; ++k13) {
                this.setBlockAndMetadata(world, i1, 1, k13, Blocks.carpet, 0);
            }
        }
        int[] i14 = new int[]{-8, 8};
        k13 = i14.length;
        for (j1 = 0; j1 < k13; ++j1) {
            int i15 = i14[j1];
            for (int i2 = i15 - 2; i2 <= i15 + 2; ++i2) {
                for (int k14 = -20; k14 <= -16; ++k14) {
                    int j13;
                    for (j13 = 4; !(j13 < 0 && this.isOpaque(world, i2, j13, k14) || this.getY(j13) < 0); --j13) {
                        this.setBlockAndMetadata(world, i2, j13, k14, Blocks.cobblestone, 0);
                        this.setGrassToDirt(world, i2, j13 - 1, k14);
                    }
                    for (j13 = 5; j13 <= 10; ++j13) {
                        this.setAir(world, i2, j13, k14);
                    }
                    if (Math.abs(i2 - i15) > 1 || Math.abs(k14 - -18) > 1) continue;
                    this.setBlockAndMetadata(world, i2, 4, k14, LOTRMod.hearth, 0);
                    this.setBlockAndMetadata(world, i2, 5, k14, (Block)Blocks.fire, 0);
                }
            }
        }
        for (int i16 = -12; i16 <= 12; ++i16) {
            for (k13 = -20; k13 <= 0; ++k13) {
                int dx = i16 - 0;
                int dz = k13 - -8;
                int dSq = dx * dx + dz * dz;
                if (dSq > 144 || random.nextInt(6) == 0) continue;
                int j14 = this.getTopBlock(world, i16, k13) - 1;
                BiomeGenBase biome = this.getBiome(world, i16, k13);
                Block below = this.getBlock(world, i16, j14, k13);
                if (below != biome.topBlock && below != biome.fillerBlock) continue;
                LOTRRoadType.RoadBlock roadblock = LOTRRoadType.PATH.getBlock(random, LOTRBiome.tundra, true, false);
                this.setBlockAndMetadata(world, i16, j14, k13, roadblock.block, roadblock.meta);
            }
        }
        this.setBlockAndMetadata(world, 0, 3, -9, Blocks.wall_sign, 2);
        TileEntity te = this.getTileEntity(world, 0, 3, -9);
        if (te instanceof TileEntitySign) {
            TileEntitySign sign2 = (TileEntitySign)te;
            sign2.signText[1] = "Kvas";
            sign2.signText[2] = "chlebov\u00c3\u00bd";
        }
        this.setBlockAndMetadata(world, 0, 3, 7, Blocks.wall_sign, 2);
        te = this.getTileEntity(world, 0, 3, 7);
        if (te instanceof TileEntitySign) {
            sign = (TileEntitySign)te;
            sign.signText[1] = ":^)";
        }
        this.setBlockAndMetadata(world, 0, 8, -7, Blocks.wall_sign, 3);
        te = this.getTileEntity(world, 0, 8, -7);
        if (te instanceof TileEntitySign) {
            sign = (TileEntitySign)te;
            sign.signText[1] = "Textures?";
        }
        this.spawnItemFrame(world, -1, 7, -8, 0, new ItemStack(LOTRMod.rollingPin));
        this.spawnItemFrame(world, 1, 7, -8, 0, new ItemStack(Items.book));
        EntityOcelot bazyl = new EntityOcelot(world);
        bazyl.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0E8);
        bazyl.setHealth(bazyl.getMaxHealth());
        bazyl.setTamed(true);
        bazyl.func_152115_b("6c94c61a-aebb-4b77-9699-4d5236d0e78a");
        bazyl.setTameSkin(1);
        bazyl.setCustomNameTag("Bazyl");
        this.spawnNPCAndSetHome((EntityCreature)bazyl, world, -1, 1, 0, 16);
        EntityWolf wiktor = new EntityWolf(world);
        wiktor.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0E8);
        wiktor.setHealth(wiktor.getMaxHealth());
        wiktor.setTamed(true);
        wiktor.func_152115_b("6c94c61a-aebb-4b77-9699-4d5236d0e78a");
        wiktor.setCustomNameTag("Wiktor");
        this.spawnNPCAndSetHome((EntityCreature)wiktor, world, 1, 1, 0, 16);
        return true;
    }

    private ItemStack getRandomDrink(Random random) {
        if (random.nextBoolean()) {
            return new ItemStack(LOTRMod.mugPlumKvass);
        }
        return new ItemStack(LOTRMod.mugVodka);
    }

    public static boolean generatesAt(World world, int i, int k) {
        return LOTRFixedStructures.generatesAtMapImageCoords(i, k, 989, 528);
    }
}

