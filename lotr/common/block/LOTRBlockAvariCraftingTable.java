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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockCraftingTable;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockAvariCraftingTable
extends LOTRBlockCraftingTable {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tableIcons;

    public LOTRBlockAvariCraftingTable() {
        super(Material.rock, LOTRFaction.AVARI, 70);
        this.setStepSound(Block.soundTypeWood);
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        boolean hasRequiredAlignment2;
        boolean hasRequiredAlignment = LOTRLevelData.getData(entityplayer).getAlignment(this.tableFaction) >= 1.0f;
        boolean bl = hasRequiredAlignment2 = LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.RED_MOUNTAINS) >= 1.0f;
        if (hasRequiredAlignment || hasRequiredAlignment2) {
            if (!world.isRemote) {
                entityplayer.openGui((Object)LOTRMod.instance, this.tableGUIID, world, i, j, k);
            }
        } else {
            for (int l = 0; l < 8; ++l) {
                double d = (float)i + world.rand.nextFloat();
                double d1 = (double)j + 1.0;
                double d2 = (float)k + world.rand.nextFloat();
                world.spawnParticle("smoke", d, d1, d2, 0.0, 0.0, 0.0);
            }
            if (!world.isRemote) {
                LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, 10.0f, this.tableFaction, LOTRFaction.RED_MOUNTAINS);
            }
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int i, int j) {
        if (i == 1) {
            return this.tableIcons[1];
        }
        if (i == 0) {
            return LOTRMod.planks.getIcon(0, 9);
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

