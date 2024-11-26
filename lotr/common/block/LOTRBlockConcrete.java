/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockColored
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
import lotr.common.LOTRCreativeTabs;
import lotr.common.block.LOTRBlockClayTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRBlockConcrete
extends LOTRBlockClayTile {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] concreteIcons;

    public LOTRBlockConcrete() {
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.setHardness(1.0f);
        this.setResistance(8.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    public int damageDropped(int metadata) {
        return metadata;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (metadata >= 16) {
            metadata = 0;
        }
        return this.concreteIcons[metadata];
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 16; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.concreteIcons = new IIcon[16];
        for (int i = 0; i < this.concreteIcons.length; ++i) {
            int dyeMeta = BlockColored.func_150031_c((int)i);
            this.concreteIcons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + ItemDye.field_150923_a[dyeMeta]);
        }
    }
}

