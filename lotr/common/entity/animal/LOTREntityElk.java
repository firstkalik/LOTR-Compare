/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityElk
extends LOTREntityHorse
implements LOTRRandomSkinEntity {
    public LOTREntityElk(World world) {
        super(world);
        this.setSize(1.6f, 1.8f);
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    @Override
    protected boolean isMountHostile() {
        return true;
    }

    @Override
    protected EntityAIBase createMountAttackAI() {
        return new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.25, true);
    }

    public int getHorseType() {
        return 0;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
    }

    @Override
    protected void onLOTRHorseSpawn() {
        double maxHealth = this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth *= (double)(1.0f + this.rand.nextFloat() * 0.5f));
    }

    @Override
    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)16.0, (double)50.0);
    }

    @Override
    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.3, (double)1.0);
    }

    @Override
    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.08, (double)0.34);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.wheat;
    }

    protected void dropFewItems(boolean flag, int i) {
        int hide = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < hide; ++l) {
            this.dropItem(Items.leather, 1);
        }
        int meat = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            if (this.isBurning()) {
                this.dropItem(LOTRMod.deerCooked, 1);
                continue;
            }
            this.dropItem(LOTRMod.deerRaw, 1);
        }
    }

    protected String getLivingSound() {
        return "lotr:elk.say";
    }

    protected String getHurtSound() {
        return "lotr:elk.hurt";
    }

    protected String getDeathSound() {
        return "lotr:elk.death";
    }
}

