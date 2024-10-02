/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPotions;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.LOTREntityBlueDwarfWarrior;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.npc.LOTRUnitTradeEntries;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTREntityBlueDwarfCommander
extends LOTREntityBlueDwarfWarrior
implements LOTRUnitTradeable {
    public LOTREntityBlueDwarfCommander(World world) {
        super(world);
        this.addTargetTasks(false);
        this.npcShield = null;
        this.npcCape = LOTRCapes.ALIGNMENT_BLUE_MOUNTAINS.capeTexture;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityWildBoar boar = new LOTREntityWildBoar(this.worldObj);
        boar.setMountArmor(new ItemStack(LOTRMod.boarArmorBlueDwarvenGold));
        return boar;
    }

    @Override
    public EntityAIBase getDwarfAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.6, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)source.getEntity();
            player.addPotionEffect(new PotionEffect(LOTRPotions.curse.id, 24000, 0));
        }
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextInt(4) == 0) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerBlueDwarven));
            this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bdgoldboots));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.bdgoldlegs));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bdgoldbody));
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetBlueDwarvenGoldCommander));
        } else {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeBlueDwarven));
            this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
            this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bdsilverboots));
            this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.bdsilverlegs));
            this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bdsilverbody));
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetBlueDwarvenSilverCommander));
        }
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 5.0f;
    }

    @Override
    public LOTRUnitTradeEntries getUnits() {
        return LOTRUnitTradeEntries.BLUE_DWARF_COMMANDER;
    }

    @Override
    public LOTRInvasions getConquestHorn() {
        return LOTRInvasions.BLUE_MOUNTAINS;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 200.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeBlueDwarfCommander);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.canTradeWith(entityplayer)) {
                return "blueDwarf/commander/friendly";
            }
            return "blueDwarf/commander/neutral";
        }
        return "blueDwarf/dwarf/hostile";
    }
}

