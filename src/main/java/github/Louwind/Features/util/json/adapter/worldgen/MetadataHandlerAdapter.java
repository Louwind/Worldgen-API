package github.Louwind.Features.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Reload.core.util.json.DynamicRegistryAdapter;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;

import java.lang.reflect.Type;

public class MetadataHandlerAdapter extends DynamicRegistryAdapter<MetadataHandler> {

    @Override
    protected MetadataHandler fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return MetadataHandler.CODEC.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, MetadataHandler src, Type typeOfSrc, JsonSerializationContext context) {
        return MetadataHandler.CODEC.encodeStart(registryReadingOps, src).get().orThrow();
    }

}