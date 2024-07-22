/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class aule2
extends ItemBow {
    private boolean isCharging = false;

    public aule2() {
        this.setUnlocalizedName("my_bow");
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setMaxStackSize(1);
        this.setMaxDamage(384);
    }

    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        EntityPlayer player;
        if (isSelected && entity instanceof EntityPlayer && (player = (EntityPlayer)entity).isUsingItem() && player.getItemInUse() == itemStack) {
            boolean flameEnchant;
            int duration = itemStack.getMaxItemUseDuration() - player.getItemInUseCount();
            float charge = (float)duration / 20.0f;
            if ((double)(charge = (charge * charge + charge * 2.0f) / 3.0f) < 0.1) {
                return;
            }
            if (charge > 1.0f) {
                charge = 1.0f;
            }
            int powerLevel = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.power.effectId, (ItemStack)itemStack);
            int punchLevel = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.punch.effectId, (ItemStack)itemStack);
            boolean bl = flameEnchant = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.flame.effectId, (ItemStack)itemStack) > 0;
            while (player.inventory.hasItem(Items.arrow)) {
                EntityArrow entityArrow = new EntityArrow(world, (EntityLivingBase)player, charge * 2.0f);
                if (charge >= 0.1f) {
                    entityArrow.setIsCritical(true);
                }
                if (powerLevel > 0) {
                    entityArrow.setDamage(entityArrow.getDamage() + (double)powerLevel * 0.5 + 0.5);
                }
                if (punchLevel > 0) {
                    entityArrow.setKnockbackStrength(punchLevel);
                }
                if (flameEnchant) {
                    entityArrow.setFire(100);
                }
                itemStack.damageItem(1, (EntityLivingBase)player);
                world.playSoundAtEntity((Entity)player, "random.bow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + charge * 0.5f);
                if (player.capabilities.isCreativeMode) {
                    entityArrow.canBePickedUp = 2;
                } else {
                    entityArrow.canBePickedUp = 1;
                    player.inventory.consumeInventoryItem(Items.arrow);
                }
                if (world.isRemote) continue;
                world.spawnEntityInWorld((Entity)entityArrow);
            }
        }
    }

    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.bow;
    }

    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        return itemStack;
    }

    public int getItemEnchantability() {
        return 1;
    }
}

