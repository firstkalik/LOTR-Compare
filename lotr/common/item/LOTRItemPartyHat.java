/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
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
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemArmor;
import lotr.common.item.LOTRMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class LOTRItemPartyHat
extends LOTRItemArmor {
    public static final int HAT_WHITE = 16777215;

    public LOTRItemPartyHat() {
        super(LOTRMaterial.COSMETIC, 0);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMisc);
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        return LOTRItemPartyHat.getHatColor(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        if (LOTRItemPartyHat.isHatDyed(itemstack) && LOTRItemPartyHat.getHatColor(itemstack) != 16777215) {
            list.add(StatCollector.translateToLocal((String)"item.lotr.hat.dyed"));
        }
    }

    public static int getHatColor(ItemStack itemstack) {
        int dye = LOTRItemPartyHat.getSavedDyeColor(itemstack);
        if (dye != -1) {
            return dye;
        }
        return 16777215;
    }

    private static int getSavedDyeColor(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("HatColor")) {
            return itemstack.getTagCompound().getInteger("HatColor");
        }
        return -1;
    }

    public static boolean isHatDyed(ItemStack itemstack) {
        return LOTRItemPartyHat.getSavedDyeColor(itemstack) != -1;
    }

    public static ItemStack setHatColor(ItemStack itemstack, int i) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("HatColor", i);
        return itemstack;
    }

    public static ItemStack createDyedHat(int i) {
        return LOTRItemPartyHat.setHatColor(new ItemStack(LOTRMod.partyHat), i);
    }

    public static void removeHatDye(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null) {
            itemstack.getTagCompound().removeTag("HatColor");
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "lotr:armor/partyhat.png";
    }
}

