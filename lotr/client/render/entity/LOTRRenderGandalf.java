/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.LOTRSpeechClient;
import lotr.client.model.LOTRModelHuman;
import lotr.client.model.LOTRModelWizardHat;
import lotr.client.render.entity.LOTRRenderBiped;
import lotr.common.entity.npc.LOTREntityGandalf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderGandalf
extends LOTRRenderBiped {
    private static ResourceLocation skin = new ResourceLocation("lotr:mob/char/gandalf.png");
    private static ResourceLocation hat = new ResourceLocation("lotr:mob/char/gandalf_hat.png");
    private static ResourceLocation cloak = new ResourceLocation("lotr:mob/char/gandalf_cloak.png");
    private ModelBiped hatModel = new LOTRModelWizardHat(1.0f);
    private ModelBiped cloakModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderGandalf() {
        super(new LOTRModelHuman(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }

    @Override
    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityGandalf gandalf = (LOTREntityGandalf)entity;
        super.doRender((EntityLiving)gandalf, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && !LOTRSpeechClient.hasSpeech(gandalf)) {
            this.func_147906_a((Entity)gandalf, gandalf.getCommandSenderName(), d, d1 + 0.75, d2, 64);
        }
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        LOTREntityGandalf gandalf = (LOTREntityGandalf)entity;
        if (pass == 0 && gandalf.getEquipmentInSlot(4) == null) {
            this.setRenderPassModel((ModelBase)this.hatModel);
            this.bindTexture(hat);
            return 1;
        }
        if (pass == 1 && gandalf.getEquipmentInSlot(3) == null) {
            this.setRenderPassModel((ModelBase)this.cloakModel);
            this.bindTexture(cloak);
            return 1;
        }
        return super.shouldRenderPass((EntityLiving)gandalf, pass, f);
    }
}

