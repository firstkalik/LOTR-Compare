/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package lotr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public enum LOTRCapes {
    ALIGNMENT_BREE(LOTRFaction.BREE),
    ALIGNMENT_RANGER(LOTRFaction.RANGER_NORTH, "ranger"),
    ALIGNMENT_BLUE_MOUNTAINS(LOTRFaction.BLUE_MOUNTAINS),
    ALIGNMENT_HIGH_ELF(LOTRFaction.HIGH_ELF),
    RIVENDELL(LOTRFaction.HIGH_ELF, "rivendell"),
    RIVENDELL_TRADER(LOTRFaction.HIGH_ELF, "rivendellTrader"),
    ALIGNMENT_GUNDABAD(LOTRFaction.GUNDABAD),
    ALIGNMENT_ANGMAR(LOTRFaction.GUNDABAD),
    ALIGNMENT_RHUDAUR(LOTRFaction.GUNDABAD),
    ALIGNMENT_WOOD_ELF(LOTRFaction.WOOD_ELF),
    ALIGNMENT_DOL_GULDUR(LOTRFaction.DOL_GULDUR),
    ALIGNMENT_DOL_GULDUR_URUK(LOTRFaction.DOL_GULDUR),
    ALIGNMENT_DOL_GULDUR_CASTELIAN(LOTRFaction.DOL_GULDUR),
    ALIGNMENT_DALE(LOTRFaction.DALE),
    ALIGNMENT_ESGAROTH(LOTRFaction.DALE),
    ALIGNMENT_DWARF(LOTRFaction.DURINS_FOLK),
    ALIGNMENT_EREBOR(LOTRFaction.DURINS_FOLK),
    ALIGNMENT_GALADHRIM(LOTRFaction.LOTHLORIEN),
    GALADHRIM_TRADER(LOTRFaction.LOTHLORIEN, "galadhrimTrader"),
    ALIGNMENT_DUNLAND(LOTRFaction.DUNLAND),
    DUNLENDING_BERSERKER(LOTRFaction.DUNLAND, "dunlendingBerserker"),
    ALIGNMENT_URUK_HAI(LOTRFaction.ISENGARD),
    ALIGNMENT_ROHAN(LOTRFaction.ROHAN),
    ROHAN(LOTRFaction.ROHAN, "rohan"),
    ALIGNMENT_GONDOR(LOTRFaction.GONDOR),
    TOWER_GUARD(LOTRFaction.GONDOR, "gondorTowerGuard"),
    GUARD_CITADEL(LOTRFaction.GONDOR, "gondorGuardCitadel"),
    RANGER_ITHILIEN(LOTRFaction.GONDOR, "ranger_ithilien"),
    ALIGNMENT_DOL_AMROTH(LOTRFaction.GONDOR),
    LOSSARNACH(LOTRFaction.GONDOR, "lossarnach"),
    ALIGNMENT_LEBENNIN(LOTRFaction.GONDOR),
    PELARGIR(LOTRFaction.GONDOR, "pelargir"),
    LAMEDON(LOTRFaction.GONDOR, "lamedon"),
    BLACKROOT(LOTRFaction.GONDOR, "blackroot"),
    PINNATH_GELIN(LOTRFaction.GONDOR, "pinnathGelin"),
    ALIGNMENT_MORDOR(LOTRFaction.MORDOR),
    ALIGNMENT_MINAS_MORGUL(LOTRFaction.MORDOR),
    ALIGNMENT_NAN_UNGOL(LOTRFaction.MORDOR),
    ALIGNMENT_BLACK_URUK(LOTRFaction.MORDOR),
    DORWINION_CAPTAIN(LOTRFaction.DORWINION, "dorwinionCaptain"),
    DORWINION_ELF_CAPTAIN(LOTRFaction.DORWINION, "dorwinionElfCaptain"),
    ALIGNMENT_RHUN(LOTRFaction.RHUDEL),
    ALIGNMENT_UTUMNO(LOTRFaction.UTUMNO),
    ALIGNMENT_RED_MOUNTAINS(LOTRFaction.RED_MOUNTAINS),
    ALIGNMENT_DARK_DWARF(LOTRFaction.RED_MOUNTAINS),
    ALIGNMENT_WIND_MOUNTAINS(LOTRFaction.WIND),
    ALIGNMENT_KHAND(LOTRFaction.RHUDEL),
    ALIGNMENT_NEAR_HARAD(LOTRFaction.NEAR_HARAD),
    SOUTHRON_CHAMPION(LOTRFaction.NEAR_HARAD, "haradChampion"),
    GULF_HARAD(LOTRFaction.NEAR_HARAD, "gulf"),
    ALIGNMENT_MOREDAIN(LOTRFaction.MORWAITH),
    ALIGNMENT_TAURETHRIM(LOTRFaction.TAURETHRIM),
    ALIGNMENT_HALF_TROLL(LOTRFaction.HALF_TROLL),
    ALIGNMENT_AVARI_ELF(LOTRFaction.AVARI),
    ALIGNMENT_NUMENOR(LOTRFaction.GONDOR),
    ALIGNMENT_ARNOR(LOTRFaction.GONDOR),
    ALIGNMENT_IMPERIAL(LOTRFaction.GONDOR),
    ALIGNMENT_FALLENELF1(LOTRFaction.UTUMNO),
    ALIGNMENT_FALLENELF2(LOTRFaction.UTUMNO),
    ACHIEVEMENT_BRONZE,
    ACHIEVEMENT_SILVER,
    ACHIEVEMENT_GOLD,
    ACHIEVEMENT_MITHRIL,
    LORE1,
    LORE2,
    LORE3,
    LOTR1,
    LOTR2,
    AMETHYSTCOATEDCAPE,
    CAPEARISTOCRAT,
    CAPECOPPERMISTRESS,
    CAPECRIOMAGE,
    CAPEDARKWANDERER,
    CAPEGREYWANDERER,
    CAPEPIROMANT,
    CAPETOXICMAGE,
    CAPETOXICPRIEST,
    CAPEWIZARD,
    LANDSCAPE,
    OLDCAPE,
    OLDSCHOOLCAPE,
    PERSONALCAPE,
    PROTECTIVECAPE,
    CUTECAPE,
    SHREK,
    LOREMASTER1,
    LOREMASTER2,
    LOREMASTER3,
    LOREMASTER4,
    LOREMASTER5,
    ELECTRICIAN,
    BALROG,
    DEFEAT_MTC,
    DOVAKIN,
    TESTERLOTR,
    FIRSTKALIK,
    MOD(new String[]{"5e906016-533f-40ef-af3a-9f2a0c9efa4a"});

    public CapeType capeType;
    public int capeID;
    public UUID[] playersForCape;
    private LOTRFaction alignmentFaction;
    public ResourceLocation capeTexture;
    public static ResourceLocation GANDALF;
    public static ResourceLocation PALLANDO;
    public static ResourceLocation RADAGHAST;
    public static ResourceLocation GANDALF_SANTA;

    private LOTRCapes(LOTRFaction faction) {
        this(CapeType.ALIGNMENT, new String[0]);
        this.alignmentFaction = faction;
    }

    private LOTRCapes() {
        this(CapeType.ACHIEVABLE, new String[0]);
    }

    private LOTRCapes(String[] players) {
        this(CapeType.EXCLUSIVE, players);
    }

    private LOTRCapes(LOTRFaction faction, String name) {
        this(CapeType.ALIGNMENT, new String[0]);
        this.alignmentFaction = faction;
        this.capeTexture = new ResourceLocation("lotr:cape/" + name + ".png");
    }

    private LOTRCapes(CapeType type, String[] players) {
        this.capeType = type;
        this.capeID = this.capeType.list.size();
        this.capeType.list.add(this);
        this.capeTexture = new ResourceLocation("lotr:cape/" + this.name().toLowerCase() + ".png");
        this.playersForCape = new UUID[players.length];
        for (int i = 0; i < this.playersForCape.length; ++i) {
            String s = players[i];
            this.playersForCape[i] = UUID.fromString(s);
        }
    }

    public String getCapeName() {
        return StatCollector.translateToLocal((String)("lotr.capes." + this.name() + ".name"));
    }

    public String getCapeDesc() {
        return StatCollector.translateToLocal((String)("lotr.capes." + this.name() + ".desc"));
    }

    public boolean canPlayerWearCape(EntityPlayer entityplayer) {
        if (this.capeType == CapeType.ALIGNMENT) {
            return LOTRLevelData.getData(entityplayer).getAlignment(this.alignmentFaction) >= 3000.0f;
        }
        if (this == ACHIEVEMENT_BRONZE) {
            return LOTRLevelData.getData(entityplayer).getEarnedAchievements(LOTRDimension.MIDDLE_EARTH).size() >= 25;
        }
        if (this == ACHIEVEMENT_SILVER) {
            return LOTRLevelData.getData(entityplayer).getEarnedAchievements(LOTRDimension.MIDDLE_EARTH).size() >= 50;
        }
        if (this == ACHIEVEMENT_GOLD) {
            return LOTRLevelData.getData(entityplayer).getEarnedAchievements(LOTRDimension.MIDDLE_EARTH).size() >= 100;
        }
        if (this == ACHIEVEMENT_MITHRIL) {
            return LOTRLevelData.getData(entityplayer).getEarnedAchievements(LOTRDimension.MIDDLE_EARTH).size() >= 200;
        }
        if (this == DEFEAT_MTC) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.killMountainTrollChieftain);
        }
        if (this == LOTR1) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == LOTR2) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == AMETHYSTCOATEDCAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPEARISTOCRAT) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPECOPPERMISTRESS) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPECRIOMAGE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPEDARKWANDERER) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPEGREYWANDERER) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPEPIROMANT) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPETOXICMAGE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPETOXICPRIEST) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CAPEWIZARD) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == LANDSCAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == OLDCAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == OLDSCHOOLCAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == PERSONALCAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == PROTECTIVECAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == CUTECAPE) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.enterLotr);
        }
        if (this == MOD) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.mod);
        }
        if (this == LOREMASTER1) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.lore);
        }
        if (this == LOREMASTER2) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.lore);
        }
        if (this == LOREMASTER3) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.lore);
        }
        if (this == LOREMASTER4) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.lore);
        }
        if (this == LOREMASTER5) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.lore);
        }
        if (this == BALROG) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.killBalrog);
        }
        if (this == SHREK) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.ogre);
        }
        if (this == DOVAKIN) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.dovakin);
        }
        if (this == TESTERLOTR) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.tester);
        }
        if (this == FIRSTKALIK) {
            return LOTRLevelData.getData(entityplayer).hasAchievement(LOTRAchievement.killDeveloper);
        }
        if (this.capeType == CapeType.EXCLUSIVE) {
            for (UUID uuid : this.playersForCape) {
                if (!uuid.equals(entityplayer.getUniqueID())) continue;
                return true;
            }
        }
        return false;
    }

    public static void forceClassLoad() {
    }

    public static LOTRCapes capeForName(String capeName) {
        for (LOTRCapes cape : LOTRCapes.values()) {
            if (!cape.name().equals(capeName)) continue;
            return cape;
        }
        return null;
    }

    static {
        GANDALF = new ResourceLocation("lotr:cape/gandalf.png");
        PALLANDO = new ResourceLocation("lotr:cape/pallando.png");
        RADAGHAST = new ResourceLocation("lotr:cape/radaghast.png");
        GANDALF_SANTA = new ResourceLocation("lotr:cape/santa.png");
    }

    public static enum CapeType {
        ALIGNMENT,
        ACHIEVABLE,
        EXCLUSIVE;

        public ArrayList<LOTRCapes> list = new ArrayList();

        public String getDisplayName() {
            return StatCollector.translateToLocal((String)("lotr.capes.category." + this.name()));
        }
    }

}

