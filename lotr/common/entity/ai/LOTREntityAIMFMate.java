/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityAnimalMF;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityAIMFMate
extends EntityAIBase {
    private LOTREntityAnimalMF theAnimal;
    World theWorld;
    public LOTREntityAnimalMF targetMate;
    int breeding = 0;
    double moveSpeed;

    public LOTREntityAIMFMate(LOTREntityAnimalMF animal, double d) {
        this.theAnimal = animal;
        this.theWorld = animal.worldObj;
        this.moveSpeed = d;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        if (!this.theAnimal.isInLove()) {
            return false;
        }
        this.targetMate = this.findMate();
        return this.targetMate != null;
    }

    public boolean continueExecuting() {
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.breeding < 60;
    }

    public void resetTask() {
        this.targetMate = null;
        this.breeding = 0;
    }

    public void updateTask() {
        this.theAnimal.getLookHelper().setLookPositionWithEntity((Entity)this.targetMate, 10.0f, (float)this.theAnimal.getVerticalFaceSpeed());
        this.theAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.targetMate, this.moveSpeed);
        ++this.breeding;
        if (this.breeding == 60 && this.theAnimal.getDistanceSqToEntity((Entity)this.targetMate) < 9.0) {
            this.procreate();
        }
    }

    private LOTREntityAnimalMF findMate() {
        LOTREntityAnimalMF mate;
        float f = 8.0f;
        Class mateClass = this.theAnimal.getAnimalMFBaseClass();
        List list = this.theWorld.getEntitiesWithinAABB(mateClass, this.theAnimal.boundingBox.expand((double)f, (double)f, (double)f));
        Iterator it = list.iterator();
        do {
            if (it.hasNext()) continue;
            return null;
        } while (!this.theAnimal.canMateWith(mate = (LOTREntityAnimalMF)((Object)it.next())));
        return mate;
    }

    private void procreate() {
        EntityAgeable babyAnimal = this.theAnimal.createChild((EntityAgeable)this.targetMate);
        if (babyAnimal != null) {
            this.theAnimal.setGrowingAge(6000);
            this.targetMate.setGrowingAge(6000);
            this.theAnimal.resetInLove();
            this.targetMate.resetInLove();
            babyAnimal.setGrowingAge(-24000);
            babyAnimal.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0f, 0.0f);
            this.theWorld.spawnEntityInWorld((Entity)babyAnimal);
            Random rand = this.theAnimal.getRNG();
            for (int i = 0; i < 7; ++i) {
                double d = this.theAnimal.posX + (double)(MathHelper.randomFloatClamp((Random)rand, (float)-1.0f, (float)1.0f) * this.theAnimal.width);
                double d1 = this.theAnimal.posY + 0.5 + (double)(rand.nextFloat() * this.theAnimal.height);
                double d2 = this.theAnimal.posZ + (double)(MathHelper.randomFloatClamp((Random)rand, (float)-1.0f, (float)1.0f) * this.theAnimal.width);
                double d3 = rand.nextGaussian() * 0.02;
                double d4 = rand.nextGaussian() * 0.02;
                double d5 = rand.nextGaussian() * 0.02;
                this.theWorld.spawnParticle("heart", d, d1, d2, d3, d4, d5);
            }
            if (LOTRMod.canDropLoot(this.theWorld)) {
                this.theWorld.spawnEntityInWorld((Entity)new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, rand.nextInt(4) + 1));
            }
        }
    }
}

