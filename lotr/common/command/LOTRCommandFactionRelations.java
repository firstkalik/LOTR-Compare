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
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionRelations;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class LOTRCommandFactionRelations
extends CommandBase {
    public String getCommandName() {
        return "facRelations";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.facRelations.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1) {
            String function = args[0];
            if (function.equals("set")) {
                if (args.length >= 4) {
                    LOTRFaction fac1 = LOTRFaction.forName(args[1]);
                    if (fac1 == null) {
                        throw new WrongUsageException("commands.lotr.alignment.noFaction", new Object[]{args[1]});
                    }
                    LOTRFaction fac2 = LOTRFaction.forName(args[2]);
                    if (fac2 == null) {
                        throw new WrongUsageException("commands.lotr.alignment.noFaction", new Object[]{args[2]});
                    }
                    LOTRFactionRelations.Relation relation = LOTRFactionRelations.Relation.forName(args[3]);
                    if (relation == null) {
                        throw new WrongUsageException("commands.lotr.facRelations.noRelation", new Object[]{args[3]});
                    }
                    try {
                        LOTRFactionRelations.overrideRelations(fac1, fac2, relation);
                        LOTRCommandFactionRelations.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.facRelations.set", (Object[])new Object[]{fac1.factionName(), fac2.factionName(), relation.getDisplayName()});
                        return;
                    }
                    catch (IllegalArgumentException e) {
                        throw new WrongUsageException("commands.lotr.facRelations.error", new Object[]{e.getMessage()});
                    }
                }
            } else if (function.equals("reset")) {
                LOTRFactionRelations.resetAllRelations();
                LOTRCommandFactionRelations.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.facRelations.reset", (Object[])new Object[0]);
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandFactionRelations.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"set", "reset"});
        }
        if (args.length == 2 || args.length == 3) {
            List<String> list = LOTRFaction.getPlayableAlignmentFactionNames();
            return LOTRCommandFactionRelations.getListOfStringsMatchingLastWord((String[])args, (String[])list.toArray(new String[0]));
        }
        if (args.length == 4) {
            List<String> list = LOTRFactionRelations.Relation.listRelationNames();
            return LOTRCommandFactionRelations.getListOfStringsMatchingLastWord((String[])args, (String[])list.toArray(new String[0]));
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

