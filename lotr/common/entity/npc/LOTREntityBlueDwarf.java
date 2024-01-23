/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityBlueDwarf
extends LOTREntityDwarf {
    public LOTREntityBlueDwarf(World world) {
        super(world);
        this.familyInfo.marriageEntityClass = LOTREntityBlueDwarf.class;
        this.familyInfo.marriageAchievement = LOTRAchievement.marryBlueDwarf;
    }

    @Override
    protected LOTRFoods getDwarfFoods() {
        return LOTRFoods.BLUE_DWARF;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerBlueDwarven));
        this.npcItemsInv.setIdleItem(null);
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.BLUE_MOUNTAINS;
    }

    @Override
    protected Item getDwarfSteelDrop() {
        return LOTRMod.blueDwarfSteel;
    }

    @Override
    protected LOTRChestContents getLarderDrops() {
        return LOTRChestContents.BLUE_DWARF_HOUSE_LARDER;
    }

    @Override
    protected LOTRChestContents getGenericDrops() {
        return LOTRChestContents.BLUE_MOUNTAINS_STRONGHOLD;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killBlueDwarf;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "blueDwarf/dwarf/hired";
            }
            return this.isChild() ? "blueDwarf/child/friendly" : "blueDwarf/dwarf/friendly";
        }
        return this.isChild() ? "blueDwarf/child/hostile" : "blueDwarf/dwarf/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.BLUE_MOUNTAINS.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.BLUE_MOUNTAINS;
    }
}

