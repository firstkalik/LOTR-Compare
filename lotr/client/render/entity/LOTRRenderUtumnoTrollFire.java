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
import lotr.common.entity.npc.LOTREntityTroll;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderUtumnoTrollFire
extends LOTRRenderTroll {
    private static LOTRRandomSkins utumnoTrollSkins;

    public LOTRRenderUtumnoTrollFire() {
        utumnoTrollSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/troll/utumnofire");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return utumnoTrollSkins.getRandomSkin((LOTREntityTroll)entity);
    }

    @Override
    protected void renderTrollWeapon(EntityLivingBase entity, float f) {
        ((LOTRModelTroll)this.mainModel).renderWoodenClubWithSpikes(0.0625f);
    }
}

