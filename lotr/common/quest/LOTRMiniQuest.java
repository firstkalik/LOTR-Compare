/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  org.apache.commons.lang3.tuple.Pair
 */
package lotr.common.quest;

import cpw.mods.fml.common.FMLLog;
import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRDate;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLore;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.npc.LOTREntityAngbandBerserk;
import lotr.common.entity.npc.LOTREntityAngbandOrc;
import lotr.common.entity.npc.LOTREntityDolGuldurUruk;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityEreborDwarf;
import lotr.common.entity.npc.LOTREntityGaladhrimElf;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityQuestInfo;
import lotr.common.entity.npc.LOTREntityRivendellElf;
import lotr.common.entity.npc.LOTREntityTauredain;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRSpeech;
import lotr.common.entity.npc.LOTRUnitTradeEntry;
import lotr.common.fac.LOTRAlignmentBonusMap;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemCoin;
import lotr.common.item.LOTRItemModifierTemplate;
import lotr.common.quest.LOTRMiniQuestBounty;
import lotr.common.quest.LOTRMiniQuestCollect;
import lotr.common.quest.LOTRMiniQuestEvent;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.quest.LOTRMiniQuestKillEntity;
import lotr.common.quest.LOTRMiniQuestKillFaction;
import lotr.common.quest.LOTRMiniQuestPickpocket;
import lotr.common.quest.LOTRMiniQuestWelcome;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.tuple.Pair;

public abstract class LOTRMiniQuest {
    private static Map<String, Class<? extends LOTRMiniQuest>> nameToQuestMapping = new HashMap<String, Class<? extends LOTRMiniQuest>>();
    private static Map<Class<? extends LOTRMiniQuest>, String> questToNameMapping = new HashMap<Class<? extends LOTRMiniQuest>, String>();
    public static int MAX_MINIQUESTS_PER_FACTION;
    public static double RENDER_HEAD_DISTANCE;
    public LOTRMiniQuestFactory questGroup;
    private LOTRPlayerData playerData;
    public UUID questUUID;
    public UUID entityUUID;
    public String entityName;
    public String entityNameFull;
    public LOTRFaction entityFaction;
    private int questColor;
    public int dateGiven;
    public LOTRBiome biomeGiven;
    public float rewardFactor = 1.0f;
    public static final float defaultRewardFactor = 1.0f;
    public boolean willHire = false;
    public float hiringAlignment;
    public List<ItemStack> rewardItemTable = new ArrayList<ItemStack>();
    private boolean completed;
    public int dateCompleted;
    public int coinsRewarded;
    public float alignmentRewarded;
    public boolean wasHired;
    public List<ItemStack> itemsRewarded = new ArrayList<ItemStack>();
    private boolean entityDead;
    private Pair<ChunkCoordinates, Integer> lastLocation;
    public String speechBankStart;
    public String speechBankProgress;
    public String speechBankComplete;
    public String speechBankTooMany;
    public String quoteStart;
    public String quoteComplete;
    public List<String> quotesStages = new ArrayList<String>();

    private static void registerQuestType(String name, Class<? extends LOTRMiniQuest> questType) {
        nameToQuestMapping.put(name, questType);
        questToNameMapping.put(questType, name);
    }

    public LOTRMiniQuest(LOTRPlayerData pd) {
        this.playerData = pd;
        this.questUUID = UUID.randomUUID();
    }

    public void setPlayerData(LOTRPlayerData pd) {
        this.playerData = pd;
    }

    public LOTRPlayerData getPlayerData() {
        return this.playerData;
    }

