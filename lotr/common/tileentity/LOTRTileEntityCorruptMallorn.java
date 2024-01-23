/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.tileentity;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityEnt;
import lotr.common.world.biome.LOTRBiomeGenFangorn;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRTileEntityCorruptMallorn
extends TileEntity {
    public void updateEntity() {
        BiomeGenBase biome;
        super.updateEntity();
        Random rand = this.worldObj.rand;
        if (!this.worldObj.isRemote && LOTRMod.canSpawnMobs(this.worldObj) && rand.nextInt(40) == 0 && (biome = this.worldObj.getBiomeGenForCoords(this.xCoord, this.zCoord)) instanceof LOTRBiomeGenFangorn) {
            int checkRange = 24;
            int spawnRange = 20;
            List nearbyEnts = this.worldObj.getEntitiesWithinAABB(LOTREntityEnt.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand((double)checkRange, (double)checkRange, (double)checkRange));
            if (nearbyEnts.isEmpty()) {
                LOTREntityEnt ent = new LOTREntityEnt(this.worldObj);
                for (int l = 0; l < 16; ++l) {
                    int k;
                    int j;
                    int i = this.xCoord + MathHelper.getRandomIntegerInRange((Random)rand, (int)(-spawnRange), (int)spawnRange);
                    if (!this.worldObj.getBlock(i, (j = this.yCoord + MathHelper.getRandomIntegerInRange((Random)rand, (int)(-spawnRange), (int)spawnRange)) - 1, k = this.zCoord + MathHelper.getRandomIntegerInRange((Random)rand, (int)(-spawnRange), (int)spawnRange)).isNormalCube() || this.worldObj.getBlock(i, j, k).isNormalCube() || this.worldObj.getBlock(i, j + 1, k).isNormalCube()) continue;
                    ent.setLocationAndAngles((double)i + 0.5, (double)j, (double)k + 0.5, rand.nextFloat() * 360.0f, 0.0f);
                    ent.liftSpawnRestrictions = false;
                    if (!ent.getCanSpawnHere()) continue;
                    ent.onSpawnWithEgg(null);
                    ent.isNPCPersistent = false;
                    this.worldObj.spawnEntityInWorld((Entity)ent);
                    break;
                }
            }
        }
    }
}

