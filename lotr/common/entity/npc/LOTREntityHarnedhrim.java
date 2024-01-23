/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityNearHaradrimBase;
import lotr.common.entity.npc.LOTRFamilyInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNames;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTREntityHarnedhrim
extends LOTREntityNearHaradrimBase {
    public LOTREntityHarnedhrim(World world) {
        super(world);
        this.addTargetTasks(true);
    }

    @Override
    protected LOTRFoods getHaradrimFoods() {
        return LOTRFoods.HARNEDOR;
    }

    @Override
    protected LOTRFoods getHaradrimDrinks() {
        return LOTRFoods.HARNEDOR_DRINK;
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getHarnennorName(this.rand, this.familyInfo.isMale()));
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerHarad));
        this.npcItemsInv.setIdleItem(null);
        return data;
    }

    @Override
    public String getNPCFormattedName(String npcName, String entityName) {
        if (this.getClass() == LOTREntityHarnedhrim.class) {
            return StatCollector.translateToLocalFormatted((String)"entity.lotr.Harnedhrim.entityName", (Object[])new Object[]{npcName});
        }
        return super.getNPCFormattedName(npcName, entityName);
    }

    @Override
    protected void dropHaradrimItems(boolean flag, int i) {
        if (this.rand.nextInt(5) == 0) {
            this.dropChestContents(LOTRChestContents.HARNENNOR_HOUSE, 1, 2 + i);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            return "nearHarad/harnennor/haradrim/friendly";
        }
        return "nearHarad/harnennor/haradrim/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.HARNENNOR.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.HARNENNOR;
    }
}

