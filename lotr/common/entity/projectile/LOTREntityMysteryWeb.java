/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import java.util.Random;
import lotr.common.entity.npc.LOTREntityMirkwoodSpider;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityMysteryWeb
extends EntityThrowable {
    public LOTREntityMysteryWeb(World world) {
        super(world);
    }

    public LOTREntityMysteryWeb(World world, EntityLivingBase entityliving) {
        super(world, entityliving);
    }

    public LOTREntityMysteryWeb(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    protected void onImpact(MovingObjectPosition m) {
        if (this.getThrower() != null && m.entityHit == this.getThrower()) {
            return;
        }
        if (!this.worldObj.isRemote) {
            boolean spawnedSpider = false;
            if (this.rand.nextInt(4) == 0) {
                LOTREntityMirkwoodSpider spider = new LOTREntityMirkwoodSpider(this.worldObj);
                spider.setSpiderScale(0);
                spider.liftSpawnRestrictions = true;
                for (int i = -2; i <= -2 && !spawnedSpider; ++i) {
                    for (int j = 0; j <= 3 && !spawnedSpider; ++j) {
                        for (int k = -2; k <= -2 && !spawnedSpider; ++k) {
                            spider.setLocationAndAngles(this.posX + (double)i / 2.0, this.posY + (double)j / 3.0, this.posZ + (double)k / 2.0, this.rand.nextFloat() * 360.0f, 0.0f);
                            if (!spider.getCanSpawnHere()) continue;
                            spider.liftSpawnRestrictions = false;
                            spider.onSpawnWithEgg(null);
                            this.worldObj.spawnEntityInWorld((Entity)spider);
                            if (this.getThrower() != null) {
                                spider.setAttackTarget(this.getThrower());
                            }
                            spawnedSpider = true;
                        }
                    }
                }
            }
            if (!spawnedSpider) {
                InventoryBasic tempInventory = new InventoryBasic("mysteryWeb", true, 1);
                LOTRChestContents.fillInventory((IInventory)tempInventory, this.rand, LOTRChestContents.MIRKWOOD_LOOT, 1);
                ItemStack item = tempInventory.getStackInSlot(0);
                if (this.rand.nextInt(500) == 0) {
                    item = new ItemStack(Items.melon, 64);
                }
                if (item != null) {
                    EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, item);
                    entityitem.delayBeforeCanPickup = 10;
                    this.worldObj.spawnEntityInWorld((Entity)entityitem);
                }
            }
            this.playSound("random.pop", 0.2f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            this.setDead();
        }
    }

    protected float func_70182_d() {
        return 0.5f;
    }

    protected float getGravityVelocity() {
        return 0.01f;
    }
}

