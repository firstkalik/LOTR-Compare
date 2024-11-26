/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRPotions;
import lotr.common.item.LOTRItemMug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LOTRItemMugDragonBrew
extends LOTRItemMug {
    public LOTRItemMugDragonBrew(float f) {
        super(f);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ItemStack result = super.onEaten(itemstack, world, entityplayer);
        if (!world.isRemote) {
            PotionEffect effect;
            int randomEffect = world.rand.nextInt(3);
            if (randomEffect == 0) {
                effect = new PotionEffect(LOTRPotions.dragon.id, 1200);
                effect = new PotionEffect(LOTRPotions.luck.id, 1200);
            } else {
                effect = randomEffect == 1 ? new PotionEffect(LOTRPotions.unluck.id, 1200) : new PotionEffect(Potion.fireResistance.id, 1200);
            }
            entityplayer.addPotionEffect(effect);
        }
        return result;
    }
}

