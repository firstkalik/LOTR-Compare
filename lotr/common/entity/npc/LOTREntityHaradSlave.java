/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIDrink;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.ai.LOTREntityAIFarm;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAINPCHurtByTarget;
import lotr.common.entity.npc.LOTREntityMan;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRFarmhand;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class LOTREntityHaradSlave
extends LOTREntityMan
implements LOTRFarmhand {
    public Item seedsItem;

    public LOTREntityHaradSlave(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.3, false));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIFarm(this, 1.0, 1.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIEat(this, LOTRFoods.HARAD_SLAVE, 12000));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIDrink(this, LOTRFoods.HARAD_SLAVE_DRINK, 8000));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 8.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 5.0f, 0.02f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.taskEntries.clear();
        this.targetTasks.addTask(1, (EntityAIBase)new LOTREntityAINPCHurtByTarget(this, false));
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public void setupNPCName() {
        this.dataWatcher.addObject(20, (Object)0);
        float f = this.rand.nextFloat();
        if (f < 0.05f) {
            this.setSlaveType(SlaveType.TAURETHRIM);
        } else if (f < 0.2f) {
            this.setSlaveType(SlaveType.MORWAITH);
        } else if (f < 0.7f) {
            this.setSlaveType(SlaveType.NEAR_HARAD);
        } else {
            this.setSlaveType(SlaveType.GONDOR);
        }
        SlaveType type = this.getSlaveType();
        if (type == SlaveType.GONDOR) {
            this.familyInfo.setName(LOTRNames.getGondorName(this.rand, this.familyInfo.isMale()));
        } else if (type == SlaveType.NEAR_HARAD) {
            if (this.rand.nextBoolean()) {
                this.familyInfo.setName(LOTRNames.getHarnennorName(this.rand, this.familyInfo.isMale()));
            } else {
                this.familyInfo.setName(LOTRNames.getNomadName(this.rand, this.familyInfo.isMale()));
            }
        } else if (type == SlaveType.MORWAITH) {
            this.familyInfo.setName(LOTRNames.getMoredainName(this.rand, this.familyInfo.isMale()));
        } else if (type == SlaveType.TAURETHRIM) {
            this.familyInfo.setName(LOTRNames.getTauredainName(this.rand, this.familyInfo.isMale()));
        }
    }

    public SlaveType getSlaveType() {
        int i = this.dataWatcher.getWatchableObjectByte(20);
        i = MathHelper.clamp_int((int)i, (int)0, (int)SlaveType.values().length);
        return SlaveType.values()[i];
    }

    public void setSlaveType(SlaveType t) {
        int i = t.ordinal();
        this.dataWatcher.updateObject(20, (Object)((byte)i));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hoeBronze));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        return data;
    }

    @Override
    public void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
    }

    @Override
    public IPlantable getUnhiredSeeds() {
        if (this.seedsItem == null) {
            return (IPlantable)Items.wheat_seeds;
        }
        return (IPlantable)this.seedsItem;
    }

    @Override
    public LOTRFaction getFaction() {
        return this.getSlaveType().faction;
    }

    @Override
    public LOTRFaction getHiringFaction() {
        return LOTRFaction.NEAR_HARAD;
    }

    @Override
    public boolean canBeFreelyTargetedBy(EntityLiving attacker) {
        if (!LOTRMod.getNPCFaction((Entity)attacker).isBadRelation(this.getHiringFaction())) {
            return false;
        }
        return super.canBeFreelyTargetedBy(attacker);
    }

    @Override
    public String getNPCName() {
        return this.familyInfo.getName();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setString("SlaveType", this.getSlaveType().saveName());
        if (this.seedsItem != null) {
            nbt.setInteger("SeedsID", Item.getIdFromItem((Item)this.seedsItem));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        Item item;
        SlaveType type;
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("SlaveType") && (type = SlaveType.forName(nbt.getString("SlaveType"))) != null) {
            this.setSlaveType(type);
        }
        if (nbt.hasKey("SeedsID") && (item = Item.getItemById((int)nbt.getInteger("SeedsID"))) != null && item instanceof IPlantable) {
            this.seedsItem = item;
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(Items.bone, 1);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
            return "nearHarad/slave/hired";
        }
        return "nearHarad/slave/neutral";
    }

    public static enum SlaveType {
        GONDOR(LOTRFaction.GONDOR, "gondor"),
        NEAR_HARAD(LOTRFaction.NEAR_HARAD, "nearHarad"),
        MORWAITH(LOTRFaction.MORWAITH, "morwaith"),
        TAURETHRIM(LOTRFaction.TAURETHRIM, "taurethrim");

        public LOTRFaction faction;
        public String skinDir;

        private SlaveType(LOTRFaction f, String s) {
            this.faction = f;
            this.skinDir = s;
        }

        public String saveName() {
            return this.name();
        }

        public static SlaveType forName(String s) {
            for (SlaveType type : SlaveType.values()) {
                if (!type.saveName().equals(s)) continue;
                return type;
            }
            return null;
        }
    }

}

