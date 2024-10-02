/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import lotr.client.model.LOTRModelFrog;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.animal.LOTREntityFrog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderFrog
extends RenderLiving {
    private static final ResourceLocation textureSnow = new ResourceLocation("lotr:mob/frog/snow.png");
    private static final ResourceLocation textureDesert = new ResourceLocation("lotr:mob/frog/desert.png");
    private static final ResourceLocation textureDefault = new ResourceLocation("lotr:mob/frog/default.png");
    private static final Map<String, LOTRRandomSkins> frogTypeSkins = new HashMap<String, LOTRRandomSkins>();

    public LOTRRenderFrog() {
        super((ModelBase)new LOTRModelFrog(), 0.2f);
    }

    private LOTRRandomSkins getFrogSkins(String textureDir) {
        LOTRRandomSkins skins = frogTypeSkins.get(textureDir);
        if (skins == null) {
            skins = LOTRRandomSkins.loadSkinsList("lotr:mob/frog/" + textureDir);
            frogTypeSkins.put(textureDir, skins);
        }
        return skins;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof LOTREntityFrog) {
            LOTREntityFrog frog = (LOTREntityFrog)entity;
            LOTREntityFrog.FrogType frogType = frog.getFrogType();
            switch (frogType) {
                case SNOW: {
                    return textureSnow;
                }
                case DESERT: {
                    return textureDesert;
                }
                case COMMON: {
                    return textureDefault;
                }
            }
        }
        return null;
    }

}

