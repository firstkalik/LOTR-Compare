/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityLargeFireball
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Fireball
extends EntityLargeFireball {
    public Fireball(World par1World, EntityLivingBase par2EntityLivingBase, double posX, double posY, double posZ, double vX, double vY, double vZ) {
        super(par1World, par2EntityLivingBase, vX, vY, vZ);
        this.setPosition(posX, posY, posZ);
        Double d3 = MathHelper.sqrt_double((double)(vX * vX + vY * vY + vZ * vZ));
        this.accelerationX = vX / d3 * 0.1;
        this.accelerationY = vY / d3 * 0.1;
        this.accelerationZ = vZ / d3 * 0.1;
    }
}

