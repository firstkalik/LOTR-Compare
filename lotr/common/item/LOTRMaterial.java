/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.EnumHelper
 */
package lotr.common.item;

import java.util.ArrayList;
import java.util.List;
import lotr.common.LOTRMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class LOTRMaterial {
    private static float[] protectionBase = new float[]{0.14f, 0.4f, 0.32f, 0.14f};
    private static float maxProtection = 25.0f;
    public static List<LOTRMaterial> allLOTRMaterials = new ArrayList<LOTRMaterial>();
    public static LOTRMaterial BRONZE = new LOTRMaterial("BRONZE").setUses(230).setDamage(1.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(5.0f).setEnchantability(10);
    public static LOTRMaterial MITHRIL = new LOTRMaterial("MITHRIL").setUses(2400).setDamage(5.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(9.0f).setEnchantability(8);
    public static LOTRMaterial FUR = new LOTRMaterial("FUR").setUses(180).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial GEMSBOK = new LOTRMaterial("GEMSBOK").setUses(180).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial BONE = new LOTRMaterial("BONE").setUses(150).setDamage(0.0f).setProtection(0.3f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial GAMBESON = new LOTRMaterial("GAMBESON").setUses(200).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial JACKET = new LOTRMaterial("JACKET").setUses(150).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial GONDOR = new LOTRMaterial("GONDOR").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial DOL_AMROTH = new LOTRMaterial("DOL_AMROTH").setUses(500).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial ROHAN = new LOTRMaterial("ROHAN").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial ROHAN_MARSHAL = new LOTRMaterial("ROHAN_MARSHAL").setUses(400).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial RANGER = new LOTRMaterial("RANGER").setUses(350).setDamage(2.5f).setProtection(0.48f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12);
    public static LOTRMaterial RANGER_ITHILIEN = new LOTRMaterial("RANGER_ITHILIEN").setUses(350).setDamage(2.5f).setProtection(0.48f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12);
    public static LOTRMaterial DUNLENDING = new LOTRMaterial("DUNLENDING").setUses(250).setDamage(2.0f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(8);
    public static LOTRMaterial NEAR_HARAD = new LOTRMaterial("NEAR_HARAD").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial HARNEDOR = new LOTRMaterial("HARNEDOR").setUses(250).setDamage(2.0f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(8);
    public static LOTRMaterial UMBAR = new LOTRMaterial("UMBAR").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial CORSAIR = new LOTRMaterial("CORSAIR").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial GULF_HARAD = new LOTRMaterial("GULF_HARAD").setUses(350).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial HARAD_NOMAD = new LOTRMaterial("HARAD_NOMAD").setUses(200).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial ANCIENT_HARAD = new LOTRMaterial("ANCIENT_HARAD").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial MOREDAIN = new LOTRMaterial("MOREDAIN").setUses(250).setDamage(2.0f).setProtection(0.48f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial MOREDAIN_SPEAR = new LOTRMaterial("MOREDAIN_SPEAR").setUses(250).setDamage(3.0f).setProtection(0.0f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial MOREDAIN_WOOD = new LOTRMaterial("MOREDAIN_WOOD").setUses(250).setDamage(2.0f).setProtection(0.0f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial MOREDAIN_LION_ARMOR = new LOTRMaterial("MOREDAIN_LION_ARMOR").setUses(300).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial MOREDAIN_BRONZE = new LOTRMaterial("MOREDAIN_BRONZE").setUses(230).setDamage(1.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(5.0f).setEnchantability(10);
    public static LOTRMaterial TAUREDAIN = new LOTRMaterial("TAUREDAIN").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(3).setSpeed(8.0f).setEnchantability(10);
    public static LOTRMaterial TAUREDAIN_GOLD = new LOTRMaterial("TAUREDAIN_GOLD").setUses(400).setDamage(0.0f).setProtection(0.6f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial BARROW = new LOTRMaterial("BARROW").setUses(600).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(8.0f).setEnchantability(10);
    public static LOTRMaterial DALE = new LOTRMaterial("DALE").setUses(300).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial DORWINION = new LOTRMaterial("DORWINION").setUses(400).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial LOSSARNACH = new LOTRMaterial("LOSSARNACH").setUses(300).setDamage(2.0f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial PELARGIR = new LOTRMaterial("PELARGIR").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial PINNATH_GELIN = new LOTRMaterial("PINNATH_GELIN").setUses(400).setDamage(2.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial BLACKROOT = new LOTRMaterial("BLACKROOT").setUses(400).setDamage(2.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial LAMEDON = new LOTRMaterial("LAMEDON").setUses(300).setDamage(2.0f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial ARNOR = new LOTRMaterial("ARNOR").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial RHUN = new LOTRMaterial("RHUN").setUses(400).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial RHUN_GOLD = new LOTRMaterial("RHUN_GOLD").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial BLACK_NUMENOREAN = new LOTRMaterial("BLACK_NUMENOREAN").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial MALLORN = new LOTRMaterial("MALLORN").setUses(200).setDamage(1.5f).setProtection(0.0f).setHarvestLevel(1).setSpeed(4.0f).setEnchantability(15);
    public static LOTRMaterial GALADHRIM = new LOTRMaterial("GALADHRIM").setUses(600).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.0f).setEnchantability(15);
    public static LOTRMaterial GALVORN = new LOTRMaterial("GALVORN").setUses(600).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.0f).setEnchantability(15);
    public static LOTRMaterial WOOD_ELVEN_SCOUT = new LOTRMaterial("WOOD_ELVEN_SCOUT").setUses(300).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(15);
    public static LOTRMaterial WOOD_ELVEN = new LOTRMaterial("WOOD_ELVEN").setUses(500).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(9.0f).setEnchantability(15);
    public static LOTRMaterial HIGH_ELVEN = new LOTRMaterial("HIGH_ELVEN").setUses(700).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(8.0f).setEnchantability(15);
    public static LOTRMaterial GONDOLIN = new LOTRMaterial("GONDOLIN").setUses(1500).setDamage(5.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(8.0f).setEnchantability(15);
    public static LOTRMaterial MALLORN_MACE = new LOTRMaterial("MALLORN_MACE").setUses(1500).setDamage(4.5f).setProtection(0.0f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(15);
    public static LOTRMaterial HITHLAIN = new LOTRMaterial("HITHLAIN").setUses(300).setDamage(0.0f).setProtection(0.3f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(15);
    public static LOTRMaterial DORWINION_ELF = new LOTRMaterial("DORWINION_ELF").setUses(500).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.0f).setEnchantability(15);
    public static LOTRMaterial RIVENDELL = new LOTRMaterial("RIVENDELL").setUses(700).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(8.0f).setEnchantability(15);
    public static LOTRMaterial DWARVEN = new LOTRMaterial("DWARVEN").setUses(700).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial BLUE_DWARVEN = new LOTRMaterial("BLUE_DWARVEN").setUses(650).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial BLADORTHIN = new LOTRMaterial("BLADORTHIN").setUses(600).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial MORDOR = new LOTRMaterial("MORDOR").setUses(400).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(7).setManFlesh();
    public static LOTRMaterial URUK = new LOTRMaterial("URUK").setUses(550).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial MORGUL = new LOTRMaterial("MORGUL").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10).setManFlesh();
    public static LOTRMaterial GUNDABAD_URUK = new LOTRMaterial("GUNDABAD_URUK").setUses(500).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial ANGMAR = new LOTRMaterial("ANGMAR").setUses(350).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(8).setManFlesh();
    public static LOTRMaterial DOL_GULDUR = new LOTRMaterial("DOL_GULDUR").setUses(350).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10).setManFlesh();
    public static LOTRMaterial BLACK_URUK = new LOTRMaterial("BLACK_URUK").setUses(550).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial UTUMNO = new LOTRMaterial("UTUMNO").setUses(400).setDamage(3.5f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12).setManFlesh();
    public static LOTRMaterial HALF_TROLL = new LOTRMaterial("HALF_TROLL").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(1).setSpeed(5.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial COSMETIC = new LOTRMaterial("COSMETIC").setUndamageable().setUses(0).setDamage(0.0f).setProtection(0.0f).setEnchantability(0);
    public static LOTRMaterial HARAD_ROBES = new LOTRMaterial("HARAD_ROBES").setUndamageable().setUses(0).setDamage(0.0f).setProtection(0.0f).setEnchantability(0);
    public static LOTRMaterial KAFTAN = new LOTRMaterial("KAFTAN").setUndamageable().setUses(0).setDamage(0.0f).setProtection(0.0f).setEnchantability(0);
    private String materialName;
    private boolean undamageable = false;
    private int uses;
    private float damage;
    private int[] protection;
    private int harvestLevel;
    private float speed;
    private int enchantability;
    private boolean canHarvestManFlesh = false;
    private Item.ToolMaterial toolMaterial;
    private ItemArmor.ArmorMaterial armorMaterial;

    public static void setCraftingItems() {
        BRONZE.setCraftingItem(LOTRMod.bronze);
        MITHRIL.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilMail);
        FUR.setCraftingItem(LOTRMod.fur);
        GEMSBOK.setCraftingItem(LOTRMod.gemsbokHide);
        GAMBESON.setCraftingItem(Item.getItemFromBlock((Block)Blocks.wool));
        JACKET.setCraftingItem(Items.leather);
        GONDOR.setCraftingItem(Items.iron_ingot);
        DOL_AMROTH.setCraftingItem(Items.iron_ingot);
        ROHAN.setCraftingItem(Items.iron_ingot);
        ROHAN_MARSHAL.setCraftingItem(Items.iron_ingot);
        RANGER.setCraftingItems(Items.iron_ingot, Items.leather);
        RANGER_ITHILIEN.setCraftingItems(Items.iron_ingot, Items.leather);
        DUNLENDING.setCraftingItem(Items.iron_ingot);
        NEAR_HARAD.setCraftingItem(LOTRMod.bronze);
        HARNEDOR.setCraftingItem(LOTRMod.bronze);
        UMBAR.setCraftingItem(Items.iron_ingot);
        CORSAIR.setCraftingItems(Items.iron_ingot, LOTRMod.bronze);
        GULF_HARAD.setCraftingItem(LOTRMod.bronze);
        HARAD_NOMAD.setCraftingItem(Item.getItemFromBlock((Block)LOTRMod.driedReeds));
        ANCIENT_HARAD.setCraftingItem(Items.iron_ingot);
        MOREDAIN.setCraftingItems(LOTRMod.rhinoHorn, LOTRMod.gemsbokHide);
        MOREDAIN_SPEAR.setCraftingItem(LOTRMod.gemsbokHorn);
        MOREDAIN_LION_ARMOR.setCraftingItem(LOTRMod.lionFur);
        MOREDAIN_BRONZE.setCraftingItem(LOTRMod.bronze);
        TAUREDAIN.setCraftingItems(LOTRMod.obsidianShard, LOTRMod.bronze);
        TAUREDAIN_GOLD.setCraftingItem(Items.gold_ingot);
        BARROW.setCraftingItem(Items.iron_ingot);
        DALE.setCraftingItem(Items.iron_ingot);
        DORWINION.setCraftingItem(Items.iron_ingot);
        LOSSARNACH.setCraftingItem(Items.iron_ingot);
        PELARGIR.setCraftingItem(Items.iron_ingot);
        PINNATH_GELIN.setCraftingItem(Items.iron_ingot);
        BLACKROOT.setCraftingItem(Items.iron_ingot);
        LAMEDON.setCraftingItem(Items.iron_ingot);
        ARNOR.setCraftingItem(Items.iron_ingot);
        RHUN.setCraftingItem(Items.iron_ingot);
        RHUN_GOLD.setCraftingItem(LOTRMod.gildedIron);
        BLACK_NUMENOREAN.setCraftingItem(Items.iron_ingot);
        GALADHRIM.setCraftingItem(LOTRMod.elfSteel);
        GALVORN.setCraftingItem(LOTRMod.galvorn);
        WOOD_ELVEN_SCOUT.setCraftingItems(LOTRMod.elfSteel, Items.leather);
        WOOD_ELVEN.setCraftingItem(LOTRMod.elfSteel);
        HIGH_ELVEN.setCraftingItem(LOTRMod.elfSteel);
        GONDOLIN.setCraftingItem(LOTRMod.elfSteel);
        HITHLAIN.setCraftingItem(LOTRMod.hithlain);
        DORWINION_ELF.setCraftingItem(LOTRMod.elfSteel);
        RIVENDELL.setCraftingItem(LOTRMod.elfSteel);
        DWARVEN.setCraftingItem(LOTRMod.dwarfSteel);
        BLUE_DWARVEN.setCraftingItem(LOTRMod.blueDwarfSteel);
        BLADORTHIN.setCraftingItem(LOTRMod.dwarfSteel);
        MORDOR.setCraftingItem(LOTRMod.orcSteel);
        URUK.setCraftingItem(LOTRMod.urukSteel);
        MORGUL.setCraftingItem(LOTRMod.morgulSteel);
        GUNDABAD_URUK.setCraftingItem(LOTRMod.urukSteel);
        ANGMAR.setCraftingItem(LOTRMod.orcSteel);
        DOL_GULDUR.setCraftingItem(LOTRMod.orcSteel);
        BLACK_URUK.setCraftingItem(LOTRMod.blackUrukSteel);
        UTUMNO.setCraftingItem(LOTRMod.orcSteel);
        HALF_TROLL.setCraftingItems(Items.flint, LOTRMod.gemsbokHide);
    }

    private LOTRMaterial(String name) {
        this.materialName = "LOTR_" + name;
        allLOTRMaterials.add(this);
    }

    private LOTRMaterial setUndamageable() {
        this.undamageable = true;
        return this;
    }

    public boolean isDamageable() {
        return !this.undamageable;
    }

    private LOTRMaterial setUses(int i) {
        this.uses = i;
        return this;
    }

    private LOTRMaterial setDamage(float f) {
        this.damage = f;
        return this;
    }

    private LOTRMaterial setProtection(float f) {
        this.protection = new int[protectionBase.length];
        for (int i = 0; i < this.protection.length; ++i) {
            this.protection[i] = Math.round(protectionBase[i] * f * maxProtection);
        }
        return this;
    }

    private LOTRMaterial setHarvestLevel(int i) {
        this.harvestLevel = i;
        return this;
    }

    private LOTRMaterial setSpeed(float f) {
        this.speed = f;
        return this;
    }

    private LOTRMaterial setEnchantability(int i) {
        this.enchantability = i;
        return this;
    }

    private LOTRMaterial setManFlesh() {
        this.canHarvestManFlesh = true;
        return this;
    }

    public boolean canHarvestManFlesh() {
        return this.canHarvestManFlesh;
    }

    public Item.ToolMaterial toToolMaterial() {
        if (this.toolMaterial == null) {
            this.toolMaterial = EnumHelper.addToolMaterial((String)this.materialName, (int)this.harvestLevel, (int)this.uses, (float)this.speed, (float)this.damage, (int)this.enchantability);
        }
        return this.toolMaterial;
    }

    public ItemArmor.ArmorMaterial toArmorMaterial() {
        if (this.armorMaterial == null) {
            this.armorMaterial = EnumHelper.addArmorMaterial((String)this.materialName, (int)Math.round((float)this.uses * 0.06f), (int[])this.protection, (int)this.enchantability);
        }
        return this.armorMaterial;
    }

    private void setCraftingItem(Item item) {
        this.setCraftingItems(item, item);
    }

    private void setCraftingItems(Item toolItem, Item armorItem) {
        this.toToolMaterial().setRepairItem(new ItemStack(toolItem));
        this.toArmorMaterial().customCraftingMaterial = armorItem;
    }

    public static Item.ToolMaterial getToolMaterialByName(String name) {
        return Item.ToolMaterial.valueOf((String)name);
    }

    public static ItemArmor.ArmorMaterial getArmorMaterialByName(String name) {
        return ItemArmor.ArmorMaterial.valueOf((String)name);
    }
}

