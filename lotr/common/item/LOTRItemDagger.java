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

    public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase user) {
        itemstack.damageItem(1, user);
        if (this.effect == DaggerEffect.NONE) {
            return true;
        }
        if (this.effect == DaggerEffect.POISON) {
            LOTRItemDagger.applyStandardPoison(hitEntity);
        }
        return true;
    }

    public static void applyStandardPoison(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poison = new PotionEffect(Potion.poison.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poison);
    }

    public static enum DaggerEffect {
        NONE,
        POISON;

    }

}

