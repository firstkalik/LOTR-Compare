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
import lotr.common.LOTRConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class LOTRCommandStructureTimelapse
extends CommandBase {
    public String getCommandName() {
        return "strTimelapse";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.strTimelapse.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("on")) {
                LOTRConfig.setStructureTimelapse(true);
                LOTRCommandStructureTimelapse.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.strTimelapse.on", (Object[])new Object[0]);
                LOTRCommandStructureTimelapse.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.strTimelapse.warn", (Object[])new Object[0]);
                return;
            }
            if (args[0].equals("off")) {
                LOTRConfig.setStructureTimelapse(false);
                LOTRCommandStructureTimelapse.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.strTimelapse.off", (Object[])new Object[0]);
                return;
            }
            int interval = LOTRCommandStructureTimelapse.parseIntWithMin((ICommandSender)sender, (String)args[0], (int)0);
            LOTRConfig.setStructureTimelapseInterval(interval);
            LOTRCommandStructureTimelapse.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.strTimelapse.interval", (Object[])new Object[]{interval});
            return;
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandStructureTimelapse.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"on", "off"});
        }
        return null;
    }
}

