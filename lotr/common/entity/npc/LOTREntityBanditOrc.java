/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
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
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIBanditFlee;
import lotr.common.entity.ai.LOTREntityAIBanditSteal;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBandit;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.npc.IBandit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.fac.LOTRFaction;
import lotr.common.inventory.LOTRInventoryNPC;
import lotr.common.item.LOTRItemLeatherHat;
import lotr.common.item.LOTRItemMug;
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
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityBanditOrc
extends LOTREntityOrc
implements IBandit {
    private static ItemStack[] weapons = new ItemStack[]{new ItemStack(LOTRMod.daggerOrc), new ItemStack(LOTRMod.daggerOrcPoisoned)};
    private LOTRInventoryNPC banditInventory = IBandit.Helper.createInv(this);

    public LOTREntityBanditOrc(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.isWeakOrc = true;
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.5, false));
        this.tasks.addTask(2, (EntityAIBase)new LOTREntityAIBanditSteal(this, 1.8));
        this.tasks.addTask(3, (EntityAIBase)new LOTREntityAIBanditFlee(this, 1.5));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.5));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 8.0f, 0.1f));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, LOTREntityNPC.class, 5.0f, 0.05f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetBandit.class);
    }

    @Override
    public LOTREntityNPC getBanditAsNPC() {
        return this;
    }

    @Override
    public int getMaxThefts() {
        return 4;
    }

    @Override
    public LOTRInventoryNPC getBanditInventory() {
        return this.banditInventory;
    }

    @Override
    public boolean canTargetPlayerForTheft(EntityPlayer player) {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)22, (int)30));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(weapons.length);
        this.npcItemsInv.setMeleeWeapon(weapons[i].copy());
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        if (this.rand.nextInt(3) == 0) {
            ItemStack hat = new ItemStack(LOTRMod.leatherHat);
            LOTRItemLeatherHat.setHatColor(hat, 0);
            LOTRItemLeatherHat.setFeatherColor(hat, 16777215);
            this.setCurrentItemOrArmor(4, hat);
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
    public int getTotalArmorValue() {
        return 13;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.HOSTILE;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        this.banditInventory.writeToNBT(nbt);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.banditInventory.readFromNBT(nbt);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(Items.bone, 1);
        }
        int coins = 10 + this.rand.nextInt(10) + this.rand.nextInt((i + 1) * 10);
        for (int l = 0; l < coins; ++l) {
            this.dropItem(LOTRMod.silverCoin, 1);
        }
        if (this.rand.nextInt(5) == 0) {
            this.entityDropItem(LOTRItemMug.Vessel.SKULL.getEmptyVessel(), 0.0f);
        }
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote && damagesource.getEntity() instanceof EntityPlayer && !this.banditInventory.isEmpty()) {
            EntityPlayer entityplayer = (EntityPlayer)damagesource.getEntity();
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.killThievingBandit);
        }
        if (!this.worldObj.isRemote) {
            this.banditInventory.dropAllItems();
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return "misc/bandit/hostile";
    }

    @Override
    public String getTheftSpeechBank(EntityPlayer player) {
        return this.getSpeechBank(player);
    }

    @Override
    public IChatComponent getTheftChatMsg(EntityPlayer player) {
        return new ChatComponentTranslation("chat.lotr.banditSteal", new Object[0]);
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }
}

