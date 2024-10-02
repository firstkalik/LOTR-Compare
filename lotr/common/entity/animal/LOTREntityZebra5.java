/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityList
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.animal.LOTREntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTREntityZebra5
extends LOTREntityHorse {
    public LOTREntityZebra5(World world) {
        super(world);
    }

    public int getHorseType() {
        return 0;
    }

    public boolean func_110259_cr() {
        return false;
    }

    @Override
    public String getCommandSenderName() {
        if (this.hasCustomNameTag()) {
            return this.getCustomNameTag();
        }
        String s = EntityList.getEntityString((Entity)this);
        return StatCollector.translateToLocal((String)("entity." + s + ".name"));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        LOTREntityZebra5 zebra = (LOTREntityZebra5)super.createChild(entityageable);
        return zebra;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        String s = "smoke";
        if (this.rand.nextInt(10) == 0) {
            double motionX = (this.rand.nextDouble() - 0.5) * (double)this.width;
            double motionY = this.rand.nextDouble() * (double)this.height;
            double motionZ = (this.rand.nextDouble() - 0.5) * (double)this.width;
            this.worldObj.spawnParticle(s, this.posX + motionX, this.posY + motionY, this.posZ + motionZ, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target) {
        return new ItemStack(LOTRMod.spawnEgg, 1, LOTREntities.getEntityID((Entity)this));
    }
}

