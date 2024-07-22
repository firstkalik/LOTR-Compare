/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.LOTRTextures;
import lotr.client.model.LOTRModelWarg;
import lotr.client.render.entity.LOTRGlowingEyes;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.client.render.entity.LOTRRenderHorse;
import lotr.client.render.entity.LOTRRenderOrcBomb;
import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityOrcBomb;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.entity.npc.LOTREntityWargBombardier;
import lotr.common.entity.npc.LOTREntityWargBombardier2;
import lotr.common.entity.npc.LOTREntityWargBombardier4;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderWarg
extends RenderLiving {
    private static Map wargSkins = new HashMap();
    private static ResourceLocation wargSaddle = new ResourceLocation("lotr:mob/warg/saddle.png");
    private LOTRModelWarg saddleModel = new LOTRModelWarg(0.5f);
    private LOTRModelWarg eyesModel = new LOTRModelWarg(0.05f);

    public LOTRRenderWarg() {
        super((ModelBase)new LOTRModelWarg(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityWarg warg = (LOTREntityWarg)entity;
        ResourceLocation skin = LOTRRenderWarg.getWargSkin(warg.getWargType());
        return LOTRRenderHorse.getLayeredMountTexture(warg, skin);
    }

    public static ResourceLocation getWargSkin(LOTREntityWarg.WargType type) {
        String s = type.textureName();
        ResourceLocation wargSkin = (ResourceLocation)wargSkins.get(s);
        if (wargSkin == null) {
            wargSkin = new ResourceLocation("lotr:mob/warg/" + s + ".png");
            wargSkins.put(s, wargSkin);
        }
        return wargSkin;
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTRRenderOrcBomb bombRenderer;
        int k;
        int j;
        int i;
        if (entity instanceof LOTREntityWargBombardier) {
            GL11.glEnable((int)32826);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.7f), (float)((float)d2));
            GL11.glRotatef((float)(-f), (float)0.0f, (float)1.0f, (float)0.0f);
            i = entity.getBrightnessForRender(f1);
            j = i % 65536;
            k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            bombRenderer = (LOTRRenderOrcBomb)RenderManager.instance.getEntityClassRenderObject(LOTREntityOrcBomb.class);
            bombRenderer.renderBomb((Entity)entity, 0.0, 0.0, 0.0, f1, ((LOTREntityWargBombardier)entity).getBombFuse(), ((LOTREntityWargBombardier)entity).getBombStrengthLevel(), 0.75f, 1.0f);
            GL11.glPopMatrix();
            GL11.glDisable((int)32826);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && ((LOTREntityWarg)entity).hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
            LOTRNPCRendering.renderHiredIcon((EntityLivingBase)entity, d, d1 + 0.5, d2);
            LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 0.5, d2);
        }
        if (entity instanceof LOTREntityWargBombardier2) {
            GL11.glEnable((int)32826);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.7f), (float)((float)d2));
            GL11.glRotatef((float)(-f), (float)0.0f, (float)1.0f, (float)0.0f);
            i = entity.getBrightnessForRender(f1);
            j = i % 65536;
            k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            bombRenderer = (LOTRRenderOrcBomb)RenderManager.instance.getEntityClassRenderObject(LOTREntityOrcBomb.class);
            bombRenderer.renderBomb((Entity)entity, 0.0, 0.0, 0.0, f1, ((LOTREntityWargBombardier2)entity).getBombFuse(), ((LOTREntityWargBombardier2)entity).getBombStrengthLevel(), 0.75f, 1.0f);
            GL11.glPopMatrix();
            GL11.glDisable((int)32826);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        super.doRender(entity, d, d1, d2, f, f1);
        if (entity instanceof LOTREntityWargBombardier4) {
            GL11.glEnable((int)32826);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d), (float)((float)d1 + 1.7f), (float)((float)d2));
            GL11.glRotatef((float)(-f), (float)0.0f, (float)1.0f, (float)0.0f);
            i = entity.getBrightnessForRender(f1);
            j = i % 65536;
            k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
            GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)1.0f);
            bombRenderer = (LOTRRenderOrcBomb)RenderManager.instance.getEntityClassRenderObject(LOTREntityOrcBomb.class);
            bombRenderer.renderBomb((Entity)entity, 0.0, 0.0, 0.0, f1, ((LOTREntityWargBombardier4)entity).getBombFuse(), ((LOTREntityWargBombardier4)entity).getBombStrengthLevel(), 0.75f, 1.0f);
            GL11.glPopMatrix();
            GL11.glDisable((int)32826);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && ((LOTREntityWarg)entity).hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
            LOTRNPCRendering.renderHiredIcon((EntityLivingBase)entity, d, d1 + 0.5, d2);
            LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 0.5, d2);
        }
    }

    protected void renderModel(EntityLivingBase entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.renderModel(entity, f, f1, f2, f3, f4, f5);
        LOTREntityWarg warg = (LOTREntityWarg)entity;
        ResourceLocation eyes = LOTRTextures.getEyesTexture(LOTRRenderWarg.getWargSkin(warg.getWargType()), new int[][]{{100, 12}, {108, 12}}, 2, 1);
        LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes, this.eyesModel, f, f1, f2, f3, f4, f5);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        LOTREntityWarg warg = (LOTREntityWarg)entity;
        if (pass == 0 && warg.isMountSaddled()) {
            this.bindTexture(wargSaddle);
            this.setRenderPassModel((ModelBase)this.saddleModel);
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        if (LOTRMod.isAprilFools()) {
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }
}

