/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRCapes;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.LOTREntityEreborDwarf;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.entity.npc.LOTRMercenary;
import lotr.common.entity.npc.LOTRNPCMount;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LOTREntityRedDwarfWarrior3
extends LOTREntityEreborDwarf
implements LOTRMercenary {
    private static ItemStack[] weaponsIron = new ItemStack[]{new ItemStack(LOTRMod.pikeDwarvenMithril), new ItemStack(LOTRMod.battleaxeDwarvenMithril), new ItemStack(LOTRMod.hammerDwarvenMithril), new ItemStack(LOTRMod.daggerDwarvenPoisoned), new ItemStack(LOTRMod.swordDwarven), new ItemStack(LOTRMod.spearDwarvenMithril)};
    private static ItemStack[] weaponsBronze = new ItemStack[]{new ItemStack(LOTRMod.pikeDwarvenMithril), new ItemStack(LOTRMod.battleaxeDwarvenMithril), new ItemStack(LOTRMod.hammerDwarvenMithril), new ItemStack(LOTRMod.daggerDwarvenPoisoned), new ItemStack(LOTRMod.swordDwarven), new ItemStack(LOTRMod.spearDwarvenMithril)};

    public LOTREntityRedDwarfWarrior3(World world) {
        super(world);
        this.npcCape = LOTRCapes.ALIGNMENT_DWARF.capeTexture;
        this.npcShield = LOTRShields.ALIGNMENT_EREBOR;
        this.spawnRidingHorse = this.rand.nextInt(6) == 0;
    }

    @Override
    public LOTRFaction getHiringFaction() {
        return LOTRFaction.DURINS_FOLK;
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityWildBoar boar = new LOTREntityWildBoar(this.worldObj);
        boar.setMountArmor(new ItemStack(LOTRMod.boarArmorDwarfMithril));
        return boar;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        if (this.rand.nextInt(3) == 0) {
            int i = this.rand.nextInt(weaponsBronze.length);
            this.npcItemsInv.setMeleeWeapon(weaponsBronze[i].copy());
            if (this.rand.nextInt(5) == 0) {
                this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
                this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearDwarvenMithril));
            }
        } else {
            int i = this.rand.nextInt(weaponsIron.length);
            this.npcItemsInv.setMeleeWeapon(weaponsIron[i].copy());
            if (this.rand.nextInt(5) == 0) {
                this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
                this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearDwarvenMithril));
            }
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsEreborMithril));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsEreborMithril));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyEreborMithril));
        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetEreborMithril));
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public int getMercBaseCost() {
        return 50;
    }

    @Override
    public float getMercAlignmentRequired() {
        return 250.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 0.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.hireEreborMercenary);
    }
}

