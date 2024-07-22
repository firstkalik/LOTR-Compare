/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.entity;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import lotr.client.model.LOTRArmorModels;
import lotr.client.model.LOTRModelBiped;
import lotr.client.render.LOTRRenderShield;
import lotr.client.render.entity.LOTRNPCRendering;
import lotr.common.LOTRShields;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.entity.npc.LOTRHiredNPCInfo;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public abstract class LOTRRenderBiped
extends RenderBiped {
    public static final float PLAYER_SCALE = 0.9375f;
    private ModelBiped capeModel = new LOTRModelBiped();
    public ModelBiped npcRenderPassModel;

    public LOTRRenderBiped(ModelBiped model, float f) {
        super(model, f);
    }

    protected void func_82421_b() {
        this.field_82423_g = new LOTRModelBiped(1.0f);
        this.field_82425_h = new LOTRModelBiped(0.5f);
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        return super.getEntityTexture(entity);
    }

    public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1) {
        super.doRender(entity, d, d1, d2, f, f1);
        if (Minecraft.isGuiEnabled() && entity instanceof LOTREntityNPC) {
            LOTREntityNPC npc = (LOTREntityNPC)entity;
            if (npc.hiredNPCInfo.getHiringPlayer() == this.renderManager.livingPlayer) {
                LOTRNPCRendering.renderHiredIcon((EntityLivingBase)npc, d, d1 + 0.5, d2);
                LOTRNPCRendering.renderNPCHealthBar((EntityLivingBase)npc, d, d1 + 0.5, d2);
            }
            LOTRNPCRendering.renderQuestBook(npc, d, d1, d2);
            LOTRNPCRendering.renderQuestOffer(npc, d, d1, d2);
        }
    }

    public int shouldRenderPass(EntityLiving entity, int pass, float f) {
        ItemStack armor = entity.getEquipmentInSlot(3 - pass + 1);
        int specialArmorResult = LOTRArmorModels.INSTANCE.getEntityArmorModel((RendererLivingEntity)this, this.modelBipedMain, (EntityLivingBase)entity, armor, pass);
        if (specialArmorResult > 0) {
            return specialArmorResult;
        }
        int result = super.shouldRenderPass(entity, pass, f);
        return result;
    }

    public void setRenderPassModel(ModelBase model) {
        super.setRenderPassModel(model);
        if (model instanceof ModelBiped) {
            this.npcRenderPassModel = (ModelBiped)model;
        }
    }

    public void func_82408_c(EntityLiving entity, int pass, float f) {
        super.func_82408_c(entity, pass, f);
    }

    protected void func_82420_a(EntityLiving entity, ItemStack itemstack) {
        LOTRArmorModels.INSTANCE.setupModelForRender(this.modelBipedMain, this.modelBipedMain, (EntityLivingBase)entity);
        LOTRArmorModels.INSTANCE.setupModelForRender(this.field_82425_h, this.modelBipedMain, (EntityLivingBase)entity);
        LOTRArmorModels.INSTANCE.setupModelForRender(this.field_82423_g, this.modelBipedMain, (EntityLivingBase)entity);
        if (this.npcRenderPassModel != null) {
            LOTRArmorModels.INSTANCE.setupModelForRender(this.npcRenderPassModel, this.modelBipedMain, (EntityLivingBase)entity);
        }
    }

    protected void renderEquippedItems(EntityLivingBase entity, float f) {
        ItemStack heldItem;
        IItemRenderer customRenderer;
        ItemStack heldItemLeft;
        float f1;
        boolean is3D;
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack headItem = entity.getEquipmentInSlot(4);
        if (headItem != null) {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625f);
            IItemRenderer customRenderer2 = MinecraftForgeClient.getItemRenderer((ItemStack)headItem, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer2 != null && customRenderer2.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, headItem, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (headItem.getItem() instanceof ItemBlock) {
                if (is3D || RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)headItem.getItem()).getRenderType())) {
                    f1 = 0.625f;
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                }
                this.renderManager.itemRenderer.renderItem(entity, headItem, 0);
            } else if (headItem.getItem() == Items.skull) {
                f1 = 1.0625f;
                GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                GameProfile gameprofile = null;
                if (headItem.hasTagCompound()) {
                    NBTTagCompound nbttagcompound = headItem.getTagCompound();
                    if (nbttagcompound.hasKey("SkullOwner", (int)new NBTTagCompound().getId())) {
                        gameprofile = NBTUtil.func_152459_a((NBTTagCompound)nbttagcompound.getCompoundTag("SkullOwner"));
                    } else if (nbttagcompound.hasKey("SkullOwner", (int)new NBTTagString().getId()) && !StringUtils.isNullOrEmpty((String)nbttagcompound.getString("SkullOwner"))) {
                        gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
                    }
                }
                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5f, 0.0f, -0.5f, 1, 180.0f, headItem.getItemDamage(), gameprofile);
            }
            GL11.glPopMatrix();
        }
        if ((heldItem = entity.getHeldItem()) != null) {
            float f12;
            GL11.glPushMatrix();
            if (this.mainModel.isChild) {
                float f13 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f13, (float)f13, (float)f13);
            }
            this.modelBipedMain.bipedRightArm.postRender(0.0625f);
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)heldItem, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, heldItem, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (heldItem.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)heldItem.getItem()).getRenderType()))) {
                f12 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)this.getHeldItemYTranslation(), (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f12 *= 0.75f)), (float)(-f12), (float)f12);
            } else if (heldItem.getItem() == Items.bow) {
                f12 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)this.getHeldItemYTranslation(), (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f12, (float)(-f12), (float)f12);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (heldItem.getItem().isFull3D()) {
                f12 = 0.625f;
                if (heldItem.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-this.getHeldItemYTranslation()), (float)0.0f);
                }
                GL11.glTranslatef((float)0.0f, (float)this.getHeldItemYTranslation(), (float)0.0f);
                GL11.glScalef((float)f12, (float)(-f12), (float)f12);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f12 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)this.getHeldItemYTranslation(), (float)-0.1875f);
                GL11.glScalef((float)f12, (float)f12, (float)f12);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            this.renderManager.itemRenderer.renderItem(entity, heldItem, 0);
            if (heldItem.getItem().requiresMultipleRenderPasses()) {
                for (int x = 1; x < heldItem.getItem().getRenderPasses(heldItem.getItemDamage()); ++x) {
                    this.renderManager.itemRenderer.renderItem(entity, heldItem, x);
                }
            }
            GL11.glPopMatrix();
        }
        if ((heldItemLeft = ((LOTREntityNPC)entity).getHeldItemLeft()) != null) {
            float f14;
            GL11.glPushMatrix();
            if (this.mainModel.isChild) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
            }
            this.modelBipedMain.bipedLeftArm.postRender(0.0625f);
            GL11.glTranslatef((float)0.0625f, (float)0.4375f, (float)0.0625f);
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)heldItemLeft, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, heldItemLeft, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (heldItemLeft.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d((int)Block.getBlockFromItem((Item)heldItemLeft.getItem()).getRenderType()))) {
                f14 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)this.getHeldItemYTranslation(), (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f14 *= 0.75f)), (float)(-f14), (float)f14);
            } else if (heldItemLeft.getItem().isFull3D()) {
                f14 = 0.625f;
                if (heldItemLeft.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-this.getHeldItemYTranslation()), (float)0.0f);
                }
                GL11.glTranslatef((float)0.0f, (float)this.getHeldItemYTranslation(), (float)0.0f);
                GL11.glScalef((float)f14, (float)(-f14), (float)f14);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f14 = 0.3175f;
                GL11.glTranslatef((float)0.0f, (float)this.getHeldItemYTranslation(), (float)0.0f);
                GL11.glScalef((float)f14, (float)(-f14), (float)f14);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            this.renderManager.itemRenderer.renderItem(entity, heldItemLeft, 0);
            if (heldItemLeft.getItem().requiresMultipleRenderPasses()) {
                for (int i = 1; i < heldItemLeft.getItem().getRenderPasses(heldItemLeft.getItemDamage()); ++i) {
                    this.renderManager.itemRenderer.renderItem(entity, heldItemLeft, i);
                }
            }
            GL11.glPopMatrix();
        }
        this.renderNPCCape((LOTREntityNPC)entity);
        this.renderNPCShield((LOTREntityNPC)entity);
    }

    protected void renderNPCCape(LOTREntityNPC entity) {
        ResourceLocation capeTexture = entity.npcCape;
        if (capeTexture != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)-10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            this.bindTexture(capeTexture);
            this.capeModel.renderCloak(0.0625f);
            GL11.glPopMatrix();
        }
    }

    protected void renderNPCShield(LOTREntityNPC entity) {
        LOTRShields shield = entity.npcShield;
        if (shield != null) {
            LOTRRenderShield.renderShield(shield, (EntityLivingBase)entity, this.modelBipedMain);
        }
    }

    protected void preRenderCallback(EntityLivingBase entity, float f) {
        float f1 = 0.9375f;
        GL11.glScalef((float)f1, (float)f1, (float)f1);
    }

    public float getHeldItemYTranslation() {
        return 0.1875f;
    }
}

