/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTradeable;
import net.minecraft.entity.player.EntityPlayer;

public interface LOTRTravellingTrader
extends LOTRTradeable {
    public void startTraderVisiting(EntityPlayer var1);

    public LOTREntityNPC createTravellingEscort();

    public String getDepartureSpeech();
}

