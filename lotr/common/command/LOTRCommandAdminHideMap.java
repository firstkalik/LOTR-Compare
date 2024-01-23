/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import com.mojang.authlib.GameProfile;
import java.util.List;
import lotr.common.LOTRLevelData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class LOTRCommandAdminHideMap
extends CommandBase {
    public String getCommandName() {
        return "opHideMap";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.opHideMap.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile())) {
                if (player.capabilities.isCreativeMode) {
                    LOTRLevelData.getData(player).setAdminHideMap(true);
                    LOTRCommandAdminHideMap.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.opHideMap.hiding", (Object[])new Object[0]);
                    return;
                }
                throw new WrongUsageException("commands.lotr.opHideMap.notCreative", new Object[0]);
            }
            throw new WrongUsageException("commands.lotr.opHideMap.notOp", new Object[0]);
        }
        throw new WrongUsageException("commands.lotr.opHideMap.notOp", new Object[0]);
    }

    public static void notifyUnhidden(EntityPlayer entityplayer) {
        entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.lotr.opHideMap.unhide", new Object[0]));
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

