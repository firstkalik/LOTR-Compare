/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  org.apache.logging.log4j.Logger
 */
package lotr.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import lotr.common.network.LOTRPacketAchievement;
import lotr.common.network.LOTRPacketAchievementRemove;
import lotr.common.network.LOTRPacketAlignDrain;
import lotr.common.network.LOTRPacketAlignment;
import lotr.common.network.LOTRPacketAlignmentBonus;
import lotr.common.network.LOTRPacketAlignmentChoiceOffer;
import lotr.common.network.LOTRPacketAlignmentChoices;
import lotr.common.network.LOTRPacketAlignmentSee;
import lotr.common.network.LOTRPacketAlignmentSee2;
import lotr.common.network.LOTRPacketAnvilEngraveOwner;
import lotr.common.network.LOTRPacketAnvilReforge;
import lotr.common.network.LOTRPacketAnvilRename;
import lotr.common.network.LOTRPacketBannerData;
import lotr.common.network.LOTRPacketBannerRequestInvalidName;
import lotr.common.network.LOTRPacketBannerValidate;
import lotr.common.network.LOTRPacketBeaconEdit;
import lotr.common.network.LOTRPacketBiomeVariantsUnwatch;
import lotr.common.network.LOTRPacketBiomeVariantsWatch;
import lotr.common.network.LOTRPacketBlockFX;
import lotr.common.network.LOTRPacketBrandingIron;
import lotr.common.network.LOTRPacketBrewingButton;
import lotr.common.network.LOTRPacketBrokenPledge;
import lotr.common.network.LOTRPacketBuyUnit;
import lotr.common.network.LOTRPacketCWPProtectionMessage;
import lotr.common.network.LOTRPacketCWPSharedHide;
import lotr.common.network.LOTRPacketCWPSharedHideClient;
import lotr.common.network.LOTRPacketCWPSharedUnlockClient;
import lotr.common.network.LOTRPacketCancelItemHighlight;
import lotr.common.network.LOTRPacketCape;
import lotr.common.network.LOTRPacketClientInfo;
import lotr.common.network.LOTRPacketClientMQEvent;
import lotr.common.network.LOTRPacketClientsideGUI;
import lotr.common.network.LOTRPacketCoinExchange;
import lotr.common.network.LOTRPacketConquestGrid;
import lotr.common.network.LOTRPacketConquestGridRequest;
import lotr.common.network.LOTRPacketConquestNotification;
import lotr.common.network.LOTRPacketCreateCWP;
import lotr.common.network.LOTRPacketCreateCWPClient;
import lotr.common.network.LOTRPacketDate;
import lotr.common.network.LOTRPacketDeleteCWP;
import lotr.common.network.LOTRPacketDeleteCWPClient;
import lotr.common.network.LOTRPacketDeleteMiniquest;
import lotr.common.network.LOTRPacketEditBanner;
import lotr.common.network.LOTRPacketEditNPCRespawner;
import lotr.common.network.LOTRPacketEditSign;
import lotr.common.network.LOTRPacketEnableAlignmentZones;
import lotr.common.network.LOTRPacketEntityUUID;
import lotr.common.network.LOTRPacketEnvironmentOverlay;
import lotr.common.network.LOTRPacketFTBounceClient;
import lotr.common.network.LOTRPacketFTBounceServer;
import lotr.common.network.LOTRPacketFTCooldown;
import lotr.common.network.LOTRPacketFTScreen;
import lotr.common.network.LOTRPacketFTTimer;
import lotr.common.network.LOTRPacketFactionData;
import lotr.common.network.LOTRPacketFactionRelations;
import lotr.common.network.LOTRPacketFamilyInfo;
import lotr.common.network.LOTRPacketFastTravel;
import lotr.common.network.LOTRPacketFellowship;
import lotr.common.network.LOTRPacketFellowshipCreate;
import lotr.common.network.LOTRPacketFellowshipDisband;
import lotr.common.network.LOTRPacketFellowshipDoPlayer;
import lotr.common.network.LOTRPacketFellowshipLeave;
import lotr.common.network.LOTRPacketFellowshipNotification;
import lotr.common.network.LOTRPacketFellowshipPartialUpdate;
import lotr.common.network.LOTRPacketFellowshipRemove;
import lotr.common.network.LOTRPacketFellowshipRename;
import lotr.common.network.LOTRPacketFellowshipRespondInvite;
import lotr.common.network.LOTRPacketFellowshipSetIcon;
import lotr.common.network.LOTRPacketFellowshipToggle;
import lotr.common.network.LOTRPacketHiredGui;
import lotr.common.network.LOTRPacketHiredInfo;
import lotr.common.network.LOTRPacketHiredUnitCommand;
import lotr.common.network.LOTRPacketHiredUnitDismiss;
import lotr.common.network.LOTRPacketHiredUnitInteract;
import lotr.common.network.LOTRPacketHornSelect;
import lotr.common.network.LOTRPacketInvasionWatch;
import lotr.common.network.LOTRPacketIsOpRequest;
import lotr.common.network.LOTRPacketIsOpResponse;
import lotr.common.network.LOTRPacketItemSquadron;
import lotr.common.network.LOTRPacketLocationFX;
import lotr.common.network.LOTRPacketLogin;
import lotr.common.network.LOTRPacketLoginPlayerData;
import lotr.common.network.LOTRPacketMallornEntHeal;
import lotr.common.network.LOTRPacketMallornEntSummon;
import lotr.common.network.LOTRPacketMapTp;
import lotr.common.network.LOTRPacketMercenaryInteract;
import lotr.common.network.LOTRPacketMessage;
import lotr.common.network.LOTRPacketMiniquest;
import lotr.common.network.LOTRPacketMiniquestOffer;
import lotr.common.network.LOTRPacketMiniquestOfferClose;
import lotr.common.network.LOTRPacketMiniquestRemove;
import lotr.common.network.LOTRPacketMiniquestTrack;
import lotr.common.network.LOTRPacketMiniquestTrackClient;
import lotr.common.network.LOTRPacketMobSpawner;
import lotr.common.network.LOTRPacketMountControl;
import lotr.common.network.LOTRPacketMountControlServerEnforce;
import lotr.common.network.LOTRPacketMountOpenInv;
import lotr.common.network.LOTRPacketNPCCombatStance;
import lotr.common.network.LOTRPacketNPCFX;
import lotr.common.network.LOTRPacketNPCIsEating;
import lotr.common.network.LOTRPacketNPCIsOfferingQuest;
import lotr.common.network.LOTRPacketNPCRespawner;
import lotr.common.network.LOTRPacketNPCSpeech;
import lotr.common.network.LOTRPacketNPCSquadron;
import lotr.common.network.LOTRPacketOpenSignEditor;
import lotr.common.network.LOTRPacketOptions;
import lotr.common.network.LOTRPacketParticles;
import lotr.common.network.LOTRPacketPledge;
import lotr.common.network.LOTRPacketPledgeSet;
import lotr.common.network.LOTRPacketPortalPos;
import lotr.common.network.LOTRPacketRenameCWP;
import lotr.common.network.LOTRPacketRenameCWPClient;
import lotr.common.network.LOTRPacketRenamePouch;
import lotr.common.network.LOTRPacketRestockPouches;
import lotr.common.network.LOTRPacketSealCracker;
import lotr.common.network.LOTRPacketSelectCape;
import lotr.common.network.LOTRPacketSelectShield;
import lotr.common.network.LOTRPacketSelectTitle;
import lotr.common.network.LOTRPacketSell;
import lotr.common.network.LOTRPacketSetOption;
import lotr.common.network.LOTRPacketSetPlayerRotation;
import lotr.common.network.LOTRPacketShareCWP;
import lotr.common.network.LOTRPacketShareCWPClient;
import lotr.common.network.LOTRPacketShield;
import lotr.common.network.LOTRPacketStopItemUse;
import lotr.common.network.LOTRPacketTitle;
import lotr.common.network.LOTRPacketTraderInfo;
import lotr.common.network.LOTRPacketTraderInteract;
import lotr.common.network.LOTRPacketUnitTraderInteract;
import lotr.common.network.LOTRPacketUpdatePlayerLocations;
import lotr.common.network.LOTRPacketUpdateViewingFaction;
import lotr.common.network.LOTRPacketUtumnoKill;
import lotr.common.network.LOTRPacketUtumnoReturn;
import lotr.common.network.LOTRPacketWaypointRegion;
import lotr.common.network.LOTRPacketWaypointUseCount;
import lotr.common.network.LOTRPacketWeaponFX;
import lotr.common.network.PacketAlcoholTolerance;
import lotr.common.network.PacketSyncNPCCount;
import lotr.common.util.LOTRLog;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.apache.logging.log4j.Logger;

