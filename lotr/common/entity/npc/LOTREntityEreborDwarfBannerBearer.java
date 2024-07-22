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
import lotr.common.entity.npc.LOTREntityEreborDwarfWarrior;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityEreborDwarfBannerBearer
extends LOTREntityEreborDwarfWarrior
implements LOTRBannerBearer {
    public LOTREntityEreborDwarfBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.EREBOR;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsEreborSilver));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsEreborSilver));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyEreborSilver));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetEreborSilver));
        return data;
    }
}

