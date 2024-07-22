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
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.fellowship.LOTRFellowship;

public class LOTRPacketFellowshipAcceptInviteResult
implements IMessage {
    private UUID fellowshipID;
    private String fellowshipName;
    private AcceptInviteResult result;

    public LOTRPacketFellowshipAcceptInviteResult() {
    }

    public LOTRPacketFellowshipAcceptInviteResult(LOTRFellowship fs, AcceptInviteResult result) {
        this.fellowshipID = fs.getFellowshipID();
        this.fellowshipName = fs.getName();
        this.result = result;
    }

    public LOTRPacketFellowshipAcceptInviteResult(AcceptInviteResult result) {
        this.fellowshipID = null;
        this.fellowshipName = null;
        this.result = result;
    }

    public void toBytes(ByteBuf data) {
        boolean hasFellowship = this.fellowshipID != null && this.fellowshipName != null;
        data.writeBoolean(hasFellowship);
        if (hasFellowship) {
            data.writeLong(this.fellowshipID.getMostSignificantBits());
            data.writeLong(this.fellowshipID.getLeastSignificantBits());
            byte[] fsNameBytes = this.fellowshipName.getBytes(Charsets.UTF_8);
            data.writeByte(fsNameBytes.length);
            data.writeBytes(fsNameBytes);
        }
        data.writeByte(this.result.ordinal());
    }

    public void fromBytes(ByteBuf data) {
        boolean hasFellowship = data.readBoolean();
        if (hasFellowship) {
            this.fellowshipID = new UUID(data.readLong(), data.readLong());
            byte fsNameLength = data.readByte();
            ByteBuf fsNameBytes = data.readBytes((int)fsNameLength);
            this.fellowshipName = fsNameBytes.toString(Charsets.UTF_8);
        }
        this.result = AcceptInviteResult.values()[data.readByte()];
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketFellowshipAcceptInviteResult, IMessage> {
        public IMessage onMessage(LOTRPacketFellowshipAcceptInviteResult packet, MessageContext context) {
            LOTRMod.proxy.displayFellowshipAcceptInvitationResult(packet.fellowshipID, packet.fellowshipName, packet.result);
            return null;
        }
    }

    public static enum AcceptInviteResult {
        JOINED,
        DISBANDED,
        TOO_LARGE,
        NONEXISTENT;

    }

}

