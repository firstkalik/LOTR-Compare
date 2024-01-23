/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import java.util.List;
import lotr.client.model.LOTRModelBiped;
import lotr.common.item.LOTRItemLeatherHat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LOTRModelLeatherHat
extends LOTRModelBiped {
    private static ItemStack feather = new ItemStack(Items.feather);
    private ItemStack hatItem;

    public LOTRModelLeatherHat() {
        this(0.0f);
    }

    public LOTRModelLeatherHat(float f) {
        super(f);
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedHead.addBox(-6.0f, -9.0f, -6.0f, 12, 2, 12, f);
        this.bipedHead.setTextureOffset(0, 14).addBox(-4.0f, -13.0f, -4.0f, 8, 4, 8, f);
        this.bipedHeadwear.cubeList.clear();
        this.bipedBody.cubeList.clear();
        this.bipedRightArm.cubeList.clear();
        this.bipedLeftArm.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();
    }

    public void setHatItem(ItemStack itemstack) {
        this.hatItem = itemstack;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        int hatColor = LOTRItemLeatherHat.getHatColor(this.hatItem);
        float r = (float)(hatColor >> 16 & 0xFF) / 255.0f;
        float g = (float)(hatColor >> 8 & 0xFF) / 255.0f;
        float b = (float)(hatColor & 0xFF) / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
        this.bipedHead.render(f5);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (LOTRItemLeatherHat.hasFeather(this.hatItem)) {
            this.bipedHead.postRender(f5);
            GL11.glScalef((float)0.375f, (float)0.375f, (float)0.375f);
            GL11.glRotatef((float)130.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.25f, (float)1.5f, (float)0.75f);
            GL11.glRotatef((float)-45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            int featherColor = LOTRItemLeatherHat.getFeatherColor(this.hatItem);
            r = (float)(featherColor >> 16 & 0xFF) / 255.0f;
            g = (float)(featherColor >> 8 & 0xFF) / 255.0f;
            b = (float)(featherColor & 0xFF) / 255.0f;
            GL11.glColor3f((float)r, (float)g, (float)b);
            if (entity instanceof EntityLivingBase) {
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase)entity, feather, 0);
            } else {
                RenderManager.instance.itemRenderer.renderItem((EntityLivingBase)Minecraft.getMinecraft().thePlayer, feather, 0);
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
        GL11.glPopMatrix();
    }
}

