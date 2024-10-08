/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityPlate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockPlate
extends BlockContainer {
    public static final Block.SoundType soundTypePlate = new Block.SoundType("lotr:plate", 1.0f, 1.0f){
        private Random rand = new Random();

        public float getPitch() {
            return super.getPitch();
        }

        public String getBreakSound() {
            return "lotr:block.plate.break";
        }

        public String getStepResourcePath() {
            return Block.soundTypeStone.getStepResourcePath();
        }

        public String func_150496_b() {
            return Block.soundTypeStone.func_150496_b();
        }
    };
    @SideOnly(value=Side.CLIENT)
    private IIcon[] plateIcons;
    private Item plateItem;

    public LOTRBlockPlate() {
        super(Material.circuits);
        this.setHardness(0.0f);
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 0.125f, 0.875f);
    }

    public void setPlateItem(Item item) {
        this.plateItem = item;
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityPlate();
    }

    public Item getItemDropped(int i, Random random, int j) {
        return this.plateItem;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k, EntityPlayer entityplayer) {
        ItemStack foodItem = LOTRBlockPlate.getFoodItem(world, i, j, k);
        if (foodItem != null) {
            ItemStack copy = foodItem.copy();
            copy.stackSize = 1;
            return copy;
        }
        int meta = world.getBlockMetadata(i, j, k);
        return new ItemStack(this.getItemDropped(meta, world.rand, 0), 1, this.damageDropped(meta));
    }

    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        ItemStack foodItem;
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (!world.isRemote && tileentity instanceof LOTRTileEntityPlate && (foodItem = ((LOTRTileEntityPlate)tileentity).getFoodItem()) != null) {
            this.dropBlockAsItem(world, i, j, k, foodItem);
        }
        super.breakBlock(world, i, j, k, block, meta);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getPlateRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return i == 1 ? this.plateIcons[0] : this.plateIcons[1];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.plateIcons = new IIcon[2];
        this.plateIcons[0] = iconregister.registerIcon(this.getTextureName() + "_top");
        this.plateIcons[1] = iconregister.registerIcon(this.getTextureName() + "_base");
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return this.canBlockStay(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        ItemStack itemstack = entityplayer.getCurrentEquippedItem();
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityPlate) {
            LOTRTileEntityPlate plate = (LOTRTileEntityPlate)tileentity;
            ItemStack plateItem = plate.getFoodItem();
            if (plateItem == null && LOTRTileEntityPlate.isValidFoodItem(itemstack)) {
                if (!world.isRemote) {
                    plateItem = itemstack.copy();
                    plateItem.stackSize = 1;
                    plate.setFoodItem(plateItem);
                }
                if (!entityplayer.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }
                return true;
            }
            if (plateItem != null) {
                if (itemstack != null && itemstack.isItemEqual(plateItem) && ItemStack.areItemStackTagsEqual((ItemStack)itemstack, (ItemStack)plateItem)) {
                    if (plateItem.stackSize < plateItem.getMaxStackSize()) {
                        if (!world.isRemote) {
                            ++plateItem.stackSize;
                            plate.setFoodItem(plateItem);
                        }
                        if (!entityplayer.capabilities.isCreativeMode) {
                            --itemstack.stackSize;
                        }
                        return true;
                    }
                } else if (entityplayer.canEat(false)) {
                    plateItem.getItem().onEaten(plateItem, world, entityplayer);
                    if (!world.isRemote) {
                        plate.setFoodItem(plateItem);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static ItemStack getFoodItem(World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityPlate) {
            return ((LOTRTileEntityPlate)tileentity).getFoodItem();
        }
        return null;
    }

    public void dropPlateItem(LOTRTileEntityPlate plate) {
        this.dropPlateItem(plate, plate.getFoodItem());
    }

    public void dropOnePlateItem(LOTRTileEntityPlate plate) {
        ItemStack item = plate.getFoodItem().copy();
        item.stackSize = 1;
        this.dropPlateItem(plate, item);
    }

    private void dropPlateItem(LOTRTileEntityPlate plate, ItemStack itemstack) {
        this.dropBlockAsItem(plate.getWorldObj(), plate.xCoord, plate.yCoord, plate.zCoord, itemstack);
    }

}

