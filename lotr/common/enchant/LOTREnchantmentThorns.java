/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import java.util.Random;
import lotr.common.enchant.LOTREnchantmentProtectionSpecial;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentThorns
extends LOTREnchantmentProtectionSpecial {
    private static final String __OBFID = "CL_00000122";

    public LOTREnchantmentThorns(String s, int level) {
        super(s, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)"lotr.enchant.thorns.desc", (Object[])new Object[]{this.formatAdditiveInt(this.calcIntProtection())});
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        return source.isProjectile() || source.isMagicDamage();
    }

    @Override
    public boolean isBeneficial() {
        return this.protectLevel >= 0;
    }

    @Override
    protected int calcIntProtection() {
        return 1 + this.protectLevel;
    }

    public void func_151367_b(EntityLivingBase p_151367_1_, Entity p_151367_2_, int p_151367_3_) {
        Random random = p_151367_1_.getRNG();
        ItemStack itemstack = EnchantmentHelper.func_92099_a((Enchantment)Enchantment.thorns, (EntityLivingBase)p_151367_1_);
        if (LOTREnchantmentThorns.func_92094_a1(p_151367_3_, random)) {
            p_151367_2_.attackEntityFrom(DamageSource.causeThornsDamage((Entity)p_151367_1_), (float)LOTREnchantmentThorns.func_92095_b1(p_151367_3_, random));
            p_151367_2_.playSound("damage.thorns", 0.5f, 1.0f);
            if (itemstack != null) {
                itemstack.damageItem(3, p_151367_1_);
            }
        } else if (itemstack != null) {
            itemstack.damageItem(1, p_151367_1_);
        }
    }

    public static boolean func_92094_a1(int p_92094_0_, Random p_92094_1_) {
        return p_92094_0_ <= 0 ? false : p_92094_1_.nextFloat() < 0.15f * (float)p_92094_0_;
    }

    public static int func_92095_b1(int p_92095_0_, Random p_92095_1_) {
        return p_92095_0_ > 10 ? p_92095_0_ - 10 : 1 + p_92095_1_.nextInt(4);
    }

    public static boolean func_92094_a(int level, Random random) {
        return level <= 0 ? false : random.nextFloat() < 0.15f * (float)level;
    }

    public static int func_92095_b(int level, Random random) {
        return level > 10 ? level - 10 : 1 + random.nextInt(4);
    }
}

