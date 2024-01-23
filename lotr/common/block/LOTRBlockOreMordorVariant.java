/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.block.LOTRBlockOre;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTRBlockOreMordorVariant
extends LOTRBlockOre {
    @SideOnly(value=Side.CLIENT)
    private IIcon mordorIcon;
    private boolean dropsBlock;

    public LOTRBlockOreMordorVariant(boolean flag) {
        this.dropsBlock = flag;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (j == 1) {
            return this.mordorIcon;
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        super.registerBlockIcons(iconregister);
        this.mordorIcon = iconregister.registerIcon(this.getTextureName() + "_mordor");
    }

    public int damageDropped(int i) {
        if (this.dropsBlock) {
            return i;
        }
        return super.damageDropped(i);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j <= 1; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int i, int j, int k, EntityPlayer entityplayer) {
        return new ItemStack((Block)this, 1, world.getBlockMetadata(i, j, k));
    }
}

