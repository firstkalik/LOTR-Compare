/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package lotr.common;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRTitle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class LOTRNewAchievementsTitle
extends LOTRTitle {
    private final LOTRAchievement[] achievements;

    public LOTRNewAchievementsTitle(String titleName, LOTRAchievement ... achievements) {
        super(titleName);
        if (achievements.length == 0) {
            throw new IllegalArgumentException("No achievements for title " + titleName);
        }
        this.achievements = achievements;
    }

    @Override
    public boolean canPlayerUse(EntityPlayer entityPlayer) {
        LOTRPlayerData playerData = LOTRLevelData.getData(entityPlayer);
        for (LOTRAchievement achievement : this.achievements) {
            if (playerData.hasAchievement(achievement)) continue;
            return false;
        }
        return true;
    }

    @Override
    public String getDisplayName(EntityPlayer entityPlayer) {
        return StatCollector.translateToLocal((String)this.getUntranslatedName(entityPlayer));
    }

    @Override
    public String getDescription(EntityPlayer entityPlayer) {
        return StatCollector.translateToLocal((String)("lotr.title." + this.getTitleName() + ".desc"));
    }

    @Override
    public String getUntranslatedName(EntityPlayer entityPlayer) {
        return "lotr.title." + this.getTitleName();
    }
}

