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
import lotr.client.model.LOTRModelFox;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.common.entity.animal.LOTREntityFox;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderFox
extends RenderLiving {
    private static final ResourceLocation textureCommon = new ResourceLocation("lotr:mob/fox/common.png");
    private static final ResourceLocation textureDesert = new ResourceLocation("lotr:mob/fox/desert.png");
    private static final ResourceLocation textureMiddle = new ResourceLocation("lotr:mob/fox/middle.png");
    private static final ResourceLocation textureSnow = new ResourceLocation("lotr:mob/fox/snow.png");
    private static final Map<String, LOTRRandomSkins> foxTypeSkins = new HashMap<String, LOTRRandomSkins>();

    public LOTRRenderFox() {
        super((ModelBase)new LOTRModelFox(), 0.2f);
    }

    private LOTRRandomSkins getFoxSkins(String textureDir) {
        LOTRRandomSkins skins = foxTypeSkins.get(textureDir);
        if (skins == null) {
            skins = LOTRRandomSkins.loadSkinsList("lotr:mob/fox/" + textureDir);
            foxTypeSkins.put(textureDir, skins);
        }
        return skins;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof LOTREntityFox) {
            LOTREntityFox fox = (LOTREntityFox)entity;
            LOTREntityFox.FoxType foxType = fox.getFoxType();
            switch (foxType) {
                case SNOW: {
                    return textureSnow;
                }
                case DESERT: {
                    return textureDesert;
                }
                case MIDDLE: {
                    return textureMiddle;
                }
            }
            return textureCommon;
        }
        return null;
    }

}

