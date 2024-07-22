/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class lesserlight
extends LOTRItemBaseRing2 {
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entity) {
        entity.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        float var4 = 1.0f;
        int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
        int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62 - (double)entity.yOffset);
        int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);
        entity.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 60, 0));
        world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i + 1), (double)j, (double)(k - 1)));
        world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i - 3), (double)j, (double)(k - 1)));
        world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i - 1), (double)j, (double)(k + 1)));
        world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)(i - 1), (double)j, (double)(k - 3)));
        itemstack.damageItem(50, (EntityLivingBase)entity);
        entity.attackEntityFrom(DamageSource.generic, 2.0f);
        LOTRLevelData.getData(entity).addAchievement(LOTRAchievement.useRing);
        return itemstack;
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

