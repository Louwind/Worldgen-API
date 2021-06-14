package github.Louwind.worldgen.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import github.Louwind.worldgen.util.MetadataHandlerList;
import github.Louwind.Reload.core.util.json.DynamicRegistryAdapter;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;

import java.lang.reflect.Type;

public class MetadataHandlerListAdapter extends DynamicRegistryAdapter<MetadataHandlerList> {

    public static final Codec<MetadataHandlerList> CODEC = MetadataHandlerList.CODEC.fieldOf("metadata").codec();

    @Override
    protected MetadataHandlerList fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return CODEC.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, MetadataHandlerList src, Type typeOfSrc, JsonSerializationContext context) {
        return CODEC.encodeStart(registryReadingOps, src).get().orThrow();
    }

}