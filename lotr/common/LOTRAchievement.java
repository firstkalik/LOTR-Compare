/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.event.HoverEvent
 *  net.minecraft.event.HoverEvent$Action
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemEmptyMap
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.WorldServer
 */
package lotr.common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import lotr.common.LOTRChatEvents;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRTitle;
import lotr.common.entity.npc.LOTREntityWickedDwarf;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemManFlesh;
import lotr.common.quest.LOTRMiniQuestPickpocket;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemEmptyMap;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldServer;

public class LOTRAchievement {
    public Category category;
    public int ID;
    public ItemStack icon;
    private String name;
    public boolean isBiomeAchievement;
    public boolean isSpecial;
    public boolean isRing;
    private LOTRTitle achievementTitle;
    public List<LOTRFaction> enemyFactions = new ArrayList<LOTRFaction>();
    public List<LOTRFaction> allyFactions = new ArrayList<LOTRFaction>();
    public static LOTRAchievement enterMiddleEarth;
    public static LOTRAchievement enterMoria;
    public static LOTRAchievement Thorin;
    public static LOTRAchievement useArcenstoneRare;
    public static LOTRAchievement useArcenstone;
    public static LOTRAchievement engraveOwnership;
    public static LOTRAchievement mod;
    public static LOTRAchievement translator;
    public static LOTRAchievement lore;
    public static LOTRAchievement tester;
    public static LOTRAchievement dovakin;
    public static LOTRAchievement ogre;
    public static LOTRAchievement doGreyQuest;
    public static LOTRAchievement killOrc;
    public static LOTRAchievement mineMithril;
    public static LOTRAchievement rideWarg;
    public static LOTRAchievement killWarg;
    public static LOTRAchievement useSpearFromFar;
    public static LOTRAchievement wearFullMithril;
    public static LOTRAchievement gainHighAlcoholTolerance;
    public static LOTRAchievement craftSaddle;
    public static LOTRAchievement drinkOrcDraught;
    public static LOTRAchievement getPouch;
    public static LOTRAchievement craftBronze;
    public static LOTRAchievement killUsingOnlyPlates;
    public static LOTRAchievement wearFullFur;
    public static LOTRAchievement brewDrinkInBarrel;
    public static LOTRAchievement findAthelas;
    public static LOTRAchievement drinkAthelasBrew;
    public static LOTRAchievement killLargeMobWithSlingshot;
    public static LOTRAchievement eatMaggotyBread;
    public static LOTRAchievement killWhileDrunk;
    public static LOTRAchievement collectCraftingTables;
    public static LOTRAchievement hitByOrcSpear;
    public static LOTRAchievement killBombardier;
    public static LOTRAchievement earnManyCoins;
    public static LOTRAchievement earnManyCoins1;
    public static LOTRAchievement earnManyCoins2;
    public static LOTRAchievement earnManyCoins3;
    public static LOTRAchievement craftAppleCrumble;
    public static LOTRAchievement killButterfly;
    public static LOTRAchievement fishRing;
    public static LOTRAchievement useCrossbow;
    public static LOTRAchievement collectCrossbowBolts;
    public static LOTRAchievement travel10;
    public static LOTRAchievement travel20;
    public static LOTRAchievement travel30;
    public static LOTRAchievement attackRabbit;
    public static LOTRAchievement craftRabbitStew;
    public static LOTRAchievement drinkSkull;
    public static LOTRAchievement travel40;
    public static LOTRAchievement travel50;
    public static LOTRAchievement killThievingBandit;
    public static LOTRAchievement hundreds;
    public static LOTRAchievement alloyBronze;
    public static LOTRAchievement bannerProtect;
    public static LOTRAchievement catchButterfly;
    public static LOTRAchievement unsmelt;
    public static LOTRAchievement tradeScrapTrader;
    public static LOTRAchievement cookDeer;
    public static LOTRAchievement eatManFlesh;
    public static LOTRAchievement craftSaltedFlesh;
    public static LOTRAchievement enchantBaneElf;
    public static LOTRAchievement enchantBaneOrc;
    public static LOTRAchievement enchantBaneDwarf;
    public static LOTRAchievement enchantBaneWarg;
    public static LOTRAchievement enchantBaneTroll;
    public static LOTRAchievement enchantBaneSpider;
    public static LOTRAchievement enchantBaneBalrog;
    public static LOTRAchievement enchantBaneHobbit;
    public static LOTRAchievement enchantBaneWight;
    public static LOTRAchievement getDrunk;
    public static LOTRAchievement reforge;
    public static LOTRAchievement doMiniquestHunter;
    public static LOTRAchievement doMiniquestHunter5;
    public static LOTRAchievement killHuntingPlayer;
    public static LOTRAchievement pledgeService;
    public static LOTRAchievement factionConquest;
    public static LOTRAchievement defeatInvasion;
    public static LOTRAchievement pickpocket;
    public static LOTRAchievement combineSmithScrolls;
    public static LOTRAchievement killHobbit;
    public static LOTRAchievement sellPipeweedLeaf;
    public static LOTRAchievement marryHobbit;
    public static LOTRAchievement findFourLeafClover;
    public static LOTRAchievement useMagicPipe;
    public static LOTRAchievement rideShirePony;
    public static LOTRAchievement tradeBartender;
    public static LOTRAchievement tradeHobbit;
    public static LOTRAchievement speakToDrunkard;
    public static LOTRAchievement tradeHobbitShirriff;
    public static LOTRAchievement killDarkHuorn;
    public static LOTRAchievement enterOldForest;
    public static LOTRAchievement buyOrcharderFood;
    public static LOTRAchievement rideGiraffeShire;
    public static LOTRAchievement buyPotatoHobbitFarmer;
    public static LOTRAchievement doMiniquestHobbit;
    public static LOTRAchievement enterWhiteDowns;
    public static LOTRAchievement useHobbitTable;
    public static LOTRAchievement hireHobbitFarmer;
    public static LOTRAchievement enterBlueMountains;
    public static LOTRAchievement smeltBlueDwarfSteel;
    public static LOTRAchievement killBlueDwarf;
    public static LOTRAchievement wearFullBlueDwarven;
    public static LOTRAchievement useBlueDwarvenTable;
    public static LOTRAchievement tradeBlueDwarfMiner;
    public static LOTRAchievement tradeBlueDwarfCommander;
    public static LOTRAchievement tradeBlueDwarfMerchant;
    public static LOTRAchievement marryBlueDwarf;
    public static LOTRAchievement doMiniquestBlueMountains;
    public static LOTRAchievement tradeBlueDwarfSmith;
    public static LOTRAchievement enterLindon;
    public static LOTRAchievement doMiniquestHighElf;
    public static LOTRAchievement killHighElf;
    public static LOTRAchievement tradeHighElfLord;
    public static LOTRAchievement useHighElvenTable;
    public static LOTRAchievement wearFullHighElven;
    public static LOTRAchievement smeltElfSteel;
    public static LOTRAchievement tradeHighElfSmith;
    public static LOTRAchievement wearFullGondolin;
    public static LOTRAchievement enterTowerHills;
    public static LOTRAchievement wearFullGalvorn;
    public static LOTRAchievement enterBreeland;
    public static LOTRAchievement enterChetwood;
    public static LOTRAchievement killBreelander;
    public static LOTRAchievement doMiniquestBree;
    public static LOTRAchievement tradeBreeCaptain;
    public static LOTRAchievement useBreeTable;
    public static LOTRAchievement tradeBreeBlacksmith;
    public static LOTRAchievement tradeBreeInnkeeper;
    public static LOTRAchievement killBreeHobbit;
    public static LOTRAchievement killRuffianSpy;
    public static LOTRAchievement killRuffianBrute;
    public static LOTRAchievement doMiniquestRuffianSpy;
    public static LOTRAchievement doMiniquestRuffianBrute;
    public static LOTRAchievement tradeBreeMarketTrader;
    public static LOTRAchievement buyAppleBreeFarmer;
    public static LOTRAchievement hireBreeFarmer;
    public static LOTRAchievement killRangerNorth;
    public static LOTRAchievement wearFullRanger;
    public static LOTRAchievement killTroll;
    public static LOTRAchievement getTrollStatue;
    public static LOTRAchievement makeTrollSneeze;
    public static LOTRAchievement killMountainTroll;
    public static LOTRAchievement killTrollFleeingSun;
    public static LOTRAchievement killMountainTrollChieftain;
    public static LOTRAchievement shootDownMidges;
    public static LOTRAchievement enterTrollshaws;
    public static LOTRAchievement enterMidgewater;
    public static LOTRAchievement enterLoneLands;
    public static LOTRAchievement enterEttenmoors;
    public static LOTRAchievement enterEriador;
    public static LOTRAchievement enterColdfells;
    public static LOTRAchievement enterSwanfleet;
    public static LOTRAchievement enterMinhiriath;
    public static LOTRAchievement tradeGundabadCaptain;
    public static LOTRAchievement tradeRangerNorthCaptain;
    public static LOTRAchievement enterBarrowDowns;
    public static LOTRAchievement useRangerTable;
    public static LOTRAchievement useGundabadTable;
    public static LOTRAchievement doMiniquestRanger;
    public static LOTRAchievement doMiniquestGundabad;
    public static LOTRAchievement killBarrowWight;
    public static LOTRAchievement killGundabadOrc;
    public static LOTRAchievement killGundabadUruk;
    public static LOTRAchievement wearFullGundabadUruk;
    public static LOTRAchievement killDunedain;
    public static LOTRAchievement enterAngle;
    public static LOTRAchievement wearFullArnor;
    public static LOTRAchievement tradeDunedainBlacksmith;
    public static LOTRAchievement enterRivendell;
    public static LOTRAchievement useRivendellTable;
    public static LOTRAchievement wearFullRivendell;
    public static LOTRAchievement tradeRivendellSmith;
    public static LOTRAchievement tradeRivendellLord;
    public static LOTRAchievement tradeRivendellTrader;
    public static LOTRAchievement doMiniquestRivendell;
    public static LOTRAchievement killRivendellElf;
    public static LOTRAchievement tradeGundabadTrader;
    public static LOTRAchievement tradeAngmarCaptain;
    public static LOTRAchievement killAngmarOrc;
    public static LOTRAchievement enterAngmar;
    public static LOTRAchievement useAngmarTable;
    public static LOTRAchievement wearFullAngmar;
    public static LOTRAchievement doMiniquestAngmar;
    public static LOTRAchievement tradeAngmarTrader;
    public static LOTRAchievement killAngmarHillman;
    public static LOTRAchievement tradeAngmarHillmanChieftain;
    public static LOTRAchievement killSnowTroll;
    public static LOTRAchievement killMountainSnowTroll;
    public static LOTRAchievement enterEregion;
    public static LOTRAchievement enterEnedwaith;
    public static LOTRAchievement enterNanCurunir;
    public static LOTRAchievement enterPukel;
    public static LOTRAchievement killDunlending;
    public static LOTRAchievement wearFullDunlending;
    public static LOTRAchievement useDunlendingTable;
    public static LOTRAchievement tradeDunlendingWarlord;
    public static LOTRAchievement useDunlendingTrident;
    public static LOTRAchievement tradeDunlendingBartender;
    public static LOTRAchievement enterDunland;
    public static LOTRAchievement doMiniquestDunland;
    public static LOTRAchievement climbMistyMountains;
    public static LOTRAchievement leapoffaith;
    public static LOTRAchievement enterMistyMountains;
    public static LOTRAchievement tameGollum;
    public static LOTRAchievement enterForodwaith;
    public static LOTRAchievement enterValesOfAnduin;
    public static LOTRAchievement enterGreyMountains;
    public static LOTRAchievement enterGladdenFields;
    public static LOTRAchievement enterEmynMuil;
    public static LOTRAchievement enterBrownLands;
    public static LOTRAchievement enterWilderland;
    public static LOTRAchievement enterDagorlad;
    public static LOTRAchievement enterCelebrant;
    public static LOTRAchievement enterLongMarshes;
    public static LOTRAchievement enterEastBight;
    public static LOTRAchievement killMirkwoodSpider;
    public static LOTRAchievement killDesertSpider;
    public static LOTRAchievement killWoodElf;
    public static LOTRAchievement useWoodElvenTable;
    public static LOTRAchievement wearFullWoodElvenScout;
    public static LOTRAchievement tradeWoodElfCaptain;
    public static LOTRAchievement tradeAvariElfCaptain;
    public static LOTRAchievement rideBarrelMirkwood;
    public static LOTRAchievement enterMirkwood;
    public static LOTRAchievement enterWoodlandRealm;
    public static LOTRAchievement wearFullWoodElven;
    public static LOTRAchievement enterDolGuldur;
    public static LOTRAchievement killDolGuldurOrc;
    public static LOTRAchievement useDolGuldurTable;
    public static LOTRAchievement tradeDolGuldurCaptain;
    public static LOTRAchievement killMirkTroll;
    public static LOTRAchievement wearFullDolGuldur;
    public static LOTRAchievement doMiniquestWoodElf;
    public static LOTRAchievement doMiniquestAvariElf;
    public static LOTRAchievement doMiniquestDolGuldur;
    public static LOTRAchievement tradeDolGuldurTrader;
    public static LOTRAchievement tradeWoodElfSmith;
    public static LOTRAchievement enterDale;
    public static LOTRAchievement doMiniquestDale;
    public static LOTRAchievement useDaleTable;
    public static LOTRAchievement wearFullDale;
    public static LOTRAchievement killDalish;
    public static LOTRAchievement tradeDaleCaptain;
    public static LOTRAchievement tradeDaleBlacksmith;
    public static LOTRAchievement tradeDaleBaker;
    public static LOTRAchievement tradeDaleMerchant;
    public static LOTRAchievement enterErebor;
    public static LOTRAchievement openDaleCracker;
    public static LOTRAchievement killDwarf;
    public static LOTRAchievement wearFullDwarven;
    public static LOTRAchievement useDwarvenThrowingAxe;
    public static LOTRAchievement useDwarvenTable;
    public static LOTRAchievement tradeDwarfMiner;
    public static LOTRAchievement tradeDwarfCommander;
    public static LOTRAchievement mineGlowstone;
    public static LOTRAchievement smeltDwarfSteel;
    public static LOTRAchievement drinkDwarvenTonic;
    public static LOTRAchievement craftMithrilDwarvenBrick;
    public static LOTRAchievement talkDwarfWoman;
    public static LOTRAchievement talkElf;
    public static LOTRAchievement enterIronHills;
    public static LOTRAchievement useDwarvenDoor;
    public static LOTRAchievement marryDwarf;
    public static LOTRAchievement doMiniquestDwarf;
    public static LOTRAchievement tradeIronHillsMerchant;
    public static LOTRAchievement tradeDwarfSmith;
    public static LOTRAchievement tradeEreborDwarfSmith;
    public static LOTRAchievement killElf;
    public static LOTRAchievement killAvariElf;
    public static LOTRAchievement useElvenPortal;
    public static LOTRAchievement wearFullElven;
    public static LOTRAchievement useElvenTable;
    public static LOTRAchievement tradeElfLord;
    public static LOTRAchievement mineQuendite;
    public static LOTRAchievement takeMallornWood;
    public static LOTRAchievement enterLothlorien;
    public static LOTRAchievement tradeElvenTrader;
    public static LOTRAchievement doMiniquestGaladhrim;
    public static LOTRAchievement tradeGaladhrimSmith;
    public static LOTRAchievement wearFullHithlain;
    public static LOTRAchievement killEnt;
    public static LOTRAchievement drinkEntDraught;
    public static LOTRAchievement killHuorn;
    public static LOTRAchievement talkEnt;
    public static LOTRAchievement enterFangorn;
    public static LOTRAchievement summonHuorn;
    public static LOTRAchievement killMallornEnt;
    public static LOTRAchievement raidUrukCamp;
    public static LOTRAchievement useUrukTable;
    public static LOTRAchievement tradeUrukTrader;
    public static LOTRAchievement tradeUrukCaptain;
    public static LOTRAchievement useRohirricTable;
    public static LOTRAchievement smeltUrukSteel;
    public static LOTRAchievement wearFullUruk;
    public static LOTRAchievement hireWargBombardier;
    public static LOTRAchievement killRohirrim;
    public static LOTRAchievement tradeRohirrimMarshal;
    public static LOTRAchievement wearFullRohirric;
    public static LOTRAchievement tradeRohanBlacksmith;
    public static LOTRAchievement buyRohanMead;
    public static LOTRAchievement enterRohan;
    public static LOTRAchievement enterRohanUrukHighlands;
    public static LOTRAchievement doMiniquestRohan;
    public static LOTRAchievement doMiniquestIsengard;
    public static LOTRAchievement killUrukHai;
    public static LOTRAchievement enterEntwashMouth;
    public static LOTRAchievement wearFullRohirricMarshal;
    public static LOTRAchievement killIsengardSnaga;
    public static LOTRAchievement doMiniquestRohanShieldmaiden;
    public static LOTRAchievement tradeRohanFarmer;
    public static LOTRAchievement hireRohanFarmer;
    public static LOTRAchievement tradeRohanMarketTrader;
    public static LOTRAchievement tradeRohanStablemaster;
    public static LOTRAchievement enterAdornland;
    public static LOTRAchievement killGondorian;
    public static LOTRAchievement lightGondorBeacon;
    public static LOTRAchievement useGondorianTable;
    public static LOTRAchievement tradeGondorBlacksmith;
    public static LOTRAchievement tradeGondorianCaptain;
    public static LOTRAchievement wearFullGondorian;
    public static LOTRAchievement killRangerIthilien;
    public static LOTRAchievement enterGondor;
    public static LOTRAchievement enterIthilien;
    public static LOTRAchievement enterWhiteMountains;
    public static LOTRAchievement enterTolfalas;
    public static LOTRAchievement enterLebennin;
    public static LOTRAchievement doMiniquestGondor;
    public static LOTRAchievement tradeRangerIthilienCaptain;
    public static LOTRAchievement useDolAmrothTable;
    public static LOTRAchievement wearFullDolAmroth;
    public static LOTRAchievement killSwanKnight;
    public static LOTRAchievement enterDorEnErnil;
    public static LOTRAchievement tradeDolAmrothCaptain;
    public static LOTRAchievement enterAnduinMouth;
    public static LOTRAchievement enterPelennor;
    public static LOTRAchievement wearFullRangerIthilien;
    public static LOTRAchievement enterLossarnach;
    public static LOTRAchievement enterImlothMelui;
    public static LOTRAchievement wearFullLossarnach;
    public static LOTRAchievement wearFullPelargir;
    public static LOTRAchievement wearFullPinnathGelin;
    public static LOTRAchievement wearFullBlackroot;
    public static LOTRAchievement hireGondorFarmer;
    public static LOTRAchievement buyPipeweedGondorFarmer;
    public static LOTRAchievement tradeGondorBartender;
    public static LOTRAchievement tradeGondorMarketTrader;
    public static LOTRAchievement tradeLossarnachCaptain;
    public static LOTRAchievement tradePelargirCaptain;
    public static LOTRAchievement tradePinnathGelinCaptain;
    public static LOTRAchievement tradeBlackrootCaptain;
    public static LOTRAchievement tradeLebenninCaptain;
    public static LOTRAchievement enterPelargir;
    public static LOTRAchievement wearFullLamedon;
    public static LOTRAchievement tradeLamedonCaptain;
    public static LOTRAchievement enterLamedon;
    public static LOTRAchievement enterBlackrootVale;
    public static LOTRAchievement enterPinnathGelin;
    public static LOTRAchievement enterAndrast;
    public static LOTRAchievement doMiniquestGondorKillRenegade;
    public static LOTRAchievement mineRemains;
    public static LOTRAchievement craftAncientItem;
    public static LOTRAchievement enterDeadMarshes;
    public static LOTRAchievement enterNindalf;
    public static LOTRAchievement killMarshWraith;
    public static LOTRAchievement killOlogHai;
    public static LOTRAchievement useMorgulTable;
    public static LOTRAchievement smeltOrcSteel;
    public static LOTRAchievement wearFullOrc;
    public static LOTRAchievement tradeOrcTrader;
    public static LOTRAchievement tradeOrcCaptain;
    public static LOTRAchievement mineNaurite;
    public static LOTRAchievement eatMorgulShroom;
    public static LOTRAchievement craftOrcBomb;
    public static LOTRAchievement hireOlogHai;
    public static LOTRAchievement hireBerserk;
    public static LOTRAchievement mineGulduril;
    public static LOTRAchievement useMorgulPortal;
    public static LOTRAchievement wearFullMorgul;
    public static LOTRAchievement enterMordor;
    public static LOTRAchievement enterNurn;
    public static LOTRAchievement enterNanUngol;
    public static LOTRAchievement killMordorSpider;
    public static LOTRAchievement killMorgulSpider;
    public static LOTRAchievement tradeOrcSpiderKeeper;
    public static LOTRAchievement killMordorOrc;
    public static LOTRAchievement doMiniquestMordor;
    public static LOTRAchievement smeltBlackUrukSteel;
    public static LOTRAchievement wearFullBlackUruk;
    public static LOTRAchievement killBlackUruk;
    public static LOTRAchievement hireNurnSlave;
    public static LOTRAchievement enterMorgulVale;
    public static LOTRAchievement tradeBlackUrukCaptain;
    public static LOTRAchievement killWickedDwarf;
    public static LOTRAchievement tradeWickedDwarf;
    public static LOTRAchievement enterDorwinion;
    public static LOTRAchievement doMiniquestDorwinion;
    public static LOTRAchievement useDorwinionTable;
    public static LOTRAchievement wearFullDorwinion;
    public static LOTRAchievement wearFullDorwinionElf;
    public static LOTRAchievement killDorwinion;
    public static LOTRAchievement tradeDorwinionCaptain;
    public static LOTRAchievement killDorwinionElf;
    public static LOTRAchievement tradeDorwinionElfCaptain;
    public static LOTRAchievement drinkWine;
    public static LOTRAchievement harvestGrapes;
    public static LOTRAchievement buyWineVintner;
    public static LOTRAchievement hireDorwinionVinekeeper;
    public static LOTRAchievement stealDorwinionGrapes;
    public static LOTRAchievement tradeDorwinionMerchant;
    public static LOTRAchievement enterDorwinionHills;
    public static LOTRAchievement enterRhun;
    public static LOTRAchievement useRhunTable;
    public static LOTRAchievement killEasterling;
    public static LOTRAchievement doMiniquestRhun;
    public static LOTRAchievement wearFullRhun;
    public static LOTRAchievement tradeRhunBlacksmith;
    public static LOTRAchievement tradeRhunCaptain;
    public static LOTRAchievement hitBirdFirePot;
    public static LOTRAchievement getKineArawHorn;
    public static LOTRAchievement wearFullRhunGold;
    public static LOTRAchievement enterLastDesert;
    public static LOTRAchievement enterMountainsWind;
    public static LOTRAchievement tradeRhunMarketTrader;
    public static LOTRAchievement tradeRhunBartender;
    public static LOTRAchievement hireRhunFarmer;
    public static LOTRAchievement enterRhunLand;
    public static LOTRAchievement enterRhunRedwood;
    public static LOTRAchievement enterRhunIsland;
    public static LOTRAchievement enterRedMountains;
    public static LOTRAchievement enterHarondor;
    public static LOTRAchievement enterNearHarad;
    public static LOTRAchievement killNearHaradrim;
    public static LOTRAchievement useNearHaradTable;
    public static LOTRAchievement wearFullNearHarad;
    public static LOTRAchievement tradeNearHaradWarlord;
    public static LOTRAchievement rideCamel;
    public static LOTRAchievement tradeBazaarTrader;
    public static LOTRAchievement tradeNearHaradMerchant;
    public static LOTRAchievement doMiniquestNearHarad;
    public static LOTRAchievement cookKebab;
    public static LOTRAchievement enterNearHaradOasis;
    public static LOTRAchievement tradeNearHaradBlacksmith;
    public static LOTRAchievement enterHarnedor;
    public static LOTRAchievement enterSouthronCoasts;
    public static LOTRAchievement enterUmbar;
    public static LOTRAchievement enterLostladen;
    public static LOTRAchievement enterGulfHarad;
    public static LOTRAchievement useUmbarTable;
    public static LOTRAchievement useGulfTable;
    public static LOTRAchievement wearFullGulfHarad;
    public static LOTRAchievement wearFullCorsair;
    public static LOTRAchievement wearFullUmbar;
    public static LOTRAchievement wearFullHarnedor;
    public static LOTRAchievement wearFullNomad;
    public static LOTRAchievement tradeHarnedorWarlord;
    public static LOTRAchievement tradeUmbarCaptain;
    public static LOTRAchievement tradeCorsairCaptain;
    public static LOTRAchievement tradeNomadWarlord;
    public static LOTRAchievement tradeGulfWarlord;
    public static LOTRAchievement hireHaradSlave;
    public static LOTRAchievement hireMoredainMercenary;
    public static LOTRAchievement hireMorgulMercenary;
    public static LOTRAchievement hireEreborMercenary;
    public static LOTRAchievement tradeHarnedorBlacksmith;
    public static LOTRAchievement tradeUmbarBlacksmith;
    public static LOTRAchievement tradeGulfBlacksmith;
    public static LOTRAchievement doMiniquestGondorRenegade;
    public static LOTRAchievement tradeNomadMerchant;
    public static LOTRAchievement tradeHaradBartender;
    public static LOTRAchievement tradeNomadArmourer;
    public static LOTRAchievement hireHarnedorFarmer;
    public static LOTRAchievement tradeHaradFarmer;
    public static LOTRAchievement wearFullBlackNumenorean;
    public static LOTRAchievement enterFarHaradSavannah;
    public static LOTRAchievement pickBanana;
    public static LOTRAchievement growBaobab;
    public static LOTRAchievement enterFarHaradVolcano;
    public static LOTRAchievement killMoredain;
    public static LOTRAchievement useMoredainTable;
    public static LOTRAchievement wearFullMoredain;
    public static LOTRAchievement tradeMoredainChieftain;
    public static LOTRAchievement doMiniquestMoredain;
    public static LOTRAchievement tradeMoredainVillager;
    public static LOTRAchievement enterCorsairCoasts;
    public static LOTRAchievement enterFarHaradJungle;
    public static LOTRAchievement drinkMangoJuice;
    public static LOTRAchievement doMiniquestTauredain;
    public static LOTRAchievement useTauredainTable;
    public static LOTRAchievement wearFullTauredain;
    public static LOTRAchievement killTauredain;
    public static LOTRAchievement tradeTauredainChieftain;
    public static LOTRAchievement tradeTauredainShaman;
    public static LOTRAchievement smeltObsidianShard;
    public static LOTRAchievement tradeTauredainFarmer;
    public static LOTRAchievement hireTauredainFarmer;
    public static LOTRAchievement tradeTauredainSmith;
    public static LOTRAchievement wearFullTaurethrimGold;
    public static LOTRAchievement enterPertorogwaith;
    public static LOTRAchievement killHalfTroll;
    public static LOTRAchievement playerRespawn;
    public static LOTRAchievement playerDeath;
    public static LOTRAchievement wearFullHalfTroll;
    public static LOTRAchievement tradeHalfTrollWarlord;
    public static LOTRAchievement useHalfTrollTable;
    public static LOTRAchievement doMiniquestHalfTroll;
    public static LOTRAchievement tradeHalfTrollScavenger;
    public static LOTRAchievement enterHalfTrollForest;
    public static LOTRAchievement enterOcean;
    public static LOTRAchievement enterMeneltarma;
    public static LOTRAchievement enterUtumnoIce;
    public static LOTRAchievement enterUtumnoObsidian;
    public static LOTRAchievement enterUtumnoFire;
    public static LOTRAchievement wearFullUtumno;
    public static LOTRAchievement killUtumnoOrc;
    public static LOTRAchievement killUtumnoWarg;
    public static LOTRAchievement killBalrog;
    public static LOTRAchievement killTormentedElf;
    public static LOTRAchievement killDarkElf;
    public static LOTRAchievement killUtumnoTroll;
    public static LOTRAchievement killObsidianTroll;
    public static LOTRAchievement killFireTroll;
    public static LOTRAchievement killObsidianSpider;
    public static LOTRAchievement killFireSpider;
    public static LOTRAchievement craftUtumnoKey;
    public static LOTRAchievement leaveUtumno;
    public static LOTRAchievement useAngbandTable;
    public static LOTRAchievement useEreborTable;
    public static LOTRAchievement useWindTable;
    public static LOTRAchievement useRedDwarfTable;
    public static LOTRAchievement enterHarnaugrim;
    public static LOTRAchievement enterStiffbeard;
    public static LOTRAchievement marryMornaugrimDwarf;
    public static LOTRAchievement tradeAngbandSpiderUrukCaptain;
    public static LOTRAchievement tradeAngbandWargUrukCaptain;
    public static LOTRAchievement enterLotr;
    public static LOTRAchievement killCaveTroll;
    public static LOTRAchievement useRedMountainsTable1;
    public static LOTRAchievement killRoyal;
    public static LOTRAchievement killAngband;
    public static LOTRAchievement enterAngband;
    public static LOTRAchievement enterAngbandMountains;
    public static LOTRAchievement tradeAngbandUrukCaptain;
    public static LOTRAchievement hireErebor;
    public static LOTRAchievement hireWind;
    public static LOTRAchievement tradeRedDwarfSmith;
    public static LOTRAchievement tradeUtumnoUrukTrader;
    public static LOTRAchievement doMiniquestRedDwarf;
    public static LOTRAchievement doMiniquestWind;
    public static LOTRAchievement doMiniquestRedDwarf1;
    public static LOTRAchievement craftgoldRing;
    public static LOTRAchievement craftMoriaForge;
    public static LOTRAchievement craftMoriaForgeMark2;
    public static LOTRAchievement craftironPan;
    public static LOTRAchievement killDurmethOrc;
    public static LOTRAchievement killDurmethWarg;
    public static LOTRAchievement doMiniquestAngband;
    public static LOTRAchievement doMiniquestDurmeth;
    public static LOTRAchievement tradeBlacklock;
    public static LOTRAchievement tradeStiffbeard;
    public static LOTRAchievement tradeStonefoot;
    public static LOTRAchievement tradeIronfist;
    public static LOTRAchievement tradeDurmethOrc;
    public static LOTRAchievement enterDurmeth;
    public static LOTRAchievement enterDurmethMountains;
    public static LOTRAchievement enterKhand;
    public static LOTRAchievement enterHildorien;
    public static LOTRAchievement enterWastelandRhun;
    public static LOTRAchievement enterRhunTaiga;
    public static LOTRAchievement enterWildRhun;
    public static LOTRAchievement enterHarhudor;
    public static LOTRAchievement enterRedMountainsSnow;
    public static LOTRAchievement enterRedMountainsCentre;
    public static LOTRAchievement smeltMithril;
    public static LOTRAchievement wearMoriaMithril;
    public static LOTRAchievement wearMoriaMithrilHelmet;
    public static LOTRAchievement wearMoriaMithrilChest;
    public static LOTRAchievement wearMoriaMithrilLegs;
    public static LOTRAchievement wearMoriaMithrilBoots;
    public static LOTRAchievement wearFullMoriaMithril;
    public static LOTRAchievement wearFullMoria;
    public static LOTRAchievement wearFullDwarvenMithril;
    public static LOTRAchievement wearFullErebor;
    public static LOTRAchievement wearFullGuldur;
    public static LOTRAchievement wearAngband;
    public static LOTRAchievement wearAngbandu;
    public static LOTRAchievement wearBerserk;
    public static LOTRAchievement wearWind;
    public static LOTRAchievement enterEasternCoasts;
    public static LOTRAchievement enterSun;
    public static LOTRAchievement getOne;
    public static LOTRAchievement getSeven;
    public static LOTRAchievement getVilia;
    public static LOTRAchievement getNenia;
    public static LOTRAchievement getNaria;
    public static LOTRAchievement getDwarven1;
    public static LOTRAchievement getDwarven2;
    public static LOTRAchievement getDwarven3;
    public static LOTRAchievement getDwarven4;
    public static LOTRAchievement getDwarven5;
    public static LOTRAchievement getDwarven6;
    public static LOTRAchievement getDwarven7;
    public static LOTRAchievement getNine;
    public static LOTRAchievement getThree;
    public static LOTRAchievement getArcenstone;
    public static LOTRAchievement getBilbo;
    public static LOTRAchievement wearBilbo;
    public static LOTRAchievement enterDimrill;
    public static LOTRAchievement enterBizar;
    public static LOTRAchievement wearAngbande;
    public static LOTRAchievement wearBoldog;
    public static LOTRAchievement getSilmaril;
    public static LOTRAchievement useRing;
    public static LOTRAchievement treasure;
    public static LOTRAchievement treasureRare;
    public static LOTRAchievement treasureUltraRare;
    public static LOTRAchievement useRingSuicide;
    public static LOTRAchievement feelBannerProtection;
    public static LOTRAchievement useBandage;
    public static LOTRAchievement enterForestShelter;
    public static LOTRAchievement enterSouthMirkwood;
    public static LOTRAchievement enterBeornsDomain;
    public static LOTRAchievement enterHarhudorJungle;
    public static LOTRAchievement enterRohanGate;
    public static LOTRAchievement burnCampfire;
    public static LOTRAchievement wearFullIstari;
    public static LOTRAchievement useOneRing;
    public static LOTRAchievement killSauron;
    public static LOTRAchievement killBoldog;
    public static LOTRAchievement tradePallando;
    public static LOTRAchievement wearFullSauron;
    public static LOTRAchievement craftMithril;
    public static LOTRAchievement wearFullBronze;
    public static LOTRAchievement wearFullBone;
    public static LOTRAchievement enterFallForodwaith;
    public static LOTRAchievement tradeRedDwarfMerchant;
    public static LOTRAchievement enterIceUgol;
    public static LOTRAchievement enterRhunSemiDesert;
    public static LOTRAchievement enterAnfalas;
    public static LOTRAchievement wearFullGrey;
    public static LOTRAchievement wearFullDark;
    public static LOTRAchievement wearFullAvari;
    public static LOTRAchievement wearFullAvariScout;
    public static LOTRAchievement tradeRedDwarfMiner;
    public static LOTRAchievement killMoriaOrc;
    public static LOTRAchievement tradeMoriaOrc;
    public static LOTRAchievement useWickedDwarfTable;
    public static LOTRAchievement killMountainUtumnoSnowTroll;
    public static LOTRAchievement drinkUrukDraught;
    public static LOTRAchievement eatKham;
    public static LOTRAchievement drinkKhamBrew;
    public static LOTRAchievement drinkRedDwarvenTonic;
    public static LOTRAchievement eatlembasNugget;
    public static LOTRAchievement drinkFire;
    public static LOTRAchievement drinkSalt;
    public static LOTRAchievement minesusSand;
    public static LOTRAchievement wearFullGondorMithril;
    public static LOTRAchievement insearchofyourself;
    public static LOTRAchievement tradeAvariElfSmith;
    public static LOTRAchievement enterAvariForest;
    public static LOTRAchievement useAvariTable;
    public static LOTRAchievement useWickedElfTable;
    public static LOTRAchievement wearFullDarkElf;
    public static LOTRAchievement wearFullDarkElfRanger;
    public static LOTRAchievement getKham;
    public static LOTRAchievement earnManyCoins4;
    public static LOTRAchievement wearFullChainmailBronze;
    public static LOTRAchievement wearFullChainmailDwarf;
    public static LOTRAchievement wearFullChainmailOrc;
    public static LOTRAchievement wearFullChainmailUruk;
    public static LOTRAchievement wearFullChainmailBlackUruk;
    public static LOTRAchievement wearFullWhite;
    public static LOTRAchievement wearFullBrown;
    public static LOTRAchievement wearFullBlue;
    public static LOTRAchievement wearFullStonefoot;
    public static LOTRAchievement wearFullStiffbeard;
    public static LOTRAchievement wearFullIronfist;
    public static LOTRAchievement wearFullBlacklock;
    public static LOTRAchievement notenough;
    public static LOTRAchievement killIceSpider;
    public static LOTRAchievement useMoriaForge;
    public static LOTRAchievement useUpgradedMoriaForge;
    public static LOTRAchievement mineMithrilPlus;
    public static LOTRAchievement killDurmethOrcWarrior;
    public static LOTRAchievement highground;
    public static LOTRAchievement theVergeofDeath;
    public static LOTRAchievement wearFullAragorn;
    public static LOTRAchievement wearFullSilver;
    public static LOTRAchievement usemagicClover;
    public static LOTRAchievement usetotemOfUndying;
    public static LOTRAchievement sweetDreams;
    public static LOTRAchievement useArrowExplosion;
    public static LOTRAchievement useArrowFire;
    public static LOTRAchievement useArrowMorgul;
    public static LOTRAchievement doMiniquestEnt;
    public static LOTRAchievement drinkMithril;
    public static LOTRAchievement tradeRadaghast;
    public static LOTRAchievement killDeveloper;
    public static LOTRAchievement wearFullDurmeth;
    public static LOTRAchievement alltotheBottom;
    public static LOTRAchievement three;
    public static LOTRAchievement thousands;
    public static LOTRAchievement spongeBob;
    public static LOTRAchievement defeatInvasion10;
    public static LOTRAchievement defeatInvasion50;
    public static LOTRAchievement defeatInvasion100;
    public static LOTRAchievement defeatInvasion200;
    public static LOTRAchievement fightorflight;
    public static LOTRAchievement thejokesareover;
    public static LOTRAchievement setBronze;
    public static LOTRAchievement setSilver;
    public static LOTRAchievement setGold;
    public static LOTRAchievement setMithril;
    public static LOTRAchievement setBlockGem;
    public static LOTRAchievement theLordofTheRings;
    public static LOTRAchievement flyingWarg;
    public static LOTRAchievement sellMushroom;
    public static LOTRAchievement killMorgulAngmarOrc;
    public static LOTRAchievement killGuldurUruk;
    public static LOTRAchievement climbRedMountains;
    public static LOTRAchievement killEreborDwarf;
    public static LOTRAchievement marryEreborDwarf;
    public static LOTRAchievement burnRing;
    public static LOTRAchievement destroyRing;
    public static LOTRAchievement treasureMillionere;
    public static LOTRAchievement getNumenorBow;
    public static LOTRAchievement killRedDwarf;
    public static LOTRAchievement useGraal;
    public static LOTRAchievement hireWindDwarfAxe;
    public static LOTRAchievement killWindDwarf;
    public static LOTRAchievement marryWindDwarf;
    public static LOTRAchievement killCaveSpider;
    public static LOTRAchievement fishBusiness;
    public static LOTRAchievement craftPot;
    public static LOTRAchievement salvageSherd;
    public static LOTRAchievement killDragon;
    public static LOTRAchievement killDragonScout;
    public static LOTRAchievement killDragonHunter;
    public static LOTRAchievement killDragonAlpha;
    public static LOTRAchievement killDragonAnkalagon;
    public static LOTRAchievement sellArrowDragon;
    public static LOTRAchievement useArrowDragon;
    public static LOTRAchievement playerDeathDragon;
    public static LOTRAchievement playerDeathDragonFire;
    public static LOTRAchievement marathon;
    public static LOTRAchievement tradeEreborDwarfMerchant;
    private EnumChatFormatting color = EnumChatFormatting.YELLOW;
    public boolean isRare = false;

