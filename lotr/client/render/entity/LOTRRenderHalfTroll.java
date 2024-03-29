/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelHalfTroll;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityHalfTroll;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderHalfTroll
extends LOTRRenderBiped {
    private static LOTRRandomSkins halfTrollSkins;

    public LOTRRenderHalfTroll() {
        super(new LOTRModelHalfTroll(), 0.5f);
        halfTrollSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/halfTroll/halfTroll");
    }

    @Override
    protected void func_82421_b() {
        this.field_82423_g = new LOTRModelHalfTroll(1.0f);
        this.field_82425_h = new LOTRModelHalfTroll(0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityHalfTroll halfTroll = (LOTREntityHalfTroll)entity;
        return halfTrollSkins.getRandomSkin(halfTroll);
    }

    @Override
    public float getHeldItemYTranslation() {
        return 0.45f;
    }
}

