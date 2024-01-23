/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class LOTRBlockPipeweedCrop
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] pipeweedIcons;

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j < 7) {
            if (j == 6) {
                j = 5;
            }
            return this.pipeweedIcons[j >> 1];
        }
        return this.pipeweedIcons[3];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.pipeweedIcons = new IIcon[4];
        for (int i = 0; i < this.pipeweedIcons.length; ++i) {
            this.pipeweedIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + i);
        }
    }

    public Item func_149866_i() {
        return LOTRMod.pipeweedSeeds;
    }

    public Item func_149865_P() {
        return LOTRMod.pipeweedLeaf;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Crop;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (world.getBlockMetadata(i, j, k) == 7) {
            LOTRMod.pipeweedPlant.randomDisplayTick(world, i, j, k, random);
        }
    }
}

