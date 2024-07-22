/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerNotFoundException
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class LOTRCommandAlignment
extends CommandBase {
    public String getCommandName() {
        return "alignment";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.alignment.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 2) {
            EntityPlayerMP entityplayer;
            List<Object> factions = new ArrayList();
            if (args[1].equalsIgnoreCase("all")) {
                factions = LOTRFaction.getPlayableAlignmentFactions();
            } else {
                LOTRFaction faction = LOTRFaction.forName(args[1]);
                if (faction == null) {
                    throw new WrongUsageException("commands.lotr.alignment.noFaction", new Object[]{args[1]});
                }
                factions.add((Object)faction);
            }
            if (args[0].equals("set")) {
                float alignment = (float)CommandBase.parseDoubleBounded((ICommandSender)sender, (String)args[2], (double)-1.0E7, (double)1.0E7);
                if (args.length >= 4) {
                    entityplayer = CommandBase.getPlayer((ICommandSender)sender, (String)args[3]);
                } else {
                    entityplayer = CommandBase.getCommandSenderAsPlayer((ICommandSender)sender);
                    if (entityplayer == null) {
                        throw new PlayerNotFoundException();
                    }
                }
                for (LOTRFaction f : factions) {
                    LOTRLevelData.getData((EntityPlayer)entityplayer).setAlignmentFromCommand(f, alignment);
                    CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.alignment.set", (Object[])new Object[]{entityplayer.getCommandSenderName(), f.factionName(), Float.valueOf(alignment)});
                }
                return;
            }
            if (args[0].equals("add")) {
                float newAlignment;
                float alignment = (float)CommandBase.parseDouble((ICommandSender)sender, (String)args[2]);
                if (args.length >= 4) {
                    entityplayer = CommandBase.getPlayer((ICommandSender)sender, (String)args[3]);
                } else {
                    entityplayer = CommandBase.getCommandSenderAsPlayer((ICommandSender)sender);
                    if (entityplayer == null) {
                        throw new PlayerNotFoundException();
                    }
                }
                HashMap<LOTRFaction, Float> newAlignments = new HashMap<LOTRFaction, Float>();
                for (LOTRFaction f : factions) {
                    newAlignment = LOTRLevelData.getData((EntityPlayer)entityplayer).getAlignment(f) + alignment;
                    if (newAlignment < -1.0E7f) {
                        throw new WrongUsageException("commands.lotr.alignment.tooLow", new Object[]{Float.valueOf(-1.0E7f)});
                    }
                    if (newAlignment > 1.0E7f) {
                        throw new WrongUsageException("commands.lotr.alignment.tooHigh", new Object[]{Float.valueOf(1.0E7f)});
                    }
                    newAlignments.put(f, Float.valueOf(newAlignment));
                }
                for (LOTRFaction f : factions) {
                    newAlignment = ((Float)newAlignments.get((Object)f)).floatValue();
                    LOTRLevelData.getData((EntityPlayer)entityplayer).addAlignmentFromCommand(f, alignment);
                    CommandBase.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.alignment.add", (Object[])new Object[]{Float.valueOf(alignment), entityplayer.getCommandSenderName(), f.factionName()});
                }
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"set", "add"});
        }
        if (args.length == 2) {
            List<String> list = LOTRFaction.getPlayableAlignmentFactionNames();
            list.add("all");
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])list.toArray(new String[0]));
        }
        if (args.length == 4) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return i == 3;
    }
}

