/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package lotr.common.entity.item;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotr.common.LOTRBannerProtection;

public class LOTRBannerWhitelistEntry {
    public final GameProfile profile;
    private Set<LOTRBannerProtection.Permission> perms = new HashSet<LOTRBannerProtection.Permission>();

    public LOTRBannerWhitelistEntry(GameProfile p) {
        this.profile = p;
        if (this.profile == null) {
            throw new IllegalArgumentException("Banner whitelist entry cannot have a null profile!");
        }
    }

    public LOTRBannerWhitelistEntry setFullPerms() {
        this.clearPermissions();
        this.addPermission(LOTRBannerProtection.Permission.FULL);
        return this;
    }

    public boolean isPermissionEnabled(LOTRBannerProtection.Permission p) {
        return this.perms.contains((Object)p);
    }

    public boolean allowsPermission(LOTRBannerProtection.Permission p) {
        return this.isPermissionEnabled(LOTRBannerProtection.Permission.FULL) || this.isPermissionEnabled(p);
    }

    public void addPermission(LOTRBannerProtection.Permission p) {
        this.perms.add(p);
    }

    public void removePermission(LOTRBannerProtection.Permission p) {
        this.perms.remove((Object)p);
    }

    public void clearPermissions() {
        this.perms.clear();
    }

    public Set<LOTRBannerProtection.Permission> listPermissions() {
        return this.perms;
    }

    public int encodePermBitFlags() {
        return LOTRBannerWhitelistEntry.static_encodePermBitFlags(this.perms);
    }

    public void decodePermBitFlags(int i) {
        this.perms.clear();
        List<LOTRBannerProtection.Permission> decoded = LOTRBannerWhitelistEntry.static_decodePermBitFlags(i);
        for (LOTRBannerProtection.Permission p : decoded) {
            this.addPermission(p);
        }
    }

    public static int static_encodePermBitFlags(Collection<LOTRBannerProtection.Permission> permList) {
        int i = 0;
        for (LOTRBannerProtection.Permission p : permList) {
            i |= p.bitFlag;
        }
        return i;
    }

    public static List<LOTRBannerProtection.Permission> static_decodePermBitFlags(int i) {
        ArrayList<LOTRBannerProtection.Permission> decoded = new ArrayList<LOTRBannerProtection.Permission>();
        for (LOTRBannerProtection.Permission p : LOTRBannerProtection.Permission.values()) {
            if ((i & p.bitFlag) == 0) continue;
            decoded.add(p);
        }
        return decoded;
    }
}

