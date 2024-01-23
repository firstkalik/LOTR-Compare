/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiHireBase;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.npc.LOTRMercenaryTradeEntry;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRGuiMercenaryHire
extends LOTRGuiHireBase {
    public LOTRGuiMercenaryHire(EntityPlayer entityplayer, LOTRMercenary mercenary, World world) {
        super(entityplayer, mercenary, world);
        LOTRMercenaryTradeEntry e = LOTRMercenaryTradeEntry.createFor(mercenary);
        LOTRUnitTradeEntries trades = new LOTRUnitTradeEntries(0.0f, e);
        this.setTrades(trades);
    }
}

