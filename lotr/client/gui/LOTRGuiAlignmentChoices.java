/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotr.client.LOTRClientProxy;
import lotr.client.gui.LOTRGuiButtonRedBook;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.network.LOTRPacketAlignmentChoices;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiAlignmentChoices
extends LOTRGuiScreenBase {
    private int xSize = 430;
    private int ySize = 250;
    private int guiLeft;
    private int guiTop;
    private int page = 0;
    private GuiButton buttonConfirm;
    private Map<LOTRFaction, LOTRGuiButtonRedBook> facButtons = new HashMap<LOTRFaction, LOTRGuiButtonRedBook>();
    private Map<LOTRGuiButtonRedBook, LOTRFaction> buttonFacs = new HashMap<LOTRGuiButtonRedBook, LOTRFaction>();
    private Set<LOTRFaction> setZeroFacs = new HashSet<LOTRFaction>();

    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttonConfirm = new LOTRGuiButtonRedBook(0, this.guiLeft + this.xSize / 2 - 100, this.guiTop + this.ySize - 30, 200, 20, "BUTTON");
        this.buttonList.add(this.buttonConfirm);
        for (LOTRFaction fac : LOTRFaction.getPlayableAlignmentFactions()) {
            LOTRGuiButtonRedBook button = new LOTRGuiButtonRedBook(0, 0, 0, 80, 20, "");
            this.facButtons.put(fac, button);
            this.buttonFacs.put(button, fac);
            this.buttonList.add(button);
            button.enabled = false;
            button.visible = false;
        }
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        Gui.drawRect((int)this.guiLeft, (int)this.guiTop, (int)(this.guiLeft + this.xSize), (int)(this.guiTop + this.ySize), (int)-5756117);
        Gui.drawRect((int)(this.guiLeft + 2), (int)(this.guiTop + 2), (int)(this.guiLeft + this.xSize - 2), (int)(this.guiTop + this.ySize - 2), (int)-1847889);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
        int warnIconSize = 32;
        this.drawTexturedModalRect(this.guiLeft - warnIconSize, this.guiTop, 16, 128, warnIconSize, warnIconSize);
        this.drawTexturedModalRect(this.guiLeft + this.xSize, this.guiTop, 16, 128, warnIconSize, warnIconSize);
        LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer);
        int textColor = 8019267;
        int border = 7;
        int lineWidth = this.xSize - border * 2;
        int x = this.guiLeft + border;
        int y = this.guiTop + border;
        if (this.page == 0) {
            String s = "Hello! You are reading this because you earned alignment before Update 35.";
            this.fontRendererObj.drawSplitString(s, x, y, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            s = "This update introduces 'Enemy Alignment Draining'. If you have + alignment with two Mortal Enemy factions (more severe than Enemy), both alignments will slowly drain over time until one reaches 0.";
            this.fontRendererObj.drawSplitString(s, x, y += this.fontRendererObj.FONT_HEIGHT, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            s = "You can still hold + alignment with Mortal Enemies in the short term if you work quickly. But long-term public friendship with Gondor and Mordor together is not in the spirit of Tolkien's Middle-earth.";
            this.fontRendererObj.drawSplitString(s, x, y += this.fontRendererObj.FONT_HEIGHT, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            s = "Because you have played before, you have the option to set any unwanted alignments to zero immediately, to prevent draining high alignment from factions you care about. This will also help if you want to Pledge to a faction.";
            this.fontRendererObj.drawSplitString(s, x, y += this.fontRendererObj.FONT_HEIGHT, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            s = "Note that if you are a server admin or playing in singleplayer you can toggle this feature in the LOTR mod config. However, players who wish to Pledge will still need to reduce Mortal Enemy alignments to zero.";
            this.fontRendererObj.drawSplitString((Object)EnumChatFormatting.ITALIC + s, x, y += this.fontRendererObj.FONT_HEIGHT, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            this.buttonConfirm.displayString = "View your alignments";
        } else if (this.page == 1) {
            String s = "Choose which alignments to set to zero. You can choose as many or as few as you like, but you can only choose once. Alignments which will drain due to a conflict are in RED - this will update as you select unwanted factions.";
            this.fontRendererObj.drawSplitString(s, x, y, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            s = "If you are hoping to Pledge to a faction, you will need to have 0 or - alignment with all of its Mortal Enemies.";
            this.fontRendererObj.drawSplitString(s, x, y += this.fontRendererObj.FONT_HEIGHT, lineWidth, textColor);
            y += this.fontRendererObj.FONT_HEIGHT * this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size();
            int buttonX = this.guiLeft + border;
            int buttonY = y += this.fontRendererObj.FONT_HEIGHT;
            for (LOTRFaction fac : LOTRFaction.getPlayableAlignmentFactions()) {
                LOTRGuiButtonRedBook button = this.facButtons.get((Object)fac);
                button.visible = true;
                button.enabled = false;
                button.displayString = "";
                button.xPosition = buttonX;
                button.yPosition = buttonY;
                if ((buttonX += button.width + 4) >= this.guiLeft + this.xSize - border) {
                    buttonX = this.guiLeft + border;
                    buttonY += 24;
                }
                float align = pd.getAlignment(fac);
                String facName = fac.factionName();
                String alignS = LOTRAlignmentValues.formatAlignForDisplay(align);
                String status = "Not draining";
                button.enabled = false;
                if (align > 0.0f) {
                    boolean isDraining = this.isFactionConflicting(pd, fac, false);
                    boolean willDrain = this.isFactionConflicting(pd, fac, true);
                    if (isDraining) {
                        if (this.setZeroFacs.contains((Object)fac)) {
                            status = "Setting to zero";
                            button.enabled = true;
                            Gui.drawRect((int)(button.xPosition - 1), (int)(button.yPosition - 1), (int)(button.xPosition + button.width + 1), (int)(button.yPosition + button.height + 1), (int)-1);
                        } else if (willDrain) {
                            status = "Draining";
                            button.enabled = true;
                            Gui.drawRect((int)(button.xPosition - 1), (int)(button.yPosition - 1), (int)(button.xPosition + button.width + 1), (int)(button.yPosition + button.height + 1), (int)-62464);
                        } else {
                            status = "Will not drain after CONFIRM";
                            button.enabled = false;
                        }
                    }
                }
                float buttonTextScale = 0.5f;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)100.0f);
                GL11.glScalef((float)buttonTextScale, (float)buttonTextScale, (float)1.0f);
                int buttonTextX = (int)((float)(button.xPosition + button.width / 2) / buttonTextScale);
                int buttonTextY = (int)((float)button.yPosition / buttonTextScale) + 4;
                this.drawCenteredString(facName, buttonTextX, buttonTextY, textColor);
                this.drawCenteredString(alignS, buttonTextX, buttonTextY += this.fontRendererObj.FONT_HEIGHT, textColor);
                this.drawCenteredString(status, buttonTextX, buttonTextY += this.fontRendererObj.FONT_HEIGHT, textColor);
                GL11.glPopMatrix();
                if (!button.func_146115_a() || align <= 0.0f || this.setZeroFacs.contains((Object)fac) || !this.isFactionConflicting(pd, fac, true)) continue;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)100.0f);
                for (LOTRFaction otherFac : LOTRFaction.getPlayableAlignmentFactions()) {
                    if (fac == otherFac || this.setZeroFacs.contains((Object)otherFac) || !pd.doFactionsDrain(fac, otherFac) || pd.getAlignment(otherFac) <= 0.0f) continue;
                    LOTRGuiButtonRedBook otherButton = this.facButtons.get((Object)otherFac);
                    int x1 = button.xPosition + button.width / 2;
                    int x2 = otherButton.xPosition + otherButton.width / 2;
                    int y1 = button.yPosition + button.height / 2;
                    int y2 = otherButton.yPosition + otherButton.height / 2;
                    GL11.glDisable((int)3553);
                    Tessellator tess = Tessellator.instance;
                    tess.startDrawing(1);
                    GL11.glPushAttrib((int)2849);
                    GL11.glLineWidth((float)4.0f);
                    tess.setColorOpaque_I(-62464);
                    tess.addVertex((double)x1, (double)y1, 0.0);
                    tess.addVertex((double)x2, (double)y2, 0.0);
                    tess.draw();
                    GL11.glPopAttrib();
                    GL11.glEnable((int)3553);
                }
                GL11.glPopMatrix();
            }
            s = "If you do not want to choose now you can close this screen with '" + GameSettings.getKeyDisplayString((int)this.mc.gameSettings.keyBindInventory.getKeyCode()) + "' and it will appear again when you log in. Remember - you can only choose once.";
            y = this.buttonConfirm.yPosition - this.fontRendererObj.FONT_HEIGHT * (this.fontRendererObj.listFormattedStringToWidth(s, lineWidth).size() + 1);
            this.fontRendererObj.drawSplitString(s, x, y, lineWidth, textColor);
            this.buttonConfirm.displayString = "CONFIRM - set " + this.setZeroFacs.size() + " alignments to zero";
        }
        super.drawScreen(i, j, f);
    }

    private boolean isFactionConflicting(LOTRPlayerData pd, LOTRFaction fac, boolean accountForSelection) {
        for (LOTRFaction otherFac : LOTRFaction.getPlayableAlignmentFactions()) {
            if (fac == otherFac || accountForSelection && this.setZeroFacs.contains((Object)otherFac) || !pd.doFactionsDrain(fac, otherFac) || pd.getAlignment(otherFac) <= 0.0f) continue;
            return true;
        }
        return false;
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button == this.buttonConfirm) {
                if (this.page == 0) {
                    this.page = 1;
                } else if (this.page == 1) {
                    LOTRPacketAlignmentChoices packet = new LOTRPacketAlignmentChoices(this.setZeroFacs);
                    LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
                    this.mc.thePlayer.closeScreen();
                }
            } else if (this.buttonFacs.containsKey((Object)button)) {
                LOTRFaction fac = this.buttonFacs.get((Object)button);
                if (this.isFactionConflicting(LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer), fac, false)) {
                    if (this.setZeroFacs.contains((Object)fac)) {
                        this.setZeroFacs.remove((Object)fac);
                    } else {
                        this.setZeroFacs.add(fac);
                    }
                }
            }
        }
        super.actionPerformed(button);
    }
}

