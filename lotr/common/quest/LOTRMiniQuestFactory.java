/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemShears
 *  net.minecraft.item.ItemStack
 */
package lotr.common.quest;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLore;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBird;
import lotr.common.entity.animal.LOTREntityCrocodile;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.entity.animal.LOTREntityDesertScorpion;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.animal.LOTREntityLion;
import lotr.common.entity.animal.LOTREntityLioness;
import lotr.common.entity.animal.LOTREntityRabbit;
import lotr.common.entity.npc.LOTREntityAngmarHillman;
import lotr.common.entity.npc.LOTREntityAngmarOrc;
import lotr.common.entity.npc.LOTREntityAngmarWarg;
import lotr.common.entity.npc.LOTREntityAvariElf;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTREntityBanditHarad;
import lotr.common.entity.npc.LOTREntityBarrowWight;
import lotr.common.entity.npc.LOTREntityBlacklock;
import lotr.common.entity.npc.LOTREntityBlacklockWarrior;
import lotr.common.entity.npc.LOTREntityCaveSpider;
import lotr.common.entity.npc.LOTREntityDaleMan;
import lotr.common.entity.npc.LOTREntityDaleSoldier;
import lotr.common.entity.npc.LOTREntityDarkHuorn;
import lotr.common.entity.npc.LOTREntityDesertSpider;
import lotr.common.entity.npc.LOTREntityDolAmrothSoldier;
import lotr.common.entity.npc.LOTREntityDolGuldurOrc;
import lotr.common.entity.npc.LOTREntityDurmethOrc;
import lotr.common.entity.npc.LOTREntityDurmethWarg;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityDwarfWarrior;
import lotr.common.entity.npc.LOTREntityGaladhrimElf;
import lotr.common.entity.npc.LOTREntityGondorMan;
import lotr.common.entity.npc.LOTREntityGondorRenegade;
import lotr.common.entity.npc.LOTREntityGondorSoldier;
import lotr.common.entity.npc.LOTREntityGundabadCaveTroll;
import lotr.common.entity.npc.LOTREntityGundabadOrc;
import lotr.common.entity.npc.LOTREntityGundabadWarg;
import lotr.common.entity.npc.LOTREntityHarnedorWarrior;
import lotr.common.entity.npc.LOTREntityHighElf;
import lotr.common.entity.npc.LOTREntityIronfist;
import lotr.common.entity.npc.LOTREntityIsengardSnaga;
import lotr.common.entity.npc.LOTREntityLossarnachAxeman;
import lotr.common.entity.npc.LOTREntityMirkwoodSpider;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityMordorWarg;
import lotr.common.entity.npc.LOTREntityMoredainWarrior;
import lotr.common.entity.npc.LOTREntityMountainSnowTroll;
import lotr.common.entity.npc.LOTREntityMountainTroll;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNearHaradrimWarrior;
import lotr.common.entity.npc.LOTREntityRangerIthilien;
import lotr.common.entity.npc.LOTREntityRangerNorth;
import lotr.common.entity.npc.LOTREntityRivendellElf;
import lotr.common.entity.npc.LOTREntityRohirrimWarrior;
import lotr.common.entity.npc.LOTREntityScrapTrader;
import lotr.common.entity.npc.LOTREntityStiffbeard;
import lotr.common.entity.npc.LOTREntityStonefoot;
import lotr.common.entity.npc.LOTREntitySwanKnight;
import lotr.common.entity.npc.LOTREntityTauredainBlowgunner;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.entity.npc.LOTREntityTundraSnowTroll;
import lotr.common.entity.npc.LOTREntityUrukHai;
import lotr.common.entity.npc.LOTREntityUrukWarg;
import lotr.common.entity.npc.LOTREntityWickedDwarf2;
import lotr.common.entity.npc.LOTREntityWoodElf;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemBanner;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestBounty;
import lotr.common.quest.LOTRMiniQuestCollect;
import lotr.common.quest.LOTRMiniQuestKill;
import lotr.common.quest.LOTRMiniQuestKillEntity;
import lotr.common.quest.LOTRMiniQuestKillFaction;
import lotr.common.quest.LOTRMiniQuestPickpocket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

public enum LOTRMiniQuestFactory {
    HOBBIT("hobbit"),
    BREE("bree"),
    RUFFIAN_SPY("ruffianSpy"),
    RUFFIAN_BRUTE("ruffianBrute"),
    RANGER_NORTH("rangerNorth"),
    RANGER_NORTH_ARNOR_RELIC("rangerNorthArnorRelic"),
    BLUE_MOUNTAINS("blueMountains"),
    HIGH_ELF("highElf"),
    RIVENDELL("rivendell"),
    GUNDABAD("gundabad"),
    ANGMAR("angmar"),
    ANGMAR_HILLMAN("angmarHillman"),
    WOOD_ELF("woodElf"),
    AVARI_ELF("avariElf"),
    DOL_GULDUR("dolGuldur"),
    DALE("dale"),
    DURIN("durin"),
    EREBOR("durin"),
    GALADHRIM("galadhrim"),
    DUNLAND("dunland"),
    ISENGARD("isengard"),
    ENT("ent"),
    ROHAN("rohan"),
    ROHAN_SHIELDMAIDEN("rohanShieldmaiden"),
    GONDOR("gondor"),
    GONDOR_KILL_RENEGADE("gondorKillRenegade"),
    MORDOR("mordor"),
    DORWINION("dorwinion"),
    DORWINION_ELF("dorwinionElf"),
    RHUN("rhun"),
    HARNENNOR("harnennor"),
    NEAR_HARAD("nearHarad"),
    UMBAR("umbar"),
    CORSAIR("corsair"),
    GONDOR_RENEGADE("gondorRenegade"),
    NOMAD("nomad"),
    GULF_HARAD("gulfHarad"),
    MOREDAIN("moredain"),
    TAUREDAIN("tauredain"),
    HALF_TROLL("halfTroll"),
    REDMOUNTAINS("red"),
    WIND("wind"),
    DURMETH("durmeth"),
    ANGBAND("angband");

    private static Random rand;
    private static Map<Class<? extends LOTRMiniQuest>, Integer> questClassWeights;
    private String baseName;
    private LOTRMiniQuestFactory baseSpeechGroup;
    private Map<Class<? extends LOTRMiniQuest>, List<LOTRMiniQuest.QuestFactoryBase>> questFactories = new HashMap<Class<? extends LOTRMiniQuest>, List<LOTRMiniQuest.QuestFactoryBase>>();
    private LOTRAchievement questAchievement;
    private List<LOTRLore.LoreCategory> loreCategories = new ArrayList<LOTRLore.LoreCategory>();
    private LOTRFaction alignmentRewardOverride;
    private boolean noAlignRewardForEnemy = false;

