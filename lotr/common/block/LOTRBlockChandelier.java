/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
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
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockOrcChain;
import lotr.common.block.LOTRBlockTorch;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockChandelier
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] chandelierIcons;
    private String[] chandelierNames = new String[]{"bronze", "iron", "silver", "gold", "mithril", "mallornSilver", "woodElven", "orc", "dwarven", "uruk", "highElven", "blueDwarven", "morgul", "mallornBlue", "mallornGold", "mallornGreen"};

    public LOTRBlockChandelier() {
        super(Material.circuits);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setHardness(0.0f);
        this.setResistance(2.0f);
        this.setStepSound(Block.soundTypeMetal);
        this.setLightLevel(0.9375f);
        this.setBlockBounds(0.0625f, 0.1875f, 0.0625f, 0.9375f, 1.0f, 0.9375f);
    }

    public IIcon getIcon(int i, int j) {
        if (j >= this.chandelierNames.length) {
            j = 0;
        }
        return this.chandelierIcons[j];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.chandelierIcons = new IIcon[this.chandelierNames.length];
        for (int i = 0; i < this.chandelierNames.length; ++i) {
            this.chandelierIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.chandelierNames[i]);
        }
    }

    public int damageDropped(int i) {
        return i;
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

    public boolean canBlockStay(World world, int i, int j, int k) {
        Block block = world.getBlock(i, j + 1, k);
        int meta = world.getBlockMetadata(i, j + 1, k);
        if (block instanceof BlockFence || block instanceof BlockWall) {
            return true;
        }
        if (block instanceof BlockSlab && !block.isOpaqueCube() && (meta & 8) == 0) {
            return true;
        }
        if (block instanceof BlockStairs && (meta & 4) == 0) {
            return true;
        }
        if (block instanceof LOTRBlockOrcChain) {
            return true;
        }
        return world.getBlock(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN);
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

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.chandelierNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMetadata(i, j, k);
        double d = 0.13;
        double d1 = 1.0 - d;
        double d2 = 0.6875;
        this.spawnChandelierParticles(world, (double)i + d, (double)j + d2, (double)k + d, random, meta);
        this.spawnChandelierParticles(world, (double)i + d1, (double)j + d2, (double)k + d1, random, meta);
        this.spawnChandelierParticles(world, (double)i + d, (double)j + d2, (double)k + d1, random, meta);
        this.spawnChandelierParticles(world, (double)i + d1, (double)j + d2, (double)k + d, random, meta);
    }

    private void spawnChandelierParticles(World world, double d, double d1, double d2, Random random, int meta) {
        if (meta == 5 || meta == 13 || meta == 14 || meta == 15) {
            LOTRBlockTorch torchBlock = null;
            if (meta == 5) {
                torchBlock = (LOTRBlockTorch)LOTRMod.mallornTorchSilver;
            } else if (meta == 13) {
                torchBlock = (LOTRBlockTorch)LOTRMod.mallornTorchBlue;
            } else if (meta == 14) {
                torchBlock = (LOTRBlockTorch)LOTRMod.mallornTorchGold;
            } else if (meta == 15) {
                torchBlock = (LOTRBlockTorch)LOTRMod.mallornTorchGreen;
            }
            LOTRBlockTorch.TorchParticle particle = torchBlock.createTorchParticle(random);
            if (particle != null) {
                particle.spawn(d, d1, d2);
            }
        } else if (meta == 6) {
            String s = "leafRed_" + (10 + random.nextInt(20));
            double d3 = -0.005 + (double)(random.nextFloat() * 0.01f);
            double d4 = -0.005 + (double)(random.nextFloat() * 0.01f);
            double d5 = -0.005 + (double)(random.nextFloat() * 0.01f);
            LOTRMod.proxy.spawnParticle(s, d, d1, d2, d3, d4, d5);
        } else if (meta == 10) {
            LOTRMod.proxy.spawnParticle("elvenGlow", d, d1, d2, 0.0, 0.0, 0.0);
        } else if (meta == 12) {
            double d3 = -0.05 + (double)random.nextFloat() * 0.1;
            double d4 = 0.1 + (double)random.nextFloat() * 0.1;
            double d5 = -0.05 + (double)random.nextFloat() * 0.1;
            LOTRMod.proxy.spawnParticle("morgulPortal", d, d1, d2, d3, d4, d5);
        } else {
            world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
            world.spawnParticle("flame", d, d1, d2, 0.0, 0.0, 0.0);
        }
    }
}

