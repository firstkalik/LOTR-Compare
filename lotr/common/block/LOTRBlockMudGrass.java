/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockStem
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockStem;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockMudGrass
extends Block
implements IGrowable {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;

    public LOTRBlockMudGrass() {
        super(Material.grass);
        this.setHardness(0.6f);
        this.setStepSound(Block.soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setTickRandomly(true);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return this.iconTop;
        }
        if (i == 0) {
            return LOTRMod.mud.getBlockTextureFromSide(i);
        }
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon(this.getTextureName() + "_side");
        this.iconTop = iconregister.registerIcon(this.getTextureName() + "_top");
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        Blocks.grass.updateTick(world, i, j, k, random);
    }

    public boolean canSustainPlant(IBlockAccess world, int i, int j, int k, ForgeDirection direction, IPlantable plantable) {
        return Blocks.grass.canSustainPlant(world, i, j, k, direction, plantable) || plantable instanceof BlockStem;
    }

    public void onPlantGrow(World world, int i, int j, int k, int sourceX, int sourceY, int sourceZ) {
        world.setBlock(i, j, k, LOTRMod.mud, 0, 2);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return LOTRMod.mud.getItemDropped(0, random, j);
    }

    @SideOnly(value=Side.CLIENT)
    public int getBlockColor() {
        return 16777215;
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int i) {
        return 16777215;
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int i, int j, int k) {
        return 16777215;
    }

    public boolean func_149851_a(World world, int i, int j, int k, boolean flag) {
        return Blocks.grass.func_149851_a(world, i, j, k, flag);
    }

    public boolean func_149852_a(World world, Random random, int i, int j, int k) {
        return Blocks.grass.func_149852_a(world, random, i, j, k);
    }

    public void func_149853_b(World world, Random random, int i, int j, int k) {
        Blocks.grass.func_149853_b(world, random, i, j, k);
    }
}

