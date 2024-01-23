/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StringUtils
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.entity.npc.LOTREntityHalfTroll;
import lotr.common.item.LOTRMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;

public class LOTRItemArmor
extends ItemArmor {
    private LOTRMaterial lotrMaterial;
    private String extraName;

    public LOTRItemArmor(LOTRMaterial material, int slotType) {
        this(material, slotType, "");
    }

    public LOTRItemArmor(LOTRMaterial material, int slotType, String s) {
        super(material.toArmorMaterial(), 0, slotType);
        this.lotrMaterial = material;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.extraName = s;
    }

    public LOTRMaterial getLOTRArmorMaterial() {
        return this.lotrMaterial;
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
        String path = "lotr:armor/";
        if (entity instanceof LOTREntityHalfTroll) {
            path = "lotr:mob/halfTroll/";
        }
        String armorName = this.getArmorName();
        String texture = path + armorName;
        if (type != null) {
            texture = texture + "_" + type;
        }
        return texture + ".png";
    }

    private String getArmorName() {
        String suffix;
        String prefix = this.getArmorMaterial().name().substring("lotr".length() + 1).toLowerCase();
        String string = suffix = this.armorType == 2 ? "2" : "1";
        if (!StringUtils.isNullOrEmpty((String)this.extraName)) {
            suffix = this.extraName;
        }
        return prefix + "_" + suffix;
    }

    public boolean isDamageable() {
        return this.lotrMaterial.isDamageable();
    }
}

