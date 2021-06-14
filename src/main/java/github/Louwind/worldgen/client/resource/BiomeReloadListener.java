package github.Louwind.worldgen.client.resource;

import com.google.gson.Gson;
import github.Louwind.worldgen.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.biome.Biome;

import static net.minecraft.util.registry.BuiltinRegistries.BIOME;

public class BiomeReloadListener extends SimpleJsonReloadListener<Biome> {

    private static final Gson GSON = FeaturesGsons.getBiomeGsonBuilder().create();

    public BiomeReloadListener() {
        super(GSON, Biome.class, (SimpleRegistry<Biome>) BIOME, "worldgen/biome");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:biome");
    }

}
