/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTRBannerBearer;
import lotr.common.entity.npc.LOTREntityDwarfWarrior;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityDwarfBannerBearer
extends LOTREntityDwarfWarrior
implements LOTRBannerBearer {
    public LOTREntityDwarfBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.DWARF;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsDwarvenSilver));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsDwarvenSilver));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyDwarvenSilver));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetDwarvenSilver));
        return data;
    }
}

