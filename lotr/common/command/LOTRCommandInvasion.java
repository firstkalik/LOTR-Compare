/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package lotr.common.command;

import java.util.List;
import lotr.common.entity.LOTREntityInvasionSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class LOTRCommandInvasion
extends CommandBase {
    public String getCommandName() {
        return "invasion";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.invasion.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayer player = sender instanceof EntityPlayer ? (EntityPlayer)sender : null;
        World world = sender.getEntityWorld();
        if (args.length >= 1) {
            String typeName = args[0];
            LOTRInvasions type = LOTRInvasions.forName(typeName);
            if (type != null) {
                double posX = (double)sender.getPlayerCoordinates().posX + 0.5;
                double posY = sender.getPlayerCoordinates().posY;
                double posZ = (double)sender.getPlayerCoordinates().posZ + 0.5;
                if (args.length >= 4) {
                    posX = LOTRCommandInvasion.func_110666_a((ICommandSender)sender, (double)posX, (String)args[1]);
                    posY = LOTRCommandInvasion.func_110666_a((ICommandSender)sender, (double)posY, (String)args[2]);
                    posZ = LOTRCommandInvasion.func_110666_a((ICommandSender)sender, (double)posZ, (String)args[3]);
                } else {
                    posY += 3.0;
                }
                int size = -1;
                if (args.length >= 5) {
                    size = LOTRCommandInvasion.parseIntBounded((ICommandSender)sender, (String)args[4], (int)0, (int)10000);
                }
                LOTREntityInvasionSpawner invasion = new LOTREntityInvasionSpawner(world);
                invasion.setInvasionType(type);
                invasion.setLocationAndAngles(posX, posY, posZ, 0.0f, 0.0f);
                world.spawnEntityInWorld((Entity)invasion);
                invasion.selectAppropriateBonusFactions();
                invasion.startInvasion(player, size);
                LOTRCommandInvasion.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.invasion.start", (Object[])new Object[]{type.invasionName(), invasion.getInvasionSize(), posX, posY, posZ});
                return;
            }
            throw new WrongUsageException("commands.lotr.invasion.noType", new Object[]{typeName});
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandInvasion.getListOfStringsMatchingLastWord((String[])args, (String[])LOTRInvasions.listInvasionNames());
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

