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
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockCraftingTable;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class LOTRBlockUrukTable
extends LOTRBlockCraftingTable {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tableIcons;

    public LOTRBlockUrukTable() {
        super(Material.rock, LOTRFaction.ISENGARD, 8);
        this.setStepSound(Block.soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return this.tableIcons[1];
        }
        if (i == 0) {
            return LOTRMod.brick2.getIcon(0, 7);
        }
        return this.tableIcons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister) {
        this.tableIcons = new IIcon[2];
        this.tableIcons[0] = iconregister.registerIcon(this.getTextureName() + "_side");
        this.tableIcons[1] = iconregister.registerIcon(this.getTextureName() + "_top");
    }
}

