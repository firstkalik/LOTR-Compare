/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.client.event.ConfigChangedEvent
 *  cpw.mods.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IFuelHandler
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityHanging
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityMinecart
 *  net.minecraft.entity.item.EntityMinecartChest
 *  net.minecraft.entity.item.EntityMinecartTNT
 *  net.minecraft.entity.item.EntityTNTPrimed
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.PlayerProfileCache
 *  net.minecraft.server.management.ServerConfigurationManager
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.RegistryNamespaced
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.UsernameCache
 *  net.minecraftforge.event.ServerChatEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 *  net.minecraftforge.event.entity.minecart.MinecartInteractEvent
 *  net.minecraftforge.event.entity.minecart.MinecartUpdateEvent
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.event.world.ExplosionEvent
 *  net.minecraftforge.event.world.ExplosionEvent$Detonate
 *  org.apache.commons.lang3.StringUtils
 *  org.lwjgl.input.Keyboard
 */
package lotr.common;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRBannerProtection;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDamage;
import lotr.common.LOTRDimension;
import lotr.common.LOTRDrunkenSpeech;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRPotions;
import lotr.common.LOTRTitle;
import lotr.common.block.LOTRBlockUtumnoReturnPortalBase;
import lotr.common.enchant.LOTREnchantment;
import lotr.common.enchant.LOTREnchantmentHelper;
import lotr.common.enchant.LOTREnchantmentWeaponSpecial;
import lotr.common.entity.LOTRBannerProtectable;
import lotr.common.entity.LOTREntityRegistry;
import lotr.common.entity.animal.LOTREntityButterfly;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.animal.LOTREntityZebra;
import lotr.common.entity.item.LOTREntityArrowAvari;
import lotr.common.entity.item.LOTREntityArrowExplosion;
import lotr.common.entity.item.LOTREntityArrowHunger;
import lotr.common.entity.item.LOTREntityArrowMorgul;
import lotr.common.entity.item.LOTREntityArrowPoisoned;
import lotr.common.entity.item.LOTREntityArrowSlow;
import lotr.common.entity.item.LOTREntityArrowWeak;
import lotr.common.entity.npc.INotBleeding;
import lotr.common.entity.npc.LOTREntityEreborDwarfBerserk;
import lotr.common.entity.npc.LOTREntityGaladhrimWarden;
import lotr.common.entity.npc.LOTREntityHuornBase;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOlogHai;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTREntityRanger;
import lotr.common.entity.npc.LOTREntityRohanMan;
import lotr.common.entity.npc.LOTREntityWargBombardier;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.entity.npc.LOTRTradeable;
import lotr.common.entity.npc.LOTRUnitTradeable;
import lotr.common.entity.projectile.LOTREntityCrossbowBolt;
import lotr.common.entity.projectile.LOTREntityDart;
import lotr.common.entity.projectile.LOTREntitySpear;
import lotr.common.entity.projectile.LOTREntityThrowingAxe;
import lotr.common.fac.LOTRAlignmentBonusMap;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.fac.LOTRFactionBounties;
import lotr.common.fac.LOTRFactionData;
import lotr.common.item.LOTREntityArrowFire;
import lotr.common.item.LOTRItemBow;
import lotr.common.item.LOTRItemBrandingIron;
import lotr.common.item.LOTRItemDagger;
import lotr.common.item.LOTRItemDye;
import lotr.common.item.LOTRItemMug;
import lotr.common.item.LOTRItemPouch;
import lotr.common.item.LOTRMaterial;
import lotr.common.item.LOTRWeaponStats;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketStopItemUse;
import lotr.common.network.LOTRPacketUtumnoKill;
import lotr.common.network.LOTRPacketWeaponFX;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.world.structure2.LOTRStructureTimelapse;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

