/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIDrink;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.npc.LOTREntityMan;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityUrukWargBombardier;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.biome.LOTRBiomeGenAdornland;
import lotr.common.world.biome.LOTRBiomeGenDunland;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityDunlending
extends LOTREntityMan {
    public LOTREntityDunlending(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(2, this.getDunlendingAttackAI());
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIEat(this, LOTRFoods.DUNLENDING, 8000));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIDrink(this, LOTRFoods.DUNLENDING_DRINK, 8000));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 8.0f, 0.02f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 5.0f, 0.02f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true);
    }

    public EntityAIBase getDunlendingAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.5, false);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(this.rand.nextBoolean());
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getDunlendingName(this.rand, this.familyInfo.isMale()));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)20, (int)22));
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(9);
        if (i == 0 || i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.dunlendingClub));
        } else if (i == 2 || i == 3) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.dunlendingTrident));
        } else if (i == 4) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.wooden_sword));
        } else if (i == 5) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.stone_sword));
        } else if (i == 6) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.stone_axe));
        } else if (i == 7) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.stone_hoe));
        } else if (i == 8) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearStone));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetFur));
        }
        if (this.rand.nextInt(10000) == 0) {
            LOTREntityUrukWargBombardier warg = new LOTREntityUrukWargBombardier(this.worldObj);
            warg.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
            warg.onSpawnWithEgg(null);
            warg.isNPCPersistent = this.isNPCPersistent;
            this.worldObj.spawnEntityInWorld((Entity)warg);
            this.mountEntity((Entity)warg);
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.orcBomb));
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.orcBomb));
            this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        }
        return data;
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DUNLAND;
    }

    @Override
    public String getNPCName() {
        return this.familyInfo.getName();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("DunlendingName")) {
            this.familyInfo.setName(nbt.getString("DunlendingName"));
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(Items.bone, 1);
        }
        this.dropDunlendingItems(flag, i);
    }

    public void dropDunlendingItems(boolean flag, int i) {
        if (this.rand.nextInt(5) == 0) {
            this.dropChestContents(LOTRChestContents.DUNLENDING_HOUSE, 1, 2 + i);
        }
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killDunlending;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            if (this.liftSpawnRestrictions) {
                return true;
            }
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.boundingBox.minY);
            int k = MathHelper.floor_double((double)this.posZ);
            BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
            if (j > 62 && this.worldObj.getBlock(i, j - 1, k) == biome.topBlock) {
                return true;
            }
        }
        return false;
    }

    @Override
    public float getBlockPathWeight(int i, int j, int k) {
        float f = 0.0f;
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiomeGenDunland || biome instanceof LOTRBiomeGenAdornland) {
            f += 20.0f;
        }
        return f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isDrunkard()) {
            return "dunlending/drunkard/neutral";
        }
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "dunlending/dunlending/hired";
            }
            return "dunlending/dunlending/friendly";
        }
        return "dunlending/dunlending/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.DUNLAND.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.DUNLAND;
    }
}