    public LOTRAchievement(Category c, int i, Block block, String s) {
        this(c, i, new ItemStack(block), s);
    }

    public LOTRAchievement(Category c, int i, Item item, String s) {
        this(c, i, new ItemStack(item), s);
    }

    public LOTRAchievement setRare() {
        this.isRare = true;
        this.color = EnumChatFormatting.DARK_PURPLE;
        return this;
    }

    public LOTRAchievement setColor(EnumChatFormatting color) {
        this.color = color;
        return this;
    }

    public EnumChatFormatting getColor() {
        if (this.isRare) {
            return EnumChatFormatting.DARK_PURPLE;
        }
        return EnumChatFormatting.YELLOW;
    }

    public EnumChatFormatting getColor1() {
        if (this.isRare) {
            return EnumChatFormatting.DARK_PURPLE;
        }
        return this.color;
    }

    public LOTRAchievement(Category c, int i, ItemStack itemstack, String s) {
        this.category = c;
        this.ID = i;
        this.icon = itemstack;
        this.name = s;
        this.color = EnumChatFormatting.YELLOW;
        for (LOTRAchievement achievement : this.category.list) {
            if (achievement.ID != this.ID) continue;
            throw new IllegalArgumentException("Duplicate ID " + this.ID + " for LOTR achievement category " + this.category.name());
        }
        this.category.list.add(this);
        this.getDimension().allAchievements.add(this);
    }

    public LOTRAchievement setBiomeAchievement() {
        this.isBiomeAchievement = true;
        return this;
    }

    public LOTRAchievement setSpecial() {
        this.isSpecial = true;
        return this;
    }

    public LOTRAchievement setRequiresEnemy(LOTRFaction ... f) {
        this.enemyFactions.addAll(Arrays.asList(f));
        return this;
    }

    public LOTRAchievement setRequiresAnyEnemy(List<LOTRFaction> f) {
        return this.setRequiresEnemy(f.toArray((T[])new LOTRFaction[0]));
    }

    public LOTRAchievement setRequiresAlly(LOTRFaction ... f) {
        this.allyFactions.addAll(Arrays.asList(f));
        return this;
    }

    public LOTRAchievement setRequiresAnyAlly(List<LOTRFaction> f) {
        return this.setRequiresAlly(f.toArray((T[])new LOTRFaction[0]));
    }

    public LOTRAchievement createTitle() {
        return this.createTitle(null);
    }

    public LOTRAchievement createTitle(String s) {
        if (this.achievementTitle != null) {
            throw new IllegalArgumentException("LOTR achievement " + this.getCodeName() + " already has an associated title!");
        }
        this.achievementTitle = new LOTRTitle(s, this);
        return this;
    }

    public LOTRTitle getAchievementTitle() {
        return this.achievementTitle;
    }

    public String getCodeName() {
        return this.name;
    }

    public String getUntranslatedTitle(EntityPlayer entityplayer) {
        return "lotr.achievement." + this.name + ".title";
    }

    public final String getTitle(EntityPlayer entityplayer) {
        return StatCollector.translateToLocal((String)this.getUntranslatedTitle(entityplayer));
    }

    public String getDescription(EntityPlayer entityplayer) {
        return StatCollector.translateToLocal((String)("lotr.achievement." + this.name + ".desc"));
    }

    public LOTRDimension getDimension() {
        return this.category.dimension;
    }

    public boolean canPlayerEarn(EntityPlayer entityplayer) {
        float alignment;
        LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
        if (!this.enemyFactions.isEmpty()) {
            boolean anyEnemies = false;
            for (LOTRFaction f : this.enemyFactions) {
                alignment = playerData.getAlignment(f);
                if (!(alignment <= 0.0f)) continue;
                anyEnemies = true;
            }
            if (!anyEnemies) {
                return false;
            }
        }
        if (!this.allyFactions.isEmpty()) {
            boolean anyAllies = false;
            for (LOTRFaction f : this.allyFactions) {
                alignment = playerData.getAlignment(f);
                if (!(alignment >= 0.0f)) continue;
                anyAllies = true;
            }
            if (!anyAllies) {
                return false;
            }
        }
        return true;
    }

    public LOTRAchievement setRing() {
        this.isRing = true;
        return this;
    }

