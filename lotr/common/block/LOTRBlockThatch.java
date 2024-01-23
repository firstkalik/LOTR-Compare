/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
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
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRBlockThatch
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] thatchIcons;
    private static String[] thatchNames = new String[]{"thatch", "reed"};

    public LOTRBlockThatch() {
        super(Material.grass);
        this.setHardness(0.5f);
        this.setStepSound(Block.soundTypeGrass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j >= thatchNames.length) {
            j = 0;
        }
        return this.thatchIcons[j];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.thatchIcons = new IIcon[thatchNames.length];
        for (int i = 0; i < thatchNames.length; ++i) {
            this.thatchIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + thatchNames[i]);
        }
    }

    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < thatchNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}

