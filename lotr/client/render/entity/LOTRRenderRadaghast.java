/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.LOTRSpeechClient;
import lotr.client.model.LOTRModelHuman;
import lotr.client.model.LOTRModelWizardHat;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.npc.LOTREntityRadaghast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderRadaghast
extends LOTRRenderBiped {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/char/radaghast.png");
    private ModelBiped hatModel = new LOTRModelWizardHat(1.0f);
    private ModelBiped cloakModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderRadaghast() {
        super(new LOTRModelHuman(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    @Override
    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityRadaghast gandalf = (LOTREntityRadaghast)entity;
        super.doRender((EntityLiving)gandalf, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && !LOTRSpeechClient.hasSpeech(gandalf)) {
            this.func_147906_a((Entity)gandalf, gandalf.getCommandSenderName(), d, d1 + 0.25, d2, 64);
        }
    }
}

