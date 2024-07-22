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
import lotr.common.entity.npc.LOTREntityTundraSnowTroll;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderSnowTroll4
extends LOTRRenderTroll {
    private static LOTRRandomSkins snowTrollSkins;

    public LOTRRenderSnowTroll4() {
        snowTrollSkins = LOTRRandomSkins.loadSkinsList("lotr:mob/troll/tundrasnowTroll");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return snowTrollSkins.getRandomSkin((LOTREntityTroll)entity);
    }

    @Override
    protected void bindTrollOutfitTexture(EntityLivingBase entity) {
    }

    @Override
    protected void renderTrollWeapon(EntityLivingBase entity, float f) {
        LOTREntityTundraSnowTroll troll = (LOTREntityTundraSnowTroll)entity;
        if (!troll.isThrowingSnow()) {
            ((LOTRModelTroll)this.mainModel).renderWoodenClubWithSpikes(0.0625f);
        }
    }
}

