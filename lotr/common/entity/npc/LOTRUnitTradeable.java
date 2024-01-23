/*
 * Decompiled with CFR 0.148.
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.world.spawning.LOTRInvasions;

public interface LOTRUnitTradeable
extends LOTRHireableBase {
    public LOTRUnitTradeEntries getUnits();

    public LOTRInvasions getWarhorn();

    public boolean shouldTraderRespawn();
}

