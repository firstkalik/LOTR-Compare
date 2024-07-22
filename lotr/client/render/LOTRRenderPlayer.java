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
 *  net.minecraft.util.AxisAlignedBB
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
import java.util.Random;
import lotr.client.LOTRClientProxy;
import lotr.client.LOTRTickHandlerClient;
import lotr.client.render.LOTRRenderShield;
import lotr.common.LOTRCapes;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class LOTRRenderPlayer {
    private Minecraft mc = Minecraft.getMinecraft();
    private RenderManager renderManager = RenderManager.instance;
    private ModelBiped playerModel = new ModelBiped(0.0f);

    public LOTRRenderPlayer() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void preRenderSpecials(RenderPlayerEvent.Specials.Pre event) {
        this.renderCapes(event);
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

    private void renderCapes(RenderPlayerEvent.Specials.Pre event) {
        LOTRCapes cape;
        EntityPlayer entityplayer = event.entityPlayer;
        float tick = event.partialRenderTick;
        if (!entityplayer.isInvisibleToPlayer((EntityPlayer)this.mc.thePlayer) && !entityplayer.getHideCape() && event.renderCape && (cape = LOTRLevelData.getData(entityplayer).getCape()) != null && LOTRLevelData.getData(entityplayer).getShield() == null) {
            this.mc.getTextureManager().bindTexture(cape.capeTexture);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
            double d = entityplayer.field_71091_bM + (entityplayer.field_71094_bP - entityplayer.field_71091_bM) * (double)tick - (entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)tick);
            double d1 = entityplayer.field_71096_bN + (entityplayer.field_71095_bQ - entityplayer.field_71096_bN) * (double)tick - (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)tick);
            double d2 = entityplayer.field_71097_bO + (entityplayer.field_71085_bR - entityplayer.field_71097_bO) * (double)tick - (entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)tick);
            float f6 = entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * tick;
            double d3 = MathHelper.sin((float)(f6 * 3.1415927f / 180.0f));
            double d4 = -MathHelper.cos((float)(f6 * 3.1415927f / 180.0f));
            float f7 = (float)d1 * 10.0f;
            if (f7 < -6.0f) {
                f7 = -6.0f;
            }
            if (f7 > 32.0f) {
                f7 = 32.0f;
            }
            float f8 = (float)(d * d3 + d2 * d4) * 100.0f;
            float f9 = (float)(d * d4 - d2 * d3) * 100.0f;
            if (f8 < 0.0f) {
                f8 = 0.0f;
            }
            float f10 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * tick;
            f7 += MathHelper.sin((float)((entityplayer.prevDistanceWalkedModified + (entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified) * tick) * 6.0f)) * 32.0f * f10;
            if (entityplayer.isSneaking()) {
                f7 += 25.0f;
            }
            GL11.glRotatef((float)(6.0f + f8 / 2.0f + f7), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(f9 / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(-f9 / 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.playerModel.renderCloak(0.0625f);
            GL11.glPopMatrix();
            if (cape == LOTRCapes.ALIGNMENT_GALADHRIM && (entityplayer.isSprinting() || !entityplayer.onGround && (Math.abs(entityplayer.motionX) > 0.0 || Math.abs(entityplayer.motionY) > 0.0 || Math.abs(entityplayer.motionZ) > 0.0)) && entityplayer.getRNG().nextInt(4) == 0) {
                double d10 = entityplayer.posX + (entityplayer.getRNG().nextDouble() - 0.5) * (double)entityplayer.width;
                double d11 = entityplayer.boundingBox.minY + (double)entityplayer.height * 0.5;
                double d12 = entityplayer.posZ + (entityplayer.getRNG().nextDouble() - 0.5) * (double)entityplayer.width;
                double d13 = entityplayer.motionX * -0.6;
                double d14 = entityplayer.motionY * -0.6;
                double d15 = entityplayer.motionZ * -0.6;
                LOTRMod.proxy.spawnParticle("leafGold_" + (40 + entityplayer.getRNG().nextInt(20)), d10, d11, d12, d13, d14, d15);
            }
            event.renderCape = false;
        }
    }

    @SubscribeEvent
    public void postRender(RenderPlayerEvent.Post event) {
        EntityPlayer entityplayer = event.entityPlayer;
        float tick = event.partialRenderTick;
        double d0 = RenderManager.renderPosX;
        double d1 = RenderManager.renderPosY;
        double d2 = RenderManager.renderPosZ;
        float f0 = (float)entityplayer.lastTickPosX + ((float)entityplayer.posX - (float)entityplayer.lastTickPosX) * tick;
        float f1 = (float)entityplayer.lastTickPosY + ((float)entityplayer.posY - (float)entityplayer.lastTickPosY) * tick;
        float f2 = (float)entityplayer.lastTickPosZ + ((float)entityplayer.posZ - (float)entityplayer.lastTickPosZ) * tick;
        float fr0 = f0 - (float)d0;
        float fr1 = f1 - (float)d1;
        float fr2 = f2 - (float)d2;
        float yOffset = entityplayer.isPlayerSleeping() ? -1.5f : 0.0f;
        float f = yOffset;
        if (this.shouldRenderAlignment(entityplayer) && (this.mc.theWorld.provider instanceof LOTRWorldProvider || LOTRConfig.alwaysShowAlignment)) {
            float range;
            float f3;
            LOTRPlayerData clientPD = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer);
            LOTRPlayerData otherPD = LOTRLevelData.getData(entityplayer);
            float alignment = otherPD.getAlignment(clientPD.getViewingFaction());
            double dist = entityplayer.getDistanceSqToEntity((Entity)this.renderManager.livingPlayer);
            if (dist < (double)(f3 * (range = RendererLivingEntity.NAME_TAG_RANGE))) {
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

