/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelEnt;
import lotr.client.render.entity.LOTRRenderEnt;
import lotr.common.entity.npc.LOTREntityMallornEnt;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderMallornEnt
extends LOTRRenderEnt {
    private static ResourceLocation mallornEntSkin = new ResourceLocation("lotr:mob/ent/mallorn.png");
    private static ResourceLocation shieldSkin = new ResourceLocation("lotr:mob/ent/mallornEnt_shield.png");
    private LOTRModelEnt shieldModel = new LOTRModelEnt();

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        super.doRender(entity, d, d1, d2, f, f1);
        LOTREntityMallornEnt ent = (LOTREntityMallornEnt)entity;
        if (ent.addedToChunk) {
            BossStatus.setBossStatus((IBossDisplayData)ent, (boolean)false);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return mallornEntSkin;
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        LOTREntityMallornEnt ent = (LOTREntityMallornEnt)entity;
        float scale = LOTREntityMallornEnt.BOSS_SCALE;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslatef((float)0.0f, (float)(-ent.getSpawningOffset(f)), (float)0.0f);
        if (ent.isWeaponShieldActive()) {
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        }
    }

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float f) {
        LOTREntityMallornEnt ent = (LOTREntityMallornEnt)entity;
        if (ent.isWeaponShieldActive()) {
            if (pass == 1) {
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
                GL11.glMatrixMode((int)5890);
                GL11.glLoadIdentity();
                float f1 = (float)ent.ticksExisted + f;
                float f2 = f1 * 0.01f;
                float f3 = f1 * 0.01f;
                GL11.glTranslatef((float)f2, (float)f3, (float)0.0f);
                GL11.glMatrixMode((int)5888);
                GL11.glAlphaFunc((int)516, (float)0.01f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)1, (int)1);
                float alpha = 0.3f + MathHelper.sin((float)(f1 * 0.05f)) * 0.15f;
                GL11.glColor4f((float)alpha, (float)alpha, (float)alpha, (float)1.0f);
                GL11.glDisable((int)2896);
                GL11.glDepthMask((boolean)false);
                this.setRenderPassModel((ModelBase)this.shieldModel);
                this.bindTexture(shieldSkin);
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
            }
        }
        return -1;
    }
}

