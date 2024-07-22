/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityGundabadCaveTroll
extends LOTREntityTroll {
    private boolean isWeakOrc;

    public LOTREntityGundabadCaveTroll(World world) {
        super(world);
        this.trollImmuneToSun = true;
        this.isWeakOrc = true;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote || this.getAttackTarget() == null) {
            // empty if block
        }
        if (!this.worldObj.isRemote && this.isWeakOrc) {
            boolean flag;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.boundingBox.minY);
            int k = MathHelper.floor_double((double)this.posZ);
            BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
            boolean bl = flag = this.worldObj.isDaytime() && this.worldObj.canBlockSeeTheSky(i, j, k);
            if (biome instanceof LOTRBiome && ((LOTRBiome)biome).canSpawnHostilesInDay()) {
                flag = false;
            }
            if (flag && this.ticksExisted % 20 == 0) {
                this.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, -1));
                this.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200));
            }
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
        this.getEntityAttribute(npcAttackDamage).setBaseValue(6.0);
        this.getEntityAttribute(npcAttackDamageExtra).setBaseValue(1.0);
    }

    @Override
    public int getTotalArmorValue() {
        return 13;
    }

    @Override
    public float getTrollScale() {
        return 0.85f;
    }

    @Override
    protected boolean hasTrollName() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return null;
    }

    @Override
    public float getAlignmentBonus() {
        return 4.0f;
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

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killCaveTroll;
    }
}

