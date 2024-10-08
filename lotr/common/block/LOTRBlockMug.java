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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemBottlePoison;
import lotr.common.item.LOTRItemGraal;
import lotr.common.item.LOTRItemMug;
import lotr.common.tileentity.LOTRTileEntityMug;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class LOTRBlockMug
extends BlockContainer {
    public static final float MUG_SCALE = 0.75f;

    public LOTRBlockMug() {
        this(3.0f, 8.0f);
    }

    public LOTRBlockMug(float f, float f1) {
        super(Material.circuits);
        f /= 16.0f;
        f1 /= 16.0f;
        this.setBlockBounds(0.5f - (f *= 0.75f), 0.0f, 0.5f - f, 0.5f + f, f1 *= 0.75f, 0.5f + f);
        this.setHardness(0.0f);
        this.setStepSound(Block.soundTypeWood);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityMug();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return -1;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.planks.getIcon(i, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        Block block = world.getBlock(i, j - 1, k);
        return block.canPlaceTorchOnTop(world, i, j - 1, k);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return this.canBlockStay(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            int meta = world.getBlockMetadata(i, j, k);
            this.dropBlockAsItem(world, i, j, k, meta, 0);
            world.setBlockToAir(i, j, k);
        }
    }

    public void onBlockHarvested(World world, int i, int j, int k, int meta, EntityPlayer entityplayer) {
        if (entityplayer.capabilities.isCreativeMode) {
            world.setBlockMetadataWithNotify(i, j, k, meta |= 4, 4);
        }
        this.dropBlockAsItem(world, i, j, k, meta, 0);
        super.onBlockHarvested(world, i, j, k, meta, entityplayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((meta & 4) == 0) {
            ItemStack itemstack = LOTRBlockMug.getMugItem(world, i, j, k);
            LOTRTileEntityMug mug = (LOTRTileEntityMug)world.getTileEntity(i, j, k);
            if (mug != null) {
                drops.add(itemstack);
            }
        }
        return drops;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k) {
        return LOTRBlockMug.getMugItem(world, i, j, k);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        ItemStack itemstack = entityplayer.getCurrentEquippedItem();
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null && tileentity instanceof LOTRTileEntityMug) {
            LOTRTileEntityMug mug = (LOTRTileEntityMug)tileentity;
            ItemStack mugItem = mug.getMugItem();
            if (!mug.isEmpty() && LOTRItemMug.isItemEmptyDrink(itemstack)) {
                ItemStack takenDrink = mugItem.copy();
                LOTRItemMug.Vessel v = LOTRItemMug.getVessel(itemstack);
                LOTRItemMug.setVessel(takenDrink, v, true);
                if (entityplayer.capabilities.isCreativeMode) {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, takenDrink);
                } else {
                    --itemstack.stackSize;
                    if (itemstack.stackSize <= 0) {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, takenDrink);
                    } else if (!entityplayer.inventory.addItemStackToInventory(takenDrink)) {
                        entityplayer.dropPlayerItemWithRandomChoice(takenDrink, false);
                    }
                }
                mug.setEmpty();
                world.playSoundAtEntity((Entity)entityplayer, "lotr:item.mug_fill", 0.5f, 0.8f + world.rand.nextFloat() * 0.4f);
                return true;
            }
            if (itemstack != null && itemstack.getItem() instanceof LOTRItemGraal) {
                return false;
            }
            if (mug.isEmpty() && LOTRItemMug.isItemFullDrink(itemstack)) {
                ItemStack emptyMug = LOTRItemMug.getVessel(itemstack).getEmptyVessel();
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, emptyMug);
                ItemStack mugFill = itemstack.copy();
                mugFill.stackSize = 1;
                mug.setMugItem(mugFill);
                world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:item.mug_fill", 0.5f, 0.8f + world.rand.nextFloat() * 0.4f);
                return true;
            }
            if (!mug.isEmpty()) {
                if (itemstack != null && itemstack.getItem() instanceof LOTRItemBottlePoison && mug.canPoisonMug()) {
                    if (!world.isRemote) {
                        mug.poisonMug(entityplayer);
                        if (!entityplayer.capabilities.isCreativeMode) {
                            ItemStack containerItem = itemstack.getItem().getContainerItem(itemstack);
                            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, containerItem);
                        }
                        entityplayer.openContainer.detectAndSendChanges();
                        ((EntityPlayerMP)entityplayer).sendContainerToPlayer(entityplayer.openContainer);
                    }
                    return true;
                }
                ItemStack equivalentDrink = LOTRItemMug.getEquivalentDrink(mugItem);
                Item eqItem = equivalentDrink.getItem();
                boolean canDrink = false;
                if (eqItem instanceof LOTRItemMug) {
                    canDrink = ((LOTRItemMug)eqItem).canPlayerDrink(entityplayer);
                }
                if (canDrink) {
                    ItemStack mugItemResult = mugItem.onFoodEaten(world, entityplayer);
                    mugItemResult = ForgeEventFactory.onItemUseFinish((EntityPlayer)entityplayer, (ItemStack)mugItem, (int)mugItem.getMaxItemUseDuration(), (ItemStack)mugItemResult);
                    mug.setEmpty();
                    world.markBlockForUpdate(i, j, k);
                    world.playSoundAtEntity((Entity)entityplayer, "random.drink", 0.5f, world.rand.nextFloat() * 0.1f + 0.9f);
                    return true;
                }
            }
        }
        return false;
    }

    public static ItemStack getMugItem(World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityMug) {
            LOTRTileEntityMug mug = (LOTRTileEntityMug)tileentity;
            return mug.getMugItem();
        }
        return new ItemStack(LOTRMod.mug);
    }

    public static void setMugItem(World world, int i, int j, int k, ItemStack itemstack, LOTRItemMug.Vessel vessel) {
        TileEntity te = world.getTileEntity(i, j, k);
        if (te instanceof LOTRTileEntityMug) {
            LOTRTileEntityMug mug = (LOTRTileEntityMug)te;
            mug.setMugItem(itemstack);
            mug.setVessel(vessel);
        }
    }
}

