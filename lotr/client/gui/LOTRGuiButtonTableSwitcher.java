/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.common.LOTRLevelData;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LOTRGuiButtonTableSwitcher
extends GuiButton {
    public Block table;
    private RenderItem render = new RenderItem();

    public LOTRGuiButtonTableSwitcher(int i, int x, int y, String s, Block table) {
        super(i, x, y, 16, 16, s);
        this.table = table;
    }

    public void drawButton(Minecraft mc, int i, int j) {
        Block block;
        Block block2 = block = !LOTRLevelData.getData((EntityPlayer)mc.thePlayer).getTableSwitched() ? Blocks.crafting_table : this.table;
        if (!this.func_146115_a()) {
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            this.zLevel = 100.0f;
            this.render.zLevel = 100.0f;
            GL11.glEnable((int)2896);
            GL11.glEnable((int)32826);
            this.render.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), new ItemStack(block), this.xPosition, this.yPosition);
            this.render.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), new ItemStack(block), this.xPosition, this.yPosition);
            GL11.glDisable((int)2896);
            this.render.zLevel = 0.0f;
            this.zLevel = 0.0f;
            this.mouseDragged(mc, i, j);
        }
    }
}

