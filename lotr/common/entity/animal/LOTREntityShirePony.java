/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRReflection;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityShirePony
extends LOTREntityHorse {
    public static float PONY_SCALE = 0.8f;
    public boolean breedingFlag = false;

    public LOTREntityShirePony(World world) {
        super(world);
        this.setSize(this.width * PONY_SCALE, this.height * PONY_SCALE);
    }

    public int getHorseType() {
        if (this.breedingFlag) {
            return 0;
        }
        return this.worldObj.isRemote ? 0 : 1;
    }

    public boolean func_110259_cr() {
        return false;
    }

    @Override
    protected void onLOTRHorseSpawn() {
        double maxHealth = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth *= 0.75);
        double jumpStrength = this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).getAttributeValue();
        this.getEntityAttribute(LOTRReflection.getHorseJumpStrength()).setBaseValue(jumpStrength *= 0.5);
        double moveSpeed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(moveSpeed *= 0.8);
    }

    @Override
    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)10.0, (double)28.0);
    }

    @Override
    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.2, (double)1.0);
    }

    @Override
    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.08, (double)0.3);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable other) {
        LOTREntityShirePony otherPony = (LOTREntityShirePony)other;
        this.breedingFlag = true;
        otherPony.breedingFlag = true;
        EntityAgeable child = super.createChild((EntityAgeable)otherPony);
        this.breedingFlag = false;
        otherPony.breedingFlag = false;
        return child;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)this.riddenByEntity;
            if (this.isHorseSaddled() && this.isChested()) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.rideShirePony);
            }
        }
    }

    protected String getLivingSound() {
        return "mob.horse.idle";
    }

    protected String getHurtSound() {
        return "mob.horse.hit";
    }

    protected String getDeathSound() {
        return "mob.horse.death";
    }

    protected String getAngrySoundName() {
        return "mob.horse.angry";
    }
}

