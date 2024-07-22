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
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityOrcBomb;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockOrcBomb
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] orcBombIcons;

    public LOTRBlockOrcBomb() {
        super(Material.iron);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        this.setHardness(3.0f);
        this.setResistance(0.0f);
        this.setStepSound(Block.soundTypeMetal);
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int i) {
        int strength = LOTRBlockOrcBomb.getBombStrengthLevel(i);
        if (strength == 1) {
            return 11974326;
        }
        if (strength == 2) {
            return 7829367;
        }
        return 16777215;
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        int strength = LOTRBlockOrcBomb.getBombStrengthLevel(meta);
        if (strength == 1) {
            return 11974326;
        }
        if (strength == 2) {
            return 7829367;
        }
        return 16777215;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        boolean isFire = LOTRBlockOrcBomb.isFireBomb(j);
        if (i == -1) {
            return this.orcBombIcons[2];
        }
        if (i == 1) {
            return isFire ? this.orcBombIcons[4] : this.orcBombIcons[1];
        }
        return isFire ? this.orcBombIcons[3] : this.orcBombIcons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.orcBombIcons = new IIcon[5];
        this.orcBombIcons[0] = iconregister.registerIcon(this.getTextureName() + "_side");
        this.orcBombIcons[1] = iconregister.registerIcon(this.getTextureName() + "_top");
        this.orcBombIcons[2] = iconregister.registerIcon(this.getTextureName() + "_handle");
        this.orcBombIcons[3] = iconregister.registerIcon(this.getTextureName() + "_fire_side");
        this.orcBombIcons[4] = iconregister.registerIcon(this.getTextureName() + "_fire_top");
    }

    public static int getBombStrengthLevel(int meta) {
        return meta & 7;
    }

    public static boolean isFireBomb(int meta) {
        return (meta & 8) != 0;
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        if (world.isBlockIndirectlyGettingPowered(i, j, k)) {
            this.onBlockDestroyedByPlayer(world, i, j, k, -1);
            world.setBlockToAir(i, j, k);
        }
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        if (block.getMaterial() != Material.air && block.canProvidePower() && world.isBlockIndirectlyGettingPowered(i, j, k)) {
            this.onBlockDestroyedByPlayer(world, i, j, k, -1);
            world.setBlockToAir(i, j, k);
        }
    }

    public void onBlockExploded(World world, int i, int j, int k, Explosion explosion) {
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(i, j, k);
            LOTREntityOrcBomb bomb = new LOTREntityOrcBomb(world, (float)i + 0.5f, (float)j + 0.5f, (float)k + 0.5f, explosion.getExplosivePlacedBy());
            bomb.setBombStrengthLevel(meta);
            bomb.setFuseFromExplosion();
            bomb.droppedByPlayer = true;
            world.spawnEntityInWorld((Entity)bomb);
        }
        super.onBlockExploded(world, i, j, k, explosion);
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int meta) {
        if (!world.isRemote && meta == -1) {
            meta = world.getBlockMetadata(i, j, k);
            LOTREntityOrcBomb bomb = new LOTREntityOrcBomb(world, (float)i + 0.5f, (float)j + 0.5f, (float)k + 0.5f, null);
            bomb.setBombStrengthLevel(meta);
            bomb.droppedByPlayer = true;
            world.spawnEntityInWorld((Entity)bomb);
            world.playSoundAtEntity((Entity)bomb, "game.tnt.primed", 1.0f, 1.0f);
        }
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2) {
        if (entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() == LOTRMod.orcTorchItem) {
            this.onBlockDestroyedByPlayer(world, i, j, k, -1);
            world.setBlockToAir(i, j, k);
            return true;
        }
        return false;
    }

    public boolean canDropFromExplosion(Explosion explosion) {
        return true;
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
        return LOTRMod.proxy.getOrcBombRenderID();
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j <= 2; ++j) {
                list.add(new ItemStack(item, 1, j + i * 8));
            }
        }
    }
}

