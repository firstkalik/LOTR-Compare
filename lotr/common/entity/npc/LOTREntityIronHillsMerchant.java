/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
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
import lotr.common.LOTRPotions;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTravellingTrader;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTREntityIronHillsMerchant
extends LOTREntityDwarf
implements LOTRTravellingTrader {
    public LOTREntityIronHillsMerchant(World world) {
        super(world);
        this.addTargetTasks(false);
        this.spawnRidingHorse = this.rand.nextInt(4) == 0;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityWildBoar boar = new LOTREntityWildBoar(this.worldObj);
        boar.setMountArmor(null);
        return boar;
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.IRON_HILLS_MERCHANT_BUY;
    }

    public int getTotalArmorValue() {
        return 12;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)source.getEntity();
            player.addPotionEffect(new PotionEffect(LOTRPotions.curse.id, 24000, 0));
        }
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.IRON_HILLS_MERCHANT_SELL;
    }

    @Override
    public LOTREntityNPC createTravellingEscort() {
        return new LOTREntityDwarf(this.worldObj);
    }

    @Override
    public String getDepartureSpeech() {
        return "dwarf/merchant/departure";
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
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeIronHillsMerchant);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "dwarf/merchant/friendly";
        }
        return "dwarf/dwarf/hostile";
    }
}

