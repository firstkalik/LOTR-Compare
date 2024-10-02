/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.block.Block;
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

public class LOTREntityWildBoar
extends LOTREntityHorse {
    public LOTREntityWildBoar(World world) {
        super(world);
        this.setSize(0.9f, 0.8f);
    }

    @Override
    protected boolean isMountHostile() {
        return true;
    }

    @Override
    protected EntityAIBase createMountAttackAI() {
        return new LOTREntityAIAttackOnCollide((EntityCreature)this, 1.2, true);
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
        maxHealth = Math.min(maxHealth, 25.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed *= 1.0);
    }

    @Override
    protected double clampChildHealth(double health) {
        return MathHelper.clamp_double((double)health, (double)10.0, (double)30.0);
    }

    @Override
    protected double clampChildJump(double jump) {
        return MathHelper.clamp_double((double)jump, (double)0.3, (double)1.0);
    }

    @Override
    protected double clampChildSpeed(double speed) {
        return MathHelper.clamp_double((double)speed, (double)0.08, (double)0.35);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.carrot;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        int meat = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + i);
        for (int l = 0; l < meat; ++l) {
            if (this.isBurning()) {
                this.dropItem(Items.cooked_porkchop, 1);
                continue;
            }
            this.dropItem(Items.porkchop, 1);
        }
        int k = 1 + this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int j = 0; j < k; ++j) {
            this.dropItem(Items.leather, 1);
        }
    }

    protected String getLivingSound() {
        return "mob.pig.say";
    }

    protected String getHurtSound() {
        return "mob.pig.say";
    }

    protected String getDeathSound() {
        return "mob.pig.death";
    }

    protected String getAngrySoundName() {
        return "mob.pig.say";
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        this.playSound("mob.pig.step", 0.15f, 1.0f);
    }
}

