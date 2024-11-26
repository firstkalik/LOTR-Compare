/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityInvasionSpawner;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemConquestHorn
extends Item {
    @SideOnly(value=Side.CLIENT)
    private IIcon baseIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayIcon;

    public LOTRItemConquestHorn() {
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabCombat);
    }

    public static LOTRInvasions getInvasionType(ItemStack itemstack) {
        String s;
        LOTRInvasions invasionType = null;
        if (itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("InvasionType")) {
            s = itemstack.getTagCompound().getString("InvasionType");
            invasionType = LOTRInvasions.forName(s);
        }
        if (invasionType == null && itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("HornFaction")) {
            s = itemstack.getTagCompound().getString("HornFaction");
            invasionType = LOTRInvasions.forName(s);
        }
        if (invasionType == null) {
            invasionType = LOTRInvasions.HOBBIT;
        }
        return invasionType;
    }

    public static void setInvasionType(ItemStack itemstack, LOTRInvasions type) {
        if (itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setString("InvasionType", type.codeName());
    }

    public static ItemStack createHorn(LOTRInvasions type) {
        ItemStack itemstack = new ItemStack(LOTRMod.conquestHorn);
        LOTRItemConquestHorn.setInvasionType(itemstack, type);
        return itemstack;
    }

    private boolean canUseHorn(ItemStack itemstack, World world, EntityPlayer entityplayer, boolean sendMessage) {
        if (LOTRDimension.getCurrentDimensionWithFallback(world) == LOTRDimension.UTUMNO) {
            if (sendMessage && !world.isRemote) {
                entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.conquestHornProtected", new Object[0]));
            }
            return false;
        }
        LOTRInvasions invasionType = LOTRItemConquestHorn.getInvasionType(itemstack);
        LOTRFaction invasionFaction = invasionType.invasionFaction;
        float alignmentRequired = 1500.0f;
        if (LOTRLevelData.getData(entityplayer).getAlignment(invasionFaction) >= alignmentRequired) {
            boolean blocked = false;
            if (LOTRBannerProtection.isProtected(world, (Entity)entityplayer, LOTRBannerProtection.forFaction(invasionFaction), false)) {
                blocked = true;
            }
            if (LOTREntityNPCRespawner.isSpawnBlocked((Entity)entityplayer, invasionFaction)) {
                blocked = true;
            }
            if (blocked) {
                if (sendMessage && !world.isRemote) {
                    entityplayer.addChatMessage((IChatComponent)new ChatComponentTranslation("chat.lotr.conquestHornProtected", new Object[]{invasionFaction.factionName()}));
                }
                return false;
            }
            return true;
        }
        if (sendMessage && !world.isRemote) {
            LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, alignmentRequired, invasionType.invasionFaction);
        }
        return false;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        this.canUseHorn(itemstack, world, entityplayer, false);
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        LOTRInvasions invasionType = LOTRItemConquestHorn.getInvasionType(itemstack);
        if (this.canUseHorn(itemstack, world, entityplayer, true)) {
            if (!world.isRemote) {
                LOTREntityInvasionSpawner invasion = new LOTREntityInvasionSpawner(world);
                invasion.setInvasionType(invasionType);
                invasion.isWarhorn = true;
                invasion.spawnsPersistent = true;
                invasion.setLocationAndAngles(entityplayer.posX, entityplayer.posY + 3.0, entityplayer.posZ, 0.0f, 0.0f);
                world.spawnEntityInWorld((Entity)invasion);
                invasion.startInvasion(entityplayer);
            }
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
            return itemstack;
        }
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        this.baseIcon = iconregister.registerIcon(this.getIconString() + "_base");
        this.overlayIcon = iconregister.registerIcon(this.getIconString() + "_overlay");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int i, int pass) {
        return pass > 0 ? this.overlayIcon : this.baseIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        if (pass == 0) {
            LOTRFaction faction = LOTRItemConquestHorn.getInvasionType((ItemStack)itemstack).invasionFaction;
            return faction.getFactionColor();
        }
        return 16777215;
    }

    public String getItemStackDisplayName(ItemStack itemstack) {
        LOTRInvasions type = LOTRItemConquestHorn.getInvasionType(itemstack);
        if (type != null) {
            return StatCollector.translateToLocal((String)type.codeNameHorn());
        }
        return super.getItemStackDisplayName(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        LOTRInvasions type = LOTRItemConquestHorn.getInvasionType(itemstack);
        LOTRFaction faction = type.invasionFaction;
        float[] factionRgb = faction.getFactionRGB_MinBrightness(0.45f);
        EnumChatFormatting color = this.getColorFromRGB(factionRgb);
        list.add((Object)color + type.invasionName());
    }

    private EnumChatFormatting getColorFromRGB(float[] rgb) {
        int r = (int)(rgb[0] * 255.0f);
        int g = (int)(rgb[1] * 255.0f);
        int b = (int)(rgb[2] * 255.0f);
        if (this.isCloseToColor(r, g, b, 16, 120, 8, 15)) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (this.isCloseToColor(r, g, b, 119, 145, 119, 15)) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (this.isCloseToColor(r, g, b, 77, 115, 88, 5)) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (this.isCloseToColor(r, g, b, 127, 32, 0, 5)) {
            return EnumChatFormatting.RED;
        }
        if (this.isCloseToColor(r, g, b, 73, 183, 82, 15)) {
            return EnumChatFormatting.GREEN;
        }
        if (this.isCloseToColor(r, g, b, 53, 135, 39, 15)) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (this.isCloseToColor(r, g, b, 53, 115, 76, 15)) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (this.isCloseToColor(r, g, b, 57, 150, 78, 15)) {
            return EnumChatFormatting.DARK_GREEN;
        }
        if (this.isCloseToColor(r, g, b, 89, 206, 78, 15)) {
            return EnumChatFormatting.GREEN;
        }
        if (this.isCloseToColor(r, g, b, 75, 97, 130, 20)) {
            return EnumChatFormatting.DARK_BLUE;
        }
        if (this.isCloseToColor(r, g, b, 206, 135, 95, 20)) {
            return EnumChatFormatting.GOLD;
        }
        if (this.isCloseToColor(r, g, b, 115, 67, 67, 20)) {
            return EnumChatFormatting.DARK_GRAY;
        }
        if (this.isCloseToColor(r, g, b, 115, 11, 0, 20)) {
            return EnumChatFormatting.DARK_RED;
        }
        if (this.isCloseToColor(r, g, b, 150, 108, 84, 10)) {
            return EnumChatFormatting.DARK_GRAY;
        }
        if (this.isCloseToColor(r, g, b, 249, 249, 249, 10)) {
            return EnumChatFormatting.WHITE;
        }
        if (this.isCloseToColor(r, g, b, 105, 115, 105, 20)) {
            return EnumChatFormatting.GRAY;
        }
        if (this.isCloseToColor(r, g, b, 168, 148, 143, 20)) {
            return EnumChatFormatting.GRAY;
        }
        if (this.isCloseToColor(r, g, b, 124, 36, 27, 20)) {
            return EnumChatFormatting.DARK_RED;
        }
        if (this.isCloseToColor(r, g, b, 89, 100, 115, 20)) {
            return EnumChatFormatting.DARK_GRAY;
        }
        if (this.isCloseToColor(r, g, b, 196, 146, 39, 20)) {
            return EnumChatFormatting.GOLD;
        }
        if (this.isCloseToColor(r, g, b, 115, 51, 109, 20)) {
            return EnumChatFormatting.LIGHT_PURPLE;
        }
        if (this.isCloseToColor(r, g, b, 181, 27, 27, 20)) {
            return EnumChatFormatting.RED;
        }
        if (this.isCloseToColor(r, g, b, 93, 145, 204, 30)) {
            return EnumChatFormatting.BLUE;
        }
        if (this.isCloseToColor(r, g, b, 198, 229, 255, 140)) {
            return EnumChatFormatting.AQUA;
        }
        if (this.isCloseToColor(r, g, b, 217, 176, 90, 20)) {
            return EnumChatFormatting.GOLD;
        }
        if (r > 200 && g < 100 && b < 100) {
            return EnumChatFormatting.RED;
        }
        if (r < 100 && g > 200 && b < 100) {
            return EnumChatFormatting.GREEN;
        }
        if (r < 100 && g < 100 && b > 200) {
            return EnumChatFormatting.BLUE;
        }
        if (r > 200 && g > 200 && b < 100) {
            return EnumChatFormatting.YELLOW;
        }
        if (r < 100 && g > 200 && b > 200) {
            return EnumChatFormatting.AQUA;
        }
        if (r > 200 && g < 100 && b > 200) {
            return EnumChatFormatting.LIGHT_PURPLE;
        }
        if (r > 200 && g > 200 && b > 200) {
            return EnumChatFormatting.WHITE;
        }
        if (r < 100 && g < 100 && b < 100) {
            return EnumChatFormatting.DARK_GRAY;
        }
        return EnumChatFormatting.GRAY;
    }

    private boolean isCloseToColor(int r1, int g1, int b1, int r2, int g2, int b2, int tolerance) {
        return Math.abs(r1 - r2) < tolerance && Math.abs(g1 - g2) < tolerance && Math.abs(b1 - b2) < tolerance;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (LOTRInvasions type : LOTRInvasions.values()) {
            ItemStack itemstack = new ItemStack(item);
            LOTRItemConquestHorn.setInvasionType(itemstack, type);
            list.add(itemstack);
        }
    }
}

