/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package lotr.common.fellowship;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.item.LOTREntityBanner;
import lotr.common.fellowship.LOTRFellowship;
import lotr.common.fellowship.LOTRFellowshipClient;
import lotr.common.fellowship.LOTRFellowshipData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class LOTRFellowshipProfile
extends GameProfile {
    public static final String fellowshipPrefix = "f/";
    private LOTREntityBanner theBanner;
    private String fellowshipName;

    public LOTRFellowshipProfile(LOTREntityBanner banner, UUID fsID, String fsName) {
        super(fsID, fsName);
        this.theBanner = banner;
        this.fellowshipName = fsName;
    }

    public LOTRFellowship getFellowship() {
        LOTRFellowship fs = LOTRFellowshipData.getFellowship(this.getId());
        if (fs != null && !fs.isDisbanded()) {
            return fs;
        }
        return null;
    }

    public LOTRFellowshipClient getFellowshipClient() {
        return LOTRLevelData.getData(LOTRMod.proxy.getClientPlayer()).getClientFellowshipByName(this.fellowshipName);
    }

    public String getName() {
        return LOTRFellowshipProfile.addFellowshipCode(super.getName());
    }

    public static boolean hasFellowshipCode(String s) {
        return s.toLowerCase().startsWith(fellowshipPrefix.toLowerCase());
    }

    public static String addFellowshipCode(String s) {
        return fellowshipPrefix + s;
    }

    public static String stripFellowshipCode(String s) {
        return s.substring(fellowshipPrefix.length());
    }

    public static String getFellowshipCodeHint() {
        return StatCollector.translateToLocalFormatted((String)"lotr.gui.bannerEdit.fellowshipHint", (Object[])new Object[]{fellowshipPrefix});
    }
}

