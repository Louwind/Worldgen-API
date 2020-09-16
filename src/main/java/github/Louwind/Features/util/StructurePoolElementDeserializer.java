package github.Louwind.Features.util;

import com.google.gson.*;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;

public class StructurePoolElementDeserializer implements JsonDeserializer<StructurePoolElement> {

    @Override
    public StructurePoolElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonSyntaxException {
        JsonObject object = json.getAsJsonObject();

        String structure = JsonHelper.getString(object, "structure");
        StructurePool.Projection projection = FeaturesJsonHelper.getProjection(object, "projection");
        StructureProcessorList processors = FeaturesJsonHelper.getProcessors(object, context,"processors");

        return StructurePoolElement.method_30435(structure, processors).apply(projection);
    }

}
