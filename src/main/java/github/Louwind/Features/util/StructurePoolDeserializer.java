package github.Louwind.Features.util;

import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StructurePoolDeserializer implements JsonDeserializer<StructurePool> {

    @Override
    public StructurePool deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Identifier name = FeaturesJsonHelper.getIdentifier(object, "name");
        Identifier terminators = FeaturesJsonHelper.getIdentifier(object, "terminators");

        List<Pair<StructurePoolElement, Integer>> elements = StreamSupport
                .stream(object.getAsJsonArray("elements").spliterator(), false)
                .map(JsonElement::getAsJsonObject)
                .map(obj -> {
                    StructurePoolElement poolElement = context.deserialize(obj, StructurePoolElement.class);
                    int weight = JsonHelper.getInt(obj, "weight");

                    return Pair.of(poolElement, weight);
                }).collect(Collectors.toList());

        return new StructurePool(name, terminators, elements);
    }

}
