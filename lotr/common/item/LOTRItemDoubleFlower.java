/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.util.IIcon
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.block.LOTRBlockDoubleFlower;
import lotr.common.item.LOTRItemBlockMetadata;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class LOTRItemDoubleFlower
extends LOTRItemBlockMetadata {
    public LOTRItemDoubleFlower(Block block) {
        super(block);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int i) {
        return ((LOTRBlockDoubleFlower)this.field_150939_a).func_149888_a(true, i);
    }
}

