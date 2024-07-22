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
import lotr.common.LOTRShields;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.LOTREntityStiffbeard;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityStiffbeardWarrior
extends LOTREntityStiffbeard {
    public LOTREntityStiffbeardWarrior(World world) {
        super(world);
        this.npcShield = LOTRShields.ALIGNMENT_RED_MOUNTAINS;
        this.spawnRidingHorse = this.rand.nextInt(6) == 0;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityWildBoar boar = new LOTREntityWildBoar(this.worldObj);
        boar.setMountArmor(new ItemStack(LOTRMod.boarArmorRed));
        return boar;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(7);
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeRed));
        } else if (i == 1 || i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerRed));
        } else if (i == 3 || i == 4) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeRed));
        } else if (i == 5) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordRed));
        } else if (i == 6) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerRed));
        }
        if (this.rand.nextInt(6) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearRed));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsStiffbeard));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsStiffbeard));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyStiffbeard));
        if (this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetStiffbeard));
        }
        return data;
    }
}
