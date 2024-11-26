/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package lotr.common.tileentity;

import java.util.Random;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRTileEntityDecoratedPot
extends TileEntity {
    public int ticksExisted;

    public void setWorldObj(World world) {
        super.setWorldObj(world);
        this.ticksExisted = world.rand.nextInt(20);
    }

    public void updateEntity() {
        ++this.ticksExisted;
    }
}

