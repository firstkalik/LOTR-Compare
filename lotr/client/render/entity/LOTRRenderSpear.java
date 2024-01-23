/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import cpw.mods.fml.common.FMLLog;
import lotr.client.model.LOTRModelBoar;
import lotr.client.render.entity.LOTRRenderWildBoar;
import lotr.common.LOTRMod;
import lotr.common.entity.projectile.LOTREntitySpear;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class LOTRRenderSpear
extends Render {
    private static ModelBase boarModel = new LOTRModelBoar();

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntitySpear spear = (LOTREntitySpear)entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glRotatef((float)(spear.prevRotationYaw + (spear.rotationYaw - spear.prevRotationYaw) * f1 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(spear.prevRotationPitch + (spear.rotationPitch - spear.prevRotationPitch) * f1), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        float f2 = (float)spear.shake - f1;
        if (f2 > 0.0f) {
            float f3 = -MathHelper.sin((float)(f2 * 3.0f)) * f2;
            GL11.glRotatef((float)f3, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        if (LOTRMod.isAprilFools()) {
            this.bindTexture(LOTRRenderWildBoar.boarSkin);
            GL11.glTranslatef((float)0.0f, (float)-2.5f, (float)0.0f);
            GL11.glScalef((float)0.25f, (float)0.25f, (float)0.25f);
            boarModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.625f);
        } else {
            ItemStack itemstack = spear.getProjectileItem();
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            if (customRenderer != null) {
                customRenderer.renderItem(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, new Object[2]);
            } else {
                FMLLog.severe((String)("Error rendering spear: no custom renderer for " + itemstack.toString()), (Object[])new Object[0]);
            }
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    static {
        LOTRRenderSpear.boarModel.isChild = false;
    }
}

