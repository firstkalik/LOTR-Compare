/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 */
package lotr.common.entity.npc;

import java.util.List;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIFarm;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.npc.LOTREntityHobbit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRFarmhand;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class LOTREntityHobbitFarmhand
extends LOTREntityHobbit
implements LOTRFarmhand {
    public Item seedsItem;

    public LOTREntityHobbitFarmhand(World world) {
        super(world);
        this.tasks.addTask(4, (EntityAIBase)new LOTREntityAIAttackOnCollide(this, 1.3, false));
        this.tasks.addTask(5, (EntityAIBase)new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(6, (EntityAIBase)new LOTREntityAIFarm(this, 1.0, 1.5f));
        this.targetTasks.taskEntries.clear();
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.iron_hoe));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        return data;
    }

    @Override
    public IPlantable getUnhiredSeeds() {
        if (this.seedsItem == null) {
            return (IPlantable)Items.wheat_seeds;
        }
        return (IPlantable)this.seedsItem;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        if (this.seedsItem != null) {
            nbt.setInteger("SeedsID", Item.getIdFromItem((Item)this.seedsItem));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        Item item;
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("SeedsID") && (item = Item.getItemById((int)nbt.getInteger("SeedsID"))) != null && item instanceof IPlantable) {
            this.seedsItem = item;
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
            return "hobbit/farmhand/hired";
        }
        return super.getSpeechBank(entityplayer);
    }
}

