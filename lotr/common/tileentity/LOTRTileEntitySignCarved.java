/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import lotr.common.block.LOTRBlockSignCarved;
import lotr.common.tileentity.LOTRTileEntitySign;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRTileEntitySignCarved
extends LOTRTileEntitySign {
    @Override
    public int getNumLines() {
        return 8;
    }

    public IIcon getOnBlockIcon() {
        World world = this.getWorldObj();
        Block block = this.getBlockType();
        if (block instanceof LOTRBlockSignCarved) {
            LOTRBlockSignCarved signBlock = (LOTRBlockSignCarved)block;
            int meta = this.getBlockMetadata();
            int i = this.xCoord;
            int j = this.yCoord;
            int k = this.zCoord;
            int onSide = meta;
            return signBlock.getOnBlockIcon((IBlockAccess)world, i, j, k, onSide);
        }
        return Blocks.stone.getIcon(0, 0);
    }
}

