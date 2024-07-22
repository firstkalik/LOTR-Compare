/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package lotr.common.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import lotr.common.util.LOTRItemStackMap;
import lotr.common.util.LOTRItemStackWrapper;
import net.minecraft.item.ItemStack;

public class LOTRItemStackMapImpl<V>
implements LOTRItemStackMap<V> {
    private final boolean isNBTSensitive;
    private final HashMap<LOTRItemStackWrapper, V> innerMap = new HashMap();

    public LOTRItemStackMapImpl() {
        this(false);
    }

    public LOTRItemStackMapImpl(boolean isNBTSensitive) {
        this.isNBTSensitive = isNBTSensitive;
    }

    @Override
    public V put(ItemStack key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("Key or value is null");
        }
        return this.innerMap.put(new LOTRItemStackWrapper(key, this.isNBTSensitive), value);
    }

    @Override
    public void putAll(Map<? extends ItemStack, ? extends V> map) {
        map.forEach((key, value) -> this.put((ItemStack)key, (V)value));
    }

    @Override
    public V get(Object key) {
        return key != null && key instanceof ItemStack ? (V)this.innerMap.get(new LOTRItemStackWrapper((ItemStack)key, this.isNBTSensitive)) : null;
    }

    @Override
    public Set<ItemStack> keySet() {
        HashSet<ItemStack> ret = new HashSet<ItemStack>();
        for (LOTRItemStackWrapper key : this.innerMap.keySet()) {
            ret.add(key.toItemStack());
        }
        return ret;
    }

    @Override
    public Collection<V> values() {
        return new ArrayList<V>(this.innerMap.values());
    }

    @Override
    public Set<Map.Entry<ItemStack, V>> entrySet() {
        HashSet<Map.Entry<ItemStack, V>> entrySet = new HashSet<Map.Entry<ItemStack, V>>();
        this.innerMap.forEach((key, value) -> entrySet.add(new AbstractMap.SimpleEntry<ItemStack, Object>(key.toItemStack(), value)));
        return entrySet;
    }

    @Override
    public boolean isEmpty() {
        return this.innerMap.isEmpty();
    }

    @Override
    public int size() {
        return this.innerMap.size();
    }

    @Override
    public boolean containsKey(Object key) {
        return key != null && key instanceof ItemStack && this.innerMap.containsKey(new LOTRItemStackWrapper((ItemStack)key, this.isNBTSensitive));
    }

    @Override
    public boolean containsValue(Object value) {
        return value != null && this.innerMap.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        if (!(key instanceof ItemStack)) {
            throw new IllegalArgumentException("Key is not an instance of item stack");
        }
        return this.innerMap.remove(new LOTRItemStackWrapper((ItemStack)key, this.isNBTSensitive));
    }

    @Override
    public void clear() {
        this.innerMap.clear();
    }
}

