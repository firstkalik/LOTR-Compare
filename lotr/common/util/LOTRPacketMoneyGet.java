/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.util;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemCoin;
import lotr.common.util.LOTRNetwork;
import lotr.common.util.LOTRPacketMoneyChange;
import lotr.common.util.LOTRPlayerMoneyData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRPacketMoneyGet
implements LOTRNetwork.LOTRRPMessage {
    private ItemStack item;
    private static final int MILESTONE_AMOUNT = 500000;
    private static final String CONSOLE_MESSAGE = "\u001b[31mPlayer %s has reached %d coins!\u001b[0m";
    private static final String MESSAGE_PLAYER_REACHED = "lotr.gui.money.get";

    public LOTRPacketMoneyGet() {
    }

    public LOTRPacketMoneyGet(ItemStack items) {
        this.item = items;
    }

    @Override
    public IMessage process(MessageContext context) {
        LOTRMod.network.execute(Side.SERVER, () -> () -> {
            if (LOTRMod.sellitems.containsKey((Object)this.item)) {
                EntityPlayerMP player = context.getServerHandler().playerEntity;
                LOTRPlayerMoneyData data = LOTRPlayerMoneyData.of((EntityPlayer)player);
                int cost = LOTRMod.sellitems.get((Object)this.item);
                int index = -1;
                for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
                    if (player.inventory.mainInventory[i] == null || player.inventory.mainInventory[i].getItem() != this.item.getItem() || player.inventory.mainInventory[i].getItemDamage() != this.item.getItemDamage()) continue;
                    index = i;
                    break;
                }
                if (index >= 0) {
                    if (--player.inventory.mainInventory[index].stackSize <= 0) {
                        player.inventory.mainInventory[index] = null;
                    }
                    int coinValue = this.getCoinValueFromDamage(this.item);
                    data.money += cost;
                    LOTRPacketMoneyChange packets = new LOTRPacketMoneyChange(data.money);
                    packets.sendTo(player);
                    String itemDisplayName = this.item.getDisplayName();
                    String message = StatCollector.translateToLocalFormatted((String)MESSAGE_PLAYER_REACHED, (Object[])new Object[]{coinValue, itemDisplayName});
                    player.addChatMessage((IChatComponent)new ChatComponentText((Object)EnumChatFormatting.RED + message));
                    player.worldObj.playSoundAtEntity((Entity)player, "lotr:event.trade", 0.5f, 1.0f + (player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.1f);
                    int milestonesReached = data.money / 500000;
                    int lastMilestone = (data.money - cost) / 500000;
                    if (milestonesReached > lastMilestone) {
                        System.out.println(String.format(CONSOLE_MESSAGE, player.getDisplayName(), data.money));
                    }
                }
            }
        });
        return null;
    }

    private int getCoinValueFromDamage(ItemStack itemstack) {
        int damage;
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemCoin && (damage = itemstack.getItemDamage()) >= 0 && damage < LOTRItemCoin.values.length) {
            return LOTRItemCoin.values[damage];
        }
        return 0;
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

