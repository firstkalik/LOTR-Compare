/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockQuenditeGrass
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon grassSideIcon;

    public LOTRBlockQuenditeGrass() {
        super(Material.grass);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 0) {
            return Blocks.dirt.getIcon(i, j);
        }
        if (i == 1) {
            return this.blockIcon;
        }
        return this.grassSideIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcon = iconregister.registerIcon("lotr:quenditeGrass_top");
        this.grassSideIcon = iconregister.registerIcon("lotr:quenditeGrass_side");
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)Blocks.dirt);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(8) == 0) {
            double d = (float)i + random.nextFloat();
            double d1 = (double)j + 1.0;
            double d2 = (float)k + random.nextFloat();
            LOTRMod.proxy.spawnParticle("quenditeSmoke", d, d1, d2, 0.0, 0.0, 0.0);
        }
    }
}

