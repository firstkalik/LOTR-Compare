/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.LOTRTickHandlerClient;
import lotr.client.model.LOTRModelBarrowWight;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityBarrowWight2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBarrowWight2
extends LOTRRenderBiped {
    private static LOTRRandomSkins wightSkins;

    public LOTRRenderBarrowWight2() {
        super(new LOTRModelBarrowWight(), 0.0f);
        wightSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/barrowWight2/wight");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBarrowWight2 wight = (LOTREntityBarrowWight2)entity;
        return wightSkins.getRandomSkin(wight);
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        EntityLivingBase viewer;
        super.doRender(entity, d, d1, d2, f, f1);
        LOTREntityBarrowWight2 wight = (LOTREntityBarrowWight2)entity;
        if (wight.addedToChunk && (viewer = Minecraft.getMinecraft().renderViewEntity) != null && wight.getTargetEntityID() == viewer.getEntityId()) {
            LOTRTickHandlerClient.anyWightsViewed = true;
        }
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        float hover = MathHelper.sin((float)(((float)entity.ticksExisted + f) * 0.05f)) * 0.2f;
        GL11.glTranslatef((float)0.0f, (float)hover, (float)0.0f);
        if (entity.deathTime > 0) {
            float death = ((float)entity.deathTime + f - 1.0f) / 20.0f;
            death = Math.max(0.0f, death);
            death = Math.min(1.0f, death);
            float scale = 1.0f + death * 1.0f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - death));
        }
    }

    protected float getDeathMaxRotation(EntityLivingBase entity) {
        return 0.0f;
    }
}

