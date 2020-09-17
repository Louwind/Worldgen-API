package github.Louwind.Features.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.List;

// TODO error handling
public class FeaturesJsonHelper {

    public static Block getBlock(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return Registry.BLOCK.get(id);
    }

    public static Identifier getIdentifier(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return new Identifier(id);
    }

    public static StructurePool.Projection getProjection(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return StructurePool.Projection.getById(id);
    }

    public static StructureProcessorList getProcessors(JsonObject object, JsonDeserializationContext context, String name) {
        StructureProcessor[] processors = JsonHelper.deserialize(object, name, new StructureProcessor[]{}, context, StructureProcessor[].class);
        List<StructureProcessor> list = Arrays.asList(processors);

        return new StructureProcessorList(list);
    }

}
