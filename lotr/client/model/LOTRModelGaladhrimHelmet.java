/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package lotr.client.model;

import java.util.List;
import lotr.client.model.LOTRModelBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class LOTRModelGaladhrimHelmet
extends LOTRModelBiped {
    public LOTRModelGaladhrimHelmet() {
        this(0.0f);
    }

    public LOTRModelGaladhrimHelmet(float f) {
        super(f);
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        ModelRenderer horn = new ModelRenderer((ModelBase)this, 32, 0);
        horn.addBox(-0.5f, -9.0f - f, 2.0f - f, 1, 3, 3, 0.0f);
        horn.setTextureOffset(32, 6).addBox(-0.5f, -10.0f - f, 3.5f - f, 1, 1, 3, 0.0f);
        horn.setTextureOffset(32, 10).addBox(-0.5f, -11.0f - f, 5.5f - f, 1, 1, 4, 0.0f);
        horn.rotateAngleX = (float)Math.toRadians(45.0);
        this.bipedHead.addChild(horn);
        this.bipedHeadwear.cubeList.clear();
        this.bipedBody.cubeList.clear();
        this.bipedRightArm.cubeList.clear();
        this.bipedLeftArm.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();
    }
}

