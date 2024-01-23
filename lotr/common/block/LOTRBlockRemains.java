/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.block.LOTRMaterialRemains;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRBlockRemains
extends Block {
    public LOTRBlockRemains() {
        super((Material)LOTRMaterialRemains.remains);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(3.0f);
        this.setStepSound(Block.soundTypeGravel);
    }

    public ArrayList getDrops(World world, int i, int j, int k, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int dropCount = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)1, (int)3) + world.rand.nextInt(1 + fortune * 2);
        if (world.rand.nextBoolean()) {
            ++dropCount;
        }
        InventoryBasic dropInv = new InventoryBasic("remains", true, dropCount * 5);
        LOTRChestContents.fillInventory((IInventory)dropInv, world.rand, LOTRChestContents.MARSH_REMAINS, dropCount);
        for (int l = 0; l < dropInv.getSizeInventory(); ++l) {
            ItemStack drop = dropInv.getStackInSlot(l);
            if (drop == null) continue;
            drops.add(drop);
        }
        return drops;
    }

    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.harvestBlock(world, entityplayer, i, j, k, l);
        if (!world.isRemote) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.mineRemains);
        }
    }
}

