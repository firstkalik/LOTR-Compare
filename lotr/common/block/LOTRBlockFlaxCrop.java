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
 *  net.minecraftforge.common.EnumPlantType
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class LOTRBlockFlaxCrop
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] flaxIcons;

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j < 7) {
            if (j == 6) {
                j = 5;
            }
            return this.flaxIcons[j >> 1];
        }
        return LOTRMod.flaxPlant.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.flaxIcons = new IIcon[3];
        for (int i = 0; i < this.flaxIcons.length; ++i) {
            this.flaxIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + i);
        }
    }

    public Item func_149866_i() {
        return LOTRMod.flaxSeeds;
    }

    public Item func_149865_P() {
        return LOTRMod.flax;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Crop;
    }
}

