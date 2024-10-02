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

public class LOTREnchantmentFishingSpeed
extends LOTREnchantment {
    public final int speedFactor;

    public LOTREnchantmentFishingSpeed(String s, int speed) {
        super(s, new LOTREnchantmentType[]{LOTREnchantmentType.TOOL});
        this.speedFactor = speed;
        this.setValueModifier(this.speedFactor);
    }

    @Override
    public boolean canApply(ItemStack itemstack, boolean considering) {
        boolean canApply = false;
        if (itemstack.getItem() instanceof ItemFishingRod) {
            canApply = true;
        }
        return canApply;
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.fishingSpeed.desc", (Object[])new Object[]{this.speedFactor * 5});
    }

    @Override
    public boolean isBeneficial() {
        return (float)this.speedFactor >= 1.0f;
    }
}

