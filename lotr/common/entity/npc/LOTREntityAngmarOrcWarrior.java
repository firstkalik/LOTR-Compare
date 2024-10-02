/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTREntityAngmarOrcWarrior
extends LOTREntityOrc
implements LOTRMercenary {
    public static float BERSERKER_SCALE = 1.1f;

    public LOTREntityAngmarOrcWarrior(World world) {
        super(world);
        this.isWeakOrc = false;
        this.setSize(this.npcWidth * BERSERKER_SCALE, this.npcHeight * BERSERKER_SCALE);
    }

    @Override
    public boolean canOrcSkirmish() {
        return false;
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)25, (int)30));
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.morgulBlade));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsMorgul));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsMorgul));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyMorgul));
        if (this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetMorgul));
        }
        return data;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.worldObj.spawnParticle("smoke", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killMorgulAngmarOrc;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            int duration;
            if (entity instanceof EntityLivingBase && (duration = this.worldObj.difficultySetting.getDifficultyId() * 3 - 1) > 0) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, duration * 20, 0));
            }
            return true;
        }
        return false;
    }

    @Override
    protected void dropOrcItems(boolean flag, int i) {
        if (this.rand.nextInt(6) == 0) {
            this.dropChestContents(LOTRChestContents.ANGMAR_TENT, 1, 2 + i);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "angmar/orc/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 100.0f) {
                return "angmar/orc/friendly";
            }
            return "angmar/orc/neutral";
        }
        return "angmar/orc/hostile";
    }

    @Override
    protected String getOrcSkirmishSpeech() {
        return "angmar/orc/skirmish";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.ANGMAR.createQuest(this);
    }

    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotionID() == Potion.wither.id) {
            return;
        }
        super.addPotionEffect(effect);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.ANGMAR;
    }

    @Override
    public int getMercBaseCost() {
        return 90;
    }

    @Override
    public float getMercAlignmentRequired() {
        return 500.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 0.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.hireMorgulMercenary);
    }
}

