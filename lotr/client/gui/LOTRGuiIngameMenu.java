/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.achievement.GuiAchievements
 *  net.minecraft.client.gui.achievement.GuiStats
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.stats.StatFileWriter
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.client.gui.LOTRGuiCapeArrows;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class LOTRGuiIngameMenu
extends GuiScreen {
    private List i;

    public void initGui() {
        this.i = this.buttonList;
        this.i.clear();
        if (this.mc.isIntegratedServerRunning()) {
            this.i.add(new LOTRGuiCapeArrows(1, 2, this.height - 22, 102, 20, "dis"));
        } else {
            this.i.add(new LOTRGuiCapeArrows(1, 2, this.height - 22, 102, 20, I18n.format((String)"menu.disconnect", (Object[])new Object[0])));
        }
        this.i.add(new LOTRGuiCapeArrows(0, 2, this.height - 44, 102, 20, I18n.format((String)"menu.options", (Object[])new Object[0])));
        this.i.add(new LOTRGuiCapeArrows(6, 2, this.height - 66, 102, 20, I18n.format((String)"gui.stats", (Object[])new Object[0])));
        this.i.add(new LOTRGuiCapeArrows(5, 2, this.height - 88, 102, 20, I18n.format((String)"gui.achievements", (Object[])new Object[0])));
        this.i.add(new LOTRGuiCapeArrows(4, 2, this.height - 110, 102, 20, I18n.format((String)"name.returnGame", (Object[])new Object[0])));
    }

    protected void actionPerformed(GuiButton p_146284_1_) {
        switch (p_146284_1_.id) {
            case 0: {
                this.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, this.mc.gameSettings));
            }
            case 1: {
                p_146284_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
            }
            default: {
                return;
            }
            case 4: {
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
            }
            case 5: {
                if (this.mc.thePlayer == null) break;
                this.mc.displayGuiScreen((GuiScreen)new GuiAchievements((GuiScreen)this, this.mc.thePlayer.getStatFileWriter()));
            }
            case 6: 
        }
        if (this.mc.thePlayer != null) {
            this.mc.displayGuiScreen((GuiScreen)new GuiStats((GuiScreen)this, this.mc.thePlayer.getStatFileWriter()));
        }
    }

    public void updateScreen() {
        super.updateScreen();
    }

    public void drawScreen(int par1, int par2, float par3) {
        int width = 107;
        this.drawGradientRect(0, this.height - 114, width, this.height, -1072689136, -804253680);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2 + 90), (float)140.0f, (float)0.0f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float var8 = 1.8f - MathHelper.abs((float)(MathHelper.sin((float)((float)(Minecraft.getSystemTime() % 1000L) / 1000.0f * 3.141593f * 2.0f)) * 0.1f));
        var8 = var8 * 100.0f / (float)(this.fontRendererObj.getStringWidth(I18n.format((String)"name.menu", (Object[])new Object[0])) + 32);
        GL11.glScalef((float)var8, (float)var8, (float)var8);
        this.drawCenteredString(this.fontRendererObj, (Object)ChatFormatting.GOLD + "WASP", 0, 0, 16776960);
        GL11.glRotatef((float)40.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.drawCenteredString(this.fontRendererObj, (Object)ChatFormatting.DARK_RED + I18n.format((String)"name.menu", (Object[])new Object[0]), -8, -15, 16776960);
        GL11.glPopMatrix();
        super.drawScreen(par1, par2, par3);
    }

    public void drawWorldBackground(int p_146270_1_) {
    }
}

