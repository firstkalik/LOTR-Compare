/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LOTREntityDesertSpider
extends LOTREntitySpiderBase {
    public LOTREntityDesertSpider(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    protected int getRandomSpiderType() {
        return this.rand.nextBoolean() ? 0 : 1 + this.rand.nextInt(5);
    }

    @Override
    protected int getRandomSpiderScale() {
        return 0 + this.rand.nextInt(2);
    }

    public int getTotalArmorValue() {
        return 15;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.HOSTILE;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected boolean canRideSpider() {
        return false;
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (flag && this.rand.nextInt(4) == 0) {
            this.dropItem(LOTRMod.mysteryWeb, 1);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return null;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killDesertSpider;
    }
}

