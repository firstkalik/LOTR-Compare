/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.ArrayList;
import java.util.List;
import lotr.client.gui.LOTRGuiButtonShieldsArrows;
import lotr.client.gui.LOTRGuiMenuBase;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketSelectCape;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiCapes
extends LOTRGuiMenuBase {
    private static ModelBiped playerModel = new ModelBiped();
    public static int playerModelRotation = 0;
    private LOTRCapes.CapeType currentCapeType;
    public static int currentCapeTypeID;
    private LOTRCapes currentCape;
    public static int currentCapeID;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new LOTRGuiButtonShieldsArrows(0, true, this.guiLeft + this.xSize / 2 - 64, this.guiTop + 210));
        this.buttonList.add(new GuiButton(1, this.guiLeft + this.xSize / 2 - 40, this.guiTop + 195, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.capes.select")));
        this.buttonList.add(new LOTRGuiButtonShieldsArrows(2, false, this.guiLeft + this.xSize / 2 + 44, this.guiTop + 210));
        this.buttonList.add(new GuiButton(3, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 240, 160, 20, ""));
        this.buttonList.add(new GuiButton(4, this.guiLeft + this.xSize / 2 - 40, this.guiTop + 219, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.capes.remove")));
        this.updateCurrentCape();
    }

    private void updateCurrentCape() {
        this.currentCapeType = LOTRCapes.CapeType.values()[currentCapeTypeID];
        this.currentCape = this.currentCapeType.list.get(currentCapeID);
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        String s = StatCollector.translateToLocal((String)"lotr.gui.capes.title");
        this.drawCenteredString(s, this.guiLeft + 100, this.guiTop - 30, 16777215);
        GL11.glEnable((int)2903);
        RenderHelper.enableStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glTranslatef((float)(this.guiLeft + this.xSize / 2), (float)((float)this.guiTop + 45.0f), (float)50.0f);
        float scale = 55.0f;
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)2.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.mc.getTextureManager().bindTexture(this.mc.thePlayer.getLocationSkin());
        playerModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        this.mc.getTextureManager().bindTexture(this.currentCape.capeTexture);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)-10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        playerModel.renderCloak(0.0625f);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        OpenGlHelper.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GL11.glDisable((int)3553);
        OpenGlHelper.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int x = this.guiLeft + this.xSize / 2;
        int y = this.guiTop + 145;
        s = this.currentCape.getCapeName();
        this.drawCenteredString(s, x, y, 16777215);
        y += this.fontRendererObj.FONT_HEIGHT * 2;
        List desc = this.fontRendererObj.listFormattedStringToWidth(this.currentCape.getCapeDesc(), 200);
        for (int l = 0; l < desc.size(); ++l) {
            s = (String)desc.get(l);
            this.drawCenteredString(s, x, y, 16777215);
            y += this.fontRendererObj.FONT_HEIGHT;
        }
        ((GuiButton)this.buttonList.get((int)1)).enabled = currentCapeID > 0;
        ((GuiButton)this.buttonList.get((int)2)).enabled = this.currentCape.canPlayerWearCape((EntityPlayer)this.mc.thePlayer);
        ((GuiButton)this.buttonList.get((int)2)).displayString = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getCape() == this.currentCape ? StatCollector.translateToLocal((String)"lotr.gui.capes.selected") : StatCollector.translateToLocal((String)"lotr.gui.capes.select");
        ((GuiButton)this.buttonList.get((int)3)).enabled = currentCapeID < this.currentCapeType.list.size() - 1;
        ((GuiButton)this.buttonList.get((int)4)).displayString = this.currentCapeType.getDisplayName();
        ((GuiButton)this.buttonList.get((int)5)).enabled = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getCape() != null;
        super.drawScreen(i, j, f);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id == 0) {
                if (currentCapeID > 0) {
                    --currentCapeID;
                    this.updateCurrentCape();
                }
            } else if (button.id == 1) {
                this.updateCurrentCape();
                LOTRPacketSelectCape packet = new LOTRPacketSelectCape(this.currentCape);
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            } else if (button.id == 2) {
                if (currentCapeID < this.currentCapeType.list.size() - 1) {
                    ++currentCapeID;
                    this.updateCurrentCape();
                }
            } else if (button.id == 3) {
                currentCapeTypeID = currentCapeTypeID < LOTRCapes.CapeType.values().length - 1 ? (currentCapeTypeID = currentCapeTypeID + 1) : 0;
                currentCapeID = 0;
                this.updateCurrentCape();
            } else if (button.id == 4) {
                this.updateCurrentCape();
                LOTRPacketSelectCape packet = new LOTRPacketSelectCape(null);
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            } else {
                super.actionPerformed(button);
            }
        }
    }

    static {
        LOTRGuiCapes.playerModel.isChild = false;
    }
}

