/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Lists
 *  cpw.mods.fml.client.GuiModList
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiButtonLanguage
 *  net.minecraft.client.gui.GuiLabel
 *  net.minecraft.client.gui.GuiLanguage
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiSelectWorld
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.LanguageManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Session
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.Sys
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lotr.client.gui.LOTRGuiButtonRedBook;
import lotr.client.gui.LOTRGuiCapeArrows;
import lotr.client.gui.LOTRGuiCapeArrows2;
import lotr.client.gui.LOTRGuiMap;
import lotr.client.gui.LOTRGuiRendererMap;
import lotr.client.gui.LOTRGuiUpdates;
import lotr.common.world.map.LOTRWaypoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class LOTRGuiMainMenu
extends GuiMainMenu {
    private static final ResourceLocation titleTexture = new ResourceLocation("textures/gui/title/minecraft1.png");
    private static final ResourceLocation titleTexture1 = new ResourceLocation("textures/gui/title/minecraft2.png");
    private static final ResourceLocation vignetteTexture = new ResourceLocation("textures/misc/vignette.png");
    private static final ResourceLocation menuOverlay = new ResourceLocation("lotr:gui/menu_overlay.png");
    private boolean fadeIn = isFirstMenu;
    private ResourceLocation skin = LOTRGuiMainMenu.getPlayerSkin(Minecraft.getMinecraft().getSession().getUsername());
    private LanguageManager field_146454_h;
    private GuiButton openVKButton;
    private static Random rand = new Random();
    private static boolean isFirstMenu = true;
    private static List<LOTRWaypoint> waypointRoute = new ArrayList<LOTRWaypoint>();
    private static boolean randomWPStart = false;
    private LOTRGuiMap mapGui;
    private static LOTRGuiRendererMap mapRenderer;
    private static int tickCounter;
    private long firstRenderTime;
    private static int currentWPIndex;
    private static float mapSpeed;
    private static float mapVelX;
    private static float mapVelY;
    private String namesite = "\u0413\u0440\u0443\u043f\u043f\u0430 \u0412\u043a\u043e\u043d\u0442\u0430\u043a\u0442\u0435";
    private String namesite1 = "\u0412\u0438\u043a\u0438 \u043f\u043e \u043c\u043e\u0434\u0443";
    private String namesettings = "\u0421\u043f\u0438\u0441\u043e\u043a \u041c\u043e\u0434\u043e\u0432";
    private String namesettings1 = "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438";
    private String namequit = "\u0412\u044b\u0439\u0442\u0438 \u0438\u0437 \u0438\u0433\u0440\u044b";
    private String nameone = "\u041e\u0434\u0438\u043d\u043e\u0447\u043d\u0430\u044f \u0438\u0433\u0440\u0430";
    private String nameserver = "\u0421\u043f\u0438\u0441\u043e\u043a \u0421\u0435\u0440\u0432\u0435\u0440\u043e\u0432";
    private String nameserver2 = "\u00a79Discord #1";
    private String nameserver3 = "\u00a72Discord #2";
    private String namesetting = "\u00a72\u0432\u2013\u0454";

    public LOTRGuiMainMenu() {
        isFirstMenu = false;
        this.mapGui = new LOTRGuiMap();
        mapRenderer = new LOTRGuiRendererMap();
        mapRenderer.setSepia(false);
        if (waypointRoute.isEmpty()) {
            LOTRGuiMainMenu.setupWaypoints();
            currentWPIndex = randomWPStart ? rand.nextInt(waypointRoute.size()) : 0;
        }
        LOTRWaypoint wp = waypointRoute.get(currentWPIndex);
        LOTRGuiMainMenu.mapRenderer.prevMapX = LOTRGuiMainMenu.mapRenderer.mapX = wp.getX();
        LOTRGuiMainMenu.mapRenderer.prevMapY = LOTRGuiMainMenu.mapRenderer.mapY = wp.getY();
    }

    private static void setupWaypoints() {
        waypointRoute.clear();
        waypointRoute.add(LOTRWaypoint.HOBBITON);
        waypointRoute.add(LOTRWaypoint.BRANDYWINE_BRIDGE);
        waypointRoute.add(LOTRWaypoint.BUCKLEBURY);
        waypointRoute.add(LOTRWaypoint.WITHYWINDLE_VALLEY);
        waypointRoute.add(LOTRWaypoint.BREE);
        waypointRoute.add(LOTRWaypoint.WEATHERTOP);
        waypointRoute.add(LOTRWaypoint.RIVENDELL);
        waypointRoute.add(LOTRWaypoint.WEST_GATE);
        waypointRoute.add(LOTRWaypoint.DIMRILL_DALE);
        waypointRoute.add(LOTRWaypoint.CERIN_AMROTH);
        waypointRoute.add(LOTRWaypoint.CARAS_GALADHON);
        waypointRoute.add(LOTRWaypoint.NORTH_UNDEEP);
        waypointRoute.add(LOTRWaypoint.SOUTH_UNDEEP);
        waypointRoute.add(LOTRWaypoint.ARGONATH);
        waypointRoute.add(LOTRWaypoint.RAUROS);
        waypointRoute.add(LOTRWaypoint.EDORAS);
        waypointRoute.add(LOTRWaypoint.HELMS_DEEP);
        waypointRoute.add(LOTRWaypoint.ISENGARD);
        waypointRoute.add(LOTRWaypoint.DUNHARROW);
        waypointRoute.add(LOTRWaypoint.ERECH);
        waypointRoute.add(LOTRWaypoint.MINAS_TIRITH);
        waypointRoute.add(LOTRWaypoint.MINAS_MORGUL);
        waypointRoute.add(LOTRWaypoint.MOUNT_DOOM);
        waypointRoute.add(LOTRWaypoint.MORANNON);
        waypointRoute.add(LOTRWaypoint.EAST_RHOVANION_ROAD);
        waypointRoute.add(LOTRWaypoint.OLD_RHOVANION);
        waypointRoute.add(LOTRWaypoint.RUNNING_FORD);
        waypointRoute.add(LOTRWaypoint.DALE_CITY);
        waypointRoute.add(LOTRWaypoint.THRANDUIL_HALLS);
        waypointRoute.add(LOTRWaypoint.ENCHANTED_RIVER);
        waypointRoute.add(LOTRWaypoint.FOREST_GATE);
        waypointRoute.add(LOTRWaypoint.BEORN);
        waypointRoute.add(LOTRWaypoint.EAGLES_EYRIE);
        waypointRoute.add(LOTRWaypoint.GOBLIN_TOWN);
        waypointRoute.add(LOTRWaypoint.MOUNT_GRAM);
        waypointRoute.add(LOTRWaypoint.FORNOST);
        waypointRoute.add(LOTRWaypoint.ANNUMINAS);
        waypointRoute.add(LOTRWaypoint.MITHLOND_NORTH);
        waypointRoute.add(LOTRWaypoint.TOWER_HILLS);
    }

    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        int i1 = this.height / 4 + 48;
        this.buttonList.add(new LOTRGuiCapeArrows2(1, this.width / 2 - 100, i1 + 6 + 12 - 24, this.nameone));
        this.buttonList.add(new LOTRGuiCapeArrows2(2, this.width / 2 - 100, i1 + 28 + 12 - 24, this.nameserver));
        this.buttonList.add(new LOTRGuiCapeArrows2(3, this.width / 2 - 100, i1 + 50 + 12 - 24, this.namesite));
        this.buttonList.add(new LOTRGuiCapeArrows2(4, this.width / 2 - 100, i1 + 94 + 12 - 24, 98, 20, this.namesettings));
        this.buttonList.add(new LOTRGuiCapeArrows2(5, this.width / 2 + 2, i1 + 124 + 12 - 24, 98, 20, this.namequit));
        this.buttonList.add(new LOTRGuiCapeArrows2(6, this.width / 2 - 100, i1 + 72 + 12 - 24, 98, 20, this.namesite1));
        this.buttonList.add(new LOTRGuiCapeArrows2(7, this.width / 2 - 100, i1 + 124 + 12 - 24, 98, 20, this.namesettings1));
        this.buttonList.add(new LOTRGuiCapeArrows(8, this.width / 2 + 2, i1 + 72 + 12 - 24, 98, 20, this.nameserver2));
        this.buttonList.add(new LOTRGuiCapeArrows(9, this.width / 2 + 2, i1 + 94 + 12 - 24, 98, 20, this.nameserver3));
        this.buttonList.add(new GuiButtonLanguage(10, this.width / 2 - 125, i1 + 94 + 12 - 24));
        this.buttonList.add(new LOTRGuiUpdates(11, this.width / 2 + 104, i1 + 96 + 12 - 24, ""));
        int lowerButtonMaxY = 0;
        for (Object obj : this.buttonList) {
            GuiButton button = (GuiButton)obj;
            int buttonMaxY = button.yPosition + button.height;
            if (buttonMaxY <= lowerButtonMaxY) continue;
            lowerButtonMaxY = buttonMaxY;
        }
        int idealMoveDown = 25;
        int lowestSuitableHeight = this.height - 50;
        int moveDown = Math.min(idealMoveDown, lowestSuitableHeight - lowerButtonMaxY);
        moveDown = Math.max(moveDown, 0);
        for (int i11 = 0; i11 < this.buttonList.size(); ++i11) {
            GuiButton button = (GuiButton)this.buttonList.get(i11);
            button.yPosition += moveDown;
            if (button.getClass() != GuiButton.class) continue;
            LOTRGuiButtonRedBook newButton = new LOTRGuiButtonRedBook(button.id, button.xPosition, button.yPosition, button.width, button.height, button.displayString);
            this.buttonList.set(i11, newButton);
        }
    }

    public void setWorldAndResolution(Minecraft mc, int i, int j) {
        super.setWorldAndResolution(mc, i, j);
        this.mapGui.setWorldAndResolution(mc, i, j);
    }

    public static void head(ResourceLocation skin, int x, int y, int w, int h) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(skin);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)x, (double)(y + h), 0.0, 0.125, 0.5);
        tessellator.addVertexWithUV((double)(x + w), (double)(y + h), 0.0, 0.25, 0.5);
        tessellator.addVertexWithUV((double)(x + w), (double)y, 0.0, 0.25, 0.25);
        tessellator.addVertexWithUV((double)x, (double)y, 0.0, 0.125, 0.25);
        tessellator.draw();
    }

    public static ResourceLocation getPlayerSkin(String string) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player != null) {
            String username = player.getDisplayName();
            ResourceLocation skinLocation = player.getLocationSkin();
            if (skinLocation != null) {
                return skinLocation;
            }
        }
        return AbstractClientPlayer.locationStevePng;
    }

    public void updateScreen() {
        super.updateScreen();
        ++tickCounter;
        mapRenderer.updateTick();
        LOTRWaypoint wp = waypointRoute.get(currentWPIndex);
        double dx = wp.getX() - LOTRGuiMainMenu.mapRenderer.mapX;
        double dy = wp.getY() - LOTRGuiMainMenu.mapRenderer.mapY;
        double distSq = dx * dx + dy * dy;
        double dist = Math.sqrt(distSq);
        if (dist <= 70.0) {
            if (++currentWPIndex >= waypointRoute.size()) {
                currentWPIndex = 0;
            }
        } else {
            mapSpeed += 0.01f;
            mapSpeed = Math.min(mapSpeed, 0.8f);
            double vXNew = dx / dist * (double)mapSpeed;
            double vYNew = dy / dist * (double)mapSpeed;
            float a = 0.09f;
            mapVelX = (float)((double)mapVelX + (vXNew - (double)mapVelX) * (double)a);
            mapVelY = (float)((double)mapVelY + (vYNew - (double)mapVelY) * (double)a);
        }
        LOTRGuiMainMenu.mapRenderer.mapX += (double)mapVelX;
        LOTRGuiMainMenu.mapRenderer.mapY += (double)mapVelY;
    }

    public void drawScreen(int i, int j, float f) {
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        if (this.firstRenderTime == 0L && this.fadeIn) {
            this.firstRenderTime = System.currentTimeMillis();
        }
        float fade = this.fadeIn ? (float)(System.currentTimeMillis() - this.firstRenderTime) / 1000.0f : 1.0f;
        float fadeAlpha = this.fadeIn ? MathHelper.clamp_float((float)(fade - 1.0f), (float)0.0f, (float)1.0f) : 1.0f;
        LOTRGuiMainMenu.mapRenderer.zoomExp = -0.75f + MathHelper.cos((float)(((float)tickCounter + f) * 0.003f)) * 0.1f;
        if (this.fadeIn) {
            float slowerFade = fade * 0.5f;
            float fadeInZoom = MathHelper.clamp_float((float)(1.0f - slowerFade), (float)0.0f, (float)1.0f) * -1.5f;
            LOTRGuiMainMenu.mapRenderer.zoomExp += fadeInZoom;
        }
        LOTRGuiMainMenu.mapRenderer.zoomStable = (float)Math.pow(100.0, -0.10000000149011612);
        mapRenderer.renderMap((GuiScreen)this, this.mapGui, f);
        mapRenderer.renderVignettes((GuiScreen)this, this.zLevel, 3);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(this.fadeIn ? MathHelper.clamp_float((float)(1.0f - fade), (float)0.0f, (float)1.0f) : 0.0f));
        this.mc.getTextureManager().bindTexture(menuOverlay);
        Gui.func_152125_a((int)0, (int)0, (float)0.0f, (float)0.0f, (int)16, (int)128, (int)this.width, (int)this.height, (float)16.0f, (float)128.0f);
        int fadeAlphaI = MathHelper.ceiling_float_int((float)(fadeAlpha * 255.0f)) << 24;
        if ((fadeAlphaI & 0xFC000000) != 0) {
            int short1 = 274;
            int k = this.width / 2 - short1 / 2;
            int b0 = 30;
            this.mc.getTextureManager().bindTexture(titleTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)fadeAlpha);
            this.drawTexturedModalRect(k + 20, b0 - 20, 0, 0, 255, 44);
            this.drawTexturedModalRect(k + 19, b0 + 35, 0, 46, 255, 30);
            List brandings = Lists.reverse((List)FMLCommonHandler.instance().getBrandings(true));
            for (int l = 0; l < brandings.size(); ++l) {
                String brd = (String)brandings.get(l);
                if (Strings.isNullOrEmpty((String)brd)) continue;
                this.drawString(this.fontRendererObj, brd, 2, this.height - (10 + l * (this.fontRendererObj.FONT_HEIGHT + 1)), -1);
            }
            ForgeHooksClient.renderMainMenu((GuiMainMenu)this, (FontRenderer)this.fontRendererObj, (int)this.width, (int)this.height);
            Map modMap = Loader.instance().getIndexedModList();
            ModContainer modContainer = (ModContainer)modMap.get("lotr");
            String modName = modContainer.getName();
            String modVersion = modContainer.getVersion();
            String copyright = (Object)EnumChatFormatting.GOLD + modName + " " + modVersion;
            this.drawString(this.fontRendererObj, copyright, this.width - this.fontRendererObj.getStringWidth(copyright) - 3, this.height - 10, -1);
            String field_92025_p = (String)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_92025_p"});
            String field_146972_A = (String)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_146972_A"});
            int field_92024_r = (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_92024_r"});
            int field_92022_t = (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_92022_t"});
            int field_92021_u = (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_92021_u"});
            int field_92020_v = (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_92020_v"});
            int field_92019_w = (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiMainMenu.class, (Object)((Object)this), (String[])new String[]{"field_92019_w"});
            if (field_92025_p != null && field_92025_p.length() > 0) {
                LOTRGuiMainMenu.drawRect((int)(field_92022_t - 2), (int)(field_92021_u - 2), (int)(field_92020_v + 2), (int)(field_92019_w - 1), (int)1428160512);
                this.drawString(this.fontRendererObj, field_92025_p, field_92022_t, field_92021_u, -1);
                this.drawString(this.fontRendererObj, field_146972_A, (this.width - field_92024_r) / 2, ((GuiButton)this.buttonList.get((int)0)).yPosition - 12, -1);
            }
            for (Object button : this.buttonList) {
                ((GuiButton)button).drawButton(this.mc, i, j);
            }
            for (Object label : this.labelList) {
                ((GuiLabel)label).func_146159_a(this.mc, i, j);
            }
        }
    }

    private void addButtons(int p_73969_1_, int p_73969_2_) {
    }

    protected void actionPerformed(GuiButton b) {
        if (b.id == 1) {
            this.mc.displayGuiScreen((GuiScreen)new GuiSelectWorld((GuiScreen)this));
        }
        if (b.id == 2) {
            this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
        }
        if (b.id == 3) {
            Sys.openURL((String)"https://vk.com/lotrmc");
        }
        if (b.id == 4) {
            this.mc.displayGuiScreen((GuiScreen)new GuiModList((GuiScreen)this));
        }
        if (b.id == 5) {
            this.mc.shutdown();
        }
        if (b.id == 6) {
            Sys.openURL((String)"https://vk.cc/cyOqGx");
        }
        if (b.id == 7) {
            this.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, this.mc.gameSettings));
        }
        if (b.id == 8) {
            Sys.openURL((String)"https://discord.gg/G5yzs9Cn3m");
        }
        if (b.id == 9) {
            Sys.openURL((String)"https://discord.com/invite/Hx5CksdyvE?utm_source=Discord%20Widget&utm_medium=Connect");
        }
        if (b.id == 10) {
            this.mc.displayGuiScreen((GuiScreen)new GuiLanguage((GuiScreen)this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }
        if (b.id == 11) {
            Sys.openURL((String)"https://disk.yandex.ru/d/MjyvZe4vs6W9PA");
        }
    }
}

