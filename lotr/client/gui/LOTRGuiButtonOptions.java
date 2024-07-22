/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package lotr.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;

public class LOTRGuiButtonOptions
extends GuiButton {
    public String baseDisplayString;

    public LOTRGuiButtonOptions(int i, int j, int k, int l, int i1, String s) {
        super(i, j, k, l, i1, s);
        this.baseDisplayString = s;
    }

    private String getDescription() {
        return StatCollector.translateToLocal((String)(this.baseDisplayString + ".desc.on")) + "\n\n" + StatCollector.translateToLocal((String)(this.baseDisplayString + ".desc.off"));
    }

    public void setState(String s) {
        this.displayString = StatCollector.translateToLocal((String)this.baseDisplayString) + ": " + s;
    }

    public void setState(boolean flag) {
        this.setState(flag ? StatCollector.translateToLocal((String)"lotr.gui.button.on") : StatCollector.translateToLocal((String)"lotr.gui.button.off"));
    }

    public void drawTooltip(Minecraft mc, int i, int j) {
        if (this.enabled && this.func_146115_a()) {
            String s = this.getDescription();
            int border = 3;
            int stringWidth = 200;
            int stringHeight = mc.fontRenderer.listFormattedStringToWidth(s, stringWidth).size() * mc.fontRenderer.FONT_HEIGHT;
            int offset = 10;
            Gui.drawRect((int)(i += offset), (int)(j += offset), (int)(i + stringWidth + border * 2), (int)(j + stringHeight + border * 2), (int)-1073741824);
            mc.fontRenderer.drawSplitString(s, i + border, j + border, stringWidth, 16777215);
        }
    }
}

