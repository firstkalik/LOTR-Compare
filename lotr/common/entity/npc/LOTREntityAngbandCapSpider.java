/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityAngbandBerserk;
import lotr.common.entity.npc.LOTREntityAngbandSpiderIce;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class LOTREntityAngbandCapSpider
extends LOTREntityAngbandBerserk
implements LOTRUnitTradeable {
    public LOTREntityAngbandCapSpider(World world) {
        super(world);
        this.addTargetTasks(false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0);
        this.getEntityAttribute(npcAttackDamageExtra).setBaseValue(1.1);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.orcSkullStaff));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsBerserk));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsBerserk));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyBerserk));
        this.setCurrentItemOrArmor(4, null);
        if (!this.worldObj.isRemote) {
            LOTREntityAngbandSpiderIce spider = new LOTREntityAngbandSpiderIce(this.worldObj);
            spider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
            spider.setSpiderScale(3);
            if (this.worldObj.func_147461_a(spider.boundingBox).isEmpty() || this.liftSpawnRestrictions) {
                spider.onSpawnWithEgg(null);
                this.worldObj.spawnEntityInWorld((Entity)spider);
                this.mountEntity((Entity)spider);
            }
        }
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 3.0f;
    }

    @Override
    public LOTRUnitTradeEntries getUnits() {
        return LOTRUnitTradeEntries.ANGBAND1;
    }

    @Override
    public LOTRInvasions getConquestHorn() {
        return LOTRInvasions.ANGBAND1;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 250.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeAngbandSpiderUrukCaptain);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.canTradeWith(entityplayer)) {
                return "angband/chieftain/friendly";
            }
            return "angband/chieftain/neutral";
        }
        return "angband/orc/hostile";
    }
}

