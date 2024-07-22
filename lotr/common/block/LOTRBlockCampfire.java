/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRTileEntityCampfire;
import lotr.common.item.LOTRItemMatch;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockCampfire
extends BlockContainer {
    public LOTRBlockCampfire() {
        super(Material.wood);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f);
        this.setHardness(0.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeWood);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return Blocks.planks.getIcon(i, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityCampfire();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getCampfireRenderID();
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
        } else if (LOTRBlockCampfire.isLit((IBlockAccess)world, i, j, k) && world.getBlock(i, j + 1, k).getMaterial() == Material.water) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "random.fizz", 0.5f, 2.6f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8f);
            if (!world.isRemote) {
                LOTRBlockCampfire.setLit(world, i, j, k, false);
            }
        }
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        ItemStack itemstack = entityplayer.getCurrentEquippedItem();
        if (this.canItemLightBeacon(itemstack) && !LOTRBlockCampfire.isLit((IBlockAccess)world, i, j, k) && world.getBlock(i, j + 1, k).getMaterial() != Material.water) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "fire.ignite", 1.0f, world.rand.nextFloat() * 0.4f + 0.8f);
            if (!entityplayer.capabilities.isCreativeMode) {
                if (itemstack.getItem().isDamageable()) {
                    itemstack.damageItem(1, (EntityLivingBase)entityplayer);
                } else if (itemstack.getMaxStackSize() > 1) {
                    --itemstack.stackSize;
                    if (itemstack.stackSize <= 0) {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                }
            }
            if (!world.isRemote) {
                LOTRBlockCampfire.setLit(world, i, j, k, true);
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.burnCampfire);
            }
            return true;
        }
        if (itemstack != null && itemstack.getItem() == Items.water_bucket && LOTRBlockCampfire.isLit((IBlockAccess)world, i, j, k)) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "random.fizz", 0.5f, 2.6f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8f);
            if (!entityplayer.capabilities.isCreativeMode) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Items.bucket));
            }
            if (!world.isRemote) {
                LOTRBlockCampfire.setLit(world, i, j, k, false);
            }
            return true;
        }
        return true;
    }

    private boolean canItemLightBeacon(ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        }
        Item item = itemstack.getItem();
        return item == Items.flint_and_steel || item instanceof LOTRItemMatch || item instanceof ItemBlock && ((ItemBlock)item).field_150939_a instanceof BlockTorch;
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        return LOTRBlockCampfire.isFullyLit(world, i, j, k) ? 15 : 0;
    }

    public static boolean isLit(IBlockAccess world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityCampfire) {
            LOTRTileEntityCampfire beacon = (LOTRTileEntityCampfire)tileentity;
            return beacon.isLit();
        }
        return false;
    }

    public static boolean isFullyLit(IBlockAccess world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityCampfire) {
            LOTRTileEntityCampfire beacon = (LOTRTileEntityCampfire)tileentity;
            return beacon.isFullyLit();
        }
        return false;
    }

    public static void setLit(World world, int i, int j, int k, boolean lit) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityCampfire) {
            LOTRTileEntityCampfire beacon = (LOTRTileEntityCampfire)tileentity;
            beacon.setLit(lit);
        }
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        if (entity.isBurning() && !LOTRBlockCampfire.isLit((IBlockAccess)world, i, j, k) && world.getBlock(i, j + 1, k).getMaterial() != Material.water) {
            world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "fire.ignite", 1.0f, world.rand.nextFloat() * 0.4f + 0.8f);
            if (!world.isRemote) {
                LOTRBlockCampfire.setLit(world, i, j, k, true);
                entity.setDead();
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (!LOTRBlockCampfire.isLit((IBlockAccess)world, i, j, k)) {
            return;
        }
        if (random.nextInt(24) == 0) {
            world.playSound((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "fire.fire", 1.0f + random.nextFloat(), random.nextFloat() * 0.7f + 0.3f, false);
        }
        for (int l = 0; l < 2; ++l) {
            double d = (float)i + random.nextFloat();
            double d1 = (double)j + (double)random.nextFloat() * 0.5 + 0.5;
            double d2 = (float)k + random.nextFloat();
            world.spawnParticle("largesmoke", d, d1, d2, 0.0, 0.0, 0.0);
            world.spawnParticle("flame", (double)i + 0.55, (double)j + 0.25, (double)k + 0.55, 0.0, 0.0, 0.0);
            world.spawnParticle("flame", (double)i + 0.45, (double)j + 0.25, (double)k + 0.45, 0.0, 0.0, 0.0);
            world.spawnParticle("flame", (double)i + 0.5, (double)j + 0.25, (double)k + 0.5, 0.0, 0.0, 0.0);
        }
    }
}

