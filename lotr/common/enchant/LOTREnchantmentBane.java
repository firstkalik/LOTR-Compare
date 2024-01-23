/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 */
package lotr.common.enchant;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentDamage;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentBane
extends LOTREnchantmentDamage {
    private List<Class<? extends EntityLivingBase>> entityClasses;
    private EnumCreatureAttribute entityAttribute;
    public final float baneDamage;
    public boolean isAchievable = true;

    private LOTREnchantmentBane(String s, float boost) {
        super(s, 0.0f);
        this.baneDamage = boost;
        this.setValueModifier((10.0f + this.baneDamage) / 10.0f);
        this.setPersistsReforge();
        this.setBypassAnvilLimit();
    }

    public LOTREnchantmentBane(String s, float boost, Class<? extends EntityLivingBase> ... classes) {
        this(s, boost);
        this.entityClasses = Arrays.asList(classes);
    }

    public LOTREnchantmentBane(String s, float boost, EnumCreatureAttribute attr) {
        this(s, boost);
        this.entityAttribute = attr;
    }

    public LOTREnchantmentBane setUnachievable() {
        this.isAchievable = false;
        return this;
    }

    public boolean isEntityType(EntityLivingBase entity) {
        if (this.entityClasses != null) {
            for (Class<? extends EntityLivingBase> cls : this.entityClasses) {
                if (!cls.isAssignableFrom(entity.getClass())) continue;
                return true;
            }
        } else if (this.entityAttribute != null) {
            return entity.getCreatureAttribute() == this.entityAttribute;
        }
        return false;
    }

    @Override
    public float getBaseDamageBoost() {
        return 0.0f;
    }

    @Override
    public float getEntitySpecificDamage(EntityLivingBase entity) {
        if (this.isEntityType(entity)) {
            return this.baneDamage;
        }
        return 0.0f;
    }

    public int getRandomKillsRequired(Random random) {
        return MathHelper.getRandomIntegerInRange((Random)random, (int)100, (int)250);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted((String)("lotr.enchant." + this.enchantName + ".desc"), (Object[])new Object[]{this.formatAdditive(this.baneDamage)});
    }

    @Override
    public boolean isBeneficial() {
        return true;
    }
}

