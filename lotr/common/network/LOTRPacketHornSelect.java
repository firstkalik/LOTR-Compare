/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;

public class LOTRPacketHornSelect
implements IMessage {
    private int hornType;

    public LOTRPacketHornSelect() {
    }

    public LOTRPacketHornSelect(int h) {
        this.hornType = h;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.hornType);
    }

    public void fromBytes(ByteBuf data) {
        this.hornType = data.readByte();
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketHornSelect, IMessage> {
        public IMessage onMessage(LOTRPacketHornSelect packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            ItemStack itemstack = entityplayer.inventory.getCurrentItem();
            if (itemstack != null && itemstack.getItem() == LOTRMod.commandHorn && itemstack.getItemDamage() == 0) {
                itemstack.setItemDamage(packet.hornType);
            }
            return null;
        }
    }

}

