/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityBanner;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTRPacketBannerValidate
implements IMessage {
    private int entityID;
    private int slot;
    private String prevText;
    private boolean valid;

    public LOTRPacketBannerValidate() {
    }

    public LOTRPacketBannerValidate(int id, int s, String pre, boolean val) {
        this.entityID = id;
        this.slot = s;
        this.prevText = pre;
        this.valid = val;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeInt(this.slot);
        byte[] textBytes = this.prevText.getBytes(Charsets.UTF_8);
        data.writeByte(textBytes.length);
        data.writeBytes(textBytes);
        data.writeBoolean(this.valid);
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.slot = data.readInt();
        byte length = data.readByte();
        ByteBuf textBytes = data.readBytes((int)length);
        this.prevText = textBytes.toString(Charsets.UTF_8);
        this.valid = data.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketBannerValidate, IMessage> {
        public IMessage onMessage(LOTRPacketBannerValidate packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity instanceof LOTREntityBanner) {
                LOTREntityBanner banner = (LOTREntityBanner)entity;
                LOTRMod.proxy.validateBannerUsername(banner, packet.slot, packet.prevText, packet.valid);
            }
            return null;
        }
    }

}

