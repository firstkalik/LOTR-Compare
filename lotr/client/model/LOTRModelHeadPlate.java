/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import lotr.client.LOTRTickHandlerClient;
import lotr.client.model.LOTRModelHuman;
import lotr.client.render.LOTRRenderBlocks;
import lotr.common.LOTRCommonProxy;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTRPlateFallingInfo;
import lotr.common.item.LOTRItemPlate;
import lotr.common.tileentity.LOTRTileEntityPlate;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LOTRModelHeadPlate
extends LOTRModelHuman {
    private RenderBlocks blockRenderer = new RenderBlocks();
    private LOTRTileEntityPlate plateTE = new LOTRTileEntityPlate();
    private Block plateBlock;

    public void setPlateItem(ItemStack itemstack) {
        this.plateBlock = itemstack.getItem() instanceof LOTRItemPlate ? ((LOTRItemPlate)itemstack.getItem()).plateBlock : LOTRMod.plateBlock;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        ItemStack heldItem;
        float tick = LOTRTickHandlerClient.renderTick;
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float headRotateY = f3;
        LOTRPlateFallingInfo fallingInfo = entity == null ? null : LOTRPlateFallingInfo.getOrCreateFor(entity, false);
        float fallOffset = fallingInfo == null ? 0.0f : fallingInfo.getPlateOffsetY(tick);
        GL11.glEnable((int)32826);
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)headRotateY, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(1.0f - this.bipedHead.rotationPointY / 16.0f), (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)(fallOffset * 0.5f), (float)0.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        World world = entity == null ? LOTRMod.proxy.getClientWorld() : entity.worldObj;
        LOTRRenderBlocks.renderEntityPlate((IBlockAccess)world, 0, 0, 0, this.plateBlock, this.blockRenderer);
        if (entity instanceof EntityLivingBase && (heldItem = ((EntityLivingBase)entity).getHeldItem()) != null && LOTRTileEntityPlate.isValidFoodItem(heldItem)) {
            ItemStack heldItemMinusOne = heldItem.copy();
            --heldItemMinusOne.stackSize;
            if (heldItemMinusOne.stackSize > 0) {
                this.plateTE.setFoodItem(heldItemMinusOne);
                this.plateTE.plateFallInfo = fallingInfo;
                TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.plateTE, -0.5, -0.5, -0.5, tick);
                this.plateTE.plateFallInfo = null;
                GL11.glDisable((int)2884);
            }
        }
        GL11.glPopMatrix();
        GL11.glDisable((int)32826);
    }
}

