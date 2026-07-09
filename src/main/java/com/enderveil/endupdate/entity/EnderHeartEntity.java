package com.enderveil.endupdate.entity;

import com.enderveil.endupdate.registry.ModEntities;
import com.enderveil.endupdate.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EnderHeartEntity extends HostileEntity {
    private int attackCooldown = 60;
    private boolean phaseTwo = false;

    public EnderHeartEntity(EntityType<? extends EnderHeartEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 50;
    }

    @Override
    protected void initGoals() {
        goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
        goalSelector.add(4, new WanderAroundGoal(this, 0.65D));
        goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 24.0F));
        goalSelector.add(6, new LookAroundGoal(this));
        targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient) {
            if (!phaseTwo && getHealth() <= getMaxHealth() * 0.5F) {
                phaseTwo = true;
                summonSupport();
            }
            if (getTarget() != null) {
                attackCooldown--;
                if (attackCooldown <= 0) {
                    attackCooldown = phaseTwo ? 35 : 60;
                    launchBurst();
                }
            }
        }
    }

    private void summonSupport() {
        if (!(getWorld() instanceof ServerWorld serverWorld)) return;
        for (int i = 0; i < 4; i++) {
            ShardlingEntity shardling = ModEntities.SHARDLING.create(serverWorld, SpawnReason.MOB_SUMMONED);
            if (shardling != null) {
                BlockPos pos = getBlockPos().add((i % 2 == 0 ? 1 : -1) * (2 + i), 0, (i < 2 ? 1 : -1) * 2);
                shardling.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, random.nextFloat() * 360.0F, 0.0F);
                serverWorld.spawnEntity(shardling);
            }
        }
    }

    private void launchBurst() {
        if (!(getTarget() instanceof PlayerEntity player)) return;
        if (!(getWorld() instanceof ServerWorld serverWorld)) return;

        Vec3d start = getPos().add(0.0D, 1.8D, 0.0D);
        Vec3d direction = player.getEyePos().subtract(start).normalize();

        SmallFireballEntity projectile = new SmallFireballEntity(serverWorld, this, direction.x, direction.y, direction.z);
        projectile.refreshPositionAndAngles(start.x, start.y, start.z, getYaw(), getPitch());
        serverWorld.spawnEntity(projectile);

        if (phaseTwo) {
            SmallFireballEntity second = new SmallFireballEntity(serverWorld, this, direction.x + random.nextGaussian() * 0.06D, direction.y, direction.z + random.nextGaussian() * 0.06D);
            second.refreshPositionAndAngles(start.x, start.y, start.z, getYaw(), getPitch());
            serverWorld.spawnEntity(second);
        }
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        if (causedByPlayer) {
            dropStack(new ItemStack(ModItems.BOSS_SIGIL));
            dropStack(new ItemStack(ModItems.ANCIENT_CORE, 2));
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 220.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.26D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D)
            .add(EntityAttributes.GENERIC_ARMOR, 12.0D)
            .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.75D);
    }
}
