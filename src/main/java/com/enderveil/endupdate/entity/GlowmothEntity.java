package com.enderveil.endupdate.entity;

import com.enderveil.endupdate.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GlowmothEntity extends PathAwareEntity {
    public GlowmothEntity(EntityType<? extends GlowmothEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 4;
    }

    @Override
    protected void initGoals() {
        goalSelector.add(1, new WanderAroundGoal(this, 0.9D));
        goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
        goalSelector.add(3, new LookAroundGoal(this));
    }

    @Override
    protected void dropEquipment(Random random, int lootingMultiplier, boolean allowDrops) {
        if (random.nextFloat() < 0.45F) {
            dropItem(ModItems.GLOWMOTH_WING);
        }
        if (random.nextFloat() < 0.15F) {
            dropItem(ModItems.CHORUS_RESIN);
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.26D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D);
    }
}
