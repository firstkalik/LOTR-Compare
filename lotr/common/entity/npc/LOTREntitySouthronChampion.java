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
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRCapes;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNearHaradrimWarrior;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTREntitySouthronChampion
extends LOTREntityNearHaradrimWarrior {
    private static ItemStack[] weaponsChampion = new ItemStack[]{new ItemStack(LOTRMod.scimitarNearHarad), new ItemStack(LOTRMod.poleaxeNearHarad), new ItemStack(LOTRMod.maceNearHarad)};

    public LOTREntitySouthronChampion(World world) {
        super(world);
        this.spawnRidingHorse = true;
        this.npcCape = LOTRCapes.SOUTHRON_CHAMPION.capeTexture;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.5);
        this.getEntityAttribute(horseAttackSpeed).setBaseValue(1.9);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(weaponsChampion.length);
        this.npcItemsInv.setMeleeWeapon(weaponsChampion[i].copy());
        this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearNearHarad));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsNearHarad));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsNearHarad));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyNearHarad));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetNearHaradWarlord));
        return data;
    }
}