    public void setNPCInfo(LOTREntityNPC npc) {
        this.entityUUID = npc.getUniqueID();
        this.entityName = npc.getNPCName();
        this.entityNameFull = npc.getCommandSenderName();
        this.entityFaction = npc.getFaction();
        this.questColor = npc.getMiniquestColor();
    }

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound itemData;
        NBTTagList itemTags;
        nbt.setString("QuestType", questToNameMapping.get(this.getClass()));
        if (this.questGroup != null) {
            nbt.setString("QuestGroup", this.questGroup.getBaseName());
        }
        nbt.setString("QuestUUID", this.questUUID.toString());
        nbt.setString("EntityUUID", this.entityUUID.toString());
        nbt.setString("Owner", this.entityName);
        nbt.setString("OwnerFull", this.entityNameFull);
        nbt.setString("Faction", this.entityFaction.codeName());
        nbt.setInteger("Color", this.questColor);
        nbt.setInteger("DateGiven", this.dateGiven);
        if (this.biomeGiven != null) {
            nbt.setByte("BiomeID", (byte)this.biomeGiven.biomeID);
            nbt.setString("BiomeDim", this.biomeGiven.biomeDimension.dimensionName);
        }
        nbt.setFloat("RewardFactor", this.rewardFactor);
        nbt.setBoolean("WillHire", this.willHire);
        nbt.setFloat("HiringAlignF", this.hiringAlignment);
        if (!this.rewardItemTable.isEmpty()) {
            itemTags = new NBTTagList();
            for (ItemStack item : this.rewardItemTable) {
                itemData = new NBTTagCompound();
                item.writeToNBT(itemData);
                itemTags.appendTag((NBTBase)itemData);
            }
            nbt.setTag("RewardItemTable", (NBTBase)itemTags);
        }
        nbt.setBoolean("Completed", this.completed);
        nbt.setInteger("DateCompleted", this.dateCompleted);
        nbt.setShort("CoinReward", (short)this.coinsRewarded);
        nbt.setFloat("AlignRewardF", this.alignmentRewarded);
        nbt.setBoolean("WasHired", this.wasHired);
        if (!this.itemsRewarded.isEmpty()) {
            itemTags = new NBTTagList();
            for (ItemStack item : this.itemsRewarded) {
                itemData = new NBTTagCompound();
                item.writeToNBT(itemData);
                itemTags.appendTag((NBTBase)itemData);
            }
            nbt.setTag("ItemRewards", (NBTBase)itemTags);
        }
        nbt.setBoolean("OwnerDead", this.entityDead);
        if (this.lastLocation != null) {
            ChunkCoordinates coords = (ChunkCoordinates)this.lastLocation.getLeft();
            nbt.setInteger("XPos", coords.posX);
            nbt.setInteger("YPos", coords.posY);
            nbt.setInteger("ZPos", coords.posZ);
            nbt.setInteger("Dimension", ((Integer)this.lastLocation.getRight()).intValue());
        }
        nbt.setString("SpeechStart", this.speechBankStart);
        nbt.setString("SpeechProgress", this.speechBankProgress);
        nbt.setString("SpeechComplete", this.speechBankComplete);
        nbt.setString("SpeechTooMany", this.speechBankTooMany);
        nbt.setString("QuoteStart", this.quoteStart);
        nbt.setString("QuoteComplete", this.quoteComplete);
        if (!this.quotesStages.isEmpty()) {
            NBTTagList stageTags = new NBTTagList();
            for (String s : this.quotesStages) {
                stageTags.appendTag((NBTBase)new NBTTagString(s));
            }
            nbt.setTag("QuotesStages", (NBTBase)stageTags);
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        int l;
        ItemStack item;
        String recovery;
        NBTTagList itemTags;
        UUID u;
        NBTTagCompound itemData;
        LOTRMiniQuestFactory factory;
        if (nbt.hasKey("QuestGroup") && (factory = LOTRMiniQuestFactory.forName(nbt.getString("QuestGroup"))) != null) {
            this.questGroup = factory;
        }
        if (nbt.hasKey("QuestUUID") && (u = UUID.fromString(nbt.getString("QuestUUID"))) != null) {
            this.questUUID = u;
        }
        this.entityUUID = nbt.hasKey("UUIDMost") && nbt.hasKey("UUIDLeast") ? new UUID(nbt.getLong("UUIDMost"), nbt.getLong("UUIDLeast")) : UUID.fromString(nbt.getString("EntityUUID"));
        this.entityName = nbt.getString("Owner");
        this.entityNameFull = nbt.hasKey("OwnerFull") ? nbt.getString("OwnerFull") : this.entityName;
        this.entityFaction = LOTRFaction.forName(nbt.getString("Faction"));
        this.questColor = nbt.hasKey("Color") ? nbt.getInteger("Color") : this.entityFaction.getFactionColor();
        this.dateGiven = nbt.getInteger("DateGiven");
        if (nbt.hasKey("BiomeID")) {
            int biomeID = nbt.getByte("BiomeID") & 0xFF;
            String biomeDimName = nbt.getString("BiomeDim");
            LOTRDimension biomeDim = LOTRDimension.forName(biomeDimName);
            if (biomeDim != null) {
                this.biomeGiven = biomeDim.biomeList[biomeID];
            }
        }
        this.rewardFactor = nbt.hasKey("RewardFactor") ? nbt.getFloat("RewardFactor") : 1.0f;
        this.willHire = nbt.getBoolean("WillHire");
        this.hiringAlignment = nbt.hasKey("HiringAlignment") ? (float)nbt.getInteger("HiringAlignment") : nbt.getFloat("HiringAlignF");
        this.rewardItemTable.clear();
        if (nbt.hasKey("RewardItemTable")) {
            itemTags = nbt.getTagList("RewardItemTable", 10);
            for (l = 0; l < itemTags.tagCount(); ++l) {
                itemData = itemTags.getCompoundTagAt(l);
                item = ItemStack.loadItemStackFromNBT((NBTTagCompound)itemData);
                if (item == null) continue;
                this.rewardItemTable.add(item);
            }
        }
        this.completed = nbt.getBoolean("Completed");
        this.dateCompleted = nbt.getInteger("DateCompleted");
        this.coinsRewarded = nbt.getShort("CoinReward");
        this.alignmentRewarded = nbt.hasKey("AlignmentReward") ? (float)nbt.getShort("AlignmentReward") : nbt.getFloat("AlignRewardF");
        this.wasHired = nbt.getBoolean("WasHired");
        this.itemsRewarded.clear();
        if (nbt.hasKey("ItemRewards")) {
            itemTags = nbt.getTagList("ItemRewards", 10);
            for (l = 0; l < itemTags.tagCount(); ++l) {
                itemData = itemTags.getCompoundTagAt(l);
                item = ItemStack.loadItemStackFromNBT((NBTTagCompound)itemData);
                if (item == null) continue;
                this.itemsRewarded.add(item);
            }
        }
        this.entityDead = nbt.getBoolean("OwnerDead");
        if (nbt.hasKey("Dimension")) {
            ChunkCoordinates coords = new ChunkCoordinates(nbt.getInteger("XPos"), nbt.getInteger("YPos"), nbt.getInteger("ZPos"));
            int dimension = nbt.getInteger("Dimension");
            this.lastLocation = Pair.of((Object)coords, (Object)dimension);
        }
        this.speechBankStart = nbt.getString("SpeechStart");
        this.speechBankProgress = nbt.getString("SpeechProgress");
        this.speechBankComplete = nbt.getString("SpeechComplete");
        this.speechBankTooMany = nbt.getString("SpeechTooMany");
        this.quoteStart = nbt.getString("QuoteStart");
        this.quoteComplete = nbt.getString("QuoteComplete");
        this.quotesStages.clear();
        if (nbt.hasKey("QuotesStages")) {
            NBTTagList stageTags = nbt.getTagList("QuotesStages", 8);
            for (int l2 = 0; l2 < stageTags.tagCount(); ++l2) {
                String s = stageTags.getStringTagAt(l2);
                this.quotesStages.add(s);
            }
        }
        if (this.questGroup == null && (recovery = this.speechBankStart) != null) {
            LOTRMiniQuestFactory factory2;
            int i1 = recovery.indexOf("/", 0);
            int i2 = recovery.indexOf("/", i1 + 1);
            if (i1 >= 0 && i2 >= 0 && (factory2 = LOTRMiniQuestFactory.forName(recovery = recovery.substring(i1 + 1, i2))) != null) {
                this.questGroup = factory2;
            }
        }
    }

