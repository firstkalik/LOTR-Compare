/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemBaseRing2;
import lotr.common.item.LOTRMaterial;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemArcenstone
extends LOTRItemBaseRing2 {
    public LOTRItemArcenstone(LOTRMaterial AUlE) {
        this.maxStackSize = 1;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabStory);
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack repairItem) {
        return repairItem.getItem() == LOTRMod.mithrilNugget;
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer entity, World world, int i, int j, int k, int l, float a, float b, float c) {
        if (!world.isRemote) {
            LOTRLevelData.getData(entity).addAchievement(LOTRAchievement.useArcenstone);
            Block clickedBlock = world.getBlock(i, j, k);
            if (clickedBlock == Blocks.stone || clickedBlock == Blocks.gravel || clickedBlock == Blocks.cobblestone || clickedBlock == Blocks.dirt) {
                float var4 = 1.0f;
                entity.attackEntityFrom(DamageSource.generic, 2.0f);
                if (Math.random() * 100.0 <= 45.0) {
                    world.setBlock(i, j, k, Blocks.coal_ore, 0, 2);
                }
                if (Math.random() * 100.0 <= 38.0) {
                    world.setBlock(i, j, k, Blocks.iron_ore, 0, 2);
                }
                if (Math.random() * 100.0 <= 10.0) {
                    world.setBlock(i, j, k, Blocks.gold_ore, 0, 2);
                }
                if (Math.random() * 100.0 <= 5.0) {
                    world.setBlock(i, j, k, LOTRMod.oreGold, 0, 2);
                }
                if (Math.random() * 100.0 <= 20.0) {
                    world.setBlock(i, j, k, LOTRMod.oreIron, 0, 2);
                }
                if (Math.random() * 100.0 <= 10.0) {
                    world.setBlock(i, j, k, LOTRMod.oreSilver, 0, 2);
                }
                if (Math.random() * 100.0 <= 10.0) {
                    world.setBlock(i, j, k, LOTRMod.oreCopper, 0, 2);
                }
                if (Math.random() * 100.0 <= 10.0) {
                    world.setBlock(i, j, k, LOTRMod.oreTin, 0, 2);
                }
                if (Math.random() * 100.0 <= 10.0) {
                    world.setBlock(i, j, k, LOTRMod.oreSulfur, 0, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.oreQuendite, 0, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 13, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, Blocks.obsidian, 0, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, Blocks.coal_block, 0, 2);
                }
                if (Math.random() * 100.0 <= 2.0) {
                    world.setBlock(i, j, k, LOTRMod.boneBlock, 0, 2);
                }
                if (Math.random() * 100.0 <= 2.0) {
                    world.setBlock(i, j, k, LOTRMod.wasteBlock, 0, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.rottenfleshBlock, 0, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, Blocks.iron_block, 0, 2);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 0, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.oreLavaCoal, 0, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 1, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 2, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 3, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 0, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 6, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 7, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 8, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 15, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage2, 1, 5);
                }
                if (Math.random() * 100.0 <= 1.0) {
                    world.setBlock(i, j, k, Blocks.gold_block, 0, 2);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 9, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 8, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 7, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 6, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 5, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 4, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 3, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 2, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 1, 1);
                }
                if (Math.random() * 100.0 <= 0.5) {
                    world.setBlock(i, j, k, LOTRMod.blockGem, 0, 1);
                }
                if (Math.random() * 100.0 <= 0.4 && !world.isRemote) {
                    world.setBlock(i, j, k, LOTRMod.oreMithril, 0, 2);
                }
                if (Math.random() * 100.0 <= 10.2 && !world.isRemote) {
                    world.setBlock(i, j, k, LOTRMod.blockOreStorage, 4, 5);
                    LOTRLevelData.getData(entity).addAchievement(LOTRAchievement.useArcenstoneRare);
                }
                entity.addPotionEffect(new PotionEffect(15, 100, 5));
                entity.addPotionEffect(new PotionEffect(4, 100, 5));
                entity.addPotionEffect(new PotionEffect(17, 100, 0));
                itemStack.damageItem(1, (EntityLivingBase)entity);
                world.playSoundEffect((double)i + 0.5, (double)j + 0.5, (double)k + 0.5, "lotr:item.pooff", 1.0f, 100.0f);
            }
        }
        return true;
    }

    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isCurrentItem) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.capabilities.isCreativeMode) {
                return;
            }
            String playerName = player.getDisplayName();
            if ("Thorin".equals(playerName)) {
                return;
            }
            if ("Thrain".equals(playerName)) {
                return;
            }
            if ("Thror".equals(playerName)) {
                return;
            }
            float dwarfAlignment = LOTRLevelData.getData(player).getAlignment(LOTRFaction.DURINS_FOLK);
            float redMountainsAlignment = LOTRLevelData.getData(player).getAlignment(LOTRFaction.RED_MOUNTAINS);
            float blueMountainsAlignment = LOTRLevelData.getData(player).getAlignment(LOTRFaction.BLUE_MOUNTAINS);
            if (entity.ticksExisted % 180 != 0) {
                return;
            }
            if (dwarfAlignment <= 2000.0f && redMountainsAlignment <= 2000.0f && blueMountainsAlignment <= 2000.0f) {
                player.setFire(5);
            }
        }
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(StatCollector.translateToLocal((String)"royal.name"));
        list.add(StatCollector.translateToLocal((String)"right.name"));
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

    @Override
    public int getUseCost() {
        return 1;
    }

    @Override
    public int getBaseRepairCost() {
        return 3;
    }
}

