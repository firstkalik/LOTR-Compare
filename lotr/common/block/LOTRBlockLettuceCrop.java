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

public class LOTRBlockLettuceCrop
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] lettuceIcons;

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j < 7) {
            if (j == 6) {
                j = 5;
            }
            return this.lettuceIcons[j >> 1];
        }
        return this.lettuceIcons[3];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.lettuceIcons = new IIcon[4];
        for (int i = 0; i < this.lettuceIcons.length; ++i) {
            this.lettuceIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + i);
        }
    }

    public Item func_149866_i() {
        return LOTRMod.lettuce;
    }

    public Item func_149865_P() {
        return LOTRMod.lettuce;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int i, int j, int k) {
        return EnumPlantType.Crop;
    }

    public int getRenderType() {
        return 1;
    }
}

