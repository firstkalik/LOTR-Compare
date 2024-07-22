/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.EnumAction
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
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTRItemDagger5
extends LOTRItemSword {
    private DaggerEffect effect;
    public static float explosion = 0.1f;

    public LOTRItemDagger5(LOTRMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger5(Item.ToolMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger5(LOTRMaterial material, DaggerEffect e) {
        this(material.toToolMaterial(), e);
    }

    public LOTRItemDagger5(Item.ToolMaterial material, DaggerEffect e) {
        super(material);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemDagger5.class, 0.5f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemDagger5.class, 2.0f);
        this.lotrWeaponDamage = material.getDamageVsEntity() + 4.0f;
        this.effect = e;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.none;
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
            LOTRItemDagger5.applyStandardPoison(hitEntity);
        }
        if (this.effect == DaggerEffect.WITHER) {
            LOTRItemDagger5.applyStandardPoison1(hitEntity);
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

    public static enum DaggerEffect {
        NONE,
        POISON,
        WITHER;

    }

}

