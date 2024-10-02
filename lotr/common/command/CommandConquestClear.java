/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package lotr.common.command;

import java.util.Arrays;
import java.util.List;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.map.LOTRConquestGrid;
import lotr.common.world.map.LOTRConquestZone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class CommandConquestClear
extends CommandBase {
    public String getCommandName() {
        return "conqclear";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/conqclear <faction> <radial> [x] [z]";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> factions = LOTRFaction.getPlayableAlignmentFactionNames();
            return CommandConquestClear.getListOfStringsMatchingLastWord((String[])args, (String[])factions.toArray(new String[0]));
        }
        if (args.length == 2) {
            return Arrays.asList("radial");
        }
        return null;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        String langkey;
        LOTRFaction faction;
        int x;
        int z;
        World world = sender.getEntityWorld();
        if (!LOTRConquestGrid.conquestEnabled(world)) {
            throw new WrongUsageException("commands.lotr.conquest.notEnabled", new Object[0]);
        }
        if (args.length >= 1) {
            faction = LOTRFaction.forName(args[0]);
            if (faction == null) {
                throw new WrongUsageException("commands.lotr.conquest.noFaction", new Object[]{args[0]});
            }
            boolean radial = args.length >= 2 && args[1].equals("radial");
            int index = radial ? 2 : 1;
            x = sender.getPlayerCoordinates().posX;
            z = sender.getPlayerCoordinates().posZ;
            if (args.length >= index + 2) {
                x = CommandConquestClear.parseInt((ICommandSender)sender, (String)args[index]);
                z = CommandConquestClear.parseInt((ICommandSender)sender, (String)args[index + 1]);
            }
            LOTRConquestZone conqZone = LOTRConquestGrid.getZoneByWorldCoords(x, z);
            if (conqZone.isDummyZone) {
                throw new WrongUsageException("commands.lotr.conquest.outOfBounds", new Object[]{x, z});
            }
            langkey = "commands.lotr.conqclear";
            if (radial) {
                float value = conqZone.getConquestStrength(faction, world);
                LOTRConquestGrid.doRadialConquest(world, conqZone, null, null, faction, value, value);
                langkey = langkey + ".radial";
            } else {
                conqZone.setConquestStrength(faction, 0.0f, world);
            }
        } else {
            throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
        }
        CommandConquestClear.func_152373_a((ICommandSender)sender, (ICommand)this, (String)langkey, (Object[])new Object[]{faction.factionName(), x, z});
    }
}

