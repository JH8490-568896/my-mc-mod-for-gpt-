# End Update

A Fabric expansion mod for Minecraft Java Edition 1.21.11 that turns The End into a full late-game progression arc.

## Target

- Minecraft: 1.21.11
- Fabric Loader: 0.18.1
- Fabric API: 0.136.0+1.21.11
- Loom: 1.14-SNAPSHOT
- Java: 21

## Included systems

- End-themed blocks and relic materials
- New tools and armor progression
- New hostile and ambient mobs
- A multi-stage boss encounter
- End structures and worldgen hooks
- Recipes, loot tables, tags, advancements, and client/server entrypoints
- GitHub Actions CI that builds the JAR

## Missing sections to finish

### 1. Client rendering and visual setup

The repo still needs the client-side registration layer for entity renderers, model layers, particle factories, and any boss or mob visual wiring.

### 2. Full data pack coverage

The repo still needs the remaining blockstates, block and item models, textures, loot tables, tags, recipes, and advancement branches for every new block and item.

### 3. Worldgen and structure content

The repo still needs the full biome and structure data set: configured and placed features, structure pools, biome integration, and the terrain/resource generation balance.

## Build

```bash
./gradlew build
```

## Layout

```text
src/main/java/com/enderveil/endupdate
src/main/resources/assets/endupdate
src/main/resources/data/endupdate
.github/workflows/ci.yml
```

## Gameplay loop

1. Explore The End and collect shards, resin, and void pearls.
2. Craft relic materials and upgrade into End relic gear.
3. Use an ancient altar and a boss sigil to summon the Ender Heart.
4. Defeat the boss to unlock the strongest part of the progression chain.

## Notes

This is a starter codebase designed to be expanded later. It keeps registration centralized, separates client entrypoints, and uses data-driven resources where practical.
