/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderInvTableCommand
implements IItemRenderer {
    public boolean handleRenderType(ItemStack itemstack, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack itemstack, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        Block block = Block.getBlockFromItem((Item)itemstack.getItem());
        int meta = itemstack.getItemDamage();
        RenderBlocks rb = (RenderBlocks)data[0];
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)-0.18f, (float)0.0f);
        float scale = 0.6f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        rb.renderBlockAsItem(block, meta, 1.0f);
        GL11.glPopMatrix();
    }
}

