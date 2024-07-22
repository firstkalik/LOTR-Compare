/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package lotr.common.util;

import java.util.Map;
import lotr.common.util.LOTRItemStackMapImpl;
import net.minecraft.item.ItemStack;

public interface LOTRItemStackMap<V>
extends Map<ItemStack, V> {
    public static <T> LOTRItemStackMap<T> create() {
        return new LOTRItemStackMapImpl();
    }

    public static <T> LOTRItemStackMap<T> create(boolean isNBTSensitive) {
        return new LOTRItemStackMapImpl(isNBTSensitive);
    }
}

