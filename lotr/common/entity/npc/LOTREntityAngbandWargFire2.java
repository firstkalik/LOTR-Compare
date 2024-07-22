/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTRBoss;
import lotr.common.entity.npc.LOTRBossInfo;
import lotr.common.entity.npc.LOTREntityAngbandBerserk2;
import lotr.common.entity.npc.LOTREntityAngbandOrcArcher;
import lotr.common.entity.npc.LOTREntityAngbandWarg;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTREntityTree;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.LOTRWorldProviderUtumno;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class LOTREntityAngbandWargFire2
extends LOTREntityWarg
implements LOTRBoss {
    public LOTREntityAngbandWargFire2(World world) {
        super(world);
        this.isImmuneToFire = true;
        this.isStrongOrc = false;
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.setWargType(LOTREntityWarg.WargType.FIRE);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)60, (int)80));
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 1));
        this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0));
        this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0));
        String s = this.rand.nextInt(3) > 0 ? "flame" : "smoke";
        this.worldObj.spawnParticle(s, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, 0.0, 0.0, 0.0);
        if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 10 == 0) {
            this.heal(0.5f);
        }
        if (!this.worldObj.isRemote && this.rand.nextInt(80) == 0) {
            boolean isUtumno = this.worldObj.provider instanceof LOTRWorldProviderUtumno;
            for (int l2 = 0; l2 < 24; ++l2) {
                int i = MathHelper.floor_double((double)this.posX);
                int j = MathHelper.floor_double((double)this.boundingBox.minY);
                int k = MathHelper.floor_double((double)this.posZ);
                Block block = this.worldObj.getBlock(i += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-4, (int)4), j += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-4, (int)8), k += MathHelper.getRandomIntegerInRange((Random)this.rand, (int)-8, (int)8));
                float maxResistance = Blocks.obsidian.getExplosionResistance((Entity)this);
                if (!block.isReplaceable((IBlockAccess)this.worldObj, i, j, k) && (!isUtumno || block.getExplosionResistance((Entity)this) > maxResistance) || !Blocks.fire.canPlaceBlockAt(this.worldObj, i, j, k)) continue;
                this.worldObj.setBlock(i, j, k, (Block)Blocks.fire, 0, 3);
            }
        }
        if (!this.worldObj.isRemote && this.getHealth() < this.getMaxHealth() && this.rand.nextInt(50) == 0) {
            this.trySummonEnts();
        }
    }

    @Override
    public float getBaseChanceModifier() {
        return this.bossInfo.getHealthChanceModifier();
    }

    private void trySummonEnts() {
        float f = this.getBaseChanceModifier();
        f *= 0.9f;
        List nearbyTrees = this.worldObj.getEntitiesWithinAABB(LOTREntityTree.class, this.boundingBox.expand(24.0, 8.0, 24.0));
        int maxNearbyTrees = 10;
        float nearbyModifier = (float)(maxNearbyTrees - nearbyTrees.size()) / (float)maxNearbyTrees;
        f *= nearbyModifier;
        if (this.rand.nextFloat() < f) {
            LOTREntityAngbandOrcArcher tree = this.rand.nextInt(3) == 0 ? new LOTREntityAngbandOrcArcher(this.worldObj) : new LOTREntityAngbandOrcArcher(this.worldObj);
            LOTREntityAngbandWarg tree1 = this.rand.nextInt(3) == 0 ? new LOTREntityAngbandWarg(this.worldObj) : new LOTREntityAngbandWarg(this.worldObj);
            int range = 12;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.posY);
            int k = MathHelper.floor_double((double)this.posZ);
            for (int l = 0; l < 30; ++l) {
                int k1;
                int j1;
                int i1 = i + MathHelper.getRandomIntegerInRange((Random)this.rand, (int)(-range), (int)range);
                if (!this.worldObj.getBlock(i1, (j1 = j + MathHelper.getRandomIntegerInRange((Random)this.rand, (int)(-range), (int)range)) - 1, k1 = k + MathHelper.getRandomIntegerInRange((Random)this.rand, (int)(-range), (int)range)).isNormalCube() || this.worldObj.getBlock(i1, j1, k1).isNormalCube() || this.worldObj.getBlock(i1, j1 + 1, k1).isNormalCube()) continue;
                tree.setLocationAndAngles((double)i1 + 0.5, (double)j1, (double)k1 + 0.5, this.rand.nextFloat() * 360.0f, 0.0f);
                tree.liftSpawnRestrictions = true;
                if (!tree.getCanSpawnHere()) continue;
                tree1.setLocationAndAngles((double)i1 + 0.5, (double)j1, (double)k1 + 0.5, this.rand.nextFloat() * 360.0f, 0.0f);
                tree1.liftSpawnRestrictions = true;
                if (!tree.getCanSpawnHere()) continue;
                tree.liftSpawnRestrictions = false;
                tree1.liftSpawnRestrictions = false;
                ((LOTREntityOrc)tree).onSpawnWithEgg(null);
                tree1.onSpawnWithEgg(null);
                this.worldObj.spawnEntityInWorld((Entity)tree);
                this.worldObj.spawnEntityInWorld((Entity)tree1);
                this.worldObj.playSoundAtEntity((Entity)tree, "lotr:orc.say", this.getSoundVolume(), this.getSoundPitch());
                this.worldObj.playSoundAtEntity((Entity)tree1, "warg.say", this.getSoundVolume(), this.getSoundPitch());
                break;
            }
        }
    }

    @Override
    public int getTotalArmorValue() {
        return 12;
    }

    @Override
    public float getAlignmentBonus() {
        return 5.0f;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);
        if (!this.worldObj.isRemote && flag) {
            entity.setFire(6);
        }
        return flag;
    }

    @Override
    public LOTREntityNPC createWargRider() {
        return this.worldObj.rand.nextBoolean() ? new LOTREntityAngbandBerserk2(this.worldObj) : new LOTREntityAngbandBerserk2(this.worldObj);
    }

    @Override
    public boolean canWargBeRidden() {
        return false;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        int hide = this.rand.nextInt(3) + this.rand.nextInt(1 + i);
        for (int l = 0; l < hide; ++l) {
            this.dropItem(LOTRMod.balrogFire, 2);
        }
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        if (!this.worldObj.isRemote) {
            int exRange = 8;
            int i = MathHelper.floor_double((double)this.posX);
            int j = MathHelper.floor_double((double)this.posY);
            int k = MathHelper.floor_double((double)this.posZ);
            for (int i1 = i - exRange; i1 <= i + exRange; ++i1) {
                for (int j1 = j - exRange; j1 <= j + exRange; ++j1) {
                    for (int k1 = k - exRange; k1 <= k + exRange; ++k1) {
                        Block block = this.worldObj.getBlock(i1, j1, k1);
                        if (block.getMaterial() != Material.fire) continue;
                        this.worldObj.setBlockToAir(i1, j1, k1);
                    }
                }
            }
        }
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killUtumnoWarg;
    }

    @Override
    public LOTRAchievement getBossKillAchievement() {
        return null;
    }

    @Override
    public void onJumpAttackFall() {
    }
}

