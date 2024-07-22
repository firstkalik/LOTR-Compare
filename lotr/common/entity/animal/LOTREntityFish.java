/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFishFood
 *  net.minecraft.item.ItemFishFood$FishType
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.animal;

import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTRRandomSkinEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityFish
extends EntityWaterMob
implements LOTRRandomSkinEntity {
    private ChunkCoordinates currentSwimTarget;
    private int swimTargetTime = 0;

    public LOTREntityFish(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
    }

    @Override
    public void setUniqueID(UUID uuid) {
        this.entityUniqueID = uuid;
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    public FishType getFishType() {
        byte i = this.dataWatcher.getWatchableObjectByte(16);
        if (i < 0 || i >= FishType.values().length) {
            i = 0;
        }
        return FishType.values()[i];
    }

    public void setFishType(FishType type) {
        this.setFishType(type.ordinal());
    }

    public void setFishType(int i) {
        this.dataWatcher.updateObject(16, (Object)((byte)i));
    }

    public String getFishTextureDir() {
        return this.getFishType().textureDir;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(MathHelper.getRandomDoubleInRange((Random)this.rand, (double)0.04, (double)0.08));
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = MathHelper.floor_double((double)this.posX);
        MathHelper.floor_double((double)this.posY);
        int k = MathHelper.floor_double((double)this.posZ);
        this.worldObj.getBiomeGenForCoords(i, k);
        if (this.rand.nextInt(30) == 0) {
            this.setFishType(FishType.CLOWNFISH);
        } else if (this.rand.nextInt(8) == 0) {
            this.setFishType(FishType.SALMON);
        } else {
            this.setFishType(FishType.COMMON);
        }
        return data;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setFishType(nbt.getInteger("FishType"));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("FishType", this.getFishType().ordinal());
    }

    protected void dropFewItems(boolean flag, int i) {
        int drops = this.rand.nextInt(2 + i);
        for (int l = 0; l < drops; ++l) {
            if (this.getFishType() == FishType.SALMON) {
                this.entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 0.0f);
                continue;
            }
            if (this.getFishType() == FishType.CLOWNFISH) {
                this.entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a()), 0.0f);
                continue;
            }
            this.entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a()), 0.0f);
        }
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.isInWater() && !this.worldObj.isRemote) {
            this.motionX = 0.0;
            this.motionY -= 0.08;
            this.motionY *= 0.98;
            this.motionZ = 0.0;
        }
    }

    public boolean isInWater() {
        double d = 0.5;
        return this.worldObj.isMaterialInBB(this.boundingBox.expand(d, d, d), Material.water);
    }

    protected void updateEntityActionState() {
        ++this.entityAge;
        if (this.currentSwimTarget != null && !this.isValidSwimTarget(this.currentSwimTarget.posX, this.currentSwimTarget.posY, this.currentSwimTarget.posZ)) {
            this.currentSwimTarget = null;
            this.swimTargetTime = 0;
        }
        if (this.currentSwimTarget == null || this.rand.nextInt(200) == 0 || this.getDistanceSqToSwimTarget() < 4.0) {
            for (int l = 0; l < 16; ++l) {
                int i = MathHelper.floor_double((double)this.posX);
                int j = MathHelper.floor_double((double)this.posY);
                int k = MathHelper.floor_double((double)this.posZ);
                if (!this.isValidSwimTarget(i += this.rand.nextInt(16) - this.rand.nextInt(16), j += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-2, (int)4), k += this.rand.nextInt(16) - this.rand.nextInt(16))) continue;
                this.currentSwimTarget = new ChunkCoordinates(i, j, k);
                this.swimTargetTime = 0;
                break;
            }
        }
        if (this.currentSwimTarget != null) {
            double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
            double d0 = (double)this.currentSwimTarget.posX + 0.5 - this.posX;
            double d1 = (double)this.currentSwimTarget.posY + 0.5 - this.posY;
            double d2 = (double)this.currentSwimTarget.posZ + 0.5 - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5 - this.motionX) * speed;
            this.motionY += (Math.signum(d1) * 0.5 - this.motionY) * speed;
            this.motionZ += (Math.signum(d2) * 0.5 - this.motionZ) * speed;
            float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / 3.141592653589793) - 90.0f;
            float f1 = MathHelper.wrapAngleTo180_float((float)(f - this.rotationYaw));
            this.moveForward = 0.5f;
            this.rotationYaw += f1;
            ++this.swimTargetTime;
            if (this.swimTargetTime >= 200) {
                this.currentSwimTarget = null;
                this.swimTargetTime = 0;
            }
        }
        this.despawnEntity();
    }

    private boolean isValidSwimTarget(int i, int j, int k) {
        return this.worldObj.getBlock(i, j, k).getMaterial() == Material.water;
    }

    private double getDistanceSqToSwimTarget() {
        double d = (double)this.currentSwimTarget.posX + 0.5;
        double d1 = (double)this.currentSwimTarget.posY + 0.5;
        double d2 = (double)this.currentSwimTarget.posZ + 0.5;
        return this.getDistanceSq(d, d1, d2);
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }

    public static enum FishType {
        COMMON("common"),
        SALMON("salmon"),
        CLOWNFISH("clownfish");

        public final String textureDir;

        private FishType(String s) {
            this.textureDir = s;
        }
    }

}

