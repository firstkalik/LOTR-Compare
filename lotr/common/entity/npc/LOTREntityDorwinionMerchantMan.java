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
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityDorwinionGuard;
import lotr.common.entity.npc.LOTREntityDorwinionMan;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTravellingTrader;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LOTREntityDorwinionMerchantMan
extends LOTREntityDorwinionMan
implements LOTRTravellingTrader {
    public static final int[] hatColors = new int[]{15387062, 12361599, 7422850, 12677797, 13401212, 11350064, 9523548, 12502137, 11718290, 8817612, 6316484};
    public static final int[] featherColors = new int[]{16777215, 12887724, 15061504, 0, 7475245, 4402118, 8311657};

    public LOTREntityDorwinionMerchantMan(World world) {
        super(world);
        this.addTargetTasks(false);
        this.spawnRidingHorse = this.rand.nextInt(4) == 0;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityHorse horse = (LOTREntityHorse)super.createMountToRide();
        horse.setMountArmor(null);
        return horse;
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
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.DORWINION_MERCHANT_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.DORWINION_MERCHANT_SELL;
    }

    @Override
    public LOTREntityNPC createTravellingEscort() {
        return new LOTREntityDorwinionGuard(this.worldObj);
    }

    @Override
    public String getDepartureSpeech() {
        return "dorwinion/merchant/departure";
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        ItemStack hat = new ItemStack(LOTRMod.leatherHat);
        int colorHat = hatColors[this.rand.nextInt(hatColors.length)];
        int colorFeather = featherColors[this.rand.nextInt(featherColors.length)];
        LOTRItemLeatherHat.setHatColor(hat, colorHat);
        LOTRItemLeatherHat.setFeatherColor(hat, colorFeather);
        this.setCurrentItemOrArmor(4, hat);
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
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeDorwinionMerchant);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "dorwinion/merchant/friendly";
        }
        return "dorwinion/merchant/hostile";
    }
}