    public static Comparator<LOTRAchievement> sortForDisplay(final EntityPlayer entityplayer) {
        return new Comparator<LOTRAchievement>(){

            @Override
            public int compare(LOTRAchievement ach1, LOTRAchievement ach2) {
                if (ach1.isSpecial) {
                    if (!ach2.isSpecial) {
                        return -1;
                    }
                    if (ach2.ID < ach1.ID) {
                        return 1;
                    }
                    if (ach2.ID == ach1.ID) {
                        return 0;
                    }
                    if (ach2.ID > ach1.ID) {
                        return -1;
                    }
                } else if (ach2.isSpecial) {
                    return 1;
                }
                if (ach1.isRing) {
                    if (!ach2.isRing) {
                        return -1;
                    }
                    if (ach2.ID < ach1.ID) {
                        return 1;
                    }
                    if (ach2.ID == ach1.ID) {
                        return 0;
                    }
                    if (ach2.ID > ach1.ID) {
                        return -1;
                    }
                } else if (ach2.isRing) {
                    return 1;
                }
                if (ach1.isBiomeAchievement) {
                    if (ach2.isBiomeAchievement) {
                        return ach1.getTitle(entityplayer).compareTo(ach2.getTitle(entityplayer));
                    }
                    return -1;
                }
                if (!ach2.isBiomeAchievement) {
                    return ach1.getTitle(entityplayer).compareTo(ach2.getTitle(entityplayer));
                }
                return 1;
            }
        };
    }

