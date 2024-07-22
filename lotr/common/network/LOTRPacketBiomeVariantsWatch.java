/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.world.biome.variant.LOTRBiomeVariantStorage;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public class LOTRPacketBiomeVariantsWatch
implements IMessage {
    private int chunkX;
    private int chunkZ;
    private byte[] variants;

    public LOTRPacketBiomeVariantsWatch() {
    }

    public LOTRPacketBiomeVariantsWatch(int x, int z, byte[] v) {
        this.chunkX = x;
        this.chunkZ = z;
        this.variants = v;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.chunkX);
        data.writeInt(this.chunkZ);
        data.writeInt(this.variants.length);
        data.writeBytes(this.variants);
    }

    public void fromBytes(ByteBuf data) {
        this.chunkX = data.readInt();
        this.chunkZ = data.readInt();
        int length = data.readInt();
        this.variants = data.readBytes(length).array();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketBiomeVariantsWatch, IMessage> {
        public IMessage onMessage(LOTRPacketBiomeVariantsWatch packet, MessageContext context) {
            int chunkX;
            int chunkZ;
            World world = LOTRMod.proxy.getClientWorld();
            if (world.blockExists((chunkX = packet.chunkX) << 4, 0, (chunkZ = packet.chunkZ) << 4)) {
                LOTRBiomeVariantStorage.setChunkBiomeVariants(world, new ChunkCoordIntPair(chunkX, chunkZ), packet.variants);
            } else {
                FMLLog.severe((String)"Client received LOTR biome variant data for nonexistent chunk at %d, %d", (Object[])new Object[]{chunkX << 4, chunkZ << 4});
            }
            return null;
        }
    }

}

