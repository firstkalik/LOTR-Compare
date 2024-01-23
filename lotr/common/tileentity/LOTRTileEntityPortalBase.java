/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.block.LOTRBlockPortal;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class LOTRTileEntityPortalBase
extends TileEntity {
    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            LOTRBlockPortal portalBlock = (LOTRBlockPortal)this.getBlockType();
            for (int i1 = this.xCoord - 1; i1 <= this.xCoord + 1; ++i1) {
                for (int k1 = this.zCoord - 1; k1 <= this.zCoord + 1; ++k1) {
                    if (!portalBlock.isValidPortalLocation(this.worldObj, i1, this.yCoord, k1, true)) continue;
                    return;
                }
            }
            this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox((double)(this.xCoord - 1), (double)this.yCoord, (double)(this.zCoord - 1), (double)(this.xCoord + 2), (double)(this.yCoord + 2), (double)(this.zCoord + 2));
    }
}

