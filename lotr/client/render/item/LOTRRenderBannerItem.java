/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.item;

import lotr.client.model.LOTRModelBanner;
import lotr.client.render.entity.LOTRRenderBanner;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBannerItem
implements IItemRenderer {
    private static LOTRModelBanner model = new LOTRModelBanner();

    public boolean handleRenderType(ItemStack itemstack, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack itemstack, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        GL11.glDisable((int)2884);
        Entity holder = (Entity)data[1];
        boolean isFirstPerson = holder == Minecraft.getMinecraft().thePlayer && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;
        boolean renderStand = false;
        TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        if (isFirstPerson) {
            GL11.glTranslatef((float)1.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
        } else {
            GL11.glTranslatef((float)-1.5f, (float)0.85f, (float)-0.1f);
            GL11.glRotatef((float)75.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        LOTRItemBanner.BannerType bannerType = LOTRItemBanner.getBannerType(itemstack);
        textureManager.bindTexture(LOTRRenderBanner.getStandTexture(bannerType));
        if (renderStand) {
            model.renderStand(0.0625f);
        }
        model.renderPost(0.0625f);
        model.renderLowerPost(0.0625f);
        textureManager.bindTexture(LOTRRenderBanner.getBannerTexture(bannerType));
        model.renderBanner(0.0625f);
    }
}

