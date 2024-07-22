/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.LOTRSpeechClient;
import lotr.client.model.LOTRModelDwarf;
import lotr.client.render.entity.LOTRRenderDwarf;
import lotr.common.entity.npc.LOTREntityBlacklock;
import lotr.common.entity.npc.LOTREntityBlueDwarf;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderDwarfCommander
extends LOTRRenderDwarf {
    private static ResourceLocation cloak = new ResourceLocation("lotr:mob/dwarf/commander_cloak.png");
    private static ResourceLocation blueCloak = new ResourceLocation("lotr:mob/dwarf/blueMountains_commander_cloak.png");
    private static ResourceLocation redCloak = new ResourceLocation("lotr:mob/dwarf/commander_cloak.png");
    private LOTRModelDwarf cloakModel = new LOTRModelDwarf(1.5f);

    protected ResourceLocation getCloakTexture(EntityLivingBase entity) {
        return entity instanceof LOTREntityBlueDwarf ? blueCloak : cloak;
    }

    protected ResourceLocation getCloakTexture1(EntityLivingBase entity) {
        return entity instanceof LOTREntityBlacklock ? redCloak : cloak;
    }

    @Override
    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityNPC legend = (LOTREntityNPC)entity;
        super.doRender((EntityLiving)legend, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && !LOTRSpeechClient.hasSpeech(legend)) {
            this.func_147906_a((Entity)legend, legend.getCommandSenderName(), d, d1 + 0.2, d2, 64);
        }
    }

    @Override
    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        if (pass == 0) {
            this.bindTexture(this.getCloakTexture((EntityLivingBase)entity));
            this.cloakModel.bipedHead.showModel = false;
            this.cloakModel.bipedHeadwear.showModel = false;
            this.cloakModel.bipedBody.showModel = true;
            this.cloakModel.bipedRightArm.showModel = true;
            this.cloakModel.bipedLeftArm.showModel = true;
            this.cloakModel.bipedRightLeg.showModel = false;
            this.cloakModel.bipedLeftLeg.showModel = false;
            this.setRenderPassModel((ModelBase)this.cloakModel);
            this.cloakModel.onGround = this.mainModel.onGround;
            this.cloakModel.isRiding = this.mainModel.isRiding;
            this.cloakModel.isChild = this.mainModel.isChild;
            this.cloakModel.heldItemRight = this.modelBipedMain.heldItemRight;
            return 1;
        }
        return super.shouldRenderPass(entity, pass, f);
    }
}

