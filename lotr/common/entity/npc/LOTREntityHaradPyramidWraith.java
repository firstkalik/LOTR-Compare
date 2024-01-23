/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityHaradPyramidWraith
extends LOTREntitySkeletalWraith {
    public LOTREntityHaradPyramidWraith(World world) {
        super(world);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextBoolean()) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerNearHaradPoisoned));
            this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsNearHarad));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsNearHarad));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyNearHarad));
        } else {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerHaradPoisoned));
            this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsGulfHarad));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsGulfHarad));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyGulfHarad));
        }
        return data;
    }
}

