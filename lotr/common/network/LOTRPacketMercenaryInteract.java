/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.network.LOTRPacketUnitTraderInteract;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRPacketMercenaryInteract
extends LOTRPacketUnitTraderInteract {
    public LOTRPacketMercenaryInteract() {
    }

    public LOTRPacketMercenaryInteract(int idt, int a) {
        super(idt, a);
    }

    @Override
    protected void openTradeGUI(EntityPlayer entityplayer, LOTREntityNPC trader) {
        entityplayer.openGui((Object)LOTRMod.instance, 59, entityplayer.worldObj, trader.getEntityId(), 0, 0);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketMercenaryInteract, IMessage> {
        public IMessage onMessage(LOTRPacketMercenaryInteract message, MessageContext ctx) {
            return new LOTRPacketUnitTraderInteract.Handler().onMessage(message, ctx);
        }
    }

}

