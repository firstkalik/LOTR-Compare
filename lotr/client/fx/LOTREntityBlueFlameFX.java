/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

public class LOTREntityBlueFlameFX
extends EntityFlameFX {
    public LOTREntityBlueFlameFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        super(world, d, d1, d2, d3, d4, d5);
        this.setParticleTextureIndex(48);
    }
}

