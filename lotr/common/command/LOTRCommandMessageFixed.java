/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.server.CommandMessage
 */
package lotr.common.command;

import lotr.common.LOTRConfig;
import net.minecraft.command.server.CommandMessage;

public class LOTRCommandMessageFixed
extends CommandMessage {
    public boolean isUsernameIndex(String[] args, int i) {
        if (LOTRConfig.preventMessageExploit) {
            return false;
        }
        return super.isUsernameIndex(args, i);
    }
}

