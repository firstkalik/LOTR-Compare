/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.List;
import java.util.Random;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemManFlesh;
import lotr.common.item.LOTRItemThrowingAxe;
import lotr.common.item.LOTRMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class LOTREntityMan
extends LOTREntityNPC {
    public LOTREntityMan(World world) {
        super(world);
    }

    @Override
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if (!this.worldObj.isRemote && LOTRMod.canDropLoot(this.worldObj) && this.rand.nextInt(5) == 0) {
            List<LOTRFaction> manFleshFactions = LOTRItemManFlesh.getManFleshFactions();
            Entity damager = damagesource.getSourceOfDamage();
            if (damager instanceof EntityLivingBase) {
                ItemStack itemstack;
                EntityLivingBase entity = (EntityLivingBase)damager;
                boolean isAligned = false;
                if (entity instanceof EntityPlayer) {
                    LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)entity);
                    for (LOTRFaction f : manFleshFactions) {
                        if (!(playerData.getAlignment(f) > 0.0f)) continue;
                        isAligned = true;
                    }
                } else {
                    LOTRFaction npcFaction = LOTRMod.getNPCFaction((Entity)entity);
                    isAligned = manFleshFactions.contains((Object)npcFaction);
                }
                if (isAligned && (itemstack = entity.getHeldItem()) != null) {
                    Item item = itemstack.getItem();
                    Item.ToolMaterial material = null;
                    if (item instanceof ItemSword) {
                        ItemSword sword = (ItemSword)item;
                        material = LOTRMaterial.getToolMaterialByName(sword.getToolMaterialName());
                    } else if (item instanceof ItemTool) {
                        ItemTool tool = (ItemTool)item;
                        material = tool.func_150913_i();
                    } else if (item instanceof LOTRItemThrowingAxe) {
                        LOTRItemThrowingAxe axe = (LOTRItemThrowingAxe)item;
                        material = axe.getAxeMaterial();
                    }
                    if (material != null) {
                        boolean canHarvest = false;
                        for (LOTRMaterial lotrMaterial : LOTRMaterial.allLOTRMaterials) {
                            if (lotrMaterial.toToolMaterial() != material || !lotrMaterial.canHarvestManFlesh()) continue;
                            canHarvest = true;
                            break;
                        }
                        if (canHarvest) {
                            ItemStack flesh = new ItemStack(LOTRMod.manFlesh, 1 + this.rand.nextInt(2));
                            this.entityDropItem(flesh, 0.0f);
                        }
                    }
                }
            }
        }
    }
}

