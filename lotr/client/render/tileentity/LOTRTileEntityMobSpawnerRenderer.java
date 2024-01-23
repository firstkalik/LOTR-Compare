/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiIngameMenu
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import java.util.Collection;
import java.util.HashMap;
import lotr.client.LOTRTickHandlerClient;
import lotr.common.entity.LOTREntities;
import lotr.common.tileentity.LOTRTileEntityMobSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRTileEntityMobSpawnerRenderer
extends TileEntitySpecialRenderer {
    private int tempID;
    private HashMap initialisedItemEntities = new HashMap();
    private static double itemYaw;
    private static double prevItemYaw;

    public static void onClientTick() {
        prevItemYaw = itemYaw = MathHelper.wrapAngleTo180_double((double)itemYaw);
        itemYaw += 1.5;
    }

    public void func_147496_a(World world) {
        this.loadEntities(world);
    }

    private void loadEntities(World world) {
        this.unloadEntities();
        if (world != null) {
            for (LOTREntities.SpawnEggInfo info : LOTREntities.spawnEggs.values()) {
                String entityName = LOTREntities.getStringFromID(info.spawnedID);
                Entity entity = EntityList.createEntityByName((String)entityName, (World)world);
                if (entity instanceof EntityLiving) {
                    ((EntityLiving)entity).onSpawnWithEgg(null);
                }
                this.initialisedItemEntities.put(info.spawnedID, entity);
            }
        }
    }

    private void unloadEntities() {
        this.initialisedItemEntities.clear();
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        WorldClient world = Minecraft.getMinecraft().theWorld;
        LOTRTileEntityMobSpawner mobSpawner = (LOTRTileEntityMobSpawner)tileentity;
        if (mobSpawner != null && !mobSpawner.isActive()) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1), (float)((float)d2 + 0.5f));
        Entity entity = null;
        double yaw = 0.0;
        double prevYaw = 0.0;
        if (mobSpawner != null) {
            entity = mobSpawner.getMobEntity((World)world);
            yaw = mobSpawner.yaw;
            prevYaw = mobSpawner.prevYaw;
        } else {
            entity = (Entity)this.initialisedItemEntities.get(this.tempID);
            yaw = itemYaw;
            prevYaw = prevItemYaw;
        }
        if (entity != null) {
            float f1 = 0.4375f;
            GL11.glTranslatef((float)0.0f, (float)0.4f, (float)0.0f);
            GL11.glRotatef((float)((float)(prevYaw + (yaw - prevYaw) * (double)f) * 10.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.4f, (float)0.0f);
            GL11.glScalef((float)f1, (float)f1, (float)f1);
            entity.setLocationAndAngles(d, d1, d2, 0.0f, 0.0f);
            RenderManager.instance.renderEntityWithPosYaw(entity, 0.0, 0.0, 0.0, 0.0f, f);
        }
        GL11.glPopMatrix();
    }

    public void renderInvMobSpawner(int i) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        this.tempID = i;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushAttrib((int)1048575);
        this.renderTileEntityAt(null, 0.0, 0.0, 0.0, LOTRTickHandlerClient.renderTick);
        GL11.glPopAttrib();
        this.tempID = 0;
        GL11.glPopMatrix();
    }
}

