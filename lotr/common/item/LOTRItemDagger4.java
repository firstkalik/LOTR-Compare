/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTRItemDagger4
extends LOTRItemSword {
    private DaggerEffect effect;
    public static float explosion = 0.1f;
    private float efficiencyOnProperMaterial;

    public LOTRItemDagger4(LOTRMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger4(Item.ToolMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger4(LOTRMaterial material, DaggerEffect e) {
        this(material.toToolMaterial(), e);
    }

    public LOTRItemDagger4(Item.ToolMaterial material, DaggerEffect e) {
        super(material);
        LOTRWeaponStats.registerMeleeSpeed(LOTRItemDagger4.class, 0.75f);
        LOTRWeaponStats.registerMeleeReach(LOTRItemDagger4.class, 1.0f);
        this.lotrWeaponDamage += 2.0f;
        this.effect = e;
    }

    public float func_150893_a(ItemStack itemstack, Block block) {
        float f = super.func_150893_a(itemstack, block);
        if (f == 1.0f && block != null && (block.getMaterial() == Material.wood || block.getMaterial() == Material.plants || block.getMaterial() == Material.vine)) {
            return this.efficiencyOnProperMaterial;
        }
        return f;
    }

    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int i, int j, int k, EntityLivingBase entity) {
        if ((double)block.getBlockHardness(world, i, j, k) != 0.0) {
            itemstack.damageItem(1, entity);
        }
        return true;
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
            LOTRItemDagger4.applyStandardPoison(hitEntity);
        }
        if (this.effect == DaggerEffect.WITHER) {
            LOTRItemDagger4.applyStandardPoison1(hitEntity);
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

