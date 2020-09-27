package github.Louwind.Features.util.deserializer;

import com.google.gson.*;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;

public class StructureProcessorRuleDeserializer implements JsonDeserializer<StructureProcessorRule>  {

    @Override
    public StructureProcessorRule deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        RuleTest test = JsonHelper.deserialize(object, "test", AlwaysTrueRuleTest.INSTANCE, context, RuleTest.class);
        RuleTest test1 = JsonHelper.deserialize(object, "test1", AlwaysTrueRuleTest.INSTANCE, context, RuleTest.class);
        BlockState state = FeaturesJsonHelper.getBlockState(object, "blockstate");

        return new StructureProcessorRule(test, test1, state);
    }

}
