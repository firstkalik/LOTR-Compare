/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package lotr.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotr.client.render.entity.LOTRHuornTextures;
import lotr.common.entity.npc.LOTREntityHuornBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class LOTRModelHuorn
extends ModelBase {
    private List woodBlocks = new ArrayList();
    private List leafBlocks = new ArrayList();
    private ModelRenderer face;
    private int baseX = 2;
    private int baseY = 0;
    private int baseZ = 2;
    private Random rand = new Random();

    public LOTRModelHuorn() {
        int j;
        this.rand.setSeed(100L);
        int height = 6;
        int leafStart = 3;
        int leafRangeMin = 0;
        for (j = this.baseY - leafStart + height; j <= this.baseY + height; ++j) {
            int j1 = j - (this.baseY + height);
            int leafRange = leafRangeMin + 1 - j1 / 2;
            for (int i = this.baseX - leafRange; i <= this.baseX + leafRange; ++i) {
                int i1 = i - this.baseX;
                for (int k = this.baseZ - leafRange; k <= this.baseZ + leafRange; ++k) {
                    int k1 = k - this.baseZ;
                    if (Math.abs(i1) == leafRange && Math.abs(k1) == leafRange && (this.rand.nextInt(2) == 0 || j1 == 0)) continue;
                    ModelRenderer block = new ModelRenderer((ModelBase)this, 0, 0);
                    block.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16);
                    block.setRotationPoint((float)i * 16.0f, 16.0f - (float)j * 16.0f, (float)k * 16.0f);
                    this.leafBlocks.add(block);
                }
            }
        }
        for (j = 0; j < height; ++j) {
            ModelRenderer block = new ModelRenderer((ModelBase)this, 0, 0);
            block.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16);
            block.setRotationPoint((float)this.baseX * 16.0f, 16.0f - (float)j * 16.0f, (float)this.baseZ * 16.0f);
            this.woodBlocks.add(block);
        }
        this.face = new ModelRenderer((ModelBase)this, 0, 0);
        this.face.addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16, 0.5f);
        this.face.setRotationPoint(0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        int i;
        ModelRenderer block;
        LOTREntityHuornBase huorn = (LOTREntityHuornBase)entity;
        if (huorn.isHuornActive()) {
            this.face.render(f5);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)2884);
        GL11.glTranslatef((float)(-((float)this.baseX)), (float)(-((float)this.baseY)), (float)(-((float)this.baseZ)));
        for (i = 0; i < this.woodBlocks.size(); ++i) {
            if (i == 0) {
                LOTRHuornTextures.INSTANCE.bindWoodTexture(huorn);
            }
            block = (ModelRenderer)this.woodBlocks.get(i);
            block.render(f5);
        }
        for (i = 0; i < this.leafBlocks.size(); ++i) {
            if (i == 0) {
                LOTRHuornTextures.INSTANCE.bindLeafTexture(huorn);
            }
            block = (ModelRenderer)this.leafBlocks.get(i);
            block.render(f5);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)2884);
        GL11.glPopMatrix();
    }
}

