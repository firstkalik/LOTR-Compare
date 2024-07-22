/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.block.LOTRBlockMug;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class LOTRBlockGoblet
extends LOTRBlockMug {
    public LOTRBlockGoblet() {
        super(2.5f, 9.0f);
        this.setStepSound(Block.soundTypeMetal);
    }

    public static class Gold
    extends LOTRBlockGoblet {
        @SideOnly(value=Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int j) {
            return Blocks.gold_block.getIcon(i, 0);
        }
    }

    public static class Mithril
    extends LOTRBlockGoblet {
        @SideOnly(value=Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int j) {
            return LOTRMod.blockOreStorage.getIcon(i, 4);
        }
    }

    public static class Silver
    extends LOTRBlockGoblet {
        @SideOnly(value=Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int j) {
            return LOTRMod.blockOreStorage.getIcon(i, 3);
        }
    }

    public static class Copper
    extends LOTRBlockGoblet {
        @SideOnly(value=Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int j) {
            return LOTRMod.blockOreStorage.getIcon(i, 0);
        }
    }

    public static class Wood
    extends LOTRBlockGoblet {
        public Wood() {
            this.setStepSound(Block.soundTypeWood);
        }

        @SideOnly(value=Side.CLIENT)
        @Override
        public IIcon getIcon(int i, int j) {
            return Blocks.planks.getIcon(i, 0);
        }
    }

}

