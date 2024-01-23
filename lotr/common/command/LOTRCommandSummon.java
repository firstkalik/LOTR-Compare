/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.server.CommandSummon
 */
package lotr.common.command;

import lotr.common.entity.LOTREntities;
import net.minecraft.command.server.CommandSummon;

public class LOTRCommandSummon
extends CommandSummon {
    public String getCommandName() {
        return "lotr_summon";
    }

    protected String[] func_147182_d() {
        return LOTREntities.getAllEntityNames().toArray(new String[0]);
    }
}

