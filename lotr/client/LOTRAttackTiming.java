/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package lotr.client;

import lotr.client.LOTRTickHandlerClient;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LOTRAttackTiming {
    private static Minecraft mc = Minecraft.getMinecraft();
    private static ResourceLocation meterTexture = new ResourceLocation("lotr:gui/attackMeter.png");
    private static RenderItem itemRenderer = new RenderItem();
    public static int attackTime;
    public static int prevAttackTime;
    public static int fullAttackTime;
    private static ItemStack attackItem;
    private static int lastCheckTick;

    public static void doAttackTiming() {
        int currentTick = LOTRTickHandlerClient.clientTick;
        if (lastCheckTick == -1) {
            lastCheckTick = currentTick;
        } else if (lastCheckTick == currentTick) {
            return;
        }
        if (LOTRAttackTiming.mc.thePlayer == null) {
            LOTRAttackTiming.reset();
        } else {
            KeyBinding attackKey = LOTRAttackTiming.mc.gameSettings.keyBindAttack;
            boolean pressed = attackKey.isPressed();
            if (pressed) {
                KeyBinding.onTick((int)attackKey.getKeyCode());
            }
            if (pressed && LOTRAttackTiming.mc.objectMouseOver != null && LOTRAttackTiming.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && LOTRAttackTiming.mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
                if (attackTime > 0) {
                    while (attackKey.isPressed()) {
                    }
                } else {
                    ItemStack itemstack = LOTRAttackTiming.mc.thePlayer.getHeldItem();
                    attackTime = fullAttackTime = LOTRWeaponStats.getAttackTimePlayer(itemstack);
                    attackItem = itemstack;
                }
                lastCheckTick = currentTick;
            }
        }
    }

    public static void update() {
        prevAttackTime = attackTime;
        if (attackTime > 0) {
            --attackTime;
        } else {
            LOTRAttackTiming.reset();
        }
    }

    public static void reset() {
        attackTime = 0;
        prevAttackTime = 0;
        fullAttackTime = 0;
        attackItem = null;
    }

    public static void renderAttackMeter(ScaledResolution resolution, float partialTicks) {
        if (fullAttackTime > 0) {
            float attackTimeF = (float)prevAttackTime + (float)(attackTime - prevAttackTime) * partialTicks;
            float meterAmount = 1.0f - (attackTimeF /= (float)fullAttackTime);
            int minX = resolution.getScaledWidth() / 2 + 120;
            int maxX = resolution.getScaledWidth() - 20;
            int maxY = resolution.getScaledHeight() - 10;
            int minY = maxY - 10;
            double lerpX = (float)minX + (float)(maxX - minX) * meterAmount;
            Tessellator tessellator = Tessellator.instance;
            mc.getTextureManager().bindTexture(meterTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            double minU = 0.0;
            double maxU = 1.0;
            double minV = 0.0;
            double maxV = 0.0625;
            double lerpU = minU + (maxU - minU) * (double)meterAmount;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)minX, (double)minY, 0.0, minU, minV);
            tessellator.addVertexWithUV((double)minX, (double)maxY, 0.0, minU, maxV);
            tessellator.addVertexWithUV((double)maxX, (double)maxY, 0.0, maxU, maxV);
            tessellator.addVertexWithUV((double)maxX, (double)minY, 0.0, maxU, minV);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(lerpX, (double)minY, 0.0, lerpU, minV + maxV);
            tessellator.addVertexWithUV(lerpX, (double)maxY, 0.0, lerpU, maxV + maxV);
            tessellator.addVertexWithUV((double)maxX, (double)maxY, 0.0, maxU, maxV + maxV);
            tessellator.addVertexWithUV((double)maxX, (double)minY, 0.0, maxU, minV + maxV);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)minX, (double)minY, 0.0, minU, minV + maxV * 2.0);
            tessellator.addVertexWithUV((double)minX, (double)maxY, 0.0, minU, maxV + maxV * 2.0);
            tessellator.addVertexWithUV((double)maxX, (double)maxY, 0.0, maxU, maxV + maxV * 2.0);
            tessellator.addVertexWithUV((double)maxX, (double)minY, 0.0, maxU, minV + maxV * 2.0);
            tessellator.draw();
            if (attackItem != null) {
                RenderHelper.enableGUIStandardItemLighting();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)32826);
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                int iconX = (minX + maxX) / 2 - 8;
                int iconY = (minY + maxY) / 2 - 8;
                itemRenderer.renderItemAndEffectIntoGUI(LOTRAttackTiming.mc.fontRenderer, mc.getTextureManager(), attackItem, iconX, iconY);
                RenderHelper.disableStandardItemLighting();
            }
        }
    }

    static {
        lastCheckTick = -1;
    }
}

