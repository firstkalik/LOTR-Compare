/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class LOTRDwarvenGlowLogic {
    private static final float[] lightValueSqrts = new float[16];
    private boolean playersNearby;
    private int glowTick;
    private int prevGlowTick;
    private int maxGlowTick = 120;
    private int playerRange = 8;
    private float fullGlow = 0.7f;

    public LOTRDwarvenGlowLogic setGlowTime(int i) {
        this.maxGlowTick = i;
        return this;
    }

    public LOTRDwarvenGlowLogic setPlayerRange(int i) {
        this.playerRange = i;
        return this;
    }

    public LOTRDwarvenGlowLogic setFullGlow(float f) {
        this.fullGlow = f;
        return this;
    }

    public void update(World world, int i, int j, int k) {
        this.prevGlowTick = this.glowTick;
        if (world.isRemote) {
            boolean bl = this.playersNearby = world.getClosestPlayer((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, (double)this.playerRange) != null;
            if (this.playersNearby && this.glowTick < this.maxGlowTick) {
                ++this.glowTick;
            } else if (!this.playersNearby && this.glowTick > 0) {
                --this.glowTick;
            }
        }
    }

    public float getGlowBrightness(World world, int i, int j, int k, float tick) {
        float f;
        float glow = ((float)this.prevGlowTick + (float)(this.glowTick - this.prevGlowTick) * tick) / (float)this.maxGlowTick;
        glow *= this.fullGlow;
        float sun = world.getSunBrightness(tick);
        float sunNorml = (sun - 0.2f) / 0.8f;
        float night = 1.0f - sunNorml;
        night -= 0.5f;
        if (f < 0.0f) {
            night = 0.0f;
        }
        float skylight = lightValueSqrts[world.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, i, j, k)];
        return glow * (night *= 2.0f) * skylight;
    }

    public int getGlowTick() {
        return this.glowTick;
    }

    public void setGlowTick(int i) {
        this.glowTick = this.prevGlowTick = i;
    }

    public void resetGlowTick() {
        this.prevGlowTick = 0;
        this.glowTick = 0;
    }

    static {
        for (int i = 0; i <= 15; ++i) {
            LOTRDwarvenGlowLogic.lightValueSqrts[i] = MathHelper.sqrt_float((float)((float)i / 15.0f));
        }
    }
}

