/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetTroll;
import lotr.common.entity.npc.LOTREntityDurmethOrc;
import lotr.common.entity.npc.LOTREntityDurmethOrcArcher;
import lotr.common.entity.npc.LOTREntityDurmethOrcWarrior;
import lotr.common.entity.npc.LOTREntityDurmethOrcWarriorArcher;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityWarg;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.biome.LOTRBiomeGenNearHarad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityDurmethWarg
extends LOTREntityWarg
implements LOTRBiomeGenNearHarad.ImmuneToFrost {
    public LOTREntityDurmethWarg(World world) {
        super(world);
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetTroll.class);
    }

    @Override
    public EntityAIBase getWargAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.75, false);
    }

    @Override
    public LOTREntityNPC createWargRider() {
        LOTREntityDurmethOrc rider;
        int randomChoice = this.worldObj.rand.nextInt(4);
        switch (randomChoice) {
            case 0: {
                rider = new LOTREntityDurmethOrcArcher(this.worldObj);
                break;
            }
            case 1: {
                rider = new LOTREntityDurmethOrc(this.worldObj);
                break;
            }
            case 2: {
                rider = new LOTREntityDurmethOrcWarriorArcher(this.worldObj);
                break;
            }
            case 3: {
                rider = new LOTREntityDurmethOrcWarrior(this.worldObj);
                break;
            }
            default: {
                rider = new LOTREntityDurmethOrc(this.worldObj);
            }
        }
        int armorChance = this.worldObj.rand.nextInt(6);
        if (rider.getClass() == LOTREntityDurmethOrcArcher.class || rider.getClass() == LOTREntityDurmethOrc.class) {
            this.setWargArmor(new ItemStack(LOTRMod.wargArmorAngmar));
        } else if (rider.getClass() == LOTREntityDurmethOrcWarriorArcher.class || rider.getClass() == LOTREntityDurmethOrcWarrior.class) {
            this.setWargArmor(new ItemStack(LOTRMod.wargArmorUruk));
        }
        return rider;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)20, (int)40));
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(34.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
        this.getEntityAttribute(npcAttackDamage).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)4, (int)6));
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        if (this.worldObj.isDaytime() && this.worldObj.canBlockSeeTheSky(i, j, k)) {
            return false;
        }
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if (!(biome instanceof LOTRBiome && ((LOTRBiome)biome).canSpawnHostilesInDay() || !this.worldObj.isDaytime())) {
            return false;
        }
        return j > 30 && j <= 113 && (this.worldObj.getBlock(i, j - 1, k) == Blocks.snow || this.worldObj.getBlock(i, j - 1, k) == Blocks.grass || this.worldObj.getBlock(i, j - 1, k) == Blocks.sand && this.worldObj.getBlockMetadata(i, j - 1, k) == 0 || this.worldObj.getBlock(i, j - 1, k) == Blocks.sand && this.worldObj.getBlockMetadata(i, j - 1, k) == 1);
    }

    @Override
    public float getAlignmentBonus() {
        return 2.5f;
    }
}

