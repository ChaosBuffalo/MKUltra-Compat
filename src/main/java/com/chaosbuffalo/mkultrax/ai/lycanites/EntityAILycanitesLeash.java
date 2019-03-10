package com.chaosbuffalo.mkultrax.ai.lycanites;

import com.chaosbuffalo.mkultra.core.IMobData;
import com.lycanitesmobs.core.entity.EntityCreatureBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.math.BlockPos;

public class EntityAILycanitesLeash  extends EntityAIBase {
    private final EntityCreatureBase creature;
    private final double movementSpeed;
    private final IMobData mobData;
    private static final double LEASH_RANGE = 40.0;
    private boolean doReturn;
    private int ticks_returning;

    public EntityAILycanitesLeash(EntityCreatureBase entity, IMobData mobData, double movementSpeed) {
        this.creature = entity;
        this.movementSpeed = movementSpeed;
        this.setMutexBits(1);
        this.mobData = mobData;
        this.ticks_returning = 0;
    }

    public boolean shouldExecute() {
        if (mobData.hasSpawnPoint()){
            double distFromSpawn = creature.getDistanceSq(mobData.getSpawnPoint());
            if (distFromSpawn <= 3.0){
                return false;
            }
            return distFromSpawn > LEASH_RANGE * LEASH_RANGE || creature.getAttackTarget() == null;
        }
        return false;
    }

    public boolean shouldContinueExecuting() {
        return !this.creature.getNavigator().noPath() ;
    }

    public void startExecuting() {
        BlockPos spawnPoint = mobData.getSpawnPoint();
        double distFromSpawn = creature.getDistanceSq(spawnPoint);
        IAttributeInstance followRange = creature.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        if (distFromSpawn >= followRange.getAttributeValue() * followRange.getAttributeValue()){
            creature.setPositionAndUpdate(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());
        }
        this.creature.getNavigator().tryMoveToXYZ(spawnPoint.getX(), spawnPoint.getY(),
                spawnPoint.getZ(), this.movementSpeed);
        this.creature.setAttackTarget(null);
        this.creature.setRevengeTarget(null);
    }

}
