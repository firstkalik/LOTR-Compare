/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.LOTRSquadrons;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRItemCommandHorn
extends Item
implements LOTRSquadrons.SquadronItem {
    public LOTRItemCommandHorn() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote) {
            List entities = world.loadedEntityList;
            for (int l = 0; l < entities.size(); ++l) {
                if (!(entities.get(l) instanceof LOTREntityNPC)) continue;
                LOTREntityNPC npc = (LOTREntityNPC)entities.get(l);
                if (!npc.hiredNPCInfo.isActive || npc.hiredNPCInfo.getHiringPlayer() != entityplayer || !LOTRSquadrons.areSquadronsCompatible(npc, itemstack)) continue;
                if (itemstack.getItemDamage() == 1 && npc.hiredNPCInfo.getObeyHornHaltReady()) {
                    npc.hiredNPCInfo.halt();
                    continue;
                }
                if (itemstack.getItemDamage() == 2 && npc.hiredNPCInfo.getObeyHornHaltReady()) {
                    npc.hiredNPCInfo.ready();
                    continue;
                }
                if (itemstack.getItemDamage() != 3 || !npc.hiredNPCInfo.getObeyHornSummon()) continue;
                npc.hiredNPCInfo.tryTeleportToHiringPlayer(true);
            }
        }
        if (itemstack.getItemDamage() == 1) {
            itemstack.setItemDamage(2);
        } else if (itemstack.getItemDamage() == 2) {
            itemstack.setItemDamage(1);
        }
        world.playSoundAtEntity((Entity)entityplayer, "lotr:item.horn", 4.0f, 1.0f);
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (itemstack.getItemDamage() == 0) {
            entityplayer.openGui((Object)LOTRMod.instance, 9, world, 0, 0, 0);
        } else {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        String s = "";
        if (itemstack.getItemDamage() == 1) {
            s = ".halt";
        } else if (itemstack.getItemDamage() == 2) {
            s = ".ready";
        } else if (itemstack.getItemDamage() == 3) {
            s = ".summon";
        }
        return super.getUnlocalizedName(itemstack) + s;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j <= 3; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
}

