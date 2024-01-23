/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package lotr.common.command;

import java.util.List;
import lotr.common.LOTRDate;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class LOTRCommandDate
extends CommandBase {
    public String getCommandName() {
        return "lotrDate";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.lotrDate.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1 && args[0].equals("get")) {
            int date = LOTRDate.ShireReckoning.currentDay;
            String dateName = LOTRDate.ShireReckoning.getShireDate().getDateName(false);
            ChatComponentTranslation message = new ChatComponentTranslation("commands.lotr.lotrDate.get", new Object[]{date, dateName});
            sender.addChatMessage((IChatComponent)message);
            return;
        }
        if (args.length >= 2) {
            int newDate = LOTRDate.ShireReckoning.currentDay;
            if (args[0].equals("set")) {
                newDate = LOTRCommandDate.parseInt((ICommandSender)sender, (String)args[1]);
            } else if (args[0].equals("add")) {
                int date = LOTRCommandDate.parseInt((ICommandSender)sender, (String)args[1]);
                newDate += date;
            }
            if (Math.abs(newDate) > 1000000) {
                throw new WrongUsageException("commands.lotr.lotrDate.outOfBounds", new Object[0]);
            }
            LOTRDate.setDate(newDate);
            String dateName = LOTRDate.ShireReckoning.getShireDate().getDateName(false);
            LOTRCommandDate.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrDate.set", (Object[])new Object[]{newDate, dateName});
            return;
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandDate.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"get", "set", "add"});
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

