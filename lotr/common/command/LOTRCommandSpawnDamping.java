/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 */
package lotr.common.command;

import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRSpawnDamping;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class LOTRCommandSpawnDamping
extends CommandBase {
    public String getCommandName() {
        return "spawnDamping";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.spawnDamping.usage";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length >= 1) {
            String option = args[0];
            if (option.equals("reset")) {
                LOTRSpawnDamping.resetAll();
                LOTRCommandSpawnDamping.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.spawnDamping.reset", (Object[])new Object[0]);
                return;
            }
            if (args.length >= 2) {
                String type = args[1];
                if (!type.equals(LOTRSpawnDamping.TYPE_NPC) && EnumCreatureType.valueOf((String)type) == null) {
                    throw new WrongUsageException("commands.lotr.spawnDamping.noType", new Object[]{type});
                }
                if (option.equals("set") && args.length >= 3) {
                    float damping = (float)LOTRCommandSpawnDamping.parseDoubleBounded((ICommandSender)sender, (String)args[2], (double)0.0, (double)1.0);
                    LOTRSpawnDamping.setSpawnDamping(type, damping);
                    LOTRCommandSpawnDamping.func_152373_a((ICommandSender)sender, (ICommand)this, (String)"commands.lotr.spawnDamping.set", (Object[])new Object[]{type, Float.valueOf(damping)});
                    return;
                }
                if (option.equals("calc")) {
                    World world = sender.getEntityWorld();
                    int dim = world.provider.dimensionId;
                    String dimName = world.provider.getDimensionName();
                    float damping = LOTRSpawnDamping.getSpawnDamping(type);
                    int players = world.playerEntities.size();
                    int expectedChunks = 196;
                    int baseCap = LOTRSpawnDamping.getBaseSpawnCapForInfo(type, world);
                    int cap = LOTRSpawnDamping.getSpawnCap(type, baseCap, players);
                    int capXPlayers = cap * players;
                    ChatComponentTranslation msg = new ChatComponentTranslation("commands.lotr.spawnDamping.calc", new Object[]{dim, dimName, type, Float.valueOf(damping), players, expectedChunks, cap, baseCap, capXPlayers});
                    msg.getChatStyle().setColor(EnumChatFormatting.GREEN);
                    sender.addChatMessage((IChatComponent)msg);
                    return;
                }
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return LOTRCommandSpawnDamping.getListOfStringsMatchingLastWord((String[])args, (String[])new String[]{"set", "calc", "reset"});
        }
        if (args.length == 2 && (args[0].equals("set") || args[0].equals("calc"))) {
            ArrayList<String> types = new ArrayList<String>();
            for (EnumCreatureType type : EnumCreatureType.values()) {
                types.add(type.name());
            }
            types.add(LOTRSpawnDamping.TYPE_NPC);
            return LOTRCommandSpawnDamping.getListOfStringsMatchingLastWord((String[])args, (String[])types.toArray(new String[0]));
        }
        return null;
    }

    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}

