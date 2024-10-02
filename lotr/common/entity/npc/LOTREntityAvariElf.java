/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetWoodElf;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.item.LOTREntityArrowAvari;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionRank;
import lotr.common.item.LOTRItemBow;
import lotr.common.item.LOTRItemMug;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.biome.LOTRBiomeGenRhunForest;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityAvariElf
extends LOTREntityElf {
    public LOTREntityAvariElf(World world) {
        super(world);
        this.tasks.addTask(2, this.rangedAttackAI);
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetWoodElf.class);
    }

    @Override
    protected LOTRFoods getElfDrinks() {
        return LOTRFoods.WOOD_ELF_DRINK;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotionID() == LOTRPotions.blood.id) {
            return;
        }
        super.addPotionEffect(effect);
    }

    @Override
    protected EntityAIBase createElfMeleeAttackAI() {
        return this.createElfRangedAttackAI();
    }

    @Override
    protected EntityAIBase createElfRangedAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 30, 50, 16.0f);
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getSindarinName(this.rand, this.familyInfo.isMale()));
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.bowAvariElf));
        this.npcItemsInv.setMeleeWeapon(this.npcItemsInv.getRangedWeapon());
        this.npcItemsInv.setIdleItem(null);
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.AVARI;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killAvariElf;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected void dropElfItems(boolean flag, int i) {
        super.dropElfItems(flag, i);
        if (flag) {
            int dropChance = 20 - i * 4;
            if (this.rand.nextInt(dropChance = Math.max(dropChance, 1)) == 0) {
                ItemStack elfDrink = new ItemStack(LOTRMod.mugRedWine);
                elfDrink.setItemDamage(1 + this.rand.nextInt(3));
                LOTRItemMug.setVessel(elfDrink, LOTRFoods.ELF_DRINK.getRandomVessel(this.rand), true);
                this.entityDropItem(elfDrink, 0.0f);
            }
        }
        if (this.rand.nextInt(6) == 0) {
            this.dropChestContents(LOTRChestContents.LOTRChestContents2.AVARI_ELF_HOUSE, 1, 1 + i);
        }
    }

    @Override
    public boolean canElfSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        return j > 62 && this.worldObj.getBlock(i, j - 1, k) == Blocks.grass;
    }

    @Override
    public float getBlockPathWeight(int i, int j, int k) {
        float f = 0.0f;
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiomeGenRhunForest) {
            f += 20.0f;
        }
        return f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "avariElf/elf/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= LOTREntityAvariElf.getWoodlandTrustLevel()) {
                return "avariElf/elf/friendly";
            }
            return "avariElf/elf/neutral";
        }
        return "avariElf/elf/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.AVARI_ELF.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.AVARI_ELF;
    }

    public static float getWoodlandTrustLevel() {
        return LOTRFaction.AVARI.getFirstRank().alignment;
    }

    protected float getFireArrowChance() {
        return 0.06666667f;
    }

    @Override
    protected void npcArrowAttack(EntityLivingBase target, float f) {
        LOTREntityArrowAvari arrow;
        ItemStack heldItem = this.getHeldItem();
        float str = 1.3f + this.getDistanceToEntity((Entity)target) / 80.0f;
        float accuracy = (float)this.getEntityAttribute(npcRangedAccuracy).getAttributeValue();
        float poisonChance = this.getFireArrowChance();
        LOTREntityArrowAvari lOTREntityArrowAvari = arrow = this.rand.nextFloat() < poisonChance ? new LOTREntityArrowAvari(this.worldObj, (EntityLivingBase)this, target, str, accuracy) : new EntityArrow(this.worldObj, (EntityLivingBase)this, target, str * LOTRItemBow.getLaunchSpeedFactor(heldItem), accuracy);
        if (heldItem != null) {
            LOTRItemBow.applyBowModifiers(arrow, heldItem);
        }
        this.playSound("random.bow", 1.0f, 1.0f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)arrow);
    }
}

