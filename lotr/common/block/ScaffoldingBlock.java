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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.block.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ScaffoldingBlock
extends Block {
    private static final ForgeDirection[] DIRECTIONS = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
    @SideOnly(value=Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon bottomIcon;
    protected double minX = 0.01;
    protected double minZ = 0.01;
    protected double maxX = 0.99;
    protected double maxZ = 0.99;

    public ScaffoldingBlock() {
        super(Material.wood);
        this.setHardness(3.5f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setStepSound(soundTypeWood);
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        if (world.isSideSolid(x, y - 1, z, ForgeDirection.UP)) {
            return true;
        }
        for (ForgeDirection direction : DIRECTIONS) {
            BlockPos position = new BlockPos(x, y, z, direction);
            for (int i = 0; i < 4; ++i) {
                position.moveForwards(1);
                if (!world.getBlock(position.x, position.y, position.z).equals((Object)this) || !world.isSideSolid(position.x, position.y - 1, position.z, ForgeDirection.UP)) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canBlockStay(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox((double)((double)x + this.minX), (double)((double)y + this.minY), (double)((double)z + this.minZ), (double)((double)x + this.maxX), (double)((double)y + this.maxY), (double)((double)z + this.maxZ));
    }

    public IIcon getIcon(int side, int meta) {
        return side == 0 ? this.bottomIcon : (side == 1 ? this.topIcon : this.blockIcon);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP || side == ForgeDirection.DOWN;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset) {
        ItemStack item = player.inventory.mainInventory[player.inventory.currentItem];
        if (item != null && Block.getBlockFromItem((Item)item.getItem()).equals((Object)this)) {
            int maxHeight = world.getActualHeight();
            for (int i = y + 1; i < maxHeight; ++i) {
                Block block = world.getBlock(x, i, z);
                if (block == null || world.isAirBlock(x, i, z) || block.isReplaceable((IBlockAccess)world, x, i, z)) {
                    if (!world.isRemote && world.setBlock(x, i, z, (Block)this)) {
                        world.playAuxSFXAtEntity(null, 2001, x, i, z, Block.getIdFromBlock((Block)this));
                        if (!player.capabilities.isCreativeMode) {
                            --item.stackSize;
                            if (item.stackSize <= 0) {
                                player.inventory.mainInventory[player.inventory.currentItem] = null;
                            }
                        }
                    }
                    return true;
                }
                if (block.equals((Object)this)) {
                    continue;
                }
                return false;
            }
        }
        return false;
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity.boundingBox.minY >= (double)((float)y + 0.9777778f) || entity.boundingBox.maxY <= (double)((float)y + 0.022222223f)) {
            return;
        }
        entity.fallDistance = 0.0f;
        if (entity.isCollidedHorizontally) {
            entity.motionY = 0.2;
        } else if (entity.isSneaking()) {
            double diff = entity.prevPosY - entity.posY;
            AxisAlignedBB boundingBox = entity.boundingBox;
            boundingBox.minY += diff;
            boundingBox.maxY += diff;
            entity.posY = entity.prevPosY;
        } else {
            entity.motionY = -0.12;
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.topIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.bottomIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return !world.getBlock(x, y, z).isOpaqueCube();
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        this.onNeighborBlockChange(world, x, y, z, null);
    }
}

