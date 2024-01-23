/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Direction
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.entity.item;

import lotr.common.LOTRMod;
import lotr.common.entity.LOTRBannerProtectable;
import lotr.common.item.LOTRItemBossTrophy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTREntityBossTrophy
extends Entity
implements LOTRBannerProtectable {
    public LOTREntityBossTrophy(World world) {
        super(world);
        this.setSize(1.0f, 1.0f);
    }

    protected void entityInit() {
        this.dataWatcher.addObject(18, (Object)0);
        this.dataWatcher.addObject(19, (Object)0);
        this.dataWatcher.addObject(20, (Object)0);
    }

    private int getTrophyTypeID() {
        return this.dataWatcher.getWatchableObjectByte(18);
    }

    private void setTrophyTypeID(int i) {
        this.dataWatcher.updateObject(18, (Object)((byte)i));
    }

    public void setTrophyType(LOTRItemBossTrophy.TrophyType type) {
        this.setTrophyTypeID(type.trophyID);
    }

    public LOTRItemBossTrophy.TrophyType getTrophyType() {
        return LOTRItemBossTrophy.TrophyType.forID(this.getTrophyTypeID());
    }

    public boolean isTrophyHanging() {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }

    public void setTrophyHanging(boolean flag) {
        this.dataWatcher.updateObject(19, (Object)(flag ? (byte)1 : 0));
    }

    public int getTrophyFacing() {
        byte i = this.dataWatcher.getWatchableObjectByte(20);
        if (i < 0 || i >= Direction.directions.length) {
            i = 0;
        }
        return i;
    }

    public void setTrophyFacing(int i) {
        this.dataWatcher.updateObject(20, (Object)((byte)i));
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    public void onUpdate() {
        super.onUpdate();
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.isTrophyHanging()) {
            if (!(this.hangingOnValidSurface() || this.worldObj.isRemote || this.isDead)) {
                this.dropAsItem(true);
            }
        } else {
            this.motionY -= 0.04;
            this.func_145771_j(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0, this.posZ);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            float f = 0.98f;
            if (this.onGround) {
                f = 0.588f;
                Block i = this.worldObj.getBlock(MathHelper.floor_double((double)this.posX), MathHelper.floor_double((double)this.boundingBox.minY) - 1, MathHelper.floor_double((double)this.posZ));
                if (i.getMaterial() != Material.air) {
                    f = i.slipperiness * 0.98f;
                }
            }
            this.motionX *= (double)f;
            this.motionY *= 0.98;
            this.motionZ *= (double)f;
            if (this.onGround) {
                this.motionY *= -0.5;
            }
        }
    }

    public boolean hangingOnValidSurface() {
        if (this.isTrophyHanging()) {
            int direction = this.getTrophyFacing();
            int opposite = Direction.rotateOpposite[direction];
            int dx = Direction.offsetX[opposite];
            int dz = Direction.offsetZ[opposite];
            int blockX = MathHelper.floor_double((double)this.posX);
            int blockY = MathHelper.floor_double((double)this.boundingBox.minY);
            int blockZ = MathHelper.floor_double((double)this.posZ);
            Block block = this.worldObj.getBlock(blockX += dx, blockY, blockZ += dz);
            int blockSide = Direction.directionToFacing[direction];
            return block.isSideSolid((IBlockAccess)this.worldObj, blockX, blockY, blockZ, ForgeDirection.getOrientation((int)blockSide));
        }
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setByte("TrophyType", (byte)this.getTrophyTypeID());
        nbt.setBoolean("TrophyHanging", this.isTrophyHanging());
        nbt.setByte("TrophyFacing", (byte)this.getTrophyFacing());
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        this.setTrophyTypeID(nbt.getByte("TrophyType"));
        this.setTrophyHanging(nbt.getBoolean("TrophyHanging"));
        this.setTrophyFacing(nbt.getByte("TrophyFacing"));
    }

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        if (!this.worldObj.isRemote && !this.isDead && damagesource.getSourceOfDamage() instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)damagesource.getSourceOfDamage();
            this.dropAsItem(!entityplayer.capabilities.isCreativeMode);
            return true;
        }
        return false;
    }

    private void dropAsItem(boolean dropItem) {
        this.worldObj.playSoundAtEntity((Entity)this, Blocks.stone.stepSound.getBreakSound(), (Blocks.stone.stepSound.getVolume() + 1.0f) / 2.0f, Blocks.stone.stepSound.getPitch() * 0.8f);
        if (dropItem) {
            this.entityDropItem(new ItemStack(LOTRMod.bossTrophy, 1, this.getTrophyType().trophyID), 0.0f);
        }
        this.setDead();
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.bossTrophy, 1, this.getTrophyType().trophyID);
    }
}

