/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRPacketUtumnoKill
implements IMessage {
    private int entityID;
    private int blockX;
    private int blockY;
    private int blockZ;

    public LOTRPacketUtumnoKill() {
    }

    public LOTRPacketUtumnoKill(int id, int i, int j, int k) {
        this.entityID = id;
        this.blockX = i;
        this.blockY = j;
        this.blockZ = k;
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeInt(this.blockX);
        data.writeInt(this.blockY);
        data.writeInt(this.blockZ);
    }

    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        this.blockX = data.readInt();
        this.blockY = data.readInt();
        this.blockZ = data.readInt();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketUtumnoKill, IMessage> {
        public IMessage onMessage(LOTRPacketUtumnoKill packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if (entity != null) {
                int l;
                double d2;
                double d5;
                double d;
                double d1;
                double d4;
                double d3;
                int i1 = packet.blockX;
                int j1 = packet.blockY;
                int k1 = packet.blockZ;
                Block block = world.getBlock(i1, j1, k1);
                block.setBlockBoundsBasedOnState((IBlockAccess)world, i1, j1, k1);
                double blockTop = block.getBlockBoundsMaxY();
                for (l = 0; l < 8; ++l) {
                    d = (double)i1 + MathHelper.getRandomDoubleInRange((Random)world.rand, (double)0.25, (double)0.75);
                    d1 = (double)j1 + 0.1;
                    d2 = (double)k1 + MathHelper.getRandomDoubleInRange((Random)world.rand, (double)0.25, (double)0.75);
                    d3 = 0.0;
                    d4 = MathHelper.getRandomDoubleInRange((Random)world.rand, (double)0.1, (double)0.2);
                    d5 = 0.0;
                    LOTRMod.proxy.spawnParticle("utumnoKill", d, d1, d2, d3, d4, d5);
                }
                for (l = 0; l < 12; ++l) {
                    d = entity.posX + world.rand.nextGaussian() * 0.8;
                    d1 = entity.boundingBox.minY + (double)entity.height * 0.7 + world.rand.nextGaussian() * 0.2;
                    d2 = entity.posZ + world.rand.nextGaussian() * 0.8;
                    d3 = (double)i1 + 0.5 - d;
                    d4 = (double)j1 + blockTop - d1;
                    d5 = (double)k1 + 0.5 - d2;
                    double v = 0.05;
                    LOTRMod.proxy.spawnParticle("utumnoKill", d, d1, d2, d3 *= v, d4 *= v, d5 *= v);
                }
            }
            return null;
        }
    }

}

