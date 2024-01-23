/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetHuorn;
import lotr.common.entity.npc.LOTREntityHuornBase;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.biome.LOTRBiomeGenOldForest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityDarkHuorn
extends LOTREntityHuornBase {
    public LOTREntityDarkHuorn(World world) {
        super(world);
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetHuorn.class);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.setTreeType(0);
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.DARK_HUORN;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killDarkHuorn;
    }

    @Override
    protected boolean isTreeHomeBiome(BiomeGenBase biome) {
        return biome instanceof LOTRBiomeGenOldForest;
    }
}

