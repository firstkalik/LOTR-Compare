/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.oredict.ShapedOreRecipe
 */
package lotr.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityFallingTreasure;
import lotr.common.recipe.LOTRRecipesTreasurePile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class LOTRBlockTreasurePile
extends Block {
    public static final Block.SoundType soundTypeTreasure = new Block.SoundType("lotr:treasure", 1.0f, 1.0f){
        private Random rand = new Random();

        public float getPitch() {
            return super.getPitch() * (0.85f + this.rand.nextFloat() * 0.3f);
        }

        public String getBreakSound() {
            return "lotr:block.treasure.break";
        }

        public String getStepResourcePath() {
            return "lotr:block.treasure.step";
        }

        public String func_150496_b() {
            return "lotr:block.treasure.place";
        }
    };
    public static final int MAX_META = 7;
    @SideOnly(value=Side.CLIENT)
    private IIcon sideIcon;

    public LOTRBlockTreasurePile() {
        super(Material.circuits);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeTreasure);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        super.registerBlockIcons(iconregister);
        this.sideIcon = iconregister.registerIcon(this.getTextureName() + "_side");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 0 || i == 1) {
            return this.blockIcon;
        }
        return this.sideIcon;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        this.setBlockBoundsBasedOnState((IBlockAccess)world, i, j, k);
        this.maxY = this.maxY >= 1.0 ? 1.0 : (this.maxY >= 0.5 ? 0.5 : 0.0625);
        return super.getCollisionBoundingBoxFromPool(world, i, j, k);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        this.setBlockBoundsMeta(meta);
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBoundsMeta(0);
    }

    private void setBlockBoundsMeta(int meta) {
        float f = (float)(meta + 1) / 8.0f;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
    }

    public static void setTreasureBlockBounds(Block block, int meta) {
        if (block instanceof LOTRBlockTreasurePile) {
            ((LOTRBlockTreasurePile)block).setBlockBoundsMeta(meta);
        }
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        int meta = world.getBlockMetadata(i, j, k);
        if (meta == 7 && side == ForgeDirection.UP) {
            return true;
        }
        return super.isSideSolid(world, i, j, k, side);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getTreasureRenderID();
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return super.canPlaceBlockAt(world, i, j, k);
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        world.scheduleBlockUpdate(i, j, k, (Block)this, this.tickRate(world));
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        world.scheduleBlockUpdate(i, j, k, (Block)this, this.tickRate(world));
    }

    public int tickRate(World world) {
        return 2;
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (!(world.isRemote || this.tryFall(world, i, j, k) || this.canBlockStay(world, i, j, k))) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    private boolean tryFall(World world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        if (LOTRBlockTreasurePile.canFallUpon(world, i, j - 1, k, this, meta) && j >= 0) {
            int range = 32;
            if (!BlockFalling.fallInstantly && world.checkChunksExist(i - range, j - range, k - range, i + range, j + range, k + range)) {
                if (!world.isRemote) {
                    LOTREntityFallingTreasure fallingBlock = new LOTREntityFallingTreasure(world, (float)i + 0.5f, (float)j + 0.5f, (float)k + 0.5f, this, meta);
                    world.spawnEntityInWorld((Entity)fallingBlock);
                    return true;
                }
            } else {
                world.setBlockToAir(i, j, k);
                while (LOTRBlockTreasurePile.canFallUpon(world, i, j - 1, k, this, meta) && j > 0) {
                    --j;
                }
                if (j > 0) {
                    world.setBlock(i, j, k, (Block)this, meta, 3);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canFallUpon(World world, int i, int j, int k, Block thisBlock, int thisMeta) {
        Block block = world.getBlock(i, j, k);
        int meta = world.getBlockMetadata(i, j, k);
        if (block == thisBlock && meta < 7) {
            return true;
        }
        return BlockFalling.func_149831_e((World)world, (int)i, (int)j, (int)k);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        ItemStack itemstack = entityplayer.getHeldItem();
        if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock((Block)this) && side == 1) {
            boolean placedTreasure = false;
            int meta = world.getBlockMetadata(i, j, k);
            if (meta < 7) {
                int itemMeta;
                for (itemMeta = itemstack.getItemDamage(); meta < 7 && itemMeta >= 0; ++meta, --itemMeta) {
                }
                world.setBlockMetadataWithNotify(i, j, k, meta, 3);
                placedTreasure = true;
                if (itemMeta >= 0 && world.getBlock(i, j + 1, k).isReplaceable((IBlockAccess)world, i, j + 1, k)) {
                    world.setBlock(i, j + 1, k, (Block)this, itemMeta, 3);
                    itemMeta = -1;
                    placedTreasure = true;
                }
                if (placedTreasure) {
                    world.playSoundEffect((double)((float)i + 0.5f), (double)((float)j + 0.5f), (double)((float)k + 0.5f), this.stepSound.func_150496_b(), (this.stepSound.getVolume() + 1.0f) / 2.0f, this.stepSound.getPitch() * 0.8f);
                    if (!entityplayer.capabilities.isCreativeMode) {
                        if (itemMeta < 0) {
                            --itemstack.stackSize;
                        } else {
                            --itemstack.stackSize;
                            ItemStack remainder = itemstack.copy();
                            remainder.stackSize = 1;
                            remainder.setItemDamage(itemMeta);
                            if (itemstack.stackSize <= 0) {
                                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, remainder);
                            } else if (!entityplayer.inventory.addItemStackToInventory(remainder)) {
                                entityplayer.dropPlayerItemWithRandomChoice(remainder, false);
                            }
                        }
                        if (!world.isRemote) {
                            entityplayer.openContainer.detectAndSendChanges();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int damageDropped(int i) {
        return i;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 1;
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
        this.setBlockBoundsBasedOnState((IBlockAccess)world, i, j, k);
        for (int l = 0; l < 8; ++l) {
            double d = (float)i + world.rand.nextFloat();
            double d1 = (double)j + this.maxY;
            double d2 = (float)k + world.rand.nextFloat();
            double d3 = MathHelper.randomFloatClamp((Random)world.rand, (float)-0.15f, (float)0.15f);
            double d4 = MathHelper.randomFloatClamp((Random)world.rand, (float)0.1f, (float)0.4f);
            double d5 = MathHelper.randomFloatClamp((Random)world.rand, (float)-0.15f, (float)0.15f);
            world.spawnParticle("blockdust_" + Block.getIdFromBlock((Block)this) + "_0", d, d1, d2, d3, d4, d5);
        }
    }

    public void onFallenUpon(World world, int i, int j, int k, Entity entity, float f) {
        this.onEntityWalking(world, i, j, k, entity);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 7));
    }

    public static void generateTreasureRecipes(Block block, Item ingot) {
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(block, 8, 0), new Object[]{"XX", "XX", Character.valueOf('X'), ingot}));
        GameRegistry.addRecipe((IRecipe)new LOTRRecipesTreasurePile(block, ingot));
    }

}

