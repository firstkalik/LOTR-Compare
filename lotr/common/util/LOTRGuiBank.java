/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
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
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

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
    protected GuiButton buy10000;
    protected GuiButton buy100000;
    protected GuiButton buy1000000;
    protected GuiButton sell10000;
    protected GuiButton sell100000;
    protected GuiButton sell1000000;
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
        this.buy1 = new LOTRGuiButtonBank(0, xOrigin + 4, yOrigin + 30, 28, 28, "");
        this.buttonList.add(this.buy1);
        this.buy10 = new LOTRGuiButtonBank(0, xOrigin + 35, yOrigin + 30, 28, 28, "");
        this.buttonList.add(this.buy10);
        this.buy100 = new LOTRGuiButtonBank(0, xOrigin + 65, yOrigin + 30, 28, 28, "");
        this.buttonList.add(this.buy100);
        this.buy1000 = new LOTRGuiButtonBank(0, xOrigin + 95, yOrigin + 30, 28, 28, "");
        this.buttonList.add(this.buy1000);
        this.buy10000 = new LOTRGuiButtonBank(0, xOrigin - 25 + 30, yOrigin + 60, 28, 28, "");
        this.buttonList.add(this.buy10000);
        this.buy100000 = new LOTRGuiButtonBank(0, xOrigin - 25 + 60, yOrigin + 60, 28, 28, "");
        this.buttonList.add(this.buy100000);
        this.buy1000000 = new LOTRGuiButtonBank(0, xOrigin - 25 + 90, yOrigin + 60, 28, 28, "");
        this.buttonList.add(this.buy1000000);
        this.sell1 = new LOTRGuiButtonBank(0, xOrigin + 4, yOrigin + 110, 28, 28, "");
        this.buttonList.add(this.sell1);
        this.sell10 = new LOTRGuiButtonBank(0, xOrigin + 35, yOrigin + 110, 28, 28, "");
        this.buttonList.add(this.sell10);
        this.sell100 = new LOTRGuiButtonBank(0, xOrigin + 65, yOrigin + 110, 28, 28, "");
        this.buttonList.add(this.sell100);
        this.sell1000 = new LOTRGuiButtonBank(0, xOrigin + 95, yOrigin + 110, 28, 28, "");
        this.buttonList.add(this.sell1000);
        this.sell10000 = new LOTRGuiButtonBank(0, xOrigin - 25 + 30, yOrigin + 110 + 30, 28, 28, "");
        this.buttonList.add(this.sell10000);
        this.sell100000 = new LOTRGuiButtonBank(0, xOrigin - 25 + 60, yOrigin + 110 + 30, 28, 28, "");
        this.buttonList.add(this.sell100000);
        this.sell1000000 = new LOTRGuiButtonBank(0, xOrigin - 25 + 90, yOrigin + 110 + 30, 28, 28, "");
        this.buttonList.add(this.sell1000000);
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
        if (B == this.buy10000) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin, 1, 4));
            packet.sendToServer();
        }
        if (B == this.buy100000) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin, 1, 5));
            packet.sendToServer();
        }
        if (B == this.buy1000000) {
            packet = new LOTRPacketMoneyGive(new ItemStack(LOTRMod.silverCoin, 1, 6));
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
        if (B == this.sell10000) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin, 1, 4));
            packet.sendToServer();
        }
        if (B == this.sell100000) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin, 1, 5));
            packet.sendToServer();
        }
        if (B == this.sell1000000) {
            packet = new LOTRPacketMoneyGet(new ItemStack(LOTRMod.silverCoin, 1, 6));
            packet.sendToServer();
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        int widthHalf = this.width / 2;
        int heightHalf = this.height / 2;
        int xOrigin = widthHalf - 64;
        int yOrigin = heightHalf - 94;
        LOTRPlayerMoneyData data = LOTRPlayerMoneyData.of((EntityPlayer)this.mc.thePlayer);
        this.mc.renderEngine.bindTexture(SKILL_LIST);
        this.drawTexturedModalRect(xOrigin, yOrigin, 0, 0, 128, 188);
        this.mc.renderEngine.bindTexture(SKILL_LISTBOOK);
        this.drawTexturedModalRect(xOrigin - 4, yOrigin - 3, 0, 0, 136, 196);
        String playerName = StatCollector.translateToLocalFormatted((String)"lotr.gui.money.balance", (Object[])new Object[]{data.money});
        String help = StatCollector.translateToLocal((String)"lotr.gui.money.press");
        int textX = xOrigin + 65;
        int textY = yOrigin - 15;
        this.drawCenteredString(this.fontRendererObj, playerName, textX, textY, 16777215);
        GL11.glPushAttrib((int)1048575);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.renderEngine, new ItemStack(LOTRMod.silverCoin, 1, this.getCoinMetaIndex(data.money)), textX + this.fontRendererObj.getStringWidth(playerName) / 2 + 3, textY - 4);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
        this.drawCenteredString(this.fontRendererObj, help, xOrigin + 65, yOrigin + 200, 10066329);
        String withdraw = (Object)EnumChatFormatting.GREEN + StatCollector.translateToLocal((String)"lotr.gui.money.withdraw") + (Object)EnumChatFormatting.RESET;
        this.drawCenteredString(this.fontRendererObj, withdraw, xOrigin + 65, yOrigin + 15, 13553356);
        String transfer = (Object)EnumChatFormatting.RED + StatCollector.translateToLocal((String)"lotr.gui.money.transfer") + (Object)EnumChatFormatting.RESET;
        this.drawCenteredString(this.fontRendererObj, transfer, xOrigin + 65, yOrigin + 95, 13553356);
        int x = xOrigin + 5;
        int y = yOrigin + 30;
        int xOffset = 0;
        int yOffset = 0;
        int i = 0;
        for (int j = 0; j < 8 && j < LOTRItemCoin.values.length; ++j) {
            int coin = LOTRItemCoin.values[j];
            if (j >= 4) {
                xOffset = -120;
                yOffset = 30;
            } else {
                xOffset = 0;
                yOffset = 0;
            }
            GL11.glPushAttrib((int)1048575);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            this.mc.renderEngine.bindTexture(SKILL_LIST);
            this.drawTexturedModalRect(x + xOffset, y + yOffset, 0, 188, 27, 27);
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.renderEngine, new ItemStack(LOTRMod.silverCoin, 0, i), x + 5 + xOffset, y + 5 + yOffset);
            GL11.glPopMatrix();
            GL11.glPopAttrib();
            GL11.glPushAttrib((int)1048575);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            this.mc.renderEngine.bindTexture(SKILL_LIST);
            this.drawTexturedModalRect(x + xOffset, y + yOffset + 80, 27, 188, 27, 27);
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.renderEngine, new ItemStack(LOTRMod.silverCoin, 0, i), x + 5 + xOffset, y + 85 + yOffset);
            GL11.glPopMatrix();
            GL11.glPopAttrib();
            x += 30;
            ++i;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private int getCoinMetaIndex(int amount) {
        if (amount < 10) {
            return 0;
        }
        if (amount < 100) {
            return 1;
        }
        if (amount < 1000) {
            return 2;
        }
        if (amount < 10000) {
            return 3;
        }
        if (amount < 100000) {
            return 4;
        }
        if (amount < 1000000) {
            return 5;
        }
        return 6;
    }
}

