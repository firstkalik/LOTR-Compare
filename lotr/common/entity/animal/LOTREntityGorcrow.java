/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.world.World
 */
package lotr.common.entity.animal;

import lotr.common.entity.animal.LOTREntityBird;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.world.World;

public class LOTREntityGorcrow
extends LOTREntityBird {
    public static float GORCROW_SCALE = 1.4f;

    public LOTREntityGorcrow(World world) {
        super(world);
        this.setSize(this.width * GORCROW_SCALE, this.height * GORCROW_SCALE);
    }

    @Override
    public String getBirdTextureDir() {
        return "gorcrow";
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.setBirdType(LOTREntityBird.BirdType.CROW);
        return data;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.75f;
    }
}

