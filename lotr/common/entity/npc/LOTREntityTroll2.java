/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.item.LOTREntityArrowExplosion;
import lotr.common.entity.item.LOTREntityArrowHunger;
import lotr.common.entity.item.LOTREntityArrowMorgul;
import lotr.common.entity.item.LOTREntityArrowPoisoned;
import lotr.common.entity.item.LOTREntityArrowSlow;
import lotr.common.entity.item.LOTREntityArrowWeak;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.entity.projectile.LOTREntityCrossbowBolt;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTREntityArrowFire;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTREntityTroll2
extends LOTREntityTroll {
    public LOTREntityTroll2(World world) {
        super(world);
    }

    @Override
    public float getTrollScale() {
        return 1.5f;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getEntity() instanceof EntityArrow) {
            LOTREntityCrossbowBolt arrow1;
            EntityArrow arrow = (EntityArrow)source.getEntity();
            if ((arrow instanceof LOTREntityArrowPoisoned || arrow instanceof LOTREntityArrowMorgul || arrow instanceof LOTREntityArrowFire || arrow instanceof LOTREntityArrowWeak || arrow instanceof LOTREntityArrowSlow || arrow instanceof LOTREntityArrowHunger || arrow instanceof LOTREntityArrowExplosion) && (double)this.getHealth() <= (double)this.getMaxHealth() * 0.5) {
                return false;
            }
            if (source.getEntity() instanceof LOTREntityCrossbowBolt && (arrow1 = (LOTREntityCrossbowBolt)source.getEntity()) instanceof LOTREntityCrossbowBolt && (double)this.getHealth() <= (double)this.getMaxHealth() * 0.5) {
                return false;
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public EntityAIBase getTrollAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 2.0, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(7.0);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    protected boolean hasTrollName() {
        return false;
    }

    @Override
    protected boolean canTrollBeTickled(EntityPlayer entityplayer) {
        return false;
    }

    @Override
    public void onTrollDeathBySun() {
        this.worldObj.playSoundAtEntity((Entity)this, "lotr:troll.transform", this.getSoundVolume(), this.getSoundPitch());
        this.worldObj.setEntityState((Entity)this, (byte)15);
        this.setDead();
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleHealthUpdate(byte b) {
        if (b == 15) {
            super.handleHealthUpdate(b);
            for (int l = 0; l < 64; ++l) {
                LOTRMod.proxy.spawnParticle("largeStone", this.posX + this.rand.nextGaussian() * (double)this.width * 0.5, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + this.rand.nextGaussian() * (double)this.width * 0.5, 0.0, 0.0, 0.0);
            }
        } else {
            super.handleHealthUpdate(b);
        }
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killTroll;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityplayer) {
        return 5 + this.rand.nextInt(6);
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return null;
    }
}

