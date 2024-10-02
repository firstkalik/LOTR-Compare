/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelTroll;
import lotr.client.render.entity.LOTRRandomSkins;
import lotr.client.render.entity.LOTRRenderTroll;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.npc.LOTREntityTroll2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderTroll2
extends LOTRRenderTroll {
    private static LOTRRandomSkins caveTrollSkins;

    public LOTRRenderTroll2() {
        caveTrollSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/troll/troll");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return caveTrollSkins.getRandomSkin((LOTREntityTroll2)entity);
    }

    @Override
    protected void renderTrollWeapon(EntityLivingBase entity, float f) {
        LOTREntityTroll2 troll = (LOTREntityTroll2)entity;
        ((LOTRModelTroll)this.mainModel).renderWoodenClubWithSpikes(0.0625f);
    }
}

