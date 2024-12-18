/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCustomPotion;
import lotr.common.LOTRMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class LOTRPotionRepair
extends LOTRCustomPotion {
    public static int repairTime = 120;
    private static final Item[] repairExceptions = new Item[]{LOTRMod.totemOfUndyingPlus, LOTRMod.magicCloverPlus};

    public LOTRPotionRepair(int id, boolean isBadEffect, int potionColor, ResourceLocation tex, String namePot) {
        super(32, false, 7829367, tex, namePot);
        this.setPotionName("potion.lotr.repair");
        this.setIconIndex(0, 2);
    }

    public boolean isReady(int tick, int level) {
        int freq = 5 >> level;
        return freq > 0 && tick % freq == 0;
    }

    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (entity.ticksExisted % repairTime == 0) {
            for (int i = 0; i < 5; ++i) {
                ItemStack stack = entity.getEquipmentInSlot(i);
                if (stack == null || !stack.isItemStackDamageable() || stack.getItemDamage() <= 0 || this.isInRepairExceptions(stack.getItem())) continue;
                int damage = Math.max(0, stack.getItemDamage() - 3);
                stack.setItemDamage(damage);
            }
        }
    }

    private boolean isInRepairExceptions(Item item) {
        for (Item exception : repairExceptions) {
            if (item != exception) continue;
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getStatusIconIndex() {
        return 0;
    }
}

