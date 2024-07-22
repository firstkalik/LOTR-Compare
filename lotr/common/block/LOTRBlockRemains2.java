/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockRemains2
extends Block {
    public static boolean fallInstantly;
    private static final String __OBFID = "CL_00000240";

    public LOTRBlockRemains2() {
        super((Material)LOTRMaterialRemains.remains);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(3.0f);
        this.setStepSound(Block.soundTypeSand);
    }

    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
        p_149726_1_.scheduleBlockUpdate(p_149726_2_, p_149726_3_, p_149726_4_, (Block)this, this.tickRate(p_149726_1_));
    }

    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, (Block)this, this.tickRate(p_149695_1_));
    }

    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        if (!p_149674_1_.isRemote) {
            this.func_149830_m(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
        }
    }

    private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_) {
        if (LOTRBlockRemains2.func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ >= 0) {
            int b0 = 32;
            if (!fallInstantly && p_149830_1_.checkChunksExist(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0)) {
                if (!p_149830_1_.isRemote) {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(p_149830_1_, (double)((float)p_149830_2_ + 0.5f), (double)((float)p_149830_3_ + 0.5f), (double)((float)p_149830_4_ + 0.5f), (Block)this, p_149830_1_.getBlockMetadata(p_149830_2_, p_149830_3_, p_149830_4_));
                    this.func_149829_a(entityfallingblock);
                    p_149830_1_.spawnEntityInWorld((Entity)entityfallingblock);
                }
            } else {
                p_149830_1_.setBlockToAir(p_149830_2_, p_149830_3_, p_149830_4_);
                while (LOTRBlockRemains2.func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ > 0) {
                    --p_149830_3_;
                }
                if (p_149830_3_ > 0) {
                    p_149830_1_.setBlock(p_149830_2_, p_149830_3_, p_149830_4_, (Block)this);
                }
            }
        }
    }

    protected void func_149829_a(EntityFallingBlock p_149829_1_) {
    }

    public int tickRate(World p_149738_1_) {
        return 2;
    }

    public static boolean func_149831_e(World p_149831_0_, int p_149831_1_, int p_149831_2_, int p_149831_3_) {
        Block block = p_149831_0_.getBlock(p_149831_1_, p_149831_2_, p_149831_3_);
        if (block.isAir((IBlockAccess)p_149831_0_, p_149831_1_, p_149831_2_, p_149831_3_)) {
            return true;
        }
        if (block == Blocks.fire) {
            return true;
        }
        Material material = block.getMaterial();
        return material == Material.water ? true : material == Material.lava;
    }

    public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_) {
    }

    public ArrayList getDrops(World world, int i, int j, int k, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int dropCount = MathHelper.getRandomIntegerInRange((Random)world.rand, (int)1, (int)3) + world.rand.nextInt(1 + fortune * 2);
        if (world.rand.nextBoolean()) {
            ++dropCount;
        }
        InventoryBasic dropInv = new InventoryBasic("sus", true, dropCount * 5);
        LOTRChestContents.fillInventory((IInventory)dropInv, world.rand, LOTRChestContents.LOTRChestContents2.SUS_SAND, dropCount);
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
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.minesusSand);
        }
    }
}

