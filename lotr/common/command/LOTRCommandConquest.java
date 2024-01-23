/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common.command;

import java.util.List;
import lotr.common.LOTRLevelData;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.map.LOTRConquestGrid;
import lotr.common.world.map.LOTRConquestZone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class LOTRCommandConquest
extends CommandBase {
    public String getCommandName() {
        return "conquest";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.conquest.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        World world = sender.getEntityWorld();
        if (!LOTRConquestGrid.conquestEnabled(world)) {
            throw new WrongUsageException("commands.lotr.conquest.notEnabled", new Object[0]);
        }
        if (args.length >= 1) {
            String function = args[0];
            if (function.equals("clear")) {
                Object[] obj = this.parseCoordsAndZone(sender, args, 1);
                int posX = (Integer)obj[0];
                int posZ = (Integer)obj[1];
                LOTRConquestZone zone = (LOTRConquestZone)obj[2];
                zone.clearAllFactions(world);
                LOTRCommandConquest.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.conquest.clear", (Object[])new Object[]{posX, posZ});
                return;
            }
            if (function.equals("rate")) {
                if (args.length >= 2) {
                    double rate = LOTRCommandConquest.parseDoubleBounded((ICommandSender)sender, (String)args[1], (double)0.0, (double)100.0);
                    LOTRLevelData.setConquestRate((float)rate);
                    LOTRCommandConquest.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.conquest.rateSet", (Object[])new Object[]{rate});
                    return;
                }
                float currentRate = LOTRLevelData.getConquestRate();
                sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.lotr.conquest.rateGet", new Object[]{Float.valueOf(currentRate)}));
                return;
            }
            if (args.length >= 3 && (function.equals("set") || function.equals("add") || function.equals("radial"))) {
                LOTRFaction fac = LOTRFaction.forName(args[1]);
                if (fac == null) {
                    throw new WrongUsageException("commands.lotr.conquest.noFaction", new Object[]{args[1]});
                }
                float amount = (float)LOTRCommandConquest.parseDouble((ICommandSender)sender, (String)args[2]);
                Object[] obj = this.parseCoordsAndZone(sender, args, 3);
                int posX = (Integer)obj[0];
                int posZ = (Integer)obj[1];
                LOTRConquestZone zone = (LOTRConquestZone)obj[2];
                if (function.equals("set")) {
                    if (amount < 0.0f) {
                        throw new WrongUsageException("commands.lotr.conquest.tooLow", new Object[]{Float.valueOf(0.0f)});
                    }
                    if (amount > 100000.0f) {
                        throw new WrongUsageException("commands.lotr.conquest.tooHigh", new Object[]{Float.valueOf(100000.0f)});
                    }
                    zone.setConquestStrength(fac, amount, world);
                    LOTRCommandConquest.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.conquest.set", (Object[])new Object[]{fac.factionName(), Float.valueOf(amount), posX, posZ});
                    return;
                }
                if (function.equals("add")) {
                    float currentStr = zone.getConquestStrength(fac, world);
                    float newStr = currentStr + amount;
                    if (newStr < 0.0f) {
                        throw new WrongUsageException("commands.lotr.conquest.tooLow", new Object[]{Float.valueOf(0.0f)});
                    }
                    if (newStr > 100000.0f) {
                        throw new WrongUsageException("commands.lotr.conquest.tooHigh", new Object[]{Float.valueOf(100000.0f)});
                    }
                    zone.addConquestStrength(fac, amount, world);
                    LOTRCommandConquest.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.conquest.add", (Object[])new Object[]{fac.factionName(), Float.valueOf(amount), posX, posZ});
                    return;
                }
                if (function.equals("radial")) {
                    EntityPlayerMP senderIfPlayer;
                    float centralStr = zone.getConquestStrength(fac, world);
                    if (centralStr + amount > 100000.0f) {
                        throw new WrongUsageException("commands.lotr.conquest.tooHigh", new Object[]{Float.valueOf(100000.0f)});
                    }
                    EntityPlayerMP entityPlayerMP = senderIfPlayer = sender instanceof EntityPlayerMP ? (EntityPlayerMP)sender : null;
                    if (amount < 0.0f) {
                        LOTRConquestGrid.doRadialConquest(world, zone, (EntityPlayer)senderIfPlayer, null, fac, -amount, -amount);
                    } else {
                        LOTRConquestGrid.doRadialConquest(world, zone, (EntityPlayer)senderIfPlayer, fac, null, amount, amount);
                    }
                    LOTRCommandConquest.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.conquest.radial", (Object[])new Object[]{fac.factionName(), Float.valueOf(amount), posX, posZ});
                    return;
                }
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    private Object[] parseCoordsAndZone(ICommandSender sender, String[] args, int specifyIndex) {
        int posX = sender.getPlayerCoordinates().posX;
        int posZ = sender.getPlayerCoordinates().posZ;
        if (args.length >= specifyIndex + 2) {
            posX = LOTRCommandConquest.parseInt((ICommandSender)sender, (String)args[specifyIndex]);
            posZ = LOTRCommandConquest.parseInt((ICommandSender)sender, (String)args[specifyIndex + 1]);
        }
        LOTRConquestZone zone = LOTRConquestGrid.getZoneByWorldCoords(posX, posZ);
        if (zone.isDummyZone) {
            throw new WrongUsageException("commands.lotr.conquest.outOfBounds", new Object[]{posX, posZ});
        }
        return new Object[]{posX, posZ, zone};
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandConquest.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"set", "add", "radial", "clear", "rate"});
        }
        if (args.length == 2 && (args[0].equals("set") || args[0].equals("add") || args[0].equals("radial"))) {
            List<String> list = LOTRFaction.getPlayableAlignmentFactionNames();
            return LOTRCommandConquest.getListOfStringsMatchingLastWord((String[])args, (String[])list.toArray(new String[0]));
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

