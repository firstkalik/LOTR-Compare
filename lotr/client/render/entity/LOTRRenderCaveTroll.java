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
import lotr.common.entity.npc.LOTREntityGundabadCaveTroll;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderCaveTroll
extends LOTRRenderTroll {
    private static LOTRRandomSkins caveTrollSkins;

    public LOTRRenderCaveTroll() {
        caveTrollSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/troll/caveTroll");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return caveTrollSkins.getRandomSkin((LOTREntityGundabadCaveTroll)entity);
    }

    @Override
    protected void renderTrollWeapon(EntityLivingBase entity, float f) {
        LOTREntityGundabadCaveTroll troll = (LOTREntityGundabadCaveTroll)entity;
        ((LOTRModelTroll)this.mainModel).renderWoodenClubWithSpikes(0.0625f);
    }
}

