/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.StatCollector
 */
package lotr.common;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionRank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public class LOTRAchievementRank
extends LOTRAchievement {
    private LOTRFactionRank theRank;
    private LOTRFaction theFac;

    public LOTRAchievementRank(LOTRFactionRank rank) {
        super(rank.fac.getAchieveCategory(), rank.fac.getAchieveCategory().getNextRankAchID(), LOTRMod.goldRing, "alignment_" + rank.fac.codeName() + "_" + rank.alignment);
        this.theRank = rank;
        this.theFac = this.theRank.fac;
        this.setRequiresAlly(this.theFac);
        this.setSpecial();
    }

    @Override
    public String getUntranslatedTitle(EntityPlayer entityplayer) {
        return this.theRank.getCodeFullNameWithGender(LOTRLevelData.getData(entityplayer));
    }

    @Override
    public String getDescription(EntityPlayer entityplayer) {
        String suffix = this.requiresPledge() ? "achieveRankPledge" : "achieveRank";
        return StatCollector.translateToLocalFormatted((String)("lotr.faction." + this.theFac.codeName() + "." + suffix), (Object[])new Object[]{LOTRAlignmentValues.formatAlignForDisplay(this.theRank.alignment)});
    }

    private boolean requiresPledge() {
        return this.theRank.isAbovePledgeRank();
    }

    @Override
    public boolean canPlayerEarn(EntityPlayer entityplayer) {
        LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
        float align = pd.getAlignment(this.theFac);
        if (align < 0.0f) {
            return false;
        }
        return !this.requiresPledge() || pd.isPledgedTo(this.theFac);
    }

    public boolean isPlayerRequiredRank(EntityPlayer entityplayer) {
        LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
        float align = pd.getAlignment(this.theFac);
        float rankAlign = this.theRank.alignment;
        if (this.requiresPledge() && !pd.isPledgedTo(this.theFac)) {
            return false;
        }
        return align >= rankAlign;
    }
}

