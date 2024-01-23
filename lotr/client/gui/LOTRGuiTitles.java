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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  org.apache.commons.lang3.tuple.Pair
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotr.client.LOTRReflectionClient;
import lotr.client.gui.LOTRGuiMenuBase;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRTitle;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketSelectTitle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class LOTRGuiTitles
extends LOTRGuiMenuBase {
    private LOTRTitle.PlayerTitle currentTitle;
    private List<LOTRTitle> displayedTitles = new ArrayList<LOTRTitle>();
    private static final int maxDisplayedTitles = 12;
    private Map<LOTRTitle, Pair<Boolean, Pair<Integer, Integer>>> displayedTitleInfo = new HashMap<LOTRTitle, Pair<Boolean, Pair<Integer, Integer>>>();
    private LOTRTitle selectedTitle;
    private EnumChatFormatting selectedColor = EnumChatFormatting.WHITE;
    private int colorBoxWidth = 8;
    private int colorBoxGap = 4;
    private Map<EnumChatFormatting, Pair<Integer, Integer>> displayedColorBoxes = new HashMap<EnumChatFormatting, Pair<Integer, Integer>>();
    private GuiButton selectButton;
    private GuiButton removeButton;
    private float currentScroll = 0.0f;
    private boolean isScrolling = false;
    private boolean wasMouseDown;
    private int scrollBarWidth = 11;
    private int scrollBarHeight = 144;
    private int scrollBarX = 197 - (this.scrollBarWidth - 1) / 2;
    private int scrollBarY = 30;
    private int scrollWidgetWidth = 11;
    private int scrollWidgetHeight = 8;

    @Override
    public void initGui() {
        this.xSize = 256;
        super.initGui();
        this.selectButton = new GuiButton(0, this.guiLeft + this.xSize / 2 - 10 - 80, this.guiTop + 220, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.titles.select"));
        this.buttonList.add(this.selectButton);
        this.removeButton = new GuiButton(1, this.guiLeft + this.xSize / 2 + 10, this.guiTop + 220, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.titles.remove"));
        this.buttonList.add(this.removeButton);
        this.updateScreen();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.currentTitle = LOTRLevelData.getData((EntityPlayer)this.mc.thePlayer).getPlayerTitle();
        this.displayedTitles.clear();
        ArrayList<LOTRTitle> availableTitles = new ArrayList<LOTRTitle>();
        ArrayList<LOTRTitle> unavailableTitles = new ArrayList<LOTRTitle>();
        for (LOTRTitle title : LOTRTitle.allTitles) {
            if (title.canPlayerUse((EntityPlayer)this.mc.thePlayer)) {
                availableTitles.add(title);
                continue;
            }
            if (!title.canDisplay((EntityPlayer)this.mc.thePlayer)) continue;
            unavailableTitles.add(title);
        }
        Collections.sort(availableTitles);
        Collections.sort(unavailableTitles);
        this.displayedTitles.addAll(availableTitles);
        this.displayedTitles.add(null);
        this.displayedTitles.addAll(unavailableTitles);
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.setupScrollBar(i, j);
        String s = StatCollector.translateToLocal((String)"lotr.gui.titles.title");
        this.drawCenteredString(s, this.guiLeft + this.xSize / 2, this.guiTop - 30, 16777215);
        String titleName = this.currentTitle == null ? StatCollector.translateToLocal((String)"lotr.gui.titles.currentTitle.none") : this.currentTitle.getTitle().getDisplayName();
        EnumChatFormatting currentColor = this.currentTitle == null ? EnumChatFormatting.WHITE : this.currentTitle.getColor();
        titleName = (Object)currentColor + titleName + (Object)EnumChatFormatting.RESET;
        this.drawCenteredString(StatCollector.translateToLocalFormatted((String)"lotr.gui.titles.currentTitle", (Object[])new Object[]{titleName}), this.guiLeft + this.xSize / 2, this.guiTop, 16777215);
        this.displayedTitleInfo.clear();
        int titleX = this.guiLeft + this.xSize / 2;
        int titleY = this.guiTop + 30;
        int yIncrement = 12;
        this.drawVerticalLine(titleX - 70, titleY - 1, titleY + yIncrement * 12, -1711276033);
        this.drawVerticalLine(titleX + 70 - 1, titleY - 1, titleY + yIncrement * 12, -1711276033);
        int size = this.displayedTitles.size();
        int min = 0 + Math.round(this.currentScroll * (float)(size - 12));
        int max = 11 + Math.round(this.currentScroll * (float)(size - 12));
        min = Math.max(min, 0);
        max = Math.min(max, size - 1);
        for (int index = min; index <= max; ++index) {
            boolean isCurrentTitle;
            String name;
            boolean mouseOver;
            LOTRTitle title = this.displayedTitles.get(index);
            boolean bl = isCurrentTitle = this.currentTitle != null && this.currentTitle.getTitle() == title;
            if (title != null) {
                name = title.getDisplayName();
                if (isCurrentTitle) {
                    name = "[" + name + "]";
                    name = (Object)this.currentTitle.getColor() + name;
                }
            } else {
                name = "---";
            }
            int nameWidth = this.fontRendererObj.getStringWidth(name);
            int nameHeight = this.mc.fontRenderer.FONT_HEIGHT;
            int nameXMin = titleX - nameWidth / 2;
            int nameXMax = titleX + nameWidth / 2;
            int nameYMin = titleY;
            int nameYMax = titleY + nameHeight;
            boolean bl2 = mouseOver = i >= nameXMin && i < nameXMax && j >= nameYMin && j < nameYMax;
            if (title != null) {
                this.displayedTitleInfo.put(title, (Pair<Boolean, Pair<Integer, Integer>>)Pair.of((Object)mouseOver, (Object)Pair.of((Object)titleX, (Object)titleY)));
            }
            int textColor = title != null ? (title.canPlayerUse((EntityPlayer)this.mc.thePlayer) ? (mouseOver ? 16777120 : 16777215) : (mouseOver ? 12303291 : 7829367)) : 7829367;
            this.drawCenteredString(name, titleX, titleY, textColor);
            titleY += yIncrement;
        }
        this.displayedColorBoxes.clear();
        if (this.selectedTitle != null) {
            String title = (Object)this.selectedColor + this.selectedTitle.getDisplayName();
            this.drawCenteredString(title, this.guiLeft + this.xSize / 2, this.guiTop + 185, 16777215);
            ArrayList<EnumChatFormatting> colorCodes = new ArrayList<EnumChatFormatting>();
            for (EnumChatFormatting ecf : EnumChatFormatting.values()) {
                if (!ecf.isColor()) continue;
                colorCodes.add(ecf);
            }
            int colorX = this.guiLeft + this.xSize / 2 - (this.colorBoxWidth * colorCodes.size() + this.colorBoxGap * (colorCodes.size() - 1)) / 2;
            int colorY = this.guiTop + 200;
            for (EnumChatFormatting code : colorCodes) {
                int color = LOTRReflectionClient.getFormattingColor(code);
                float[] rgb = new Color(color).getColorComponents(null);
                GL11.glColor4f((float)rgb[0], (float)rgb[1], (float)rgb[2], (float)1.0f);
                boolean mouseOver = i >= colorX && i < colorX + this.colorBoxWidth && j >= colorY && j < colorY + this.colorBoxWidth;
                GL11.glDisable((int)3553);
                this.drawTexturedModalRect(colorX, colorY + (mouseOver ? -1 : 0), 0, 0, this.colorBoxWidth, this.colorBoxWidth);
                GL11.glEnable((int)3553);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.displayedColorBoxes.put(code, (Pair<Integer, Integer>)Pair.of((Object)colorX, (Object)colorY));
                colorX += this.colorBoxWidth + this.colorBoxGap;
            }
        }
        if (this.displayedTitles.size() > 12) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            int scroll = (int)(this.currentScroll * (float)(this.scrollBarHeight - this.scrollWidgetHeight));
            int x1 = this.guiLeft + this.scrollBarX;
            int y1 = this.guiTop + this.scrollBarY + scroll;
            int x2 = x1 + this.scrollWidgetWidth;
            int y2 = y1 + this.scrollWidgetHeight;
            LOTRGuiTitles.drawRect((int)x1, (int)y1, (int)x2, (int)y2, (int)-1426063361);
        }
        this.selectButton.enabled = this.selectedTitle != null;
        this.removeButton.enabled = this.currentTitle != null;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        super.drawScreen(i, j, f);
        for (Map.Entry<LOTRTitle, Pair<Boolean, Pair<Integer, Integer>>> entry : this.displayedTitleInfo.entrySet()) {
            LOTRTitle title = entry.getKey();
            String desc = title.getDescription((EntityPlayer)this.mc.thePlayer);
            titleX = (Integer)((Pair)entry.getValue().getRight()).getLeft();
            titleY = (Integer)((Pair)entry.getValue().getRight()).getRight();
            boolean mouseOver = (Boolean)entry.getValue().getLeft();
            if (!mouseOver) continue;
            int stringWidth = 200;
            List titleLines = this.fontRendererObj.listFormattedStringToWidth(desc, stringWidth);
            int offset = 10;
            int x = i + offset;
            int y = j + offset;
            this.func_146283_a(titleLines, x, y);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    private void setupScrollBar(int i, int j) {
        boolean isMouseDown = Mouse.isButtonDown((int)0);
        int i1 = this.guiLeft + this.scrollBarX;
        int j1 = this.guiTop + this.scrollBarY;
        int i2 = i1 + this.scrollBarWidth;
        int j2 = j1 + this.scrollBarHeight;
        if (!this.wasMouseDown && isMouseDown && i >= i1 && j >= j1 && i < i2 && j < j2) {
            this.isScrolling = true;
        }
        if (!isMouseDown) {
            this.isScrolling = false;
        }
        this.wasMouseDown = isMouseDown;
        if (this.isScrolling) {
            this.currentScroll = ((float)(j - j1) - (float)this.scrollWidgetHeight / 2.0f) / ((float)(j2 - j1) - (float)this.scrollWidgetHeight);
            this.currentScroll = MathHelper.clamp_float((float)this.currentScroll, (float)0.0f, (float)1.0f);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button == this.selectButton && (this.currentTitle == null || this.selectedTitle != this.currentTitle.getTitle() || this.selectedColor != this.currentTitle.getColor())) {
                LOTRPacketSelectTitle packet = new LOTRPacketSelectTitle(new LOTRTitle.PlayerTitle(this.selectedTitle, this.selectedColor));
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            } else if (button == this.removeButton) {
                LOTRPacketSelectTitle packet = new LOTRPacketSelectTitle(null);
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            } else {
                super.actionPerformed(button);
            }
        }
    }

    protected void mouseClicked(int i, int j, int mouse) {
        if (mouse == 0) {
            for (Map.Entry<LOTRTitle, Pair<Boolean, Pair<Integer, Integer>>> entry : this.displayedTitleInfo.entrySet()) {
                LOTRTitle title = entry.getKey();
                boolean mouseOver = (Boolean)entry.getValue().getLeft();
                if (!mouseOver || !title.canPlayerUse((EntityPlayer)this.mc.thePlayer)) continue;
                this.selectedTitle = title;
                this.selectedColor = EnumChatFormatting.WHITE;
                return;
            }
            if (!this.displayedColorBoxes.isEmpty()) {
                for (Map.Entry<LOTRTitle, Object> entry : this.displayedColorBoxes.entrySet()) {
                    EnumChatFormatting color = (EnumChatFormatting)entry.getKey();
                    int colorX = (Integer)((Pair)entry.getValue()).getLeft();
                    int colorY = (Integer)((Pair)entry.getValue()).getRight();
                    if (i < colorX || i >= colorX + this.colorBoxWidth || j < colorY || j >= colorY + this.colorBoxWidth) continue;
                    this.selectedColor = color;
                    break;
                }
            }
        }
        super.mouseClicked(i, j, mouse);
    }

    public void handleMouseInput() {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        if (i != 0) {
            i = Integer.signum(i);
            int j = this.displayedTitles.size() - 12;
            this.currentScroll -= (float)i / (float)j;
            this.currentScroll = MathHelper.clamp_float((float)this.currentScroll, (float)0.0f, (float)1.0f);
        }
    }
}

