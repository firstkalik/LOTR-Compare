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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRItemArmor;
import lotr.common.item.LOTRMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class LOTRItemHaradRobes
extends LOTRItemArmor {
    public static int ROBES_WHITE = 16777215;

    public LOTRItemHaradRobes(int slot) {
        this(LOTRMaterial.HARAD_ROBES, slot);
    }

    public LOTRItemHaradRobes(LOTRMaterial material, int slot) {
        super(material, slot);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        return LOTRItemHaradRobes.getRobesColor(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        if (LOTRItemHaradRobes.areRobesDyed(itemstack)) {
            list.add(StatCollector.translateToLocal((String)"item.lotr.haradRobes.dyed"));
        }
    }

    public static int getRobesColor(ItemStack itemstack) {
        int dye = LOTRItemHaradRobes.getSavedDyeColor(itemstack);
        if (dye != -1) {
            return dye;
        }
        return ROBES_WHITE;
    }

    private static int getSavedDyeColor(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("RobesColor")) {
            return itemstack.getTagCompound().getInteger("RobesColor");
        }
        return -1;
    }

    public static boolean areRobesDyed(ItemStack itemstack) {
        return LOTRItemHaradRobes.getSavedDyeColor(itemstack) != -1;
    }

    public static void setRobesColor(ItemStack itemstack, int i) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("RobesColor", i);
    }

    public static void removeRobeDye(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null) {
            itemstack.getTagCompound().removeTag("RobesColor");
        }
    }
}

