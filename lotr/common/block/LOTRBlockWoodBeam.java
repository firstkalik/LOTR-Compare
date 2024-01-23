/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.material.Material
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
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public abstract class LOTRBlockWoodBeam
extends BlockRotatedPillar {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] sideIcons;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] topIcons;
    private String[] woodNames;

    public LOTRBlockWoodBeam() {
        super(Material.wood);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(2.0f);
        this.setStepSound(Block.soundTypeWood);
    }

    protected void setWoodNames(String ... s) {
        this.woodNames = s;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.sideIcons = new IIcon[this.woodNames.length];
        this.topIcons = new IIcon[this.woodNames.length];
        for (int i = 0; i < this.woodNames.length; ++i) {
            this.topIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.woodNames[i] + "_top");
            this.sideIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.woodNames[i] + "_side");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < this.woodNames.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon getSideIcon(int i) {
        if (i < 0 || i >= this.woodNames.length) {
            i = 0;
        }
        return this.sideIcons[i];
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon getTopIcon(int i) {
        if (i < 0 || i >= this.woodNames.length) {
            i = 0;
        }
        return this.topIcons[i];
    }

    public int getRenderType() {
        return LOTRMod.proxy.getBeamRenderID();
    }
}

