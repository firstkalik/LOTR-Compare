/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntitySpiderBase;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LOTREntityMirkwoodSpider
extends LOTREntitySpiderBase {
    public LOTREntityMirkwoodSpider(World world) {
        super(world);
    }

    @Override
    protected int getRandomSpiderScale() {
        return this.rand.nextInt(3);
    }

    @Override
    protected int getRandomSpiderType() {
        return this.rand.nextBoolean() ? 0 : 1 + this.rand.nextInt(5);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DOL_GULDUR;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag && this.rand.nextInt(4) == 0) {
            this.dropItem(LOTRMod.mysteryWeb, 1);
        }
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killMirkwoodSpider;
    }
}

