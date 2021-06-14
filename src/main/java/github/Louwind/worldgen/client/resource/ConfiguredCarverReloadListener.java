package github.Louwind.worldgen.client.resource;

import com.google.gson.Gson;
import github.Louwind.worldgen.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.carver.ConfiguredCarver;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_CARVER;

public class ConfiguredCarverReloadListener extends SimpleJsonReloadListener<ConfiguredCarver<?>> {

    private static final Gson GSON = FeaturesGsons.getConfiguredCarverGsonBuilder().create();

    public ConfiguredCarverReloadListener() {
        super(GSON, ConfiguredCarver.class, (SimpleRegistry<ConfiguredCarver<?>>) CONFIGURED_CARVER, "worldgen/configured_carver");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:configured_carver");
    }

}
