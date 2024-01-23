/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.LOTRTextures;
import lotr.client.model.LOTRModelOrc;
import lotr.client.render.entity.LOTRGlowingEyes;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityBlackUruk;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTREntityUrukHai;
import lotr.common.entity.npc.LOTREntityUrukHaiBerserker;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderOrc
extends LOTRRenderBiped {
    private static LOTRRandomSkins orcSkins;
    private static LOTRRandomSkins urukSkins;
    private static LOTRRandomSkins blackUrukSkins;
    private LOTRModelOrc eyesModel = new LOTRModelOrc(0.05f);

    public LOTRRenderOrc() {
        super(new LOTRModelOrc(), 0.5f);
        orcSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/orc/orc");
        urukSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/orc/urukHai");
        blackUrukSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/orc/blackUruk");
    }

    @Override
    protected void func_82421_b() {
        this.field_82423_g = new LOTRModelOrc(1.0f);
        this.field_82425_h = new LOTRModelOrc(0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityOrc orc = (LOTREntityOrc)entity;
        if (orc instanceof LOTREntityUrukHai) {
            return urukSkins.getRandomSkin(orc);
        }
        if (orc instanceof LOTREntityBlackUruk) {
            return blackUrukSkins.getRandomSkin(orc);
        }
        return orcSkins.getRandomSkin(orc);
    }

    protected void renderModel(EntityLivingBase entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.renderModel(entity, f, f1, f2, f3, f4, f5);
        ResourceLocation eyes = LOTRTextures.getEyesTexture(this.getEntityTexture((Entity)entity), new int[][]{{9, 11}, {13, 11}}, 2, 1);
        LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes, this.eyesModel, f, f1, f2, f3, f4, f5);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
        super.preRenderCallback(entity, f);
        LOTREntityOrc orc = (LOTREntityOrc)entity;
        if (orc.isWeakOrc) {
            GL11.glScalef((float)0.85f, (float)0.85f, (float)0.85f);
        } else if (orc instanceof LOTREntityUrukHaiBerserker) {
            float scale = LOTREntityUrukHaiBerserker.BERSERKER_SCALE;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
        }
    }
}

