/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.entity.npc.IBandit;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.inventory.LOTRInventoryNPC;
import lotr.common.item.D1;
import lotr.common.item.D2;
import lotr.common.item.D3;
import lotr.common.item.D4;
import lotr.common.item.D5;
import lotr.common.item.D6;
import lotr.common.item.D7;
import lotr.common.item.H1;
import lotr.common.item.LOTRItemCoin;
import lotr.common.item.LOTRItemGem;
import lotr.common.item.LOTRItemGraal;
import lotr.common.item.LOTRItemGraalMithril;
import lotr.common.item.LOTRItemMagicClover;
import lotr.common.item.LOTRItemMagicCloverPlus;
import lotr.common.item.LOTRItemMagicTotem;
import lotr.common.item.LOTRItemMagicTotemPlus;
import lotr.common.item.LOTRItemPouch;
import lotr.common.item.LOTRItemRing;
import lotr.common.item.LOTRValuableItems;
import lotr.common.item.Naria;
import lotr.common.item.Nenia;
import lotr.common.item.Thorin;
import lotr.common.item.Vilia;
import lotr.common.item.arven;
import lotr.common.item.aule;
import lotr.common.item.elrond;
import lotr.common.item.elrondsilver;
import lotr.common.item.este;
import lotr.common.item.farin;
import lotr.common.item.haldir;
import lotr.common.item.irmo;
import lotr.common.item.khain;
import lotr.common.item.kibil;
import lotr.common.item.lesserfire;
import lotr.common.item.lesserivisible;
import lotr.common.item.lesserjump;
import lotr.common.item.lesserlight;
import lotr.common.item.lessermining;
import lotr.common.item.lessernightvision;
import lotr.common.item.lesserpower;
import lotr.common.item.lesserresistance;
import lotr.common.item.lessersaturation;
import lotr.common.item.lesserspeed;
import lotr.common.item.lesserstrenght;
import lotr.common.item.lessersuicide;
import lotr.common.item.lesserwatherbreathing;
import lotr.common.item.light;
import lotr.common.item.linhir;
import lotr.common.item.manve;
import lotr.common.item.melkor;
import lotr.common.item.melkor2;
import lotr.common.item.namo;
import lotr.common.item.narchuil;
import lotr.common.item.nessa;
import lotr.common.item.nienna;
import lotr.common.item.numenor;
import lotr.common.item.orome;
import lotr.common.item.ringBarachir;
import lotr.common.item.ringShaman;
import lotr.common.item.ringSmithing;
import lotr.common.item.sarumanring;
import lotr.common.item.theOneRing;
import lotr.common.item.thorinrune;
import lotr.common.item.thranduilmithril;
import lotr.common.item.thranduilsilver;
import lotr.common.item.thranduilsnake;
import lotr.common.item.tulkas;
import lotr.common.item.ulmo;
import lotr.common.item.vaire;
import lotr.common.item.vana;
import lotr.common.item.varda;
import lotr.common.item.yavanna;
import lotr.common.recipe.LOTRRecipes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityAIBanditSteal
extends EntityAIBase {
    private IBandit theBandit;
    private LOTREntityNPC theBanditAsNPC;
    private EntityPlayer targetPlayer;
    private EntityPlayer prevTargetPlayer;
    private double speed;
    private int chaseTimer;
    private int rePathDelay;

    public LOTREntityAIBanditSteal(IBandit bandit, double d) {
        this.theBandit = bandit;
        this.theBanditAsNPC = this.theBandit.getBanditAsNPC();
        this.speed = d;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        if (!this.theBandit.getBanditInventory().isEmpty()) {
            return false;
        }
        double range = 32.0;
        List players = this.theBanditAsNPC.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.theBanditAsNPC.boundingBox.expand(range, range, range));
        ArrayList<EntityPlayer> validTargets = new ArrayList<EntityPlayer>();
        for (Object player : players) {
            EntityPlayer entityplayer = (EntityPlayer)player;
            if (entityplayer.capabilities.isCreativeMode || !this.theBandit.canTargetPlayerForTheft(entityplayer) || !IBandit.Helper.canStealFromPlayerInv(this.theBandit, entityplayer)) continue;
            validTargets.add(entityplayer);
        }
        if (validTargets.isEmpty()) {
            return false;
        }
        this.targetPlayer = (EntityPlayer)validTargets.get(this.theBanditAsNPC.getRNG().nextInt(validTargets.size()));
        if (this.targetPlayer != this.prevTargetPlayer) {
            this.theBanditAsNPC.sendSpeechBank(this.targetPlayer, this.theBandit.getTheftSpeechBank(this.targetPlayer));
        }
        return true;
    }

    public void startExecuting() {
        this.chaseTimer = 600;
    }

    public void updateTask() {
        --this.chaseTimer;
        this.theBanditAsNPC.getLookHelper().setLookPositionWithEntity((Entity)this.targetPlayer, 30.0f, 30.0f);
        --this.rePathDelay;
        if (this.rePathDelay <= 0) {
            this.rePathDelay = 10;
            this.theBanditAsNPC.getNavigator().tryMoveToEntityLiving((Entity)this.targetPlayer, this.speed);
        }
        if (this.theBanditAsNPC.getDistanceSqToEntity((Entity)this.targetPlayer) <= 2.0) {
            this.chaseTimer = 0;
            this.steal();
        }
    }

    public boolean continueExecuting() {
        if (this.targetPlayer == null || !this.targetPlayer.isEntityAlive() || this.targetPlayer.capabilities.isCreativeMode || !IBandit.Helper.canStealFromPlayerInv(this.theBandit, this.targetPlayer)) {
            return false;
        }
        return this.chaseTimer > 0 && this.theBanditAsNPC.getDistanceSqToEntity((Entity)this.targetPlayer) < 256.0;
    }

    public void resetTask() {
        this.chaseTimer = 0;
        this.rePathDelay = 0;
        if (this.targetPlayer != null) {
            this.prevTargetPlayer = this.targetPlayer;
        }
        this.targetPlayer = null;
    }

    private void steal() {
        InventoryPlayer inv = this.targetPlayer.inventory;
        int thefts = MathHelper.getRandomIntegerInRange((Random)this.theBanditAsNPC.getRNG(), (int)1, (int)this.theBandit.getMaxThefts());
        boolean stolenSomething = false;
        Class[] stealableItems = new Class[]{theOneRing.class, ringBarachir.class, ringShaman.class, ringSmithing.class, Vilia.class, Nenia.class, Naria.class, D1.class, D2.class, D3.class, D4.class, D5.class, D6.class, D7.class, H1.class, sarumanring.class, elrond.class, elrondsilver.class, narchuil.class, numenor.class, Thorin.class, thorinrune.class, arven.class, thranduilsilver.class, thranduilsnake.class, thranduilmithril.class, lesserfire.class, lesserivisible.class, lesserjump.class, lesserlight.class, lessernightvision.class, lesserpower.class, lessermining.class, lessersaturation.class, lesserresistance.class, lesserspeed.class, lesserstrenght.class, lesserwatherbreathing.class, lessersuicide.class, aule.class, este.class, irmo.class, manve.class, melkor.class, melkor2.class, namo.class, nessa.class, nienna.class, orome.class, tulkas.class, ulmo.class, vaire.class, vana.class, varda.class, yavanna.class, light.class, linhir.class, farin.class, haldir.class, khain.class, LOTRItemGraal.class, LOTRItemGraalMithril.class, LOTRItemMagicClover.class, LOTRItemMagicTotem.class, LOTRItemMagicCloverPlus.class, LOTRItemMagicTotemPlus.class, kibil.class};
        for (int i = 0; i < thefts; ++i) {
            for (Class itemClass : stealableItems) {
                if (!this.tryStealItem(inv, itemClass)) continue;
                stolenSomething = true;
                break;
            }
            if (stolenSomething) continue;
            if (this.tryStealItem(inv, LOTRItemCoin.class)) {
                stolenSomething = true;
                continue;
            }
            if (this.tryStealItem(inv, LOTRItemGem.class)) {
                stolenSomething = true;
                continue;
            }
            if (this.tryStealItem(inv, LOTRValuableItems.getToolMaterials())) {
                stolenSomething = true;
                continue;
            }
            if (this.tryStealItem(inv, LOTRItemRing.class)) {
                stolenSomething = true;
                continue;
            }
            if (this.tryStealItem(inv, ItemArmor.class)) {
                stolenSomething = true;
                continue;
            }
            if (this.tryStealItem(inv, ItemSword.class)) {
                stolenSomething = true;
                continue;
            }
            if (this.tryStealItem(inv, ItemTool.class)) {
                stolenSomething = true;
                continue;
            }
            if (!this.tryStealItem(inv, LOTRItemPouch.class)) continue;
            stolenSomething = true;
        }
        if (stolenSomething) {
            this.targetPlayer.addChatMessage(this.theBandit.getTheftChatMsg(this.targetPlayer));
            this.theBanditAsNPC.playSound("mob.horse.leather", 0.5f, 1.0f);
            if (this.theBanditAsNPC.getAttackTarget() != null) {
                this.theBanditAsNPC.setAttackTarget(null);
            }
            LOTRLevelData.getData(this.targetPlayer).cancelFastTravel();
        }
    }

    private boolean tryStealItem(InventoryPlayer inv, final Class itemclass) {
        return this.tryStealItem_do(inv, new BanditItemFilter(){

            @Override
            public boolean isApplicable(ItemStack itemstack) {
                return itemclass.isAssignableFrom(itemstack.getItem().getClass());
            }
        });
    }

    private boolean tryStealItem(InventoryPlayer inv, final List<ItemStack> itemList) {
        return this.tryStealItem_do(inv, new BanditItemFilter(){

            @Override
            public boolean isApplicable(ItemStack itemstack) {
                for (ItemStack listItem : itemList) {
                    if (!LOTRRecipes.checkItemEquals(listItem, itemstack)) continue;
                    return true;
                }
                return false;
            }
        });
    }

    private boolean tryStealItem(InventoryPlayer inv) {
        return this.tryStealItem_do(inv, new BanditItemFilter(){

            @Override
            public boolean isApplicable(ItemStack itemstack) {
                return true;
            }
        });
    }

    private boolean tryStealItem_do(InventoryPlayer inv, BanditItemFilter filter) {
        Integer[] inventorySlots = new Integer[inv.mainInventory.length];
        for (int l = 0; l < inventorySlots.length; ++l) {
            inventorySlots[l] = l;
        }
        List<Integer> slotsAsList = Arrays.asList(inventorySlots);
        Collections.shuffle(slotsAsList);
        inventorySlots = slotsAsList.toArray(inventorySlots);
        Integer[] arrinteger = inventorySlots;
        int n = arrinteger.length;
        for (int i = 0; i < n; ++i) {
            ItemStack itemstack;
            int slot = arrinteger[i];
            if (slot == inv.currentItem || (itemstack = inv.getStackInSlot(slot)) == null || !filter.isApplicable(itemstack) || !this.stealItem(inv, slot)) continue;
            return true;
        }
        return false;
    }

    private int getRandomTheftAmount(ItemStack itemstack) {
        return MathHelper.getRandomIntegerInRange((Random)this.theBanditAsNPC.getRNG(), (int)1, (int)8);
    }

    private boolean stealItem(InventoryPlayer inv, int slot) {
        ItemStack playerItem = inv.getStackInSlot(slot);
        int theft = this.getRandomTheftAmount(playerItem);
        if (theft > playerItem.stackSize) {
            theft = playerItem.stackSize;
        }
        int banditSlot = 0;
        while (this.theBandit.getBanditInventory().getStackInSlot(banditSlot) != null) {
            if (++banditSlot < this.theBandit.getBanditInventory().getSizeInventory()) continue;
            return false;
        }
        ItemStack stolenItem = playerItem.copy();
        stolenItem.stackSize = theft;
        this.theBandit.getBanditInventory().setInventorySlotContents(banditSlot, stolenItem);
        playerItem.stackSize -= theft;
        if (playerItem.stackSize <= 0) {
            inv.setInventorySlotContents(slot, null);
        }
        this.theBanditAsNPC.isNPCPersistent = true;
        return true;
    }

    private abstract class BanditItemFilter {
        private BanditItemFilter() {
        }

        public abstract boolean isApplicable(ItemStack var1);
    }

}

