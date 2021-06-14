package github.Louwind.worldgen.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Reload.core.util.json.DynamicRegistryAdapter;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;

import java.lang.reflect.Type;

public class ConfiguredSurfaceBuilderAdapter extends DynamicRegistryAdapter<ConfiguredSurfaceBuilder<?>> {

    @Override
    protected ConfiguredSurfaceBuilder<?> fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return ConfiguredSurfaceBuilder.CODEC.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, ConfiguredSurfaceBuilder<?> src, Type typeOfSrc, JsonSerializationContext context) {
        return ConfiguredSurfaceBuilder.CODEC.encodeStart(registryReadingOps, src).get().orThrow();
    }

}