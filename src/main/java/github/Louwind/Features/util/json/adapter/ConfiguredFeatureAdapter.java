package github.Louwind.Features.util.json.adapter;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.lang.reflect.Type;

public class ConfiguredFeatureAdapter implements JsonDeserializer<ConfiguredFeature<?, ?>>, JsonSerializer<ConfiguredFeature<?, ?>> {

    @Override
    public ConfiguredFeature<?, ?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ConfiguredFeature.CODEC.parse(JsonOps.INSTANCE, json).get().orThrow();
    }

    @Override
    public JsonElement serialize(ConfiguredFeature<?, ?> src, Type typeOfSrc, JsonSerializationContext context) {
        return ConfiguredFeature.CODEC.encodeStart(JsonOps.INSTANCE, src).get().orThrow();
    }

}