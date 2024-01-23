/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityBarrel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderEntityBarrel
extends Render {
    private ItemStack barrelItem = new ItemStack(LOTRMod.barrel);

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationBlocksTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityBarrel barrel = (LOTREntityBarrel)entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1 + 0.5f), (float)((float)d2));
        GL11.glRotatef((float)(180.0f - f), (float)0.0f, (float)1.0f, (float)0.0f);
        float f2 = (float)barrel.getTimeSinceHit() - f1;
        float f3 = barrel.getDamageTaken() - f1;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f2 > 0.0f) {
            GL11.glRotatef((float)(MathHelper.sin((float)f2) * f2 * f3 / 10.0f * (float)barrel.getForwardDirection()), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        this.bindEntityTexture((Entity)barrel);
        GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
        this.renderManager.itemRenderer.renderItem(this.renderManager.livingPlayer, this.barrelItem, 0);
        GL11.glPopMatrix();
    }
}

