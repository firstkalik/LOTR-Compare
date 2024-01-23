/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class LOTRItemPlantableBlock
extends ItemBlock
implements IPlantable {
    private IPlantable plantableBlock;

    public LOTRItemPlantableBlock(Block block) {
        super(block);
        this.plantableBlock = (IPlantable)block;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return this.plantableBlock.getPlantType(world, i, j, k);
    }

    public Block getPlant(IBlockAccess world, int i, int j, int k) {
        return this.plantableBlock.getPlant(world, i, j, k);
    }

    public int getPlantMetadata(IBlockAccess world, int i, int j, int k) {
        return this.plantableBlock.getPlantMetadata(world, i, j, k);
    }
}

