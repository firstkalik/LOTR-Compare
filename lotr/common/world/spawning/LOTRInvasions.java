/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.WeightedRandom$Item
 */
package lotr.common.world.spawning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngbandBerserk;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.entity.npc.LOTREntityAngbandSpiderFire;
import lotr.common.entity.npc.LOTREntityAngbandSpiderIce;
import lotr.common.entity.npc.LOTREntityAngbandSpiderObsidian;
import lotr.common.entity.npc.LOTREntityAngbandTrollObsidian;
import lotr.common.entity.npc.LOTREntityAngbandUruc;
import lotr.common.entity.npc.LOTREntityAngbandWarg;
import lotr.common.entity.npc.LOTREntityAngbandWargBombardier;
import lotr.common.entity.npc.LOTREntityAngbandWargFire;
import lotr.common.entity.npc.LOTREntityAngbandWargIce;
import lotr.common.entity.npc.LOTREntityAngbandWargObsidian;
import lotr.common.entity.npc.LOTREntityAngbandZnam;
import lotr.common.entity.npc.LOTREntityAngbandZnam2;
import lotr.common.entity.npc.LOTREntityAngmarBannerBearer;
import lotr.common.entity.npc.LOTREntityAngmarHillman;
import lotr.common.entity.npc.LOTREntityAngmarHillmanAxeThrower;
import lotr.common.entity.npc.LOTREntityAngmarHillmanBannerBearer;
import lotr.common.entity.npc.LOTREntityAngmarHillmanWarrior;
import lotr.common.entity.npc.LOTREntityAngmarOrc;
import lotr.common.entity.npc.LOTREntityAngmarOrcArcher;
import lotr.common.entity.npc.LOTREntityAngmarOrcBombardier;
import lotr.common.entity.npc.LOTREntityAngmarOrcWarrior;
import lotr.common.entity.npc.LOTREntityAngmarWarg;
import lotr.common.entity.npc.LOTREntityAngmarWargBombardier;
import lotr.common.entity.npc.LOTREntityAvariElfBannerBearer;
import lotr.common.entity.npc.LOTREntityAvariElfScout;
import lotr.common.entity.npc.LOTREntityAvariElfWarrior;
import lotr.common.entity.npc.LOTREntityBlackUruk;
import lotr.common.entity.npc.LOTREntityBlackUrukArcher;
import lotr.common.entity.npc.LOTREntityBlackUrukBannerBearer;
import lotr.common.entity.npc.LOTREntityBlacklockAxeThrower;
import lotr.common.entity.npc.LOTREntityBlacklockWarrior;
import lotr.common.entity.npc.LOTREntityBlacklockZnam;
import lotr.common.entity.npc.LOTREntityBlackrootArcher;
import lotr.common.entity.npc.LOTREntityBlackrootBannerBearer;
import lotr.common.entity.npc.LOTREntityBlackrootSoldier;
import lotr.common.entity.npc.LOTREntityBlueDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityBlueDwarfBannerBearer;
import lotr.common.entity.npc.LOTREntityBlueDwarfWarrior;
import lotr.common.entity.npc.LOTREntityBreeBannerBearer;
import lotr.common.entity.npc.LOTREntityBreeGuard;
import lotr.common.entity.npc.LOTREntityCorsair;
import lotr.common.entity.npc.LOTREntityDaleArcher;
import lotr.common.entity.npc.LOTREntityDaleBannerBearer;
import lotr.common.entity.npc.LOTREntityDaleLevyman;
import lotr.common.entity.npc.LOTREntityDaleSoldier;
import lotr.common.entity.npc.LOTREntityDolAmrothArcher;
import lotr.common.entity.npc.LOTREntityDolAmrothBannerBearer;
import lotr.common.entity.npc.LOTREntityDolAmrothSoldier;
import lotr.common.entity.npc.LOTREntityDolGuldurBannerBearer;
import lotr.common.entity.npc.LOTREntityDolGuldurOrc;
import lotr.common.entity.npc.LOTREntityDolGuldurOrcArcher;
import lotr.common.entity.npc.LOTREntityDolGuldurUruk;
import lotr.common.entity.npc.LOTREntityDolGuldurUrukArcher;
import lotr.common.entity.npc.LOTREntityDolGuldurUrukBannerBearer;
import lotr.common.entity.npc.LOTREntityDolGuldurUrukBerserk;
import lotr.common.entity.npc.LOTREntityDorwinionBannerBearer;
import lotr.common.entity.npc.LOTREntityDorwinionCrossbower;
import lotr.common.entity.npc.LOTREntityDorwinionElfArcher;
import lotr.common.entity.npc.LOTREntityDorwinionElfBannerBearer;
import lotr.common.entity.npc.LOTREntityDorwinionElfWarrior;
import lotr.common.entity.npc.LOTREntityDorwinionGuard;
import lotr.common.entity.npc.LOTREntityDunlending;
import lotr.common.entity.npc.LOTREntityDunlendingArcher;
import lotr.common.entity.npc.LOTREntityDunlendingAxeThrower;
import lotr.common.entity.npc.LOTREntityDunlendingBannerBearer;
import lotr.common.entity.npc.LOTREntityDunlendingBerserker;
import lotr.common.entity.npc.LOTREntityDunlendingWarrior;
import lotr.common.entity.npc.LOTREntityDurmethBannerBearer;
import lotr.common.entity.npc.LOTREntityDurmethOrc;
import lotr.common.entity.npc.LOTREntityDurmethOrcArcher;
import lotr.common.entity.npc.LOTREntityDurmethOrcWarrior;
import lotr.common.entity.npc.LOTREntityDurmethOrcWarriorArcher;
import lotr.common.entity.npc.LOTREntityDurmethWarg;
import lotr.common.entity.npc.LOTREntityDurmethWarriorBannerBearer;
import lotr.common.entity.npc.LOTREntityDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityDwarfBannerBearer;
import lotr.common.entity.npc.LOTREntityDwarfWarrior;
import lotr.common.entity.npc.LOTREntityEasterlingArcher;
import lotr.common.entity.npc.LOTREntityEasterlingBannerBearer;
import lotr.common.entity.npc.LOTREntityEasterlingFireThrower;
import lotr.common.entity.npc.LOTREntityEasterlingGoldWarrior;
import lotr.common.entity.npc.LOTREntityEasterlingLevyman;
import lotr.common.entity.npc.LOTREntityEasterlingWarrior;
import lotr.common.entity.npc.LOTREntityEnt;
import lotr.common.entity.npc.LOTREntityEreborDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityEreborDwarfBannerBearer;
import lotr.common.entity.npc.LOTREntityEreborDwarfBerserk;
import lotr.common.entity.npc.LOTREntityEreborDwarfCrossbow;
import lotr.common.entity.npc.LOTREntityEreborDwarfWarrior;
import lotr.common.entity.npc.LOTREntityEsgarothBannerBearer;
import lotr.common.entity.npc.LOTREntityGaladhrimBannerBearer;
import lotr.common.entity.npc.LOTREntityGaladhrimWarrior;
import lotr.common.entity.npc.LOTREntityGondorArcher;
import lotr.common.entity.npc.LOTREntityGondorBannerBearer;
import lotr.common.entity.npc.LOTREntityGondorLevyman;
import lotr.common.entity.npc.LOTREntityGondorRenegade;
import lotr.common.entity.npc.LOTREntityGondorSoldier;
import lotr.common.entity.npc.LOTREntityGulfHaradArcher;
import lotr.common.entity.npc.LOTREntityGulfHaradBannerBearer;
import lotr.common.entity.npc.LOTREntityGulfHaradWarrior;
import lotr.common.entity.npc.LOTREntityGundabadBannerBearer;
import lotr.common.entity.npc.LOTREntityGundabadCaveTroll;
import lotr.common.entity.npc.LOTREntityGundabadOrc;
import lotr.common.entity.npc.LOTREntityGundabadOrcArcher;
import lotr.common.entity.npc.LOTREntityGundabadUruk;
import lotr.common.entity.npc.LOTREntityGundabadUrukArcher;
import lotr.common.entity.npc.LOTREntityGundabadWarg;
import lotr.common.entity.npc.LOTREntityHalfTroll;
import lotr.common.entity.npc.LOTREntityHalfTrollBannerBearer;
import lotr.common.entity.npc.LOTREntityHalfTrollWarrior;
import lotr.common.entity.npc.LOTREntityHarnedorArcher;
import lotr.common.entity.npc.LOTREntityHarnedorBannerBearer;
import lotr.common.entity.npc.LOTREntityHarnedorWarrior;
import lotr.common.entity.npc.LOTREntityHighElfBannerBearer;
import lotr.common.entity.npc.LOTREntityHighElfWarrior;
import lotr.common.entity.npc.LOTREntityHobbitBounder;
import lotr.common.entity.npc.LOTREntityHuorn;
import lotr.common.entity.npc.LOTREntityIronfistBerserk;
import lotr.common.entity.npc.LOTREntityIronfistWarrior;
import lotr.common.entity.npc.LOTREntityIronfistZnam;
import lotr.common.entity.npc.LOTREntityIsengardSnaga;
import lotr.common.entity.npc.LOTREntityIsengardSnagaArcher;
import lotr.common.entity.npc.LOTREntityLamedonArcher;
import lotr.common.entity.npc.LOTREntityLamedonBannerBearer;
import lotr.common.entity.npc.LOTREntityLamedonHillman;
import lotr.common.entity.npc.LOTREntityLamedonSoldier;
import lotr.common.entity.npc.LOTREntityLebenninBannerBearer;
import lotr.common.entity.npc.LOTREntityLebenninLevyman;
import lotr.common.entity.npc.LOTREntityLossarnachAxeman;
import lotr.common.entity.npc.LOTREntityLossarnachBannerBearer;
import lotr.common.entity.npc.LOTREntityMinasMorgulBannerBearer;
import lotr.common.entity.npc.LOTREntityMirkTroll;
import lotr.common.entity.npc.LOTREntityMirkwoodSpider;
import lotr.common.entity.npc.LOTREntityMordorBannerBearer;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityMordorOrcArcher;
import lotr.common.entity.npc.LOTREntityMordorOrcBombardier;
import lotr.common.entity.npc.LOTREntityMordorSpider;
import lotr.common.entity.npc.LOTREntityMordorWarg;
import lotr.common.entity.npc.LOTREntityMordorWargBombardier;
import lotr.common.entity.npc.LOTREntityMoredainBannerBearer;
import lotr.common.entity.npc.LOTREntityMoredainMercenary;
import lotr.common.entity.npc.LOTREntityMoredainWarrior;
import lotr.common.entity.npc.LOTREntityMoriaOrc;
import lotr.common.entity.npc.LOTREntityMoriaOrcArcher;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNanUngolBannerBearer;
import lotr.common.entity.npc.LOTREntityNearHaradBannerBearer;
import lotr.common.entity.npc.LOTREntityNearHaradrimArcher;
import lotr.common.entity.npc.LOTREntityNearHaradrimWarrior;
import lotr.common.entity.npc.LOTREntityNomadArcher;
import lotr.common.entity.npc.LOTREntityNomadBannerBearer;
import lotr.common.entity.npc.LOTREntityNomadWarrior;
import lotr.common.entity.npc.LOTREntityOlogHai;
import lotr.common.entity.npc.LOTREntityPelargirBannerBearer;
import lotr.common.entity.npc.LOTREntityPelargirMarine;
import lotr.common.entity.npc.LOTREntityPinnathGelinBannerBearer;
import lotr.common.entity.npc.LOTREntityPinnathGelinSoldier;
import lotr.common.entity.npc.LOTREntityRangerIthilien;
import lotr.common.entity.npc.LOTREntityRangerIthilienBannerBearer;
import lotr.common.entity.npc.LOTREntityRangerNorth;
import lotr.common.entity.npc.LOTREntityRangerNorthBannerBearer;
import lotr.common.entity.npc.LOTREntityRivendellBannerBearer;
import lotr.common.entity.npc.LOTREntityRivendellWarrior;
import lotr.common.entity.npc.LOTREntityRohanBannerBearer;
import lotr.common.entity.npc.LOTREntityRohirrimArcher;
import lotr.common.entity.npc.LOTREntityRohirrimWarrior;
import lotr.common.entity.npc.LOTREntitySnowTroll;
import lotr.common.entity.npc.LOTREntitySouthronChampion;
import lotr.common.entity.npc.LOTREntityStiffbeardCrossbow;
import lotr.common.entity.npc.LOTREntityStiffbeardWarrior;
import lotr.common.entity.npc.LOTREntityStiffbeardZnam;
import lotr.common.entity.npc.LOTREntityStonefootFlameThrower;
import lotr.common.entity.npc.LOTREntityStonefootWarrior;
import lotr.common.entity.npc.LOTREntityStonefootZnam;
import lotr.common.entity.npc.LOTREntitySwanKnight;
import lotr.common.entity.npc.LOTREntityTauredainBannerBearer;
import lotr.common.entity.npc.LOTREntityTauredainBlowgunner;
import lotr.common.entity.npc.LOTREntityTauredainWarrior;
import lotr.common.entity.npc.LOTREntityTundraSnowTroll;
import lotr.common.entity.npc.LOTREntityUmbarArcher;
import lotr.common.entity.npc.LOTREntityUmbarBannerBearer;
import lotr.common.entity.npc.LOTREntityUmbarWarrior;
import lotr.common.entity.npc.LOTREntityUrukHai;
import lotr.common.entity.npc.LOTREntityUrukHaiBannerBearer;
import lotr.common.entity.npc.LOTREntityUrukHaiBerserker;
import lotr.common.entity.npc.LOTREntityUrukHaiCrossbower;
import lotr.common.entity.npc.LOTREntityUrukHaiSapper;
import lotr.common.entity.npc.LOTREntityUrukWarg;
import lotr.common.entity.npc.LOTREntityUrukWargBombardier;
import lotr.common.entity.npc.LOTREntityWickedDwarf2;
import lotr.common.entity.npc.LOTREntityWickedElf;
import lotr.common.entity.npc.LOTREntityWindDwarfAxeThrower;
import lotr.common.entity.npc.LOTREntityWindDwarfBannerBearer;
import lotr.common.entity.npc.LOTREntityWindDwarfWarrior;
import lotr.common.entity.npc.LOTREntityWoodElfBannerBearer;
import lotr.common.entity.npc.LOTREntityWoodElfScout;
import lotr.common.entity.npc.LOTREntityWoodElfWarrior;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemConquestHorn;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.util.WeightedRandom;

