package github.Louwind.Features.util;

import com.google.common.collect.ImmutableList;
import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;
import java.util.Arrays;
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
        StructureProcessor[] defaultProcessors = FeaturesJsonHelper.getProcessors(object, context, "processors");

        List list = StreamSupport
                .stream(object.getAsJsonArray("elements").spliterator(), false)
                .map(JsonElement::getAsJsonObject)
                .map(obj -> {
                    String structure = JsonHelper.getString(obj, "structure");
                    int weight = JsonHelper.getInt(obj, "weight");

                    StructureProcessor[] processors = FeaturesJsonHelper.getProcessors(obj, defaultProcessors, context, "processors");
                    StructureProcessorList processorList = new StructureProcessorList(Arrays.asList(processors));

                    return Pair.of(StructurePoolElement.method_30426(structure, processorList), weight);
                }).collect(Collectors.toList());

        return new StructurePool(name, terminators, ImmutableList.copyOf(list), projection);
    }

}
