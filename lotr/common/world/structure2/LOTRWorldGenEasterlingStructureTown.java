/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.structure2;

import lotr.common.world.structure2.LOTRWorldGenEasterlingStructure;

public abstract class LOTRWorldGenEasterlingStructureTown
extends LOTRWorldGenEasterlingStructure {
    public LOTRWorldGenEasterlingStructureTown(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean useTownBlocks() {
        return true;
    }
}

