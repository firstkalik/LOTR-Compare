/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 */
package lotr.common.command;

import java.util.List;
import lotr.common.LOTRLevelData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class LOTRCommandEnableAlignmentZones
extends CommandBase {
    public String getCommandName() {
        return "alignmentZones";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.alignmentZones.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1) {
            String flag = args[0];
            if (flag.equals("enable")) {
                LOTRLevelData.setEnableAlignmentZones(true);
                CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.alignmentZones.enable", (Object[])new Object[0]);
                return;
            }
            if (flag.equals("disable")) {
                LOTRLevelData.setEnableAlignmentZones(false);
                CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.alignmentZones.disable", (Object[])new Object[0]);
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"enable", "disable"});
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

