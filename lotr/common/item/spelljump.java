/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.item.SpellBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class spelljump
extends SpellBase {
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity) {
        float var4 = 1.0f;
        int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
        int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62 - (double)entity.yOffset);
        int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);
        if (entity instanceof EntityLivingBase) {
            entity.addPotionEffect(new PotionEffect(8, 2400, 0));
        }
        entity.attackEntityFrom(DamageSource.generic, 2.0f);
        itemStack.damageItem(8, (EntityLivingBase)entity);
        return itemStack;
    }
}