    public static LOTRMiniQuest loadQuestFromNBT(NBTTagCompound nbt, LOTRPlayerData playerData) {
        String questTypeName = nbt.getString("QuestType");
        Class<? extends LOTRMiniQuest> questType = nameToQuestMapping.get(questTypeName);
        if (questType == null) {
            FMLLog.severe((String)("Could not instantiate miniquest of type " + questTypeName), (Object[])new Object[0]);
            return null;
        }
        LOTRMiniQuest quest = LOTRMiniQuest.newQuestInstance(questType, playerData);
        quest.readFromNBT(nbt);
        if (quest.isValidQuest()) {
            return quest;
        }
        FMLLog.severe((String)("Loaded an invalid LOTR miniquest " + quest.speechBankStart), (Object[])new Object[0]);
        return null;
    }

    private static <Q extends LOTRMiniQuest> Q newQuestInstance(Class<Q> questType, LOTRPlayerData playerData) {
        try {
            LOTRMiniQuest quest = (LOTRMiniQuest)questType.getConstructor(LOTRPlayerData.class).newInstance(playerData);
            return (Q)quest;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isValidQuest() {
        return this.entityUUID != null && this.entityFaction != null;
    }

    public boolean canPlayerAccept(EntityPlayer entityplayer) {
        return true;
    }

    public String getFactionSubtitle() {
        if (this.entityFaction.isPlayableAlignmentFaction()) {
            return this.entityFaction.factionName();
        }
        return "";
    }

    public final int getQuestColor() {
        return this.questColor;
    }

    public final float[] getQuestColorComponents() {
        return new Color(this.getQuestColor()).getColorComponents(null);
    }

    public abstract String getQuestObjective();

    public abstract String getObjectiveInSpeech();

    public abstract String getProgressedObjectiveInSpeech();

    public abstract String getQuestProgress();

    public abstract String getQuestProgressShorthand();

    public abstract float getCompletionFactor();

    public abstract ItemStack getQuestIcon();

    public void onInteract(EntityPlayer entityplayer, LOTREntityNPC npc) {
    }

    public boolean onInteractOther(EntityPlayer entityplayer, LOTREntityNPC npc) {
        return false;
    }

    protected void sendProgressSpeechbank(EntityPlayer entityplayer, LOTREntityNPC npc) {
        npc.sendSpeechBank(entityplayer, this.speechBankProgress, this);
    }

    protected void sendCompletedSpeech(EntityPlayer entityplayer, LOTREntityNPC npc) {
        this.sendQuoteSpeech(entityplayer, npc, this.quoteComplete);
    }

    protected void sendQuoteSpeech(EntityPlayer entityplayer, LOTREntityNPC npc, String quote) {
        LOTRSpeech.sendSpeech(entityplayer, npc, LOTRSpeech.formatSpeech(quote, entityplayer, null, this.getObjectiveInSpeech()));
        npc.markNPCSpoken();
    }

    public void onKill(EntityPlayer entityplayer, EntityLivingBase entity) {
    }

    public void onKilledByPlayer(EntityPlayer entityplayer, EntityPlayer killer) {
    }

    public void onPlayerTick(EntityPlayer entityplayer) {
    }

    public void handleEvent(LOTRMiniQuestEvent event) {
    }

    public final boolean isActive() {
        return !this.isCompleted() && !this.isFailed();
    }

    public boolean isFailed() {
        return this.entityDead;
    }

    public String getQuestFailure() {
        return StatCollector.translateToLocalFormatted((String)"lotr.gui.redBook.mq.diary.dead", (Object[])new Object[]{this.entityName});
    }

    public String getQuestFailureShorthand() {
        return StatCollector.translateToLocal((String)"lotr.gui.redBook.mq.dead");
    }

    public void setEntityDead() {
        this.entityDead = true;
        this.updateQuest();
    }

    public void start(EntityPlayer entityplayer, LOTREntityNPC npc) {
        this.setNPCInfo(npc);
        this.dateGiven = LOTRDate.ShireReckoning.currentDay;
        int i = MathHelper.floor_double((double)entityplayer.posX);
        int k = MathHelper.floor_double((double)entityplayer.posZ);
        BiomeGenBase biome = entityplayer.worldObj.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiome) {
            this.biomeGiven = (LOTRBiome)biome;
        }
        this.playerData.addMiniQuest(this);
        npc.questInfo.addActiveQuestPlayer(entityplayer);
        this.playerData.setTrackingMiniQuest(this);
    }

    public boolean isCompleted() {
        return this.completed;
    }

    protected void complete(EntityPlayer entityplayer, LOTREntityNPC npc) {
        LOTRAchievement achievement;
        int coins;
        this.completed = true;
        this.dateCompleted = LOTRDate.ShireReckoning.currentDay;
        Random rand = npc.getRNG();
        ArrayList<ItemStack> dropItems = new ArrayList<ItemStack>();
        float alignment = this.getAlignmentBonus();
        if (alignment != 0.0f) {
            alignment *= MathHelper.randomFloatClamp((Random)rand, (float)0.75f, (float)1.25f);
            alignment = Math.max(alignment, 1.0f);
            LOTRAlignmentValues.AlignmentBonus bonus = LOTRAlignmentValues.createMiniquestBonus(alignment);
            LOTRFaction rewardFaction = this.getAlignmentRewardFaction();
            if (!this.questGroup.isNoAlignRewardForEnemy() || this.playerData.getAlignment(rewardFaction) >= 0.0f) {
                LOTRAlignmentBonusMap alignmentMap = this.playerData.addAlignment(entityplayer, bonus, rewardFaction, (Entity)npc);
                this.alignmentRewarded = ((Float)alignmentMap.get((Object)((Object)rewardFaction))).floatValue();
            }
        }
        if ((coins = this.getCoinBonus()) != 0) {
            if (this.shouldRandomiseCoinReward()) {
                coins = Math.round((float)coins * MathHelper.randomFloatClamp((Random)rand, (float)0.75f, (float)1.25f));
                if (rand.nextInt(12) == 0) {
                    coins *= MathHelper.getRandomIntegerInRange((Random)rand, (int)2, (int)5);
                }
            }
            this.coinsRewarded = coins = Math.max(coins, 1);
            int coinsRemain = coins;
            for (int l = LOTRItemCoin.values.length - 1; l >= 0; --l) {
                int coinValue = LOTRItemCoin.values[l];
                if (coinsRemain < coinValue) continue;
                int numCoins = coinsRemain / coinValue;
                coinsRemain -= numCoins * coinValue;
                while (numCoins > 64) {
                    numCoins -= 64;
                    dropItems.add(new ItemStack(LOTRMod.silverCoin, 64, l));
                }
                dropItems.add(new ItemStack(LOTRMod.silverCoin, numCoins, l));
            }
        }
        if (!this.rewardItemTable.isEmpty()) {
            ItemStack item = this.rewardItemTable.get(rand.nextInt(this.rewardItemTable.size()));
            dropItems.add(item.copy());
            this.itemsRewarded.add(item.copy());
        }
        if (this.canRewardVariousExtraItems()) {
            ItemStack mithrilBook;
            LOTRLore lore;
            if (rand.nextInt(10) == 0 && this.questGroup != null && !this.questGroup.getLoreCategories().isEmpty() && (lore = LOTRLore.getMultiRandomLore(this.questGroup.getLoreCategories(), rand, true)) != null) {
                ItemStack loreBook = lore.createLoreBook(rand);
                dropItems.add(loreBook.copy());
                this.itemsRewarded.add(loreBook.copy());
            }
            if (rand.nextInt(15) == 0) {
                ItemStack modItem = LOTRItemModifierTemplate.getRandomCommonTemplate(rand);
                dropItems.add(modItem.copy());
                this.itemsRewarded.add(modItem.copy());
            }
            if (npc instanceof LOTREntityGaladhrimElf && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.arven);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityRivendellElf && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.elrondgold);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityTauredain && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.totemOfUndying);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityTauredain && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.ringShaman);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityHobbit && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.magicClover);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityHobbit && rand.nextInt(200) == 0) {
                mithrilBook = new ItemStack(LOTRMod.magicCloverPlus);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityRivendellElf && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.elrondsilver);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityRivendellElf && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.thranduilsilver);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityRivendellElf && rand.nextInt(100) == 0) {
                mithrilBook = new ItemStack(LOTRMod.thranduilsnake);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityRivendellElf && rand.nextInt(120) == 0) {
                mithrilBook = new ItemStack(LOTRMod.thranduilmithril);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityDwarf && rand.nextInt(10) == 0) {
                mithrilBook = new ItemStack(LOTRMod.mithrilBook);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityDwarf && rand.nextInt(45) == 0) {
                mithrilBook = new ItemStack(LOTRMod.ring_khain);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityDwarf && rand.nextInt(45) == 0) {
                mithrilBook = new ItemStack(LOTRMod.ring_farin);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityDwarf && rand.nextInt(90) == 0) {
                mithrilBook = new ItemStack(LOTRMod.diggingBook);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityEreborDwarf && rand.nextInt(110) == 0) {
                mithrilBook = new ItemStack(LOTRMod.diggingBook2);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityEreborDwarf && rand.nextInt(110) == 0) {
                mithrilBook = new ItemStack(LOTRMod.thorin);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityAngbandOrc && rand.nextInt(110) == 0) {
                mithrilBook = new ItemStack(LOTRMod.chilling);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityAngbandBerserk && rand.nextInt(200) == 0) {
                mithrilBook = new ItemStack(LOTRMod.balrogFire);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
            if (npc instanceof LOTREntityDolGuldurUruk && rand.nextInt(50) == 0) {
                ItemStack sword = new ItemStack(LOTRMod.swordDolGuldurUrukPoisoned);
                dropItems.add(sword.copy());
                this.itemsRewarded.add(sword.copy());
            } else if (npc instanceof LOTREntityDolGuldurUruk && rand.nextInt(50) == 1) {
                ItemStack hammer = new ItemStack(LOTRMod.hammerDolGuldurUrukPoisoned);
                dropItems.add(hammer.copy());
                this.itemsRewarded.add(hammer.copy());
            } else if (npc instanceof LOTREntityDolGuldurUruk && rand.nextInt(50) == 2) {
                ItemStack battleaxe = new ItemStack(LOTRMod.battleaxeDolGuldurUrukPoisoned);
                dropItems.add(battleaxe.copy());
                this.itemsRewarded.add(battleaxe.copy());
            } else if (npc instanceof LOTREntityDolGuldurUruk && rand.nextInt(50) == 3) {
                ItemStack pike = new ItemStack(LOTRMod.pikeDolGuldurUrukPoisoned);
                dropItems.add(pike.copy());
                this.itemsRewarded.add(pike.copy());
            }
            if (npc instanceof LOTREntityEreborDwarf && rand.nextInt(110) == 0) {
                mithrilBook = new ItemStack(LOTRMod.thorinrune);
                dropItems.add(mithrilBook.copy());
                this.itemsRewarded.add(mithrilBook.copy());
            }
        }
        if (!dropItems.isEmpty()) {
            boolean givePouch;
            boolean bl = givePouch = this.canRewardVariousExtraItems() && rand.nextInt(10) == 0;
            if (givePouch) {
                ItemStack pouch = npc.createNPCPouchDrop();
                npc.fillPouchFromListAndRetainUnfilled(pouch, dropItems);
                npc.entityDropItem(pouch, 0.0f);
                ItemStack pouchCopy = pouch.copy();
                pouchCopy.setTagCompound(null);
                this.itemsRewarded.add(pouchCopy);
            }
            npc.dropItemList(dropItems);
        }
        if (this.willHire) {
            LOTRUnitTradeEntry tradeEntry = new LOTRUnitTradeEntry(npc.getClass(), 0, this.hiringAlignment);
            tradeEntry.setTask(LOTRHiredNPCInfo.Task.WARRIOR);
            npc.hiredNPCInfo.hireUnit(entityplayer, false, this.entityFaction, tradeEntry, null, npc.ridingEntity);
            this.wasHired = true;
        }
        this.updateQuest();
        this.playerData.completeMiniQuest(this);
        this.sendCompletedSpeech(entityplayer, npc);
        if (this.questGroup != null && (achievement = this.questGroup.getAchievement()) != null) {
            this.playerData.addAchievement(achievement);
        }
    }

    public final LOTRFaction getAlignmentRewardFaction() {
        return this.questGroup.checkAlignmentRewardFaction(this.entityFaction);
    }

    public boolean anyRewardsGiven() {
        return this.alignmentRewarded > 0.0f || this.coinsRewarded > 0 || !this.itemsRewarded.isEmpty();
    }

    public void updateLocation(LOTREntityNPC npc) {
        int i = MathHelper.floor_double((double)npc.posX);
        int j = MathHelper.floor_double((double)npc.posY);
        int k = MathHelper.floor_double((double)npc.posZ);
        ChunkCoordinates coords = new ChunkCoordinates(i, j, k);
        int dim = npc.dimension;
        ChunkCoordinates prevCoords = null;
        if (this.lastLocation != null) {
            prevCoords = (ChunkCoordinates)this.lastLocation.getLeft();
        }
        this.lastLocation = Pair.of((Object)coords, (Object)dim);
        boolean sendUpdate = false;
        if (prevCoords == null) {
            sendUpdate = true;
        } else {
            boolean bl = sendUpdate = (double)coords.getDistanceSquaredToChunkCoordinates(prevCoords) > 256.0;
        }
        if (sendUpdate) {
            this.updateQuest();
        }
    }

    public ChunkCoordinates getLastLocation() {
        return this.lastLocation == null ? null : (ChunkCoordinates)this.lastLocation.getLeft();
    }

    protected void updateQuest() {
        this.playerData.updateMiniQuest(this);
    }

    public abstract float getAlignmentBonus();

    public abstract int getCoinBonus();

    protected boolean shouldRandomiseCoinReward() {
        return true;
    }

    protected boolean canRewardVariousExtraItems() {
        return true;
    }

    static {
        LOTRMiniQuest.registerQuestType("Collect", LOTRMiniQuestCollect.class);
        LOTRMiniQuest.registerQuestType("KillFaction", LOTRMiniQuestKillFaction.class);
        LOTRMiniQuest.registerQuestType("KillEntity", LOTRMiniQuestKillEntity.class);
        LOTRMiniQuest.registerQuestType("Bounty", LOTRMiniQuestBounty.class);
        LOTRMiniQuest.registerQuestType("Welcome", LOTRMiniQuestWelcome.class);
        LOTRMiniQuest.registerQuestType("Pickpocket", LOTRMiniQuestPickpocket.class);
        MAX_MINIQUESTS_PER_FACTION = 5;
        RENDER_HEAD_DISTANCE = 15.0;
    }

    public static abstract class QuestFactoryBase<Q extends LOTRMiniQuest> {
        private LOTRMiniQuestFactory questFactoryGroup;
        private String questName;
        private float rewardFactor = 1.0f;
        private boolean willHire = false;
        private float hiringAlignment;
        private List<ItemStack> rewardItems;

        public QuestFactoryBase(String name) {
            this.questName = name;
        }

        public void setFactoryGroup(LOTRMiniQuestFactory factory) {
            this.questFactoryGroup = factory;
        }

        public LOTRMiniQuestFactory getFactoryGroup() {
            return this.questFactoryGroup;
        }

        public QuestFactoryBase setRewardFactor(float f) {
            this.rewardFactor = f;
            return this;
        }

        public QuestFactoryBase setHiring(float f) {
            this.willHire = true;
            this.hiringAlignment = f;
            return this;
        }

        public QuestFactoryBase setRewardItems(ItemStack[] items) {
            this.rewardItems = Arrays.asList(items);
            return this;
        }

        public abstract Class<Q> getQuestClass();

        public Q createQuest(LOTREntityNPC npc, Random rand) {
            LOTRMiniQuest quest = LOTRMiniQuest.newQuestInstance(this.getQuestClass(), null);
            quest.questGroup = this.getFactoryGroup();
            String pathName = "miniquest/" + this.getFactoryGroup().getBaseName() + "/";
            String pathNameBaseSpeech = "miniquest/" + this.getFactoryGroup().getBaseSpeechGroup().getBaseName() + "/";
            String questPathName = pathName + this.questName + "_";
            quest.speechBankStart = questPathName + "start";
            quest.speechBankProgress = questPathName + "progress";
            quest.speechBankComplete = questPathName + "complete";
            quest.speechBankTooMany = pathNameBaseSpeech + "_tooMany";
            quest.quoteStart = LOTRSpeech.getRandomSpeech(quest.speechBankStart);
            quest.quoteComplete = LOTRSpeech.getRandomSpeech(quest.speechBankComplete);
            quest.setNPCInfo(npc);
            quest.rewardFactor = this.rewardFactor;
            quest.willHire = this.willHire;
            quest.hiringAlignment = this.hiringAlignment;
            if (this.rewardItems != null) {
                quest.rewardItemTable.addAll(this.rewardItems);
            }
            return (Q)quest;
        }
    }

    public static class SorterAlphabetical
    implements Comparator<LOTRMiniQuest> {
        @Override
        public int compare(LOTRMiniQuest q1, LOTRMiniQuest q2) {
            if (!q2.isActive() && q1.isActive()) {
                return 1;
            }
            if (!q1.isActive() && q2.isActive()) {
                return -1;
            }
            if (q1.entityFaction == q2.entityFaction) {
                return q1.entityName.compareTo(q2.entityName);
            }
            return Integer.valueOf(q1.entityFaction.ordinal()).compareTo(q2.entityFaction.ordinal());
        }
    }

}

