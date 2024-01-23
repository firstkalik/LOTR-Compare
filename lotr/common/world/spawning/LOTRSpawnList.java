/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.world.World
 */
package lotr.common.world.spawning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.npc.LOTREntityAngmarHillman;
import lotr.common.entity.npc.LOTREntityAngmarHillmanAxeThrower;
import lotr.common.entity.npc.LOTREntityAngmarHillmanWarrior;
import lotr.common.entity.npc.LOTREntityAngmarOrc;
import lotr.common.entity.npc.LOTREntityAngmarOrcArcher;
import lotr.common.entity.npc.LOTREntityAngmarOrcBombardier;
import lotr.common.entity.npc.LOTREntityAngmarWarg;
import lotr.common.entity.npc.LOTREntityBalrog;
import lotr.common.entity.npc.LOTREntityBarrowWight;
import lotr.common.entity.npc.LOTREntityBlackUruk;
import lotr.common.entity.npc.LOTREntityBlackUrukArcher;
import lotr.common.entity.npc.LOTREntityBlackrootArcher;
import lotr.common.entity.npc.LOTREntityBlackrootSoldier;
import lotr.common.entity.npc.LOTREntityBlueDwarf;
import lotr.common.entity.npc.LOTREntityBlueDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityBlueDwarfMiner;
import lotr.common.entity.npc.LOTREntityBlueDwarfWarrior;
import lotr.common.entity.npc.LOTREntityBreeGuard;
import lotr.common.entity.npc.LOTREntityBreeHobbit;
import lotr.common.entity.npc.LOTREntityBreeMan;
import lotr.common.entity.npc.LOTREntityCorsair;
import lotr.common.entity.npc.LOTREntityDaleArcher;
import lotr.common.entity.npc.LOTREntityDaleLevyman;
import lotr.common.entity.npc.LOTREntityDaleMan;
import lotr.common.entity.npc.LOTREntityDaleSoldier;
import lotr.common.entity.npc.LOTREntityDarkHuorn;
import lotr.common.entity.npc.LOTREntityDolAmrothArcher;
import lotr.common.entity.npc.LOTREntityDolAmrothSoldier;
import lotr.common.entity.npc.LOTREntityDolGuldurOrc;
import lotr.common.entity.npc.LOTREntityDolGuldurOrcArcher;
import lotr.common.entity.npc.LOTREntityDorwinionCrossbower;
import lotr.common.entity.npc.LOTREntityDorwinionElf;
import lotr.common.entity.npc.LOTREntityDorwinionElfArcher;
import lotr.common.entity.npc.LOTREntityDorwinionElfWarrior;
import lotr.common.entity.npc.LOTREntityDorwinionGuard;
import lotr.common.entity.npc.LOTREntityDorwinionMan;
import lotr.common.entity.npc.LOTREntityDorwinionVinehand;
import lotr.common.entity.npc.LOTREntityDorwinionVinekeeper;
import lotr.common.entity.npc.LOTREntityDunedain;
import lotr.common.entity.npc.LOTREntityDunlending;
import lotr.common.entity.npc.LOTREntityDunlendingArcher;
import lotr.common.entity.npc.LOTREntityDunlendingAxeThrower;
import lotr.common.entity.npc.LOTREntityDunlendingBerserker;
import lotr.common.entity.npc.LOTREntityDunlendingWarrior;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityDwarfMiner;
import lotr.common.entity.npc.LOTREntityDwarfWarrior;
import lotr.common.entity.npc.LOTREntityEasterling;
import lotr.common.entity.npc.LOTREntityEasterlingArcher;
import lotr.common.entity.npc.LOTREntityEasterlingFireThrower;
import lotr.common.entity.npc.LOTREntityEasterlingGoldWarrior;
import lotr.common.entity.npc.LOTREntityEasterlingLevyman;
import lotr.common.entity.npc.LOTREntityEasterlingWarrior;
import lotr.common.entity.npc.LOTREntityEnt;
import lotr.common.entity.npc.LOTREntityGaladhrimElf;
import lotr.common.entity.npc.LOTREntityGaladhrimWarden;
import lotr.common.entity.npc.LOTREntityGaladhrimWarrior;
import lotr.common.entity.npc.LOTREntityGondorArcher;
import lotr.common.entity.npc.LOTREntityGondorLevyman;
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.entity.npc.LOTREntityGondorSoldier;
import lotr.common.entity.npc.LOTREntityGulfHaradArcher;
import lotr.common.entity.npc.LOTREntityGulfHaradWarrior;
import lotr.common.entity.npc.LOTREntityGulfHaradrim;
import lotr.common.entity.npc.LOTREntityGundabadOrc;
import lotr.common.entity.npc.LOTREntityGundabadOrcArcher;
import lotr.common.entity.npc.LOTREntityGundabadUruk;
import lotr.common.entity.npc.LOTREntityGundabadUrukArcher;
import lotr.common.entity.npc.LOTREntityGundabadWarg;
import lotr.common.entity.npc.LOTREntityHalfTroll;
import lotr.common.entity.npc.LOTREntityHalfTrollScavenger;
import lotr.common.entity.npc.LOTREntityHalfTrollWarrior;
import lotr.common.entity.npc.LOTREntityHarnedhrim;
import lotr.common.entity.npc.LOTREntityHarnedorArcher;
import lotr.common.entity.npc.LOTREntityHarnedorWarrior;
import lotr.common.entity.npc.LOTREntityHighElf;
import lotr.common.entity.npc.LOTREntityHighElfWarrior;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityHobbitBounder;
import lotr.common.entity.npc.LOTREntityHobbitOrcharder;
import lotr.common.entity.npc.LOTREntityHuorn;
import lotr.common.entity.npc.LOTREntityIsengardSnaga;
import lotr.common.entity.npc.LOTREntityIsengardSnagaArcher;
import lotr.common.entity.npc.LOTREntityLamedonArcher;
import lotr.common.entity.npc.LOTREntityLamedonHillman;
import lotr.common.entity.npc.LOTREntityLamedonSoldier;
import lotr.common.entity.npc.LOTREntityLebenninLevyman;
import lotr.common.entity.npc.LOTREntityLossarnachAxeman;
import lotr.common.entity.npc.LOTREntityMirkTroll;
import lotr.common.entity.npc.LOTREntityMirkwoodSpider;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityMordorOrcArcher;
import lotr.common.entity.npc.LOTREntityMordorOrcBombardier;
import lotr.common.entity.npc.LOTREntityMordorSpider;
import lotr.common.entity.npc.LOTREntityMordorWarg;
import lotr.common.entity.npc.LOTREntityMoredain;
import lotr.common.entity.npc.LOTREntityMoredainWarrior;
import lotr.common.entity.npc.LOTREntityMountainTroll;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNearHaradrim;
import lotr.common.entity.npc.LOTREntityNearHaradrimArcher;
import lotr.common.entity.npc.LOTREntityNearHaradrimWarrior;
import lotr.common.entity.npc.LOTREntityNomad;
import lotr.common.entity.npc.LOTREntityNomadArcher;
import lotr.common.entity.npc.LOTREntityNomadWarrior;
import lotr.common.entity.npc.LOTREntityOlogHai;
import lotr.common.entity.npc.LOTREntityPelargirMarine;
import lotr.common.entity.npc.LOTREntityPinnathGelinSoldier;
import lotr.common.entity.npc.LOTREntityRangerIthilien;
import lotr.common.entity.npc.LOTREntityRangerNorth;
import lotr.common.entity.npc.LOTREntityRivendellElf;
import lotr.common.entity.npc.LOTREntityRivendellWarrior;
import lotr.common.entity.npc.LOTREntityRohanMan;
import lotr.common.entity.npc.LOTREntityRohirrimArcher;
import lotr.common.entity.npc.LOTREntityRohirrimWarrior;
import lotr.common.entity.npc.LOTREntityRuffianBrute;
import lotr.common.entity.npc.LOTREntityRuffianSpy;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.entity.npc.LOTREntitySnowTroll;
import lotr.common.entity.npc.LOTREntitySouthronChampion;
import lotr.common.entity.npc.LOTREntitySwanKnight;
import lotr.common.entity.npc.LOTREntityTauredain;
import lotr.common.entity.npc.LOTREntityTauredainBlowgunner;
import lotr.common.entity.npc.LOTREntityTauredainWarrior;
import lotr.common.entity.npc.LOTREntityTormentedElf;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.entity.npc.LOTREntityUmbarArcher;
import lotr.common.entity.npc.LOTREntityUmbarWarrior;
import lotr.common.entity.npc.LOTREntityUmbarian;
import lotr.common.entity.npc.LOTREntityUrukHai;
import lotr.common.entity.npc.LOTREntityUrukHaiBerserker;
import lotr.common.entity.npc.LOTREntityUrukHaiCrossbower;
import lotr.common.entity.npc.LOTREntityUrukHaiSapper;
import lotr.common.entity.npc.LOTREntityUrukWarg;
import lotr.common.entity.npc.LOTREntityUtumnoFireWarg;
import lotr.common.entity.npc.LOTREntityUtumnoIceSpider;
import lotr.common.entity.npc.LOTREntityUtumnoIceWarg;
import lotr.common.entity.npc.LOTREntityUtumnoObsidianWarg;
import lotr.common.entity.npc.LOTREntityUtumnoOrc;
import lotr.common.entity.npc.LOTREntityUtumnoOrcArcher;
import lotr.common.entity.npc.LOTREntityUtumnoSnowTroll;
import lotr.common.entity.npc.LOTREntityUtumnoTroll;
import lotr.common.entity.npc.LOTREntityUtumnoWarg;
import lotr.common.entity.npc.LOTREntityWickedDwarf;
import lotr.common.entity.npc.LOTREntityWoodElf;
import lotr.common.entity.npc.LOTREntityWoodElfScout;
import lotr.common.entity.npc.LOTREntityWoodElfWarrior;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.spawning.LOTRSpawnEntry;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public class LOTRSpawnList {
    public static final LOTRSpawnList HOBBITS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHobbit.class, 40, 1, 4), new LOTRSpawnEntry(LOTREntityHobbitBounder.class, 1, 1, 3));
    public static final LOTRSpawnList HOBBITS_ORCHARD = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHobbitOrcharder.class, 10, 1, 1));
    public static final LOTRSpawnList DARK_HUORNS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDarkHuorn.class, 10, 4, 4));
    public static final LOTRSpawnList BARROW_WIGHTS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityBarrowWight.class, 10, 1, 1));
    public static final LOTRSpawnList BREE_MEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityBreeMan.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityBreeHobbit.class, 3, 4, 6));
    public static final LOTRSpawnList BREE_GUARDS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityBreeGuard.class, 10, 2, 4));
    public static final LOTRSpawnList RUFFIANS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRuffianSpy.class, 10, 1, 4), new LOTRSpawnEntry(LOTREntityRuffianBrute.class, 5, 1, 4)).factionOverride(LOTRFaction.ISENGARD);
    public static final LOTRSpawnList BLUE_DWARVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityBlueDwarf.class, 100, 4, 4), new LOTRSpawnEntry(LOTREntityBlueDwarfMiner.class, 15, 1, 3), new LOTRSpawnEntry(LOTREntityBlueDwarfWarrior.class, 20, 4, 4), new LOTRSpawnEntry(LOTREntityBlueDwarfAxeThrower.class, 10, 4, 4));
    public static final LOTRSpawnList DUNEDAIN_NORTH = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDunedain.class, 10, 2, 4));
    public static final LOTRSpawnList RANGERS_NORTH = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRangerNorth.class, 10, 1, 4));
    public static final LOTRSpawnList LINDON_ELVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHighElf.class, 10, 4, 6));
    public static final LOTRSpawnList LINDON_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHighElfWarrior.class, 10, 4, 4));
    public static final LOTRSpawnList RIVENDELL_ELVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRivendellElf.class, 10, 4, 6));
    public static final LOTRSpawnList RIVENDELL_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRivendellWarrior.class, 10, 4, 4));
    public static final LOTRSpawnList GUNDABAD_ORCS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGundabadOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityGundabadOrcArcher.class, 10, 4, 6));
    public static final LOTRSpawnList GUNDABAD_URUKS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGundabadUruk.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityGundabadUrukArcher.class, 10, 2, 4));
    public static final LOTRSpawnList GUNDABAD_WARGS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGundabadWarg.class, 10, 4, 4));
    public static final LOTRSpawnList ANGMAR_ORCS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityAngmarOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityAngmarOrcArcher.class, 10, 4, 6));
    public static final LOTRSpawnList ANGMAR_BOMBARDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityAngmarOrcBombardier.class, 10, 1, 2));
    public static final LOTRSpawnList ANGMAR_WARGS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityAngmarWarg.class, 10, 4, 4));
    public static final LOTRSpawnList ANGMAR_HILLMEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityAngmarHillman.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityAngmarHillmanWarrior.class, 5, 4, 6), new LOTRSpawnEntry(LOTREntityAngmarHillmanAxeThrower.class, 5, 4, 6));
    public static final LOTRSpawnList TROLLS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityTroll.class, 10, 1, 3));
    public static final LOTRSpawnList HILL_TROLLS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMountainTroll.class, 10, 1, 3));
    public static final LOTRSpawnList SNOW_TROLLS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntitySnowTroll.class, 10, 1, 3));
    public static final LOTRSpawnList WOOD_ELVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityWoodElf.class, 10, 4, 6));
    public static final LOTRSpawnList WOOD_ELF_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityWoodElfScout.class, 10, 4, 4), new LOTRSpawnEntry(LOTREntityWoodElfWarrior.class, 5, 4, 4));
    public static final LOTRSpawnList MIRKWOOD_SPIDERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMirkwoodSpider.class, 10, 4, 6));
    public static final LOTRSpawnList DOL_GULDUR_ORCS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDolGuldurOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityDolGuldurOrcArcher.class, 10, 4, 6));
    public static final LOTRSpawnList MIRK_TROLLS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMirkTroll.class, 10, 1, 3));
    public static final LOTRSpawnList DALE_MEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDaleMan.class, 10, 2, 4));
    public static final LOTRSpawnList DALE_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDaleLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityDaleSoldier.class, 10, 2, 8), new LOTRSpawnEntry(LOTREntityDaleArcher.class, 5, 2, 8));
    public static final LOTRSpawnList DWARVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDwarf.class, 100, 4, 4), new LOTRSpawnEntry(LOTREntityDwarfMiner.class, 15, 1, 3), new LOTRSpawnEntry(LOTREntityDwarfWarrior.class, 20, 4, 4), new LOTRSpawnEntry(LOTREntityDwarfAxeThrower.class, 10, 4, 4));
    public static final LOTRSpawnList GALADHRIM = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGaladhrimElf.class, 10, 4, 6));
    public static final LOTRSpawnList GALADHRIM_WARDENS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGaladhrimWarden.class, 10, 4, 4));
    public static final LOTRSpawnList GALADHRIM_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGaladhrimWarrior.class, 10, 4, 4));
    public static final LOTRSpawnList DUNLENDINGS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDunlending.class, 30, 4, 6));
    public static final LOTRSpawnList DUNLENDING_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDunlendingWarrior.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityDunlendingArcher.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityDunlendingAxeThrower.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityDunlendingBerserker.class, 5, 1, 2));
    public static final LOTRSpawnList ENTS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityEnt.class, 10, 4, 4));
    public static final LOTRSpawnList HUORNS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHuorn.class, 10, 4, 4));
    public static final LOTRSpawnList ISENGARD_SNAGA = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityIsengardSnaga.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityIsengardSnagaArcher.class, 5, 4, 6));
    public static final LOTRSpawnList URUK_HAI = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUrukHai.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityUrukHaiCrossbower.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityUrukHaiSapper.class, 3, 1, 2), new LOTRSpawnEntry(LOTREntityUrukHaiBerserker.class, 5, 4, 6));
    public static final LOTRSpawnList URUK_WARGS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUrukWarg.class, 10, 4, 4));
    public static final LOTRSpawnList ROHIRRIM = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRohanMan.class, 10, 4, 6));
    public static final LOTRSpawnList ROHIRRIM_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRohirrimWarrior.class, 20, 3, 8), new LOTRSpawnEntry(LOTREntityRohirrimArcher.class, 10, 3, 8));
    public static final LOTRSpawnList GONDOR_MEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGondorMan.class, 10, 4, 6));
    public static final LOTRSpawnList GONDOR_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGondorLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityGondorSoldier.class, 10, 2, 8), new LOTRSpawnEntry(LOTREntityGondorArcher.class, 5, 2, 8));
    public static final LOTRSpawnList DOL_AMROTH_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDolAmrothSoldier.class, 20, 2, 6), new LOTRSpawnEntry(LOTREntityDolAmrothArcher.class, 10, 2, 6), new LOTRSpawnEntry(LOTREntitySwanKnight.class, 5, 2, 4));
    public static final LOTRSpawnList LOSSARNACH_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGondorLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityLossarnachAxeman.class, 20, 2, 6));
    public static final LOTRSpawnList LEBENNIN_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityLebenninLevyman.class, 40, 2, 4), new LOTRSpawnEntry(LOTREntityGondorSoldier.class, 10, 2, 8), new LOTRSpawnEntry(LOTREntityGondorArcher.class, 5, 2, 8));
    public static final LOTRSpawnList PELARGIR_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityLebenninLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityPelargirMarine.class, 15, 2, 8));
    public static final LOTRSpawnList LAMEDON_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGondorLevyman.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityLamedonSoldier.class, 10, 2, 6), new LOTRSpawnEntry(LOTREntityLamedonArcher.class, 5, 2, 6));
    public static final LOTRSpawnList LAMEDON_HILLMEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityLamedonHillman.class, 20, 4, 6));
    public static final LOTRSpawnList BLACKROOT_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGondorLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityBlackrootSoldier.class, 5, 2, 8), new LOTRSpawnEntry(LOTREntityBlackrootArcher.class, 5, 15, 8));
    public static final LOTRSpawnList PINNATH_GELIN_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGondorLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityPinnathGelinSoldier.class, 15, 2, 6));
    public static final LOTRSpawnList RANGERS_ITHILIEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityRangerIthilien.class, 10, 1, 4));
    public static final LOTRSpawnList MORDOR_ORCS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMordorOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityMordorOrcArcher.class, 10, 4, 6));
    public static final LOTRSpawnList MORDOR_BOMBARDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMordorOrcBombardier.class, 10, 1, 2));
    public static final LOTRSpawnList BLACK_URUKS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityBlackUruk.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityBlackUrukArcher.class, 10, 4, 6));
    public static final LOTRSpawnList MORDOR_WARGS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMordorWarg.class, 10, 4, 4));
    public static final LOTRSpawnList MORDOR_SPIDERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMordorSpider.class, 10, 4, 4));
    public static final LOTRSpawnList OLOG_HAI = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityOlogHai.class, 10, 1, 3));
    public static final LOTRSpawnList WICKED_DWARVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityWickedDwarf.class, 10, 1, 3));
    public static final LOTRSpawnList DORWINION_MEN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDorwinionMan.class, 10, 4, 6));
    public static final LOTRSpawnList DORWINION_GUARDS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDorwinionGuard.class, 20, 1, 3), new LOTRSpawnEntry(LOTREntityDorwinionCrossbower.class, 10, 1, 3));
    public static final LOTRSpawnList DORWINION_ELVES = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDorwinionElf.class, 10, 4, 6));
    public static final LOTRSpawnList DORWINION_ELF_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDorwinionElfWarrior.class, 20, 1, 3), new LOTRSpawnEntry(LOTREntityDorwinionElfArcher.class, 10, 1, 3));
    public static final LOTRSpawnList DORWINION_VINEYARDS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityDorwinionVinehand.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityDorwinionVinekeeper.class, 2, 1, 1));
    public static final LOTRSpawnList EASTERLINGS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityEasterling.class, 20, 2, 4));
    public static final LOTRSpawnList EASTERLING_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityEasterlingLevyman.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityEasterlingWarrior.class, 10, 3, 6), new LOTRSpawnEntry(LOTREntityEasterlingArcher.class, 5, 3, 6), new LOTRSpawnEntry(LOTREntityEasterlingFireThrower.class, 2, 1, 3));
    public static final LOTRSpawnList EASTERLING_GOLD_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityEasterlingGoldWarrior.class, 10, 2, 4));
    public static final LOTRSpawnList HARNEDHRIM = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHarnedhrim.class, 10, 2, 4));
    public static final LOTRSpawnList HARNEDOR_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHarnedorWarrior.class, 20, 3, 6), new LOTRSpawnEntry(LOTREntityHarnedorArcher.class, 10, 3, 6));
    public static final LOTRSpawnList COAST_SOUTHRONS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityNearHaradrim.class, 10, 2, 4));
    public static final LOTRSpawnList SOUTHRON_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityNearHaradrimWarrior.class, 20, 3, 6), new LOTRSpawnEntry(LOTREntityNearHaradrimArcher.class, 10, 3, 6), new LOTRSpawnEntry(LOTREntitySouthronChampion.class, 4, 1, 2));
    public static final LOTRSpawnList UMBARIANS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUmbarian.class, 10, 2, 4));
    public static final LOTRSpawnList UMBAR_SOLDIERS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUmbarWarrior.class, 10, 3, 6), new LOTRSpawnEntry(LOTREntityUmbarArcher.class, 5, 3, 6));
    public static final LOTRSpawnList CORSAIRS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityCorsair.class, 10, 2, 6));
    public static final LOTRSpawnList NOMADS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityNomad.class, 10, 4, 6));
    public static final LOTRSpawnList NOMAD_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityNomadWarrior.class, 20, 2, 4), new LOTRSpawnEntry(LOTREntityNomadArcher.class, 10, 2, 4));
    public static final LOTRSpawnList GULF_HARADRIM = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGulfHaradrim.class, 10, 2, 4));
    public static final LOTRSpawnList GULF_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityGulfHaradWarrior.class, 20, 3, 6), new LOTRSpawnEntry(LOTREntityGulfHaradArcher.class, 10, 3, 6));
    public static final LOTRSpawnList MORWAITH = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMoredain.class, 10, 4, 6));
    public static final LOTRSpawnList MORWAITH_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityMoredainWarrior.class, 10, 4, 6));
    public static final LOTRSpawnList TAURETHRIM = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityTauredain.class, 10, 4, 6));
    public static final LOTRSpawnList TAURETHRIM_WARRIORS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityTauredainWarrior.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityTauredainBlowgunner.class, 20, 4, 6));
    public static final LOTRSpawnList HALF_TROLLS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityHalfTroll.class, 60, 2, 4), new LOTRSpawnEntry(LOTREntityHalfTrollWarrior.class, 35, 2, 4), new LOTRSpawnEntry(LOTREntityHalfTrollScavenger.class, 5, 1, 1));
    public static final LOTRSpawnList UTUMNO_ICE = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUtumnoOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityUtumnoOrcArcher.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityUtumnoWarg.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityUtumnoIceWarg.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityUtumnoIceSpider.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityUtumnoSnowTroll.class, 5, 1, 3));
    public static final LOTRSpawnList UTUMNO_OBSIDIAN = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUtumnoOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityUtumnoOrcArcher.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityUtumnoWarg.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityUtumnoObsidianWarg.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityTormentedElf.class, 10, 2, 6), new LOTRSpawnEntry(LOTREntityUtumnoTroll.class, 5, 2, 4));
    public static final LOTRSpawnList UTUMNO_FIRE = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityUtumnoOrc.class, 20, 4, 6), new LOTRSpawnEntry(LOTREntityUtumnoOrcArcher.class, 10, 4, 6), new LOTRSpawnEntry(LOTREntityUtumnoWarg.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityUtumnoFireWarg.class, 10, 2, 4), new LOTRSpawnEntry(LOTREntityTormentedElf.class, 15, 2, 6), new LOTRSpawnEntry(LOTREntityUtumnoTroll.class, 15, 2, 4), new LOTRSpawnEntry(LOTREntityBalrog.class, 2, 1, 1));
    public static final LOTRSpawnList UTUMNO_GUESTS = new LOTRSpawnList(new LOTRSpawnEntry(LOTREntityScrapTrader.class, 10, 1, 1));
    private final List<LOTRSpawnEntry> spawnList;
    private LOTRFaction discoveredFaction;

    private LOTRSpawnList(LOTRSpawnEntry ... entries) {
        this.spawnList = Arrays.asList(entries);
    }

    private LOTRSpawnList factionOverride(LOTRFaction fac) {
        this.discoveredFaction = fac;
        return this;
    }

    public LOTRFaction getListCommonFaction(World world) {
        if (this.discoveredFaction != null) {
            return this.discoveredFaction;
        }
        LOTRFaction commonFaction = null;
        for (LOTRSpawnEntry entry : this.spawnList) {
            Class entityClass = entry.entityClass;
            if (LOTREntityNPC.class.isAssignableFrom(entityClass)) {
                try {
                    LOTREntityNPC npc = (LOTREntityNPC)LOTREntities.createEntityByClass(entityClass, world);
                    LOTRFaction fac = npc.getFaction();
                    if (commonFaction == null) {
                        commonFaction = fac;
                        continue;
                    }
                    if (commonFaction == fac) continue;
                    throw new IllegalArgumentException("Spawn lists must contain only one faction! Mismatched entity class: " + entityClass.getName());
                }
                catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            throw new IllegalArgumentException("Spawn list must contain only NPCs - invalid " + entityClass.getName());
        }
        if (commonFaction != null) {
            this.discoveredFaction = commonFaction;
            return this.discoveredFaction;
        }
        throw new IllegalArgumentException("Failed to discover faction for spawn list");
    }

    public List<LOTRSpawnEntry> getReadOnlyList() {
        return new ArrayList<LOTRSpawnEntry>(this.spawnList);
    }

    public LOTRSpawnEntry getRandomSpawnEntry(Random rand) {
        return (LOTRSpawnEntry)WeightedRandom.getRandomItem((Random)rand, this.spawnList);
    }
}

