/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockStainedGlass
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.block.LOTRBlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRBlockStainedGlass
extends LOTRBlockGlass {
    private IIcon[] glassIcons = new IIcon[16];

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.glassIcons[j % this.glassIcons.length];
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        for (int i = 0; i < this.glassIcons.length; ++i) {
            this.glassIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[BlockStainedGlass.func_149997_b((int)i)]);
        }
    }

    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.glassIcons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

