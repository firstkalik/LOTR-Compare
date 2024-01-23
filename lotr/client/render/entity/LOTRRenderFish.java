/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelFish;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTREntityFish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderFish
extends RenderLiving {
    private static Map<String, LOTRRandomSkins> fishTypeSkins = new HashMap<String, LOTRRandomSkins>();

    public LOTRRenderFish() {
        super((ModelBase)new LOTRModelFish(), 0.0f);
    }

    private LOTRRandomSkins getFishSkins(String s) {
        LOTRRandomSkins skins = fishTypeSkins.get(s);
        if (skins == null) {
            skins = LOTRRandomSkins.loadSkinsList("lotr:mob/fish/" + s);
            fishTypeSkins.put(s, skins);
        }
        return skins;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityFish fish = (LOTREntityFish)entity;
        String type = fish.getFishTextureDir();
        LOTRRandomSkins skins = this.getFishSkins(type);
        return skins.getRandomSkin(fish);
    }

    public void preRenderCallback(EntityLivingBase entity, float f) {
        if (!entity.isInWater()) {
            GL11.glTranslatef((float)0.0f, (float)-0.05f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
    }

    protected float handleRotationFloat(EntityLivingBase entity, float f) {
        return super.handleRotationFloat(entity, f);
    }
}

