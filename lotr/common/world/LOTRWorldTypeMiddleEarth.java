/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.WorldType
 */
package lotr.common.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldType;

public class LOTRWorldTypeMiddleEarth
extends WorldType {
    public LOTRWorldTypeMiddleEarth(String name) {
        super(name);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean showWorldInfoNotice() {
        return true;
    }
}

