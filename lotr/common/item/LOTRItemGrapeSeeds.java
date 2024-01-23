/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.item;

import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockGrapevine;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class LOTRItemGrapeSeeds
extends Item
implements IPlantable {
    private Block grapevineBlock;

    public LOTRItemGrapeSeeds(Block block) {
        this.grapevineBlock = block;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2) {
        Block block;
        if (entityplayer.canPlayerEdit(i, j, k, side, itemstack) && (block = world.getBlock(i, j, k)) == LOTRMod.grapevine && LOTRBlockGrapevine.canPlantGrapesAt(world, i, j, k, this)) {
            world.setBlock(i, j, k, this.grapevineBlock, 0, 3);
            --itemstack.stackSize;
            return true;
        }
        return false;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Crop;
    }

    public Block getPlant(IBlockAccess world, int i, int j, int k) {
        return this.grapevineBlock;
    }

    public int getPlantMetadata(IBlockAccess world, int i, int j, int k) {
        return 0;
    }
}