    public static void createAchievements() {
        enterMiddleEarth = new LOTRAchievement(Category.GENERAL, 1, LOTRMod.redBook, "enterMiddleEarth").setSpecial();
        doGreyQuest = new LOTRAchievement(Category.GENERAL, 2, LOTRMod.gandalfStaffGrey, "doGreyQuest");
        killOrc = new LOTRAchievement(Category.GENERAL, 14, LOTRMod.orcBone, "killOrc").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        mineMithril = new LOTRAchievement(Category.GENERAL, 15, LOTRMod.oreMithril, "mineMithril").createTitle().setRare();
        rideWarg = new LOTRAchievement(Category.GENERAL, 16, Items.saddle, "rideWarg").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        killWarg = new LOTRAchievement(Category.GENERAL, 17, LOTRMod.wargBone, "killWarg").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        useSpearFromFar = new LOTRAchievement(Category.GENERAL, 18, LOTRMod.spearIron, "useSpearFromFar");
        wearFullMithril = new LOTRAchievement(Category.GENERAL, 19, LOTRMod.bodyMithril, "wearFullMithril").setRare();
        gainHighAlcoholTolerance = new LOTRAchievement(Category.GENERAL, 20, LOTRMod.mugRum, "gainHighAlcoholTolerance").createTitle("alcoholic").setRare();
        craftSaddle = new LOTRAchievement(Category.GENERAL, 21, Items.saddle, "craftSaddle");
        craftBronze = new LOTRAchievement(Category.GENERAL, 22, LOTRMod.bronze, "craftBronze");
        drinkOrcDraught = new LOTRAchievement(Category.GENERAL, 23, LOTRMod.mugOrcDraught, "drinkOrcDraught");
        getPouch = new LOTRAchievement(Category.GENERAL, 24, LOTRMod.pouch, "getPouch");
        killUsingOnlyPlates = new LOTRAchievement(Category.GENERAL, 25, LOTRMod.plate, "killUsingOnlyPlates").createTitle();
        wearFullFur = new LOTRAchievement(Category.GENERAL, 26, LOTRMod.bodyFur, "wearFullFur");
        brewDrinkInBarrel = new LOTRAchievement(Category.GENERAL, 27, LOTRMod.barrel, "brewDrinkInBarrel").createTitle("brewDrink");
        findAthelas = new LOTRAchievement(Category.GENERAL, 28, LOTRMod.athelas, "findAthelas");
        drinkAthelasBrew = new LOTRAchievement(Category.GENERAL, 29, LOTRMod.mugAthelasBrew, "drinkAthelasBrew");
        killLargeMobWithSlingshot = new LOTRAchievement(Category.GENERAL, 30, LOTRMod.sling, "killLargeMobWithSlingshot");
        eatMaggotyBread = new LOTRAchievement(Category.GENERAL, 31, LOTRMod.maggotyBread, "eatMaggotyBread");
        killWhileDrunk = new LOTRAchievement(Category.GENERAL, 32, LOTRMod.mugAle, "killWhileDrunk").createTitle();
        collectCraftingTables = new LOTRAchievement(Category.GENERAL, 33, Blocks.crafting_table, "collectCraftingTables").createTitle();
        hitByOrcSpear = new LOTRAchievement(Category.GENERAL, 34, LOTRMod.spearOrc, "hitByOrcSpear").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC));
        killBombardier = new LOTRAchievement(Category.GENERAL, 35, LOTRMod.orcBomb, "killBombardier").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC));
        earnManyCoins = new LOTRAchievement(Category.GENERAL, 36, new ItemStack(LOTRMod.silverCoin, 1, 1), "earnManyCoins").createTitle();
        craftAppleCrumble = new LOTRAchievement(Category.GENERAL, 37, LOTRMod.appleCrumbleItem, "craftAppleCrumble");
        killButterfly = new LOTRAchievement(Category.GENERAL, 38, Items.iron_sword, "killButterfly").createTitle();
        fishRing = new LOTRAchievement(Category.GENERAL, 39, (Item)Items.fishing_rod, "fishRing").createTitle("fishRing").setRare();
        useCrossbow = new LOTRAchievement(Category.GENERAL, 40, LOTRMod.ironCrossbow, "useCrossbow");
        collectCrossbowBolts = new LOTRAchievement(Category.GENERAL, 41, LOTRMod.crossbowBolt, "collectCrossbowBolts");
        travel10 = new LOTRAchievement(Category.GENERAL, 42, (Item)Items.leather_boots, "travel10").setSpecial();
        travel20 = new LOTRAchievement(Category.GENERAL, 43, Items.compass, "travel20").setSpecial();
        travel30 = new LOTRAchievement(Category.GENERAL, 44, (Item)Items.map, "travel30").setSpecial();
        attackRabbit = new LOTRAchievement(Category.GENERAL, 45, Items.wheat_seeds, "attackRabbit");
        craftRabbitStew = new LOTRAchievement(Category.GENERAL, 46, LOTRMod.rabbitStew, "craftRabbitStew");
        drinkSkull = new LOTRAchievement(Category.GENERAL, 47, LOTRMod.skullCup, "drinkSkull").createTitle("drinkSkull");
        travel40 = new LOTRAchievement(Category.GENERAL, 48, (Item)Items.map, "travel40").setSpecial();
        travel50 = new LOTRAchievement(Category.GENERAL, 49, (Item)Items.map, "travel50").setSpecial().createTitle("explore50Biomes");
        killThievingBandit = new LOTRAchievement(Category.GENERAL, 50, LOTRMod.leatherHat, "killThievingBandit");
        hundreds = new LOTRAchievement(Category.GENERAL, 51, LOTRMod.commandSword, "hundreds");
        alloyBronze = new LOTRAchievement(Category.GENERAL, 52, LOTRMod.alloyForge, "alloyBronze");
        bannerProtect = new LOTRAchievement(Category.GENERAL, 53, LOTRMod.banner, "bannerProtect").createTitle();
        catchButterfly = new LOTRAchievement(Category.GENERAL, 54, LOTRMod.butterflyJar, "catchButterfly");
        unsmelt = new LOTRAchievement(Category.GENERAL, 55, LOTRMod.unsmeltery, "unsmelt");
        tradeScrapTrader = new LOTRAchievement(Category.GENERAL, 56, LOTRMod.silverCoin, "tradeScrapTrader");
        cookDeer = new LOTRAchievement(Category.GENERAL, 57, LOTRMod.deerCooked, "cookDeer");
        eatManFlesh = new LOTRAchievement(Category.GENERAL, 58, LOTRMod.manFlesh, "eatManFlesh").setRequiresAnyAlly(LOTRItemManFlesh.getManFleshFactions()).createTitle("manFlesh");
        craftSaltedFlesh = new LOTRAchievement(Category.GENERAL, 59, LOTRMod.saltedFlesh, "craftSaltedFlesh").createTitle("saltedFlesh");
        enchantBaneElf = new LOTRAchievement(Category.GENERAL, 60, LOTRMod.scimitarOrc, "enchantBaneElf").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ELF)).createTitle();
        enchantBaneOrc = new LOTRAchievement(Category.GENERAL, 61, LOTRMod.swordHighElven, "enchantBaneOrc").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        enchantBaneDwarf = new LOTRAchievement(Category.GENERAL, 62, LOTRMod.battleaxeGundabadUruk, "enchantBaneDwarf").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF)).createTitle();
        enchantBaneWarg = new LOTRAchievement(Category.GENERAL, 63, LOTRMod.swordRohan, "enchantBaneWarg").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        enchantBaneTroll = new LOTRAchievement(Category.GENERAL, 64, LOTRMod.swordArnor, "enchantBaneTroll").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_TROLL)).createTitle();
        enchantBaneSpider = new LOTRAchievement(Category.GENERAL, 65, LOTRMod.sting, "enchantBaneSpider").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        enchantBaneWight = new LOTRAchievement(Category.GENERAL, 66, LOTRMod.daggerBarrow, "enchantBaneWight").createTitle();
        getDrunk = new LOTRAchievement(Category.GENERAL, 67, LOTRMod.mugAle, "getDrunk").createTitle();
        reforge = new LOTRAchievement(Category.GENERAL, 68, Blocks.anvil, "reforge").createTitle();
        doMiniquestHunter = new LOTRAchievement(Category.GENERAL, 69, LOTRMod.redBook, "doMiniquestHunter").createTitle();
        doMiniquestHunter5 = new LOTRAchievement(Category.GENERAL, 70, LOTRMod.bountyTrophy, "doMiniquestHunter5").createTitle();
        killHuntingPlayer = new LOTRAchievement(Category.GENERAL, 71, Items.iron_sword, "killHuntingPlayer").createTitle();
        pledgeService = new LOTRAchievement(Category.GENERAL, 72, LOTRMod.goldRing, "pledgeService");
        factionConquest = new LOTRAchievement(Category.GENERAL, 73, LOTRMod.commandTable, "factionConquest");
        defeatInvasion = new LOTRAchievement(Category.GENERAL, 74, Items.stone_sword, "defeatInvasion");
        pickpocket = new LOTRAchievement(Category.GENERAL, 75, LOTRMiniQuestPickpocket.createPickpocketIcon(), "pickpocket").createTitle("pickpocket");
        combineSmithScrolls = new LOTRAchievement(Category.GENERAL, 76, LOTRMod.modTemplate, "combineSmithScrolls");
        engraveOwnership = new LOTRAchievement(Category.GENERAL, 77, LOTRMod.theOneRing, "engraveOwnership");
        killHobbit = new LOTRAchievement(Category.SHIRE, 0, LOTRMod.hobbitBone, "killHobbit").setRequiresEnemy(LOTRFaction.HOBBIT).createTitle();
        sellPipeweedLeaf = new LOTRAchievement(Category.SHIRE, 1, LOTRMod.pipeweedLeaf, "sellPipeweedLeaf").setRequiresAlly(LOTRFaction.HOBBIT);
        marryHobbit = new LOTRAchievement(Category.SHIRE, 2, LOTRMod.hobbitRing, "marryHobbit").setRequiresAlly(LOTRFaction.HOBBIT);
        findFourLeafClover = new LOTRAchievement(Category.SHIRE, 3, new ItemStack(LOTRMod.clover, 1, 1), "findFourLeafClover").createTitle("fourLeafClover").setRare();
        useMagicPipe = new LOTRAchievement(Category.SHIRE, 4, LOTRMod.hobbitPipe, "useMagicPipe").createTitle().setRare();
        rideShirePony = new LOTRAchievement(Category.SHIRE, 5, Items.saddle, "rideShirePony");
        tradeBartender = new LOTRAchievement(Category.SHIRE, 6, LOTRMod.silverCoin, "tradeBartender").setRequiresAlly(LOTRFaction.HOBBIT);
        speakToDrunkard = new LOTRAchievement(Category.SHIRE, 7, LOTRMod.mugAle, "speakToDrunkard");
        tradeHobbitShirriff = new LOTRAchievement(Category.SHIRE, 8, LOTRMod.silverCoin, "tradeHobbitShirriffChief").setRequiresAlly(LOTRFaction.HOBBIT);
        killDarkHuorn = new LOTRAchievement(Category.SHIRE, 9, Blocks.log, "killDarkHuorn");
        enterOldForest = new LOTRAchievement(Category.SHIRE, 10, Blocks.log, "enterOldForest").setBiomeAchievement();
        buyOrcharderFood = new LOTRAchievement(Category.SHIRE, 11, Items.apple, "buyOrcharderFood").setRequiresAlly(LOTRFaction.HOBBIT);
        rideGiraffeShire = new LOTRAchievement(Category.SHIRE, 15, Items.saddle, "rideGiraffeShire").createTitle("zookeeper").setRare();
        buyPotatoHobbitFarmer = new LOTRAchievement(Category.SHIRE, 16, Items.potato, "buyPotatoHobbitFarmer").setRequiresAlly(LOTRFaction.HOBBIT);
        doMiniquestHobbit = new LOTRAchievement(Category.SHIRE, 17, LOTRMod.redBook, "doMiniquestHobbit").setRequiresAlly(LOTRFaction.HOBBIT);
        enterWhiteDowns = new LOTRAchievement(Category.SHIRE, 18, new ItemStack(LOTRMod.rock, 1, 5), "enterWhiteDowns").setBiomeAchievement();
        useHobbitTable = new LOTRAchievement(Category.SHIRE, 19, LOTRMod.hobbitTable, "useHobbitTable").setRequiresAlly(LOTRFaction.HOBBIT);
        hireHobbitFarmer = new LOTRAchievement(Category.SHIRE, 20, Items.iron_hoe, "hireHobbitFarmer").setRequiresAlly(LOTRFaction.HOBBIT);
        craftironPan = new LOTRAchievement(Category.SHIRE, 21, LOTRMod.ironPan, "craftironPan");
        tradeHobbit = new LOTRAchievement(Category.SHIRE, 22, LOTRMod.silverCoin, "tradeHobbit").setRequiresAlly(LOTRFaction.HOBBIT);
        enterBlueMountains = new LOTRAchievement(Category.BLUE_MOUNTAINS, 0, new ItemStack(LOTRMod.rock, 1, 3), "enterBlueMountains").setBiomeAchievement();
        smeltBlueDwarfSteel = new LOTRAchievement(Category.BLUE_MOUNTAINS, 4, LOTRMod.blueDwarfSteel, "smeltBlueDwarfSteel");
        killBlueDwarf = new LOTRAchievement(Category.BLUE_MOUNTAINS, 5, LOTRMod.dwarfBone, "killBlueDwarf").setRequiresEnemy(LOTRFaction.BLUE_MOUNTAINS).createTitle();
        wearFullBlueDwarven = new LOTRAchievement(Category.BLUE_MOUNTAINS, 6, LOTRMod.bodyBlueDwarven, "wearFullBlueDwarven");
        useBlueDwarvenTable = new LOTRAchievement(Category.BLUE_MOUNTAINS, 7, LOTRMod.blueDwarvenTable, "useBlueDwarvenTable").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        tradeBlueDwarfMiner = new LOTRAchievement(Category.BLUE_MOUNTAINS, 8, LOTRMod.silverCoin, "tradeBlueDwarfMiner").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        tradeBlueDwarfCommander = new LOTRAchievement(Category.BLUE_MOUNTAINS, 9, LOTRMod.silverCoin, "tradeBlueDwarfCommander").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        tradeBlueDwarfMerchant = new LOTRAchievement(Category.BLUE_MOUNTAINS, 10, LOTRMod.silverCoin, "tradeBlueDwarfMerchant").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        marryBlueDwarf = new LOTRAchievement(Category.BLUE_MOUNTAINS, 11, LOTRMod.dwarvenRing, "marryBlueDwarf").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        doMiniquestBlueMountains = new LOTRAchievement(Category.BLUE_MOUNTAINS, 12, LOTRMod.redBook, "doMiniquestBlueMountains").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        tradeBlueDwarfSmith = new LOTRAchievement(Category.BLUE_MOUNTAINS, 13, LOTRMod.silverCoin, "tradeBlueDwarfSmith").setRequiresAlly(LOTRFaction.BLUE_MOUNTAINS);
        enterLindon = new LOTRAchievement(Category.LINDON, 0, new ItemStack(LOTRMod.brick3, 1, 2), "enterLindon").setBiomeAchievement();
        doMiniquestHighElf = new LOTRAchievement(Category.LINDON, 1, LOTRMod.redBook, "doMiniquestHighElf").setRequiresAlly(LOTRFaction.HIGH_ELF);
        killHighElf = new LOTRAchievement(Category.LINDON, 5, LOTRMod.elfBone, "killHighElf").setRequiresEnemy(LOTRFaction.HIGH_ELF).createTitle();
        tradeHighElfLord = new LOTRAchievement(Category.LINDON, 6, LOTRMod.silverCoin, "tradeHighElfLord").setRequiresAlly(LOTRFaction.HIGH_ELF);
        useHighElvenTable = new LOTRAchievement(Category.LINDON, 7, LOTRMod.highElvenTable, "useHighElvenTable").setRequiresAlly(LOTRFaction.HIGH_ELF);
        wearFullHighElven = new LOTRAchievement(Category.LINDON, 8, LOTRMod.bodyHighElven, "wearFullHighElven");
        smeltElfSteel = new LOTRAchievement(Category.LINDON, 9, LOTRMod.elfSteel, "smeltElfSteel");
        tradeHighElfSmith = new LOTRAchievement(Category.LINDON, 10, LOTRMod.silverCoin, "tradeHighElfSmith").setRequiresAlly(LOTRFaction.HIGH_ELF);
        wearFullGondolin = new LOTRAchievement(Category.LINDON, 11, LOTRMod.bodyGondolin, "wearFullGondolin").createTitle("wearFullGondolin");
        enterTowerHills = new LOTRAchievement(Category.LINDON, 12, new ItemStack(LOTRMod.brick4, 1, 15), "enterTowerHills").setBiomeAchievement();
        wearFullGalvorn = new LOTRAchievement(Category.LINDON, 13, LOTRMod.bodyGalvorn, "wearFullGalvorn");
        enterBreeland = new LOTRAchievement(Category.BREE_LAND, 0, new ItemStack(LOTRMod.cobblebrick, 1, 0), "enterBreeland").setBiomeAchievement();
        enterChetwood = new LOTRAchievement(Category.BREE_LAND, 1, Blocks.sapling, "enterChetwood").setBiomeAchievement();
        killBreelander = new LOTRAchievement(Category.BREE_LAND, 2, Items.bone, "killBreelander").setRequiresEnemy(LOTRFaction.BREE).createTitle();
        doMiniquestBree = new LOTRAchievement(Category.BREE_LAND, 3, LOTRMod.redBook, "doMiniquestBree").setRequiresAlly(LOTRFaction.BREE);
        tradeBreeCaptain = new LOTRAchievement(Category.BREE_LAND, 4, LOTRMod.silverCoin, "tradeBreeCaptain").setRequiresAlly(LOTRFaction.BREE);
        useBreeTable = new LOTRAchievement(Category.BREE_LAND, 5, LOTRMod.breeTable, "useBreeTable").setRequiresAlly(LOTRFaction.BREE);
        tradeBreeBlacksmith = new LOTRAchievement(Category.BREE_LAND, 6, LOTRMod.silverCoin, "tradeBreeBlacksmith").setRequiresAlly(LOTRFaction.BREE);
        tradeBreeInnkeeper = new LOTRAchievement(Category.BREE_LAND, 7, LOTRMod.silverCoin, "tradeBreeInnkeeper").setRequiresAlly(LOTRFaction.BREE);
        killBreeHobbit = new LOTRAchievement(Category.BREE_LAND, 8, LOTRMod.hobbitBone, "killBreeHobbit").setRequiresEnemy(LOTRFaction.BREE).createTitle();
        killRuffianSpy = new LOTRAchievement(Category.BREE_LAND, 9, LOTRMod.leatherHat, "killRuffianSpy").createTitle();
        killRuffianBrute = new LOTRAchievement(Category.BREE_LAND, 10, LOTRMod.battleaxeIron, "killRuffianBrute");
        doMiniquestRuffianSpy = new LOTRAchievement(Category.BREE_LAND, 11, LOTRMod.redBook, "doMiniquestRuffianSpy");
        doMiniquestRuffianBrute = new LOTRAchievement(Category.BREE_LAND, 12, LOTRMod.redBook, "doMiniquestRuffianBrute");
        tradeBreeMarketTrader = new LOTRAchievement(Category.BREE_LAND, 13, LOTRMod.silverCoin, "tradeBreeMarketTrader").setRequiresAlly(LOTRFaction.BREE);
        buyAppleBreeFarmer = new LOTRAchievement(Category.BREE_LAND, 14, LOTRMod.appleGreen, "buyAppleBreeFarmer").setRequiresAlly(LOTRFaction.BREE);
        hireBreeFarmer = new LOTRAchievement(Category.BREE_LAND, 15, Items.iron_hoe, "hireBreeFarmer").setRequiresAlly(LOTRFaction.BREE);
        killRangerNorth = new LOTRAchievement(Category.ERIADOR, 0, LOTRMod.rangerBow, "killRangerNorth").setRequiresEnemy(LOTRFaction.RANGER_NORTH).createTitle();
        wearFullRanger = new LOTRAchievement(Category.ERIADOR, 1, LOTRMod.bodyRanger, "wearFullRanger");
        killTroll = new LOTRAchievement(Category.ERIADOR, 2, LOTRMod.trollBone, "killTroll").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        getTrollStatue = new LOTRAchievement(Category.ERIADOR, 3, LOTRMod.trollStatue, "getTrollStatue").createTitle();
        makeTrollSneeze = new LOTRAchievement(Category.ERIADOR, 4, Items.slime_ball, "makeTrollSneeze").setRequiresAlly(LOTRFaction.GUNDABAD);
        killMountainTroll = new LOTRAchievement(Category.ERIADOR, 5, LOTRMod.trollBone, "killMountainTroll").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        killTrollFleeingSun = new LOTRAchievement(Category.ERIADOR, 6, Items.feather, "killTrollFleeingSun").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        killMountainTrollChieftain = new LOTRAchievement(Category.ERIADOR, 7, new ItemStack(LOTRMod.bossTrophy, 1, 0), "killMountainTrollChieftain").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle("trollSlayer");
        shootDownMidges = new LOTRAchievement(Category.ERIADOR, 8, Items.arrow, "shootDownMidges");
        enterTrollshaws = new LOTRAchievement(Category.ERIADOR, 9, LOTRMod.muttonCooked, "enterTrollshaws").setBiomeAchievement();
        enterMidgewater = new LOTRAchievement(Category.ERIADOR, 10, LOTRMod.quagmire, "enterMidgewater").setBiomeAchievement();
        enterLoneLands = new LOTRAchievement(Category.ERIADOR, 11, new ItemStack(Blocks.stonebrick, 1, 2), "enterLoneLands").setBiomeAchievement();
        enterEttenmoors = new LOTRAchievement(Category.ERIADOR, 12, new ItemStack(Blocks.sapling, 1, 1), "enterEttenmoors").setBiomeAchievement();
        enterEriador = new LOTRAchievement(Category.ERIADOR, 13, (Block)Blocks.grass, "enterEriador").setBiomeAchievement();
        enterColdfells = new LOTRAchievement(Category.ERIADOR, 14, new ItemStack(Blocks.sapling, 1, 0), "enterColdfells").setBiomeAchievement();
        enterSwanfleet = new LOTRAchievement(Category.ERIADOR, 15, Blocks.waterlily, "enterSwanfleet").setBiomeAchievement();
        enterMinhiriath = new LOTRAchievement(Category.ERIADOR, 16, (Block)Blocks.grass, "enterMinhiriath").setBiomeAchievement();
        tradeGundabadCaptain = new LOTRAchievement(Category.ERIADOR, 17, LOTRMod.silverCoin, "tradeGundabadCaptain").setRequiresAlly(LOTRFaction.GUNDABAD);
        tradeRangerNorthCaptain = new LOTRAchievement(Category.ERIADOR, 18, LOTRMod.silverCoin, "tradeRangerNorthCaptain").setRequiresAlly(LOTRFaction.RANGER_NORTH);
        enterBarrowDowns = new LOTRAchievement(Category.ERIADOR, 25, Items.bone, "enterBarrowDowns").setBiomeAchievement();
        useRangerTable = new LOTRAchievement(Category.ERIADOR, 26, LOTRMod.rangerTable, "useRangerTable").setRequiresAlly(LOTRFaction.RANGER_NORTH);
        useGundabadTable = new LOTRAchievement(Category.ERIADOR, 27, LOTRMod.gundabadTable, "useGundabadTable").setRequiresAlly(LOTRFaction.GUNDABAD);
        doMiniquestRanger = new LOTRAchievement(Category.ERIADOR, 28, LOTRMod.redBook, "doMiniquestRanger").setRequiresAlly(LOTRFaction.RANGER_NORTH);
        doMiniquestGundabad = new LOTRAchievement(Category.ERIADOR, 29, LOTRMod.redBook, "doMiniquestGundabad").setRequiresAlly(LOTRFaction.GUNDABAD);
        killBarrowWight = new LOTRAchievement(Category.ERIADOR, 30, Items.bone, "killBarrowWight").createTitle("killBarrowWight");
        killGundabadOrc = new LOTRAchievement(Category.ERIADOR, 31, LOTRMod.orcBone, "killGundabadOrc").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        killGundabadUruk = new LOTRAchievement(Category.ERIADOR, 32, LOTRMod.helmetGundabadUruk, "killGundabadUruk").setRequiresEnemy(LOTRFaction.GUNDABAD);
        wearFullGundabadUruk = new LOTRAchievement(Category.ERIADOR, 33, LOTRMod.bodyGundabadUruk, "wearFullGundabadUruk");
        killDunedain = new LOTRAchievement(Category.ERIADOR, 34, Items.bone, "killDunedain").setRequiresEnemy(LOTRFaction.RANGER_NORTH).createTitle();
        enterAngle = new LOTRAchievement(Category.ERIADOR, 35, new ItemStack(Blocks.wool, 1, 13), "enterAngle").setBiomeAchievement();
        wearFullArnor = new LOTRAchievement(Category.ERIADOR, 36, LOTRMod.bodyArnor, "wearFullArnor");
        tradeDunedainBlacksmith = new LOTRAchievement(Category.ERIADOR, 37, LOTRMod.silverCoin, "tradeDunedainBlacksmith").setRequiresAlly(LOTRFaction.RANGER_NORTH);
        enterRivendell = new LOTRAchievement(Category.ERIADOR, 38, new ItemStack(LOTRMod.brick3, 1, 2), "enterRivendell").setBiomeAchievement();
        useRivendellTable = new LOTRAchievement(Category.ERIADOR, 39, LOTRMod.rivendellTable, "useRivendellTable").setRequiresAlly(LOTRFaction.HIGH_ELF);
        wearFullRivendell = new LOTRAchievement(Category.ERIADOR, 40, LOTRMod.bodyRivendell, "wearFullRivendell");
        tradeRivendellSmith = new LOTRAchievement(Category.ERIADOR, 41, LOTRMod.silverCoin, "tradeRivendellSmith").setRequiresAlly(LOTRFaction.HIGH_ELF);
        tradeRivendellLord = new LOTRAchievement(Category.ERIADOR, 42, LOTRMod.silverCoin, "tradeRivendellLord").setRequiresAlly(LOTRFaction.HIGH_ELF);
        tradeRivendellTrader = new LOTRAchievement(Category.ERIADOR, 43, LOTRMod.silverCoin, "tradeRivendellTrader").setRequiresAlly(LOTRFaction.HIGH_ELF);
        doMiniquestRivendell = new LOTRAchievement(Category.ERIADOR, 44, LOTRMod.redBook, "doMiniquestRivendell").setRequiresAlly(LOTRFaction.HIGH_ELF);
        killRivendellElf = new LOTRAchievement(Category.ERIADOR, 45, LOTRMod.elfBone, "killRivendellElf").setRequiresEnemy(LOTRFaction.HIGH_ELF);
        tradeGundabadTrader = new LOTRAchievement(Category.ERIADOR, 46, LOTRMod.silverCoin, "tradeGundabadTrader").setRequiresAlly(LOTRFaction.GUNDABAD);
        tradeAngmarCaptain = new LOTRAchievement(Category.ANGMAR, 0, LOTRMod.silverCoin, "tradeAngmarCaptain").setRequiresAlly(LOTRFaction.GUNDABAD);
        killAngmarOrc = new LOTRAchievement(Category.ANGMAR, 1, LOTRMod.orcBone, "killAngmarOrc").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        enterAngmar = new LOTRAchievement(Category.ANGMAR, 2, new ItemStack(LOTRMod.brick2, 1, 0), "enterAngmar").setBiomeAchievement();
        useAngmarTable = new LOTRAchievement(Category.ANGMAR, 3, LOTRMod.angmarTable, "useAngmarTable").setRequiresAlly(LOTRFaction.GUNDABAD);
        wearFullAngmar = new LOTRAchievement(Category.ANGMAR, 4, LOTRMod.bodyAngmar, "wearFullAngmar");
        doMiniquestAngmar = new LOTRAchievement(Category.ANGMAR, 8, LOTRMod.redBook, "doMiniquestAngmar").setRequiresAlly(LOTRFaction.GUNDABAD);
        tradeAngmarTrader = new LOTRAchievement(Category.ANGMAR, 9, LOTRMod.silverCoin, "tradeAngmarTrader").setRequiresAlly(LOTRFaction.GUNDABAD);
        killAngmarHillman = new LOTRAchievement(Category.ANGMAR, 10, Items.bone, "killAngmarHillman").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        tradeAngmarHillmanChieftain = new LOTRAchievement(Category.ANGMAR, 11, LOTRMod.silverCoin, "tradeAngmarHillmanChieftain").setRequiresAlly(LOTRFaction.GUNDABAD);
        killSnowTroll = new LOTRAchievement(Category.ANGMAR, 12, LOTRMod.trollBone, "killSnowTroll").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        enterEregion = new LOTRAchievement(Category.EREGION, 0, new ItemStack(LOTRMod.sapling2, 1, 2), "enterEregion").setBiomeAchievement();
        enterEnedwaith = new LOTRAchievement(Category.ENEDWAITH, 0, (Block)Blocks.grass, "enterEnedwaith").setBiomeAchievement();
        enterNanCurunir = new LOTRAchievement(Category.ENEDWAITH, 1, LOTRMod.scimitarUruk, "enterNanCurunir").setBiomeAchievement();
        enterPukel = new LOTRAchievement(Category.ENEDWAITH, 2, new ItemStack(Blocks.sapling, 1, 5), "enterPukel").setBiomeAchievement();
        killDunlending = new LOTRAchievement(Category.DUNLAND, 0, LOTRMod.dunlendingClub, "killDunlending").setRequiresEnemy(LOTRFaction.DUNLAND).createTitle();
        wearFullDunlending = new LOTRAchievement(Category.DUNLAND, 1, LOTRMod.bodyDunlending, "wearFullDunlending");
        useDunlendingTable = new LOTRAchievement(Category.DUNLAND, 2, LOTRMod.dunlendingTable, "useDunlendingTable").setRequiresAlly(LOTRFaction.DUNLAND);
        tradeDunlendingWarlord = new LOTRAchievement(Category.DUNLAND, 3, LOTRMod.silverCoin, "tradeDunlendingWarlord").setRequiresAlly(LOTRFaction.DUNLAND);
        useDunlendingTrident = new LOTRAchievement(Category.DUNLAND, 4, LOTRMod.dunlendingTrident, "useDunlendingTrident");
        tradeDunlendingBartender = new LOTRAchievement(Category.DUNLAND, 5, LOTRMod.silverCoin, "tradeDunlendingBartender").setRequiresAlly(LOTRFaction.DUNLAND);
        enterDunland = new LOTRAchievement(Category.DUNLAND, 6, Items.stone_sword, "enterDunland").setBiomeAchievement();
        doMiniquestDunland = new LOTRAchievement(Category.DUNLAND, 10, LOTRMod.redBook, "doMiniquestDunland").setRequiresAlly(LOTRFaction.DUNLAND);
        climbMistyMountains = new LOTRAchievement(Category.MISTY_MOUNTAINS, 0, Blocks.snow, "climbMistyMountains");
        enterMistyMountains = new LOTRAchievement(Category.MISTY_MOUNTAINS, 1, new ItemStack(Blocks.stone, 1, 1000), "enterMistyMountains").setBiomeAchievement();
        tameGollum = new LOTRAchievement(Category.MISTY_MOUNTAINS, 2, Items.fish, "tameGollum");
        enterForodwaith = new LOTRAchievement(Category.FORODWAITH, 0, Blocks.ice, "enterForodwaith").setBiomeAchievement();
        enterValesOfAnduin = new LOTRAchievement(Category.RHOVANION, 0, Blocks.sapling, "enterValesOfAnduin").setBiomeAchievement();
        enterGreyMountains = new LOTRAchievement(Category.RHOVANION, 1, Blocks.stone, "enterGreyMountains").setBiomeAchievement();
        enterGladdenFields = new LOTRAchievement(Category.RHOVANION, 2, new ItemStack(LOTRMod.doubleFlower, 1, 1), "enterGladdenFields").setBiomeAchievement();
        enterEmynMuil = new LOTRAchievement(Category.RHOVANION, 3, Blocks.stone, "enterEmynMuil").setBiomeAchievement();
        enterBrownLands = new LOTRAchievement(Category.RHOVANION, 4, Blocks.dirt, "enterBrownLands").setBiomeAchievement();
        enterWilderland = new LOTRAchievement(Category.RHOVANION, 5, (Block)Blocks.grass, "enterWilderland").setBiomeAchievement();
        enterDagorlad = new LOTRAchievement(Category.RHOVANION, 6, LOTRMod.mordorGravel, "enterDagorlad").setBiomeAchievement();
        enterCelebrant = new LOTRAchievement(Category.RHOVANION, 8, new ItemStack((Block)Blocks.red_flower, 1, 3), "enterCelebrant").setBiomeAchievement();
        enterLongMarshes = new LOTRAchievement(Category.RHOVANION, 9, new ItemStack(LOTRMod.reeds, 1, 0), "enterLongMarshes").setBiomeAchievement();
        enterEastBight = new LOTRAchievement(Category.RHOVANION, 10, new ItemStack(Blocks.log, 1, 0), "enterEastBight").setBiomeAchievement();
        killMirkwoodSpider = new LOTRAchievement(Category.MIRKWOOD, 0, Items.string, "killMirkwoodSpider").setRequiresEnemy(LOTRFaction.DOL_GULDUR).createTitle();
        killWoodElf = new LOTRAchievement(Category.MIRKWOOD, 1, LOTRMod.elfBone, "killWoodElf").setRequiresEnemy(LOTRFaction.WOOD_ELF).createTitle();
        useWoodElvenTable = new LOTRAchievement(Category.MIRKWOOD, 2, LOTRMod.woodElvenTable, "useWoodElvenTable").setRequiresAlly(LOTRFaction.WOOD_ELF);
        wearFullWoodElvenScout = new LOTRAchievement(Category.MIRKWOOD, 4, LOTRMod.bodyWoodElvenScout, "wearFullWoodElvenScout");
        tradeWoodElfCaptain = new LOTRAchievement(Category.MIRKWOOD, 5, LOTRMod.silverCoin, "tradeWoodElfCaptain").setRequiresAlly(LOTRFaction.WOOD_ELF);
        rideBarrelMirkwood = new LOTRAchievement(Category.MIRKWOOD, 6, LOTRMod.barrel, "rideBarrelMirkwood").createTitle("rideBarrel");
        enterMirkwood = new LOTRAchievement(Category.MIRKWOOD, 7, LOTRMod.webUngoliant, "enterMirkwood").setBiomeAchievement();
        enterWoodlandRealm = new LOTRAchievement(Category.MIRKWOOD, 8, LOTRMod.mirkwoodBow, "enterWoodlandRealm").setBiomeAchievement();
        wearFullWoodElven = new LOTRAchievement(Category.MIRKWOOD, 9, LOTRMod.bodyWoodElven, "wearFullWoodElven");
        enterDolGuldur = new LOTRAchievement(Category.MIRKWOOD, 17, new ItemStack(LOTRMod.brick2, 1, 8), "enterDolGuldur").setBiomeAchievement();
        killDolGuldurOrc = new LOTRAchievement(Category.MIRKWOOD, 18, LOTRMod.orcBone, "killDolGuldurOrc").setRequiresEnemy(LOTRFaction.DOL_GULDUR).createTitle();
        useDolGuldurTable = new LOTRAchievement(Category.MIRKWOOD, 19, LOTRMod.dolGuldurTable, "useDolGuldurTable").setRequiresAlly(LOTRFaction.DOL_GULDUR);
        tradeDolGuldurCaptain = new LOTRAchievement(Category.MIRKWOOD, 20, LOTRMod.silverCoin, "tradeDolGuldurCaptain").setRequiresAlly(LOTRFaction.DOL_GULDUR);
        killMirkTroll = new LOTRAchievement(Category.MIRKWOOD, 21, LOTRMod.trollBone, "killMirkTroll").setRequiresEnemy(LOTRFaction.DOL_GULDUR).createTitle();
        wearFullDolGuldur = new LOTRAchievement(Category.MIRKWOOD, 22, LOTRMod.bodyDolGuldur, "wearFullDolGuldur");
        doMiniquestWoodElf = new LOTRAchievement(Category.MIRKWOOD, 23, LOTRMod.redBook, "doMiniquestWoodElf").setRequiresAlly(LOTRFaction.WOOD_ELF);
        doMiniquestDolGuldur = new LOTRAchievement(Category.MIRKWOOD, 24, LOTRMod.redBook, "doMiniquestDolGuldur").setRequiresAlly(LOTRFaction.DOL_GULDUR);
        tradeDolGuldurTrader = new LOTRAchievement(Category.MIRKWOOD, 25, LOTRMod.silverCoin, "tradeDolGuldurTrader").setRequiresAlly(LOTRFaction.DOL_GULDUR);
        tradeWoodElfSmith = new LOTRAchievement(Category.MIRKWOOD, 26, LOTRMod.silverCoin, "tradeWoodElfSmith").setRequiresAlly(LOTRFaction.WOOD_ELF);
        enterDale = new LOTRAchievement(Category.DALE, 0, LOTRMod.dalishPastryItem, "enterDale").setBiomeAchievement();
        doMiniquestDale = new LOTRAchievement(Category.DALE, 4, LOTRMod.redBook, "doMiniquestDale").setRequiresAlly(LOTRFaction.DALE);
        useDaleTable = new LOTRAchievement(Category.DALE, 5, LOTRMod.daleTable, "useDaleTable").setRequiresAlly(LOTRFaction.DALE);
        wearFullDale = new LOTRAchievement(Category.DALE, 6, LOTRMod.bodyDale, "wearFullDale");
        killDalish = new LOTRAchievement(Category.DALE, 7, Items.bone, "killDalish").setRequiresEnemy(LOTRFaction.DALE).createTitle();
        tradeDaleCaptain = new LOTRAchievement(Category.DALE, 8, LOTRMod.silverCoin, "tradeDaleCaptain").setRequiresAlly(LOTRFaction.DALE);
        tradeDaleBlacksmith = new LOTRAchievement(Category.DALE, 9, LOTRMod.silverCoin, "tradeDaleBlacksmith").setRequiresAlly(LOTRFaction.DALE);
        tradeDaleBaker = new LOTRAchievement(Category.DALE, 10, LOTRMod.silverCoin, "tradeDaleBaker").setRequiresAlly(LOTRFaction.DALE);
        tradeDaleMerchant = new LOTRAchievement(Category.DALE, 11, LOTRMod.silverCoin, "tradeDaleMerchant").setRequiresAlly(LOTRFaction.DALE);
        enterErebor = new LOTRAchievement(Category.DALE, 12, new ItemStack(LOTRMod.brick, 1, 6), "enterErebor").setBiomeAchievement();
        openDaleCracker = new LOTRAchievement(Category.DALE, 13, new ItemStack(LOTRMod.daleCracker, 1, 0), "openDaleCracker");
        killDwarf = new LOTRAchievement(Category.IRON_HILLS, 0, LOTRMod.dwarfBone, "killDwarf").setRequiresEnemy(LOTRFaction.DURINS_FOLK).createTitle();
        wearFullDwarven = new LOTRAchievement(Category.IRON_HILLS, 1, LOTRMod.bodyDwarven, "wearFullDwarven");
        useDwarvenThrowingAxe = new LOTRAchievement(Category.IRON_HILLS, 2, LOTRMod.throwingAxeDwarven, "useDwarvenThrowingAxe");
        useDwarvenTable = new LOTRAchievement(Category.IRON_HILLS, 3, LOTRMod.dwarvenTable, "useDwarvenTable").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeDwarfMiner = new LOTRAchievement(Category.IRON_HILLS, 4, LOTRMod.silverCoin, "tradeDwarfMiner").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeDwarfCommander = new LOTRAchievement(Category.IRON_HILLS, 5, LOTRMod.silverCoin, "tradeDwarfCommander").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        mineGlowstone = new LOTRAchievement(Category.IRON_HILLS, 6, LOTRMod.oreGlowstone, "mineGlowstone");
        smeltDwarfSteel = new LOTRAchievement(Category.IRON_HILLS, 7, LOTRMod.dwarfSteel, "smeltDwarfSteel");
        drinkDwarvenTonic = new LOTRAchievement(Category.IRON_HILLS, 8, LOTRMod.mugDwarvenTonic, "drinkDwarvenTonic");
        craftMithrilDwarvenBrick = new LOTRAchievement(Category.IRON_HILLS, 9, new ItemStack(LOTRMod.brick, 1, 10), "craftMithrilDwarvenBrick").setRare();
        talkDwarfWoman = new LOTRAchievement(Category.IRON_HILLS, 10, LOTRMod.mugDwarvenAle, "talkDwarfWoman").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF));
        enterIronHills = new LOTRAchievement(Category.IRON_HILLS, 11, LOTRMod.pickaxeDwarven, "enterIronHills").setBiomeAchievement();
        useDwarvenDoor = new LOTRAchievement(Category.IRON_HILLS, 12, LOTRMod.dwarvenDoor, "useDwarvenDoor");
        marryDwarf = new LOTRAchievement(Category.IRON_HILLS, 16, LOTRMod.dwarvenRing, "marryDwarf").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF));
        doMiniquestDwarf = new LOTRAchievement(Category.IRON_HILLS, 17, LOTRMod.redBook, "doMiniquestDwarf").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeIronHillsMerchant = new LOTRAchievement(Category.IRON_HILLS, 18, LOTRMod.silverCoin, "tradeIronHillsMerchant").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeDwarfSmith = new LOTRAchievement(Category.IRON_HILLS, 19, LOTRMod.silverCoin, "tradeDwarfSmith").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        killElf = new LOTRAchievement(Category.LOTHLORIEN, 0, LOTRMod.elfBone, "killElf").setRequiresEnemy(LOTRFaction.LOTHLORIEN).createTitle();
        useElvenPortal = new LOTRAchievement(Category.LOTHLORIEN, 1, LOTRMod.quenditeGrass, "useElvenPortal").setRequiresAlly(LOTRFaction.LOTHLORIEN);
        wearFullElven = new LOTRAchievement(Category.LOTHLORIEN, 2, LOTRMod.bodyElven, "wearFullElven");
        useElvenTable = new LOTRAchievement(Category.LOTHLORIEN, 3, LOTRMod.elvenTable, "useElvenTable").setRequiresAlly(LOTRFaction.LOTHLORIEN);
        tradeElfLord = new LOTRAchievement(Category.LOTHLORIEN, 4, LOTRMod.silverCoin, "tradeElfLord").setRequiresAlly(LOTRFaction.LOTHLORIEN);
        mineQuendite = new LOTRAchievement(Category.LOTHLORIEN, 5, LOTRMod.oreQuendite, "mineQuendite");
        takeMallornWood = new LOTRAchievement(Category.LOTHLORIEN, 6, new ItemStack(LOTRMod.wood, 1, 1), "takeMallornWood").setRequiresEnemy(LOTRFaction.LOTHLORIEN);
        enterLothlorien = new LOTRAchievement(Category.LOTHLORIEN, 8, new ItemStack(LOTRMod.sapling, 1, 1), "enterLothlorien").setBiomeAchievement();
        tradeElvenTrader = new LOTRAchievement(Category.LOTHLORIEN, 12, LOTRMod.silverCoin, "tradeElvenTrader").setRequiresAlly(LOTRFaction.LOTHLORIEN);
        doMiniquestGaladhrim = new LOTRAchievement(Category.LOTHLORIEN, 13, LOTRMod.redBook, "doMiniquestGaladhrim").setRequiresAlly(LOTRFaction.LOTHLORIEN);
        tradeGaladhrimSmith = new LOTRAchievement(Category.LOTHLORIEN, 14, LOTRMod.silverCoin, "tradeGaladhrimSmith").setRequiresAlly(LOTRFaction.LOTHLORIEN);
        wearFullHithlain = new LOTRAchievement(Category.LOTHLORIEN, 15, LOTRMod.bodyHithlain, "wearFullHithlain");
        killEnt = new LOTRAchievement(Category.FANGORN, 0, Blocks.log, "killEnt").setRequiresEnemy(LOTRFaction.FANGORN).createTitle("killEnt");
        drinkEntDraught = new LOTRAchievement(Category.FANGORN, 1, LOTRMod.entDraught, "drinkEntDraught").setRequiresAlly(LOTRFaction.FANGORN);
        killHuorn = new LOTRAchievement(Category.FANGORN, 2, Blocks.log, "killHuorn").setRequiresEnemy(LOTRFaction.FANGORN).createTitle();
        talkEnt = new LOTRAchievement(Category.FANGORN, 3, Blocks.log, "talkEnt");
        enterFangorn = new LOTRAchievement(Category.FANGORN, 4, (Block)Blocks.leaves, "enterFangorn").setBiomeAchievement();
        summonHuorn = new LOTRAchievement(Category.FANGORN, 8, new ItemStack(LOTRMod.entDraught, 1, 2), "summonHuorn").setRequiresAlly(LOTRFaction.FANGORN);
        killMallornEnt = new LOTRAchievement(Category.FANGORN, 9, new ItemStack(LOTRMod.bossTrophy, 1, 1), "killMallornEnt").setRequiresEnemy(LOTRFaction.FANGORN).createTitle("entSlayer");
        doMiniquestEnt = new LOTRAchievement(Category.FANGORN, 10, Blocks.cobblestone, "doMiniquestEnt").setRequiresAlly(LOTRFaction.FANGORN);
        raidUrukCamp = new LOTRAchievement(Category.ROHAN, 0, Items.skull, "raidUrukCamp").setRequiresEnemy(LOTRFaction.ISENGARD);
        useUrukTable = new LOTRAchievement(Category.ROHAN, 1, LOTRMod.urukTable, "useUrukTable").setRequiresAlly(LOTRFaction.ISENGARD);
        tradeUrukTrader = new LOTRAchievement(Category.ROHAN, 2, LOTRMod.silverCoin, "tradeUrukTrader").setRequiresAlly(LOTRFaction.ISENGARD);
        tradeUrukCaptain = new LOTRAchievement(Category.ROHAN, 3, LOTRMod.silverCoin, "tradeUrukCaptain").setRequiresAlly(LOTRFaction.ISENGARD);
        useRohirricTable = new LOTRAchievement(Category.ROHAN, 4, LOTRMod.rohirricTable, "useRohirricTable").setRequiresAlly(LOTRFaction.ROHAN);
        smeltUrukSteel = new LOTRAchievement(Category.ROHAN, 5, LOTRMod.urukSteel, "smeltUrukSteel");
        wearFullUruk = new LOTRAchievement(Category.ROHAN, 6, LOTRMod.bodyUruk, "wearFullUruk");
        hireWargBombardier = new LOTRAchievement(Category.ROHAN, 7, LOTRMod.fur, "hireWargBombardier").setRequiresAlly(LOTRFaction.ISENGARD);
        killRohirrim = new LOTRAchievement(Category.ROHAN, 8, LOTRMod.swordRohan, "killRohirrim").setRequiresEnemy(LOTRFaction.ROHAN).createTitle();
        tradeRohirrimMarshal = new LOTRAchievement(Category.ROHAN, 9, LOTRMod.silverCoin, "tradeRohirrimMarshal").setRequiresAlly(LOTRFaction.ROHAN);
        wearFullRohirric = new LOTRAchievement(Category.ROHAN, 10, LOTRMod.bodyRohan, "wearFullRohirric");
        tradeRohanBlacksmith = new LOTRAchievement(Category.ROHAN, 11, LOTRMod.silverCoin, "tradeRohanBlacksmith").setRequiresAlly(LOTRFaction.ROHAN);
        buyRohanMead = new LOTRAchievement(Category.ROHAN, 12, LOTRMod.mugMead, "buyRohanMead").setRequiresAlly(LOTRFaction.ROHAN);
        enterRohan = new LOTRAchievement(Category.ROHAN, 13, LOTRMod.spearRohan, "enterRohan").setBiomeAchievement();
        enterRohanUrukHighlands = new LOTRAchievement(Category.ROHAN, 14, LOTRMod.helmetUruk, "enterRohanUrukHighlands").setBiomeAchievement();
        doMiniquestRohan = new LOTRAchievement(Category.ROHAN, 21, LOTRMod.redBook, "doMiniquestRohan").setRequiresAlly(LOTRFaction.ROHAN);
        doMiniquestIsengard = new LOTRAchievement(Category.ROHAN, 22, LOTRMod.redBook, "doMiniquestIsengard").setRequiresAlly(LOTRFaction.ISENGARD);
        killUrukHai = new LOTRAchievement(Category.ROHAN, 23, LOTRMod.orcBone, "killUrukHai").setRequiresEnemy(LOTRFaction.ISENGARD).createTitle();
        enterEntwashMouth = new LOTRAchievement(Category.ROHAN, 24, new ItemStack((Block)Blocks.tallgrass, 1, 2), "enterEntwashMouth").setBiomeAchievement();
        wearFullRohirricMarshal = new LOTRAchievement(Category.ROHAN, 25, LOTRMod.bodyRohanMarshal, "wearFullRohirricMarshal");
        killIsengardSnaga = new LOTRAchievement(Category.ROHAN, 26, LOTRMod.orcBone, "killIsengardSnaga").setRequiresEnemy(LOTRFaction.ISENGARD).createTitle();
        doMiniquestRohanShieldmaiden = new LOTRAchievement(Category.ROHAN, 27, LOTRMod.redBook, "doMiniquestRohanShieldmaiden").setRequiresAlly(LOTRFaction.ROHAN);
        tradeRohanFarmer = new LOTRAchievement(Category.ROHAN, 28, LOTRMod.silverCoin, "tradeRohanFarmer").setRequiresAlly(LOTRFaction.ROHAN);
        hireRohanFarmer = new LOTRAchievement(Category.ROHAN, 29, Items.iron_hoe, "hireRohanFarmer").setRequiresAlly(LOTRFaction.ROHAN);
        tradeRohanMarketTrader = new LOTRAchievement(Category.ROHAN, 30, LOTRMod.silverCoin, "tradeRohanMarketTrader").setRequiresAlly(LOTRFaction.ROHAN);
        tradeRohanStablemaster = new LOTRAchievement(Category.ROHAN, 31, LOTRMod.silverCoin, "tradeRohanStablemaster").setRequiresAlly(LOTRFaction.ROHAN);
        enterAdornland = new LOTRAchievement(Category.ROHAN, 32, Items.skull, "enterAdornland").setBiomeAchievement();
        killGondorian = new LOTRAchievement(Category.GONDOR, 0, LOTRMod.swordGondor, "killGondorian").setRequiresEnemy(LOTRFaction.GONDOR).createTitle();
        lightGondorBeacon = new LOTRAchievement(Category.GONDOR, 1, LOTRMod.beacon, "lightGondorBeacon");
        useGondorianTable = new LOTRAchievement(Category.GONDOR, 2, LOTRMod.gondorianTable, "useGondorianTable").setRequiresAlly(LOTRFaction.GONDOR);
        tradeGondorBlacksmith = new LOTRAchievement(Category.GONDOR, 3, LOTRMod.silverCoin, "tradeGondorBlacksmith").setRequiresAlly(LOTRFaction.GONDOR);
        tradeGondorianCaptain = new LOTRAchievement(Category.GONDOR, 4, LOTRMod.silverCoin, "tradeGondorianCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        wearFullGondorian = new LOTRAchievement(Category.GONDOR, 5, LOTRMod.bodyGondor, "wearFullGondorian");
        killRangerIthilien = new LOTRAchievement(Category.GONDOR, 6, LOTRMod.gondorBow, "killRangerIthilien").setRequiresEnemy(LOTRFaction.GONDOR).createTitle();
        enterGondor = new LOTRAchievement(Category.GONDOR, 7, LOTRMod.spearGondor, "enterGondor").setBiomeAchievement();
        enterIthilien = new LOTRAchievement(Category.GONDOR, 8, LOTRMod.gondorBow, "enterIthilien").setBiomeAchievement();
        enterWhiteMountains = new LOTRAchievement(Category.GONDOR, 9, new ItemStack(LOTRMod.rock, 1, 1), "enterWhiteMountains").setBiomeAchievement();
        enterTolfalas = new LOTRAchievement(Category.GONDOR, 13, Blocks.stone, "enterTolfalas").setBiomeAchievement();
        enterLebennin = new LOTRAchievement(Category.GONDOR, 14, new ItemStack(Blocks.sapling, 1, 2), "enterLebennin").setBiomeAchievement();
        doMiniquestGondor = new LOTRAchievement(Category.GONDOR, 15, LOTRMod.redBook, "doMiniquestGondor").setRequiresAlly(LOTRFaction.GONDOR);
        tradeRangerIthilienCaptain = new LOTRAchievement(Category.GONDOR, 16, LOTRMod.silverCoin, "tradeRangerIthilienCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        useDolAmrothTable = new LOTRAchievement(Category.GONDOR, 17, LOTRMod.dolAmrothTable, "useDolAmrothTable").setRequiresAlly(LOTRFaction.GONDOR);
        wearFullDolAmroth = new LOTRAchievement(Category.GONDOR, 18, LOTRMod.bodyDolAmroth, "wearFullDolAmroth");
        killSwanKnight = new LOTRAchievement(Category.GONDOR, 19, LOTRMod.swordDolAmroth, "killSwanKnight").setRequiresEnemy(LOTRFaction.GONDOR).createTitle();
        enterDorEnErnil = new LOTRAchievement(Category.GONDOR, 20, Items.feather, "enterDorEnErnil").setBiomeAchievement();
        tradeDolAmrothCaptain = new LOTRAchievement(Category.GONDOR, 21, LOTRMod.silverCoin, "tradeDolAmrothCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        enterAnduinMouth = new LOTRAchievement(Category.GONDOR, 22, Blocks.waterlily, "enterAnduinMouth").setBiomeAchievement();
        enterPelennor = new LOTRAchievement(Category.GONDOR, 23, new ItemStack(LOTRMod.brick, 1, 1), "enterPelennor").setBiomeAchievement();
        wearFullRangerIthilien = new LOTRAchievement(Category.GONDOR, 24, LOTRMod.bodyRangerIthilien, "wearFullRangerIthilien");
        enterLossarnach = new LOTRAchievement(Category.GONDOR, 25, new ItemStack(LOTRMod.fruitSapling, 1, 0), "enterLossarnach").setBiomeAchievement();
        enterImlothMelui = new LOTRAchievement(Category.GONDOR, 26, new ItemStack((Block)Blocks.double_plant, 1, 4), "enterImlothMelui").setBiomeAchievement();
        wearFullLossarnach = new LOTRAchievement(Category.GONDOR, 27, LOTRMod.bodyLossarnach, "wearFullLossarnach");
        wearFullPelargir = new LOTRAchievement(Category.GONDOR, 28, LOTRMod.bodyPelargir, "wearFullPelargir");
        wearFullPinnathGelin = new LOTRAchievement(Category.GONDOR, 29, LOTRMod.bodyPinnathGelin, "wearFullPinnathGelin");
        wearFullBlackroot = new LOTRAchievement(Category.GONDOR, 30, LOTRMod.bodyBlackroot, "wearFullBlackroot");
        hireGondorFarmer = new LOTRAchievement(Category.GONDOR, 31, Items.iron_hoe, "hireGondorFarmer").setRequiresAlly(LOTRFaction.GONDOR);
        buyPipeweedGondorFarmer = new LOTRAchievement(Category.GONDOR, 32, LOTRMod.pipeweedPlant, "buyPipeweedGondorFarmer").setRequiresAlly(LOTRFaction.GONDOR);
        tradeGondorBartender = new LOTRAchievement(Category.GONDOR, 33, LOTRMod.silverCoin, "tradeGondorBartender").setRequiresAlly(LOTRFaction.GONDOR);
        tradeGondorMarketTrader = new LOTRAchievement(Category.GONDOR, 34, LOTRMod.silverCoin, "tradeGondorMarketTrader").setRequiresAlly(LOTRFaction.GONDOR);
        tradeLossarnachCaptain = new LOTRAchievement(Category.GONDOR, 35, LOTRMod.silverCoin, "tradeLossarnachCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        tradePelargirCaptain = new LOTRAchievement(Category.GONDOR, 36, LOTRMod.silverCoin, "tradePelargirCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        tradePinnathGelinCaptain = new LOTRAchievement(Category.GONDOR, 37, LOTRMod.silverCoin, "tradePinnathGelinCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        tradeBlackrootCaptain = new LOTRAchievement(Category.GONDOR, 38, LOTRMod.silverCoin, "tradeBlackrootCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        tradeLebenninCaptain = new LOTRAchievement(Category.GONDOR, 39, LOTRMod.silverCoin, "tradeLebenninCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        enterPelargir = new LOTRAchievement(Category.GONDOR, 40, LOTRMod.tridentPelargir, "enterPelargir").setBiomeAchievement();
        wearFullLamedon = new LOTRAchievement(Category.GONDOR, 41, LOTRMod.bodyLamedon, "wearFullLamedon");
        tradeLamedonCaptain = new LOTRAchievement(Category.GONDOR, 42, LOTRMod.silverCoin, "tradeLamedonCaptain").setRequiresAlly(LOTRFaction.GONDOR);
        enterLamedon = new LOTRAchievement(Category.GONDOR, 43, LOTRMod.axeBronze, "enterLamedon").setBiomeAchievement();
        enterBlackrootVale = new LOTRAchievement(Category.GONDOR, 44, LOTRMod.blackroot, "enterBlackrootVale").setBiomeAchievement();
        enterPinnathGelin = new LOTRAchievement(Category.GONDOR, 45, (Block)Blocks.grass, "enterPinnathGelin").setBiomeAchievement();
        enterAndrast = new LOTRAchievement(Category.GONDOR, 46, Blocks.stone, "enterAndrast").setBiomeAchievement();
        doMiniquestGondorKillRenegade = new LOTRAchievement(Category.GONDOR, 47, LOTRMod.redBook, "doMiniquestGondorKillRenegade").setRequiresAlly(LOTRFaction.GONDOR).createTitle("killGondorRenegade");
        mineRemains = new LOTRAchievement(Category.NINDALF, 0, LOTRMod.remains, "mineRemains");
        craftAncientItem = new LOTRAchievement(Category.NINDALF, 1, LOTRMod.ancientItem, "craftAncientItem");
        enterDeadMarshes = new LOTRAchievement(Category.NINDALF, 2, LOTRMod.deadPlant, "enterDeadMarshes").setBiomeAchievement();
        enterNindalf = new LOTRAchievement(Category.NINDALF, 3, new ItemStack((Block)Blocks.tallgrass, 1, 2), "enterNindalf").setBiomeAchievement();
        killMarshWraith = new LOTRAchievement(Category.NINDALF, 4, Items.skull, "killMarshWraith").createTitle();
        killOlogHai = new LOTRAchievement(Category.MORDOR, 0, LOTRMod.trollBone, "killOlogHai").setRequiresEnemy(LOTRFaction.MORDOR).createTitle();
        useMorgulTable = new LOTRAchievement(Category.MORDOR, 1, LOTRMod.morgulTable, "useMorgulTable").setRequiresAlly(LOTRFaction.MORDOR);
        smeltOrcSteel = new LOTRAchievement(Category.MORDOR, 2, LOTRMod.orcSteel, "smeltOrcSteel");
        wearFullOrc = new LOTRAchievement(Category.MORDOR, 3, LOTRMod.bodyOrc, "wearFullOrc");
        tradeOrcTrader = new LOTRAchievement(Category.MORDOR, 4, LOTRMod.silverCoin, "tradeOrcTrader").setRequiresAlly(LOTRFaction.MORDOR);
        tradeOrcCaptain = new LOTRAchievement(Category.MORDOR, 5, LOTRMod.silverCoin, "tradeOrcCaptain").setRequiresAlly(LOTRFaction.MORDOR);
        mineNaurite = new LOTRAchievement(Category.MORDOR, 6, LOTRMod.oreNaurite, "mineNaurite");
        eatMorgulShroom = new LOTRAchievement(Category.MORDOR, 7, LOTRMod.morgulShroom, "eatMorgulShroom");
        craftOrcBomb = new LOTRAchievement(Category.MORDOR, 8, LOTRMod.orcBomb, "craftOrcBomb").setRequiresAlly(LOTRFaction.MORDOR);
        hireOlogHai = new LOTRAchievement(Category.MORDOR, 9, LOTRMod.hammerOrc, "hireOlogHai").setRequiresAlly(LOTRFaction.MORDOR);
        mineGulduril = new LOTRAchievement(Category.MORDOR, 10, new ItemStack(LOTRMod.oreGulduril, 1, 1), "mineGulduril");
        useMorgulPortal = new LOTRAchievement(Category.MORDOR, 11, LOTRMod.guldurilCrystal, "useMorgulPortal").setRequiresAlly(LOTRFaction.MORDOR);
        wearFullMorgul = new LOTRAchievement(Category.MORDOR, 12, LOTRMod.bodyMorgul, "wearFullMorgul");
        enterMordor = new LOTRAchievement(Category.MORDOR, 13, new ItemStack(LOTRMod.rock, 1, 0), "enterMordor").setBiomeAchievement();
        enterNurn = new LOTRAchievement(Category.MORDOR, 14, LOTRMod.hoeOrc, "enterNurn").setBiomeAchievement();
        enterNanUngol = new LOTRAchievement(Category.MORDOR, 15, LOTRMod.webUngoliant, "enterNanUngol").setBiomeAchievement();
        killMordorSpider = new LOTRAchievement(Category.MORDOR, 16, Items.string, "killMordorSpider").setRequiresEnemy(LOTRFaction.MORDOR).createTitle();
        tradeOrcSpiderKeeper = new LOTRAchievement(Category.MORDOR, 17, LOTRMod.silverCoin, "tradeOrcSpiderKeeper").setRequiresAlly(LOTRFaction.MORDOR);
        killMordorOrc = new LOTRAchievement(Category.MORDOR, 18, LOTRMod.orcBone, "killMordorOrc").setRequiresEnemy(LOTRFaction.MORDOR).createTitle();
        doMiniquestMordor = new LOTRAchievement(Category.MORDOR, 22, LOTRMod.redBook, "doMiniquestMordor").setRequiresAlly(LOTRFaction.MORDOR);
        smeltBlackUrukSteel = new LOTRAchievement(Category.MORDOR, 23, LOTRMod.blackUrukSteel, "smeltBlackUrukSteel");
        wearFullBlackUruk = new LOTRAchievement(Category.MORDOR, 24, LOTRMod.bodyBlackUruk, "wearFullBlackUruk");
        killBlackUruk = new LOTRAchievement(Category.MORDOR, 25, LOTRMod.helmetBlackUruk, "killBlackUruk").setRequiresEnemy(LOTRFaction.MORDOR).createTitle();
        hireNurnSlave = new LOTRAchievement(Category.MORDOR, 26, LOTRMod.brandingIron, "hireNurnSlave").setRequiresAlly(LOTRFaction.MORDOR);
        enterMorgulVale = new LOTRAchievement(Category.MORDOR, 27, Items.skull, "enterMorgulVale").setBiomeAchievement();
        tradeBlackUrukCaptain = new LOTRAchievement(Category.MORDOR, 28, LOTRMod.silverCoin, "tradeBlackUrukCaptain").setRequiresAlly(LOTRFaction.MORDOR);
        killWickedDwarf = new LOTRAchievement(Category.MORDOR, 29, LOTRMod.pickaxeDwarven, "killWickedDwarf").setRequiresEnemy(LOTRFaction.MORDOR).createTitle();
        tradeWickedDwarf = new LOTRAchievement(Category.MORDOR, 30, LOTRMod.silverCoin, "tradeWickedDwarf").setRequiresAlly(LOTREntityWickedDwarf.tradeFactions);
        enterDorwinion = new LOTRAchievement(Category.DORWINION, 0, LOTRMod.mugWhiteWine, "enterDorwinion").setBiomeAchievement();
        doMiniquestDorwinion = new LOTRAchievement(Category.DORWINION, 4, LOTRMod.redBook, "doMiniquestDorwinion").setRequiresAlly(LOTRFaction.DORWINION);
        useDorwinionTable = new LOTRAchievement(Category.DORWINION, 5, LOTRMod.dorwinionTable, "useDorwinionTable").setRequiresAlly(LOTRFaction.DORWINION);
        wearFullDorwinion = new LOTRAchievement(Category.DORWINION, 6, LOTRMod.bodyDorwinion, "wearFullDorwinion");
        wearFullDorwinionElf = new LOTRAchievement(Category.DORWINION, 7, LOTRMod.bodyDorwinionElf, "wearFullDorwinionElf");
        killDorwinion = new LOTRAchievement(Category.DORWINION, 8, Items.bone, "killDorwinion").setRequiresEnemy(LOTRFaction.DORWINION).createTitle();
        tradeDorwinionCaptain = new LOTRAchievement(Category.DORWINION, 9, LOTRMod.silverCoin, "tradeDorwinionCaptain").setRequiresAlly(LOTRFaction.DORWINION);
        killDorwinionElf = new LOTRAchievement(Category.DORWINION, 10, LOTRMod.elfBone, "killDorwinionElf").setRequiresEnemy(LOTRFaction.DORWINION).createTitle();
        tradeDorwinionElfCaptain = new LOTRAchievement(Category.DORWINION, 11, LOTRMod.silverCoin, "tradeDorwinionElfCaptain").setRequiresAlly(LOTRFaction.DORWINION);
        drinkWine = new LOTRAchievement(Category.DORWINION, 12, LOTRMod.mugRedWine, "drinkWine");
        harvestGrapes = new LOTRAchievement(Category.DORWINION, 13, LOTRMod.grapeWhite, "harvestGrapes");
        buyWineVintner = new LOTRAchievement(Category.DORWINION, 14, LOTRMod.silverCoin, "buyWineVintner").setRequiresAlly(LOTRFaction.DORWINION);
        hireDorwinionVinekeeper = new LOTRAchievement(Category.DORWINION, 15, LOTRMod.seedsGrapeRed, "hireDorwinionVinekeeper").setRequiresAlly(LOTRFaction.DORWINION);
        stealDorwinionGrapes = new LOTRAchievement(Category.DORWINION, 16, LOTRMod.grapeRed, "stealDorwinionGrapes").createTitle("stealGrapes");
        tradeDorwinionMerchant = new LOTRAchievement(Category.DORWINION, 17, LOTRMod.silverCoin, "tradeDorwinionMerchant").setRequiresAlly(LOTRFaction.DORWINION);
        enterDorwinionHills = new LOTRAchievement(Category.DORWINION, 18, new ItemStack(LOTRMod.rock, 1, 5), "enterDorwinionHills").setBiomeAchievement();
        enterRhun = new LOTRAchievement(Category.RHUN, 0, (Block)Blocks.grass, "enterRhun").setBiomeAchievement();
        useRhunTable = new LOTRAchievement(Category.RHUN, 4, LOTRMod.rhunTable, "useRhunTable").setRequiresAlly(LOTRFaction.RHUDEL);
        killEasterling = new LOTRAchievement(Category.RHUN, 5, Items.bone, "killEasterling").setRequiresEnemy(LOTRFaction.RHUDEL).createTitle();
        doMiniquestRhun = new LOTRAchievement(Category.RHUN, 6, LOTRMod.redBook, "doMiniquestRhun").setRequiresAlly(LOTRFaction.RHUDEL);
        wearFullRhun = new LOTRAchievement(Category.RHUN, 7, LOTRMod.bodyRhun, "wearFullRhun");
        tradeRhunBlacksmith = new LOTRAchievement(Category.RHUN, 8, LOTRMod.silverCoin, "tradeRhunBlacksmith").setRequiresAlly(LOTRFaction.RHUDEL);
        tradeRhunCaptain = new LOTRAchievement(Category.RHUN, 9, LOTRMod.silverCoin, "tradeRhunCaptain").setRequiresAlly(LOTRFaction.RHUDEL);
        hitBirdFirePot = new LOTRAchievement(Category.RHUN, 10, LOTRMod.rhunFirePot, "hitBirdFirePot");
        getKineArawHorn = new LOTRAchievement(Category.RHUN, 11, LOTRMod.kineArawHorn, "getKineArawHorn");
        wearFullRhunGold = new LOTRAchievement(Category.RHUN, 12, LOTRMod.bodyRhunGold, "wearFullRhunGold");
        enterLastDesert = new LOTRAchievement(Category.RHUN, 13, (Block)Blocks.sand, "enterLastDesert").setBiomeAchievement();
        enterMountainsWind = new LOTRAchievement(Category.RHUN, 14, new ItemStack(Blocks.stone, 1, 1000), "enterMountainsWind").setBiomeAchievement();
        tradeRhunMarketTrader = new LOTRAchievement(Category.RHUN, 15, LOTRMod.silverCoin, "tradeRhunMarketTrader").setRequiresAlly(LOTRFaction.RHUDEL);
        tradeRhunBartender = new LOTRAchievement(Category.RHUN, 16, LOTRMod.silverCoin, "tradeRhunBartender").setRequiresAlly(LOTRFaction.RHUDEL);
        hireRhunFarmer = new LOTRAchievement(Category.RHUN, 17, LOTRMod.hoeBronze, "hireRhunFarmer").setRequiresAlly(LOTRFaction.RHUDEL);
        enterRhunLand = new LOTRAchievement(Category.RHUN, 18, LOTRMod.helmetRhunGold, "enterRhunLand").setBiomeAchievement();
        enterRhunRedwood = new LOTRAchievement(Category.RHUN, 19, new ItemStack(LOTRMod.sapling8, 1, 1), "enterRhunRedwood").setBiomeAchievement();
        enterRhunIsland = new LOTRAchievement(Category.RHUN, 20, Items.boat, "enterRhunIsland").setBiomeAchievement();
        enterRedMountains = new LOTRAchievement(Category.OROCARNI, 0, new ItemStack(LOTRMod.rock, 1, 4), "enterRedMountains").setBiomeAchievement();
        enterHarondor = new LOTRAchievement(Category.NEAR_HARAD, 0, Blocks.dirt, "enterHarondor").setBiomeAchievement();
        enterNearHarad = new LOTRAchievement(Category.NEAR_HARAD, 1, (Block)Blocks.sand, "enterNearHarad").setBiomeAchievement();
        killNearHaradrim = new LOTRAchievement(Category.NEAR_HARAD, 2, Items.bone, "killNearHaradrim").setRequiresEnemy(LOTRFaction.NEAR_HARAD).createTitle();
        useNearHaradTable = new LOTRAchievement(Category.NEAR_HARAD, 6, LOTRMod.nearHaradTable, "useNearHaradTable").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        wearFullNearHarad = new LOTRAchievement(Category.NEAR_HARAD, 7, LOTRMod.bodyNearHarad, "wearFullNearHarad");
        tradeNearHaradWarlord = new LOTRAchievement(Category.NEAR_HARAD, 8, LOTRMod.silverCoin, "tradeNearHaradWarlord").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        rideCamel = new LOTRAchievement(Category.NEAR_HARAD, 9, Items.saddle, "rideCamel");
        tradeBazaarTrader = new LOTRAchievement(Category.NEAR_HARAD, 10, LOTRMod.silverCoin, "tradeBazaarTrader").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeNearHaradMerchant = new LOTRAchievement(Category.NEAR_HARAD, 11, LOTRMod.silverCoin, "tradeNearHaradMerchant").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        doMiniquestNearHarad = new LOTRAchievement(Category.NEAR_HARAD, 12, LOTRMod.redBook, "doMiniquestNearHarad").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        cookKebab = new LOTRAchievement(Category.NEAR_HARAD, 13, LOTRMod.kebab, "cookKebab").createTitle("cookKebab");
        enterNearHaradOasis = new LOTRAchievement(Category.NEAR_HARAD, 14, new ItemStack(LOTRMod.sapling3, 1, 2), "enterNearHaradOasis").setBiomeAchievement();
        tradeNearHaradBlacksmith = new LOTRAchievement(Category.NEAR_HARAD, 15, LOTRMod.silverCoin, "tradeNearHaradBlacksmith").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        enterHarnedor = new LOTRAchievement(Category.NEAR_HARAD, 16, LOTRMod.swordHarad, "enterHarnedor").setBiomeAchievement();
        enterSouthronCoasts = new LOTRAchievement(Category.NEAR_HARAD, 17, new ItemStack(LOTRMod.sapling3, 1, 2), "enterSouthronCoasts").setBiomeAchievement();
        enterUmbar = new LOTRAchievement(Category.NEAR_HARAD, 18, LOTRMod.scimitarNearHarad, "enterUmbar").setBiomeAchievement();
        enterLostladen = new LOTRAchievement(Category.NEAR_HARAD, 19, Blocks.stone, "enterLostladen").setBiomeAchievement();
        enterGulfHarad = new LOTRAchievement(Category.NEAR_HARAD, 20, new ItemStack(LOTRMod.doubleFlower, 1, 3), "enterGulfHarad").setBiomeAchievement();
        useUmbarTable = new LOTRAchievement(Category.NEAR_HARAD, 21, LOTRMod.umbarTable, "useUmbarTable").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        useGulfTable = new LOTRAchievement(Category.NEAR_HARAD, 22, LOTRMod.gulfTable, "useGulfTable").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        wearFullGulfHarad = new LOTRAchievement(Category.NEAR_HARAD, 23, LOTRMod.bodyGulfHarad, "wearFullGulfHarad");
        wearFullCorsair = new LOTRAchievement(Category.NEAR_HARAD, 24, LOTRMod.bodyCorsair, "wearFullCorsair");
        wearFullUmbar = new LOTRAchievement(Category.NEAR_HARAD, 25, LOTRMod.bodyUmbar, "wearFullUmbar");
        wearFullHarnedor = new LOTRAchievement(Category.NEAR_HARAD, 26, LOTRMod.bodyHarnedor, "wearFullHarnedor");
        wearFullNomad = new LOTRAchievement(Category.NEAR_HARAD, 27, LOTRMod.bodyNomad, "wearFullNomad");
        tradeHarnedorWarlord = new LOTRAchievement(Category.NEAR_HARAD, 28, LOTRMod.silverCoin, "tradeHarnedorWarlord").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeUmbarCaptain = new LOTRAchievement(Category.NEAR_HARAD, 29, LOTRMod.silverCoin, "tradeUmbarCaptain").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeCorsairCaptain = new LOTRAchievement(Category.NEAR_HARAD, 30, LOTRMod.silverCoin, "tradeCorsairCaptain").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeNomadWarlord = new LOTRAchievement(Category.NEAR_HARAD, 31, LOTRMod.silverCoin, "tradeNomadWarlord").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeGulfWarlord = new LOTRAchievement(Category.NEAR_HARAD, 32, LOTRMod.silverCoin, "tradeGulfWarlord").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        hireHaradSlave = new LOTRAchievement(Category.NEAR_HARAD, 33, LOTRMod.brandingIron, "hireHaradSlave").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        hireMoredainMercenary = new LOTRAchievement(Category.NEAR_HARAD, 34, LOTRMod.silverCoin, "hireMoredainMercenary").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeHarnedorBlacksmith = new LOTRAchievement(Category.NEAR_HARAD, 35, LOTRMod.silverCoin, "tradeHarnedorBlacksmith").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeUmbarBlacksmith = new LOTRAchievement(Category.NEAR_HARAD, 36, LOTRMod.silverCoin, "tradeUmbarBlacksmith").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeGulfBlacksmith = new LOTRAchievement(Category.NEAR_HARAD, 37, LOTRMod.silverCoin, "tradeGulfBlacksmith").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        doMiniquestGondorRenegade = new LOTRAchievement(Category.NEAR_HARAD, 38, LOTRMod.redBook, "doMiniquestGondorRenegade").setRequiresAlly(LOTRFaction.NEAR_HARAD).createTitle("gondorRenegade");
        tradeNomadMerchant = new LOTRAchievement(Category.NEAR_HARAD, 39, LOTRMod.silverCoin, "tradeNomadMerchant").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeHaradBartender = new LOTRAchievement(Category.NEAR_HARAD, 40, LOTRMod.silverCoin, "tradeHaradBartender").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeNomadArmourer = new LOTRAchievement(Category.NEAR_HARAD, 41, LOTRMod.silverCoin, "tradeNomadArmourer").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        hireHarnedorFarmer = new LOTRAchievement(Category.NEAR_HARAD, 42, LOTRMod.hoeBronze, "hireHarnedorFarmer").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        tradeHaradFarmer = new LOTRAchievement(Category.NEAR_HARAD, 43, LOTRMod.silverCoin, "tradeHaradFarmer").setRequiresAlly(LOTRFaction.NEAR_HARAD);
        wearFullBlackNumenorean = new LOTRAchievement(Category.NEAR_HARAD, 44, LOTRMod.bodyBlackNumenorean, "wearFullBlackNumenorean");
        killDesertSpider = new LOTRAchievement(Category.NEAR_HARAD, 45, Blocks.web, "killDesertSpider");
        minesusSand = new LOTRAchievement(Category.NEAR_HARAD, 46, LOTRMod.susSand, "minesusSand");
        enterFarHaradSavannah = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 0, LOTRMod.lionFur, "enterFarHaradSavannah").setBiomeAchievement();
        pickBanana = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 1, LOTRMod.banana, "pickBanana");
        growBaobab = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 5, new ItemStack(LOTRMod.sapling4, 1, 1), "growBaobab");
        enterFarHaradVolcano = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 6, Blocks.lava, "enterFarHaradVolcano").setBiomeAchievement();
        killMoredain = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 7, Items.bone, "killMoredain").setRequiresEnemy(LOTRFaction.MORWAITH).createTitle();
        useMoredainTable = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 8, LOTRMod.moredainTable, "useMoredainTable").setRequiresAlly(LOTRFaction.MORWAITH);
        wearFullMoredain = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 9, LOTRMod.bodyMoredain, "wearFullMoredain");
        tradeMoredainChieftain = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 10, LOTRMod.silverCoin, "tradeMoredainChieftain").setRequiresAlly(LOTRFaction.MORWAITH);
        doMiniquestMoredain = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 11, LOTRMod.redBook, "doMiniquestMoredain").setRequiresAlly(LOTRFaction.MORWAITH);
        tradeMoredainVillager = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 12, LOTRMod.silverCoin, "tradeMoredainVillager").setRequiresAlly(LOTRFaction.MORWAITH);
        enterCorsairCoasts = new LOTRAchievement(Category.FAR_HARAD_SAVANNAH, 13, LOTRMod.swordCorsair, "enterCorsairCoasts").setBiomeAchievement();
        enterFarHaradJungle = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 0, new ItemStack(Blocks.sapling, 1, 3), "enterFarHaradJungle").setBiomeAchievement();
        drinkMangoJuice = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 1, LOTRMod.mugMangoJuice, "drinkMangoJuice");
        doMiniquestTauredain = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 5, LOTRMod.redBook, "doMiniquestTauredain").setRequiresAlly(LOTRFaction.TAURETHRIM);
        useTauredainTable = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 6, LOTRMod.tauredainTable, "useTauredainTable").setRequiresAlly(LOTRFaction.TAURETHRIM);
        wearFullTauredain = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 7, LOTRMod.bodyTauredain, "wearFullTauredain");
        killTauredain = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 8, Items.bone, "killTauredain").setRequiresEnemy(LOTRFaction.TAURETHRIM).createTitle();
        tradeTauredainChieftain = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 9, LOTRMod.silverCoin, "tradeTauredainChieftain").setRequiresAlly(LOTRFaction.TAURETHRIM);
        tradeTauredainShaman = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 10, LOTRMod.silverCoin, "tradeTauredainShaman").setRequiresAlly(LOTRFaction.TAURETHRIM);
        smeltObsidianShard = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 11, LOTRMod.obsidianShard, "smeltObsidianShard");
        tradeTauredainFarmer = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 12, LOTRMod.silverCoin, "tradeTauredainFarmer").setRequiresAlly(LOTRFaction.TAURETHRIM);
        hireTauredainFarmer = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 13, LOTRMod.hoeTauredain, "hireTauredainFarmer").setRequiresAlly(LOTRFaction.TAURETHRIM);
        tradeTauredainSmith = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 14, LOTRMod.silverCoin, "tradeTauredainSmith").setRequiresAlly(LOTRFaction.TAURETHRIM);
        wearFullTaurethrimGold = new LOTRAchievement(Category.FAR_HARAD_JUNGLE, 15, LOTRMod.bodyTauredainGold, "wearFullTaurethrimGold");
        enterPertorogwaith = new LOTRAchievement(Category.PERDOROGWAITH, 0, Blocks.stone, "enterPertorogwaith").setBiomeAchievement();
        killHalfTroll = new LOTRAchievement(Category.PERDOROGWAITH, 4, LOTRMod.trollBone, "killHalfTroll").setRequiresEnemy(LOTRFaction.HALF_TROLL).createTitle();
        wearFullHalfTroll = new LOTRAchievement(Category.PERDOROGWAITH, 5, LOTRMod.bodyHalfTroll, "wearFullHalfTroll");
        tradeHalfTrollWarlord = new LOTRAchievement(Category.PERDOROGWAITH, 6, LOTRMod.silverCoin, "tradeHalfTrollWarlord").setRequiresAlly(LOTRFaction.HALF_TROLL);
        useHalfTrollTable = new LOTRAchievement(Category.PERDOROGWAITH, 7, LOTRMod.halfTrollTable, "useHalfTrollTable").setRequiresAlly(LOTRFaction.HALF_TROLL);
        doMiniquestHalfTroll = new LOTRAchievement(Category.PERDOROGWAITH, 8, LOTRMod.redBook, "doMiniquestHalfTroll").setRequiresAlly(LOTRFaction.HALF_TROLL);
        tradeHalfTrollScavenger = new LOTRAchievement(Category.PERDOROGWAITH, 9, LOTRMod.silverCoin, "tradeHalfTrollScavenger").setRequiresAlly(LOTRFaction.HALF_TROLL);
        enterHalfTrollForest = new LOTRAchievement(Category.PERDOROGWAITH, 10, new ItemStack(LOTRMod.brick4, 1, 2), "enterHalfTrollForest").setBiomeAchievement();
        enterOcean = new LOTRAchievement(Category.OCEAN, 0, Items.boat, "enterOcean").setBiomeAchievement().createTitle("visitOcean");
        enterMeneltarma = new LOTRAchievement(Category.OCEAN, 1, LOTRMod.athelas, "enterMeneltarma").setBiomeAchievement().createTitle("enterMeneltarma");
        enterUtumnoIce = new LOTRAchievement(Category.UTUMNO, 0, new ItemStack(LOTRMod.utumnoBrick, 1, 3), "enterUtumnoIce").setSpecial().createTitle("enterUtumno");
        enterUtumnoObsidian = new LOTRAchievement(Category.UTUMNO, 1, new ItemStack(LOTRMod.utumnoBrick, 1, 5), "enterUtumnoObsidian").setSpecial();
        enterUtumnoFire = new LOTRAchievement(Category.UTUMNO, 2, new ItemStack(LOTRMod.utumnoBrick, 1, 1), "enterUtumnoFire").setSpecial().createTitle("enterUtumnoFire").setRare();
        wearFullUtumno = new LOTRAchievement(Category.UTUMNO, 3, LOTRMod.bodyUtumno, "wearFullUtumno");
        killUtumnoOrc = new LOTRAchievement(Category.UTUMNO, 4, LOTRMod.orcBone, "killUtumnoOrc");
        killUtumnoWarg = new LOTRAchievement(Category.UTUMNO, 5, LOTRMod.wargBone, "killUtumnoWarg");
        killBalrog = new LOTRAchievement(Category.UTUMNO, 6, LOTRMod.balrogWhip, "killBalrog").createTitle().setRare();
        killTormentedElf = new LOTRAchievement(Category.UTUMNO, 7, LOTRMod.elfBone, "killTormentedElf");
        killUtumnoTroll = new LOTRAchievement(Category.UTUMNO, 8, LOTRMod.trollBone, "killUtumnoTroll");
        craftUtumnoKey = new LOTRAchievement(Category.UTUMNO, 9, LOTRMod.utumnoKey, "craftUtumnoKey");
        leaveUtumno = new LOTRAchievement(Category.UTUMNO, 10, LOTRMod.swordUtumno, "leaveUtumno").createTitle().setRare();
        mod = new LOTRAchievement(Category.UTUMNO, 12, new ItemStack(LOTRMod.goldRing, 1, 1), "mod").setRare();
        translator = new LOTRAchievement(Category.UTUMNO, 13, new ItemStack(LOTRMod.goldRing, 1, 1), "translator").setRare();
        ogre = new LOTRAchievement(Category.UTUMNO, 14, new ItemStack(LOTRMod.goldRing, 1, 1), "ogre").setRare();
        tester = new LOTRAchievement(Category.UTUMNO, 15, LOTRMod.mithrilRing, "tester").setRare();
        dovakin = new LOTRAchievement(Category.UTUMNO, 16, LOTRMod.mithrilRing, "dovakin").setRare();
        lore = new LOTRAchievement(Category.UTUMNO, 17, new ItemStack(LOTRMod.goldRing, 1, 1), "lore").setRare();
        doMiniquestRedDwarf = new LOTRAchievement(Category.OROCARNI, 3, LOTRMod.redBook, "doMiniquestRedDwarf").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        killAngband = new LOTRAchievement(Category.FORODWAITH, 11, LOTRMod.orcBone, "killAngband").setRequiresEnemy(LOTRFaction.UTUMNO);
        useAngbandTable = new LOTRAchievement(Category.FORODWAITH, 12, LOTRMod.angbandtable, "useAngbandTable").setRequiresAlly(LOTRFaction.UTUMNO);
        useEreborTable = new LOTRAchievement(Category.IRON_HILLS, 20, LOTRMod.erebortable, "useEreborTable").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        useWindTable = new LOTRAchievement(Category.OROCARNI, 7, LOTRMod.windtable, "useWindTable").setRequiresAlly(LOTRFaction.WIND);
        useRedDwarfTable = new LOTRAchievement(Category.OROCARNI, 4, LOTRMod.reddwarvenTable, "useRedMountainsTable").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        hireErebor = new LOTRAchievement(Category.IRON_HILLS, 21, LOTRMod.swordDwarven, "hireErebor").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeRedDwarfSmith = new LOTRAchievement(Category.OROCARNI, 5, LOTRMod.silverCoin, "tradeRedDwarfSmith").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        tradeAngbandUrukCaptain = new LOTRAchievement(Category.FORODWAITH, 13, LOTRMod.silverCoin, "tradeAngbandCaptain").setRequiresAlly(LOTRFaction.UTUMNO);
        tradeAngbandSpiderUrukCaptain = new LOTRAchievement(Category.FORODWAITH, 14, LOTRMod.silverCoin, "tradeAngbandSpiderCaptain").setRequiresAlly(LOTRFaction.UTUMNO);
        tradeAngbandWargUrukCaptain = new LOTRAchievement(Category.FORODWAITH, 15, LOTRMod.silverCoin, "tradeAngbandWargCaptain").setRequiresAlly(LOTRFaction.UTUMNO);
        hireWind = new LOTRAchievement(Category.OROCARNI, 6, LOTRMod.battleaxeDwarven, "hireWind").setRequiresAlly(LOTRFaction.WIND);
        enterStiffbeard = new LOTRAchievement(Category.OROCARNI, 8, LOTRMod.helmetStiffbeard, "enterMornaugrim").setBiomeAchievement();
        enterHarnaugrim = new LOTRAchievement(Category.OROCARNI, 9, LOTRMod.helmetIronfist, "enterHarnaugrim").setBiomeAchievement();
        marryMornaugrimDwarf = new LOTRAchievement(Category.OROCARNI, 10, LOTRMod.dwarvenRing, "marryMornaugrimDwarf").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        enterLotr = new LOTRAchievement(Category.GENERAL, 78, LOTRMod.goldRing, "enterLotr").setSpecial();
        killRoyal = new LOTRAchievement(Category.IRON_HILLS, 22, LOTRMod.hammerDwarven, "killRoyal").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeUtumnoUrukTrader = new LOTRAchievement(Category.FORODWAITH, 17, LOTRMod.silverCoin, "tradeUtumnoUrukTrader").setRequiresAlly(LOTRFaction.UTUMNO);
        enterAngband = new LOTRAchievement(Category.FORODWAITH, 18, LOTRMod.helmetangbande, "enterAngband").setBiomeAchievement();
        enterAngbandMountains = new LOTRAchievement(Category.FORODWAITH, 19, Items.bone, "enterAngbandMountains").setBiomeAchievement();
        craftgoldRing = new LOTRAchievement(Category.GENERAL, 79, LOTRMod.goldRing, "craftgoldRing");
        doMiniquestAngband = new LOTRAchievement(Category.FORODWAITH, 10, LOTRMod.redBook, "doMiniquestAngband").setRequiresAlly(LOTRFaction.UTUMNO);
        doMiniquestDurmeth = new LOTRAchievement(Category.ERIADOR, 47, LOTRMod.redBook, "doMiniquestDurmeth").setRequiresAlly(LOTRFaction.GUNDABAD);
        tradeBlacklock = new LOTRAchievement(Category.OROCARNI, 15, LOTRMod.silverCoin, "tradeBlacklock").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        tradeStiffbeard = new LOTRAchievement(Category.OROCARNI, 16, LOTRMod.silverCoin, "tradeStiffbeard").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        tradeStonefoot = new LOTRAchievement(Category.OROCARNI, 17, LOTRMod.silverCoin, "tradeStonefoot").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        tradeIronfist = new LOTRAchievement(Category.OROCARNI, 18, LOTRMod.silverCoin, "tradeIronfist").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        tradeDurmethOrc = new LOTRAchievement(Category.RHUN, 23, LOTRMod.silverCoin, "tradeDurmethOrc").setRequiresAlly(LOTRFaction.GUNDABAD);
        enterDurmeth = new LOTRAchievement(Category.RHUN, 21, LOTRMod.axeOrc, "enterDurmeth").setBiomeAchievement();
        enterDurmethMountains = new LOTRAchievement(Category.OROCARNI, 25, LOTRMod.dwarfBone, "enterDurmethMountains").setBiomeAchievement();
        enterKhand = new LOTRAchievement(Category.RHUN, 25, LOTRMod.daggerRhun, "enterKhand").setBiomeAchievement();
        enterWastelandRhun = new LOTRAchievement(Category.RHUN, 29, Items.skull, "enterWastelandRhun").setBiomeAchievement();
        enterRhunTaiga = new LOTRAchievement(Category.RHUN, 30, (Block)Blocks.grass, "enterRhunTaiga").setBiomeAchievement();
        enterWildRhun = new LOTRAchievement(Category.RHUN, 31, LOTRMod.daggerUruk, "enterWildRhun").setBiomeAchievement();
        enterHarhudor = new LOTRAchievement(Category.RHUN, 32, LOTRMod.flax, "enterHarhudor").setBiomeAchievement();
        enterRedMountainsSnow = new LOTRAchievement(Category.OROCARNI, 19, LOTRMod.helmetIronfist, "enterRedMountainsSnow").setBiomeAchievement();
        enterRedMountainsCentre = new LOTRAchievement(Category.OROCARNI, 20, LOTRMod.helmetStonefoot, "enterRedMountainsCentre").setBiomeAchievement();
        enterEasternCoasts = new LOTRAchievement(Category.RHUN, 33, LOTRMod.swordRhun, "enterEasternCoasts").setBiomeAchievement();
        smeltMithril = new LOTRAchievement(Category.GENERAL, 80, LOTRMod.mithrilNugget, "smeltMithril").setRare();
        wearFullDwarvenMithril = new LOTRAchievement(Category.IRON_HILLS, 24, LOTRMod.bodyDMithril, "wearFullDwarvenMithril").setRare();
        wearFullErebor = new LOTRAchievement(Category.IRON_HILLS, 26, LOTRMod.bodyErebor, "wearFullErebor");
        wearFullGuldur = new LOTRAchievement(Category.MIRKWOOD, 27, LOTRMod.bodyguldururuk, "wearFullGuldur");
        wearAngband = new LOTRAchievement(Category.FORODWAITH, 20, LOTRMod.bodyAngband, "wearAngband");
        wearAngbandu = new LOTRAchievement(Category.FORODWAITH, 21, LOTRMod.bodyAngbandu, "wearAngbandu");
        wearBerserk = new LOTRAchievement(Category.FORODWAITH, 22, LOTRMod.bodyBerserk, "wearBerserk");
        wearWind = new LOTRAchievement(Category.OROCARNI, 22, LOTRMod.bodyWind, "wearWind");
        killDurmethOrc = new LOTRAchievement(Category.RHUN, 34, LOTRMod.orcBone, "killDurmethOrc").setRequiresEnemy(LOTRFaction.GUNDABAD);
        killDurmethWarg = new LOTRAchievement(Category.RHUN, 35, LOTRMod.wargBone, "killDurmethWarg").setRequiresEnemy(LOTRFaction.GUNDABAD);
        enterRhunSemiDesert = new LOTRAchievement(Category.RHUN, 37, (Block)Blocks.sand, "enterRhunSemiDesert").setBiomeAchievement();
        enterSun = new LOTRAchievement(Category.GENERAL, 81, (Block)Blocks.sand, "enterSun").setBiomeAchievement().setRare();
        getOne = new LOTRAchievement(Category.GENERAL, 82, LOTRMod.theOneRing, "getOne").setRare().setRing();
        getThree = new LOTRAchievement(Category.GENERAL, 83, LOTRMod.nenia, "getThree").setRare().setRing();
        getSeven = new LOTRAchievement(Category.GENERAL, 84, LOTRMod.d1, "getSeven").setRare().setRing();
        getNine = new LOTRAchievement(Category.GENERAL, 85, LOTRMod.h1, "getNine").setRare().setRing();
        getArcenstone = new LOTRAchievement(Category.GENERAL, 86, LOTRMod.arcenstone, "getArcenstone").setRare();
        getBilbo = new LOTRAchievement(Category.GENERAL, 87, LOTRMod.bodyBilbo, "getBilbo").setRare();
        enterDimrill = new LOTRAchievement(Category.RHOVANION, 11, LOTRMod.dwarvenDoorIthildin, "enterDimrill").setBiomeAchievement();
        enterBizar = new LOTRAchievement(Category.OROCARNI, 23, new ItemStack(LOTRMod.brick2, 0, 2), "enterBizar").setBiomeAchievement();
        wearAngbande = new LOTRAchievement(Category.FORODWAITH, 23, LOTRMod.bodyangbande, "wearAngbande");
        wearBoldog = new LOTRAchievement(Category.FORODWAITH, 24, LOTRMod.bodyboldog, "wearBoldog");
        getSilmaril = new LOTRAchievement(Category.GENERAL, 88, LOTRMod.silmaril_fire, "getSilmaril").setRare();
        useRing = new LOTRAchievement(Category.GENERAL, 89, LOTRMod.theOneRing, "useRing");
        playerRespawn = new LOTRAchievement(Category.GENERAL, 90, LOTRMod.gandalfStaffWhite, "playerRespawn");
        playerDeath = new LOTRAchievement(Category.GENERAL, 91, LOTRMod.gandalfStaffGrey, "playerDeath");
        enterForestShelter = new LOTRAchievement(Category.ANDUIN_VALLEY, 4, new ItemStack(LOTRMod.sapling7, 1, 1), "enterForestShelter").setBiomeAchievement();
        enterSouthMirkwood = new LOTRAchievement(Category.MIRKWOOD, 28, new ItemStack(LOTRMod.guldurilBrick, 1, 4), "enterSouthMirkwood").setBiomeAchievement();
        enterBeornsDomain = new LOTRAchievement(Category.ANDUIN_VALLEY, 5, Blocks.sapling, "enterBeornsDomain").setBiomeAchievement();
        enterHarhudorJungle = new LOTRAchievement(Category.RHUN, 36, new ItemStack(LOTRMod.leaves6, 1, 0), "enterHarhudorJungle").setBiomeAchievement();
        enterRohanGate = new LOTRAchievement(Category.DUNLAND, 11, Items.stone_sword, "enterRohanGate").setBiomeAchievement();
        wearFullIstari = new LOTRAchievement(Category.GENERAL, 92, LOTRMod.gandalfhat, "wearIstari");
        doMiniquestWind = new LOTRAchievement(Category.OROCARNI, 24, LOTRMod.redBook, "doMiniquestWind").setRequiresAlly(LOTRFaction.WIND);
        useOneRing = new LOTRAchievement(Category.GENERAL, 93, LOTRMod.theOneRing, "useOneRing").setRequiresAlly(LOTRFaction.GONDOR, LOTRFaction.HOBBIT).setRare();
        killSauron = new LOTRAchievement(Category.MORDOR, 31, LOTRMod.sauronMace, "killSauron").setRare();
        killBoldog = new LOTRAchievement(Category.FORODWAITH, 25, LOTRMod.hammerBoldog, "killBoldog");
        tradePallando = new LOTRAchievement(Category.GENERAL, 94, LOTRMod.pallandostaff, "tradePallando");
        wearFullSauron = new LOTRAchievement(Category.MORDOR, 32, LOTRMod.sauronbody, "wearSauron");
        craftMithril = new LOTRAchievement(Category.GENERAL, 95, LOTRMod.mithril, "craftMithril").setRare();
        wearFullBronze = new LOTRAchievement(Category.GENERAL, 96, LOTRMod.bodyBronze, "wearFullBronze");
        wearFullBone = new LOTRAchievement(Category.GENERAL, 97, LOTRMod.bodyBone, "wearFullBone");
        enterFallForodwaith = new LOTRAchievement(Category.FORODWAITH, 26, new ItemStack(LOTRMod.wargskinRug, 0, 7), "enterFallForodwaith").setBiomeAchievement();
        tradeRedDwarfMerchant = new LOTRAchievement(Category.OROCARNI, 26, LOTRMod.silverCoin, "tradeRedDwarfMerchant").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        enterIceUgol = new LOTRAchievement(Category.FORODWAITH, 27, Blocks.web, "enterIceUgol").setBiomeAchievement();
        enterAnfalas = new LOTRAchievement(Category.GONDOR, 48, LOTRMod.axeBronze, "enterAnfalas").setBiomeAchievement();
        wearFullGrey = new LOTRAchievement(Category.RHOVANION, 12, LOTRMod.gdbody, "wearFullGrey");
        wearFullDark = new LOTRAchievement(Category.OROCARNI, 27, LOTRMod.wdbody, "wearFullDark");
        tradeRedDwarfMiner = new LOTRAchievement(Category.OROCARNI, 28, LOTRMod.silverCoin, "tradeRedDwarfMiner").setRequiresAlly(LOTRFaction.RED_MOUNTAINS);
        enterMoria = new LOTRAchievement(Category.MISTY_MOUNTAINS, 3, Blocks.stone, "enterMoria").setBiomeAchievement().setRare();
        killMoriaOrc = new LOTRAchievement(Category.MISTY_MOUNTAINS, 4, LOTRMod.orcBone, "killMoriaOrc").setRequiresEnemy(LOTRFaction.GUNDABAD);
        killCaveTroll = new LOTRAchievement(Category.FORODWAITH, 5, LOTRMod.trollBone, "killCaveTroll").setRequiresEnemy(LOTRFaction.GUNDABAD);
        tradeMoriaOrc = new LOTRAchievement(Category.MISTY_MOUNTAINS, 6, LOTRMod.silverCoin, "tradeMoriaOrc").setRequiresAlly(LOTRFaction.GUNDABAD);
        wearMoriaMithril = new LOTRAchievement(Category.MISTY_MOUNTAINS, 7, new ItemStack(LOTRMod.treasureMithril, 1, 7), "wearMoriaMithril").setRare();
        wearFullMoria = new LOTRAchievement(Category.MISTY_MOUNTAINS, 8, LOTRMod.moriahelmet, "wearFullMoria");
        wearFullMoriaMithril = new LOTRAchievement(Category.MISTY_MOUNTAINS, 9, LOTRMod.bodyMoriaMithril, "wearFullMoriaMithril").setRare();
        useWickedDwarfTable = new LOTRAchievement(Category.OROCARNI, 29, LOTRMod.wickedDwarvenTable, "useWickedDwarfTable").setRequiresAlly(LOTRFaction.MORDOR);
        killMountainSnowTroll = new LOTRAchievement(Category.ANGMAR, 13, LOTRMod.trollBone, "killMountainSnowTroll").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        drinkUrukDraught = new LOTRAchievement(Category.FORODWAITH, 28, LOTRMod.mugUrukDraught, "drinkUrukDraught");
        eatKham = new LOTRAchievement(Category.GENERAL, 98, LOTRMod.kham, "eatKham");
        drinkKhamBrew = new LOTRAchievement(Category.GENERAL, 99, LOTRMod.mugKhamBrew, "drinkKhamBrew");
        drinkRedDwarvenTonic = new LOTRAchievement(Category.OROCARNI, 21, LOTRMod.mugRedDwarvenTonic, "drinkRedDwarvenTonic");
        eatlembasNugget = new LOTRAchievement(Category.GENERAL, 100, LOTRMod.lembasNugget, "eatlembasNugget");
        drinkFire = new LOTRAchievement(Category.GENERAL, 101, LOTRMod.mugKhamBrew, "drinkFire");
        Thorin = new LOTRAchievement(Category.IRON_HILLS, 27, LOTRMod.ereborHorn, "Thorin").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        tradeEreborDwarfSmith = new LOTRAchievement(Category.IRON_HILLS, 28, LOTRMod.silverCoin, "tradeEreborDwarfSmith").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        wearFullGondorMithril = new LOTRAchievement(Category.GONDOR, 49, LOTRMod.gondormithrilbody, "wearFullGondorMithril").setRare();
        leapoffaith = new LOTRAchievement(Category.GENERAL, 102, Blocks.hay_block, "leapoffaith");
        insearchofyourself = new LOTRAchievement(Category.GENERAL, 103, LOTRMod.bootsFur, "insearchofyourself");
        useBandage = new LOTRAchievement(Category.GENERAL, 104, LOTRMod.cloth, "useBandage");
        doMiniquestAvariElf = new LOTRAchievement(Category.OROCARNI, 31, LOTRMod.redBook, "doMiniquestAvariElf").setRequiresAlly(LOTRFaction.AVARI);
        killAvariElf = new LOTRAchievement(Category.OROCARNI, 32, LOTRMod.elfBone, "killAvariElf").setRequiresEnemy(LOTRFaction.AVARI);
        tradeAvariElfCaptain = new LOTRAchievement(Category.OROCARNI, 33, LOTRMod.silverCoin, "tradeAvariElfCaptain").setRequiresAlly(LOTRFaction.AVARI);
        tradeWoodElfSmith = new LOTRAchievement(Category.OROCARNI, 34, LOTRMod.silverCoin, "tradeAvariElfSmith").setRequiresAlly(LOTRFaction.AVARI);
        enterAvariForest = new LOTRAchievement(Category.OROCARNI, 35, LOTRMod.helmetAvariElf, "enterAvariForest").setBiomeAchievement();
        wearFullAvari = new LOTRAchievement(Category.OROCARNI, 36, LOTRMod.bodyAvariElf, "wearFullAvari");
        wearFullAvariScout = new LOTRAchievement(Category.OROCARNI, 37, LOTRMod.bodyAvariElfScout, "wearFullAvariScout");
        useAvariTable = new LOTRAchievement(Category.OROCARNI, 38, LOTRMod.avariTable, "useAvariTable").setRequiresAlly(LOTRFaction.AVARI);
        killDarkElf = new LOTRAchievement(Category.FORODWAITH, 29, LOTRMod.elfBone, "killDarkElf");
        wearFullStonefoot = new LOTRAchievement(Category.OROCARNI, 39, LOTRMod.bodyStonefoot, "wearFullStonefoot");
        wearFullStiffbeard = new LOTRAchievement(Category.OROCARNI, 40, LOTRMod.bodyStiffbeard, "wearFullStiffbeard");
        wearFullIronfist = new LOTRAchievement(Category.OROCARNI, 41, LOTRMod.bodyIronfist, "wearFullIronfist");
        wearFullBlacklock = new LOTRAchievement(Category.OROCARNI, 42, LOTRMod.bodyBlacklock, "wearFullBlacklock");
        useWickedElfTable = new LOTRAchievement(Category.FORODWAITH, 30, LOTRMod.darkElfTable, "useWickedElfTable").setRequiresAlly(LOTRFaction.UTUMNO);
        wearFullDarkElf = new LOTRAchievement(Category.FORODWAITH, 31, LOTRMod.bodyWickedElf, "wearFullDarkElf");
        wearFullDarkElfRanger = new LOTRAchievement(Category.FORODWAITH, 32, LOTRMod.bodyWickedElfRanger, "wearFullDarkElfRanger");
        getKham = new LOTRAchievement(Category.GENERAL, 105, LOTRMod.khamRaw, "getKham");
        earnManyCoins1 = new LOTRAchievement(Category.GENERAL, 106, new ItemStack(LOTRMod.silverCoin, 1, 2), "earnManyCoins1").createTitle();
        earnManyCoins2 = new LOTRAchievement(Category.GENERAL, 107, new ItemStack(LOTRMod.silverCoin, 1, 3), "earnManyCoins2").createTitle();
        earnManyCoins3 = new LOTRAchievement(Category.GENERAL, 108, new ItemStack(LOTRMod.silverCoin, 1, 4), "earnManyCoins3").createTitle();
        earnManyCoins4 = new LOTRAchievement(Category.GENERAL, 109, new ItemStack(LOTRMod.silverCoin, 1, 5), "earnManyCoins4").createTitle().setRare();
        wearFullChainmailBronze = new LOTRAchievement(Category.GENERAL, 110, LOTRMod.bodyBronzeChainmail, "wearFullChainmailBronze");
        wearFullChainmailDwarf = new LOTRAchievement(Category.IRON_HILLS, 29, LOTRMod.bodyDwarvenChainmail, "wearFullChainmailDwarf");
        wearFullChainmailOrc = new LOTRAchievement(Category.MORDOR, 34, LOTRMod.bodyOrcChainmail, "wearFullChainmailOrc");
        wearFullChainmailUruk = new LOTRAchievement(Category.ROHAN, 33, LOTRMod.bodyUrukChainmail, "wearFullChainmailUruk");
        wearFullChainmailBlackUruk = new LOTRAchievement(Category.MORDOR, 35, LOTRMod.bodyBlackUrukChainmail, "wearFullChainmailBlackUruk");
        wearFullWhite = new LOTRAchievement(Category.GENERAL, 115, LOTRMod.sarumanhat, "wearFullWhite");
        wearFullBrown = new LOTRAchievement(Category.GENERAL, 116, LOTRMod.radagasthat, "wearFullBrown");
        wearFullBlue = new LOTRAchievement(Category.GENERAL, 117, LOTRMod.alatarhat, "wearFullBlue");
        wearBilbo = new LOTRAchievement(Category.GENERAL, 118, LOTRMod.bodyBilbo, "wearBilbo").setRare();
        drinkSalt = new LOTRAchievement(Category.GENERAL, 119, LOTRMod.mugPickleJuice, "drinkSalt");
        notenough = new LOTRAchievement(Category.GENERAL, 120, LOTRMod.bootsMithril, "notenough").setRare();
        feelBannerProtection = new LOTRAchievement(Category.GENERAL, 121, LOTRMod.gandalfhat, "feelBannerProtection");
        useRingSuicide = new LOTRAchievement(Category.GENERAL, 122, LOTRMod.ring_lesser_suicide, "useRingSuicide");
        killMorgulSpider = new LOTRAchievement(Category.MORDOR, 33, Items.string, "killMorgulSpider").setRequiresEnemy(LOTRFaction.MORDOR).createTitle();
        killObsidianTroll = new LOTRAchievement(Category.FORODWAITH, 33, LOTRMod.trollBone, "killObsidianTroll");
        killFireTroll = new LOTRAchievement(Category.FORODWAITH, 34, LOTRMod.trollBone, "killFireTroll");
        killIceSpider = new LOTRAchievement(Category.FORODWAITH, 35, Items.string, "killIceSpider");
        killObsidianSpider = new LOTRAchievement(Category.FORODWAITH, 36, Items.string, "killObsidianSpider");
        killFireSpider = new LOTRAchievement(Category.FORODWAITH, 37, Items.string, "killFireSpider");
        hireEreborMercenary = new LOTRAchievement(Category.IRON_HILLS, 32, LOTRMod.silverCoin, "hireEreborMercenary").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        hireBerserk = new LOTRAchievement(Category.IRON_HILLS, 30, LOTRMod.hammerDwarven, "hireBerserk").setRequiresAlly(LOTRFaction.DURINS_FOLK);
        talkElf = new LOTRAchievement(Category.OROCARNI, 43, LOTRMod.mugWhiteWine, "talkElf").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF));
        useMoriaForge = new LOTRAchievement(Category.MISTY_MOUNTAINS, 10, LOTRMod.moriaForge, "useMoriaForge").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF)).setRare();
        useUpgradedMoriaForge = new LOTRAchievement(Category.MISTY_MOUNTAINS, 11, LOTRMod.moriaForgeMark2, "useUpgradedMoriaForge").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF)).setRare();
        craftMoriaForge = new LOTRAchievement(Category.MISTY_MOUNTAINS, 12, LOTRMod.moriaForge, "craftMoriaForge").setRare();
        craftMoriaForgeMark2 = new LOTRAchievement(Category.MISTY_MOUNTAINS, 13, LOTRMod.moriaForgeMark2, "craftMoriaForgeMark2").setRare();
        wearMoriaMithrilHelmet = new LOTRAchievement(Category.MISTY_MOUNTAINS, 14, LOTRMod.helmetMoriaMithril, "wearMoriaMithrilHelmet").setRare();
        wearMoriaMithrilChest = new LOTRAchievement(Category.MISTY_MOUNTAINS, 15, LOTRMod.bodyMoriaMithril, "wearMoriaMithrilChest").setRare();
        wearMoriaMithrilLegs = new LOTRAchievement(Category.MISTY_MOUNTAINS, 16, LOTRMod.legsMoriaMithril, "wearMoriaMithrilLegs").setRare();
        wearMoriaMithrilBoots = new LOTRAchievement(Category.MISTY_MOUNTAINS, 17, LOTRMod.bootsMoriaMithril, "wearMoriaMithrilBoots").setRare();
        mineMithrilPlus = new LOTRAchievement(Category.GENERAL, 123, LOTRMod.oreMithril2, "mineMithrilPlus").createTitle().setRare();
        highground = new LOTRAchievement(Category.GENERAL, 124, Items.bone, "highground").setRare();
        usemagicClover = new LOTRAchievement(Category.GENERAL, 125, LOTRMod.magicClover, "usemagicClover").setRare();
        usetotemOfUndying = new LOTRAchievement(Category.GENERAL, 126, LOTRMod.totemOfUndying, "usetotemOfUndying").setRare();
        sweetDreams = new LOTRAchievement(Category.GENERAL, 127, LOTRMod.AvariBedItem, "sweetDreams");
        useArrowExplosion = new LOTRAchievement(Category.GENERAL, 128, LOTRMod.arrowExplosion, "useArrowExplosion");
        useArrowFire = new LOTRAchievement(Category.GENERAL, 129, LOTRMod.arrowFire, "useArrowFire");
        useArrowMorgul = new LOTRAchievement(Category.GENERAL, 130, LOTRMod.arrowMorgul, "useArrowMorgul");
        drinkMithril = new LOTRAchievement(Category.GENERAL, 131, LOTRMod.gobletMithril, "drinkMithril").setRare();
        killDurmethOrcWarrior = new LOTRAchievement(Category.OROCARNI, 44, LOTRMod.orcBone, "killDurmethOrcWarrior").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        wearFullAragorn = new LOTRAchievement(Category.GONDOR, 50, LOTRMod.helmetAragorn, "wearFullAragorn");
        wearFullSilver = new LOTRAchievement(Category.IRON_HILLS, 31, LOTRMod.bodyDwarvenSilverFull, "wearFullSilver");
        tradeRadaghast = new LOTRAchievement(Category.MIRKWOOD, 29, LOTRMod.radaghaststaff, "tradeRadaghast");
        killDeveloper = new LOTRAchievement(Category.GENERAL, 132, LOTRMod.eru, "killDeveloper").setRare();
        wearFullDurmeth = new LOTRAchievement(Category.OROCARNI, 45, LOTRMod.bodyDurmethOrc, "wearFullDurmeth");
        alltotheBottom = new LOTRAchievement(Category.GENERAL, 133, LOTRMod.coralReef, "alltotheBottom");
        three = new LOTRAchievement(Category.GENERAL, 134, LOTRMod.daggerBronze, "three");
        thousands = new LOTRAchievement(Category.GENERAL, 135, LOTRMod.twoHandedSwordMithril, "thousands").setRare();
        spongeBob = new LOTRAchievement(Category.GENERAL, 136, Blocks.sponge, "spongeBob");
        defeatInvasion10 = new LOTRAchievement(Category.GENERAL, 137, LOTRMod.swordBronze, "defeatInvasion10");
        defeatInvasion50 = new LOTRAchievement(Category.GENERAL, 138, Items.iron_sword, "defeatInvasion50");
        defeatInvasion100 = new LOTRAchievement(Category.GENERAL, 139, LOTRMod.swordMithril, "defeatInvasion100").setRare();
        defeatInvasion200 = new LOTRAchievement(Category.GENERAL, 140, LOTRMod.hammerMithril, "defeatInvasion200").setRare();
        fightorflight = new LOTRAchievement(Category.GENERAL, 141, (Item)Items.chainmail_boots, "fightorflight");
        thejokesareover = new LOTRAchievement(Category.GENERAL, 142, LOTRMod.daggerIron, "thejokesareover");
        setBronze = new LOTRAchievement(Category.GENERAL, 143, new ItemStack(LOTRMod.blockOreStorage, 1, 2), "setBronze");
        setSilver = new LOTRAchievement(Category.GENERAL, 144, new ItemStack(LOTRMod.blockOreStorage, 1, 3), "setSilver");
        setGold = new LOTRAchievement(Category.GENERAL, 145, new ItemStack(LOTRMod.blockOreStorage2, 1, 4), "setGold");
        setBlockGem = new LOTRAchievement(Category.GENERAL, 146, new ItemStack(LOTRMod.blockGem, 1, 4), "setBlockGem");
        setMithril = new LOTRAchievement(Category.GENERAL, 147, new ItemStack(LOTRMod.blockOreStorage, 1, 4), "setMithril").setRare().setRing();
        getVilia = new LOTRAchievement(Category.GENERAL, 148, LOTRMod.vilia, "getVilia").setRare().setRing();
        getNenia = new LOTRAchievement(Category.GENERAL, 149, LOTRMod.nenia, "getNenia").setRare().setRing();
        getNaria = new LOTRAchievement(Category.GENERAL, 150, LOTRMod.naria, "getNaria").setRare().setRing();
        getDwarven1 = new LOTRAchievement(Category.GENERAL, 151, LOTRMod.d1, "getDwarven1").setRare().setRing();
        getDwarven2 = new LOTRAchievement(Category.GENERAL, 152, LOTRMod.d2, "getDwarven2").setRare().setRing();
        getDwarven3 = new LOTRAchievement(Category.GENERAL, 153, LOTRMod.d3, "getDwarven3").setRare().setRing();
        getDwarven4 = new LOTRAchievement(Category.GENERAL, 154, LOTRMod.d4, "getDwarven4").setRare().setRing();
        getDwarven5 = new LOTRAchievement(Category.GENERAL, 155, LOTRMod.d5, "getDwarven5").setRare().setRing();
        getDwarven6 = new LOTRAchievement(Category.GENERAL, 156, LOTRMod.d6, "getDwarven6").setRare().setRing();
        getDwarven7 = new LOTRAchievement(Category.GENERAL, 157, LOTRMod.d7, "getDwarven7").setRare().setRing();
        theLordofTheRings = new LOTRAchievement(Category.GENERAL, 158, LOTRMod.theOneRing, "theLordofTheRings").setRare();
        sellMushroom = new LOTRAchievement(Category.GENERAL, 159, new ItemStack((Block)Blocks.red_mushroom, 1, 0), "sellMushroom");
        flyingWarg = new LOTRAchievement(Category.GENERAL, 160, LOTRMod.wargBone, "flyingWarg").setRare();
        hireMorgulMercenary = new LOTRAchievement(Category.ANGMAR, 14, LOTRMod.silverCoin, "hireMorgulMercenary").setRequiresAlly(LOTRFaction.GUNDABAD);
        killMorgulAngmarOrc = new LOTRAchievement(Category.ANGMAR, 15, LOTRMod.orcBone, "killMorgulAngmarOrc").setRequiresEnemy(LOTRFaction.GUNDABAD).createTitle();
        killGuldurUruk = new LOTRAchievement(Category.MIRKWOOD, 30, LOTRMod.helmetguldururuk, "killGuldurUruk").setRequiresEnemy(LOTRFaction.DOL_GULDUR);
        treasure = new LOTRAchievement(Category.GENERAL, 161, LOTRMod.treasureMap, "treasure");
        treasureRare = new LOTRAchievement(Category.GENERAL, 162, LOTRMod.treasureMap, "treasureRare").setRare();
        treasureUltraRare = new LOTRAchievement(Category.GENERAL, 163, LOTRMod.treasureMap, "treasureUltraRare").setRare();
        theVergeofDeath = new LOTRAchievement(Category.UTUMNO, 18, Items.skull, "theVergeofDeath").setRare().createTitle();
        climbRedMountains = new LOTRAchievement(Category.OROCARNI, 46, Blocks.snow, "climbRedMountains").setRare();
        useArcenstoneRare = new LOTRAchievement(Category.IRON_HILLS, 33, new ItemStack(LOTRMod.blockOreStorage, 1, 4), "useArcenstoneRare").setRequiresAlly(LOTRFaction.DURINS_FOLK).setRare();
        useArcenstone = new LOTRAchievement(Category.IRON_HILLS, 34, new ItemStack(LOTRMod.treasureGold, 1, 1), "useArcenstone").setRequiresAlly(LOTRFaction.DURINS_FOLK).setRare();
        killEreborDwarf = new LOTRAchievement(Category.IRON_HILLS, 35, LOTRMod.dwarfBone, "killEreborDwarf").setRequiresEnemy(LOTRFaction.DURINS_FOLK);
        marryEreborDwarf = new LOTRAchievement(Category.IRON_HILLS, 36, LOTRMod.dwarvenRing, "marryEreborDwarf").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF));
        burnRing = new LOTRAchievement(Category.GENERAL, 164, (Block)Blocks.fire, "burnRing").setRare();
        destroyRing = new LOTRAchievement(Category.GENERAL, 165, LOTRMod.theOneRing, "destroyRing").setRare();
        treasureMillionere = new LOTRAchievement(Category.GENERAL, 166, new ItemStack(LOTRMod.silverCoin, 1, 6), "treasureMillionere");
        getNumenorBow = new LOTRAchievement(Category.GENERAL, 167, LOTRMod.steelbow, "getNumenorBow");
        killRedDwarf = new LOTRAchievement(Category.OROCARNI, 47, LOTRMod.dwarfBone, "killRedDwarf").setRequiresEnemy(LOTRFaction.RED_MOUNTAINS).createTitle();
        useGraal = new LOTRAchievement(Category.GENERAL, 168, LOTRMod.graalGold, "useGraal");
        hireWindDwarfAxe = new LOTRAchievement(Category.OROCARNI, 48, LOTRMod.throwingAxeMithril, "hireWindDwarfAxe").setRequiresAlly(LOTRFaction.WIND);
        killWindDwarf = new LOTRAchievement(Category.OROCARNI, 49, LOTRMod.dwarfBone, "killWindDawrf").setRequiresEnemy(LOTRFaction.WIND);
        marryWindDwarf = new LOTRAchievement(Category.OROCARNI, 50, LOTRMod.dwarvenRing, "marryWindDwarf").setRequiresAnyAlly(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF));
        killCaveSpider = new LOTRAchievement(Category.OROCARNI, 51, Blocks.web, "killCaveSpider");
        fishBusiness = new LOTRAchievement(Category.GENERAL, 169, (Item)Items.fishing_rod, "fishBusiness");
        craftPot = new LOTRAchievement(Category.GENERAL, 170, LOTRMod.decoratedPot, "craftPot");
        salvageSherd = new LOTRAchievement(Category.GENERAL, 171, LOTRMod.brush, "salvageSherd");
        killDragon = new LOTRAchievement(Category.RHOVANION, 13, LOTRMod.DragonTalon, "killDragon");
        killDragonScout = new LOTRAchievement(Category.RHOVANION, 14, LOTRMod.DragonScoutScale, "killDragonScout");
        killDragonHunter = new LOTRAchievement(Category.RHOVANION, 15, LOTRMod.DragonHunterScale, "killDragonHunter");
        killDragonAlpha = new LOTRAchievement(Category.RHOVANION, 16, LOTRMod.DragonAlphaScale, "killDragonAlpha");
        killDragonAnkalagon = new LOTRAchievement(Category.RHOVANION, 17, LOTRMod.DragonAnkalagonScale, "killDragonAnkalagon");
        sellArrowDragon = new LOTRAchievement(Category.IRON_HILLS, 37, LOTRMod.arrowDragon, "sellArrowDragon");
        useArrowDragon = new LOTRAchievement(Category.IRON_HILLS, 38, LOTRMod.arrowDragon, "useArrowDragon");
        playerDeathDragon = new LOTRAchievement(Category.RHOVANION, 18, LOTRMod.DragonTalon, "playerDeathDragon");
        playerDeathDragonFire = new LOTRAchievement(Category.RHOVANION, 19, Items.fire_charge, "playerDeathDragonFire");
        enchantBaneBalrog = new LOTRAchievement(Category.GENERAL, 172, LOTRMod.balrogFire, "enchantBaneBalrog").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC)).createTitle();
        enchantBaneHobbit = new LOTRAchievement(Category.GENERAL, 173, LOTRMod.leatherHat, "enchantBaneHobbit").setRequiresAnyEnemy(LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_FREE)).createTitle();
        marathon = new LOTRAchievement(Category.GENERAL, 174, (Item)Items.golden_boots, "marathon");
        tradeEreborDwarfMerchant = new LOTRAchievement(Category.IRON_HILLS, 39, LOTRMod.silverCoin, "tradeEreborDwarfMerchant").setRequiresAlly(LOTRFaction.DURINS_FOLK);
    }

    public static Category categoryForName(String name) {
        for (Category category : Category.values()) {
            if (!category.name().equals(name)) continue;
            return category;
        }
        return null;
    }

    public static LOTRAchievement achievementForCategoryAndID(Category category, int ID) {
        if (category == null) {
            return null;
        }
        for (LOTRAchievement achievement : category.list) {
            if (achievement.ID != ID) continue;
            return achievement;
        }
        return null;
    }

    public static LOTRAchievement findByName(String name) {
        for (Category category : Category.values()) {
            for (LOTRAchievement achievement : category.list) {
                if (!achievement.getCodeName().equalsIgnoreCase(name)) continue;
                return achievement;
            }
        }
        return null;
    }

    public static List<LOTRAchievement> getAllAchievements() {
        ArrayList<LOTRAchievement> list = new ArrayList<LOTRAchievement>();
        for (Category category : Category.values()) {
            for (LOTRAchievement achievement : category.list) {
                list.add(achievement);
            }
        }
        return list;
    }

    public IChatComponent getAchievementChatComponent(EntityPlayer entityplayer) {
        ChatComponentTranslation component = new ChatComponentTranslation(this.getUntranslatedTitle(entityplayer), new Object[0]).createCopy();
        component.getChatStyle().setColor(this.getColor());
        component.getChatStyle().setChatHoverEvent(new HoverEvent(LOTRChatEvents.SHOW_LOTR_ACHIEVEMENT, (IChatComponent)new ChatComponentText(this.category.name() + "$" + this.ID)));
        return component;
    }

    public IChatComponent getChatComponentForEarn(EntityPlayer entityplayer) {
        IChatComponent base = this.getAchievementChatComponent(entityplayer);
        IChatComponent component = new ChatComponentText("[").appendSibling(base).appendText("]");
        component.setChatStyle(base.getChatStyle());
        return component;
    }

    public void broadcastEarning(EntityPlayer entityplayer) {
        if (LOTRConfig.protectHobbitKillers && this == killHobbit) {
            return;
        }
        ChatComponentTranslation dimName = new ChatComponentTranslation(this.getDimension().getUntranslatedDimensionName(), new Object[0]);
        IChatComponent earnName = this.getChatComponentForEarn(entityplayer);
        ChatComponentTranslation msg = new ChatComponentTranslation("chat.lotr.achievement", new Object[]{entityplayer.func_145748_c_(), dimName, earnName});
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)msg);
        if (entityplayer instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP)entityplayer;
            WorldServer worldServer = playerMP.getServerForPlayer();
            String sound = this.isRare ? "lotr:item.achievementrare" : "lotr:item.achievement";
            worldServer.playSoundEffect(playerMP.posX, playerMP.posY, playerMP.posZ, sound, 1.0f, 1.0f);
            worldServer.playSoundEffect(playerMP.posX, playerMP.posY, playerMP.posZ, sound, 1.0f, 1.0f);
        }
    }

    public static enum Category {
        GENERAL(15916746),
        SHIRE(LOTRFaction.HOBBIT),
        BLUE_MOUNTAINS(LOTRFaction.BLUE_MOUNTAINS),
        LINDON(LOTRBiome.lindon),
        ERIADOR(LOTRBiome.eriador),
        BREE_LAND(LOTRBiome.breeland),
        ANGMAR(LOTRBiome.angmar),
        EREGION(LOTRBiome.eregion),
        ENEDWAITH(LOTRBiome.enedwaith),
        DUNLAND(LOTRFaction.DUNLAND),
        MISTY_MOUNTAINS(LOTRBiome.mistyMountains),
        FORODWAITH(LOTRBiome.forodwaith),
        RHOVANION(LOTRBiome.wilderland),
        MIRKWOOD(LOTRBiome.woodlandRealm),
        DALE(LOTRFaction.DALE),
        IRON_HILLS(LOTRFaction.DURINS_FOLK),
        LOTHLORIEN(LOTRFaction.LOTHLORIEN),
        FANGORN(LOTRFaction.FANGORN),
        ROHAN(LOTRFaction.ROHAN),
        GONDOR(LOTRFaction.GONDOR),
        NINDALF(LOTRBiome.nindalf),
        MORDOR(LOTRFaction.MORDOR),
        DORWINION(LOTRFaction.DORWINION),
        RHUN(LOTRBiome.rhun),
        OROCARNI(LOTRBiome.redMountains),
        NEAR_HARAD(LOTRBiome.nearHarad),
        FAR_HARAD_SAVANNAH(LOTRBiome.farHarad),
        FAR_HARAD_JUNGLE(LOTRBiome.farHaradJungle),
        PERDOROGWAITH(LOTRBiome.pertorogwaith),
        OCEAN(LOTRBiome.ocean),
        ANDUIN_VALLEY(LOTRBiome.anduinVale),
        UTUMNO(LOTRDimension.UTUMNO, LOTRFaction.UTUMNO.getFactionColor());

        private String codeName;
        private float[] categoryColors;
        public LOTRDimension dimension;
        public List<LOTRAchievement> list = new ArrayList<LOTRAchievement>();
        private int nextRankAchID = 1000;

        private Category(LOTRBiome biome) {
            this(biome.color);
        }

        private Category(LOTRFaction faction) {
            this(faction.getFactionColor());
        }

        private Category(int color) {
            this(LOTRDimension.MIDDLE_EARTH, color);
        }

        private Category(LOTRDimension dim, int color) {
            this.codeName = this.name();
            this.categoryColors = new Color(color).getColorComponents(null);
            this.dimension = dim;
            this.dimension.achievementCategories.add(this);
        }

        public String codeName() {
            return this.codeName;
        }

        public String getDisplayName() {
            return StatCollector.translateToLocal((String)("lotr.achievement.category." + this.codeName()));
        }

        public float[] getCategoryRGB() {
            return this.categoryColors;
        }

        public int getNextRankAchID() {
            ++this.nextRankAchID;
            return this.nextRankAchID;
        }
    }

}

