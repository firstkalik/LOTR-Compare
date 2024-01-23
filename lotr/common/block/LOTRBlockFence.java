/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockFence
extends BlockFence {
    private Block plankBlock;

    public LOTRBlockFence(Block planks) {
        super("", Material.wood);
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeWood);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabDeco);
        this.plankBlock = planks;
    }

    public boolean canPlaceTorchOnTop(World world, int i, int j, int k) {
        return true;
    }

    public int damageDropped(int i) {
        return i;
    }

    public int getRenderType() {
        if (LOTRMod.proxy.isClient()) {
            return LOTRMod.proxy.getFenceRenderID();
        }
        return super.getRenderType();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        return this.plankBlock.getIcon(i, j);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        ArrayList plankTypes = new ArrayList();
        this.plankBlock.getSubBlocks(Item.getItemFromBlock((Block)this.plankBlock), this.plankBlock.getCreativeTabToDisplayOn(), plankTypes);
        for (int j = 0; j < plankTypes.size(); ++j) {
            Object obj = plankTypes.get(j);
            if (!(obj instanceof ItemStack)) continue;
            int meta = ((ItemStack)obj).getItemDamage();
            list.add(new ItemStack((Block)this, 1, meta));
        }
    }
}

