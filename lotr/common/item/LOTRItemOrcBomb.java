/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IRegistry
 *  net.minecraft.util.StatCollector
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.block.LOTRBlockOrcBomb;
import lotr.common.dispenser.LOTRDispenseOrcBomb;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.util.StatCollector;

public class LOTRItemOrcBomb
extends ItemBlock {
    public LOTRItemOrcBomb(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        BlockDispenser.dispenseBehaviorRegistry.putObject((Object)this, (Object)new LOTRDispenseOrcBomb());
    }

    public int getMetadata(int i) {
        return i;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        int meta = itemstack.getItemDamage();
        int strength = LOTRBlockOrcBomb.getBombStrengthLevel(meta);
        if (strength == 1) {
            list.add(StatCollector.translateToLocal((String)"tile.lotr.orcBomb.doubleStrength"));
        }
        if (strength == 2) {
            list.add(StatCollector.translateToLocal((String)"tile.lotr.orcBomb.tripleStrength"));
        }
        if (LOTRBlockOrcBomb.isFireBomb(meta)) {
            list.add(StatCollector.translateToLocal((String)"tile.lotr.orcBomb.fire"));
        }
    }
}

