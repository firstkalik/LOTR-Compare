/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityAngbandCap;
import lotr.common.entity.npc.LOTREntityAngbandCrossbow;
import lotr.common.entity.npc.LOTREntityAngbandUruc;
import lotr.common.entity.npc.LOTREntityUtumnoTrader;
import lotr.common.item.LOTRItemBanner;
import lotr.common.world.map.LOTRFixedStructures;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure2.LOTRWorldGenMordorStructure2;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenAngbandFort
extends LOTRWorldGenMordorStructure2 {
    public LOTRWorldGenAngbandFort(boolean flag) {
        super(flag);
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        this.setOriginAndRotation(world, i, j, k, rotation, 19);
        this.setupRandomBlocks(random);
        if (this.restrictions) {
            int minHeight = 0;
            int maxHeight = 0;
            for (int i1 = -18; i1 <= 26; ++i1) {
                for (int k1 = -18; k1 <= 20; ++k1) {
                    int j1 = this.getTopBlock(world, i1, k1) - 1;
                    if (!this.isSurface(world, i1, j1, k1)) {
                        return false;
                    }
                    if (j1 < minHeight) {
                        minHeight = j1;
                    }
                    if (j1 > maxHeight) {
                        maxHeight = j1;
                    }
                    if (maxHeight - minHeight <= 16) continue;
                    return false;
                }
            }
        }
        for (int i1 = -17; i1 <= 25; ++i1) {
            for (int k1 = -18; k1 <= 19; ++k1) {
                for (int j1 = 1; j1 <= 16; ++j1) {
                    this.setAir(world, i1, j1, k1);
                }
            }
        }
        this.loadStrScan("black_uruk_fort2");
        this.associateBlockMetaAlias("BRICK", this.brickBlock, this.brickMeta);
        this.associateBlockMetaAlias("BRICK_SLAB", this.brickSlabBlock, this.brickSlabMeta);
        this.associateBlockMetaAlias("BRICK_SLAB_INV", this.brickSlabBlock, this.brickSlabMeta | 8);
        this.associateBlockAlias("BRICK_STAIR", this.brickStairBlock);
        this.associateBlockMetaAlias("BRICK_WALL", this.brickWallBlock, this.brickWallMeta);
        this.associateBlockMetaAlias("BRICK_CARVED", this.brickCarvedBlock, this.brickCarvedMeta);
        this.associateBlockMetaAlias("PILLAR", this.pillarBlock, this.pillarMeta);
        this.associateBlockMetaAlias("SMOOTH", this.smoothBlock, this.smoothMeta);
        this.associateBlockMetaAlias("SMOOTH_SLAB", this.smoothSlabBlock, this.smoothSlabMeta);
        this.associateBlockMetaAlias("TILE", this.tileBlock, this.tileMeta);
        this.associateBlockMetaAlias("TILE_SLAB", this.tileSlabBlock, this.tileSlabMeta);
        this.associateBlockMetaAlias("TILE_SLAB_INV", this.tileSlabBlock, this.tileSlabMeta | 8);
        this.associateBlockAlias("TILE_STAIR", this.tileStairBlock);
        this.associateBlockMetaAlias("PLANK", this.plankBlock, this.plankMeta);
        this.associateBlockMetaAlias("PLANK_SLAB", this.plankSlabBlock, this.plankSlabMeta);
        this.associateBlockMetaAlias("PLANK_SLAB_INV", this.plankSlabBlock, this.plankSlabMeta | 8);
        this.associateBlockAlias("PLANK_STAIR", this.plankStairBlock);
        this.associateBlockMetaAlias("FENCE", this.fenceBlock, this.fenceMeta);
        this.associateBlockAlias("TRAPDOOR", this.trapdoorBlock);
        this.associateBlockMetaAlias("BEAM", this.beamBlock, this.beamMeta);
        this.associateBlockMetaAlias("BEAM|4", this.beamBlock, this.beamMeta | 4);
        this.associateBlockMetaAlias("BEAM|8", this.beamBlock, this.beamMeta | 8);
        this.addBlockMetaAliasOption("GROUND", 6, LOTRMod.utumnoBrick, 6);
        this.addBlockMetaAliasOption("GROUND", 2, LOTRMod.utumnoBrick, 3);
        this.addBlockMetaAliasOption("GROUND", 2, LOTRMod.utumnoBrick, 2);
        this.associateBlockAlias("GATE_IRON", this.gateIronBlock);
        this.associateBlockAlias("GATE_ORC", this.gateOrcBlock);
        this.associateBlockMetaAlias("BARS", this.barsBlock, 0);
        this.associateBlockMetaAlias("CHANDELIER", this.chandelierBlock, this.chandelierMeta);
        this.generateStrScan(world, random, 0, 0, 0);
        this.placeWallBanner(world, -2, 5, -17, LOTRItemBanner.BannerType.ANFAUGLITH, 2);
        this.placeWallBanner(world, 2, 5, -17, LOTRItemBanner.BannerType.ANFAUGLITH, 2);
        this.placeWallBanner(world, 0, 14, -20, LOTRItemBanner.BannerType.UTUMNO, 2);
        this.setBlockAndMetadata(world, -2, 11, -15, LOTRMod.commandTable, 0);
        this.placeOrcTorch(world, -3, 2, -18);
        this.placeOrcTorch(world, 3, 2, -18);
        this.placeOrcTorch(world, 19, 2, -11);
        this.placeOrcTorch(world, -3, 2, -10);
        this.placeOrcTorch(world, 3, 2, -10);
        this.placeOrcTorch(world, 2, 2, 4);
        this.placeOrcTorch(world, 5, 2, 4);
        this.placeOrcTorch(world, -11, 2, 12);
        this.placeOrcTorch(world, 18, 4, -13);
        this.placeOrcTorch(world, 10, 4, -13);
        this.placeOrcTorch(world, 21, 4, -10);
        this.placeOrcTorch(world, 21, 4, -2);
        this.placeOrcTorch(world, 24, 4, 1);
        this.placeOrcTorch(world, 20, 4, 1);
        this.placeOrcTorch(world, -13, 4, 3);
        this.placeOrcTorch(world, 5, 4, 9);
        this.placeOrcTorch(world, 2, 4, 9);
        this.placeOrcTorch(world, -13, 4, 11);
        this.placeOrcTorch(world, 11, 4, 14);
        this.placeOrcTorch(world, -4, 4, 14);
        this.placeOrcTorch(world, -10, 4, 14);
        this.placeOrcTorch(world, 20, 4, 17);
        this.placeOrcTorch(world, 22, 10, -14);
        this.placeOrcTorch(world, 22, 15, -14);
        this.placeWeaponRack(world, 9, 2, 2, 6, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 10, 2, 3, 5, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 8, 2, 3, 7, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 9, 2, 4, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 15, 2, 7, 6, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 16, 2, 8, 5, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 14, 2, 8, 7, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 15, 2, 9, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 6, 5, 10, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 1, 5, 10, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 10, 5, 12, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, -3, 5, 12, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 6, 6, 10, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 1, 6, 10, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, 10, 6, 12, 4, this.getRandomUrukWeapon(random));
        this.placeWeaponRack(world, -3, 6, 12, 4, this.getRandomUrukWeapon(random));
        this.placeArmorStand(world, 15, 1, 6, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetBerserk), new ItemStack(LOTRMod.bodyBerserk), new ItemStack(LOTRMod.legsBerserk), new ItemStack(LOTRMod.bootsBerserk)});
        this.placeArmorStand(world, 13, 1, 6, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetboldog), new ItemStack(LOTRMod.bodyboldog), new ItemStack(LOTRMod.legsboldog), new ItemStack(LOTRMod.bootsboldog)});
        this.placeArmorStand(world, 16, 1, -8, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, -8, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 16, 1, -6, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 16, 1, -4, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 16, 1, -2, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 16, 1, 0, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 16, 1, 2, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 16, 1, 4, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, -6, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, -4, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, -2, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, 0, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, 2, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, 14, 1, 4, 1, new ItemStack[]{new ItemStack(LOTRMod.helmetAngband), new ItemStack(LOTRMod.bodyAngband), new ItemStack(LOTRMod.legsAngband), new ItemStack(LOTRMod.bootsAngband)});
        this.placeArmorStand(world, -6, 1, -10, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetAngbandu), new ItemStack(LOTRMod.bodyAngbandu), new ItemStack(LOTRMod.legsAngbandu), new ItemStack(LOTRMod.bootsAngbandu)});
        this.placeArmorStand(world, -6, 1, -8, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetAngbandu), new ItemStack(LOTRMod.bodyAngbandu), new ItemStack(LOTRMod.legsAngbandu), new ItemStack(LOTRMod.bootsAngbandu)});
        this.placeArmorStand(world, -6, 1, -6, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetAngbandu), new ItemStack(LOTRMod.bodyAngbandu), new ItemStack(LOTRMod.legsAngbandu), new ItemStack(LOTRMod.bootsAngbandu)});
        this.placeArmorStand(world, -6, 1, -4, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetAngbandu), new ItemStack(LOTRMod.bodyAngbandu), new ItemStack(LOTRMod.legsAngbandu), new ItemStack(LOTRMod.bootsAngbandu)});
        this.placeArmorStand(world, -6, 1, -2, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetAngbandu), new ItemStack(LOTRMod.bodyAngbandu), new ItemStack(LOTRMod.legsAngbandu), new ItemStack(LOTRMod.bootsAngbandu)});
        this.placeArmorStand(world, -6, 1, 0, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetAngbandu), new ItemStack(LOTRMod.bodyAngbandu), new ItemStack(LOTRMod.legsAngbandu), new ItemStack(LOTRMod.bootsAngbandu)});
        this.placeArmorStand(world, 6, 1, 4, 0, new ItemStack[]{new ItemStack(LOTRMod.helmetangbande), new ItemStack(LOTRMod.bodyangbande), new ItemStack(LOTRMod.legsangbande), new ItemStack(LOTRMod.bootsangbande)});
        this.placeArmorStand(world, 1, 1, 4, 0, new ItemStack[]{new ItemStack(LOTRMod.helmetangbande), new ItemStack(LOTRMod.bodyangbande), new ItemStack(LOTRMod.legsangbande), new ItemStack(LOTRMod.bootsangbande)});
        this.placeArmorStand(world, -10, 1, 5, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetUtumno), new ItemStack(LOTRMod.bodyUtumno), new ItemStack(LOTRMod.legsUtumno), new ItemStack(LOTRMod.bootsUtumno)});
        this.placeArmorStand(world, -10, 1, 7, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetUtumno), new ItemStack(LOTRMod.bodyUtumno), new ItemStack(LOTRMod.legsUtumno), new ItemStack(LOTRMod.bootsUtumno)});
        this.placeArmorStand(world, -10, 1, 9, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetUtumno), new ItemStack(LOTRMod.bodyUtumno), new ItemStack(LOTRMod.legsUtumno), new ItemStack(LOTRMod.bootsUtumno)});
        this.placeArmorStand(world, -10, 1, 11, 3, new ItemStack[]{new ItemStack(LOTRMod.helmetUtumno), new ItemStack(LOTRMod.bodyUtumno), new ItemStack(LOTRMod.legsUtumno), new ItemStack(LOTRMod.bootsUtumno)});
        this.placeArmorStand(world, -8, 1, 11, 0, new ItemStack[]{new ItemStack(LOTRMod.helmetUtumno), new ItemStack(LOTRMod.bodyUtumno), new ItemStack(LOTRMod.legsUtumno), new ItemStack(LOTRMod.bootsUtumno)});
        this.placeArmorStand(world, -6, 1, 11, 0, new ItemStack[]{new ItemStack(LOTRMod.helmetUtumno), new ItemStack(LOTRMod.bodyUtumno), new ItemStack(LOTRMod.legsUtumno), new ItemStack(LOTRMod.bootsUtumno)});
        this.placeUrukArmor(world, random, 8, 4, 10, 2);
        this.placeUrukArmor(world, random, -1, 4, 10, 2);
        this.placeUrukArmor(world, random, 7, 4, 12, 3);
        this.placeUrukArmor(world, random, 0, 4, 12, 1);
        this.placeUrukArmor(world, random, 6, 4, 13, 2);
        this.placeUrukArmor(world, random, 1, 4, 13, 2);
        this.placeChest(world, random, 14, 1, 8, 0, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        this.placeChest(world, random, 9, 4, 11, 3, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        this.placeChest(world, random, -2, 4, 11, 3, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        this.placeChest(world, random, 12, 4, 13, 5, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        this.placeChest(world, random, -5, 4, 13, 4, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        this.placeChest(world, random, 12, 4, 17, 5, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        this.placeChest(world, random, -5, 4, 17, 4, LOTRChestContents.LOTRChestContents2.ANGBAND_TENT);
        for (int j1 = 4; j1 <= 5; ++j1) {
            for (int i1 : new int[]{-3, -1, 1}) {
                this.setBlockAndMetadata(world, i1, j1, 17, this.bedBlock, 0);
                this.setBlockAndMetadata(world, i1, j1, 18, this.bedBlock, 8);
            }
            for (int i1 : new int[]{6, 8, 10}) {
                this.setBlockAndMetadata(world, i1, j1, 17, this.bedBlock, 0);
                this.setBlockAndMetadata(world, i1, j1, 18, this.bedBlock, 8);
            }
        }
        this.placeBarrel(world, random, -11, 5, -3, 3, LOTRFoods.ORC_DRINK);
        this.placeBarrel(world, random, -13, 5, -3, 3, LOTRFoods.ORC_DRINK);
        for (int k1 = -7; k1 <= -4; ++k1) {
            for (int i1 : new int[]{-13, -11}) {
                if (random.nextBoolean()) {
                    this.placePlate(world, random, i1, 5, k1, LOTRMod.woodPlateBlock, LOTRFoods.ORC);
                    continue;
                }
                this.placeMug(world, random, i1, 5, k1, random.nextInt(4), LOTRFoods.ORC_DRINK);
            }
        }
        LOTREntityAngbandCap captain = new LOTREntityAngbandCap(world);
        captain.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(captain, world, 0, 1, 0, 8);
        LOTREntityUtumnoTrader captain1 = new LOTREntityUtumnoTrader(world);
        captain.spawnRidingHorse = false;
        this.spawnNPCAndSetHome(captain1, world, 0, 1, 0, 8);
        int uruks = 12;
        for (int l = 0; l < uruks; ++l) {
            LOTREntityAngbandUruc uruk = random.nextInt(3) == 0 ? new LOTREntityAngbandCrossbow(world) : new LOTREntityAngbandUruc(world);
            uruk.spawnRidingHorse = false;
            this.spawnNPCAndSetHome(uruk, world, 0, 1, 0, 32);
        }
        LOTREntityNPCRespawner respawner = new LOTREntityNPCRespawner(world);
        respawner.setSpawnClasses(LOTREntityAngbandUruc.class, LOTREntityAngbandCrossbow.class);
        respawner.setCheckRanges(32, -16, 20, 24);
        respawner.setSpawnRanges(24, -4, 8, 24);
        this.placeNPCRespawner(respawner, world, 0, 0, 0);
        return true;
    }

    protected ItemStack getRandomUrukWeapon(Random random) {
        ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.swordBerserk), new ItemStack(LOTRMod.daggerAngband), new ItemStack(LOTRMod.daggerAngbandPoisoned), new ItemStack(LOTRMod.spearAngband), new ItemStack(LOTRMod.battleaxeBlackUruk), new ItemStack(LOTRMod.hammerAngband), new ItemStack(LOTRMod.angbandBow)};
        return items[random.nextInt(items.length)].copy();
    }

    private void placeUrukArmor(World world, Random random, int i, int j, int k, int meta) {
        ItemStack[] arritemStack;
        if (random.nextInt(4) != 0) {
            ItemStack[] arritemStack2 = new ItemStack[4];
            arritemStack2[0] = null;
            arritemStack2[1] = null;
            arritemStack2[2] = null;
            arritemStack = arritemStack2;
            arritemStack2[3] = null;
        } else {
            ItemStack[] arritemStack3 = new ItemStack[4];
            arritemStack3[0] = new ItemStack(LOTRMod.helmetBerserk);
            arritemStack3[1] = new ItemStack(LOTRMod.bodyBerserk);
            arritemStack3[2] = new ItemStack(LOTRMod.legsBerserk);
            arritemStack = arritemStack3;
            arritemStack3[3] = new ItemStack(LOTRMod.bootsBerserk);
        }
        ItemStack[] armor = arritemStack;
        this.placeArmorStand(world, i, j, k, meta, armor);
    }

    public static boolean generatesAt(World world, int i, int k) {
        return LOTRFixedStructures.generatesAtMapImageCoords(i, k, 1135, 402);
    }
}

