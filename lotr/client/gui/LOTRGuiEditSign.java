/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.List;
import lotr.common.network.LOTRPacketEditSign;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.tileentity.LOTRTileEntitySign;
import lotr.common.tileentity.LOTRTileEntitySignCarved;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class LOTRGuiEditSign
extends GuiScreen {
    private LOTRTileEntitySign tileSign;
    private int updateCounter;
    private int editLine;
    private GuiButton buttonDone;
    private static RenderItem itemRenderer = new RenderItem();

    public LOTRGuiEditSign(LOTRTileEntitySign sign) {
        this.tileSign = sign;
    }

    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        this.buttonDone = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, StatCollector.translateToLocal((String)"gui.done"));
        this.buttonList.add(this.buttonDone);
        this.tileSign.setEditable(false);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
        LOTRPacketEditSign packet = new LOTRPacketEditSign(this.tileSign);
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        this.tileSign.setEditable(true);
    }

    public void updateScreen() {
        ++this.updateCounter;
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            this.tileSign.markDirty();
            this.mc.displayGuiScreen(null);
        }
    }

    protected void keyTyped(char c, int i) {
        if (i == 200) {
            --this.editLine;
        }
        if (i == 208 || i == 28 || i == 156) {
            ++this.editLine;
        }
        this.editLine &= this.tileSign.getNumLines() - 1;
        if (i == 14 && this.tileSign.signText[this.editLine].length() > 0) {
            String s = this.tileSign.signText[this.editLine];
            this.tileSign.signText[this.editLine] = s.substring(0, s.length() - 1);
        }
        if (ChatAllowedCharacters.isAllowedCharacter((char)c) && this.tileSign.signText[this.editLine].length() < 15) {
            int n = this.editLine;
            this.tileSign.signText[n] = this.tileSign.signText[n] + c;
        }
        if (i == 1) {
            this.actionPerformed(this.buttonDone);
        }
    }

    public void drawScreen(int i, int j, float f) {
        this.tileSign.getBlockType();
        int meta = this.tileSign.getBlockMetadata();
        float rotation = (float)Direction.facingToDirection[meta] * 90.0f;
        IIcon onIcon = ((LOTRTileEntitySignCarved)this.tileSign).getOnBlockIcon();
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, StatCollector.translateToLocal((String)"sign.edit"), this.width / 2, 40, 16777215);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2), (float)0.0f, (float)50.0f);
        float f1 = 93.75f;
        GL11.glScalef((float)(-f1), (float)(-f1), (float)(-f1));
        GL11.glTranslatef((float)0.0f, (float)-1.0625f, (float)0.0f);
        GL11.glDisable((int)2929);
        GL11.glPushMatrix();
        float iconScale = 0.5f;
        GL11.glScalef((float)(-iconScale), (float)(-iconScale), (float)iconScale);
        GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.0f);
        this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        itemRenderer.renderIcon(-1, -1, onIcon, 2, 2);
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        if (this.updateCounter / 6 % 2 == 0) {
            this.tileSign.lineBeingEdited = this.editLine;
        }
        GL11.glRotatef((float)(rotation + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.tileSign, -0.5, -0.75, -0.5, 0.0f);
        GL11.glDisable((int)2896);
        this.tileSign.lineBeingEdited = -1;
        GL11.glPopMatrix();
        super.drawScreen(i, j, f);
    }
}

