/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IRegistry
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRCreativeTabs;
import lotr.common.item.LOTRDispenseArrowDragon;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IRegistry;
import net.minecraft.util.StatCollector;

public class LOTRItemArrowDragon
extends Item {
    public LOTRItemArrowDragon() {
        this.setFull3D();
        this.maxStackSize = 4;
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseArrowDragon());
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        list.add((Object)EnumChatFormatting.RED + StatCollector.translateToLocal((String)"lotr.dragon"));
    }
}

