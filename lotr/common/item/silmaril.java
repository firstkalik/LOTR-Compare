/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemBaseRing2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class silmaril
extends LOTRItemBaseRing2 {
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (entity instanceof EntityPlayer) {
            float high_elf = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.HIGH_ELF);
            float high_elf1 = LOTRLevelData.getData((EntityPlayer)entity).getAlignment(LOTRFaction.LOTHLORIEN);
            if (high_elf <= 1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 0, 0));
            }
            if (high_elf1 <= 1000.0f) {
                ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 0, 0));
            }
        }
    }

    @Override
    public int getUseCost() {
        return 0;
    }

    @Override
    public int getBaseRepairCost() {
        return 0;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }
}

