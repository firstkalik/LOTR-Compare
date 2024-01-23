/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import java.util.Set;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketAlignmentChoices
implements IMessage {
    private Set<LOTRFaction> setZeroFacs = new HashSet<LOTRFaction>();

    public LOTRPacketAlignmentChoices() {
    }

    public LOTRPacketAlignmentChoices(Set<LOTRFaction> facs) {
        this.setZeroFacs = facs;
    }

    public void toBytes(ByteBuf data) {
        for (LOTRFaction fac : this.setZeroFacs) {
            data.writeByte(fac.ordinal());
        }
        data.writeByte(-1);
    }

    public void fromBytes(ByteBuf data) {
        byte facID = 0;
        while ((facID = data.readByte()) >= 0) {
            LOTRFaction fac = LOTRFaction.forID(facID);
            if (fac == null) continue;
            this.setZeroFacs.add(fac);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketAlignmentChoices, IMessage> {
        public IMessage onMessage(LOTRPacketAlignmentChoices packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
            playerData.chooseUnwantedAlignments(entityplayer, packet.setZeroFacs);
            return null;
        }
    }

}

