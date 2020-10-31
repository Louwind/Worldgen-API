package github.Louwind.Features.impl.util.serializer.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

public class RandomBlockMatchRuleTestSerializer implements JsonSerializer<RandomBlockMatchRuleTest> {

    @Override
    public void toJson(JsonObject json, RandomBlockMatchRuleTest object, JsonSerializationContext context) {

    }

    @Override
    public RandomBlockMatchRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        Block block = FeaturesJsonHelper.getBlock(json, "block");
        float probability = JsonHelper.getFloat(json, "probability");

        return new RandomBlockMatchRuleTest(block, probability);
    }

}
