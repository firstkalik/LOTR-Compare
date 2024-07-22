/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class LOTRItemOwnership {
    public static void setCurrentOwner(ItemStack itemstack, String name) {
        String previousCurrentOwner;
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        if ((previousCurrentOwner = LOTRItemOwnership.getCurrentOwner(itemstack)) != null) {
            LOTRItemOwnership.addPreviousOwner(itemstack, previousCurrentOwner);
        }
        NBTTagCompound nbt = itemstack.getTagCompound();
        nbt.setString("LOTRCurrentOwner", name);
    }

    public static String getCurrentOwner(ItemStack itemstack) {
        NBTTagCompound nbt;
        if (itemstack.getTagCompound() != null && (nbt = itemstack.getTagCompound()).hasKey("LOTRCurrentOwner", 8)) {
            return nbt.getString("LOTRCurrentOwner");
        }
        return null;
    }

    public static void addPreviousOwner(ItemStack itemstack, String name) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        List<String> previousOwners = LOTRItemOwnership.getPreviousOwners(itemstack);
        NBTTagCompound nbt = itemstack.getTagCompound();
        if (nbt.hasKey("LOTROwner", 8)) {
            nbt.removeTag("LOTROwner");
        }
        List<String> lastPreviousOwners = previousOwners;
        previousOwners = new ArrayList<String>();
        previousOwners.add(name);
        previousOwners.addAll(lastPreviousOwners);
        while (previousOwners.size() > 3) {
            previousOwners.remove(previousOwners.size() - 1);
        }
        NBTTagList tagList = new NBTTagList();
        for (String owner : previousOwners) {
            tagList.appendTag((NBTBase)new NBTTagString(owner));
        }
        nbt.setTag("LOTRPrevOwnerList", (NBTBase)tagList);
    }

    public static List<String> getPreviousOwners(ItemStack itemstack) {
        ArrayList<String> owners = new ArrayList<String>();
        if (itemstack.getTagCompound() != null) {
            NBTTagCompound nbt = itemstack.getTagCompound();
            if (nbt.hasKey("LOTROwner", 8)) {
                String outdatedOwner = nbt.getString("LOTROwner");
                owners.add(outdatedOwner);
            }
            if (nbt.hasKey("LOTRPrevOwnerList", 9)) {
                NBTTagList tagList = nbt.getTagList("LOTRPrevOwnerList", 8);
                for (int i = 0; i < tagList.tagCount(); ++i) {
                    String owner = tagList.getStringTagAt(i);
                    owners.add(owner);
                }
            }
        }
        return owners;
    }
}

