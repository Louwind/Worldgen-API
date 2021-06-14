package github.Louwind.worldgen.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Reload.core.util.json.DynamicRegistryAdapter;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;

import java.lang.reflect.Type;

public class StructurePoolAdapter extends DynamicRegistryAdapter<StructurePool> {

    @Override
    protected StructurePool fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return StructurePool.CODEC.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, StructurePool src, Type typeOfSrc, JsonSerializationContext context) {
        return StructurePool.CODEC.encodeStart(registryReadingOps, src).get().orThrow();
    }

}