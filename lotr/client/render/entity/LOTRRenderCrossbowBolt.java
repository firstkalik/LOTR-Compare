/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.entity.projectile.LOTREntityCrossbowBolt;
import lotr.common.item.LOTRItemCrossbowBolt;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderCrossbowBolt
extends Render {
    private static ResourceLocation boltTexture = new ResourceLocation("lotr:item/crossbowBolt.png");

    protected ResourceLocation getEntityTexture(Entity entity) {
        return boltTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityCrossbowBolt bolt = (LOTREntityCrossbowBolt)entity;
        ItemStack boltItem = bolt.getProjectileItem();
        this.bindEntityTexture((Entity)bolt);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glRotatef((float)(bolt.prevRotationYaw + (bolt.rotationYaw - bolt.prevRotationYaw) * f1 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(bolt.prevRotationPitch + (bolt.rotationPitch - bolt.prevRotationPitch) * f1), (float)0.0f, (float)0.0f, (float)1.0f);
        Tessellator tessellator = Tessellator.instance;
        int yOffset = 0;
        if (boltItem != null && boltItem.getItem() instanceof LOTRItemCrossbowBolt && ((LOTRItemCrossbowBolt)boltItem.getItem()).isPoisoned) {
            yOffset = 1;
        }
        float f2 = 0.0f;
        float f3 = 0.5f;
        float f4 = (float)(0 + yOffset * 10) / 32.0f;
        float f5 = (float)(5 + yOffset * 10) / 32.0f;
        float f6 = 0.0f;
        float f7 = 0.15625f;
        float f8 = (float)(5 + yOffset * 10) / 32.0f;
        float f9 = (float)(10 + yOffset * 10) / 32.0f;
        float f10 = 0.05625f;
        GL11.glEnable((int)32826);
        float f11 = (float)bolt.shake - f1;
        if (f11 > 0.0f) {
            float f12 = -MathHelper.sin((float)(f11 * 3.0f)) * f11;
            GL11.glRotatef((float)f12, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)f10, (float)f10, (float)f10);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)f10, (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f((float)(-f10), (float)0.0f, (float)0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f9);
        tessellator.draw();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)f10);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-8.0, -2.0, 0.0, (double)f2, (double)f4);
            tessellator.addVertexWithUV(8.0, -2.0, 0.0, (double)f3, (double)f4);
            tessellator.addVertexWithUV(8.0, 2.0, 0.0, (double)f3, (double)f5);
            tessellator.addVertexWithUV(-8.0, 2.0, 0.0, (double)f2, (double)f5);
            tessellator.draw();
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

