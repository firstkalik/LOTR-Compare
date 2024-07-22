/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package lotr.client.render.entity;

import java.util.HashMap;
import lotr.client.LOTRTextures;
import lotr.client.model.LOTRModelEnt;
import lotr.client.render.entity.LOTRGlowingEyes;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.common.entity.npc.LOTREntityEnt;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityTree;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderEnt
extends RenderLiving {
    private static HashMap entTextures = new HashMap();
    private LOTRModelEnt eyesModel = new LOTRModelEnt(0.05f);

    public LOTRRenderEnt() {
        super((ModelBase)new LOTRModelEnt(), 0.5f);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        int treeType = ((LOTREntityEnt)entity).getTreeType();
        String s = "lotr:mob/ent/" + LOTREntityTree.TYPES[treeType] + ".png";
        ResourceLocation r = (ResourceLocation)entTextures.get(treeType);
        if (r == null) {
            r = new ResourceLocation(s);
            entTextures.put(treeType, r);
        }
        return r;
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && ((LOTREntityNPC)entity).hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
            LOTRNPCRendering.renderHiredIcon((EntityLivingBase)entity, d, d1 + 1.0, d2);
            LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)entity, d, d1 + 2.0, d2);
        }
    }

    protected void renderModel(EntityLivingBase entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.renderModel(entity, f, f1, f2, f3, f4, f5);
        ResourceLocation eyes = LOTRTextures.getEyesTexture(this.getEntityTexture((Entity)entity), new int[][]{{15, 23}, {22, 23}}, 3, 2);
        LOTRGlowingEyes.renderGlowingEyes((Entity)entity, eyes, this.eyesModel, f, f1, f2, f3, f4, f5);
    }
}

