/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 */
package lotr;

import lotr.EnchantmentSoulbound;
import net.minecraft.enchantment.Enchantment;

public class Enchantments {
    public static int soulboundID = 160;
    public static Enchantment soulbound;

    public static void init() {
        soulbound = new EnchantmentSoulbound(soulboundID, 1);
    }
}

