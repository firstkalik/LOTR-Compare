/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.util.LOTRLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.Logger;

public abstract class LOTRPacketFellowshipPartialUpdate
implements IMessage {
    protected UUID fellowshipID;

    protected LOTRPacketFellowshipPartialUpdate() {
    }

    protected LOTRPacketFellowshipPartialUpdate(LOTRFellowship fs) {
        this.fellowshipID = fs.getFellowshipID();
    }

    public void toBytes(ByteBuf data) {
        data.writeLong(this.fellowshipID.getMostSignificantBits());
        data.writeLong(this.fellowshipID.getLeastSignificantBits());
    }

    public void fromBytes(ByteBuf data) {
        this.fellowshipID = new UUID(data.readLong(), data.readLong());
    }

    public abstract void updateClient(LOTRFellowshipClient var1);

    public static class ToggleShowMap
    extends LOTRPacketFellowshipPartialUpdate {
        private boolean showMapLocations;

        public ToggleShowMap() {
        }

        public ToggleShowMap(LOTRFellowship fs) {
            super(fs);
            this.showMapLocations = fs.getShowMapLocations();
        }

        @Override
        public void toBytes(ByteBuf data) {
            super.toBytes(data);
            data.writeBoolean(this.showMapLocations);
        }

        @Override
        public void fromBytes(ByteBuf data) {
            super.fromBytes(data);
            this.showMapLocations = data.readBoolean();
        }

        @Override
        public void updateClient(LOTRFellowshipClient fellowship) {
            fellowship.setShowMapLocations(this.showMapLocations);
        }

        public static class Handler
        extends lotr.common.network.LOTRPacketFellowshipPartialUpdate$Handler<ToggleShowMap> {
        }

    }

    public static class ToggleHiredFriendlyFire
    extends LOTRPacketFellowshipPartialUpdate {
        private boolean preventHiredFF;

        public ToggleHiredFriendlyFire() {
        }

        public ToggleHiredFriendlyFire(LOTRFellowship fs) {
            super(fs);
            this.preventHiredFF = fs.getPreventHiredFriendlyFire();
        }

        @Override
        public void toBytes(ByteBuf data) {
            super.toBytes(data);
            data.writeBoolean(this.preventHiredFF);
        }

        @Override
        public void fromBytes(ByteBuf data) {
            super.fromBytes(data);
            this.preventHiredFF = data.readBoolean();
        }

        @Override
        public void updateClient(LOTRFellowshipClient fellowship) {
            fellowship.setPreventHiredFriendlyFire(this.preventHiredFF);
        }

        public static class Handler
        extends lotr.common.network.LOTRPacketFellowshipPartialUpdate$Handler<ToggleHiredFriendlyFire> {
        }

    }

    public static class TogglePvp
    extends LOTRPacketFellowshipPartialUpdate {
        private boolean preventPVP;

        public TogglePvp() {
        }

        public TogglePvp(LOTRFellowship fs) {
            super(fs);
            this.preventPVP = fs.getPreventPVP();
        }

        @Override
        public void toBytes(ByteBuf data) {
            super.toBytes(data);
            data.writeBoolean(this.preventPVP);
        }

        @Override
        public void fromBytes(ByteBuf data) {
            super.fromBytes(data);
            this.preventPVP = data.readBoolean();
        }

        @Override
        public void updateClient(LOTRFellowshipClient fellowship) {
            fellowship.setPreventPVP(this.preventPVP);
        }

        public static class Handler
        extends lotr.common.network.LOTRPacketFellowshipPartialUpdate$Handler<TogglePvp> {
        }

    }

    public static class ChangeIcon
    extends LOTRPacketFellowshipPartialUpdate {
        private ItemStack fellowshipIcon;

        public ChangeIcon() {
        }

        public ChangeIcon(LOTRFellowship fs) {
            super(fs);
            this.fellowshipIcon = fs.getIcon();
        }

        @Override
        public void toBytes(ByteBuf data) {
            super.toBytes(data);
            NBTTagCompound iconData = new NBTTagCompound();
            if (this.fellowshipIcon != null) {
                this.fellowshipIcon.writeToNBT(iconData);
            }
            try {
                new PacketBuffer(data).writeNBTTagCompoundToBuffer(iconData);
            }
            catch (IOException e) {
                FMLLog.severe((String)"LOTR: Error writing fellowship icon data", (Object[])new Object[0]);
                e.printStackTrace();
            }
        }

        @Override
        public void fromBytes(ByteBuf data) {
            super.fromBytes(data);
            NBTTagCompound iconData = new NBTTagCompound();
            try {
                iconData = new PacketBuffer(data).readNBTTagCompoundFromBuffer();
            }
            catch (IOException e) {
                FMLLog.severe((String)"LOTR: Error reading fellowship icon data", (Object[])new Object[0]);
                e.printStackTrace();
            }
            this.fellowshipIcon = ItemStack.loadItemStackFromNBT((NBTTagCompound)iconData);
        }

        @Override
        public void updateClient(LOTRFellowshipClient fellowship) {
            fellowship.setIcon(this.fellowshipIcon);
        }

        public static class Handler
        extends lotr.common.network.LOTRPacketFellowshipPartialUpdate$Handler<ChangeIcon> {
        }

    }

    public static class Rename
    extends LOTRPacketFellowshipPartialUpdate {
        private String fellowshipName;

        public Rename() {
        }

        public Rename(LOTRFellowship fs) {
            super(fs);
            this.fellowshipName = fs.getName();
        }

        @Override
        public void toBytes(ByteBuf data) {
            super.toBytes(data);
            byte[] fsNameBytes = this.fellowshipName.getBytes(Charsets.UTF_8);
            data.writeByte(fsNameBytes.length);
            data.writeBytes(fsNameBytes);
        }

        @Override
        public void fromBytes(ByteBuf data) {
            super.fromBytes(data);
            byte fsNameLength = data.readByte();
            ByteBuf fsNameBytes = data.readBytes((int)fsNameLength);
            this.fellowshipName = fsNameBytes.toString(Charsets.UTF_8);
        }

        @Override
        public void updateClient(LOTRFellowshipClient fellowship) {
            fellowship.setName(this.fellowshipName);
        }

        public static class Handler
        extends lotr.common.network.LOTRPacketFellowshipPartialUpdate$Handler<Rename> {
        }

    }

    public static abstract class Handler<P extends LOTRPacketFellowshipPartialUpdate>
    implements IMessageHandler<P, IMessage> {
        public IMessage onMessage(P packet, MessageContext context) {
            EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
            LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
            LOTRFellowshipClient fellowship = pd.getClientFellowshipByID(((LOTRPacketFellowshipPartialUpdate)packet).fellowshipID);
            if (fellowship != null) {
                ((LOTRPacketFellowshipPartialUpdate)packet).updateClient(fellowship);
            } else {
                LOTRLog.logger.warn("Client couldn't find fellowship for ID " + ((LOTRPacketFellowshipPartialUpdate)packet).fellowshipID);
            }
            return null;
        }
    }

}

