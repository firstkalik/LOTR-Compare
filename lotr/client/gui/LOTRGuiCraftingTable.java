/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.gui;

import lotr.common.inventory.LOTRContainerCraftingTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public abstract class LOTRGuiCraftingTable
extends GuiContainer {
    private static ResourceLocation craftingTexture = new ResourceLocation("textures/gui/container/crafting_table.png");
    private String unlocalizedName;

    public LOTRGuiCraftingTable(LOTRContainerCraftingTable container, String s) {
        super((Container)container);
        this.unlocalizedName = s;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String title = StatCollector.translateToLocal((String)("container.lotr.crafting." + this.unlocalizedName));
        this.fontRendererObj.drawString(title, 28, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(craftingTexture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    public static class Wind
    extends LOTRGuiCraftingTable {
        public Wind(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Wind(inv, world, i, j, k), "wind");
        }
    }

    public static class Erebor
    extends LOTRGuiCraftingTable {
        public Erebor(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Erebor(inv, world, i, j, k), "erebor");
        }
    }

    public static class Angband
    extends LOTRGuiCraftingTable {
        public Angband(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Angband(inv, world, i, j, k), "angband");
        }
    }

    public static class Red
    extends LOTRGuiCraftingTable {
        public Red(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Red(inv, world, i, j, k), "reddwarf");
        }
    }

    public static class Morgul
    extends LOTRGuiCraftingTable {
        public Morgul(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Morgul(inv, world, i, j, k), "morgul");
        }
    }

    public static class Elven
    extends LOTRGuiCraftingTable {
        public Elven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Elven(inv, world, i, j, k), "elven");
        }
    }

    public static class Dwarven
    extends LOTRGuiCraftingTable {
        public Dwarven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Dwarven(inv, world, i, j, k), "dwarven");
        }
    }

    public static class Uruk
    extends LOTRGuiCraftingTable {
        public Uruk(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Uruk(inv, world, i, j, k), "uruk");
        }
    }

    public static class Gondorian
    extends LOTRGuiCraftingTable {
        public Gondorian(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Gondorian(inv, world, i, j, k), "gondorian");
        }
    }

    public static class Rohirric
    extends LOTRGuiCraftingTable {
        public Rohirric(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Rohirric(inv, world, i, j, k), "rohirric");
        }
    }

    public static class WoodElven
    extends LOTRGuiCraftingTable {
        public WoodElven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.WoodElven(inv, world, i, j, k), "woodElven");
        }
    }

    public static class Dunlending
    extends LOTRGuiCraftingTable {
        public Dunlending(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Dunlending(inv, world, i, j, k), "dunlending");
        }
    }

    public static class Angmar
    extends LOTRGuiCraftingTable {
        public Angmar(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Angmar(inv, world, i, j, k), "angmar");
        }
    }

    public static class NearHarad
    extends LOTRGuiCraftingTable {
        public NearHarad(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.NearHarad(inv, world, i, j, k), "nearHarad");
        }
    }

    public static class HighElven
    extends LOTRGuiCraftingTable {
        public HighElven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.HighElven(inv, world, i, j, k), "highElven");
        }
    }

    public static class DarkElf
    extends LOTRGuiCraftingTable {
        public DarkElf(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.DarkElf(inv, world, i, j, k), "DarkElf");
        }
    }

    public static class Avari
    extends LOTRGuiCraftingTable {
        public Avari(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Avari(inv, world, i, j, k), "Avari");
        }
    }

    public static class WickedDwarfs
    extends LOTRGuiCraftingTable {
        public WickedDwarfs(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.WickedDwarf(inv, world, i, j, k), "WickedDwarfs");
        }
    }

    public static class BlueDwarven
    extends LOTRGuiCraftingTable {
        public BlueDwarven(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.BlueDwarven(inv, world, i, j, k), "blueDwarven");
        }
    }

    public static class Ranger
    extends LOTRGuiCraftingTable {
        public Ranger(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Ranger(inv, world, i, j, k), "ranger");
        }
    }

    public static class DolGuldur
    extends LOTRGuiCraftingTable {
        public DolGuldur(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.DolGuldur(inv, world, i, j, k), "dolGuldur");
        }
    }

    public static class Gundabad
    extends LOTRGuiCraftingTable {
        public Gundabad(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Gundabad(inv, world, i, j, k), "gundabad");
        }
    }

    public static class HalfTroll
    extends LOTRGuiCraftingTable {
        public HalfTroll(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.HalfTroll(inv, world, i, j, k), "halfTroll");
        }
    }

    public static class DolAmroth
    extends LOTRGuiCraftingTable {
        public DolAmroth(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.DolAmroth(inv, world, i, j, k), "dolAmroth");
        }
    }

    public static class Moredain
    extends LOTRGuiCraftingTable {
        public Moredain(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Moredain(inv, world, i, j, k), "moredain");
        }
    }

    public static class Tauredain
    extends LOTRGuiCraftingTable {
        public Tauredain(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Tauredain(inv, world, i, j, k), "tauredain");
        }
    }

    public static class Dale
    extends LOTRGuiCraftingTable {
        public Dale(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Dale(inv, world, i, j, k), "dale");
        }
    }

    public static class Dorwinion
    extends LOTRGuiCraftingTable {
        public Dorwinion(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Dorwinion(inv, world, i, j, k), "dorwinion");
        }
    }

    public static class Hobbit
    extends LOTRGuiCraftingTable {
        public Hobbit(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Hobbit(inv, world, i, j, k), "hobbit");
        }
    }

    public static class Rhun
    extends LOTRGuiCraftingTable {
        public Rhun(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Rhun(inv, world, i, j, k), "rhun");
        }
    }

    public static class Rivendell
    extends LOTRGuiCraftingTable {
        public Rivendell(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Rivendell(inv, world, i, j, k), "rivendell");
        }
    }

    public static class Umbar
    extends LOTRGuiCraftingTable {
        public Umbar(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Umbar(inv, world, i, j, k), "umbar");
        }
    }

    public static class Gulf
    extends LOTRGuiCraftingTable {
        public Gulf(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Gulf(inv, world, i, j, k), "gulf");
        }
    }

    public static class Bree
    extends LOTRGuiCraftingTable {
        public Bree(InventoryPlayer inv, World world, int i, int j, int k) {
            super(new LOTRContainerCraftingTable.Bree(inv, world, i, j, k), "bree");
        }
    }

}

