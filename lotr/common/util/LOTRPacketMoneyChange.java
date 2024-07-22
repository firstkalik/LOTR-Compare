/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.PacketBuffer
 */
package lotr.common.util;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.function.Supplier;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.util.LOTRNetwork;
import lotr.common.util.LOTRPlayerMoneyData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public class LOTRPacketMoneyChange
implements LOTRNetwork.LOTRRPMessage {
    public int canchange;

    public LOTRPacketMoneyChange() {
    }

    public LOTRPacketMoneyChange(int canchange) {
        this.canchange = canchange;
    }

    @Override
    public IMessage process(MessageContext context) {
        LOTRMod.network.execute(Side.CLIENT, () -> () -> {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerMoneyData pd = LOTRPlayerMoneyData.of(entityplayer);
            pd.money = this.canchange;
        });
        return null;
    }

    @Override
    public void readBuffer(PacketBuffer packet) throws IOException {
        this.canchange = packet.readInt();
    }

    @Override
    public void writeBuffer(PacketBuffer packet) throws IOException {
        packet.writeInt(this.canchange);
    }
}

