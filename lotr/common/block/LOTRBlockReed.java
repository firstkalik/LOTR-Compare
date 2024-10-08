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
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class LOTRBlockReed
extends Block
implements IPlantable {
    private static int MAX_GROW_HEIGHT = 3;
    private static int META_GROW_END = 15;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconUpper;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconLower;

    public LOTRBlockReed() {
        super(Material.plants);
        float f = 0.375f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 1.0f, 0.5f + f);
        this.setTickRandomly(true);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        Block below = world.getBlock(i, j - 1, k);
        int belowMeta = world.getBlockMetadata(i, j - 1, k);
        if (below == this) {
            return true;
        }
        return below.getMaterial() == Material.water && belowMeta == 0;
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return this.canPlaceBlockAt(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        this.checkCanStay(world, i, j, k);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (this.checkCanStay(world, i, j, k) && this.canReedGrow(world, i, j, k) && world.isAirBlock(i, j + 1, k)) {
            int belowReeds = 1;
            while (world.getBlock(i, j - belowReeds, k) == this) {
                ++belowReeds;
            }
            if (belowReeds < MAX_GROW_HEIGHT) {
                int meta = world.getBlockMetadata(i, j, k);
                if (meta == META_GROW_END) {
                    world.setBlock(i, j + 1, k, (Block)this, 0, 3);
                    world.setBlockMetadataWithNotify(i, j, k, 0, 4);
                } else {
                    world.setBlockMetadataWithNotify(i, j, k, meta + 1, 4);
                }
            }
        }
    }

    protected boolean canReedGrow(World world, int i, int j, int k) {
        return true;
    }

    private boolean checkCanStay(World world, int i, int j, int k) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            this.dropBlockAsItem(world, i, j, k, meta, 0);
            world.setBlockToAir(i, j, k);
            return false;
        }
        return true;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)this);
    }

    public int damageDropped(int i) {
        return 0;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getReedsRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        if (side == -2) {
            return this.iconLower;
        }
        if (side == -1) {
            return this.blockIcon;
        }
        world.getBlock(i, j - 1, k);
        Block above = world.getBlock(i, j + 1, k);
        if (above != this) {
            return this.iconUpper;
        }
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == -2) {
            return this.iconLower;
        }
        if (i == -1) {
            return this.blockIcon;
        }
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon(this.getTextureName() + "_mid");
        this.iconUpper = iconregister.registerIcon(this.getTextureName() + "_upper");
        this.iconLower = iconregister.registerIcon(this.getTextureName() + "_lower");
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Water;
    }

    public Block getPlant(IBlockAccess world, int i, int j, int k) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int i, int j, int k) {
        return world.getBlockMetadata(i, j, k);
    }
}

