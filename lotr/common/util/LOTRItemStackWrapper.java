/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package lotr.common.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LOTRItemStackWrapper {
    private final boolean isNBTSensitive;
    private final ItemStack stack;
    private final Item item;
    private final int damage;
    private final NBTTagCompound compound;

    public LOTRItemStackWrapper(ItemStack stack) {
        this(stack, false);
    }

    public LOTRItemStackWrapper(ItemStack stack, boolean isNBTSensitive) {
        this.isNBTSensitive = isNBTSensitive;
        this.item = stack.getItem();
        this.damage = stack.getItemDamage();
        this.compound = stack.getTagCompound();
        this.stack = stack;
    }

    public Item getItem() {
        return this.item;
    }

    public int getDamage() {
        return this.damage;
    }

    public NBTTagCompound getTagCompound() {
        return this.compound;
    }

    public ItemStack toItemStack() {
        return this.stack;
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.item == null ? 0 : this.item.hashCode());
        result = 31 * result + this.damage;
        result = 31 * result + (this.isNBTSensitive ? 1231 : 1237);
        if (this.isNBTSensitive) {
            result = 31 * result + (this.compound == null ? 0 : this.compound.hashCode());
        }
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        LOTRItemStackWrapper other = (LOTRItemStackWrapper)obj;
        if (this.item == null ? other.item != null : !this.item.equals((Object)other.item)) {
            return false;
        }
        if (this.damage != other.damage) {
            return false;
        }
        if (this.isNBTSensitive != other.isNBTSensitive) {
            return false;
        }
        return !this.isNBTSensitive || !(this.compound != null ? !this.compound.equals((Object)other.compound) : other.compound != null);
    }
}

