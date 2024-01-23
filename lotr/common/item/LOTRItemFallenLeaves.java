/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import lotr.common.block.LOTRBlockFallenLeaves;
import lotr.common.item.LOTRItemBlockMetadata;
import lotr.common.item.LOTRItemWaterPlant;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemFallenLeaves
extends LOTRItemBlockMetadata {
    public LOTRItemFallenLeaves(Block block) {
        super(block);
    }

    public String getItemStackDisplayName(ItemStack itemstack) {
        Object[] obj = ((LOTRBlockFallenLeaves)this.field_150939_a).leafBlockMetaFromFallenMeta(itemstack.getItemDamage());
        ItemStack leaves = new ItemStack((Block)obj[0], 1, ((Integer)obj[1]).intValue());
        String name = leaves.getDisplayName();
        return StatCollector.translateToLocalFormatted((String)"tile.lotr.fallenLeaves", (Object[])new Object[]{name});
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return LOTRItemWaterPlant.tryPlaceWaterPlant(this, itemstack, world, entityplayer, this.getMovingObjectPositionFromPlayer(world, entityplayer, true));
    }
}

