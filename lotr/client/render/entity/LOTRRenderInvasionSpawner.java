/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.entity.LOTREntityInvasionSpawner;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderInvasionSpawner
extends Render {
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityInvasionSpawner spawner = (LOTREntityInvasionSpawner)entity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        float rotation = this.interpolateRotation(spawner.prevSpawnerSpin, spawner.spawnerSpin, f1);
        float scale = 1.5f;
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        ItemStack item = spawner.getInvasionItem();
        this.renderManager.itemRenderer.renderItem(this.renderManager.livingPlayer, item, 0, IItemRenderer.ItemRenderType.EQUIPPED);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    private float interpolateRotation(float prevRotation, float newRotation, float tick) {
        float interval;
        for (interval = newRotation - prevRotation; interval < -180.0f; interval += 360.0f) {
        }
        while (interval >= 180.0f) {
            interval -= 360.0f;
        }
        return prevRotation + tick * interval;
    }
}

