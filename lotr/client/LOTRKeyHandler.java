/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.math.IntMath
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$MouseInputEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package lotr.client;

import com.google.common.math.IntMath;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import java.util.HashMap;
import java.util.List;
import lotr.client.LOTRAttackTiming;
import lotr.client.LOTRClientProxy;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LOTRKeyHandler {
    public static KeyBinding keyBindingMenu = new KeyBinding("Menu", 38, "LOTR");
    public static KeyBinding keyBindingMapTeleport = new KeyBinding("Map Teleport", 50, "LOTR");
    public static KeyBinding keyBindingFastTravel = new KeyBinding("Fast Travel", 33, "LOTR");
    public static KeyBinding keyBindingAlignmentCycleLeft = new KeyBinding("Alignment Cycle Left", 203, "LOTR");
    public static KeyBinding keyBindingAlignmentCycleRight = new KeyBinding("Alignment Cycle Right", 205, "LOTR");
    public static KeyBinding keyBindingAlignmentGroupPrev = new KeyBinding("Alignment Group Prev", 200, "LOTR");
    public static KeyBinding keyBindingAlignmentGroupNext = new KeyBinding("Alignment Group Next", 208, "LOTR");
    private static Minecraft mc = Minecraft.getMinecraft();
    private static int alignmentChangeTick;

    public LOTRKeyHandler() {
        FMLCommonHandler.instance().bus().register((Object)this);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingMenu);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingMapTeleport);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingFastTravel);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingAlignmentCycleLeft);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingAlignmentCycleRight);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingAlignmentGroupPrev);
        ClientRegistry.registerKeyBinding((KeyBinding)keyBindingAlignmentGroupNext);
    }

    @SubscribeEvent
    public void MouseInputEvent(InputEvent.MouseInputEvent event) {
        LOTRAttackTiming.doAttackTiming();
    }

    @SubscribeEvent
    public void KeyInputEvent(InputEvent.KeyInputEvent event) {
        LOTRAttackTiming.doAttackTiming();
        if (keyBindingMenu.getIsKeyPressed() && LOTRKeyHandler.mc.currentScreen == null) {
            LOTRKeyHandler.mc.thePlayer.openGui((Object)LOTRMod.instance, 11, (World)LOTRKeyHandler.mc.theWorld, 0, 0, 0);
        }
        LOTRPlayerData pd = LOTRLevelData.getData((EntityPlayer)LOTRKeyHandler.mc.thePlayer);
        boolean usedAlignmentKeys = false;
        HashMap<LOTRDimension.DimensionRegion, LOTRFaction> lastViewedRegions = new HashMap<LOTRDimension.DimensionRegion, LOTRFaction>();
        LOTRDimension currentDimension = LOTRDimension.getCurrentDimensionWithFallback((World)LOTRKeyHandler.mc.theWorld);
        LOTRFaction currentFaction = pd.getViewingFaction();
        LOTRDimension.DimensionRegion currentRegion = currentFaction.factionRegion;
        List<LOTRDimension.DimensionRegion> regionList = currentDimension.dimensionRegions;
        List<LOTRFaction> factionList = currentRegion.factionList;
        if (LOTRKeyHandler.mc.currentScreen == null && alignmentChangeTick <= 0) {
            int i;
            if (keyBindingAlignmentCycleLeft.getIsKeyPressed()) {
                i = factionList.indexOf((Object)currentFaction);
                --i;
                i = IntMath.mod((int)i, (int)factionList.size());
                currentFaction = factionList.get(i);
                usedAlignmentKeys = true;
            }
            if (keyBindingAlignmentCycleRight.getIsKeyPressed()) {
                i = factionList.indexOf((Object)currentFaction);
                ++i;
                i = IntMath.mod((int)i, (int)factionList.size());
                currentFaction = factionList.get(i);
                usedAlignmentKeys = true;
            }
            if (regionList != null && currentRegion != null) {
                if (keyBindingAlignmentGroupPrev.getIsKeyPressed()) {
                    pd.setRegionLastViewedFaction(currentRegion, currentFaction);
                    lastViewedRegions.put(currentRegion, currentFaction);
                    i = regionList.indexOf((Object)currentRegion);
                    --i;
                    i = IntMath.mod((int)i, (int)regionList.size());
                    currentRegion = regionList.get(i);
                    factionList = currentRegion.factionList;
                    currentFaction = pd.getRegionLastViewedFaction(currentRegion);
                    usedAlignmentKeys = true;
                }
                if (keyBindingAlignmentGroupNext.getIsKeyPressed()) {
                    pd.setRegionLastViewedFaction(currentRegion, currentFaction);
                    lastViewedRegions.put(currentRegion, currentFaction);
                    i = regionList.indexOf((Object)currentRegion);
                    ++i;
                    i = IntMath.mod((int)i, (int)regionList.size());
                    currentRegion = regionList.get(i);
                    factionList = currentRegion.factionList;
                    currentFaction = pd.getRegionLastViewedFaction(currentRegion);
                    usedAlignmentKeys = true;
                }
            }
        }
        if (usedAlignmentKeys) {
            LOTRClientProxy.sendClientInfoPacket(currentFaction, lastViewedRegions);
            alignmentChangeTick = 2;
        }
    }

    public static void update() {
        if (alignmentChangeTick > 0) {
            --alignmentChangeTick;
        }
    }
}

