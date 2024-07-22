/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.LOTRSquadrons;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketItemSquadron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;

public class LOTRGuiSquadronItem
extends LOTRGuiScreenBase {
    private static final ResourceLocation guiTexture = new ResourceLocation("lotr:gui/squadronItem.png");
    private static final RenderItem itemRenderer = new RenderItem();
    private int xSize = 200;
    private int ySize = 120;
    private int guiLeft;
    private int guiTop;
    private GuiButton buttonDone;
    private GuiTextField squadronNameField;
    private ItemStack theItem;

    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttonDone = new GuiButton(1, this.guiLeft + this.xSize / 2 - 40, this.guiTop + 85, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.squadronItem.done"));
        this.buttonList.add(this.buttonDone);
        ItemStack itemstack = this.mc.thePlayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof LOTRSquadrons.SquadronItem) {
            this.theItem = itemstack;
            this.squadronNameField = new GuiTextField(this.fontRendererObj, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 50, 160, 20);
            this.squadronNameField.setMaxStringLength(LOTRSquadrons.SQUADRON_LENGTH_MAX);
            String squadron = LOTRSquadrons.getSquadron(this.theItem);
            if (!StringUtils.isNullOrEmpty((String)squadron)) {
                this.squadronNameField.setText(squadron);
            }
        }
        if (this.theItem == null) {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void drawScreen(int i, int j, float f) {
        boolean noSquadron;
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        String s = this.theItem.getDisplayName();
        this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 11, 4210752);
        s = StatCollector.translateToLocal((String)"lotr.gui.squadronItem.squadron");
        this.fontRendererObj.drawString(s, this.squadronNameField.xPosition, this.squadronNameField.yPosition - this.fontRendererObj.FONT_HEIGHT - 3, 4210752);
        boolean bl = noSquadron = StringUtils.isNullOrEmpty((String)this.squadronNameField.getText()) && !this.squadronNameField.isFocused();
        if (noSquadron) {
            String squadronMessage = StatCollector.translateToLocal((String)"lotr.gui.squadronItem.none");
            this.squadronNameField.setText((Object)EnumChatFormatting.DARK_GRAY + squadronMessage);
        }
        this.squadronNameField.drawTextBox();
        if (noSquadron) {
            this.squadronNameField.setText("");
        }
        super.drawScreen(i, j, f);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.squadronNameField.updateCursorCounter();
        ItemStack itemstack = this.mc.thePlayer.getCurrentEquippedItem();
        if (itemstack == null || !(itemstack.getItem() instanceof LOTRSquadrons.SquadronItem)) {
            this.mc.thePlayer.closeScreen();
        } else {
            this.theItem = itemstack;
        }
    }

    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonDone) {
            this.mc.thePlayer.closeScreen();
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (this.squadronNameField.getVisible() && this.squadronNameField.textboxKeyTyped(c, i)) {
            return;
        }
        super.keyTyped(c, i);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        this.squadronNameField.mouseClicked(i, j, k);
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        String squadron = this.squadronNameField.getText();
        LOTRPacketItemSquadron packet = new LOTRPacketItemSquadron(squadron);
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }
}

