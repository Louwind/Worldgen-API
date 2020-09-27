package github.Louwind.Features.util.deserializer;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;

import java.lang.reflect.Type;

public class StructureProcessorListDeserializer implements JsonDeserializer<StructureProcessorList>  {

    @Override
    public StructureProcessorList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        StructureProcessor[] processors = FeaturesJsonHelper.getProcessors(json.getAsJsonObject(), context, "processors");

        return new StructureProcessorList(ImmutableList.copyOf(processors));
    }

}
