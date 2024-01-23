/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.entity.projectile.LOTREntityDart;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderDart
extends Render {
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityDart dart = (LOTREntityDart)entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glRotatef((float)(dart.prevRotationYaw + (dart.rotationYaw - dart.prevRotationYaw) * f1 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(dart.prevRotationPitch + (dart.rotationPitch - dart.prevRotationPitch) * f1), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        float f2 = (float)dart.shake - f1;
        if (f2 > 0.0f) {
            float f3 = -MathHelper.sin((float)(f2 * 3.0f)) * f2;
            GL11.glRotatef((float)f3, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        float scale = 0.6f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslatef((float)0.0f, (float)0.8f, (float)0.0f);
        ItemStack itemstack = dart.getProjectileItem();
        IIcon icon = itemstack.getIconIndex();
        Tessellator tessellator = Tessellator.instance;
        float minU = icon.getMinU();
        float maxU = icon.getMaxU();
        float minV = icon.getMinV();
        float maxV = icon.getMaxV();
        int width = icon.getIconWidth();
        int height = icon.getIconWidth();
        this.bindTexture(this.getEntityTexture(dart));
        ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)maxU, (float)minV, (float)minU, (float)maxV, (int)width, (int)height, (float)0.0625f);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

