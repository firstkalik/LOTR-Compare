/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import com.google.common.base.Function;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lotr.client.gui.LOTRGuiButtonBanner;
import lotr.client.gui.LOTRGuiButtonBannerWhitelistSlots;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.LOTRBannerProtection;
import lotr.common.entity.item.LOTRBannerWhitelistEntry;
import lotr.common.entity.item.LOTREntityBanner;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.fellowship.LOTRFellowshipProfile;
import lotr.common.item.LOTRItemBanner;
import lotr.common.network.LOTRPacketBannerRequestInvalidName;
import lotr.common.network.LOTRPacketEditBanner;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class LOTRGuiBanner
extends LOTRGuiScreenBase {
    public static ResourceLocation bannerTexture = new ResourceLocation("lotr:gui/banner_edit.png");
    public final LOTREntityBanner theBanner;
    public int xSize = 200;
    public int ySize = 250;
    private int guiLeft;
    private int guiTop;
    private GuiButton buttonMode;
    private LOTRGuiButtonBanner buttonSelfProtection;
    private GuiButton buttonAddSlot;
    private GuiButton buttonRemoveSlot;
    private LOTRGuiButtonBanner buttonDefaultPermissions;
    private GuiTextField alignmentField;
    private static final int displayedPlayers = 6;
    private GuiTextField[] allowedPlayers = new GuiTextField[0];
    private boolean[] invalidUsernames = new boolean[0];
    private boolean[] validatedUsernames = new boolean[0];
    private boolean[] checkUsernames = new boolean[0];
    private static final int textboxInvalid = 16711680;
    private static final int textboxValid = 65280;
    private static final int textboxDefault = 16777215;
    private float currentScroll = 0.0f;
    private boolean isScrolling = false;
    private boolean wasMouseDown;
    private int scrollBarWidth = 12;
    private int scrollBarHeight = 132;
    private int scrollBarX = 181;
    private int scrollBarY = 68;
    private int scrollBarBorder = 1;
    private int scrollWidgetWidth = 10;
    private int scrollWidgetHeight = 17;
    private int permIconX = 3;
    private int permIconY = 0;
    private int permIconWidth = 10;
    private int permissionsMouseoverIndex = -1;
    private int permissionsMouseoverY = -1;
    private int permWindowBorder = 4;
    private int permWindowWidth = 150;
    private int permWindowHeight = 70;
    private int permissionsOpenIndex = -1;
    private int permissionsOpenY = -1;
    private LOTRBannerProtection.Permission mouseOverPermission = null;
    private boolean defaultPermissionsOpen = false;

    public LOTRGuiBanner(LOTREntityBanner banner) {
        this.theBanner = banner;
    }

    public void initGui() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.buttonMode = new GuiButton(0, this.guiLeft + this.xSize / 2 - 80, this.guiTop + 20, 160, 20, "");
        this.buttonList.add(this.buttonMode);
        this.buttonSelfProtection = new LOTRGuiButtonBanner(1, this.guiLeft + this.xSize / 2 - 24, this.guiTop + 224, 212, 100);
        this.buttonList.add(this.buttonSelfProtection);
        this.buttonAddSlot = new LOTRGuiButtonBannerWhitelistSlots(0, this.guiLeft + 179, this.guiTop + 202);
        this.buttonList.add(this.buttonAddSlot);
        this.buttonRemoveSlot = new LOTRGuiButtonBannerWhitelistSlots(1, this.guiLeft + 187, this.guiTop + 202);
        this.buttonList.add(this.buttonRemoveSlot);
        this.buttonDefaultPermissions = new LOTRGuiButtonBanner(2, this.guiLeft + this.xSize / 2 + 8, this.guiTop + 224, 200, 134);
        this.buttonList.add(this.buttonDefaultPermissions);
        this.buttonDefaultPermissions.activated = true;
        this.alignmentField = new GuiTextField(this.fontRendererObj, this.guiLeft + this.xSize / 2 - 70, this.guiTop + 100, 130, 18);
        this.alignmentField.setText(String.valueOf(this.theBanner.getAlignmentProtection()));
        this.alignmentField.setEnabled(false);
        this.refreshWhitelist();
        for (int i = 0; i < this.allowedPlayers.length; ++i) {
            String name;
            GuiTextField textBox = this.allowedPlayers[i];
            textBox.setTextColor(16777215);
            GameProfile profile = this.theBanner.getWhitelistedPlayer(i);
            if (profile != null && !StringUtils.isNullOrEmpty((String)(name = profile.getName()))) {
                textBox.setText(name);
                textBox.setTextColor(65280);
                this.validatedUsernames[i] = true;
            }
            this.allowedPlayers[i] = textBox;
        }
        this.allowedPlayers[0].setEnabled(false);
        Arrays.fill(this.checkUsernames, false);
    }

    private void updateWhitelistedPlayer(int index, String username) {
        LOTRBannerWhitelistEntry prevEntry = this.theBanner.getWhitelistEntry(index);
        int prevPerms = -1;
        if (prevEntry != null) {
            prevPerms = prevEntry.encodePermBitFlags();
        }
        if (StringUtils.isNullOrEmpty((String)username)) {
            this.theBanner.whitelistPlayer(index, null);
        } else {
            if (LOTRFellowshipProfile.hasFellowshipCode(username)) {
                this.theBanner.whitelistPlayer(index, new LOTRFellowshipProfile(this.theBanner, null, LOTRFellowshipProfile.stripFellowshipCode(username)));
            } else {
                this.theBanner.whitelistPlayer(index, new GameProfile(null, username));
            }
            if (prevPerms >= 0) {
                this.theBanner.getWhitelistEntry(index).decodePermBitFlags(prevPerms);
            }
        }
    }

    private void refreshWhitelist() {
        int length = this.theBanner.getWhitelistLength();
        GuiTextField[] allowedPlayers_new = new GuiTextField[length];
        boolean[] invalidUsernames_new = new boolean[length];
        boolean[] validatedUsernames_new = new boolean[length];
        boolean[] checkUsernames_new = new boolean[length];
        for (int i = 0; i < length; ++i) {
            allowedPlayers_new[i] = i < this.allowedPlayers.length ? this.allowedPlayers[i] : new GuiTextField(this.fontRendererObj, 0, 0, 130, 18);
            if (i < this.invalidUsernames.length) {
                invalidUsernames_new[i] = this.invalidUsernames[i];
            }
            if (i < this.validatedUsernames.length) {
                validatedUsernames_new[i] = this.validatedUsernames[i];
            }
            if (i >= this.checkUsernames.length) continue;
            checkUsernames_new[i] = this.checkUsernames[i];
        }
        this.allowedPlayers = allowedPlayers_new;
        this.invalidUsernames = invalidUsernames_new;
        this.validatedUsernames = validatedUsernames_new;
        this.checkUsernames = checkUsernames_new;
    }

    public void drawScreen(int i, int j, float f) {
        String s;
        int windowY;
        this.permissionsMouseoverIndex = -1;
        this.permissionsMouseoverY = -1;
        this.mouseOverPermission = null;
        this.setupScrollBar(i, j);
        this.alignmentField.setVisible(false);
        this.alignmentField.setEnabled(false);
        for (int l = 0; l < this.allowedPlayers.length; ++l) {
            GuiTextField textBox = this.allowedPlayers[l];
            textBox.setVisible(false);
            textBox.setEnabled(false);
        }
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(bannerTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        String title = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.title");
        this.fontRendererObj.drawString(title, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(title) / 2, this.guiTop + 6, 4210752);
        if (this.theBanner.isPlayerSpecificProtection()) {
            this.buttonMode.displayString = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.protectionMode.playerSpecific");
            s = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.protectionMode.playerSpecific.desc.1");
            this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 46, 4210752);
            s = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.protectionMode.playerSpecific.desc.2");
            this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 46 + this.fontRendererObj.FONT_HEIGHT, 4210752);
            s = LOTRFellowshipProfile.getFellowshipCodeHint();
            this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 206, 4210752);
            int start = 0 + Math.round(this.currentScroll * (float)(this.allowedPlayers.length - 6));
            int end = start + 6 - 1;
            start = Math.max(start, 0);
            end = Math.min(end, this.allowedPlayers.length - 1);
            for (int index = start; index <= end; ++index) {
                int displayIndex = index - start;
                GuiTextField textBox = this.allowedPlayers[index];
                textBox.setVisible(true);
                textBox.setEnabled(index != 0);
                textBox.xPosition = this.guiLeft + this.xSize / 2 - 70;
                textBox.yPosition = this.guiTop + 70 + displayIndex * (textBox.height + 4);
                textBox.drawTextBox();
                String number = index + 1 + ".";
                this.fontRendererObj.drawString(number, this.guiLeft + 24 - this.fontRendererObj.getStringWidth(number), textBox.yPosition + 6, 4210752);
                if (index <= 0 || !this.validatedUsernames[index]) continue;
                this.mc.getTextureManager().bindTexture(bannerTexture);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                int permX = textBox.xPosition + textBox.width + this.permIconX;
                int permY = textBox.yPosition + this.permIconY;
                boolean mouseOver = i >= permX && i < permX + this.permIconWidth && j >= permY && j < permY + this.permIconWidth;
                this.drawTexturedModalRect(permX, permY, 200 + (mouseOver ? this.permIconWidth : 0), 150, this.permIconWidth, this.permIconWidth);
                if (!mouseOver) continue;
                this.permissionsMouseoverIndex = index;
                this.permissionsMouseoverY = textBox.yPosition;
            }
            if (this.hasScrollBar()) {
                this.mc.getTextureManager().bindTexture(bannerTexture);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.drawTexturedModalRect(this.guiLeft + this.scrollBarX, this.guiTop + this.scrollBarY, 200, 0, this.scrollBarWidth, this.scrollBarHeight);
                if (this.canScroll()) {
                    int scroll = (int)(this.currentScroll * (float)(this.scrollBarHeight - this.scrollBarBorder * 2 - this.scrollWidgetHeight));
                    this.drawTexturedModalRect(this.guiLeft + this.scrollBarX + this.scrollBarBorder, this.guiTop + this.scrollBarY + this.scrollBarBorder + scroll, 212, 0, this.scrollWidgetWidth, this.scrollWidgetHeight);
                }
            }
        } else {
            this.permissionsOpenY = -1;
            this.permissionsOpenIndex = -1;
            this.buttonMode.displayString = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.protectionMode.faction");
            s = StatCollector.translateToLocalFormatted((String)"lotr.gui.bannerEdit.protectionMode.faction.desc.1", (Object[])new Object[0]);
            this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 46, 4210752);
            s = StatCollector.translateToLocalFormatted((String)"lotr.gui.bannerEdit.protectionMode.faction.desc.2", (Object[])new Object[]{Float.valueOf(this.theBanner.getAlignmentProtection()), this.theBanner.getBannerType().faction.factionName()});
            this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 46 + this.fontRendererObj.FONT_HEIGHT, 4210752);
            s = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.protectionMode.faction.desc.3");
            this.fontRendererObj.drawString(s, this.guiLeft + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + 46 + this.fontRendererObj.FONT_HEIGHT * 2, 4210752);
            s = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.protectionMode.faction.alignment");
            this.fontRendererObj.drawString(s, this.alignmentField.xPosition, this.alignmentField.yPosition - this.fontRendererObj.FONT_HEIGHT - 3, 4210752);
            this.alignmentField.setVisible(true);
            this.alignmentField.setEnabled(true);
            this.alignmentField.drawTextBox();
        }
        if (this.permissionsOpenIndex >= 0) {
            int windowX = this.guiLeft + this.xSize + this.permWindowBorder;
            windowY = this.permissionsOpenY;
            String username = this.allowedPlayers[this.permissionsOpenIndex].getText();
            boolean isFellowship = LOTRFellowshipProfile.hasFellowshipCode(username);
            if (isFellowship) {
                username = LOTRFellowshipProfile.stripFellowshipCode(username);
            }
            String boxTitle = StatCollector.translateToLocal((String)(isFellowship ? "lotr.gui.bannerEdit.perms.fellowship" : "lotr.gui.bannerEdit.perms.player"));
            String boxSubtitle = StatCollector.translateToLocalFormatted((String)"lotr.gui.bannerEdit.perms.name", (Object[])new Object[]{username});
            Function<LOTRBannerProtection.Permission, Boolean> getEnabled = new Function<LOTRBannerProtection.Permission, Boolean>(){

                public Boolean apply(LOTRBannerProtection.Permission p) {
                    return LOTRGuiBanner.this.theBanner.getWhitelistEntry(LOTRGuiBanner.this.permissionsOpenIndex).isPermissionEnabled(p);
                }
            };
            this.drawPermissionsWindow(i, j, windowX, windowY, boxTitle, boxSubtitle, getEnabled, true);
        }
        if (this.defaultPermissionsOpen) {
            int windowX = this.guiLeft + this.xSize + this.permWindowBorder;
            windowY = this.guiTop + this.ySize - this.permWindowHeight;
            String boxTitle = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.perms.default");
            String boxSubtitle = StatCollector.translateToLocalFormatted((String)"lotr.gui.bannerEdit.perms.allPlayers", (Object[])new Object[0]);
            Function<LOTRBannerProtection.Permission, Boolean> getEnabled = new Function<LOTRBannerProtection.Permission, Boolean>(){

                public Boolean apply(LOTRBannerProtection.Permission p) {
                    return LOTRGuiBanner.this.theBanner.hasDefaultPermission(p);
                }
            };
            this.drawPermissionsWindow(i, j, windowX, windowY, boxTitle, boxSubtitle, getEnabled, false);
        }
        super.drawScreen(i, j, f);
        if (this.buttonSelfProtection.func_146115_a()) {
            float z = this.zLevel;
            String tooltip = StatCollector.translateToLocal((String)("lotr.gui.bannerEdit.selfProtection." + (this.buttonSelfProtection.activated ? "on" : "off")));
            this.drawCreativeTabHoveringText(tooltip, i, j);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.zLevel = z;
        }
        if (this.buttonDefaultPermissions.func_146115_a()) {
            float z = this.zLevel;
            String tooltip = StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.perms.default");
            this.drawCreativeTabHoveringText(tooltip, i, j);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.zLevel = z;
        }
        if (this.permissionsMouseoverIndex >= 0) {
            float z = this.zLevel;
            String username = this.allowedPlayers[this.permissionsMouseoverIndex].getText();
            boolean isFellowship = LOTRFellowshipProfile.hasFellowshipCode(username);
            String tooltip = StatCollector.translateToLocal((String)(isFellowship ? "lotr.gui.bannerEdit.perms.fellowship" : "lotr.gui.bannerEdit.perms.player"));
            this.drawCreativeTabHoveringText(tooltip, i, j);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.zLevel = z;
        }
    }

    private void drawPermissionsWindow(int i, int j, int windowX, int windowY, String boxTitle, String boxSubtitle, Function<LOTRBannerProtection.Permission, Boolean> getEnabled, boolean includeFull) {
        LOTRGuiBanner.drawRect((int)windowX, (int)windowY, (int)(windowX + this.permWindowWidth), (int)(windowY + this.permWindowHeight), (int)-1442840576);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.fontRendererObj.drawString(boxTitle, windowX + 4, windowY + 4, 16777215);
        this.fontRendererObj.drawString(boxSubtitle, windowX + 4, windowY + 14, 11184810);
        this.mc.getTextureManager().bindTexture(bannerTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int x = windowX + 4;
        int y = windowY + 32;
        this.mouseOverPermission = null;
        for (LOTRBannerProtection.Permission p : LOTRBannerProtection.Permission.values()) {
            boolean enabled;
            if (!includeFull && p == LOTRBannerProtection.Permission.FULL) continue;
            if (i >= x && i < x + 10 && j >= y && j < y + 10) {
                this.mouseOverPermission = p;
            }
            this.drawTexturedModalRect(x, y, 200 + ((enabled = ((Boolean)getEnabled.apply((Object)p)).booleanValue()) ? 0 : 20) + (this.mouseOverPermission == p ? 10 : 0), 160 + p.ordinal() * 10, 10, 10);
            x += 14;
            if (p != LOTRBannerProtection.Permission.FULL) continue;
            x += 4;
        }
        if (this.mouseOverPermission != null) {
            String permName = StatCollector.translateToLocal((String)("lotr.gui.bannerEdit.perm." + this.mouseOverPermission.codeName));
            this.fontRendererObj.drawSplitString(permName, windowX + 4, windowY + 47, this.permWindowWidth - this.permWindowBorder * 2, 16777215);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.buttonSelfProtection.activated = this.theBanner.isSelfProtection();
        this.buttonAddSlot.visible = this.buttonRemoveSlot.visible = this.theBanner.isPlayerSpecificProtection();
        this.buttonAddSlot.enabled = this.theBanner.getWhitelistLength() < LOTREntityBanner.WHITELIST_MAX;
        this.buttonRemoveSlot.enabled = this.theBanner.getWhitelistLength() > LOTREntityBanner.WHITELIST_MIN;
        this.alignmentField.updateCursorCounter();
        this.alignmentField.setVisible(!this.theBanner.isPlayerSpecificProtection());
        this.alignmentField.setEnabled(this.alignmentField.getVisible());
        if (this.alignmentField.getVisible() && !this.alignmentField.isFocused()) {
            float alignment;
            float prevAlignment = this.theBanner.getAlignmentProtection();
            try {
                alignment = Float.parseFloat(this.alignmentField.getText());
            }
            catch (NumberFormatException e) {
                alignment = 0.0f;
            }
            alignment = Math.max(alignment, LOTREntityBanner.ALIGNMENT_PROTECTION_MIN);
            alignment = Math.min(alignment, LOTREntityBanner.ALIGNMENT_PROTECTION_MAX);
            this.theBanner.setAlignmentProtection(alignment);
            this.alignmentField.setText(LOTRAlignmentValues.formatAlignForDisplay(alignment));
            if (alignment != prevAlignment) {
                this.sendBannerData(false);
            }
        }
        for (int l = 0; l < this.allowedPlayers.length; ++l) {
            GuiTextField textBox = this.allowedPlayers[l];
            textBox.updateCursorCounter();
        }
    }

    private void setupScrollBar(int i, int j) {
        boolean isMouseDown = Mouse.isButtonDown((int)0);
        int i1 = this.guiLeft + this.scrollBarX;
        int j1 = this.guiTop + this.scrollBarY;
        int i2 = i1 + this.scrollBarWidth;
        int j2 = j1 + this.scrollBarHeight;
        if (!this.wasMouseDown && isMouseDown && i >= i1 && j >= j1 && i < i2 && j < j2) {
            this.isScrolling = this.canScroll();
        }
        if (!isMouseDown) {
            this.isScrolling = false;
        }
        this.wasMouseDown = isMouseDown;
        if (this.isScrolling) {
            this.currentScroll = ((float)(j - j1) - (float)this.scrollWidgetHeight / 2.0f) / ((float)(j2 - j1) - (float)this.scrollWidgetHeight);
            if (this.currentScroll < 0.0f) {
                this.currentScroll = 0.0f;
            }
            if (this.currentScroll > 1.0f) {
                this.currentScroll = 1.0f;
            }
        }
    }

    private boolean hasScrollBar() {
        return this.theBanner.isPlayerSpecificProtection();
    }

    private boolean canScroll() {
        return true;
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (this.alignmentField.getVisible() && this.alignmentField.textboxKeyTyped(c, i)) {
            return;
        }
        for (int l = 1; l < this.allowedPlayers.length; ++l) {
            GuiTextField textBox = this.allowedPlayers[l];
            if (!textBox.getVisible() || !textBox.textboxKeyTyped(c, i)) continue;
            this.validatedUsernames[l] = false;
            this.checkUsernames[l] = true;
            textBox.setTextColor(16777215);
            this.updateWhitelistedPlayer(l, null);
            return;
        }
        if (this.permissionsOpenIndex >= 0 && (i == 1 || i == this.mc.gameSettings.keyBindInventory.getKeyCode())) {
            this.permissionsOpenY = -1;
            this.permissionsOpenIndex = -1;
            return;
        }
        if (this.defaultPermissionsOpen && (i == 1 || i == this.mc.gameSettings.keyBindInventory.getKeyCode())) {
            this.defaultPermissionsOpen = false;
            return;
        }
        super.keyTyped(c, i);
    }

    protected void mouseClicked(int i, int j, int k) {
        int dx;
        super.mouseClicked(i, j, k);
        if (this.alignmentField.getVisible()) {
            this.alignmentField.mouseClicked(i, j, k);
        }
        for (int l = 1; l < this.allowedPlayers.length; ++l) {
            GuiTextField textBox = this.allowedPlayers[l];
            if (!textBox.getVisible()) continue;
            textBox.mouseClicked(i, j, k);
            if (!textBox.isFocused() && this.checkUsernames[l]) {
                this.checkUsernameValid(l);
                this.checkUsernames[l] = false;
            }
            if (!textBox.isFocused() || !this.invalidUsernames[l]) continue;
            this.invalidUsernames[l] = false;
            textBox.setTextColor(16777215);
            textBox.setText("");
        }
        if (this.permissionsMouseoverIndex >= 0) {
            this.permissionsOpenIndex = this.permissionsMouseoverIndex;
            this.permissionsOpenY = this.permissionsMouseoverY;
            this.permissionsMouseoverIndex = -1;
            this.permissionsMouseoverY = -1;
            this.defaultPermissionsOpen = false;
            this.buttonSound();
            return;
        }
        if (this.permissionsOpenIndex >= 0) {
            dx = i - (this.guiLeft + this.xSize + this.permWindowBorder);
            int dy = j - this.permissionsOpenY;
            if (dx < 0 || dx >= this.permWindowWidth || dy < 0 || dy >= this.permWindowHeight) {
                this.permissionsOpenY = -1;
                this.permissionsOpenIndex = -1;
                return;
            }
            if (this.mouseOverPermission != null) {
                LOTRBannerWhitelistEntry entry = this.theBanner.getWhitelistEntry(this.permissionsOpenIndex);
                if (this.mouseOverPermission == LOTRBannerProtection.Permission.FULL) {
                    if (entry.isPermissionEnabled(this.mouseOverPermission)) {
                        entry.clearPermissions();
                    } else {
                        entry.clearPermissions();
                        entry.addPermission(this.mouseOverPermission);
                    }
                } else if (entry.isPermissionEnabled(this.mouseOverPermission)) {
                    entry.removePermission(this.mouseOverPermission);
                } else {
                    if (entry.isPermissionEnabled(LOTRBannerProtection.Permission.FULL)) {
                        entry.removePermission(LOTRBannerProtection.Permission.FULL);
                    }
                    entry.addPermission(this.mouseOverPermission);
                }
                this.buttonSound();
                return;
            }
        }
        if (this.defaultPermissionsOpen) {
            dx = i - (this.guiLeft + this.xSize + this.permWindowBorder);
            int dy = j - (this.guiTop + this.ySize - this.permWindowHeight);
            if (!(dx >= 0 && dx < this.permWindowWidth && dy >= 0 && dy < this.permWindowHeight || this.buttonDefaultPermissions.mousePressed(this.mc, i, j))) {
                this.defaultPermissionsOpen = false;
                return;
            }
            if (this.mouseOverPermission != null) {
                if (this.theBanner.hasDefaultPermission(this.mouseOverPermission)) {
                    this.theBanner.removeDefaultPermission(this.mouseOverPermission);
                } else {
                    this.theBanner.addDefaultPermission(this.mouseOverPermission);
                }
                this.sendBannerData(false);
                this.buttonSound();
                return;
            }
        }
    }

    private void buttonSound() {
        this.buttonMode.func_146113_a(this.mc.getSoundHandler());
    }

    private void checkUsernameValid(int index) {
        GuiTextField textBox = this.allowedPlayers[index];
        String username = textBox.getText();
        if (!StringUtils.isNullOrEmpty((String)username) && !this.invalidUsernames[index]) {
            LOTRPacketBannerRequestInvalidName packet = new LOTRPacketBannerRequestInvalidName(this.theBanner, index, username);
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }

    public void validateUsername(int index, String prevText, boolean valid) {
        GuiTextField textBox = this.allowedPlayers[index];
        String username = textBox.getText();
        if (username.equals(prevText)) {
            if (valid) {
                this.validatedUsernames[index] = true;
                this.invalidUsernames[index] = false;
                textBox.setTextColor(65280);
                this.updateWhitelistedPlayer(index, username);
            } else {
                this.invalidUsernames[index] = true;
                this.validatedUsernames[index] = false;
                textBox.setTextColor(16711680);
                textBox.setText(StatCollector.translateToLocal((String)"lotr.gui.bannerEdit.invalidUsername"));
                this.updateWhitelistedPlayer(index, null);
            }
        }
    }

    public void handleMouseInput() {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        if (i != 0 && this.canScroll()) {
            int j = this.allowedPlayers.length - 6;
            if (i > 0) {
                i = 1;
            }
            if (i < 0) {
                i = -1;
            }
            this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);
            if (this.currentScroll < 0.0f) {
                this.currentScroll = 0.0f;
            }
            if (this.currentScroll > 1.0f) {
                this.currentScroll = 1.0f;
            }
        }
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button == this.buttonMode) {
                this.theBanner.setPlayerSpecificProtection(!this.theBanner.isPlayerSpecificProtection());
            }
            if (button == this.buttonSelfProtection) {
                this.theBanner.setSelfProtection(!this.theBanner.isSelfProtection());
            }
            if (button == this.buttonAddSlot) {
                this.theBanner.resizeWhitelist(this.theBanner.getWhitelistLength() + 1);
                this.refreshWhitelist();
            }
            if (button == this.buttonRemoveSlot) {
                this.theBanner.resizeWhitelist(this.theBanner.getWhitelistLength() - 1);
                this.refreshWhitelist();
            }
            if (button == this.buttonDefaultPermissions) {
                this.defaultPermissionsOpen = true;
            }
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        this.sendBannerData(true);
    }

    private void sendBannerData(boolean sendWhitelist) {
        LOTRPacketEditBanner packet = new LOTRPacketEditBanner(this.theBanner);
        packet.playerSpecificProtection = this.theBanner.isPlayerSpecificProtection();
        packet.selfProtection = this.theBanner.isSelfProtection();
        packet.alignmentProtection = this.theBanner.getAlignmentProtection();
        packet.whitelistLength = this.theBanner.getWhitelistLength();
        if (sendWhitelist) {
            String[] whitelistSlots = new String[this.allowedPlayers.length];
            int[] whitelistPerms = new int[this.allowedPlayers.length];
            for (int index = 1; index < this.allowedPlayers.length; ++index) {
                String text = this.allowedPlayers[index].getText();
                this.updateWhitelistedPlayer(index, text);
                LOTRBannerWhitelistEntry entry = this.theBanner.getWhitelistEntry(index);
                if (entry == null) {
                    whitelistSlots[index] = null;
                    continue;
                }
                GameProfile profile = entry.profile;
                if (profile == null) {
                    whitelistSlots[index] = null;
                    continue;
                }
                String username = profile.getName();
                if (StringUtils.isNullOrEmpty((String)username)) {
                    whitelistSlots[index] = null;
                    continue;
                }
                whitelistSlots[index] = username;
                whitelistPerms[index] = entry.encodePermBitFlags();
            }
            packet.whitelistSlots = whitelistSlots;
            packet.whitelistPerms = whitelistPerms;
        }
        packet.defaultPerms = this.theBanner.getDefaultPermBitFlags();
        LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
    }

}

