/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import java.util.List;
import lotr.client.model.LOTRModelBiped;
import lotr.common.item.LOTRItemPartyHat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LOTRModelPartyHat
extends LOTRModelBiped {
    private ItemStack hatItem;

    public LOTRModelPartyHat() {
        this(0.0f);
    }

    public LOTRModelPartyHat(float f) {
        super(f);
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedHead.addBox(-4.0f, -14.0f, -4.0f, 8, 8, 8, f);
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
        int hatColor = LOTRItemPartyHat.getHatColor(this.hatItem);
        float r = (float)(hatColor >> 16 & 0xFF) / 255.0f;
        float g = (float)(hatColor >> 8 & 0xFF) / 255.0f;
        float b = (float)(hatColor & 0xFF) / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
        this.bipedHead.render(f5);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

