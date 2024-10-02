/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
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
import lotr.common.LOTRPotions;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTRItemHammer
extends LOTRItemSword {
    public LOTRItemHammer(LOTRMaterial material) {
        super(material.toToolMaterial());
        this.lotrWeaponDamage += 2.5f;
    }

    public LOTRItemHammer(Item.ToolMaterial material) {
        super(material);
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.none;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        World world = attacker.worldObj;
        if (!world.isRemote && world.rand.nextFloat() <= 0.1f && !target.isPotionActive(LOTRPotions.broken)) {
            EnumDifficulty difficulty = world.difficultySetting;
            int baseDuration = 1 + difficulty.getDifficultyId() * 2;
            int duration = (baseDuration + world.rand.nextInt(baseDuration)) * 60;
            world.playSoundAtEntity((Entity)target, "lotr:misc.bone", 1.0f, 1.0f);
            target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration));
            target.addPotionEffect(new PotionEffect(LOTRPotions.broken.id, duration));
        }
        return super.hitEntity(stack, target, attacker);
    }
}

