/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.world.structure2;

import lotr.common.entity.npc.LOTREntityNearHaradrimBase;
import lotr.common.entity.npc.LOTREntityUmbarFarmer;
import lotr.common.world.structure2.LOTRWorldGenSouthronFarm;
import net.minecraft.world.World;

public class LOTRWorldGenUmbarFarm
extends LOTRWorldGenSouthronFarm {
    public LOTRWorldGenUmbarFarm(boolean flag) {
        super(flag);
    }

    @Override
    protected boolean isUmbar() {
        return true;
    }

    @Override
    protected LOTREntityNearHaradrimBase createFarmer(World world) {
        return new LOTREntityUmbarFarmer(world);
    }
}

