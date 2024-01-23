/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import java.util.ArrayList;
import java.util.List;
import lotr.client.gui.LOTRGuiAchievements;
import lotr.client.gui.LOTRGuiButtonMenu;
import lotr.client.gui.LOTRGuiFactions;
import lotr.client.gui.LOTRGuiFellowships;
import lotr.client.gui.LOTRGuiMap;
import lotr.client.gui.LOTRGuiMenuBase;
import lotr.client.gui.LOTRGuiOptions;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.client.gui.LOTRGuiShields;
import lotr.client.gui.LOTRGuiTitles;
import lotr.common.LOTRDimension;
import lotr.common.quest.LOTRMiniQuestWelcome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRGuiMenu
extends LOTRGuiScreenBase {
    public static final ResourceLocation menuIconsTexture = new ResourceLocation("lotr:gui/menu_icons.png");
    public static Class<? extends LOTRGuiMenuBase> lastMenuScreen = null;

    public void initGui() {
        super.initGui();
        LOTRGuiMenu.resetLastMenuScreen();
        int midX = this.width / 2;
        int midY = this.height / 2;
        int buttonGap = 10;
        int buttonSize = 32;
        this.buttonList.add(new LOTRGuiButtonMenu(this, 2, 0, 0, LOTRGuiAchievements.class, StatCollector.translateToLocal((String)"lotr.gui.achievements"), 30));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 3, 0, 0, LOTRGuiMap.class, StatCollector.translateToLocal((String)"lotr.gui.map"), 50));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 4, 0, 0, LOTRGuiFactions.class, StatCollector.translateToLocal((String)"lotr.gui.factions"), 33));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 0, 0, 0, null, "?", -1));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 6, 0, 0, LOTRGuiFellowships.class, StatCollector.translateToLocal((String)"lotr.gui.fellowships"), 25));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 7, 0, 0, LOTRGuiTitles.class, StatCollector.translateToLocal((String)"lotr.gui.titles"), 20));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 5, 0, 0, LOTRGuiShields.class, StatCollector.translateToLocal((String)"lotr.gui.shields"), 31));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 1, 0, 0, LOTRGuiOptions.class, StatCollector.translateToLocal((String)"lotr.gui.options"), 24));
        ArrayList<LOTRGuiButtonMenu> menuButtons = new ArrayList<LOTRGuiButtonMenu>();
        for (Object obj : this.buttonList) {
            if (!(obj instanceof LOTRGuiButtonMenu)) continue;
            LOTRGuiButtonMenu button = (LOTRGuiButtonMenu)((Object)obj);
            button.enabled = button.canDisplayMenu();
            menuButtons.add(button);
        }
        int numButtons = menuButtons.size();
        int numTopRowButtons = (numButtons - 1) / 2 + 1;
        int numBtmRowButtons = numButtons - numTopRowButtons;
        int topRowLeft = midX - (numTopRowButtons * buttonSize + (numTopRowButtons - 1) * buttonGap) / 2;
        int btmRowLeft = midX - (numBtmRowButtons * buttonSize + (numBtmRowButtons - 1) * buttonGap) / 2;
        for (int l = 0; l < numButtons; ++l) {
            LOTRGuiButtonMenu button = (LOTRGuiButtonMenu)((Object)menuButtons.get(l));
            if (l < numTopRowButtons) {
                button.xPosition = topRowLeft + l * (buttonSize + buttonGap);
                button.yPosition = midY - buttonGap / 2 - buttonSize;
                continue;
            }
            button.xPosition = btmRowLeft + (l - numTopRowButtons) * (buttonSize + buttonGap);
            button.yPosition = midY + buttonGap / 2;
        }
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        String title = StatCollector.translateToLocalFormatted((String)"lotr.gui.menu", (Object[])new Object[]{LOTRDimension.getCurrentDimensionWithFallback((World)this.mc.theWorld).getDimensionName()});
        this.fontRendererObj.drawStringWithShadow(title, this.width / 2 - this.fontRendererObj.getStringWidth(title) / 2, this.height / 2 - 80, 16777215);
        super.drawScreen(i, j, f);
        for (Object obj : this.buttonList) {
            LOTRGuiButtonMenu button;
            if (!(obj instanceof LOTRGuiButtonMenu) || !(button = (LOTRGuiButtonMenu)((Object)obj)).func_146115_a() || button.displayString == null) continue;
            float z = this.zLevel;
            int stringWidth = 200;
            this.drawCreativeTabHoveringText(button.displayString, i, j);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.zLevel = z;
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        for (Object obj : this.buttonList) {
            if (!(obj instanceof LOTRGuiButtonMenu)) continue;
            LOTRGuiButtonMenu button = (LOTRGuiButtonMenu)((Object)obj);
            if (!button.visible || !button.enabled || button.menuKeyCode < 0 || i != button.menuKeyCode) continue;
            this.actionPerformed(button);
            return;
        }
        super.keyTyped(c, i);
    }

    protected void actionPerformed(GuiButton button) {
        LOTRGuiMenuBase screen;
        LOTRGuiButtonMenu buttonMenu;
        if (button.enabled && button instanceof LOTRGuiButtonMenu && (screen = (buttonMenu = (LOTRGuiButtonMenu)button).openMenu()) != null) {
            this.mc.displayGuiScreen((GuiScreen)screen);
            lastMenuScreen = ((Object)((Object)screen)).getClass();
            return;
        }
        super.actionPerformed(button);
    }

    public static void resetLastMenuScreen() {
        lastMenuScreen = null;
    }

    public static GuiScreen openMenu(EntityPlayer entityplayer) {
        boolean[] map_factions = LOTRMiniQuestWelcome.forceMenu_Map_Factions(entityplayer);
        if (map_factions[0]) {
            return new LOTRGuiMap();
        }
        if (map_factions[1]) {
            return new LOTRGuiFactions();
        }
        if (lastMenuScreen != null) {
            try {
                return lastMenuScreen.newInstance();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new LOTRGuiMenu();
    }
}

