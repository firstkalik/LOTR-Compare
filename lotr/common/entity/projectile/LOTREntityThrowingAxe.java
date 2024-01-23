/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.projectile;

import lotr.common.entity.projectile.LOTREntityProjectileBase;
import lotr.common.item.LOTRItemThrowingAxe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityThrowingAxe
extends LOTREntityProjectileBase {
    private int axeRotation;

    public LOTREntityThrowingAxe(World world) {
        super(world);
    }

    public LOTREntityThrowingAxe(World world, ItemStack item, double d, double d1, double d2) {
        super(world, item, d, d1, d2);
    }

    public LOTREntityThrowingAxe(World world, EntityLivingBase entityliving, ItemStack item, float charge) {
        super(world, entityliving, item, charge);
    }

    public LOTREntityThrowingAxe(World world, EntityLivingBase entityliving, EntityLivingBase target, ItemStack item, float charge, float inaccuracy) {
        super(world, entityliving, target, item, charge, inaccuracy);
    }

    private boolean isThrowingAxe() {
        Item item = this.getProjectileItem().getItem();
        return item instanceof LOTRItemThrowingAxe;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.inGround) {
            ++this.axeRotation;
            if (this.axeRotation > 9) {
                this.axeRotation = 0;
            }
            this.rotationPitch = (float)this.axeRotation / 9.0f * 360.0f;
        }
        if (!this.isThrowingAxe()) {
            this.setDead();
        }
    }

    @Override
    public float getBaseImpactDamage(Entity entity, ItemStack itemstack) {
        if (!this.isThrowingAxe()) {
            return 0.0f;
        }
        float speed = MathHelper.sqrt_double((double)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ));
        float damage = ((LOTRItemThrowingAxe)itemstack.getItem()).getRangedDamageMultiplier(itemstack, this.shootingEntity, entity);
        return speed * damage;
    }
}

