/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.FishingHooks
 *  net.minecraftforge.common.FishingHooks$FishableCategory
 */
package lotr.common.entity.projectile;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRReflection;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.enchant.LOTREnchantmentSeaFortune;
import lotr.common.entity.projectile.LOTRFishing;
import lotr.common.item.LOTRItemRing;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.FishingHooks;

public class LOTREntityFishHook
extends EntityFishHook {
    public LOTREntityFishHook(World world) {
        super(world);
    }

    public LOTREntityFishHook(World world, EntityPlayer entityplayer) {
        super(world, entityplayer);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)0);
    }

    public int getPlayerID() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setPlayerID(int id) {
        this.dataWatcher.updateObject(16, (Object)id);
    }

    public void onUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.field_146042_b == null) {
                this.setDead();
                return;
            }
        } else if (this.field_146042_b == null) {
            int playerID = this.getPlayerID();
            Entity player = this.worldObj.getEntityByID(playerID);
            if (player instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer)player;
                entityplayer.fishEntity = this;
                this.field_146042_b = entityplayer;
            } else {
                this.setDead();
                return;
            }
        }
        super.onUpdate();
    }

    public int func_146034_e() {
        if (this.worldObj.isRemote) {
            return 0;
        }
        int damage = 0;
        if (this.field_146043_c != null) {
            double d0 = this.field_146042_b.posX - this.posX;
            double d1 = this.field_146042_b.posY - this.posY;
            double d2 = this.field_146042_b.posZ - this.posZ;
            double dist = MathHelper.sqrt_double((double)(d0 * d0 + d1 * d1 + d2 * d2));
            double motion = 0.1;
            this.field_146043_c.motionX += d0 * motion;
            this.field_146043_c.motionY += d1 * motion + (double)MathHelper.sqrt_double((double)dist) * 0.08;
            this.field_146043_c.motionZ += d2 * motion;
            damage = 3;
        } else if (LOTRReflection.getFishHookBobTime(this) > 0) {
            float chance = this.worldObj.rand.nextFloat();
            int luck = EnchantmentHelper.func_151386_g((EntityLivingBase)this.field_146042_b);
            int speed = EnchantmentHelper.func_151387_h((EntityLivingBase)this.field_146042_b);
            int seaFortuneLevel = 0;
            if (this.field_146042_b.getHeldItem() != null) {
                for (LOTREnchantment enchantment : LOTREnchantmentHelper.getEnchantList(this.field_146042_b.getHeldItem())) {
                    if (!(enchantment instanceof LOTREnchantmentSeaFortune)) continue;
                    seaFortuneLevel += ((LOTREnchantmentSeaFortune)enchantment).luckFactor;
                }
            }
            LOTRFishing.FishResult result = LOTRFishing.getFishResult(this.rand, chance, luck, speed, true, seaFortuneLevel);
            ItemStack item = result.fishedItem;
            EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, item);
            double d0 = this.field_146042_b.posX - this.posX;
            double d1 = this.field_146042_b.posY - this.posY;
            double d2 = this.field_146042_b.posZ - this.posZ;
            double dist = MathHelper.sqrt_double((double)(d0 * d0 + d1 * d1 + d2 * d2));
            double motion = 0.1;
            entityitem.motionX = d0 * motion;
            entityitem.motionY = d1 * motion + (double)MathHelper.sqrt_double((double)dist) * 0.08;
            entityitem.motionZ = d2 * motion;
            this.worldObj.spawnEntityInWorld((Entity)entityitem);
            this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.field_146042_b.worldObj, this.field_146042_b.posX, this.field_146042_b.posY + 0.5, this.field_146042_b.posZ + 0.5, this.rand.nextInt(6) + 1));
            this.field_146042_b.addStat(result.category.stat, 1);
            if (item.getItem() instanceof LOTRItemRing) {
                LOTRLevelData.getData(this.field_146042_b).addAchievement(LOTRAchievement.fishRing);
            }
            damage = 1;
        }
        if (LOTRReflection.isFishHookInGround(this)) {
            damage = 2;
        }
        this.setDead();
        this.field_146042_b.fishEntity = null;
        return damage;
    }
}

