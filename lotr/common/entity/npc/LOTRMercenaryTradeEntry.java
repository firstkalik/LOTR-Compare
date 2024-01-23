/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHireableBase;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.npc.LOTRUnitTradeEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRMercenaryTradeEntry
extends LOTRUnitTradeEntry {
    private LOTRMercenary theMerc;

    private LOTRMercenaryTradeEntry(LOTRMercenary merc) {
        super(merc.getClass(), merc.getMercBaseCost(), merc.getMercAlignmentRequired());
        this.theMerc = merc;
    }

    public static LOTRMercenaryTradeEntry createFor(LOTRMercenary merc) {
        return new LOTRMercenaryTradeEntry(merc);
    }

    @Override
    public LOTREntityNPC getOrCreateHiredNPC(World world) {
        return (LOTREntityNPC)((Object)this.theMerc);
    }

    @Override
    public boolean hasRequiredCostAndAlignment(EntityPlayer entityplayer, LOTRHireableBase trader) {
        if (((LOTREntityNPC)this.theMerc).hiredNPCInfo.isActive) {
            return false;
        }
        return super.hasRequiredCostAndAlignment(entityplayer, trader);
    }
}

