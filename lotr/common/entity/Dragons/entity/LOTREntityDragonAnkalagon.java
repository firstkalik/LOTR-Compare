/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.boss.EntityDragonPart
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package lotr.common.entity.Dragons.entity;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.Dragons.entity.SmaugFireballs;
import lotr.common.entity.item.LOTREntityArrowDragon;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityMan;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class LOTREntityDragonAnkalagon
extends EntityDragon {
    private Entity target;
    private static final Random RANDOM = new Random();

    public LOTREntityDragonAnkalagon(World p_i1700_1_) {
        super(p_i1700_1_);
        this.setSize(8.0f, 4.0f);
        float randomHealth = 2000 + RANDOM.nextInt(400);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)randomHealth);
        this.setHealth(randomHealth);
    }

    public boolean getCanSpawnHere() {
        AxisAlignedBB BB = this.boundingBox.expand(10.0, 10.0, 10.0);
        return this.worldObj.checkNoEntityCollision(BB) && this.worldObj.getCollidingBoundingBoxes((Entity)this, BB).isEmpty() && !this.worldObj.isAnyLiquid(BB);
    }

    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (damagesource.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)damagesource.getEntity();
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.killDragon);
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.killDragonAnkalagon);
            if (damagesource.getSourceOfDamage() instanceof LOTREntityArrowDragon) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.useArrowDragon);
            }
        }
        if (!this.worldObj.isRemote) {
            int exRange = 8;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.posY);
            int k = MathHelper.floor_double((double)this.posZ);
            for (int i1 = i - exRange; i1 <= i + exRange; ++i1) {
                for (int j1 = j - exRange; j1 <= j + exRange; ++j1) {
                    for (int k1 = k - exRange; k1 <= k + exRange; ++k1) {
                        Block block = this.worldObj.getBlock(i1, j1, k1);
                        if (block.getMaterial() != Material.fire) continue;
                        this.worldObj.setBlockToAir(i1, j1, k1);
                    }
                }
            }
        }
    }

    public void onLivingUpdate() {
        List block;
        AxisAlignedBB leaves;
        if (!this.worldObj.isRemote && !(block = this.worldObj.getCollidingBoundingBoxes((Entity)this, leaves = this.boundingBox.expand(1.0, 1.0, 1.0))).isEmpty()) {
            this.noClip = false;
            for (AxisAlignedBB b : block) {
                if (this.worldObj.getBlock((int)b.minX, (int)b.minY, (int)b.minZ).getMaterial() == Material.wood) {
                    this.noClip = false;
                    this.motionY = 0.2;
                    break;
                }
                if (this.worldObj.getBlock((int)b.minX, (int)b.minY, (int)b.minZ).getMaterial() != Material.leaves) {
                    this.noClip = false;
                    this.motionY = 0.2;
                    continue;
                }
                this.noClip = true;
                this.motionY = 0.2;
            }
        }
        if (this.worldObj.isRemote) {
            float f = MathHelper.cos((float)(this.animTime * 3.1415927f * 2.0f));
            float f1 = MathHelper.cos((float)(this.prevAnimTime * 3.1415927f * 2.0f));
            if (f1 <= -0.3f && f >= -0.3f) {
                this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0f, 0.8f + this.rand.nextFloat() * 0.3f, false);
            }
        }
        this.prevAnimTime = this.animTime;
        if (this.getHealth() <= 0.0f) {
            float f = (this.rand.nextFloat() - 0.5f) * 8.0f;
            float f1 = (this.rand.nextFloat() - 0.5f) * 4.0f;
            float f2 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            this.worldObj.spawnParticle("largeexplode", this.posX + (double)f, this.posY + 2.0 + (double)f1, this.posZ + (double)f2, 0.0, 0.0, 0.0);
        } else {
            double d2;
            double d1;
            float f = 0.2f / (MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ)) * 10.0f + 1.0f);
            this.animTime = this.slowed ? (this.animTime += f * 0.5f) : (this.animTime += (f *= (float)Math.pow(2.0, this.motionY)));
            this.rotationYaw = MathHelper.wrapAngleTo180_float((float)this.rotationYaw);
            if (this.ringBufferIndex < 0) {
                for (int i = 0; i < this.ringBuffer.length; ++i) {
                    this.ringBuffer[i][0] = this.rotationYaw;
                    this.ringBuffer[i][1] = this.posY;
                }
            }
            if (++this.ringBufferIndex == this.ringBuffer.length) {
                this.ringBufferIndex = 0;
            }
            this.ringBuffer[this.ringBufferIndex][0] = this.rotationYaw;
            this.ringBuffer[this.ringBufferIndex][1] = this.posY;
            if (this.worldObj.isRemote) {
                if (this.newPosRotationIncrements > 0) {
                    double d10 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
                    double d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
                    d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
                    d2 = MathHelper.wrapAngleTo180_double((double)(this.newRotationYaw - (double)this.rotationYaw));
                    this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
                    this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
                    --this.newPosRotationIncrements;
                    this.setPosition(d10, d0, d1);
                    this.setRotation(this.rotationYaw, this.rotationPitch);
                }
            } else {
                double maxDistance;
                float f14;
                double d10 = this.targetX - this.posX;
                double d0 = this.targetY - this.posY;
                d1 = this.targetZ - this.posZ;
                d2 = d10 * d10 + d0 * d0 + d1 * d1;
                if (this.target != null) {
                    this.targetX = this.target.posX;
                    this.targetZ = this.target.posZ;
                    double d3 = this.targetX - this.posX;
                    double d5 = this.targetZ - this.posZ;
                    double d7 = Math.sqrt(d3 * d3 + d5 * d5);
                    double d8 = 0.4000000059604645 + d7 / 80.0 - 1.0;
                    if (d8 > 10.0) {
                        d8 = 10.0;
                    }
                    this.targetY = this.target.boundingBox.minY + d8;
                } else {
                    this.targetX += this.rand.nextGaussian() * 2.0;
                    this.targetZ += this.rand.nextGaussian() * 2.0;
                }
                if (this.target instanceof EntityPlayer && ((EntityPlayer)this.target).capabilities.isCreativeMode) {
                    this.target = null;
                }
                if (d2 > (maxDistance = 80.0) * maxDistance) {
                    d10 = d10 / Math.sqrt(d2) * maxDistance;
                    d1 = d1 / Math.sqrt(d2) * maxDistance;
                }
                if (this.inWater) {
                    this.setNewTarget();
                }
                if (this.forceNewTarget || d2 < 100.0 || d2 > 22500.0) {
                    this.setNewTarget();
                }
                if ((d0 /= (double)MathHelper.sqrt_double((double)(d10 * d10 + d1 * d1))) < (double)(-(f14 = 0.6f))) {
                    d0 = -f14;
                }
                if (d0 > (double)f14) {
                    d0 = f14;
                }
                this.motionY += d0 * 0.10000000149011612;
                this.rotationYaw = MathHelper.wrapAngleTo180_float((float)this.rotationYaw);
                double d4 = 180.0 - Math.atan2(d10, d1) * 180.0 / 3.141592653589793;
                double d6 = MathHelper.wrapAngleTo180_double((double)(d4 - (double)this.rotationYaw));
                if (d6 > 50.0) {
                    d6 = 50.0;
                }
                if (d6 < -50.0) {
                    d6 = -50.0;
                }
                Vec3 vec3 = Vec3.createVectorHelper((double)(this.targetX - this.posX), (double)(this.targetY - this.posY), (double)(this.targetZ - this.posZ)).normalize();
                Vec3 vec32 = Vec3.createVectorHelper((double)MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f)), (double)this.motionY, (double)(-MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f)))).normalize();
                float f5 = (float)(vec32.dotProduct(vec3) + 0.5) / 1.5f;
                if (f5 < 0.0f) {
                    f5 = 0.0f;
                }
                this.randomYawVelocity *= 0.8f;
                float f6 = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionZ * this.motionZ)) * 1.0f + 1.0f;
                double d9 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0 + 1.0;
                if (d9 > 40.0) {
                    d9 = 40.0;
                }
                this.randomYawVelocity = (float)((double)this.randomYawVelocity + d6 * 0.699999988079071 / d9 / (double)f6);
                this.rotationYaw += this.randomYawVelocity * 0.1f;
                float f7 = (float)(2.0 / (d9 + 1.0));
                float f8 = 0.06f;
                this.moveFlying(0.0f, -1.0f, f8 * (f5 * f7 + 1.0f - f7));
                if (this.slowed) {
                    this.moveEntity(this.motionX * 0.800000011920929, this.motionY * 0.800000011920929, this.motionZ * 0.800000011920929);
                } else {
                    this.moveEntity(this.motionX, this.motionY, this.motionZ);
                }
                Vec3 vec31 = Vec3.createVectorHelper((double)this.motionX, (double)this.motionY, (double)this.motionZ).normalize();
                float f9 = (float)(vec31.dotProduct(vec32) + 1.0) / 2.0f;
                f9 = 0.8f + 0.15f * f9;
                this.motionX *= (double)f9;
                this.motionZ *= (double)f9;
                this.motionY *= 0.9100000262260437;
            }
            this.renderYawOffset = this.rotationYaw;
            this.dragonPartHead.height = 3.0f;
            this.dragonPartHead.width = 3.0f;
            this.dragonPartTail1.height = 2.0f;
            this.dragonPartTail1.width = 2.0f;
            this.dragonPartTail2.height = 2.0f;
            this.dragonPartTail2.width = 2.0f;
            this.dragonPartTail3.height = 2.0f;
            this.dragonPartTail3.width = 2.0f;
            this.dragonPartBody.height = 3.0f;
            this.dragonPartBody.width = 5.0f;
            this.dragonPartWing1.height = 2.0f;
            this.dragonPartWing1.width = 4.0f;
            this.dragonPartWing2.height = 3.0f;
            this.dragonPartWing2.width = 4.0f;
            float f1 = (float)(this.getMovementOffsets(5, 1.0f)[1] - this.getMovementOffsets(10, 1.0f)[1]) * 10.0f / 180.0f * 3.1415927f;
            float f2 = MathHelper.cos((float)f1);
            float f10 = -MathHelper.sin((float)f1);
            float f3 = this.rotationYaw * 3.1415927f / 180.0f;
            float f11 = MathHelper.sin((float)f3);
            float f4 = MathHelper.cos((float)f3);
            this.dragonPartBody.onUpdate();
            this.dragonPartBody.setLocationAndAngles(this.posX + (double)(f11 * 0.5f), this.posY, this.posZ - (double)(f4 * 0.5f), 0.0f, 0.0f);
            this.dragonPartWing1.onUpdate();
            this.dragonPartWing1.setLocationAndAngles(this.posX + (double)(f4 * 4.5f), this.posY + 2.0, this.posZ + (double)(f11 * 4.5f), 0.0f, 0.0f);
            this.dragonPartWing2.onUpdate();
            this.dragonPartWing2.setLocationAndAngles(this.posX - (double)(f4 * 4.5f), this.posY + 2.0, this.posZ - (double)(f11 * 4.5f), 0.0f, 0.0f);
            if (!this.worldObj.isRemote && this.hurtTime == 0) {
                this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.dragonPartBody.boundingBox.expand(0.0, 0.0, 0.0)));
                this.attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.dragonPartBody.boundingBox.expand(0.0, 0.0, 0.0)));
            }
            double[] adouble1 = this.getMovementOffsets(5, 1.0f);
            double[] adouble = this.getMovementOffsets(0, 1.0f);
            float f12 = MathHelper.sin((float)(this.rotationYaw * 3.1415927f / 180.0f - this.randomYawVelocity * 0.01f));
            float f13 = MathHelper.cos((float)(this.rotationYaw * 3.1415927f / 180.0f - this.randomYawVelocity * 0.01f));
            this.dragonPartHead.onUpdate();
            this.dragonPartHead.setLocationAndAngles(this.posX + (double)(f12 * 5.5f * f2), this.posY + (adouble[1] - adouble1[1]) * 1.0 + (double)(f10 * 5.5f), this.posZ - (double)(f13 * 5.5f * f2), 0.0f, 0.0f);
            for (int j = 0; j < 3; ++j) {
                EntityDragonPart entitydragonpart = null;
                if (j == 0) {
                    entitydragonpart = this.dragonPartTail1;
                }
                if (j == 1) {
                    entitydragonpart = this.dragonPartTail2;
                }
                if (j == 2) {
                    entitydragonpart = this.dragonPartTail3;
                }
                double[] adouble2 = this.getMovementOffsets(12 + j * 2, 1.0f);
                float f14 = this.rotationYaw * 3.1415927f / 180.0f + this.simplifyAngle(adouble2[0] - adouble1[0]) * 3.1415927f / 180.0f * 1.0f;
                float f15 = MathHelper.sin((float)f14);
                float f16 = MathHelper.cos((float)f14);
                float f17 = 1.5f;
                float f18 = (float)(j + 1) * 2.0f;
                entitydragonpart.onUpdate();
                entitydragonpart.setLocationAndAngles(this.posX - (double)((f11 * f17 + f15 * f18) * f2), this.posY + (adouble2[1] - adouble1[1]) * 1.0 - (double)((f18 + f17) * f10) + 1.5, this.posZ + (double)((f4 * f17 + f16 * f18) * f2), 0.0f, 0.0f);
            }
        }
    }

    private float simplifyAngle(double p_70973_1_) {
        return (float)MathHelper.wrapAngleTo180_double((double)p_70973_1_);
    }

    private void setNewTarget() {
        this.forceNewTarget = false;
        List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.copy().expand(150.0, 150.0, 150.0));
        if (list.isEmpty()) {
            list = this.worldObj.getEntitiesWithinAABB(Entity.class, this.boundingBox.copy().expand(50.0, 50.0, 50.0));
            list.removeIf(entity -> !(entity instanceof LOTREntityElf) && !(entity instanceof LOTREntityDwarf) && !(entity instanceof LOTREntityHobbit) && !(entity instanceof LOTREntityMan));
        }
        if (this.rand.nextInt(2) == 0 && !list.isEmpty()) {
            this.target = (Entity)list.get(this.rand.nextInt(list.size()));
            if (this.worldObj.rand.nextDouble() > 0.9) {
                double d5 = this.target.posX - this.posX;
                double d6 = this.target.boundingBox.minY + (double)(this.target.height / 2.0f) - this.posY + (double)(this.height / 2.0f);
                double d7 = this.target.posZ - this.posZ;
                SmaugFireballs entitylargefireball = new SmaugFireballs(this.worldObj, this, d5, d6, d7);
                entitylargefireball.setFire(60);
                entitylargefireball.field_92057_e = 2;
                double d8 = 4.0;
                Vec3 vec3 = this.getLook(1.0f);
                entitylargefireball.posX = this.posX + vec3.xCoord * d8;
                entitylargefireball.posY = this.posY + (double)(this.height / 2.0f) + 0.5;
                entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
                this.worldObj.spawnEntityInWorld((Entity)entitylargefireball);
            }
        } else {
            double d2;
            double d1;
            double d0;
            boolean flag = false;
            do {
                this.targetX = 0.0;
                this.targetY = 70.0f + this.rand.nextFloat() * 50.0f;
                this.targetZ = 0.0;
                this.targetX += (double)(this.rand.nextFloat() * 120.0f - 60.0f);
                this.targetZ += (double)(this.rand.nextFloat() * 120.0f - 60.0f);
            } while (!(flag = (d0 = this.posX - this.targetX) * d0 + (d1 = this.posY - this.targetY) * d1 + (d2 = this.posZ - this.targetZ) * d2 > 100.0));
            this.target = null;
        }
    }

    private void attackEntitiesInList(List<Entity> p_70971_1_) {
        for (int i = 0; i < p_70971_1_.size(); ++i) {
            Entity entity = p_70971_1_.get(i);
            if (!(entity instanceof EntityLivingBase)) continue;
            entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 24.0f);
        }
    }

    private void collideWithEntities(List<Entity> p_70970_1_) {
        double d0 = (this.dragonPartBody.boundingBox.minX + this.dragonPartBody.boundingBox.maxX) / 2.0;
        double d1 = (this.dragonPartBody.boundingBox.minZ + this.dragonPartBody.boundingBox.maxZ) / 2.0;
        for (Entity entity : p_70970_1_) {
            int difficulty;
            int duration;
            boolean flag = super.attackEntityAsMob(entity);
            if (!(entity instanceof EntityLivingBase)) continue;
            EntityLivingBase livingEntity = (EntityLivingBase)entity;
            if (!(livingEntity instanceof EntityPlayer && ((EntityPlayer)livingEntity).capabilities.isCreativeMode || (duration = (difficulty = this.worldObj.difficultySetting.getDifficultyId()) * (difficulty + 5) / 2) <= 0 || entity instanceof EntityDragon)) {
                livingEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration * 20, 0));
                livingEntity.addPotionEffect(new PotionEffect(LOTRPotions.vulnerability.id, duration * 20, 0));
                livingEntity.addPotionEffect(new PotionEffect(LOTRPotions.blood.id, duration * 20, 1));
                entity.setFire(4);
            }
            double d2 = entity.posX - d0;
            double d3 = entity.posZ - d1;
            double d4 = d2 * d2 + d3 * d3;
            entity.addVelocity(d2 / d4 * 4.0, 0.20000000298023224, d3 / d4 * 6.0);
            float damageDealt = 5.0f;
            this.heal(damageDealt);
        }
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;
        if (this.deathTicks >= 180 && this.deathTicks <= 200) {
            float f = (this.rand.nextFloat() - 0.5f) * 8.0f;
            float f1 = (this.rand.nextFloat() - 0.5f) * 4.0f;
            float f2 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)f, this.posY + 2.0 + (double)f1, this.posZ + (double)f2, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote) {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0) {
                int j;
                for (int i = 1; i > 0; i -= j) {
                    j = EntityXPOrb.getXPSplit((int)i);
                    this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }
            if (this.deathTicks == 1) {
                this.worldObj.playSoundAtEntity((Entity)this, "mob.enderdragon.end", 5.0f, 0.8f);
            }
        }
        this.moveEntity(0.0, 0.10000000149011612, 0.0);
        this.renderYawOffset = this.rotationYaw += 20.0f;
        if (this.deathTicks == 200 && !this.worldObj.isRemote) {
            ItemStack[] drops = new ItemStack[]{new ItemStack(LOTRMod.DragonAnkalagonScale), new ItemStack(LOTRMod.DragonTalon), new ItemStack(LOTRMod.magicClover), new ItemStack(LOTRMod.totemOfUndyingPlus)};
            int[] amounts = new int[]{1, 6, 2, 2};
            for (int i = 0; i < drops.length; ++i) {
                int amountToDrop;
                ItemStack dropItem = drops[i];
                for (int totalDrops = amounts[i]; totalDrops > 0; totalDrops -= amountToDrop) {
                    amountToDrop = Math.min(totalDrops, dropItem.getMaxStackSize());
                    this.worldObj.spawnEntityInWorld((Entity)new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(dropItem.getItem(), amountToDrop)));
                }
            }
            this.worldObj.removeEntity((Entity)this);
        }
    }

    public void addPotionEffect(PotionEffect effect) {
    }

    public boolean canDespawn() {
        return true;
    }
}

