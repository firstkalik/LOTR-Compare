/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.tileentity.LOTRDwarvenGlowLogic;
import lotr.common.tileentity.LOTRTileEntitySignCarved;
import net.minecraft.world.World;

public class LOTRTileEntitySignCarvedIthildin
extends LOTRTileEntitySignCarved {
    private LOTRDwarvenGlowLogic glowLogic = new LOTRDwarvenGlowLogic().setPlayerRange(8);

    public void updateEntity() {
        super.updateEntity();
        this.glowLogic.update(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public float getGlowBrightness(float f) {
        if (this.isFakeGuiSign) {
            return 1.0f;
        }
        return this.glowLogic.getGlowBrightness(this.worldObj, this.xCoord, this.yCoord, this.zCoord, f);
    }

    @SideOnly(value=Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 1024.0;
    }
}

