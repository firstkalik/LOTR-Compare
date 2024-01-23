/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.LOTRSpeechClient;
import lotr.client.model.LOTRModelGollum;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.common.entity.npc.LOTREntityGollum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderGollum
extends RenderLiving {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/char/gollum.png");

    public LOTRRenderGollum() {
        super((ModelBase)new LOTRModelGollum(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = 0.85f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityGollum gollum = (LOTREntityGollum)entity;
        super.doRender((EntityLiving)gollum, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled()) {
            if (!LOTRSpeechClient.hasSpeech(gollum)) {
                this.func_147906_a((Entity)gollum, gollum.getCommandSenderName(), d, d1 + 0.5, d2, 64);
            }
            if (gollum.getGollumOwner() == Minecraft.getMinecraft().thePlayer) {
                LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 0.5, d2);
            }
        }
    }

    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack heldItem = entity.getHeldItem();
        if (heldItem != null && heldItem.getItem() == Items.fish) {
            GL11.glPushMatrix();
            ((LOTRModelGollum)this.mainModel).head.postRender(0.0625f);
            GL11.glTranslatef((float)0.21875f, (float)0.03125f, (float)-0.375f);
            float f1 = 0.375f;
            GL11.glScalef((float)f1, (float)f1, (float)f1);
            GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-50.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            this.renderManager.itemRenderer.renderItem(entity, heldItem, 0);
            GL11.glPopMatrix();
        }
    }
}

