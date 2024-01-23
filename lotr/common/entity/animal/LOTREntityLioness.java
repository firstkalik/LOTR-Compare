/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import lotr.common.entity.animal.LOTREntityLionBase;
import lotr.common.item.LOTRItemLionRug;
import net.minecraft.world.World;

public class LOTREntityLioness
extends LOTREntityLionBase {
    public LOTREntityLioness(World world) {
        super(world);
    }

    @Override
    public boolean isMale() {
        return false;
    }

    @Override
    protected LOTRItemLionRug.LionRugType getLionRugType() {
        return LOTRItemLionRug.LionRugType.LIONESS;
    }
}

