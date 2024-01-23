/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.opengl.GL11;

public abstract class LOTRGuiScreenBase
extends GuiScreen {
    public void updateScreen() {
        super.updateScreen();
        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead) {
            this.mc.thePlayer.closeScreen();
        }
    }

    protected void keyTyped(char c, int i) {
        if (i == 1 || i == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
            this.mc.thePlayer.closeScreen();
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void drawCenteredString(String s, int x, int y, int c) {
        this.fontRendererObj.drawString(s, x - this.fontRendererObj.getStringWidth(s) / 2, y, c);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
        this.drawTexturedModalRect(x, y, u, v, width, height, 256);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, int imageWidth) {
        LOTRGuiScreenBase.drawTexturedModalRectFloat(x, y, u, v, width, height, imageWidth, this.zLevel);
    }

    public void drawTexturedModalRectFloat(float x, float y, int u, int v, float width, float height) {
        this.drawTexturedModalRectFloat(x, y, u, v, width, height, 256);
    }

    public void drawTexturedModalRectFloat(float x, float y, int u, int v, float width, float height, int imageWidth) {
        LOTRGuiScreenBase.drawTexturedModalRectFloat(x, y, u, v, width, height, imageWidth, this.zLevel);
    }

    public static void drawTexturedModalRectFloat(double x, double y, double u, double v, double width, double height, int imageWidth, float z) {
        float f = 1.0f / (float)imageWidth;
        float f1 = 1.0f / (float)imageWidth;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0.0, y + height, (double)z, (u + 0.0) * (double)f, (v + height) * (double)f1);
        tessellator.addVertexWithUV(x + width, y + height, (double)z, (u + width) * (double)f, (v + height) * (double)f1);
        tessellator.addVertexWithUV(x + width, y + 0.0, (double)z, (u + width) * (double)f, (v + 0.0) * (double)f1);
        tessellator.addVertexWithUV(x + 0.0, y + 0.0, (double)z, (u + 0.0) * (double)f, (v + 0.0) * (double)f1);
        tessellator.draw();
    }

    public static void drawFloatRect(float x0, float y0, float x1, float y1, int color) {
        float temp;
        if (x0 < x1) {
            temp = x0;
            x0 = x1;
            x1 = temp;
        }
        if (y0 < y1) {
            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)x0, (double)y1, 0.0);
        tessellator.addVertex((double)x1, (double)y1, 0.0);
        tessellator.addVertex((double)x1, (double)y0, 0.0);
        tessellator.addVertex((double)x0, (double)y0, 0.0);
        tessellator.draw();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }
}

