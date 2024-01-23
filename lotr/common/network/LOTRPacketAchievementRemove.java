/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;

public class LOTRPacketAchievementRemove
implements IMessage {
    private LOTRAchievement achievement;

    public LOTRPacketAchievementRemove() {
    }

    public LOTRPacketAchievementRemove(LOTRAchievement ach) {
        this.achievement = ach;
    }

    public void toBytes(ByteBuf data) {
        data.writeByte(this.achievement.category.ordinal());
        data.writeShort(this.achievement.ID);
    }

    public void fromBytes(ByteBuf data) {
        byte catID = data.readByte();
        short achID = data.readShort();
        LOTRAchievement.Category cat = LOTRAchievement.Category.values()[catID];
        this.achievement = LOTRAchievement.achievementForCategoryAndID(cat, achID);
    }

    public static class Handler
    implements IMessageHandler<LOTRPacketAchievementRemove, IMessage> {
        public IMessage onMessage(LOTRPacketAchievementRemove packet, MessageContext context) {
            LOTRAchievement achievement = packet.achievement;
            if (achievement != null && !LOTRMod.proxy.isSingleplayer()) {
                EntityPlayer entityplayer = LOTRMod.proxy.getClientPlayer();
                LOTRLevelData.getData(entityplayer).removeAchievement(achievement);
            }
            return null;
        }
    }

}

