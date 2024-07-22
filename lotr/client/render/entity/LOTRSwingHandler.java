/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package lotr.client.render.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lotr.client.LOTRAttackTiming;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LOTRSwingHandler {
    private static Map<EntityLivingBase, SwingTime> entitySwings = new HashMap<EntityLivingBase, SwingTime>();
    private static float swingFactor = 0.8f;

    public LOTRSwingHandler() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        SwingTime swt;
        ItemStack item;
        EntityLivingBase entity = event.entityLiving;
        World world = entity.worldObj;
        if (world.isRemote && (swt = entitySwings.get((Object)entity)) == null && entity.isSwingInProgress && entity.swingProgressInt == 0 && LOTRWeaponStats.isMeleeWeapon(item = entity.getHeldItem())) {
            int time = 0;
            time = entity instanceof EntityPlayer ? LOTRWeaponStats.getAttackTimePlayer(item) : LOTRWeaponStats.getAttackTimePlayer(item);
            time = Math.round((float)time * swingFactor);
            swt = new SwingTime();
            swt.swing = 1;
            swt.swingPrev = 0;
            swt.swingMax = time;
            entitySwings.put(entity, swt);
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.theWorld == null) {
                entitySwings.clear();
            } else if (!mc.isGamePaused()) {
                ArrayList<EntityLivingBase> removes = new ArrayList<EntityLivingBase>();
                for (Map.Entry<EntityLivingBase, SwingTime> e : entitySwings.entrySet()) {
                    EntityLivingBase entity = e.getKey();
                    SwingTime swt = e.getValue();
                    ++swt.swing;
                    swt.swingPrev = swt.swingPrev;
                    if (swt.swing <= swt.swingMax) continue;
                    removes.add(entity);
                }
                for (EntityLivingBase entity : removes) {
                    entitySwings.remove((Object)entity);
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        EntityClientPlayerMP entityplayer;
        if (event.phase == TickEvent.Phase.START && (entityplayer = Minecraft.getMinecraft().thePlayer) != null) {
            this.tryUpdateSwing((EntityLivingBase)entityplayer);
        }
    }

    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event) {
        this.tryUpdateSwing((EntityLivingBase)event.entityPlayer);
    }

    @SubscribeEvent
    public void preRenderLiving(RenderLivingEvent.Pre event) {
        this.tryUpdateSwing(event.entity);
    }

    private void tryUpdateSwing(EntityLivingBase entity) {
        if (entity == Minecraft.getMinecraft().thePlayer) {
            if (LOTRAttackTiming.fullAttackTime > 0) {
                float max = LOTRAttackTiming.fullAttackTime;
                float swing = (max - (float)LOTRAttackTiming.attackTime) / max;
                float pre = (max - (float)LOTRAttackTiming.prevAttackTime) / max;
                swing /= swingFactor;
                pre /= swingFactor;
                if (swing <= 1.0f) {
                    entity.swingProgress = swing;
                    entity.prevSwingProgress = pre;
                }
            }
        } else {
            SwingTime swt = entitySwings.get((Object)entity);
            if (swt != null) {
                entity.swingProgress = (float)swt.swing / (float)swt.swingMax;
                entity.prevSwingProgress = (float)swt.swingPrev / (float)swt.swingMax;
            }
        }
    }

    private static class SwingTime {
        public int swingPrev;
        public int swing;
        public int swingMax;

        private SwingTime() {
        }
    }

}

