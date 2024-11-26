/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package lotr.common.entity.npc;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetBasic;
import lotr.common.entity.ai.LOTREntityAINearestAttackableTargetTroll;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import lotr.common.entity.npc.LOTRInventoryNPCItems;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityMoriaOrc
extends LOTREntityOrc {
    private static ItemStack[] weapons = new ItemStack[]{new ItemStack(Items.stone_sword), new ItemStack(Items.stone_axe), new ItemStack(Items.stone_pickaxe), new ItemStack(Items.iron_sword), new ItemStack(Items.iron_axe), new ItemStack(Items.iron_pickaxe), new ItemStack(LOTRMod.daggerIron), new ItemStack(LOTRMod.daggerIronPoisoned), new ItemStack(LOTRMod.battleaxeIron), new ItemStack(LOTRMod.swordBronze), new ItemStack(LOTRMod.axeBronze), new ItemStack(LOTRMod.pickaxeBronze), new ItemStack(LOTRMod.daggerBronze), new ItemStack(LOTRMod.daggerBronzePoisoned), new ItemStack(LOTRMod.battleaxeBronze), new ItemStack(LOTRMod.swordAngmar), new ItemStack(LOTRMod.axeAngmar), new ItemStack(LOTRMod.pickaxeAngmar), new ItemStack(LOTRMod.daggerAngmar), new ItemStack(LOTRMod.daggerAngmarPoisoned), new ItemStack(LOTRMod.battleaxeAngmar), new ItemStack(LOTRMod.hammerAngmar), new ItemStack(LOTRMod.scimitarOrc), new ItemStack(LOTRMod.axeOrc), new ItemStack(LOTRMod.pickaxeOrc), new ItemStack(LOTRMod.daggerOrc), new ItemStack(LOTRMod.daggerOrcPoisoned), new ItemStack(LOTRMod.battleaxeOrc), new ItemStack(LOTRMod.hammerOrc), new ItemStack(LOTRMod.polearmOrc), new ItemStack(LOTRMod.swordDwarven), new ItemStack(LOTRMod.axeDwarven), new ItemStack(LOTRMod.pickaxeDwarven), new ItemStack(LOTRMod.daggerDwarven), new ItemStack(LOTRMod.daggerDwarvenPoisoned), new ItemStack(LOTRMod.battleaxeDwarven), new ItemStack(LOTRMod.hammerDwarven), new ItemStack(LOTRMod.pikeDwarven), new ItemStack(LOTRMod.swordDolGuldur), new ItemStack(LOTRMod.axeDolGuldur), new ItemStack(LOTRMod.pickaxeDolGuldur), new ItemStack(LOTRMod.daggerDolGuldur), new ItemStack(LOTRMod.daggerDolGuldurPoisoned), new ItemStack(LOTRMod.battleaxeDolGuldur), new ItemStack(LOTRMod.hammerDolGuldur), new ItemStack(LOTRMod.pikeDolGuldurUruk), new ItemStack(LOTRMod.daggerDolGuldurUruk), new ItemStack(LOTRMod.daggerDolGuldurUrukPoisoned), new ItemStack(LOTRMod.battleaxeDolGuldurUruk), new ItemStack(LOTRMod.hammerDolGuldurUruk), new ItemStack(LOTRMod.swordGundabadUruk), new ItemStack(LOTRMod.battleaxeGundabadUruk), new ItemStack(LOTRMod.hammerGundabadUruk), new ItemStack(LOTRMod.pikeGundabadUruk), new ItemStack(LOTRMod.daggerGundabadUruk), new ItemStack(LOTRMod.daggerGundabadUrukPoisoned), new ItemStack(LOTRMod.polearmAngmar), new ItemStack(LOTRMod.pikeDolGuldur)};
    private static ItemStack[] spears = new ItemStack[]{new ItemStack(LOTRMod.spearIron), new ItemStack(LOTRMod.spearBronze), new ItemStack(LOTRMod.spearStone), new ItemStack(LOTRMod.spearAngmar), new ItemStack(LOTRMod.spearOrc), new ItemStack(LOTRMod.spearDolGuldur), new ItemStack(LOTRMod.spearGundabadUruk)};
    private static ItemStack[] helmets = new ItemStack[]{new ItemStack((Item)Items.leather_helmet), new ItemStack(LOTRMod.helmetBronze), new ItemStack(LOTRMod.helmetDwarvenChainmail), new ItemStack(LOTRMod.helmetFur), new ItemStack(LOTRMod.moriahelmet), new ItemStack(LOTRMod.helmetBone), new ItemStack(LOTRMod.helmetAngmar), new ItemStack(LOTRMod.helmetOrc), new ItemStack(LOTRMod.helmetDolGuldur)};
    private static ItemStack[] bodies = new ItemStack[]{new ItemStack((Item)Items.leather_chestplate), new ItemStack(LOTRMod.bodyDwarvenChainmail), new ItemStack(LOTRMod.bodyguldururuk), new ItemStack(LOTRMod.bodyDwarven), new ItemStack(LOTRMod.bodyDwarven), new ItemStack(LOTRMod.moriabody), new ItemStack(LOTRMod.bodyBronze), new ItemStack(LOTRMod.bodyFur), new ItemStack(LOTRMod.bodyBone), new ItemStack(LOTRMod.bodyAngmar), new ItemStack(LOTRMod.bodyOrc), new ItemStack(LOTRMod.bodyDolGuldur), new ItemStack(LOTRMod.bodyGundabadUruk)};
    private static ItemStack[] legs = new ItemStack[]{new ItemStack((Item)Items.leather_leggings), new ItemStack(LOTRMod.legsDwarvenChainmail), new ItemStack(LOTRMod.legsguldururuk), new ItemStack(LOTRMod.legsDwarven), new ItemStack(LOTRMod.legsDwarven), new ItemStack(LOTRMod.morialegs), new ItemStack(LOTRMod.legsBronze), new ItemStack(LOTRMod.legsFur), new ItemStack(LOTRMod.legsBone), new ItemStack(LOTRMod.legsAngmar), new ItemStack(LOTRMod.legsOrc), new ItemStack(LOTRMod.legsDolGuldur), new ItemStack(LOTRMod.legsGundabadUruk)};
    private static ItemStack[] boots = new ItemStack[]{new ItemStack((Item)Items.leather_boots), new ItemStack(LOTRMod.bootsDwarvenChainmail), new ItemStack(LOTRMod.bootsguldururuk), new ItemStack(LOTRMod.bootsDwarven), new ItemStack(LOTRMod.bootsDwarven), new ItemStack(LOTRMod.morialegs), new ItemStack(LOTRMod.bootsBronze), new ItemStack(LOTRMod.bootsFur), new ItemStack(LOTRMod.bootsBone), new ItemStack(LOTRMod.bootsAngmar), new ItemStack(LOTRMod.bootsOrc), new ItemStack(LOTRMod.bootsDolGuldur), new ItemStack(LOTRMod.bootsGundabadUruk)};

    public LOTREntityMoriaOrc(World world) {
        super(world);
        this.addTargetTasks(true, LOTREntityAINearestAttackableTargetTroll.class);
    }

    @Override
    public EntityAIBase createOrcAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)MathHelper.getRandomIntegerInRange((Random)this.rand, (int)18, (int)20));
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(MathHelper.getRandomDoubleInRange((Random)this.rand, (double)0.19, (double)0.21));
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(weapons.length);
        this.npcItemsInv.setMeleeWeapon(weapons[i].copy());
        if (this.rand.nextInt(6) == 0) {
            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
            i = this.rand.nextInt(spears.length);
            this.npcItemsInv.setMeleeWeapon(spears[i].copy());
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        i = this.rand.nextInt(boots.length);
        this.setCurrentItemOrArmor(1, boots[i].copy());
        i = this.rand.nextInt(legs.length);
        this.setCurrentItemOrArmor(2, legs[i].copy());
        i = this.rand.nextInt(bodies.length);
        this.setCurrentItemOrArmor(3, bodies[i].copy());
        if (this.rand.nextInt(3) != 0) {
            i = this.rand.nextInt(helmets.length);
            this.setCurrentItemOrArmor(4, helmets[i].copy());
        }
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.GUNDABAD;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killMoriaOrc;
    }

    @Override
    protected void dropOrcItems(boolean flag, int i) {
        ItemStack dirt;
        if (this.rand.nextInt(6) == 0) {
            this.dropChestContents(LOTRChestContents.GUNDABAD_TENT, 1, 2 + i);
        }
        if (this.rand.nextInt(4000) == 0) {
            dirt = new ItemStack(Blocks.dirt);
            dirt.setStackDisplayName("\u00a7cSuch Wealth");
            this.entityDropItem(dirt, 0.0f);
        }
        if (this.rand.nextInt(8000) == 0) {
            dirt = new ItemStack(LOTRMod.eru);
            dirt.setStackDisplayName("\u00a7eCharm");
            this.entityDropItem(dirt, 0.0f);
        }
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if (this.isFriendly(entityplayer)) {
            if (this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
                return "gundabad/orc/hired";
            }
            if (LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 100.0f) {
                return "gundabad/orc/friendly";
            }
            return "gundabad/orc/neutral";
        }
        return "gundabad/orc/hostile";
    }

    @Override
    protected String getOrcSkirmishSpeech() {
        return "gundabad/orc/skirmish";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.GUNDABAD.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.GUNDABAD;
    }
}

