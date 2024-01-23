/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockLog
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
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public abstract class LOTRBlockWoodBase
extends BlockLog {
    @SideOnly(value=Side.CLIENT)
    private IIcon[][] woodIcons;
    private String[] woodNames;

    public LOTRBlockWoodBase() {
        this.setHardness(2.0f);
        this.setStepSound(Block.soundTypeWood);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    public void setWoodNames(String ... s) {
        this.woodNames = s;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)this);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        int j1 = j & 0xC;
        if ((j &= 3) >= this.woodNames.length) {
            j = 0;
        }
        if (j1 == 0 && (i == 0 || i == 1) || j1 == 4 && (i == 4 || i == 5) || j1 == 8 && (i == 2 || i == 3)) {
            return this.woodIcons[j][0];
        }
        return this.woodIcons[j][1];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.woodIcons = new IIcon[this.woodNames.length][2];
        for (int i = 0; i < this.woodNames.length; ++i) {
            this.woodIcons[i][0] = iconregister.registerIcon(this.getTextureName() + "_" + this.woodNames[i] + "_top");
            this.woodIcons[i][1] = iconregister.registerIcon(this.getTextureName() + "_" + this.woodNames[i] + "_side");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.woodNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

