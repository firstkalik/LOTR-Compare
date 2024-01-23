/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.LOTRReflection;
import lotr.common.item.LOTRItemMug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class LOTRItemMugTauredainCure
extends LOTRItemMug {
    public LOTRItemMugTauredainCure() {
        super(true, false);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ItemStack result = super.onEaten(itemstack, world, entityplayer);
        if (!world.isRemote) {
            for (int i = 0; i < Potion.potionTypes.length; ++i) {
                Potion potion = Potion.potionTypes[i];
                if (potion == null || !LOTRReflection.isBadEffect(potion)) continue;
                entityplayer.removePotionEffect(potion.id);
            }
        }
        return result;
    }
}

