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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package lotr.common.entity.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRDimension;
import lotr.common.LOTRMod;
import lotr.common.world.LOTRTeleporter;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class LOTREntityPortal
extends Entity {
    public static int MAX_SCALE = 120;
    private float prevPortalRotation;
    private float portalRotation;

    public LOTREntityPortal(World world) {
        super(world);
        this.setSize(3.0f, 1.5f);
    }

    protected void entityInit() {
        this.dataWatcher.addObject(10, (Object)0);
    }

    public int getScale() {
        return this.dataWatcher.getWatchableObjectShort(10);
    }

    public void setScale(int i) {
        this.dataWatcher.updateObject(10, (Object)((short)i));
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Scale", this.getScale());
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        this.setScale(nbt.getInteger("Scale"));
    }

    public float getPortalRotation(float f) {
        return this.prevPortalRotation + (this.portalRotation - this.prevPortalRotation) * f;
    }

    public void onUpdate() {
        this.prevPortalRotation = this.portalRotation;
        this.portalRotation += 4.0f;
        while (this.portalRotation - this.prevPortalRotation < -180.0f) {
            this.prevPortalRotation -= 360.0f;
        }
        while (this.portalRotation - this.prevPortalRotation >= 180.0f) {
            this.prevPortalRotation += 360.0f;
        }
        if (!this.worldObj.isRemote && this.dimension != 0 && this.dimension != LOTRDimension.MIDDLE_EARTH.dimensionID) {
            this.setDead();
        }
        if (!this.worldObj.isRemote && this.getScale() < MAX_SCALE) {
            this.setScale(this.getScale() + 1);
        }
        if (this.getScale() >= MAX_SCALE) {
            int i;
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(8.0, 8.0, 8.0));
            for (Object player : players) {
                EntityPlayer entityplayer = (EntityPlayer)player;
                if (!this.boundingBox.intersectsWith(entityplayer.boundingBox) || entityplayer.ridingEntity != null || entityplayer.riddenByEntity != null) continue;
                LOTRMod.proxy.setInPortal(entityplayer);
            }
            List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(8.0, 8.0, 8.0));
            for (i = 0; i < entities.size(); ++i) {
                Entity entity = (Entity)entities.get(i);
                if (entity instanceof EntityPlayer || !this.boundingBox.intersectsWith(entity.boundingBox) || entity.ridingEntity != null || entity.riddenByEntity != null || entity.timeUntilPortal != 0) continue;
                this.transferEntity(entity);
            }
            if (this.rand.nextInt(50) == 0) {
                this.worldObj.playSoundAtEntity((Entity)this, "portal.portal", 0.5f, this.rand.nextFloat() * 0.4f + 0.8f);
            }
            for (i = 0; i < 2; ++i) {
                double d = this.posX - 3.0 + (double)(this.rand.nextFloat() * 6.0f);
                double d1 = this.posY - 0.75 + (double)(this.rand.nextFloat() * 3.0f);
                double d2 = this.posZ - 3.0 + (double)(this.rand.nextFloat() * 6.0f);
                double d3 = (this.posX - d) / 8.0;
                double d4 = (this.posY + 1.5 - d1) / 8.0;
                double d5 = (this.posZ - d2) / 8.0;
                double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                double d7 = 1.0 - d6;
                double d8 = 0.0;
                double d9 = 0.0;
                double d10 = 0.0;
                if (d7 > 0.0) {
                    d7 *= d7;
                    d8 += d3 / d6 * d7 * 0.2;
                    d9 += d4 / d6 * d7 * 0.2;
                    d10 += d5 / d6 * d7 * 0.2;
                }
                this.worldObj.spawnParticle("flame", d, d1, d2, d8, d9, d10);
            }
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean isEntityInvulnerable() {
        return true;
    }

    public boolean canBePushed() {
        return false;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    private void transferEntity(Entity entity) {
        if (!this.worldObj.isRemote) {
            int dimension = 0;
            if (entity.dimension == 0) {
                dimension = LOTRDimension.MIDDLE_EARTH.dimensionID;
            } else if (entity.dimension == LOTRDimension.MIDDLE_EARTH.dimensionID) {
                dimension = 0;
            }
            LOTRMod.transferEntityToDimension(entity, dimension, new LOTRTeleporter(DimensionManager.getWorld((int)dimension), true));
        }
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
                this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem((Item)LOTRMod.goldRing), this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
            }
        } else {
            super.handleHealthUpdate(b);
        }
    }

    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.goldRing);
    }
}

