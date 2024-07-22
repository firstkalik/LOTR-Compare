/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.item.LOTRItemHaradRobes;
import lotr.common.item.LOTRItemHaradTurban;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityBanditRhun
extends LOTREntityBandit {
    private static ItemStack[] weapons = new ItemStack[]{new ItemStack(LOTRMod.daggerBronze), new ItemStack(LOTRMod.daggerIron), new ItemStack(LOTRMod.daggerNearHarad), new ItemStack(LOTRMod.daggerNearHaradPoisoned), new ItemStack(LOTRMod.daggerHarad), new ItemStack(LOTRMod.daggerHaradPoisoned), new ItemStack(LOTRMod.daggerRhun), new ItemStack(LOTRMod.daggerRhunPoisoned)};
    private static int[] robeColors = new int[]{3354412, 5984843, 5968655, 3619908, 9007463, 3228720};

    public LOTREntityBanditRhun(World world) {
        super(world);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(weapons.length);
        this.npcItemsInv.setMeleeWeapon(weapons[i].copy());
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(4, null);
        if (this.rand.nextInt(4) == 0) {
            ItemStack turban = new ItemStack(LOTRMod.helmetHaradRobes);
            int robeColor = robeColors[this.rand.nextInt(robeColors.length)];
            LOTRItemHaradRobes.setRobesColor(turban, robeColor);
            if (this.rand.nextInt(3) == 0) {
                LOTRItemHaradTurban.setHasOrnament(turban, true);
            }
            this.setCurrentItemOrArmor(4, turban);
        }
        return data;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        return "misc/banditrhun/hostile";
    }
}

