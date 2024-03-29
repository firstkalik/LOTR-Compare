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
import lotr.client.model.LOTRArmorModels;
import lotr.client.model.LOTRModelHaradRobes;
import lotr.common.item.LOTRItemHaradTurban;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LOTRModelHaradTurban
extends LOTRModelHaradRobes {
    private ModelRenderer ornament;

    public LOTRModelHaradTurban() {
        this(0.0f);
    }

    public LOTRModelHaradTurban(float f) {
        super(f);
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedHead.addBox(-5.0f, -10.0f, -5.0f, 10, 5, 10, 0.0f);
        ModelRenderer shawl = new ModelRenderer((ModelBase)this, 0, 15);
        shawl.addBox(-4.5f, -5.0f, 1.5f, 9, 6, 4, 0.25f);
        shawl.rotateAngleX = (float)Math.toRadians(13.0);
        this.bipedHead.addChild(shawl);
        this.ornament = new ModelRenderer((ModelBase)this, 0, 0);
        this.ornament.addBox(-1.0f, -9.0f, -6.0f, 2, 2, 1, 0.0f);
        this.bipedHead.addChild(this.ornament);
        this.bipedHeadwear.cubeList.clear();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.ornament.showModel = false;
        super.render(entity, f, f1, f2, f3, f4, f5);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        LOTRArmorModels.INSTANCE.copyBoxRotations(this.ornament, this.bipedHead);
        this.ornament.showModel = this.bipedHead.showModel && LOTRItemHaradTurban.hasOrnament(this.robeItem);
        this.ornament.render(f5);
    }
}

