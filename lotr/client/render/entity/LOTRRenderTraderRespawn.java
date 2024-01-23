/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityTraderRespawn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderTraderRespawn
extends Render {
    private ItemStack theItemStack;

    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        if (this.theItemStack == null) {
            this.theItemStack = new ItemStack(LOTRMod.silverCoin);
        }
        LOTREntityTraderRespawn traderRespawn = (LOTREntityTraderRespawn)entity;
        this.bindEntityTexture((Entity)traderRespawn);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        float rotation = this.interpolateRotation(traderRespawn.prevSpawnerSpin, traderRespawn.spawnerSpin, f1);
        float scale = traderRespawn.getScaleFloat(f1);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)(-0.5f * scale), (float)traderRespawn.getBobbingOffset(f1), (float)(0.03125f * scale));
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        IIcon icon = this.theItemStack.getIconIndex();
        if (icon == null) {
            icon = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationItemsTexture)).getAtlasSprite("missingno");
        }
        Tessellator tessellator = Tessellator.instance;
        float f2 = icon.getMinU();
        float f3 = icon.getMaxU();
        float f4 = icon.getMinV();
        float f5 = icon.getMaxV();
        ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)f3, (float)f4, (float)f2, (float)f5, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
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

