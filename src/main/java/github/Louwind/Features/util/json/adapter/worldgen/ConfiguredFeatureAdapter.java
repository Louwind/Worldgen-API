package github.Louwind.Features.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Reload.core.util.json.DynamicRegistryAdapter;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.lang.reflect.Type;

public class ConfiguredFeatureAdapter extends DynamicRegistryAdapter<ConfiguredFeature<?, ?>> {

    @Override
    protected ConfiguredFeature<?, ?> fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return ConfiguredFeature.CODEC.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, ConfiguredFeature<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
        return ConfiguredFeature.CODEC.encodeStart(registryReadingOps, src).get().orThrow();
    }

}