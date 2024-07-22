/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package lotr.common;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.UUID;
import lotr.common.LOTRPotionBleeding;
import lotr.common.LOTRPotionExplode;
import lotr.common.LOTRPotionFire;
import lotr.common.LOTRPotionFrostResistance;
import lotr.common.LOTRPotionRepair;
import lotr.common.LOTRPotionSlowfall;
import lotr.common.item.LOTRItemMug;
import lotr.common.item.LOTRPotionPoisonKilling;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class LOTRPotions {
    private static final int POISON_DURATION = 300;
    public static Potion killingPoison;
    public static Potion frostResistance;
    public static Potion Repair;
    public static Potion Slowfall;
    public static Potion Fire;
    public static Potion explode;
    public static Potion blood;

    public static void registerPotion() {
        killingPoison = new LOTRPotionPoisonKilling();
        frostResistance = new LOTRPotionFrostResistance();
        Repair = new LOTRPotionRepair();
        Slowfall = new LOTRPotionSlowfall();
        Fire = new LOTRPotionFire();
        explode = new LOTRPotionExplode();
        blood = new LOTRPotionBleeding();
    }

    public static void addPoisonEffect(EntityPlayer entityplayer, ItemStack itemstack) {
        int duration = 300;
        entityplayer.addPotionEffect(new PotionEffect(LOTRPotions.killingPoison.id, duration));
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
        LOTRPotions.setPoisonerUUID(itemstack, entityplayer.getUniqueID());
    }

    public static void setPoisonerUUID(ItemStack itemstack, UUID uuid) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setString("PoisonerUUID", uuid.toString());
    }

    public static boolean canPlayerSeePoisoned(ItemStack itemstack, EntityPlayer entityplayer) {
        UUID uuid = LOTRPotions.getPoisonerUUID(itemstack);
        if (uuid == null) {
            return true;
        }
        return entityplayer.getUniqueID().equals(uuid) || entityplayer.capabilities.isCreativeMode;
    }

    public static void changePotions() {
        Potion.damageBoost.func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.0, 2);
        Potion.weakness.func_111184_a(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0, 0);
    }

    public static void extendPotionArray() {
        Potion[] potionTypes = null;
        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!f.getName().equals("potionTypes") && !f.getName().equals("field_76425_a")) continue;
                Field modfield = Field.class.getDeclaredField("modifiers");
                modfield.setAccessible(true);
                modfield.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                potionTypes = (Potion[])f.get(null);
                Potion[] newPotionTypes = new Potion[64];
                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                f.set(null, newPotionTypes);
                return;
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
