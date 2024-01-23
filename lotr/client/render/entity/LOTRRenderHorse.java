/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelHorse
 *  net.minecraft.client.renderer.entity.RenderHorse
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.LayeredTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.lang3.StringUtils
 */
package lotr.client.render.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTRNPCMount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

public class LOTRRenderHorse
extends RenderHorse {
    private static Map<String, ResourceLocation> layeredMountTextures = new HashMap<String, ResourceLocation>();

    public LOTRRenderHorse() {
        super((ModelBase)new ModelHorse(), 0.75f);
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityHorse horse = (LOTREntityHorse)entity;
        boolean fools = LOTRMod.isAprilFools();
        int horseType = horse.getHorseType();
        if (fools) {
            horse.setHorseType(1);
        }
        super.doRender(entity, d, d1, d2, f, f1);
        if (fools) {
            horse.setHorseType(horseType);
        }
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityHorse horse = (LOTREntityHorse)entity;
        ResourceLocation horseSkin = super.getEntityTexture(entity);
        return LOTRRenderHorse.getLayeredMountTexture(horse, horseSkin);
    }

    public static ResourceLocation getLayeredMountTexture(LOTRNPCMount mount, ResourceLocation mountSkin) {
        String skinPath = mountSkin.toString();
        String armorPath = mount.getMountArmorTexture();
        if (armorPath == null || StringUtils.isBlank((CharSequence)armorPath)) {
            return mountSkin;
        }
        Minecraft mc = Minecraft.getMinecraft();
        String path = "lotr_" + skinPath + "_" + armorPath;
        ResourceLocation texture = layeredMountTextures.get(path);
        if (texture == null) {
            texture = new ResourceLocation(path);
            ArrayList<String> layers = new ArrayList<String>();
            ITextureObject skinTexture = mc.getTextureManager().getTexture(mountSkin);
            if (skinTexture instanceof LayeredTexture) {
                LayeredTexture skinTextureLayered = (LayeredTexture)skinTexture;
                layers.addAll(skinTextureLayered.layeredTextureNames);
            } else {
                layers.add(skinPath);
            }
            layers.add(armorPath);
            mc.getTextureManager().loadTexture(texture, (ITextureObject)new LayeredTexture(layers.toArray(new String[0])));
            layeredMountTextures.put(path, texture);
        }
        return texture;
    }
}

