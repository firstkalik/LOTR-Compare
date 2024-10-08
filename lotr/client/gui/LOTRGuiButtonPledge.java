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

import java.util.Arrays;
import java.util.List;
import lotr.client.LOTRClientProxy;
import lotr.client.gui.LOTRGuiFactions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonPledge
extends GuiButton {
    private LOTRGuiFactions parentGUI;
    public boolean isBroken;
    public List<String> displayLines;

    public LOTRGuiButtonPledge(LOTRGuiFactions gui, int i, int x, int y, String s) {
        super(i, x, y, 32, 32, s);
        this.parentGUI = gui;
    }

    public void setDisplayLines(String ... s) {
        this.displayLines = s == null ? null : Arrays.asList(s);
    }

    public void drawButton(Minecraft mc, int i, int j) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(LOTRClientProxy.alignmentTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height;
            int state = this.getHoverState(this.field_146123_n);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0 + state * this.width, 180, this.width, this.height);
            this.mouseDragged(mc, i, j);
            if (this.func_146115_a() && this.displayLines != null) {
                float z = this.zLevel;
                this.parentGUI.drawButtonHoveringText(this.displayLines, i, j);
                GL11.glDisable((int)2896);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.zLevel = z;
            }
        }
    }

    public int getHoverState(boolean flag) {
        if (this.isBroken) {
            return flag ? 4 : 3;
        }
        if (!this.enabled) {
            return 0;
        }
        return flag ? 2 : 1;
    }
}

