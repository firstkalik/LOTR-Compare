/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
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
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class LOTRBlockLeekCrop
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] leekIcons;

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j < 7) {
            if (j == 6) {
                j = 5;
            }
            return this.leekIcons[j >> 1];
        }
        return this.leekIcons[3];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.leekIcons = new IIcon[4];
        for (int i = 0; i < this.leekIcons.length; ++i) {
            this.leekIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + i);
        }
    }

    public Item func_149866_i() {
        return LOTRMod.leek;
    }

    public Item func_149865_P() {
        return LOTRMod.leek;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Crop;
    }
}

