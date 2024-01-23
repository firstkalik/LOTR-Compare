/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import java.util.Set;
import lotr.client.LOTRClientProxy;
import lotr.client.LOTRTickHandlerClient;
import lotr.client.fx.LOTREntityAlignmentBonus;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRAlignmentBonusMap;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRRenderAlignmentBonus
extends Render {
    public LOTRRenderAlignmentBonus() {
        this.shadowSize = 0.0f;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return LOTRClientProxy.alignmentTexture;
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        EntityClientPlayerMP entityplayer = Minecraft.getMinecraft().thePlayer;
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entityplayer);
        LOTRFaction viewingFaction = playerData.getViewingFaction();
        LOTREntityAlignmentBonus bonusParticle = (LOTREntityAlignmentBonus)entity;
        LOTRFaction mainFaction = bonusParticle.mainFaction;
        LOTRAlignmentBonusMap factionBonusMap = bonusParticle.factionBonusMap;
        LOTRFaction renderFaction = null;
        boolean showConquest = false;
        if (bonusParticle.conquestBonus > 0.0f && playerData.isPledgedTo(viewingFaction)) {
            renderFaction = viewingFaction;
            showConquest = true;
        } else if (bonusParticle.conquestBonus < 0.0f && (viewingFaction == mainFaction || playerData.isPledgedTo(viewingFaction))) {
            renderFaction = viewingFaction;
            showConquest = true;
        } else if (!factionBonusMap.isEmpty()) {
            if (factionBonusMap.containsKey((Object)viewingFaction)) {
                renderFaction = viewingFaction;
            } else if (factionBonusMap.size() == 1 && mainFaction.isPlayableAlignmentFaction()) {
                renderFaction = mainFaction;
            } else if (mainFaction.isPlayableAlignmentFaction() && bonusParticle.prevMainAlignment >= 0.0f && ((Float)factionBonusMap.get((Object)mainFaction)).floatValue() < 0.0f) {
                renderFaction = mainFaction;
            } else {
                float alignment;
                float bonus;
                for (LOTRFaction faction : factionBonusMap.keySet()) {
                    if (!faction.isPlayableAlignmentFaction() || !((bonus = ((Float)factionBonusMap.get((Object)faction)).floatValue()) > 0.0f)) continue;
                    alignment = playerData.getAlignment(faction);
                    if (renderFaction != null && !(alignment > playerData.getAlignment(renderFaction))) continue;
                    renderFaction = faction;
                }
                if (renderFaction == null) {
                    if (mainFaction.isPlayableAlignmentFaction() && ((Float)factionBonusMap.get((Object)mainFaction)).floatValue() < 0.0f) {
                        renderFaction = mainFaction;
                    } else {
                        for (LOTRFaction faction : factionBonusMap.keySet()) {
                            if (!faction.isPlayableAlignmentFaction() || !((bonus = ((Float)factionBonusMap.get((Object)faction)).floatValue()) < 0.0f)) continue;
                            alignment = playerData.getAlignment(faction);
                            if (renderFaction != null && !(alignment > playerData.getAlignment(renderFaction))) continue;
                            renderFaction = faction;
                        }
                    }
                }
            }
        }
        if (renderFaction != null) {
            float alignBonus = factionBonusMap.containsKey((Object)renderFaction) ? ((Float)factionBonusMap.get((Object)renderFaction)).floatValue() : 0.0f;
            boolean showAlign = alignBonus != 0.0f;
            float conqBonus = bonusParticle.conquestBonus;
            if (showAlign || showConquest) {
                String title = bonusParticle.name;
                boolean isViewingFaction = renderFaction == viewingFaction;
                boolean showTitle = showAlign || showConquest && !bonusParticle.isHiredKill;
                float particleHealth = (float)bonusParticle.particleAge / (float)bonusParticle.particleMaxAge;
                float alpha = particleHealth < 0.75f ? 1.0f : (1.0f - particleHealth) / 0.25f;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)this.renderManager.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)-0.025f, (float)-0.025f, (float)0.025f);
                GL11.glDisable((int)2896);
                GL11.glDepthMask((boolean)false);
                GL11.glDisable((int)2929);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                this.renderBonusText(isViewingFaction, renderFaction, title, showTitle, alignBonus, showAlign, conqBonus, showConquest, alpha);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2929);
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2896);
                GL11.glPopMatrix();
            }
        }
    }

    private void renderBonusText(boolean isViewingFaction, LOTRFaction renderFaction, String title, boolean showTitle, float align, boolean showAlign, float conq, boolean showConq, float alpha) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = mc.fontRenderer;
        String strAlign = LOTRAlignmentValues.formatAlignForDisplay(align);
        String strConq = LOTRAlignmentValues.formatConqForDisplay(conq, true);
        boolean negativeConq = conq < 0.0f;
        GL11.glPushMatrix();
        if (!isViewingFaction) {
            float scale = 0.5f;
            GL11.glScalef((float)scale, (float)scale, (float)1.0f);
            strAlign = strAlign + " (" + renderFaction.factionName() + "...)";
        }
        int x = -MathHelper.floor_double((double)((double)(fr.getStringWidth(strAlign) + 18) / 2.0));
        int y = -16;
        if (showAlign) {
            this.bindTexture(LOTRClientProxy.alignmentTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            LOTRTickHandlerClient.drawTexturedModalRect(x, y - 5, 0, 36, 16, 16);
            LOTRTickHandlerClient.drawAlignmentText(fr, x + 18, y, strAlign, alpha);
            y += 14;
        }
        if (showTitle) {
            x = -MathHelper.floor_double((double)((double)fr.getStringWidth(title) / 2.0));
            if (showAlign) {
                LOTRTickHandlerClient.drawAlignmentText(fr, x, y, title, alpha);
            } else {
                LOTRTickHandlerClient.drawConquestText(fr, x, y, title, negativeConq, alpha);
            }
            y += 16;
        }
        if (showConq) {
            x = -MathHelper.floor_double((double)((double)(fr.getStringWidth(strConq) + 18) / 2.0));
            this.bindTexture(LOTRClientProxy.alignmentTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            LOTRTickHandlerClient.drawTexturedModalRect(x, y - 5, negativeConq ? 16 : 0, 228, 16, 16);
            LOTRTickHandlerClient.drawConquestText(fr, x + 18, y, strConq, negativeConq, alpha);
        }
        GL11.glPopMatrix();
    }
}

