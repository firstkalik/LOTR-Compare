/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.StatCollector
 */
package lotr.client.gui;

import java.util.List;
import lotr.client.LOTRKeyHandler;
import lotr.client.gui.LOTRGuiButtonLeftRight;
import lotr.client.gui.LOTRGuiMenu;
import lotr.client.gui.LOTRGuiScreenBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;

public abstract class LOTRGuiMenuBase
extends LOTRGuiScreenBase {
    public static RenderItem renderItem = new RenderItem();
    public int xSize = 200;
    public int ySize = 256;
    public int guiLeft;
    public int guiTop;
    protected GuiButton buttonMenuReturn;

    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int buttonH = 20;
        int buttonGap = 35;
        int minGap = 10;
        this.buttonMenuReturn = new LOTRGuiButtonLeftRight(1000, true, 0, this.guiTop + (this.ySize + buttonH) / 4, StatCollector.translateToLocal((String)"lotr.gui.menuButton"));
        this.buttonList.add(this.buttonMenuReturn);
        this.buttonMenuReturn.xPosition = Math.min(0 + buttonGap, this.guiLeft - minGap - this.buttonMenuReturn.width);
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (i == LOTRKeyHandler.keyBindingMenu.getKeyCode()) {
            this.mc.displayGuiScreen((GuiScreen)new LOTRGuiMenu());
            return;
        }
        super.keyTyped(c, i);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled && button == this.buttonMenuReturn) {
            this.mc.displayGuiScreen((GuiScreen)new LOTRGuiMenu());
        }
        super.actionPerformed(button);
    }
}

