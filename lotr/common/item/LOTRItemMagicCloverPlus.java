/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemMagicClover;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRItemMagicCloverPlus
extends LOTRItemMagicClover {
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.translateToLocal((String)"magic.name"));
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    public int getMaxDamage(ItemStack stack) {
        return 3;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.mithrilNugget;
    }

    public boolean isDamageable() {
        return true;
    }
}

