/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.awt.Color;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNearHaradrim;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemHaradRobes;
import lotr.common.item.LOTRItemHaradTurban;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class LOTREntitySouthronTrader
extends LOTREntityNearHaradrim
implements LOTRTradeable {
    public LOTREntitySouthronTrader(World world) {
        super(world);
        this.addTargetTasks(false);
    }

    public static ItemStack createTraderTurban(Random random) {
        ItemStack turban = new ItemStack(LOTRMod.helmetHaradRobes);
        if (random.nextInt(3) == 0) {
            LOTRItemHaradTurban.setHasOrnament(turban, true);
        }
        float h = random.nextFloat() * 360.0f;
        float s = MathHelper.randomFloatClamp((Random)random, (float)0.6f, (float)0.8f);
        float b = MathHelper.randomFloatClamp((Random)random, (float)0.5f, (float)0.75f);
        int turbanColor = Color.HSBtoRGB(h, s, b) & 0xFFFFFF;
        LOTRItemHaradRobes.setRobesColor(turban, turbanColor);
        return turban;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.setCurrentItemOrArmor(4, LOTREntitySouthronTrader.createTraderTurban(this.rand));
        return data;
    }

    @Override
    public void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
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
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeBazaarTrader);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "nearHarad/coast/bazaarTrader/friendly";
        }
        return "nearHarad/coast/bazaarTrader/hostile";
    }
}

