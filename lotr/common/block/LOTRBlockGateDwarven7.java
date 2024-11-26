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
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.client.render.LOTRConnectedTextures;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockGate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockGateDwarven7
extends LOTRBlockGate {
    public LOTRBlockGateDwarven7() {
        super(Material.rock, false);
        this.setHardness(4.0f);
        this.setResistance(10.0f);
        this.setStepSound(Block.soundTypeStone);
        this.setFullBlock();
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        LOTRConnectedTextures.registerNonConnectedGateIcons(iconregister, this, 0, LOTRMod.rock.getIcon(0, 6).getIconName());
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
        boolean open = LOTRBlockGate.isGateOpen(world, i, j, k);
        if (open) {
            return LOTRConnectedTextures.getConnectedIconBlock(this, world, i, j, k, side, false);
        }
        return LOTRMod.rock.getIcon(side, 6);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return LOTRMod.rock.getIcon(i, 6);
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        boolean flag = super.onBlockActivated(world, i, j, k, entityplayer, side, f, f1, f2);
        if (flag && !world.isRemote) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.useDwarvenDoor);
        }
        return flag;
    }
}

