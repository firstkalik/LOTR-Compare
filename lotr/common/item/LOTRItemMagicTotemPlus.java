/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemMagicTotem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRItemMagicTotemPlus
extends LOTRItemMagicTotem {
    public LOTRItemMagicTotemPlus() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
        this.maxStackSize = 1;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.translateToLocal((String)"totem.name"));
    }

    public int getMaxDamage(ItemStack stack) {
        return 2;
    }

    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.mithrilNugget;
    }

    public boolean isDamageable() {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }
}

