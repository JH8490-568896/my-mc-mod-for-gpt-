package com.enderveil.endupdate.effect;

import com.enderveil.endupdate.EndUpdateMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEffects {
    public static final StatusEffect END_ATTUNEMENT = register("end_attunement", new StatusEffect(StatusEffectCategory.BENEFICIAL, 0x9c7cff) {});

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, Identifier.of(EndUpdateMod.MOD_ID, name), effect);
    }

    public static void register() {
    }
}
