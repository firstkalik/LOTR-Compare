/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.List;
import lotr.common.LOTRLevelData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class LOTRCommandBanStructures
extends CommandBase {
    public String getCommandName() {
        return "banStructures";
    }

    public int getRequiredPermissionLevel() {
        return 3;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.banStructures.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            if (LOTRLevelData.structuresBanned()) {
                throw new WrongUsageException("commands.lotr.banStructures.alreadyBanned", new Object[0]);
            }
            LOTRLevelData.setStructuresBanned(true);
            CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.banStructures.ban", (Object[])new Object[0]);
        } else {
            LOTRLevelData.setPlayerBannedForStructures(args[0], true);
            CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.banStructures.banPlayer", (Object[])new Object[]{args[0]});
            EntityPlayerMP entityplayer = CommandBase.getPlayer((ICommandSender)sender, (String)args[0]);
            if (entityplayer != null) {
                entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.banStructures", new Object[0]));
            }
        }
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }
}

