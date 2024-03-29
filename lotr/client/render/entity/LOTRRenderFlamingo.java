/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelFlamingo;
import lotr.common.entity.animal.LOTREntityFlamingo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderFlamingo
extends RenderLiving {
    private static ResourceLocation texture = new ResourceLocation("lotr:mob/flamingo/flamingo.png");
    private static ResourceLocation textureChick = new ResourceLocation("lotr:mob/flamingo/chick.png");

    public LOTRRenderFlamingo() {
        super((ModelBase)new LOTRModelFlamingo(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return ((LOTREntityFlamingo)entity).isChild() ? textureChick : texture;
    }

    protected float handleRotationFloat(EntityLivingBase entity, float f) {
        LOTREntityFlamingo flamingo = (LOTREntityFlamingo)entity;
        float f1 = flamingo.field_756_e + (flamingo.field_752_b - flamingo.field_756_e) * f;
        float f2 = flamingo.field_757_d + (flamingo.destPos - flamingo.field_757_d) * f;
        return (MathHelper.sin((float)f1) + 1.0f) * f2;
    }
}

