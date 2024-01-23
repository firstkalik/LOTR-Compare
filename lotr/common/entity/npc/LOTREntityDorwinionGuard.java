/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityDorwinionCrossbower;
import lotr.common.entity.npc.LOTREntityDorwinionMan;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.fac.LOTRAlignmentBonusMap;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.LOTRWorldChunkManager;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.biome.LOTRBiomeGenDorwinion;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTREntityDorwinionGuard
extends LOTREntityDorwinionMan {
    private static ItemStack[] guardWeapons = new ItemStack[]{new ItemStack(Items.iron_sword), new ItemStack(LOTRMod.battleaxeIron), new ItemStack(LOTRMod.pikeIron)};
    public int grapeAlert;
    public static final int MAX_GRAPE_ALERT = 3;

    public LOTREntityDorwinionGuard(World world) {
        super(world);
        this.addTargetTasks(true);
        this.npcShield = LOTRShields.ALIGNMENT_DORWINION;
    }

    @Override
    protected EntityAIBase createDorwinionAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, true);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(guardWeapons.length);
        this.npcItemsInv.setMeleeWeapon(guardWeapons[i].copy());
        if (this.rand.nextInt(8) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearIron));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsDorwinion));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsDorwinion));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyDorwinion));
        if (this.rand.nextInt(4) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetDorwinion));
        } else {
            this.setCurrentItemOrArmor(4, null);
        }
        return data;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("GrapeAlert", this.grapeAlert);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.grapeAlert = nbt.getInteger("GrapeAlert");
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.grapeAlert > 0 && this.ticksExisted % 600 == 0) {
            --this.grapeAlert;
        }
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "dorwinion/guard/hired";
            }
            return "dorwinion/guard/friendly";
        }
        return "dorwinion/guard/hostile";
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote && damagesource.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)damagesource.getEntity();
            if (this.grapeAlert >= 3) {
                LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.stealDorwinionGrapes);
            }
        }
    }

    public static void defendGrapevines(EntityPlayer entityplayer, World world, int i, int j, int k) {
        if (!entityplayer.capabilities.isCreativeMode) {
            LOTRBiomeVariant variant;
            BiomeGenBase biome = world.getBiomeGenForCoords(i, k);
            LOTRBiomeVariant lOTRBiomeVariant = variant = world.provider instanceof LOTRWorldProvider ? ((LOTRWorldChunkManager)world.provider.worldChunkMgr).getBiomeVariantAt(i, k) : null;
            if (biome instanceof LOTRBiomeGenDorwinion && variant == LOTRBiomeVariant.VINEYARD) {
                LOTREntityDorwinionGuard guard;
                float alignment = LOTRLevelData.getData(entityplayer).getAlignment(LOTRFaction.DORWINION);
                boolean evil = alignment < 0.0f;
                float limit = 2000.0f;
                float chance = (limit - alignment) / limit;
                chance = Math.max(chance, 0.0f);
                chance = Math.min(chance, 1.0f);
                chance *= chance;
                if ((evil || world.rand.nextFloat() < chance) && world.rand.nextInt(4) == 0) {
                    int nearbyGuards = 0;
                    int spawnRange = 8;
                    List guardList = world.getEntitiesWithinAABB(LOTREntityDorwinionGuard.class, entityplayer.boundingBox.expand((double)spawnRange, (double)spawnRange, (double)spawnRange));
                    for (Object obj : guardList) {
                        guard = (LOTREntityDorwinionGuard)obj;
                        if (guard.hiredNPCInfo.isActive) continue;
                        ++nearbyGuards;
                    }
                    if (nearbyGuards < 8) {
                        int guardSpawns = 1 + world.rand.nextInt(6);
                        block1: for (int l = 0; l < guardSpawns; ++l) {
                            guard = new LOTREntityDorwinionGuard(world);
                            if (world.rand.nextBoolean()) {
                                guard = new LOTREntityDorwinionCrossbower(world);
                            }
                            int attempts = 16;
                            for (int a = 0; a < attempts; ++a) {
                                int i1 = i + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-spawnRange), (int)spawnRange);
                                int j1 = j + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-spawnRange / 2), (int)(spawnRange / 2));
                                int k1 = k + MathHelper.getRandomIntegerInRange((Random)world.rand, (int)(-spawnRange), (int)spawnRange);
                                Block belowBlock = world.getBlock(i1, j1 - 1, k1);
                                int belowMeta = world.getBlockMetadata(i1, j1 - 1, k1);
                                boolean belowSolid = belowBlock.isSideSolid((IBlockAccess)world, i1, j1 - 1, k1, ForgeDirection.UP);
                                if (!belowSolid || world.getBlock(i1, j1, k1).isNormalCube() || world.getBlock(i1, j1 + 1, k1).isNormalCube()) continue;
                                guard.setLocationAndAngles((double)i1 + 0.5, (double)j1, (double)k1 + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
                                guard.liftSpawnRestrictions = true;
                                if (!guard.getCanSpawnHere()) continue;
                                guard.liftSpawnRestrictions = false;
                                world.spawnEntityInWorld((Entity)guard);
                                guard.spawnRidingHorse = false;
                                guard.onSpawnWithEgg(null);
                                continue block1;
                            }
                        }
                    }
                }
                int range = 16;
                List guardList = world.getEntitiesWithinAABB(LOTREntityDorwinionGuard.class, entityplayer.boundingBox.expand((double)range, (double)range, (double)range));
                boolean anyAlert = false;
                for (Object obj : guardList) {
                    guard = (LOTREntityDorwinionGuard)obj;
                    if (guard.hiredNPCInfo.isActive) continue;
                    if (evil) {
                        guard.setAttackTarget((EntityLivingBase)entityplayer);
                        guard.sendSpeechBank(entityplayer, "dorwinion/guard/grapeAttack");
                        guard.grapeAlert = 3;
                        anyAlert = true;
                        continue;
                    }
                    if (!(world.rand.nextFloat() < chance)) continue;
                    ++guard.grapeAlert;
                    if (guard.grapeAlert >= 3) {
                        guard.setAttackTarget((EntityLivingBase)entityplayer);
                        guard.sendSpeechBank(entityplayer, "dorwinion/guard/grapeAttack");
                    } else {
                        guard.sendSpeechBank(entityplayer, "dorwinion/guard/grapeWarn");
                    }
                    anyAlert = true;
                }
                if (anyAlert && alignment >= 0.0f) {
                    LOTRLevelData.getData(entityplayer).addAlignment(entityplayer, LOTRAlignmentValues.VINEYARD_STEAL_PENALTY, LOTRFaction.DORWINION, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5);
                }
            }
        }
    }
}

