/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package lotr.client.render.tileentity;

import lotr.client.LOTRClientProxy;
import lotr.client.render.tileentity.LOTRRenderDwarvenGlow;
import lotr.client.render.tileentity.LOTRRenderSignCarved;
import lotr.common.tileentity.LOTRTileEntitySignCarved;
import lotr.common.tileentity.LOTRTileEntitySignCarvedIthildin;
import net.minecraft.tileentity.TileEntity;

public class LOTRRenderSignCarvedIthildin
extends LOTRRenderSignCarved {
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntitySignCarvedIthildin sign = (LOTRTileEntitySignCarvedIthildin)tileentity;
        float alphaFunc = LOTRRenderDwarvenGlow.setupGlow(sign.getGlowBrightness(f));
        super.renderTileEntityAt(tileentity, d, d1, d2, f);
        LOTRRenderDwarvenGlow.endGlow(alphaFunc);
    }

    @Override
    protected int getTextColor(LOTRTileEntitySignCarved sign, float f) {
        float glow = ((LOTRTileEntitySignCarvedIthildin)sign).getGlowBrightness(f);
        int alpha = LOTRClientProxy.getAlphaInt(glow);
        return 0xFFFFFF | alpha << 24;
    }
}

