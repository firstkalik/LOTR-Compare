/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderDunedain;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityRanger;
import lotr.common.entity.npc.LOTREntityRangerIthilien;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderRanger
extends LOTRRenderDunedain {
    private static LOTRRandomSkins ithilienSkins;

    public LOTRRenderRanger() {
        ithilienSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/ranger");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityRanger ranger = (LOTREntityRanger)entity;
        if (ranger instanceof LOTREntityRangerIthilien) {
            return ithilienSkins.getRandomSkin(ranger);
        }
        return super.getEntityTexture(entity);
    }

    private void doRangerInvisibility() {
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.001f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
    }

    private void undoRangerInvisibility() {
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        if (((LOTREntityRanger)entity).isRangerSneaking()) {
            this.doRangerInvisibility();
        }
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        int i = super.shouldRenderPass(entity, pass, f);
        if (i > 0 && ((LOTREntityRanger)entity).isRangerSneaking()) {
            this.doRangerInvisibility();
        }
        return i;
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        if (((LOTREntityRanger)entity).isRangerSneaking()) {
            this.doRangerInvisibility();
        }
        super.renderEquippedItems(entity, f);
        if (((LOTREntityRanger)entity).isRangerSneaking()) {
            this.undoRangerInvisibility();
        }
    }

    @Override
    protected void renderNPCCape(LOTREntityNPC entity) {
        if (((LOTREntityRanger)entity).isRangerSneaking()) {
            this.doRangerInvisibility();
        }
        super.renderNPCCape(entity);
        if (((LOTREntityRanger)entity).isRangerSneaking()) {
            this.undoRangerInvisibility();
        }
    }
}

