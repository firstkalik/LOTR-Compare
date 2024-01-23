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
import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.world.map.LOTRWaypoint;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class LOTRCommandWaypoints
extends CommandBase {
    public String getCommandName() {
        return "lotrWaypoints";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.lotrWaypoints.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 2) {
            String regionName = args[1];
            EntityPlayerMP entityplayer = args.length >= 3 ? LOTRCommandWaypoints.getPlayer((ICommandSender)sender, (String)args[2]) : LOTRCommandWaypoints.getCommandSenderAsPlayer((ICommandSender)sender);
            LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
            if (args[0].equalsIgnoreCase("unlock")) {
                if (regionName.equalsIgnoreCase("all")) {
                    for (LOTRWaypoint.Region region : LOTRWaypoint.Region.values()) {
                        playerData.unlockFTRegion(region);
                    }
                    LOTRCommandWaypoints.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrWaypoints.unlockAll", (Object[])new Object[]{entityplayer.getCommandSenderName()});
                    return;
                }
                LOTRWaypoint.Region region = this.findRegionByName(regionName);
                if (playerData.isFTRegionUnlocked(region)) {
                    throw new WrongUsageException("commands.lotr.lotrWaypoints.unlock.fail", new Object[]{entityplayer.getCommandSenderName(), region.name()});
                }
                playerData.unlockFTRegion(region);
                LOTRCommandWaypoints.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrWaypoints.unlock", (Object[])new Object[]{entityplayer.getCommandSenderName(), region.name()});
                return;
            }
            if (args[0].equalsIgnoreCase("lock")) {
                if (regionName.equalsIgnoreCase("all")) {
                    for (LOTRWaypoint.Region region : LOTRWaypoint.Region.values()) {
                        playerData.lockFTRegion(region);
                    }
                    LOTRCommandWaypoints.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrWaypoints.lockAll", (Object[])new Object[]{entityplayer.getCommandSenderName()});
                    return;
                }
                LOTRWaypoint.Region region = this.findRegionByName(regionName);
                if (!playerData.isFTRegionUnlocked(region)) {
                    throw new WrongUsageException("commands.lotr.lotrWaypoints.lock.fail", new Object[]{entityplayer.getCommandSenderName(), region.name()});
                }
                playerData.lockFTRegion(region);
                LOTRCommandWaypoints.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.lotrWaypoints.lock", (Object[])new Object[]{entityplayer.getCommandSenderName(), region.name()});
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    private LOTRWaypoint.Region findRegionByName(String name) {
        LOTRWaypoint.Region region = LOTRWaypoint.regionForName(name);
        if (region == null) {
            throw new CommandException("commands.lotr.lotrWaypoints.unknown", new Object[]{name});
        }
        return region;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandWaypoints.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"unlock", "lock"});
        }
        if (args.length == 2) {
            ArrayList<String> names = new ArrayList<String>();
            for (LOTRWaypoint.Region r : LOTRWaypoint.Region.values()) {
                names.add(r.name());
            }
            names.add("all");
            return LOTRCommandWaypoints.getListOfStringsMatchingLastWord((String[])args, (String[])names.toArray(new String[0]));
        }
        if (args.length == 3) {
            return LOTRCommandWaypoints.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return i == 2;
    }
}

