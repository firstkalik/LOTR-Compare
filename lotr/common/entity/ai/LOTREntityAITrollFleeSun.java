/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.ai;

import java.util.Random;
import lotr.common.entity.npc.LOTREntityTroll;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityAITrollFleeSun
extends EntityAIBase {
    private LOTREntityTroll theTroll;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double moveSpeed;
    private World theWorld;

    public LOTREntityAITrollFleeSun(LOTREntityTroll troll, double d) {
        this.theTroll = troll;
        this.moveSpeed = d;
        this.theWorld = troll.worldObj;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        Vec3 vec3;
        if (!this.theWorld.isDaytime()) {
            return false;
        }
        if (!this.theWorld.canBlockSeeTheSky(MathHelper.floor_double((double)this.theTroll.posX), (int)this.theTroll.boundingBox.minY, MathHelper.floor_double((double)this.theTroll.posZ))) {
            return false;
        }
        if (this.theTroll.trollImmuneToSun) {
            return false;
        }
        BiomeGenBase biome = this.theWorld.getBiomeGenForCoords(MathHelper.floor_double((double)this.theTroll.posX), MathHelper.floor_double((double)this.theTroll.posZ));
        if (biome instanceof LOTRBiome && ((LOTRBiome)biome).canSpawnHostilesInDay()) {
            return false;
        }
        if (this.theTroll.getTrollBurnTime() == -1) {
            this.theTroll.setTrollBurnTime(300);
        }
        if ((vec3 = this.findPossibleShelter()) == null && (vec3 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.theTroll, (int)12, (int)6)) == null) {
            return false;
        }
        this.xPosition = vec3.xCoord;
        this.yPosition = vec3.yCoord;
        this.zPosition = vec3.zCoord;
        return true;
    }

    public boolean continueExecuting() {
        return !this.theTroll.getNavigator().noPath();
    }

    public void startExecuting() {
        this.theTroll.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.moveSpeed);
    }

    private Vec3 findPossibleShelter() {
        Random random = this.theTroll.getRNG();
        for (int l = 0; l < 32; ++l) {
            int j;
            int k;
            int i = MathHelper.floor_double((double)this.theTroll.posX) - 24 + random.nextInt(49);
            if (this.theWorld.canBlockSeeTheSky(i, j = MathHelper.floor_double((double)this.theTroll.boundingBox.minY) - 12 + random.nextInt(25), k = MathHelper.floor_double((double)this.theTroll.posZ) - 24 + random.nextInt(49)) || !(this.theTroll.getBlockPathWeight(i, j, k) < 0.0f)) continue;
            return Vec3.createVectorHelper((double)i, (double)j, (double)k);
        }
        return null;
    }
}

