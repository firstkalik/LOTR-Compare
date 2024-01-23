/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.item;

import java.util.List;
import lotr.client.LOTRClientProxy;
import lotr.client.render.item.LOTRRenderLargeItem;
import lotr.common.LOTRConfig;
import lotr.common.entity.npc.LOTREntityOrc;
import lotr.common.item.LOTRItemSword;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderElvenBlade
implements IItemRenderer {
    private double distance;
    private LOTRRenderLargeItem largeItemRenderer;
    private LOTRRenderLargeItem.ExtraLargeIconToken tokenGlowing;

    public LOTRRenderElvenBlade(double d, LOTRRenderLargeItem large) {
        this.distance = d;
        this.largeItemRenderer = large;
        if (this.largeItemRenderer != null) {
            this.tokenGlowing = this.largeItemRenderer.extraIcon("glowing");
        }
    }

    public boolean handleRenderType(ItemStack itemstack, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack itemstack, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        EntityLivingBase entity = (EntityLivingBase)data[1];
        Item item = itemstack.getItem();
        entity.worldObj.theProfiler.startSection("elvenBlade");
        boolean glows = false;
        List orcs = entity.worldObj.getEntitiesWithinAABB(LOTREntityOrc.class, entity.boundingBox.expand(this.distance, this.distance, this.distance));
        if (!orcs.isEmpty()) {
            glows = true;
        }
        if (glows) {
            GL11.glDisable((int)2896);
        }
        if (this.largeItemRenderer != null) {
            if (glows) {
                this.largeItemRenderer.renderLargeItem(this.tokenGlowing);
            } else {
                this.largeItemRenderer.renderLargeItem();
            }
        } else {
            IIcon icon = ((EntityLivingBase)data[1]).getItemIcon(itemstack, 0);
            if (glows) {
                icon = ((LOTRItemSword)item).glowingIcon;
            }
            icon = RenderBlocks.getInstance().getIconSafe(icon);
            float minU = icon.getMinU();
            float maxU = icon.getMaxU();
            float minV = icon.getMinV();
            float maxV = icon.getMaxV();
            int width = icon.getIconWidth();
            int height = icon.getIconWidth();
            Tessellator tessellator = Tessellator.instance;
            ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)maxU, (float)minV, (float)minU, (float)maxV, (int)width, (int)height, (float)0.0625f);
        }
        if (itemstack != null && itemstack.hasEffect(0)) {
            LOTRClientProxy.renderEnchantmentEffect();
        }
        if (glows) {
            GL11.glEnable((int)2896);
            if (LOTRConfig.elvenBladeGlow) {
                for (int i = 0; i < 4; ++i) {
                    LOTRClientProxy.renderEnchantmentEffect();
                }
            }
        }
        GL11.glDisable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        entity.worldObj.theProfiler.endSection();
    }
}

