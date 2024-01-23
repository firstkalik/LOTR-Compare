/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonShieldsArrows
extends GuiButton {
    private static ResourceLocation texture = new ResourceLocation("lotr:gui/widgets.png");
    private boolean leftOrRight;

    public LOTRGuiButtonShieldsArrows(int i, boolean flag, int j, int k) {
        super(i, j, k, 20, 20, "");
        this.leftOrRight = flag;
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(texture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.leftOrRight ? 0 : 20, 60 + k * 20, this.width, this.height);
        }
    }
}

