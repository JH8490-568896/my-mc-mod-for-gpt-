package com.enderveil.endupdate.entity;

import com.enderveil.endupdate.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ShardlingEntity extends HostileEntity {
    public ShardlingEntity(EntityType<? extends ShardlingEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 8;
    }

    @Override
    protected void initGoals() {
        goalSelector.add(1, new MeleeAttackGoal(this, 1.25D, true));
        goalSelector.add(5, new WanderAroundGoal(this, 0.95D));
        goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.add(7, new LookAroundGoal(this));
        targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void dropEquipment(Random random, int lootingMultiplier, boolean allowDrops) {
        if (random.nextFloat() < 0.35F + (lootingMultiplier * 0.05F)) {
            dropItem(ModItems.SHARDLING_SCALE);
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.34D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0D)
            .add(EntityAttributes.GENERIC_ARMOR, 2.0D);
    }
}
