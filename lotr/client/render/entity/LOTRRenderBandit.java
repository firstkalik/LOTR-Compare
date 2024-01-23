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
import lotr.common.entity.npc.LOTREntityBandit;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBandit
extends LOTRRenderBiped {
    private LOTRRandomSkins banditSkins;

    public LOTRRenderBandit(String s) {
        super(new LOTRModelHuman(), 0.5f);
        this.banditSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/bandit/" + s);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return this.banditSkins.getRandomSkin((LOTREntityBandit)entity);
    }
}