public class LOTRPacketHandler {
    public static final SimpleNetworkWrapper networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("lotr_");

    public LOTRPacketHandler() {
        int id = 0;
        networkWrapper.registerMessage(LOTRPacketParticles.Handler.class, LOTRPacketParticles.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketLogin.Handler.class, LOTRPacketLogin.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketLoginPlayerData.Handler.class, LOTRPacketLoginPlayerData.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketPortalPos.Handler.class, LOTRPacketPortalPos.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketUpdateViewingFaction.Handler.class, LOTRPacketUpdateViewingFaction.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAlignment.Handler.class, LOTRPacketAlignment.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAlignmentBonus.Handler.class, LOTRPacketAlignmentBonus.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAlignDrain.Handler.class, LOTRPacketAlignDrain.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAchievement.Handler.class, LOTRPacketAchievement.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAchievementRemove.Handler.class, LOTRPacketAchievementRemove.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketWeaponFX.Handler.class, LOTRPacketWeaponFX.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketBlockFX.Handler.class, LOTRPacketBlockFX.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketLocationFX.Handler.class, LOTRPacketLocationFX.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketStopItemUse.Handler.class, LOTRPacketStopItemUse.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketTraderInfo.Handler.class, LOTRPacketTraderInfo.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketShield.Handler.class, LOTRPacketShield.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketCape.Handler.class, LOTRPacketCape.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketOptions.Handler.class, LOTRPacketOptions.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMessage.Handler.class, LOTRPacketMessage.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketHiredInfo.Handler.class, LOTRPacketHiredInfo.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketHiredGui.Handler.class, LOTRPacketHiredGui.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketIsOpResponse.Handler.class, LOTRPacketIsOpResponse.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFTCooldown.Handler.class, LOTRPacketFTCooldown.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketWaypointRegion.Handler.class, LOTRPacketWaypointRegion.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFTTimer.Handler.class, LOTRPacketFTTimer.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFTScreen.Handler.class, LOTRPacketFTScreen.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFTBounceClient.Handler.class, LOTRPacketFTBounceClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketWaypointUseCount.Handler.class, LOTRPacketWaypointUseCount.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketEnvironmentOverlay.Handler.class, LOTRPacketEnvironmentOverlay.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketUpdatePlayerLocations.Handler.class, LOTRPacketUpdatePlayerLocations.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketCreateCWPClient.Handler.class, LOTRPacketCreateCWPClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketDeleteCWPClient.Handler.class, LOTRPacketDeleteCWPClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketRenameCWPClient.Handler.class, LOTRPacketRenameCWPClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketCWPProtectionMessage.Handler.class, LOTRPacketCWPProtectionMessage.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketNPCFX.Handler.class, LOTRPacketNPCFX.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketBannerData.Handler.class, LOTRPacketBannerData.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketBannerValidate.Handler.class, LOTRPacketBannerValidate.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFamilyInfo.Handler.class, LOTRPacketFamilyInfo.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketEntityUUID.Handler.class, LOTRPacketEntityUUID.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketNPCSpeech.Handler.class, LOTRPacketNPCSpeech.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketNPCCombatStance.Handler.class, LOTRPacketNPCCombatStance.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMiniquest.Handler.class, LOTRPacketMiniquest.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMiniquestOffer.Handler.class, LOTRPacketMiniquestOffer.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMiniquestRemove.Handler.class, LOTRPacketMiniquestRemove.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketInvasionWatch.Handler.class, LOTRPacketInvasionWatch.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketDate.Handler.class, LOTRPacketDate.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketTitle.Handler.class, LOTRPacketTitle.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketUtumnoReturn.Handler.class, LOTRPacketUtumnoReturn.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFactionData.Handler.class, LOTRPacketFactionData.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketBiomeVariantsWatch.Handler.class, LOTRPacketBiomeVariantsWatch.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketBiomeVariantsUnwatch.Handler.class, LOTRPacketBiomeVariantsUnwatch.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMallornEntHeal.Handler.class, LOTRPacketMallornEntHeal.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMallornEntSummon.Handler.class, LOTRPacketMallornEntSummon.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketUtumnoKill.Handler.class, LOTRPacketUtumnoKill.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketNPCRespawner.Handler.class, LOTRPacketNPCRespawner.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketNPCIsEating.Handler.class, LOTRPacketNPCIsEating.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketNPCIsOfferingQuest.Handler.class, LOTRPacketNPCIsOfferingQuest.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMiniquestTrackClient.Handler.class, LOTRPacketMiniquestTrackClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketOpenSignEditor.Handler.class, LOTRPacketOpenSignEditor.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAlignmentSee.Handler.class, LOTRPacketAlignmentSee.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAlignmentSee2.Handler.class, LOTRPacketAlignmentSee2.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowship.Handler.class, LOTRPacketFellowship.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipRemove.Handler.class, LOTRPacketFellowshipRemove.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipNotification.Handler.class, LOTRPacketFellowshipNotification.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketShareCWPClient.Handler.class, LOTRPacketShareCWPClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketCWPSharedUnlockClient.Handler.class, LOTRPacketCWPSharedUnlockClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketCWPSharedHideClient.Handler.class, LOTRPacketCWPSharedHideClient.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketEnableAlignmentZones.Handler.class, LOTRPacketEnableAlignmentZones.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketCancelItemHighlight.Handler.class, LOTRPacketCancelItemHighlight.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketSetPlayerRotation.Handler.class, LOTRPacketSetPlayerRotation.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketPledge.Handler.class, LOTRPacketPledge.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketBrokenPledge.Handler.class, LOTRPacketBrokenPledge.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFactionRelations.Handler.class, LOTRPacketFactionRelations.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketConquestGrid.Handler.class, LOTRPacketConquestGrid.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketConquestNotification.Handler.class, LOTRPacketConquestNotification.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketAlignmentChoiceOffer.Handler.class, LOTRPacketAlignmentChoiceOffer.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketMountControlServerEnforce.Handler.class, LOTRPacketMountControlServerEnforce.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketClientsideGUI.Handler.class, LOTRPacketClientsideGUI.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipPartialUpdate.Rename.Handler.class, LOTRPacketFellowshipPartialUpdate.Rename.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipPartialUpdate.ChangeIcon.Handler.class, LOTRPacketFellowshipPartialUpdate.ChangeIcon.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipPartialUpdate.TogglePvp.Handler.class, LOTRPacketFellowshipPartialUpdate.TogglePvp.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipPartialUpdate.ToggleHiredFriendlyFire.Handler.class, LOTRPacketFellowshipPartialUpdate.ToggleHiredFriendlyFire.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketFellowshipPartialUpdate.ToggleShowMap.Handler.class, LOTRPacketFellowshipPartialUpdate.ToggleShowMap.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketSell.Handler.class, LOTRPacketSell.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMobSpawner.Handler.class, LOTRPacketMobSpawner.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketBuyUnit.Handler.class, LOTRPacketBuyUnit.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketHornSelect.Handler.class, LOTRPacketHornSelect.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketBrewingButton.Handler.class, LOTRPacketBrewingButton.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketSelectShield.Handler.class, LOTRPacketSelectShield.class, id++, Side.SERVER);
        networkWrapper.registerMessage(PacketAlcoholTolerance.Handler.class, PacketAlcoholTolerance.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(PacketSyncNPCCount.Handler.class, PacketSyncNPCCount.class, id++, Side.CLIENT);
        networkWrapper.registerMessage(LOTRPacketTraderInteract.Handler.class, LOTRPacketTraderInteract.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketUnitTraderInteract.Handler.class, LOTRPacketUnitTraderInteract.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketHiredUnitInteract.Handler.class, LOTRPacketHiredUnitInteract.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketSetOption.Handler.class, LOTRPacketSetOption.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketRenamePouch.Handler.class, LOTRPacketRenamePouch.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketHiredUnitCommand.Handler.class, LOTRPacketHiredUnitCommand.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMapTp.Handler.class, LOTRPacketMapTp.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketIsOpRequest.Handler.class, LOTRPacketIsOpRequest.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFastTravel.Handler.class, LOTRPacketFastTravel.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFTBounceServer.Handler.class, LOTRPacketFTBounceServer.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketHiredUnitDismiss.Handler.class, LOTRPacketHiredUnitDismiss.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketClientInfo.Handler.class, LOTRPacketClientInfo.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketCreateCWP.Handler.class, LOTRPacketCreateCWP.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketDeleteCWP.Handler.class, LOTRPacketDeleteCWP.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketRenameCWP.Handler.class, LOTRPacketRenameCWP.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMountOpenInv.Handler.class, LOTRPacketMountOpenInv.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketEditBanner.Handler.class, LOTRPacketEditBanner.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketBannerRequestInvalidName.Handler.class, LOTRPacketBannerRequestInvalidName.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMiniquestOfferClose.Handler.class, LOTRPacketMiniquestOfferClose.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketDeleteMiniquest.Handler.class, LOTRPacketDeleteMiniquest.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketSelectTitle.Handler.class, LOTRPacketSelectTitle.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketNPCSquadron.Handler.class, LOTRPacketNPCSquadron.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketItemSquadron.Handler.class, LOTRPacketItemSquadron.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketEditNPCRespawner.Handler.class, LOTRPacketEditNPCRespawner.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketCoinExchange.Handler.class, LOTRPacketCoinExchange.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMiniquestTrack.Handler.class, LOTRPacketMiniquestTrack.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketEditSign.Handler.class, LOTRPacketEditSign.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketSealCracker.Handler.class, LOTRPacketSealCracker.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipCreate.Handler.class, LOTRPacketFellowshipCreate.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipDoPlayer.Handler.class, LOTRPacketFellowshipDoPlayer.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipDisband.Handler.class, LOTRPacketFellowshipDisband.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipRename.Handler.class, LOTRPacketFellowshipRename.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipLeave.Handler.class, LOTRPacketFellowshipLeave.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipSetIcon.Handler.class, LOTRPacketFellowshipSetIcon.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipRespondInvite.Handler.class, LOTRPacketFellowshipRespondInvite.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketFellowshipToggle.Handler.class, LOTRPacketFellowshipToggle.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketShareCWP.Handler.class, LOTRPacketShareCWP.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketCWPSharedHide.Handler.class, LOTRPacketCWPSharedHide.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketBeaconEdit.Handler.class, LOTRPacketBeaconEdit.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketAnvilRename.Handler.class, LOTRPacketAnvilRename.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketAnvilReforge.Handler.class, LOTRPacketAnvilReforge.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketAnvilEngraveOwner.Handler.class, LOTRPacketAnvilEngraveOwner.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMercenaryInteract.Handler.class, LOTRPacketMercenaryInteract.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketPledgeSet.Handler.class, LOTRPacketPledgeSet.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketClientMQEvent.Handler.class, LOTRPacketClientMQEvent.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketConquestGridRequest.Handler.class, LOTRPacketConquestGridRequest.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketAlignmentChoices.Handler.class, LOTRPacketAlignmentChoices.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketBrandingIron.Handler.class, LOTRPacketBrandingIron.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketRestockPouches.Handler.class, LOTRPacketRestockPouches.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketMountControl.Handler.class, LOTRPacketMountControl.class, id++, Side.SERVER);
        networkWrapper.registerMessage(LOTRPacketSelectCape.Handler.class, LOTRPacketSelectCape.class, id++, Side.SERVER);
        LOTRLog.logger.info("LOTR: Registered " + id + " packet types");
    }

    public static NetworkRegistry.TargetPoint nearEntity(Entity entity, double range) {
        return new NetworkRegistry.TargetPoint(entity.dimension, entity.posX, entity.boundingBox.minY, entity.posZ, range);
    }
}

