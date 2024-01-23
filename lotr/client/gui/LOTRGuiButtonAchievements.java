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

import lotr.client.gui.LOTRGuiAchievements;
import lotr.common.LOTRAchievement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonAchievements
extends GuiButton {
    private boolean leftOrRight;
    public LOTRAchievement.Category buttonCategory;

    public LOTRGuiButtonAchievements(int i, boolean flag, int j, int k) {
        super(i, j, k, 15, 21, "");
        this.leftOrRight = flag;
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            boolean highlighted;
            mc.getTextureManager().bindTexture(LOTRGuiAchievements.iconsTexture);
            int texU = this.leftOrRight ? 0 : this.width * 3;
            int texV = 124;
            boolean bl = highlighted = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            if (!this.enabled) {
                texU += this.width * 2;
            } else if (highlighted) {
                texU += this.width;
            }
            float[] catColors = this.buttonCategory.getCategoryRGB();
            GL11.glColor4f((float)catColors[0], (float)catColors[1], (float)catColors[2], (float)1.0f);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, texU, texV, this.width, this.height);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, texU, texV + this.height, this.width, this.height);
        }
    }
}

