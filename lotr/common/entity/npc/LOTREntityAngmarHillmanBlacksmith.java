/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import addon.drealm.database.DRAchievement;
import addon.drealm.database.DRRegistry;
import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityAngmarHillman;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRTradeEntries;
import lotr.common.entity.npc.LOTRTradeEntry;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAngmarHillmanBlacksmith
extends LOTREntityAngmarHillman
implements LOTRTradeable.Smith {
    public LOTREntityAngmarHillmanBlacksmith(World world) {
        super(world);
        this.addTargetTasks(false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 50.0f && this.isFriendlyAndAligned(entityplayer);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        this.dropItem(Items.iron_ingot, 1 + this.rand.nextInt(3) + this.rand.nextInt(i + 1));
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return new LOTRTradeEntries(LOTRTradeEntries.TradeType.BUY, new LOTRTradeEntry(new ItemStack(DRRegistry.rhudaur_table), 100), new LOTRTradeEntry(new ItemStack(DRRegistry.sword_rhudaur), 15), new LOTRTradeEntry(new ItemStack(DRRegistry.dagger_rhudaur), 9), new LOTRTradeEntry(new ItemStack(DRRegistry.spear_rhudaur), 16), new LOTRTradeEntry(new ItemStack(DRRegistry.helmet_rhudaur), 20), new LOTRTradeEntry(new ItemStack(DRRegistry.body_rhudaur), 32), new LOTRTradeEntry(new ItemStack(DRRegistry.legs_rhudaur), 26), new LOTRTradeEntry(new ItemStack(DRRegistry.boots_rhudaur), 17), new LOTRTradeEntry(new ItemStack(LOTRMod.blacksmithHammer), 18), new LOTRTradeEntry(new ItemStack(Blocks.iron_bars, 8), 20), new LOTRTradeEntry(new ItemStack(LOTRMod.bronzeBars, 8), 20), new LOTRTradeEntry(new ItemStack(DRRegistry.hammer_rhudaur), 18), new LOTRTradeEntry(new ItemStack(LOTRMod.crossbowBolt, 4), 3), new LOTRTradeEntry(new ItemStack(LOTRMod.ironCrossbow), 15));
    }

    @Override
    public EntityAIBase getHillmanAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return new LOTRTradeEntries(LOTRTradeEntries.TradeType.SELL, new LOTRTradeEntry(new ItemStack(DRRegistry.cast_iron_ingot), 4), new LOTRTradeEntry(new ItemStack(Items.iron_ingot), 3), new LOTRTradeEntry(new ItemStack(Items.coal, 2, 32767), 1), new LOTRTradeEntry(new ItemStack(Items.gold_ingot), 10), new LOTRTradeEntry(new ItemStack(LOTRMod.copper), 3), new LOTRTradeEntry(new ItemStack(LOTRMod.tin), 3), new LOTRTradeEntry(new ItemStack(LOTRMod.bronze), 3), new LOTRTradeEntry(new ItemStack(Items.string, 3), 1), new LOTRTradeEntry(new ItemStack(LOTRMod.diamond), 25), new LOTRTradeEntry(new ItemStack(LOTRMod.sapphire), 12), new LOTRTradeEntry(new ItemStack(LOTRMod.pearl), 25), new LOTRTradeEntry(new ItemStack(Items.leather), 2));
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(DRAchievement.trade_rhudaur_smith);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.blacksmithHammer));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        return data;
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }
}

