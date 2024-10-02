/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class thranduilsilver
extends LOTRItemBaseRing2 {
    public static int cooldown = 10;
    public static int defaultCharges = 100;

    public thranduilsilver() {
        this.setMaxDamage(defaultCharges + 1);
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        int i = (int)entity.posX;
        int j = (int)entity.posY;
        int k = (int)entity.posZ;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 120, 0));
        }
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.silver;
    }
}

