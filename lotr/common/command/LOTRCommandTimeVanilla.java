/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandTime
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 */
package lotr.common.command;

import lotr.common.world.LOTRWorldProvider;
import net.minecraft.command.CommandTime;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class LOTRCommandTimeVanilla
extends CommandTime {
    protected void setTime(ICommandSender sender, int time) {
        for (WorldServer world : MinecraftServer.getServer().worldServers) {
            if (world.provider instanceof LOTRWorldProvider) continue;
            world.setWorldTime((long)time);
        }
    }

    protected void addTime(ICommandSender sender, int time) {
        for (WorldServer world : MinecraftServer.getServer().worldServers) {
            if (world.provider instanceof LOTRWorldProvider) continue;
            world.setWorldTime(world.getWorldTime() + (long)time);
        }
    }
}

