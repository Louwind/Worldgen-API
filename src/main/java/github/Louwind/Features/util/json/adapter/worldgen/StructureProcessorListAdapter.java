package github.Louwind.Features.util.json.adapter.worldgen;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.json.adapter.DynamicRegistryAdapter;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.dynamic.RegistryReadingOps;

import java.lang.reflect.Type;

public class StructureProcessorListAdapter extends DynamicRegistryAdapter<StructureProcessorList> {

    @Override
    protected StructureProcessorList fromJson(RegistryOps<JsonElement> registryOps, JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return StructureProcessorType.field_25876.parse(registryOps, json).get().orThrow();
    }

    @Override
    protected JsonElement toJson(RegistryReadingOps<JsonElement> registryReadingOps, StructureProcessorList src, Type typeOfSrc, JsonSerializationContext context) {
        return StructureProcessorType.field_25876.encodeStart(registryReadingOps, src).get().orThrow();
    }

}