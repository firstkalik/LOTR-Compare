/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.world.village;

import com.google.common.math.IntMath;
import java.util.Random;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.structure2.LOTRWorldGenBlackrootFortress;
import lotr.common.world.structure2.LOTRWorldGenBlackrootWatchtower;
import lotr.common.world.structure2.LOTRWorldGenDolAmrothStables;
import lotr.common.world.structure2.LOTRWorldGenDolAmrothWatchtower;
import lotr.common.world.structure2.LOTRWorldGenGondorBarn;
import lotr.common.world.structure2.LOTRWorldGenGondorBath;
import lotr.common.world.structure2.LOTRWorldGenGondorCottage;
import lotr.common.world.structure2.LOTRWorldGenGondorFortGate;
import lotr.common.world.structure2.LOTRWorldGenGondorFortWall;
import lotr.common.world.structure2.LOTRWorldGenGondorFortWallCorner;
import lotr.common.world.structure2.LOTRWorldGenGondorFortress;
import lotr.common.world.structure2.LOTRWorldGenGondorGatehouse;
import lotr.common.world.structure2.LOTRWorldGenGondorHouse;
import lotr.common.world.structure2.LOTRWorldGenGondorLampPost;
import lotr.common.world.structure2.LOTRWorldGenGondorMarketStall;
import lotr.common.world.structure2.LOTRWorldGenGondorObelisk;
import lotr.common.world.structure2.LOTRWorldGenGondorSmithy;
import lotr.common.world.structure2.LOTRWorldGenGondorStables;
import lotr.common.world.structure2.LOTRWorldGenGondorStoneHouse;
import lotr.common.world.structure2.LOTRWorldGenGondorStructure;
import lotr.common.world.structure2.LOTRWorldGenGondorTavern;
import lotr.common.world.structure2.LOTRWorldGenGondorTownBench;
import lotr.common.world.structure2.LOTRWorldGenGondorTownGarden;
import lotr.common.world.structure2.LOTRWorldGenGondorTownTrees;
import lotr.common.world.structure2.LOTRWorldGenGondorTownWall;
import lotr.common.world.structure2.LOTRWorldGenGondorVillageFarm;
import lotr.common.world.structure2.LOTRWorldGenGondorVillageSign;
import lotr.common.world.structure2.LOTRWorldGenGondorWatchtower;
import lotr.common.world.structure2.LOTRWorldGenGondorWell;
import lotr.common.world.structure2.LOTRWorldGenHayBales;
import lotr.common.world.structure2.LOTRWorldGenLamedonFortress;
import lotr.common.world.structure2.LOTRWorldGenLamedonWatchtower;
import lotr.common.world.structure2.LOTRWorldGenLebenninFortress;
import lotr.common.world.structure2.LOTRWorldGenLebenninWatchtower;
import lotr.common.world.structure2.LOTRWorldGenLossarnachFortress;
import lotr.common.world.structure2.LOTRWorldGenLossarnachWatchtower;
import lotr.common.world.structure2.LOTRWorldGenNPCRespawner;
import lotr.common.world.structure2.LOTRWorldGenPelargirFortress;
import lotr.common.world.structure2.LOTRWorldGenPelargirWatchtower;
import lotr.common.world.structure2.LOTRWorldGenPinnathGelinFortress;
import lotr.common.world.structure2.LOTRWorldGenPinnathGelinWatchtower;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import lotr.common.world.village.LOTRVillageGen;
import lotr.common.world.village.LocationInfo;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRVillageGenGondor
extends LOTRVillageGen {
    public LOTRWorldGenGondorStructure.GondorFiefdom villageFief;

    public LOTRVillageGenGondor(LOTRBiome biome, LOTRWorldGenGondorStructure.GondorFiefdom fief, float f) {
        super(biome);
        this.gridScale = 16;
        this.gridRandomDisplace = 2;
        this.spawnChance = f;
        this.villageChunkRadius = 5;
        this.villageFief = fief;
    }

    @Override
    public LOTRVillageGen.AbstractInstance<?> createVillageInstance(World world, int i, int k, Random random, LocationInfo loc) {
        return new Instance(this, world, i, k, random, loc);
    }

    public static enum VillageType {
        VILLAGE,
        TOWN,
        FORT;

    }

    public static class Instance
    extends LOTRVillageGen.AbstractInstance<LOTRVillageGenGondor> {
        public VillageType villageType;
        public LOTRWorldGenGondorStructure.GondorFiefdom villageFief;
        public String[] villageName;

        public Instance(LOTRVillageGenGondor village, World world, int i, int k, Random random, LocationInfo loc) {
            super(village, world, i, k, random, loc);
            this.villageFief = village.villageFief;
        }

        @Override
        public void addStructure(LOTRWorldGenStructureBase2 structure, int x, int z, int r, boolean force) {
            super.addStructure(structure, x, z, r, force);
            if (structure instanceof LOTRWorldGenGondorStructure) {
                ((LOTRWorldGenGondorStructure)structure).strFief = this.villageFief;
            }
        }

        @Override
        public void addVillageStructures(Random random) {
            if (this.villageType == VillageType.VILLAGE) {
                this.setupVillage(random);
            } else if (this.villageType == VillageType.TOWN) {
                this.setupTown(random);
            } else if (this.villageType == VillageType.FORT) {
                this.setupFortVillage(random);
            }
        }

        @Override
        public LOTRRoadType getPath(Random random, int i, int k) {
            int i1 = Math.abs(i);
            int k1 = Math.abs(k);
            if (this.villageType == VillageType.VILLAGE) {
                int dSq = i * i + k * k;
                int imn = 20 + random.nextInt(4);
                if (dSq < imn * imn) {
                    return LOTRRoadType.PATH;
                }
                int omn = 53 - random.nextInt(4);
                int omx = 60 + random.nextInt(4);
                if (dSq > omn * omn && dSq < omx * omx || dSq < 2809 && Math.abs(i1 - k1) <= 2 + random.nextInt(4)) {
                    return LOTRRoadType.PATH;
                }
            }
            if (this.villageType == VillageType.TOWN && i1 <= 80 && k1 <= 80) {
                return LOTRRoadType.COBBLESTONE;
            }
            if (this.villageType == VillageType.FORT) {
                if (i1 <= 1 && (k >= 13 || k <= -12) && k1 <= 36) {
                    return this.instanceVillageBiome.getRoadBlock();
                }
                if (k1 <= 1 && i1 >= 12 && i1 <= 36) {
                    return this.instanceVillageBiome.getRoadBlock();
                }
                if (k >= 26 && k <= 28 && i1 <= 12) {
                    return this.instanceVillageBiome.getRoadBlock();
                }
            }
            return null;
        }

        public LOTRWorldGenStructureBase2 getRandomFarm(Random random) {
            if (random.nextBoolean()) {
                if (random.nextBoolean()) {
                    return new LOTRWorldGenGondorVillageFarm.Animals(false);
                }
                return new LOTRWorldGenGondorVillageFarm.Crops(false);
            }
            return new LOTRWorldGenGondorVillageFarm.Tree(false);
        }

        public LOTRWorldGenStructureBase2 getRandomHouse(Random random) {
            if (random.nextInt(5) == 0) {
                int i = random.nextInt(3);
                switch (i) {
                    case 0: {
                        return new LOTRWorldGenGondorStables(false);
                    }
                    case 1: {
                        return new LOTRWorldGenGondorSmithy(false);
                    }
                    case 2: {
                        return new LOTRWorldGenGondorBarn(false);
                    }
                }
            }
            return new LOTRWorldGenGondorHouse(false);
        }

        public LOTRWorldGenStructureBase2 getVillageFortress() {
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.GONDOR) {
                return new LOTRWorldGenGondorFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.LOSSARNACH) {
                return new LOTRWorldGenLossarnachFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.LEBENNIN) {
                return new LOTRWorldGenLebenninFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.PELARGIR) {
                return new LOTRWorldGenPelargirFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.PINNATH_GELIN) {
                return new LOTRWorldGenPinnathGelinFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.BLACKROOT_VALE) {
                return new LOTRWorldGenBlackrootFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.LAMEDON) {
                return new LOTRWorldGenLamedonFortress(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.DOL_AMROTH) {
                return new LOTRWorldGenDolAmrothStables(false);
            }
            return null;
        }

        public LOTRWorldGenStructureBase2 getVillageWatchtower() {
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.GONDOR) {
                return new LOTRWorldGenGondorWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.LOSSARNACH) {
                return new LOTRWorldGenLossarnachWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.LEBENNIN) {
                return new LOTRWorldGenLebenninWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.PELARGIR) {
                return new LOTRWorldGenPelargirWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.PINNATH_GELIN) {
                return new LOTRWorldGenPinnathGelinWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.BLACKROOT_VALE) {
                return new LOTRWorldGenBlackrootWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.LAMEDON) {
                return new LOTRWorldGenLamedonWatchtower(false);
            }
            if (this.villageFief == LOTRWorldGenGondorStructure.GondorFiefdom.DOL_AMROTH) {
                return new LOTRWorldGenDolAmrothWatchtower(false);
            }
            return null;
        }

        @Override
        public boolean isFlat() {
            return this.villageType == VillageType.TOWN;
        }

        @Override
        public boolean isVillageSpecificSurface(World world, int i, int j, int k) {
            Block block = world.getBlock(i, j, k);
            return this.villageType == VillageType.TOWN && block == Blocks.cobblestone;
        }

        public void setupFortVillage(Random random) {
            this.addStructure(new LOTRWorldGenNPCRespawner(false){

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    spawner.setSpawnClass(LOTREntityGondorMan.class);
                    spawner.setCheckRanges(50, -12, 12, 16);
                    spawner.setSpawnRanges(30, -6, 6, 40);
                    spawner.setBlockEnemySpawnRange(60);
                }
            }, 0, 0, 0);
            for (int i1 : new int[]{-20, 20}) {
                for (int k1 : new int[]{-20, 20}) {
                    this.addStructure(new LOTRWorldGenNPCRespawner(false){

                        @Override
                        public void setupRespawner(LOTREntityNPCRespawner spawner) {
                            spawner.setSpawnClasses(villageFief.getSoldierClasses()[0], villageFief.getSoldierClasses()[1]);
                            spawner.setCheckRanges(20, -12, 12, 16);
                            spawner.setSpawnRanges(20, -6, 6, 40);
                            spawner.setBlockEnemySpawnRange(40);
                        }
                    }, i1, k1, 0);
                }
            }
            this.addStructure(this.getVillageFortress(), 0, 12, 2, true);
            this.addStructure(new LOTRWorldGenGondorFortGate(false), 0, -37, 0, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Right(false), -11, -37, 0, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Left(false), 11, -37, 0, true);
            this.addStructure(this.getVillageWatchtower(), -23, -33, 2, true);
            this.addStructure(this.getVillageWatchtower(), 23, -33, 2, true);
            this.addStructure(new LOTRWorldGenGondorFortGate(false), -37, 0, 3, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Left(false), -37, -11, 3, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Right(false), -37, 11, 3, true);
            this.addStructure(this.getVillageWatchtower(), -33, -23, 1, true);
            this.addStructure(this.getVillageWatchtower(), -33, 23, 1, true);
            this.addStructure(new LOTRWorldGenGondorFortGate(false), 0, 37, 2, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Left(false), -11, 37, 2, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Right(false), 11, 37, 2, true);
            this.addStructure(this.getVillageWatchtower(), -23, 33, 0, true);
            this.addStructure(this.getVillageWatchtower(), 23, 33, 0, true);
            this.addStructure(new LOTRWorldGenGondorFortGate(false), 37, 0, 1, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Right(false), 37, -11, 1, true);
            this.addStructure(new LOTRWorldGenGondorFortWall.Left(false), 37, 11, 1, true);
            this.addStructure(this.getVillageWatchtower(), 33, -23, 3, true);
            this.addStructure(this.getVillageWatchtower(), 33, 23, 3, true);
            this.addStructure(new LOTRWorldGenGondorFortWallCorner(false), -30, -30, 3);
            this.addStructure(new LOTRWorldGenGondorFortWallCorner(false), -30, 30, 2);
            this.addStructure(new LOTRWorldGenGondorFortWallCorner(false), 30, 30, 1);
            this.addStructure(new LOTRWorldGenGondorFortWallCorner(false), 30, -30, 0);
            this.addStructure(new LOTRWorldGenGondorStables(false), -24, 2, 0);
            this.addStructure(new LOTRWorldGenGondorStables(false), -24, -2, 2);
            this.addStructure(new LOTRWorldGenGondorSmithy(false), 24, 1, 0);
            this.addStructure(new LOTRWorldGenGondorSmithy(false), 24, -1, 2);
            this.addStructure(new LOTRWorldGenGondorStoneHouse(false), -3, -25, 1);
            this.addStructure(new LOTRWorldGenGondorStoneHouse(false), 3, -25, 3);
            this.addStructure(new LOTRWorldGenGondorVillageFarm.Crops(false), -18, -21, 1);
            this.addStructure(new LOTRWorldGenGondorVillageFarm.Crops(false), 18, -21, 3);
            this.addStructure(new LOTRWorldGenGondorWell(false), -12, 27, 1);
            this.addStructure(new LOTRWorldGenGondorWell(false), 12, 27, 3);
        }

        public void setupTown(Random random) {
            int wallX;
            int l;
            boolean outerTavern = random.nextBoolean();
            this.addStructure(new LOTRWorldGenNPCRespawner(false){

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    spawner.setSpawnClass(LOTREntityGondorMan.class);
                    spawner.setCheckRanges(80, -12, 12, 100);
                    spawner.setSpawnRanges(60, -6, 6, 64);
                    spawner.setBlockEnemySpawnRange(60);
                }
            }, 0, 0, 0);
            for (int i1 : new int[]{-40, 40}) {
                int[] arrn = new int[]{-40, 40};
                int n = arrn.length;
                for (int i = 0; i < n; ++i) {
                    int k1 = arrn[i];
                    this.addStructure(new LOTRWorldGenNPCRespawner(false){

                        @Override
                        public void setupRespawner(LOTREntityNPCRespawner spawner) {
                            spawner.setSpawnClasses(villageFief.getLevyClasses()[0], villageFief.getLevyClasses()[1]);
                            spawner.setCheckRanges(40, -12, 12, 16);
                            spawner.setSpawnRanges(20, -6, 6, 64);
                            spawner.setBlockEnemySpawnRange(60);
                        }
                    }, i1, k1, 0);
                }
            }
            this.addStructure(new LOTRWorldGenGondorWell(false), 0, -4, 0, true);
            int stallPos = 12;
            for (int k1 = -1; k1 <= 1; ++k1) {
                int k2 = k1 * stallPos;
                if (random.nextInt(3) != 0) {
                    this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), -stallPos + 3, k2, 1, true);
                }
                if (random.nextInt(3) == 0) continue;
                this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), stallPos - 3, k2, 3, true);
            }
            if (random.nextInt(3) != 0) {
                this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), 0, stallPos - 3, 0, true);
            }
            if (random.nextInt(3) != 0) {
                this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), 0, -stallPos + 3, 2, true);
            }
            int flowerX = 12;
            int flowerZ = 18;
            for (int i1 : new int[]{-flowerX, flowerX}) {
                this.addStructure(new LOTRWorldGenGondorTownGarden(false), i1, flowerZ, 0, true);
                this.addStructure(new LOTRWorldGenGondorTownGarden(false), i1, -flowerZ, 2, true);
                this.addStructure(new LOTRWorldGenGondorTownGarden(false), -flowerZ, i1, 1, true);
                this.addStructure(new LOTRWorldGenGondorTownGarden(false), flowerZ, i1, 3, true);
            }
            int lampZ = 21;
            for (int i1 : new int[]{-1, 1}) {
                int lampX = i1 * 6;
                this.addStructure(new LOTRWorldGenGondorLampPost(false), lampX, lampZ, 0, true);
                this.addStructure(new LOTRWorldGenGondorLampPost(false), lampX, -lampZ, 2, true);
                if (i1 != -1) {
                    this.addStructure(new LOTRWorldGenGondorLampPost(false), -lampZ, lampX, 1, true);
                }
                this.addStructure(new LOTRWorldGenGondorLampPost(false), lampZ, lampX, 3, true);
            }
            int houseX = 24;
            for (int k1 = -1; k1 <= 1; ++k1) {
                int houseZ = k1 * 12;
                if (k1 == 1) {
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), -houseX, houseZ, 1, true);
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseX, houseZ, 3, true);
                }
                if (k1 == 0) continue;
                this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseZ, houseX, 0, true);
                this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseZ, -houseX, 2, true);
            }
            this.addStructure(new LOTRWorldGenGondorSmithy(false), 0, -26, 2, true);
            this.addStructure(new LOTRWorldGenGondorObelisk(false), 0, 27, 0, true);
            this.addStructure(new LOTRWorldGenGondorTavern(false), -houseX, -5, 1, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -47, -13, 2, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -47, 1, 0, true);
            for (int i1 : new int[]{-43, -51}) {
                this.addStructure(new LOTRWorldGenGondorTownBench(false), i1, -9, 2, true);
                this.addStructure(new LOTRWorldGenGondorTownBench(false), i1, -3, 0, true);
            }
            this.addStructure(new LOTRWorldGenGondorBath(false), houseX + 2, -6, 3, true);
            this.addStructure(new LOTRWorldGenGondorTownGarden(false), 51, -13, 2, true);
            this.addStructure(new LOTRWorldGenGondorTownGarden(false), 51, 1, 0, true);
            this.addStructure(new LOTRWorldGenGondorTownGarden(false), 52, -6, 3, true);
            int wellX = 22;
            int wellZ = 31;
            for (int i1 : new int[]{-wellX, wellX}) {
                this.addStructure(new LOTRWorldGenGondorWell(false), i1, -wellZ, 2, true);
                this.addStructure(new LOTRWorldGenGondorWell(false), i1, wellZ, 0, true);
                this.addStructure(new LOTRWorldGenGondorWell(false), -wellZ, i1, 1, true);
                this.addStructure(new LOTRWorldGenGondorWell(false), wellZ, i1, 3, true);
            }
            houseX = 54;
            for (int k1 = -2; k1 <= 2; ++k1) {
                int houseZ = k1 * 12;
                if (k1 <= -2 || k1 >= 1) {
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), -houseX, houseZ, 3, true);
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseX, houseZ, 1, true);
                }
                this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseZ, houseX, 2, true);
                this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseZ, -houseX, 0, true);
            }
            int treeX = 47;
            int treeZ = 35;
            for (int i1 : new int[]{-treeX, treeX}) {
                this.addStructure(new LOTRWorldGenGondorTownTrees(false), i1, -treeZ, 0, true);
                this.addStructure(new LOTRWorldGenGondorTownTrees(false), i1, treeZ, 2, true);
                this.addStructure(new LOTRWorldGenGondorTownTrees(false), -treeZ, i1, 3, true);
                this.addStructure(new LOTRWorldGenGondorTownTrees(false), treeZ, i1, 1, true);
            }
            houseX = 64;
            int lampX = 59;
            for (int k1 = -4; k1 <= 4; ++k1) {
                boolean treepiece;
                int houseZ = k1 * 12;
                boolean bl = treepiece = IntMath.mod((int)k1, (int)2) == 1;
                if (treepiece) {
                    this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), -houseX - 2, houseZ, 1, true);
                    this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), houseX + 2, houseZ, 3, true);
                } else {
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), -houseX, houseZ, 1, true);
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseX, houseZ, 3, true);
                }
                if (treepiece) {
                    this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), houseZ, -houseX - 2, 2, true);
                } else {
                    this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseZ, -houseX, 2, true);
                }
                if (!(Math.abs(k1) < 2 || outerTavern && k1 > 2)) {
                    if (treepiece) {
                        this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), houseZ, houseX + 2, 0, true);
                    } else {
                        this.addStructure(new LOTRWorldGenGondorStoneHouse(false), houseZ, houseX, 0, true);
                    }
                }
                this.addStructure(new LOTRWorldGenGondorLampPost(false), -lampX, houseZ, 1, true);
                this.addStructure(new LOTRWorldGenGondorLampPost(false), lampX, houseZ, 3, true);
                this.addStructure(new LOTRWorldGenGondorLampPost(false), houseZ, lampX, 0, true);
                this.addStructure(new LOTRWorldGenGondorLampPost(false), houseZ, -lampX, 2, true);
            }
            if (outerTavern) {
                this.addStructure(new LOTRWorldGenGondorTavern(false), 44, houseX, 0, true);
            }
            int gardenX = 42;
            int gardenZ = 48;
            this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), -gardenX, -gardenZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), -gardenX, gardenZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), gardenX, -gardenZ, 3, true);
            this.addStructure(new LOTRWorldGenGondorVillageFarm.Tree(false), gardenX, gardenZ, 3, true);
            int obeliskX = 62;
            int obeliskZ = 66;
            this.addStructure(new LOTRWorldGenGondorObelisk(false), -obeliskX, -obeliskZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorObelisk(false), -obeliskX, obeliskZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorObelisk(false), obeliskX, -obeliskZ, 3, true);
            this.addStructure(new LOTRWorldGenGondorObelisk(false), obeliskX, obeliskZ, 3, true);
            wellX = 64;
            wellZ = 57;
            this.addStructure(new LOTRWorldGenGondorWell(false), -wellX, -wellZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), -wellX, wellZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), wellX, -wellZ, 3, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), wellX, wellZ, 3, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), -wellZ, -wellX, 2, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), wellZ, -wellX, 2, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), -wellZ, wellX, 0, true);
            this.addStructure(new LOTRWorldGenGondorWell(false), wellZ, wellX, 0, true);
            treeX = 75;
            treeZ = 61;
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -treeX, -treeZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -treeX, treeZ, 1, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), treeX, -treeZ, 3, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), treeX, treeZ, 3, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -treeZ, -treeX, 2, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), treeZ, -treeX, 2, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -treeZ, treeX, 0, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), treeZ, treeX, 0, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), -14, 71, 1, true);
            this.addStructure(new LOTRWorldGenGondorTownTrees(false), 14, 71, 3, true);
            for (int k1 : new int[]{67, 75}) {
                this.addStructure(new LOTRWorldGenGondorTownBench(false), -10, k1, 1, true);
                this.addStructure(new LOTRWorldGenGondorTownBench(false), 10, k1, 3, true);
            }
            this.addStructure(new LOTRWorldGenGondorGatehouse(false).setSignText(this.villageName), 0, 84, 2, true);
            this.addStructure(new LOTRWorldGenGondorLampPost(false), -4, 73, 0, true);
            this.addStructure(new LOTRWorldGenGondorLampPost(false), 4, 73, 0, true);
            int towerX = 78;
            int towerZ = 74;
            for (int i1 : new int[]{-towerX, towerX}) {
                this.addStructure(this.getVillageWatchtower(), i1, -towerZ, 2, true);
                this.addStructure(this.getVillageWatchtower(), i1, towerZ, 0, true);
            }
            int wallZ = 82;
            int wallEndX = 76;
            for (l = 0; l <= 3; ++l) {
                wallX = 12 + l * 16;
                this.addStructure(LOTRWorldGenGondorTownWall.Left(false), -wallX, wallZ, 2, true);
                this.addStructure(LOTRWorldGenGondorTownWall.Right(false), wallX, wallZ, 2, true);
            }
            this.addStructure(LOTRWorldGenGondorTownWall.LeftEndShort(false), -wallEndX, wallZ, 2, true);
            this.addStructure(LOTRWorldGenGondorTownWall.RightEndShort(false), wallEndX, wallZ, 2, true);
            this.addStructure(LOTRWorldGenGondorTownWall.Centre(false), -wallZ, 0, 3, true);
            this.addStructure(LOTRWorldGenGondorTownWall.Centre(false), wallZ, 0, 1, true);
            this.addStructure(LOTRWorldGenGondorTownWall.Centre(false), 0, -wallZ, 0, true);
            for (l = 0; l <= 3; ++l) {
                wallX = 12 + l * 16;
                this.addStructure(LOTRWorldGenGondorTownWall.Left(false), -wallZ, -wallX, 3, true);
                this.addStructure(LOTRWorldGenGondorTownWall.Right(false), -wallZ, wallX, 3, true);
                this.addStructure(LOTRWorldGenGondorTownWall.Left(false), wallZ, wallX, 1, true);
                this.addStructure(LOTRWorldGenGondorTownWall.Right(false), wallZ, -wallX, 1, true);
                this.addStructure(LOTRWorldGenGondorTownWall.Left(false), wallX, -wallZ, 0, true);
                this.addStructure(LOTRWorldGenGondorTownWall.Right(false), -wallX, -wallZ, 0, true);
            }
            this.addStructure(LOTRWorldGenGondorTownWall.LeftEnd(false), -wallZ, -wallEndX, 3, true);
            this.addStructure(LOTRWorldGenGondorTownWall.RightEnd(false), -wallZ, wallEndX, 3, true);
            this.addStructure(LOTRWorldGenGondorTownWall.LeftEnd(false), wallZ, wallEndX, 1, true);
            this.addStructure(LOTRWorldGenGondorTownWall.RightEnd(false), wallZ, -wallEndX, 1, true);
            this.addStructure(LOTRWorldGenGondorTownWall.LeftEndShort(false), wallEndX, -wallZ, 0, true);
            this.addStructure(LOTRWorldGenGondorTownWall.RightEndShort(false), -wallEndX, -wallZ, 0, true);
        }

        public void setupVillage(Random random) {
            this.addStructure(new LOTRWorldGenGondorWell(false), 0, -4, 0, true);
            this.addStructure(new LOTRWorldGenNPCRespawner(false){

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    spawner.setSpawnClass(LOTREntityGondorMan.class);
                    spawner.setCheckRanges(40, -12, 12, 40);
                    spawner.setSpawnRanges(20, -6, 6, 64);
                    spawner.setBlockEnemySpawnRange(60);
                }
            }, 0, 0, 0);
            this.addStructure(new LOTRWorldGenNPCRespawner(false){

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    spawner.setSpawnClass(villageFief.getLevyClasses()[0]);
                    spawner.setCheckRanges(40, -12, 12, 16);
                    spawner.setSpawnRanges(20, -6, 6, 64);
                    spawner.setBlockEnemySpawnRange(60);
                }
            }, 0, 0, 0);
            this.addStructure(new LOTRWorldGenGondorCottage(false), -21, 0, 1);
            this.addStructure(new LOTRWorldGenGondorCottage(false), 0, -21, 2);
            this.addStructure(new LOTRWorldGenGondorCottage(false), 21, 0, 3);
            this.addStructure(new LOTRWorldGenGondorTavern(false), 0, 21, 0);
            if (random.nextBoolean()) {
                if (random.nextInt(3) == 0) {
                    this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), -9, -12, 1);
                }
                if (random.nextInt(3) == 0) {
                    this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), 9, -12, 3);
                }
                if (random.nextInt(3) == 0) {
                    this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), -9, 12, 1);
                }
                if (random.nextInt(3) == 0) {
                    this.addStructure(LOTRWorldGenGondorMarketStall.getRandomStall(random, false), 9, 12, 3);
                }
            }
            int houses = 20;
            float frac = 1.0f / (float)houses;
            float turn = 0.0f;
            while (turn < 1.0f) {
                int l;
                int k;
                int i;
                float turnR = (float)Math.toRadians((turn += frac) * 360.0f);
                float sin = MathHelper.sin((float)turnR);
                float cos = MathHelper.cos((float)turnR);
                int r = 0;
                float turn8 = turn * 8.0f;
                if (turn8 >= 1.0f && turn8 < 3.0f) {
                    r = 0;
                } else if (turn8 >= 3.0f && turn8 < 5.0f) {
                    r = 1;
                } else if (turn8 >= 5.0f && turn8 < 7.0f) {
                    r = 2;
                } else if (turn8 >= 7.0f || turn8 < 1.0f) {
                    r = 3;
                }
                if (random.nextBoolean()) {
                    l = 61;
                    i = Math.round((float)l * cos);
                    k = Math.round((float)l * sin);
                    this.addStructure(this.getRandomHouse(random), i, k, r);
                    continue;
                }
                if (random.nextInt(3) == 0) continue;
                l = 65;
                i = Math.round((float)l * cos);
                k = Math.round((float)l * sin);
                this.addStructure(new LOTRWorldGenHayBales(false), i, k, r);
            }
            int signPos = Math.round(50.0f * MathHelper.cos((float)((float)Math.toRadians(45.0))));
            int signDisp = Math.round(7.0f * MathHelper.cos((float)((float)Math.toRadians(45.0))));
            this.addStructure(new LOTRWorldGenGondorVillageSign(false).setSignText(this.villageName), -signPos, -signPos + signDisp, 1);
            this.addStructure(new LOTRWorldGenGondorVillageSign(false).setSignText(this.villageName), signPos, -signPos + signDisp, 3);
            this.addStructure(new LOTRWorldGenGondorVillageSign(false).setSignText(this.villageName), -signPos, signPos - signDisp, 1);
            this.addStructure(new LOTRWorldGenGondorVillageSign(false).setSignText(this.villageName), signPos, signPos - signDisp, 3);
            int farmX = 38;
            int farmZ = 17;
            int farmSize = 6;
            if (random.nextBoolean()) {
                this.addStructure(this.getRandomFarm(random), -farmX + farmSize, -farmZ, 1);
            }
            if (random.nextBoolean()) {
                this.addStructure(this.getRandomFarm(random), -farmZ + farmSize, -farmX, 1);
            }
            if (random.nextBoolean()) {
                this.addStructure(this.getRandomFarm(random), farmX - farmSize, -farmZ, 3);
            }
            if (random.nextBoolean()) {
                this.addStructure(this.getRandomFarm(random), farmZ - farmSize, -farmX, 3);
            }
            if (random.nextBoolean()) {
                this.addStructure(this.getRandomFarm(random), -farmX + farmSize, farmZ, 1);
            }
            if (random.nextBoolean()) {
                this.addStructure(this.getRandomFarm(random), farmX - farmSize, farmZ, 3);
            }
        }

        @Override
        public void setupVillageProperties(Random random) {
            this.villageName = LOTRNames.getGondorVillageName(random);
            this.villageType = random.nextInt(4) == 0 ? VillageType.FORT : (random.nextInt(4) == 0 ? VillageType.TOWN : VillageType.VILLAGE);
        }

    }

}

