/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRCapes;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.npc.LOTREntityGondorSoldier;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTREntityPinnathGelinSoldier
extends LOTREntityGondorSoldier {
    public LOTREntityPinnathGelinSoldier(World world) {
        super(world);
        this.spawnRidingHorse = this.rand.nextInt(8) == 0;
        this.npcShield = LOTRShields.ALIGNMENT_PINNATH_GELIN;
        this.npcCape = LOTRCapes.PINNATH_GELIN.capeTexture;
    }

    @Override
    public EntityAIBase createGondorAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.45, false);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(2);
        if (i == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordGondor));
        } else if (i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeGondor));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsPinnathGelin));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsPinnathGelin));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyPinnathGelin));
        if (this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetPinnathGelin));
        } else {
            this.setCurrentItemOrArmor(4, null);
        }
        return data;
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if (mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        } else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
    }
}

