/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.entity.npc;

import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.player.EntityPlayer;

public interface LOTRHireableBase {
    public String getNPCName();

    public LOTRFaction getFaction();

    public boolean canTradeWith(EntityPlayer var1);

    public void onUnitTrade(EntityPlayer var1);
}

