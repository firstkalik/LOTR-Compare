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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiUpdates
extends GuiButton {
    private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation("lotr:gui/updates.png");
    private static final ResourceLocation HOVER_TEXTURE = new ResourceLocation("lotr:gui/updates_hover.png");

    public LOTRGuiUpdates(int id, int x, int y, String name) {
        super(id, x, y, 18, 18, name);
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            ResourceLocation texture = this.field_146123_n ? HOVER_TEXTURE : NORMAL_TEXTURE;
            mc.getTextureManager().bindTexture(texture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);
            int textColor = 14737632;
            if (this.packedFGColour != 0) {
                textColor = this.packedFGColour;
            } else if (!this.enabled) {
                textColor = 10526880;
            } else if (this.field_146123_n) {
                textColor = 16777120;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, textColor);
        }
    }
}

