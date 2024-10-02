/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  cpw.mods.fml.client.GuiModList
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.ModMetadata
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiDownloadTerrain
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.client.gui.GuiOptionButton
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.GuiRepair
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.renderer.InventoryEffectRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.GameSettings$Options
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.event.HoverEvent
 *  net.minecraft.event.HoverEvent$Action
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCraftResult
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.WorldProvider
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package lotr.client;

import com.google.common.collect.Lists;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotr.client.LOTRReflectionClient;
import lotr.client.gui.LOTRGuiAchievementHoverEvent;
import lotr.client.gui.LOTRGuiAnvil;
import lotr.client.gui.LOTRGuiBarrel;
import lotr.client.gui.LOTRGuiButtonLock;
import lotr.client.gui.LOTRGuiButtonRestockPouch;
import lotr.client.gui.LOTRGuiChestWithPouch;
import lotr.client.gui.LOTRGuiDownloadTerrain;
import lotr.client.gui.LOTRGuiMainMenu;
import lotr.client.gui.LOTRGuiPouch;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRChatEvents;
import lotr.common.LOTRConfig;
import lotr.common.LOTRDimension;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRModInfo;
import lotr.common.entity.npc.LOTREntityNPCRideable;
import lotr.common.inventory.LOTRContainerCoinExchange;
import lotr.common.item.LOTRItemCoin;
import lotr.common.network.LOTRPacketHandler;
import lotr.common.network.LOTRPacketMountOpenInv;
import lotr.common.network.LOTRPacketRestockPouches;
import lotr.common.world.LOTRWorldProvider;
import lotr.compatibility.LOTRModChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class LOTRGuiHandler {
    private static RenderItem itemRenderer = new RenderItem();
    public static boolean coinCountLeftSide = false;
    public static final Set<Class<? extends Container>> coinCount_excludedContainers = new HashSet<Class<? extends Container>>();
    public static final Set<Class<? extends GuiContainer>> coinCount_excludedGUIs = new HashSet<Class<? extends GuiContainer>>();
    public static final Set<Class<? extends IInventory>> coinCount_excludedInvTypes = new HashSet<Class<? extends IInventory>>();
    public static final Set<String> coinCount_excludedContainers_clsNames = new HashSet<String>();
    public static final Set<String> coinCount_excludedGUIs_clsNames = new HashSet<String>();
    public static final Set<String> coinCount_excludedInvTypes_clsNames = new HashSet<String>();
    public static final Set<Class<? extends GuiContainer>> pouchRestock_leftPositionGUIs = new HashSet<Class<? extends GuiContainer>>();
    public static final Set<Class<? extends GuiContainer>> pouchRestock_sidePositionGUIs = new HashSet<Class<? extends GuiContainer>>();
    private int descScrollIndex = -1;
    private static LOTRAchievement.Category currentCategory;
    private int currentCategoryTakenCount;
    private int currentCategoryUntakenCount;
    private ArrayList currentCategoryTakenAchievements = new ArrayList();
    private ArrayList currentCategoryUntakenAchievements = new ArrayList();

    public LOTRGuiHandler() {
        FMLCommonHandler.instance().bus().register((Object)this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        Object gui = event.gui;
        if (LOTRConfig.customMainMenu && gui != null && gui.getClass() == GuiMainMenu.class) {
            event.gui = gui = new LOTRGuiMainMenu();
        }
        if (gui != null && gui.getClass() == GuiDownloadTerrain.class) {
            Minecraft mc = Minecraft.getMinecraft();
            WorldProvider provider = mc.theWorld.provider;
            if (provider instanceof LOTRWorldProvider) {
                event.gui = gui = new LOTRGuiDownloadTerrain(mc.thePlayer.sendQueue);
            }
        }
    }

    @SubscribeEvent
    public void preInitGui(GuiScreenEvent.InitGuiEvent.Pre event) {
        GuiScreen gui = event.gui;
        Minecraft mc = Minecraft.getMinecraft();
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        WorldClient world = mc.theWorld;
        if ((gui instanceof GuiInventory || gui instanceof GuiContainerCreative) && entityplayer != null && world != null && entityplayer.ridingEntity instanceof LOTREntityNPCRideable && ((LOTREntityNPCRideable)entityplayer.ridingEntity).getMountInventory() != null) {
            entityplayer.closeScreen();
            LOTRPacketMountOpenInv packet = new LOTRPacketMountOpenInv();
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
            event.setCanceled(true);
            return;
        }
    }

    @SubscribeEvent
    public void postInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        GuiButton buttonDifficulty;
        GuiScreen gui = event.gui;
        List buttons = event.buttonList;
        if (gui instanceof GuiOptions && (buttonDifficulty = this.getDifficultyButton((GuiOptions)gui, buttons)) != null) {
            LOTRGuiButtonLock lock = new LOTRGuiButtonLock(1000000, buttonDifficulty.xPosition + buttonDifficulty.width + 4, buttonDifficulty.yPosition);
            lock.enabled = !LOTRLevelData.isDifficultyLocked();
            buttons.add(lock);
            buttonDifficulty.enabled = !LOTRLevelData.isDifficultyLocked();
        }
        this.addPouchRestockButton(gui, buttons);
    }

    private void addPouchRestockButton(GuiScreen gui, List buttons) {
        if (gui instanceof GuiContainer && !(gui instanceof LOTRGuiPouch) && !(gui instanceof LOTRGuiChestWithPouch)) {
            GuiContainer guiContainer = (GuiContainer)gui;
            EntityClientPlayerMP thePlayer = guiContainer.mc.thePlayer;
            InventoryPlayer playerInv = thePlayer.inventory;
            boolean containsPlayer = false;
            Slot topRightPlayerSlot = null;
            Slot topLeftPlayerSlot = null;
            Container container = guiContainer.inventorySlots;
            for (Object obj : container.inventorySlots) {
                boolean acceptableSlotIndex;
                Slot slot = (Slot)obj;
                boolean bl = acceptableSlotIndex = slot.getSlotIndex() < playerInv.mainInventory.length;
                if (gui instanceof GuiContainerCreative) {
                    boolean bl2 = acceptableSlotIndex = slot.getSlotIndex() >= 9;
                }
                if (slot.inventory != playerInv || !acceptableSlotIndex) continue;
                containsPlayer = true;
                boolean isTopRight = false;
                if (topRightPlayerSlot == null || slot.yDisplayPosition < topRightPlayerSlot.yDisplayPosition || slot.yDisplayPosition == topRightPlayerSlot.yDisplayPosition && slot.xDisplayPosition > topRightPlayerSlot.xDisplayPosition) {
                    isTopRight = true;
                }
                if (isTopRight) {
                    topRightPlayerSlot = slot;
                }
                boolean isTopLeft = false;
                if (topLeftPlayerSlot == null || slot.yDisplayPosition < topLeftPlayerSlot.yDisplayPosition || slot.yDisplayPosition == topLeftPlayerSlot.yDisplayPosition && slot.xDisplayPosition < topLeftPlayerSlot.xDisplayPosition) {
                    isTopLeft = true;
                }
                if (!isTopLeft) continue;
                topLeftPlayerSlot = slot;
            }
            if (containsPlayer) {
                int guiLeft = LOTRReflectionClient.getGuiLeft(guiContainer);
                int guiTop = LOTRReflectionClient.getGuiTop(guiContainer);
                LOTRReflectionClient.getGuiXSize(guiContainer);
                int buttonX = topRightPlayerSlot.xDisplayPosition + 7;
                int buttonY = topRightPlayerSlot.yDisplayPosition - 14;
                if (pouchRestock_leftPositionGUIs.contains(gui.getClass())) {
                    buttonX = topLeftPlayerSlot.xDisplayPosition - 1;
                    buttonY = topLeftPlayerSlot.yDisplayPosition - 14;
                } else if (pouchRestock_sidePositionGUIs.contains(gui.getClass())) {
                    buttonX = topRightPlayerSlot.xDisplayPosition + 21;
                    buttonY = topRightPlayerSlot.yDisplayPosition - 1;
                }
                if (LOTRModChecker.hasNEI() && guiContainer instanceof InventoryEffectRenderer && LOTRReflectionClient.hasGuiPotionEffects((InventoryEffectRenderer)guiContainer)) {
                    buttonX -= 60;
                }
                buttons.add(new LOTRGuiButtonRestockPouch(guiContainer, 2000, guiLeft + buttonX, guiTop + buttonY));
            }
        }
    }

    @SubscribeEvent
    public void postActionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen gui = event.gui;
        List buttons = event.buttonList;
        GuiButton button = event.button;
        if (gui instanceof GuiOptions && button instanceof LOTRGuiButtonLock && button.enabled && mc.isSingleplayer()) {
            LOTRLevelData.setSavedDifficulty(mc.gameSettings.difficulty);
            LOTRLevelData.setDifficultyLocked(true);
            button.enabled = false;
            GuiButton buttonDifficulty = this.getDifficultyButton((GuiOptions)gui, buttons);
            if (buttonDifficulty != null) {
                buttonDifficulty.enabled = false;
            }
        }
        if (button instanceof LOTRGuiButtonRestockPouch && button.enabled) {
            LOTRPacketRestockPouches packet = new LOTRPacketRestockPouches();
            LOTRPacketHandler.networkWrapper.sendToServer((IMessage)packet);
        }
    }

    private GuiButton getDifficultyButton(GuiOptions gui, List buttons) {
        for (Object obj : buttons) {
            GuiOptionButton button;
            if (!(obj instanceof GuiOptionButton) || (button = (GuiOptionButton)obj).returnEnumOptions() != GameSettings.Options.DIFFICULTY) continue;
            return button;
        }
        return null;
    }

    @SubscribeEvent
    public void preDrawScreen(GuiScreenEvent.DrawScreenEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen gui = event.gui;
        if (gui instanceof GuiModList) {
            ModContainer mod = LOTRMod.getModContainer();
            ModMetadata meta = mod.getMetadata();
            if (this.descScrollIndex == -1) {
                meta.description = LOTRModInfo.concatenateDescription(0);
                this.descScrollIndex = 0;
            }
            while (Mouse.next()) {
                int dwheel = Mouse.getEventDWheel();
                if (dwheel == 0) continue;
                int scroll = Integer.signum(dwheel);
                this.descScrollIndex -= scroll;
                this.descScrollIndex = MathHelper.clamp_int((int)this.descScrollIndex, (int)0, (int)(LOTRModInfo.description.length - 1));
                meta.description = LOTRModInfo.concatenateDescription(this.descScrollIndex);
            }
        }
        if (gui instanceof GuiContainer && LOTRConfig.displayCoinCounts) {
            boolean excludeGui;
            mc.theWorld.theProfiler.startSection("invCoinCount");
            GuiContainer guiContainer = (GuiContainer)gui;
            Container container = guiContainer.inventorySlots;
            Class<?> containerCls = container.getClass();
            Class<?> guiCls = guiContainer.getClass();
            boolean excludeContainer = coinCount_excludedContainers.contains(containerCls) || coinCount_excludedContainers_clsNames.contains(containerCls.getName());
            boolean bl = excludeGui = coinCount_excludedGUIs.contains(guiCls) || coinCount_excludedGUIs_clsNames.contains(guiCls.getName());
            if (guiContainer instanceof GuiContainerCreative && LOTRReflectionClient.getCreativeTabIndex((GuiContainerCreative)guiContainer) != CreativeTabs.tabInventory.getTabIndex()) {
                excludeGui = true;
            }
            if (!excludeContainer && !excludeGui) {
                int guiLeft = -1;
                int guiTop = -1;
                int guiXSize = -1;
                ArrayList<IInventory> differentInvs = new ArrayList<IInventory>();
                HashMap<IInventory, Integer> invHighestY = new HashMap<IInventory, Integer>();
                for (int i = 0; i < container.inventorySlots.size(); ++i) {
                    boolean excludeInv;
                    Slot slot = container.getSlot(i);
                    IInventory inv = slot.inventory;
                    if (inv == null) continue;
                    Class<?> invClass = inv.getClass();
                    boolean bl2 = excludeInv = coinCount_excludedInvTypes.contains(invClass) || coinCount_excludedInvTypes_clsNames.contains(invClass.getName());
                    if (excludeInv) continue;
                    if (!differentInvs.contains((Object)inv)) {
                        differentInvs.add(inv);
                    }
                    int slotY = slot.yDisplayPosition;
                    if (!invHighestY.containsKey((Object)inv)) {
                        invHighestY.put(inv, slotY);
                        continue;
                    }
                    int highestY = (Integer)invHighestY.get((Object)inv);
                    if (slotY >= highestY) continue;
                    invHighestY.put(inv, slotY);
                }
                for (IInventory inv : differentInvs) {
                    int coins = LOTRItemCoin.getContainerValue(inv, true);
                    if (coins <= 0) continue;
                    int metaIndex = coins < 10 ? 0 : (coins < 100 ? 1 : (coins < 1000 ? 2 : (coins < 10000 ? 3 : (coins < 100000 ? 4 : (coins < 1000000 ? 5 : 6)))));
                    String sCoins = String.valueOf(coins);
                    int sCoinsW = mc.fontRenderer.getStringWidth(sCoins);
                    int border = 2;
                    int rectWidth = 18 + sCoinsW + 1;
                    if (guiLeft == -1) {
                        guiTop = LOTRReflectionClient.getGuiTop(guiContainer);
                        guiXSize = LOTRReflectionClient.getGuiXSize(guiContainer);
                        guiLeft = gui.width / 2 - guiXSize / 2;
                        if (guiContainer instanceof InventoryEffectRenderer && LOTRReflectionClient.hasGuiPotionEffects((InventoryEffectRenderer)gui) && !LOTRModChecker.hasNEI()) {
                            guiLeft += 60;
                        }
                    }
                    int guiGap = 8;
                    int x = guiLeft + guiXSize + guiGap;
                    if (coinCountLeftSide) {
                        x = guiLeft - guiGap;
                        x -= rectWidth;
                    }
                    int y = (Integer)invHighestY.get((Object)inv) + guiTop;
                    int rectX0 = x - border;
                    int rectX1 = x + rectWidth + border;
                    int rectY0 = y - border;
                    int rectY1 = y + 16 + border;
                    float a0 = 1.0f;
                    float a1 = 0.1f;
                    GL11.glDisable((int)3553);
                    GL11.glDisable((int)3008);
                    GL11.glShadeModel((int)7425);
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-500.0f);
                    Tessellator tessellator = Tessellator.instance;
                    tessellator.startDrawingQuads();
                    tessellator.setColorRGBA_F(0.0f, 0.0f, 0.0f, a1);
                    tessellator.addVertex((double)rectX1, (double)rectY0, 0.0);
                    tessellator.setColorRGBA_F(0.0f, 0.0f, 0.0f, a0);
                    tessellator.addVertex((double)rectX0, (double)rectY0, 0.0);
                    tessellator.addVertex((double)rectX0, (double)rectY1, 0.0);
                    tessellator.setColorRGBA_F(0.0f, 0.0f, 0.0f, a1);
                    tessellator.addVertex((double)rectX1, (double)rectY1, 0.0);
                    tessellator.draw();
                    GL11.glPopMatrix();
                    GL11.glShadeModel((int)7424);
                    GL11.glEnable((int)3008);
                    GL11.glEnable((int)3553);
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)500.0f);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), new ItemStack(LOTRMod.silverCoin, 1, metaIndex), x, y);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glDisable((int)2896);
                    mc.fontRenderer.drawString(sCoins, x + 16 + 2, y + (16 - mc.fontRenderer.FONT_HEIGHT + 2) / 2, 16777215);
                    GL11.glPopMatrix();
                    GL11.glDisable((int)2896);
                    GL11.glEnable((int)3008);
                    GL11.glEnable((int)3042);
                    GL11.glDisable((int)2884);
                }
                mc.theWorld.theProfiler.endSection();
            }
        }
    }

    @SubscribeEvent
    public void postDrawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
        HoverEvent hoverevent;
        IChatComponent component;
        Minecraft mc = Minecraft.getMinecraft();
        EntityClientPlayerMP entityplayer = mc.thePlayer;
        GuiScreen gui = event.gui;
        int mouseX = event.mouseX;
        int mouseY = event.mouseY;
        if (gui instanceof GuiChat && (component = mc.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY())) != null && component.getChatStyle().getChatHoverEvent() != null && (hoverevent = component.getChatStyle().getChatHoverEvent()).getAction() == LOTRChatEvents.SHOW_LOTR_ACHIEVEMENT) {
            LOTRGuiAchievementHoverEvent proxyGui = new LOTRGuiAchievementHoverEvent();
            proxyGui.setWorldAndResolution(mc, gui.width, gui.height);
            try {
                String unformattedText = hoverevent.getValue().getUnformattedText();
                int splitIndex = unformattedText.indexOf("$");
                String categoryName = unformattedText.substring(0, splitIndex);
                LOTRAchievement.Category category = LOTRAchievement.categoryForName(categoryName);
                int achievementID = Integer.parseInt(unformattedText.substring(splitIndex + 1));
                LOTRAchievement achievement = LOTRAchievement.achievementForCategoryAndID(category, achievementID);
                if (achievement != null) {
                    if (currentCategory != category) {
                        currentCategory = category;
                        this.updateAchievementCounts(category);
                    }
                    ChatComponentTranslation name = new ChatComponentTranslation("lotr.gui.achievements.hover.name", new Object[]{achievement.getAchievementChatComponent((EntityPlayer)entityplayer)});
                    String categoryNameTranslated = StatCollector.translateToLocal((String)("lotr.achievement.category." + category.codeName()));
                    String countText = String.format("(%s/%s)", this.currentCategoryTakenCount, this.currentCategoryTakenCount + this.currentCategoryUntakenCount);
                    ChatComponentTranslation name2 = new ChatComponentTranslation("lotr.gui.achievements.hover1.name", new Object[]{categoryNameTranslated + " " + countText, achievement.getAchievementChatComponent((EntityPlayer)entityplayer).getFormattedText()});
                    String dimensionName = achievement.getDimension().getDimensionName();
                    ChatComponentTranslation subtitle = new ChatComponentTranslation("lotr.gui.achievements.hover.subtitle", new Object[]{dimensionName, categoryNameTranslated});
                    subtitle.getChatStyle().setItalic(Boolean.valueOf(true));
                    String desc = achievement.getDescription((EntityPlayer)entityplayer);
                    ArrayList list = Lists.newArrayList((Object[])new String[]{name.getFormattedText(), name2.getFormattedText(), subtitle.getFormattedText()});
                    list.addAll(mc.fontRenderer.listFormattedStringToWidth(desc, 450));
                    proxyGui.func_146283_a(list, mouseX, mouseY);
                } else {
                    proxyGui.drawCreativeTabHoveringText((Object)EnumChatFormatting.RED + "Invalid LOTRAchievement!", mouseX, mouseY);
                }
            }
            catch (Exception e) {
                proxyGui.drawCreativeTabHoveringText((Object)EnumChatFormatting.RED + "Invalid LOTRAchievement!", mouseX, mouseY);
            }
        }
    }

    private void updateAchievementCounts(LOTRAchievement.Category category) {
        this.currentCategoryTakenCount = 0;
        this.currentCategoryUntakenCount = 0;
        this.currentCategoryTakenAchievements.clear();
        this.currentCategoryUntakenAchievements.clear();
        for (LOTRAchievement achievement : category.list) {
            if (!achievement.canPlayerEarn((EntityPlayer)Minecraft.getMinecraft().thePlayer)) continue;
            if (LOTRLevelData.getData((EntityPlayer)Minecraft.getMinecraft().thePlayer).hasAchievement(achievement)) {
                this.currentCategoryTakenAchievements.add(achievement);
                ++this.currentCategoryTakenCount;
                continue;
            }
            this.currentCategoryUntakenAchievements.add(achievement);
            ++this.currentCategoryUntakenCount;
        }
    }

    static {
        coinCount_excludedInvTypes.add(LOTRContainerCoinExchange.InventoryCoinExchangeSlot.class);
        coinCount_excludedInvTypes.add(InventoryCraftResult.class);
        coinCount_excludedInvTypes_clsNames.add("thaumcraft.common.entities.InventoryMob");
        pouchRestock_leftPositionGUIs.add(LOTRGuiAnvil.class);
        pouchRestock_leftPositionGUIs.add(GuiRepair.class);
        pouchRestock_sidePositionGUIs.add(LOTRGuiBarrel.class);
    }
}

