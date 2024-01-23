/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIOrcPlaceBomb;
import lotr.common.entity.npc.LOTREntityAngmarOrc;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityAngmarOrcBombardier
extends LOTREntityAngmarOrc {
    public LOTREntityAngmarOrcBombardier(World world) {
        super(world);
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIOrcPlaceBomb(this, 1.4));
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }

    @Override
    public boolean isOrcBombardier() {
        return true;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setBombingItem(new ItemStack(LOTRMod.orcTorchItem));
        this.npcItemsInv.setBomb(new ItemStack(LOTRMod.orcBomb, 1, 0));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetAngmar));
        return data;
    }
}

