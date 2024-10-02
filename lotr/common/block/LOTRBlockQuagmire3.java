/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRDamage;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.LOTRPlayerData;
import lotr.common.entity.animal.LOTREntityFox;
import lotr.common.entity.npc.LOTREntityElf;
import lotr.common.fac.LOTRFaction;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockQuagmire3
extends Block {
    private float slowdownFactor;

    public LOTRBlockQuagmire3() {
        super(Material.rock);
        this.setHardness(10.0f);
        this.setResistance(2.0f);
        this.setStepSound(soundTypeSnow);
        this.setCreativeTab((CreativeTabs)LOTRCreativeTabs.tabBlock);
        this.slowdownFactor = 0.9f;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox((double)i, (double)j, (double)k, (double)((double)i + 1.0), (double)((double)j + 1.0), (double)((double)k + 1.0));
        double expand = 1.0;
        AxisAlignedBB expandedBox = boundingBox.expand(expand, expand, expand);
        for (Object obj : world.getEntitiesWithinAABB(Entity.class, expandedBox)) {
            if (!(obj instanceof Entity)) continue;
            Entity entity = (Entity)obj;
            if (entity instanceof EntityPlayer) {
                Item materialItem;
                ItemArmor armor;
                ItemStack armorItemStack;
                EntityPlayer player = (EntityPlayer)entity;
                LOTRPlayerData playerData = LOTRLevelData.getData(player);
                LOTRFaction pledgeFaction = playerData.getPledgeFaction();
                if (pledgeFaction != null) {
                    float reputation = playerData.getAlignment(pledgeFaction);
                    Set<LOTRFaction.FactionType> factionTypesSet = pledgeFaction.getFactionTypes();
                    EnumSet<LOTRFaction.FactionType> factionTypes = EnumSet.copyOf(factionTypesSet);
                    LOTRFaction.FactionType factionType = this.getPriorityFactionType(factionTypes);
                    if (reputation >= 5000.0f && factionType == LOTRFaction.FactionType.TYPE_ELF) {
                        return super.getCollisionBoundingBoxFromPool(world, i, j, k);
                    }
                }
                if ((armorItemStack = player.getEquipmentInSlot(1)) == null || !(armorItemStack.getItem() instanceof ItemArmor) || (materialItem = (armor = (ItemArmor)armorItemStack.getItem()).getArmorMaterial().func_151685_b()) != Items.leather) continue;
                return super.getCollisionBoundingBoxFromPool(world, i, j, k);
            }
            if (entity instanceof LOTREntityElf) {
                return super.getCollisionBoundingBoxFromPool(world, i, j, k);
            }
            if (!(entity instanceof LOTREntityFox)) continue;
            return super.getCollisionBoundingBoxFromPool(world, i, j, k);
        }
        return null;
    }

    private LOTRFaction.FactionType getPriorityFactionType(EnumSet<LOTRFaction.FactionType> factionTypes) {
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_ELF)) {
            return LOTRFaction.FactionType.TYPE_ELF;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_DWARF)) {
            return LOTRFaction.FactionType.TYPE_DWARF;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_MAN)) {
            return LOTRFaction.FactionType.TYPE_MAN;
        }
        if (factionTypes.contains((Object)LOTRFaction.FactionType.TYPE_ORC)) {
            return LOTRFaction.FactionType.TYPE_ORC;
        }
        return LOTRFaction.FactionType.TYPE_FREE;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack currentItem = player.getCurrentEquippedItem();
        if (currentItem != null && currentItem.getItem() == Items.bucket && world.getBlock(x, y, z) == this) {
            if (!world.isRemote) {
                world.setBlockToAir(x, y, z);
                if (!player.capabilities.isCreativeMode) {
                    --currentItem.stackSize;
                    if (currentItem.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(LOTRMod.bucketSnow));
                    } else {
                        player.inventory.addItemStackToInventory(new ItemStack(LOTRMod.bucketSnow));
                    }
                    ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                }
            }
            return true;
        }
        return false;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        if (entity instanceof EntityPlayerMP) {
            LOTRDamage.doFrostDamage((EntityPlayerMP)entity);
            entity.attackEntityFrom(LOTRDamage.frost, 0.15f);
        }
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
            float currentSpeed = ((EntityLivingBase)entity).getAIMoveSpeed();
            ((EntityLivingBase)entity).setAIMoveSpeed(currentSpeed * this.slowdownFactor);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return world.getBlock(x, y, z) != this && super.shouldSideBeRendered(world, x, y, z, side);
    }
}

