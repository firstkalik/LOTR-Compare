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
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.LOTRMod;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketHornSelect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class LOTRGuiHornSelect
extends LOTRGuiScreenBase {
    private static final ResourceLocation guiTexture = new ResourceLocation("lotr:gui/horn_select.png");
    private static final RenderItem itemRenderer = new RenderItem();
    private int xSize = 176;
    private int ySize = 256;
    private int guiLeft;
    private int guiTop;

    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(1, this.guiLeft + 40, this.guiTop + 40, 120, 20, StatCollector.translateToLocal((String)"lotr.gui.hornSelect.haltReady")));
        this.buttonList.add(new GuiButton(3, this.guiLeft + 40, this.guiTop + 75, 120, 20, StatCollector.translateToLocal((String)"lotr.gui.hornSelect.summon")));
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            LOTRPacketHornSelect packet = new LOTRPacketHornSelect(button.id);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            this.mc.thePlayer.closeScreen();
        }
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        String s = StatCollector.translateToLocal((String)"lotr.gui.hornSelect.title");
        this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 11, 4210752);
        super.drawScreen(i, j, f);
        for (int k = 0; k < this.buttonList.size(); ++k) {
            GuiButton button = (GuiButton)this.buttonList.get(k);
            itemRenderer.renderItemIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), new ItemStack(LOTRMod.commandHorn, 1, button.id), button.xPosition - 22, button.yPosition + 2);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ItemStack itemstack = this.mc.thePlayer.inventory.getCurrentItem();
        if (itemstack == null || itemstack.getItem() != LOTRMod.commandHorn || itemstack.getItemDamage() != 0) {
            this.mc.thePlayer.closeScreen();
        }
    }
}

