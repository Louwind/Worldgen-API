package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

import static net.minecraft.util.registry.BuiltinRegistries.CHUNK_GENERATOR_SETTINGS;

public class ChunkGeneratorSettingsReloadListener extends SimpleJsonReloadListener<ChunkGeneratorSettings> {

    private static final Gson GSON = FeaturesGsons.getChunkGeneratorSettingsGsonBuilder().create();

    public ChunkGeneratorSettingsReloadListener() {
        super(GSON, ChunkGeneratorSettings.class, (SimpleRegistry<ChunkGeneratorSettings>) CHUNK_GENERATOR_SETTINGS, "worldgen/chunk_generator_settings");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:chunk_generator_settings");
    }

}
