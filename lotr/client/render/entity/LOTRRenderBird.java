/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelBird;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityBird;
import lotr.common.entity.animal.LOTREntityCrebain;
import lotr.common.entity.animal.LOTREntityGorcrow;
import lotr.common.entity.animal.LOTREntitySeagull;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBird
extends RenderLiving {
    private static Map<String, LOTRRandomSkins> birdTypeSkins = new HashMap<String, LOTRRandomSkins>();
    public static boolean renderStolenItem = true;

    public LOTRRenderBird() {
        super((ModelBase)new LOTRModelBird(), 0.2f);
    }

    private LOTRRandomSkins getBirdSkins(String s) {
        LOTRRandomSkins skins = birdTypeSkins.get(s);
        if (skins == null) {
            skins = LOTRRandomSkins.loadSkinsList("lotr:mob/bird/" + s);
            birdTypeSkins.put(s, skins);
        }
        return skins;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBird bird = (LOTREntityBird)entity;
        String type = bird.getBirdTextureDir();
        LOTRRandomSkins skins = this.getBirdSkins(type);
        return skins.getRandomSkin(bird);
    }

    public void preRenderCallback(EntityLivingBase entity, float f) {
        if (entity instanceof LOTREntityCrebain) {
            float scale = LOTREntityCrebain.CREBAIN_SCALE;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
        } else if (entity instanceof LOTREntityGorcrow) {
            float scale = LOTREntityGorcrow.GORCROW_SCALE;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
        } else if (entity instanceof LOTREntitySeagull) {
            float scale = LOTREntitySeagull.SEAGULL_SCALE;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
        }
    }

    protected float handleRotationFloat(EntityLivingBase entity, float f) {
        LOTREntityBird bird = (LOTREntityBird)entity;
        if (bird.isBirdStill() && bird.flapTime > 0) {
            return (float)bird.flapTime - f;
        }
        return super.handleRotationFloat(entity, f);
    }

    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        LOTREntityBird bird = (LOTREntityBird)entity;
        if (renderStolenItem) {
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            ItemStack stolenItem = bird.getStolenItem();
            if (stolenItem != null) {
                GL11.glPushMatrix();
                ((LOTRModelBird)this.mainModel).head.postRender(0.0625f);
                GL11.glTranslatef((float)0.05f, (float)1.4f, (float)-0.1f);
                float scale = 0.25f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                this.renderManager.itemRenderer.renderItem(entity, stolenItem, 0);
                GL11.glPopMatrix();
            }
        }
    }
}

