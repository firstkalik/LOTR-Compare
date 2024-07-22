/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package lotr.common.util;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import lotr.common.LOTRMod;
import lotr.common.util.LOTRNetwork;
import lotr.common.util.LOTRPacketMoneyChange;
import lotr.common.util.LOTRPlayerMoneyData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class LOTRPacketMoneyGive
implements LOTRNetwork.LOTRRPMessage {
    private ItemStack item;
    public UUID mevans = UUID.fromString("3d275a4b-ff1f-3610-827c-57cb24b5399c");
    public UUID mevans1 = UUID.fromString("b1a05b7c-ca4c-37af-b037-5ff523b15201");

    public LOTRPacketMoneyGive() {
    }

    public LOTRPacketMoneyGive(ItemStack items) {
        this.item = items;
    }

    @Override
    public IMessage process(MessageContext context) {
        LOTRMod.network.execute(Side.SERVER, () -> () -> {
            if (LOTRMod.buyitems.containsKey((Object)this.item)) {
                EntityPlayerMP player = context.getServerHandler().playerEntity;
                LOTRPlayerMoneyData data = LOTRPlayerMoneyData.of((EntityPlayer)player);
                int cost = LOTRMod.buyitems.get((Object)this.item);
                if (data.money >= cost && player.inventory.addItemStackToInventory(this.item)) {
                    LOTRPacketMoneyChange packets = new LOTRPacketMoneyChange(data.money -= cost);
                    packets.sendTo(player);
                    player.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"lotr.gui.money.give", (Object[])new Object[]{this.item.getDisplayName()})));
                }
            }
        });
        return null;
    }

    @Override
    public void readBuffer(PacketBuffer packet) {
        try {
            this.item = packet.readItemStackFromBuffer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeBuffer(PacketBuffer packet) {
        try {
            packet.writeItemStackToBuffer(this.item);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