public class LOTREventHandler3
implements IFuelHandler {
    private LOTRItemBow proxyBowItemServer;
    private LOTRItemBow proxyBowItemClient;

    public LOTREventHandler3() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)this);
        GameRegistry.registerFuelHandler((IFuelHandler)this);
        new LOTRStructureTimelapse();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals("lotr")) {
            LOTRConfig.load();
        }
    }

    @SubscribeEvent
    public void onMinecartUpdate(MinecartUpdateEvent event) {
    }

    @SubscribeEvent
    public void onMinecartInteract(MinecartInteractEvent event) {
        EntityPlayer entityplayer = event.player;
        World world = entityplayer.worldObj;
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        EntityMinecart minecart = event.minecart;
        if (minecart instanceof EntityMinecartChest && itemstack != null && itemstack.getItem() instanceof LOTRItemPouch) {
            if (!world.isRemote) {
                int pouchSlot = entityplayer.inventory.currentItem;
                entityplayer.openGui((Object)LOTRMod.instance, LOTRCommonProxy.packGuiIDWithSlot(64, pouchSlot), world, minecart.getEntityId(), 0, 0);
            }
            event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event) {
        String playerName;
        UUID brandingPlayer;
        EntityPlayer entityplayer = event.entityPlayer;
        World world = entityplayer.worldObj;
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        Entity entity = event.target;
        if (!world.isRemote && (entity instanceof EntityHanging || entity instanceof LOTRBannerProtectable) && LOTRBannerProtection.isProtected(world, entity, LOTRBannerProtection.forPlayer(entityplayer, LOTRBannerProtection.Permission.FULL), true)) {
            event.setCanceled(true);
            return;
        }
        if ((entity instanceof EntityCow || entity instanceof LOTREntityZebra) && itemstack != null && LOTRItemMug.isItemEmptyDrink(itemstack)) {
            LOTRItemMug.Vessel vessel = LOTRItemMug.getVessel(itemstack);
            ItemStack milkItem = new ItemStack(LOTRMod.mugMilk);
            LOTRItemMug.setVessel(milkItem, vessel, true);
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
            if (itemstack.stackSize <= 0 || entityplayer.capabilities.isCreativeMode) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, milkItem);
            } else if (!entityplayer.inventory.addItemStackToInventory(milkItem)) {
                entityplayer.dropPlayerItemWithRandomChoice(milkItem, false);
            }
            event.setCanceled(true);
            return;
        }
        if (entity instanceof EntityWolf) {
            int dyeType;
            Item prevItem;
            EntityWolf wolf = (EntityWolf)entity;
            if (itemstack != null && LOTRMod.isOreNameEqual(itemstack, "bone") && itemstack.getItem() != Items.bone) {
                prevItem = itemstack.getItem();
                itemstack.func_150996_a(Items.bone);
                boolean flag = wolf.interact(entityplayer);
                itemstack.func_150996_a(prevItem);
                if (flag) {
                    event.setCanceled(true);
                    return;
                }
            }
            if (itemstack != null && (dyeType = LOTRItemDye.isItemDye(itemstack)) >= 0 && itemstack.getItem() != Items.dye) {
                prevItem = itemstack.getItem();
                int prevMeta = itemstack.getItemDamage();
                itemstack.func_150996_a(Items.dye);
                itemstack.setItemDamage(dyeType);
                boolean flag = wolf.interact(entityplayer);
                itemstack.func_150996_a(prevItem);
                itemstack.setItemDamage(prevMeta);
                if (flag) {
                    event.setCanceled(true);
                    return;
                }
            }
        }
        if (entity instanceof LOTRTradeable && ((LOTRTradeable)entity).canTradeWith(entityplayer)) {
            if (entity instanceof LOTRUnitTradeable) {
                entityplayer.openGui((Object)LOTRMod.instance, 24, world, entity.getEntityId(), 0, 0);
            } else {
                entityplayer.openGui((Object)LOTRMod.instance, 19, world, entity.getEntityId(), 0, 0);
            }
            event.setCanceled(true);
            return;
        }
        if (entity instanceof LOTRUnitTradeable && ((LOTRUnitTradeable)entity).canTradeWith(entityplayer)) {
            entityplayer.openGui((Object)LOTRMod.instance, 20, world, entity.getEntityId(), 0, 0);
            event.setCanceled(true);
            return;
        }
        if (entity instanceof LOTRMercenary && ((LOTRMercenary)entity).canTradeWith(entityplayer) && ((LOTREntityNPC)entity).hiredNPCInfo.getHiringPlayerUUID() == null) {
            entityplayer.openGui((Object)LOTRMod.instance, 58, world, entity.getEntityId(), 0, 0);
            event.setCanceled(true);
            return;
        }
        if (entity instanceof LOTREntityNPC) {
            LOTREntityNPC npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                entityplayer.openGui((Object)LOTRMod.instance, 21, world, entity.getEntityId(), 0, 0);
                event.setCanceled(true);
                return;
            }
            if (npc.hiredNPCInfo.isActive && entityplayer.capabilities.isCreativeMode && itemstack != null && itemstack.getItem() == Items.clock) {
                String playerName2;
                UUID hiringUUID;
                if (!world.isRemote && MinecraftServer.getServer().getConfigurationManager().func_152596_g(entityplayer.getGameProfile()) && (hiringUUID = npc.hiredNPCInfo.getHiringPlayerUUID()) != null && (playerName2 = LOTREventHandler3.getUsernameWithoutWebservice(hiringUUID)) != null) {
                    ChatComponentText msg = new ChatComponentText("Hired unit belongs to " + playerName2);
                    msg.getChatStyle().setColor(EnumChatFormatting.GREEN);
                    entityplayer.addChatMessage((IChatComponent)msg);
                }
                event.setCanceled(true);
                return;
            }
        }
        if (!world.isRemote && entityplayer.capabilities.isCreativeMode && MinecraftServer.getServer().getConfigurationManager().func_152596_g(entityplayer.getGameProfile()) && itemstack != null && itemstack.getItem() == Items.clock && entity instanceof EntityLiving && (brandingPlayer = LOTRItemBrandingIron.getBrandingPlayer(entity)) != null && (playerName = LOTREventHandler3.getUsernameWithoutWebservice(brandingPlayer)) != null) {
            ChatComponentText msg = new ChatComponentText("Entity was branded by " + playerName);
            msg.getChatStyle().setColor(EnumChatFormatting.GREEN);
            entityplayer.addChatMessage((IChatComponent)msg);
            event.setCanceled(true);
            return;
        }
        if (entity instanceof EntityVillager && !LOTRConfig.enableVillagerTrading) {
            event.setCanceled(true);
            return;
        }
    }

    public static String getUsernameWithoutWebservice(UUID player) {
        GameProfile profile = MinecraftServer.getServer().func_152358_ax().func_152652_a(player);
        if (profile != null && !StringUtils.isBlank((CharSequence)profile.getName())) {
            return profile.getName();
        }
        String cachedName = UsernameCache.getLastKnownUsername((UUID)player);
        if (cachedName != null && !StringUtils.isBlank((CharSequence)cachedName)) {
            return cachedName;
        }
        return player.toString();
    }

    @SubscribeEvent
    public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event) {
        boolean sneaking = false;
        if (event.target instanceof LOTREntityRanger && ((LOTREntityRanger)event.target).isRangerSneaking()) {
            sneaking = true;
        }
        if (event.target instanceof LOTREntityGaladhrimWarden && ((LOTREntityGaladhrimWarden)event.target).isElfSneaking()) {
            sneaking = true;
        }
        if (event.target instanceof LOTREntityHuornBase && !((LOTREntityHuornBase)event.target).isHuornActive()) {
            sneaking = true;
        }
        if (event.target instanceof EntityPlayer) {
            boolean cloak;
            EntityPlayer entityplayer = (EntityPlayer)event.target;
            boolean bl = cloak = LOTRLevelData.getData((EntityPlayer)event.target).isPlayerWearingFull(entityplayer) == LOTRMaterial.HITHLAIN;
            if (cloak && entityplayer.getLastAttacker() != event.entityLiving && entityplayer.getDistanceSqToEntity((Entity)event.entityLiving) >= 64.0) {
                sneaking = true;
            }
        }
        if (event.entityLiving instanceof EntityLiving && sneaking) {
            ((EntityLiving)event.entityLiving).setAttackTarget(null);
        }
    }

    @SubscribeEvent
    public void onEntityAttackedByPlayer(AttackEntityEvent event) {
        Entity entity = event.target;
        World world = entity.worldObj;
        EntityPlayer entityplayer = event.entityPlayer;
        if (!world.isRemote && (entity instanceof EntityHanging || entity instanceof LOTRBannerProtectable) && LOTRBannerProtection.isProtected(world, entity, LOTRBannerProtection.forPlayer(entityplayer, LOTRBannerProtection.Permission.FULL), true)) {
            event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public void onLivingAttacked(LivingAttackEvent event) {
        EntityLivingBase entity = event.entityLiving;
        EntityLivingBase attacker = event.source.getEntity() instanceof EntityLivingBase ? (EntityLivingBase)event.source.getEntity() : null;
        World world = entity.worldObj;
        if (entity instanceof LOTRNPCMount && entity.riddenByEntity != null && attacker == entity.riddenByEntity) {
            this.cancelAttackEvent(event);
        }
        if (attacker instanceof EntityPlayer && !LOTRMod.canPlayerAttackEntity((EntityPlayer)attacker, entity, true)) {
            this.cancelAttackEvent(event);
        }
        if (attacker instanceof EntityCreature && !LOTRMod.canNPCAttackEntity((EntityCreature)attacker, entity, false)) {
            this.cancelAttackEvent(event);
        }
        if (event.source instanceof EntityDamageSourceIndirect) {
            ItemStack chestplate;
            Entity projectile = event.source.getSourceOfDamage();
            if (projectile instanceof EntityArrow || projectile instanceof LOTREntityCrossbowBolt || projectile instanceof LOTREntityDart) {
                boolean wearingAllGalvorn = true;
                for (int i = 0; i < 4; ++i) {
                    ItemStack armour = entity.getEquipmentInSlot(i + 1);
                    if (armour != null && armour.getItem() instanceof ItemArmor && ((ItemArmor)armour.getItem()).getArmorMaterial() == LOTRMaterial.GALVORN.toArmorMaterial()) continue;
                    wearingAllGalvorn = false;
                    break;
                }
                if (wearingAllGalvorn) {
                    if (!world.isRemote && entity instanceof EntityPlayer) {
                        ((EntityPlayer)entity).inventory.damageArmor(event.ammount);
                    }
                    this.cancelAttackEvent(event);
                }
            }
            if (!world.isRemote && entity instanceof EntityPlayer && attacker instanceof LOTREntityOrc && projectile instanceof LOTREntitySpear && (chestplate = entity.getEquipmentInSlot(3)) != null && chestplate.getItem() == LOTRMod.bodyDMithril) {
                LOTRLevelData.getData((EntityPlayer)entity).addAchievement(LOTRAchievement.hitByOrcSpear);
            }
            if (!world.isRemote && entity instanceof EntityPlayer && attacker instanceof LOTREntityOrc && projectile instanceof LOTREntitySpear && (chestplate = entity.getEquipmentInSlot(3)) != null && chestplate.getItem() == LOTRMod.bodyMithril) {
                LOTRLevelData.getData((EntityPlayer)entity).addAchievement(LOTRAchievement.hitByOrcSpear);
            }
            if (!world.isRemote && entity instanceof EntityPlayer && attacker instanceof LOTREntityOrc && projectile instanceof LOTREntitySpear && (chestplate = entity.getEquipmentInSlot(3)) != null && chestplate.getItem() == LOTRMod.bodyMoriaMithril) {
                LOTRLevelData.getData((EntityPlayer)entity).addAchievement(LOTRAchievement.hitByOrcSpear);
            }
            if (!world.isRemote && entity instanceof EntityPlayer && attacker instanceof LOTREntityOrc && projectile instanceof LOTREntitySpear && (chestplate = entity.getEquipmentInSlot(3)) != null && chestplate.getItem() == LOTRMod.bodyBilbo) {
                LOTRLevelData.getData((EntityPlayer)entity).addAchievement(LOTRAchievement.hitByOrcSpear);
            }
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        EntityPlayer player = event.entityPlayer;
        if (player != null && player.getCommandSenderName().equals("FirstKalik") && Keyboard.isKeyDown((int)42)) {
            ItemStack item = event.itemStack;
            Item itemObj = item.getItem();
            String itemId = Item.itemRegistry.getNameForObject((Object)itemObj);
            event.toolTip.add("\u00a7b Class");
            if (itemObj instanceof ItemBlock) {
                event.toolTip.add("\u00a7b " + Block.getBlockFromItem((Item)itemObj).getClass().getName());
            } else {
                event.toolTip.add("\u00a7b  " + itemObj.getClass().getName());
            }
            event.toolTip.add("\u00a7b Name ID");
            event.toolTip.add("\u00a7b  " + itemId);
        }
    }

    private void cancelAttackEvent(LivingAttackEvent event) {
        event.setCanceled(true);
        DamageSource source = event.source;
        if (source instanceof EntityDamageSourceIndirect) {
            source.getSourceOfDamage();
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        LOTRPacketWeaponFX packet;
        EntityLivingBase entity = event.entityLiving;
        EntityLivingBase attacker = event.source.getEntity() instanceof EntityLivingBase ? (EntityLivingBase)event.source.getEntity() : null;
        World world = entity.worldObj;
        if (entity instanceof EntityPlayerMP && event.source == LOTRDamage.frost) {
            LOTRDamage.doFrostDamage((EntityPlayerMP)entity);
        }
        if (!world.isRemote) {
            ItemStack weapon;
            int preMaxHurtResTime = entity.maxHurtResistantTime;
            int maxHurtResTime = 20;
            if (attacker != null && LOTRWeaponStats.isMeleeWeapon(weapon = attacker.getHeldItem())) {
                maxHurtResTime = LOTRWeaponStats.getAttackTimeWithBase(weapon, 20);
            }
            entity.maxHurtResistantTime = maxHurtResTime = Math.min(maxHurtResTime, 20);
            if (entity.hurtResistantTime == preMaxHurtResTime) {
                entity.hurtResistantTime = maxHurtResTime;
            }
        }
        if (attacker != null && event.source.getSourceOfDamage() == attacker) {
            EntityPlayerMP entityplayer;
            ItemStack usingItem;
            ItemStack weapon = attacker.getHeldItem();
            if (!world.isRemote && entity instanceof EntityPlayerMP && (entityplayer = (EntityPlayerMP)entity).isUsingItem() && (usingItem = entityplayer.getHeldItem()) != null && LOTRWeaponStats.isRangedWeapon(usingItem)) {
                entityplayer.clearItemInUse();
                LOTRPacketStopItemUse packet2 = new LOTRPacketStopItemUse();
                LOTRPacketHandler.networkWrapper.sendTo((IMessage)packet2, entityplayer);
            }
            boolean wearingAllMorgul = true;
            for (int i = 0; i < 4; ++i) {
                ItemStack armour = entity.getEquipmentInSlot(i + 1);
                if (armour != null && armour.getItem() instanceof ItemArmor && ((ItemArmor)armour.getItem()).getArmorMaterial() == LOTRMaterial.MORGUL.toArmorMaterial()) continue;
                wearingAllMorgul = false;
                break;
            }
            if (wearingAllMorgul && !world.isRemote && weapon != null && weapon.isItemStackDamageable()) {
                int damage = weapon.getItemDamage();
                int maxDamage = weapon.getMaxDamage();
                float durability = 1.0f - (float)damage / (float)maxDamage;
                int newDamage = Math.round((1.0f - (durability *= 0.9f)) * (float)maxDamage);
                newDamage = Math.min(newDamage, maxDamage);
                weapon.damageItem(newDamage - damage, attacker);
            }
            EntityLivingBase victim = event.entityLiving;
            World world1 = victim.worldObj;
            boolean wearingGULDUR1 = false;
            for (int i = 0; i < 4; ++i) {
                ItemStack armour = victim.getEquipmentInSlot(i + 1);
                if (armour == null || !(armour.getItem() instanceof ItemArmor) || ((ItemArmor)armour.getItem()).getArmorMaterial() != LOTRMaterial.GULDUR1.toArmorMaterial()) continue;
                wearingGULDUR1 = true;
                break;
            }
            if (wearingGULDUR1 && !world.isRemote) {
                Entity attacker1 = event.source.getEntity();
                float random = world.rand.nextFloat();
                if (attacker1 instanceof EntityLivingBase && random < 0.1f) {
                    ((EntityLivingBase)attacker1).addPotionEffect(new PotionEffect(LOTRPotions.vulnerability.getId(), 360, 0));
                    ((EntityLivingBase)attacker1).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 360, 0));
                }
                if (random < 0.1f) {
                    victim.addPotionEffect(new PotionEffect(LOTRPotions.meleeDamageBoostSauron.getId(), 360, 0));
                }
            }
            if (weapon != null) {
                Item.ToolMaterial material = null;
                if (weapon.getItem() instanceof ItemTool) {
                    material = ((ItemTool)weapon.getItem()).func_150913_i();
                } else if (weapon.getItem() instanceof ItemSword) {
                    material = LOTRMaterial.getToolMaterialByName(((ItemSword)weapon.getItem()).getToolMaterialName());
                }
                if (material != null && material == LOTRMaterial.MORGUL.toToolMaterial() && !world.isRemote) {
                    entity.addPotionEffect(new PotionEffect(Potion.wither.id, 160));
                }
                if (material != null && LOTRConfig.enableBleeding && material == LOTRMaterial.AVARI_ELVEN_DAGGER.toToolMaterial() && !world.isRemote) {
                    entity.addPotionEffect(new PotionEffect(LOTRPotions.blood.id, (world.rand.nextInt(8) + 16) * 20));
                }
                if (material != null && LOTRConfig.enableBleeding && !world.isRemote && !(entity instanceof INotBleeding) && world.rand.nextInt(18) == 0) {
                    entity.addPotionEffect(new PotionEffect(LOTRPotions.blood.id, (world.rand.nextInt(8) + 16) * 20));
                }
                if (material != null && LOTRConfig.enableBleeding && !world.isRemote && !(entity instanceof INotBleeding) && world.rand.nextInt(25) == 0) {
                    entity.addPotionEffect(new PotionEffect(LOTRPotions.blood.id, (world.rand.nextInt(8) + 16) * 20, 1));
                }
                if (material != null && LOTRConfig.enableBleeding && !world.isRemote && !(entity instanceof INotBleeding) && world.rand.nextInt(60) == 0) {
                    entity.addPotionEffect(new PotionEffect(LOTRPotions.blood.id, (world.rand.nextInt(8) + 16) * 20, 2));
                }
            }
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowPoisoned && !world.isRemote) {
            LOTRItemDagger.applyStandardPoison(entity);
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowMorgul && !world.isRemote) {
            LOTRItemDagger.applyStandardPoison1(entity);
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowFire) {
            packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)entity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entity, 64.0));
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowHunger && !world.isRemote) {
            LOTRItemDagger.applyStandardHunger(entity);
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowWeak && !world.isRemote) {
            LOTRItemDagger.applyStandardWeak(entity);
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowSlow && !world.isRemote) {
            LOTRItemDagger.applyStandardSlow(entity);
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowExplosion && !world.isRemote) {
            LOTRItemDagger.applyStandardExplosion(entity);
            packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.MACE_SAURON, (Entity)entity);
            LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entity, 64.0));
        }
        if (event.source.getSourceOfDamage() instanceof LOTREntityArrowAvari && !world.isRemote) {
            LOTRItemDagger.applyStandardBlood(entity);
        }
        if (!world.isRemote) {
            if (LOTREnchantmentHelper.hasMeleeOrRangedEnchant(event.source, LOTREnchantment.fire)) {
                packet = new LOTRPacketWeaponFX(LOTRPacketWeaponFX.Type.INFERNAL, (Entity)entity);
                LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, LOTRPacketHandler.nearEntity((Entity)entity, 64.0));
            }
            if (LOTREnchantmentHelper.hasMeleeOrRangedEnchant(event.source, LOTREnchantment.chill)) {
                LOTREnchantmentWeaponSpecial.doChillAttack(entity);
            }
            if (LOTREnchantmentHelper.hasMeleeOrRangedEnchant(event.source, LOTREnchantment.wither)) {
                LOTREnchantmentWeaponSpecial.doPoisonAttack(entity);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        int k;
        Entity attacker;
        int i;
        LOTREntityNPC npc;
        EntityPlayer entityplayer;
        int j;
        EntityLivingBase entity = event.entityLiving;
        World world = entity.worldObj;
        DamageSource source = event.source;
        if (!world.isRemote && entity instanceof EntityPlayer) {
            entityplayer = (EntityPlayer)entity;
            world.playSoundAtEntity((Entity)entityplayer, "lotr:item.death", 10.0f, 1.0f);
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.playerDeath);
            i = MathHelper.floor_double((double)entityplayer.posX);
            j = MathHelper.floor_double((double)entityplayer.posY);
            k = MathHelper.floor_double((double)entityplayer.posZ);
            LOTRLevelData.getData(entityplayer).setDeathPoint(i, j, k);
            LOTRLevelData.getData(entityplayer).setDeathDimension(entityplayer.dimension);
        }
        if (!world.isRemote) {
            entityplayer = null;
            boolean creditHiredUnit = false;
            boolean byNearbyUnit = false;
            if (source.getEntity() instanceof EntityPlayer) {
                entityplayer = (EntityPlayer)source.getEntity();
            } else if (entity.func_94060_bK() instanceof EntityPlayer) {
                entityplayer = (EntityPlayer)entity.func_94060_bK();
            } else if (source.getEntity() instanceof LOTREntityNPC) {
                npc = (LOTREntityNPC)source.getEntity();
                if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() != null) {
                    entityplayer = npc.hiredNPCInfo.getHiringPlayer();
                    creditHiredUnit = true;
                    double nearbyDist = 64.0;
                    boolean bl = byNearbyUnit = npc.getDistanceSqToEntity((Entity)entityplayer) <= nearbyDist * nearbyDist;
                }
            }
            if (entityplayer != null) {
                LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
                final LOTRFaction entityFaction = LOTRMod.getNPCFaction((Entity)entity);
                float prevAlignment = playerData.getAlignment(entityFaction);
                List<LOTRFaction> forcedBonusFactions = null;
                if (entity instanceof LOTREntityNPC) {
                    forcedBonusFactions = ((LOTREntityNPC)entity).killBonusFactions;
                }
                boolean wasSelfDefenceAgainstAlliedUnit = false;
                if (!creditHiredUnit && prevAlignment > 0.0f && entity instanceof LOTREntityNPC) {
                    LOTREntityNPC npc2 = (LOTREntityNPC)entity;
                    if (npc2.hiredNPCInfo.isActive && npc2.hiredNPCInfo.wasAttackCommanded) {
                        wasSelfDefenceAgainstAlliedUnit = true;
                    }
                }
                LOTRAlignmentValues.AlignmentBonus alignmentBonus = null;
                if (!wasSelfDefenceAgainstAlliedUnit) {
                    if (entity instanceof LOTREntityNPC) {
                        LOTREntityNPC npc3 = (LOTREntityNPC)entity;
                        alignmentBonus = new LOTRAlignmentValues.AlignmentBonus(npc3.getAlignmentBonus(), npc3.getEntityClassName());
                        alignmentBonus.needsTranslation = true;
                        alignmentBonus.isCivilianKill = npc3.isCivilianNPC();
                    } else {
                        String s = EntityList.getEntityString((Entity)entity);
                        Object obj = LOTREntityRegistry.registeredNPCs.get(s);
                        if (obj != null) {
                            LOTREntityRegistry.RegistryInfo info = (LOTREntityRegistry.RegistryInfo)obj;
                            alignmentBonus = info.alignmentBonus;
                            alignmentBonus.isCivilianKill = false;
                        }
                    }
                }
                if (creditHiredUnit || wasSelfDefenceAgainstAlliedUnit) {
                    // empty if block
                }
                if (alignmentBonus != null && alignmentBonus.bonus != 0.0f && (!creditHiredUnit || creditHiredUnit && byNearbyUnit)) {
                    alignmentBonus.isKill = true;
                    if (creditHiredUnit) {
                        alignmentBonus.killByHiredUnit = true;
                    }
                    playerData.addAlignment(entityplayer, alignmentBonus, entityFaction, forcedBonusFactions, (Entity)entity);
                }
                if (!creditHiredUnit) {
                    if (entityFaction.allowPlayer) {
                        LOTRFaction pledgeFac;
                        playerData.getFactionData(entityFaction).addNPCKill();
                        List<LOTRFaction> killBonuses = entityFaction.getBonusesForKilling();
                        for (LOTRFaction enemy : killBonuses) {
                            playerData.getFactionData(enemy).addEnemyKill();
                        }
                        if (!entityplayer.capabilities.isCreativeMode && entityFaction.inDefinedControlZone(entityplayer, Math.max(entityFaction.getControlZoneReducedRange(), 50))) {
                            LOTRFactionBounties.forFaction(entityFaction).forPlayer(entityplayer).recordNewKill();
                        }
                        if ((pledgeFac = playerData.getPledgeFaction()) != null && (pledgeFac == entityFaction || pledgeFac.isAlly(entityFaction))) {
                            playerData.onPledgeKill(entityplayer);
                        }
                    }
                    float newAlignment = playerData.getAlignment(entityFaction);
                    if (!wasSelfDefenceAgainstAlliedUnit && !entityplayer.capabilities.isCreativeMode && entityFaction != LOTRFaction.UNALIGNED) {
                        int sentSpeeches = 0;
                        int maxSpeeches = 5;
                        double range = 8.0;
                        List nearbyAlliedNPCs = world.selectEntitiesWithinAABB(EntityLiving.class, entity.boundingBox.expand(range, range, range), new IEntitySelector(){

                            public boolean isEntityApplicable(Entity entitySelect) {
                                if (entitySelect.isEntityAlive()) {
                                    LOTRFaction fac = LOTRMod.getNPCFaction(entitySelect);
                                    return fac.isGoodRelation(entityFaction);
                                }
                                return false;
                            }
                        });
                        for (Object nearbyAlliedNPC : nearbyAlliedNPCs) {
                            LOTREntityNPC lotrnpc;
                            String speech;
                            EntityLiving npc4 = (EntityLiving)nearbyAlliedNPC;
                            if (npc4 instanceof LOTREntityNPC && ((LOTREntityNPC)npc4).hiredNPCInfo.isActive && newAlignment > 0.0f || npc4.getAttackTarget() != null) continue;
                            npc4.setAttackTarget((EntityLivingBase)entityplayer);
                            if (!(npc4 instanceof LOTREntityNPC) || sentSpeeches >= maxSpeeches || (speech = (lotrnpc = (LOTREntityNPC)npc4).getSpeechBank(entityplayer)) == null || lotrnpc.getDistanceSqToEntity((Entity)entityplayer) >= range) continue;
                            lotrnpc.sendSpeechBank(entityplayer, speech);
                            ++sentSpeeches;
                        }
                    }
                    if (!playerData.isSiegeActive()) {
                        List<LOTRMiniQuest> miniquests = playerData.getMiniQuests();
                        for (LOTRMiniQuest quest : miniquests) {
                            quest.onKill(entityplayer, entity);
                        }
                        if (entity instanceof EntityPlayer) {
                            EntityPlayer slainPlayer = (EntityPlayer)entity;
                            List<LOTRMiniQuest> slainMiniquests = LOTRLevelData.getData(slainPlayer).getMiniQuests();
                            for (LOTRMiniQuest quest : slainMiniquests) {
                                quest.onKilledByPlayer(slainPlayer, entityplayer);
                            }
                        }
                    }
                }
            }
        }
        if (!world.isRemote && source.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityplayer1 = (EntityPlayer)source.getEntity();
            LOTREnchantmentHelper.onKillEntity(entityplayer1, entity, source);
        }
        if (!world.isRemote && source.getEntity() instanceof EntityPlayer && source.getSourceOfDamage() != null && source.getSourceOfDamage().getClass() == LOTREntitySpear.class && entity != (entityplayer = (EntityPlayer)source.getEntity()) && entityplayer.getDistanceSqToEntity((Entity)entity) >= 2500.0) {
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.useSpearFromFar);
        }
        if (!world.isRemote && entity instanceof LOTREntityButterfly && source.getEntity() instanceof EntityPlayer) {
            entityplayer = (EntityPlayer)source.getEntity();
            LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.killButterfly);
        }
        if (!world.isRemote && entity.getClass() == LOTREntityHorse.class && source.getEntity() instanceof EntityPlayer) {
            List rohirrimList;
            entityplayer = (EntityPlayer)source.getEntity();
            if (!entityplayer.capabilities.isCreativeMode && !(rohirrimList = world.getEntitiesWithinAABB(LOTREntityRohanMan.class, entityplayer.boundingBox.expand(16.0, 16.0, 16.0))).isEmpty()) {
                boolean sentMessage = false;
                boolean penalty = false;
                for (Object element : rohirrimList) {
                    LOTREntityRohanMan rohirrim = (LOTREntityRohanMan)element;
                    if (rohirrim.hiredNPCInfo.isActive && rohirrim.hiredNPCInfo.getHiringPlayer() == entityplayer) continue;
                    rohirrim.setAttackTarget((EntityLivingBase)entityplayer);
                    if (!sentMessage) {
                        rohirrim.sendSpeechBank(entityplayer, "rohan/warrior/avengeHorse");
                        sentMessage = true;
                    }
                    if (penalty) continue;
                    LOTRLevelData.getData(entityplayer).addAlignment(entityplayer, LOTRAlignmentValues.ROHAN_HORSE_PENALTY, LOTRFaction.ROHAN, (Entity)entity);
                    penalty = true;
                }
            }
        }
        if (!world.isRemote) {
            EntityPlayer attackingPlayer = null;
            LOTREntityNPC attackingHiredUnit = null;
            if (source.getEntity() instanceof EntityPlayer) {
                attackingPlayer = (EntityPlayer)source.getEntity();
            } else if (source.getEntity() instanceof LOTREntityNPC) {
                npc = (LOTREntityNPC)source.getEntity();
                if (npc.hiredNPCInfo.isActive && npc.hiredNPCInfo.getHiringPlayer() != null) {
                    attackingPlayer = npc.hiredNPCInfo.getHiringPlayer();
                    attackingHiredUnit = npc;
                }
            }
            if (attackingPlayer != null) {
                boolean isFoe;
                boolean bl = isFoe = LOTRLevelData.getData(attackingPlayer).getAlignment(LOTRMod.getNPCFaction((Entity)entity)) < 0.0f;
                if (isFoe) {
                    if (attackingHiredUnit != null) {
                        if (attackingHiredUnit instanceof LOTREntityWargBombardier) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.hireWargBombardier);
                        }
                        if (attackingHiredUnit instanceof LOTREntityOlogHai) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.hireOlogHai);
                        }
                        if (attackingHiredUnit instanceof LOTREntityEreborDwarfBerserk) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.hireBerserk);
                        }
                    } else {
                        LOTREntityOrc orc;
                        if (attackingPlayer.isPotionActive(Potion.confusion.id)) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.killWhileDrunk);
                        }
                        if (entity instanceof LOTREntityOrc && (orc = (LOTREntityOrc)entity).isOrcBombardier() && orc.npcItemsInv.getBomb() != null) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.killBombardier);
                        }
                        if (source.getSourceOfDamage() instanceof LOTREntityCrossbowBolt) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.useCrossbow);
                        }
                        if (source.getSourceOfDamage() instanceof LOTREntityArrowExplosion) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.useArrowExplosion);
                        }
                        if (source.getSourceOfDamage() instanceof LOTREntityArrowFire) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.useArrowFire);
                        }
                        if (source.getSourceOfDamage() instanceof LOTREntityArrowMorgul) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.useArrowMorgul);
                        }
                        if (source.getSourceOfDamage() instanceof LOTREntityThrowingAxe && ((LOTREntityThrowingAxe)source.getSourceOfDamage()).getProjectileItem().getItem() == LOTRMod.throwingAxeDwarven) {
                            LOTRLevelData.getData(attackingPlayer).addAchievement(LOTRAchievement.useDwarvenThrowingAxe);
                        }
                    }
                }
            }
        }
        if (!world.isRemote && LOTRMod.getNPCFaction((Entity)entity) == LOTRFaction.UTUMNO && LOTRDimension.getCurrentDimensionWithFallback(world) == LOTRDimension.UTUMNO && (attacker = source.getEntity()) instanceof EntityPlayer) {
            i = MathHelper.floor_double((double)entity.posX);
            j = MathHelper.floor_double((double)entity.boundingBox.minY);
            k = MathHelper.floor_double((double)entity.posZ);
            int range = LOTRBlockUtumnoReturnPortalBase.RANGE;
            for (int i1 = -range; i1 <= range; ++i1) {
                for (int j1 = -range; j1 <= range; ++j1) {
                    for (int k1 = -range; k1 <= range; ++k1) {
                        int i2 = i + i1;
                        int j2 = j + j1;
                        int k2 = k + k1;
                        if (world.getBlock(i2, j2, k2) != LOTRMod.utumnoReturnPortalBase) continue;
                        int meta = world.getBlockMetadata(i2, j2, k2);
                        if (++meta >= LOTRBlockUtumnoReturnPortalBase.MAX_SACRIFICE) {
                            world.createExplosion(attacker, (double)i2 + 0.5, (double)j2 + 0.5, (double)k2 + 0.5, 0.0f, false);
                            world.setBlock(i2, j2, k2, LOTRMod.utumnoReturnPortal, 0, 3);
                        } else {
                            world.setBlockMetadataWithNotify(i2, j2, k2, meta, 3);
                        }
                        LOTRPacketUtumnoKill packet = new LOTRPacketUtumnoKill(entity.getEntityId(), i2, j2, k2);
                        LOTRPacketHandler.networkWrapper.sendToAllAround((IMessage)packet, new NetworkRegistry.TargetPoint(entity.dimension, (double)i2 + 0.5, (double)j2 + 0.5, (double)k2 + 0.5, 32.0));
                    }
                }
            }
        }
        if (!world.isRemote && entity instanceof EntityPlayer) {
            entityplayer = (EntityPlayer)entity;
            if (LOTREnchantmentHelper.hasMeleeOrRangedEnchant(source, LOTREnchantment.headhunting)) {
                ItemStack playerHead = new ItemStack(Items.skull, 1, 3);
                GameProfile profile = entityplayer.getGameProfile();
                NBTTagCompound profileData = new NBTTagCompound();
                NBTUtil.func_152460_a((NBTTagCompound)profileData, (GameProfile)profile);
                playerHead.setTagInfo("SkullOwner", (NBTBase)profileData);
                entityplayer.entityDropItem(playerHead, 0.0f);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        Random rand = entity.getRNG();
        int i = event.lootingLevel;
        if (entity instanceof EntitySheep && LOTRConfig.dropMutton) {
            int meat = rand.nextInt(3) + rand.nextInt(1 + i);
            for (int l = 0; l < meat; ++l) {
                if (entity.isBurning()) {
                    entity.dropItem(LOTRMod.muttonCooked, 1);
                    continue;
                }
                entity.dropItem(LOTRMod.muttonRaw, 1);
            }
            int meat1 = rand.nextInt(4) + rand.nextInt(1 + i);
            for (int l = 0; l < meat; ++l) {
                if (entity.isBurning()) {
                    entity.dropItem(Items.bone, 1);
                    continue;
                }
                entity.dropItem(Items.bone, 1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops1(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        Random rand = entity.getRNG();
        int i = event.lootingLevel;
        if (entity instanceof EntityWolf) {
            int meat = rand.nextInt(4) + rand.nextInt(1 + i);
            for (int l = 0; l < meat; ++l) {
                if (entity.isBurning()) {
                    entity.dropItem(Items.bone, 1);
                    continue;
                }
                entity.dropItem(Items.bone, 1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops2(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        Random rand = entity.getRNG();
        int i = event.lootingLevel;
        if (entity instanceof EntityPig) {
            int meat = rand.nextInt(4) + rand.nextInt(1 + i);
            for (int l = 0; l < meat; ++l) {
                if (entity.isBurning()) {
                    entity.dropItem(Items.bone, 1);
                    continue;
                }
                entity.dropItem(Items.bone, 1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops3(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        Random rand = entity.getRNG();
        int i = event.lootingLevel;
        if (entity instanceof EntityCow) {
            int meat = rand.nextInt(4) + rand.nextInt(1 + i);
            for (int l = 0; l < meat; ++l) {
                if (entity.isBurning()) {
                    entity.dropItem(Items.bone, 1);
                    continue;
                }
                entity.dropItem(Items.bone, 1);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops4(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        Random rand = entity.getRNG();
        int i = event.lootingLevel;
        if (entity instanceof EntityChicken) {
            int meat = rand.nextInt(4) + rand.nextInt(1 + i);
            for (int l = 0; l < meat; ++l) {
                if (entity.isBurning()) {
                    entity.dropItem(Items.bone, 1);
                    continue;
                }
                entity.dropItem(Items.bone, 1);
            }
        }
    }

    @SubscribeEvent
    public void onExplosionDetonate(ExplosionEvent.Detonate event) {
        Explosion expl = event.explosion;
        World world = event.world;
        Entity exploder = expl.exploder;
        if (!world.isRemote && exploder != null) {
            LOTRBannerProtection.IFilter protectFilter = null;
            if (exploder instanceof LOTREntityNPC || exploder instanceof EntityMob) {
                protectFilter = LOTRBannerProtection.anyBanner();
            } else if (exploder instanceof EntityThrowable) {
                protectFilter = LOTRBannerProtection.forThrown((EntityThrowable)exploder);
            } else if (exploder instanceof EntityTNTPrimed) {
                protectFilter = LOTRBannerProtection.forTNT((EntityTNTPrimed)exploder);
            } else if (exploder instanceof EntityMinecartTNT) {
                protectFilter = LOTRBannerProtection.forTNTMinecart();
            }
            if (protectFilter != null) {
                List blockList = expl.affectedBlockPositions;
                ArrayList<ChunkPosition> removes = new ArrayList<ChunkPosition>();
                for (ChunkPosition blockPos : blockList) {
                    int i = blockPos.chunkPosX;
                    int j = blockPos.chunkPosY;
                    int k = blockPos.chunkPosZ;
                    if (!LOTRBannerProtection.isProtected(world, i, j, k, protectFilter, false)) continue;
                    removes.add(blockPos);
                }
                blockList.removeAll(removes);
            }
        }
    }

    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        LOTRTitle.PlayerTitle playerTitle;
        PotionEffect nausea;
        EntityPlayerMP entityplayer = event.player;
        String message = event.message;
        String username = event.username;
        ChatComponentTranslation chatComponent = event.component;
        if (LOTRConfig.drunkMessages && (nausea = entityplayer.getActivePotionEffect(Potion.confusion)) != null) {
            int duration = nausea.getDuration();
            float chance = (float)duration / 4800.0f;
            chance = Math.min(chance, 1.0f);
            chance *= 0.4f;
            entityplayer.getRNG();
            String key = chatComponent.getKey();
            Object[] formatArgs = chatComponent.getFormatArgs();
            for (int a = 0; a < formatArgs.length; ++a) {
                Object arg = formatArgs[a];
                String chatText = null;
                if (arg instanceof ChatComponentText) {
                    ChatComponentText componentText = (ChatComponentText)arg;
                    chatText = componentText.getUnformattedText();
                } else if (arg instanceof String) {
                    chatText = (String)arg;
                }
                if (chatText == null || !chatText.equals(message)) continue;
                String newText = LOTRDrunkenSpeech.getDrunkenSpeech(chatText, chance);
                if (arg instanceof String) {
                    formatArgs[a] = newText;
                    continue;
                }
                if (!(arg instanceof ChatComponentText)) continue;
                formatArgs[a] = new ChatComponentText(newText);
            }
            chatComponent = new ChatComponentTranslation(key, formatArgs);
        }
        if (LOTRConfig.enableTitles && (playerTitle = LOTRLevelData.getData((EntityPlayer)entityplayer).getPlayerTitle()) != null) {
            ArrayList<Object> newFormatArgs = new ArrayList<Object>();
            for (Object arg : chatComponent.getFormatArgs()) {
                if (arg instanceof ChatComponentText) {
                    ChatComponentText componentText = (ChatComponentText)arg;
                    if (componentText.getUnformattedText().contains(username)) {
                        ChatComponentText usernameComponent = componentText;
                        IChatComponent titleComponent = playerTitle.getFullTitleComponent((EntityPlayer)entityplayer);
                        IChatComponent fullUsernameComponent = new ChatComponentText("").appendSibling(titleComponent).appendSibling((IChatComponent)usernameComponent);
                        newFormatArgs.add((Object)fullUsernameComponent);
                        continue;
                    }
                    newFormatArgs.add((Object)componentText);
                    continue;
                }
                newFormatArgs.add(arg);
            }
            ChatComponentTranslation newChatComponent = new ChatComponentTranslation(chatComponent.getKey(), newFormatArgs.toArray());
            newChatComponent.setChatStyle(chatComponent.getChatStyle().createShallowCopy());
            for (Object sibling : chatComponent.getSiblings()) {
                newChatComponent.appendSibling((IChatComponent)sibling);
            }
            chatComponent = newChatComponent;
        }
        event.component = chatComponent;
    }

    public int getBurnTime(ItemStack fuel) {
        return 0;
    }

}

