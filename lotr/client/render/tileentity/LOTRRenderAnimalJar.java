/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.tileentity;

import lotr.client.LOTRTickHandlerClient;
import lotr.client.render.entity.LOTRRenderBird;
import lotr.common.item.LOTRItemAnimalJar;
import lotr.common.tileentity.LOTRTileEntityAnimalJar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderAnimalJar
extends TileEntitySpecialRenderer
implements IItemRenderer {
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        LOTRTileEntityAnimalJar jar = (LOTRTileEntityAnimalJar)tileentity;
        Entity jarEntity = jar.getOrCreateJarEntity();
        if (jarEntity != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)jar.getEntityBobbing(f), (float)0.0f);
            if (jarEntity instanceof EntityLivingBase) {
                EntityLivingBase jarLiving = (EntityLivingBase)jarEntity;
                EntityLivingBase viewer = RenderManager.instance.livingPlayer;
                if (jar.isEntityWatching()) {
                    Vec3 viewerPos = viewer.getPosition(f);
                    Vec3 entityPos = jarLiving.getPosition(f);
                    double dx = entityPos.xCoord - viewerPos.xCoord;
                    double dz = entityPos.zCoord - viewerPos.zCoord;
                    float lookYaw = (float)Math.toDegrees(Math.atan2(dz, dx));
                    jarLiving.prevRotationYaw = lookYaw += 90.0f;
                    jarLiving.rotationYaw = lookYaw;
                }
                jarLiving.renderYawOffset = jarLiving.rotationYaw;
                jarLiving.prevRenderYawOffset = jarLiving.prevRotationYaw;
            }
            RenderManager.instance.renderEntityStatic(jarEntity, f, false);
            GL11.glPopMatrix();
        }
    }

    public boolean handleRenderType(ItemStack itemstack, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack itemstack, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        EntityLivingBase viewer = Minecraft.getMinecraft().renderViewEntity;
        Entity jarEntity = LOTRItemAnimalJar.getItemJarEntity(itemstack, viewer.worldObj);
        if (jarEntity != null) {
            jarEntity.setLocationAndAngles(0.0, 0.0, 0.0, 0.0f, 0.0f);
            jarEntity.ticksExisted = viewer.ticksExisted;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPushAttrib((int)1048575);
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                LOTRRenderBird.renderStolenItem = false;
            }
            RenderManager.instance.renderEntityWithPosYaw(jarEntity, 0.0, 0.0, 0.0, 0.0f, LOTRTickHandlerClient.renderTick);
            LOTRRenderBird.renderStolenItem = true;
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        GL11.glEnable((int)3008);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        RenderBlocks.getInstance().renderBlockAsItem(Block.getBlockFromItem((Item)itemstack.getItem()), itemstack.getItemDamage(), 1.0f);
        GL11.glDisable((int)3042);
    }
}

