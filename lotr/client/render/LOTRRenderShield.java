/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render;

import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.LOTRItemArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderShield {
    private static int SHIELD_WIDTH = 32;
    private static int SHIELD_HEIGHT = 32;
    private static float MODELSCALE = 0.0625f;

    public static void renderShield(LOTRShields shield, EntityLivingBase entity, ModelBiped model) {
        boolean blocking;
        ItemStack inUse;
        Minecraft mc = Minecraft.getMinecraft();
        ResourceLocation shieldTexture = shield.shieldTexture;
        ItemStack held = entity == null ? null : entity.getHeldItem();
        ItemStack heldLeft = entity instanceof LOTREntityNPC ? ((LOTREntityNPC)entity).getHeldItemLeft() : null;
        ItemStack itemStack = inUse = entity instanceof EntityPlayer ? ((EntityPlayer)entity).getItemInUse() : null;
        boolean holdingSword = entity == null ? true : !(held == null || !(held.getItem() instanceof ItemSword) && !(held.getItem() instanceof ItemTool) || inUse != null && inUse.getItemUseAction() == EnumAction.bow);
        boolean bl = blocking = holdingSword && inUse != null && inUse.getItemUseAction() == EnumAction.block;
        if (heldLeft != null && entity instanceof LOTREntityNPC) {
            LOTREntityNPC npc = (LOTREntityNPC)entity;
            if (npc.npcCape != null) {
                return;
            }
        }
        ItemStack chestplate = entity == null ? null : entity.getEquipmentInSlot(3);
        boolean wearingChestplate = chestplate != null && chestplate.getItem().isValidArmor(chestplate, ((LOTRItemArmor)LOTRMod.bodyMithril).armorType, (Entity)entity);
        boolean renderOnBack = !holdingSword || holdingSword && heldLeft != null;
        GL11.glPushMatrix();
        if (renderOnBack) {
            model.bipedBody.postRender(MODELSCALE);
        } else {
            model.bipedLeftArm.postRender(MODELSCALE);
        }
        GL11.glScalef((float)-1.5f, (float)-1.5f, (float)1.5f);
        if (renderOnBack) {
            GL11.glTranslatef((float)0.5f, (float)-0.8f, (float)0.0f);
            if (wearingChestplate) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.24f);
            } else {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.16f);
            }
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (blocking) {
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.4f, (float)-0.9f, (float)-0.15f);
        } else {
            GL11.glRotatef((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.75f, (float)0.0f);
            if (wearingChestplate) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.24f);
            } else {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.16f);
            }
            GL11.glRotatef((float)-15.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        mc.getTextureManager().bindTexture(shieldTexture);
        GL11.glEnable((int)3008);
        LOTRRenderShield.doRenderShield(0.0f);
        GL11.glTranslatef((float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
        LOTRRenderShield.doRenderShield(0.5f);
        GL11.glPopMatrix();
    }

    private static void doRenderShield(float f) {
        int k;
        float f9;
        float f8;
        float f7;
        float minU = 0.0f + f;
        float maxU = 0.5f + f;
        float minV = 0.0f;
        float maxV = 1.0f;
        int width = SHIELD_WIDTH;
        int height = SHIELD_HEIGHT;
        double depth1 = MODELSCALE * 0.5f * f;
        double depth2 = MODELSCALE * 0.5f * (0.5f + f);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, 1.0f);
        tessellator.addVertexWithUV(0.0, 0.0, depth1, (double)maxU, (double)maxV);
        tessellator.addVertexWithUV(1.0, 0.0, depth1, (double)minU, (double)maxV);
        tessellator.addVertexWithUV(1.0, 1.0, depth1, (double)minU, (double)minV);
        tessellator.addVertexWithUV(0.0, 1.0, depth1, (double)maxU, (double)minV);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        tessellator.addVertexWithUV(0.0, 1.0, depth2, (double)maxU, (double)minV);
        tessellator.addVertexWithUV(1.0, 1.0, depth2, (double)minU, (double)minV);
        tessellator.addVertexWithUV(1.0, 0.0, depth2, (double)minU, (double)maxV);
        tessellator.addVertexWithUV(0.0, 0.0, depth2, (double)maxU, (double)maxV);
        tessellator.draw();
        float f5 = 0.5f * (maxU - minU) / (float)width;
        float f6 = 0.5f * (maxV - minV) / (float)height;
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0f, 0.0f, 0.0f);
        for (k = 0; k < width; ++k) {
            f7 = (float)k / (float)width;
            f8 = maxU + (minU - maxU) * f7 - f5;
            tessellator.addVertexWithUV((double)f7, 0.0, depth2, (double)f8, (double)maxV);
            tessellator.addVertexWithUV((double)f7, 0.0, depth1, (double)f8, (double)maxV);
            tessellator.addVertexWithUV((double)f7, 1.0, depth1, (double)f8, (double)minV);
            tessellator.addVertexWithUV((double)f7, 1.0, depth2, (double)f8, (double)minV);
        }
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0f, 0.0f, 0.0f);
        for (k = 0; k < width; ++k) {
            f7 = (float)k / (float)width;
            f8 = maxU + (minU - maxU) * f7 - f5;
            f9 = f7 + 1.0f / (float)width;
            tessellator.addVertexWithUV((double)f9, 1.0, depth2, (double)f8, (double)minV);
            tessellator.addVertexWithUV((double)f9, 1.0, depth1, (double)f8, (double)minV);
            tessellator.addVertexWithUV((double)f9, 0.0, depth1, (double)f8, (double)maxV);
            tessellator.addVertexWithUV((double)f9, 0.0, depth2, (double)f8, (double)maxV);
        }
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        for (k = 0; k < height; ++k) {
            f7 = (float)k / (float)height;
            f8 = maxV + (minV - maxV) * f7 - f6;
            f9 = f7 + 1.0f / (float)height;
            tessellator.addVertexWithUV(0.0, (double)f9, depth1, (double)maxU, (double)f8);
            tessellator.addVertexWithUV(1.0, (double)f9, depth1, (double)minU, (double)f8);
            tessellator.addVertexWithUV(1.0, (double)f9, depth2, (double)minU, (double)f8);
            tessellator.addVertexWithUV(0.0, (double)f9, depth2, (double)maxU, (double)f8);
        }
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, -1.0f, 0.0f);
        for (k = 0; k < height; ++k) {
            f7 = (float)k / (float)height;
            f8 = maxV + (minV - maxV) * f7 - f6;
            tessellator.addVertexWithUV(1.0, (double)f7, depth1, (double)minU, (double)f8);
            tessellator.addVertexWithUV(0.0, (double)f7, depth1, (double)maxU, (double)f8);
            tessellator.addVertexWithUV(0.0, (double)f7, depth2, (double)maxU, (double)f8);
            tessellator.addVertexWithUV(1.0, (double)f7, depth2, (double)minU, (double)f8);
        }
        tessellator.draw();
    }
}

