/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IThrowableEntity
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EntityTracker
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S0DPacketCollectItem
 *  net.minecraft.network.play.server.S2BPacketChangeGameState
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package lotr.common.entity.projectile;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import lotr.common.item.LOTRWeaponStats;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class LOTREntityProjectileBase
extends Entity
implements IThrowableEntity,
IProjectile {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData = 0;
    public boolean inGround = false;
    public int shake = 0;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir = 0;
    public int canBePickedUp = 0;
    public int knockbackStrength = 0;

    public LOTREntityProjectileBase(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
    }

    public LOTREntityProjectileBase(World world, ItemStack item, double d, double d1, double d2) {
        super(world);
        this.setProjectileItem(item);
        this.setSize(0.5f, 0.5f);
        this.setPosition(d, d1, d2);
        this.yOffset = 0.0f;
    }

    public LOTREntityProjectileBase(World world, EntityLivingBase entityliving, ItemStack item, float charge) {
        super(world);
        this.setProjectileItem(item);
        this.shootingEntity = entityliving;
        if (entityliving instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.setSize(0.5f, 0.5f);
        this.setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        this.posX -= (double)(MathHelper.cos((float)(this.rotationYaw / 180.0f * 3.1415927f)) * 0.16f);
        this.posY -= 0.1;
        this.posZ -= (double)(MathHelper.sin((float)(this.rotationYaw / 180.0f * 3.1415927f)) * 0.16f);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0f;
        this.motionX = -MathHelper.sin((float)(this.rotationYaw / 180.0f * 3.1415927f)) * MathHelper.cos((float)(this.rotationPitch / 180.0f * 3.1415927f));
        this.motionZ = MathHelper.cos((float)(this.rotationYaw / 180.0f * 3.1415927f)) * MathHelper.cos((float)(this.rotationPitch / 180.0f * 3.1415927f));
        this.motionY = -MathHelper.sin((float)(this.rotationPitch / 180.0f * 3.1415927f));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, charge * 1.5f, 1.0f);
    }

    public LOTREntityProjectileBase(World world, EntityLivingBase entityliving, EntityLivingBase target, ItemStack item, float charge, float inaccuracy) {
        super(world);
        this.setProjectileItem(item);
        this.shootingEntity = entityliving;
        if (entityliving instanceof EntityPlayer) {
            this.canBePickedUp = 1;
        }
        this.setSize(0.5f, 0.5f);
        this.posY = entityliving.posY + (double)entityliving.getEyeHeight() - 0.1;
        double d = target.posX - entityliving.posX;
        double d1 = target.posY + (double)target.getEyeHeight() - 0.7 - this.posY;
        double d2 = target.posZ - entityliving.posZ;
        double d3 = MathHelper.sqrt_double((double)(d * d + d2 * d2));
        if (d3 >= 1.0E-7) {
            float f = (float)(Math.atan2(d2, d) * 180.0 / 3.141592653589793) - 90.0f;
            float f1 = (float)(-(Math.atan2(d1, d3) * 180.0 / 3.141592653589793));
            double d4 = d / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(entityliving.posX + d4, this.posY, entityliving.posZ + d5, f, f1);
            this.yOffset = 0.0f;
            float d6 = (float)d3 * 0.2f;
            this.setThrowableHeading(d, d1 + (double)d6, d2, charge * 1.5f, inaccuracy);
        }
    }

    public Entity getThrower() {
        return this.shootingEntity;
    }

    public void setThrower(Entity entity) {
        this.shootingEntity = entity;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isInRangeToRenderDist(double d) {
        double d2;
        double d1 = this.boundingBox.getAverageEdgeLength() * 4.0;
        return d < d2 * (d1 *= 64.0);
    }

    protected void entityInit() {
        this.dataWatcher.addObject(17, (Object)0);
        this.dataWatcher.addObjectByDataType(18, 5);
    }

    public ItemStack getProjectileItem() {
        return this.dataWatcher.getWatchableObjectItemStack(18);
    }

    public void setProjectileItem(ItemStack item) {
        this.dataWatcher.updateObject(18, (Object)item);
    }

    public void setThrowableHeading(double d, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.sqrt_double((double)(d * d + d1 * d1 + d2 * d2));
        d /= (double)f2;
        d1 /= (double)f2;
        d2 /= (double)f2;
        d += this.rand.nextGaussian() * 0.0075 * (double)f1;
        d1 += this.rand.nextGaussian() * 0.0075 * (double)f1;
        d2 += this.rand.nextGaussian() * 0.0075 * (double)f1;
        this.motionX = d *= (double)f;
        this.motionY = d1 *= (double)f;
        this.motionZ = d2 *= (double)f;
        float f3 = MathHelper.sqrt_double((double)(d * d + d2 * d2));
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180.0 / 3.141592653589793);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, f3) * 180.0 / 3.141592653589793);
        this.ticksInGround = 0;
    }

    public void setVelocity(double d, double d1, double d2) {
        this.motionX = d;
        this.motionY = d1;
        this.motionZ = d2;
        if (this.prevRotationPitch == 0.0f && this.prevRotationYaw == 0.0f) {
            float f = MathHelper.sqrt_double((double)(d * d + d2 * d2));
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(d, d2) * 180.0 / 3.141592653589793);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(d1, f) * 180.0 / 3.141592653589793);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    public void onUpdate() {
        Block block;
        super.onUpdate();
        if (this.prevRotationPitch == 0.0f && this.prevRotationYaw == 0.0f) {
            float f = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / 3.141592653589793);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0 / 3.141592653589793);
        }
        if ((block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile)) != Blocks.air) {
            block.setBlockBoundsBasedOnState((IBlockAccess)this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ))) {
                this.inGround = true;
            }
        }
        if (this.shake > 0) {
            --this.shake;
        }
        if (this.inGround) {
            Block j = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
            int k = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
            if (j == this.inTile && k == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround >= this.maxTicksInGround()) {
                    this.setDead();
                }
            } else {
                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2f);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2f);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2f);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            int l;
            ++this.ticksInAir;
            Vec3 vec3d = Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ);
            Vec3 vec3d1 = Vec3.createVectorHelper((double)(this.posX + this.motionX), (double)(this.posY + this.motionY), (double)(this.posZ + this.motionZ));
            MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec3d, vec3d1, false, true, false);
            vec3d = Vec3.createVectorHelper((double)this.posX, (double)this.posY, (double)this.posZ);
            vec3d1 = Vec3.createVectorHelper((double)(this.posX + this.motionX), (double)(this.posY + this.motionY), (double)(this.posZ + this.motionZ));
            if (movingobjectposition != null) {
                vec3d1 = Vec3.createVectorHelper((double)movingobjectposition.hitVec.xCoord, (double)movingobjectposition.hitVec.yCoord, (double)movingobjectposition.hitVec.zCoord);
            }
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
            double d = 0.0;
            for (l = 0; l < list.size(); ++l) {
                float f5;
                double d2;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(l);
                if (!entity1.canBeCollidedWith() || entity1 == this.shootingEntity && this.ticksInAir < 5 || (movingobjectposition1 = entity1.boundingBox.expand((double)0.3f, (double)(f5 = 0.3f), (double)f5).calculateIntercept(vec3d, vec3d1)) == null) continue;
                double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
                if (d2 >= d && d != 0.0) continue;
                entity = entity1;
                d = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            if (movingobjectposition != null && movingobjectposition.entityHit instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer)) {
                    movingobjectposition = null;
                }
            }
            if (movingobjectposition != null) {
                Entity hitEntity = movingobjectposition.entityHit;
                if (hitEntity != null) {
                    ItemStack itemstack = this.getProjectileItem();
                    int damageInt = MathHelper.ceiling_double_int((double)this.getBaseImpactDamage(hitEntity, itemstack));
                    int fireAspect = 0;
                    if (itemstack != null) {
                        int n = this.knockbackStrength = this.shootingEntity instanceof EntityLivingBase && hitEntity instanceof EntityLivingBase ? (this.knockbackStrength = this.knockbackStrength + EnchantmentHelper.getKnockbackModifier((EntityLivingBase)((EntityLivingBase)this.shootingEntity), (EntityLivingBase)((EntityLivingBase)hitEntity))) : (this.knockbackStrength = this.knockbackStrength + LOTRWeaponStats.getTotalKnockback(itemstack));
                    }
                    if (this.getIsCritical()) {
                        damageInt += this.rand.nextInt(damageInt / 2 + 2);
                    }
                    double[] prevMotion = new double[]{hitEntity.motionX, hitEntity.motionY, hitEntity.motionZ};
                    DamageSource damagesource = this.getDamageSource();
                    if (hitEntity.attackEntityFrom(damagesource, (float)damageInt)) {
                        double[] newMotion = new double[]{hitEntity.motionX, hitEntity.motionY, hitEntity.motionZ};
                        float kbf = this.getKnockbackFactor();
                        hitEntity.motionX = prevMotion[0] + (newMotion[0] - prevMotion[0]) * (double)kbf;
                        hitEntity.motionY = prevMotion[1] + (newMotion[1] - prevMotion[1]) * (double)kbf;
                        hitEntity.motionZ = prevMotion[2] + (newMotion[2] - prevMotion[2]) * (double)kbf;
                        if (this.isBurning()) {
                            hitEntity.setFire(5);
                        }
                        if (hitEntity instanceof EntityLivingBase) {
                            EntityLivingBase hitEntityLiving = (EntityLivingBase)hitEntity;
                            if (this.knockbackStrength > 0) {
                                float f;
                                float knockback = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
                                if (f > 0.0f) {
                                    hitEntityLiving.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6 / (double)knockback, 0.1, this.motionZ * (double)this.knockbackStrength * 0.6 / (double)knockback);
                                }
                            }
                            if (fireAspect > 0) {
                                hitEntityLiving.setFire(fireAspect * 4);
                            }
                            if (this.shootingEntity instanceof EntityLivingBase) {
                                EnchantmentHelper.func_151384_a((EntityLivingBase)hitEntityLiving, (Entity)this.shootingEntity);
                                EnchantmentHelper.func_151385_b((EntityLivingBase)((EntityLivingBase)this.shootingEntity), (Entity)hitEntityLiving);
                            }
                            if (this.shootingEntity instanceof EntityPlayerMP && hitEntityLiving instanceof EntityPlayer) {
                                ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                        this.worldObj.playSoundAtEntity((Entity)this, this.getImpactSound(), 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                        this.onCollideWithTarget(hitEntity);
                    } else {
                        this.motionX *= -0.1;
                        this.motionY *= -0.1;
                        this.motionZ *= -0.1;
                        this.rotationYaw += 180.0f;
                        this.prevRotationYaw += 180.0f;
                        this.ticksInAir = 0;
                    }
                } else {
                    this.xTile = movingobjectposition.blockX;
                    this.yTile = movingobjectposition.blockY;
                    this.zTile = movingobjectposition.blockZ;
                    this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
                    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
                    this.motionX = (float)(movingobjectposition.hitVec.xCoord - this.posX);
                    this.motionY = (float)(movingobjectposition.hitVec.yCoord - this.posY);
                    this.motionZ = (float)(movingobjectposition.hitVec.zCoord - this.posZ);
                    float f2 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ));
                    this.posX -= this.motionX / (double)f2 * 0.05;
                    this.posY -= this.motionY / (double)f2 * 0.05;
                    this.posZ -= this.motionZ / (double)f2 * 0.05;
                    this.worldObj.playSoundAtEntity((Entity)this, this.getImpactSound(), 1.0f, 1.2f / (this.rand.nextFloat() * 0.2f + 0.9f));
                    this.inGround = true;
                    this.shake = 7;
                    this.setIsCritical(false);
                    if (this.inTile.getMaterial() != Material.air) {
                        this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, (Entity)this);
                    }
                }
            }
            if (this.getIsCritical()) {
                for (l = 0; l < 4; ++l) {
                    this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)l / 4.0, this.posY + this.motionY * (double)l / 4.0, this.posZ + this.motionZ * (double)l / 4.0, -this.motionX, -this.motionY + 0.2, -this.motionZ);
                }
            }
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f3 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ));
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / 3.141592653589793);
            this.rotationPitch = (float)(Math.atan2(this.motionY, f3) * 180.0 / 3.141592653589793);
            while (this.rotationPitch - this.prevRotationPitch < -180.0f) {
                this.prevRotationPitch -= 360.0f;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0f) {
                this.prevRotationPitch += 360.0f;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0f) {
                this.prevRotationYaw -= 360.0f;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0f) {
                this.prevRotationYaw += 360.0f;
            }
            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2f;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2f;
            float f4 = this.getSpeedReduction();
            if (this.isInWater()) {
                for (int k1 = 0; k1 < 4; ++k1) {
                    float f7 = 0.25f;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f7, this.posY - this.motionY * (double)f7, this.posZ - this.motionZ * (double)f7, this.motionX, this.motionY, this.motionZ);
                }
                f4 = 0.8f;
            }
            this.motionX *= (double)f4;
            this.motionY *= (double)f4;
            this.motionZ *= (double)f4;
            this.motionY -= 0.05000000074505806;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.func_145775_I();
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("xTile", this.xTile);
        nbt.setInteger("yTile", this.yTile);
        nbt.setInteger("zTile", this.zTile);
        nbt.setInteger("inTile", Block.getIdFromBlock((Block)this.inTile));
        nbt.setByte("inData", (byte)this.inData);
        nbt.setByte("shake", (byte)this.shake);
        nbt.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        nbt.setByte("pickup", (byte)this.canBePickedUp);
        nbt.setByte("Knockback", (byte)this.knockbackStrength);
        if (this.getProjectileItem() != null) {
            nbt.setTag("ProjectileItem", (NBTBase)this.getProjectileItem().writeToNBT(new NBTTagCompound()));
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        this.xTile = nbt.getInteger("xTile");
        this.yTile = nbt.getInteger("yTile");
        this.zTile = nbt.getInteger("zTile");
        this.inTile = Block.getBlockById((int)nbt.getInteger("inTile"));
        this.inData = nbt.getByte("inData");
        this.shake = nbt.getByte("shake");
        this.inGround = nbt.getByte("inGround") == 1;
        this.canBePickedUp = nbt.getByte("pickup");
        this.knockbackStrength = nbt.getByte("Knockback");
        if (nbt.hasKey("itemID")) {
            ItemStack item = new ItemStack(Item.getItemById((int)nbt.getInteger("itemID")), 1, nbt.getInteger("itemDamage"));
            if (nbt.hasKey("ItemTagCompound")) {
                item.setTagCompound(nbt.getCompoundTag("ItemTagCompound"));
            }
            this.setProjectileItem(item);
        } else {
            this.setProjectileItem(ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("ProjectileItem")));
        }
    }

    protected ItemStack createPickupDrop(EntityPlayer entityplayer) {
        ItemStack itemstack = this.getProjectileItem();
        if (itemstack != null) {
            ItemStack itemPickup = itemstack.copy();
            if (itemPickup.isItemStackDamageable()) {
                itemPickup.damageItem(1, (EntityLivingBase)entityplayer);
                if (itemPickup.getItemDamage() >= itemPickup.getMaxDamage()) {
                    return null;
                }
            }
            return itemPickup;
        }
        return null;
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer) {
        if (!this.worldObj.isRemote && this.inGround && this.shake <= 0) {
            ItemStack itemstack = this.createPickupDrop(entityplayer);
            if (itemstack != null) {
                boolean pickup;
                boolean bl = pickup = this.canBePickedUp == 1 || this.canBePickedUp == 2 && entityplayer.capabilities.isCreativeMode;
                if (this.canBePickedUp == 1 && !entityplayer.inventory.addItemStackToInventory(itemstack.copy())) {
                    pickup = false;
                    EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, itemstack);
                    entityitem.delayBeforeCanPickup = 0;
                    this.worldObj.spawnEntityInWorld((Entity)entityitem);
                    this.setDead();
                }
                if (pickup) {
                    if (!this.isDead) {
                        EntityTracker entitytracker = ((WorldServer)this.worldObj).getEntityTracker();
                        entitytracker.func_151247_a((Entity)this, (Packet)new S0DPacketCollectItem(this.getEntityId(), entityplayer.getEntityId()));
                    }
                    this.playSound("random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                    this.setDead();
                }
            } else {
                this.setDead();
            }
        }
    }

    protected void onCollideWithTarget(Entity entity) {
        ItemStack itemstack;
        if (!(this.worldObj.isRemote || (itemstack = this.getProjectileItem()) != null && itemstack.isItemStackDamageable())) {
            this.setDead();
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public float getShadowSize() {
        return 0.0f;
    }

    public boolean canAttackWithItem() {
        return false;
    }

    public abstract float getBaseImpactDamage(Entity var1, ItemStack var2);

    protected float getKnockbackFactor() {
        return 1.0f;
    }

    public DamageSource getDamageSource() {
        if (this.shootingEntity == null) {
            return DamageSource.causeThrownDamage((Entity)this, (Entity)this);
        }
        return DamageSource.causeThrownDamage((Entity)this, (Entity)this.shootingEntity);
    }

    public void setIsCritical(boolean flag) {
        this.dataWatcher.updateObject(17, (Object)((byte)(flag ? 1 : 0)));
    }

    public boolean getIsCritical() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }

    public String getImpactSound() {
        return "random.bowhit";
    }

    public float getSpeedReduction() {
        return 0.99f;
    }

    public int maxTicksInGround() {
        return this.canBePickedUp == 1 ? 6000 : 1200;
    }
}

