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

import lotr.client.gui.LOTRGuiFactions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonFactionsPage
extends GuiButton {
    private boolean leftOrRight;

    public LOTRGuiButtonFactionsPage(int i, int x, int y, boolean flag) {
        super(i, x, y, 16, 16, "");
        this.leftOrRight = flag;
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(LOTRGuiFactions.factionsTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0 + k * 16, this.leftOrRight ? 176 : 160, this.width, this.height);
            this.mouseDragged(mc, i, j);
            if (this.enabled) {
                FontRenderer fr = mc.fontRenderer;
                int stringBorder = 0;
                int stringY = this.yPosition + this.height / 2 - fr.FONT_HEIGHT / 2;
                if (this.leftOrRight) {
                    String s = "->";
                    fr.drawString(s, this.xPosition - stringBorder - fr.getStringWidth(s), stringY, 0);
                } else {
                    String s = "<-";
                    fr.drawString(s, this.xPosition + this.width + stringBorder, stringY, 0);
                }
            }
        }
    }
}

