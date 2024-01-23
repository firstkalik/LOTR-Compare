/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import java.util.List;
import lotr.client.LOTRClientProxy;
import lotr.client.gui.LOTRGuiMap;
import lotr.client.gui.LOTRGuiRendererMap;
import lotr.client.gui.LOTRGuiScreenBase;
import lotr.common.entity.npc.LOTRSpeech;
import lotr.common.util.LOTRFunctions;
import lotr.common.world.map.LOTRAbstractWaypoint;
import lotr.common.world.map.LOTRWaypoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRGuiFastTravel
extends LOTRGuiScreenBase {
    private LOTRGuiMap mapGui;
    private LOTRGuiRendererMap mapRenderer;
    private int tickCounter;
    private LOTRAbstractWaypoint theWaypoint;
    private int startX;
    private int startZ;
    private String message;
    private boolean chunkLoaded = false;
    private boolean playedSound = false;
    private static final ResourceLocation ftSound = new ResourceLocation("lotr:event.fastTravel");
    private final float zoomBase;
    private final float mapScaleFactor;
    private float currentZoom;
    private float prevZoom;
    private static final float zoomInAmount = 0.5f;
    private static final float zoomInIncr = 0.008333334f;
    private boolean finishedZoomIn = false;
    private float mapSpeed;
    private float mapVelX;
    private float mapVelY;
    private static final float mapSpeedMax = 2.0f;
    private static final float mapSpeedIncr = 0.01f;
    private static final float mapAccel = 0.2f;
    private boolean reachedWP = false;
    private static final float wpReachedDistance = 1.0f;

    public LOTRGuiFastTravel(LOTRAbstractWaypoint waypoint, int x, int z) {
        this.theWaypoint = waypoint;
        this.startX = x;
        this.startZ = z;
        this.message = LOTRSpeech.getRandomSpeech("fastTravel");
        this.mapGui = new LOTRGuiMap();
        this.mapRenderer = new LOTRGuiRendererMap();
        this.mapRenderer.setSepia(true);
        this.mapRenderer.mapX = LOTRWaypoint.worldToMapX(this.startX);
        this.mapRenderer.mapY = LOTRWaypoint.worldToMapZ(this.startZ);
        float dx = (float)this.theWaypoint.getX() - this.mapRenderer.mapX;
        float dy = (float)this.theWaypoint.getY() - this.mapRenderer.mapY;
        float distSq = dx * dx + dy * dy;
        float dist = (float)Math.sqrt(distSq);
        this.mapScaleFactor = dist / 100.0f;
        this.zoomBase = -((float)(Math.log(this.mapScaleFactor * 0.3f) / Math.log(2.0)));
        this.currentZoom = this.prevZoom = this.zoomBase + 0.5f;
    }

    @Override
    public void updateScreen() {
        if (!this.chunkLoaded && LOTRClientProxy.doesClientChunkExist((World)this.mc.theWorld, this.theWaypoint.getXCoord(), this.theWaypoint.getZCoord())) {
            this.chunkLoaded = true;
        }
        if (!this.playedSound) {
            this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.func_147673_a((ResourceLocation)ftSound));
            this.playedSound = true;
        }
        this.mapRenderer.updateTick();
        ++this.tickCounter;
        this.prevZoom = this.currentZoom;
        if (!this.reachedWP) {
            float dy;
            float dx = (float)this.theWaypoint.getX() - this.mapRenderer.mapX;
            float distSq = dx * dx + (dy = (float)this.theWaypoint.getY() - this.mapRenderer.mapY) * dy;
            float dist = (float)Math.sqrt(distSq);
            if (dist <= 1.0f * this.mapScaleFactor) {
                this.reachedWP = true;
                this.mapSpeed = 0.0f;
                this.mapVelX = 0.0f;
                this.mapVelY = 0.0f;
            } else {
                this.mapSpeed += 0.01f;
                this.mapSpeed = Math.min(this.mapSpeed, 2.0f);
                float vXNew = dx / dist * this.mapSpeed;
                float vYNew = dy / dist * this.mapSpeed;
                float a = 0.2f;
                this.mapVelX += (vXNew - this.mapVelX) * a;
                this.mapVelY += (vYNew - this.mapVelY) * a;
            }
            this.mapRenderer.mapX += this.mapVelX * this.mapScaleFactor;
            this.mapRenderer.mapY += this.mapVelY * this.mapScaleFactor;
            this.currentZoom -= 0.008333334f;
            this.currentZoom = Math.max(this.currentZoom, this.zoomBase);
        } else {
            this.currentZoom += 0.008333334f;
            this.currentZoom = Math.min(this.currentZoom, this.zoomBase + 0.5f);
            if (this.currentZoom >= this.zoomBase + 0.5f) {
                this.finishedZoomIn = true;
            }
        }
        if (this.chunkLoaded && this.reachedWP && this.finishedZoomIn) {
            this.mc.displayGuiScreen(null);
        }
    }

    public void setWorldAndResolution(Minecraft mc, int i, int j) {
        super.setWorldAndResolution(mc, i, j);
        this.mapGui.setWorldAndResolution(mc, i, j);
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (this.chunkLoaded && (i == 1 || i == this.mc.gameSettings.keyBindInventory.getKeyCode())) {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void drawScreen(int i, int j, float f) {
        float zoom;
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        this.mapRenderer.zoomExp = zoom = this.prevZoom + (this.currentZoom - this.prevZoom) * f;
        this.mapRenderer.zoomStable = (float)Math.pow(2.0, this.zoomBase);
        this.mapRenderer.renderMap(this, this.mapGui, f);
        this.mapRenderer.renderVignettes(this, this.zLevel, 4);
        GL11.glEnable((int)3042);
        String title = StatCollector.translateToLocalFormatted((String)"lotr.fastTravel.travel", (Object[])new Object[]{this.theWaypoint.getDisplayName()});
        String titleExtra = new String[]{"", ".", "..", "..."}[this.tickCounter / 10 % 4];
        List messageLines = this.fontRendererObj.listFormattedStringToWidth(this.message, this.width - 100);
        String skipText = StatCollector.translateToLocalFormatted((String)"lotr.fastTravel.skip", (Object[])new Object[]{GameSettings.getKeyDisplayString((int)this.mc.gameSettings.keyBindInventory.getKeyCode())});
        float boxAlpha = 0.5f;
        int boxColor = (int)(boxAlpha * 255.0f) << 24 | 0;
        int fh = this.fontRendererObj.FONT_HEIGHT;
        int border = fh * 2;
        if (this.chunkLoaded) {
            LOTRGuiFastTravel.drawRect((int)0, (int)0, (int)this.width, (int)(0 + border + fh * 3 + border), (int)boxColor);
        } else {
            LOTRGuiFastTravel.drawRect((int)0, (int)0, (int)this.width, (int)(0 + border + fh + border), (int)boxColor);
        }
        int messageY = this.height - border - messageLines.size() * fh;
        LOTRGuiFastTravel.drawRect((int)0, (int)(messageY - border), (int)this.width, (int)this.height, (int)boxColor);
        GL11.glDisable((int)3042);
        this.fontRendererObj.drawStringWithShadow(title + titleExtra, this.width / 2 - this.fontRendererObj.getStringWidth(title) / 2, 0 + border, 16777215);
        for (Object obj : messageLines) {
            String s1 = (String)obj;
            this.drawCenteredString(this.fontRendererObj, s1, this.width / 2, messageY, 16777215);
            messageY += fh;
        }
        if (this.chunkLoaded) {
            float skipAlpha = LOTRFunctions.triangleWave((float)this.tickCounter + f, 0.3f, 1.0f, 80.0f);
            int skipColor = 0xFFFFFF | LOTRClientProxy.getAlphaInt(skipAlpha) << 24;
            GL11.glEnable((int)3042);
            this.fontRendererObj.drawString(skipText, this.width / 2 - this.fontRendererObj.getStringWidth(skipText) / 2, 0 + border + fh * 2, skipColor);
        }
        GL11.glDisable((int)3042);
        super.drawScreen(i, j, f);
    }
}

