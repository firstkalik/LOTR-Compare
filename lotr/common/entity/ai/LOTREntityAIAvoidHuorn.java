/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntitySenses
 */
package lotr.common.entity.ai;

import cpw.mods.fml.common.FMLLog;
import java.lang.reflect.Field;
import lotr.common.LOTRReflection;
import lotr.common.entity.npc.LOTREntityHuornBase;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntitySenses;

public class LOTREntityAIAvoidHuorn
extends EntityAIAvoidEntity {
    public LOTREntityAIAvoidHuorn(final EntityCreature entity, float range, double near, double far) {
        super(entity, LOTREntityHuornBase.class, range, near, far);
        try {
            IEntitySelector replaceSelect = new IEntitySelector(){

                public boolean isEntityApplicable(Entity target) {
                    if (target.isEntityAlive() && entity.getEntitySenses().canSee(target)) {
                        LOTREntityHuornBase huorn = (LOTREntityHuornBase)target;
                        return huorn.isHuornActive();
                    }
                    return false;
                }
            };
            for (Field f : EntityAIAvoidEntity.class.getFields()) {
                Object inst = f.get((Object)this);
                if (inst != this.field_98218_a) continue;
                LOTRReflection.unlockFinalField(f);
                f.set((Object)this, (Object)replaceSelect);
                break;
            }
        }
        catch (Exception e) {
            FMLLog.warning((String)"LOTR: Error constructing Avoid Huorn AI", (Object[])new Object[0]);
            e.printStackTrace();
        }
    }

}

