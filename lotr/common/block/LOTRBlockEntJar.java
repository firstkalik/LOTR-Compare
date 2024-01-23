/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemEntDraught;
import lotr.common.item.LOTRItemMug;
import lotr.common.recipe.LOTREntJarRecipes;
import lotr.common.tileentity.LOTRTileEntityEntJar;
import lotr.common.world.biome.LOTRBiomeGenFangorn;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockEntJar
extends BlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] jarIcons;

    public LOTRBlockEntJar() {
        super(Material.clay);
        this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 0.875f, 0.75f);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabUtil);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntityEntJar();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getEntJarRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return i == 0 || i == 1 ? this.jarIcons[0] : this.jarIcons[1];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.jarIcons = new IIcon[2];
        this.jarIcons[0] = iconregister.registerIcon(this.getTextureName() + "_top");
        this.jarIcons[1] = iconregister.registerIcon(this.getTextureName() + "_side");
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
        ItemStack itemstack = entityplayer.getHeldItem();
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity instanceof LOTRTileEntityEntJar) {
            LOTRTileEntityEntJar jar = (LOTRTileEntityEntJar)tileentity;
            if (itemstack != null && itemstack.getItem() instanceof LOTRItemEntDraught && jar.fillFromBowl(itemstack)) {
                if (!entityplayer.capabilities.isCreativeMode) {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Items.bowl));
                }
                world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:item.mug_fill", 0.5f, 0.8f + world.rand.nextFloat() * 0.4f);
                return true;
            }
            if (jar.drinkMeta >= 0) {
                ItemStack drink = new ItemStack(LOTRMod.entDraught, 1, jar.drinkMeta);
                if (itemstack != null && itemstack.getItem() == Items.bowl) {
                    if (!entityplayer.capabilities.isCreativeMode) {
                        --itemstack.stackSize;
                    }
                    if (itemstack.stackSize <= 0) {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, drink.copy());
                    } else if (!entityplayer.inventory.addItemStackToInventory(drink.copy())) {
                        entityplayer.dropPlayerItemWithRandomChoice(drink.copy(), false);
                    }
                    world.playSoundAtEntity((Entity)entityplayer, "lotr:item.mug_fill", 0.5f, 0.8f + world.rand.nextFloat() * 0.4f);
                    jar.consume();
                    return true;
                }
            } else if (itemstack != null) {
                ItemStack vesselEmpty;
                LOTRItemMug.Vessel[] draught;
                ItemStack vesselFull;
                BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
                if (biome instanceof LOTRBiomeGenFangorn && jar.drinkAmount > 0 && (draught = LOTREntJarRecipes.findMatchingRecipe(itemstack)) != null) {
                    jar.drinkMeta = draught.getItemDamage();
                    if (!entityplayer.capabilities.isCreativeMode) {
                        --itemstack.stackSize;
                    }
                    if (!world.isRemote) {
                        world.playAuxSFX(2005, i, j, k, 0);
                    }
                    return true;
                }
                if (jar.drinkAmount > 0) {
                    if (this.tryTakeWaterFromJar(jar, world, entityplayer, new ItemStack(Items.bucket), new ItemStack(Items.water_bucket), LOTRTileEntityEntJar.MAX_CAPACITY)) {
                        return true;
                    }
                    for (LOTRItemMug.Vessel vessel : LOTRItemMug.Vessel.values()) {
                        vesselEmpty = vessel.getEmptyVessel();
                        vesselFull = new ItemStack(LOTRMod.mugWater);
                        LOTRItemMug.setVessel(vesselFull, vessel, true);
                        if (!this.tryTakeWaterFromJar(jar, world, entityplayer, vesselEmpty, vesselFull, 1)) continue;
                        return true;
                    }
                }
                if (jar.drinkAmount < LOTRTileEntityEntJar.MAX_CAPACITY) {
                    if (this.tryAddWaterToJar(jar, world, entityplayer, new ItemStack(Items.water_bucket), new ItemStack(Items.bucket), LOTRTileEntityEntJar.MAX_CAPACITY)) {
                        return true;
                    }
                    for (LOTRItemMug.Vessel vessel : LOTRItemMug.Vessel.values()) {
                        vesselEmpty = vessel.getEmptyVessel();
                        vesselFull = new ItemStack(LOTRMod.mugWater);
                        LOTRItemMug.setVessel(vesselFull, vessel, true);
                        if (!this.tryAddWaterToJar(jar, world, entityplayer, vesselFull, vesselEmpty, 1)) continue;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean tryTakeWaterFromJar(LOTRTileEntityEntJar jar, World world, EntityPlayer entityplayer, ItemStack container, ItemStack filled, int amount) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack.getItem() != container.getItem() || itemstack.getItemDamage() != container.getItemDamage()) {
            return false;
        }
        for (int i = 0; i < amount; ++i) {
            jar.consume();
        }
        world.playSoundAtEntity((Entity)entityplayer, "lotr:item.mug_fill", 0.5f, 0.8f + world.rand.nextFloat() * 0.4f);
        if (!entityplayer.capabilities.isCreativeMode) {
            --itemstack.stackSize;
            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, filled.copy());
            } else if (!entityplayer.inventory.addItemStackToInventory(filled.copy())) {
                entityplayer.dropPlayerItemWithRandomChoice(filled.copy(), false);
            }
        }
        return true;
    }

    private boolean tryAddWaterToJar(LOTRTileEntityEntJar jar, World world, EntityPlayer entityplayer, ItemStack filled, ItemStack container, int amount) {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack.getItem() != filled.getItem() || itemstack.getItemDamage() != filled.getItemDamage()) {
            return false;
        }
        for (int i = 0; i < amount; ++i) {
            jar.fillWithWater();
        }
        world.playSoundAtEntity((Entity)entityplayer, "lotr:item.mug_fill", 0.5f, 0.8f + world.rand.nextFloat() * 0.4f);
        if (!entityplayer.capabilities.isCreativeMode) {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, container.copy());
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        TileEntity tileentity;
        if (random.nextInt(4) == 0 && (tileentity = world.getTileEntity(i, j, k)) instanceof LOTRTileEntityEntJar) {
            LOTRTileEntityEntJar jar = (LOTRTileEntityEntJar)tileentity;
            if (jar.drinkMeta >= 0) {
                double d = (double)i + 0.25 + (double)(random.nextFloat() * 0.5f);
                double d1 = (double)j + 1.0;
                double d2 = (double)k + 0.25 + (double)(random.nextFloat() * 0.5f);
                world.spawnParticle("happyVillager", d, d1, d2, 0.0, 0.2, 0.0);
            }
        }
    }
}

