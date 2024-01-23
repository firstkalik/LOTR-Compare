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

import lotr.client.gui.LOTRGuiFellowships;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonFsOption
extends GuiButton {
    private int iconU;
    private int iconV;

    public LOTRGuiButtonFsOption(int i, int x, int y, int u, int v, String s) {
        super(i, x, y, 16, 16, s);
        this.iconU = u;
        this.iconV = v;
    }

    public void setIconUV(int u, int v) {
        this.iconU = u;
        this.iconV = v;
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(LOTRGuiFellowships.iconsTextures);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.iconU, this.iconV + (this.field_146123_n ? this.height : 0), this.width, this.height);
            this.mouseDragged(mc, i, j);
        }
    }
}

