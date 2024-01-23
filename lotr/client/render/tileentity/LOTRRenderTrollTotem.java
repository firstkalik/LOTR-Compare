/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.model.LOTRModelTrollTotem;
import lotr.common.tileentity.LOTRTileEntityTrollTotem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderTrollTotem
extends TileEntitySpecialRenderer {
    private LOTRModelTrollTotem totemModel = new LOTRModelTrollTotem();
    private ResourceLocation totemTexture = new ResourceLocation("lotr:item/trollTotem.png");

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityTrollTotem trollTotem = (LOTRTileEntityTrollTotem)tileentity;
        int i = trollTotem.getBlockMetadata();
        int meta = i & 3;
        float rotation = 0.0f;
        switch ((i & 0xC) >> 2) {
            case 0: {
                rotation = 180.0f;
                break;
            }
            case 1: {
                rotation = 270.0f;
                break;
            }
            case 2: {
                rotation = 0.0f;
                break;
            }
            case 3: {
                rotation = 90.0f;
            }
        }
        this.renderTrollTotem(d, d1, d2, meta, rotation, trollTotem.getJawRotation(f));
    }

    private void renderTrollTotem(double d, double d1, double d2, int meta, float rotation, float jawRotation) {
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        this.bindTexture(this.totemTexture);
        switch (meta) {
            case 0: {
                this.totemModel.renderHead(0.0625f, jawRotation);
                break;
            }
            case 1: {
                this.totemModel.renderBody(0.0625f);
                break;
            }
            case 2: {
                this.totemModel.renderBase(0.0625f);
            }
        }
        GL11.glPopMatrix();
    }

    public void renderInvTrollTotem(int meta) {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        this.renderTrollTotem(0.0, 0.0, 0.0, meta, 0.0f, 0.0f);
        GL11.glEnable((int)32826);
    }
}

