/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNearHaradrimBase;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityNearHaradrim
extends LOTREntityNearHaradrimBase {
    public LOTREntityNearHaradrim(World world) {
        super(world);
        this.addTargetTasks(false);
    }

    @Override
    protected LOTRFoods getHaradrimFoods() {
        return LOTRFoods.SOUTHRON;
    }

    @Override
    protected LOTRFoods getHaradrimDrinks() {
        return LOTRFoods.SOUTHRON_DRINK;
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getSouthronCoastName(this.rand, this.familyInfo.isMale()));
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityHorse horse = (LOTREntityHorse)super.createMountToRide();
        horse.setMountArmor(new ItemStack(LOTRMod.horseArmorNearHarad));
        return horse;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerNearHarad));
        this.npcItemsInv.setIdleItem(null);
        return data;
    }

    @Override
    protected void dropHaradrimItems(boolean flag, int i) {
        if (this.rand.nextInt(5) == 0) {
            this.dropChestContents(LOTRChestContents.NEAR_HARAD_HOUSE, 1, 2 + i);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "nearHarad/coast/haradrim/friendly";
        }
        return "nearHarad/coast/haradrim/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.NEAR_HARAD.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.NEAR_HARAD;
    }
}

