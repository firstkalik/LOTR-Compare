/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.LOTRMod;
import lotr.common.entity.projectile.LOTREntityThrownRock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderThrownRock
extends Render {
    private RenderBlocks blockRenderer = new RenderBlocks();

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationBlocksTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        GL11.glEnable((int)32826);
        GL11.glDisable((int)2884);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glRotatef((float)f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f1), (float)1.0f, (float)0.0f, (float)0.0f);
        int i = entity.getBrightnessForRender(f1);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.bindEntityTexture(entity);
        if (((LOTREntityThrownRock)entity).getSpawnsTroll()) {
            this.blockRenderer.renderBlockAsItem(LOTRMod.trollTotem, 0, 1.0f);
        } else {
            this.blockRenderer.renderBlockAsItem(Blocks.stone, 0, 1.0f);
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)2884);
        GL11.glDisable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

