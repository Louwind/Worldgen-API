package github.Louwind.Features.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Reload.core.util.json.DynamicRegistryAdapter;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;
import net.minecraft.world.biome.Biome;

import java.lang.reflect.Type;

public class BiomeAdapter extends DynamicRegistryAdapter<Biome> {

    @Override
    protected Biome fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return Biome.CODEC.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, Biome src, Type typeOfSrc, JsonSerializationContext context) {
        return Biome.CODEC.encodeStart(registryReadingOps, src).get().orThrow();
    }

}