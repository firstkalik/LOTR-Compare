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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockRock
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] rockIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconMordorSide;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconMordorMoss;
    private String[] rockNames = new String[]{"mordor", "gondor", "rohan", "blue", "red", "chalk"};

    public LOTRBlockRock() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(1.5f);
        this.setResistance(10.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    public boolean isReplaceableOreGen(World world, int i, int j, int k, Block target) {
        if (target == this) {
            return world.getBlockMetadata(i, j, k) == 0;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.rockIcons = new IIcon[this.rockNames.length];
        for (int i = 0; i < this.rockNames.length; ++i) {
            String subName = this.getTextureName() + "_" + this.rockNames[i];
            this.rockIcons[i] = iconregister.registerIcon(subName);
            if (i != 0) continue;
            this.iconMordorSide = iconregister.registerIcon(subName + "_side");
            this.iconMordorMoss = iconregister.registerIcon(subName + "_moss");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        Block above;
        int meta = world.getBlockMetadata(i, j, k);
        if (meta == 0 && side != 1 && side != 0 && (above = world.getBlock(i, j + 1, k)) == LOTRMod.mordorMoss) {
            return this.iconMordorMoss;
        }
        return super.getIcon(world, i, j, k, side);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta >= this.rockNames.length) {
            meta = 0;
        }
        if (meta == 0 && side != 1 && side != 0) {
            return this.iconMordorSide;
        }
        return this.rockIcons[meta];
    }

    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.rockNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (world.getBlock(i, j, k) == this && world.getBlockMetadata(i, j, k) == 0 && random.nextInt(10) == 0) {
            world.spawnParticle("smoke", (double)((float)i + random.nextFloat()), (double)((float)j + 1.1f), (double)((float)k + random.nextFloat()), 0.0, 0.0, 0.0);
        }
    }
}

