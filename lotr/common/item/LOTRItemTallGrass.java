/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockTallGrass;
import lotr.common.item.LOTRItemBlockMetadata;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRItemTallGrass
extends LOTRItemBlockMetadata {
    public LOTRItemTallGrass(Block block) {
        super(block);
    }

    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public int getRenderPasses(int meta) {
        return LOTRBlockTallGrass.grassOverlay[meta] ? 2 : 1;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
        if (pass > 0) {
            return LOTRMod.tallGrass.getIcon(-1, meta);
        }
        return super.getIconFromDamageForRenderPass(meta, pass);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        if (pass > 0) {
            return 16777215;
        }
        return super.getColorFromItemStack(itemstack, pass);
    }
}

