/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package lotr.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class CommandListPotions
extends CommandBase {
    public String getCommandName() {
        return "listpotions";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/listpotions - Lists all potion effects with their IDs and localized names.";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("List of all potion effects: ");
        for (int i = 0; i < Potion.potionTypes.length; ++i) {
            Potion potion = Potion.potionTypes[i];
            if (potion == null) continue;
            String effectName = StatCollector.translateToLocal((String)potion.getName());
            sb.append("ID ").append(i).append(": ").append(effectName).append("; ");
        }
        sender.addChatMessage((IChatComponent)new ChatComponentText(sb.toString()));
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }
}

