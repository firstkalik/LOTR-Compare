/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.event.ForgeEventFactory
 */
package lotr.common.world.spawning;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTravellingTrader;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeEventFactory;

public class LOTRTravellingTraderSpawner {
    private static Random rand = new Random();
    private Class theEntityClass;
    public String entityClassName;
    private int timeUntilTrader;

    public LOTRTravellingTraderSpawner(Class<? extends LOTREntityNPC> entityClass) {
        this.theEntityClass = entityClass;
        this.entityClassName = LOTREntities.getStringFromClass(this.theEntityClass);
    }

    private static int getRandomTraderTime() {
        float minHours = 0.8f;
        float maxHours = 10.0f;
        return MathHelper.getRandomIntegerInRange((Random)rand, (int)((int)(minHours * 3600.0f) * 20), (int)((int)(maxHours * 3600.0f) * 20));
    }

    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("TraderTime", this.timeUntilTrader);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.timeUntilTrader = nbt.hasKey("TraderTime") ? nbt.getInteger("TraderTime") : LOTRTravellingTraderSpawner.getRandomTraderTime();
    }

    public void performSpawning(World world) {
        if (this.timeUntilTrader > 0) {
            --this.timeUntilTrader;
        } else if (world.rand.nextInt(1000) == 0) {
            boolean spawned = false;
            LOTREntityNPC entityTrader = (LOTREntityNPC)EntityList.createEntityByName((String)LOTREntities.getStringFromClass(this.theEntityClass), (World)world);
            LOTRTravellingTrader trader = (LOTRTravellingTrader)((Object)entityTrader);
            block0: for (int players = 0; players < world.playerEntities.size(); ++players) {
                EntityPlayer entityplayer = (EntityPlayer)world.playerEntities.get(players);
                if (!(LOTRLevelData.getData(entityplayer).getAlignment(entityTrader.getFaction()) >= 0.0f)) continue;
                for (int attempts = 0; attempts < 16; ++attempts) {
                    int k;
                    float angle = world.rand.nextFloat() * 360.0f;
                    int i = MathHelper.floor_double((double)entityplayer.posX) + MathHelper.floor_double((double)(MathHelper.sin((float)angle) * (float)(48 + world.rand.nextInt(33))));
                    BiomeGenBase biome = world.getBiomeGenForCoords(i, k = MathHelper.floor_double((double)entityplayer.posZ) + MathHelper.floor_double((double)(MathHelper.cos((float)angle) * (float)(48 + world.rand.nextInt(33)))));
                    if (!(biome instanceof LOTRBiome) || !((LOTRBiome)biome).canSpawnTravellingTrader(this.theEntityClass)) continue;
                    int j = world.getHeightValue(i, k);
                    Block block = world.getBlock(i, j - 1, k);
                    if (j <= 62 || block != biome.topBlock && block != biome.fillerBlock || world.getBlock(i, j, k).isNormalCube() || world.getBlock(i, j + 1, k).isNormalCube()) continue;
                    entityTrader.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
                    entityTrader.liftSpawnRestrictions = true;
                    Event.Result canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entityTrader, (World)world, (float)((float)entityTrader.posX), (float)((float)entityTrader.posY), (float)((float)entityTrader.posZ));
                    if (canSpawn != Event.Result.ALLOW && (canSpawn != Event.Result.DEFAULT || !entityTrader.getCanSpawnHere())) continue;
                    entityTrader.liftSpawnRestrictions = false;
                    entityTrader.spawnRidingHorse = false;
                    world.spawnEntityInWorld((Entity)entityTrader);
                    entityTrader.onSpawnWithEgg(null);
                    trader.startTraderVisiting(entityplayer);
                    spawned = true;
                    this.timeUntilTrader = LOTRTravellingTraderSpawner.getRandomTraderTime();
                    LOTRLevelData.markDirty();
                    break block0;
                }
            }
            if (!spawned) {
                this.timeUntilTrader = 200 + world.rand.nextInt(400);
            }
        }
    }
}

