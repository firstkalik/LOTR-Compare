/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 */
package lotr.common;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.event.FMLInterModComms;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class LOTRInterModComms {
    public static void update() {
        ImmutableList messages = FMLInterModComms.fetchRuntimeMessages((Object)LOTRMod.instance);
        if (!messages.isEmpty()) {
            for (FMLInterModComms.IMCMessage message : messages) {
                if (!message.key.equals("SIEGE_ACTIVE")) continue;
                String playerName = message.getStringValue();
                EntityPlayerMP entityplayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(playerName);
                if (entityplayer == null) continue;
                int duration = 20;
                LOTRLevelData.getData((EntityPlayer)entityplayer).setSiegeActive(duration);
            }
        }
    }
}

