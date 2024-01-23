/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.client.gui.LOTRGuiBanner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonBanner
extends GuiButton {
    public boolean activated;
    private int iconU;
    private int iconV;

    public LOTRGuiButtonBanner(int i, int x, int y, int u, int v) {
        super(i, x, y, 16, 16, "");
        this.iconU = u;
        this.iconV = v;
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(LOTRGuiBanner.bannerTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int state = this.getHoverState(this.field_146123_n);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.iconU + state % 2 * this.width, this.iconV + state / 2 * this.height, this.width, this.height);
            this.mouseDragged(mc, i, j);
        }
    }

    public int getHoverState(boolean mouseover) {
        return (this.activated ? 0 : 2) + (mouseover ? 1 : 0);
    }
}

