/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.storage.DerivedWorldInfo
 *  net.minecraft.world.storage.WorldInfo
 */
package lotr.common.world;

import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.WorldInfo;

public class LOTRWorldInfo
extends DerivedWorldInfo {
    private long lotrTotalTime;
    private long lotrWorldTime;

    public LOTRWorldInfo(WorldInfo worldinfo) {
        super(worldinfo);
    }

    public long getWorldTotalTime() {
        return this.lotrTotalTime;
    }

    public long getWorldTime() {
        return this.lotrWorldTime;
    }

    public void incrementTotalWorldTime(long time) {
    }

    public void setWorldTime(long time) {
    }

    public void lotr_setTotalTime(long time) {
        this.lotrTotalTime = time;
    }

    public void lotr_setWorldTime(long time) {
        this.lotrWorldTime = time;
    }
}

