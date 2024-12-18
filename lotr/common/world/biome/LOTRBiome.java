/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.ColorizerGrass
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$FlowerEntry
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.NoiseGeneratorPerlin
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenDoublePlant
 *  net.minecraft.world.gen.feature.WorldGenTallGrass
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.util.EnumHelper
 */
package lotr.common.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lotr.client.LOTRTickHandlerClient;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRDimension;
import lotr.common.LOTRMod;
import lotr.common.entity.Dragons.entity.LOTREntityDragonAnkalagon;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTREntityAurochs;
import lotr.common.entity.animal.LOTREntityBird;
import lotr.common.entity.animal.LOTREntityButterfly;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.animal.LOTREntityFish;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditOrc;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityWickedDwarf;
import lotr.common.entity.npc.LOTREntityWickedDwarf2;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.biome.LOTRBiomeDecorator;
import lotr.common.world.biome.LOTRBiomeGenAdornland;
import lotr.common.world.biome.LOTRBiomeGenAndrast;
import lotr.common.world.biome.LOTRBiomeGenAnduin;
import lotr.common.world.biome.LOTRBiomeGenAnduinMouth;
import lotr.common.world.biome.LOTRBiomeGenAnduinSource;
import lotr.common.world.biome.LOTRBiomeGenAnduinVale;
import lotr.common.world.biome.LOTRBiomeGenAnfalas;
import lotr.common.world.biome.LOTRBiomeGenAngband;
import lotr.common.world.biome.LOTRBiomeGenAngband2;
import lotr.common.world.biome.LOTRBiomeGenAngle;
import lotr.common.world.biome.LOTRBiomeGenAngmar;
import lotr.common.world.biome.LOTRBiomeGenAngmarMountains;
import lotr.common.world.biome.LOTRBiomeGenBarrowDowns;
import lotr.common.world.biome.LOTRBiomeGenBeach;
import lotr.common.world.biome.LOTRBiomeGenBeornsDomain;
import lotr.common.world.biome.LOTRBiomeGenBizar;
import lotr.common.world.biome.LOTRBiomeGenBlackrootVale;
import lotr.common.world.biome.LOTRBiomeGenBlueMountains;
import lotr.common.world.biome.LOTRBiomeGenBlueMountainsFoothills;
import lotr.common.world.biome.LOTRBiomeGenBreeland;
import lotr.common.world.biome.LOTRBiomeGenBrownLands;
import lotr.common.world.biome.LOTRBiomeGenCelebrant;
import lotr.common.world.biome.LOTRBiomeGenChetwood;
import lotr.common.world.biome.LOTRBiomeGenColdfells;
import lotr.common.world.biome.LOTRBiomeGenDagorlad;
import lotr.common.world.biome.LOTRBiomeGenDale;
import lotr.common.world.biome.LOTRBiomeGenDeadMarshes;
import lotr.common.world.biome.LOTRBiomeGenDimrillDale;
import lotr.common.world.biome.LOTRBiomeGenDolGuldur;
import lotr.common.world.biome.LOTRBiomeGenDorEnErnil;
import lotr.common.world.biome.LOTRBiomeGenDorEnErnilHills;
import lotr.common.world.biome.LOTRBiomeGenDorwinion;
import lotr.common.world.biome.LOTRBiomeGenDorwinionHills;
import lotr.common.world.biome.LOTRBiomeGenDunland;
import lotr.common.world.biome.LOTRBiomeGenDurmeth;
import lotr.common.world.biome.LOTRBiomeGenDurmethMountains;
import lotr.common.world.biome.LOTRBiomeGenEastBight;
import lotr.common.world.biome.LOTRBiomeGenEasternCoasts;
import lotr.common.world.biome.LOTRBiomeGenEasternDesolation;
import lotr.common.world.biome.LOTRBiomeGenEmynMuil;
import lotr.common.world.biome.LOTRBiomeGenEnedwaith;
import lotr.common.world.biome.LOTRBiomeGenEntwashMouth;
import lotr.common.world.biome.LOTRBiomeGenErebor;
import lotr.common.world.biome.LOTRBiomeGenEregion;
import lotr.common.world.biome.LOTRBiomeGenEriador;
import lotr.common.world.biome.LOTRBiomeGenEriadorDowns;
import lotr.common.world.biome.LOTRBiomeGenErynVorn;
import lotr.common.world.biome.LOTRBiomeGenEttenmoors;
import lotr.common.world.biome.LOTRBiomeGenFallForodwaith;
import lotr.common.world.biome.LOTRBiomeGenFangorn;
import lotr.common.world.biome.LOTRBiomeGenFangornClearing;
import lotr.common.world.biome.LOTRBiomeGenFangornWasteland;
import lotr.common.world.biome.LOTRBiomeGenFarHaradArid;
import lotr.common.world.biome.LOTRBiomeGenFarHaradBushland;
import lotr.common.world.biome.LOTRBiomeGenFarHaradCloudForest;
import lotr.common.world.biome.LOTRBiomeGenFarHaradCoast;
import lotr.common.world.biome.LOTRBiomeGenFarHaradForest;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungle;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungleEdge;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungleLake;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungleMountains;
import lotr.common.world.biome.LOTRBiomeGenFarHaradMangrove;
import lotr.common.world.biome.LOTRBiomeGenFarHaradSavannah;
import lotr.common.world.biome.LOTRBiomeGenFarHaradSwamp;
import lotr.common.world.biome.LOTRBiomeGenFarHaradVolcano;
import lotr.common.world.biome.LOTRBiomeGenForestShelter;
import lotr.common.world.biome.LOTRBiomeGenForodwaith;
import lotr.common.world.biome.LOTRBiomeGenForodwaithCoast;
import lotr.common.world.biome.LOTRBiomeGenForodwaithGlacier;
import lotr.common.world.biome.LOTRBiomeGenForodwaithMountains;
import lotr.common.world.biome.LOTRBiomeGenForodwaithMountains2;
import lotr.common.world.biome.LOTRBiomeGenGladdenFields;
import lotr.common.world.biome.LOTRBiomeGenGondor;
import lotr.common.world.biome.LOTRBiomeGenGondorWoodlands;
import lotr.common.world.biome.LOTRBiomeGenGorgoroth;
import lotr.common.world.biome.LOTRBiomeGenGreyMountains;
import lotr.common.world.biome.LOTRBiomeGenGreyMountainsFoothills;
import lotr.common.world.biome.LOTRBiomeGenGulfHarad;
import lotr.common.world.biome.LOTRBiomeGenGulfHaradForest;
import lotr.common.world.biome.LOTRBiomeGenHalfTrollForest;
import lotr.common.world.biome.LOTRBiomeGenHaradMountains;
import lotr.common.world.biome.LOTRBiomeGenHarhudor;
import lotr.common.world.biome.LOTRBiomeGenHarhudorForest;
import lotr.common.world.biome.LOTRBiomeGenHarhudorForestEdge;
import lotr.common.world.biome.LOTRBiomeGenHarnedor;
import lotr.common.world.biome.LOTRBiomeGenHarondor;
import lotr.common.world.biome.LOTRBiomeGenImlothMelui;
import lotr.common.world.biome.LOTRBiomeGenIronHills;
import lotr.common.world.biome.LOTRBiomeGenIthilien;
import lotr.common.world.biome.LOTRBiomeGenIthilienHills;
import lotr.common.world.biome.LOTRBiomeGenIthilienWasteland;
import lotr.common.world.biome.LOTRBiomeGenKanuka;
import lotr.common.world.biome.LOTRBiomeGenKhand;
import lotr.common.world.biome.LOTRBiomeGenKhandHills;
import lotr.common.world.biome.LOTRBiomeGenLake;
import lotr.common.world.biome.LOTRBiomeGenLamedon;
import lotr.common.world.biome.LOTRBiomeGenLamedonHills;
import lotr.common.world.biome.LOTRBiomeGenLastDesert;
import lotr.common.world.biome.LOTRBiomeGenLebennin;
import lotr.common.world.biome.LOTRBiomeGenLindon;
import lotr.common.world.biome.LOTRBiomeGenLindonCoast;
import lotr.common.world.biome.LOTRBiomeGenLindonWoodlands;
import lotr.common.world.biome.LOTRBiomeGenLoneLands;
import lotr.common.world.biome.LOTRBiomeGenLoneLandsHills;
import lotr.common.world.biome.LOTRBiomeGenLongMarshes;
import lotr.common.world.biome.LOTRBiomeGenLossarnach;
import lotr.common.world.biome.LOTRBiomeGenLostladen;
import lotr.common.world.biome.LOTRBiomeGenLothlorien;
import lotr.common.world.biome.LOTRBiomeGenLothlorienEdge;
import lotr.common.world.biome.LOTRBiomeGenMeneltarma;
import lotr.common.world.biome.LOTRBiomeGenMidgewater;
import lotr.common.world.biome.LOTRBiomeGenMinhiriath;
import lotr.common.world.biome.LOTRBiomeGenMirkwoodCorrupted;
import lotr.common.world.biome.LOTRBiomeGenMirkwoodMountains;
import lotr.common.world.biome.LOTRBiomeGenMirkwoodNorth;
import lotr.common.world.biome.LOTRBiomeGenMistyMountains;
import lotr.common.world.biome.LOTRBiomeGenMistyMountainsFoothills;
import lotr.common.world.biome.LOTRBiomeGenMordor;
import lotr.common.world.biome.LOTRBiomeGenMordorMountains;
import lotr.common.world.biome.LOTRBiomeGenMorgulVale;
import lotr.common.world.biome.LOTRBiomeGenMoria;
import lotr.common.world.biome.LOTRBiomeGenNanCurunir;
import lotr.common.world.biome.LOTRBiomeGenNanUngol;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import lotr.common.world.biome.LOTRBiomeGenNearHaradFertile;
import lotr.common.world.biome.LOTRBiomeGenNearHaradFertileForest;
import lotr.common.world.biome.LOTRBiomeGenNearHaradHills;
import lotr.common.world.biome.LOTRBiomeGenNearHaradOasis;
import lotr.common.world.biome.LOTRBiomeGenNearHaradRed;
import lotr.common.world.biome.LOTRBiomeGenNearHaradRiverbank;
import lotr.common.world.biome.LOTRBiomeGenNearHaradSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenNindalf;
import lotr.common.world.biome.LOTRBiomeGenNurn;
import lotr.common.world.biome.LOTRBiomeGenNurnMarshes;
import lotr.common.world.biome.LOTRBiomeGenNurnen;
import lotr.common.world.biome.LOTRBiomeGenOcean;
import lotr.common.world.biome.LOTRBiomeGenOldForest;
import lotr.common.world.biome.LOTRBiomeGenPelargir;
import lotr.common.world.biome.LOTRBiomeGenPelennor;
import lotr.common.world.biome.LOTRBiomeGenPertorogwaith;
import lotr.common.world.biome.LOTRBiomeGenPinnathGelin;
import lotr.common.world.biome.LOTRBiomeGenPukel;
import lotr.common.world.biome.LOTRBiomeGenRedGlacier;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsBlacklock;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsFoothillsBlacklock;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsFoothillsStiffbeard;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsFoothillsStonefoot;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsIronfist;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStiffbeard;
import lotr.common.world.biome.LOTRBiomeGenRedMountainsStonefoot;
import lotr.common.world.biome.LOTRBiomeGenRedSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenRhun;
import lotr.common.world.biome.LOTRBiomeGenRhunForest;
import lotr.common.world.biome.LOTRBiomeGenRhunForest2;
import lotr.common.world.biome.LOTRBiomeGenRhunIsland;
import lotr.common.world.biome.LOTRBiomeGenRhunIslandForest;
import lotr.common.world.biome.LOTRBiomeGenRhunLand;
import lotr.common.world.biome.LOTRBiomeGenRhunLandHills;
import lotr.common.world.biome.LOTRBiomeGenRhunLandSteppe;
import lotr.common.world.biome.LOTRBiomeGenRhunRedForest;
import lotr.common.world.biome.LOTRBiomeGenRhunSemiDesert;
import lotr.common.world.biome.LOTRBiomeGenRivendell;
import lotr.common.world.biome.LOTRBiomeGenRivendellHills;
import lotr.common.world.biome.LOTRBiomeGenRiver;
import lotr.common.world.biome.LOTRBiomeGenRohan;
import lotr.common.world.biome.LOTRBiomeGenRohanGate;
import lotr.common.world.biome.LOTRBiomeGenRohanUruk;
import lotr.common.world.biome.LOTRBiomeGenRohanWoodlands;
import lotr.common.world.biome.LOTRBiomeGenShire;
import lotr.common.world.biome.LOTRBiomeGenShireMarshes;
import lotr.common.world.biome.LOTRBiomeGenShireMoors;
import lotr.common.world.biome.LOTRBiomeGenShireWoodlands;
import lotr.common.world.biome.LOTRBiomeGenSouthMirkwoodCorrupted;
import lotr.common.world.biome.LOTRBiomeGenSwanfleet;
import lotr.common.world.biome.LOTRBiomeGenTaiga;
import lotr.common.world.biome.LOTRBiomeGenTaiga2;
import lotr.common.world.biome.LOTRBiomeGenTaigaRhun;
import lotr.common.world.biome.LOTRBiomeGenTauredainClearing;
import lotr.common.world.biome.LOTRBiomeGenTolfalas;
import lotr.common.world.biome.LOTRBiomeGenTowerHills;
import lotr.common.world.biome.LOTRBiomeGenTrollshaws;
import lotr.common.world.biome.LOTRBiomeGenTundra;
import lotr.common.world.biome.LOTRBiomeGenTundra2;
import lotr.common.world.biome.LOTRBiomeGenTundra3;
import lotr.common.world.biome.LOTRBiomeGenTundra4;
import lotr.common.world.biome.LOTRBiomeGenUdun;
import lotr.common.world.biome.LOTRBiomeGenUmbar;
import lotr.common.world.biome.LOTRBiomeGenUmbarForest;
import lotr.common.world.biome.LOTRBiomeGenUnchartedRhun;
import lotr.common.world.biome.LOTRBiomeGenUtumno;
import lotr.common.world.biome.LOTRBiomeGenWastelandRhun;
import lotr.common.world.biome.LOTRBiomeGenWhiteDowns;
import lotr.common.world.biome.LOTRBiomeGenWhiteMountains;
import lotr.common.world.biome.LOTRBiomeGenWhiteMountainsFoothills;
import lotr.common.world.biome.LOTRBiomeGenWilderland;
import lotr.common.world.biome.LOTRBiomeGenWilderlandNorth;
import lotr.common.world.biome.LOTRBiomeGenWindMountains;
import lotr.common.world.biome.LOTRBiomeGenWindMountainsFoothills;
import lotr.common.world.biome.LOTRBiomeGenWold;
import lotr.common.world.biome.LOTRBiomeGenWoodlandRealm;
import lotr.common.world.biome.LOTRBiomeGenWoodlandRealmHills;
import lotr.common.world.biome.LOTRBiomeSunLands;
import lotr.common.world.biome.LOTRMusicRegion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.biome.variant.LOTRBiomeVariantList;
import lotr.common.world.biome.variant.LOTRBiomeVariantStorage;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeInvasionSpawns;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.EnumHelper;

