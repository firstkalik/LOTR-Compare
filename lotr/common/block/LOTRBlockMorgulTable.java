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
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockCraftingTable;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockMorgulTable
extends LOTRBlockCraftingTable {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tableIcons;

    public LOTRBlockMorgulTable() {
        super(Material.rock, LOTRFaction.MORDOR, 1);
        this.setStepSound(Block.soundTypeStone);
        this.setLightLevel(0.5f);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return this.tableIcons[1];
        }
        if (i == 0) {
            return LOTRMod.rock.getIcon(2, 0);
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
        for (int l = 0; l < 2; ++l) {
            double d = (double)i + 0.25 + (double)(random.nextFloat() * 0.5f);
            double d1 = (double)j + 1.0;
            double d2 = (double)k + 0.25 + (double)(random.nextFloat() * 0.5f);
            world.spawnParticle("flame", d, d1, d2, 0.0, 0.0, 0.0);
        }
    }
}

