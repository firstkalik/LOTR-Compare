/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import java.util.Random;
import lotr.common.LOTRConfig;
import lotr.common.LOTRLevelData;
import lotr.common.block.LOTRBlockWoodBase;
import lotr.common.entity.npc.LOTREntityGaladhrimWarrior;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiomeGenLothlorien;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockWood
extends LOTRBlockWoodBase {
    public LOTRBlockWood() {
        this.setWoodNames("shirePine", "mallorn", "mirkOak", "charred");
    }

    public boolean removedByPlayer(World world, EntityPlayer entityplayer, int i, int j, int k, boolean willHarvest) {
        if (!world.isRemote && (world.getBlockMetadata(i, j, k) & 3) == 1 && world.rand.nextInt(3) == 0 && world.getBiomeGenForCoords(i, k) instanceof LOTRBiomeGenLothlorien && LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.LOTHLORIEN) < 0.0f && LOTRConfig.spawnElfFix && !entityplayer.capabilities.isCreativeMode) {
            int elves = 4 + world.rand.nextInt(3);
            boolean sentMessage = false;
            for (int l = 0; l < elves; ++l) {
                int j1;
                int k1;
                LOTREntityGaladhrimWarrior elfWarrior = new LOTREntityGaladhrimWarrior(world);
                int i1 = MathHelper.floor_double((double)entityplayer.posX) - 6 + world.rand.nextInt(12);
                if (!world.getBlock(i1, (j1 = world.getTopSolidOrLiquidBlock(i1, k1 = MathHelper.floor_double((double)entityplayer.posZ) - 6 + world.rand.nextInt(12))) - 1, k1).isSideSolid((IBlockAccess)world, i1, j1 - 1, k1, ForgeDirection.UP) || world.getBlock(i1, j1, k1).isNormalCube() || world.getBlock(i1, j1 + 1, k1).isNormalCube()) continue;
                elfWarrior.setLocationAndAngles((double)i1 + 0.5, (double)j1, (double)k1 + 0.5, 0.0f, 0.0f);
                if (!elfWarrior.getCanSpawnHere()) continue;
                elfWarrior.spawnRidingHorse = false;
                elfWarrior.onSpawnWithEgg(null);
                world.spawnEntityInWorld((Entity)elfWarrior);
                elfWarrior.isDefendingTree = true;
                elfWarrior.setAttackTarget((EntityLivingBase)entityplayer);
                if (sentMessage) continue;
                elfWarrior.sendSpeechBank(entityplayer, "galadhrim/warrior/defendTrees");
                sentMessage = true;
            }
        }
        return super.removedByPlayer(world, entityplayer, i, j, k, willHarvest);
    }
}

