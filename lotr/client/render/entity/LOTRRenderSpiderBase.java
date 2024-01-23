/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
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

import lotr.client.LOTRTextures;
import lotr.client.model.LOTRModelSpider;
import lotr.client.render.entity.LOTRGlowingEyes;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class LOTRRenderSpiderBase
extends RenderLiving {
    private LOTRModelSpider eyesModel = new LOTRModelSpider(0.55f);

    public LOTRRenderSpiderBase() {
        super((ModelBase)new LOTRModelSpider(0.5f), 1.0f);
        this.setRenderPassModel((ModelBase)new LOTRModelSpider(0.5f));
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && ((LOTREntitySpiderBase)entity).hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
            LOTRNPCRendering.renderHiredIcon((EntityLivingBase)entity, d, d1 + 0.5, d2);
            LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 0.5, d2);
        }
    }

    protected void renderModel(EntityLivingBase entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.renderModel(entity, f, f1, f2, f3, f4, f5);
        ResourceLocation eyes1 = LOTRTextures.getEyesTexture(this.getEntityTexture((Entity)entity), new int[][]{{39, 10}, {42, 11}, {44, 11}, {47, 10}}, 2, 2);
        ResourceLocation eyes2 = LOTRTextures.getEyesTexture(this.getEntityTexture((Entity)entity), new int[][]{{41, 8}, {42, 9}, {45, 9}, {46, 8}}, 1, 1);
        LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes1, this.eyesModel, f, f1, f2, f3, f4, f5);
        LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes2, this.eyesModel, f, f1, f2, f3, f4, f5);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float scale = ((LOTREntitySpiderBase)entity).getSpiderScaleAmount();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
    }

    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return 180.0f;
    }
}

