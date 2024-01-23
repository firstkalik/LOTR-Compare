/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
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

import cpw.mods.fml.common.FMLLog;
import lotr.common.entity.projectile.LOTREntityThrowingAxe;
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

public class LOTRRenderThrowingAxe
extends Render {
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityThrowingAxe axe = (LOTREntityThrowingAxe)entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        if (!axe.inGround) {
            GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.0f);
        }
        GL11.glRotatef((float)(axe.prevRotationYaw + (axe.rotationYaw - axe.prevRotationYaw) * f1 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        if (!axe.inGround) {
            GL11.glRotatef((float)(axe.rotationPitch + (axe.inGround ? 0.0f : 45.0f * f1)), (float)0.0f, (float)0.0f, (float)-1.0f);
        } else {
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)0.75f, (float)0.0f);
        }
        GL11.glEnable((int)32826);
        float f2 = (float)axe.shake - f1;
        if (f2 > 0.0f) {
            float f3 = -MathHelper.sin((float)(f2 * 3.0f)) * f2;
            GL11.glRotatef((float)f3, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ItemStack axeItem = axe.getProjectileItem();
        IIcon icon = axeItem.getIconIndex();
        if (icon == null) {
            FMLLog.severe((String)("Error rendering throwing axe: no icon for " + axeItem.toString()), (Object[])new Object[0]);
            GL11.glPopMatrix();
            return;
        }
        this.bindEntityTexture(entity);
        Tessellator tessellator = Tessellator.instance;
        ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)icon.getMaxU(), (float)icon.getMinV(), (float)icon.getMinU(), (float)icon.getMaxV(), (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

