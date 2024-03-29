/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemCoin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRUnitTradeEntry {
    public Class entityClass;
    public Class mountClass;
    private Item mountArmor;
    private float mountArmorChance;
    private String name;
    private int initialCost;
    public float alignmentRequired;
    private PledgeType pledgeType = PledgeType.NONE;
    public LOTRHiredNPCInfo.Task task = LOTRHiredNPCInfo.Task.WARRIOR;
    private String extraInfo = null;

    public LOTRUnitTradeEntry(Class c, int cost, float alignment) {
        this.entityClass = c;
        this.initialCost = cost;
        this.alignmentRequired = alignment;
        if (LOTRBannerBearer.class.isAssignableFrom(this.entityClass)) {
            this.setExtraInfo("Banner");
        }
    }

    public LOTRUnitTradeEntry(Class c, Class c1, String s, int cost, float alignment) {
        this(c, cost, alignment);
        this.mountClass = c1;
        this.name = s;
    }

    public LOTRUnitTradeEntry setTask(LOTRHiredNPCInfo.Task t) {
        this.task = t;
        return this;
    }

    public LOTRUnitTradeEntry setMountArmor(Item item) {
        return this.setMountArmor(item, 1.0f);
    }

    public LOTRUnitTradeEntry setMountArmor(Item item, float chance) {
        this.mountArmor = item;
        this.mountArmorChance = chance;
        return this;
    }

    public LOTRUnitTradeEntry setPledgeExclusive() {
        return this.setPledgeType(PledgeType.FACTION);
    }

    public LOTRUnitTradeEntry setPledgeType(PledgeType t) {
        this.pledgeType = t;
        return this;
    }

    public PledgeType getPledgeType() {
        return this.pledgeType;
    }

    public LOTRUnitTradeEntry setExtraInfo(String s) {
        this.extraInfo = s;
        return this;
    }

    public boolean hasExtraInfo() {
        return this.extraInfo != null;
    }

    public String getFormattedExtraInfo() {
        return StatCollector.translateToLocal((String)("lotr.unitinfo." + this.extraInfo));
    }

    public int getCost(EntityPlayer entityplayer, LOTRHireableBase trader) {
        float f;
        float cost = this.initialCost;
        float maxDiscount = 0.5f;
        float notPledgedExpense = 2.0f;
        LOTRFaction fac = trader.getFaction();
        LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
        float alignment = pd.getAlignment(fac);
        boolean pledged = pd.isPledgedTo(fac);
        float alignSurplus = Math.max(alignment - this.alignmentRequired, 0.0f);
        if (pledged) {
            f = alignSurplus / 1500.0f;
            f = MathHelper.clamp_float((float)f, (float)0.0f, (float)1.0f);
            cost *= 1.0f - (f *= 0.5f);
        } else {
            cost *= 2.0f;
            f = alignSurplus / 2000.0f;
            f = MathHelper.clamp_float((float)f, (float)0.0f, (float)1.0f);
            cost *= 1.0f - (f *= 0.5f);
        }
        int costI = Math.round(cost);
        costI = Math.max(costI, 1);
        return costI;
    }

    public boolean hasRequiredCostAndAlignment(EntityPlayer entityplayer, LOTRHireableBase trader) {
        int coins = LOTRItemCoin.getInventoryValue(entityplayer, false);
        if (coins < this.getCost(entityplayer, trader)) {
            return false;
        }
        LOTRFaction fac = trader.getFaction();
        if (!this.pledgeType.canAcceptPlayer(entityplayer, fac)) {
            return false;
        }
        float alignment = LOTRLevelData.getData(entityplayer).getAlignment(fac);
        return alignment >= this.alignmentRequired;
    }

    public String getUnitTradeName() {
        if (this.mountClass == null) {
            String entityName = LOTREntities.getStringFromClass(this.entityClass);
            return StatCollector.translateToLocal((String)("entity." + entityName + ".name"));
        }
        return StatCollector.translateToLocal((String)("lotr.unit." + this.name));
    }

    public void hireUnit(EntityPlayer entityplayer, LOTRHireableBase trader, String squadron) {
        if (this.hasRequiredCostAndAlignment(entityplayer, trader)) {
            trader.onUnitTrade(entityplayer);
            int cost = this.getCost(entityplayer, trader);
            LOTRItemCoin.takeCoins(cost, entityplayer);
            ((LOTREntityNPC)((Object)trader)).playTradeSound();
            World world = entityplayer.worldObj;
            LOTREntityNPC hiredNPC = this.getOrCreateHiredNPC(world);
            if (hiredNPC != null) {
                boolean unitExists;
                EntityLiving mount = null;
                if (this.mountClass != null) {
                    mount = this.createHiredMount(world);
                }
                hiredNPC.hiredNPCInfo.hireUnit(entityplayer, !(unitExists = world.loadedEntityList.contains(hiredNPC)), trader.getFaction(), this, squadron, (Entity)mount);
                if (!unitExists) {
                    world.spawnEntityInWorld((Entity)hiredNPC);
                    if (mount != null) {
                        world.spawnEntityInWorld((Entity)mount);
                    }
                }
            }
        }
    }

    public LOTREntityNPC getOrCreateHiredNPC(World world) {
        LOTREntityNPC entity = (LOTREntityNPC)EntityList.createEntityByName((String)LOTREntities.getStringFromClass(this.entityClass), (World)world);
        entity.initCreatureForHire(null);
        entity.refreshCurrentAttackMode();
        return entity;
    }

    public EntityLiving createHiredMount(World world) {
        if (this.mountClass == null) {
            return null;
        }
        EntityLiving entity = (EntityLiving)EntityList.createEntityByName((String)LOTREntities.getStringFromClass(this.mountClass), (World)world);
        if (entity instanceof LOTREntityNPC) {
            ((LOTREntityNPC)entity).initCreatureForHire(null);
            ((LOTREntityNPC)entity).refreshCurrentAttackMode();
        } else {
            entity.onSpawnWithEgg(null);
        }
        if (this.mountArmor != null && world.rand.nextFloat() < this.mountArmorChance) {
            if (entity instanceof LOTREntityHorse) {
                ((LOTREntityHorse)entity).setMountArmor(new ItemStack(this.mountArmor));
            } else if (entity instanceof LOTREntityWarg) {
                ((LOTREntityWarg)entity).setWargArmor(new ItemStack(this.mountArmor));
            }
        }
        return entity;
    }

    public static enum PledgeType {
        NONE(0),
        FACTION(1),
        ANY_ELF(2),
        ANY_DWARF(3);

        public final int typeID;

        private PledgeType(int i) {
            this.typeID = i;
        }

        public boolean canAcceptPlayer(EntityPlayer entityplayer, LOTRFaction fac) {
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            LOTRFaction pledged = pd.getPledgeFaction();
            if (this == NONE) {
                return true;
            }
            if (this == FACTION) {
                return pd.isPledgedTo(fac);
            }
            if (this == ANY_ELF) {
                return pledged != null && pledged.isOfType(LOTRFaction.FactionType.TYPE_ELF) && !pledged.isOfType(LOTRFaction.FactionType.TYPE_MAN);
            }
            if (this == ANY_DWARF) {
                return pledged != null && pledged.isOfType(LOTRFaction.FactionType.TYPE_DWARF);
            }
            return false;
        }

        public String getCommandReqText(LOTRFaction fac) {
            if (this == NONE) {
                return null;
            }
            return StatCollector.translateToLocalFormatted((String)("lotr.hiredNPC.commandReq.pledge." + this.name()), (Object[])new Object[]{fac.factionName()});
        }

        public static PledgeType forID(int i) {
            for (PledgeType t : PledgeType.values()) {
                if (t.typeID != i) continue;
                return t;
            }
            return NONE;
        }
    }

}

