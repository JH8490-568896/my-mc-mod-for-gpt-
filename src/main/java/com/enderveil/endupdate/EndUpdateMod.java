package com.enderveil.endupdate;

import com.enderveil.endupdate.config.EndUpdateConfig;
import com.enderveil.endupdate.effect.ModEffects;
import com.enderveil.endupdate.entity.EnderHeartEntity;
import com.enderveil.endupdate.registry.ModBlocks;
import com.enderveil.endupdate.registry.ModEntities;
import com.enderveil.endupdate.registry.ModFeatures;
import com.enderveil.endupdate.registry.ModItems;
import com.enderveil.endupdate.world.ModWorldgen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EndUpdateMod implements ModInitializer {
    public static final String MOD_ID = "endupdate";
    public static EndUpdateConfig CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = EndUpdateConfig.load();
        ModBlocks.register();
        ModItems.register();
        ModEffects.register();
        ModEntities.register();
        ModFeatures.register();
        ModWorldgen.register();

        ServerTickEvents.END_SERVER_TICK.register(this::tick);
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!(world instanceof ServerWorld serverWorld)) {
                return ActionResult.PASS;
            }
            ItemStack stack = player.getStackInHand(hand);
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            if (state.isOf(ModBlocks.ANCIENT_ALTAR) && stack.isOf(ModItems.BOSS_SIGIL)) {
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                EnderHeartEntity boss = ModEntities.ENDER_HEART.create(serverWorld, SpawnReason.MOB_SUMMONED);
                if (boss != null) {
                    boss.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, player.getYaw(), 0.0F);
                    boss.setCustomName(Text.translatable("entity.endupdate.ender_heart"));
                    serverWorld.spawnEntity(boss);
                    serverWorld.playSound(null, pos, SoundEvents.ENTITY_WITHER_SPAWN, player.getSoundCategory(), 1.0F, 0.9F);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

    private void tick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            if (world.getRegistryKey() != World.END) continue;
            for (PlayerEntity player : world.getPlayers()) {
                if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.RELIC_HELMET)
                    && player.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.RELIC_CHESTPLATE)
                    && player.getEquippedStack(EquipmentSlot.LEGS).isOf(ModItems.RELIC_LEGGINGS)
                    && player.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.RELIC_BOOTS)) {
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.END_ATTUNEMENT, 220, 0, true, false, true));
                }
            }
        }
    }
}
