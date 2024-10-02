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
import java.util.List;
import lotr.common.LOTRPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;

@SideOnly(value=Side.CLIENT)
public class LOTRPlayerFallEventHandler2
extends MovementInputFromOptions {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final GameSettings gameSettings;
    private static final List<Integer> bindIndexesOriginal = Arrays.asList(2, 4, 3, 5);
    private final List<Integer> bindIndexes = new ArrayList<Integer>(bindIndexesOriginal);
    private boolean shuffled = false;

    public LOTRPlayerFallEventHandler2(GameSettings gameSettings) {
        super(gameSettings);
        this.gameSettings = gameSettings;
    }

    public void updatePlayerMoveState() {
        EntityClientPlayerMP player = this.mc.thePlayer;
        this.moveStrafe = 0.0f;
        this.moveForward = 0.0f;
        if (this.gameSettings.keyBindForward.getIsKeyPressed()) {
            this.moveForward += 1.0f;
        }
        if (this.gameSettings.keyBindBack.getIsKeyPressed()) {
            this.moveForward -= 1.0f;
        }
        if (this.gameSettings.keyBindLeft.getIsKeyPressed()) {
            this.moveStrafe += 1.0f;
        }
        if (this.gameSettings.keyBindRight.getIsKeyPressed()) {
            this.moveStrafe -= 1.0f;
        }
        this.jump = this.gameSettings.keyBindJump.getIsKeyPressed();
        this.sneak = this.gameSettings.keyBindSneak.getIsKeyPressed();
        if (this.sneak) {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3);
            this.moveForward = (float)((double)this.moveForward * 0.3);
        }
        if (player.isPotionActive(LOTRPotions.broken.id) && !player.capabilities.allowFlying) {
            this.jump = false;
        }
    }

    public void onUpdate(EntityPlayerSP playerSP) {
    }

    public void copyData(MovementInput input) {
        this.jump = input.jump;
        this.sneak = input.sneak;
    }

    public static boolean shouldPerform(EntityPlayerSP playerSP) {
        return playerSP.isPotionActive(LOTRPotions.broken.id);
    }
}

