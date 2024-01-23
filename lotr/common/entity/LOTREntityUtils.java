/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 */
package lotr.common.entity;

import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;

public class LOTREntityUtils {
    public static EntityAITasks.EntityAITaskEntry removeAITask(EntityCreature entity, Class taskClass) {
        EntityAITasks.EntityAITaskEntry taskEntry;
        int i;
        for (i = 0; i < entity.tasks.taskEntries.size(); ++i) {
            taskEntry = (EntityAITasks.EntityAITaskEntry)entity.tasks.taskEntries.get(i);
            if (!taskClass.isAssignableFrom(taskEntry.action.getClass())) continue;
            entity.tasks.removeTask(taskEntry.action);
            return taskEntry;
        }
        for (i = 0; i < entity.targetTasks.taskEntries.size(); ++i) {
            taskEntry = (EntityAITasks.EntityAITaskEntry)entity.targetTasks.taskEntries.get(i);
            if (!taskClass.isAssignableFrom(taskEntry.action.getClass())) continue;
            entity.targetTasks.removeTask(taskEntry.action);
            return taskEntry;
        }
        return null;
    }
}

