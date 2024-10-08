/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.client.LOTRTickHandlerClient;
import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.quest.LOTRMiniQuest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiMiniquestTracker
extends Gui {
    private static ResourceLocation guiTexture = new ResourceLocation("lotr:gui/quest/tracker.png");
    private static RenderItem renderItem = new RenderItem();
    private int width;
    private int height;
    private int barX = 16;
    private int barY = 10;
    private int barWidth = 90;
    private int barHeight = 15;
    private int barEdge = 2;
    private int iconWidth = 20;
    private int iconHeight = 20;
    private int gap = 4;
    private LOTRMiniQuest trackedQuest;
    private boolean holdingComplete;
    private int completeTime;

    public void drawTracker(Minecraft mc, EntityPlayer entityplayer) {
        ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        this.width = resolution.getScaledWidth();
        this.height = resolution.getScaledHeight();
        FontRenderer fr = mc.fontRenderer;
        boolean flip = LOTRConfig.trackingQuestRight;
        if (entityplayer != null && this.trackedQuest != null) {
            float[] questRGB = this.trackedQuest.getQuestColorComponents();
            ItemStack itemstack = this.trackedQuest.getQuestIcon();
            String objective = this.trackedQuest.getQuestObjective();
            String progress = this.trackedQuest.getQuestProgressShorthand();
            float completion = this.trackedQuest.getCompletionFactor();
            boolean failed = this.trackedQuest.isFailed();
            boolean complete = this.trackedQuest.isCompleted();
            if (failed) {
                objective = this.trackedQuest.getQuestFailureShorthand();
            } else if (complete) {
                objective = StatCollector.translateToLocal((String)"lotr.gui.redBook.mq.diary.complete");
            }
            int x = this.barX;
            int y = this.barY;
            if (flip) {
                x = this.width - this.barX - this.iconWidth;
            }
            GL11.glEnable((int)3008);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            mc.getTextureManager().bindTexture(guiTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(x, y, 0, 0, this.iconWidth, this.iconHeight);
            int iconX = x + (this.iconWidth - 16) / 2;
            int iconY = y + (this.iconHeight - 16) / 2;
            x = flip ? (x = x - (this.barWidth + this.gap)) : (x = x + (this.iconWidth + this.gap));
            int meterWidth = this.barWidth - this.barEdge * 2;
            meterWidth = Math.round((float)meterWidth * completion);
            mc.getTextureManager().bindTexture(guiTexture);
            GL11.glColor4f((float)questRGB[0], (float)questRGB[1], (float)questRGB[2], (float)1.0f);
            this.drawTexturedModalRect(x + this.barEdge, y, this.iconWidth + this.barEdge, this.barHeight, meterWidth, this.barHeight);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(x, y, this.iconWidth, 0, this.barWidth, this.barHeight);
            LOTRTickHandlerClient.drawAlignmentText(fr, x + this.barWidth / 2 - fr.getStringWidth(progress) / 2, y + this.barHeight - this.barHeight / 2 - fr.FONT_HEIGHT / 2, progress, 1.0f);
            fr.drawSplitString(objective, x, y += this.barHeight + this.gap, this.barWidth, 16777215);
            GL11.glDisable((int)3042);
            GL11.glDisable((int)3008);
            if (itemstack != null) {
                RenderHelper.enableGUIStandardItemLighting();
                GL11.glDisable((int)2896);
                GL11.glEnable((int)32826);
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2884);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                renderItem.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemstack, iconX, iconY);
                GL11.glDisable((int)2896);
            }
        }
    }

    public void update(Minecraft mc, EntityPlayer entityplayer) {
        if (entityplayer == null) {
            this.trackedQuest = null;
        } else {
            if (this.trackedQuest != null && this.trackedQuest.isCompleted() && !this.holdingComplete) {
                this.completeTime = 200;
                this.holdingComplete = true;
            }
            LOTRMiniQuest currentTrackedQuest = LOTRLevelData.getData(entityplayer).getTrackingMiniQuest();
            if (this.completeTime > 0 && currentTrackedQuest == null) {
                --this.completeTime;
            } else {
                this.trackedQuest = currentTrackedQuest;
                this.holdingComplete = false;
            }
        }
    }

    public void setTrackedQuest(LOTRMiniQuest quest) {
        this.trackedQuest = quest;
    }
}

