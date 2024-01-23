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

public class LOTRModelBlackUrukHelmet
extends LOTRModelBiped {
    private ModelRenderer crest;

    public LOTRModelBlackUrukHelmet() {
        this(0.0f);
    }

    public LOTRModelBlackUrukHelmet(float f) {
        super(f);
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.crest = new ModelRenderer((ModelBase)this, 32, 0);
        this.crest.addBox(-8.0f, -16.0f, -3.0f, 16, 10, 0, 0.0f);
        this.crest.rotateAngleX = (float)Math.toRadians(-20.0);
        this.bipedHead.addChild(this.crest);
        this.bipedHeadwear.cubeList.clear();
        this.bipedBody.cubeList.clear();
        this.bipedRightArm.cubeList.clear();
        this.bipedLeftArm.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();
    }
}

