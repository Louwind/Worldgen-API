package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_SURFACE_BUILDER;

public class ConfiguredSurfaceBuilderReloadListener extends SimpleJsonReloadListener<ConfiguredSurfaceBuilder<?>> {

    private static final Gson GSON = FeaturesGsons.getConfiguredSurfaceBuilderGsonBuilder().create();

    public ConfiguredSurfaceBuilderReloadListener() {
        super(GSON, ConfiguredSurfaceBuilder.class, (SimpleRegistry<ConfiguredSurfaceBuilder<?>>) CONFIGURED_SURFACE_BUILDER, "worldgen/configured_surface_builder");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:configured_surface_builder");
    }

}
