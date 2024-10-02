/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetWoodElf;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityIronfist
extends LOTREntityDwarf {
    public LOTREntityIronfist(World world) {
        super(world);
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetWoodElf.class);
        this.familyInfo.marriageEntityClass = LOTREntityIronfist.class;
        this.familyInfo.marriageAchievement = LOTRAchievement.marryMornaugrimDwarf;
    }

    @Override
    protected LOTRFoods getDwarfFoods() {
        return LOTRFoods.DWARF;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerRed));
        this.npcItemsInv.setIdleItem(null);
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.RED_MOUNTAINS;
    }

    @Override
    protected Item getDwarfSteelDrop() {
        return LOTRMod.dwarfSteel;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killDwarf;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
        for (int l = 0; l < bones; ++l) {
            this.dropItem(LOTRMod.dwarfBone, 1);
        }
        if (this.rand.nextInt(4) == 0) {
            this.dropChestContents(this.getLarderDrops(), 1, 2 + i);
        }
        if (this.rand.nextInt(8) == 0) {
            this.dropChestContents(this.getGenericDrops(), 1, 2 + i);
        }
        if (flag) {
            int rareDropChance = 20 - i * 4;
            if (this.rand.nextInt(rareDropChance = Math.max(rareDropChance, 1)) == 0) {
                int randDrop = this.rand.nextInt(4);
                switch (randDrop) {
                    case 0: {
                        this.entityDropItem(new ItemStack(Items.iron_ingot), 0.0f);
                        break;
                    }
                    case 1: {
                        this.entityDropItem(new ItemStack(this.getDwarfSteelDrop()), 0.0f);
                        break;
                    }
                    case 2: {
                        this.entityDropItem(new ItemStack(Items.gold_nugget, 1 + this.rand.nextInt(3)), 0.0f);
                        break;
                    }
                    case 3: {
                        this.entityDropItem(new ItemStack(LOTRMod.silverNugget, 1 + this.rand.nextInt(3)), 0.0f);
                    }
                }
            }
            int mithrilBookChance = 40 - i * 5;
            if (this.rand.nextInt(mithrilBookChance = Math.max(mithrilBookChance, 1)) == 0) {
                this.entityDropItem(new ItemStack(LOTRMod.mithrilBook), 0.0f);
            }
        }
    }

    @Override
    protected LOTRChestContents getLarderDrops() {
        return LOTRChestContents.REDDWARF_SMITHY;
    }

    @Override
    protected LOTRChestContents getGenericDrops() {
        return LOTRChestContents.LOTRChestContents2.REDDWARFTOWER;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "ironfist/dwarf/hired";
            }
            return this.isChild() ? "ironfist/child/friendly" : "ironfist/dwarf/friendly";
        }
        return this.isChild() ? "ironfist/child/hostile" : "ironfist/dwarf/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.REDMOUNTAINS.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.REDMOUNTAINS;
    }
}

