package github.Louwind.Features.impl.util.serializer;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.JsonSerializer;

public class RuleStructureProcessorSerializer implements JsonSerializer<RuleStructureProcessor>  {

    @Override
    public void toJson(JsonObject json, RuleStructureProcessor object, JsonSerializationContext context) {

    }

    @Override
    public RuleStructureProcessor fromJson(JsonObject json, JsonDeserializationContext context) {
        StructureProcessorRule[] rules = FeaturesJsonHelper.getProcessorRules(json, context, "rules");

        return new RuleStructureProcessor(ImmutableList.copyOf(rules));
    }

}
