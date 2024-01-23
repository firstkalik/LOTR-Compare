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
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRGuiUnitTrade
extends LOTRGuiHireBase {
    public LOTRGuiUnitTrade(EntityPlayer entityplayer, LOTRUnitTradeable trader, World world) {
        super(entityplayer, trader, world);
        this.setTrades(trader.getUnits());
    }
}

