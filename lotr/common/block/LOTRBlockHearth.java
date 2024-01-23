/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockHearth
extends Block {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] blockIcons;

    public LOTRBlockHearth() {
        super(Material.rock);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 0) {
            return this.blockIcons[0];
        }
        if (i == 1) {
            return this.blockIcons[1];
        }
        return this.blockIcons[2];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.blockIcons = new IIcon[3];
        this.blockIcons[0] = iconregister.registerIcon(this.getTextureName() + "_bottom");
        this.blockIcons[1] = iconregister.registerIcon(this.getTextureName() + "_top");
        this.blockIcons[2] = iconregister.registerIcon(this.getTextureName() + "_side");
    }

    public boolean isFireSource(World world, int i, int j, int k, ForgeDirection side) {
        return side == ForgeDirection.UP;
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (world.getBlock(i, j + 1, k) == Blocks.fire) {
            int smokeHeight = 5;
            for (int j1 = j + 1; j1 <= j + smokeHeight && !world.getBlock(i, j1, k).getMaterial().isSolid(); ++j1) {
                for (int l = 0; l < 3; ++l) {
                    float f = (float)i + random.nextFloat();
                    float f1 = (float)j1 + random.nextFloat();
                    float f2 = (float)k + random.nextFloat();
                    world.spawnParticle("largesmoke", (double)f, (double)f1, (double)f2, 0.0, 0.0, 0.0);
                }
            }
        }
    }
}

