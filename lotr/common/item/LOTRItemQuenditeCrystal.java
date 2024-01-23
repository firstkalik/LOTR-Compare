/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemQuenditeCrystal
extends Item {
    public LOTRItemQuenditeCrystal() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabMaterials);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float f, float f1, float f2) {
        if (!entityplayer.canPlayerEdit(i, j, k, side, itemstack)) {
            return false;
        }
        if (world.getBlock(i, j, k) == Blocks.grass) {
            if (LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.LOTHLORIEN) >= 1.0f || LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.HIGH_ELF) >= 1.0f) {
                world.setBlock(i, j, k, LOTRMod.quenditeGrass, 0, 3);
                --itemstack.stackSize;
                for (int l = 0; l < 8; ++l) {
                    world.spawnParticle("iconcrack_" + Item.getIdFromItem((Item)this), (double)i + (double)world.rand.nextFloat(), (double)j + 1.5, (double)k + (double)world.rand.nextFloat(), 0.0, 0.0, 0.0);
                }
            } else {
                for (int l = 0; l < 8; ++l) {
                    double d = (double)i + (double)world.rand.nextFloat();
                    double d1 = (double)j + 1.0;
                    double d2 = (double)k + (double)world.rand.nextFloat();
                    world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
                }
                if (!world.isRemote) {
                    LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 1.0f, LOTRFaction.LOTHLORIEN, LOTRFaction.HIGH_ELF);
                }
            }
            return true;
        }
        return false;
    }
}

