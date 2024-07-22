/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import lotr.client.model.LOTRModelSkeleton;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderSkeleton
extends RenderBiped {
    private static ResourceLocation skin = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public LOTRRenderSkeleton() {
        super((ModelBiped)new LOTRModelSkeleton(), 0.5f);
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && entity instanceof LOTREntityNPC) {
            LOTREntityNPC npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
                LOTRNPCRendering.renderHiredIcon((EntityLivingBase)npc, d, d1 + 0.5, d2);
                LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)npc, d, d1 + 0.5, d2);
            }
            LOTRNPCRendering.renderQuestBook(npc, d, d1, d2);
            LOTRNPCRendering.renderQuestOffer(npc, d, d1, d2);
        }
    }

    protected void func_82421_b() {
        this.field_82423_g = new LOTRModelSkeleton(1.0f);
        this.field_82425_h = new LOTRModelSkeleton(0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return skin;
    }
}

