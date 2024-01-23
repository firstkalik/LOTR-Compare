/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import java.util.Random;
import lotr.common.entity.LOTRBannerProtectable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class LOTREntityRugBase
extends Entity
implements LOTRBannerProtectable {
    private int timeSinceLastGrowl = this.getTimeUntilGrowl();

    public LOTREntityRugBase(World world) {
        super(world);
    }

    protected void entityInit() {
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
        if (!this.worldObj.isRemote) {
            if (this.timeSinceLastGrowl > 0) {
                --this.timeSinceLastGrowl;
            } else if (this.rand.nextInt(5000) == 0) {
                this.worldObj.playSoundAtEntity((Entity)this, this.getRugNoise(), 1.0f, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2f + 1.0f);
                this.timeSinceLastGrowl = this.getTimeUntilGrowl();
            }
        }
    }

    private int getTimeUntilGrowl() {
        return (60 + this.rand.nextInt(150)) * 20;
    }

    protected abstract String getRugNoise();

    protected void readEntityFromNBT(NBTTagCompound nbt) {
    }

    protected void writeEntityToNBT(NBTTagCompound nbt) {
    }

    protected abstract ItemStack getRugItem();

    public boolean attackEntityFrom(DamageSource damagesource, float f) {
        if (!this.worldObj.isRemote && !this.isDead) {
            boolean creative;
            Block.SoundType blockSound = Blocks.wool.stepSound;
            this.worldObj.playSoundAtEntity((Entity)this, blockSound.getBreakSound(), (blockSound.getVolume() + 1.0f) / 2.0f, blockSound.getPitch() * 0.8f);
            Entity attacker = damagesource.getEntity();
            boolean bl = creative = attacker instanceof EntityPlayer && ((EntityPlayer)attacker).capabilities.isCreativeMode;
            if (!creative) {
                this.entityDropItem(this.getRugItem(), 0.0f);
            }
            this.setDead();
        }
        return true;
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return this.getRugItem();
    }
}

