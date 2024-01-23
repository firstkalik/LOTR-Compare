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
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.apache.commons.lang3.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.item.LOTRItemBrandingIron;
import lotr.common.network.LOTRPacketBrandingIron;
import lotr.common.network.LOTRPacketHandler;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public class LOTRGuiBrandingIron
extends LOTRGuiScreenBase {
    private static final ResourceLocation guiTexture = new ResourceLocation("lotr:gui/brandingIron.png");
    private static final RenderItem itemRenderer = new RenderItem();
    private int xSize = 200;
    private int ySize = 132;
    private int guiLeft;
    private int guiTop;
    private GuiButton buttonDone;
    private GuiTextField brandNameField;
    private ItemStack theItem;

    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttonDone = new GuiButton(1, this.guiLeft + this.xSize / 2 - 40, this.guiTop + 97, 80, 20, StatCollector.translateToLocal((String)"lotr.gui.brandingIron.done"));
        this.buttonList.add(this.buttonDone);
        ItemStack itemstack = this.mc.thePlayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof LOTRItemBrandingIron) {
            this.theItem = itemstack;
            this.brandNameField = new GuiTextField(this.fontRendererObj, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 50, 160, 20);
        }
        if (this.theItem == null) {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        String s = StatCollector.translateToLocal((String)"lotr.gui.brandingIron.title");
        this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 11, 4210752);
        s = StatCollector.translateToLocal((String)"lotr.gui.brandingIron.naming");
        this.fontRendererObj.drawString(s, this.brandNameField.xPosition, this.brandNameField.yPosition - this.fontRendererObj.FONT_HEIGHT - 3, 4210752);
        s = StatCollector.translateToLocal((String)"lotr.gui.brandingIron.unnameHint");
        this.fontRendererObj.drawString(s, this.brandNameField.xPosition, this.brandNameField.yPosition + this.brandNameField.height + 3, 4210752);
        this.brandNameField.drawTextBox();
        this.buttonDone.enabled = !StringUtils.isBlank((CharSequence)this.brandNameField.getText());
        super.drawScreen(i, j, f);
        if (this.theItem != null) {
            itemRenderer.renderItemIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), this.theItem, this.guiLeft + 8, this.guiTop + 8);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.brandNameField.updateCursorCounter();
        ItemStack itemstack = this.mc.thePlayer.getCurrentEquippedItem();
        if (itemstack == null || !(itemstack.getItem() instanceof LOTRItemBrandingIron)) {
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
        if (this.brandNameField.getVisible() && this.brandNameField.textboxKeyTyped(c, i)) {
            return;
        }
        super.keyTyped(c, i);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        this.brandNameField.mouseClicked(i, j, k);
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        String brandName = this.brandNameField.getText();
        if (!StringUtils.isBlank((CharSequence)brandName)) {
            LOTRPacketBrandingIron packet = new LOTRPacketBrandingIron(brandName);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }
}

