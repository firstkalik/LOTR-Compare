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
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityAngbandUruc;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.fac.LOTRFaction;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LOTREntityAngbandBerserk2
extends LOTREntityAngbandUruc
implements LOTRMercenary {
    public static float BERSERKER_SCALE = 1.3f;

    public LOTREntityAngbandBerserk2(World world) {
        super(world);
        this.setSize(this.npcWidth * BERSERKER_SCALE, this.npcHeight * BERSERKER_SCALE);
        this.npcShield = LOTRShields.ALIGNMENT_URUMNO_OUTFIT;
        this.npcCape = LOTRCapes.ALIGNMENT_UTUMNO.capeTexture;
        this.isImmuneToFrost = true;
        this.isImmuneToFire = true;
        this.isStrongOrc = true;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 20 == 0) {
            this.heal(0.5f);
        }
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.6, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)40, (int)50));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23);
        this.getEntityAttribute(npcAttackDamageExtra).setBaseValue(1.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(2);
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerBoldog));
        } else if (i == 1 || i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordBoldog));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsboldog));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsboldog));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyboldog));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetboldog));
        return data;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "angband/orc/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 100.0f) {
                return "angband/orc/friendly";
            }
            return "angband/orc/neutral";
        }
        return "angband/orc/hostile";
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killBoldog;
    }

    @Override
    public float getAlignmentBonus() {
        return 8.0f;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            List entities;
            int difficulty;
            int duration;
            boolean flag = super.attackEntityAsMob(entity);
            if (!this.worldObj.isRemote && flag) {
                entity.setFire(4);
                return flag;
            }
            if (entity instanceof EntityLivingBase && (duration = (difficulty = this.worldObj.difficultySetting.getDifficultyId()) * (difficulty + 5) / 2) > 0) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration * 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.weakness.id, duration * 20, 0));
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, duration * 20, 0));
            }
            float attackDamage = (float)this.getEntityAttribute(LOTREntityNPC.npcAttackDamage).getAttributeValue();
            float knockbackModifier = 1.25f * attackDamage;
            entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f), 0.0, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockbackModifier * 0.5f));
            this.worldObj.playSoundAtEntity(entity, "lotr:troll.ologHai_hammer", 0.9f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            if (!this.worldObj.isRemote && !(entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entity.boundingBox.expand(0.5, 0.5, 0.5))).isEmpty()) {
                for (Object entitie : entities) {
                    float f;
                    EntityLivingBase hitEntity = (EntityLivingBase)entitie;
                    if (hitEntity == this || hitEntity == entity || !LOTRMod.canNPCAttackEntity(this, hitEntity, false)) continue;
                    float strength = 4.0f - entity.getDistanceToEntity((Entity)hitEntity);
                    strength += 4.0f;
                    if (f > 4.0f) {
                        strength = 8.0f;
                    }
                    if (!hitEntity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), strength / 8.0f * attackDamage)) continue;
                    float knockback = strength * 0.0f;
                    if (knockback < 0.75f) {
                        knockback = 0.75f;
                    }
                    hitEntity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f), 0.2 + 0.12 * (double)knockback, (double)(MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f));
                }
                return flag;
            }
            return true;
        }
        return false;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 2.0f, false);
            this.setDead();
        }
    }

    @Override
    public LOTRFaction getHiringFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public int getMercBaseCost() {
        return 150;
    }

    @Override
    public float getMercAlignmentRequired() {
        return 2000.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 0.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.hireMoredainMercenary);
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.9f;
    }
}

