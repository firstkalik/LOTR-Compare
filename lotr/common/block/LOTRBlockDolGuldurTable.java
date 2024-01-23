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
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockCraftingTable;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockDolGuldurTable
extends LOTRBlockCraftingTable {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tableIcons;

    public LOTRBlockDolGuldurTable() {
        super(Material.rock, LOTRFaction.DOL_GULDUR, 30);
        this.setStepSound(Block.soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return this.tableIcons[1];
        }
        if (i == 0) {
            return LOTRMod.brick2.getIcon(0, 8);
        }
        return this.tableIcons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.tableIcons = new IIcon[2];
        this.tableIcons[0] = iconregister.registerIcon(this.getTextureName() + "_side");
        this.tableIcons[1] = iconregister.registerIcon(this.getTextureName() + "_top");
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt(20) == 0) {
            for (int l = 0; l < 16; ++l) {
                double d = (double)i + 0.25 + (double)(random.nextFloat() * 0.5f);
                double d1 = (double)j + 1.0;
                double d2 = (double)k + 0.25 + (double)(random.nextFloat() * 0.5f);
                double d3 = -0.05 + (double)random.nextFloat() * 0.1;
                double d4 = 0.1 + (double)random.nextFloat() * 0.1;
                double d5 = -0.05 + (double)random.nextFloat() * 0.1;
                LOTRMod.proxy.spawnParticle("morgulPortal", d, d1, d2, d3, d4, d5);
            }
        }
    }
}

