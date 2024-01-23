/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.block.LOTRBlockFlower;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRBlockFangornPlant
extends LOTRBlockFlower {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] plantIcons;
    private String[] plantNames = new String[]{"green", "brown", "gold", "yellow", "red", "silver"};

    public LOTRBlockFangornPlant() {
        this.setFlowerBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.8f, 0.8f);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j >= this.plantNames.length) {
            j = 0;
        }
        return this.plantIcons[j];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.plantIcons = new IIcon[this.plantNames.length];
        for (int i = 0; i < this.plantNames.length; ++i) {
            this.plantIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.plantNames[i]);
        }
    }

    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < this.plantNames.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
}

