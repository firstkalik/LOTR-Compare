/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngbandBerserk;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTREntityAngbandCap2
extends LOTREntityAngbandBerserk
implements LOTRUnitTradeable {
    public static float BERSERKER_SCALE = 1.2f;

    public LOTREntityAngbandCap2(World world) {
        super(world);
        this.addTargetTasks(false);
        this.npcCape = LOTRCapes.ALIGNMENT_UTUMNO.capeTexture;
        this.setSize(this.npcWidth * BERSERKER_SCALE, this.npcHeight * BERSERKER_SCALE);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(28.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.orcSkullStaff));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsangbande));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsangbande));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyangbande));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetangbande));
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.UTUMNO;
    }

    @Override
    public float getAlignmentBonus() {
        return 3.0f;
    }

    @Override
    public LOTRUnitTradeEntries getUnits() {
        return LOTRUnitTradeEntries.ANGBAND3;
    }

    @Override
    public LOTRInvasions getConquestHorn() {
        return LOTRInvasions.ANGBAND;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killAngband;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 400.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeAngbandUrukCaptain);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "angband/orc/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 100.0f) {
                return "angband/chieftain/friendly";
            }
            return "angband/chieftain/neutral";
        }
        return "angband/orc/hostile";
    }
}

