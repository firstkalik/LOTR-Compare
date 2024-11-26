/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.MovementInputFromOptions
 */
package lotr.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.LOTRPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;

@SideOnly(value=Side.CLIENT)
public class LOTRDrunkenMovementHandler
extends MovementInputFromOptions {
    private final Minecraft gameInstance = Minecraft.getMinecraft();
    private final GameSettings controlSettings;
    private static final List<Integer> originalKeyBindings = Arrays.asList(2, 4, 3, 5);
    private final List<Integer> shuffledKeyBindings = new ArrayList<Integer>(originalKeyBindings);
    private boolean isKeysShuffled = false;

    public LOTRDrunkenMovementHandler(GameSettings settings) {
        super(settings);
        this.controlSettings = settings;
    }

    public void updatePlayerMoveState() {
        boolean shouldApplyEffect;
        EntityClientPlayerMP currentPlayer = this.gameInstance.thePlayer;
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)currentPlayer);
        int alcoholTolerance = playerData.getAlcoholTolerance();
        boolean bl = shouldApplyEffect = alcoholTolerance <= 1000;
        if (!this.isKeysShuffled && currentPlayer.isPotionActive(LOTRPotions.drunk.id) && shouldApplyEffect) {
            Collections.shuffle(this.shuffledKeyBindings);
            this.isKeysShuffled = true;
        }
        this.resetMovement();
        this.checkKeyBindings();
        this.handleJumpAndSneak(currentPlayer);
        if (currentPlayer.isPotionActive(LOTRPotions.drunk.id) && !currentPlayer.capabilities.allowFlying && shouldApplyEffect) {
            this.jump = false;
        }
    }

    private void resetMovement() {
        this.moveStrafe = 0.0f;
        this.moveForward = 0.0f;
    }

    private void checkKeyBindings() {
        if (this.controlSettings.keyBindings[this.shuffledKeyBindings.get(0)].getIsKeyPressed()) {
            this.moveForward += 1.0f;
        }
        if (this.controlSettings.keyBindings[this.shuffledKeyBindings.get(1)].getIsKeyPressed()) {
            this.moveForward -= 1.0f;
        }
        if (this.controlSettings.keyBindings[this.shuffledKeyBindings.get(2)].getIsKeyPressed()) {
            this.moveStrafe += 1.0f;
        }
        if (this.controlSettings.keyBindings[this.shuffledKeyBindings.get(3)].getIsKeyPressed()) {
            this.moveStrafe -= 1.0f;
        }
    }

    private void handleJumpAndSneak(EntityClientPlayerMP player) {
        this.jump = this.controlSettings.keyBindJump.getIsKeyPressed();
        this.sneak = this.controlSettings.keyBindSneak.getIsKeyPressed();
        if (this.sneak) {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3);
            this.moveForward = (float)((double)this.moveForward * 0.3);
        }
    }

    public void resetShuffledKeys(EntityPlayerSP playerSP) {
        boolean shouldApplyEffect;
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)playerSP);
        boolean hasAchievement = playerData.hasAchievement(LOTRAchievement.gainHighAlcoholTolerance);
        int alcoholTolerance = playerData.getAlcoholTolerance();
        boolean bl = shouldApplyEffect = alcoholTolerance <= 1000;
        if (!(!this.isKeysShuffled || playerSP.isPotionActive(LOTRPotions.drunk.id) && shouldApplyEffect)) {
            Collections.copy(this.shuffledKeyBindings, originalKeyBindings);
            this.isKeysShuffled = false;
        }
    }

    public void copyMovementData(MovementInput input) {
        this.moveStrafe = input.moveStrafe;
        this.moveForward = input.moveForward;
        this.jump = input.jump;
        this.sneak = input.sneak;
    }

    public static boolean shouldHandleDrunkenMovement(EntityPlayerSP playerSP) {
        LOTRPlayerData playerData = LOTRLevelData.getData((EntityPlayer)playerSP);
        int alcoholTolerance = playerData.getAlcoholTolerance();
        return playerSP.isPotionActive(LOTRPotions.drunk.id) && alcoholTolerance <= 1000;
    }
}

