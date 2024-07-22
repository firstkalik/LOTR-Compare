/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.entity.npc.LOTREntityAngbandWarg;
import lotr.common.entity.npc.LOTREntityWarg;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTREntityAngbandWargFire
extends LOTREntityAngbandWarg {
    public LOTREntityAngbandWargFire(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.setWargType(LOTREntityWarg.WargType.FIRE);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        String s = this.rand.nextInt(3) > 0 ? "flame" : "smoke";
        this.worldObj.spawnParticle(s, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);
        if (!this.worldObj.isRemote && flag) {
            entity.setFire(4);
        }
        return flag;
    }
}

