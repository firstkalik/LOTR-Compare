/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package lotr.common.block;

import lotr.common.block.LOTRBlockWoodBase;
import net.minecraft.block.Block;

public class LOTRBlockRottenLog
extends LOTRBlockWoodBase {
    public LOTRBlockRottenLog() {
        this.setWoodNames("rotten");
    }

    public static boolean isRottenWood(Block block) {
        return block instanceof LOTRBlockRottenLog;
    }
}

