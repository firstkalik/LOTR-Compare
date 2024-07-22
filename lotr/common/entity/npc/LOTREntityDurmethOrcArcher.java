/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIRangedAttack;
import lotr.common.entity.npc.LOTREntityDurmethOrc;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.item.LOTRItemCrossbow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityDurmethOrcArcher
extends LOTREntityDurmethOrc {
    public LOTREntityDurmethOrcArcher(World world) {
        super(world);
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 30, 60, 16.0f);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextInt(4) == 0) {
            if (this.rand.nextBoolean()) {
                this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.ironCrossbow));
            } else {
                this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.redCrossbow));
            }
        } else if (this.rand.nextBoolean()) {
            this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.orcBow));
        } else {
            this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.gundabadUrukBow));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getRangedWeapon());
        return data;
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getRangedWeapon());
        }
    }

    private boolean isCrossbowOrc() {
        ItemStack itemstack = this.npcItemsInv.getRangedWeapon();
        return itemstack != null && itemstack.getItem() instanceof LOTRItemCrossbow;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        if (this.isCrossbowOrc()) {
            this.npcCrossbowAttack(target, f);
        } else {
            this.npcArrowAttack(target, f);
        }
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        if (this.isCrossbowOrc()) {
            this.dropNPCCrossbowBolts(i);
        } else {
            this.dropNPCArrows(i);
        }
    }
}

