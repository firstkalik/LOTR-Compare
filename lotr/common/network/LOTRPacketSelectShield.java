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
import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRShields;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class LOTRPacketSelectShield
implements IMessage {
    private LOTRShields shield;

    public LOTRPacketSelectShield() {
    }

    public LOTRPacketSelectShield(LOTRShields s) {
        this.shield = s;
    }

    public void toBytes(ByteBuf data) {
        if (this.shield == null) {
            data.writeByte(-1);
        } else {
            data.writeByte(this.shield.shieldID);
            data.writeByte(this.shield.shieldType.ordinal());
        }
    }

    public void fromBytes(ByteBuf data) {
        byte shieldID = data.readByte();
        if (shieldID == -1) {
            this.shield = null;
        } else {
            byte shieldTypeID = data.readByte();
            if (shieldTypeID < 0 || shieldTypeID >= LOTRShields.ShieldType.values().length) {
                FMLLog.severe((String)("Failed to update LOTR shield on server side: There is no shieldtype with ID " + shieldTypeID), (Object[])new Object[0]);
            } else {
                LOTRShields.ShieldType shieldType = LOTRShields.ShieldType.values()[shieldTypeID];
                if (shieldID < 0 || shieldID >= shieldType.list.size()) {
                    FMLLog.severe((String)("Failed to update LOTR shield on server side: There is no shield with ID " + shieldID + " for shieldtype " + shieldTypeID), (Object[])new Object[0]);
                } else {
                    this.shield = shieldType.list.get(shieldID);
                }
            }
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketSelectShield, IMessage> {
        public IMessage onMessage(LOTRPacketSelectShield packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRShields shield = packet.shield;
            if (shield == null || shield.canPlayerWear((EntityPlayer)entityplayer)) {
                LOTRLevelData.getData((EntityPlayer)entityplayer).setShield(shield);
                LOTRLevelData.sendShieldToAllPlayersInWorld((EntityPlayer)entityplayer, entityplayer.worldObj);
            } else {
                FMLLog.severe((String)("Failed to update LOTR shield on server side: Player " + entityplayer.getCommandSenderName() + " cannot wear shield " + shield.name()), (Object[])new Object[0]);
            }
            return null;
        }
    }

}

