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
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuorn;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityHuornBase;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderHuorn
extends RenderLiving {
    private static LOTRRandomSkins faceSkins;

    public LOTRRenderHuorn() {
        super((ModelBase)new LOTRModelHuorn(), 0.0f);
        faceSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/huorn/face");
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityHuornBase huorn = (LOTREntityHuornBase)entity;
        return faceSkins.getRandomSkin(huorn);
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityHuornBase huorn = (LOTREntityHuornBase)entity;
        if (huorn.ignoringFrustumForRender) {
            huorn.ignoringFrustumForRender = false;
            huorn.ignoreFrustumCheck = false;
        }
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && huorn.hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
            LOTRNPCRendering.renderHiredIcon((EntityLivingBase)entity, d, d1 + 3.5, d2);
            LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 3.5, d2);
        }
    }

    protected void renderLivingAt(EntityLivingBase entity, double d, double d1, double d2) {
        LOTREntityHuornBase huorn = (LOTREntityHuornBase)entity;
        if (!huorn.isHuornActive()) {
            int i = MathHelper.floor_double((double)huorn.posX);
            int j = MathHelper.floor_double((double)huorn.posY);
            int k = MathHelper.floor_double((double)huorn.posZ);
            d = (double)i + 0.5 - RenderManager.renderPosX;
            d1 = (double)j - RenderManager.renderPosY;
            d2 = (double)k + 0.5 - RenderManager.renderPosZ;
        }
        super.renderLivingAt(entity, d, d1 -= 0.0078125, d2);
        huorn.hurtTime = 0;
    }

    protected void rotateCorpse(EntityLivingBase entity, float f, float f1, float f2) {
        LOTREntityHuornBase huorn = (LOTREntityHuornBase)entity;
        if (!huorn.isHuornActive()) {
            f1 = 0.0f;
        }
        super.rotateCorpse(entity, f, f1, f2);
    }

    protected float handleRotationFloat(EntityLivingBase entity, float f) {
        return f;
    }
}

