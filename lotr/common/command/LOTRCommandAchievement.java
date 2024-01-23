/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class LOTRCommandAchievement
extends CommandBase {
    public String getCommandName() {
        return "lotrAchievement";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.lotrAchievement.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 2) {
            String achName = args[1];
            EntityPlayerMP entityplayer = args.length >= 3 ? LOTRCommandAchievement.getPlayer((ICommandSender)sender, (String)args[2]) : LOTRCommandAchievement.getCommandSenderAsPlayer((ICommandSender)sender);
            LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
            if (args[0].equalsIgnoreCase("give")) {
                LOTRAchievement ach = this.findAchievementByName(achName);
                if (playerData.hasAchievement(ach)) {
                    throw new WrongUsageException("commands.lotr.lotrAchievement.give.fail", new Object[]{entityplayer.getCommandSenderName(), ach.getTitle((EntityPlayer)entityplayer)});
                }
                playerData.addAchievement(ach);
                LOTRCommandAchievement.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrAchievement.give", (Object[])new Object[]{entityplayer.getCommandSenderName(), ach.getTitle((EntityPlayer)entityplayer)});
                return;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                if (achName.equalsIgnoreCase("all")) {
                    ArrayList<LOTRAchievement> allAchievements = new ArrayList<LOTRAchievement>(playerData.getAchievements());
                    for (LOTRAchievement ach : allAchievements) {
                        playerData.removeAchievement(ach);
                    }
                    LOTRCommandAchievement.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrAchievement.removeAll", (Object[])new Object[]{entityplayer.getCommandSenderName()});
                    return;
                }
                LOTRAchievement ach = this.findAchievementByName(achName);
                if (!playerData.hasAchievement(ach)) {
                    throw new WrongUsageException("commands.lotr.lotrAchievement.remove.fail", new Object[]{entityplayer.getCommandSenderName(), ach.getTitle((EntityPlayer)entityplayer)});
                }
                playerData.removeAchievement(ach);
                LOTRCommandAchievement.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrAchievement.remove", (Object[])new Object[]{entityplayer.getCommandSenderName(), ach.getTitle((EntityPlayer)entityplayer)});
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    private LOTRAchievement findAchievementByName(String name) {
        LOTRAchievement ach = LOTRAchievement.findByName(name);
        if (ach == null) {
            throw new CommandException("commands.lotr.lotrAchievement.unknown", new Object[]{name});
        }
        return ach;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandAchievement.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"give", "remove"});
        }
        if (args.length == 2) {
            List<LOTRAchievement> achievements = LOTRAchievement.getAllAchievements();
            ArrayList<String> names = new ArrayList<String>();
            for (LOTRAchievement a : achievements) {
                names.add(a.getCodeName());
            }
            if (args[0].equals("remove")) {
                names.add("all");
            }
            return LOTRCommandAchievement.getListOfStringsMatchingLastWord((String[])args, (String[])names.toArray(new String[0]));
        }
        if (args.length == 3) {
            return LOTRCommandAchievement.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return i == 2;
    }
}

