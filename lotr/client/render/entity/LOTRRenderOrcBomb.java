/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityOrcBomb;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderOrcBomb
extends Render {
    private RenderBlocks blockRenderer = new RenderBlocks();

    public LOTRRenderOrcBomb() {
        this.shadowSize = 0.5f;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationBlocksTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityOrcBomb bomb = (LOTREntityOrcBomb)entity;
        this.renderBomb((Entity)bomb, d, d1, d2, f1, bomb.orcBombFuse, bomb.getBombStrengthLevel(), 1.0f, entity.getBrightness(f1));
    }

    public void renderBomb(Entity entity, double d, double d1, double d2, float ticks, int fuse, int strengthLevel, float bombScale, float brightness) {
        float f2;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glScalef((float)bombScale, (float)bombScale, (float)bombScale);
        if ((float)fuse - ticks + 1.0f < 10.0f) {
            f2 = 1.0f - ((float)fuse - ticks + 1.0f) / 10.0f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            f2 *= f2;
            f2 *= f2;
            float scale = 1.0f + f2 * 0.3f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
        }
        f2 = (1.0f - ((float)fuse - ticks + 1.0f) / 100.0f) * 0.8f;
        this.bindEntityTexture(entity);
        this.blockRenderer.renderBlockAsItem(LOTRMod.orcBomb, strengthLevel, brightness);
        if (fuse / 5 % 2 == 0) {
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)772);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f2);
            this.blockRenderer.renderBlockAsItem(LOTRMod.orcBomb, strengthLevel, 1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
        }
        GL11.glPopMatrix();
    }
}

