/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import net.minecraft.world.World;

public class LOTREntityEreborDwarf
extends LOTREntityDwarf {
    public LOTREntityEreborDwarf(World world) {
        super(world);
        this.familyInfo.marriageEntityClass = LOTREntityEreborDwarf.class;
        this.familyInfo.marriageAchievement = LOTRAchievement.marryEreborDwarf;
    }

    @Override
    protected LOTRFoods getDwarfFoods() {
        return LOTRFoods.DWARF;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killEreborDwarf;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.EREBOR.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.EREBOR;
    }
}

