/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
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
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityWickedDwarf
extends LOTREntityDwarf
implements LOTRTradeable.Smith {
    public static final LOTRFaction[] tradeFactions = new LOTRFaction[]{LOTRFaction.MORDOR, LOTRFaction.GUNDABAD, LOTRFaction.RHUDEL};
    private static ItemStack[] wickedWeapons = new ItemStack[]{new ItemStack(LOTRMod.swordWickedDwarf), new ItemStack(LOTRMod.battleaxeWickedDwarf), new ItemStack(LOTRMod.hammerWickedDwarf)};

    public LOTREntityWickedDwarf(World world) {
        super(world);
        this.addTargetTasks(true);
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.WICKED_DWARF_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.WICKED_DWARF_SELL;
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(wickedWeapons.length);
        this.npcItemsInv.setMeleeWeapon(wickedWeapons[i].copy());
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.pickaxeWickedDwarf));
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsDwarven));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsDwarven));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyDwarven));
        }
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsIronfist));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsIronfist));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyIronfist));
        }
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsStonefoot));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsStonefoot));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyStonefoot));
        }
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsBlacklock));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsBlacklock));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyBlacklock));
        }
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsStiffbeard));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsStiffbeard));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyStiffbeard));
        }
        if (this.rand.nextInt(4) == 0) {
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.wdboots));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.wdlegs));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.wdbody));
        } else {
            this.setCurrentItemOrArmor(1, null);
            this.setCurrentItemOrArmor(2, null);
            this.setCurrentItemOrArmor(3, null);
        }
        this.setCurrentItemOrArmor(4, null);
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.MORDOR;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killWickedDwarf;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        boolean hasAlignment = false;
        for (LOTRFaction f : tradeFactions) {
            if (LOTRLevelData.getData(entityplayer).getAlignment(f) < 100.0f) continue;
            hasAlignment = true;
            break;
        }
        return hasAlignment && this.isFriendly(entityplayer);
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeWickedDwarf);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return false;
    }

    @Override
    protected boolean canDwarfSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        return j > 62 && this.worldObj.getBlock(i, j - 1, k) == this.worldObj.getBiomeGenForCoords((int)i, (int)k).topBlock;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double((double)this.posX);
        int j = MathHelper.floor_double((double)this.boundingBox.minY);
        int k = MathHelper.floor_double((double)this.posZ);
        return j > 30 && this.worldObj.getBlock(i, j - 1, k) == Blocks.snow;
    }

    @Override
    protected boolean canDwarfSpawnAboveGround() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.canTradeWith(entityplayer)) {
                return "dwarf/wicked/friendly";
            }
            return "dwarf/wicked/neutral";
        }
        return "dwarf/wicked/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return null;
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return null;
    }
}

