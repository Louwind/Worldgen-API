package github.Louwind.Features.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.Arrays;
import java.util.List;

// TODO error handling
public class FeaturesJsonHelper {

    public static Identifier getIdentifier(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return new Identifier(id);
    }

    public static StructurePool.Projection getProjection(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return StructurePool.Projection.getById(id);
    }

    public static StructureProcessorList getProcessors(JsonObject object, JsonDeserializationContext context, String name) {
        FeatureProcessor[] processors = JsonHelper.deserialize(object, name, new FeatureProcessor[] {}, context, FeatureProcessor[].class);
        List<StructureProcessor> list = Arrays.asList(processors);

        return new StructureProcessorList(list);
    }

}
