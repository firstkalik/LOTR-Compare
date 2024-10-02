/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentSeaFortune
extends LOTREnchantment {
    public final int luckFactor;

    public LOTREnchantmentSeaFortune(String s, int luck) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.TOOL});
        this.luckFactor = luck;
        this.setValueModifier(this.luckFactor);
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        return itemstack.getItem() instanceof ItemFishingRod;
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.seaFortune.desc", (Object[])new Object[]{this.luckFactor * 5});
    }

    @Override
    public boolean isBeneficial() {
        return (float)this.luckFactor >= 1.0f;
    }
}

