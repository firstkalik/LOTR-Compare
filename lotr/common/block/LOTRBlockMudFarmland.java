/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFarmland
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockMudFarmland
extends BlockFarmland {
    public LOTRBlockMudFarmland() {
        this.setHardness(0.6f);
        this.setStepSound(Block.soundTypeGravel);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return super.getIcon(i, j);
        }
        return LOTRMod.mud.getBlockTextureFromSide(i);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        super.updateTick(world, i, j, k, random);
        if (world.getBlock(i, j, k) == Blocks.dirt) {
            world.setBlock(i, j, k, LOTRMod.mud);
        }
    }

    public boolean canSustainPlant(IBlockAccess world, int i, int j, int k, ForgeDirection direction, IPlantable plantable) {
        return Blocks.farmland.canSustainPlant(world, i, j, k, direction, plantable);
    }

    public void onPlantGrow(World world, int i, int j, int k, int sourceX, int sourceY, int sourceZ) {
        world.setBlock(i, j, k, LOTRMod.mud, 0, 2);
    }

    public boolean isFertile(World world, int i, int j, int k) {
        return Blocks.farmland.isFertile(world, i, j, k);
    }

    public void onFallenUpon(World world, int i, int j, int k, Entity entity, float f) {
        super.onFallenUpon(world, i, j, k, entity, f);
        if (world.getBlock(i, j, k) == Blocks.dirt) {
            world.setBlock(i, j, k, LOTRMod.mud);
        }
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        super.onNeighborBlockChange(world, i, j, k, block);
        if (world.getBlock(i, j, k) == Blocks.dirt) {
            world.setBlock(i, j, k, LOTRMod.mud);
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return LOTRMod.mud.getItemDropped(0, random, j);
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        return Item.getItemFromBlock((Block)LOTRMod.mud);
    }
}