public enum LOTRInvasions {
    HOBBIT(LOTRFaction.HOBBIT),
    BREE(LOTRFaction.BREE),
    RANGER_NORTH(LOTRFaction.RANGER_NORTH),
    BLUE_MOUNTAINS(LOTRFaction.BLUE_MOUNTAINS),
    HIGH_ELF_LINDON(LOTRFaction.HIGH_ELF, "lindon"),
    HIGH_ELF_RIVENDELL(LOTRFaction.HIGH_ELF, "rivendell"),
    GUNDABAD(LOTRFaction.GUNDABAD),
    GUNDABAD_WARG(LOTRFaction.GUNDABAD, "warg"),
    ANGMAR(LOTRFaction.ANGMAR, "orcs"),
    ANGMAR_HILLMEN(LOTRFaction.ANGMAR, "hillmen"),
    ANGMAR_WARG(LOTRFaction.ANGMAR, "wargs"),
    WOOD_ELF(LOTRFaction.WOOD_ELF),
    DOL_GULDUR(LOTRFaction.DOL_GULDUR),
    DOL_GULDUR_URUK(LOTRFaction.DOL_GULDUR),
    DALE(LOTRFaction.DALE),
    DWARF(LOTRFaction.DURINS_FOLK),
    GALADHRIM(LOTRFaction.LOTHLORIEN),
    DUNLAND(LOTRFaction.DUNLAND),
    URUK_HAI(LOTRFaction.ISENGARD),
    FANGORN(LOTRFaction.FANGORN),
    ROHAN(LOTRFaction.ROHAN),
    GONDOR(LOTRFaction.GONDOR),
    GONDOR_ITHILIEN(LOTRFaction.GONDOR, "ithilien"),
    GONDOR_DOL_AMROTH(LOTRFaction.GONDOR, "dolAmroth"),
    GONDOR_LOSSARNACH(LOTRFaction.GONDOR, "lossarnach"),
    GONDOR_PELARGIR(LOTRFaction.GONDOR, "pelargir"),
    GONDOR_PINNATH_GELIN(LOTRFaction.GONDOR, "pinnathGelin"),
    GONDOR_BLACKROOT(LOTRFaction.GONDOR, "blackroot"),
    GONDOR_LEBENNIN(LOTRFaction.GONDOR, "lebennin"),
    GONDOR_LAMEDON(LOTRFaction.GONDOR, "lamedon"),
    MORDOR(LOTRFaction.MORDOR),
    MORDOR_BLACK_URUK(LOTRFaction.MORDOR, "blackUruk"),
    MORDOR_NAN_UNGOL(LOTRFaction.MORDOR, "nanUngol"),
    MORDOR_WARG(LOTRFaction.MORDOR, "warg"),
    DORWINION(LOTRFaction.DORWINION),
    DORWINION_ELF(LOTRFaction.DORWINION, "elf"),
    RHUN(LOTRFaction.RHUDEL),
    NEAR_HARAD_HARNEDOR(LOTRFaction.NEAR_HARAD, "harnedor"),
    NEAR_HARAD_COAST(LOTRFaction.NEAR_HARAD, "coast"),
    UMBAR(LOTRFaction.UMBAR),
    UMBAR_CORSAIR(LOTRFaction.UMBAR, "corsair"),
    NEAR_HARAD_NOMAD(LOTRFaction.NEAR_HARAD, "nomad"),
    NEAR_HARAD_GULF(LOTRFaction.NEAR_HARAD, "gulf"),
    MOREDAIN(LOTRFaction.MORWAITH),
    TAUREDAIN(LOTRFaction.TAURETHRIM),
    HALF_TROLL(LOTRFaction.HALF_TROLL),
    ANGBAND(LOTRFaction.UTUMNO),
    BLACKLOCK(LOTRFaction.RED_MOUNTAINS, "blacklock"),
    STONEFOOT(LOTRFaction.RED_MOUNTAINS, "stonefoot"),
    STIFFBEARD(LOTRFaction.RED_MOUNTAINS, "stiffbeard"),
    IRONFIST(LOTRFaction.RED_MOUNTAINS, "ironfist"),
    EREBOR(LOTRFaction.DURINS_FOLK, "erebor"),
    ANGBAND1(LOTRFaction.UTUMNO, "utumno_spiders"),
    ANGBAND2(LOTRFaction.UTUMNO, "utumno_wargs"),
    DURMETH(LOTRFaction.GUNDABAD, "durmeth"),
    DURMETH_URUK(LOTRFaction.GUNDABAD, "durmeth_uruk"),
    MORIA(LOTRFaction.GUNDABAD, "moria"),
    DURMETH_WARG(LOTRFaction.GUNDABAD, "durmeth_wargs"),
    AVARI_ELF(LOTRFaction.AVARI),
    WIND(LOTRFaction.WIND);

