/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.model.LOTRModelDecoPot;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDecoratedPot
extends TileEntitySpecialRenderer {
    public ModelBase decoPotModel = new LOTRModelDecoPot();
    public ResourceLocation decoPotTexture = new ResourceLocation("lotr:item/blockDecoPot.png");

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.bindTexture(this.decoPotTexture);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        this.decoPotModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}

