/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.material.MaterialLogic
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFlintAndSteel
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityFallingFireJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLogic;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockRhunFireJar
extends BlockFalling {
    public static int renderingStage = 0;
    public static final int renderBase = 1;
    public static final int renderNeck = 2;
    public static final int renderLid = 3;
    public static final int renderCap = 4;
    public static final int renderCrown = 5;
    public static final int renderHandle = 6;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBaseSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBaseTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBaseBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconNeckSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconLidSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconLidTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconLidBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconCapSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconCapTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconCapBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconCrownSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconHandleSide;
    public static boolean explodeOnAdded = true;
    private static Material materialFireJar = new MaterialLogic(MapColor.stoneColor);

    public LOTRBlockRhunFireJar() {
        super(materialFireJar);
        this.setTickRandomly(true);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.setBlockBounds(0.125f, 0.0f, 0.125f, 0.875f, 1.0f, 0.875f);
        this.setHardness(0.5f);
        this.setStepSound(Block.soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.iconBaseSide = iconregister.registerIcon(this.getTextureName() + "_base_side");
        this.iconBaseTop = iconregister.registerIcon(this.getTextureName() + "_base_top");
        this.iconBaseBottom = iconregister.registerIcon(this.getTextureName() + "_base_bottom");
        this.iconNeckSide = iconregister.registerIcon(this.getTextureName() + "_neck_side");
        this.iconLidSide = iconregister.registerIcon(this.getTextureName() + "_lid_side");
        this.iconLidTop = iconregister.registerIcon(this.getTextureName() + "_lid_top");
        this.iconLidBottom = iconregister.registerIcon(this.getTextureName() + "_lid_bottom");
        this.iconCapSide = iconregister.registerIcon(this.getTextureName() + "_cap_side");
        this.iconCapTop = iconregister.registerIcon(this.getTextureName() + "_cap_top");
        this.iconCapBottom = iconregister.registerIcon(this.getTextureName() + "_cap_bottom");
        this.iconCrownSide = iconregister.registerIcon(this.getTextureName() + "_crown_side");
        this.iconHandleSide = iconregister.registerIcon(this.getTextureName() + "_handle_side");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (renderingStage == 1) {
            return i == 0 ? this.iconBaseBottom : (i == 1 ? this.iconBaseTop : this.iconBaseSide);
        }
        if (renderingStage == 2) {
            return this.iconNeckSide;
        }
        if (renderingStage == 3) {
            return i == 0 ? this.iconLidBottom : (i == 1 ? this.iconLidTop : this.iconLidSide);
        }
        if (renderingStage == 4) {
            return i == 0 ? this.iconCapBottom : (i == 1 ? this.iconCapTop : this.iconCapSide);
        }
        if (renderingStage == 5) {
            return this.iconCrownSide;
        }
        if (renderingStage == 6) {
            return this.iconHandleSide;
        }
        return LOTRMod.brick5.getIcon(i, 11);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getRhunFireJarRenderID();
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        if (world.isBlockIndirectlyGettingPowered(i, j, k)) {
            if (explodeOnAdded) {
                this.explode(world, i, j, k);
            }
        } else {
            super.onBlockAdded(world, i, j, k);
        }
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        int b0;
        if (LOTRMod.doFireTick(world)) {
            boolean foundFire = false;
            for (int l = 0; l < 12; ++l) {
                int range = 1 + random.nextInt(4);
                int i1 = i + MathHelper.getRandomIntegerInRange((Random)random, (int)(-range), (int)range);
                Block block = world.getBlock(i1, j + MathHelper.getRandomIntegerInRange((Random)random, (int)(-range), (int)range), k + MathHelper.getRandomIntegerInRange((Random)random, (int)(-range), (int)range));
                Material material = block.getMaterial();
                if (material != Material.fire && material != Material.lava) continue;
                foundFire = true;
                break;
            }
            if (foundFire) {
                this.explode(world, i, j, k);
            }
        }
        if (world.getBlock(i, j, k) == this && !world.isRemote && BlockFalling.func_149831_e((World)world, (int)i, (int)(j - 1), (int)k) && j >= 0 && world.checkChunksExist(i - 32, j - (b0 = 32), k - b0, i + b0, j + b0, k + b0)) {
            LOTREntityFallingFireJar falling = new LOTREntityFallingFireJar(world, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5, (Block)this, world.getBlockMetadata(i, j, k));
            this.func_149829_a((EntityFallingBlock)falling);
            world.spawnEntityInWorld((Entity)falling);
        }
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        super.onNeighborBlockChange(world, i, j, k, block);
        if (world.getBlock(i, j, k) == this) {
            if (world.isBlockIndirectlyGettingPowered(i, j, k)) {
                this.explode(world, i, j, k);
            } else if (!world.isRemote) {
                this.updateTick(world, i, j, k, world.rand);
            }
        }
    }

    public void explode(World world, int i, int j, int k) {
        if (!world.isRemote) {
            world.createExplosion(null, (double)i, (double)j, (double)k, 2.0f, false);
            world.setBlockToAir(i, j, k);
            int range = 2;
            for (int l = 0; l < 64; ++l) {
                int k1;
                int j1;
                int i1 = i + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-range), (int)range);
                Block block = world.getBlock(i1, j1 = j + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-range), (int)range), k1 = k + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-range), (int)range));
                if (!block.isAir((IBlockAccess)world, i1, j1, k1) && !block.isReplaceable((IBlockAccess)world, i1, j1, k1) || block.getMaterial().isLiquid()) continue;
                world.setBlock(i1, j1, k1, LOTRMod.rhunFire, 0, 3);
            }
        }
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        ItemStack itemstack = entityplayer.getCurrentEquippedItem();
        if (itemstack != null && itemstack.getItem() instanceof ItemFlintAndSteel) {
            this.explode(world, i, j, k);
            return true;
        }
        return false;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        double speed = Math.sqrt(entity.motionX * entity.motionX + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ);
        if (speed >= MathHelper.getRandomDoubleInRange((Random)world.rand, (double)0.3, (double)0.8)) {
            this.explode(world, i, j, k);
        }
    }

    public void onBlockExploded(World world, int i, int j, int k, Explosion explosion) {
        this.explode(world, i, j, k);
        super.onBlockExploded(world, i, j, k, explosion);
    }

    public void func_149828_a(World world, int i, int j, int k, int meta) {
        this.explode(world, i, j, k);
    }
}

