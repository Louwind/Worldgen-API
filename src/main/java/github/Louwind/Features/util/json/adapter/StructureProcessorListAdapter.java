package github.Louwind.Features.util.json.adapter;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;

import java.lang.reflect.Type;

public class StructureProcessorListAdapter implements JsonDeserializer<StructureProcessorList>, JsonSerializer<StructureProcessorList> {

    @Override
    public StructureProcessorList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return StructureProcessorType.field_25876.parse(JsonOps.INSTANCE, json).get().orThrow();
    }

    @Override
    public JsonElement serialize(StructureProcessorList src, Type typeOfSrc, JsonSerializationContext context) {
        return StructureProcessorType.field_25876.encodeStart(JsonOps.INSTANCE, src).get().orThrow();
    }

}