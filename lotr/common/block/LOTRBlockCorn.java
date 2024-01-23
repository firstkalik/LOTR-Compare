/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockCorn
extends Block
implements IPlantable,
IGrowable {
    public static int MAX_GROW_HEIGHT = 3;
    private static int META_GROW_END = 7;
    @SideOnly(value=Side.CLIENT)
    private IIcon cornIcon;

    public LOTRBlockCorn() {
        super(Material.plants);
        float f = 0.375f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 1.0f, 0.5f + f);
        this.setTickRandomly(true);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (this.checkCanStay(world, i, j, k)) {
            int cornHeight = 1;
            while (world.getBlock(i, j - cornHeight, k) == this) {
                ++cornHeight;
            }
            float growth = this.getGrowthFactor(world, i, j - cornHeight + 1, k);
            if (world.isAirBlock(i, j + 1, k) && cornHeight < MAX_GROW_HEIGHT) {
                int meta = world.getBlockMetadata(i, j, k);
                int corn = meta & 8;
                int grow = meta & 7;
                if (grow == META_GROW_END) {
                    world.setBlock(i, j + 1, k, (Block)this, 0, 3);
                    grow = 0;
                } else {
                    ++grow;
                }
                meta = corn | grow;
                world.setBlockMetadataWithNotify(i, j, k, meta, 4);
            }
            if (!LOTRBlockCorn.hasCorn(world, i, j, k) && this.canGrowCorn(world, i, j, k) && world.rand.nextFloat() < growth) {
                LOTRBlockCorn.setHasCorn(world, i, j, k, true);
            }
        }
    }

    private float getGrowthFactor(World world, int i, int j, int k) {
        float growth = 1.0f;
        Block below = world.getBlock(i, j - 1, k);
        if (below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.wheat)) {
            growth = 3.0f;
            if (below.isFertile(world, i, j - 1, k)) {
                growth = 9.0f;
            }
        }
        if (world.isRaining()) {
            growth *= 3.0f;
        }
        return growth / 250.0f;
    }

    private boolean canGrowCorn(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k) == this;
    }

    public static boolean hasCorn(World world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        return LOTRBlockCorn.metaHasCorn(meta);
    }

    private static boolean metaHasCorn(int l) {
        return (l & 8) != 0;
    }

    public static void setHasCorn(World world, int i, int j, int k, boolean flag) {
        int meta = world.getBlockMetadata(i, j, k);
        meta = flag ? (meta |= 8) : (meta &= 7);
        world.setBlockMetadataWithNotify(i, j, k, meta, 3);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        Block below = world.getBlock(i, j - 1, k);
        if (below == this) {
            return true;
        }
        IPlantable beachTest = new IPlantable(){

            public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
                return EnumPlantType.Beach;
            }

            public Block getPlant(IBlockAccess world, int i, int j, int k) {
                return LOTRBlockCorn.this;
            }

            public int getPlantMetadata(IBlockAccess world, int i, int j, int k) {
                return 0;
            }
        };
        return below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)this) || below.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, beachTest);
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return this.canPlaceBlockAt(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        this.checkCanStay(world, i, j, k);
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
        return 1;
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2) {
        if (LOTRBlockCorn.hasCorn(world, i, j, k)) {
            if (!world.isRemote) {
                int preMeta = world.getBlockMetadata(i, j, k);
                LOTRBlockCorn.setHasCorn(world, i, j, k, false);
                if (!world.isRemote) {
                    ArrayList<ItemStack> cornDrops = this.getCornDrops(world, i, j, k, preMeta);
                    for (ItemStack corn : cornDrops) {
                        this.dropBlockAsItem(world, i, j, k, corn);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)this);
    }

    public int damageDropped(int i) {
        return 0;
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.addAll(super.getDrops(world, i, j, k, meta, fortune));
        drops.addAll(this.getCornDrops(world, i, j, k, meta));
        return drops;
    }

    public ArrayList<ItemStack> getCornDrops(World world, int i, int j, int k, int meta) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if (LOTRBlockCorn.metaHasCorn(meta)) {
            int corns = 1;
            if (world.rand.nextInt(4) == 0) {
                ++corns;
            }
            for (int l = 0; l < corns; ++l) {
                drops.add(new ItemStack(LOTRMod.corn));
            }
        }
        return drops;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (LOTRBlockCorn.metaHasCorn(j)) {
            return this.cornIcon;
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        super.registerBlockIcons(iconregister);
        this.cornIcon = iconregister.registerIcon(this.getTextureName() + "_corn");
    }

    @SideOnly(value=Side.CLIENT)
    public String getItemIconName() {
        return this.getTextureName();
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Crop;
    }

    public Block getPlant(IBlockAccess world, int i, int j, int k) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int i, int j, int k) {
        return world.getBlockMetadata(i, j, k);
    }

    public boolean func_149851_a(World world, int i, int j, int k, boolean isRemote) {
        return world.getBlock(i, j - 1, k) != this && world.isAirBlock(i, j + 1, k) || !LOTRBlockCorn.hasCorn(world, i, j, k) && this.canGrowCorn(world, i, j, k);
    }

    public boolean func_149852_a(World world, Random random, int i, int j, int k) {
        return true;
    }

    public void func_149853_b(World world, Random random, int i, int j, int k) {
        if (world.getBlock(i, j - 1, k) != this && world.isAirBlock(i, j + 1, k)) {
            world.setBlock(i, j + 1, k, (Block)this, 0, 3);
        } else if (!LOTRBlockCorn.hasCorn(world, i, j, k) && this.canGrowCorn(world, i, j, k) && random.nextInt(2) == 0) {
            LOTRBlockCorn.setHasCorn(world, i, j, k, true);
        }
    }

}

