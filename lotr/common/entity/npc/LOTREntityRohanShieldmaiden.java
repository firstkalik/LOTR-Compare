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
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityQuestInfo;
import lotr.common.entity.npc.LOTREntityRohirrimWarrior;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityRohanShieldmaiden
extends LOTREntityRohirrimWarrior {
    public LOTREntityRohanShieldmaiden(World world) {
        super(world);
        this.spawnRidingHorse = false;
        this.questInfo.setOfferChance(4000);
        this.questInfo.setMinAlignment(150.0f);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(false);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextBoolean()) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetRohan));
        } else {
            this.setCurrentItemOrArmor(4, null);
        }
        return data;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "rohan/warrior/hired";
            }
            return "rohan/shieldmaiden/friendly";
        }
        return "rohan/warrior/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.ROHAN_SHIELDMAIDEN.createQuest(this);
    }
}

