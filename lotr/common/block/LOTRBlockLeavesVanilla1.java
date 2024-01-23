/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockOldLeaf
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.IBlockAccess
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.block.LOTRBlockLeavesBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;

public class LOTRBlockLeavesVanilla1
extends LOTRBlockLeavesBase {
    public LOTRBlockLeavesVanilla1() {
        super(true, "lotr:leavesV1");
        this.setLeafNames("oak", "spruce", "birch", "jungle");
        this.setSeasonal(true, false, true, false);
    }

    @Override
    public String[] func_150125_e() {
        return BlockOldLeaf.field_150131_O;
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getRenderColor(int i) {
        int meta = i & 3;
        if (meta == 0) {
            return ColorizerFoliage.getFoliageColorBasic();
        }
        return super.getRenderColor(i);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k) & 3;
        if (meta == 0) {
            return LOTRBlockLeavesVanilla1.getBiomeLeafColor(world, i, j, k);
        }
        return super.colorMultiplier(world, i, j, k);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)Blocks.sapling);
    }

    @Override
    protected int getSaplingChance(int meta) {
        if (meta == 3) {
            return 30;
        }
        return super.getSaplingChance(meta);
    }
}

