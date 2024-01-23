/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelEnt;
import lotr.client.model.LOTRModelTroll;
import lotr.common.entity.item.LOTREntityBossTrophy;
import lotr.common.item.LOTRItemBossTrophy;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBossTrophy
extends Render {
    private static Map<LOTRItemBossTrophy.TrophyType, ResourceLocation> trophyTextures = new HashMap<LOTRItemBossTrophy.TrophyType, ResourceLocation>();
    private static LOTRModelTroll trollModel = new LOTRModelTroll();
    private static LOTRModelEnt entModel = new LOTRModelEnt();

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBossTrophy trophy = (LOTREntityBossTrophy)entity;
        LOTRItemBossTrophy.TrophyType type = trophy.getTrophyType();
        ResourceLocation r = trophyTextures.get((Object)type);
        if (r == null) {
            r = new ResourceLocation("lotr:item/bossTrophy/" + type.trophyName + ".png");
            trophyTextures.put(type, r);
        }
        return r;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityBossTrophy trophy = (LOTREntityBossTrophy)entity;
        LOTRItemBossTrophy.TrophyType type = trophy.getTrophyType();
        float modelscale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        float rotation = 0.0f;
        rotation = trophy.isTrophyHanging() ? 180.0f + (float)trophy.getTrophyFacing() * 90.0f : 180.0f - entity.rotationYaw;
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        this.bindEntityTexture(entity);
        if (type == LOTRItemBossTrophy.TrophyType.MOUNTAIN_TROLL_CHIEFTAIN) {
            ModelRenderer head = LOTRRenderBossTrophy.trollModel.head;
            head.setRotationPoint(0.0f, -6.0f, 6.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.05f, (float)0.1f);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.25f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)15.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            head.render(modelscale);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.25f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)-15.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            head.render(modelscale);
            GL11.glPopMatrix();
        }
        if (type == LOTRItemBossTrophy.TrophyType.MALLORN_ENT) {
            ModelRenderer trunk = LOTRRenderBossTrophy.entModel.trunk;
            LOTRRenderBossTrophy.entModel.rightArm.showModel = false;
            LOTRRenderBossTrophy.entModel.leftArm.showModel = false;
            LOTRRenderBossTrophy.entModel.trophyBottomPanel.showModel = true;
            float scale = 0.6f;
            GL11.glTranslatef((float)0.0f, (float)(34.0f * modelscale * scale), (float)0.0f);
            if (trophy.isTrophyHanging()) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(3.0f * modelscale / scale));
            }
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            trunk.render(modelscale);
        }
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

