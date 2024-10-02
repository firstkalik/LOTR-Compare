/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRPotions;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTRItemDagger
extends LOTRItemSword {
    private DaggerEffect effect;

    public LOTRItemDagger(LOTRMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger(Item.ToolMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger(LOTRMaterial material, DaggerEffect e) {
        this(material.toToolMaterial(), e);
    }

    public LOTRItemDagger(Item.ToolMaterial material, DaggerEffect e) {
        super(material);
        this.lotrWeaponDamage -= 3.0f;
        this.effect = e;
    }

    public DaggerEffect getDaggerEffect() {
        return this.effect;
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase user) {
        itemstack.damageItem(1, user);
        if (this.effect == DaggerEffect.NONE) {
            return true;
        }
        if (this.effect == DaggerEffect.POISON) {
            LOTRItemDagger.applyStandardPoison(hitEntity);
        }
        if (this.effect == DaggerEffect.WITHER) {
            LOTRItemDagger.applyStandardPoison1(hitEntity);
        }
        if (this.effect == DaggerEffect.WEAK) {
            LOTRItemDagger.applyStandardWeak(hitEntity);
        }
        if (this.effect == DaggerEffect.SLOWNESS) {
            LOTRItemDagger.applyStandardSlow(hitEntity);
        }
        if (this.effect == DaggerEffect.SLOWNESSM) {
            LOTRItemDagger.applyStandardSlow1(hitEntity);
        }
        if (this.effect == DaggerEffect.HUNGER) {
            LOTRItemDagger.applyStandardHunger(hitEntity);
        }
        if (this.effect == DaggerEffect.BLOOD) {
            LOTRItemDagger.applyStandardBlood(hitEntity);
        }
        if (this.effect == DaggerEffect.BLOOD1) {
            LOTRItemDagger.applyStandardBlood1(hitEntity);
        }
        if (this.effect == DaggerEffect.EXPLOSION) {
            LOTRItemDagger.applyStandardExplosion(hitEntity);
        }
        return true;
    }

    public static void applyStandardPoison(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison = new PotionEffect(Potion.poison.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poison);
    }

    public static void applyStandardPoison1(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison = new PotionEffect(Potion.wither.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poison);
    }

    public static void applyStandardSlow(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison1 = new PotionEffect(Potion.moveSlowdown.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poison1);
    }

    public static void applyStandardSlow1(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison1 = new PotionEffect(Potion.moveSlowdown.id, (duration + itemRand.nextInt(duration)) * 20);
        PotionEffect poison2 = new PotionEffect(Potion.poison.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poison1);
        entity.addPotionEffect(poison2);
    }

    public static void applyStandardWeak(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison2 = new PotionEffect(LOTRPotions.vulnerability.id, (duration + itemRand.nextInt(duration)) * 20);
        PotionEffect poison21 = new PotionEffect(Potion.weakness.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poison21);
    }

    public static void applyStandardHunger(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 8 + difficulty.getDifficultyId() * 2;
        PotionEffect hungerEffect = new PotionEffect(Potion.hunger.id, (duration + itemRand.nextInt(duration)) * 20, 26);
        PotionEffect poison2 = new PotionEffect(LOTRPotions.infection.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(hungerEffect);
    }

    public static void applyStandardBlood(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 4 + difficulty.getDifficultyId() * 2;
        int chance = entity.worldObj.rand.nextInt(100);
        PotionEffect poisonEffect = new PotionEffect(Potion.poison.id, (duration + itemRand.nextInt(duration)) * 20);
        if (chance <= 30) {
            PotionEffect bloodEffect = new PotionEffect(LOTRPotions.blood.id, (duration + entity.worldObj.rand.nextInt(duration)) * 20);
            entity.addPotionEffect(bloodEffect);
        }
        entity.addPotionEffect(poisonEffect);
    }

    public static void applyStandardBlood1(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 4 + difficulty.getDifficultyId() * 2;
        int chance = entity.worldObj.rand.nextInt(100);
        if (chance <= 30) {
            PotionEffect bloodEffect = new PotionEffect(LOTRPotions.blood.id, (duration + entity.worldObj.rand.nextInt(duration)) * 20);
            entity.addPotionEffect(bloodEffect);
        }
    }

    public static void applyStandardExplosion(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison4 = new PotionEffect(LOTRPotions.explode.id, 0, 0);
        int ticks = 1;
        poison4.combine(new PotionEffect(LOTRPotions.explode.id, ticks));
        entity.addPotionEffect(poison4);
    }

    public static enum DaggerEffect {
        NONE,
        POISON,
        BLOOD,
        BLOOD1,
        WITHER,
        SLOWNESS,
        SLOWNESSM,
        WEAK,
        HUNGER,
        EXPLOSION;

    }

}

