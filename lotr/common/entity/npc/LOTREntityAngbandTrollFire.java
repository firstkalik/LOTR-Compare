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
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTREntityAngbandTrollFire
extends LOTREntityTroll {
    public LOTREntityAngbandTrollFire(World world) {
        super(world);
        this.trollImmuneToSun = true;
        this.isImmuneToFire = true;
    }

    @Override
    public float getTrollScale() {
        return 1.5f;
    }

    @Override
    public EntityAIBase getTrollAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 2.0, false);
    }

    @Override
    public int getTotalArmorValue() {
        return 5;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(7.0);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
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
    public float getAlignmentBonus() {
        return 2.0f;
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
        return LOTRAchievement.killFireTroll;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.worldObj.spawnParticle("flame", this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);
        if (!this.worldObj.isRemote && flag) {
            entity.setFire(5);
        }
        return flag;
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

