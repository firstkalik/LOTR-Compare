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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.FoodStats
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockPlaceableFood
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconEaten;
    public Item foodItem;
    private float foodHalfWidth;
    private float foodHeight;
    private static int MAX_EATS = 6;
    private int healAmount;
    private float saturationAmount;

    public LOTRBlockPlaceableFood() {
        this(0.4375f, 0.5f);
    }

    public LOTRBlockPlaceableFood(float f, float f1) {
        super(Material.cake);
        this.foodHalfWidth = f;
        this.foodHeight = f1;
        this.setHardness(0.5f);
        this.setStepSound(Block.soundTypeCloth);
        this.setTickRandomly(true);
        this.setFoodStats(2, 0.1f);
    }

    public LOTRBlockPlaceableFood setFoodStats(int i, float f) {
        this.healAmount = i;
        this.saturationAmount = f;
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 0) {
            return this.iconBottom;
        }
        if (i == 1) {
            return this.iconTop;
        }
        if (j > 0 && i == 4) {
            return this.iconEaten;
        }
        return this.iconSide;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.iconBottom = iconregister.registerIcon(this.getTextureName() + "_bottom");
        this.iconTop = iconregister.registerIcon(this.getTextureName() + "_top");
        this.iconSide = iconregister.registerIcon(this.getTextureName() + "_side");
        this.iconEaten = iconregister.registerIcon(this.getTextureName() + "_inner");
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        float f = 0.5f - this.foodHalfWidth;
        float f1 = 0.5f + this.foodHalfWidth;
        float f2 = f + (f1 - f) * ((float)world.getBlockMetadata(i, j, k) / (float)MAX_EATS);
        this.setBlockBounds(f2, 0.0f, f, f1, this.foodHeight, f1);
    }

    public void setBlockBoundsForItemRender() {
        float f = 0.5f - this.foodHalfWidth;
        float f1 = 0.5f + this.foodHalfWidth;
        this.setBlockBounds(f, 0.0f, f, f1, this.foodHeight, f1);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        float f = 0.5f - this.foodHalfWidth;
        float f1 = 0.5f + this.foodHalfWidth;
        float f2 = f + (f1 - f) * ((float)world.getBlockMetadata(i, j, k) / (float)MAX_EATS);
        return AxisAlignedBB.getBoundingBox((double)((float)i + f2), (double)j, (double)((float)k + f), (double)((float)i + f1), (double)((float)j + this.foodHeight), (double)((float)k + f1));
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        return this.getCollisionBoundingBoxFromPool(world, i, j, k);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        this.eatCake(world, i, j, k, entityplayer);
        return true;
    }

    private void eatCake(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (!world.isRemote && entityplayer.canEat(false)) {
            entityplayer.getFoodStats().addStats(this.healAmount, this.saturationAmount);
            entityplayer.playSound("random.burp", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
            int meta = world.getBlockMetadata(i, j, k);
            if (++meta >= MAX_EATS) {
                world.setBlockToAir(i, j, k);
            } else {
                world.setBlockMetadataWithNotify(i, j, k, meta, 3);
            }
        }
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return super.canPlaceBlockAt(world, i, j, k) && this.canBlockStay(world, i, j, k);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            this.dropBlockAsItem(world, i, j, k, meta, 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if (meta == 0) {
            if (this.foodItem != null) {
                drops.add(new ItemStack(this.foodItem));
            } else {
                drops.add(new ItemStack((Block)this, 1, 0));
            }
        }
        return drops;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int i, int j, int k) {
        if (this.foodItem != null) {
            return this.foodItem;
        }
        return Item.getItemFromBlock((Block)this);
    }
}

