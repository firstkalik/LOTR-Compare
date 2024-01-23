/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelSauron;
import lotr.common.entity.npc.LOTREntitySauron;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderSauron
extends RenderBiped {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/char/sauron.png");

    public LOTRRenderSauron() {
        super((ModelBiped)new LOTRModelSauron(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntitySauron sauron = (LOTREntitySauron)entity;
        if (sauron.getIsUsingMace()) {
            this.modelBipedMain.heldItemRight = 3;
            this.field_82425_h.heldItemRight = 3;
            this.field_82423_g.heldItemRight = 3;
            this.modelBipedMain.aimedBow = true;
            this.field_82425_h.aimedBow = true;
            this.field_82423_g.aimedBow = true;
        }
        super.doRender(entity, d, d1, d2, f, f1);
    }

    protected void func_82422_c() {
        GL11.glTranslatef((float)0.0f, (float)0.4375f, (float)0.0f);
    }
}

