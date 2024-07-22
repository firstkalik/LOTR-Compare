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
package lotr.common.item;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
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

public class LOTREntityArrowFire
extends EntityArrow
implements IEntityAdditionalSpawnData {
    public LOTREntityArrowFire(World world) {
        super(world);
        this.init();
    }

    public LOTREntityArrowFire(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
        this.init();
    }

    public LOTREntityArrowFire(World world, EntityLivingBase shooter, EntityLivingBase target, float charge, float inaccuracy) {
        super(world, shooter, target, charge, inaccuracy);
        this.init();
    }

    public LOTREntityArrowFire(World world, EntityLivingBase shooter, float charge) {
        super(world, shooter, charge);
        this.init();
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer) {
        boolean isInGround;
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);
        boolean bl = isInGround = nbt.getByte("inGround") == 1;
        if (!this.worldObj.isRemote && isInGround && this.arrowShake <= 0) {
            boolean pickup;
            boolean bl2 = pickup = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityplayer.capabilities.isCreativeMode;
            if (this.canBePickedUp == 1 && !entityplayer.inventory.addItemStackToInventory(new ItemStack(LOTRMod.arrowFire, 1))) {
                pickup = false;
            }
            if (pickup) {
                this.playSound("random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                entityplayer.onItemPickup((Entity)this, 1);
                this.setDead();
            }
        }
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

    public void writeSpawnData(ByteBuf data) {
        data.writeDouble(this.motionX);
        data.writeDouble(this.motionY);
        data.writeDouble(this.motionZ);
        data.writeInt(this.shootingEntity == null ? -1 : this.shootingEntity.getEntityId());
    }

    private void init() {
        Random random = new Random();
        int fireDuration = random.nextInt(80) + 20;
        this.setFire(fireDuration);
        LOTREnchantmentHelper.setProjectileEnchantment((Entity)this, LOTREnchantment.fire);
    }
}

