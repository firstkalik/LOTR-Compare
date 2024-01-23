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

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntitySkeletalWraith;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityTauredainPyramidWraith
extends LOTREntitySkeletalWraith {
    public LOTREntityTauredainPyramidWraith(World world) {
        super(world);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(6);
        if (i == 0 || i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordTauredain));
        } else if (i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerTauredain));
        } else if (i == 3) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerTauredainPoisoned));
        } else if (i == 4) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerTauredain));
        } else if (i == 5) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeTauredain));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsTauredain));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsTauredain));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyTauredain));
        return data;
    }
}

