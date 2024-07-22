/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IRegistry
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.dispenser.LOTRDispenseThrowingAxe;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.projectile.LOTREntityThrowingAxe;
import lotr.common.item.LOTRMaterial;
import lotr.common.recipe.LOTRRecipes;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.world.World;

public class LOTRItemThrowingAxe
extends Item {
    private Item.ToolMaterial axeMaterial;

    public LOTRItemThrowingAxe(LOTRMaterial material) {
        this(material.toToolMaterial());
    }

    public LOTRItemThrowingAxe(Item.ToolMaterial material) {
        this.axeMaterial = material;
        this.setMaxStackSize(1);
        this.setMaxDamage(material.getMaxUses());
        this.setFull3D();
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseThrowingAxe());
    }

    public Item.ToolMaterial getAxeMaterial() {
        return this.axeMaterial;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        LOTREntityThrowingAxe axe = new LOTREntityThrowingAxe(world, (EntityLivingBase)entityplayer, itemstack.copy(), 2.0f);
        axe.setIsCritical(true);
        int fireAspect = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.flame.effectId, (ItemStack)itemstack) + LOTREnchantmentHelper.calcFireAspect(itemstack);
        if (fireAspect > 0) {
            axe.setFire(100);
        }
        for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
            if (!ench.applyToProjectile() || !LOTREnchantmentHelper.hasEnchant(itemstack, ench)) continue;
            LOTREnchantmentHelper.setProjectileEnchantment(axe, ench);
        }
        if (entityplayer.capabilities.isCreativeMode) {
            axe.canBePickedUp = 2;
        }
        world.playSoundAtEntity((Entity)entityplayer, "random.bow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + 0.25f);
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)axe);
        }
        if (!entityplayer.capabilities.isCreativeMode) {
            --itemstack.stackSize;
        }
        return itemstack;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        if (LOTRRecipes.checkItemEquals(this.axeMaterial.getRepairItemStack(), repairItem)) {
            return true;
        }
        return super.getIsRepairable(itemstack, repairItem);
    }

    public float getRangedDamageMultiplier(ItemStack itemstack, Entity shooter, Entity hit) {
        float damage = this.axeMaterial.getDamageVsEntity() + 4.0f;
        damage = shooter instanceof EntityLivingBase && hit instanceof EntityLivingBase ? (damage = damage + EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)((EntityLivingBase)shooter), (EntityLivingBase)((EntityLivingBase)hit))) : (damage = damage + EnchantmentHelper.func_152377_a((ItemStack)itemstack, (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED));
        return damage * 0.5f;
    }
}

