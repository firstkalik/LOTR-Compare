/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderSwanKnight
extends LOTRRenderBiped {
    private static LOTRRandomSkins dolAmrothSkins;

    public LOTRRenderSwanKnight() {
        super(new LOTRModelHuman(), 0.5f);
        dolAmrothSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/swanKnight");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return dolAmrothSkins.getRandomSkin((LOTREntityNPC)entity);
    }
}

