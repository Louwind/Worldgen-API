package github.Louwind.Features.util.json.adapter;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import net.minecraft.structure.pool.StructurePool;

import java.lang.reflect.Type;

public class StructurePoolAdapter implements JsonDeserializer<StructurePool>, JsonSerializer<StructurePool> {

    @Override
    public StructurePool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return StructurePool.CODEC.parse(JsonOps.INSTANCE, json).get().orThrow();
    }

    @Override
    public JsonElement serialize(StructurePool src, Type typeOfSrc, JsonSerializationContext context) {
        return StructurePool.CODEC.encodeStart(JsonOps.INSTANCE, src).get().orThrow();
    }

}