public abstract class LOTRBiome
extends BiomeGenBase {
    private static Class[][] correctCreatureTypeParams = new Class[][]{{EnumCreatureType.class, Class.class, Integer.TYPE, Material.class, Boolean.TYPE, Boolean.TYPE}};
    public static EnumCreatureType creatureType_LOTRAmbient = (EnumCreatureType)EnumHelper.addEnum((Class[][])correctCreatureTypeParams, EnumCreatureType.class, (String)"LOTRAmbient", (Object[])new Object[]{LOTRAmbientCreature.class, 45, Material.air, true, false});
    public static LOTRBiome river;
    public static LOTRBiome rohan;
    public static LOTRBiome mistyMountains;
    public static LOTRBiome shire;
    public static LOTRBiome shireWoodlands;
    public static LOTRBiome mordor;
    public static LOTRBiome mordorMountains;
    public static LOTRBiome gondor;
    public static LOTRBiome whiteMountains;
    public static LOTRBiome lothlorien;
    public static LOTRBiome celebrant;
    public static LOTRBiome ironHills;
    public static LOTRBiome deadMarshes;
    public static LOTRBiome trollshaws;
    public static LOTRBiome woodlandRealm;
    public static LOTRBiome mirkwoodCorrupted;
    public static LOTRBiome rohanUrukHighlands;
    public static LOTRBiome emynMuil;
    public static LOTRBiome ithilien;
    public static LOTRBiome pelargir;
    public static LOTRBiome loneLands;
    public static LOTRBiome loneLandsHills;
    public static LOTRBiome dunland;
    public static LOTRBiome fangorn;
    public static LOTRBiome angle;
    public static LOTRBiome ettenmoors;
    public static LOTRBiome oldForest;
    public static LOTRBiome harondor;
    public static LOTRBiome eriador;
    public static LOTRBiome eriadorDowns;
    public static LOTRBiome erynVorn;
    public static LOTRBiome greyMountains;
    public static LOTRBiome midgewater;
    public static LOTRBiome brownLands;
    public static LOTRBiome ocean;
    public static LOTRBiome anduinHills;
    public static LOTRBiome meneltarma;
    public static LOTRBiome gladdenFields;
    public static LOTRBiome lothlorienEdge;
    public static LOTRBiome forodwaith;
    public static LOTRBiome enedwaith;
    public static LOTRBiome angmar;
    public static LOTRBiome eregion;
    public static LOTRBiome lindon;
    public static LOTRBiome lindonWoodlands;
    public static LOTRBiome eastBight;
    public static LOTRBiome blueMountains;
    public static LOTRBiome mirkwoodMountains;
    public static LOTRBiome wilderland;
    public static LOTRBiome dagorlad;
    public static LOTRBiome nurn;
    public static LOTRBiome nurnen;
    public static LOTRBiome nurnMarshes;
    public static LOTRBiome angmarMountains;
    public static LOTRBiome anduinMouth;
    public static LOTRBiome entwashMouth;
    public static LOTRBiome dorEnErnil;
    public static LOTRBiome dorEnErnilHills;
    public static LOTRBiome fangornWasteland;
    public static LOTRBiome rohanWoodlands;
    public static LOTRBiome gondorWoodlands;
    public static LOTRBiome lake;
    public static LOTRBiome lindonCoast;
    public static LOTRBiome barrowDowns;
    public static LOTRBiome longMarshes;
    public static LOTRBiome fangornClearing;
    public static LOTRBiome ithilienHills;
    public static LOTRBiome ithilienWasteland;
    public static LOTRBiome nindalf;
    public static LOTRBiome coldfells;
    public static LOTRBiome nanCurunir;
    public static LOTRBiome adornland;
    public static LOTRBiome whiteDowns;
    public static LOTRBiome swanfleet;
    public static LOTRBiome pelennor;
    public static LOTRBiome minhiriath;
    public static LOTRBiome erebor;
    public static LOTRBiome mirkwoodNorth;
    public static LOTRBiome woodlandRealmHills;
    public static LOTRBiome nanUngol;
    public static LOTRBiome pinnathGelin;
    public static LOTRBiome island;
    public static LOTRBiome forodwaithMountains;
    public static LOTRBiome forodwaithMountains2;
    public static LOTRBiome mistyMountainsFoothills;
    public static LOTRBiome greyMountainsFoothills;
    public static LOTRBiome blueMountainsFoothills;
    public static LOTRBiome tundra;
    public static LOTRBiome taiga;
    public static LOTRBiome notDeepOcean;
    public static LOTRBiome middleDeepOcean;
    public static LOTRBiome rhunSemiDesert;
    public static LOTRBiome nearHaradRedSemiDesert;
    public static LOTRBiome anfalas;
    public static LOTRBiome breeland;
    public static LOTRBiome chetwood;
    public static LOTRBiome forodwaithGlacier;
    public static LOTRBiome redGlacier;
    public static LOTRBiome whiteMountainsFoothills;
    public static LOTRBiome beach;
    public static LOTRBiome beachGravel;
    public static LOTRBiome nearHarad;
    public static LOTRBiome farHarad;
    public static LOTRBiome haradMountains;
    public static LOTRBiome umbar;
    public static LOTRBiome farHaradJungle;
    public static LOTRBiome umbarHills;
    public static LOTRBiome nearHaradHills;
    public static LOTRBiome farHaradJungleLake;
    public static LOTRBiome lostladen;
    public static LOTRBiome farHaradForest;
    public static LOTRBiome nearHaradFertile;
    public static LOTRBiome pertorogwaith;
    public static LOTRBiome umbarForest;
    public static LOTRBiome farHaradJungleEdge;
    public static LOTRBiome tauredainClearing;
    public static LOTRBiome gulfHarad;
    public static LOTRBiome dorwinionHills;
    public static LOTRBiome tolfalas;
    public static LOTRBiome lebennin;
    public static LOTRBiome rhun;
    public static LOTRBiome rhunForest;
    public static LOTRBiome rhunForest2;
    public static LOTRBiome redMountains;
    public static LOTRBiome redMountains2;
    public static LOTRBiome redMountains3;
    public static LOTRBiome redMountains4;
    public static LOTRBiome redMountainsFoothills;
    public static LOTRBiome redMountainsFoothills2;
    public static LOTRBiome redMountainsFoothills3;
    public static LOTRBiome dolGuldur;
    public static LOTRBiome nearHaradSemiDesert;
    public static LOTRBiome farHaradArid;
    public static LOTRBiome farHaradAridHills;
    public static LOTRBiome farHaradSwamp;
    public static LOTRBiome farHaradCloudForest;
    public static LOTRBiome farHaradBushland;
    public static LOTRBiome farHaradBushlandHills;
    public static LOTRBiome farHaradMangrove;
    public static LOTRBiome nearHaradFertileForest;
    public static LOTRBiome anduinVale;
    public static LOTRBiome wold;
    public static LOTRBiome shireMoors;
    public static LOTRBiome shireMarshes;
    public static LOTRBiome nearHaradRedDesert;
    public static LOTRBiome farHaradVolcano;
    public static LOTRBiome udun;
    public static LOTRBiome gorgoroth;
    public static LOTRBiome morgulVale;
    public static LOTRBiome easternDesolation;
    public static LOTRBiome dale;
    public static LOTRBiome dorwinion;
    public static LOTRBiome towerHills;
    public static LOTRBiome gulfHaradForest;
    public static LOTRBiome wilderlandNorth;
    public static LOTRBiome forodwaithCoast;
    public static LOTRBiome farHaradCoast;
    public static LOTRBiome nearHaradRiverbank;
    public static LOTRBiome lossarnach;
    public static LOTRBiome imlothMelui;
    public static LOTRBiome nearHaradOasis;
    public static LOTRBiome beachWhite;
    public static LOTRBiome harnedor;
    public static LOTRBiome lamedon;
    public static LOTRBiome lamedonHills;
    public static LOTRBiome blackrootVale;
    public static LOTRBiome andrast;
    public static LOTRBiome pukel;
    public static LOTRBiome rhunLand;
    public static LOTRBiome rhunLandSteppe;
    public static LOTRBiome rhunLandHills;
    public static LOTRBiome rhunRedForest;
    public static LOTRBiome rhunIsland;
    public static LOTRBiome rhunIslandForest;
    public static LOTRBiome lastDesert;
    public static LOTRBiome windMountains;
    public static LOTRBiome windMountainsFoothills;
    public static LOTRBiome rivendell;
    public static LOTRBiome rivendellHills;
    public static LOTRBiome farHaradJungleMountains;
    public static LOTRBiome halfTrollForest;
    public static LOTRBiome farHaradKanuka;
    public static LOTRBiome moria;
    public static LOTRBiome utumno;
    public static LOTRBiome angbandMountains;
    public static LOTRBiome angband;
    public static LOTRBiome uncharted_rhun;
    public static LOTRBiome eastern_coasts;
    public static LOTRBiome harhudor;
    public static LOTRBiome wasteland_rhun;
    public static LOTRBiome durmeth;
    public static LOTRBiome durmeth_mountains;
    public static LOTRBiome taiga_rhun;
    public static LOTRBiome bizar;
    public static LOTRBiome sunLands;
    public static LOTRBiome tundrarhun;
    public static LOTRBiome dimrill;
    public static LOTRBiome fallforodwaith;
    public static LOTRBiome forestShelter;
    public static LOTRBiome southMirkwood;
    public static LOTRBiome beornsDomain;
    public static LOTRBiome anduinSource;
    public static LOTRBiome harhudorForest;
    public static LOTRBiome harhudorForestEdge;
    public static LOTRBiome rohanGate;
    public static LOTRBiome iceugol;
    public static LOTRBiome snowtaiga;
    public static LOTRBiome forohel;
    public static LOTRBiome snowtundra;
    public static LOTRBiome khand;
    public static LOTRBiome khandHills;
    public LOTRDimension biomeDimension;
    public LOTRBiomeDecorator decorator;
    public int topBlockMeta = 0;
    public int fillerBlockMeta = 0;
    public float heightBaseParameter;
    public static NoiseGeneratorPerlin biomeTerrainNoise;
    protected static Random terrainRand;
    protected boolean enablePodzol = true;
    protected boolean enableRocky = false;
    private LOTRBiomeVariantList biomeVariantsSmall = new LOTRBiomeVariantList();
    private LOTRBiomeVariantList biomeVariantsLarge = new LOTRBiomeVariantList();
    public float variantChance = 0.4f;
    public LOTRBiomeSpawnList npcSpawnList = new LOTRBiomeSpawnList(this);
    protected List spawnableLOTRAmbientList = new ArrayList();
    private List spawnableTraders = new ArrayList();
    private LOTREventSpawner.EventChance banditChance;
    private LOTREventSpawner.EventChance dragonChance;
    private LOTREventSpawner.EventChance banditChance1;
    private Class<? extends LOTREntityBandit> banditEntityClass;
    private Class<? extends EntityDragon> dragonEntityClass;
    private Class<? extends LOTREntityBanditOrc> banditEntityClass1;
    public final LOTRBiomeInvasionSpawns invasionSpawns;
    public BiomeColors biomeColors = new BiomeColors(this);
    public BiomeTerrain biomeTerrain = new BiomeTerrain(this);
    private boolean initDwarven = false;
    private boolean isDwarven;
    private static Color waterColorNorth;
    private static Color waterColorSouth;
    private static int waterLimitNorth;
    private static int waterLimitSouth;

    public static void initBiomes() {
        river = new LOTRBiomeGenRiver(0, false).setMinMaxHeight(-0.5f, 0.0f).setColor(3570869).setBiomeName("river");
        rohan = new LOTRBiomeGenRohan(1, true).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.2f, 0.15f).setColor(7384389).setBiomeName("rohan");
        mistyMountains = new LOTRBiomeGenMistyMountains(2, true).setTemperatureRainfall(0.2f, 0.5f).setMinMaxHeight(2.0f, 2.0f).setColor(15263713).setBiomeName("mistyMountains");
        shire = new LOTRBiomeGenShire(3, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.15f, 0.3f).setColor(6794549).setBiomeName("shire");
        shireWoodlands = new LOTRBiomeGenShireWoodlands(4, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.3f, 0.5f).setColor(4486966).setBiomeName("shireWoodlands");
        mordor = new LOTRBiomeGenMordor(5, true).setTemperatureRainfall(2.0f, 0.0f).setMinMaxHeight(0.3f, 0.5f).setColor(1118222).setBiomeName("mordor");
        mordorMountains = new LOTRBiomeGenMordorMountains(6, true).setTemperatureRainfall(2.0f, 0.0f).setMinMaxHeight(2.0f, 3.0f).setColor(5328200).setBiomeName("mordorMountains");
        gondor = new LOTRBiomeGenGondor(7, true).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.1f, 0.15f).setColor(8959045).setBiomeName("gondor");
        whiteMountains = new LOTRBiomeGenWhiteMountains(8, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(1.5f, 2.0f).setColor(15066600).setBiomeName("whiteMountains");
        lothlorien = new LOTRBiomeGenLothlorien(9, true).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.1f, 0.3f).setColor(16504895).setBiomeName("lothlorien");
        celebrant = new LOTRBiomeGenCelebrant(10, true).setTemperatureRainfall(1.1f, 1.1f).setMinMaxHeight(0.1f, 0.05f).setColor(7647046).setBiomeName("celebrant");
        ironHills = new LOTRBiomeGenIronHills(11, true).setTemperatureRainfall(0.27f, 0.4f).setMinMaxHeight(0.3f, 1.4f).setColor(9142093).setBiomeName("ironHills");
        deadMarshes = new LOTRBiomeGenDeadMarshes(12, true).setTemperatureRainfall(0.4f, 1.0f).setMinMaxHeight(0.0f, 0.1f).setColor(7303999).setBiomeName("deadMarshes");
        trollshaws = new LOTRBiomeGenTrollshaws(13, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(0.15f, 1.0f).setColor(5798959).setBiomeName("trollshaws");
        woodlandRealm = new LOTRBiomeGenWoodlandRealm(14, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.2f, 0.3f).setColor(4089126).setBiomeName("woodlandRealm");
        mirkwoodCorrupted = new LOTRBiomeGenMirkwoodCorrupted(15, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(0.2f, 0.4f).setColor(3032091).setBiomeName("mirkwoodCorrupted");
        rohanUrukHighlands = new LOTRBiomeGenRohanUruk(16, true).setTemperatureRainfall(0.7f, 0.4f).setMinMaxHeight(0.8f, 0.3f).setColor(8295258).setBiomeName("rohanUrukHighlands");
        emynMuil = new LOTRBiomeGenEmynMuil(17, true).setTemperatureRainfall(0.5f, 0.9f).setMinMaxHeight(0.2f, 0.8f).setColor(9866354).setBiomeName("emynMuil");
        ithilien = new LOTRBiomeGenIthilien(18, true).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.15f, 0.5f).setColor(7710516).setBiomeName("ithilien");
        pelargir = new LOTRBiomeGenPelargir(19, true).setTemperatureRainfall(1.0f, 1.0f).setMinMaxHeight(0.08f, 0.2f).setColor(11256145).setBiomeName("pelargir");
        loneLands = new LOTRBiomeGenLoneLands(21, true).setTemperatureRainfall(0.6f, 0.5f).setMinMaxHeight(0.15f, 0.4f).setColor(8562762).setBiomeName("loneLands");
        loneLandsHills = new LOTRBiomeGenLoneLandsHills(22, false).setTemperatureRainfall(0.6f, 0.5f).setMinMaxHeight(0.6f, 0.8f).setColor(8687182).setBiomeName("loneLandsHills");
        dunland = new LOTRBiomeGenDunland(23, true).setTemperatureRainfall(0.4f, 0.7f).setMinMaxHeight(0.3f, 0.5f).setColor(6920524).setBiomeName("dunland");
        fangorn = new LOTRBiomeGenFangorn(24, true).setTemperatureRainfall(0.7f, 0.8f).setMinMaxHeight(0.2f, 0.4f).setColor(4355353).setBiomeName("fangorn");
        angle = new LOTRBiomeGenAngle(25, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(0.15f, 0.3f).setColor(9416527).setBiomeName("angle");
        ettenmoors = new LOTRBiomeGenEttenmoors(26, true).setTemperatureRainfall(0.2f, 0.6f).setMinMaxHeight(0.5f, 0.6f).setColor(8161626).setBiomeName("ettenmoors");
        oldForest = new LOTRBiomeGenOldForest(27, true).setTemperatureRainfall(0.5f, 1.0f).setMinMaxHeight(0.2f, 0.3f).setColor(4551995).setBiomeName("oldForest");
        harondor = new LOTRBiomeGenHarondor(28, true).setTemperatureRainfall(1.0f, 0.6f).setMinMaxHeight(0.2f, 0.3f).setColor(10663238).setBiomeName("harondor");
        eriador = new LOTRBiomeGenEriador(29, true).setTemperatureRainfall(0.9f, 0.8f).setMinMaxHeight(0.1f, 0.4f).setColor(7054916).setBiomeName("eriador");
        eriadorDowns = new LOTRBiomeGenEriadorDowns(30, true).setTemperatureRainfall(0.6f, 0.7f).setMinMaxHeight(0.5f, 0.5f).setColor(7638087).setBiomeName("eriadorDowns");
        erynVorn = new LOTRBiomeGenErynVorn(31, false).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.1f, 0.4f).setColor(4357965).setBiomeName("erynVorn");
        greyMountains = new LOTRBiomeGenGreyMountains(32, true).setTemperatureRainfall(0.28f, 0.2f).setMinMaxHeight(1.8f, 2.0f).setColor(13290689).setBiomeName("greyMountains");
        midgewater = new LOTRBiomeGenMidgewater(33, true).setTemperatureRainfall(0.6f, 1.0f).setMinMaxHeight(0.0f, 0.1f).setColor(6001495).setBiomeName("midgewater");
        brownLands = new LOTRBiomeGenBrownLands(34, true).setTemperatureRainfall(1.0f, 0.2f).setMinMaxHeight(0.2f, 0.2f).setColor(8552016).setBiomeName("brownLands");
        ocean = new LOTRBiomeGenOcean(35, false).setTemperatureRainfall(0.8f, 0.7f).setMinMaxHeight(-1.0f, 0.3f).setColor(152962).setBiomeName("ocean");
        anduinHills = new LOTRBiomeGenAnduin(36, true).setTemperatureRainfall(0.7f, 0.7f).setMinMaxHeight(0.6f, 0.4f).setColor(7058012).setBiomeName("anduinHills");
        meneltarma = new LOTRBiomeGenMeneltarma(37, false).setTemperatureRainfall(0.9f, 0.8f).setMinMaxHeight(0.1f, 0.2f).setColor(9549658).setBiomeName("meneltarma");
        gladdenFields = new LOTRBiomeGenGladdenFields(38, true).setTemperatureRainfall(0.6f, 1.2f).setMinMaxHeight(0.0f, 0.1f).setColor(5020505).setBiomeName("gladdenFields");
        lothlorienEdge = new LOTRBiomeGenLothlorienEdge(39, true).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.1f, 0.2f).setColor(13944387).setBiomeName("lothlorienEdge");
        forodwaith = new LOTRBiomeGenForodwaith(40, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(0.1f, 0.1f).setColor(14211282).setBiomeName("forodwaith");
        enedwaith = new LOTRBiomeGenEnedwaith(41, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(0.2f, 0.3f).setColor(8038479).setBiomeName("enedwaith");
        angmar = new LOTRBiomeGenAngmar(42, true).setTemperatureRainfall(0.2f, 0.2f).setMinMaxHeight(0.2f, 0.6f).setColor(5523247).setBiomeName("angmar");
        eregion = new LOTRBiomeGenEregion(43, true).setTemperatureRainfall(0.6f, 0.7f).setMinMaxHeight(0.2f, 0.3f).setColor(6656072).setBiomeName("eregion");
        lindon = new LOTRBiomeGenLindon(44, true).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.15f, 0.2f).setColor(7646533).setBiomeName("lindon");
        lindonWoodlands = new LOTRBiomeGenLindonWoodlands(45, false).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.2f, 0.5f).setColor(1996591).setBiomeName("lindonWoodlands");
        eastBight = new LOTRBiomeGenEastBight(46, true).setTemperatureRainfall(0.8f, 0.3f).setMinMaxHeight(0.15f, 0.05f).setColor(9082205).setBiomeName("eastBight");
        blueMountains = new LOTRBiomeGenBlueMountains(47, true).setTemperatureRainfall(0.22f, 0.8f).setMinMaxHeight(1.0f, 2.5f).setColor(13228770).setBiomeName("blueMountains");
        mirkwoodMountains = new LOTRBiomeGenMirkwoodMountains(48, true).setTemperatureRainfall(0.28f, 0.9f).setMinMaxHeight(1.2f, 1.5f).setColor(2632989).setBiomeName("mirkwoodMountains");
        wilderland = new LOTRBiomeGenWilderland(49, true).setTemperatureRainfall(0.9f, 0.4f).setMinMaxHeight(0.2f, 0.4f).setColor(9612368).setBiomeName("wilderland");
        dagorlad = new LOTRBiomeGenDagorlad(50, true).setTemperatureRainfall(1.0f, 0.2f).setMinMaxHeight(0.1f, 0.05f).setColor(7036741).setBiomeName("dagorlad");
        nurn = new LOTRBiomeGenNurn(51, true).setTemperatureRainfall(0.9f, 0.4f).setMinMaxHeight(0.1f, 0.2f).setColor(2630683).setBiomeName("nurn");
        nurnen = new LOTRBiomeGenNurnen(52, false).setTemperatureRainfall(0.9f, 0.4f).setMinMaxHeight(-1.0f, 0.3f).setColor(931414).setBiomeName("nurnen");
        nurnMarshes = new LOTRBiomeGenNurnMarshes(53, true).setTemperatureRainfall(0.9f, 0.4f).setMinMaxHeight(0.0f, 0.1f).setColor(4012843).setBiomeName("nurnMarshes");
        adornland = new LOTRBiomeGenAdornland(54, true).setTemperatureRainfall(0.7f, 0.6f).setMinMaxHeight(0.2f, 0.2f).setColor(7838543).setBiomeName("adornland");
        angmarMountains = new LOTRBiomeGenAngmarMountains(55, true).setTemperatureRainfall(0.25f, 0.1f).setMinMaxHeight(1.6f, 1.5f).setColor(13619147).setBiomeName("angmarMountains");
        anduinMouth = new LOTRBiomeGenAnduinMouth(56, true).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.0f, 0.1f).setColor(5089363).setBiomeName("anduinMouth");
        entwashMouth = new LOTRBiomeGenEntwashMouth(57, true).setTemperatureRainfall(1.0f, 0.5f).setMinMaxHeight(0.0f, 0.1f).setColor(5612358).setBiomeName("entwashMouth");
        dorEnErnil = new LOTRBiomeGenDorEnErnil(58, true).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.07f, 0.2f).setColor(9355077).setBiomeName("dorEnErnil");
        dorEnErnilHills = new LOTRBiomeGenDorEnErnilHills(59, false).setTemperatureRainfall(0.8f, 0.7f).setMinMaxHeight(0.5f, 0.5f).setColor(8560707).setBiomeName("dorEnErnilHills");
        fangornWasteland = new LOTRBiomeGenFangornWasteland(60, true).setTemperatureRainfall(0.7f, 0.4f).setMinMaxHeight(0.2f, 0.4f).setColor(6782028).setBiomeName("fangornWasteland");
        rohanWoodlands = new LOTRBiomeGenRohanWoodlands(61, false).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.2f, 0.4f).setColor(5736246).setBiomeName("rohanWoodlands");
        gondorWoodlands = new LOTRBiomeGenGondorWoodlands(62, false).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.2f, 0.2f).setColor(5867307).setBiomeName("gondorWoodlands");
        lake = new LOTRBiomeGenLake(63, false).setColor(3433630).setBiomeName("lake");
        lindonCoast = new LOTRBiomeGenLindonCoast(64, false).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.0f, 0.5f).setColor(9278870).setBiomeName("lindonCoast");
        barrowDowns = new LOTRBiomeGenBarrowDowns(65, true).setTemperatureRainfall(0.6f, 0.7f).setMinMaxHeight(0.3f, 0.4f).setColor(8097362).setBiomeName("barrowDowns");
        longMarshes = new LOTRBiomeGenLongMarshes(66, true).setTemperatureRainfall(0.6f, 0.9f).setMinMaxHeight(0.0f, 0.1f).setColor(7178054).setBiomeName("longMarshes");
        fangornClearing = new LOTRBiomeGenFangornClearing(67, false).setTemperatureRainfall(0.7f, 0.8f).setMinMaxHeight(0.2f, 0.1f).setColor(5877050).setBiomeName("fangornClearing");
        ithilienHills = new LOTRBiomeGenIthilienHills(68, false).setTemperatureRainfall(0.7f, 0.7f).setMinMaxHeight(0.6f, 0.6f).setColor(6985792).setBiomeName("ithilienHills");
        ithilienWasteland = new LOTRBiomeGenIthilienWasteland(69, true).setTemperatureRainfall(0.6f, 0.6f).setMinMaxHeight(0.15f, 0.2f).setColor(8030031).setBiomeName("ithilienWasteland");
        nindalf = new LOTRBiomeGenNindalf(70, true).setTemperatureRainfall(0.4f, 1.0f).setMinMaxHeight(0.0f, 0.1f).setColor(7111750).setBiomeName("nindalf");
        coldfells = new LOTRBiomeGenColdfells(71, true).setTemperatureRainfall(0.25f, 0.8f).setMinMaxHeight(0.4f, 0.8f).setColor(8296018).setBiomeName("coldfells");
        nanCurunir = new LOTRBiomeGenNanCurunir(72, true).setTemperatureRainfall(0.6f, 0.4f).setMinMaxHeight(0.2f, 0.1f).setColor(7109714).setBiomeName("nanCurunir");
        whiteDowns = new LOTRBiomeGenWhiteDowns(74, true).setTemperatureRainfall(0.6f, 0.7f).setMinMaxHeight(0.6f, 0.6f).setColor(10210937).setBiomeName("whiteDowns");
        swanfleet = new LOTRBiomeGenSwanfleet(75, true).setTemperatureRainfall(0.8f, 1.0f).setMinMaxHeight(0.0f, 0.1f).setColor(6265945).setBiomeName("swanfleet");
        pelennor = new LOTRBiomeGenPelennor(76, true).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.1f, 0.02f).setColor(11258955).setBiomeName("pelennor");
        minhiriath = new LOTRBiomeGenMinhiriath(77, true).setTemperatureRainfall(0.7f, 0.4f).setMinMaxHeight(0.1f, 0.2f).setColor(7380550).setBiomeName("minhiriath");
        erebor = new LOTRBiomeGenErebor(78, true).setTemperatureRainfall(0.6f, 0.7f).setMinMaxHeight(0.4f, 0.6f).setColor(7499093).setBiomeName("erebor");
        mirkwoodNorth = new LOTRBiomeGenMirkwoodNorth(79, true).setTemperatureRainfall(0.7f, 0.7f).setMinMaxHeight(0.2f, 0.4f).setColor(3822115).setBiomeName("mirkwoodNorth");
        woodlandRealmHills = new LOTRBiomeGenWoodlandRealmHills(80, false).setTemperatureRainfall(0.8f, 0.6f).setMinMaxHeight(0.9f, 0.7f).setColor(3624991).setBiomeName("woodlandRealmHills");
        nanUngol = new LOTRBiomeGenNanUngol(81, true).setTemperatureRainfall(2.0f, 0.0f).setMinMaxHeight(0.1f, 0.4f).setColor(656641).setBiomeName("nanUngol");
        pinnathGelin = new LOTRBiomeGenPinnathGelin(82, false).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.5f, 0.5f).setColor(9946693).setBiomeName("pinnathGelin");
        island = new LOTRBiomeGenOcean(83, false).setTemperatureRainfall(0.9f, 0.8f).setMinMaxHeight(0.0f, 0.3f).setColor(10138963).setBiomeName("island");
        forodwaithMountains = new LOTRBiomeGenForodwaithMountains(84, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(2.0f, 2.0f).setColor(15592942).setBiomeName("forodwaithMountains");
        mistyMountainsFoothills = new LOTRBiomeGenMistyMountainsFoothills(85, true).setTemperatureRainfall(0.25f, 0.6f).setMinMaxHeight(0.7f, 0.9f).setColor(12501430).setBiomeName("mistyMountainsFoothills");
        greyMountainsFoothills = new LOTRBiomeGenGreyMountainsFoothills(86, true).setTemperatureRainfall(0.5f, 0.7f).setMinMaxHeight(0.5f, 0.9f).setColor(9148000).setBiomeName("greyMountainsFoothills");
        blueMountainsFoothills = new LOTRBiomeGenBlueMountainsFoothills(87, true).setTemperatureRainfall(0.5f, 0.8f).setMinMaxHeight(0.5f, 0.9f).setColor(11253170).setBiomeName("blueMountainsFoothills");
        tundra = new LOTRBiomeGenTundra(88, true).setTemperatureRainfall(0.1f, 0.3f).setMinMaxHeight(0.1f, 0.2f).setColor(12366486).setBiomeName("tundra");
        taiga = new LOTRBiomeGenTaiga(89, true).setTemperatureRainfall(0.1f, 0.7f).setMinMaxHeight(0.1f, 0.5f).setColor(6526543).setBiomeName("taiga");
        breeland = new LOTRBiomeGenBreeland(90, true).setTemperatureRainfall(0.8f, 0.7f).setMinMaxHeight(0.1f, 0.2f).setColor(6861625).setBiomeName("breeland");
        chetwood = new LOTRBiomeGenChetwood(91, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.2f, 0.4f).setColor(4424477).setBiomeName("chetwood");
        forodwaithGlacier = new LOTRBiomeGenForodwaithGlacier(92, true).setTemperatureRainfall(0.0f, 0.1f).setMinMaxHeight(1.0f, 0.1f).setColor(9424096).setBiomeName("forodwaithGlacier");
        whiteMountainsFoothills = new LOTRBiomeGenWhiteMountainsFoothills(93, true).setTemperatureRainfall(0.6f, 0.7f).setMinMaxHeight(0.5f, 0.9f).setColor(12635575).setBiomeName("whiteMountainsFoothills");
        beach = new LOTRBiomeGenBeach(94, false).setBeachBlock((Block)Blocks.sand, 0).setColor(14404247).setBiomeName("beach");
        beachGravel = new LOTRBiomeGenBeach(95, false).setBeachBlock(Blocks.gravel, 0).setColor(9868704).setBiomeName("beachGravel");
        nearHarad = new LOTRBiomeGenNearHarad(96, true).setTemperatureRainfall(1.5f, 0.1f).setMinMaxHeight(0.2f, 0.1f).setColor(14205815).setBiomeName("nearHarad");
        farHarad = new LOTRBiomeGenFarHaradSavannah(97, true).setTemperatureRainfall(1.2f, 0.2f).setMinMaxHeight(0.1f, 0.1f).setColor(9740353).setBiomeName("farHarad");
        haradMountains = new LOTRBiomeGenHaradMountains(98, true).setTemperatureRainfall(0.9f, 0.5f).setMinMaxHeight(1.8f, 2.0f).setColor(9867381).setBiomeName("haradMountains");
        umbar = new LOTRBiomeGenUmbar(99, true).setTemperatureRainfall(0.9f, 0.6f).setMinMaxHeight(0.1f, 0.2f).setColor(9542740).setBiomeName("umbar");
        farHaradJungle = new LOTRBiomeGenFarHaradJungle(100, true).setTemperatureRainfall(1.2f, 0.9f).setMinMaxHeight(0.2f, 0.4f).setColor(4944931).setBiomeName("farHaradJungle");
        umbarHills = new LOTRBiomeGenUmbar(101, false).setTemperatureRainfall(0.8f, 0.5f).setMinMaxHeight(1.2f, 0.8f).setColor(8226378).setBiomeName("umbarHills");
        nearHaradHills = new LOTRBiomeGenNearHaradHills(102, false).setTemperatureRainfall(1.2f, 0.3f).setMinMaxHeight(0.5f, 0.8f).setColor(12167010).setBiomeName("nearHaradHills");
        farHaradJungleLake = new LOTRBiomeGenFarHaradJungleLake(103, false).setTemperatureRainfall(1.2f, 0.9f).setMinMaxHeight(-0.5f, 0.2f).setColor(2271948).setBiomeName("farHaradJungleLake");
        lostladen = new LOTRBiomeGenLostladen(104, true).setTemperatureRainfall(1.2f, 0.2f).setMinMaxHeight(0.2f, 0.1f).setColor(10658661).setBiomeName("lostladen");
        farHaradForest = new LOTRBiomeGenFarHaradForest(105, true).setTemperatureRainfall(1.0f, 1.0f).setMinMaxHeight(0.3f, 0.4f).setColor(3703325).setBiomeName("farHaradForest");
        nearHaradFertile = new LOTRBiomeGenNearHaradFertile(106, true).setTemperatureRainfall(1.2f, 0.7f).setMinMaxHeight(0.2f, 0.1f).setColor(10398286).setBiomeName("nearHaradFertile");
        pertorogwaith = new LOTRBiomeGenPertorogwaith(107, true).setTemperatureRainfall(0.7f, 0.1f).setMinMaxHeight(0.2f, 0.5f).setColor(8879706).setBiomeName("pertorogwaith");
        umbarForest = new LOTRBiomeGenUmbarForest(108, false).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.2f, 0.3f).setColor(7178042).setBiomeName("umbarForest");
        farHaradJungleEdge = new LOTRBiomeGenFarHaradJungleEdge(109, true).setTemperatureRainfall(1.2f, 0.8f).setMinMaxHeight(0.2f, 0.2f).setColor(7440430).setBiomeName("farHaradJungleEdge");
        tauredainClearing = new LOTRBiomeGenTauredainClearing(110, true).setTemperatureRainfall(1.2f, 0.8f).setMinMaxHeight(0.2f, 0.2f).setColor(10796101).setBiomeName("tauredainClearing");
        gulfHarad = new LOTRBiomeGenGulfHarad(111, true).setTemperatureRainfall(1.0f, 0.5f).setMinMaxHeight(0.15f, 0.1f).setColor(9152592).setBiomeName("gulfHarad");
        dorwinionHills = new LOTRBiomeGenDorwinionHills(112, true).setTemperatureRainfall(0.9f, 0.8f).setMinMaxHeight(0.8f, 0.8f).setColor(13357993).setBiomeName("dorwinionHills");
        tolfalas = new LOTRBiomeGenTolfalas(113, true).setTemperatureRainfall(0.8f, 0.4f).setMinMaxHeight(0.3f, 1.0f).setColor(10199149).setBiomeName("tolfalas");
        lebennin = new LOTRBiomeGenLebennin(114, true).setTemperatureRainfall(1.0f, 0.9f).setMinMaxHeight(0.1f, 0.3f).setColor(7845418).setBiomeName("lebennin");
        rhun = new LOTRBiomeGenRhun(115, true).setTemperatureRainfall(0.9f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(10465880).setBiomeName("rhun");
        rhunForest = new LOTRBiomeGenRhunForest(116, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.3f, 0.5f).setColor(7505723).setBiomeName("rhunForest");
        rhunForest2 = new LOTRBiomeGenRhunForest2(226, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.2f, 0.3f).setColor(7834942).setBiomeName("rhunAvariForest");
        dolGuldur = new LOTRBiomeGenDolGuldur(119, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(0.2f, 0.5f).setColor(2371343).setBiomeName("dolGuldur");
        nearHaradSemiDesert = new LOTRBiomeGenNearHaradSemiDesert(120, true).setTemperatureRainfall(1.5f, 0.2f).setMinMaxHeight(0.2f, 0.1f).setColor(12434282).setBiomeName("nearHaradSemiDesert");
        farHaradArid = new LOTRBiomeGenFarHaradArid(121, true).setTemperatureRainfall(1.5f, 0.3f).setMinMaxHeight(0.2f, 0.15f).setColor(11185749).setBiomeName("farHaradArid");
        farHaradAridHills = new LOTRBiomeGenFarHaradArid(122, false).setTemperatureRainfall(1.5f, 0.3f).setMinMaxHeight(1.0f, 0.6f).setColor(10063195).setBiomeName("farHaradAridHills");
        farHaradSwamp = new LOTRBiomeGenFarHaradSwamp(123, true).setTemperatureRainfall(0.8f, 1.0f).setMinMaxHeight(0.0f, 0.1f).setColor(5608267).setBiomeName("farHaradSwamp");
        farHaradCloudForest = new LOTRBiomeGenFarHaradCloudForest(124, true).setTemperatureRainfall(1.2f, 1.2f).setMinMaxHeight(0.7f, 0.4f).setColor(3046208).setBiomeName("farHaradCloudForest");
        farHaradBushland = new LOTRBiomeGenFarHaradBushland(125, true).setTemperatureRainfall(1.0f, 0.4f).setMinMaxHeight(0.2f, 0.1f).setColor(10064190).setBiomeName("farHaradBushland");
        farHaradBushlandHills = new LOTRBiomeGenFarHaradBushland(126, false).setTemperatureRainfall(0.8f, 0.4f).setMinMaxHeight(0.8f, 0.8f).setColor(8354100).setBiomeName("farHaradBushlandHills");
        farHaradMangrove = new LOTRBiomeGenFarHaradMangrove(127, true).setTemperatureRainfall(1.0f, 0.9f).setMinMaxHeight(-0.05f, 0.05f).setColor(8883789).setBiomeName("farHaradMangrove");
        nearHaradFertileForest = new LOTRBiomeGenNearHaradFertileForest(128, false).setTemperatureRainfall(1.2f, 1.0f).setMinMaxHeight(0.2f, 0.4f).setColor(6915122).setBiomeName("nearHaradFertileForest");
        anduinVale = new LOTRBiomeGenAnduinVale(129, true).setTemperatureRainfall(0.9f, 1.1f).setMinMaxHeight(0.05f, 0.05f).setColor(7447880).setBiomeName("anduinVale");
        wold = new LOTRBiomeGenWold(130, true).setTemperatureRainfall(0.9f, 0.1f).setMinMaxHeight(0.4f, 0.3f).setColor(9483599).setBiomeName("wold");
        shireMoors = new LOTRBiomeGenShireMoors(131, true).setTemperatureRainfall(0.6f, 1.6f).setMinMaxHeight(0.4f, 0.6f).setColor(6921036).setBiomeName("shireMoors");
        shireMarshes = new LOTRBiomeGenShireMarshes(132, true).setTemperatureRainfall(0.8f, 1.2f).setMinMaxHeight(0.0f, 0.1f).setColor(4038751).setBiomeName("shireMarshes");
        nearHaradRedDesert = new LOTRBiomeGenNearHaradRed(133, true).setTemperatureRainfall(1.5f, 0.1f).setMinMaxHeight(0.2f, 0.0f).setColor(13210447).setBiomeName("nearHaradRedDesert");
        farHaradVolcano = new LOTRBiomeGenFarHaradVolcano(134, true).setTemperatureRainfall(1.5f, 0.0f).setMinMaxHeight(0.6f, 1.2f).setColor(6838068).setBiomeName("farHaradVolcano");
        udun = new LOTRBiomeGenUdun(135, true).setTemperatureRainfall(1.5f, 0.0f).setMinMaxHeight(0.2f, 0.7f).setColor(65536).setBiomeName("udun");
        gorgoroth = new LOTRBiomeGenGorgoroth(136, true).setTemperatureRainfall(2.0f, 0.0f).setMinMaxHeight(0.6f, 0.2f).setColor(2170141).setBiomeName("gorgoroth");
        morgulVale = new LOTRBiomeGenMorgulVale(137, true).setTemperatureRainfall(1.0f, 0.0f).setMinMaxHeight(0.2f, 0.1f).setColor(1387801).setBiomeName("morgulVale");
        easternDesolation = new LOTRBiomeGenEasternDesolation(138, true).setTemperatureRainfall(1.0f, 0.3f).setMinMaxHeight(0.2f, 0.2f).setColor(6052935).setBiomeName("easternDesolation");
        dale = new LOTRBiomeGenDale(139, true).setTemperatureRainfall(0.8f, 0.7f).setMinMaxHeight(0.1f, 0.2f).setColor(8233807).setBiomeName("dale");
        dorwinion = new LOTRBiomeGenDorwinion(140, true).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.1f, 0.3f).setColor(7120197).setBiomeName("dorwinion");
        towerHills = new LOTRBiomeGenTowerHills(141, true).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.5f, 0.5f).setColor(6854209).setBiomeName("towerHills");
        gulfHaradForest = new LOTRBiomeGenGulfHaradForest(142, false).setTemperatureRainfall(1.0f, 1.0f).setMinMaxHeight(0.2f, 0.4f).setColor(5868590).setBiomeName("gulfHaradForest");
        wilderlandNorth = new LOTRBiomeGenWilderlandNorth(143, true).setTemperatureRainfall(0.6f, 0.6f).setMinMaxHeight(0.2f, 0.5f).setColor(9676396).setBiomeName("wilderlandNorth");
        forodwaithCoast = new LOTRBiomeGenForodwaithCoast(144, false).setTemperatureRainfall(0.0f, 0.4f).setMinMaxHeight(0.0f, 0.5f).setColor(9214637).setBiomeName("forodwaithCoast");
        farHaradCoast = new LOTRBiomeGenFarHaradCoast(145, false).setTemperatureRainfall(1.2f, 0.8f).setMinMaxHeight(0.0f, 0.5f).setColor(8356472).setBiomeName("farHaradCoast");
        nearHaradRiverbank = new LOTRBiomeGenNearHaradRiverbank(146, false).setTemperatureRainfall(1.2f, 0.8f).setMinMaxHeight(0.1f, 0.1f).setColor(7183952).setBiomeName("nearHaradRiverbank");
        lossarnach = new LOTRBiomeGenLossarnach(147, true).setTemperatureRainfall(1.0f, 1.0f).setMinMaxHeight(0.1f, 0.2f).setColor(8439086).setBiomeName("lossarnach");
        imlothMelui = new LOTRBiomeGenImlothMelui(148, true).setTemperatureRainfall(1.0f, 1.2f).setMinMaxHeight(0.1f, 0.2f).setColor(14517608).setBiomeName("imlothMelui");
        nearHaradOasis = new LOTRBiomeGenNearHaradOasis(149, false).setTemperatureRainfall(1.2f, 0.8f).setMinMaxHeight(0.1f, 0.1f).setColor(832768).setBiomeName("nearHaradOasis");
        beachWhite = new LOTRBiomeGenBeach(150, false).setBeachBlock(LOTRMod.whiteSand, 0).setColor(15592941).setBiomeName("beachWhite");
        harnedor = new LOTRBiomeGenHarnedor(151, true).setTemperatureRainfall(1.0f, 0.3f).setMinMaxHeight(0.1f, 0.3f).setColor(11449173).setBiomeName("harnedor");
        lamedon = new LOTRBiomeGenLamedon(152, true).setTemperatureRainfall(0.9f, 0.5f).setMinMaxHeight(0.2f, 0.2f).setColor(10927460).setBiomeName("lamedon");
        lamedonHills = new LOTRBiomeGenLamedonHills(153, true).setTemperatureRainfall(0.6f, 0.4f).setMinMaxHeight(0.6f, 0.9f).setColor(13555369).setBiomeName("lamedonHills");
        blackrootVale = new LOTRBiomeGenBlackrootVale(154, true).setTemperatureRainfall(0.8f, 0.9f).setMinMaxHeight(0.2f, 0.12f).setColor(7183921).setBiomeName("blackrootVale");
        andrast = new LOTRBiomeGenAndrast(155, true).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.2f, 0.2f).setColor(8885856).setBiomeName("andrast");
        pukel = new LOTRBiomeGenPukel(156, true).setTemperatureRainfall(0.7f, 0.7f).setMinMaxHeight(0.2f, 0.4f).setColor(5667394).setBiomeName("pukel");
        rhunLand = new LOTRBiomeGenRhunLand(157, true).setTemperatureRainfall(1.0f, 0.8f).setMinMaxHeight(0.1f, 0.3f).setColor(11381583).setBiomeName("rhunLand");
        rhunLandSteppe = new LOTRBiomeGenRhunLandSteppe(158, true).setTemperatureRainfall(1.0f, 0.3f).setMinMaxHeight(0.2f, 0.05f).setColor(11712354).setBiomeName("rhunLandSteppe");
        rhunLandHills = new LOTRBiomeGenRhunLandHills(159, true).setTemperatureRainfall(1.0f, 0.5f).setMinMaxHeight(0.6f, 0.8f).setColor(9342286).setBiomeName("rhunLandHills");
        rhunRedForest = new LOTRBiomeGenRhunRedForest(160, true).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.1f, 0.3f).setColor(9530430).setBiomeName("rhunRedForest");
        rhunIsland = new LOTRBiomeGenRhunIsland(161, false).setTemperatureRainfall(1.0f, 0.8f).setMinMaxHeight(0.1f, 0.4f).setColor(10858839).setBiomeName("rhunIsland");
        rhunIslandForest = new LOTRBiomeGenRhunIslandForest(162, false).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.1f, 0.4f).setColor(9533758).setBiomeName("rhunIslandForest");
        lastDesert = new LOTRBiomeGenLastDesert(163, true).setTemperatureRainfall(0.7f, 0.0f).setMinMaxHeight(0.2f, 0.05f).setColor(13878151).setBiomeName("lastDesert");
        windMountains = new LOTRBiomeGenWindMountains(164, true).setTemperatureRainfall(0.28f, 0.2f).setMinMaxHeight(2.0f, 2.0f).setColor(13882323).setBiomeName("windMountains");
        windMountainsFoothills = new LOTRBiomeGenWindMountainsFoothills(165, true).setTemperatureRainfall(0.4f, 0.6f).setMinMaxHeight(0.5f, 0.6f).setColor(10133354).setBiomeName("windMountainsFoothills");
        rivendell = new LOTRBiomeGenRivendell(166, true).setTemperatureRainfall(0.9f, 1.0f).setMinMaxHeight(0.15f, 0.3f).setColor(8828714).setBiomeName("rivendell");
        rivendellHills = new LOTRBiomeGenRivendellHills(167, true).setTemperatureRainfall(0.7f, 0.8f).setMinMaxHeight(2.0f, 0.5f).setColor(14210481).setBiomeName("rivendellHills");
        farHaradJungleMountains = new LOTRBiomeGenFarHaradJungleMountains(168, true).setTemperatureRainfall(1.0f, 1.0f).setMinMaxHeight(1.8f, 1.5f).setColor(6511174).setBiomeName("farHaradJungleMountains");
        halfTrollForest = new LOTRBiomeGenHalfTrollForest(169, true).setTemperatureRainfall(0.8f, 0.2f).setMinMaxHeight(0.3f, 0.4f).setColor(5992500).setBiomeName("halfTrollForest");
        farHaradKanuka = new LOTRBiomeGenKanuka(170, true).setTemperatureRainfall(1.0f, 1.0f).setMinMaxHeight(0.3f, 0.5f).setColor(5142552).setBiomeName("farHaradKanuka");
        utumno = new LOTRBiomeGenUtumno(0).setTemperatureRainfall(2.0f, 0.0f).setMinMaxHeight(0.0f, 0.0f).setColor(0).setBiomeName("utumno");
        moria = new LOTRBiomeGenMoria(171, true).setTemperatureRainfall(0.2f, 0.5f).setMinMaxHeight(2.0f, 2.0f).setColor(14539991).setBiomeName("moria");
        angband = new LOTRBiomeGenAngband(172, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(0.1f, 0.1f).setColor(13421767).setBiomeName("angband");
        redMountains2 = new LOTRBiomeGenRedMountainsBlacklock(189, true).setTemperatureRainfall(0.3f, 0.4f).setMinMaxHeight(1.5f, 2.0f).setColor(10056783).setBiomeName("orocarni");
        redMountainsFoothills2 = new LOTRBiomeGenRedMountainsFoothillsStiffbeard(190, true).setTemperatureRainfall(0.7f, 0.4f).setMinMaxHeight(0.5f, 0.9f).setColor(10392917).setBiomeName("northFoothills");
        redMountains3 = new LOTRBiomeGenRedMountainsStiffbeard(193, true).setTemperatureRainfall(0.3f, 0.2f).setMinMaxHeight(1.5f, 2.0f).setColor(9859662).setBiomeName("middle_orocarni");
        redMountains4 = new LOTRBiomeGenRedMountainsIronfist(195, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(1.5f, 2.0f).setColor(15066597).setBiomeName("notrh_orocarni");
        redMountainsFoothills3 = new LOTRBiomeGenRedMountainsFoothillsBlacklock(194, true).setTemperatureRainfall(0.7f, 0.4f).setMinMaxHeight(0.5f, 0.9f).setColor(10393685).setBiomeName("middleFoothills");
        redMountains = new LOTRBiomeGenRedMountainsStonefoot(117, true).setTemperatureRainfall(0.4f, 0.3f).setMinMaxHeight(1.5f, 2.0f).setColor(9662796).setBiomeName("redMountainsStonefoot");
        redMountainsFoothills = new LOTRBiomeGenRedMountainsFoothillsStonefoot(118, true).setTemperatureRainfall(0.7f, 0.4f).setMinMaxHeight(0.5f, 0.9f).setColor(10064978).setBiomeName("southFoothills");
        forodwaithMountains2 = new LOTRBiomeGenForodwaithMountains2(191, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(2.0f, 2.0f).setColor(15395562).setBiomeName("angbandMountains");
        uncharted_rhun = new LOTRBiomeGenUnchartedRhun(196, true).setTemperatureRainfall(0.9f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(11449194).setBiomeName("uncharted_rhun");
        eastern_coasts = new LOTRBiomeGenEasternCoasts(198, true).setTemperatureRainfall(0.6f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(8165242).setBiomeName("eastern_coasts");
        harhudor = new LOTRBiomeGenHarhudor(199, true).setTemperatureRainfall(1.0f, 0.8f).setMinMaxHeight(0.1f, 0.3f).setColor(12041329).setBiomeName("harhudor");
        wasteland_rhun = new LOTRBiomeGenWastelandRhun(202, true).setTemperatureRainfall(0.6f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(9014877).setBiomeName("wasteland_rhun");
        durmeth = new LOTRBiomeGenDurmeth(204, true).setTemperatureRainfall(0.9f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(11511180).setBiomeName("durmeth");
        durmeth_mountains = new LOTRBiomeGenDurmethMountains(205, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(0.5f, 0.9f).setColor(13553354).setBiomeName("durmeth_Foothills");
        taiga_rhun = new LOTRBiomeGenTaigaRhun(206, true).setTemperatureRainfall(0.9f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(6328653).setBiomeName("taiga_rhun");
        sunLands = new LOTRBiomeSunLands(208, true).setTemperatureRainfall(2.0f, 0.5f).setMinMaxHeight(0.3f, 0.0f).setColor(13744486).setBiomeName("sunLands");
        tundrarhun = new LOTRBiomeGenTundra2(209, true).setTemperatureRainfall(0.1f, 0.3f).setMinMaxHeight(0.1f, 0.2f).setColor(11905936).setBiomeName("tundrarhun");
        dimrill = new LOTRBiomeGenDimrillDale(210, true).setTemperatureRainfall(0.8f, 1.0f).setMinMaxHeight(0.6f, 0.4f).setColor(6134095).setBiomeName("dimrill");
        bizar = new LOTRBiomeGenBizar(197, true).setTemperatureRainfall(0.6f, 0.3f).setMinMaxHeight(0.3f, 0.0f).setColor(9537614).setBiomeName("bizar_sham");
        fallforodwaith = new LOTRBiomeGenFallForodwaith(173, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(0.1f, 0.1f).setColor(13750735).setBiomeName("fallforodwaith");
        forestShelter = new LOTRBiomeGenForestShelter(211, true).setTemperatureRainfall(0.6f, 1.2f).setMinMaxHeight(0.0f, 0.1f).setColor(4087329).setBiomeName("forestShelter");
        southMirkwood = new LOTRBiomeGenSouthMirkwoodCorrupted(212, true).setTemperatureRainfall(0.6f, 0.8f).setMinMaxHeight(0.2f, 0.5f).setColor(2308368).setBiomeName("southMirkwood");
        beornsDomain = new LOTRBiomeGenBeornsDomain(213, true).setTemperatureRainfall(0.9f, 1.1f).setMinMaxHeight(0.05f, 0.05f).setColor(6590783).setBiomeName("beornsDomain");
        anduinSource = new LOTRBiomeGenAnduinSource(214, true).setTemperatureRainfall(0.7f, 0.7f).setMinMaxHeight(0.6f, 0.4f).setColor(6920515).setBiomeName("anduinSource");
        harhudorForest = new LOTRBiomeGenHarhudorForest(215, true).setTemperatureRainfall(1.5f, 1.0f).setMinMaxHeight(0.1f, 0.3f).setColor(7508280).setBiomeName("harhudorForest");
        harhudorForestEdge = new LOTRBiomeGenHarhudorForestEdge(216, true).setTemperatureRainfall(1.5f, 0.8f).setMinMaxHeight(0.2f, 0.2f).setColor(8561471).setBiomeName("harhudorForestEdge");
        rohanGate = new LOTRBiomeGenRohanGate(217, true).setTemperatureRainfall(0.4f, 0.7f).setMinMaxHeight(0.3f, 0.5f).setColor(7445577).setBiomeName("rohanGate");
        iceugol = new LOTRBiomeGenAngband2(218, true).setTemperatureRainfall(0.0f, 0.2f).setMinMaxHeight(0.1f, 0.1f).setColor(13027010).setBiomeName("iceugol");
        snowtaiga = new LOTRBiomeGenTaiga2(219, true).setTemperatureRainfall(0.1f, 0.7f).setMinMaxHeight(0.1f, 0.5f).setColor(9219966).setBiomeName("taigaSnow");
        forohel = new LOTRBiomeGenTundra3(220, true).setTemperatureRainfall(0.1f, 0.3f).setMinMaxHeight(0.1f, 0.2f).setColor(13026228).setBiomeName("forohel");
        snowtundra = new LOTRBiomeGenTundra4(221, true).setTemperatureRainfall(0.1f, 0.3f).setMinMaxHeight(0.1f, 0.2f).setColor(14076856).setBiomeName("snowtundra");
        notDeepOcean = new LOTRBiomeGenOcean(222, false).setTemperatureRainfall(0.85f, 0.8f).setMinMaxHeight(-0.4f, 0.3f).setColor(225197).setBiomeName("ocean");
        middleDeepOcean = new LOTRBiomeGenOcean(223, false).setTemperatureRainfall(0.85f, 0.8f).setMinMaxHeight(-0.75f, 0.3f).setColor(153997).setBiomeName("ocean");
        rhunSemiDesert = new LOTRBiomeGenRhunSemiDesert(224, true).setTemperatureRainfall(1.5f, 0.2f).setMinMaxHeight(0.2f, 0.1f).setColor(12037994).setBiomeName("rhunSemiDesert");
        anfalas = new LOTRBiomeGenAnfalas(225, true).setTemperatureRainfall(0.9f, 0.9f).setMinMaxHeight(0.07f, 0.2f).setColor(9152597).setBiomeName("anfalas");
        nearHaradRedSemiDesert = new LOTRBiomeGenRedSemiDesert(174, true).setTemperatureRainfall(1.5f, 0.1f).setMinMaxHeight(0.2f, 0.0f).setColor(12618825).setBiomeName("nearHaradRedSemiDesert");
        khand = new LOTRBiomeGenKhand(175, true).setTemperatureRainfall(1.5f, 0.2f).setMinMaxHeight(0.2f, 0.1f).setColor(11052646).setBiomeName("khand");
        khandHills = new LOTRBiomeGenKhandHills(176, true).setTemperatureRainfall(1.5f, 0.2f).setMinMaxHeight(0.6f, 0.8f).setColor(9671001).setBiomeName("khandHills");
        redGlacier = new LOTRBiomeGenRedGlacier(177, true).setTemperatureRainfall(0.0f, 0.1f).setMinMaxHeight(1.4f, 1.5f).setColor(14605789).setBiomeName("redGlacier");
    }

    public LOTRBiome(int i, boolean major) {
        this(i, major, LOTRDimension.MIDDLE_EARTH);
    }

    public LOTRBiome(int i, boolean major, LOTRDimension dim) {
        super(i, false);
        this.biomeDimension = dim;
        if (this.biomeDimension.biomeList[i] != null) {
            throw new IllegalArgumentException("LOTR biome already exists at index " + i + " for dimension " + this.biomeDimension.dimensionName + "!");
        }
        this.biomeDimension.biomeList[i] = this;
        if (major) {
            this.biomeDimension.majorBiomes.add(this);
        }
        this.waterColorMultiplier = BiomeColors.DEFAULT_WATER;
        this.decorator = new LOTRBiomeDecorator(this);
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        if (this.hasDomesticAnimals()) {
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 10, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 8, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 5, 4, 4));
        } else {
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityWildBoar.class, 10, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 8, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 10, 4, 4));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityAurochs.class, 6, 4, 4));
        }
        this.spawnableWaterCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityFish.class, 10, 4, 4));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityButterfly.class, 8, 4, 4));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityRabbit.class, 8, 4, 4));
        this.spawnableLOTRAmbientList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBird.class, 10, 4, 4));
        this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 10, 8, 8));
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
        this.setDragonChance(LOTREventSpawner.EventChance.NEVER);
        this.invasionSpawns = new LOTRBiomeInvasionSpawns(this);
    }

    protected void addBiomeVariant(LOTRBiomeVariant v) {
        this.addBiomeVariant(v, 1.0f);
    }

    protected void addBiomeVariant(LOTRBiomeVariant v, float f) {
        if (v.variantScale == LOTRBiomeVariant.VariantScale.ALL) {
            this.biomeVariantsLarge.add(v, f);
            this.biomeVariantsSmall.add(v, f);
        } else if (v.variantScale == LOTRBiomeVariant.VariantScale.LARGE) {
            this.biomeVariantsLarge.add(v, f);
        } else if (v.variantScale == LOTRBiomeVariant.VariantScale.SMALL) {
            this.biomeVariantsSmall.add(v, f);
        }
    }

    protected void addBiomeVariantSet(LOTRBiomeVariant[] set) {
        for (LOTRBiomeVariant v : set) {
            this.addBiomeVariant(v);
        }
    }

    protected void clearBiomeVariants() {
        this.biomeVariantsLarge.clear();
        this.biomeVariantsSmall.clear();
        this.variantChance = 0.4f;
    }

    public LOTRBiomeVariantList getBiomeVariantsLarge() {
        return this.biomeVariantsLarge;
    }

    public LOTRBiomeVariantList getBiomeVariantsSmall() {
        return this.biomeVariantsSmall;
    }

    public LOTRBiome setTemperatureRainfall(float f, float f1) {
        super.setTemperatureRainfall(f, f1);
        return this;
    }

    public boolean hasSeasonalGrass() {
        return this.temperature > 0.3f && this.temperature < 1.0f;
    }

    public LOTRBiome setMinMaxHeight(float f, float f1) {
        this.heightBaseParameter = f;
        f -= 2.0f;
        this.rootHeight = f += 0.2f;
        this.heightVariation = f1 / 2.0f;
        return this;
    }

    public boolean isWateryBiome() {
        return this.heightBaseParameter < 0.0f;
    }

    public LOTRBiome setColor(int color) {
        Integer existingBiomeID = this.biomeDimension.colorsToBiomeIDs.get(color |= 0xFF000000);
        if (existingBiomeID != null) {
            throw new RuntimeException("LOTR biome (ID " + this.biomeID + ") is duplicating the color of another LOTR biome (ID " + existingBiomeID + ")");
        }
        this.biomeDimension.colorsToBiomeIDs.put(color, this.biomeID);
        return (LOTRBiome)super.setColor(color);
    }

    public LOTRBiome setBiomeName(String s) {
        return (LOTRBiome)super.setBiomeName(s);
    }

    public final String getBiomeDisplayName() {
        return StatCollector.translateToLocal((String)("lotr.biome." + this.biomeName + ".name"));
    }

    public final void plantFlower(World world, Random rand, int x, int y, int z) {
        BiomeGenBase.FlowerEntry flower = this.getRandomFlower(world, rand, x, y, z);
        if (flower == null || flower.block == null || !flower.block.canBlockStay(world, x, y, z)) {
            return;
        }
        world.setBlock(x, y, z, flower.block, flower.metadata, 3);
    }

    public BiomeGenBase.FlowerEntry getRandomFlower(World world, Random rand, int i, int j, int k) {
        return (BiomeGenBase.FlowerEntry)WeightedRandom.getRandomItem((Random)rand, (Collection)this.flowers);
    }

    protected void registerPlainsFlowers() {
        this.flowers.clear();
        this.addFlower((Block)Blocks.red_flower, 4, 3);
        this.addFlower((Block)Blocks.red_flower, 5, 3);
        this.addFlower((Block)Blocks.red_flower, 6, 3);
        this.addFlower((Block)Blocks.red_flower, 7, 3);
        this.addFlower((Block)Blocks.red_flower, 0, 20);
        this.addFlower((Block)Blocks.red_flower, 3, 20);
        this.addFlower((Block)Blocks.red_flower, 8, 20);
        this.addFlower((Block)Blocks.yellow_flower, 0, 30);
        this.addFlower(LOTRMod.bluebell, 0, 5);
        this.addFlower(LOTRMod.marigold, 0, 10);
        this.addFlower(LOTRMod.lavender, 0, 5);
    }

    protected void registerRhunPlainsFlowers() {
        this.registerPlainsFlowers();
        this.addFlower(LOTRMod.marigold, 0, 10);
        this.addFlower(LOTRMod.rhunFlower, 0, 10);
        this.addFlower(LOTRMod.rhunFlower, 1, 10);
        this.addFlower(LOTRMod.rhunFlower, 2, 10);
        this.addFlower(LOTRMod.rhunFlower, 3, 10);
        this.addFlower(LOTRMod.rhunFlower, 4, 10);
    }

    protected void registerForestFlowers() {
        this.flowers.clear();
        this.addDefaultFlowers();
        this.addFlower(LOTRMod.bluebell, 0, 5);
        this.addFlower(LOTRMod.marigold, 0, 10);
    }

    protected void registerRhunForestFlowers() {
        this.registerForestFlowers();
        this.addFlower(LOTRMod.marigold, 0, 10);
        this.addFlower(LOTRMod.rhunFlower, 0, 10);
        this.addFlower(LOTRMod.rhunFlower, 1, 10);
        this.addFlower(LOTRMod.rhunFlower, 2, 10);
        this.addFlower(LOTRMod.rhunFlower, 3, 10);
        this.addFlower(LOTRMod.rhunFlower, 4, 10);
    }

    protected void registerJungleFlowers() {
        this.flowers.clear();
        this.addDefaultFlowers();
        this.addFlower(LOTRMod.haradFlower, 2, 20);
        this.addFlower(LOTRMod.haradFlower, 3, 20);
    }

    protected void registerMountainsFlowers() {
        this.flowers.clear();
        this.addDefaultFlowers();
        this.addFlower((Block)Blocks.red_flower, 1, 10);
        this.addFlower(LOTRMod.bluebell, 0, 5);
    }

    protected void registerTaigaFlowers() {
        this.flowers.clear();
        this.addDefaultFlowers();
        this.addFlower((Block)Blocks.red_flower, 1, 10);
        this.addFlower(LOTRMod.bluebell, 0, 5);
    }

    protected void registerSavannaFlowers() {
        this.flowers.clear();
        this.addDefaultFlowers();
    }

    protected void registerSwampFlowers() {
        this.flowers.clear();
        this.addDefaultFlowers();
    }

    protected void registerHaradFlowers() {
        this.flowers.clear();
        this.addFlower(LOTRMod.haradFlower, 0, 10);
        this.addFlower(LOTRMod.haradFlower, 1, 10);
        this.addFlower(LOTRMod.haradFlower, 2, 5);
        this.addFlower(LOTRMod.haradFlower, 3, 5);
    }

    protected void registerTravellingTrader(Class entityClass) {
        this.spawnableTraders.add(entityClass);
        LOTREventSpawner.createTraderSpawner(entityClass);
    }

    protected final void clearTravellingTraders() {
        this.spawnableTraders.clear();
    }

    public final boolean canSpawnTravellingTrader(Class entityClass) {
        return this.spawnableTraders.contains(entityClass);
    }

    protected final void setBanditChance(LOTREventSpawner.EventChance c) {
        this.banditChance = c;
    }

    protected final void setDragonChance(LOTREventSpawner.EventChance c) {
        this.dragonChance = c;
    }

    public final LOTREventSpawner.EventChance getDragonChance() {
        return this.dragonChance;
    }

    public final LOTREventSpawner.EventChance getBanditChance() {
        return this.banditChance;
    }

    protected final void setDragonEntityClass(Class<LOTREntityDragonAnkalagon> class1) {
        this.dragonEntityClass = class1;
    }

    public final Class<? extends EntityDragon> getdragonEntityClass() {
        if (this.dragonEntityClass == null) {
            return EntityDragon.class;
        }
        return this.dragonEntityClass;
    }

    protected final void setBanditEntityClass(Class<? extends LOTREntityBandit> c) {
        this.banditEntityClass = c;
    }

    public final Class<? extends LOTREntityBandit> getBanditEntityClass() {
        if (this.banditEntityClass == null) {
            return LOTREntityBandit.class;
        }
        return this.banditEntityClass;
    }

    public void addBiomeF3Info(List info, World world, LOTRBiomeVariant variant, int i, int j, int k) {
        int colorRGB = this.color & 0xFFFFFF;
        String colorString = Integer.toHexString(colorRGB);
        while (colorString.length() < 6) {
            colorString = "0" + colorString;
        }
        info.add("Middle-earth biome: " + this.getBiomeDisplayName() + ", ID: " + this.biomeID + ", c: #" + colorString);
        info.add("Variant: " + variant.variantName + ", loaded: " + LOTRBiomeVariantStorage.getSize(world));
    }

    protected boolean hasDomesticAnimals() {
        return false;
    }

    public boolean hasSky() {
        return true;
    }

    public LOTRAchievement getBiomeAchievement() {
        return null;
    }

    public LOTRWaypoint.Region getBiomeWaypoints() {
        return null;
    }

    public abstract LOTRMusicRegion.Sub getBiomeMusic();

    public boolean isHiddenBiome() {
        return false;
    }

    public boolean isRiver() {
        return false;
    }

    public boolean getEnableRiver() {
        return true;
    }

    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.PATH;
    }

    public LOTRRoadType.BridgeType getBridgeBlock() {
        return LOTRRoadType.BridgeType.DEFAULT;
    }

    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        int chunkX = i & 0xF;
        int chunkZ = k & 0xF;
        int xzIndex = chunkX * 16 + chunkZ;
        int ySize = blocks.length / 256;
        int seaLevel = 63;
        double stoneNoiseFiller = this.modifyStoneNoiseForFiller(stoneNoise);
        int fillerDepthBase = (int)(stoneNoiseFiller / 4.0 + 5.0 + random.nextDouble() * 0.25);
        int fillerDepth = -1;
        Block top = this.topBlock;
        int topMeta = this.topBlockMeta;
        Block filler = this.fillerBlock;
        int fillerMeta = this.fillerBlockMeta;
        if (this.enableRocky && height >= 90) {
            float hFactor = (float)(height - 90) / 10.0f;
            float thresh = 1.2f - hFactor * 0.2f;
            thresh = Math.max(thresh, 0.0f);
            double d12 = biomeTerrainNoise.func_151601_a((double)i * 0.03, (double)k * 0.03);
            if (d12 + biomeTerrainNoise.func_151601_a((double)i * 0.3, (double)k * 0.3) > (double)thresh) {
                if (random.nextInt(5) == 0) {
                    top = Blocks.gravel;
                    topMeta = 0;
                }
                if (random.nextInt(5) == 0) {
                    top = LOTRMod.susGravel;
                    topMeta = 0;
                } else {
                    top = Blocks.stone;
                    topMeta = 0;
                }
                filler = Blocks.stone;
                fillerMeta = 0;
                int prevHeight = height++;
                if (random.nextInt(20) == 0) {
                    // empty if block
                }
                for (int j = height; j >= prevHeight; --j) {
                    int index = xzIndex * ySize + j;
                    blocks[index] = Blocks.stone;
                    meta[index] = 0;
                }
            }
        }
        if (this.enablePodzol) {
            boolean podzol = false;
            if (this.topBlock == Blocks.grass) {
                float f;
                float trees = (float)this.decorator.treesPerChunk + this.getTreeIncreaseChance();
                trees = Math.max(trees, variant.treeFactor * 0.5f);
                if (f >= 1.0f) {
                    float thresh = 0.8f;
                    thresh -= trees * 0.15f;
                    thresh = Math.max(thresh, 0.0f);
                    double d = 0.06;
                    double randNoise = biomeTerrainNoise.func_151601_a((double)i * d, (double)k * d);
                    if (randNoise > (double)thresh) {
                        podzol = true;
                    }
                }
            }
            if (podzol) {
                terrainRand.setSeed(world.getSeed());
                terrainRand.setSeed(terrainRand.nextLong() + (long)i * 4668095025L + (long)k * 1387590552L ^ world.getSeed());
                float pdzRand = terrainRand.nextFloat();
                if (pdzRand < 0.35f) {
                    top = Blocks.dirt;
                    topMeta = 2;
                } else if (pdzRand < 0.5f) {
                    top = Blocks.dirt;
                    topMeta = 1;
                } else if (pdzRand < 0.51f) {
                    top = Blocks.gravel;
                    topMeta = 0;
                }
            }
        }
        if (variant.hasMarsh && LOTRBiomeVariant.marshNoise.func_151601_a((double)i * 0.1, (double)k * 0.1) > -0.1) {
            for (int j = ySize - 1; j >= 0; --j) {
                int index = xzIndex * ySize + j;
                if (blocks[index] != null && blocks[index].getMaterial() == Material.air) continue;
                if (j != seaLevel - 1 || blocks[index] == Blocks.water) break;
                blocks[index] = Blocks.water;
                break;
            }
        }
        for (int j = ySize - 1; j >= 0; --j) {
            int index = xzIndex * ySize + j;
            if (j <= 0 + random.nextInt(5)) {
                blocks[index] = Blocks.bedrock;
                continue;
            }
            Block block = blocks[index];
            if (block == Blocks.air) {
                fillerDepth = -1;
                continue;
            }
            if (block != Blocks.stone) continue;
            if (fillerDepth == -1) {
                if (fillerDepthBase <= 0) {
                    top = Blocks.air;
                    topMeta = 0;
                    filler = Blocks.stone;
                    fillerMeta = 0;
                } else if (j >= seaLevel - 4 && j <= seaLevel + 1) {
                    top = this.topBlock;
                    topMeta = (byte)this.topBlockMeta;
                    filler = this.fillerBlock;
                    fillerMeta = (byte)this.fillerBlockMeta;
                }
                if (j < seaLevel && top == Blocks.air) {
                    top = Blocks.water;
                    topMeta = 0;
                }
                fillerDepth = fillerDepthBase;
                if (j >= seaLevel - 1) {
                    blocks[index] = top;
                    meta[index] = topMeta;
                    continue;
                }
                blocks[index] = filler;
                meta[index] = fillerMeta;
                continue;
            }
            if (fillerDepth <= 0) continue;
            blocks[index] = filler;
            meta[index] = fillerMeta;
            if (--fillerDepth == 0) {
                boolean sand = false;
                if (filler == Blocks.sand) {
                    if (fillerMeta == 1) {
                        filler = LOTRMod.redSandstone;
                        fillerMeta = 0;
                    } else {
                        filler = Blocks.sandstone;
                        fillerMeta = 0;
                    }
                    sand = true;
                }
                if (filler == LOTRMod.whiteSand) {
                    filler = LOTRMod.whiteSandstone;
                    fillerMeta = 0;
                    sand = true;
                }
                if (sand) {
                    fillerDepth = 10 + random.nextInt(4);
                }
            }
            if ((this instanceof LOTRBiomeGenGondor || this instanceof LOTRBiomeGenIthilien || this instanceof LOTRBiomeGenDorEnErnil) && fillerDepth == 0 && filler == this.fillerBlock) {
                fillerDepth = 8 + random.nextInt(3);
                filler = LOTRMod.rock;
                fillerMeta = 1;
                continue;
            }
            if ((this instanceof LOTRBiomeGenRohan || this instanceof LOTRBiomeGenAdornland) && fillerDepth == 0 && filler == this.fillerBlock) {
                fillerDepth = 8 + random.nextInt(3);
                filler = LOTRMod.rock;
                fillerMeta = 2;
                continue;
            }
            if (!(this instanceof LOTRBiomeGenDorwinion) || fillerDepth != 0 || this.fillerBlock == LOTRMod.rock || filler != this.fillerBlock) continue;
            fillerDepth = 6 + random.nextInt(3);
            filler = LOTRMod.rock;
            fillerMeta = 5;
        }
        int rockDepth = (int)(stoneNoise * 6.0 + 2.0 + random.nextDouble() * 0.25);
        this.generateMountainTerrain(world, random, blocks, meta, i, k, xzIndex, ySize, height, rockDepth, variant);
        variant.generateVariantTerrain(world, random, blocks, meta, i, k, height, this);
    }

    protected double modifyStoneNoiseForFiller(double stoneNoise) {
        return stoneNoise;
    }

    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
    }

    public void decorate(World world, Random random, int i, int k) {
        this.decorator.decorate(world, random, i, k);
    }

    public LOTRBiomeSpawnList getNPCSpawnList(World world, Random random, int i, int j, int k, LOTRBiomeVariant variant) {
        return this.npcSpawnList;
    }

    public final boolean isDwarvenBiome(World world) {
        if (this.initDwarven) {
            return this.isDwarven;
        }
        this.initDwarven = true;
        this.isDwarven = this.npcSpawnList.containsEntityClassByDefault(LOTREntityDwarf.class, world) && !this.npcSpawnList.containsEntityClassByDefault(LOTREntityWickedDwarf.class, world) && !this.npcSpawnList.containsEntityClassByDefault(LOTREntityWickedDwarf2.class, world);
        return this.isDwarven;
    }

    public List getSpawnableList(EnumCreatureType creatureType) {
        if (creatureType == creatureType_LOTRAmbient) {
            return this.spawnableLOTRAmbientList;
        }
        return super.getSpawnableList(creatureType);
    }

    public float getChanceToSpawnAnimals() {
        return 1.0f;
    }

    public boolean canSpawnHostilesInDay() {
        return false;
    }

    public final WorldGenAbstractTree func_150567_a(Random random) {
        LOTRTreeType tree = this.decorator.getRandomTree(random);
        return tree.create(false, random);
    }

    public final WorldGenAbstractTree getTreeGen(World world, Random random, int i, int j, int k) {
        LOTRWorldChunkManager chunkManager = (LOTRWorldChunkManager)world.getWorldChunkManager();
        LOTRBiomeVariant variant = chunkManager.getBiomeVariantAt(i, k);
        LOTRTreeType tree = this.decorator.getRandomTreeForVariant(random, variant);
        return tree.create(false, random);
    }

    public float getTreeIncreaseChance() {
        return 0.1f;
    }

    public final WorldGenerator getRandomWorldGenForGrass(Random random) {
        GrassBlockAndMeta obj = this.getRandomGrass(random);
        return new WorldGenTallGrass(obj.block, obj.meta);
    }

    public GrassBlockAndMeta getRandomGrass(Random random) {
        boolean fern = this.decorator.enableFern;
        boolean special = this.decorator.enableSpecialGrasses;
        if (fern && random.nextInt(3) == 0) {
            return new GrassBlockAndMeta((Block)Blocks.tallgrass, 2);
        }
        if (special && random.nextInt(500) == 0) {
            return new GrassBlockAndMeta(LOTRMod.flaxPlant, 0);
        }
        if (random.nextInt(4) > 0) {
            if (special) {
                if (random.nextInt(200) == 0) {
                    return new GrassBlockAndMeta(LOTRMod.tallGrass, 3);
                }
                if (random.nextInt(16) == 0) {
                    return new GrassBlockAndMeta(LOTRMod.tallGrass, 1);
                }
                if (random.nextInt(10) == 0) {
                    return new GrassBlockAndMeta(LOTRMod.tallGrass, 2);
                }
            }
            if (random.nextInt(80) == 0) {
                return new GrassBlockAndMeta(LOTRMod.tallGrass, 4);
            }
            return new GrassBlockAndMeta(LOTRMod.tallGrass, 0);
        }
        if (random.nextInt(3) == 0) {
            return new GrassBlockAndMeta(LOTRMod.clover, 0);
        }
        return new GrassBlockAndMeta((Block)Blocks.tallgrass, 1);
    }

    public WorldGenerator getRandomWorldGenForDoubleGrass(Random random) {
        WorldGenDoublePlant generator = new WorldGenDoublePlant();
        if (this.decorator.enableFern && random.nextInt(4) == 0) {
            generator.func_150548_a(3);
        } else {
            generator.func_150548_a(2);
        }
        return generator;
    }

    public WorldGenerator getRandomWorldGenForDoubleFlower(Random random) {
        WorldGenDoublePlant doubleFlowerGen = new WorldGenDoublePlant();
        int i = random.nextInt(3);
        switch (i) {
            case 0: {
                doubleFlowerGen.func_150548_a(1);
                break;
            }
            case 1: {
                doubleFlowerGen.func_150548_a(4);
                break;
            }
            case 2: {
                doubleFlowerGen.func_150548_a(5);
            }
        }
        return doubleFlowerGen;
    }

    public int spawnCountMultiplier() {
        return 1;
    }

    public BiomeGenBase createMutation() {
        return this;
    }

    public boolean canSpawnLightningBolt() {
        return !this.getEnableSnow() && super.canSpawnLightningBolt();
    }

    public boolean getEnableRain() {
        return this.enableRain;
    }

    public boolean getEnableSnow() {
        if (LOTRMod.isChristmas() && LOTRMod.proxy.isClient()) {
            return true;
        }
        return super.getEnableSnow();
    }

    public int getSnowHeight() {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBiomeGrassColor(int i, int j, int k) {
        int grassColor = this.biomeColors.grass != null ? this.biomeColors.grass.getRGB() : this.getBaseGrassColor(i, j, k);
        return grassColor;
    }

    @SideOnly(value=Side.CLIENT)
    public final int getBaseGrassColor(int i, int j, int k) {
        float temp = this.getFloatTemperature(i, j, k);
        float rain = this.rainfall;
        WorldChunkManager wcMgr = LOTRMod.proxy.getClientWorld().getWorldChunkManager();
        if (wcMgr instanceof LOTRWorldChunkManager) {
            LOTRBiomeVariant variant = ((LOTRWorldChunkManager)wcMgr).getBiomeVariantAt(i, k);
            temp += variant.tempBoost;
            rain += variant.rainBoost;
        }
        temp = MathHelper.clamp_float((float)temp, (float)0.0f, (float)1.0f);
        rain = MathHelper.clamp_float((float)rain, (float)0.0f, (float)1.0f);
        return ColorizerGrass.getGrassColor((double)temp, (double)rain);
    }

    @SideOnly(value=Side.CLIENT)
    public int getBiomeFoliageColor(int i, int j, int k) {
        int folgColor = this.biomeColors.foliage != null ? this.biomeColors.foliage.getRGB() : this.getBaseFoliageColor(i, j, k);
        return folgColor;
    }

    @SideOnly(value=Side.CLIENT)
    public final int getBaseFoliageColor(int i, int j, int k) {
        LOTRBiomeVariant variant = ((LOTRWorldChunkManager)LOTRMod.proxy.getClientWorld().getWorldChunkManager()).getBiomeVariantAt(i, k);
        float temp = this.getFloatTemperature(i, j, k) + variant.tempBoost;
        float rain = this.rainfall + variant.rainBoost;
        temp = MathHelper.clamp_float((float)temp, (float)0.0f, (float)1.0f);
        rain = MathHelper.clamp_float((float)rain, (float)0.0f, (float)1.0f);
        return ColorizerFoliage.getFoliageColor((double)temp, (double)rain);
    }

    @SideOnly(value=Side.CLIENT)
    public final int getSkyColorByTemp(float f) {
        if (LOTRTickHandlerClient.scrapTraderMisbehaveTick > 0) {
            return 0;
        }
        if (this.biomeColors.sky != null) {
            return this.biomeColors.sky.getRGB();
        }
        return super.getSkyColorByTemp(f);
    }

    @SideOnly(value=Side.CLIENT)
    public final int getBaseSkyColorByTemp(int i, int j, int k) {
        return super.getSkyColorByTemp(this.getFloatTemperature(i, j, k));
    }

    public final Vec3 getCloudColor(Vec3 clouds) {
        if (this.biomeColors.clouds != null) {
            float[] colors = this.biomeColors.clouds.getColorComponents(null);
            clouds.xCoord *= (double)colors[0];
            clouds.yCoord *= (double)colors[1];
            clouds.zCoord *= (double)colors[2];
        }
        return clouds;
    }

    public final Vec3 getFogColor(Vec3 fog) {
        if (this.biomeColors.fog != null) {
            float[] colors = this.biomeColors.fog.getColorComponents(null);
            fog.xCoord *= (double)colors[0];
            fog.yCoord *= (double)colors[1];
            fog.zCoord *= (double)colors[2];
        }
        return fog;
    }

    public final boolean hasFog() {
        return this.biomeColors.foggy;
    }

    public static void updateWaterColor(int i, int j, int k) {
        int min = 0;
        int max = waterLimitSouth - waterLimitNorth;
        float latitude = (float)MathHelper.clamp_int((int)(k - waterLimitNorth), (int)min, (int)max) / (float)max;
        float[] northColors = waterColorNorth.getColorComponents(null);
        float[] southColors = waterColorSouth.getColorComponents(null);
        float dR = southColors[0] - northColors[0];
        float dG = southColors[1] - northColors[1];
        float dB = southColors[2] - northColors[2];
        float r = dR * latitude;
        float g = dG * latitude;
        float b = dB * latitude;
        Color water = new Color(r += northColors[0], g += northColors[1], b += northColors[2]);
        int waterRGB = water.getRGB();
        for (LOTRDimension dimension : LOTRDimension.values()) {
            for (LOTRBiome biome : dimension.biomeList) {
                if (biome == null || biome.biomeColors.hasCustomWater()) continue;
                biome.biomeColors.updateWater(waterRGB);
            }
        }
    }

    static {
        biomeTerrainNoise = new NoiseGeneratorPerlin(new Random(1955L), 1);
        terrainRand = new Random();
        waterColorNorth = new Color(602979);
        waterColorSouth = new Color(4973293);
        waterLimitNorth = -40000;
        waterLimitSouth = 160000;
    }

    public static class BiomeColors {
        private LOTRBiome theBiome;
        private Color grass;
        private Color foliage;
        private Color sky;
        private Color clouds;
        private Color fog;
        private boolean foggy;
        private boolean hasCustomWater = false;
        private static int DEFAULT_WATER = 7186907;

        public BiomeColors(LOTRBiome biome) {
            this.theBiome = biome;
        }

        public void setGrass(int rgb) {
            this.grass = new Color(rgb);
        }

        public void resetGrass() {
            this.grass = null;
        }

        public void setFoliage(int rgb) {
            this.foliage = new Color(rgb);
        }

        public void resetFoliage() {
            this.foliage = null;
        }

        public void setSky(int rgb) {
            this.sky = new Color(rgb);
        }

        public void resetSky() {
            this.sky = null;
        }

        public void setClouds(int rgb) {
            this.clouds = new Color(rgb);
        }

        public void resetClouds() {
            this.clouds = null;
        }

        public void setFog(int rgb) {
            this.fog = new Color(rgb);
        }

        public void resetFog() {
            this.fog = null;
        }

        public void setFoggy(boolean flag) {
            this.foggy = flag;
        }

        public void setWater(int rgb) {
            this.theBiome.waterColorMultiplier = rgb;
            if (rgb != DEFAULT_WATER) {
                this.hasCustomWater = true;
            }
        }

        public void resetWater() {
            this.setWater(DEFAULT_WATER);
        }

        public boolean hasCustomWater() {
            return this.hasCustomWater;
        }

        public void updateWater(int rgb) {
            this.theBiome.waterColorMultiplier = rgb;
        }
    }

    public static class BiomeTerrain {
        private double xzScale = -1.0;
        private double heightStretchFactor = -1.0;

        public BiomeTerrain(LOTRBiome biome) {
        }

        public void setXZScale(double d) {
            this.xzScale = d;
        }

        public void resetXZScale() {
            this.setXZScale(-1.0);
        }

        public boolean hasXZScale() {
            return this.xzScale != -1.0;
        }

        public double getXZScale() {
            return this.xzScale;
        }

        public void setHeightStretchFactor(double d) {
            this.heightStretchFactor = d;
        }

        public void resetHeightStretchFactor() {
            this.setHeightStretchFactor(-1.0);
        }

        public boolean hasHeightStretchFactor() {
            return this.heightStretchFactor != -1.0;
        }

        public double getHeightStretchFactor() {
            return this.heightStretchFactor;
        }
    }

    public static class GrassBlockAndMeta {
        public final Block block;
        public final int meta;

        public GrassBlockAndMeta(Block b, int i) {
            this.block = b;
            this.meta = i;
        }
    }

    public static interface ImmuneToHeat {
    }

    public static interface ImmuneToFrost {
    }

}

