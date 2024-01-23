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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;

public class LOTRPacketAlignmentSee
implements IMessage {
    private String username;
    private Map<LOTRFaction, Float> alignmentMap = new HashMap<LOTRFaction, Float>();

    public LOTRPacketAlignmentSee() {
    }

    public LOTRPacketAlignmentSee(String name, LOTRPlayerData pd) {
        this.username = name;
        for (LOTRFaction f : LOTRFaction.getPlayableAlignmentFactions()) {
            float al = pd.getAlignment(f);
            this.alignmentMap.put(f, Float.valueOf(al));
        }
    }

    public void toBytes(ByteBuf data) {
        byte[] nameBytes = this.username.getBytes(Charsets.UTF_8);
        data.writeByte(nameBytes.length);
        data.writeBytes(nameBytes);
        for (Map.Entry<LOTRFaction, Float> entry : this.alignmentMap.entrySet()) {
            LOTRFaction f = entry.getKey();
            float alignment = entry.getValue().floatValue();
            data.writeByte(f.ordinal());
            data.writeFloat(alignment);
        }
        data.writeByte(-1);
    }

    public void fromBytes(ByteBuf data) {
        byte length = data.readByte();
        ByteBuf nameBytes = data.readBytes((int)length);
        this.username = nameBytes.toString(Charsets.UTF_8);
        byte factionID = 0;
        while ((factionID = data.readByte()) >= 0) {
            LOTRFaction f = LOTRFaction.forID(factionID);
            float alignment = data.readFloat();
            this.alignmentMap.put(f, Float.valueOf(alignment));
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketAlignmentSee, IMessage> {
        public IMessage onMessage(LOTRPacketAlignmentSee packet, MessageContext context) {
            LOTRMod.proxy.displayAlignmentSee(packet.username, packet.alignmentMap);
            return null;
        }
    }

}

