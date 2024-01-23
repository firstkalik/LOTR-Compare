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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockStalactite
extends Block {
    private Block modelBlock;
    private int modelMeta;

    public LOTRBlockStalactite(Block block, int meta) {
        super(block.getMaterial());
        this.modelBlock = block;
        this.modelMeta = meta;
        this.setStepSound(this.modelBlock.stepSound);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.setBlockBounds(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f);
    }

    public float getBlockHardness(World world, int i, int j, int k) {
        return this.modelBlock.getBlockHardness(world, i, j, k);
    }

    public float getExplosionResistance(Entity entity) {
        return this.modelBlock.getExplosionResistance(entity);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.modelBlock.getIcon(i, this.modelMeta);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    public int quantityDropped(Random random) {
        return this.modelBlock.quantityDropped(random);
    }

    public int damageDropped(int i) {
        return i;
    }

    public boolean canSilkHarvest(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
        return true;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return LOTRMod.proxy.getStalactiteRenderID();
    }

    public boolean canBlockStay(World world, int i, int j, int k) {
        int metadata = world.getBlockMetadata(i, j, k);
        if (metadata == 0) {
            return world.getBlock(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN);
        }
        if (metadata == 1) {
            return world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
        }
        return false;
    }

    public boolean canReplace(World world, int i, int j, int k, int side, ItemStack itemstack) {
        int metadata = itemstack.getItemDamage();
        if (metadata == 0) {
            return world.getBlock(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN);
        }
        if (metadata == 1) {
            return world.getBlock(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP);
        }
        return false;
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (!this.canBlockStay(world, i, j, k)) {
            this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockToAir(i, j, k);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j <= 1; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        Block above;
        if (random.nextInt(50) == 0 && world.getBlockMetadata(i, j, k) == 0 && (above = world.getBlock(i, j + 1, k)).isOpaqueCube() && above.getMaterial() == Material.rock) {
            world.spawnParticle("dripWater", (double)i + 0.6, (double)j, (double)k + 0.6, 0.0, 0.0, 0.0);
        }
    }

    public void onFallenUpon(World world, int i, int j, int k, Entity entity, float fallDistance) {
        if (entity instanceof EntityLivingBase && world.getBlockMetadata(i, j, k) == 1) {
            int damage = (int)(fallDistance * 2.0f) + 1;
            entity.attackEntityFrom(DamageSource.fall, (float)damage);
        }
    }
}

