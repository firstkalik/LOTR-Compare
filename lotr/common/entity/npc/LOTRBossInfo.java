/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.apache.commons.lang3.tuple.Pair
 */
package lotr.common.entity.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTRNPCTargetSelector;
import lotr.common.entity.npc.LOTRBoss;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.LOTRWorldProviderUtumno;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

public class LOTRBossInfo {
    private LOTREntityNPC theNPC;
    private LOTRBoss theBoss;
    public EntityPlayer lastAttackingPlayer;
    private Map<UUID, Pair<Integer, Float>> playerHurtTimes = new HashMap<UUID, Pair<Integer, Float>>();
    private static int PLAYER_HURT_COOLDOWN = 600;
    private static float PLAYER_DAMAGE_THRESHOLD = 40.0f;
    public boolean jumpAttack;

    public LOTRBossInfo(LOTRBoss boss) {
        this.theBoss = boss;
        this.theNPC = (LOTREntityNPC)((Object)this.theBoss);
    }

    public float getHealthChanceModifier() {
        float f = 1.0f - this.theNPC.getHealth() / this.theNPC.getMaxHealth();
        return MathHelper.sqrt_float((float)f);
    }

    public List getNearbyEnemies() {
        ArrayList<EntityPlayer> enemies = new ArrayList<EntityPlayer>();
        List players = this.theNPC.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.theNPC.boundingBox.expand(12.0, 6.0, 12.0));
        for (int i = 0; i < players.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer)players.get(i);
            if (entityplayer.capabilities.isCreativeMode || !(LOTRLevelData.getData(entityplayer).getAlignment(this.theNPC.getFaction()) < 0.0f)) continue;
            enemies.add(entityplayer);
        }
        enemies.addAll(this.theNPC.worldObj.selectEntitiesWithinAABB(EntityLiving.class, this.theNPC.boundingBox.expand(12.0, 6.0, 12.0), (IEntitySelector)new LOTRNPCTargetSelector((EntityLiving)this.theNPC)));
        return enemies;
    }

    public void doJumpAttack(double jumpSpeed) {
        this.jumpAttack = true;
        this.theNPC.motionY = jumpSpeed;
    }

    public void doTargetedJumpAttack(double jumpSpeed) {
        if (!this.theNPC.worldObj.isRemote && this.lastAttackingPlayer != null && (this.lastAttackingPlayer.posY - this.theNPC.posY > 10.0 || this.theNPC.getDistanceSqToEntity((Entity)this.lastAttackingPlayer) > 400.0) && this.theNPC.onGround) {
            this.doJumpAttack(jumpSpeed);
            this.theNPC.motionX = this.lastAttackingPlayer.posX - this.theNPC.posX;
            this.theNPC.motionY = this.lastAttackingPlayer.posY - this.theNPC.posY;
            this.theNPC.motionZ = this.lastAttackingPlayer.posZ - this.theNPC.posZ;
            this.theNPC.motionX /= 10.0;
            this.theNPC.motionY /= 10.0;
            this.theNPC.motionZ /= 10.0;
            if (this.theNPC.motionY < jumpSpeed) {
                this.theNPC.motionY = jumpSpeed;
            }
            this.theNPC.getLookHelper().setLookPositionWithEntity((Entity)this.lastAttackingPlayer, 100.0f, 100.0f);
            this.theNPC.getLookHelper().onUpdateLook();
            this.theNPC.rotationYaw = this.theNPC.rotationYawHead;
        }
    }

    public float onFall(float fall) {
        if (!this.theNPC.worldObj.isRemote && this.jumpAttack) {
            fall = 0.0f;
            this.jumpAttack = false;
            List enemies = this.getNearbyEnemies();
            float attackDamage = (float)this.theNPC.getEntityAttribute(LOTREntityNPC.npcAttackDamage).getAttributeValue();
            for (int i = 0; i < enemies.size(); ++i) {
                EntityLivingBase entity = (EntityLivingBase)enemies.get(i);
                float strength = 12.0f - this.theNPC.getDistanceToEntity((Entity)entity) / 3.0f;
                entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.theNPC), (strength /= 12.0f) * attackDamage * 3.0f);
                float knockback = strength * 3.0f;
                entity.addVelocity((double)(-MathHelper.sin((float)(this.theNPC.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f), 0.25 * (double)knockback, (double)(MathHelper.cos((float)(this.theNPC.rotationYaw * 3.1415927f / 180.0f)) * knockback * 0.5f));
            }
            this.theBoss.onJumpAttackFall();
        }
        return fall;
    }

    private void clearSurroundingBlocks() {
        if (LOTRMod.canGrief(this.theNPC.worldObj)) {
            int xzRange = MathHelper.ceiling_float_int((float)(this.theNPC.width / 2.0f * 1.5f));
            int yRange = MathHelper.ceiling_float_int((float)(this.theNPC.height * 1.5f));
            int xzDist = xzRange * xzRange + xzRange * xzRange;
            int i = MathHelper.floor_double((double)this.theNPC.posX);
            int j = MathHelper.floor_double((double)this.theNPC.boundingBox.minY);
            int k = MathHelper.floor_double((double)this.theNPC.posZ);
            for (int i1 = i - xzRange; i1 <= i + xzRange; ++i1) {
                for (int j1 = j; j1 <= j + yRange; ++j1) {
                    for (int k1 = k - xzRange; k1 <= k + xzRange; ++k1) {
                        Block block;
                        int i2 = i1 - i;
                        int k2 = k1 - k;
                        int dist = i2 * i2 + k2 * k2;
                        if (dist >= xzDist || (block = this.theNPC.worldObj.getBlock(i1, j1, k1)) == null || block.getMaterial().isLiquid()) continue;
                        float resistance = block.getExplosionResistance((Entity)this.theNPC, this.theNPC.worldObj, i1, j1, k1, this.theNPC.posX, this.theNPC.boundingBox.minY + (double)(this.theNPC.height / 2.0f), this.theNPC.posZ);
                        if (block instanceof LOTRWorldProviderUtumno.UtumnoBlock && LOTRDimension.getCurrentDimension(this.theNPC.worldObj) != LOTRDimension.UTUMNO) {
                            resistance = 100.0f;
                        }
                        if (!(resistance < 2000.0f)) continue;
                        block.dropBlockAsItemWithChance(this.theNPC.worldObj, i1, j1, k1, this.theNPC.worldObj.getBlockMetadata(i1, j1, k1), resistance / 100.0f, 0);
                        this.theNPC.worldObj.setBlockToAir(i1, j1, k1);
                    }
                }
            }
        }
    }

    public void onUpdate() {
        if (this.lastAttackingPlayer != null && (!this.lastAttackingPlayer.isEntityAlive() || this.lastAttackingPlayer.capabilities.isCreativeMode)) {
            this.lastAttackingPlayer = null;
        }
        if (!this.theNPC.worldObj.isRemote) {
            HashMap<UUID, Pair<Integer, Float>> playerHurtTimes_new = new HashMap<UUID, Pair<Integer, Float>>();
            for (Map.Entry<UUID, Pair<Integer, Float>> entry : this.playerHurtTimes.entrySet()) {
                UUID player = entry.getKey();
                int time = (Integer)entry.getValue().getLeft();
                float damage = ((Float)entry.getValue().getRight()).floatValue();
                if (--time <= 0) continue;
                playerHurtTimes_new.put(player, (Pair<Integer, Float>)Pair.of((Object)time, (Object)Float.valueOf(damage)));
            }
            this.playerHurtTimes = playerHurtTimes_new;
        }
        if (!this.theNPC.worldObj.isRemote && this.jumpAttack && this.theNPC.ticksExisted % 5 == 0) {
            this.clearSurroundingBlocks();
        }
    }

    public void onHurt(DamageSource damagesource, float f) {
        if (!this.theNPC.worldObj.isRemote) {
            EntityPlayer playerSource;
            if (damagesource.getEntity() instanceof EntityPlayer) {
                EntityPlayer attackingPlayer = (EntityPlayer)damagesource.getEntity();
                if (!attackingPlayer.capabilities.isCreativeMode) {
                    this.lastAttackingPlayer = attackingPlayer;
                }
            }
            if ((playerSource = LOTRMod.getDamagingPlayerIncludingUnits(damagesource)) != null) {
                UUID player = playerSource.getUniqueID();
                int time = PLAYER_HURT_COOLDOWN;
                float totalDamage = f;
                if (this.playerHurtTimes.containsKey(player)) {
                    Pair<Integer, Float> pair = this.playerHurtTimes.get(player);
                    totalDamage += ((Float)pair.getRight()).floatValue();
                }
                this.playerHurtTimes.put(player, (Pair<Integer, Float>)Pair.of((Object)time, (Object)Float.valueOf(totalDamage)));
            }
        }
    }

    public void onDeath(DamageSource damagesource) {
        this.onHurt(damagesource, 0.0f);
        if (!this.theNPC.worldObj.isRemote) {
            for (Map.Entry<UUID, Pair<Integer, Float>> e : this.playerHurtTimes.entrySet()) {
                UUID player = e.getKey();
                Pair<Integer, Float> pair = e.getValue();
                float damage = ((Float)pair.getRight()).floatValue();
                if (!(damage >= PLAYER_DAMAGE_THRESHOLD)) continue;
                LOTRLevelData.getData(player).addAchievement(this.theBoss.getBossKillAchievement());
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound data = new NBTTagCompound();
        NBTTagList playerHurtTags = new NBTTagList();
        for (Map.Entry<UUID, Pair<Integer, Float>> entry : this.playerHurtTimes.entrySet()) {
            UUID player = entry.getKey();
            Pair<Integer, Float> pair = entry.getValue();
            int time = (Integer)pair.getLeft();
            float damage = ((Float)pair.getRight()).floatValue();
            NBTTagCompound playerTag = new NBTTagCompound();
            playerTag.setString("UUID", player.toString());
            playerTag.setInteger("Time", time);
            playerTag.setFloat("Damage", damage);
            playerHurtTags.appendTag((NBTBase)playerTag);
        }
        data.setTag("PlayerHurtTimes", (NBTBase)playerHurtTags);
        data.setBoolean("JumpAttack", this.jumpAttack);
        nbt.setTag("NPCBossInfo", (NBTBase)data);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagCompound data = nbt.getCompoundTag("NPCBossInfo");
        if (data != null) {
            NBTTagList playerHurtTags = data.getTagList("PlayerHurtTimes", 10);
            for (int i = 0; i < playerHurtTags.tagCount(); ++i) {
                NBTTagCompound playerTag = playerHurtTags.getCompoundTagAt(i);
                UUID player = UUID.fromString(playerTag.getString("UUID"));
                if (player == null) continue;
                int time = playerTag.getInteger("Time");
                float damage = playerTag.getFloat("Damage");
                this.playerHurtTimes.put(player, (Pair<Integer, Float>)Pair.of((Object)time, (Object)Float.valueOf(damage)));
            }
            this.jumpAttack = data.getBoolean("JumpAttack");
        }
    }
}

