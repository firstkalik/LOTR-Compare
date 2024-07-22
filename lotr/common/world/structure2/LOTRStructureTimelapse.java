/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.world.WorldEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 */
package lotr.common.world.structure2;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRConfig;
import lotr.common.world.structure.LOTRWorldGenStructureBase;
import lotr.common.world.structure2.LOTRWorldGenStructureBase2;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

public class LOTRStructureTimelapse {
    private static List<ThreadTimelapse> allThreads = Collections.synchronizedList(new ArrayList());

    public LOTRStructureTimelapse() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public static void start(WorldGenerator gen, World world, int i, int j, int k) {
        new ThreadTimelapse(gen, world, i, j, k).start();
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        for (ThreadTimelapse thr : allThreads) {
            thr.interrupt();
        }
        allThreads.clear();
    }

    public static class ThreadTimelapse
    extends Thread {
        private WorldGenerator structureGen;
        private World theWorld;
        private int posX;
        private int posY;
        private int posZ;

        public ThreadTimelapse(WorldGenerator gen, World world, int i, int j, int k) {
            this.structureGen = gen;
            this.theWorld = world;
            this.posX = i;
            this.posY = j;
            this.posZ = k;
        }

        @Override
        public void start() {
            this.setDaemon(true);
            super.start();
            allThreads.add(this);
        }

        @Override
        public void run() {
            if (this.structureGen instanceof LOTRWorldGenStructureBase2) {
                LOTRWorldGenStructureBase2 str2 = (LOTRWorldGenStructureBase2)this.structureGen;
                str2.threadTimelapse = this;
                str2.generateWithSetRotation(this.theWorld, this.theWorld.rand, this.posX, this.posY, this.posZ, str2.usingPlayerRotation());
            } else if (this.structureGen instanceof LOTRWorldGenStructureBase) {
                LOTRWorldGenStructureBase str = (LOTRWorldGenStructureBase)this.structureGen;
                str.threadTimelapse = this;
                str.generate(this.theWorld, this.theWorld.rand, this.posX, this.posY, this.posZ);
            }
            allThreads.remove(this);
        }

        public void onBlockSet() {
            if (LOTRConfig.strTimelapse) {
                try {
                    Thread.sleep(LOTRConfig.strTimelapseInterval);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

