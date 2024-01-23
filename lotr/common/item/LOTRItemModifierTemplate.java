/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.WeightedRandom
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.WeightedRandom;

public class LOTRItemModifierTemplate
extends Item {
    public LOTRItemModifierTemplate() {
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
    }

    public static LOTREnchantment getModifier(ItemStack itemstack) {
        NBTTagCompound nbt = itemstack.getTagCompound();
        if (nbt != null) {
            String s = nbt.getString("ScrollModifier");
            return LOTREnchantment.getEnchantmentByName(s);
        }
        return null;
    }

    public static void setModifier(ItemStack itemstack, LOTREnchantment ench) {
        String s = ench.enchantName;
        itemstack.setTagInfo("ScrollModifier", (NBTBase)new NBTTagString(s));
    }

    public String getItemStackDisplayName(ItemStack itemstack) {
        String s = super.getItemStackDisplayName(itemstack);
        LOTREnchantment mod = LOTRItemModifierTemplate.getModifier(itemstack);
        if (mod != null) {
            s = String.format(s, mod.getDisplayName());
        }
        return s;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        LOTREnchantment mod = LOTRItemModifierTemplate.getModifier(itemstack);
        if (mod != null) {
            String desc = mod.getNamedFormattedDescription(itemstack);
            list.add(desc);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
            if (!ench.hasTemplateItem()) continue;
            ItemStack itemstack = new ItemStack((Item)this);
            LOTRItemModifierTemplate.setModifier(itemstack, ench);
            list.add(itemstack);
        }
    }

    public static ItemStack getRandomCommonTemplate(Random random) {
        ArrayList<LOTREnchantmentHelper.WeightedRandomEnchant> applicable = new ArrayList<LOTREnchantmentHelper.WeightedRandomEnchant>();
        for (LOTREnchantment ench : LOTREnchantment.allEnchantments) {
            if (!ench.hasTemplateItem()) continue;
            int weight = LOTREnchantmentHelper.getSkilfulWeight(ench);
            LOTREnchantmentHelper.WeightedRandomEnchant wre = new LOTREnchantmentHelper.WeightedRandomEnchant(ench, weight);
            applicable.add(wre);
        }
        LOTREnchantmentHelper.WeightedRandomEnchant chosenWre = (LOTREnchantmentHelper.WeightedRandomEnchant)WeightedRandom.getRandomItem((Random)random, applicable);
        LOTREnchantment chosenEnch = chosenWre.theEnchant;
        ItemStack itemstack = new ItemStack(LOTRMod.modTemplate);
        LOTRItemModifierTemplate.setModifier(itemstack, chosenEnch);
        return itemstack;
    }
}

