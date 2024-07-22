/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import net.minecraft.world.World;

public class LOTRPacketBlockFX
implements IMessage {
    private Type type;
    private int blockX;
    private int blockY;
    private int blockZ;

    public LOTRPacketBlockFX() {
    }

    public LOTRPacketBlockFX(Type t, int i, int j, int k) {
        this.type = t;
        this.blockX = i;
        this.blockY = j;
        this.blockZ = k;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.type.ordinal());
        data.writeInt(this.blockX);
        data.writeInt(this.blockY);
        data.writeInt(this.blockZ);
    }

    public void fromBytes(ByteBuf data) {
        byte typeID = data.readByte();
        this.type = Type.values()[typeID];
        this.blockX = data.readInt();
        this.blockY = data.readInt();
        this.blockZ = data.readInt();
    }

    public static enum Type {
        UTUMNO_EVAPORATE;

    }

    public static class Handler
    implements IMessageHandler<LOTRPacketBlockFX, IMessage> {
        public IMessage onMessage(LOTRPacketBlockFX packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            int i = packet.blockX;
            int j = packet.blockY;
            int k = packet.blockZ;
            Random rand = world.rand;
            if (packet.type == Type.UTUMNO_EVAPORATE) {
                for (int l = 0; l < 8; ++l) {
                    world.spawnParticle("largesmoke", (double)((float)i + rand.nextFloat()), (double)((float)j + rand.nextFloat()), (double)((float)k + rand.nextFloat()), 0.0, 0.0, 0.0);
                }
            }
            return null;
        }
    }

}

