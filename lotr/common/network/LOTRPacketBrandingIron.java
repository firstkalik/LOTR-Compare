/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.NetHandlerPlayServer
 *  org.apache.commons.lang3.StringUtils
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import lotr.common.item.LOTRItemBrandingIron;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import org.apache.commons.lang3.StringUtils;

public class LOTRPacketBrandingIron
implements IMessage {
    private String brandName;

    public LOTRPacketBrandingIron() {
    }

    public LOTRPacketBrandingIron(String s) {
        this.brandName = s;
    }

    public void toBytes(ByteBuf data) {
        if (StringUtils.isBlank((CharSequence)this.brandName)) {
            data.writeInt(-1);
        } else {
            byte[] brandBytes = this.brandName.getBytes(Charsets.UTF_8);
            data.writeInt(brandBytes.length);
            data.writeBytes(brandBytes);
        }
    }

    public void fromBytes(ByteBuf data) {
        int length = data.readInt();
        if (length > -1) {
            this.brandName = data.readBytes(length).toString(Charsets.UTF_8);
        }
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketBrandingIron, IMessage> {
        public IMessage onMessage(LOTRPacketBrandingIron packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            ItemStack itemstack = entityplayer.getCurrentEquippedItem();
            if (itemstack != null && itemstack.getItem() instanceof LOTRItemBrandingIron) {
                String brandName = packet.brandName;
                if (!StringUtils.isBlank((CharSequence)(brandName = LOTRItemBrandingIron.trimAcceptableBrandName(brandName))) && !LOTRItemBrandingIron.hasBrandName(itemstack)) {
                    LOTRItemBrandingIron.setBrandName(itemstack, brandName);
                }
            }
            return null;
        }
    }

}

