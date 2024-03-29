/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class LOTRGuiScrollPane {
    private final int scrollWidgetWidth;
    private final int scrollWidgetHeight;
    private int barColor;
    private int widgetColor;
    public int scrollBarX0;
    public int paneX0;
    public int paneY0;
    public int paneY1;
    public boolean hasScrollBar;
    public float currentScroll;
    public boolean isScrolling;
    public boolean mouseOver;
    private boolean wasMouseDown;

    public LOTRGuiScrollPane(int ww, int wh) {
        this.scrollWidgetWidth = ww;
        this.scrollWidgetHeight = wh;
        this.barColor = -1711276033;
        this.widgetColor = -1426063361;
    }

    public LOTRGuiScrollPane setColors(int c1, int c2) {
        int alphaMask = -16777216;
        if ((c1 & alphaMask) == 0) {
            c1 |= alphaMask;
        }
        if ((c2 & alphaMask) == 0) {
            c2 |= alphaMask;
        }
        this.barColor = c1;
        this.widgetColor = c2;
        return this;
    }

    public int[] getMinMaxIndices(List list, int displayed) {
        int size = list.size();
        int min = 0 + Math.round(this.currentScroll * (float)(size - displayed));
        int max = displayed - 1 + Math.round(this.currentScroll * (float)(size - displayed));
        min = Math.max(min, 0);
        max = Math.min(max, size - 1);
        return new int[]{min, max};
    }

    public void drawScrollBar() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int x0 = this.scrollBarX0 + this.scrollWidgetWidth / 2;
        int y0 = this.paneY0;
        int y1 = this.paneY1;
        Gui.drawRect((int)x0, (int)y0, (int)(x0 + 1), (int)y1, (int)this.barColor);
        int scroll = (int)(this.currentScroll * (float)(y1 - y0 - this.scrollWidgetHeight));
        x0 = this.scrollBarX0;
        int x1 = x0 + this.scrollWidgetWidth;
        y1 = (y0 += scroll) + this.scrollWidgetHeight;
        Gui.drawRect((int)x0, (int)y0, (int)x1, (int)y1, (int)this.widgetColor);
    }

    public void mouseDragScroll(int i, int j) {
        boolean isMouseDown = Mouse.isButtonDown((int)0);
        if (!this.hasScrollBar) {
            this.resetScroll();
        } else {
            boolean mouseOverScroll;
            int x0 = this.paneX0;
            int x1 = this.scrollBarX0 + this.scrollWidgetWidth;
            int y0 = this.paneY0;
            int y1 = this.paneY1;
            this.mouseOver = i >= x0 && j >= y0 && i < x1 && j < y1;
            x0 = this.scrollBarX0;
            boolean bl = mouseOverScroll = i >= x0 && j >= y0 && i < x1 && j < y1;
            if (!this.wasMouseDown && isMouseDown && mouseOverScroll) {
                this.isScrolling = true;
            }
            if (!isMouseDown) {
                this.isScrolling = false;
            }
            if (this.isScrolling) {
                this.currentScroll = ((float)(j - y0) - (float)this.scrollWidgetHeight / 2.0f) / ((float)(y1 - y0) - (float)this.scrollWidgetHeight);
                this.currentScroll = MathHelper.clamp_float((float)this.currentScroll, (float)0.0f, (float)1.0f);
            }
        }
        this.wasMouseDown = isMouseDown;
    }

    public void resetScroll() {
        this.currentScroll = 0.0f;
        this.isScrolling = false;
    }

    public void mouseWheelScroll(int delta, int size) {
        this.currentScroll -= (float)delta / (float)size;
        this.currentScroll = MathHelper.clamp_float((float)this.currentScroll, (float)0.0f, (float)1.0f);
    }
}

