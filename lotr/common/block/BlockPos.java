/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package lotr.common.block;

import net.minecraftforge.common.util.ForgeDirection;

public class BlockPos {
    public int x;
    public int y;
    public int z;
    public ForgeDirection direct;

    public BlockPos(int x, int y, int z, ForgeDirection direction) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.direct = direction;
    }

    public BlockPos moveForwards(int i) {
        switch (this.direct) {
            case UP: {
                this.y += i;
                break;
            }
            case DOWN: {
                this.y -= i;
                break;
            }
            case SOUTH: {
                this.z += i;
                break;
            }
            case NORTH: {
                this.z -= i;
                break;
            }
            case EAST: {
                this.x += i;
                break;
            }
            case WEST: {
                this.x -= i;
            }
        }
        return this;
    }

}

