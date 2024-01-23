/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.layer.GenLayer
 */
package lotr.common.world.genlayer;

import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayer;

public abstract class LOTRGenLayer
extends GenLayer {
    protected LOTRGenLayer lotrParent;

    public LOTRGenLayer(long l) {
        super(l);
    }

    public void initWorldGenSeed(long l) {
        super.initWorldGenSeed(l);
        if (this.lotrParent != null) {
            this.lotrParent.initWorldGenSeed(l);
        }
    }

    public final int[] getInts(int i, int k, int xSize, int zSize) {
        throw new RuntimeException("Do not use this method!");
    }

    public abstract int[] getInts(World var1, int var2, int var3, int var4, int var5);
}

