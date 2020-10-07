package github.Louwind.Features.util.deserializer;

import com.google.common.collect.ImmutableList;
import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import github.Louwind.Features.pool.element.FeaturesPoolElementFunction;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.pool.StructurePool;
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
        StructurePool.Projection projection = FeaturesJsonHelper.getProjection(object, "projection");

        List list = StreamSupport
                .stream(object.getAsJsonArray("elements").spliterator(), false)
                .map(JsonElement::getAsJsonObject)
                .map(obj -> {
                    FeaturesPoolElementFunction function = JsonHelper.deserialize(obj, "element", context, FeaturesPoolElementFunction.class);
                    int weight = JsonHelper.getInt(obj, "weight");

                    return Pair.of(function.get(), weight);
                }).collect(Collectors.toList());

        return new StructurePool(name, terminators, ImmutableList.copyOf(list), projection);
    }

}
