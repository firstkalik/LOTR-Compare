/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntitySmokeFX
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.util.Random;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;

public class LOTREntityQuenditeSmokeFX
extends EntitySmokeFX {
    public LOTREntityQuenditeSmokeFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        super(world, d, d1, d2, d3, d4, d5);
        this.particleRed = this.rand.nextFloat() * 0.3f;
        this.particleGreen = 0.5f + this.rand.nextFloat() * 0.5f;
        this.particleBlue = 1.0f;
    }
}

