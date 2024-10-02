/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiButtonReforge;
import lotr.common.LOTRConfig;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.inventory.LOTRContainerAnvil;
import lotr.common.network.LOTRPacketAnvilEngraveOwner;
import lotr.common.network.LOTRPacketAnvilReforge;
import lotr.common.network.LOTRPacketAnvilRename;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class LOTRGuiAnvil
extends GuiContainer {
    public static final ResourceLocation anvilTexture = new ResourceLocation("lotr:gui/anvil.png");
    private LOTRContainerAnvil theAnvil;
    private ItemStack prevItemStack;
    private GuiButton buttonReforge;
    private GuiButton buttonEngraveOwner;
    private GuiTextField textFieldRename;
    private static int[] colorCodes = new int[16];

    public LOTRGuiAnvil(EntityPlayer entityplayer, int i, int j, int k) {
        super((Container)new LOTRContainerAnvil(entityplayer, i, j, k));
        this.theAnvil = (LOTRContainerAnvil)this.inventorySlots;
        this.xSize = 176;
        this.ySize = 198;
    }

    public LOTRGuiAnvil(EntityPlayer entityplayer, LOTREntityNPC npc) {
        super((Container)new LOTRContainerAnvil(entityplayer, npc));
        this.theAnvil = (LOTRContainerAnvil)this.inventorySlots;
        this.xSize = 176;
        this.ySize = 198;
    }

    public void initGui() {
        super.initGui();
        this.buttonReforge = new LOTRGuiButtonReforge(0, this.guiLeft + 25, this.guiTop + 78, 176, 39);
        this.buttonEngraveOwner = new LOTRGuiButtonReforge(1, this.guiLeft + 5, this.guiTop + 78, 176, 59);
        this.buttonList.add(this.buttonReforge);
        this.buttonList.add(this.buttonEngraveOwner);
        Keyboard.enableRepeatEvents((boolean)true);
        this.textFieldRename = new GuiTextField(this.fontRendererObj, this.guiLeft + 62, this.guiTop + 24, 103, 12);
        this.textFieldRename.setTextColor(-1);
        this.textFieldRename.setDisabledTextColour(-1);
        this.textFieldRename.setEnableBackgroundDrawing(false);
        this.textFieldRename.setMaxStringLength(40);
        this.prevItemStack = null;
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents((boolean)false);
    }

    public void updateScreen() {
        ItemStack itemstack;
        super.updateScreen();
        if (this.theAnvil.clientReforgeTime > 0) {
            --this.theAnvil.clientReforgeTime;
        }
        if ((itemstack = this.theAnvil.invInput.getStackInSlot(0)) != this.prevItemStack) {
            this.prevItemStack = itemstack;
            String textFieldText = itemstack == null ? "" : LOTRContainerAnvil.stripFormattingCodes(itemstack.getDisplayName());
            boolean textFieldEnabled = itemstack != null;
            this.textFieldRename.setText(textFieldText);
            this.textFieldRename.setEnabled(textFieldEnabled);
            if (itemstack != null) {
                this.renameItem(textFieldText);
            }
        }
    }

    public void drawScreen(int i, int j, float f) {
        float z;
        String tooltip;
        ItemStack inputItem = this.theAnvil.invInput.getStackInSlot(0);
        boolean canReforge = inputItem != null && LOTREnchantmentHelper.isReforgeable(inputItem) && this.theAnvil.reforgeCost > 0;
        boolean canEngrave = inputItem != null && LOTREnchantmentHelper.isReforgeable(inputItem) && this.theAnvil.engraveOwnerCost > 0;
        this.buttonReforge.visible = this.buttonReforge.enabled = canReforge;
        this.buttonEngraveOwner.visible = this.buttonEngraveOwner.enabled = canEngrave && this.theAnvil.canEngraveNewOwner(inputItem, (EntityPlayer)this.mc.thePlayer);
        super.drawScreen(i, j, f);
        if (this.buttonReforge.visible && this.buttonReforge.func_146115_a()) {
            z = this.zLevel;
            tooltip = (Object)EnumChatFormatting.AQUA + StatCollector.translateToLocal((String)"container.lotr.anvil.reforge");
            this.drawCreativeTabHoveringText(tooltip, i - 12, j + 24);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.zLevel = z;
        }
        if (this.buttonEngraveOwner.visible && this.buttonEngraveOwner.func_146115_a()) {
            z = this.zLevel;
            tooltip = (Object)EnumChatFormatting.GOLD + StatCollector.translateToLocal((String)"container.lotr.anvil.engraveOwner");
            this.drawCreativeTabHoveringText(tooltip, i - this.fontRendererObj.getStringWidth(tooltip), j + 24);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.zLevel = z;
        }
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3042);
        List<EnumChatFormatting> itemNameFormatting = this.theAnvil.getActiveItemNameFormatting();
        for (EnumChatFormatting formatting : itemNameFormatting) {
            int formattingID = formatting.ordinal();
            if (!formatting.isColor() || formattingID >= colorCodes.length) continue;
            int color = colorCodes[formattingID];
            this.textFieldRename.setTextColor(color);
        }
        this.textFieldRename.drawTextBox();
        this.textFieldRename.setTextColor(-1);
        if (LOTRConfig.enableAnvilGui) {
            this.displayReforgeItemInfo(i, j);
        }
    }

    private void displayReforgeItemInfo(int mouseX, int mouseY) {
        int yPos = this.guiTop + 15;
        int xPos = this.guiLeft + this.xSize - 7;
        ItemStack reforgedItem = this.theAnvil.invInput.getStackInSlot(0);
        if (reforgedItem != null) {
            this.renderToolTip(reforgedItem, xPos, yPos);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(anvilTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.theAnvil.isTrader) {
            this.drawTexturedModalRect(this.guiLeft + 75, this.guiTop + 69, 176, 21, 18, 18);
        }
        this.drawTexturedModalRect(this.guiLeft + 59, this.guiTop + 20, 0, this.ySize + (this.theAnvil.invInput.getStackInSlot(0) != null ? 0 : 16), 110, 16);
        if (this.theAnvil.invOutput.getStackInSlot(0) == null) {
            boolean flag = false;
            for (int l = 0; l < this.theAnvil.invInput.getSizeInventory(); ++l) {
                if (this.theAnvil.invInput.getStackInSlot(l) == null) continue;
                flag = true;
                break;
            }
            if (flag) {
                this.drawTexturedModalRect(this.guiLeft + 99, this.guiTop + 56, this.xSize, 0, 28, 21);
            }
        }
        if (this.buttonReforge.visible && this.buttonEngraveOwner.visible) {
            this.drawTexturedModalRect(this.guiLeft + 5, this.guiTop + 78, 176, 99, 40, 20);
        } else if (this.buttonReforge.visible) {
            this.drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 78, 176, 79, 20, 20);
        }
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3042);
        String s = this.theAnvil.isTrader ? StatCollector.translateToLocal((String)"container.lotr.smith") : StatCollector.translateToLocal((String)"container.lotr.anvil");
        this.fontRendererObj.drawString(s, 60, 6, 4210752);
        boolean reforge = this.buttonReforge.enabled && this.buttonReforge.func_146115_a();
        boolean engraveOwner = this.buttonEngraveOwner.enabled && this.buttonEngraveOwner.func_146115_a();
        String costText = null;
        int color = 8453920;
        ItemStack inputItem = this.theAnvil.invInput.getStackInSlot(0);
        ItemStack outputItem = this.theAnvil.invOutput.getStackInSlot(0);
        if (inputItem != null) {
            if (reforge && this.theAnvil.reforgeCost > 0) {
                costText = StatCollector.translateToLocalFormatted((String)"container.lotr.anvil.reforgeCost", (Object[])new Object[]{this.theAnvil.reforgeCost});
                if (!this.theAnvil.hasMaterialOrCoinAmount(this.theAnvil.reforgeCost)) {
                    color = 16736352;
                }
            } else if (engraveOwner && this.theAnvil.engraveOwnerCost > 0) {
                costText = (Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"container.lotr.anvil.engraveOwnerCost", (Object[])new Object[]{this.theAnvil.engraveOwnerCost});
                if (!this.theAnvil.hasMaterialOrCoinAmount(this.theAnvil.engraveOwnerCost)) {
                    color = 16736352;
                }
            } else if (this.theAnvil.materialCost > 0 && outputItem != null) {
                String string = this.theAnvil.isTrader ? (Object)EnumChatFormatting.GREEN + StatCollector.translateToLocalFormatted((String)"container.lotr.smith.cost", (Object[])new Object[]{this.theAnvil.materialCost}) : (costText = StatCollector.translateToLocalFormatted((String)"container.lotr.anvil.cost", (Object[])new Object[]{this.theAnvil.materialCost}));
                if (!this.theAnvil.getSlotFromInventory(this.theAnvil.invOutput, 0).canTakeStack((EntityPlayer)this.mc.thePlayer)) {
                    color = 16736352;
                }
            }
        }
        if (costText != null) {
            int colorF = 0xFF000000 | (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
            int x = this.xSize - 8 - this.fontRendererObj.getStringWidth(costText);
            int y = 94;
            if (this.fontRendererObj.getUnicodeFlag()) {
                Gui.drawRect((int)(x - 3), (int)(y - 2), (int)(this.xSize - 7), (int)(y + 10), (int)-16777216);
                Gui.drawRect((int)(x - 2), (int)(y - 1), (int)(this.xSize - 8), (int)(y + 9), (int)-12895429);
            } else {
                this.fontRendererObj.drawString(costText, x, y + 1, colorF);
                this.fontRendererObj.drawString(costText, x + 1, y, colorF);
                this.fontRendererObj.drawString(costText, x + 1, y + 1, colorF);
            }
            this.fontRendererObj.drawString(costText, x, y, color);
        }
        GL11.glEnable((int)2896);
        if (this.theAnvil.clientReforgeTime > 0) {
            float f = (float)this.theAnvil.clientReforgeTime / 40.0f;
            int alpha = (int)(f * 255.0f);
            alpha = MathHelper.clamp_int((int)alpha, (int)0, (int)255);
            int overlayColor = 0xFFFFFF | alpha << 24;
            Slot slot = this.theAnvil.getSlotFromInventory(this.theAnvil.invInput, 0);
            Gui.drawRect((int)slot.xDisplayPosition, (int)slot.yDisplayPosition, (int)(slot.xDisplayPosition + 16), (int)(slot.yDisplayPosition + 16), (int)overlayColor);
        }
    }

    protected void keyTyped(char c, int i) {
        if (this.textFieldRename.textboxKeyTyped(c, i)) {
            this.renameItem(this.textFieldRename.getText());
        } else {
            super.keyTyped(c, i);
        }
    }

    private void renameItem(String rename) {
        ItemStack itemstack = this.theAnvil.invInput.getStackInSlot(0);
        if (itemstack != null && !itemstack.hasDisplayName()) {
            String displayNameStripped = LOTRContainerAnvil.stripFormattingCodes(itemstack.getDisplayName());
            String renameStripped = LOTRContainerAnvil.stripFormattingCodes(rename);
            if (renameStripped.equals(displayNameStripped)) {
                rename = "";
            }
        }
        this.theAnvil.updateItemName(rename);
        LOTRPacketAnvilRename packet = new LOTRPacketAnvilRename(rename);
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }

    protected void mouseClicked(int i, int j, int k) {
        super.mouseClicked(i, j, k);
        this.textFieldRename.mouseClicked(i, j, k);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button == this.buttonReforge) {
                ItemStack inputItem2 = this.theAnvil.invInput.getStackInSlot(0);
                if (inputItem2 != null && this.theAnvil.reforgeCost > 0 && this.theAnvil.hasMaterialOrCoinAmount(this.theAnvil.reforgeCost)) {
                    LOTRPacketAnvilReforge packet = new LOTRPacketAnvilReforge();
                    LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
                }
            } else if (button == this.buttonEngraveOwner && this.theAnvil.invInput.getStackInSlot(0) != null && this.theAnvil.engraveOwnerCost > 0 && this.theAnvil.hasMaterialOrCoinAmount(this.theAnvil.engraveOwnerCost)) {
                LOTRPacketAnvilEngraveOwner packet = new LOTRPacketAnvilEngraveOwner();
                LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            }
        }
    }

    static {
        for (int i = 0; i < 16; ++i) {
            int baseBrightness = (i >> 3 & 1) * 85;
            int r = (i >> 2 & 1) * 170 + baseBrightness;
            int g = (i >> 1 & 1) * 170 + baseBrightness;
            int b = (i >> 0 & 1) * 170 + baseBrightness;
            if (i == 6) {
                r += 85;
            }
            LOTRGuiAnvil.colorCodes[i] = (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
        }
    }
}

