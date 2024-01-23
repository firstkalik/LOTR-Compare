/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityWoodElf;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityWoodElfScout
extends LOTREntityWoodElf {
    private static final UUID scoutArmorSpeedBoost_id = UUID.fromString("cf0ceb91-0f13-4788-be0e-a6c67a830308");
    public static final AttributeModifier scoutArmorSpeedBoost = new AttributeModifier(scoutArmorSpeedBoost_id, "WE Scout armor speed boost", 0.3, 2).setSaved(false);

    public LOTREntityWoodElfScout(World world) {
        super(world);
        this.tasks.addTask(2, this.rangedAttackAI);
    }

    @Override
    protected EntityAIBase createElfRangedAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 25, 35, 24.0f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getRangedWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsWoodElvenScout));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsWoodElvenScout));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyWoodElvenScout));
        if (this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetWoodElvenScout));
        }
        return data;
    }

    @Override
    public void onLivingUpdate() {
        ItemStack currentItem;
        EntityLivingBase lastAttacker;
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.isEntityAlive() && this.ridingEntity == null && (currentItem = this.getEquipmentInSlot(0)) != null && currentItem.getItem() instanceof ItemBow && (lastAttacker = this.getAITarget()) != null && this.getDistanceSqToEntity((Entity)lastAttacker) < 16.0 && this.rand.nextInt(20) == 0) {
            for (int l = 0; l < 32; ++l) {
                int j;
                int k;
                int i = MathHelper.floor_double((double)this.posX) - this.rand.nextInt(16) + this.rand.nextInt(16);
                if (!(this.getDistance((double)i, (double)(j = MathHelper.floor_double((double)this.posY) - this.rand.nextInt(3) + this.rand.nextInt(3)), (double)(k = MathHelper.floor_double((double)this.posZ) - this.rand.nextInt(16) + this.rand.nextInt(16))) > 6.0) || !this.worldObj.getBlock(i, j - 1, k).isNormalCube() || this.worldObj.getBlock(i, j, k).isNormalCube() || this.worldObj.getBlock(i, j + 1, k).isNormalCube()) continue;
                double d = (double)i + 0.5;
                double d1 = j;
                double d2 = (double)k + 0.5;
                AxisAlignedBB aabb = this.boundingBox.copy().offset(d - this.posX, d1 - this.posY, d2 - this.posZ);
                if (!this.worldObj.checkNoEntityCollision(aabb) || !this.worldObj.getCollidingBoundingBoxes((Entity)this, aabb).isEmpty() || this.worldObj.isAnyLiquid(aabb)) continue;
                this.doTeleportEffects();
                this.setPosition(d, d1, d2);
                break;
            }
        }
    }

    private void doTeleportEffects() {
        this.worldObj.playSoundAtEntity((Entity)this, "lotr:elf.woodElf_teleport", this.getSoundVolume(), 0.5f + this.rand.nextFloat());
        this.worldObj.setEntityState((Entity)this, (byte)15);
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte b) {
        if (b == 15) {
            for (int i = 0; i < 16; ++i) {
                double d = this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width;
                double d1 = this.posY + this.rand.nextDouble() * (double)this.height;
                double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width;
                double d3 = -0.05 + (double)(this.rand.nextFloat() * 0.1f);
                double d4 = -0.05 + (double)(this.rand.nextFloat() * 0.1f);
                double d5 = -0.05 + (double)(this.rand.nextFloat() * 0.1f);
                LOTRMod.proxy.spawnParticle("leafGreen_" + (20 + this.rand.nextInt(30)), d, d1, d2, d3, d4, d5);
            }
        } else {
            super.handleHealthUpdate(b);
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
                return "woodElf/elf/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= LOTREntityWoodElfScout.getWoodlandTrustLevel()) {
                return "woodElf/warrior/friendly";
            }
            return "woodElf/elf/neutral";
        }
        return "woodElf/warrior/hostile";
    }
}

