/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockWallBase;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class LOTRBlockWallClayTileDyed
extends LOTRBlockWallBase {
    public LOTRBlockWallClayTileDyed() {
        super(LOTRMod.clayTileDyed, 16);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return LOTRMod.clayTileDyed.getIcon(i, j);
    }
}

