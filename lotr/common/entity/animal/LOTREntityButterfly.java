/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.entity.animal;

import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockTorch;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRRandomSkinEntity;
import lotr.common.entity.animal.LOTRAmbientCreature;
import lotr.common.entity.animal.LOTRAmbientSpawnChecks;
import lotr.common.world.biome.LOTRBiomeGenFarHaradJungle;
import lotr.common.world.biome.LOTRBiomeGenLothlorien;
import lotr.common.world.biome.LOTRBiomeGenMirkwood;
import lotr.common.world.biome.LOTRBiomeGenWoodlandRealm;
import lotr.common.world.structure2.LOTRWorldGenElfHouse;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTREntityButterfly
extends EntityLiving
implements LOTRAmbientCreature,
LOTRRandomSkinEntity {
    private LOTRBlockTorch elfTorchBlock;
    private ChunkCoordinates currentFlightTarget;
    public int flapTime = 0;

    public LOTREntityButterfly(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
        this.dataWatcher.addObject(17, (Object)0);
    }

    public ButterflyType getButterflyType() {
        byte i = this.dataWatcher.getWatchableObjectByte(16);
        if (i < 0 || i >= ButterflyType.values().length) {
            i = 0;
        }
        return ButterflyType.values()[i];
    }

    public void setButterflyType(ButterflyType type) {
        this.setButterflyType(type.ordinal());
    }

    public void setButterflyType(int i) {
        this.dataWatcher.updateObject(16, (Object)((byte)i));
    }

    public boolean isButterflyStill() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public void setButterflyStill(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)(flag ? (byte)1 : 0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(MathHelper.getRandomDoubleInRange((Random)this.rand, (double)0.08, (double)0.12));
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = MathHelper.floor_double((double)this.posX);
        MathHelper.floor_double((double)this.posY);
        int k = MathHelper.floor_double((double)this.posZ);
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if (biome instanceof LOTRBiomeGenMirkwood || biome instanceof LOTRBiomeGenWoodlandRealm) {
            this.setButterflyType(ButterflyType.MIRKWOOD);
        } else if (biome instanceof LOTRBiomeGenLothlorien) {
            this.setButterflyType(ButterflyType.LORIEN);
        } else if (biome instanceof LOTRBiomeGenFarHaradJungle) {
            this.setButterflyType(ButterflyType.JUNGLE);
        } else {
            this.setButterflyType(ButterflyType.COMMON);
        }
        return data;
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity entity) {
    }

    protected void collideWithNearbyEntities() {
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.isButterflyStill()) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.posY = MathHelper.floor_double((double)this.posY);
            if (this.worldObj.isRemote) {
                if (this.rand.nextInt(200) == 0) {
                    this.flapTime = 40;
                }
                if (this.flapTime > 0) {
                    --this.flapTime;
                }
            }
        } else {
            this.motionY *= 0.6;
            if (this.worldObj.isRemote) {
                this.flapTime = 0;
            }
            if (this.getButterflyType() == ButterflyType.LORIEN) {
                LOTRBlockTorch.TorchParticle particle;
                double d = this.posX;
                double d1 = this.posY;
                double d2 = this.posZ;
                if (this.elfTorchBlock == null) {
                    Random torchRand = new Random();
                    torchRand.setSeed(this.entityUniqueID.getLeastSignificantBits());
                    this.elfTorchBlock = (LOTRBlockTorch)LOTRWorldGenElfHouse.getRandomTorch(torchRand);
                }
                if ((particle = this.elfTorchBlock.createTorchParticle(this.rand)) != null) {
                    particle.spawn(d, d1, d2);
                }
            }
        }
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.isButterflyStill()) {
            int k;
            int j;
            int i = MathHelper.floor_double((double)this.posX);
            if (!this.worldObj.getBlock(i, j = (int)this.posY - 1, k = MathHelper.floor_double((double)this.posZ)).isSideSolid((IBlockAccess)this.worldObj, i, j, k, ForgeDirection.UP) || this.rand.nextInt(400) == 0 || this.worldObj.getClosestPlayerToEntity((Entity)this, 3.0) != null) {
                this.setButterflyStill(false);
            }
        } else {
            if (!(this.currentFlightTarget == null || this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) && this.currentFlightTarget.posY >= 1)) {
                this.currentFlightTarget = null;
            }
            if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0f) {
                this.currentFlightTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }
            double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
            double d0 = (double)this.currentFlightTarget.posX + 0.5 - this.posX;
            double d1 = (double)this.currentFlightTarget.posY + 0.5 - this.posY;
            double d2 = (double)this.currentFlightTarget.posZ + 0.5 - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5 - this.motionX) * speed;
            this.motionY += (Math.signum(d1) * 0.7 - this.motionY) * speed;
            this.motionZ += (Math.signum(d2) * 0.5 - this.motionZ) * speed;
            float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / 3.141592653589793) - 90.0f;
            float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
            this.moveForward = 0.5f;
            this.rotationYaw += f1;
            if (this.rand.nextInt(150) == 0 && this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), (int)this.posY - 1, MathHelper.floor_double((double)this.posZ)).isNormalCube()) {
                this.setButterflyStill(true);
            }
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float f) {
    }

    protected void updateFallState(double d, boolean flag) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        boolean flag = super.attackEntityFrom(damagesource, f);
        if (flag && !this.worldObj.isRemote && this.isButterflyStill()) {
            this.setButterflyStill(false);
        }
        return flag;
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setButterflyType(nbt.getInteger("ButterflyType"));
        this.setButterflyStill(nbt.getBoolean("ButterflyStill"));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("ButterflyType", this.getButterflyType().ordinal());
        nbt.setBoolean("ButterflyStill", this.isButterflyStill());
    }

    protected boolean canDespawn() {
        return true;
    }

    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            return LOTRAmbientSpawnChecks.canSpawn(this, 8, 4, 32, 4, Material.plants, Material.vine);
        }
        return false;
    }

    public boolean allowLeashing() {
        return false;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

    public static enum ButterflyType {
        MIRKWOOD("mirkwood"),
        LORIEN("lorien"),
        COMMON("common"),
        JUNGLE("jungle");

        public final String textureDir;

        private ButterflyType(String s) {
            this.textureDir = s;
        }
    }

}

