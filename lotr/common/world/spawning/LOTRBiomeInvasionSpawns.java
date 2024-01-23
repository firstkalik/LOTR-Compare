/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 */
package lotr.common.world.spawning;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;

public class LOTRBiomeInvasionSpawns {
    private LOTRBiome theBiome;
    private Map<LOTREventSpawner.EventChance, List<LOTRInvasions>> invasionsByChance = new HashMap<LOTREventSpawner.EventChance, List<LOTRInvasions>>();
    private List<LOTRInvasions> registeredInvasions = new ArrayList<LOTRInvasions>();

    public LOTRBiomeInvasionSpawns(LOTRBiome biome) {
        this.theBiome = biome;
    }

    public void addInvasion(LOTRInvasions invasion, LOTREventSpawner.EventChance chance) {
        List<LOTRInvasions> chanceList = this.getInvasionsForChance(chance);
        if (chanceList.contains((Object)invasion) || this.registeredInvasions.contains((Object)invasion)) {
            FMLLog.warning((String)"LOTR biome %s already has invasion %s registered", (Object[])new Object[]{this.theBiome.biomeName, invasion.codeName()});
        } else {
            chanceList.add(invasion);
            this.registeredInvasions.add(invasion);
        }
    }

    public void clearInvasions() {
        this.invasionsByChance.clear();
        this.registeredInvasions.clear();
    }

    public List<LOTRInvasions> getInvasionsForChance(LOTREventSpawner.EventChance chance) {
        List<LOTRInvasions> chanceList = this.invasionsByChance.get((Object)chance);
        if (chanceList == null) {
            chanceList = new ArrayList<LOTRInvasions>();
        }
        this.invasionsByChance.put(chance, chanceList);
        return chanceList;
    }
}

