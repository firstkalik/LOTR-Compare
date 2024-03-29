/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityGulduril;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTRBlockGuldurilBrick
extends Block {
    public LOTRBlockGuldurilBrick() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setStepSound(Block.soundTypeStone);
        this.setLightLevel(0.75f);
    }

    public static int guldurilMetaForBlock(Block block, int i) {
        if (block == null) {
            return -1;
        }
        if (block == LOTRMod.brick && i == 0) {
            return 0;
        }
        if (block == LOTRMod.brick && i == 7) {
            return 1;
        }
        if (block == LOTRMod.brick2 && i == 0) {
            return 2;
        }
        if (block == LOTRMod.brick2 && i == 1) {
            return 3;
        }
        if (block == LOTRMod.brick2 && i == 8) {
            return 4;
        }
        if (block == LOTRMod.brick2 && i == 9) {
            return 5;
        }
        if (block == LOTRMod.brick && i == 1) {
            return 6;
        }
        if (block == LOTRMod.brick && i == 2) {
            return 7;
        }
        if (block == LOTRMod.brick && i == 3) {
            return 8;
        }
        if (block == LOTRMod.brick2 && i == 11) {
            return 9;
        }
        return -1;
    }

    public static ItemStack blockForGuldurilMeta(int i) {
        if (i == 0) {
            return new ItemStack(LOTRMod.brick, 1, 0);
        }
        if (i == 1) {
            return new ItemStack(LOTRMod.brick, 1, 7);
        }
        if (i == 2) {
            return new ItemStack(LOTRMod.brick2, 1, 0);
        }
        if (i == 3) {
            return new ItemStack(LOTRMod.brick2, 1, 1);
        }
        if (i == 4) {
            return new ItemStack(LOTRMod.brick2, 1, 8);
        }
        if (i == 5) {
            return new ItemStack(LOTRMod.brick2, 1, 9);
        }
        if (i == 6) {
            return new ItemStack(LOTRMod.brick, 1, 1);
        }
        if (i == 7) {
            return new ItemStack(LOTRMod.brick, 1, 2);
        }
        if (i == 8) {
            return new ItemStack(LOTRMod.brick, 1, 3);
        }
        if (i == 9) {
            return new ItemStack(LOTRMod.brick2, 1, 11);
        }
        return null;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k) {
        return new ItemStack((Block)this, 1, world.getBlockMetadata(i, j, k));
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        Item item;
        ItemStack itemstack = LOTRBlockGuldurilBrick.blockForGuldurilMeta(j);
        if (itemstack != null && (item = itemstack.getItem()) instanceof ItemBlock) {
            Block block = ((ItemBlock)item).field_150939_a;
            int meta = itemstack.getItemDamage();
            return block.getIcon(i, meta);
        }
        return LOTRMod.brick.getIcon(i, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public int getRenderType() {
        return LOTRMod.proxy.getGuldurilRenderID();
    }

    public ArrayList getDrops(World world, int i, int j, int k, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        ItemStack drop = LOTRBlockGuldurilBrick.blockForGuldurilMeta(metadata);
        if (drop != null) {
            drops.add(drop);
        }
        return drops;
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new LOTRTileEntityGulduril();
    }

    protected boolean canSilkHarvest() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= 9; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

