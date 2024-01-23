/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.common.LOTRLevelData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class LOTRCommandAllowStructures
extends CommandBase {
    public String getCommandName() {
        return "allowStructures";
    }

    public int getRequiredPermissionLevel() {
        return 3;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.allowStructures.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            if (!LOTRLevelData.structuresBanned()) {
                throw new WrongUsageException("commands.lotr.allowStructures.alreadyAllowed", new Object[0]);
            }
            LOTRLevelData.setStructuresBanned(false);
            LOTRCommandAllowStructures.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.allowStructures.allow", (Object[])new Object[0]);
        } else {
            LOTRLevelData.setPlayerBannedForStructures(args[0], false);
            LOTRCommandAllowStructures.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.allowStructures.allowPlayer", (Object[])new Object[]{args[0]});
            EntityPlayerMP entityplayer = LOTRCommandAllowStructures.getPlayer((ICommandSender)sender, (String)args[0]);
            if (entityplayer != null) {
                entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.allowStructures", new Object[0]));
            }
        }
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            ArrayList<String> bannedNames = new ArrayList<String>();
            bannedNames.addAll(LOTRLevelData.getBannedStructurePlayersUsernames());
            return LOTRCommandAllowStructures.getListOfStringsMatchingLastWord((String[])args, (String[])bannedNames.toArray(new String[0]));
        }
        return null;
    }
}

