/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class LOTRItemFeatherDyed
extends Item {
    public LOTRItemFeatherDyed() {
        this.setMaxStackSize(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        return Items.feather.getIconFromDamage(i);
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        return LOTRItemFeatherDyed.getFeatherColor(itemstack);
    }

    public static int getFeatherColor(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("FeatherColor")) {
            return itemstack.getTagCompound().getInteger("FeatherColor");
        }
        return 16777215;
    }

    public static boolean isFeatherDyed(ItemStack itemstack) {
        return LOTRItemFeatherDyed.getFeatherColor(itemstack) != 16777215;
    }

    public static void setFeatherColor(ItemStack itemstack, int i) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("FeatherColor", i);
    }

    public static void removeFeatherDye(ItemStack itemstack) {
        LOTRItemFeatherDyed.setFeatherColor(itemstack, 16777215);
    }
}

