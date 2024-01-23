/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import lotr.client.LOTRClientProxy;
import lotr.client.LOTRTickHandlerClient;
import lotr.client.render.LOTRRenderShield;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRShields;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.world.LOTRWorldProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class LOTRRenderPlayer {
    private Minecraft mc = Minecraft.getMinecraft();
    private RenderManager renderManager = RenderManager.instance;

    public LOTRRenderPlayer() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void preRenderSpecials(RenderPlayerEvent.Specials.Pre event) {
        EntityPlayer entityplayer = event.entityPlayer;
        float tick = event.partialRenderTick;
        LOTRShields shield = LOTRLevelData.getData(entityplayer).getShield();
        if (shield != null) {
            if (!entityplayer.isInvisible()) {
                LOTRRenderShield.renderShield(shield, (EntityLivingBase)entityplayer, event.renderer.modelBipedMain);
            } else if (!entityplayer.isInvisibleToPlayer((EntityPlayer)this.mc.thePlayer)) {
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
                GL11.glDepthMask((boolean)false);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                LOTRRenderShield.renderShield(shield, (EntityLivingBase)entityplayer, event.renderer.modelBipedMain);
                GL11.glDisable((int)3042);
                GL11.glAlphaFunc((int)516, (float)0.1f);
                GL11.glPopMatrix();
                GL11.glDepthMask((boolean)true);
            }
        }
    }

    @SubscribeEvent
    public void postRender(RenderPlayerEvent.Post event) {
        float yOffset;
        EntityPlayer entityplayer = event.entityPlayer;
        float tick = event.partialRenderTick;
        double d0 = RenderManager.renderPosX;
        double d1 = RenderManager.renderPosY;
        double d2 = RenderManager.renderPosZ;
        float f0 = (float)entityplayer.lastTickPosX + (float)(entityplayer.posX - entityplayer.lastTickPosX) * tick;
        float f1 = (float)entityplayer.lastTickPosY + (float)(entityplayer.posY - entityplayer.lastTickPosY) * tick;
        float f2 = (float)entityplayer.lastTickPosZ + (float)(entityplayer.posZ - entityplayer.lastTickPosZ) * tick;
        float fr0 = f0 - (float)d0;
        float fr1 = f1 - (float)d1;
        float fr2 = f2 - (float)d2;
        float f = yOffset = entityplayer.isPlayerSleeping() ? -1.5f : 0.0f;
        if (this.shouldRenderAlignment(entityplayer) && (this.mc.theWorld.provider instanceof LOTRWorldProvider || LOTRConfig.alwaysShowAlignment)) {
            float range;
            LOTRPlayerData clientPD = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer);
            LOTRPlayerData otherPD = LOTRLevelData.getData(entityplayer);
            float alignment = otherPD.getAlignment(clientPD.getViewingFaction());
            double dist = entityplayer.getDistanceSqToEntity((Entity)this.renderManager.livingPlayer);
            if (dist < (double)((range = RendererLivingEntity.NAME_TAG_RANGE) * range)) {
                FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)fr0, (float)fr1, (float)fr2);
                GL11.glTranslatef((float)0.0f, (float)(entityplayer.height + 0.6f + yOffset), (float)0.0f);
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)this.renderManager.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
                float scale = 0.025f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glDisable((int)2896);
                GL11.glDepthMask((boolean)false);
                GL11.glDisable((int)2929);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                String sAlign = LOTRAlignmentValues.formatAlignForDisplay(alignment);
                this.mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
                LOTRTickHandlerClient.drawTexturedModalRect(-MathHelper.floor_double((double)((double)(fr.getStringWidth(sAlign) + 18) / 2.0)), -19.0, 0, 36, 16, 16);
                LOTRTickHandlerClient.drawAlignmentText(fr, 18 - MathHelper.floor_double((double)((double)(fr.getStringWidth(sAlign) + 18) / 2.0)), -12, sAlign, 1.0f);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2929);
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2896);
                GL11.glDisable((int)32826);
                GL11.glPopMatrix();
            }
        }
        if (this.shouldRenderFellowPlayerHealth(entityplayer)) {
            LOTRNPCRendering.renderHealthBar((EntityLivingBase)entityplayer, fr0, fr1, fr2, new int[]{16375808, 12006707}, null);
        }
    }

    private boolean shouldRenderPlayerHUD(EntityPlayer entityplayer) {
        if (Minecraft.isGuiEnabled()) {
            return entityplayer != this.renderManager.livingPlayer && !entityplayer.isSneaking() && !entityplayer.isInvisibleToPlayer((EntityPlayer)this.mc.thePlayer);
        }
        return false;
    }

    private boolean shouldRenderAlignment(EntityPlayer entityplayer) {
        if (LOTRConfig.displayAlignmentAboveHead && this.shouldRenderPlayerHUD(entityplayer)) {
            if (LOTRLevelData.getData(entityplayer).getHideAlignment()) {
                String playerName = entityplayer.getCommandSenderName();
                List<LOTRFellowshipClient> fellowships = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getClientFellowships();
                for (LOTRFellowshipClient fs : fellowships) {
                    if (!fs.isPlayerIn(playerName)) continue;
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean shouldRenderFellowPlayerHealth(EntityPlayer entityplayer) {
        if (LOTRConfig.fellowPlayerHealthBars && this.shouldRenderPlayerHUD(entityplayer)) {
            List<LOTRFellowshipClient> fellowships = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getClientFellowships();
            for (LOTRFellowshipClient fs : fellowships) {
                if (!fs.isPlayerIn(entityplayer.getCommandSenderName())) continue;
                return true;
            }
        }
        return false;
    }
}

