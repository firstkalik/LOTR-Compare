/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import addon.drealm.database.DRRegistry;
import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityDaleLevyman;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityEsgarothSoldier
extends LOTREntityDaleLevyman {
    public LOTREntityEsgarothSoldier(World world) {
        super(world);
        this.npcShield = LOTRShields.ALIGNMENT_ESGAROTH;
        this.spawnRidingHorse = this.rand.nextInt(8) == 0;
    }

    @Override
    protected EntityAIBase createDaleAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.5, true);
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityHorse horse = (LOTREntityHorse)super.createMountToRide();
        horse.setMountArmor(new ItemStack(LOTRMod.horseArmorDale));
        return horse;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(4);
        if (i == 0 || i == 1 || i == 2) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(DRRegistry.sword_esgaroth));
        } else if (i == 3) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(DRRegistry.battleaxe_esgaroth));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(DRRegistry.boots_esgaroth));
        this.setCurrentItemOrArmor(2, new ItemStack(DRRegistry.legs_esgaroth));
        this.setCurrentItemOrArmor(3, new ItemStack(DRRegistry.body_esgaroth));
        if (this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(DRRegistry.helmet_esgaroth));
        } else {
            this.setCurrentItemOrArmor(4, null);
        }
        return data;
    }
}

