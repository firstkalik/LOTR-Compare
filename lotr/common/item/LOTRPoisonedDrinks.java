/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package lotr.common.item;

import java.util.UUID;
import lotr.common.item.LOTRItemMug;
import lotr.common.item.LOTRPotionPoisonKilling;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class LOTRPoisonedDrinks {
    public static Potion killingPoison;

    public static void registerPotion() {
        killingPoison = new LOTRPotionPoisonKilling();
    }

    public static void addPoisonEffect(EntityPlayer entityplayer, ItemStack itemstack) {
        int duration = 300;
        entityplayer.addPotionEffect(new PotionEffect(LOTRPoisonedDrinks.killingPoison.id, duration));
    }

    public static boolean canPoison(ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        }
        return LOTRItemMug.isItemFullDrink(itemstack);
    }

    public static boolean isDrinkPoisoned(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("PoisonDrink")) {
            return itemstack.getTagCompound().getBoolean("PoisonDrink");
        }
        return false;
    }

    public static void setDrinkPoisoned(ItemStack itemstack, boolean flag) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setBoolean("PoisonDrink", flag);
    }

    public static UUID getPoisonerUUID(ItemStack itemstack) {
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("PoisonerUUID")) {
            String s = itemstack.getTagCompound().getString("PoisonerUUID");
            return UUID.fromString(s);
        }
        return null;
    }

    public static void setPoisonerPlayer(ItemStack itemstack, EntityPlayer entityplayer) {
        LOTRPoisonedDrinks.setPoisonerUUID(itemstack, entityplayer.getUniqueID());
    }

    public static void setPoisonerUUID(ItemStack itemstack, UUID uuid) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setString("PoisonerUUID", uuid.toString());
    }

    public static boolean canPlayerSeePoisoned(ItemStack itemstack, EntityPlayer entityplayer) {
        UUID uuid = LOTRPoisonedDrinks.getPoisonerUUID(itemstack);
        if (uuid == null) {
            return true;
        }
        return entityplayer.getUniqueID().equals(uuid) || entityplayer.capabilities.isCreativeMode;
    }
}

