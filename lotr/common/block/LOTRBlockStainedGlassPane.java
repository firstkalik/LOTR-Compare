/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStainedGlassPane
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
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockGlassPane;
import lotr.common.block.LOTRBlockPane;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRBlockStainedGlassPane
extends LOTRBlockGlassPane {
    private IIcon[] paneIcons = new IIcon[16];

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149735_b(int i, int j) {
        return this.paneIcons[j % this.paneIcons.length];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_150097_e() {
        return ((LOTRBlockPane)LOTRMod.glassPane).func_150097_e();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.func_149735_b(i, ~j & 0xF);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        for (int i = 0; i < this.paneIcons.length; ++i) {
            this.paneIcons[i] = iconregister.registerIcon("lotr:stainedGlass_" + ItemDye.field_150921_b[BlockStainedGlassPane.func_150103_c((int)i)]);
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
        for (int i = 0; i < this.paneIcons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

