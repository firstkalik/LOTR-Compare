/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntitySign;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRPacketOpenSignEditor
implements IMessage {
    private int posX;
    private int posY;
    private int posZ;
    private Block blockType;
    private int blockMeta;

    public LOTRPacketOpenSignEditor() {
    }

    public LOTRPacketOpenSignEditor(LOTRTileEntitySign sign) {
        this.posX = sign.xCoord;
        this.posY = sign.yCoord;
        this.posZ = sign.zCoord;
        this.blockType = sign.getBlockType();
        this.blockMeta = sign.getBlockMetadata();
    }

    public void toBytes(ByteBuf data) {
        data.writeInt(this.posX);
        data.writeInt(this.posY);
        data.writeInt(this.posZ);
        data.writeShort(Block.getIdFromBlock((Block)this.blockType));
        data.writeByte(this.blockMeta);
    }

    public void fromBytes(ByteBuf data) {
        this.posX = data.readInt();
        this.posY = data.readInt();
        this.posZ = data.readInt();
        this.blockType = Block.getBlockById((int)data.readShort());
        this.blockMeta = data.readByte();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketOpenSignEditor, IMessage> {
        public IMessage onMessage(LOTRPacketOpenSignEditor packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            World world = LOTRMod.proxy.getClientWorld();
            world.setBlock(packet.posX, packet.posY, packet.posZ, packet.blockType, packet.blockMeta, 3);
            entityplayer.openGui((Object)LOTRMod.instance, 47, world, packet.posX, packet.posY, packet.posZ);
            return null;
        }
    }

}

