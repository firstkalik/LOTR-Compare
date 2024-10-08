/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.entity.npc.LOTRTraderNPCInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityTraderRespawn
extends Entity {
    private static int MAX_SCALE = 40;
    private int timeUntilSpawn;
    private int prevBobbingTime;
    private int bobbingTime;
    private String traderClassID;
    private boolean traderHasHome;
    private int traderHomeX;
    private int traderHomeY;
    private int traderHomeZ;
    private float traderHomeRadius;
    private String traderLocationName;
    private NBTTagCompound traderData;
    public float spawnerSpin;
    public float prevSpawnerSpin;

    public LOTREntityTraderRespawn(World world) {
        super(world);
        this.setSize(0.75f, 0.75f);
        this.spawnerSpin = this.rand.nextFloat() * 360.0f;
    }

    protected void entityInit() {
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)0);
        this.dataWatcher.addObject(18, (Object)"");
    }

    public int getScale() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setScale(int i) {
        this.dataWatcher.updateObject(16, (Object)i);
    }

    public boolean isSpawnImminent() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setSpawnImminent() {
        this.dataWatcher.updateObject(17, (Object)1);
    }

    public String getClientTraderString() {
        return this.dataWatcher.getWatchableObjectString(18);
    }

    public void setClientTraderString(String s) {
        this.dataWatcher.updateObject(18, (Object)s);
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Scale", this.getScale());
        nbt.setInteger("TimeUntilSpawn", this.timeUntilSpawn);
        nbt.setString("TraderClassID", this.traderClassID);
        nbt.setBoolean("TraderHasHome", this.traderHasHome);
        nbt.setInteger("TraderHomeX", this.traderHomeX);
        nbt.setInteger("TraderHomeY", this.traderHomeY);
        nbt.setInteger("TraderHomeZ", this.traderHomeZ);
        nbt.setFloat("TraderHomeRadius", this.traderHomeRadius);
        if (this.traderLocationName != null) {
            nbt.setString("TraderLocationName", this.traderLocationName);
        }
        if (this.traderData != null) {
            nbt.setTag("TraderData", (NBTBase)this.traderData);
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        this.setScale(nbt.getInteger("Scale"));
        this.timeUntilSpawn = nbt.getInteger("TimeUntilSpawn");
        if (this.timeUntilSpawn <= 1200) {
            this.setSpawnImminent();
        }
        this.traderClassID = nbt.getString("TraderClassID");
        this.traderHasHome = nbt.getBoolean("TraderHasHome");
        this.traderHomeX = nbt.getInteger("TraderHomeX");
        this.traderHomeY = nbt.getInteger("TraderHomeY");
        this.traderHomeZ = nbt.getInteger("TraderHomeZ");
        this.traderHomeRadius = nbt.getFloat("TraderHomeRadius");
        if (nbt.hasKey("TraderLocationName")) {
            this.traderLocationName = nbt.getString("TraderLocationName");
        }
        if (nbt.hasKey("TraderData")) {
            this.traderData = nbt.getCompoundTag("TraderData");
        }
    }

    public void copyTraderDataFrom(LOTREntityNPC entity) {
        this.traderClassID = LOTREntities.getStringFromClass(entity.getClass());
        this.traderHasHome = entity.hasHome();
        if (this.traderHasHome) {
            ChunkCoordinates home = entity.getHomePosition();
            this.traderHomeX = home.posX;
            this.traderHomeY = home.posY;
            this.traderHomeZ = home.posZ;
            this.traderHomeRadius = entity.func_110174_bM();
        }
        if (entity.getHasSpecificLocationName()) {
            this.traderLocationName = entity.npcLocationName;
        }
        if (entity instanceof LOTRTradeable) {
            LOTRTraderNPCInfo traderInfo = entity.traderNPCInfo;
            this.traderData = new NBTTagCompound();
            traderInfo.writeToNBT(this.traderData);
        }
    }

    public void onSpawn() {
        this.motionY = 0.25;
        this.timeUntilSpawn = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)10, (int)30) * 1200;
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public void applyEntityCollision(Entity entity) {
    }

    public boolean hitByEntity(Entity entity) {
        if (entity instanceof EntityPlayer) {
            return this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)((EntityPlayer)entity)), 0.0f);
        }
        return false;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        Entity entity = damagesource.getEntity();
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode) {
            if (!this.worldObj.isRemote) {
                Block.SoundType sound = Blocks.glass.stepSound;
                this.worldObj.playSoundAtEntity((Entity)this, sound.getBreakSound(), (sound.getVolume() + 1.0f) / 2.0f, sound.getPitch() * 0.8f);
                this.worldObj.setEntityState((Entity)this, (byte)16);
                this.setDead();
            }
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte b) {
        if (b == 16) {
            for (int l = 0; l < 16; ++l) {
                this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem((Item)LOTRMod.silverCoin), this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
            }
        } else {
            super.handleHealthUpdate(b);
        }
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevSpawnerSpin = this.spawnerSpin;
        this.spawnerSpin = this.isSpawnImminent() ? (this.spawnerSpin = this.spawnerSpin + 24.0f) : (this.spawnerSpin = this.spawnerSpin + 6.0f);
        this.prevSpawnerSpin = MathHelper.wrapAngleTo180_float((float)this.prevSpawnerSpin);
        this.spawnerSpin = MathHelper.wrapAngleTo180_float((float)this.spawnerSpin);
        if (this.getScale() < MAX_SCALE) {
            if (!this.worldObj.isRemote) {
                this.setScale(this.getScale() + 1);
            }
            this.motionX = 0.0;
            this.motionY *= 0.9;
            this.motionZ = 0.0;
        } else {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        if (!this.worldObj.isRemote) {
            this.setClientTraderString(this.traderClassID);
            if (!this.isSpawnImminent() && this.timeUntilSpawn <= 1200) {
                this.setSpawnImminent();
            }
            if (this.timeUntilSpawn > 0) {
                --this.timeUntilSpawn;
            } else {
                boolean flag = false;
                Entity entity = EntityList.createEntityByName((String)this.traderClassID, (World)this.worldObj);
                if (entity != null && entity instanceof LOTREntityNPC) {
                    LOTREntityNPC trader = (LOTREntityNPC)entity;
                    trader.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
                    trader.spawnRidingHorse = false;
                    trader.liftSpawnRestrictions = true;
                    this.boundingBox.offset(0.0, 100.0, 0.0);
                    if (trader.getCanSpawnHere()) {
                        trader.liftSpawnRestrictions = false;
                        trader.onSpawnWithEgg(null);
                        if (this.traderHasHome) {
                            trader.setHomeArea(this.traderHomeX, this.traderHomeY, this.traderHomeZ, Math.round(this.traderHomeRadius));
                        }
                        if (this.traderLocationName != null) {
                            trader.setSpecificLocationName(this.traderLocationName);
                        }
                        flag = this.worldObj.spawnEntityInWorld((Entity)trader);
                        if (trader instanceof LOTRTradeable && this.traderData != null) {
                            trader.traderNPCInfo.readFromNBT(this.traderData);
                        }
                    }
                    this.boundingBox.offset(0.0, -100.0, 0.0);
                }
                if (flag) {
                    this.playSound("random.pop", 1.0f, 0.5f + this.rand.nextFloat() * 0.5f);
                    this.setDead();
                } else {
                    this.timeUntilSpawn = 60;
                    this.setLocationAndAngles(this.posX, this.posY + 1.0, this.posZ, this.rotationYaw, this.rotationPitch);
                }
            }
        } else if (this.isSpawnImminent()) {
            this.prevBobbingTime = this.bobbingTime++;
        }
    }

    public float getScaleFloat(float tick) {
        float scale = this.getScale();
        if (scale < (float)MAX_SCALE) {
            scale += tick;
        }
        return scale / (float)MAX_SCALE;
    }

    public float getBobbingOffset(float tick) {
        float f = this.bobbingTime - this.prevBobbingTime;
        return MathHelper.sin((float)(((float)this.prevBobbingTime + (f *= tick)) / 5.0f)) * 0.25f;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        int entityID = LOTREntities.getIDFromString(this.getClientTraderString());
        if (entityID > 0) {
            return new ItemStack(LOTRMod.spawnEgg, 1, entityID);
        }
        return null;
    }
}

