/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.entity.animal.LOTREntityCamel;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNomad;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTravellingTrader;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemHaradRobes;
import lotr.common.item.LOTRItemHaradTurban;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTREntityNomadMerchant
extends LOTREntityNomad
implements LOTRTravellingTrader {
    private static int[] robeColors = new int[]{15723226, 13551017, 6512465, 2499615, 11376219, 7825215};

    public LOTREntityNomadMerchant(World world) {
        super(world);
        this.addTargetTasks(false);
        this.spawnRidingHorse = this.rand.nextInt(4) == 0;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityCamel horse = (LOTREntityCamel)super.createMountToRide();
        horse.setMountArmor(null);
        return horse;
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.NOMAD_MERCHANT_BUY;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)source.getEntity();
            player.addPotionEffect(new PotionEffect(LOTRPotions.curse.id, 24000, 0));
        }
    }

    public int getTotalArmorValue() {
        return 12;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.NOMAD_MERCHANT_SELL;
    }

    @Override
    public LOTREntityNPC createTravellingEscort() {
        return new LOTREntityNomad(this.worldObj);
    }

    @Override
    public String getDepartureSpeech() {
        return "nearHarad/nomad/merchant/departure";
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.pouch, 1, 3));
        int robeColor = robeColors[this.rand.nextInt(robeColors.length)];
        ItemStack[] robe = new ItemStack[]{new ItemStack(LOTRMod.bootsHaradRobes), new ItemStack(LOTRMod.legsHaradRobes), new ItemStack(LOTRMod.bodyHaradRobes), new ItemStack(LOTRMod.helmetHaradRobes)};
        for (ItemStack item : robe) {
            LOTRItemHaradRobes.setRobesColor(item, robeColor);
        }
        if (this.rand.nextBoolean()) {
            LOTRItemHaradTurban.setHasOrnament(robe[3], true);
        }
        this.setCurrentItemOrArmor(1, robe[0]);
        this.setCurrentItemOrArmor(2, robe[1]);
        this.setCurrentItemOrArmor(3, robe[2]);
        this.setCurrentItemOrArmor(4, robe[3]);
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 0.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeNomadMerchant);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "nearHarad/nomad/merchant/friendly";
        }
        return "nearHarad/nomad/merchant/hostile";
    }
}

