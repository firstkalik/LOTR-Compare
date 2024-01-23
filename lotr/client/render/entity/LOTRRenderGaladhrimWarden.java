/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRenderElf;
import lotr.common.entity.npc.LOTREntityGaladhrimWarden;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class LOTRRenderGaladhrimWarden
extends LOTRRenderElf {
    private void doElfInvisibility() {
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.001f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.05f);
    }

    private void undoElfInvisibility() {
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        if (((LOTREntityGaladhrimWarden)entity).isElfSneaking()) {
            this.doElfInvisibility();
        }
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        int j = super.shouldRenderPass(entity, pass, f);
        if (j > 0 && ((LOTREntityGaladhrimWarden)entity).isElfSneaking()) {
            this.doElfInvisibility();
        }
        return j;
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        if (((LOTREntityGaladhrimWarden)entity).isElfSneaking()) {
            this.doElfInvisibility();
            return;
        }
        super.renderEquippedItems(entity, f);
        if (((LOTREntityGaladhrimWarden)entity).isElfSneaking()) {
            this.undoElfInvisibility();
        }
    }

    @Override
    protected void renderNPCCape(LOTREntityNPC entity) {
        if (((LOTREntityGaladhrimWarden)entity).isElfSneaking()) {
            this.doElfInvisibility();
        }
        super.renderNPCCape(entity);
        if (((LOTREntityGaladhrimWarden)entity).isElfSneaking()) {
            this.undoElfInvisibility();
        }
    }
}