    private LOTRMiniQuestFactory(String s) {
        this.baseName = s;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public LOTRMiniQuestFactory getBaseSpeechGroup() {
        if (this.baseSpeechGroup != null) {
            return this.baseSpeechGroup;
        }
        return this;
    }

    private void setBaseSpeechGroup(LOTRMiniQuestFactory qf) {
        this.baseSpeechGroup = qf;
    }

    private void addQuest(LOTRMiniQuest.QuestFactoryBase factory) {
        Class questClass = factory.getQuestClass();
        Class<? extends LOTRMiniQuest> registryClass = null;
        for (Class<? extends LOTRMiniQuest> c : questClassWeights.keySet()) {
            if (!questClass.equals(c)) continue;
            registryClass = c;
            break;
        }
        if (registryClass == null) {
            for (Class<? extends LOTRMiniQuest> c : questClassWeights.keySet()) {
                if (!c.isAssignableFrom(questClass)) continue;
                registryClass = c;
                break;
            }
        }
        if (registryClass == null) {
            throw new IllegalArgumentException("Could not find registered quest class for " + questClass.toString());
        }
        factory.setFactoryGroup(this);
        List<LOTRMiniQuest.QuestFactoryBase> list = this.questFactories.get(registryClass);
        if (list == null) {
            list = new ArrayList<LOTRMiniQuest.QuestFactoryBase>();
            this.questFactories.put(registryClass, list);
        }
        list.add(factory);
    }

    public LOTRMiniQuest createQuest(LOTREntityNPC npc) {
        int totalWeight = LOTRMiniQuestFactory.getTotalQuestClassWeight(this);
        if (totalWeight <= 0) {
            FMLLog.warning((String)"LOTR: No quests registered for %s!", (Object[])new Object[]{this.baseName});
            return null;
        }
        int i = rand.nextInt(totalWeight);
        Iterator<Map.Entry<Class<? extends LOTRMiniQuest>, List<LOTRMiniQuest.QuestFactoryBase>>> iterator = this.questFactories.entrySet().iterator();
        List<LOTRMiniQuest.QuestFactoryBase> chosenFactoryList = null;
        while (iterator.hasNext()) {
            Map.Entry<Class<? extends LOTRMiniQuest>, List<LOTRMiniQuest.QuestFactoryBase>> next = iterator.next();
            chosenFactoryList = next.getValue();
            if ((i -= LOTRMiniQuestFactory.getQuestClassWeight(next.getKey())) < 0) continue;
        }
        LOTRMiniQuest.QuestFactoryBase factory = (LOTRMiniQuest.QuestFactoryBase)chosenFactoryList.get(rand.nextInt(chosenFactoryList.size()));
        Object quest = factory.createQuest(npc, rand);
        if (quest != null) {
            ((LOTRMiniQuest)quest).questGroup = this;
        }
        return quest;
    }

    private void setAchievement(LOTRAchievement a) {
        if (this.questAchievement != null) {
            throw new IllegalArgumentException("Miniquest achievement is already registered");
        }
        this.questAchievement = a;
    }

    public LOTRAchievement getAchievement() {
        return this.questAchievement;
    }

    private void setLore(LOTRLore.LoreCategory ... categories) {
        this.loreCategories = Arrays.asList(categories);
    }

    public List<LOTRLore.LoreCategory> getLoreCategories() {
        return this.loreCategories;
    }

    private LOTRMiniQuestFactory setAlignmentRewardOverride(LOTRFaction fac) {
        this.alignmentRewardOverride = fac;
        return this;
    }

    private LOTRMiniQuestFactory setNoAlignRewardForEnemy() {
        this.noAlignRewardForEnemy = true;
        return this;
    }

    public LOTRFaction checkAlignmentRewardFaction(LOTRFaction fac) {
        if (this.alignmentRewardOverride != null) {
            return this.alignmentRewardOverride;
        }
        return fac;
    }

    public boolean isNoAlignRewardForEnemy() {
        return this.noAlignRewardForEnemy;
    }

    private static void registerQuestClass(Class<? extends LOTRMiniQuest> questClass, int weight) {
        questClassWeights.put(questClass, weight);
    }

    private static int getQuestClassWeight(Class<? extends LOTRMiniQuest> questClass) {
        Integer i = questClassWeights.get(questClass);
        if (i == null) {
            throw new RuntimeException("Encountered a registered quest class " + questClass.toString() + " which is not assigned a weight");
        }
        return i;
    }

    private static int getTotalQuestClassWeight(LOTRMiniQuestFactory factory) {
        HashSet<Class<? extends LOTRMiniQuest>> registeredQuestTypes = new HashSet<Class<? extends LOTRMiniQuest>>();
        for (Map.Entry<Class<? extends LOTRMiniQuest>, List<LOTRMiniQuest.QuestFactoryBase>> entry : factory.questFactories.entrySet()) {
            Class<? extends LOTRMiniQuest> questType = entry.getKey();
            registeredQuestTypes.add(questType);
        }
        int totalWeight = 0;
        for (Class c : registeredQuestTypes) {
            totalWeight += LOTRMiniQuestFactory.getQuestClassWeight(c);
        }
        return totalWeight;
    }

    /*
     * Opcode count of 18390 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     */
    public static void createMiniQuests() {
        LOTRMiniQuestFactory.registerQuestClass(LOTRMiniQuestCollect.class, 10);
        LOTRMiniQuestFactory.registerQuestClass(LOTRMiniQuestPickpocket.class, 6);
        LOTRMiniQuestFactory.registerQuestClass(LOTRMiniQuestKill.class, 8);
        LOTRMiniQuestFactory.registerQuestClass(LOTRMiniQuestBounty.class, 4);
        HOBBIT.setAchievement(LOTRAchievement.doMiniquestHobbit);
        HOBBIT.setLore(LOTRLore.LoreCategory.SHIRE);
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("pipeweed").setCollectItem(new ItemStack(LOTRMod.pipeweed), 20, 40).setRewardFactor(0.25f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugAle), 1, 6).setRewardFactor(3.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCider), 1, 6).setRewardFactor(3.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugPerry), 1, 6).setRewardFactor(3.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugMead), 1, 6).setRewardFactor(4.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCherryLiqueur), 1, 6).setRewardFactor(3.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(Items.apple), 4, 10).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.appleGreen), 4, 10).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.pear), 4, 10).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.plum), 4, 10).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.cherry), 4, 10).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(LOTRMod.appleCrumbleItem), 1, 3).setRewardFactor(5.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(LOTRMod.cherryPieItem), 1, 3).setRewardFactor(5.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(LOTRMod.berryPieItem), 1, 3).setRewardFactor(5.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(Items.bread), 4, 12).setRewardFactor(1.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(Items.cooked_chicken), 4, 8).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(Items.cooked_porkchop), 4, 8).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 4, 8).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("uncleBirthday").setCollectItem(new ItemStack(LOTRMod.deerCooked), 4, 8).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("farmingTool").setCollectItem(new ItemStack(Items.iron_hoe), 1, 3).setRewardFactor(4.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("farmingTool").setCollectItem(new ItemStack(LOTRMod.hoeBronze), 1, 3).setRewardFactor(4.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("farmingTool").setCollectItem(new ItemStack(Items.bucket), 1, 4).setRewardFactor(3.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(Blocks.log, 1, 0), 10, 30).setRewardFactor(0.5f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood, 1, 0), 10, 30).setRewardFactor(0.5f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 0), 10, 30).setRewardFactor(0.5f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 1), 10, 30).setRewardFactor(0.5f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("kitchenware").setCollectItem(new ItemStack(LOTRMod.plate), 5, 12).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("kitchenware").setCollectItem(new ItemStack(LOTRMod.clayPlate), 5, 12).setRewardFactor(1.5f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("kitchenware").setCollectItem(new ItemStack(LOTRMod.mug), 5, 15).setRewardFactor(1.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("books").setCollectItem(new ItemStack(Items.book), 4, 10).setRewardFactor(2.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("pastries").setCollectItem(new ItemStack(LOTRMod.appleCrumbleItem), 3, 5).setRewardFactor(4.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("pastries").setCollectItem(new ItemStack(LOTRMod.cherryPieItem), 3, 5).setRewardFactor(4.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("pastries").setCollectItem(new ItemStack(LOTRMod.berryPieItem), 3, 5).setRewardFactor(4.0f));
        HOBBIT.addQuest(new LOTRMiniQuestCollect.QFCollect("pastries").setCollectItem(new ItemStack(Items.cake), 3, 5).setRewardFactor(4.0f));
        BREE.setAchievement(LOTRAchievement.doMiniquestBree);
        BREE.setLore(LOTRLore.LoreCategory.BREE);
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBucket").setCollectItem(new ItemStack(Items.bucket), 1, 4).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugAle), 1, 6).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCider), 1, 6).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugPerry), 1, 6).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugMead), 1, 6).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCherryLiqueur), 1, 6).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bread), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 5, 20).setRewardFactor(0.75f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 5, 20).setRewardFactor(0.75f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_chicken), 5, 20).setRewardFactor(0.75f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 5, 20).setRewardFactor(0.75f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 5, 20).setRewardFactor(0.75f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 3, 15).setRewardFactor(1.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.rabbitStew), 3, 8).setRewardFactor(2.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.baked_potato), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.apple), 3, 12).setRewardFactor(1.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.appleGreen), 3, 12).setRewardFactor(1.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.pear), 3, 12).setRewardFactor(1.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.appleCrumbleItem), 2, 5).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.cherryPieItem), 2, 5).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.mugAle), 5, 15).setRewardFactor(1.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.mugCider), 5, 15).setRewardFactor(1.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRangerItem").setCollectItem(new ItemStack(LOTRMod.helmetRanger), 1, 2).setRewardFactor(8.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRangerItem").setCollectItem(new ItemStack(LOTRMod.bodyRanger), 1, 2).setRewardFactor(8.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRangerItem").setCollectItem(new ItemStack(LOTRMod.legsRanger), 1, 2).setRewardFactor(8.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRangerItem").setCollectItem(new ItemStack(LOTRMod.bootsRanger), 1, 2).setRewardFactor(8.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(Items.iron_hoe), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(LOTRMod.hoeBronze), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(Items.iron_axe), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(LOTRMod.axeBronze), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(Items.iron_shovel), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(LOTRMod.shovelBronze), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(LOTRMod.chisel), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack((Item)Items.shears), 1, 3).setRewardFactor(4.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTool").setCollectItem(new ItemStack(Items.bucket), 1, 4).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(Blocks.log, 1, 0), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood2, 1, 1), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood3, 1, 0), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 0), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("firewood").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 1), 10, 30).setRewardFactor(0.5f));
        BREE.addQuest(new LOTRMiniQuestCollect.QFCollect("pipeweed").setCollectItem(new ItemStack(LOTRMod.pipeweed), 20, 40).setRewardFactor(0.25f));
        BREE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killEnemy").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        BREE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killEnemy").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        BREE.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        BREE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killEnemy").setKillEntity(LOTREntityBandit.class, 1, 3).setRewardFactor(8.0f));
        BREE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 40));
        BREE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWight").setKillEntity(LOTREntityBarrowWight.class, 5, 10).setRewardFactor(3.0f));
        BREE.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        RUFFIAN_SPY.setAchievement(LOTRAchievement.doMiniquestRuffianSpy);
        RUFFIAN_SPY.setAlignmentRewardOverride(LOTRFaction.ISENGARD).setNoAlignRewardForEnemy();
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestPickpocket.QFPickpocket("pickpocket").setPickpocketFaction(LOTRFaction.BREE, 2, 6));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestPickpocket.QFPickpocket("pickpocketForBoss").setPickpocketFaction(LOTRFaction.BREE, 2, 8).setRewardFactor(1.5f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPipeweed").setCollectItem(new ItemStack(LOTRMod.pipeweed), 10, 20).setRewardFactor(0.5f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugAle), 3, 6).setRewardFactor(3.0f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCider), 3, 6).setRewardFactor(3.0f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugPerry), 3, 6).setRewardFactor(3.0f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugMead), 3, 6).setRewardFactor(4.0f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMathom").setCollectItem(new ItemStack(LOTRMod.banner, 1, LOTRItemBanner.BannerType.HOBBIT.bannerID), 10, 15).setRewardFactor(1.5f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMathom").setCollectItem(new ItemStack(LOTRMod.hobbitPipe), 1, 2).setRewardFactor(15.0f));
        RUFFIAN_SPY.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMathom").setCollectItem(new ItemStack(LOTRMod.pipeweed), 10, 20).setRewardFactor(0.5f));
        RUFFIAN_BRUTE.setAchievement(LOTRAchievement.doMiniquestRuffianBrute);
        RUFFIAN_BRUTE.setAlignmentRewardOverride(LOTRFaction.ISENGARD).setNoAlignRewardForEnemy();
        RUFFIAN_BRUTE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugAle), 3, 6));
        RUFFIAN_BRUTE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCider), 3, 6));
        RUFFIAN_BRUTE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugPerry), 3, 6));
        RUFFIAN_BRUTE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugMead), 3, 6));
        RUFFIAN_BRUTE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPipeweed").setCollectItem(new ItemStack(LOTRMod.pipeweed), 20, 40));
        RUFFIAN_BRUTE.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killBreelanders").setKillFaction(LOTRFaction.BREE, 10, 30));
        for (List<LOTRMiniQuest.QuestFactoryBase> qfList : LOTRMiniQuestFactory.RUFFIAN_BRUTE.questFactories.values()) {
            for (LOTRMiniQuest.QuestFactoryBase qf : qfList) {
                qf.setRewardFactor(0.0f);
                qf.setHiring(0.0f);
            }
        }
        RANGER_NORTH.setAchievement(LOTRAchievement.doMiniquestRanger);
        RANGER_NORTH.setLore(LOTRLore.LoreCategory.ERIADOR);
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(Blocks.log, 1, 0), 30, 60).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(Blocks.log, 1, 1), 30, 60).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(LOTRMod.wood2, 1, 1), 30, 60).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 0), 30, 60).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 1), 30, 60).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBricks").setCollectItem(new ItemStack(Blocks.stonebrick), 40, 100).setRewardFactor(0.2f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBricks").setCollectItem(new ItemStack(LOTRMod.brick2, 1, 3), 30, 80).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bread), 10, 30).setRewardFactor(0.5f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 5, 20).setRewardFactor(0.75f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 5, 20).setRewardFactor(0.75f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 5, 20).setRewardFactor(0.75f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 5, 20).setRewardFactor(0.75f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 3, 15).setRewardFactor(1.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.mugAle), 5, 15).setRewardFactor(1.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.mugCider), 5, 15).setRewardFactor(1.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectAthelas").setCollectItem(new ItemStack(LOTRMod.athelas), 2, 6).setRewardFactor(3.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGondorItem").setCollectItem(new ItemStack(LOTRMod.swordGondor), 1, 1).setRewardFactor(10.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGondorItem").setCollectItem(new ItemStack(LOTRMod.helmetGondor), 1, 1).setRewardFactor(10.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGondorItem").setCollectItem(new ItemStack(LOTRMod.helmetGondorWinged), 1, 1).setRewardFactor(15.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("craftRangerItem").setCollectItem(new ItemStack(LOTRMod.helmetRanger), 2, 5).setRewardFactor(3.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("craftRangerItem").setCollectItem(new ItemStack(LOTRMod.bodyRanger), 2, 5).setRewardFactor(4.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(Items.iron_sword), 2, 4).setRewardFactor(3.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.daggerIron), 2, 6).setRewardFactor(2.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.rangerBow), 3, 7).setRewardFactor(2.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(Items.arrow), 20, 40).setRewardFactor(0.25f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(Blocks.wool, 1, 0), 6, 15).setRewardFactor(1.0f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(Items.leather), 10, 20).setRewardFactor(0.5f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEnemyBones").setCollectItem(new ItemStack(LOTRMod.orcBone), 10, 40).setRewardFactor(0.5f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEnemyBones").setCollectItem(new ItemStack(LOTRMod.wargBone), 10, 30).setRewardFactor(0.75f));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 10, 40));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killAngmar").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTroll").setKillEntity(LOTREntityTroll.class, 10, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMountainTroll").setKillEntity(LOTREntityMountainTroll.class, 20, 40));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 40));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityAngmarWarg.class, 10, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDarkHuorn").setKillEntity(LOTREntityDarkHuorn.class, 20, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("avengeBrother").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("avengeBrother").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killHillmen").setKillEntity(LOTREntityAngmarHillman.class, 10, 30));
        RANGER_NORTH.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        RANGER_NORTH_ARNOR_RELIC.setAchievement(LOTRAchievement.doMiniquestRanger);
        RANGER_NORTH_ARNOR_RELIC.setBaseSpeechGroup(RANGER_NORTH);
        RANGER_NORTH_ARNOR_RELIC.setLore(LOTRLore.LoreCategory.ERIADOR);
        RANGER_NORTH_ARNOR_RELIC.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("arnorRelicKill").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        RANGER_NORTH_ARNOR_RELIC.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("arnorRelicKill").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        RANGER_NORTH_ARNOR_RELIC.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("arnorRelicKill").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        for (List<LOTRMiniQuest.QuestFactoryBase> qfList : LOTRMiniQuestFactory.RANGER_NORTH_ARNOR_RELIC.questFactories.values()) {
            for (LOTRMiniQuest.QuestFactoryBase qf : qfList) {
                qf.setRewardFactor(0.0f);
                qf.setRewardItems(new ItemStack[]{new ItemStack(LOTRMod.helmetArnor), new ItemStack(LOTRMod.bodyArnor), new ItemStack(LOTRMod.legsArnor), new ItemStack(LOTRMod.bootsArnor), new ItemStack(LOTRMod.swordArnor), new ItemStack(LOTRMod.daggerArnor), new ItemStack(LOTRMod.spearArnor)});
            }
        }
        BLUE_MOUNTAINS.setAchievement(LOTRAchievement.doMiniquestBlueMountains);
        BLUE_MOUNTAINS.setLore(LOTRLore.LoreCategory.BLUE_MOUNTAINS);
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("mineMithril").setCollectItem(new ItemStack(LOTRMod.mithril), 1, 6).setRewardFactor(100.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 15).setRewardFactor(4.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.goldRaw), 3, 15).setRewardFactor(5.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 15).setRewardFactor(4.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.glowstone_dust), 5, 15).setRewardFactor(2.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.topaz), 1, 6).setRewardFactor(8.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.sapphire), 1, 3).setRewardFactor(10.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amber), 1, 3).setRewardFactor(8.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amethyst), 1, 6).setRewardFactor(8.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.hammerBlueDwarven), 1, 3).setRewardFactor(5.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeBlueDwarven), 1, 3).setRewardFactor(5.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.throwingAxeBlueDwarven), 1, 4).setRewardFactor(4.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.axeBlueDwarven), 1, 4).setRewardFactor(4.5f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.swordBlueDwarven), 1, 4).setRewardFactor(4.5f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.pikeBlueDwarven), 1, 4).setRewardFactor(4.5f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.spearBlueDwarven), 1, 4).setRewardFactor(4.5f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugKhamBrew), 2, 5).setRewardFactor(5.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugDwarvenAle), 2, 5).setRewardFactor(3.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugBlueDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugRedDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 20, 50));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        BLUE_MOUNTAINS.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        HIGH_ELF.setAchievement(LOTRAchievement.doMiniquestHighElf);
        HIGH_ELF.setLore(LOTRLore.LoreCategory.LINDON);
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(Blocks.sapling, 1, 2), 5, 20).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(LOTRMod.sapling2, 1, 1), 5, 20).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBirchWood").setCollectItem(new ItemStack(Blocks.log, 1, 2), 10, 50).setRewardFactor(0.5f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGoldenLeaves").setCollectItem(new ItemStack(LOTRMod.leaves, 1, 1), 10, 20).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornSapling").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 1), 3, 10).setRewardFactor(2.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornNut").setCollectItem(new ItemStack(LOTRMod.mallornNut), 1, 3).setRewardFactor(5.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectCrystal").setCollectItem(new ItemStack(LOTRMod.quenditeCrystal), 4, 16).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.swordHighElven), 1, 4).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.polearmHighElven), 1, 4).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.longspearHighElven), 1, 4).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.spearHighElven), 1, 4).setRewardFactor(2.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetHighElven), 1, 4).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.bodyHighElven), 1, 4).setRewardFactor(4.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 10).setRewardFactor(4.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 10).setRewardFactor(4.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.sapphire), 1, 3).setRewardFactor(12.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.pearl), 1, 3).setRewardFactor(15.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDwarven").setCollectItem(new ItemStack(LOTRMod.brick3, 1, 12), 2, 6).setRewardFactor(4.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.swordGondor), 1, 1).setRewardFactor(10.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.helmetGondorWinged), 1, 1).setRewardFactor(15.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.helmetRanger), 1, 1).setRewardFactor(10.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick, 1, 1), 10, 20).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick, 1, 5), 3, 5).setRewardFactor(4.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick2, 1, 3), 10, 20).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick2, 1, 6), 3, 5).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 0), 2, 5).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 1), 2, 5).setRewardFactor(3.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 2), 2, 5).setRewardFactor(2.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.red_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.yellow_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectString").setCollectItem(new ItemStack(Items.string), 5, 20).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 1, 1).setRewardFactor(10.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetAngmar), 1, 1).setRewardFactor(10.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 40));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityAngmarOrc.class, 10, 40));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 10, 40));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killAngmar").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityAngmarWarg.class, 10, 30));
        HIGH_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTroll").setKillEntity(LOTREntityTroll.class, 10, 30));
        HIGH_ELF.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        RIVENDELL.setAchievement(LOTRAchievement.doMiniquestRivendell);
        RIVENDELL.setLore(LOTRLore.LoreCategory.RIVENDELL, LOTRLore.LoreCategory.EREGION);
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(Blocks.sapling, 1, 2), 5, 20).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(LOTRMod.sapling2, 1, 1), 5, 20).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBirchWood").setCollectItem(new ItemStack(Blocks.log, 1, 2), 10, 50).setRewardFactor(0.5f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGoldenLeaves").setCollectItem(new ItemStack(LOTRMod.leaves, 1, 1), 10, 20).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornSapling").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 1), 3, 10).setRewardFactor(2.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornNut").setCollectItem(new ItemStack(LOTRMod.mallornNut), 1, 3).setRewardFactor(5.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectCrystal").setCollectItem(new ItemStack(LOTRMod.quenditeCrystal), 4, 16).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.swordRivendell), 1, 4).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.polearmRivendell), 1, 4).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.longspearRivendell), 1, 4).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.spearRivendell), 1, 4).setRewardFactor(2.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetRivendell), 1, 4).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.bodyRivendell), 1, 4).setRewardFactor(4.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 10).setRewardFactor(4.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 10).setRewardFactor(4.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.pearl), 1, 3).setRewardFactor(15.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDwarven").setCollectItem(new ItemStack(LOTRMod.brick3, 1, 12), 2, 6).setRewardFactor(4.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.swordGondor), 1, 1).setRewardFactor(10.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.helmetGondorWinged), 1, 1).setRewardFactor(15.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.helmetRanger), 1, 1).setRewardFactor(10.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick, 1, 1), 10, 20).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick, 1, 5), 3, 5).setRewardFactor(4.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick2, 1, 3), 10, 20).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectNumenorItem").setCollectItem(new ItemStack(LOTRMod.brick2, 1, 6), 3, 5).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 0), 2, 5).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 1), 2, 5).setRewardFactor(3.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 2), 2, 5).setRewardFactor(2.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.red_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.yellow_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectString").setCollectItem(new ItemStack(Items.string), 5, 20).setRewardFactor(1.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 1, 1).setRewardFactor(10.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetAngmar), 1, 1).setRewardFactor(10.0f));
        RIVENDELL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 40));
        RIVENDELL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityAngmarOrc.class, 10, 40));
        RIVENDELL.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 10, 40));
        RIVENDELL.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killAngmar").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        RIVENDELL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        RIVENDELL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityAngmarWarg.class, 10, 30));
        RIVENDELL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTroll").setKillEntity(LOTREntityTroll.class, 10, 30));
        RIVENDELL.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        GUNDABAD.setAchievement(LOTRAchievement.doMiniquestGundabad);
        GUNDABAD.setLore(LOTRLore.LoreCategory.GUNDABAD);
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(Items.iron_sword), 1, 5).setRewardFactor(3.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerIron), 1, 6).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_helmet), 2, 5).setRewardFactor(3.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_chestplate), 2, 5).setRewardFactor(4.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetBronze), 2, 5).setRewardFactor(3.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyBronze), 2, 5).setRewardFactor(4.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 8).setRewardFactor(4.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 8).setRewardFactor(4.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        GUNDABAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.HOBBIT, 10, 30));
        GUNDABAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.RANGER_NORTH, 10, 40));
        GUNDABAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.HIGH_ELF, 10, 40));
        GUNDABAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.LOTHLORIEN, 10, 40));
        GUNDABAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerNorth.class, 10, 30));
        GUNDABAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityHighElf.class, 10, 30));
        GUNDABAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityRivendellElf.class, 10, 30));
        GUNDABAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityGaladhrimElf.class, 10, 30));
        GUNDABAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityDwarf.class, 10, 30));
        GUNDABAD.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        ANGMAR.setAchievement(LOTRAchievement.doMiniquestAngmar);
        ANGMAR.setLore(LOTRLore.LoreCategory.ANGMAR);
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.swordAngmar), 1, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeAngmar), 1, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.axeAngmar), 1, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerAngmar), 1, 6).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.polearmAngmar), 1, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetAngmar), 2, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyAngmar), 2, 5).setRewardFactor(4.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsAngmar), 2, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsAngmar), 2, 5).setRewardFactor(3.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 8).setRewardFactor(4.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 8).setRewardFactor(4.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.guldurilCrystal), 3, 6).setRewardFactor(4.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        ANGMAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        ANGMAR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.RANGER_NORTH, 10, 40));
        ANGMAR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.HIGH_ELF, 10, 40));
        ANGMAR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerNorth.class, 10, 30));
        ANGMAR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityHighElf.class, 10, 30));
        ANGMAR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityRivendellElf.class, 10, 30));
        ANGMAR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        ANGMAR_HILLMAN.setAchievement(LOTRAchievement.doMiniquestAngmar);
        ANGMAR_HILLMAN.setLore(LOTRLore.LoreCategory.ANGMAR);
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMeat").setCollectItem(new ItemStack(Items.cooked_porkchop), 4, 8).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMeat").setCollectItem(new ItemStack(Items.cooked_beef), 4, 8).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMeat").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 4, 8).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMeat").setCollectItem(new ItemStack(LOTRMod.deerCooked), 4, 8).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMeat").setCollectItem(new ItemStack(Items.cooked_chicken), 4, 8).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetAngmar), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyAngmar), 2, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsAngmar), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsAngmar), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetBronze), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyBronze), 2, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsBronze), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsBronze), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_helmet), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_chestplate), 2, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_leggings), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_boots), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMetal").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMetal").setCollectItem(new ItemStack(LOTRMod.bronze), 3, 10).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMetal").setCollectItem(new ItemStack(LOTRMod.copper), 3, 10).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMetal").setCollectItem(new ItemStack(LOTRMod.orcSteel), 3, 10).setRewardFactor(2.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcBrew").setCollectItem(new ItemStack(LOTRMod.mugOrcDraught), 2, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.swordAngmar), 1, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeAngmar), 1, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.polearmAngmar), 1, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.swordBronze), 1, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeBronze), 1, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(Items.iron_sword), 1, 5).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeIron), 1, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.pikeIron), 1, 5).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectThrowingAxes").setCollectItem(new ItemStack(LOTRMod.throwingAxeIron), 3, 6).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectThrowingAxes").setCollectItem(new ItemStack(LOTRMod.throwingAxeBronze), 3, 6).setRewardFactor(3.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTreasure").setCollectItem(new ItemStack(Items.gold_ingot), 3, 6).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTreasure").setCollectItem(new ItemStack(LOTRMod.silver), 3, 6).setRewardFactor(4.0f));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killRanger").setKillFaction(LOTRFaction.RANGER_NORTH, 10, 30));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killRangerMany").setKillFaction(LOTRFaction.RANGER_NORTH, 40, 60));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killHighElf").setKillFaction(LOTRFaction.HIGH_ELF, 10, 30));
        ANGMAR_HILLMAN.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        WOOD_ELF.setAchievement(LOTRAchievement.doMiniquestWoodElf);
        WOOD_ELF.setLore(LOTRLore.LoreCategory.WOODLAND_REALM);
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGoldenLeaves").setCollectItem(new ItemStack(LOTRMod.leaves, 1, 1), 10, 20).setRewardFactor(1.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornSapling").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 1), 3, 10).setRewardFactor(2.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornNut").setCollectItem(new ItemStack(LOTRMod.mallornNut), 1, 3).setRewardFactor(5.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectLorienFlowers").setCollectItem(new ItemStack(LOTRMod.elanor), 2, 7).setRewardFactor(2.5f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectLorienFlowers").setCollectItem(new ItemStack(LOTRMod.niphredil), 2, 7).setRewardFactor(2.5f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.swordWoodElven), 1, 4).setRewardFactor(3.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.polearmWoodElven), 1, 4).setRewardFactor(3.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.longspearWoodElven), 1, 4).setRewardFactor(3.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.spearWoodElven), 1, 4).setRewardFactor(2.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetWoodElven), 1, 4).setRewardFactor(3.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.bodyWoodElven), 1, 4).setRewardFactor(4.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(LOTRMod.sapling7, 1, 1), 4, 8).setRewardFactor(2.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 3), 2, 5).setRewardFactor(4.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectString").setCollectItem(new ItemStack(Items.string), 5, 20).setRewardFactor(1.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 1, 1).setRewardFactor(10.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetDolGuldur), 1, 1).setRewardFactor(10.0f));
        WOOD_ELF.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDolGuldur").setKillFaction(LOTRFaction.DOL_GULDUR, 10, 40));
        WOOD_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        WOOD_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDolGuldurOrc.class, 10, 40));
        WOOD_ELF.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        WOOD_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        WOOD_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSpider").setKillEntity(LOTREntityMirkwoodSpider.class, 10, 40));
        WOOD_ELF.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        AVARI_ELF.setAchievement(LOTRAchievement.doMiniquestAvariElf);
        AVARI_ELF.setLore(LOTRLore.LoreCategory.AVARI);
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGoldenLeaves").setCollectItem(new ItemStack(LOTRMod.leaves, 1, 1), 10, 20).setRewardFactor(1.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornSapling").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 1), 3, 10).setRewardFactor(2.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMallornNut").setCollectItem(new ItemStack(LOTRMod.mallornNut), 1, 3).setRewardFactor(5.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectLorienFlowers").setCollectItem(new ItemStack(LOTRMod.elanor), 2, 7).setRewardFactor(2.5f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectLorienFlowers").setCollectItem(new ItemStack(LOTRMod.niphredil), 2, 7).setRewardFactor(2.5f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.red_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.yellow_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        HIGH_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectString").setCollectItem(new ItemStack(Items.string), 5, 20).setRewardFactor(1.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.swordAvariElf), 1, 4).setRewardFactor(3.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.pikeAvariElf), 1, 4).setRewardFactor(3.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.spearAvariElf), 1, 4).setRewardFactor(3.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetAvariElf), 1, 4).setRewardFactor(3.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.bodyAvariElf), 1, 4).setRewardFactor(4.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(LOTRMod.sapling7, 1, 1), 4, 8).setRewardFactor(2.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 3), 2, 5).setRewardFactor(4.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectString").setCollectItem(new ItemStack(Items.string), 5, 20).setRewardFactor(1.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 1, 1).setRewardFactor(10.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetDolGuldur), 1, 1).setRewardFactor(10.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetDurmethOrc), 1, 1).setRewardFactor(10.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDolGuldur").setKillFaction(LOTRFaction.MORDOR, 10, 40));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDurmethOrc.class, 10, 30));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityMordorOrc.class, 10, 40));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityDurmethWarg.class, 10, 30));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTroll").setKillEntity(LOTREntityGundabadCaveTroll.class, 10, 20).setRewardFactor(2.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTroll").setKillEntity(LOTREntityMountainSnowTroll.class, 10, 20).setRewardFactor(3.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTroll").setKillEntity(LOTREntityTundraSnowTroll.class, 10, 20).setRewardFactor(3.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityWickedDwarf2.class, 5, 15).setRewardFactor(2.0f));
        AVARI_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSpider").setKillEntity(LOTREntityCaveSpider.class, 10, 40).setRewardFactor(1.2f));
        AVARI_ELF.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DOL_GULDUR.setAchievement(LOTRAchievement.doMiniquestDolGuldur);
        DOL_GULDUR.setLore(LOTRLore.LoreCategory.DOL_GULDUR);
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.swordDolGuldur), 1, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeDolGuldur), 1, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.axeDolGuldur), 1, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerDolGuldur), 1, 6).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.pikeDolGuldur), 1, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetDolGuldur), 2, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyDolGuldur), 2, 5).setRewardFactor(4.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsDolGuldur), 2, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsDolGuldur), 2, 5).setRewardFactor(3.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 8).setRewardFactor(4.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 8).setRewardFactor(4.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.guldurilCrystal), 3, 6).setRewardFactor(4.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amethyst), 1, 3).setRewardFactor(10.0f));
        DOL_GULDUR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.LOTHLORIEN, 10, 40));
        DOL_GULDUR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.WOOD_ELF, 10, 40));
        DOL_GULDUR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.DALE, 10, 40));
        DOL_GULDUR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityGaladhrimElf.class, 10, 30));
        DOL_GULDUR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityWoodElf.class, 10, 30));
        DOL_GULDUR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DALE.setAchievement(LOTRAchievement.doMiniquestDale);
        DALE.setLore(LOTRLore.LoreCategory.DALE);
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("pastries").setCollectItem(new ItemStack(LOTRMod.dalishPastryItem), 3, 8).setRewardFactor(4.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.mugRedWine), 2, 5).setRewardFactor(5.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.mugWhiteWine), 2, 5).setRewardFactor(5.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArrows").setCollectItem(new ItemStack(Items.arrow), 10, 30).setRewardFactor(0.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("foreignItem").setCollectItem(new ItemStack(LOTRMod.oliveBread), 2, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("foreignItem").setCollectItem(new ItemStack(LOTRMod.banana), 3, 6).setRewardFactor(4.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("foreignItem").setCollectItem(new ItemStack(LOTRMod.mango), 3, 6).setRewardFactor(4.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("foreignItem").setCollectItem(new ItemStack(LOTRMod.daggerGondor), 1, 1).setRewardFactor(15.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("foreignItem").setCollectItem(new ItemStack(LOTRMod.daggerRohan), 1, 1).setRewardFactor(15.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("foreignItem").setCollectItem(new ItemStack(LOTRMod.daggerBlueDwarven), 1, 1).setRewardFactor(15.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(Items.gold_ingot), 3, 10).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(LOTRMod.silver), 3, 10).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(Items.iron_ingot), 5, 12).setRewardFactor(2.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(LOTRMod.bronze), 5, 12).setRewardFactor(2.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(Items.wheat), 20, 40).setRewardFactor(0.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(Items.bread), 3, 10).setRewardFactor(2.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(Blocks.log, 1, 0), 20, 60).setRewardFactor(0.25f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("dwarfTrade").setCollectItem(new ItemStack(Blocks.log, 1, 1), 20, 60).setRewardFactor(0.25f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("smithyItem").setCollectItem(new ItemStack(Items.coal), 10, 30).setRewardFactor(0.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("smithyItem").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(1.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("smithyItem").setCollectItem(new ItemStack(LOTRMod.bronze), 3, 10).setRewardFactor(1.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("smithyItem").setCollectItem(new ItemStack(Items.bucket), 3, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("smithyItem").setCollectItem(new ItemStack(Items.lava_bucket), 2, 4).setRewardFactor(5.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.swordDale), 1, 4).setRewardFactor(5.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.daggerDale), 2, 6).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.spearDale), 1, 4).setRewardFactor(4.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.pikeDale), 1, 4).setRewardFactor(4.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.helmetDale), 2, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.bodyDale), 2, 5).setRewardFactor(4.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.legsDale), 2, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.bootsDale), 2, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.daleBow), 3, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(Items.arrow), 10, 40).setRewardFactor(0.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(Items.leather), 10, 30).setRewardFactor(0.5f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.apple), 3, 8).setRewardFactor(2.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.appleGreen), 3, 8).setRewardFactor(2.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.appleCrumbleItem), 2, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 6).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_chicken), 2, 6).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.cherryPieItem), 2, 5).setRewardFactor(3.0f));
        DALE.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDolGuldur").setKillFaction(LOTRFaction.DOL_GULDUR, 10, 40));
        DALE.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killMordor").setKillFaction(LOTRFaction.MORDOR, 10, 40));
        DALE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        DALE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDolGuldurOrc.class, 10, 30));
        DALE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        DALE.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        EREBOR.setAchievement(LOTRAchievement.doMiniquestDwarf);
        EREBOR.setLore(LOTRLore.LoreCategory.DURIN);
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("mineMithril").setCollectItem(new ItemStack(LOTRMod.mithril), 1, 6).setRewardFactor(100.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 15).setRewardFactor(4.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.goldRaw), 3, 15).setRewardFactor(5.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 15).setRewardFactor(4.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.glowstone_dust), 5, 15).setRewardFactor(2.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.topaz), 1, 6).setRewardFactor(8.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.sapphire), 1, 3).setRewardFactor(10.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amber), 1, 3).setRewardFactor(8.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amethyst), 1, 6).setRewardFactor(8.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.hammerDwarven), 1, 3).setRewardFactor(5.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeDwarven), 1, 3).setRewardFactor(5.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.throwingAxeDwarven), 1, 4).setRewardFactor(4.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.axeDwarven), 1, 4).setRewardFactor(4.5f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.swordDwarven), 1, 4).setRewardFactor(4.5f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.pikeDwarven), 1, 4).setRewardFactor(4.5f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.spearDwarven), 1, 4).setRewardFactor(4.5f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugKhamBrew), 2, 5).setRewardFactor(4.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugDwarvenAle), 2, 5).setRewardFactor(3.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugBlueDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        EREBOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugRedDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        EREBOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 20, 50));
        EREBOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        EREBOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDurmethOrc.class, 10, 40));
        EREBOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityDurmethWarg.class, 10, 40));
        EREBOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        EREBOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSpider").setKillEntity(LOTREntityMirkwoodSpider.class, 20, 30));
        EREBOR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DURIN.setAchievement(LOTRAchievement.doMiniquestDwarf);
        DURIN.setLore(LOTRLore.LoreCategory.DURIN);
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("mineMithril").setCollectItem(new ItemStack(LOTRMod.mithril), 1, 6).setRewardFactor(100.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 15).setRewardFactor(4.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.goldRaw), 3, 15).setRewardFactor(5.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 15).setRewardFactor(4.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.glowstone_dust), 5, 15).setRewardFactor(2.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.topaz), 1, 6).setRewardFactor(8.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.sapphire), 1, 3).setRewardFactor(10.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amber), 1, 3).setRewardFactor(8.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amethyst), 1, 6).setRewardFactor(8.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.hammerDwarven), 1, 3).setRewardFactor(5.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeDwarven), 1, 3).setRewardFactor(5.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.throwingAxeDwarven), 1, 4).setRewardFactor(4.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.axeDwarven), 1, 4).setRewardFactor(4.5f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.swordDwarven), 1, 4).setRewardFactor(4.5f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.pikeDwarven), 1, 4).setRewardFactor(4.5f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.spearDwarven), 1, 4).setRewardFactor(4.5f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugKhamBrew), 2, 5).setRewardFactor(5.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugDwarvenAle), 2, 5).setRewardFactor(3.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugBlueDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        DURIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugRedDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        DURIN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 20, 50));
        DURIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        DURIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDurmethOrc.class, 10, 40));
        DURIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityDurmethWarg.class, 10, 40));
        DURIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        DURIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSpider").setKillEntity(LOTREntityMirkwoodSpider.class, 20, 30));
        DURIN.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        GALADHRIM.setAchievement(LOTRAchievement.doMiniquestGaladhrim);
        GALADHRIM.setLore(LOTRLore.LoreCategory.LOTHLORIEN);
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(LOTRMod.sapling, 1, 1), 5, 20).setRewardFactor(0.5f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(LOTRMod.elanor), 5, 30).setRewardFactor(0.25f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(LOTRMod.niphredil), 5, 30).setRewardFactor(0.25f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collect").setCollectItem(new ItemStack(LOTRMod.mallornNut), 5, 10).setRewardFactor(2.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFangorn").setCollectItem(new ItemStack(LOTRMod.fangornPlant, 1, 0), 4, 10).setRewardFactor(2.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFangorn").setCollectItem(new ItemStack(LOTRMod.fangornPlant, 1, 2), 4, 10).setRewardFactor(2.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFangorn").setCollectItem(new ItemStack(LOTRMod.fangornPlant, 1, 5), 4, 10).setRewardFactor(2.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectCrystal").setCollectItem(new ItemStack(LOTRMod.quenditeCrystal), 4, 16).setRewardFactor(1.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.swordElven), 1, 4).setRewardFactor(3.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.polearmElven), 1, 4).setRewardFactor(3.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.longspearElven), 1, 4).setRewardFactor(3.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.spearElven), 1, 4).setRewardFactor(2.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetElven), 1, 4).setRewardFactor(3.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.bodyElven), 1, 4).setRewardFactor(4.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 10).setRewardFactor(4.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 10).setRewardFactor(4.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amber), 1, 3).setRewardFactor(10.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDwarven").setCollectItem(new ItemStack(LOTRMod.brick3, 1, 12), 2, 6).setRewardFactor(4.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 1), 2, 5).setRewardFactor(3.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack(Blocks.sapling, 1, 2), 2, 5).setRewardFactor(3.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.red_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPlants").setCollectItem(new ItemStack((Block)Blocks.yellow_flower, 1, 0), 2, 8).setRewardFactor(1.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectString").setCollectItem(new ItemStack(Items.string), 5, 20).setRewardFactor(1.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 1, 1).setRewardFactor(10.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetDolGuldur), 1, 1).setRewardFactor(10.0f));
        GALADHRIM.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDolGuldur").setKillFaction(LOTRFaction.DOL_GULDUR, 10, 30));
        GALADHRIM.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        GALADHRIM.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDolGuldurOrc.class, 10, 30));
        GALADHRIM.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 10, 30));
        GALADHRIM.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        GALADHRIM.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DUNLAND.setAchievement(LOTRAchievement.doMiniquestDunland);
        DUNLAND.setLore(LOTRLore.LoreCategory.DUNLAND);
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectResources").setCollectItem(new ItemStack(Blocks.log, 1, 0), 30, 80).setRewardFactor(0.25f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectResources").setCollectItem(new ItemStack(Blocks.log, 1, 1), 30, 80).setRewardFactor(0.25f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectResources").setCollectItem(new ItemStack(Items.coal), 10, 30).setRewardFactor(0.5f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectResources").setCollectItem(new ItemStack(Blocks.cobblestone), 30, 80).setRewardFactor(0.25f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectResources").setCollectItem(new ItemStack(Items.leather), 10, 30).setRewardFactor(0.5f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectResources").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(1.5f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugAle), 3, 10).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugMead), 3, 10).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCider), 3, 10).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugRum), 3, 10).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRohanItem").setCollectItem(new ItemStack(LOTRMod.helmetRohan), 1, 3).setRewardFactor(10.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRohanItem").setCollectItem(new ItemStack(LOTRMod.bodyRohan), 1, 3).setRewardFactor(10.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRohanItem").setCollectItem(new ItemStack(LOTRMod.swordRohan), 1, 3).setRewardFactor(10.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 3, 8).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 3, 8).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 3, 8).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 3, 8).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_chicken), 3, 8).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 3, 12).setRewardFactor(2.0f));
        DUNLAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bread), 5, 15).setRewardFactor(1.0f));
        DUNLAND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killRohirrim").setKillFaction(LOTRFaction.ROHAN, 10, 40));
        DUNLAND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("avengeKin").setKillFaction(LOTRFaction.ROHAN, 30, 60));
        DUNLAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killHorse").setKillEntity(LOTREntityHorse.class, 10, 20));
        DUNLAND.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        ISENGARD.setAchievement(LOTRAchievement.doMiniquestIsengard);
        ISENGARD.setLore(LOTRLore.LoreCategory.ISENGARD);
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.scimitarUruk), 1, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.pikeUruk), 1, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeUruk), 1, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.axeUruk), 1, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerUruk), 1, 6).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetUruk), 2, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyUruk), 2, 5).setRewardFactor(4.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsUruk), 2, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsUruk), 2, 5).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 8).setRewardFactor(4.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 8).setRewardFactor(4.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeSteel").setCollectItem(new ItemStack(LOTRMod.urukSteel), 3, 10).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeSteel").setCollectItem(new ItemStack(LOTRMod.orcSteel), 3, 10).setRewardFactor(3.0f));
        ISENGARD.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeSteel").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        ISENGARD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.ROHAN, 10, 40));
        ISENGARD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.GONDOR, 10, 40));
        ISENGARD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMen").setKillEntity(LOTREntityRohirrimWarrior.class, 10, 30));
        ISENGARD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMen").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        ISENGARD.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        ROHAN.setAchievement(LOTRAchievement.doMiniquestRohan);
        ROHAN.setLore(LOTRLore.LoreCategory.ROHAN);
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 3, 8).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 3, 8).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 3, 8).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 3, 8).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_chicken), 3, 8).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 3, 12).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bread), 5, 15).setRewardFactor(1.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMead").setCollectItem(new ItemStack(LOTRMod.mugMead), 3, 20).setRewardFactor(1.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.log, 1, 0), 20, 60).setRewardFactor(0.25f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.log, 1, 1), 20, 60).setRewardFactor(0.25f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.planks, 1, 0), 80, 160).setRewardFactor(0.125f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.planks, 1, 1), 80, 160).setRewardFactor(0.125f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.cobblestone), 30, 80).setRewardFactor(0.25f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringWeapon").setCollectItem(new ItemStack(LOTRMod.swordRohan), 1, 4).setRewardFactor(3.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringWeapon").setCollectItem(new ItemStack(Items.iron_ingot), 3, 8).setRewardFactor(2.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("stealUruk").setCollectItem(new ItemStack(LOTRMod.urukTable), 1, 2).setRewardFactor(10.0f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBones").setCollectItem(new ItemStack(LOTRMod.orcBone), 15, 30).setRewardFactor(0.5f));
        ROHAN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBones").setCollectItem(new ItemStack(LOTRMod.wargBone), 15, 30).setRewardFactor(0.75f));
        ROHAN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityIsengardSnaga.class, 10, 30));
        ROHAN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityUrukHai.class, 10, 30));
        ROHAN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityMordorOrc.class, 10, 30));
        ROHAN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("avengeRiders").setKillEntity(LOTREntityUrukWarg.class, 10, 20));
        ROHAN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDunland").setKillFaction(LOTRFaction.DUNLAND, 10, 40));
        ROHAN.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        ROHAN_SHIELDMAIDEN.setAchievement(LOTRAchievement.doMiniquestRohanShieldmaiden);
        ROHAN_SHIELDMAIDEN.setLore(LOTRLore.LoreCategory.ROHAN);
        ROHAN_SHIELDMAIDEN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemies").setKillFaction(LOTRFaction.DUNLAND, 5, 20));
        ROHAN_SHIELDMAIDEN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemies").setKillFaction(LOTRFaction.ISENGARD, 5, 20));
        for (List<LOTRMiniQuest.QuestFactoryBase> qfList : LOTRMiniQuestFactory.ROHAN_SHIELDMAIDEN.questFactories.values()) {
            for (LOTRMiniQuest.QuestFactoryBase qf : qfList) {
                qf.setRewardFactor(0.0f);
                qf.setHiring(150.0f);
            }
        }
        ENT.setAchievement(LOTRAchievement.doMiniquestEnt);
        ENT.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemies").setKillFaction(LOTRFaction.ISENGARD, 5, 20));
        for (List<LOTRMiniQuest.QuestFactoryBase> qfList : LOTRMiniQuestFactory.ENT.questFactories.values()) {
            for (LOTRMiniQuest.QuestFactoryBase qf : qfList) {
                qf.setRewardFactor(0.0f);
                qf.setHiring(150.0f);
            }
        }
        GONDOR.setAchievement(LOTRAchievement.doMiniquestGondor);
        GONDOR.setLore(LOTRLore.LoreCategory.GONDOR);
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.log, 1, 0), 20, 60).setRewardFactor(0.25f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(Blocks.planks, 1, 0), 80, 160).setRewardFactor(0.125f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(LOTRMod.rock, 1, 1), 30, 80).setRewardFactor(0.25f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(LOTRMod.brick, 1, 1), 30, 60).setRewardFactor(0.5f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("defences").setCollectItem(new ItemStack(LOTRMod.brick5, 1, 8), 30, 60).setRewardFactor(0.5f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("lebethron").setCollectItem(new ItemStack(LOTRMod.wood2, 1, 0), 10, 30).setRewardFactor(1.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectIron").setCollectItem(new ItemStack(Blocks.iron_ore), 10, 20).setRewardFactor(1.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectIron").setCollectItem(new ItemStack(Items.iron_ingot), 6, 15).setRewardFactor(1.5f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("blackMarble").setCollectItem(new ItemStack(LOTRMod.rock, 1, 0), 25, 35).setRewardFactor(1.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.swordGondor), 1, 4).setRewardFactor(5.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.spearGondor), 1, 4).setRewardFactor(5.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.pikeGondor), 1, 4).setRewardFactor(5.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetGondor), 1, 4).setRewardFactor(5.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.helmetGondorWinged), 1, 4).setRewardFactor(5.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("forge").setCollectItem(new ItemStack(LOTRMod.bodyGondor), 1, 4).setRewardFactor(5.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRohanItem").setCollectItem(new ItemStack(LOTRMod.swordRohan), 1, 1).setRewardFactor(15.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRohanItem").setCollectItem(new ItemStack(LOTRMod.helmetRohan), 1, 1).setRewardFactor(15.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRohanItem").setCollectItem(new ItemStack(LOTRMod.helmetRohanMarshal), 1, 1).setRewardFactor(20.0f));
        GONDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPipeweed").setCollectItem(new ItemStack(LOTRMod.pipeweedPlant), 2, 4).setRewardFactor(6.0f));
        GONDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killMordor").setKillFaction(LOTRFaction.MORDOR, 10, 40));
        GONDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.MORDOR, 30, 40));
        GONDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.DUNLAND, 30, 40));
        GONDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.ISENGARD, 30, 40));
        GONDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killMordorMany").setKillFaction(LOTRFaction.MORDOR, 60, 90));
        GONDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killHarad").setKillFaction(LOTRFaction.NEAR_HARAD, 10, 40));
        GONDOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killHarnennor").setKillEntity(LOTREntityHarnedorWarrior.class, 20, 30));
        GONDOR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        GONDOR_KILL_RENEGADE.setAchievement(LOTRAchievement.doMiniquestGondorKillRenegade);
        GONDOR_KILL_RENEGADE.setBaseSpeechGroup(GONDOR);
        GONDOR_KILL_RENEGADE.setLore(LOTRLore.LoreCategory.GONDOR);
        GONDOR_KILL_RENEGADE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRenegades").setKillEntity(LOTREntityGondorRenegade.class, 2, 6).setRewardFactor(8.0f));
        MORDOR.setAchievement(LOTRAchievement.doMiniquestMordor);
        MORDOR.setLore(LOTRLore.LoreCategory.MORDOR);
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.scimitarOrc), 1, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.polearmOrc), 1, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeOrc), 1, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.axeOrc), 1, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerOrc), 1, 6).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 2, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyOrc), 2, 5).setRewardFactor(4.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsOrc), 2, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsOrc), 2, 5).setRewardFactor(3.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.orcSteel), 3, 10).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.nauriteGem), 4, 12).setRewardFactor(2.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.guldurilCrystal), 3, 6).setRewardFactor(4.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        MORDOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        MORDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.ROHAN, 10, 40));
        MORDOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.GONDOR, 10, 40));
        MORDOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMen").setKillEntity(LOTREntityRohirrimWarrior.class, 10, 30));
        MORDOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMen").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        MORDOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        MORDOR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DORWINION.setAchievement(LOTRAchievement.doMiniquestDorwinion);
        DORWINION.setLore(LOTRLore.LoreCategory.DORWINION);
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBarrel").setCollectItem(new ItemStack(LOTRMod.barrel), 3, 6).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.grapeRed), 4, 12).setRewardFactor(2.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.grapeWhite), 4, 12).setRewardFactor(2.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.raisins), 4, 12).setRewardFactor(2.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.mugRedWine), 2, 6).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.mugWhiteWine), 2, 6).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.mugRedGrapeJuice), 2, 6).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.mugWhiteGrapeJuice), 2, 6).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.olive), 10, 20).setRewardFactor(1.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.deerCooked), 5, 12).setRewardFactor(1.5f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 5, 12).setRewardFactor(1.5f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("feast").setCollectItem(new ItemStack(Items.cooked_beef), 5, 12).setRewardFactor(1.5f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWater").setCollectItem(new ItemStack(Items.bucket), 3, 5).setRewardFactor(3.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWater").setCollectItem(new ItemStack(Items.water_bucket), 3, 5).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectHoe").setCollectItem(new ItemStack(Items.iron_hoe), 1, 3).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectHoe").setCollectItem(new ItemStack(LOTRMod.hoeBronze), 1, 3).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectHoe").setCollectItem(new ItemStack(Items.stone_hoe), 2, 6).setRewardFactor(2.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectHoe").setCollectItem(new ItemStack(Items.wooden_hoe), 3, 8).setRewardFactor(1.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBonemeal").setCollectItem(new ItemStack(Items.dye, 1, 15), 12, 40).setRewardFactor(0.25f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(Items.iron_sword), 2, 4).setRewardFactor(3.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.daggerIron), 2, 6).setRewardFactor(2.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.pikeIron), 2, 4).setRewardFactor(3.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.helmetDorwinion), 2, 5).setRewardFactor(3.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.bodyDorwinion), 2, 5).setRewardFactor(4.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.legsDorwinion), 2, 5).setRewardFactor(3.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.bootsDorwinion), 2, 5).setRewardFactor(3.0f));
        DORWINION.addQuest(new LOTRMiniQuestCollect.QFCollect("kineHorn").setCollectItem(new ItemStack(LOTRMod.kineArawHorn), 1, 3).setRewardFactor(20.0f));
        DORWINION.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRabbits").setKillEntity(LOTREntityRabbit.class, 10, 20));
        DORWINION.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killBirds").setKillEntity(LOTREntityBird.class, 10, 20));
        DORWINION.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killBandit").setKillEntity(LOTREntityBandit.class, 1, 3).setRewardFactor(8.0f));
        DORWINION.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOddmentCollector").setKillEntity(LOTREntityScrapTrader.class, 1, 2).setRewardFactor(15.0f));
        DORWINION.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DORWINION_ELF.setAchievement(LOTRAchievement.doMiniquestDorwinion);
        DORWINION_ELF.setLore(LOTRLore.LoreCategory.DORWINION);
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(Blocks.log, 1, 0), 20, 60).setRewardFactor(0.25f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(Blocks.log, 1, 2), 20, 60).setRewardFactor(0.25f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 2), 20, 60).setRewardFactor(0.25f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 3), 20, 60).setRewardFactor(0.25f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.grapeRed), 4, 12).setRewardFactor(2.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.grapeWhite), 4, 12).setRewardFactor(2.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.barrel), 3, 6).setRewardFactor(4.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.mugRedWine), 2, 6).setRewardFactor(4.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWine").setCollectItem(new ItemStack(LOTRMod.mugWhiteWine), 2, 6).setRewardFactor(4.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBarrel").setCollectItem(new ItemStack(LOTRMod.barrel), 3, 6).setRewardFactor(4.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGreenOak").setCollectItem(new ItemStack(LOTRMod.wood7, 1, 1), 20, 40).setRewardFactor(0.5f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGreenOak").setCollectItem(new ItemStack(LOTRMod.leaves7, 1, 1), 40, 80).setRewardFactor(0.25f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGreenOak").setCollectItem(new ItemStack(LOTRMod.sapling7, 1, 1), 5, 10).setRewardFactor(1.5f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("collectGreenOak").setCollectItem(new ItemStack(LOTRMod.planks2, 1, 13), 40, 80).setRewardFactor(0.25f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("brewing").setCollectItem(new ItemStack(LOTRMod.grapeRed), 4, 12).setRewardFactor(2.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("brewing").setCollectItem(new ItemStack(LOTRMod.grapeWhite), 4, 12).setRewardFactor(2.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("brewing").setCollectItem(new ItemStack(LOTRMod.barrel), 3, 6).setRewardFactor(4.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("brewing").setCollectItem(new ItemStack(LOTRMod.mug), 5, 12).setRewardFactor(1.5f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("brewing").setCollectItem(new ItemStack(Items.bucket), 3, 5).setRewardFactor(3.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestCollect.QFCollect("brewing").setCollectItem(new ItemStack(Items.water_bucket), 3, 5).setRewardFactor(4.0f));
        DORWINION_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        DORWINION_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityMordorOrc.class, 10, 30));
        DORWINION_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        DORWINION_ELF.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityMordorWarg.class, 10, 30));
        DORWINION_ELF.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        RHUN.setAchievement(LOTRAchievement.doMiniquestRhun);
        RHUN.setLore(LOTRLore.LoreCategory.RHUN);
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.pomegranate), 4, 12).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.date), 4, 12).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.rhunFlower, 1, 0), 2, 5).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.rhunFlower, 1, 1), 2, 5).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.rhunFlower, 1, 2), 2, 5).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.rhunFlower, 1, 3), 2, 5).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.rhunFlower, 1, 4), 2, 5).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringRhunThing").setCollectItem(new ItemStack(LOTRMod.sapling8, 1, 1), 3, 5).setRewardFactor(3.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.scimitarOrc), 3, 5).setRewardFactor(3.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.spearOrc), 3, 5).setRewardFactor(3.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.orcSteel), 5, 10).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.helmetOrc), 2, 4).setRewardFactor(4.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.bodyOrc), 2, 4).setRewardFactor(4.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.legsOrc), 2, 4).setRewardFactor(4.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectOrcItem").setCollectItem(new ItemStack(LOTRMod.bootsOrc), 2, 4).setRewardFactor(4.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("goHunting").setCollectItem(new ItemStack(LOTRMod.rabbitRaw), 5, 10).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("goHunting").setCollectItem(new ItemStack(LOTRMod.deerRaw), 5, 10).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("goHunting").setCollectItem(new ItemStack(Items.beef), 5, 10).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("goHunting").setCollectItem(new ItemStack(Items.leather), 8, 16).setRewardFactor(1.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("goHunting").setCollectItem(new ItemStack(Items.feather), 8, 16).setRewardFactor(1.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("goHunting").setCollectItem(new ItemStack(LOTRMod.kineArawHorn), 1, 2).setRewardFactor(10.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFire").setCollectItem(new ItemStack(LOTRMod.nauriteGem), 4, 8).setRewardFactor(3.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFire").setCollectItem(new ItemStack(LOTRMod.sulfur), 4, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFire").setCollectItem(new ItemStack(LOTRMod.saltpeter), 4, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(Items.bread), 5, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(LOTRMod.oliveBread), 5, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(Items.cooked_fished), 2, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRations").setCollectItem(new ItemStack(LOTRMod.raisins), 6, 12).setRewardFactor(1.5f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(Blocks.log, 1, 0), 30, 60).setRewardFactor(0.25f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(LOTRMod.wood8, 1, 1), 30, 60).setRewardFactor(0.25f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 2), 30, 60).setRewardFactor(0.25f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(LOTRMod.brick5, 1, 11), 40, 100).setRewardFactor(0.2f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMaterials").setCollectItem(new ItemStack(Blocks.cobblestone, 1, 0), 40, 100).setRewardFactor(0.25f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectKineHorn").setCollectItem(new ItemStack(LOTRMod.kineArawHorn), 1, 1).setRewardFactor(30.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDorwinionWine").setCollectItem(new ItemStack(LOTRMod.mugRedWine), 3, 8).setRewardFactor(3.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDorwinionWine").setCollectItem(new ItemStack(LOTRMod.mugWhiteWine), 3, 8).setRewardFactor(3.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringPoison").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        RHUN.addQuest(new LOTRMiniQuestCollect.QFCollect("bringPoison").setCollectItem(new ItemStack(LOTRMod.sulfur), 4, 8).setRewardFactor(2.0f));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killThings").setKillEntity(LOTREntityGondorMan.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killThings").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killThings").setKillEntity(LOTREntityDaleMan.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killThings").setKillEntity(LOTREntityRabbit.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killThings").setKillEntity(LOTREntityDeer.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorAllies").setKillEntity(LOTREntityRohirrimWarrior.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorAllies").setKillEntity(LOTREntityDaleSoldier.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorAllies").setKillEntity(LOTREntityDolAmrothSoldier.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorAllies").setKillEntity(LOTREntityLossarnachAxeman.class, 10, 30));
        RHUN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDale").setKillFaction(LOTRFaction.DALE, 20, 40));
        RHUN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killNorthmen").setKillFaction(LOTRFaction.DALE, 10, 40));
        RHUN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killDwarves").setKillFaction(LOTRFaction.DURINS_FOLK, 10, 40));
        RHUN.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        HARNENNOR.setAchievement(LOTRAchievement.doMiniquestNearHarad);
        HARNENNOR.setLore(LOTRLore.LoreCategory.HARNENNOR);
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("bringWater").setCollectItem(new ItemStack(Items.water_bucket), 3, 5).setRewardFactor(5.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBlackRock").setCollectItem(new ItemStack(LOTRMod.rock, 1, 0), 30, 50).setRewardFactor(0.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDates").setCollectItem(new ItemStack(LOTRMod.date), 8, 15).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lemon), 4, 12).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.orange), 4, 12).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lime), 4, 12).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.plum), 4, 12).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("orangeJuice").setCollectItem(new ItemStack(LOTRMod.mugOrangeJuice), 2, 6).setRewardFactor(4.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("lemonLiqueur").setCollectItem(new ItemStack(LOTRMod.mugLemonLiqueur), 2, 6).setRewardFactor(4.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPoison").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRangedWeapon").setCollectItem(new ItemStack(LOTRMod.nearHaradBow), 1, 3).setRewardFactor(5.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectRangedWeapon").setCollectItem(new ItemStack(Items.arrow), 20, 40).setRewardFactor(0.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTradeGoods").setCollectItem(new ItemStack(Items.dye, 1, 4), 3, 8).setRewardFactor(3.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTradeGoods").setCollectItem(new ItemStack(LOTRMod.lionFur), 3, 6).setRewardFactor(3.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTradeGoods").setCollectItem(new ItemStack(LOTRMod.doubleFlower, 1, 3), 5, 15).setRewardFactor(1.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectTradeGoods").setCollectItem(new ItemStack(LOTRMod.olive), 10, 20).setRewardFactor(1.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMarketFood").setCollectItem(new ItemStack(Items.bread), 5, 8).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMarketFood").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 5, 12).setRewardFactor(1.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMarketFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 5, 12).setRewardFactor(1.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMarketFood").setCollectItem(new ItemStack(LOTRMod.orange), 4, 8).setRewardFactor(2.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMarketFood").setCollectItem(new ItemStack(LOTRMod.lemon), 4, 8).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(Blocks.log, 1, 0), 20, 60).setRewardFactor(0.25f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 2), 20, 60).setRewardFactor(0.25f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(Blocks.planks, 1, 0), 80, 160).setRewardFactor(0.125f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(LOTRMod.planks2, 1, 2), 80, 160).setRewardFactor(0.125f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(Blocks.sandstone, 1, 0), 30, 80).setRewardFactor(0.25f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(Blocks.sandstone, 1, 1), 15, 40).setRewardFactor(0.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("buildMaterials").setCollectItem(new ItemStack(LOTRMod.thatch, 1, 1), 20, 40).setRewardFactor(0.5f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("specialFood").setCollectItem(new ItemStack(LOTRMod.orange), 4, 8).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("specialFood").setCollectItem(new ItemStack(LOTRMod.lemon), 4, 8).setRewardFactor(2.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("specialFood").setCollectItem(new ItemStack(LOTRMod.mango), 2, 4).setRewardFactor(4.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("specialFood").setCollectItem(new ItemStack(LOTRMod.banana), 2, 4).setRewardFactor(4.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestCollect.QFCollect("specialFood").setCollectItem(new ItemStack(LOTRMod.lionCooked), 3, 6).setRewardFactor(3.0f));
        HARNENNOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("conquerGondor").setKillFaction(LOTRFaction.GONDOR, 20, 50));
        HARNENNOR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGondor").setKillFaction(LOTRFaction.GONDOR, 20, 50));
        HARNENNOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("avengeRangers").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        HARNENNOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorSoldier").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        HARNENNOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("reclaimHarondor").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        HARNENNOR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRohirrim").setKillEntity(LOTREntityRohirrimWarrior.class, 10, 30));
        HARNENNOR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        NEAR_HARAD.setAchievement(LOTRAchievement.doMiniquestNearHarad);
        NEAR_HARAD.setLore(LOTRLore.LoreCategory.SOUTHRON);
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringWater").setCollectItem(new ItemStack(Items.water_bucket), 3, 5).setRewardFactor(5.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBlackRock").setCollectItem(new ItemStack(LOTRMod.rock, 1, 0), 30, 50).setRewardFactor(0.5f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDates").setCollectItem(new ItemStack(LOTRMod.date), 8, 15).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lemon), 4, 12).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.orange), 4, 12).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lime), 4, 12).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.plum), 4, 12).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("orangeJuice").setCollectItem(new ItemStack(LOTRMod.mugOrangeJuice), 2, 6).setRewardFactor(4.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("lemonLiqueur").setCollectItem(new ItemStack(LOTRMod.mugLemonLiqueur), 2, 6).setRewardFactor(4.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPoison").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("gulfSword").setCollectItem(new ItemStack(LOTRMod.swordGulfHarad), 1, 1).setRewardFactor(5.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("ringTax").setCollectItem(new ItemStack(LOTRMod.goldRing), 2, 5).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("ringTax").setCollectItem(new ItemStack(LOTRMod.silverRing), 2, 5).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("saveFromVenom").setCollectItem(new ItemStack(LOTRMod.pearl), 1, 1).setRewardFactor(20.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringReedsRoof").setCollectItem(new ItemStack(LOTRMod.driedReeds), 10, 20).setRewardFactor(0.5f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringReedsRoof").setCollectItem(new ItemStack(LOTRMod.thatch, 1, 1), 10, 20).setRewardFactor(0.5f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringReedsRoof").setCollectItem(new ItemStack(LOTRMod.slabSingleThatch, 1, 1), 20, 40).setRewardFactor(0.25f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("cedarWood").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 2), 30, 60).setRewardFactor(0.25f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("cedarWood").setCollectItem(new ItemStack(LOTRMod.planks2, 1, 2), 60, 120).setRewardFactor(0.125f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(Blocks.sandstone, 1, 0), 30, 80).setRewardFactor(0.25f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(LOTRMod.brick, 1, 15), 30, 60).setRewardFactor(0.5f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(LOTRMod.brick3, 1, 13), 30, 60).setRewardFactor(0.75f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringMeat").setCollectItem(new ItemStack(LOTRMod.rabbitCooked), 5, 10).setRewardFactor(1.5f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringMeat").setCollectItem(new ItemStack(LOTRMod.deerCooked), 5, 10).setRewardFactor(1.5f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringMeat").setCollectItem(new ItemStack(Items.cooked_beef), 4, 8).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringMeat").setCollectItem(new ItemStack(Items.cooked_porkchop), 4, 8).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringMeat").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 4, 8).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("bringMeat").setCollectItem(new ItemStack(LOTRMod.kebab), 4, 8).setRewardFactor(2.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("lionSteak").setCollectItem(new ItemStack(LOTRMod.lionCooked), 2, 4).setRewardFactor(4.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarArmor").setCollectItem(new ItemStack(LOTRMod.helmetUmbar), 1, 1).setRewardFactor(15.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarArmor").setCollectItem(new ItemStack(LOTRMod.bodyUmbar), 1, 1).setRewardFactor(15.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarArmor").setCollectItem(new ItemStack(LOTRMod.legsUmbar), 1, 1).setRewardFactor(15.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarArmor").setCollectItem(new ItemStack(LOTRMod.bootsUmbar), 1, 1).setRewardFactor(15.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("defendCorsair").setCollectItem(new ItemStack(LOTRMod.scimitarNearHarad), 1, 3).setRewardFactor(5.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("defendCorsair").setCollectItem(new ItemStack(LOTRMod.spearNearHarad), 1, 3).setRewardFactor(5.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("defendCorsair").setCollectItem(new ItemStack(LOTRMod.maceNearHarad), 1, 3).setRewardFactor(5.0f));
        NEAR_HARAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGondor").setKillFaction(LOTRFaction.GONDOR, 20, 50));
        NEAR_HARAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killForUmbar").setKillFaction(LOTRFaction.GONDOR, 10, 40));
        NEAR_HARAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killRohirrim").setKillFaction(LOTRFaction.ROHAN, 10, 30));
        NEAR_HARAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRangers").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        NEAR_HARAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("greenDemons").setKillEntity(LOTREntityRangerIthilien.class, 10, 20));
        NEAR_HARAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorSoldiers").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        NEAR_HARAD.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        UMBAR.setAchievement(LOTRAchievement.doMiniquestNearHarad);
        UMBAR.setLore(LOTRLore.LoreCategory.UMBAR);
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBlackRock").setCollectItem(new ItemStack(LOTRMod.rock, 1, 0), 30, 50).setRewardFactor(0.5f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDates").setCollectItem(new ItemStack(LOTRMod.date), 8, 15).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lemon), 4, 12).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.orange), 4, 12).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lime), 4, 12).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.plum), 4, 12).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("orangeJuice").setCollectItem(new ItemStack(LOTRMod.mugOrangeJuice), 2, 6).setRewardFactor(4.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("lemonLiqueur").setCollectItem(new ItemStack(LOTRMod.mugLemonLiqueur), 2, 6).setRewardFactor(4.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPoison").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("gulfSword").setCollectItem(new ItemStack(LOTRMod.swordGulfHarad), 1, 1).setRewardFactor(5.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("ringTax").setCollectItem(new ItemStack(LOTRMod.goldRing), 2, 5).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("ringTax").setCollectItem(new ItemStack(LOTRMod.silverRing), 2, 5).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("saveFromVenom").setCollectItem(new ItemStack(LOTRMod.pearl), 1, 1).setRewardFactor(20.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("bringReedsRoof").setCollectItem(new ItemStack(LOTRMod.driedReeds), 10, 20).setRewardFactor(0.5f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("bringReedsRoof").setCollectItem(new ItemStack(LOTRMod.thatch, 1, 1), 10, 20).setRewardFactor(0.5f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("bringReedsRoof").setCollectItem(new ItemStack(LOTRMod.slabSingleThatch, 1, 1), 20, 40).setRewardFactor(0.25f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("cedarWood").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 2), 30, 60).setRewardFactor(0.25f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("cedarWood").setCollectItem(new ItemStack(LOTRMod.planks2, 1, 2), 60, 120).setRewardFactor(0.125f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(Blocks.sandstone, 1, 0), 30, 80).setRewardFactor(0.25f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(Blocks.stone, 1, 0), 30, 80).setRewardFactor(0.25f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(LOTRMod.brick, 1, 15), 30, 60).setRewardFactor(0.5f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("repairWall").setCollectItem(new ItemStack(LOTRMod.brick6, 1, 6), 30, 60).setRewardFactor(0.5f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("lionSteak").setCollectItem(new ItemStack(LOTRMod.lionCooked), 2, 4).setRewardFactor(4.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("defendCorsair").setCollectItem(new ItemStack(LOTRMod.scimitarNearHarad), 1, 3).setRewardFactor(5.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("defendCorsair").setCollectItem(new ItemStack(LOTRMod.spearNearHarad), 1, 3).setRewardFactor(5.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("defendCorsair").setCollectItem(new ItemStack(LOTRMod.maceNearHarad), 1, 3).setRewardFactor(5.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(LOTRMod.swordGondor), 1, 2).setRewardFactor(8.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(LOTRMod.helmetGondor), 1, 2).setRewardFactor(8.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(LOTRMod.helmetGondorWinged), 1, 1).setRewardFactor(20.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(LOTRMod.bodyGondor), 1, 2).setRewardFactor(8.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(LOTRMod.swordArnor), 1, 1).setRewardFactor(40.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(LOTRMod.helmetArnor), 1, 1).setRewardFactor(40.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(Items.iron_ingot), 4, 8).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(Items.gold_ingot), 3, 6).setRewardFactor(4.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarCraft").setCollectItem(new ItemStack(Items.lava_bucket), 2, 4).setRewardFactor(5.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("travelSupplies").setCollectItem(new ItemStack(LOTRMod.kebab), 4, 8).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("travelSupplies").setCollectItem(new ItemStack(Items.cooked_beef), 4, 8).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("travelSupplies").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 4, 8).setRewardFactor(2.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("travelSupplies").setCollectItem(new ItemStack(LOTRMod.mugAraq), 3, 5).setRewardFactor(4.0f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("travelSupplies").setCollectItem(new ItemStack(LOTRMod.waterskin), 8, 20).setRewardFactor(0.75f));
        UMBAR.addQuest(new LOTRMiniQuestCollect.QFCollect("findOldDagger").setCollectItem(new ItemStack(LOTRMod.daggerAncientHarad), 1, 2).setRewardFactor(20.0f));
        UMBAR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGondor").setKillFaction(LOTRFaction.GONDOR, 10, 30));
        UMBAR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("revengeGondor").setKillFaction(LOTRFaction.GONDOR, 20, 40));
        UMBAR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killForAnadune").setKillEntity(LOTREntityGondorSoldier.class, 20, 50));
        UMBAR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRangers").setKillEntity(LOTREntityRangerIthilien.class, 10, 40));
        UMBAR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSwanKnights").setKillEntity(LOTREntitySwanKnight.class, 10, 30));
        UMBAR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        CORSAIR.setAchievement(LOTRAchievement.doMiniquestNearHarad);
        CORSAIR.setLore(LOTRLore.LoreCategory.UMBAR);
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("whipMaterial").setCollectItem(new ItemStack(Items.string), 5, 12).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("whipMaterial").setCollectItem(new ItemStack(LOTRMod.rope), 5, 12).setRewardFactor(1.1f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("whipMaterial").setCollectItem(new ItemStack(Items.leather), 10, 20).setRewardFactor(0.75f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("scurvy").setCollectItem(new ItemStack(LOTRMod.orange), 6, 12).setRewardFactor(1.75f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("scurvy").setCollectItem(new ItemStack(LOTRMod.lemon), 6, 12).setRewardFactor(1.75f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("scurvy").setCollectItem(new ItemStack(LOTRMod.lemon), 6, 12).setRewardFactor(1.75f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugAraq), 4, 10).setRewardFactor(2.5f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugRum), 4, 10).setRewardFactor(2.5f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCactusLiqueur), 4, 10).setRewardFactor(2.5f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCarrotWine), 4, 10).setRewardFactor(2.5f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugBananaBeer), 4, 10).setRewardFactor(2.5f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugCornLiquor), 4, 10).setRewardFactor(2.5f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectChests").setCollectItem(new ItemStack((Block)Blocks.chest), 8, 16).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectChests").setCollectItem(new ItemStack(LOTRMod.chestBasket), 5, 10).setRewardFactor(2.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("collectChests").setCollectItem(new ItemStack(LOTRMod.pouch, 1, 0), 3, 5).setRewardFactor(5.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("poisonCaptain").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixSails").setCollectItem(new ItemStack(Items.string), 5, 12).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixSails").setCollectItem(new ItemStack(LOTRMod.rope), 5, 12).setRewardFactor(1.1f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixSails").setCollectItem(new ItemStack(Blocks.wool, 1, 15), 6, 15).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixSails").setCollectItem(new ItemStack(Blocks.wool, 1, 14), 6, 15).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixSails").setCollectItem(new ItemStack(Blocks.wool, 1, 12), 6, 15).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixSails").setCollectItem(new ItemStack(Blocks.wool, 1, 0), 6, 15).setRewardFactor(1.0f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixShip").setCollectItem(new ItemStack(LOTRMod.planks2, 1, 2), 60, 120).setRewardFactor(0.2f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixShip").setCollectItem(new ItemStack(LOTRMod.planks3, 1, 3), 60, 120).setRewardFactor(0.2f));
        CORSAIR.addQuest(new LOTRMiniQuestCollect.QFCollect("fixShip").setCollectItem(new ItemStack(LOTRMod.planks2, 1, 11), 60, 120).setRewardFactor(0.2f));
        CORSAIR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondor").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        CORSAIR.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRangers").setKillEntity(LOTREntityRangerIthilien.class, 10, 20).setRewardFactor(1.5f));
        CORSAIR.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killTaurethrim").setKillFaction(LOTRFaction.TAURETHRIM, 10, 30));
        CORSAIR.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        GONDOR_RENEGADE.setAchievement(LOTRAchievement.doMiniquestGondorRenegade);
        GONDOR_RENEGADE.setLore(LOTRLore.LoreCategory.UMBAR);
        GONDOR_RENEGADE.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorSoldiers").setKillEntity(LOTREntityGondorSoldier.class, 3, 8));
        for (List<LOTRMiniQuest.QuestFactoryBase> qfList : LOTRMiniQuestFactory.GONDOR_RENEGADE.questFactories.values()) {
            for (LOTRMiniQuest.QuestFactoryBase qf : qfList) {
                qf.setRewardFactor(0.0f);
                qf.setHiring(50.0f);
            }
        }
        ANGBAND.setAchievement(LOTRAchievement.doMiniquestAngband);
        ANGBAND.setLore(LOTRLore.LoreCategory.UTUMNO);
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.scimitarOrc), 1, 5).setRewardFactor(3.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeAngband), 1, 5).setRewardFactor(3.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.axeAngband), 1, 5).setRewardFactor(3.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerAngband), 1, 6).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetAngband), 2, 5).setRewardFactor(3.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyAngband), 2, 5).setRewardFactor(4.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.legsAngband), 2, 5).setRewardFactor(3.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bootsAngband), 2, 5).setRewardFactor(3.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.urukSteel), 3, 10).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.nauriteGem), 4, 12).setRewardFactor(2.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.guldurilCrystal), 3, 6).setRewardFactor(4.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        ANGBAND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        ANGBAND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.ROHAN, 10, 40));
        ANGBAND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.GONDOR, 10, 40));
        ANGBAND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.RED_MOUNTAINS, 10, 40));
        ANGBAND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.DURINS_FOLK, 10, 40));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMen").setKillEntity(LOTREntityRohirrimWarrior.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMen").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityBlacklockWarrior.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityDwarfWarrior.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityGaladhrimElf.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityWoodElf.class, 10, 30));
        ANGBAND.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        REDMOUNTAINS.setAchievement(LOTRAchievement.doMiniquestRedDwarf);
        REDMOUNTAINS.setLore(LOTRLore.LoreCategory.RED_MOUNTAINS);
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("mineMithril").setCollectItem(new ItemStack(LOTRMod.mithril), 1, 6).setRewardFactor(100.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 15).setRewardFactor(4.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.goldRaw), 3, 15).setRewardFactor(5.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 15).setRewardFactor(4.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.glowstone_dust), 5, 15).setRewardFactor(2.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.topaz), 1, 6).setRewardFactor(8.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.sapphire), 1, 3).setRewardFactor(10.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amber), 1, 3).setRewardFactor(8.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amethyst), 1, 6).setRewardFactor(8.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.hammerRed), 1, 3).setRewardFactor(5.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeRed), 1, 3).setRewardFactor(5.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.throwingAxeRed), 1, 4).setRewardFactor(4.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.axeRed), 1, 4).setRewardFactor(4.5f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.swordRed), 1, 4).setRewardFactor(4.5f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.pikeRed), 1, 4).setRewardFactor(4.5f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.spearRed), 1, 4).setRewardFactor(4.5f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugKhamBrew), 2, 5).setRewardFactor(5.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugDwarvenAle), 2, 5).setRewardFactor(3.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugBlueDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugRedDwarvenTonic), 2, 5).setRewardFactor(3.0f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 20, 50));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDurmethOrc.class, 10, 40));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityDurmethWarg.class, 10, 40));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSpider").setKillEntity(LOTREntityCaveSpider.class, 10, 40).setRewardFactor(1.2f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDesertSpider").setKillEntity(LOTREntityDesertSpider.class, 10, 40).setRewardFactor(1.2f));
        REDMOUNTAINS.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        WIND.setAchievement(LOTRAchievement.doMiniquestWind);
        WIND.setLore(LOTRLore.LoreCategory.WIND_MOUNTAINS);
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("mineMithril").setCollectItem(new ItemStack(LOTRMod.mithril), 1, 6).setRewardFactor(100.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 15).setRewardFactor(4.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.goldRaw), 3, 15).setRewardFactor(5.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 15).setRewardFactor(4.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.glowstone_dust), 5, 15).setRewardFactor(2.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.emerald), 1, 3).setRewardFactor(12.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.opal), 1, 3).setRewardFactor(10.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.topaz), 1, 6).setRewardFactor(8.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.sapphire), 1, 3).setRewardFactor(10.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amber), 1, 3).setRewardFactor(8.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.amethyst), 1, 6).setRewardFactor(8.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.hammerDwarven), 1, 3).setRewardFactor(5.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.battleaxeDwarven), 1, 3).setRewardFactor(5.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.throwingAxeDwarven), 1, 4).setRewardFactor(4.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.axeDwarven), 1, 4).setRewardFactor(4.5f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.swordDwarven), 1, 4).setRewardFactor(4.5f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.pikeDwarven), 1, 4).setRewardFactor(4.5f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("forgeDwarfWeapon").setCollectItem(new ItemStack(LOTRMod.spearDwarven), 1, 4).setRewardFactor(4.5f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugKhamBrew), 2, 5).setRewardFactor(5.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugDwarvenAle), 2, 5).setRewardFactor(3.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugBlueDwarvenTonic), 2, 5).setRewardFactor(5.0f));
        WIND.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDrink").setCollectItem(new ItemStack(LOTRMod.mugDwarvenTonic), 2, 5).setRewardFactor(5.0f));
        WIND.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGundabad").setKillFaction(LOTRFaction.GUNDABAD, 20, 50));
        WIND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityGundabadOrc.class, 10, 30));
        WIND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityGundabadWarg.class, 10, 30));
        WIND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killOrc").setKillEntity(LOTREntityDurmethOrc.class, 10, 40));
        WIND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killWarg").setKillEntity(LOTREntityDurmethWarg.class, 10, 40));
        WIND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killSpider").setKillEntity(LOTREntityCaveSpider.class, 10, 40).setRewardFactor(1.2f));
        WIND.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDesertSpider").setKillEntity(LOTREntityCaveSpider.class, 10, 40).setRewardFactor(1.2f));
        WIND.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        DURMETH.setAchievement(LOTRAchievement.doMiniquestDurmeth);
        DURMETH.setLore(LOTRLore.LoreCategory.DURMETH);
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(Items.iron_sword), 1, 5).setRewardFactor(3.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapon").setCollectItem(new ItemStack(LOTRMod.daggerIron), 1, 6).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_porkchop), 2, 8).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.cooked_beef), 2, 8).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.muttonCooked), 2, 8).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.deerCooked), 2, 8).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bone), 2, 8).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_helmet), 2, 5).setRewardFactor(3.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack((Item)Items.iron_chestplate), 2, 5).setRewardFactor(4.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.helmetBronze), 2, 5).setRewardFactor(3.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectArmour").setCollectItem(new ItemStack(LOTRMod.bodyBronze), 2, 5).setRewardFactor(4.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.iron_ingot), 3, 10).setRewardFactor(2.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(Items.gold_ingot), 3, 8).setRewardFactor(4.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.silver), 3, 8).setRewardFactor(4.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.diamond), 1, 3).setRewardFactor(15.0f));
        DURMETH.addQuest(new LOTRMiniQuestCollect.QFCollect("collectMineral").setCollectItem(new ItemStack(LOTRMod.ruby), 1, 3).setRewardFactor(12.0f));
        DURMETH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.DURINS_FOLK, 10, 40).setRewardFactor(1.0f));
        DURMETH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.RED_MOUNTAINS, 10, 60).setRewardFactor(1.0f));
        DURMETH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.AVARI, 10, 40).setRewardFactor(1.0f));
        DURMETH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.HIGH_ELF, 10, 40).setRewardFactor(1.0f));
        DURMETH.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killEnemy").setKillFaction(LOTRFaction.LOTHLORIEN, 10, 40).setRewardFactor(1.0f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerNorth.class, 10, 30));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityHighElf.class, 10, 30).setRewardFactor(1.6f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityRivendellElf.class, 10, 30).setRewardFactor(1.6f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityGaladhrimElf.class, 10, 30).setRewardFactor(1.6f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killElf").setKillEntity(LOTREntityAvariElf.class, 10, 40).setRewardFactor(1.5f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityStiffbeard.class, 10, 45).setRewardFactor(1.5f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityIronfist.class, 10, 50).setRewardFactor(1.5f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityBlacklock.class, 10, 42).setRewardFactor(1.5f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityStonefoot.class, 10, 40).setRewardFactor(1.5f));
        DURMETH.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killDwarf").setKillEntity(LOTREntityDwarf.class, 10, 40).setRewardFactor(1.6f));
        DURMETH.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        NOMAD.setAchievement(LOTRAchievement.doMiniquestNearHarad);
        NOMAD.setLore(LOTRLore.LoreCategory.NOMAD);
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDates").setCollectItem(new ItemStack(LOTRMod.date), 8, 15).setRewardFactor(2.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lemon), 4, 12).setRewardFactor(2.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.orange), 4, 12).setRewardFactor(2.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.lime), 4, 12).setRewardFactor(2.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFruit").setCollectItem(new ItemStack(LOTRMod.plum), 4, 12).setRewardFactor(2.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("orangeJuice").setCollectItem(new ItemStack(LOTRMod.mugOrangeJuice), 2, 6).setRewardFactor(4.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("lemonLiqueur").setCollectItem(new ItemStack(LOTRMod.mugLemonLiqueur), 2, 6).setRewardFactor(4.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPoison").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 14), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 4), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 13), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 11), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 10), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 5), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 4), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 3), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("camelCarpets").setCollectItem(new ItemStack(Blocks.carpet, 1), 8, 15).setRewardFactor(0.75f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("waterskins").setCollectItem(new ItemStack(LOTRMod.waterskin), 8, 16).setRewardFactor(1.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("gulfEquipment").setCollectItem(new ItemStack(LOTRMod.swordGulfHarad), 1, 2).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("gulfEquipment").setCollectItem(new ItemStack(LOTRMod.helmetGulfHarad), 1, 2).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("gulfEquipment").setCollectItem(new ItemStack(LOTRMod.bodyGulfHarad), 1, 2).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarEquipment").setCollectItem(new ItemStack(LOTRMod.scimitarNearHarad), 1, 2).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarEquipment").setCollectItem(new ItemStack(LOTRMod.helmetUmbar), 1, 2).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestCollect.QFCollect("umbarEquipment").setCollectItem(new ItemStack(LOTRMod.bodyUmbar), 1, 2).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killScorpions").setKillEntity(LOTREntityDesertScorpion.class, 10, 30));
        NOMAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killManyScorpions").setKillEntity(LOTREntityDesertScorpion.class, 40, 80));
        NOMAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killBandits").setKillEntity(LOTREntityBanditHarad.class, 1, 3).setRewardFactor(8.0f));
        NOMAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRangers").setKillEntity(LOTREntityRangerIthilien.class, 10, 20));
        NOMAD.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        GULF_HARAD.setAchievement(LOTRAchievement.doMiniquestNearHarad);
        GULF_HARAD.setLore(LOTRLore.LoreCategory.GULF);
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDates").setCollectItem(new ItemStack(LOTRMod.date), 8, 15).setRewardFactor(2.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("orangeJuice").setCollectItem(new ItemStack(LOTRMod.mugOrangeJuice), 2, 6).setRewardFactor(4.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("lemonLiqueur").setCollectItem(new ItemStack(LOTRMod.mugLemonLiqueur), 2, 6).setRewardFactor(4.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectPoison").setCollectItem(new ItemStack(LOTRMod.bottlePoison), 2, 4).setRewardFactor(5.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectIronWeapon").setCollectItem(new ItemStack(LOTRMod.scimitarNearHarad), 2, 3).setRewardFactor(5.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectIronWeapon").setCollectItem(new ItemStack(LOTRMod.spearNearHarad), 2, 3).setRewardFactor(4.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectIronWeapon").setCollectItem(new ItemStack(LOTRMod.poleaxeNearHarad), 2, 3).setRewardFactor(6.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectIronWeapon").setCollectItem(new ItemStack(LOTRMod.maceNearHarad), 2, 3).setRewardFactor(6.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("findOldDagger").setCollectItem(new ItemStack(LOTRMod.daggerAncientHarad), 1, 2).setRewardFactor(20.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("morwaithStuff").setCollectItem(new ItemStack(LOTRMod.helmetMoredain), 1, 1).setRewardFactor(8.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("morwaithStuff").setCollectItem(new ItemStack(LOTRMod.bodyMoredain), 1, 1).setRewardFactor(8.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("morwaithStuff").setCollectItem(new ItemStack(LOTRMod.legsMoredain), 1, 1).setRewardFactor(8.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("morwaithStuff").setCollectItem(new ItemStack(LOTRMod.bootsMoredain), 1, 1).setRewardFactor(8.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("easterlingStuff").setCollectItem(new ItemStack(LOTRMod.helmetRhunGold), 1, 1).setRewardFactor(20.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("easterlingStuff").setCollectItem(new ItemStack(LOTRMod.bodyRhunGold), 1, 1).setRewardFactor(20.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("easterlingStuff").setCollectItem(new ItemStack(LOTRMod.legsRhunGold), 1, 1).setRewardFactor(20.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("easterlingStuff").setCollectItem(new ItemStack(LOTRMod.bootsRhunGold), 1, 1).setRewardFactor(20.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBones").setCollectItem(new ItemStack(Items.bone), 10, 20).setRewardFactor(1.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(Blocks.sandstone, 1, 0), 30, 80).setRewardFactor(0.25f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.brick, 1, 15), 30, 60).setRewardFactor(0.5f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.brick3, 1, 13), 30, 60).setRewardFactor(0.5f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.thatch, 1, 1), 10, 20).setRewardFactor(0.5f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.wood8, 1, 3), 30, 60).setRewardFactor(0.25f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.planks3, 1, 3), 60, 120).setRewardFactor(0.125f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.wood9, 1, 0), 30, 60).setRewardFactor(0.25f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.planks3, 1, 4), 60, 120).setRewardFactor(0.125f));
        GULF_HARAD.addQuest(new LOTRMiniQuestCollect.QFCollect("templeBuild").setCollectItem(new ItemStack(LOTRMod.boneBlock, 1, 0), 5, 10).setRewardFactor(2.0f));
        GULF_HARAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killTaurethrim").setKillFaction(LOTRFaction.TAURETHRIM, 10, 30));
        GULF_HARAD.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGondor").setKillFaction(LOTRFaction.GONDOR, 20, 40));
        GULF_HARAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killScorpions").setKillEntity(LOTREntityDesertScorpion.class, 10, 30));
        GULF_HARAD.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRangers").setKillEntity(LOTREntityRangerIthilien.class, 20, 40));
        GULF_HARAD.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        MOREDAIN.setAchievement(LOTRAchievement.doMiniquestMoredain);
        MOREDAIN.setLore(LOTRLore.LoreCategory.FAR_HARAD);
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectLionFur").setCollectItem(new ItemStack(LOTRMod.lionFur), 3, 6).setRewardFactor(3.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.lionCooked), 4, 6).setRewardFactor(3.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.zebraCooked), 4, 6).setRewardFactor(2.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.rhinoCooked), 4, 6).setRewardFactor(3.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(Items.bread), 5, 8).setRewardFactor(2.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectFood").setCollectItem(new ItemStack(LOTRMod.yamRoast), 5, 8).setRewardFactor(2.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectHide").setCollectItem(new ItemStack(LOTRMod.gemsbokHide), 4, 12).setRewardFactor(2.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBananas").setCollectItem(new ItemStack(LOTRMod.banana), 4, 6).setRewardFactor(4.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.battleaxeMoredain), 1, 4).setRewardFactor(5.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.daggerMoredain), 1, 4).setRewardFactor(5.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.spearMoredain), 1, 4).setRewardFactor(5.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.swordMoredain), 1, 4).setRewardFactor(5.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.clubMoredain), 1, 4).setRewardFactor(5.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("huntRhino").setCollectItem(new ItemStack(LOTRMod.rhinoHorn), 1, 3).setRewardFactor(8.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDates").setCollectItem(new ItemStack(LOTRMod.date), 3, 5).setRewardFactor(4.0f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warResources").setCollectItem(new ItemStack(Blocks.log, 1, 0), 30, 60).setRewardFactor(0.3f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warResources").setCollectItem(new ItemStack(Blocks.log2, 1, 0), 30, 60).setRewardFactor(0.3f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warResources").setCollectItem(new ItemStack(LOTRMod.gemsbokHide), 6, 15).setRewardFactor(1.5f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warResources").setCollectItem(new ItemStack(Items.stick), 80, 200).setRewardFactor(0.05f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("wallMaterials").setCollectItem(new ItemStack(LOTRMod.brick3, 1, 10), 40, 60).setRewardFactor(0.2f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("wallMaterials").setCollectItem(new ItemStack(Blocks.hardened_clay), 20, 30).setRewardFactor(0.5f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("roofMaterials").setCollectItem(new ItemStack(LOTRMod.thatch), 10, 20).setRewardFactor(0.5f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("jungleWood").setCollectItem(new ItemStack(Blocks.log, 1, 3), 40, 80).setRewardFactor(0.25f));
        MOREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("jungleWood").setCollectItem(new ItemStack(LOTRMod.wood6, 1, 0), 40, 80).setRewardFactor(0.25f));
        MOREDAIN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGondor").setKillFaction(LOTRFaction.GONDOR, 20, 50));
        MOREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorSoldier").setKillEntity(LOTREntityGondorSoldier.class, 10, 30));
        MOREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        MOREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killCrocodile").setKillEntity(LOTREntityCrocodile.class, 10, 20));
        MOREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killLion").setKillEntity(LOTREntityLion.class, 10, 20));
        MOREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killLion").setKillEntity(LOTREntityLioness.class, 10, 20));
        MOREDAIN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killTauredain").setKillFaction(LOTRFaction.TAURETHRIM, 20, 50));
        MOREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killTauredainBlowgunner").setKillEntity(LOTREntityTauredainBlowgunner.class, 10, 30));
        MOREDAIN.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        TAUREDAIN.setAchievement(LOTRAchievement.doMiniquestTauredain);
        TAUREDAIN.setLore(LOTRLore.LoreCategory.FAR_HARAD_JUNGLE);
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.swordTauredain), 1, 4).setRewardFactor(5.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.daggerTauredain), 1, 4).setRewardFactor(4.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.daggerTauredainPoisoned), 1, 3).setRewardFactor(6.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.axeTauredain), 1, 4).setRewardFactor(5.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.spearTauredain), 1, 4).setRewardFactor(5.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.hammerTauredain), 1, 4).setRewardFactor(5.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.battleaxeTauredain), 1, 4).setRewardFactor(5.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWeapons").setCollectItem(new ItemStack(LOTRMod.pikeTauredain), 1, 4).setRewardFactor(5.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectObsidian").setCollectItem(new ItemStack(LOTRMod.obsidianShard), 10, 30).setRewardFactor(0.75f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectCocoa").setCollectItem(new ItemStack(Items.dye, 1, 3), 8, 20).setRewardFactor(1.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(Items.bread), 5, 8).setRewardFactor(2.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.bananaBread), 5, 8).setRewardFactor(2.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.cornBread), 5, 8).setRewardFactor(2.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.banana), 4, 6).setRewardFactor(4.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.mango), 4, 6).setRewardFactor(4.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(Items.melon), 10, 20).setRewardFactor(0.75f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.melonSoup), 3, 8).setRewardFactor(2.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.corn), 6, 12).setRewardFactor(1.5f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("warriorFood").setCollectItem(new ItemStack(LOTRMod.cornCooked), 5, 10).setRewardFactor(2.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDarts").setCollectItem(new ItemStack(LOTRMod.tauredainDart), 20, 40).setRewardFactor(0.5f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectDarts").setCollectItem(new ItemStack(LOTRMod.tauredainDartPoisoned), 10, 20).setRewardFactor(1.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("collectBanners").setCollectItem(new ItemStack(LOTRMod.banner, 1, LOTRItemBanner.BannerType.TAUREDAIN.bannerID), 5, 15).setRewardFactor(1.5f));
        TAUREDAIN.addQuest(new LOTRMiniQuestCollect.QFCollect("amulet").setCollectItem(new ItemStack(LOTRMod.tauredainAmulet), 1, 4).setRewardFactor(20.0f));
        TAUREDAIN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killMoredain").setKillFaction(LOTRFaction.MORWAITH, 20, 50));
        TAUREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killMoredainWarrior").setKillEntity(LOTREntityMoredainWarrior.class, 10, 30));
        TAUREDAIN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killHalfTrolls").setKillFaction(LOTRFaction.HALF_TROLL, 10, 40));
        TAUREDAIN.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killNearHaradrim").setKillFaction(LOTRFaction.NEAR_HARAD, 20, 50));
        TAUREDAIN.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killNearHaradWarrior").setKillEntity(LOTREntityNearHaradrimWarrior.class, 10, 30));
        TAUREDAIN.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
        HALF_TROLL.setAchievement(LOTRAchievement.doMiniquestHalfTroll);
        HALF_TROLL.setLore(LOTRLore.LoreCategory.HALF_TROLL);
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.scimitarHalfTroll), 2, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.maceHalfTroll), 2, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.pikeHalfTroll), 2, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.daggerHalfTroll), 2, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.battleaxeHalfTroll), 2, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.helmetHalfTroll), 1, 4).setRewardFactor(4.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.bodyHalfTroll), 1, 4).setRewardFactor(5.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.legsHalfTroll), 1, 4).setRewardFactor(4.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectEquipment").setCollectItem(new ItemStack(LOTRMod.bootsHalfTroll), 1, 4).setRewardFactor(4.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("flesh").setCollectItem(new ItemStack(LOTRMod.lionRaw), 2, 6).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("flesh").setCollectItem(new ItemStack(LOTRMod.zebraRaw), 2, 6).setRewardFactor(2.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("flesh").setCollectItem(new ItemStack(LOTRMod.rhinoRaw), 2, 6).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("flesh").setCollectItem(new ItemStack(Items.rotten_flesh), 3, 8).setRewardFactor(2.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(Blocks.log, 1, 0), 30, 60).setRewardFactor(0.3f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(Blocks.log2, 1, 0), 30, 60).setRewardFactor(0.3f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("collectWood").setCollectItem(new ItemStack(LOTRMod.wood4, 1, 1), 20, 40).setRewardFactor(0.5f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("manTrophy").setCollectItem(new ItemStack(LOTRMod.swordGondor), 1, 1).setRewardFactor(20.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("manTrophy").setCollectItem(new ItemStack(LOTRMod.gondorBow), 1, 1).setRewardFactor(20.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("manTrophy").setCollectItem(new ItemStack(LOTRMod.beacon), 1, 1).setRewardFactor(20.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("resources").setCollectItem(new ItemStack(Blocks.log, 1, 0), 30, 80).setRewardFactor(0.25f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("resources").setCollectItem(new ItemStack(Items.coal), 10, 30).setRewardFactor(0.5f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("resources").setCollectItem(new ItemStack(Blocks.cobblestone), 30, 80).setRewardFactor(0.25f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("resources").setCollectItem(new ItemStack(LOTRMod.gemsbokHide), 10, 30).setRewardFactor(0.5f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("huntItems").setCollectItem(new ItemStack(LOTRMod.lionRaw), 4, 8).setRewardFactor(2.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("huntItems").setCollectItem(new ItemStack(LOTRMod.rhinoHorn), 3, 6).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("huntItems").setCollectItem(new ItemStack(LOTRMod.gemsbokHide), 4, 10).setRewardFactor(2.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("huntItems").setCollectItem(new ItemStack(LOTRMod.gemsbokHorn), 3, 6).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("mordorItems").setCollectItem(new ItemStack(LOTRMod.orcSteel), 4, 8).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("mordorItems").setCollectItem(new ItemStack(LOTRMod.scimitarOrc), 3, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("mordorItems").setCollectItem(new ItemStack(LOTRMod.battleaxeOrc), 3, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("mordorItems").setCollectItem(new ItemStack(LOTRMod.hammerOrc), 3, 5).setRewardFactor(3.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("tribeItem").setCollectItem(new ItemStack(LOTRMod.swordTauredain), 1, 1).setRewardFactor(20.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestCollect.QFCollect("tribeItem").setCollectItem(new ItemStack(LOTRMod.daggerTauredain), 1, 1).setRewardFactor(20.0f));
        HALF_TROLL.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killGondor").setKillFaction(LOTRFaction.GONDOR, 20, 50));
        HALF_TROLL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killGondorSoldier").setKillEntity(LOTREntityGondorSoldier.class, 20, 40));
        HALF_TROLL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRanger").setKillEntity(LOTREntityRangerIthilien.class, 10, 30));
        HALF_TROLL.addQuest(new LOTRMiniQuestKillEntity.QFKillEntity("killRohirrim").setKillEntity(LOTREntityRohirrimWarrior.class, 10, 30));
        HALF_TROLL.addQuest(new LOTRMiniQuestKillFaction.QFKillFaction("killTauredain").setKillFaction(LOTRFaction.TAURETHRIM, 20, 50));
        HALF_TROLL.addQuest(new LOTRMiniQuestBounty.QFBounty("bounty"));
    }

    public static LOTRMiniQuestFactory forName(String name) {
        for (LOTRMiniQuestFactory group : LOTRMiniQuestFactory.values()) {
            if (!group.getBaseName().equals(name)) continue;
            return group;
        }
        return null;
    }

    static {
        rand = new Random();
        questClassWeights = new HashMap<Class<? extends LOTRMiniQuest>, Integer>();
    }
}

