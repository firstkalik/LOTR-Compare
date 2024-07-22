/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package lotr.common;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class LOTRStrike3
extends CommandBase {
    public String getCommandName() {
        return "disableEntityShadows";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/disableEntityShadows";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            if (!player.worldObj.isRemote) {
                player.addChatMessage((IChatComponent)new ChatComponentText("Entity shadows have been disabled."));
                this.disableEntityShadows();
            }
        }
    }

    private void disableEntityShadows() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityRenderer entityRenderer = minecraft.entityRenderer;
        try {
            Field shadowOpaqueField = ReflectionHelper.findField(EntityRenderer.class, (String[])new String[]{"shadowOpaque", "field_78529_t"});
            shadowOpaqueField.setAccessible(true);
            shadowOpaqueField.set((Object)entityRenderer, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

