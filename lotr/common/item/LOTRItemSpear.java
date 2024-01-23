/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IRegistry
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import java.util.Random;
import lotr.common.dispenser.LOTRDispenseSpear;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.projectile.LOTREntitySpear;
import lotr.common.item.LOTRItemSword;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.BlockDispenser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.world.World;

public class LOTRItemSpear
extends LOTRItemSword {
    public LOTRItemSpear(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemSpear(Item.ToolMaterial material) {
        super(material);
        this.lotrWeaponDamage -= 1.0f;
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseSpear());
    }

    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i) {
        int fireAspect;
        if (entityplayer.getHeldItem() != itemstack) {
            return;
        }
        int useTick = this.getMaxItemUseDuration(itemstack) - i;
        float charge = (float)useTick / (float)this.getMaxDrawTime();
        if (charge < 0.1f) {
            return;
        }
        charge = (charge * charge + charge * 2.0f) / 3.0f;
        charge = Math.min(charge, 1.0f);
        LOTREntitySpear spear = new LOTREntitySpear(world, (EntityLivingBase)entityplayer, itemstack.copy(), charge * 2.0f);
        if (charge >= 1.0f) {
            spear.setIsCritical(true);
        }
        if ((fireAspect = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.flame.effectId, (ItemStack)itemstack) + LOTREnchantmentHelper.calcFireAspect(itemstack)) > 0) {
            spear.setFire(100);
        }
        for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
            if (!ench.applyToProjectile() || !LOTREnchantmentHelper.hasEnchant(itemstack, ench)) continue;
            LOTREnchantmentHelper.setProjectileEnchantment(spear, ench);
        }
        if (entityplayer.capabilities.isCreativeMode) {
            spear.canBePickedUp = 2;
        }
        world.playSoundAtEntity((Entity)entityplayer, "random.bow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + charge * 0.5f);
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)spear);
        }
        if (!entityplayer.capabilities.isCreativeMode) {
            --itemstack.stackSize;
            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            }
        }
    }

    public int getMaxDrawTime() {
        return 20;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 72000;
    }

    public float getRangedDamageMultiplier(ItemStack itemstack, Entity shooter, Entity hit) {
        float damage = this.getLOTRWeaponDamage();
        damage = shooter instanceof EntityLivingBase && hit instanceof EntityLivingBase ? (damage += EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)((EntityLivingBase)shooter), (EntityLivingBase)((EntityLivingBase)hit))) : (damage += EnchantmentHelper.func_152377_a((ItemStack)itemstack, (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED));
        return damage * 0.7f;
    }
}

