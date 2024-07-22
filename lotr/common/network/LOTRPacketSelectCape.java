/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.world.World
 */
package lotr.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketSelectCape
implements IMessage {
    public LOTRCapes cape;

    public LOTRPacketSelectCape() {
    }

    public LOTRPacketSelectCape(LOTRCapes s) {
        this.cape = s;
    }

    public void toBytes(ByteBuf data) {
        if (this.cape == null) {
            data.writeByte(-1);
        } else {
            data.writeByte(this.cape.capeID);
            data.writeByte(this.cape.capeType.ordinal());
        }
    }

    public void fromBytes(ByteBuf data) {
        byte capeID = data.readByte();
        if (capeID == -1) {
            this.cape = null;
        } else {
            byte capeTypeID = data.readByte();
            if (capeTypeID < 0 || capeTypeID >= LOTRCapes.CapeType.values().length) {
                FMLLog.severe((String)("Failed to update LOTR cape on server side: There is no capetype with ID " + capeTypeID), (Object[])new Object[0]);
            } else {
                LOTRCapes.CapeType capeType = LOTRCapes.CapeType.values()[capeTypeID];
                if (capeID < 0 || capeID >= capeType.list.size()) {
                    FMLLog.severe((String)("Failed to update LOTR cape on server side: There is no cape with ID " + capeID + " for capetype " + capeTypeID), (Object[])new Object[0]);
                } else {
                    this.cape = capeType.list.get(capeID);
                }
            }
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSelectCape, IMessage> {
        public IMessage onMessage(LOTRPacketSelectCape packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRCapes cape = packet.cape;
            if (cape == null || cape.canPlayerWearCape((EntityPlayer)entityplayer)) {
                LOTRLevelData.getData((EntityPlayer)entityplayer).setCape(cape);
                LOTRLevelData.sendCapeToAllPlayersInWorld((EntityPlayer)entityplayer, entityplayer.worldObj);
            } else {
                FMLLog.severe((String)("Failed to update LOTR cape on server side: Player " + entityplayer.getCommandSenderName() + " cannot wear cape " + cape.name()), (Object[])new Object[0]);
            }
            return null;
        }
    }

}

