/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package lotr.common.entity.item;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LOTREntityArrowExplosion
extends EntityArrow
implements IEntityAdditionalSpawnData {
    public LOTREntityArrowExplosion(World world) {
        super(world);
    }

    public LOTREntityArrowExplosion(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    public LOTREntityArrowExplosion(World world, EntityLivingBase shooter, EntityLivingBase target, float charge, float inaccuracy) {
        super(world, shooter, target, charge, inaccuracy);
    }

    public LOTREntityArrowExplosion(World world, EntityLivingBase shooter, float charge) {
        super(world, shooter, charge);
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.motionX);
        data.writeDouble(this.motionY);
        data.writeDouble(this.motionZ);
        data.writeInt(this.shootingEntity == null ? -1 : this.shootingEntity.getEntityId());
    }

    public void readSpawnData(ByteBuf data) {
        Entity entity;
        this.motionX = data.readDouble();
        this.motionY = data.readDouble();
        this.motionZ = data.readDouble();
        int id = data.readInt();
        if (id >= 0 && (entity = this.worldObj.getEntityByID(id)) != null) {
            this.shootingEntity = entity;
        }
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer) {
        boolean isInGround;
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);
        boolean bl = isInGround = nbt.getByte("inGround") == 1;
        if (!this.worldObj.isRemote && isInGround && this.arrowShake <= 0) {
            boolean pickup;
            boolean bl2 = pickup = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityplayer.capabilities.isCreativeMode;
            if (this.canBePickedUp == 1 && !entityplayer.inventory.addItemStackToInventory(new ItemStack(LOTRMod.arrowExplosion, 1))) {
                pickup = false;
            }
            if (pickup) {
                this.playSound("random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                entityplayer.onItemPickup((Entity)this, 1);
                this.setDead();
            }
        }
    }
}

