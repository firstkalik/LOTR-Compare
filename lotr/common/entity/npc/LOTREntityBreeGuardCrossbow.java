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
import lotr.common.entity.npc.LOTREntityBreeGuard;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityBreeGuardCrossbow
extends LOTREntityBreeGuard {
    public LOTREntityBreeGuardCrossbow(World world) {
        super(world);
    }

    @Override
    protected EntityAIBase createBreeAttackAI() {
        return new LOTREntityAIRangedAttack(this, 1.25, 30, 50, 16.0f);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        ItemStack chosenWeapon;
        data = super.onSpawnWithEgg(data);
        Random random = new Random();
        int weaponChoice = random.nextInt(2);
        switch (weaponChoice) {
            case 0: {
                chosenWeapon = new ItemStack(LOTRMod.ironCrossbow);
                break;
            }
            default: {
                chosenWeapon = new ItemStack(LOTRMod.bronzeCrossbow);
            }
        }
        this.npcItemsInv.setRangedWeapon(chosenWeapon);
        this.npcItemsInv.setIdleItem(chosenWeapon);
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

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        this.npcCrossbowAttack(target, f);
    }

    @Override
    protected void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        this.dropNPCCrossbowBolts(i);
    }
}

