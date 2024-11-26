/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
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
import net.minecraft.entity.EntityLivingBase;
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
    public static LOTRMaterial GOLD1 = new LOTRMaterial("GOLD1").setUses(200).setDamage(1.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(5.0f).setEnchantability(10);
    public static LOTRMaterial BRONZE1 = new LOTRMaterial("BRONZE1").setUses(180).setDamage(1.5f).setProtection(0.45f).setHarvestLevel(2).setSpeed(5.0f).setEnchantability(10);
    public static LOTRMaterial MITHRIL = new LOTRMaterial("MITHRIL").setUses(2400).setDamage(5.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(9.0f).setEnchantability(8);
    public static LOTRMaterial FUR = new LOTRMaterial("FUR").setUses(180).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial GEMSBOK = new LOTRMaterial("GEMSBOK").setUses(180).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial BONE = new LOTRMaterial("BONE").setUses(150).setDamage(0.0f).setProtection(0.3f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial GAMBESON = new LOTRMaterial("GAMBESON").setUses(200).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial JACKET = new LOTRMaterial("JACKET").setUses(150).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(10);
    public static LOTRMaterial GONDOR = new LOTRMaterial("GONDOR").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial DOL_AMROTH = new LOTRMaterial("DOL_AMROTH").setUses(500).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial ROHAN = new LOTRMaterial("ROHAN").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial CARDOLAN = new LOTRMaterial("ROHAN").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial ROHAN_MARSHAL = new LOTRMaterial("ROHAN_MARSHAL").setUses(400).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial RANGER = new LOTRMaterial("RANGER").setUses(350).setDamage(2.5f).setProtection(0.48f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12);
    public static LOTRMaterial RANGER_ITHILIEN = new LOTRMaterial("RANGER_ITHILIEN").setUses(350).setDamage(2.5f).setProtection(0.48f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12);
    public static LOTRMaterial DUNLENDING = new LOTRMaterial("DUNLENDING").setUses(250).setDamage(2.0f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(8);
    public static LOTRMaterial WOODLENDING = new LOTRMaterial("WOODLENDING").setUses(120).setDamage(2.0f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(8);
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
    public static LOTRMaterial LOSSARNACH = new LOTRMaterial("LOSSARNACH").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
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
    public static LOTRMaterial DAIN = new LOTRMaterial("DWARVEN").setUses(765).setDamage(3.0f).setProtection(0.8f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial DWARVENGOLD = new LOTRMaterial("DWARVEN").setUses(735).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial DWARVENSILVER = new LOTRMaterial("DWARVEN").setUses(710).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial DWARVENGOLDBOAR = new LOTRMaterial("DWARVENGOLD").setUses(735).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial DWARVENSILVERBOAR = new LOTRMaterial("DWARVENSILVER").setUses(710).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial DWARVENMITHRIL = new LOTRMaterial("DWARVEN").setUses(765).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial DWARVEN1 = new LOTRMaterial("DWARVEN1").setUses(450).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial BLUE_DWARVEN = new LOTRMaterial("BLUE_DWARVEN").setUses(650).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial BLUE_DWARVENGOLD = new LOTRMaterial("BLUE_DWARVEN").setUses(685).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial BLUE_DWARVENSILVER = new LOTRMaterial("BLUE_DWARVEN").setUses(665).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial BLUE_DWARVENMITHRIL = new LOTRMaterial("BLUE_DWARVEN").setUses(700).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial BLUEDWARVENGOLDBOAR = new LOTRMaterial("BLUEDWARVENGOLD").setUses(735).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial BLUEDWARVENSILVERBOAR = new LOTRMaterial("BLUEDWARVENSILVER").setUses(710).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial BLADORTHIN = new LOTRMaterial("BLADORTHIN").setUses(600).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial MORDOR = new LOTRMaterial("MORDOR").setUses(400).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(7).setManFlesh();
    public static LOTRMaterial MORDOR1 = new LOTRMaterial("MORDOR1").setUses(250).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(7).setManFlesh();
    public static LOTRMaterial URUK = new LOTRMaterial("URUK").setUses(550).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial URUK1 = new LOTRMaterial("URUK1").setUses(350).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial MORGUL = new LOTRMaterial("MORGUL").setUses(450).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10).setManFlesh();
    public static LOTRMaterial GUNDABAD_URUK = new LOTRMaterial("GUNDABAD_URUK").setUses(500).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial ANGMAR = new LOTRMaterial("ANGMAR").setUses(350).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(8).setManFlesh();
    public static LOTRMaterial DOL_GULDUR = new LOTRMaterial("DOL_GULDUR").setUses(350).setDamage(2.5f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10).setManFlesh();
    public static LOTRMaterial BLACK_URUK = new LOTRMaterial("BLACK_URUK").setUses(550).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial BLACK_URUK1 = new LOTRMaterial("BLACK_URUK1").setUses(350).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial UTUMNO = new LOTRMaterial("UTUMNO").setUses(400).setDamage(3.5f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12).setManFlesh();
    public static LOTRMaterial HALF_TROLL = new LOTRMaterial("HALF_TROLL").setUses(300).setDamage(2.5f).setProtection(0.5f).setHarvestLevel(1).setSpeed(5.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial COSMETIC = new LOTRMaterial("COSMETIC").setUndamageable().setUses(0).setDamage(0.0f).setProtection(0.0f).setEnchantability(0);
    public static LOTRMaterial HARAD_ROBES = new LOTRMaterial("HARAD_ROBES").setUndamageable().setUses(0).setDamage(0.0f).setProtection(0.0f).setEnchantability(0);
    public static LOTRMaterial KAFTAN = new LOTRMaterial("KAFTAN").setUndamageable().setUses(0).setDamage(0.0f).setProtection(0.0f).setEnchantability(0);
    public static LOTRMaterial MITHRILD = new LOTRMaterial("MITHRILD").setUses(3500).setDamage(3.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(7.0f).setEnchantability(8);
    public static LOTRMaterial BILBO = new LOTRMaterial("BILBO").setUses(3300).setDamage(3.0f).setProtection(0.85f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial REDGILDED = new LOTRMaterial("REDGILDED").setUses(700).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial ANGBAND = new LOTRMaterial("ANGBAND").setUses(500).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial ANGBANDU = new LOTRMaterial("ANGBANDU").setUses(280).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial MORIA = new LOTRMaterial("MORIA").setUses(3000).setDamage(3.0f).setProtection(0.8f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial WIND = new LOTRMaterial("WIND").setUses(650).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial BERSERK = new LOTRMaterial("BERSERK").setUses(600).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial ANGBANDE = new LOTRMaterial("ANGBANDE").setUses(615).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial BOLDOG = new LOTRMaterial("BOLDOG").setUses(630).setDamage(3.5f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial GULDUR = new LOTRMaterial("GULDUR").setUses(580).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.5f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial GULDUR1 = new LOTRMaterial("GULDUR1").setUses(550).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.5f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial GULDUR2 = new LOTRMaterial("GULDUR2").setUses(580).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.5f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial EREBOR = new LOTRMaterial("EREBOR").setUses(750).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial EREBORGOLD = new LOTRMaterial("EREBOR").setUses(775).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial EREBORSILVER = new LOTRMaterial("EREBOR").setUses(760).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial EREBORMITHRIL = new LOTRMaterial("EREBOR").setUses(795).setDamage(3.5f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial AULE = new LOTRMaterial("AULE").setUses(4800).setDamage(5.5f).setProtection(0.8f).setHarvestLevel(4).setSpeed(9.5f).setEnchantability(8);
    public static LOTRMaterial ARCENSTONE = new LOTRMaterial("ARCENSTONE").setUses(4800).setDamage(0.0f).setProtection(0.0f).setHarvestLevel(4).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial TILKAL = new LOTRMaterial("TILKAL").setUses(2400).setDamage(5.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(9.0f).setEnchantability(8);
    public static LOTRMaterial GANDALF = new LOTRMaterial("GANDALF").setUses(700).setDamage(0.0f).setProtection(0.8f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial SARUMAN = new LOTRMaterial("SARUMAN").setUses(710).setDamage(0.0f).setProtection(0.8f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial RADAGAST = new LOTRMaterial("RADAGAST").setUses(690).setDamage(0.0f).setProtection(0.8f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial ALATAR = new LOTRMaterial("ALATAR").setUses(670).setDamage(0.0f).setProtection(0.8f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial PALLANDO = new LOTRMaterial("PALLANDO").setUses(671).setDamage(0.0f).setProtection(0.8f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(8);
    public static LOTRMaterial SAURON = new LOTRMaterial("SAURON").setUses(900).setDamage(3.5f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial DARK_DWARVEN = new LOTRMaterial("DARK_DWARVEN").setUses(580).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(6.5f).setEnchantability(12).setManFlesh();
    public static LOTRMaterial GREY_DWARVEN = new LOTRMaterial("GREY_DWARVEN").setUses(655).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.0f).setEnchantability(12);
    public static LOTRMaterial GOLDEN_DWARVEN = new LOTRMaterial("GOLDEN_DWARVEN").setUses(310).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(6.6f).setEnchantability(10);
    public static LOTRMaterial DWARVENSILVERFULL = new LOTRMaterial("DWARVENSILVERFULL").setUses(100).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(6.6f).setEnchantability(10);
    public static LOTRMaterial MORIA_DWARVEN = new LOTRMaterial("MORIA_DWARVEN").setUses(660).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(12);
    public static LOTRMaterial GONDOR_MITHRIL = new LOTRMaterial("GONDOR_MITHRIL").setUses(2600).setDamage(3.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(7.0f).setEnchantability(10);
    public static LOTRMaterial MITHRIL_BRUSH = new LOTRMaterial("MITHRIL_BRUSH").setUses(1200).setDamage(5.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(7.0f).setEnchantability(8);
    public static LOTRMaterial AVARI_ELVEN_SCOUT = new LOTRMaterial("AVARI_ELVEN_SCOUT").setUses(300).setDamage(0.0f).setProtection(0.4f).setHarvestLevel(0).setSpeed(0.0f).setEnchantability(15);
    public static LOTRMaterial AVARI_ELVEN = new LOTRMaterial("AVARI_ELVEN").setUses(480).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.4f).setEnchantability(15);
    public static LOTRMaterial AVARI_ELVEN_DAGGER = new LOTRMaterial("AVARI_ELVEN_DAGGER").setUses(480).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(7.4f).setEnchantability(15);
    public static LOTRMaterial MELKOQUENDI = new LOTRMaterial("MELKOQUENDI").setUses(450).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.5f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial MELKOQUENDI_RANGER = new LOTRMaterial("MELKOQUENDI_RANGER").setUses(300).setDamage(3.0f).setProtection(0.6f).setHarvestLevel(2).setSpeed(6.5f).setEnchantability(6).setManFlesh();
    public static LOTRMaterial MAIRON = new LOTRMaterial("MAIRON").setUses(3800).setDamage(5.5f).setProtection(0.8f).setHarvestLevel(4).setSpeed(9.3f).setEnchantability(8);
    public static LOTRMaterial MITHRIL_ELVEN = new LOTRMaterial("MITHRIL").setUses(2800).setDamage(5.0f).setProtection(0.8f).setHarvestLevel(4).setSpeed(9.0f).setEnchantability(8);
    public static LOTRMaterial REDIRONFIST = new LOTRMaterial("RED").setUses(700).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(8);
    public static LOTRMaterial REDSTIFFBEARD = new LOTRMaterial("RED").setUses(710).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial REDBLACKLOCK = new LOTRMaterial("RED").setUses(730).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial REDSTONEFOOT = new LOTRMaterial("RED").setUses(745).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(3).setSpeed(7.5f).setEnchantability(10);
    public static LOTRMaterial ARAGORN = new LOTRMaterial("ARAGORN").setUses(500).setDamage(2.5f).setProtection(0.8f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(10);
    public static LOTRMaterial DURMETH = new LOTRMaterial("DURMETH").setUses(500).setDamage(3.0f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(5).setManFlesh();
    public static LOTRMaterial UTUMNO_LEGENDARY = new LOTRMaterial("UTUMNO_LEGENDARY").setUses(400).setDamage(3.5f).setProtection(0.7f).setHarvestLevel(2).setSpeed(6.0f).setEnchantability(12).setManFlesh();
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
        ALATAR.setCraftingItem(LOTRMod.fur);
        ANCIENT_HARAD.setCraftingItem(Items.iron_ingot);
        ANGBAND.setCraftingItem(LOTRMod.urukSteel);
        ANGBANDE.setCraftingItem(LOTRMod.urukSteel);
        ANGBANDU.setCraftingItem(LOTRMod.urukSteel);
        ANGMAR.setCraftingItem(LOTRMod.orcSteel);
        ARCENSTONE.setCraftingItem(Items.leather);
        ARNOR.setCraftingItem(Items.iron_ingot);
        AULE.setCraftingItem(LOTRMod.dwarfSteel);
        AVARI_ELVEN.setCraftingItem(LOTRMod.elfSteel);
        AVARI_ELVEN_DAGGER.setCraftingItem(LOTRMod.elfSteel);
        AVARI_ELVEN_SCOUT.setCraftingItems(LOTRMod.elfSteel, Items.leather);
        BARROW.setCraftingItem(Items.iron_ingot);
        BERSERK.setCraftingItem(LOTRMod.urukSteel);
        BILBO.setCraftingItems(LOTRMod.mithril, LOTRMod.mithril);
        BLACK_NUMENOREAN.setCraftingItem(Items.iron_ingot);
        BLACK_URUK.setCraftingItem(LOTRMod.blackUrukSteel);
        BLACKROOT.setCraftingItem(Items.iron_ingot);
        BLADORTHIN.setCraftingItem(LOTRMod.dwarfSteel);
        BLUE_DWARVEN.setCraftingItem(LOTRMod.dwarfSteel);
        BLUE_DWARVENSILVER.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.silverNugget);
        BLUE_DWARVENGOLD.setCraftingItems(LOTRMod.dwarfSteel, Items.gold_nugget);
        BLUEDWARVENGOLDBOAR.setCraftingItems(LOTRMod.dwarfSteel, Items.gold_nugget);
        BLUEDWARVENSILVERBOAR.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.silverNugget);
        BOLDOG.setCraftingItem(LOTRMod.urukSteel);
        BRONZE.setCraftingItem(LOTRMod.bronze);
        GOLD1.setCraftingItems(Items.gold_ingot, Items.gold_nugget);
        BRONZE1.setCraftingItems(LOTRMod.bronze, LOTRMod.bronzeNugget);
        CORSAIR.setCraftingItems(Items.iron_ingot, LOTRMod.bronze);
        DALE.setCraftingItem(Items.iron_ingot);
        DARK_DWARVEN.setCraftingItem(LOTRMod.dwarfSteel);
        DOL_AMROTH.setCraftingItem(Items.iron_ingot);
        DOL_GULDUR.setCraftingItems(LOTRMod.orcSteel, LOTRMod.urukSteel);
        DORWINION.setCraftingItem(Items.iron_ingot);
        DORWINION_ELF.setCraftingItem(LOTRMod.elfSteel);
        DUNLENDING.setCraftingItem(Items.iron_ingot);
        DWARVEN.setCraftingItem(LOTRMod.dwarfSteel);
        DAIN.setCraftingItem(LOTRMod.dwarfSteel);
        DWARVENSILVER.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.silverNugget);
        DWARVENGOLD.setCraftingItems(LOTRMod.dwarfSteel, Items.gold_nugget);
        DWARVENSILVERBOAR.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.silverNugget);
        DWARVENGOLDBOAR.setCraftingItems(LOTRMod.dwarfSteel, Items.gold_nugget);
        DWARVENMITHRIL.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.mithrilNugget);
        EREBOR.setCraftingItem(LOTRMod.dwarfSteel);
        EREBORSILVER.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.silverNugget);
        EREBORGOLD.setCraftingItems(LOTRMod.dwarfSteel, Items.gold_nugget);
        EREBORMITHRIL.setCraftingItems(LOTRMod.dwarfSteel, LOTRMod.mithrilNugget);
        FUR.setCraftingItem(LOTRMod.fur);
        GALADHRIM.setCraftingItem(LOTRMod.elfSteel);
        GALVORN.setCraftingItem(LOTRMod.galvorn);
        GAMBESON.setCraftingItem(Item.getItemFromBlock((Block)Blocks.wool));
        GANDALF.setCraftingItem(LOTRMod.fur);
        GEMSBOK.setCraftingItem(LOTRMod.gemsbokHide);
        GOLDEN_DWARVEN.setCraftingItem(LOTRMod.gildedIron);
        DWARVENSILVERFULL.setCraftingItem(LOTRMod.silver);
        GONDOLIN.setCraftingItem(LOTRMod.elfSteel);
        GONDOR.setCraftingItem(Items.iron_ingot);
        ARAGORN.setCraftingItem(Items.iron_ingot);
        GREY_DWARVEN.setCraftingItem(LOTRMod.dwarfSteel);
        GULDUR.setCraftingItems(LOTRMod.urukSteel, LOTRMod.orcSteel);
        GULDUR1.setCraftingItem(LOTRMod.morgulSteel);
        GULDUR2.setCraftingItem(LOTRMod.urukSteel);
        GULF_HARAD.setCraftingItem(LOTRMod.bronze);
        DURMETH.setCraftingItem(LOTRMod.orcSteel);
        GUNDABAD_URUK.setCraftingItem(LOTRMod.urukSteel);
        HALF_TROLL.setCraftingItems(Items.flint, LOTRMod.gemsbokHide);
        HARAD_NOMAD.setCraftingItem(Item.getItemFromBlock((Block)LOTRMod.driedReeds));
        HARNEDOR.setCraftingItem(LOTRMod.bronze);
        HIGH_ELVEN.setCraftingItem(LOTRMod.elfSteel);
        HITHLAIN.setCraftingItem(LOTRMod.hithlain);
        JACKET.setCraftingItem(Items.leather);
        LAMEDON.setCraftingItem(Items.iron_ingot);
        LOSSARNACH.setCraftingItem(Items.iron_ingot);
        MAIRON.setCraftingItem(LOTRMod.urukSteel);
        MORIA_DWARVEN.setCraftingItem(LOTRMod.dwarfSteel);
        MITHRIL.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilMail);
        GONDOR_MITHRIL.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilMail);
        MITHRIL_ELVEN.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilMail);
        MITHRIL_BRUSH.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilNugget);
        MITHRILD.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilMail);
        MORDOR.setCraftingItem(LOTRMod.orcSteel);
        MELKOQUENDI.setCraftingItem(LOTRMod.urukSteel);
        MELKOQUENDI_RANGER.setCraftingItems(LOTRMod.urukSteel, LOTRMod.fur);
        MOREDAIN.setCraftingItems(LOTRMod.rhinoHorn, LOTRMod.gemsbokHide);
        MOREDAIN_BRONZE.setCraftingItem(LOTRMod.bronze);
        MOREDAIN_LION_ARMOR.setCraftingItem(LOTRMod.lionFur);
        MOREDAIN_SPEAR.setCraftingItem(LOTRMod.gemsbokHorn);
        MORGUL.setCraftingItem(LOTRMod.morgulSteel);
        MORIA.setCraftingItems(LOTRMod.mithril, LOTRMod.mithrilMail);
        NEAR_HARAD.setCraftingItem(LOTRMod.bronze);
        PALLANDO.setCraftingItem(LOTRMod.fur);
        PELARGIR.setCraftingItem(Items.iron_ingot);
        PINNATH_GELIN.setCraftingItem(Items.iron_ingot);
        RANGER.setCraftingItems(Items.iron_ingot, Items.leather);
        RANGER_ITHILIEN.setCraftingItems(Items.iron_ingot, Items.leather);
        REDSTONEFOOT.setCraftingItem(LOTRMod.dwarfSteel);
        REDBLACKLOCK.setCraftingItem(LOTRMod.dwarfSteel);
        REDSTIFFBEARD.setCraftingItem(LOTRMod.dwarfSteel);
        REDIRONFIST.setCraftingItem(LOTRMod.dwarfSteel);
        RHUN.setCraftingItem(Items.iron_ingot);
        RHUN_GOLD.setCraftingItem(LOTRMod.gildedIron);
        RIVENDELL.setCraftingItem(LOTRMod.elfSteel);
        ROHAN.setCraftingItem(Items.iron_ingot);
        ROHAN_MARSHAL.setCraftingItem(Items.iron_ingot);
        SARUMAN.setCraftingItem(LOTRMod.fur);
        SAURON.setCraftingItem(LOTRMod.urukSteel);
        TAUREDAIN.setCraftingItems(LOTRMod.obsidianShard, LOTRMod.bronze);
        TAUREDAIN_GOLD.setCraftingItem(Items.gold_ingot);
        UMBAR.setCraftingItem(Items.iron_ingot);
        URUK.setCraftingItem(LOTRMod.urukSteel);
        UTUMNO.setCraftingItem(LOTRMod.urukSteel);
        UTUMNO_LEGENDARY.setCraftingItem(LOTRMod.urukSteel);
        WIND.setCraftingItem(LOTRMod.dwarfSteel);
        WOOD_ELVEN.setCraftingItem(LOTRMod.elfSteel);
        WOOD_ELVEN_SCOUT.setCraftingItems(LOTRMod.elfSteel, Items.leather);
        WOODLENDING.setCraftingItem(Items.coal);
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

    public static ItemArmor.ArmorMaterial getFullMaterial(EntityLivingBase entity) {
        ItemArmor.ArmorMaterial fullmaterial = null;
        int i = 1;
        while (i <= 4) {
            ItemStack armour = entity.getEquipmentInSlot(i);
            if (armour != null && armour.getItem() instanceof ItemArmor) {
                ItemArmor.ArmorMaterial mat = ((ItemArmor)armour.getItem()).getArmorMaterial();
                if (fullmaterial == null) {
                    fullmaterial = mat;
                }
                if (fullmaterial == mat) {
                    ++i;
                    continue;
                }
            }
            fullmaterial = null;
        }
        return fullmaterial;
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

