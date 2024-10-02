/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRConfig;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;

public class LOTRPacketAlignmentSee2
implements IMessage {
    private String username;
    private int currentHiredNPCs;
    private int maxHiredNPCs;
    private int maxUpHiredNPCs;
    private int getCustomMaxHiredNPCs;

    public LOTRPacketAlignmentSee2() {
    }

    public LOTRPacketAlignmentSee2(String name, LOTRPlayerData pd) {
        this.username = name;
        this.currentHiredNPCs = pd.getGlobalHiredNPCCount();
        this.maxHiredNPCs = LOTRConfig.maxHiredNPCs;
        this.maxUpHiredNPCs = LOTRConfig.maxUpHiredNPCs;
        this.getCustomMaxHiredNPCs = pd.getCustomMaxHiredNPCs();
    }

    public void toBytes(ByteBuf data) {
        byte[] nameBytes = this.username.getBytes(Charsets.UTF_8);
        data.writeByte(nameBytes.length);
        data.writeBytes(nameBytes);
        data.writeInt(this.currentHiredNPCs);
        data.writeInt(this.maxHiredNPCs);
        data.writeInt(this.maxUpHiredNPCs);
        data.writeInt(this.getCustomMaxHiredNPCs);
    }

    public void fromBytes(ByteBuf data) {
        byte length = data.readByte();
        ByteBuf nameBytes = data.readBytes((int)length);
        this.username = nameBytes.toString(Charsets.UTF_8);
        this.currentHiredNPCs = data.readInt();
        this.maxHiredNPCs = data.readInt();
        this.maxUpHiredNPCs = data.readInt();
        this.getCustomMaxHiredNPCs = data.readInt();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketAlignmentSee2, IMessage> {
        public IMessage onMessage(LOTRPacketAlignmentSee2 packet, MessageContext context) {
            LOTRMod.proxy.displayAlignmentSee(packet.username, packet.currentHiredNPCs, packet.maxHiredNPCs + packet.maxUpHiredNPCs, packet.getCustomMaxHiredNPCs);
            return null;
        }
    }

}

