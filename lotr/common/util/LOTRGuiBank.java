/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package lotr.common.util;

import java.util.List;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRItemCoin;
import lotr.common.util.LOTRGuiButtonBank;
import lotr.common.util.LOTRNetwork;
import lotr.common.util.LOTRPacketMoneyGet;
import lotr.common.util.LOTRPacketMoneyGive;
import lotr.common.util.LOTRPlayerMoneyData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class LOTRGuiBank
extends GuiScreen {
    private EntityPlayer thePlayer;
    private String skillOwner;
    public static final ResourceLocation SKILL_LIST = new ResourceLocation("lotr", "gui/help_icons.png");
    public static final ResourceLocation SKILL_LISTBOOK = new ResourceLocation("lotr", "gui/skill_listbook.png");
    public static final int backgroundWidth = 128;
    public static final int backgroundWidthHalf = 64;
    public static final int backgroundHeight = 188;
    public static final int backgroundHeightHalf = 94;
    int widthHalf;
    int heightHalf;
    int xOrigin;
    int yOrigin;
    protected GuiButton buy1;
    protected GuiButton buy10;
    protected GuiButton buy100;
    protected GuiButton buy1000;
    protected GuiButton sell1;
    protected GuiButton sell10;
    protected GuiButton sell100;
    protected GuiButton sell1000;
    public static String prefix = (Object)EnumChatFormatting.GOLD + "[" + (Object)EnumChatFormatting.AQUA + "Bank" + (Object)EnumChatFormatting.GOLD + "]" + (Object)EnumChatFormatting.WHITE + " ";

    public LOTRGuiBank(EntityPlayer player) {
        this(player, player.getCommandSenderName());
    }

    public LOTRGuiBank(EntityPlayer player, String skillOwner) {
        this.widthHalf = this.width / 2;
        this.heightHalf = this.height / 2;
        this.xOrigin = this.widthHalf - 64;
        this.yOrigin = this.heightHalf - 94;
        this.thePlayer = player;
        this.skillOwner = skillOwner;
    }

    public String getSkillOwner() {
        return this.skillOwner;
    }

    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        int widthHalf = this.width / 2;
        int heightHalf = this.height / 2;
        int xOrigin = widthHalf - 64;
        int yOrigin = heightHalf - 94;
        this.buy1 = new LOTRGuiButtonBank(0, xOrigin + 4, yOrigin + 50, 28, 28, "");
        this.buttonList.add(this.buy1);
        this.buy10 = new LOTRGuiButtonBank(0, xOrigin + 35, yOrigin + 50, 28, 28, "");
        this.buttonList.add(this.buy10);
        this.buy100 = new LOTRGuiButtonBank(0, xOrigin + 65, yOrigin + 50, 28, 28, "");
        this.buttonList.add(this.buy100);
        this.buy1000 = new LOTRGuiButtonBank(0, xOrigin + 95, yOrigin + 50, 28, 28, "");
        this.buttonList.add(this.buy1000);
        this.sell1 = new LOTRGuiButtonBank(0, xOrigin + 4, yOrigin + 120, 28, 28, "");
        this.buttonList.add(this.sell1);
        this.sell10 = new LOTRGuiButtonBank(0, xOrigin + 35, yOrigin + 120, 28, 28, "");
        this.buttonList.add(this.sell10);
        this.sell100 = new LOTRGuiButtonBank(0, xOrigin + 65, yOrigin + 120, 28, 28, "");
        this.buttonList.add(this.sell100);
        this.sell1000 = new LOTRGuiButtonBank(0, xOrigin + 95, yOrigin + 120, 28, 28, "");
        this.buttonList.add(this.sell1000);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void actionPerformed(GuiButton B) {
        LOTRNetwork.LOTRRPMessage packet;
        Minecraft.getMinecraft().thePlayer.getDisplayName();
        if (B == this.buy1) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin));
            packet.sendToServer();
        }
        if (B == this.buy10) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin, 1, 1));
            packet.sendToServer();
        }
        if (B == this.buy100) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin, 1, 2));
            packet.sendToServer();
        }
        if (B == this.buy1000) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin, 1, 3));
            packet.sendToServer();
        }
        if (B == this.sell1) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin));
            packet.sendToServer();
        }
        if (B == this.sell10) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin, 1, 1));
            packet.sendToServer();
        }
        if (B == this.sell100) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin, 1, 2));
            packet.sendToServer();
        }
        if (B == this.sell1000) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin, 1, 3));
            packet.sendToServer();
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int widthHalf = this.width / 2;
        int heightHalf = this.height / 2;
        int xOrigin = widthHalf - 64;
        int yOrigin = heightHalf - 94;
        this.mc.renderEngine.bindTexture(SKILL_LIST);
        this.drawTexturedModalRect(xOrigin, yOrigin, 0, 0, 128, 188);
        this.mc.renderEngine.bindTexture(SKILL_LISTBOOK);
        this.drawTexturedModalRect(xOrigin - 4, yOrigin - 3, 0, 0, 136, 196);
        LOTRPlayerMoneyData data = LOTRPlayerMoneyData.of((EntityPlayer)this.mc.thePlayer);
        String playerName = StatCollector.translateToLocalFormatted((String)"lotr.gui.money.balance", (Object[])new Object[]{data.money});
        String help = StatCollector.translateToLocal((String)"lotr.gui.money.press");
        this.drawCenteredString(this.fontRendererObj, playerName, xOrigin + 65, yOrigin - 15, 16777215);
        this.drawCenteredString(this.fontRendererObj, help, xOrigin + 65, yOrigin + 200, 10066329);
        String withdraw = StatCollector.translateToLocal((String)"lotr.gui.money.withdraw");
        this.drawCenteredString(this.fontRendererObj, withdraw, xOrigin + 65, yOrigin + 35, 13553356);
        String transfer = StatCollector.translateToLocal((String)"lotr.gui.money.transfer");
        this.drawCenteredString(this.fontRendererObj, transfer, xOrigin + 65, yOrigin + 105, 13553356);
        int x = xOrigin + 5;
        int i = 0;
        for (int coin : LOTRItemCoin.values) {
            RenderHelper.enableGUIStandardItemLighting();
            this.mc.renderEngine.bindTexture(SKILL_LIST);
            this.drawTexturedModalRect(x, yOrigin + 50, 0, 188, 27, 27);
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.renderEngine, new ItemStack(LOTRMod.silverCoin, 0, i), x + 5, yOrigin + 50 + 5);
            this.mc.renderEngine.bindTexture(SKILL_LIST);
            RenderHelper.enableGUIStandardItemLighting();
            this.drawTexturedModalRect(x, yOrigin + 120, 27, 188, 27, 27);
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.renderEngine, new ItemStack(LOTRMod.silverCoin, 0, i), x + 5, yOrigin + 120 + 5);
            RenderHelper.disableStandardItemLighting();
            x += 30;
            ++i;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

