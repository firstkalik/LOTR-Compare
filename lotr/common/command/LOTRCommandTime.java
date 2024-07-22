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
import lotr.common.LOTRTime;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class LOTRCommandTime
extends CommandBase {
    public String getCommandName() {
        return "lotr_time";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.time.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 2) {
            if (args[0].equals("set")) {
                long time = 0L;
                time = args[1].equals("day") ? Math.round((double)LOTRTime.DAY_LENGTH * 0.03) : (args[1].equals("night") ? Math.round((double)LOTRTime.DAY_LENGTH * 0.6) : (long)CommandBase.parseIntWithMin((ICommandSender)sender, (String)args[1], (int)0));
                LOTRTime.setWorldTime(time);
                CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.time.set", (Object[])new Object[]{time});
                return;
            }
            if (args[0].equals("add")) {
                int time = CommandBase.parseIntWithMin((ICommandSender)sender, (String)args[1], (int)0);
                LOTRTime.addWorldTime(time);
                CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.time.add", (Object[])new Object[]{time});
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"set", "add"});
        }
        if (args.length == 2 && args[0].equals("set")) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"day", "night"});
        }
        return null;
    }
}

