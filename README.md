# Worldgen-API
A worldgen utility mod for Minecraft Fabric that allows to generate and register objects with JSON.

## Download
Add the repository link to your `build.gradle`.
```
repositories {
    maven {
        name = "Worldgen-API"
        url = "https://dl.cloudsmith.io/public/louwinds-mods/worldgen-api/maven"
    }
}
```

You can include the dependency as a Jar-in-Jar dependency.
```
dependencies {
    modImplementation "github.louwind.worldgen-api:Worldgen-API:<VERSION>"
    
    // Jar-in-Jar dependency (optional)
    include "github.louwind.worldgen-api:Worldgen-API:<VERSION>"
}
```
## Registering
Worldgen-API can register objects with JSON, similar to data packs. The JSON files must be located inside your <code>data</code> folder. The sintax can be found in the <a href="https://minecraft.fandom.com/wiki/Custom_world_generation">Wiki</a>. Worldgen-API supports the following objects:
<ul>
    <li>
        <article>
            <h3>Biomes</h3>
            Path: <code>worldgen/biome</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Chunk Generator Settings</h3>
            Path: <code>worldgen/chunk_generator_settings</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Configured Carvers</h3>
            Path: <code>worldgen/configured_carver</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Configured Surface Builder</h3>
            Path: <code>worldgen/configured_surface_builder</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Configured Features</h3>
            Path: <code>worldgen/configured_feature</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Configured Structure Features</h3>
            Path: <code>worldgen/configured_structure_feature</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Metadata Handler Lists</h3>
            Path: <code>worldgen/metadata</code>
        </article>
    </li>
    <li>
        <article>
            <h3>Structure Pools</h3>
            Path: <code>worldgen/template_pool</code>
        </article></li>
    <li>
        <article>
            <h3>Structure Processor Lists</h3>
            Path: <code>worldgen/processor_list</code>
        </article>
    </li>
</ul>

## Utilities
### Metadata Handler Lists
Represents a list of Metadata Handlers, which are instructions that can be referenced by an identifier inside a Jigsaw Block in <code>data</code> mode. When a <code>single_pool_element</code> is placed, the Jigsaw Blocks invoke the Metadata Handler List.

#### Examples
##### Loot Tables for Barrels
`worldgen:lootable_blockstate` places any block state at the Jigsaw Block position, and takes a Loot Behavior List. Worldgen-API provide common Loot Behavior Types, for Barrels, Chests, Lecterns and others. Check `worldgen:bookshelf_barrel`

```
{
  "metadata": [
    {
      "type": "worldgen:lootable_blockstate",
      "output_state": {
        "name": "minecraft:barrel",
        "properties": {
          "facing": "north",
          "open": "false"
        }
      },
      "loot_behaviors": [
        {
          "type": "worldgen:lootable_container",
          "loot_table": "worldgen:structures/bookshelf/barrel"
        }
      ]
    }
  ]
}
```

##### Loot Tables for Furnaces
Places a Furnace Block with a Loot Table at the Output Slot. See `worldgen:kitchen_furnace`
```
{
  "metadata": [
    {
      "type": "worldgen:lootable_blockstate",
      "output_state": {
        "name": "minecraft:furnace",
        "properties": {
          "facing": "east",
          "lit": "false"
        }
      },
      "loot_behaviors": [
        {
          "type": "worldgen:abstract_furnace",
          "loot_table": "worldgen:structures/kitchen/furnace",
          "slot": 2
        }
      ]
    }
  ]
}
```