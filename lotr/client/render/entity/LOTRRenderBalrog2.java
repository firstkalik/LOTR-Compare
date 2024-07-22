/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelBalrog2;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityAngbandBalrog;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBalrog2
extends RenderLiving {
    private static LOTRRandomSkins balrogSkins;
    private static LOTRRandomSkins balrogSkinsBright;
    private static final ResourceLocation fireTexture;
    private LOTRModelBalrog2 balrogModel;
    private LOTRModelBalrog2 balrogModelBright;
    private LOTRModelBalrog2 fireModel;

    public LOTRRenderBalrog2() {
        super((ModelBase)new LOTRModelBalrog2(), 0.5f);
        this.balrogModel = (LOTRModelBalrog2)this.mainModel;
        this.balrogModelBright = new LOTRModelBalrog2(0.05f);
        this.fireModel = new LOTRModelBalrog2(0.0f);
        this.fireModel.setFireModel();
        balrogSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/balrog/balrog");
        balrogSkinsBright = LOTRRandomSkins.loadSkinsList("lotr:mob/balrog/balrog_bright");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return balrogSkins.getRandomSkin((LOTREntityAngbandBalrog)entity);
    }

    protected void scaleTroll(LOTREntityAngbandBalrog troll, boolean inverse) {
        float scale = troll.getTrollScale();
        if (inverse) {
            scale = 1.0f / scale;
        }
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityAngbandBalrog balrog = (LOTREntityAngbandBalrog)entity;
        ItemStack heldItem = balrog.getHeldItem();
        this.balrogModel.heldItemRight = this.fireModel.heldItemRight = heldItem == null ? 0 : 2;
        if (Minecraft.isGuiEnabled() && ((LOTREntityNPC)entity).hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
            LOTRNPCRendering.renderHiredIcon((EntityLivingBase)entity, d, d1 + 2.0, d2);
            LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 2.0, d2);
        }
        super.doRender((EntityLiving)balrog, d, d1, d2, f, f1);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        LOTREntityAngbandBalrog balrog = (LOTREntityAngbandBalrog)entity;
        float scale = 1.5f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        if (balrog.isBalrogCharging()) {
            float lean = balrog.getInterpolatedChargeLean(f);
            GL11.glRotatef((float)(lean *= 35.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    private void setupFullBright() {
        int light2 = 15728880;
        int lx = light2 % 65536;
        int ly = light2 / 65536;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)lx / 1.0f), (float)((float)ly / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        LOTREntityAngbandBalrog balrog = (LOTREntityAngbandBalrog)entity;
        if (balrog.isWreathedInFlame()) {
            if (pass == 1) {
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
                GL11.glMatrixMode((int)5890);
                GL11.glLoadIdentity();
                float f1 = (float)balrog.ticksExisted + f;
                float f2 = f1 * 0.01f;
                float f3 = f1 * 0.01f;
                GL11.glTranslatef((float)f2, (float)f3, (float)0.0f);
                GL11.glMatrixMode((int)5888);
                GL11.glAlphaFunc((int)516, (float)0.01f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)1, (int)1);
                float alpha = balrog.isBalrogCharging() ? 0.6f + MathHelper.sin((float)(f1 * 0.1f)) * 0.15f : 0.3f + MathHelper.sin((float)(f1 * 0.05f)) * 0.15f;
                GL11.glColor4f((float)alpha, (float)alpha, (float)alpha, (float)1.0f);
                GL11.glDisable((int)2896);
                GL11.glDepthMask((boolean)false);
                this.setRenderPassModel((ModelBase)this.fireModel);
                this.bindTexture(fireTexture);
                return 1;
            }
            if (pass == 2) {
                GL11.glMatrixMode((int)5890);
                GL11.glLoadIdentity();
                GL11.glMatrixMode((int)5888);
                GL11.glAlphaFunc((int)516, (float)0.1f);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2896);
                GL11.glDepthMask((boolean)true);
                GL11.glDisable((int)2896);
                this.setupFullBright();
                this.setRenderPassModel((ModelBase)this.balrogModelBright);
                this.bindTexture(balrogSkinsBright.getRandomSkin(balrog));
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                return 1;
            }
            if (pass == 3) {
                GL11.glEnable((int)2896);
                GL11.glDisable((int)3042);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
        return -1;
    }

    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack heldItem = entity.getHeldItem();
        if (heldItem != null) {
            GL11.glPushMatrix();
            this.balrogModel.body.postRender(0.0625f);
            this.balrogModel.rightArm.postRender(0.0625f);
            GL11.glTranslatef((float)-0.25f, (float)1.5f, (float)-0.125f);
            float scale = 1.25f;
            GL11.glScalef((float)scale, (float)(-scale), (float)scale);
            GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.renderManager.itemRenderer.renderItem(entity, heldItem, 0);
            if (heldItem.getItem().requiresMultipleRenderPasses()) {
                for (int x = 1; x < heldItem.getItem().getRenderPasses(heldItem.getItemDamage()); ++x) {
                    this.renderManager.itemRenderer.renderItem(entity, heldItem, x);
                }
            }
            GL11.glPopMatrix();
        }
    }

    static {
        fireTexture = new ResourceLocation("lotr:mob/balrog/fire.png");
    }
}

