package com.enderveil.endupdate.registry;

import com.enderveil.endupdate.EndUpdateMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
    public static final Item END_FRAGMENT = register("end_fragment", new Item(new Item.Settings()));
    public static final Item END_RELIC = register("end_relic", new Item(new Item.Settings()));
    public static final Item VOID_PEARL = register("void_pearl", new Item(new Item.Settings()));
    public static final Item CHORUS_RESIN = register("chorus_resin", new Item(new Item.Settings()));
    public static final Item ANCIENT_CORE = register("ancient_core", new Item(new Item.Settings()));
    public static final Item BOSS_SIGIL = register("boss_sigil", new Item(new Item.Settings()));
    public static final Item SHARDLING_SCALE = register("shardling_scale", new Item(new Item.Settings()));
    public static final Item GLOWMOTH_WING = register("glowmoth_wing", new Item(new Item.Settings()));

    public static final Item CHORUS_STONE_ITEM = registerBlockItem("chorus_stone", ModBlocks.CHORUS_STONE);
    public static final Item CHORUS_BRICKS_ITEM = registerBlockItem("chorus_bricks", ModBlocks.CHORUS_BRICKS);
    public static final Item CHORUS_TILES_ITEM = registerBlockItem("chorus_tiles", ModBlocks.CHORUS_TILES);
    public static final Item POLISHED_END_STONE_ITEM = registerBlockItem("polished_end_stone", ModBlocks.POLISHED_END_STONE);
    public static final Item RUNED_END_STONE_ITEM = registerBlockItem("runed_end_stone", ModBlocks.RUNED_END_STONE);
    public static final Item VOID_GLASS_ITEM = registerBlockItem("void_glass", ModBlocks.VOID_GLASS);
    public static final Item ENDER_LATTICE_ITEM = registerBlockItem("ender_lattice", ModBlocks.ENDER_LATTICE);
    public static final Item ANCIENT_ALTAR_ITEM = registerBlockItem("ancient_altar", ModBlocks.ANCIENT_ALTAR);
    public static final Item RELIC_ORE_ITEM = registerBlockItem("relic_ore", ModBlocks.RELIC_ORE);
    public static final Item END_BLOSSOM_ITEM = registerBlockItem("end_blossom", ModBlocks.END_BLOSSOM);
    public static final Item VOIDROOT_BLOCK_ITEM = registerBlockItem("voidroot_block", ModBlocks.VOIDROOT_BLOCK);

    public static final Item RELIC_SWORD = register("relic_sword", new SwordItem(ToolMaterials.NETHERITE, 4, -2.3F, new Item.Settings()));
    public static final Item RELIC_PICKAXE = register("relic_pickaxe", new PickaxeItem(ToolMaterials.NETHERITE, 1, -2.8F, new Item.Settings()));
    public static final Item RELIC_AXE = register("relic_axe", new AxeItem(ToolMaterials.NETHERITE, 6.0F, -3.0F, new Item.Settings()));
    public static final Item RELIC_SHOVEL = register("relic_shovel", new ShovelItem(ToolMaterials.NETHERITE, 1.5F, -3.0F, new Item.Settings()));
    public static final Item RELIC_HOE = register("relic_hoe", new net.minecraft.item.HoeItem(ToolMaterials.NETHERITE, -2, -1.0F, new Item.Settings()));

    public static final Item RELIC_HELMET = register("relic_helmet", new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item RELIC_CHESTPLATE = register("relic_chestplate", new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item RELIC_LEGGINGS = register("relic_leggings", new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item RELIC_BOOTS = register("relic_boots", new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.BOOTS, new Item.Settings()));

    private static Item registerBlockItem(String name, net.minecraft.block.Block block) {
        return register(name, new BlockItem(block, new Item.Settings()));
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(EndUpdateMod.MOD_ID, name), item);
    }

    public static void register() {
    }
}
