/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.world.biome;

import lotr.common.world.biome.LOTRBiomeGenNurn;
import lotr.common.world.spawning.LOTRBiomeSpawnList;

public class LOTRBiomeGenNurnen
extends LOTRBiomeGenNurn {
    public LOTRBiomeGenNurnen(int i, boolean major) {
        super(i, major);
        this.npcSpawnList.clear();
        this.clearBiomeVariants();
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }
}

