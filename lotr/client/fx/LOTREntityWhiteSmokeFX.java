/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntitySmokeFX
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.client.fx;

import java.util.Random;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityWhiteSmokeFX
extends EntitySmokeFX {
    public LOTREntityWhiteSmokeFX(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        super(world, d, d1, d2, d3, d4, d5);
        float grey;
        this.particleGreen = this.particleBlue = (grey = MathHelper.randomFloatClamp((Random)this.rand, (float)0.6f, (float)1.0f));
        this.particleRed = this.particleBlue;
    }
}

