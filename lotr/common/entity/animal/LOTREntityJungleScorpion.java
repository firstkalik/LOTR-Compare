/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import java.util.Random;
import lotr.common.entity.animal.LOTREntityScorpion;
import net.minecraft.world.World;

public class LOTREntityJungleScorpion
extends LOTREntityScorpion {
    public LOTREntityJungleScorpion(World world) {
        super(world);
    }

    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere() && (this.spawningFromSpawner || this.posY < 60.0 || this.rand.nextInt(100) == 0);
    }
}