    public final LOTRFaction invasionFaction;
    private final String subfaction;
    public List<InvasionSpawnEntry> invasionMobs = new ArrayList<InvasionSpawnEntry>();
    private Item invasionIcon;

    private LOTRInvasions(LOTRFaction f) {
        this(f, null);
    }

    private LOTRInvasions(LOTRFaction f, String s) {
        this.invasionFaction = f;
        this.subfaction = s;
    }

    public String codeName() {
        String s = this.invasionFaction.codeName();
        if (this.subfaction != null) {
            s = s + "_" + this.subfaction;
        }
        return s;
    }

    public List<String> codeNameAndAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        if (this.subfaction != null) {
            String subfactionAdd = "_" + this.subfaction;
            aliases.add(this.invasionFaction.codeName() + subfactionAdd);
            for (String al : this.invasionFaction.listAliases()) {
                aliases.add(al + subfactionAdd);
            }
        } else {
            aliases.add(this.invasionFaction.codeName());
            aliases.addAll(this.invasionFaction.listAliases());
        }
        return aliases;
    }

    public String invasionName() {
        if (this.subfaction == null) {
            return this.invasionFaction.factionName();
        }
        return StatCollector.translateToLocal((String)("lotr.invasion." + this.codeName()));
    }

    public String codeNameHorn() {
        return "lotr.invasion." + this.codeName() + ".horn";
    }

    public ItemStack getInvasionIcon() {
        Item sword = this.invasionIcon;
        if (sword == null) {
            sword = Items.iron_sword;
        }
        return new ItemStack(sword);
    }

    public final ItemStack createConquestHorn() {
        ItemStack horn = new ItemStack(LOTRMod.conquestHorn);
        LOTRItemConquestHorn.setInvasionType(horn, this);
        return horn;
    }

    public static void createMobLists() {
        LOTRInvasions.HOBBIT.invasionIcon = LOTRMod.hobbitPipe;
        LOTRInvasions.HOBBIT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHobbitBounder.class, 15));
        LOTRInvasions.BREE.invasionIcon = LOTRMod.pikeIron;
        LOTRInvasions.BREE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBreeGuard.class, 15));
        LOTRInvasions.BREE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBreeBannerBearer.class, 2));
        LOTRInvasions.RANGER_NORTH.invasionIcon = LOTRMod.rangerBow;
        LOTRInvasions.RANGER_NORTH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRangerNorth.class, 15));
        LOTRInvasions.RANGER_NORTH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRangerNorthBannerBearer.class, 2));
        LOTRInvasions.BLUE_MOUNTAINS.invasionIcon = LOTRMod.hammerBlueDwarven;
        LOTRInvasions.BLUE_MOUNTAINS.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlueDwarfWarrior.class, 10));
        LOTRInvasions.BLUE_MOUNTAINS.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlueDwarfAxeThrower.class, 5));
        LOTRInvasions.BLUE_MOUNTAINS.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlueDwarfBannerBearer.class, 2));
        LOTRInvasions.HIGH_ELF_LINDON.invasionIcon = LOTRMod.swordHighElven;
        LOTRInvasions.HIGH_ELF_LINDON.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHighElfWarrior.class, 15));
        LOTRInvasions.HIGH_ELF_LINDON.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHighElfBannerBearer.class, 2));
        LOTRInvasions.HIGH_ELF_RIVENDELL.invasionIcon = LOTRMod.swordRivendell;
        LOTRInvasions.HIGH_ELF_RIVENDELL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRivendellWarrior.class, 15));
        LOTRInvasions.HIGH_ELF_RIVENDELL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRivendellBannerBearer.class, 2));
        LOTRInvasions.GUNDABAD.invasionIcon = LOTRMod.swordGundabadUruk;
        LOTRInvasions.GUNDABAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadOrc.class, 20));
        LOTRInvasions.GUNDABAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadOrcArcher.class, 10));
        LOTRInvasions.GUNDABAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadWarg.class, 20));
        LOTRInvasions.GUNDABAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadBannerBearer.class, 5));
        LOTRInvasions.GUNDABAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadUruk.class, 5));
        LOTRInvasions.GUNDABAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadUrukArcher.class, 2));
        LOTRInvasions.GUNDABAD_WARG.invasionIcon = LOTRMod.wargBone;
        LOTRInvasions.GUNDABAD_WARG.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadWarg.class, 10));
        LOTRInvasions.ANGMAR.invasionIcon = LOTRMod.swordAngmar;
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarOrc.class, 10));
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarOrcArcher.class, 5));
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarOrcBombardier.class, 3));
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarOrcWarrior.class, 3));
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarBannerBearer.class, 2));
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarWarg.class, 10));
        LOTRInvasions.ANGMAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarWargBombardier.class, 1));
        LOTRInvasions.ANGMAR_HILLMEN.invasionIcon = LOTRMod.swordBronze;
        LOTRInvasions.ANGMAR_HILLMEN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarHillman.class, 10));
        LOTRInvasions.ANGMAR_HILLMEN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarHillmanWarrior.class, 5));
        LOTRInvasions.ANGMAR_HILLMEN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarHillmanAxeThrower.class, 5));
        LOTRInvasions.ANGMAR_HILLMEN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarHillmanBannerBearer.class, 2));
        LOTRInvasions.ANGMAR_WARG.invasionIcon = LOTRMod.wargBone;
        LOTRInvasions.ANGMAR_WARG.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngmarWarg.class, 10));
        LOTRInvasions.WOOD_ELF.invasionIcon = LOTRMod.swordWoodElven;
        LOTRInvasions.WOOD_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWoodElfWarrior.class, 10));
        LOTRInvasions.WOOD_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWoodElfScout.class, 5));
        LOTRInvasions.WOOD_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWoodElfBannerBearer.class, 2));
        LOTRInvasions.AVARI_ELF.invasionIcon = LOTRMod.swordWoodElven;
        LOTRInvasions.AVARI_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAvariElfWarrior.class, 10));
        LOTRInvasions.AVARI_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAvariElfScout.class, 5));
        LOTRInvasions.AVARI_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAvariElfBannerBearer.class, 2));
        LOTRInvasions.DOL_GULDUR.invasionIcon = LOTRMod.swordDolGuldur;
        LOTRInvasions.DOL_GULDUR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMirkwoodSpider.class, 15));
        LOTRInvasions.DOL_GULDUR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurOrc.class, 10));
        LOTRInvasions.DOL_GULDUR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurOrcArcher.class, 5));
        LOTRInvasions.DOL_GULDUR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurUruk.class, 3));
        LOTRInvasions.DOL_GULDUR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurBannerBearer.class, 2));
        LOTRInvasions.DOL_GULDUR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMirkTroll.class, 3));
        LOTRInvasions.DOL_GULDUR_URUK.invasionIcon = LOTRMod.swordDolGuldurUruk;
        LOTRInvasions.DOL_GULDUR_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurUruk.class, 10));
        LOTRInvasions.DOL_GULDUR_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurUrukBerserk.class, 3));
        LOTRInvasions.DOL_GULDUR_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurUrukArcher.class, 5));
        LOTRInvasions.DOL_GULDUR_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolGuldurUrukBannerBearer.class, 2));
        LOTRInvasions.DALE.invasionIcon = LOTRMod.swordDale;
        LOTRInvasions.DALE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDaleLevyman.class, 5));
        LOTRInvasions.DALE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDaleSoldier.class, 10));
        LOTRInvasions.DALE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDaleArcher.class, 5));
        LOTRInvasions.DALE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDaleBannerBearer.class, 1));
        LOTRInvasions.DALE.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEsgarothBannerBearer.class, 1));
        LOTRInvasions.DWARF.invasionIcon = LOTRMod.hammerDwarven;
        LOTRInvasions.DWARF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDwarfWarrior.class, 10));
        LOTRInvasions.DWARF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDwarfAxeThrower.class, 5));
        LOTRInvasions.DWARF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDwarfBannerBearer.class, 2));
        LOTRInvasions.GALADHRIM.invasionIcon = LOTRMod.swordElven;
        LOTRInvasions.GALADHRIM.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGaladhrimWarrior.class, 15));
        LOTRInvasions.GALADHRIM.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGaladhrimBannerBearer.class, 2));
        LOTRInvasions.DUNLAND.invasionIcon = LOTRMod.dunlendingClub;
        LOTRInvasions.DUNLAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDunlending.class, 10));
        LOTRInvasions.DUNLAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDunlendingWarrior.class, 5));
        LOTRInvasions.DUNLAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDunlendingArcher.class, 3));
        LOTRInvasions.DUNLAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDunlendingAxeThrower.class, 3));
        LOTRInvasions.DUNLAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDunlendingBerserker.class, 2));
        LOTRInvasions.DUNLAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDunlendingBannerBearer.class, 2));
        LOTRInvasions.URUK_HAI.invasionIcon = LOTRMod.scimitarUruk;
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityIsengardSnaga.class, 5));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityIsengardSnagaArcher.class, 5));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukHai.class, 10));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukHaiCrossbower.class, 5));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukHaiBerserker.class, 5));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukHaiSapper.class, 3));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukHaiBannerBearer.class, 2));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukWarg.class, 10));
        LOTRInvasions.URUK_HAI.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUrukWargBombardier.class, 1));
        LOTRInvasions.FANGORN.invasionIcon = Items.stick;
        LOTRInvasions.FANGORN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEnt.class, 10));
        LOTRInvasions.FANGORN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHuorn.class, 20));
        LOTRInvasions.ROHAN.invasionIcon = LOTRMod.swordRohan;
        LOTRInvasions.ROHAN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRohirrimWarrior.class, 10));
        LOTRInvasions.ROHAN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRohirrimArcher.class, 5));
        LOTRInvasions.ROHAN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRohanBannerBearer.class, 2));
        LOTRInvasions.GONDOR.invasionIcon = LOTRMod.swordGondor;
        LOTRInvasions.GONDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorLevyman.class, 5));
        LOTRInvasions.GONDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorSoldier.class, 10));
        LOTRInvasions.GONDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorArcher.class, 5));
        LOTRInvasions.GONDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorBannerBearer.class, 2));
        LOTRInvasions.GONDOR_ITHILIEN.invasionIcon = LOTRMod.gondorBow;
        LOTRInvasions.GONDOR_ITHILIEN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRangerIthilien.class, 15));
        LOTRInvasions.GONDOR_ITHILIEN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityRangerIthilienBannerBearer.class, 2));
        LOTRInvasions.GONDOR_DOL_AMROTH.invasionIcon = LOTRMod.swordDolAmroth;
        LOTRInvasions.GONDOR_DOL_AMROTH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolAmrothSoldier.class, 10));
        LOTRInvasions.GONDOR_DOL_AMROTH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolAmrothArcher.class, 5));
        LOTRInvasions.GONDOR_DOL_AMROTH.invasionMobs.add(new InvasionSpawnEntry(LOTREntitySwanKnight.class, 5));
        LOTRInvasions.GONDOR_DOL_AMROTH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDolAmrothBannerBearer.class, 2));
        LOTRInvasions.GONDOR_LOSSARNACH.invasionIcon = LOTRMod.battleaxeLossarnach;
        LOTRInvasions.GONDOR_LOSSARNACH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorLevyman.class, 5));
        LOTRInvasions.GONDOR_LOSSARNACH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLossarnachAxeman.class, 15));
        LOTRInvasions.GONDOR_LOSSARNACH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLossarnachBannerBearer.class, 2));
        LOTRInvasions.GONDOR_PELARGIR.invasionIcon = LOTRMod.tridentPelargir;
        LOTRInvasions.GONDOR_PELARGIR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLebenninLevyman.class, 5));
        LOTRInvasions.GONDOR_PELARGIR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityPelargirMarine.class, 15));
        LOTRInvasions.GONDOR_PELARGIR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityPelargirBannerBearer.class, 2));
        LOTRInvasions.GONDOR_PINNATH_GELIN.invasionIcon = LOTRMod.swordGondor;
        LOTRInvasions.GONDOR_PINNATH_GELIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorLevyman.class, 5));
        LOTRInvasions.GONDOR_PINNATH_GELIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityPinnathGelinSoldier.class, 15));
        LOTRInvasions.GONDOR_PINNATH_GELIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityPinnathGelinBannerBearer.class, 2));
        LOTRInvasions.GONDOR_BLACKROOT.invasionIcon = LOTRMod.blackrootBow;
        LOTRInvasions.GONDOR_BLACKROOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorLevyman.class, 5));
        LOTRInvasions.GONDOR_BLACKROOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackrootSoldier.class, 10));
        LOTRInvasions.GONDOR_BLACKROOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackrootArcher.class, 5));
        LOTRInvasions.GONDOR_BLACKROOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackrootBannerBearer.class, 2));
        LOTRInvasions.GONDOR_LEBENNIN.invasionIcon = LOTRMod.swordGondor;
        LOTRInvasions.GONDOR_LEBENNIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLebenninLevyman.class, 10));
        LOTRInvasions.GONDOR_LEBENNIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorSoldier.class, 10));
        LOTRInvasions.GONDOR_LEBENNIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorArcher.class, 5));
        LOTRInvasions.GONDOR_LEBENNIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLebenninBannerBearer.class, 2));
        LOTRInvasions.GONDOR_LAMEDON.invasionIcon = LOTRMod.hammerGondor;
        LOTRInvasions.GONDOR_LAMEDON.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLamedonHillman.class, 5));
        LOTRInvasions.GONDOR_LAMEDON.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLamedonSoldier.class, 10));
        LOTRInvasions.GONDOR_LAMEDON.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLamedonArcher.class, 5));
        LOTRInvasions.GONDOR_LAMEDON.invasionMobs.add(new InvasionSpawnEntry(LOTREntityLamedonBannerBearer.class, 2));
        LOTRInvasions.MORDOR.invasionIcon = LOTRMod.scimitarOrc;
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorOrc.class, 10));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorOrcArcher.class, 5));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorOrcBombardier.class, 2));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorBannerBearer.class, 2));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMinasMorgulBannerBearer.class, 1));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorWarg.class, 10));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorWargBombardier.class, 1));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackUruk.class, 2));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackUrukArcher.class, 1));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackUrukBannerBearer.class, 1));
        LOTRInvasions.MORDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityOlogHai.class, 3));
        LOTRInvasions.MORDOR_BLACK_URUK.invasionIcon = LOTRMod.scimitarBlackUruk;
        LOTRInvasions.MORDOR_BLACK_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackUruk.class, 10));
        LOTRInvasions.MORDOR_BLACK_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackUrukArcher.class, 5));
        LOTRInvasions.MORDOR_BLACK_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlackUrukBannerBearer.class, 2));
        LOTRInvasions.MORDOR_NAN_UNGOL.invasionIcon = LOTRMod.scimitarOrc;
        LOTRInvasions.MORDOR_NAN_UNGOL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorOrc.class, 20));
        LOTRInvasions.MORDOR_NAN_UNGOL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorOrcArcher.class, 10));
        LOTRInvasions.MORDOR_NAN_UNGOL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNanUngolBannerBearer.class, 5));
        LOTRInvasions.MORDOR_NAN_UNGOL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorSpider.class, 30));
        LOTRInvasions.MORDOR_WARG.invasionIcon = LOTRMod.wargBone;
        LOTRInvasions.MORDOR_WARG.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMordorWarg.class, 10));
        LOTRInvasions.DORWINION.invasionIcon = LOTRMod.mugRedWine;
        LOTRInvasions.DORWINION.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDorwinionGuard.class, 10));
        LOTRInvasions.DORWINION.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDorwinionCrossbower.class, 5));
        LOTRInvasions.DORWINION.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDorwinionBannerBearer.class, 2));
        LOTRInvasions.DORWINION_ELF.invasionIcon = LOTRMod.spearBladorthin;
        LOTRInvasions.DORWINION_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDorwinionElfWarrior.class, 10));
        LOTRInvasions.DORWINION_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDorwinionElfArcher.class, 5));
        LOTRInvasions.DORWINION_ELF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDorwinionElfBannerBearer.class, 2));
        LOTRInvasions.RHUN.invasionIcon = LOTRMod.swordRhun;
        LOTRInvasions.RHUN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEasterlingLevyman.class, 5));
        LOTRInvasions.RHUN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEasterlingWarrior.class, 10));
        LOTRInvasions.RHUN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEasterlingArcher.class, 5));
        LOTRInvasions.RHUN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEasterlingGoldWarrior.class, 5));
        LOTRInvasions.RHUN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEasterlingBannerBearer.class, 5));
        LOTRInvasions.RHUN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEasterlingFireThrower.class, 3));
        LOTRInvasions.NEAR_HARAD_HARNEDOR.invasionIcon = LOTRMod.swordHarad;
        LOTRInvasions.NEAR_HARAD_HARNEDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHarnedorWarrior.class, 10));
        LOTRInvasions.NEAR_HARAD_HARNEDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHarnedorArcher.class, 5));
        LOTRInvasions.NEAR_HARAD_HARNEDOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHarnedorBannerBearer.class, 2));
        LOTRInvasions.NEAR_HARAD_COAST.invasionIcon = LOTRMod.scimitarNearHarad;
        LOTRInvasions.NEAR_HARAD_COAST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNearHaradrimWarrior.class, 8));
        LOTRInvasions.NEAR_HARAD_COAST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNearHaradrimArcher.class, 5));
        LOTRInvasions.NEAR_HARAD_COAST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNearHaradBannerBearer.class, 2));
        LOTRInvasions.NEAR_HARAD_COAST.invasionMobs.add(new InvasionSpawnEntry(LOTREntitySouthronChampion.class, 2));
        LOTRInvasions.NEAR_HARAD_COAST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMoredainMercenary.class, 5));
        LOTRInvasions.UMBAR.invasionIcon = LOTRMod.scimitarNearHarad;
        LOTRInvasions.UMBAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUmbarWarrior.class, 100));
        LOTRInvasions.UMBAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUmbarArcher.class, 50));
        LOTRInvasions.UMBAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityUmbarBannerBearer.class, 20));
        LOTRInvasions.UMBAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMoredainMercenary.class, 30));
        LOTRInvasions.UMBAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGondorRenegade.class, 4));
        LOTRInvasions.UMBAR.invasionIcon = LOTRMod.swordCorsair;
        LOTRInvasions.UMBAR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityCorsair.class, 10));
        LOTRInvasions.NEAR_HARAD_NOMAD.invasionIcon = LOTRMod.swordHarad;
        LOTRInvasions.NEAR_HARAD_NOMAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNomadWarrior.class, 10));
        LOTRInvasions.NEAR_HARAD_NOMAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNomadArcher.class, 5));
        LOTRInvasions.NEAR_HARAD_NOMAD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityNomadBannerBearer.class, 2));
        LOTRInvasions.NEAR_HARAD_GULF.invasionIcon = LOTRMod.swordGulfHarad;
        LOTRInvasions.NEAR_HARAD_GULF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGulfHaradWarrior.class, 10));
        LOTRInvasions.NEAR_HARAD_GULF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGulfHaradArcher.class, 5));
        LOTRInvasions.NEAR_HARAD_GULF.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGulfHaradBannerBearer.class, 2));
        LOTRInvasions.MOREDAIN.invasionIcon = LOTRMod.spearMoredain;
        LOTRInvasions.MOREDAIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMoredainWarrior.class, 15));
        LOTRInvasions.MOREDAIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMoredainBannerBearer.class, 2));
        LOTRInvasions.TAUREDAIN.invasionIcon = LOTRMod.swordTauredain;
        LOTRInvasions.TAUREDAIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityTauredainWarrior.class, 10));
        LOTRInvasions.TAUREDAIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityTauredainBlowgunner.class, 5));
        LOTRInvasions.TAUREDAIN.invasionMobs.add(new InvasionSpawnEntry(LOTREntityTauredainBannerBearer.class, 2));
        LOTRInvasions.HALF_TROLL.invasionIcon = LOTRMod.scimitarHalfTroll;
        LOTRInvasions.HALF_TROLL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHalfTroll.class, 10));
        LOTRInvasions.HALF_TROLL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHalfTrollWarrior.class, 10));
        LOTRInvasions.HALF_TROLL.invasionMobs.add(new InvasionSpawnEntry(LOTREntityHalfTrollBannerBearer.class, 2));
        LOTRInvasions.BLACKLOCK.invasionIcon = LOTRMod.swordRed;
        LOTRInvasions.BLACKLOCK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlacklockWarrior.class, 10));
        LOTRInvasions.BLACKLOCK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityIronfistBerserk.class, 2));
        LOTRInvasions.BLACKLOCK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlacklockZnam.class, 2));
        LOTRInvasions.STONEFOOT.invasionIcon = LOTRMod.battleaxeRed;
        LOTRInvasions.STONEFOOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityStonefootWarrior.class, 10));
        LOTRInvasions.STONEFOOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityStonefootFlameThrower.class, 2));
        LOTRInvasions.STONEFOOT.invasionMobs.add(new InvasionSpawnEntry(LOTREntityStonefootZnam.class, 2));
        LOTRInvasions.IRONFIST.invasionIcon = LOTRMod.hammerRed;
        LOTRInvasions.IRONFIST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityIronfistWarrior.class, 10));
        LOTRInvasions.IRONFIST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityBlacklockAxeThrower.class, 2));
        LOTRInvasions.IRONFIST.invasionMobs.add(new InvasionSpawnEntry(LOTREntityIronfistZnam.class, 2));
        LOTRInvasions.STIFFBEARD.invasionIcon = LOTRMod.spearRed;
        LOTRInvasions.STIFFBEARD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityStiffbeardWarrior.class, 10));
        LOTRInvasions.STIFFBEARD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityStiffbeardCrossbow.class, 2));
        LOTRInvasions.STIFFBEARD.invasionMobs.add(new InvasionSpawnEntry(LOTREntityStiffbeardZnam.class, 2));
        LOTRInvasions.EREBOR.invasionIcon = LOTRMod.swordDwarven;
        LOTRInvasions.EREBOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEreborDwarfWarrior.class, 10));
        LOTRInvasions.EREBOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEreborDwarfAxeThrower.class, 5));
        LOTRInvasions.EREBOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEreborDwarfCrossbow.class, 5));
        LOTRInvasions.EREBOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEreborDwarfBerserk.class, 2));
        LOTRInvasions.EREBOR.invasionMobs.add(new InvasionSpawnEntry(LOTREntityEreborDwarfBannerBearer.class, 2));
        LOTRInvasions.ANGBAND.invasionIcon = LOTRMod.swordAngband;
        LOTRInvasions.ANGBAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandOrc.class, 10));
        LOTRInvasions.ANGBAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandUruc.class, 5));
        LOTRInvasions.ANGBAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWickedElf.class, 5));
        LOTRInvasions.ANGBAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandBerserk.class, 2));
        LOTRInvasions.ANGBAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandTrollObsidian.class, 2));
        LOTRInvasions.ANGBAND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandZnam2.class, 2));
        LOTRInvasions.ANGBAND1.invasionIcon = LOTRMod.spearAngband;
        LOTRInvasions.ANGBAND1.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandSpiderIce.class, 10));
        LOTRInvasions.ANGBAND1.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandSpiderObsidian.class, 9));
        LOTRInvasions.ANGBAND1.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandSpiderFire.class, 8));
        LOTRInvasions.ANGBAND1.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandZnam.class, 2));
        LOTRInvasions.WIND.invasionIcon = LOTRMod.battleaxeDwarven;
        LOTRInvasions.WIND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWindDwarfWarrior.class, 10));
        LOTRInvasions.WIND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWindDwarfAxeThrower.class, 5));
        LOTRInvasions.WIND.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWindDwarfBannerBearer.class, 2));
        LOTRInvasions.ANGBAND2.invasionIcon = LOTRMod.wargBone;
        LOTRInvasions.ANGBAND2.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandWarg.class, 10));
        LOTRInvasions.ANGBAND2.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandWargIce.class, 8));
        LOTRInvasions.ANGBAND2.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandWargObsidian.class, 7));
        LOTRInvasions.ANGBAND2.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandWargFire.class, 5));
        LOTRInvasions.ANGBAND2.invasionMobs.add(new InvasionSpawnEntry(LOTREntityAngbandWargBombardier.class, 2));
        LOTRInvasions.DURMETH.invasionIcon = LOTRMod.hammerGundabadUruk;
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethOrc.class, 20));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethOrcArcher.class, 15));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethOrcWarrior.class, 10));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethOrcWarriorArcher.class, 10));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityWickedDwarf2.class, 15));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethWarg.class, 20));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethBannerBearer.class, 5));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethWarriorBannerBearer.class, 3));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadCaveTroll.class, 3));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntityTundraSnowTroll.class, 2));
        LOTRInvasions.DURMETH.invasionMobs.add(new InvasionSpawnEntry(LOTREntitySnowTroll.class, 2));
        LOTRInvasions.DURMETH_URUK.invasionIcon = LOTRMod.swordGundabadUruk;
        LOTRInvasions.DURMETH_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethOrcWarrior.class, 10));
        LOTRInvasions.DURMETH_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethOrcWarriorArcher.class, 10));
        LOTRInvasions.DURMETH_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethWarriorBannerBearer.class, 3));
        LOTRInvasions.DURMETH_URUK.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadCaveTroll.class, 3));
        LOTRInvasions.DURMETH_WARG.invasionIcon = LOTRMod.wargBone;
        LOTRInvasions.DURMETH_WARG.invasionMobs.add(new InvasionSpawnEntry(LOTREntityDurmethWarg.class, 10));
        LOTRInvasions.MORIA.invasionIcon = LOTRMod.battleaxeGundabadUruk;
        LOTRInvasions.MORIA.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMoriaOrc.class, 20));
        LOTRInvasions.MORIA.invasionMobs.add(new InvasionSpawnEntry(LOTREntityMoriaOrcArcher.class, 15));
        LOTRInvasions.MORIA.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadWarg.class, 20));
        LOTRInvasions.MORIA.invasionMobs.add(new InvasionSpawnEntry(LOTREntityGundabadCaveTroll.class, 3));
    }

    public static LOTRInvasions forName(String name) {
        for (LOTRInvasions i : LOTRInvasions.values()) {
            List<String> aliases = i.codeNameAndAliases();
            for (String al : aliases) {
                if (!al.equals(name)) continue;
                return i;
            }
        }
        return null;
    }

    public static LOTRInvasions forID(int ID) {
        for (LOTRInvasions i : LOTRInvasions.values()) {
            if (i.ordinal() != ID) continue;
            return i;
        }
        return null;
    }

    public static String[] listInvasionNames() {
        String[] names = new String[LOTRInvasions.values().length];
        for (int i = 0; i < names.length; ++i) {
            names[i] = LOTRInvasions.values()[i].codeName();
        }
        return names;
    }

    public static class InvasionSpawnEntry
    extends WeightedRandom.Item {
        private Class entityClass;

        public InvasionSpawnEntry(Class<? extends LOTREntityNPC> c, int chance) {
            super(chance);
            this.entityClass = c;
        }

        public Class getEntityClass() {
            return this.entityClass;
        }
    }

}

