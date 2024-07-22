/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityWickedElf
extends LOTREntityElf
implements LOTRMercenary {
    public LOTREntityWickedElf(World world) {
        super(world);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(5);
        if (this.rand.nextInt(2) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsWickedElf));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsWickedElf));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyWickedElf));
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetWickedElf));
            if (this.rand.nextInt(2) == 0) {
                this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsWickedElfRanger));
                this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsWickedElfRanger));
                this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyWickedElfRanger));
                this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetWickedElfRanger));
            }
        }
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeMelkoquendi));
        } else if (i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.maceMelkoquendi));
        } else if (i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeMelkoquendi));
        } else if (i == 3) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.polearmMelkoquendi));
        } else if (i == 4) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordMelkoquendi));
        } else {
            this.setCurrentItemOrArmor(1, null);
            this.setCurrentItemOrArmor(2, null);
            this.setCurrentItemOrArmor(3, null);
        }
        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.melkoquendiBow));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killDarkElf;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
    }

    @Override
    protected void dropElfItems(boolean flag, int i) {
    }

    @Override
    public boolean canElfSpawnHere() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "utumnoElf/hired";
            }
            return "utumnoElf/friendly";
        }
        return "utumnoElf/hostile";
    }

    @Override
    public String getLivingSound() {
        return null;
    }

    @Override
    public String getAttackSound() {
        return null;
    }

    @Override
    public LOTRFaction getHiringFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public int getMercBaseCost() {
        return 80;
    }

    @Override
    public float getMercAlignmentRequired() {
        return 250.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 0.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.hireMoredainMercenary);
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        return j > 30 && this.worldObj.getBlock(i, j - 1, k) == Blocks.snow;
    }
}

