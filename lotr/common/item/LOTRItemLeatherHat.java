/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemArmor;
import lotr.common.item.LOTRMaterial;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class LOTRItemLeatherHat
extends LOTRItemArmor {
    public static final int HAT_LEATHER = 6834742;
    public static final int HAT_SHIRRIFF_CHIEF = 2301981;
    public static final int FEATHER_WHITE = 16777215;
    public static final int FEATHER_SHIRRIFF_CHIEF = 3381529;
    public static final int HAT_BLACK = 0;
    @SideOnly(value=Side.CLIENT)
    private IIcon featherIcon;

    public LOTRItemLeatherHat() {
        super(LOTRMaterial.COSMETIC, 0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        super.registerIcons(iconregister);
        this.featherIcon = iconregister.registerIcon(this.getIconString() + "_feather");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack itemstack, int pass) {
        if (pass == 1 && LOTRItemLeatherHat.hasFeather(itemstack)) {
            return this.featherIcon;
        }
        return this.itemIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        if (pass == 1 && LOTRItemLeatherHat.hasFeather(itemstack)) {
            return LOTRItemLeatherHat.getFeatherColor(itemstack);
        }
        return LOTRItemLeatherHat.getHatColor(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        if (LOTRItemLeatherHat.isHatDyed(itemstack)) {
            list.add(StatCollector.translateToLocal((String)"item.lotr.hat.dyed"));
        }
        if (LOTRItemLeatherHat.hasFeather(itemstack)) {
            list.add(StatCollector.translateToLocal((String)"item.lotr.hat.feathered"));
        }
    }

    public static int getHatColor(ItemStack itemstack) {
        int dye = LOTRItemLeatherHat.getSavedDyeColor(itemstack);
        if (dye != -1) {
            return dye;
        }
        return 6834742;
    }

    private static int getSavedDyeColor(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("HatColor")) {
            return itemstack.getTagCompound().getInteger("HatColor");
        }
        return -1;
    }

    public static boolean isHatDyed(ItemStack itemstack) {
        return LOTRItemLeatherHat.getSavedDyeColor(itemstack) != -1;
    }

    public static ItemStack setHatColor(ItemStack itemstack, int i) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("HatColor", i);
        return itemstack;
    }

    public static int getFeatherColor(ItemStack itemstack) {
        int i = -1;
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("FeatherColor")) {
            i = itemstack.getTagCompound().getInteger("FeatherColor");
        }
        return i;
    }

    public static boolean hasFeather(ItemStack itemstack) {
        return LOTRItemLeatherHat.getFeatherColor(itemstack) != -1;
    }

    public static boolean isFeatherDyed(ItemStack itemstack) {
        return LOTRItemLeatherHat.hasFeather(itemstack) && LOTRItemLeatherHat.getFeatherColor(itemstack) != 16777215;
    }

    public static ItemStack setFeatherColor(ItemStack itemstack, int i) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("FeatherColor", i);
        return itemstack;
    }

    public static void removeHatAndFeatherDye(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null) {
            itemstack.getTagCompound().removeTag("HatColor");
        }
        if (LOTRItemLeatherHat.hasFeather(itemstack) && LOTRItemLeatherHat.isFeatherDyed(itemstack)) {
            LOTRItemLeatherHat.setFeatherColor(itemstack, 16777215);
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "lotr:armor/hat.png";
    }
}

