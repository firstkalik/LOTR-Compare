/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.fac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lotr.common.fac.LOTRFaction;

public class LOTRAlignmentBonusMap
extends HashMap<LOTRFaction, Float> {
    public Set<LOTRFaction> getChangedFactions() {
        HashSet<LOTRFaction> changed = new HashSet<LOTRFaction>();
        for (LOTRFaction fac : this.keySet()) {
            float bonus = ((Float)this.get((Object)fac)).floatValue();
            if (bonus == 0.0f) continue;
            changed.add(fac);
        }
        return changed;
    }
}

