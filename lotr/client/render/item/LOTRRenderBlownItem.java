/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class LOTRRenderBlownItem
implements IItemRenderer {
    public boolean handleRenderType(ItemStack itemstack, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack itemstack, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        EntityLivingBase equipper = (EntityLivingBase)data[1];
        Item item = itemstack.getItem();
        Tessellator tessellator = Tessellator.instance;
        if (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0 || equipper != Minecraft.getMinecraft().thePlayer) {
            GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
            GL11.glTranslatef((float)-1.35f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)0.3f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.15f);
        }
        int passes = item.getRenderPasses(itemstack.getItemDamage());
        for (int pass = 0; pass < passes; ++pass) {
            int color = item.getColorFromItemStack(itemstack, pass);
            float r = (float)(color >> 16 & 0xFF) / 255.0f;
            float g = (float)(color >> 8 & 0xFF) / 255.0f;
            float b = (float)(color & 0xFF) / 255.0f;
            GL11.glColor3f((float)r, (float)g, (float)b);
            IIcon icon = equipper.getItemIcon(itemstack, pass);
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            ItemRenderer.renderItemIn2D((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.0625f);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)32826);
    }
}

